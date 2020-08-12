/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon.json.model;

import com.symphony.oss.commons.type.provider.IDoubleProvider;
import com.symphony.oss.commons.type.provider.IFloatProvider;

/**
 * A JSON String.
 * 
 * @author Bruce Skingle
 *
 */
public class JsonFloat extends JsonValue implements IDoubleProvider, IFloatProvider
{
  private final float value_;

  private JsonFloat(AbstractBuilder<?,?> builder)
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
  public Float asFloat()
  {
    return value_;
  }

  @Override
  public Double asDouble()
  {
    return (double)value_;
  }

  /**
   * Builder for JsonValue and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <T> Concrete type of builder for fluent methods.
   * @param <B> Concrete type of built type.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends JsonFloat> extends JsonValue.AbstractBuilder<T, B>
  {
    private float value_;

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
    public T withValue(float value)
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
  public static class Builder extends AbstractBuilder<Builder, JsonFloat>
  {
    /**
     * Constructor.
     */
    public Builder()
    {
      super(Builder.class);
    }

    @Override
    protected JsonFloat construct()
    {
      return new JsonFloat(this);
    }
  }
}
