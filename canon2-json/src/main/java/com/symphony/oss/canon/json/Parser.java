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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonDouble;
import com.symphony.oss.canon.json.model.JsonFloat;
import com.symphony.oss.canon.json.model.JsonInteger;
import com.symphony.oss.canon.json.model.JsonLong;
import com.symphony.oss.canon.json.model.JsonParsedNumber;
import com.symphony.oss.commons.fault.CodingFault;
import com.symphony.oss.commons.fluent.BaseAbstractBuilder;

/**
 * Base class for parsers.
 * 
 * @author Bruce Skingle
 *
 */
public class Parser implements IParserContext
{
  static final char            EOF = 0;

  private final BufferedReader in_;
  final boolean                canonicalize_;
  String                       inputSource_;
  int                          line_;
  int                          col_;
  String                       lineBuffer_;
  boolean                      atEof_;

  
  Parser(AbstractBuilder<?,?> builder)
  {
    in_ = builder.in_;
    canonicalize_ = builder.canonicalize_;
  }

  /**
   * Builder for this and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <T> The concrete type of the builder for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends Parser> extends BaseAbstractBuilder<T, B>
  {
    BufferedReader    in_;
    boolean           canonicalize_ = true;
    
    AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    /**
     * Set the canonicalization mode for the parser.
     * 
     * @param canonicalize The canonicalization mode for the parser.
     * 
     * @return This (fluent method).
     */
    public T withCanonicalize(boolean canonicalize)
    {
      canonicalize_ = canonicalize;
      
      return self();
    }
    
    /**
     * Set the input for the parser.
     * 
     * @param in The input for the parser.
     * 
     * @return This (fluent method).
     */
    public T withInput(Reader in)
    {
      in_ = new BufferedReader(in);
      
      return self();
    }
    
    /**
     * Set the input for the parser.
     * 
     * @param in The input for the parser.
     * 
     * @return This (fluent method).
     */
    public T withInput(String in)
    {
      in_ = new BufferedReader(new StringReader(in));
      
      return self();
    }
    
    /**
     * Set the input for the parser.
     * 
     * @param in The input for the parser.
     * 
     * @return This (fluent method).
     */
    public T withInput(InputStream in)
    {
      in_ = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
      
      return self();
    }
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
  
  boolean isAtEof() throws IOException
  {
    if(!atEof_ && (lineBuffer_ == null || col_ >= lineBuffer_.length()))
    {
      fillBuffer();
      
      if(lineBuffer_ == null)
      {
        atEof_ = true;
      }
    }
    
    return atEof_;
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

  void expectString(String string) throws SyntaxErrorException
  {
    int end = col_ + string.length() - 1;
    
    if(end > lineBuffer_.length())
      end = lineBuffer_.length();
    
    String found = lineBuffer_.substring(col_ - 1, end);
    
    if(!string.equals(found))
      throw new SyntaxErrorException("Expected a value but found " + found, this);
    
    col_ = end;
  }
  
  void skipTo(char end)
  {
    char c = 0;
    
    do
    {
      try
      {
        c = getCharToken();
      }
      catch (IOException e)
      {
        return;
      }
    } while(c != end);
  }

  int getHexNumber(int digits) throws ParserException, IOException
  {
    int v=0;
    
    for(int i=0 ; i<digits ; i++)
    {
      v = 16 * v + getHexDigit();
    }
    return v;
  }

  int getHexDigit() throws ParserException, IOException
  {
    char c = getCharToken();
    
    if(c >= '0' && c <= '9')
      return c - '0';
    

    if(c >= 'a' && c <= 'f')
      return c - 'a';
    

    if(c >= 'A' && c <= 'F')
      return c - 'A';
    
    throw new SyntaxErrorException("Invalid hex digit \"" + escapeChar(c) + "\" in char escape found", this);
  }

  String escapeChar(char token)
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
  
  String escapeString(String token)
  {
    StringBuilder s = new StringBuilder();
    
    for(char c : token.toCharArray())
      s.append(escapeChar(c));
    
    return s.toString();
  }

  @SuppressWarnings("null") // The check for expectedTokens.length == 0 means that s cannot be null after the loop.
  String expectStringToken(String ...expectedTokens) throws IOException, ParserException
  {
    String token = getStringToken();
    
    for(String t : expectedTokens)
      if(token.equals(t))
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
      for(String t : expectedTokens)
      {
        if(s == null)
        {
          s = new StringBuilder("Expected one of [\"");
        }
        else
        {
          s.append("\", \"");
        }
        
        s.append(t);
      }
      
      s.append("\"] but found \"");
    }
    
    if(token == null)
      s.append("End of File");
    else
      s.append(escapeString(token));
    
    throw new SyntaxErrorException(s.toString(), this);
  }

