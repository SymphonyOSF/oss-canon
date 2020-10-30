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

import org.apache.commons.codec.binary.Base64;

import com.symphony.oss.commons.immutable.ImmutableByteArray;
import com.symphony.oss.commons.type.provider.IStringProvider;

/**
 * A JSON String.
 * 
 * @author Bruce Skingle
 *
 */
public class JsonString extends JsonValue implements IStringProvider
{
  private final String             value_;
  private BigInteger               asBigInteger_;
  private NumberFormatException    asBigIntegerException_;
  private BigDecimal               asBigDecimal_;
  private NumberFormatException    asBigDecimalException_;
  private ImmutableByteArray       asImmutableByteArray_;
  private IllegalArgumentException asImmutableByteArrayException_;

  JsonString(AbstractBuilder<?,?> builder)
  {
    super(builder);
    
    value_ = builder.value_;
  }

  /**
   * Builder for this and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <T> The concrete type of the builder for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends JsonString> extends JsonValue.AbstractBuilder<T, B>
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
   * Builder for JsonObject.
   * 
   * @author Bruce Skingle
   *
   */
  public static class Builder extends AbstractBuilder<Builder, JsonString>
  {
    /**
     * Constructor.
     */
    public Builder()
    {
      super(Builder.class);
    }

    @Override
    protected JsonString construct()
    {
      return new JsonString(this);
    }
  }
  
  @Override
  void toString(StringBuilder s, String indent)
  {
    s.append('"');
    for(char c : value_.toCharArray())
    {
      switch(c)
      {
        case '\\':  s.append("\\\\");   break;
        case '\"':  s.append("\\\"");   break;
        case '\n':  s.append("\\n");   break;
        case '\r':  s.append("\\r");   break;
        case '\t':  s.append("\\t");   break;
        case '\f':  s.append("\\f");   break;
        case '\b':  s.append("\\b");   break;
          
        default:
          if(c < ' ')
            s.append(String.format("\\u%04x", (int) c));
          else
            s.append(c);
      }
    }
    s.append('"');
  }

  @Override
  public String asString()
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
    return obj instanceof JsonString && value_.equals(((JsonString)obj).value_);
  }

  /**
   * Return true iff this value can be represented as a BigInteger.
   * 
   * @return true iff this value can be represented as a BigInteger.
   */
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
  
  /**
   * Return this value as a BigInteger.
   * 
   * @return this value as a BigInteger.
   * 
   * @throws IllegalStateException if this value cannot be represented as such.
   */
  public BigInteger asBigInteger()
  {
    if(isBigInteger())
      return asBigInteger_;
    else
      throw asBigIntegerException_;
  }
  
  /**
   * Return true iff this value can be represented as a BigDecimal.
   * 
   * @return true iff this value can be represented as a BigDecimal.
   */
  public synchronized boolean isBigDecimal()
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

  /**
   * Return this value as a BigDecimal.
   * 
   * @return this value as a BigDecimal.
   * 
   * @throws IllegalStateException if this value cannot be represented as such.
   */
  public BigDecimal asBigDecimal()
  {
    if(isBigDecimal())
      return asBigDecimal_;
    else
      throw asBigDecimalException_;
  }
  
  /**
   * Return true iff this value can be represented as a ImmutableByteArray.
   * 
   * @return true iff this value can be represented as a ImmutableByteArray.
   */
  public synchronized boolean isImmutableByteArray()
  {
    if(asImmutableByteArray_ == null && asImmutableByteArrayException_ == null)
    {
      try
      {
        asImmutableByteArray_ = ImmutableByteArray.newInstance(Base64.decodeBase64(value_));
      }
      catch(IllegalArgumentException e)
      {
        asImmutableByteArrayException_ = e;
      }
    }
    return asImmutableByteArrayException_ == null;
  }

  /**
   * Return this value as a ImmutableByteArray.
   * 
   * @return this value as a ImmutableByteArray.
   * 
   * @throws IllegalStateException if this value cannot be represented as such.
   */
  public ImmutableByteArray asImmutableByteArray()
  {
    if(isImmutableByteArray())
      return asImmutableByteArray_;
    else
      throw asImmutableByteArrayException_;
  }
}
