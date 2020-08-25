/*
 *
 *
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.symphony.oss.canon.json;

import java.io.IOException;
import java.util.function.Consumer;

import com.symphony.oss.canon.json.model.JsonArray;
import com.symphony.oss.canon.json.model.JsonDom;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonNull;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon.json.model.JsonString;
import com.symphony.oss.canon.json.model.JsonValue;
import com.symphony.oss.commons.fault.CodingFault;

/**
 * A parser for the JSON subset of YAML input.
 * 
 * @author Bruce Skingle
 *
 */
public class YamlParser extends Parser
{
  static final String   START_DOCUMENT = "---";
  static final String   END_DOCUMENT   = "...";

  static final char     MAPPING_START  = '{';
  static final char     MAPPING_END    = '}';
  static final char     SEQUENCE_START = '[';
  static final char     SEQUENCE_END   = ']';
  static final char     SEQUENCE_ENTRY = '-';
  static final char     MAPPING_KEY    = '?';
  static final char     MAPPING_ENTRY  = ':';
  static final char     COLLECT_ENTRY  = ',';
  static final char     DOUBLE_QUOTE   = '\"';
  static final char     SINGLE_QUOTE   = '\'';
  static final char     COMMENT        = '#';
  static final char     LITERAL        = '|';
  static final char     FOLDED         = '>';
  static final char     SPACE          = ' ';
  
  private JsonDom.ParserBuilder       domBuilder_     = new JsonDom.ParserBuilder();
//  private int lineIndentLevel_;
  
  static final ScannerText NullScannerText = new ScannerText(0, null);
  
  static class ScannerText
  {
    int initCol_;
    String text_;
    
    ScannerText(int initCol, String text)
    {
      initCol_ = initCol;
      text_ = text;
    }
  }

  YamlParser(AbstractBuilder<?, ?> builder)
  {
    super(builder);
  }

  /**
   * The main method to parse the input.
   * 
   * The returned DOM will be one of JsonObjectDom, JsonArrayDom or JsonInvalidDom. In any case the caller should
   * check that the getErrors() method returns an empty list, if there are errors then the returned dom may be 
   * incomplete. The parser attempts to recover from errors so that it can return multiple error messages in
   * a single pass.
   * 
   * @return A JSON dom object.
   */
  public JsonDom parse()
  {
    try
    {
      readHeader();
      
      YamlDocument document = new YamlDocument();

      parse(document);
      
      return document.buildDom();
    }
    catch(IOException e)
    {
      domBuilder_.withError(new ParserException("Unable to read input (" + e.getMessage() + ")", this, e));
    }
   
    return domBuilder_.build();
  }
  
