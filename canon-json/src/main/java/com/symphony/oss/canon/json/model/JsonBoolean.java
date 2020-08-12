/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon.json.model;

import com.symphony.oss.commons.type.provider.IBooleanProvider;

/**
 * A JSON String.
 * 
 * @author Bruce Skingle
 *
 */
public class JsonBoolean extends JsonValue implements IBooleanProvider
{
  private final boolean value_;

  private JsonBoolean(AbstractBuilder<?,?> builder)
  {
    super(builder);
    
    value_ = builder.value_;
  }
  
  @Override
  void toString(StringBuilder s, String indent)
  {
    s.append(value_);
  }

  @Override
  public Boolean asBoolean()
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
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends JsonBoolean> extends JsonValue.AbstractBuilder<T, B>
  {
    private boolean value_;

    AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    /**
     * Set the value.
     * 
     * @param value The value.
     * 
     * @return This (fluent method).
     */
    public T withValue(boolean value)
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
  public static class Builder extends AbstractBuilder<Builder, JsonBoolean>
  {
    /**
     * Constructor.
     */
    public Builder()
    {
      super(Builder.class);
    }

    @Override
    protected JsonBoolean construct()
    {
      return new JsonBoolean(this);
    }
  }
}
