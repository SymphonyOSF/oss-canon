/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon.json.model;

/**
 * A JSON String.
 * 
 * @author Bruce Skingle
 *
 */
public class JsonNull extends JsonValue
{
  private JsonNull(AbstractBuilder<?,?> builder)
  {
    super(builder);
  }
  
  @Override
  void toString(StringBuilder s, String indent)
  {
    s.append("null");
  }
  
  /**
   * Builder for JsonObject.
   * 
   * @author Bruce Skingle
   *
   */
  public static class Builder extends JsonValue.AbstractBuilder<Builder, JsonNull>
  {
    /**
     * Constructor.
     */
    public Builder()
    {
      super(Builder.class);
    }

    @Override
    protected JsonNull construct()
    {
      return new JsonNull(this);
    }
  }
}
