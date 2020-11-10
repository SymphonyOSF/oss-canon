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

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.apache.commons.codec.binary.Base64;

import com.symphony.oss.canon.json.IParserContext;
import com.symphony.oss.commons.fault.FaultAccumulator;
import com.symphony.oss.commons.fluent.BaseAbstractBuilder;
import com.symphony.oss.commons.immutable.ImmutableByteArray;

/**
 * A node in a JSON DOM.
 * 
 * @author Bruce Skingle
 *
 */
@Immutable
public abstract class JsonDomNode implements Comparable<JsonDomNode>
{
  static final String INDENT_LEVEL = "  ";
  
  private final IParserContext context_;
  final boolean                canonicalize_;
  private String stringValue_;
  
  protected JsonDomNode(AbstractBuilder<?,?> builder)
  {
    context_ = builder.context_;
    canonicalize_ = builder.canonicalize_;
  }

  /**
   * Create a JsonDomNode for the given value.
   * 
   * @param value The value.
   * 
   * @return a JsonDomNode for the given value.
   */
  public static JsonDomNode newInstance(String value)
  {
    return Base64.isBase64(value) ?
        new JsonBase64String.Builder().withValue(value).build() :
        new JsonString.Builder().withValue(value).build();
  }

  /**
   * Create a JsonDomNode for the given value.
   * 
   * @param value The value.
   * 
   * @return a JsonDomNode for the given value.
   */
  public static JsonDomNode newInstance(Boolean value)
  {
    return new JsonBoolean.Builder().withValue(value).build();
  }

  /**
   * Create a JsonDomNode for the given value.
   * 
   * @param value The value.
   * 
   * @return a JsonDomNode for the given value.
   */
  public static JsonDomNode newInstance(ImmutableByteArray value)
  {
    return new JsonBase64String.Builder().withValue(value).build();
  }

  /**
   * Create a JsonDomNode for the given value.
   * 
   * @param value The value.
   * 
   * @return a JsonDomNode for the given value.
   */
  public static JsonDomNode newInstance(BigDecimal value)
  {
    return new JsonBigDecimal.Builder().withValue(value).build();
  }

  /**
   * Create a JsonDomNode for the given value.
   * 
   * @param value The value.
   * 
   * @return a JsonDomNode for the given value.
   */
  public static JsonDomNode newInstance(BigInteger value)
  {
    return new JsonBigInteger.Builder().withValue(value).build();
  }

  /**
   * Create a JsonDomNode for the given value.
   * 
   * @param value The value.
   * 
   * @return a JsonDomNode for the given value.
   */
  public static JsonDomNode newInstance(Double value)
  {
    return new JsonDouble.Builder().withValue(value).build();
  }

  /**
   * Create a JsonDomNode for the given value.
   * 
   * @param value The value.
   * 
   * @return a JsonDomNode for the given value.
   */
  public static JsonDomNode newInstance(Float value)
  {
    return new JsonFloat.Builder().withValue(value).build();
  }

  /**
   * Create a JsonDomNode for the given value.
   * 
   * @param value The value.
   * 
   * @return a JsonDomNode for the given value.
   */
  public static JsonDomNode newInstance(Long value)
  {
    return new JsonLong.Builder().withValue(value).build();
  }

  /**
   * Create a JsonDomNode for the given value.
   * 
   * @param value The value.
   * 
   * @return a JsonDomNode for the given value.
   */
  public static JsonDomNode newInstance(Integer value)
  {
    return new JsonInteger.Builder().withValue(value).build();
  }
  
  @Override
  public synchronized String toString()
  {
    if(stringValue_ == null)
    {
      StringBuilder s = new StringBuilder();
      
      toString(s, "");

      s.append('\n');
      
      stringValue_ = s.toString();
    }
    
    return stringValue_;
  }
  
  @Override
  public int hashCode()
  {
    return toString().hashCode();
  }

  @Override
  public boolean equals(Object other)
  {
    return other instanceof JsonDom && toString().equals(((JsonDom)other).toString());
  }

  @Override
  public int compareTo(JsonDomNode other)
  {
    return toString().compareTo(other.toString());
  }
  
  /**
   * Return the location of the source for this node if it was parsed.
   * 
   * @return The location of the source for this node if it was parsed, otherwise null.
   */
  public @Nullable IParserContext getContext()
  {
    return context_;
  }

  abstract void toString(StringBuilder s, String indent);

  /**
   * Builder for this and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <T> The concrete type of the builder for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends JsonDomNode> extends BaseAbstractBuilder<T, B>
  {
    IParserContext context_;
    boolean        canonicalize_ = true;
    
    AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    /**
     * Set the location of the source of this object in the parsed input stream.
     * 
     * @param context The location of the source of this object in the parsed input stream.
     * 
     * @return This (fluent method).
     */
    public T withContext(IParserContext context)
    {
      context_ = context;
      
      return self();
    }
    
    /**
     * Set the canonicalization mode for this object.
     * 
     * @param canonicalize The canonicalization mode for this object.
     * 
     * @return This (fluent method).
     */
    public T withCanonicalize(boolean canonicalize)
    {
      canonicalize_ = canonicalize;
      
      return self();
    }
  }
}