  /**
   * Get the next token from the input, ignoring whitespace.
   * 
   * @return The next token from the input, ignoring whitespace.
   * 
   * @throws IOException If there is a problem reading the data.
   */
  String getStringToken() throws IOException
  {
    if(atEof_)
    {
      return null;
    }
    
    char c;
    
    do
    {
      fillBuffer();
      
      if(lineBuffer_ == null)
      {
        atEof_ = true;
        return null;
      }
      
      c = lineBuffer_.charAt(col_++);
    } while(isSpace(c));
    
    StringBuilder s = new StringBuilder(c);
    
    while(col_ < lineBuffer_.length())
    {
      c = lineBuffer_.charAt(col_++);
      
      if(isSpace(c))
        return s.toString();
      
      s.append(c);
    }
    return s.toString();
  }
  
  @SuppressWarnings("null") // The check for expectedTokens.length == 0 means that s cannot be null after the loop.
  char expectCharToken(char ...expectedTokens) throws IOException, ParserException
  {
    char token = getCharToken();
    
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
  char getCharToken() throws IOException
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
  char getChar() throws IOException
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
    
  boolean isSpace(char c)
  {
    return c==' ' || c=='\t' || c=='\r' || c=='\n';
  }

  void fillBuffer() throws IOException
  {
    if(lineBuffer_ == null || col_ >= lineBuffer_.length())
    {
      do
      {
        readLine();
      } while(lineBuffer_ != null && col_ >= lineBuffer_.length());
    }
  }

  void readLine() throws IOException
  {
    lineBuffer_ = in_.readLine();
    line_++;
    col_ = 0;
  }
  
  JsonDomNode getNumber(IParserContext context) throws SyntaxErrorException
  {
    if(col_ >= lineBuffer_.length())
      return getInteger(context);
    
    while(lineBuffer_.charAt(col_) >='0' && lineBuffer_.charAt(col_) <= '9')
    {
      col_++;
      
      if(col_ >= lineBuffer_.length())
        return getInteger(context);
    }
    
    if(lineBuffer_.charAt(col_) == '.')
    {
      col_++;
      
      if(col_ >= lineBuffer_.length())
      {
        throw new SyntaxErrorException("Invalid number value (trailing decimal point)", context);
      }
      
      while(lineBuffer_.charAt(col_) >='0' && lineBuffer_.charAt(col_) <= '9')
      {
        col_++;
        
        if(col_ >= lineBuffer_.length())
          return getFloat(context);
      }
      
      if(lineBuffer_.charAt(col_) == 'e' || lineBuffer_.charAt(col_) == 'E')
      {
        col_++;
        
        if(col_ >= lineBuffer_.length())
        {
          throw new SyntaxErrorException("Invalid number value (trailing exponential)", context);
        }
      }
      
      if(lineBuffer_.charAt(col_) == '+' || lineBuffer_.charAt(col_) == '-')
      {
        col_++;
        
        if(col_ >= lineBuffer_.length())
        {
          throw new SyntaxErrorException("Invalid number value (trailing + or -)", context);
        }
      }
      
      while(lineBuffer_.charAt(col_) >='0' && lineBuffer_.charAt(col_) <= '9')
      {
        col_++;
        
        if(col_ >= lineBuffer_.length())
          return getFloat(context);
      }
      
      return getFloat(context);
    }
    
    return getInteger(context);
  }

  JsonDomNode getFloat(IParserContext context)
  {
    //return getFloat(context, lineBuffer_.substring(context.getCol() - 1, col_));
    
    return new JsonParsedNumber.Builder()
        .withContext(context)
        .withValue(lineBuffer_.substring(context.getCol() - 1, col_))
        .build();
  }

//  JsonDomNode getFloat(IParserContext context, String input) throws SyntaxErrorException
//  {
//    try
//    {
////      BigDecimal bigDecimalValue = new BigDecimal(input);
//      
////      bigDecimalValue.doubleValue(input);
//      
//      double doubleValue = Double.parseDouble(input);
//      float floatValue = Float.parseFloat(input);
//      
//      if(String.valueOf(doubleValue).equals(String.valueOf(floatValue)))
//      {
//        return new JsonFloat.Builder()
//            .withContext(context)
//            .withValue(floatValue)
//            .build();
//      }
//      else
//      {
//        return new JsonDouble.Builder()
//            .withContext(context)
//            .withValue(doubleValue)
//            .build();
//      }
//    }
//    catch(NumberFormatException e)
//    {
//      throw new SyntaxErrorException("Invalid integer value", context);
//    }
//  }

  JsonDomNode getInteger(IParserContext context)
  {
    return new JsonParsedNumber.Builder()
        .withContext(context)
        .withValue(lineBuffer_.substring(context.getCol() - 1, col_))
        .build();
    
//    try
//    {
//      Long longValue = Long.parseLong(lineBuffer_.substring(context.getCol() - 1, col_));
//      
//      if(longValue.intValue() == longValue)
//      {
//        return new JsonInteger.Builder()
//            .withContext(context)
//            .withValue(longValue.intValue())
//            .build();
//      }
//      else
//      {
//        return new JsonLong.Builder()
//            .withContext(context)
//            .withValue(longValue)
//            .build();
//      }
//    }
//    catch(NumberFormatException e)
//    {
//      throw new SyntaxErrorException("Invalid integer value", context);
//    }
  }
}