  private IParserContext parse(YamlCollection collection) throws IOException
  {
    IParserContext context = getContext();
    
    while(lineBuffer_ != null)
    {
      ScannerText key = NullScannerText;
      
      try
      {
        while(lineBuffer_ != null && col_ < lineBuffer_.length())
        {
          while(col_ < lineBuffer_.length() && isSpace(lineBuffer_.charAt(col_)))
          {
            col_++;
          }

          context = getContext();
          
          boolean couldBeBlock = (col_ == lineBuffer_.length() - 1 || (col_ <= lineBuffer_.length() - 2 && lineBuffer_.charAt(col_ + 1) == SPACE));
          if(collection.getIndentLevel() == null)
          {
            collection.setIndentLevel(context.getCol());
          }
          
          if(couldBeBlock && lineBuffer_.charAt(col_) == SEQUENCE_ENTRY)
          {
            if(col_ > collection.getIndentLevel())
            {
              collection.validate(key.text_, context);
              YamlCollection newCollection = new YamlCollection(col_, YamlCollectionType.SEQUENCE, canonicalize_);
              
              parse(newCollection);
              
              collection.put(key.text_, newCollection.build(context), context);
              
              if(col_ > collection.getIndentLevel())
              {
                throw new SyntaxErrorException("Unexpected indent level", this);
              }
              else if(col_ < collection.getIndentLevel())
              {
                return context;
              }
            }
            else if(col_ == collection.getIndentLevel())
            {
              col_+=2;
              
              if(col_ < lineBuffer_.length())
              {
                try
                {
                  collection.setType(YamlCollectionType.SEQUENCE, this);
                  collection.put(key.text_, getValue(YamlStyle.BLOCK, col_), getContext());
                }
                catch(ParserException e)
                {
                  readLine();
                  throw e;
                }
              }
            }
            else // less than
            {
              return context;
            }
          }
          else
          {
            try
            {
              key = getMappingKey();
              
              if(key == NullScannerText)
              {
                // scalar
                collection.put(key.text_, getValue(YamlStyle.BLOCK, key.initCol_), context);
              }
              else
              {
                if(collection.getIndentLevel() == null)
                {
                  System.err.println("COLLECTION HAS NO LEVEL");
                  collection.setIndentLevel(context.getCol());
                }
                if(context.getCol() > collection.getIndentLevel())
                {
                  System.err.println("NEW LEVEL");
                  throw new SyntaxErrorException("Unexpected indent level", context);
                }
                else if(context.getCol() == collection.getIndentLevel())
                {
                  System.err.println("THIS LEVEL");
                  if(collection.getType() == YamlCollectionType.SEQUENCE && key.text_ != null)
                  {
                    /*
                     *  special rule because existing editors allow the content of a mapping entry which is a sequence
                     *  to be indented to the same level as the mapping entry. This seems to be a breach of the yaml 
                     *  specification which says (section 6.1) 
                     *  
                     *    Each node must be indented further than its parent node.
                     */
                    col_ = key.initCol_;
                    return context;
                  }
                }
                else
                {
                  System.err.println("PREV LEVEL");

                  col_ = key.initCol_;
                  return context;
                }
                  
                  
                if(col_ < lineBuffer_.length())
                {
                  collection.put(key.text_, getValue(YamlStyle.BLOCK, key.initCol_), context);
                }
                else
                {
                  //nested object
                  YamlCollection newCollection = new YamlCollection(null, YamlCollectionType.COLLECTION, canonicalize_); 
                  
                  context = parse(newCollection);
                  
                  collection.put(key.text_, newCollection.build(context), context);

                  if(context.getCol() < collection.getIndentLevel())
                  {
                    return context;
                  }
                }
              }
            }
            catch(ParserException e)
            {
              readLine();
              throw e;
            }
          }
        }
        // End of line

        readLine();
      }
      catch (ParserException e)
      {
        // Skip to the next line and try again.
        domBuilder_.withError(e);
      }
    }
    return context;
  }
  
  

  private ScannerText getMappingKey() throws IOException, ParserException
  {
    int initCol = col_;
    
    // skip any whitespace
    while(col_ < lineBuffer_.length() && isSpace(lineBuffer_.charAt(col_)))
    {
      col_++;
    }
    
    if(col_ < lineBuffer_.length())
    {
      switch(lineBuffer_.charAt(col_))
      {
        case DOUBLE_QUOTE:
        {
          int endCol = col_ + 1;
          
          while(endCol < lineBuffer_.length() && lineBuffer_.charAt(endCol) != DOUBLE_QUOTE)
          {
            if(endCol < lineBuffer_.length() - 1 && lineBuffer_.charAt(endCol) == '\\')
              endCol++;
            
  
            endCol++;
            
          }
          
          if(endCol < lineBuffer_.length())
          {
            while(endCol < lineBuffer_.length() && isSpace(lineBuffer_.charAt(endCol)))
            {
              endCol++;
            }
            
            endCol++; // point at the next char after what we matched
            if(endCol < lineBuffer_.length() && lineBuffer_.charAt(endCol) == MAPPING_ENTRY)
            {
              col_++;
              String key = getDoubleQuotedString();
              
              if(key.length() == 0)
              {
                col_ = initCol;
                return NullScannerText;
              }
              col_ = endCol + 1;
  
              return new ScannerText(initCol, key);
            }
          }
          return NullScannerText;
        }
          
        case SINGLE_QUOTE:
        {
          int endCol = col_ + 1;
          
          while(endCol < lineBuffer_.length())
          {
            if(lineBuffer_.charAt(endCol) == SINGLE_QUOTE)
            {
              if(endCol < lineBuffer_.length() - 1 && lineBuffer_.charAt(endCol + 1) == SINGLE_QUOTE)
              {
                endCol++;
              }
              else
              {
                break;
              }
            }
            
            endCol++;
          }
          
          if(endCol < lineBuffer_.length())
          {
            while(endCol < lineBuffer_.length() && isSpace(lineBuffer_.charAt(endCol)))
            {
              endCol++;
            }
            
  
            endCol++; // point at the next char after what we matched
            if(endCol < lineBuffer_.length() && lineBuffer_.charAt(endCol) == MAPPING_ENTRY)
            {
              col_++;
              String key = getSingleQuotedString();
              
              if(key.length() == 0)
              {
                col_ = initCol;
                return NullScannerText;
              }
              
              col_ = endCol + 1;
  
              return new ScannerText(initCol, key);
            }
          }
          return NullScannerText;
        }
        
        case SEQUENCE_START:
        case MAPPING_START:
          return NullScannerText;
          
        default:
          for(int i=col_ ; i<lineBuffer_.length() ; i++)
          {
            if(lineBuffer_.charAt(i) == MAPPING_ENTRY &&
                (
                    (lineBuffer_.length() > i+1 && isSpace(lineBuffer_.charAt(i + 1)) ||
                        (lineBuffer_.length() == i+1)
                )   
                    ))
            {
              String key = lineBuffer_.substring(col_, i++);
              
              if(key.length() == 0)
              {
                col_ = initCol;
                return NullScannerText;
              }
              
              col_ = i;
  
              return new ScannerText(initCol, key);
            }
          }
      }
    }
    return NullScannerText;
  }

