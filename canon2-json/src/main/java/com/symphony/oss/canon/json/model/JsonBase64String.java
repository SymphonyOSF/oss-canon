/*
 *
 *
 * Copyright 2017 Symphony Communication Services, LLC.
 *
 * Licensed to The Symphony Software Foundation (SSF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The SSF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.symphony.oss.canon.json.model;

import org.apache.commons.codec.binary.Base64;

import com.symphony.oss.commons.immutable.ImmutableByteArray;
import com.symphony.oss.commons.type.provider.IImmutableByteArrayProvider;

public class JsonBase64String extends JsonString implements IImmutableByteArrayProvider
{
  private ImmutableByteArray immutableByteArray_;

  private JsonBase64String(AbstractBuilder<?,?> builder)
  {
    super(builder);
    
    immutableByteArray_ = builder.immutableByteArray_;
  }

  @Override
  public synchronized ImmutableByteArray asImmutableByteArray()
  {
    if(immutableByteArray_ == null)
      immutableByteArray_ = ImmutableByteArray.newInstance(Base64.decodeBase64(asString()));
    
    return immutableByteArray_;
  }

  /**
   * Builder for this and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <T> The concrete type of the builder for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends JsonBase64String> extends JsonString.AbstractBuilder<T, B>
  {
    private ImmutableByteArray immutableByteArray_;
    
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
    public T withValue(ImmutableByteArray value)
    {
      immutableByteArray_ = value;
      return withValue(value.toBase64String());
    }
  }
  
  /**
   * Builder.
   * 
   * @author Bruce Skingle
   *
   */
  public static class Builder extends AbstractBuilder<Builder, JsonBase64String>
  {
    /**
     * Constructor.
     */
    public Builder()
    {
      super(Builder.class);
    }

    @Override
    protected JsonBase64String construct()
    {
      return new JsonBase64String(this);
    }
  }
}
