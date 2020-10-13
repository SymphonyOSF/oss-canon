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
import java.io.Reader;
import java.io.StringReader;
import java.util.function.Consumer;

import org.apache.commons.codec.binary.Base64;

import com.symphony.oss.canon.json.model.JsonArray;
import com.symphony.oss.canon.json.model.JsonBase64String;
import com.symphony.oss.canon.json.model.JsonBoolean;
import com.symphony.oss.canon.json.model.JsonDom;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonNull;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon.json.model.JsonObjectDom;
import com.symphony.oss.canon.json.model.JsonString;

/**
 * A parser for JSON input.
 * 
 * @author Bruce Skingle
 *
 */
public class JsonParser extends Parser
{
  private static final char           START_OBJECT    = '{';
  private static final char           END_OBJECT      = '}';
  private static final char           START_ARRAY     = '[';
  private static final char           END_ARRAY       = ']';
  private static final char           QUOTE           = '\"';
  private static final char           NAME_SEPARATOR  = ':';
  private static final char           VALUE_SEPARATOR = ',';

  private final Consumer<JsonDomNode> arrayElementConsumer_;

  private JsonDom.ParserBuilder       domBuilder_     = new JsonDom.ParserBuilder();
  
 
  JsonParser(AbstractBuilder<?,?> builder)
  {
    super(builder);
    
    arrayElementConsumer_ = builder.arrayElementConsumer_;
  }
  
  /**
   * Convenience method to parse a Dom from a Reader.
   * 
   * @param json The input JSON.
   * 
   * If the input is invalid the return value will be an instance of JsonInvalidDom.
   * 
   * @return A JsonDom representing the given input.
   */
  public static JsonDom parseDom(Reader json)
  {
    return new JsonParser.Builder().withInput(json).build().parse();
  }
  
  /**
   * Convenience method to parse a Dom from a String.
   * 
   * @param json The input JSON.
   * 
   * If the input is invalid the return value will be an instance of JsonInvalidDom.
   * 
   * @return A JsonDom representing the given input.
   */
  public static JsonDom parseDom(String json)
  {
    return new JsonParser.Builder().withInput(json).build().parse();
  }
  
  /**
   * Convenience method to parse an Object from a String.
   * 
   * This method does not do a partial read, it is expected that the contents of the String
   * are a single object.
   * 
   * @param json The input JSON.
   * 
   * @throws IllegalStateException If the input is invalid or does not contain a single object.
   * 
   * @return A JsonDom representing the given input.
   */
  public static JsonObject parseObject(String json)
  {
    return parseObject(new StringReader(json));
  }
  
  /**
   * Convenience method to parse an Object from a Reader.
   * 
   * This method does not do a partial read, it is expected that the contents of the Reader
   * are a single object.
   * 
   * @param json The input JSON.
   * 
   * @throws IllegalStateException If the input is invalid or does not contain a single object.
   * 
   * @return A JsonDom representing the given input.
   */
  public static JsonObject parseObject(Reader json)
  {
    JsonDom dom = parseDom(json);
    
    if(dom instanceof JsonObjectDom)
    {
      return ((JsonObjectDom)dom).getObject();
    }
    else
    {
      throw new IllegalStateException("Expected a JSON Object but read a " + dom.getClass().getName());
    }
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
      char c = expectCharToken(START_OBJECT, START_ARRAY);
      
      switch(c)
      {
        case START_OBJECT:
          domBuilder_.withObject(getObject());
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
    if(arrayElementConsumer_ == null)
    {
      domBuilder_.withArray(getArray());
    }
    else
    {
      processArrayContents(arrayElementConsumer_);
    }
  }

  private JsonArray getArray() throws IOException
  {
    JsonArray.Builder  builder = new JsonArray.Builder();
    
    processArrayContents(builder);
    
    return builder.build();
  }

  private void processArrayContents(Consumer<JsonDomNode> builder) throws IOException
  {
    
    
    char token = getCharToken();

    if(token == END_ARRAY)
      return;
    
    // back up over the first token of the value.
    col_--;
    
    while(true)
    {
      try
      {
        builder.accept(getValue());
        
        if(END_ARRAY == expectCharToken(VALUE_SEPARATOR, END_ARRAY))
        {
          return;
        }
      }
      catch (ParserException e)
      {
        domBuilder_.withError(e);
        
        do
        {
          token = getCharToken();
        } while(token != VALUE_SEPARATOR && token != END_ARRAY);
        
        if(token == END_ARRAY)
          return;
      }
    }
  }

  private JsonObject getObject() throws IOException, ParserException
  {
    JsonObject.Builder  builder = new JsonObject.Builder().withCanonicalize(canonicalize_);
    char                token   = expectCharToken(QUOTE, END_OBJECT);
    
    if(token == QUOTE)
    {
      processObjectAttributes(builder);
    }
    
    return builder.build();
  }

  private void processObjectAttributes(JsonObject.Builder builder) throws IOException, ParserException
  {
    while(true)
    {
      try
      {
        IParserContext context = getContext();
        
        String name = getQuotedString();
        
        expectCharToken(NAME_SEPARATOR);
        
        JsonDomNode value = getValue();
        
        builder.with(context, name, value);
      }
      catch (ParserException e)
      {
        domBuilder_.withError(e);
      }
      
      if(END_OBJECT == expectCharToken(VALUE_SEPARATOR, END_OBJECT))
      {
        return;
      }
      
      expectCharToken(QUOTE);
    }
  }

  private JsonDomNode getValue() throws IOException, ParserException
  {
    char token = getCharToken();
    IParserContext context = getContext();
    
    try
    {
      if((token >='0' && token <= '9') || token == '-')
      {
        return getNumber(context);
      }
      switch(token)
      {
        case QUOTE:
          String stringValue = getQuotedString();
          if(Base64.isBase64(stringValue))
            return new JsonBase64String.Builder()
                .withValue(stringValue)
                .withContext(context)
                .build();
          else
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
          
        case START_OBJECT:
          return getObject();
          
        case START_ARRAY:
          return getArray();
          
        default:
          throw new SyntaxErrorException("Expected a value but found " + escapeChar(token), this);
      }
    }
    catch(RuntimeException e)
    {
      throw new ParserException(e.toString(), context, e);
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

  
