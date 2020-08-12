/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon.json.model;

public abstract class JsonValue extends JsonDomNode
{
  private static final Builder DUMMY_BUILDER = new Builder();
  
  JsonValue(AbstractBuilder<?,?> builder)
  {
    super(builder);
  }
  
  JsonValue()
  {
    super(DUMMY_BUILDER);
  }
  
  /**
   * Builder for JsonValue and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <T> Concrete type of builder for fluent methods.
   * @param <B> Concrete type of built type.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends JsonValue> extends JsonDomNode.AbstractBuilder<T, B>
  {
    AbstractBuilder(Class<T> type)
    {
      super(type);
    }
  }
  
  static class Builder extends AbstractBuilder<Builder, JsonValue>
  {
    /**
     * Constructor.
     */
    public Builder()
    {
      super(Builder.class);
    }

    @Override
    protected JsonValue construct()
    {
      throw new IllegalStateException("Virtual Method");
    }
  }
}