  private class YamlDocument extends YamlCollection
  {
    private JsonArray array_;
    private JsonObject object_;

    private YamlDocument()
    {
      super(0, YamlCollectionType.COLLECTION, canonicalize_);
    }

    @Override
    void validate(String name, IParserContext context) throws ParserException
    {
      if(array_ != null || object_ != null)
      {
        throw new SyntaxErrorException("Multiple top level entities not permitted", context);
      }
      
      super.validate(name, context);
    }

    @Override
    void put(String name, JsonDomNode value, IParserContext context) throws ParserException
    {
      if(array_ != null || object_ != null)
      {
        throw new SyntaxErrorException("Multiple top level entities not permitted", context);
      }
      
      if(type_ == YamlCollectionType.COLLECTION && name == null)
      {
        if(value instanceof JsonArray)
        {
          type_ = YamlCollectionType.SEQUENCE;
          array_ = (JsonArray) value;
        }
        else if(value instanceof JsonObject)
        {
          type_ = YamlCollectionType.MAPPING;
          object_ =  (JsonObject) value;
          return;
        }
      }
      
      super.put(name, value, context);
    }

    @Override
    JsonDomNode build(IParserContext context)
    {
      throw new CodingFault("abstract method");
    }
    
    JsonDom buildDom()
    {
      if(array_ != null)
      {
        domBuilder_.withArray(array_);
      }
      else if(object_ != null)
      {
        domBuilder_.withObject(object_);
      }
      else if(arrayBuilder_ != null)
      {
        domBuilder_.withArray(arrayBuilder_.build());
      }
      else if(objectBuilder_ != null)
      {
        domBuilder_.withObject(objectBuilder_.build());
      }
      
      return domBuilder_.build();
    }
  }
  
