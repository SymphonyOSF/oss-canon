/*
 *
 *
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.symphony.oss.canon.json.model;

import java.math.BigInteger;

import com.symphony.oss.commons.type.provider.IBigIntegerProvider;
import com.symphony.oss.commons.type.provider.INumberProvider;

/**
 * A JSON BigInteger value.
 * 
 * @author Bruce Skingle
 *
 */
public class JsonBigInteger extends JsonValue implements INumberProvider, IBigIntegerProvider
{
  private final BigInteger value_;

  private JsonBigInteger(AbstractBuilder<?,?> builder)
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
  public Number asNumber()
  {
    return value_;
  }

  @Override
  public BigInteger asBigInteger()
  {
    return value_;
  }

  @Override
  public int hashCode()
  {
    return value_.hashCode();
  }

  @Override
  public boolean equals(Object obj)
  {
    return obj instanceof JsonBigInteger && value_.equals(((JsonBigInteger)obj).value_);
  }

  /**
   * Builder for this and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <T> The concrete type of the builder for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends JsonBigInteger> extends JsonValue.AbstractBuilder<T, B>
  {
    private BigInteger value_;

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
    public T withValue(BigInteger value)
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
  public static class Builder extends AbstractBuilder<Builder, JsonBigInteger>
  {
    /**
     * Constructor.
     */
    public Builder()
    {
      super(Builder.class);
    }

    @Override
    protected JsonBigInteger construct()
    {
      return new JsonBigInteger(this);
    }
  }
}
