/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon.json.model;

import com.symphony.oss.canon.json.JsonParser;
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
   * Builder for JsonValue and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <T> Concrete type of builder for fluent methods.
   * @param <B> Concrete type of built type.
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