  JsonDomNode getValue(YamlStyle style, int indentLevel) throws IOException, ParserException
  {
    IParserContext context = getContext();

    try
    {
      ScannerText key = style == YamlStyle.BLOCK ? getMappingKey() : NullScannerText;
      
      if(key.text_ == null)
      {
        char token = getCharToken();
        switch(token)
        {
          case DOUBLE_QUOTE:
          {
            String stringValue = getDoubleQuotedString();
            return new JsonString.Builder()
                .withValue(stringValue)
                .withContext(context)
                .build();
          }
            
          case SINGLE_QUOTE:
          {
            String stringValue = getSingleQuotedString();
            return new JsonString.Builder()
                .withValue(stringValue)
                .withContext(context)
                .build();
          }
          case MAPPING_START:
            return getFlowMapping();
            
          case SEQUENCE_START:
            return getFlowSequence();
            
          case EOF:
            return JsonNull.INSTANCE;
            
          default:
            col_--;
            return getBareValue(style, context, indentLevel);
        }
      }
      else
      {
        JsonDomNode value = getValue(style, indentLevel);
        
        return new JsonObject.Builder()
          .withCanonicalize(canonicalize_)
          .with(key.text_, value)
          .build();
      }
    }
    catch(RuntimeException e)
    {
      throw new ParserException(e.toString(), context, e);
    }
  }
  
  
  String getDoubleQuotedString() throws IOException, ParserException
  {
    IParserContext context = getContext();
    StringBuilder s = new StringBuilder();
    boolean       readNextLine = true;
    boolean       firstLine = true;
    boolean       addSpace = false;
      
    while(readNextLine)
    {
      if(firstLine)
      {
        firstLine = false;
      }
      else
      {
        int l=0;
        do
        {
          readLine();
          l++;
          
          if(lineBuffer_ == null)
          {
            throw new ParserException("Unexpected end of file in double quoted string", context);
          }
        } while(lineBuffer_.trim().length() == 0);
        

        if(l > 1)
          s.append('\n');
        else if(addSpace)
          s.append(SPACE);
        
        
        while(col_ < lineBuffer_.length() && lineBuffer_.charAt(col_) == SPACE)
          col_++;
      }
      
      int endCol = col_;
      
      while(endCol < lineBuffer_.length() && lineBuffer_.charAt(endCol) != DOUBLE_QUOTE)
      {
        if(endCol < lineBuffer_.length() - 1 && lineBuffer_.charAt(endCol) == '\\')
          endCol++;

        endCol++;
      }
      
      if(endCol == lineBuffer_.length())
      {
        readNextLine = true;
        
        if(endCol > col_ &&                                                   // line is not empty and 
            lineBuffer_.charAt(endCol - 1) == '\\' &&                         // last character is a back slash
            (endCol == col_ + 1 || lineBuffer_.charAt(endCol - 2) != '\\'))   // penultimate character is not a back slash
        {
          endCol--;
          addSpace=false;
        }
        else
        {
          while(endCol > col_ && isSpace(lineBuffer_.charAt(endCol - 1)))
          {
            endCol--;
          }
          addSpace=true;
        }
      }
      else
      {
        readNextLine = false;
        addSpace=false;
      }

      while(col_ < endCol)
      {
        char c = getChar();
        
        if(c < ' ')
        {
          switch(c)
          {
            case '\t':
            case '\r':
              break;
              
            default:
              throw new SyntaxErrorException("Invalid character \"" + escapeChar(c) + "\" in double quoted string", this);
          }
        }
        
        if(c == '\\')
        {
          c = getChar();
          
          switch(c)
          {
            case '"':
              s.append('"');
              break;
              
            case '\\':
              s.append('\\');
              break;
              
            case '/':
              s.append('/');
              break;
              
            case 'a':
              s.append('\u0007');
              break;
              
            case 'b':
              s.append('\b');
              break;
              
            case 'f':
              s.append('\f');
              break;
              
            case 'n':
              s.append('\n');
              break;
              
            case 'r':
              s.append('\r');
              break;
              
            case 't':
              s.append('\t');
              break;
              
            case 'u':
              try
              {
                s.append((char)getHexNumber(4));
              }
              catch (ParserException e)
              {
                domBuilder_.withError(e);
                skipTo(DOUBLE_QUOTE);
                
                return s.toString();
              }
              
            default:
              domBuilder_.withError(new SyntaxErrorException("Invalid escape sequence \"\\" + escapeChar(c) + "\"", this));
              skipTo(DOUBLE_QUOTE);
              
              return s.toString();
          }
        }
        else
        {
          s.append(c);
        }
      }
    }
    
    col_++; // consume the closing double quote
    return s.toString();
  }
  

