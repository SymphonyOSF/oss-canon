/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon.json.model;

import com.symphony.oss.commons.type.provider.IIntegerProvider;
import com.symphony.oss.commons.type.provider.ILongProvider;

/**
 * A JSON String.
 * 
 * @author Bruce Skingle
 *
 */
public class JsonInteger extends JsonValue implements ILongProvider, IIntegerProvider
{
  private final int value_;

  private JsonInteger(AbstractBuilder<?,?> builder)
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
  public Integer asInteger()
  {
    return value_;
  }

  @Override
  public Long asLong()
  {
    return (long)value_;
  }

  /**
   * Builder for JsonValue and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <T> Concrete type of builder for fluent methods.
   * @param <B> Concrete type of built type.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends JsonInteger> extends JsonValue.AbstractBuilder<T, B>
  {
    private int value_;

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
    public T withValue(int value)
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
  public static class Builder extends AbstractBuilder<Builder, JsonInteger>
  {
    /**
     * Constructor.
     */
    public Builder()
    {
      super(Builder.class);
    }

    @Override
    protected JsonInteger construct()
    {
      return new JsonInteger(this);
    }
  }
}
