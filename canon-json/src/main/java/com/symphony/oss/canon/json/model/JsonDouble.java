/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon.json.model;

import com.symphony.oss.commons.type.provider.IDoubleProvider;

/**
 * A JSON String.
 * 
 * @author Bruce Skingle
 *
 */
public class JsonDouble extends JsonValue implements IDoubleProvider
{
  private final double value_;

  private JsonDouble(AbstractBuilder<?,?> builder)
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
  public Double asDouble()
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
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends JsonDouble> extends JsonValue.AbstractBuilder<T, B>
  {
    private double value_;

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
    public T withValue(double value)
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
  public static class Builder extends AbstractBuilder<Builder, JsonDouble>
  {
    /**
     * Constructor.
     */
    public Builder()
    {
      super(Builder.class);
    }

    @Override
    protected JsonDouble construct()
    {
      return new JsonDouble(this);
    }
  }
}
