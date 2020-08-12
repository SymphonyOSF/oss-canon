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

package com.symphony.oss.canon.json.model;

import com.symphony.oss.commons.type.provider.IStringProvider;

/**
 * A JSON String.
 * 
 * @author Bruce Skingle
 *
 */
public class JsonString extends JsonValue implements IStringProvider
{
  private final String value_;

  private JsonString(AbstractBuilder<?,?> builder)
  {
    super(builder);
    
    value_ = builder.value_;
  }
  
  @Override
  void toString(StringBuilder s, String indent)
  {
    s.append('"');
    for(char c : value_.toCharArray())
    {
      switch(c)
      {
        case '\\':  s.append("\\\\");   break;
        case '\"':  s.append("\\\"");   break;
        case '\n':  s.append("\\n");   break;
        case '\r':  s.append("\\r");   break;
        case '\t':  s.append("\\t");   break;
        case '\f':  s.append("\\f");   break;
        case '\b':  s.append("\\b");   break;
          
        default:
          if(c < ' ')
            s.append(String.format("\\u%04x", (int) c));
          else
            s.append(c);
      }
    }
    s.append('"');
  }

  @Override
  public String asString()
  {
    return value_;
  }

  /**
   * Builder for this and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <T> The concrete type of the builder for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends JsonString> extends JsonValue.AbstractBuilder<T, B>
  {
    private String value_;

    AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    /**
     * Set the value.
     * 
     * @param value The member.
     * 
     * @return This (fluent method).
     */
    public T withValue(String value)
    {
      value_ = value;
      
      return self();
    }
  }
  
  /**
   * Builder for JsonObject.
   * 
   * @author Bruce Skingle
   *
   */
  public static class Builder extends AbstractBuilder<Builder, JsonString>
  {
    /**
     * Constructor.
     */
    public Builder()
    {
      super(Builder.class);
    }

    @Override
    protected JsonString construct()
    {
      return new JsonString(this);
    }
  }
}
