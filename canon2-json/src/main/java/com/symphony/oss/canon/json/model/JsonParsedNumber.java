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
 * A JSON Number value parsed from a String.
 * 
 * @author Bruce Skingle
 *
 */
public class JsonParsedNumber extends JsonNumber
{
  private final String          value_;
  private boolean               parsedInteger_;
  private int                   asInteger_;
  private NumberFormatException asIntegerException_;
  private boolean               parsedLong_;
  private long                  asLong_;
  private NumberFormatException asLongException_;
  private BigInteger            asBigInteger_;
  private NumberFormatException asBigIntegerException_;
  private boolean               parsedFloat_;
  private float                 asFloat_;
  private NumberFormatException asFloatException_;
  private boolean               parsedDouble_;
  private double                asDouble_;
  private NumberFormatException asDoubleException_;
  private BigDecimal            asBigDecimal_;
  private NumberFormatException asBigDecimalException_;

  private JsonParsedNumber(AbstractBuilder<?,?> builder)
  {
    super(builder);
    
    value_ = builder.value_;
  }

//  @Override
//  public Number asNumber()
//  {
//    return value_;
//  }
//
//  @Override
//  public Double asDouble()
//  {
//    return value_;
//  }
//
//  @Override
//  public BigDecimal asBigDecimal()
//  {
//    return BigDecimal.valueOf(value_);
//  }

  /**
   * Builder for this and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <T> The concrete type of the builder for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends JsonParsedNumber> extends JsonNumber.AbstractBuilder<T, B>
  {
    private String value_;

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
    public T withValue(String value)
    {
      value_ = value;
      
      return self();
    }
  }
  
  /**
   * Builder.
   * 
   * @author Bruce Skingle
   *
   */
  public static class Builder extends AbstractBuilder<Builder, JsonParsedNumber>
  {
    /**
     * Constructor.
     */
    public Builder()
    {
      super(Builder.class);
    }

    @Override
    protected JsonParsedNumber construct()
    {
      return new JsonParsedNumber(this);
    }
  }
  
  @Override
  void toString(StringBuilder s, String indent)
  {
    s.append(value_);
  }

  @Override
  public synchronized boolean isInteger()
  {
    if(!parsedInteger_)
    {
      try
      {
        asInteger_ = Integer.parseInt(value_);
      }
      catch(NumberFormatException e)
      {
        asIntegerException_ = e;
      }
      parsedInteger_ = true;
    }
    return asIntegerException_ == null;
  }

  @Override
  public Integer asInteger()
  {
    if(isInteger())
      return asInteger_;
    else
      throw asIntegerException_;
  }

  @Override
  public synchronized boolean isLong()
  {
    if(!parsedLong_)
    {
      try
      {
        asLong_ = Long.parseLong(value_);
      }
      catch(NumberFormatException e)
      {
        asLongException_ = e;
      }
      parsedLong_ = true;
    }
    return asLongException_ == null;
  }

  @Override
  public Long asLong()
  {
    if(isLong())
      return asLong_;
    else
      throw asLongException_;
  }

  @Override
  public synchronized boolean isBigInteger()
  {
    if(asBigInteger_ == null && asBigIntegerException_ == null)
    {
      try
      {
        asBigInteger_ = new BigInteger(value_);
      }
      catch(NumberFormatException e)
      {
        asBigIntegerException_ = e;
      }
    }
    return asBigIntegerException_ == null;
  }

  @Override
  public BigInteger asBigInteger()
  {
    if(isBigInteger())
      return asBigInteger_;
    else
      throw asBigIntegerException_;
  }

  @Override
  public synchronized boolean isFloat()
  {
    if(!parsedFloat_)
    {
      try
      {
        asFloat_ = Float.parseFloat(value_);
      }
      catch(NumberFormatException e)
      {
        asFloatException_ = e;
      }
      parsedFloat_ = true;
    }
    return asFloatException_ == null;
  }

  @Override
  public Float asFloat()
  {
    if(isFloat())
      return asFloat_;
    else
      throw asFloatException_;
  }

  @Override
  public synchronized boolean isDouble()
  {
    if(!parsedDouble_)
    {
      try
      {
        asDouble_ = Double.parseDouble(value_);
      }
      catch(NumberFormatException e)
      {
        asDoubleException_ = e;
      }
      parsedDouble_ = true;
    }
    return asDoubleException_ == null;
  }

  @Override
  public Double asDouble()
  {
    if(isDouble())
      return asDouble_;
    else
      throw asDoubleException_;
  }

  // Not exposed because all numbers are supposed to be representable as BigDecimal.
  boolean isBigDecimal()
  {
    if(asBigDecimal_ == null && asBigDecimalException_ == null)
    {
      try
      {
        asBigDecimal_ = new BigDecimal(value_);
      }
      catch(NumberFormatException e)
      {
        asBigDecimalException_ = e;
      }
    }
    return asBigDecimalException_ == null;
  }

  @Override
  public BigDecimal asBigDecimal()
  {
    if(isBigDecimal())
      return asBigDecimal_;
    else
      throw asBigDecimalException_;
  }
}
