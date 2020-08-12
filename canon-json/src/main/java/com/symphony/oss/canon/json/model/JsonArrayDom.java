/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon.json.model;

import javax.annotation.concurrent.Immutable;

/**
 * A JSON Dom (a.k.a document or text) containing an array.
 * 
 * @author Bruce Skingle
 *
 */
@Immutable
public class JsonArrayDom extends JsonDom
{
  private final JsonArray array_;

  protected JsonArrayDom(AbstractBuilder<?,?> builder, JsonArray array)
  {
    super(builder);
    
    array_ = array;
  }

  @Override
  public String toString()
  {
    return array_.toString();
  }

  public JsonArray getArray()
  {
    return array_;
  }
}
