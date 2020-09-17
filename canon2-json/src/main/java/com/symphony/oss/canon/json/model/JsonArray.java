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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableList;
import com.symphony.oss.commons.immutable.ImmutableByteArray;

/**
 * A JSON Array.
 * 
 * @author Bruce Skingle
 *
 */
@Immutable
public class JsonArray extends JsonDomNode implements Iterable<JsonDomNode>, IJsonOrBuilder<JsonArray, Void>
{
  private final ImmutableList<JsonDomNode> children_;

  JsonArray(AbstractBuilder<?,?> builder)
  {
    super(builder);
    
    children_ = ImmutableList.copyOf(builder.children_);
  }
  
  @Override
  public JsonArray getJson()
  {
    return this;
  }

  @Override
  public Void getBuilder()
  {
    return null;
  }
  
  @Override
  void toString(StringBuilder s, String indent)
  {
    s.append("[");
    toStringChildren(s, indent + INDENT_LEVEL);
    s.append(indent);
    s.append("]");
  }

  private void toStringChildren(StringBuilder s, String indent)
  {
    boolean first = true;
    
    for(JsonDomNode child : children_)
    {
      if(first)
      {
        first = false;
        s.append("\n");
      }
      else
      {
        s.append(",\n");
      }
      
      s.append(indent);
      child.toString(s, indent);
    }
    if(!first)
      s.append("\n");
  }
  
  class ArrayIterator implements Iterator<JsonDomNode>
  {
    int cnt_;
    
    @Override
    public boolean hasNext()
    {
      return cnt_ < children_.size();
    }

    @Override
    public JsonDomNode next()
    {
      if(cnt_ < children_.size())
        return children_.get(cnt_++);
      
      throw new IndexOutOfBoundsException("Array length is " + children_.size());
    }
    
  }
  
  @Override
  public Iterator<JsonDomNode> iterator()
  {
    return new ArrayIterator();
  }

  /**
   * Builder for JsonArray and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <T> Concrete type of builder for fluent methods.
   * @param <B> Concrete type of built type.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends JsonArray> extends JsonDomNode.AbstractBuilder<T, B>
    implements Consumer<JsonDomNode>
  {
    List<JsonDomNode> children_ = new LinkedList<>();
    
    AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    /**
     * Replace the elements of the array with the given list.
     * 
     * A copy of the given list is made and that list replaces any current children.
     * 
     * @param children The members of the array.
     * 
     * @return This (fluent method).
     */
    public T with(List<JsonDomNode> children)
    {
      children_ = new LinkedList<>(children);
      
      return self();
    }
    
    /**
     * Add the given elements to the array.
     * 
     * @param children One or more elements to be added to the array.
     * 
     * @return This (fluent method).
     */
    public T with(JsonDomNode ...children)
    {
      for(JsonDomNode child : children)
        children_.add(child);
      
      return self();
    }
    
    /**
     * Add the given elements to the array.
     * 
     * @param children One or more elements to be added to the array.
     * 
     * @return This (fluent method).
     */
    public T with(String ...children)
    {
      for(String child : children)
        children_.add(new JsonString.Builder().withValue(child).build());
      
      return self();
    }
    
    /**
     * Add the given elements to the array.
     * 
     * @param children One or more elements to be added to the array.
     * 
     * @return This (fluent method).
     */
    public T with(ImmutableByteArray ...children)
    {
      for(ImmutableByteArray child : children)
        children_.add(new JsonBase64String.Builder().withValue(child).build());
      
      return self();
    }
    
    /**
     * Add the given elements to the array.
     * 
     * @param children One or more elements to be added to the array.
     * 
     * @return This (fluent method).
     */
    public T with(Boolean ...children)
    {
      for(Boolean child : children)
        children_.add(new JsonBoolean.Builder().withValue(child).build());
      
      return self();
    }
    
    /**
     * Add the given elements to the array.
     * 
     * @param children One or more elements to be added to the array.
     * 
     * @return This (fluent method).
     */
    public T with(BigDecimal ...children)
    {
      for(BigDecimal child : children)
        children_.add(new JsonBigDecimal.Builder().withValue(child).build());
      
      return self();
    }
    
    /**
     * Add the given elements to the array.
     * 
     * @param children One or more elements to be added to the array.
     * 
     * @return This (fluent method).
     */
    public T with(BigInteger ...children)
    {
      for(BigInteger child : children)
        children_.add(new JsonBigInteger.Builder().withValue(child).build());
      
      return self();
    }
    
    /**
     * Add the given elements to the array.
     * 
     * @param children One or more elements to be added to the array.
     * 
     * @return This (fluent method).
     */
    public T with(Double ...children)
    {
      for(Double child : children)
        children_.add(new JsonDouble.Builder().withValue(child).build());
      
      return self();
    }
    
    /**
     * Add the given elements to the array.
     * 
     * @param children One or more elements to be added to the array.
     * 
     * @return This (fluent method).
     */
    public T with(Float ...children)
    {
      for(Float child : children)
        children_.add(new JsonFloat.Builder().withValue(child).build());
      
      return self();
    }
    
    /**
     * Add the given elements to the array.
     * 
     * @param children One or more elements to be added to the array.
     * 
     * @return This (fluent method).
     */
    public T with(Long ...children)
    {
      for(Long child : children)
        children_.add(new JsonLong.Builder().withValue(child).build());
      
      return self();
    }
    
    /**
     * Add the given elements to the array.
     * 
     * @param children One or more elements to be added to the array.
     * 
     * @return This (fluent method).
     */
    public T with(Integer ...children)
    {
      for(Integer child : children)
        children_.add(new JsonInteger.Builder().withValue(child).build());
      
      return self();
    }

    @Override
    public void accept(JsonDomNode child)
    {
      children_.add(child);
    }
  }
  
  /**
   * Builder for JsonArray.
   * 
   * @author Bruce Skingle
   *
   */
  public static class Builder extends AbstractBuilder<Builder, JsonArray>
  {
    /**
     * Constructor.
     */
    public Builder()
    {
      super(Builder.class);
    }

    @Override
    protected JsonArray construct()
    {
      return new JsonArray(this);
    }
  }
}
