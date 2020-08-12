/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon.json.model;

import javax.annotation.concurrent.Immutable;

/**
 * An invalid JSON Dom (a.k.a document or text).
 * 
 * This type may result from parsing operations.
 * 
 * @author Bruce Skingle
 *
 */
@Immutable
public class JsonInvalidDom extends JsonDom
{
  protected JsonInvalidDom(AbstractBuilder<?,?> builder)
  {
    super(builder);
  }

  @Override
  public String toString()
  {
    return "";
  }
}
