/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon.json.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableList;
import com.symphony.oss.canon.json.IParserContext;

/**
 * A JSON Array.
 * 
 * @author Bruce Skingle
 *
 */
@Immutable
public class JsonArray extends JsonDomNode implements Iterable<JsonDomNode>
{
  private final ImmutableList<JsonDomNode> children_;

  JsonArray(AbstractBuilder<?,?> builder)
  {
    super(builder);
    
    children_ = ImmutableList.copyOf(builder.children_);
  }
  
  @Override
  void toString(StringBuilder s, String indent)
  {
    s.append("[");
    toStringChildren(s, indent + INDENT_LEVEL);
    s.append("]\n");
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
     * @param context A ParserContext for reporting errors.
     * @param children One or more elements to be added to the array.
     * 
     * @return This (fluent method).
     */
    public T with(IParserContext context, JsonDomNode ...children)
    {
      for(JsonDomNode child : children)
        children_.add(child);
      
      return self();
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
