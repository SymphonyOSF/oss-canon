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

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * A JSON Number value.
 * 
 * @author Bruce Skingle
 *
 */
public abstract class JsonNumber extends JsonValue
{
  JsonNumber(AbstractBuilder<?,?> builder)
  {
    super(builder);
  }

  /**
   * Return true iff this value can be represented as a 32bit Integer.
   * 
   * @return true iff this value can be represented as a 32bit Integer.
   */
  public abstract boolean isInteger();
  
  /**
   * Return this value as a 32bit Integer.
   * 
   * @return this value as a 32bit Integer.
   * 
   * @throws IllegalStateException if this value cannot be represented as such.
   */
  public abstract Integer asInteger();

  /**
   * Return true iff this value can be represented as a 64bit Long.
   * 
   * @return true iff this value can be represented as a 64bit Long.
   */
  public abstract boolean isLong();
  
  /**
   * Return this value as a 64bit Long.
   * 
   * @return this value as a 64bit Long.
   * 
   * @throws IllegalStateException if this value cannot be represented as such.
   */
  public abstract Long asLong();

  /**
   * Return true iff this value can be represented as a BigInteger.
   * 
   * @return true iff this value can be represented as a BigInteger.
   */
  public abstract boolean isBigInteger();
  
  /**
   * Return this value as a BigInteger.
   * 
   * @return this value as a BigInteger.
   * 
   * @throws IllegalStateException if this value cannot be represented as such.
   */
  public abstract BigInteger asBigInteger();

  /**
   * Return true iff this value can be represented as a Float.
   * 
   * @return true iff this value can be represented as a Float.
   */
  public abstract boolean isFloat();
  
  /**
   * Return this value as a Float.
   * 
   * @return this value as a Float.
   * 
   * @throws IllegalStateException if this value cannot be represented as such.
   */
  public abstract Float asFloat();

  /**
   * Return true iff this value can be represented as a Double.
   * 
   * @return true iff this value can be represented as a Double.
   */
  public abstract boolean isDouble();
  
  /**
   * Return this value as a Double.
   * 
   * @return this value as a Double.
   * 
   * @throws IllegalStateException if this value cannot be represented as such.
   */
  public abstract Double asDouble();
  
  /**
   * Return this value as a BigDecimal.
   * 
   * @return this value as a BigDecimal.
   * 
   * @throws IllegalStateException if this value cannot be represented as such.
   */
  public abstract BigDecimal asBigDecimal();

  @Override
  public int hashCode()
  {
    return toString().hashCode();
  }

  @Override
  public boolean equals(Object obj)
  {
    return obj instanceof JsonNumber && toString().equals(((JsonNumber)obj).toString());
  }
  
  /**
   * Builder for this and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <T> The concrete type of the builder for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends JsonNumber> extends JsonValue.AbstractBuilder<T, B>
  {
    AbstractBuilder(Class<T> type)
    {
      super(type);
    }
  }
}
