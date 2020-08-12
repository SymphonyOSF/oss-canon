/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon.json.model;

import javax.annotation.concurrent.Immutable;

/**
 * A JSON Dom (a.k.a document or text) containing an object.
 * 
 * @author Bruce Skingle
 *
 */
@Immutable
public class JsonObjectDom extends JsonDom
{
  private final JsonObject object_;

  protected JsonObjectDom(AbstractBuilder<?,?> builder, JsonObject object)
  {
    super(builder);
    
    object_ = object;
  }

  @Override
  public String toString()
  {
    return object_.toString();
  }

  public JsonObject getObject()
  {
    return object_;
  }
}
