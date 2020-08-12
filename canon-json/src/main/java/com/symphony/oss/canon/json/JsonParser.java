/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

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
import com.symphony.oss.canon.json.model.JsonObject.Builder;
import com.symphony.oss.canon.json.model.JsonString;
import com.symphony.oss.commons.fault.CodingFault;

/**
 * A parser for JSON input.
 * 
 * @author Bruce Skingle
 *
 */
public class JsonParser implements IParserContext
{
  private static final char EOF   = 0;
  private static final char START_OBJECT   = '{';
  private static final char END_OBJECT   = '}';
  private static final char START_ARRAY   = '[';
  private static final char END_ARRAY   = ']';
  private static final char QUOTE   = '\"';
  private static final char NAME_SEPARATOR   = ':';
  private static final char VALUE_SEPARATOR   = ',';

  private BufferedReader    in_;
  private JsonDom.ParserBuilder domBuilder_ = new JsonDom.ParserBuilder();
  private String            inputSource_;
  private int               line_;
  private int               col_;
  private String            lineBuffer_;
  private boolean           atEof_;
  
 
  public JsonParser(Reader in)
  {
    in_ = new BufferedReader(in);
  }
  
  public JsonParser(String in)
  {
    in_ = new BufferedReader(new StringReader(in));
  }
  
  @Override
  public String getInputSource()
  {
    return inputSource_;
  }

  @Override
  public int getLine()
  {
    return line_;
  }

  @Override
  public int getCol()
  {
    return col_;
  }
  
  /**
   * Return a snapshot of the current context in the input stream.
   * 
   * @return A snapshot of the current context in the input stream.
   */
  public IParserContext getContext()
  {
    return new ParserContext(this);
  }

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

  private void processObjectAttributes(Builder builder) throws IOException, ParserException
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

  private void expectString(String string) throws SyntaxErrorException
  {
    int end = col_ + string.length() - 1;
    
    if(end > lineBuffer_.length())
      end = lineBuffer_.length();
    
    String found = lineBuffer_.substring(col_ - 1, end);
    
    if(!string.equals(found))
      throw new SyntaxErrorException("Expected a value but found " + found, this);
    
    col_ = end;
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

  private void skipTo(char end)
  {
    char c = 0;
    
    do
    {
      try
      {
        c = getToken();
      }
      catch (IOException e)
      {
        return;
      }
    } while(c != end);
    
  }

  private int getHexNumber(int digits) throws ParserException, IOException
  {
    int v=0;
    
    for(int i=0 ; i<digits ; i++)
    {
      v = 16 * v + getHexDigit();
    }
    return v;
  }

  private int getHexDigit() throws ParserException, IOException
  {
    char c = getToken();
    
    if(c >= '0' && c <= '9')
      return c - '0';
    

    if(c >= 'a' && c <= 'f')
      return c - 'a';
    

    if(c >= 'A' && c <= 'F')
      return c - 'A';
    
    throw new SyntaxErrorException("Invalid hex digit \"" + escapeChar(c) + "\" in char escape found", this);
  }

  static String escapeChar(char token)
  {
    switch(token)
    {
      case '\n':
        return "\\n";
        
      case '\r':
        return "\\r";

      case '\\':
        return "\\\\";
        
      case '\b':
        return "\\b";
        
      case '\f':
        return "\\f";
        
      case '\t':
        return "\\t";
    }
    
    if(token < ' ' || token > '~')
      return String.format("\\u%04x", (int)token);
    else
     return String.valueOf(token);
  }

  @SuppressWarnings("null") // The check for expectedTokens.length == 0 means that s cannot be null after the loop.
  private char expectToken(char ...expectedTokens) throws IOException, ParserException
  {
    char token = getToken();
    
    for(char t : expectedTokens)
      if(token == t)
        return token;
    
    StringBuilder s = null;
    
    if(expectedTokens.length == 0)
      throw new CodingFault("expectedTokens may not be empty");
    
    if(expectedTokens.length == 1)
    {
      s = new StringBuilder("Expected \"" + expectedTokens[0] + "\" but found ");
    }
    else
    {
      for(char t : expectedTokens)
      {
        if(s == null)
        {
          s = new StringBuilder("Expected one of [");
        }
        else
        {
          s.append(", ");
        }
        
        s.append(t);
      }
      
      s.append("] but found ");
    }
    
    if(token == EOF)
      s.append("End of File");
    else
      s.append(escapeChar(token));
    
    throw new SyntaxErrorException(s.toString(), this);
  }

  /**
   * Get the next token from the input, ignoring whitespace.
   * 
   * @return The next token from the input, ignoring whitespace.
   * 
   * @throws IOException If there is a problem reading the data.
   */
  private char getToken() throws IOException
  {
    if(atEof_)
    {
      return EOF;
    }
    
    char c;
    
    do
    {
      fillBuffer();
      
      if(lineBuffer_ == null)
      {
        atEof_ = true;
        return EOF;
      }
      
      c = lineBuffer_.charAt(col_++);
    } while(isSpace(c));
    
    return c;
  }
  
  /**
   * Get the next character from the input.
   * 
   * @return The next character from the input.
   * 
   * @throws IOException If there is a problem reading the data.
   */
  private char getChar() throws IOException
  {
    if(atEof_)
    {
      return EOF;
    }
    
    if(lineBuffer_ != null && col_ >= lineBuffer_.length())
    {
      lineBuffer_ = null;
      return '\n';
    }

    fillBuffer();
      
    if(lineBuffer_ == null)
    {
      atEof_ = true;
      return EOF;
    }
    
    return lineBuffer_.charAt(col_++);
  }
    
  private boolean isSpace(char c)
  {
    return c==' ' || c=='\t' || c=='\r' || c=='\n';
  }

  private void fillBuffer() throws IOException
  {
    if(lineBuffer_ == null || col_ >= lineBuffer_.length())
    {
      do
      {
        lineBuffer_ = in_.readLine();
        line_++;
        col_ = 0;
      } while(lineBuffer_ != null && col_ >= lineBuffer_.length());
    }
  }
}

  