  String getSingleQuotedString() throws IOException, ParserException
  {
    IParserContext context = getContext();
    StringBuilder s = new StringBuilder();
    boolean       readNextLine = true;
    boolean       firstLine = true;
    boolean       addSpace = false;
      
    while(readNextLine)
    {
      if(firstLine)
      {
        firstLine = false;
      }
      else
      {
        int l=0;
        do
        {
          readLine();
          l++;
          
          if(lineBuffer_ == null)
          {
            throw new ParserException("Unexpected end of file in single quoted string", context);
          }
        } while(lineBuffer_.trim().length() == 0);
        

        if(l > 1)
          s.append('\n');
        else if(addSpace)
          s.append(SPACE);
        
        
        while(col_ < lineBuffer_.length() && lineBuffer_.charAt(col_) == SPACE)
          col_++;
      }
      
      int endCol = col_;
      
      while(endCol < lineBuffer_.length())
      {
        if(lineBuffer_.charAt(endCol) == SINGLE_QUOTE)
        {
          if(endCol < lineBuffer_.length() - 1 && lineBuffer_.charAt(endCol + 1) == SINGLE_QUOTE)
          {
            endCol++;
          }
          else
          {
            break;
          }
        }
        
        endCol++;
      }
      
      if(endCol == lineBuffer_.length())
      {
        readNextLine = true;
        
        if(endCol > col_ &&                                                   // line is not empty and 
            lineBuffer_.charAt(endCol - 1) == '\\' &&                         // last character is a back slash
            (endCol == col_ + 1 || lineBuffer_.charAt(endCol - 2) != '\\'))   // penultimate character is not a back slash
        {
          endCol--;
          addSpace=false;
        }
        else
        {
          while(endCol > col_ && isSpace(lineBuffer_.charAt(endCol - 1)))
          {
            endCol--;
          }
          addSpace=true;
        }
      }
      else
      {
        readNextLine = false;
        addSpace=false;
      }

      while(col_ < endCol)
      {
        char c = getChar();
        
        if(c < ' ')
        {
          switch(c)
          {
            case '\t':
            case '\r':
              break;
              
            default:
              throw new SyntaxErrorException("Invalid character \"" + escapeChar(c) + "\" in double single string", this);
          }
        }
        
        if(c == '\'')
        {
          c = getChar();
          if(c != '\'')
            throw new SyntaxErrorException("Unexpected single quote in single quoted string", this);
        }
        s.append(c);
      }
      
    }
    
    col_++; // consume the closing double quote
    return s.toString();
  }
  
  private JsonDomNode getFlowMapping() throws IOException
  {
    JsonObject.Builder  builder = new JsonObject.Builder().withCanonicalize(canonicalize_);
    
    char token = getCharToken();

    if(token == MAPPING_END)
      return builder.build();
    
    // back up over the first token of the value.
    col_--;
    
    while(true)
    {
      try
      {
        IParserContext context = getContext();
        ScannerText key = getMappingKey();
        
        if(key == null)
          throw new SyntaxErrorException("Mapping key expected", this);
        
        builder.with(context, key.text_, getValue(YamlStyle.FLOW, key.initCol_));
        
        if(MAPPING_END == expectCharToken(COLLECT_ENTRY, MAPPING_END))
        {
          return builder.build();
        }
        
        // skip whitespace and read more lines if necessary
        token = getCharToken();
        col_--;
      }
      catch (ParserException e)
      {
        domBuilder_.withError(e);
        
        do
        {
          token = getCharToken();
        } while(token != COLLECT_ENTRY && token != MAPPING_END);
        
        if(token == MAPPING_END)
          return builder.build();
      }
    }
  }
  
  private JsonDomNode getFlowSequence() throws IOException
  {
    JsonArray.Builder  builder = new JsonArray.Builder();
    
    char token = getCharToken();

    if(token == SEQUENCE_END)
      return builder.build();
    
    // back up over the first token of the value.
    col_--;
    
    while(true)
    {
      try
      {
        builder.with(getValue(YamlStyle.FLOW, col_));
        
        if(SEQUENCE_END == expectCharToken(COLLECT_ENTRY, SEQUENCE_END))
        {
          return builder.build();
        }
      }
      catch (ParserException e)
      {
        domBuilder_.withError(e);
        
        do
        {
          token = getCharToken();
        } while(token != COLLECT_ENTRY && token != SEQUENCE_END);
        
        if(token == SEQUENCE_END)
          return builder.build();
      }
    }
  }

  private JsonDomNode getBareValue(YamlStyle style, IParserContext context, int indentLevel) throws SyntaxErrorException, IOException
  {
    return JsonValue.valueOf(getBareString(style, context, indentLevel));
  }
  
