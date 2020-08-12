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

import com.symphony.oss.canon.json.model.JsonArray;
import com.symphony.oss.canon.json.model.JsonBoolean;
import com.symphony.oss.canon.json.model.JsonDom;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonDouble;
import com.symphony.oss.canon.json.model.JsonFloat;
import com.symphony.oss.canon.json.model.JsonInteger;
import com.symphony.oss.canon.json.model.JsonLong;
import com.symphony.oss.canon.json.model.JsonNull;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon.json.model.JsonString;

/**
 * A parser for JSON input.
 * 
 * @author Bruce Skingle
 *
 */
public class JsonParser extends Parser
{
  private static final char START_OBJECT   = '{';
  private static final char END_OBJECT   = '}';
  private static final char START_ARRAY   = '[';
  private static final char END_ARRAY   = ']';
  private static final char QUOTE   = '\"';
  private static final char NAME_SEPARATOR   = ':';
  private static final char VALUE_SEPARATOR   = ',';

  private JsonDom.ParserBuilder domBuilder_ = new JsonDom.ParserBuilder();
  
 
  JsonParser(AbstractBuilder<?,?> builder)
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
      char c = expectToken(START_OBJECT, START_ARRAY);
      
      switch(c)
      {
        case START_OBJECT:
          processObject();
          break;
          
        case START_ARRAY:
          processArray();
          break;
      }
    }
    catch(IOException e)
    {
      domBuilder_.withError(new ParserException("Unable to read input (" + e.getMessage() + ")", this, e));
    }
    catch (ParserException e)
    {
      domBuilder_.withError(e);
    }
    
    return domBuilder_.build();
  }

  private void processArray() throws IOException
  {
    JsonArray.Builder  builder = new JsonArray.Builder();
    
    processArrayContents(builder);
    
    domBuilder_.withArray(builder.build());
  }

  private void processArrayContents(JsonArray.Builder builder) throws IOException
  {
    
    try
    {
      char token = getToken();

      if(token == END_ARRAY)
        return;
      
      // back up over the first token of the value.
      col_--;
      
      while(true)
      {
        IParserContext context = getContext();
        
        JsonDomNode value = getValue();
        
        builder.with(context, value);
        
        if(END_ARRAY == expectToken(VALUE_SEPARATOR, END_ARRAY))
        {
          return;
        }
      }
    }
    catch (ParserException e)
    {
      domBuilder_.withError(e);
    }
  }

  private void processObject() throws IOException, ParserException
  {
    JsonObject.Builder  builder = new JsonObject.Builder();
    char                token   = expectToken(QUOTE, END_OBJECT);
    
    if(token == QUOTE)
    {
      processObjectAttributes(builder);
    }
    
    domBuilder_.withObject(builder.build());
  }

  private void processObjectAttributes(JsonObject.Builder builder) throws IOException, ParserException
  {
    while(true)
    {
      try
      {
        IParserContext context = getContext();
        
        String name = getQuotedString();
        
        expectToken(NAME_SEPARATOR);
        
        JsonDomNode value = getValue();
        
        builder.with(context, name, value);
      }
      catch (ParserException e)
      {
        domBuilder_.withError(e);
      }
      
      if(END_OBJECT == expectToken(VALUE_SEPARATOR, END_OBJECT))
      {
        return;
      }
      
      expectToken(QUOTE);
    }
  }

  private JsonDomNode getValue() throws IOException, ParserException
  {
    char token = getToken();
    IParserContext context = getContext();
    
    if((token >='1' && token <= '9') || token == '-')
    {
      return getNumber(context);
    }
    switch(token)
    {
      case QUOTE:
        String stringValue = getQuotedString();
        return new JsonString.Builder()
            .withValue(stringValue)
            .withContext(context)
            .build();
        
      case 't':
        expectString("true");
        return new JsonBoolean.Builder()
            .withValue(true)
            .withContext(context)
            .build();
        
      case 'f':
        expectString("false");
        return new JsonBoolean.Builder()
            .withValue(false)
            .withContext(context)
            .build();
        
      case 'n':
        expectString("null");
        return new JsonNull.Builder()
            .withContext(context)
            .build();
        
      default:
        throw new SyntaxErrorException("Expected a value but found " + escapeChar(token), this);
    }
  }

  private JsonDomNode getNumber(IParserContext context) throws SyntaxErrorException
  {
    while(lineBuffer_.charAt(col_) >='0' && lineBuffer_.charAt(col_) <= '9')
    {
      col_++;
      
      if(col_ > lineBuffer_.length())
        return getInteger(context);
    }
    
    if(lineBuffer_.charAt(col_) == '.')
    {
      col_++;
      
      if(col_ > lineBuffer_.length())
      {
        throw new SyntaxErrorException("Invalid number value (trailing decimal point)", context);
      }
      
      while(lineBuffer_.charAt(col_) >='0' && lineBuffer_.charAt(col_) <= '9')
      {
        col_++;
        
        if(col_ > lineBuffer_.length())
          return getFloat(context);
      }
      
      if(lineBuffer_.charAt(col_) == 'e' || lineBuffer_.charAt(col_) == 'E')
      {
        col_++;
        
        if(col_ > lineBuffer_.length())
        {
          throw new SyntaxErrorException("Invalid number value (trailing exponential)", context);
        }
      }
      
      if(lineBuffer_.charAt(col_) == '+' || lineBuffer_.charAt(col_) == '-')
      {
        col_++;
        
        if(col_ > lineBuffer_.length())
        {
          throw new SyntaxErrorException("Invalid number value (trailing + or -)", context);
        }
      }
      
      while(lineBuffer_.charAt(col_) >='0' && lineBuffer_.charAt(col_) <= '9')
      {
        col_++;
        
        if(col_ > lineBuffer_.length())
          return getFloat(context);
      }
      
      return getFloat(context);
    }
    
    return getInteger(context);
  }

  private JsonDomNode getFloat(IParserContext context) throws SyntaxErrorException
  {
    try
    {
      double doubleValue = Double.parseDouble(lineBuffer_.substring(context.getCol() - 1, col_));
      float floatValue = Float.parseFloat(lineBuffer_.substring(context.getCol() - 1, col_));
      
      if(String.valueOf(doubleValue).equals(String.valueOf(floatValue)))
      {
        return new JsonFloat.Builder()
            .withContext(context)
            .withValue(floatValue)
            .build();
      }
      else
      {
        return new JsonDouble.Builder()
            .withContext(context)
            .withValue(doubleValue)
            .build();
      }
    }
    catch(NumberFormatException e)
    {
      throw new SyntaxErrorException("Invalid integer value", context);
    }
  }

  private JsonDomNode getInteger(IParserContext context) throws SyntaxErrorException
  {
    try
    {
      Long longValue = Long.parseLong(lineBuffer_.substring(context.getCol() - 1, col_));
      
      if(longValue.intValue() == longValue)
      {
        return new JsonInteger.Builder()
            .withContext(context)
            .withValue(longValue.intValue())
            .build();
      }
      else
      {
        return new JsonLong.Builder()
            .withContext(context)
            .withValue(longValue)
            .build();
      }
    }
    catch(NumberFormatException e)
    {
      throw new SyntaxErrorException("Invalid integer value", context);
    }
  }

  String getQuotedString() throws IOException, ParserException
  {
    StringBuilder s = new StringBuilder();
    
    while(true)
    {
      char c = getChar();
      
      if(c == QUOTE)
      {
        return s.toString();
      }
      
      if(c == EOF)
      {
        throw new ParserException("Unexpected end of file", this);
      }
      
      if(c < ' ')
      {
        throw new SyntaxErrorException("Invalid character \"" + escapeChar(c) + "\" in quoted string", this);
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
              skipTo(QUOTE);
              
              return s.toString();
            }
            
          default:
            domBuilder_.withError(new SyntaxErrorException("Invalid escape sequence \"\\" + escapeChar(c) + "\"", this));
            skipTo(QUOTE);
            
            return s.toString();
        }
      }
      else
      {
        s.append(c);
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
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends JsonParser> extends Parser.AbstractBuilder<T, B>
  {
    AbstractBuilder(Class<T> type)
    {
      super(type);
    }
  }
  
  /**
   * Builder.
   * 
   * @author Bruce Skingle
   *
   */
  public static class Builder extends AbstractBuilder<Builder, JsonParser>
  {
    /**
     * Constructor.
     */
    public Builder()
    {
      super(Builder.class);
    }

    @Override
    protected JsonParser construct()
    {
      return new JsonParser(this);
    }
  }
}

  