  String getBareString(YamlStyle style, IParserContext context, int indentLevel) throws SyntaxErrorException, IOException
  {
    StringBuilder builder = new StringBuilder();
    StringBuilder trailingBlank = null;
    boolean literal = false;
    YamlChompType chomp = YamlChompType.CLIP;
    
    switch(lineBuffer_.charAt(col_))
    {
      case COMMENT:
        break;
        
      case '|':
        literal = true;
        // fall through
        
      case '>':
        if(style.isFlow())
          throw new SyntaxErrorException("Invalid character in a FLOW context", context);
        
        if(lineBuffer_.length() > col_ + 1)
        {
          switch(lineBuffer_.charAt(col_ + 1))
          {
            case '+':
              chomp = YamlChompType.KEEP;
              break;
              
            case '-':
              chomp = YamlChompType.STRIP;
              break;
            
            default:
              throw new SyntaxErrorException("Expected end of line but found " + lineBuffer_.substring(col_), this);
          }
          
        }
        break;

      case MAPPING_ENTRY:
      case MAPPING_KEY:
      case SEQUENCE_ENTRY:
        if(col_ < lineBuffer_.length() - 1 && lineBuffer_.charAt(col_ + 1) == SPACE)
          throw new SyntaxErrorException("Plain scalars may not begin with \"" + lineBuffer_.charAt(col_) + " \"", context);
        break;
    }

    int indent=col_;
    boolean firstLine = true;
    
    do
    {
      // on subsequent iterations of the loop this does something
      col_ = indent;
      
      String line = lineBuffer_.substring(col_);
      StringBuilder b;
      
      if(line.trim().length()==0)
      {
        if(trailingBlank == null)
          trailingBlank = new StringBuilder();
        
        b = trailingBlank;
      }
      else
      {
        if(trailingBlank != null)
        {
          if(literal)
            builder.append(trailingBlank);
          else
            builder.append(SPACE);
          
          trailingBlank = null;
        }
        
        b = builder;
      }
      
      if(style.isFlow())
      {
        for(int i = col_ ; i < lineBuffer_.length() ; i++)
        {
          switch(lineBuffer_.charAt(i))
          {
            case MAPPING_ENTRY:
            case SEQUENCE_START:
            case SEQUENCE_END:
            case COLLECT_ENTRY:
            case MAPPING_START:
            case MAPPING_END:
            case MAPPING_KEY:
              
              String s = lineBuffer_.substring(col_,  i).trim();
              
              if(s.length() > 0)
              {
                if(!firstLine)
                {
                  b.append(SPACE);
                }
                
                b.append(s);
              }
              
              col_ = i;
              // This is not a blank line so b == builder.
              return builder.toString();
          }
        }
        line = line.trim();
      }
      
      if(firstLine)
      {
        firstLine = false;
      }
      else
      {
        if(line.length() > 0)
        {
          if(style.isFlow() || !literal)
          {
            b.append(SPACE);
          }
          else
          {
            b.append('\n');
          }
        }
      }
      b.append(line);
      
      readLine();
      
      indent = 0;
      while(lineBuffer_ != null && indent < lineBuffer_.length() && lineBuffer_.charAt(indent) == SPACE)
      {
        indent++;
      }
    } while(lineBuffer_ != null && (style.isFlow() || indent > indentLevel));
    
    if(trailingBlank != null)
    {
      switch(chomp)
      {
        case STRIP:
          // Nothing
          break;
          
        case CLIP:
          builder.append('\n');
          break;
          
        case KEEP:
          builder.append(trailingBlank);
          break;
      }
    }
    
    return builder.toString();
  }

  private void readHeader() throws IOException
  {
    fillBuffer();
    while(true)
    {
      if(lineBuffer_.startsWith(START_DOCUMENT))
      {
        System.err.println("Read DOCUMENT_SEPARATOR " + lineBuffer_);
        readLine();
        return;
      }
      else if(lineBuffer_.charAt(0) == '%')
      {
        System.err.println("Read directive " + lineBuffer_);
        readLine();
      }
      else
      {
        return;
      }
    }
  }
  
  /**
   * Builder for JsonParser and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <T> The concrete type of the builder for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends YamlParser> extends Parser.AbstractBuilder<T, B>
  {
    Consumer<JsonDomNode> arrayElementConsumer_;

    AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    /**
     * Expect an Array object and pass each element of the array to the given consumer.
     * 
     * @param consumer A consumer of JsonDomNode objects.
     * 
     * @return This (Fluent method).
     */
    public T withArrayElementConsumer(Consumer<JsonDomNode> consumer)
    {
      arrayElementConsumer_ = consumer;
      
      return self();
    }
  }
  
  /**
   * Builder.
   * 
   * @author Bruce Skingle
   *
   */
  public static class Builder extends AbstractBuilder<Builder, YamlParser>
  {
    /**
     * Constructor.
     */
    public Builder()
    {
      super(Builder.class);
    }

    @Override
    protected YamlParser construct()
    {
      return new YamlParser(this);
    }
  }
}
