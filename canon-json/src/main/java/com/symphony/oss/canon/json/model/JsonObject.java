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

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.symphony.oss.canon.json.DuplicateAttributeException;
import com.symphony.oss.canon.json.IParserContext;

/**
 * A JSON object.
 * 
 * @author Bruce Skingle
 *
 */
public class JsonObject extends JsonDomNode
{
  private final ImmutableMap<String, JsonDomNode> children_;
  
  protected JsonObject(AbstractBuilder<?,?> builder)
  {
    super(builder);
    
    children_ = ImmutableMap.copyOf(builder.children_);
  }
  
  @Override
  void toString(StringBuilder s, String indent)
  {
    s.append("{");
    toStringChildren(s, indent + INDENT_LEVEL);
    s.append("}\n");
  }

  private void toStringChildren(StringBuilder s, String indent)
  {
    boolean first = true;
    
    for(Entry<String, JsonDomNode> entry : children_.entrySet())
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
      s.append("\"");
      s.append(entry.getKey());
      s.append("\":");
      entry.getValue().toString(s, indent);
    }
    if(!first)
      s.append("\n");
  }
  
  /**
   * Return the children (attributes) of this object.
   * 
   * @return The children (attributes) of this object.
   */
  public ImmutableCollection<JsonDomNode> getChildren()
  {
    return children_.values();
  }

  /**
   * Return the value of the attribute with the given name, or null.
   * 
   * @param name The name of the required attribute.
   * 
   * @return The value of the attribute with the given name, or null.
   */
  public @Nullable JsonDomNode get(String name)
  {
    return children_.get(name);
  }
  
  /**
   * Return the names of all attributes.
   * 
   * @return The names of all attributes.
   */
  public ImmutableSet<String> getNames()
  {
    return children_.keySet();
  }

  /**
   * Builder for this and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <T> The concrete type of the builder for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends JsonObject> extends JsonDomNode.AbstractBuilder<T, B>
  {
    Map<String, JsonDomNode> children_ = new TreeMap<>();
    
    AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    /**
     * Set the given value as a member of this object with the given name.
     * 
     * @param name  The name of the new member.
     * @param value The member.
     * 
     * @return This (fluent method).
     */
    public T with(String name, JsonDomNode value)
    {
      children_.put(name, value);
      
      return self();
    }
    
    /**
     * Set the given value as a member of this object with the given name.
     * 
     * @param name  The name of the new member.
     * @param value The member.
     * 
     * @return This (fluent method).
     */
    public T with(String name, String value)
    {
      children_.put(name, new JsonString.Builder().withValue(value).build());
      
      return self();
    }
    

    
    /**
     * Set the given value as a member of this object with the given name.
     * 
     * @param context A ParserContext for reporting errors.
     * @param name  The name of the new member.
     * @param value The member.
     * 
     * @return This (fluent method).
     * 
     * @throws DuplicateAttributeException If the same attribute is defined more than once.
     */
    public T with(IParserContext context, String name, JsonDomNode value) throws DuplicateAttributeException
    {
      if(children_.containsKey(name))
        throw new DuplicateAttributeException("Attribute \"" + name + "\" redefined", context);
      
      children_.put(name, value);
      
      return self();
    }
  }
  
  /**
   * Builder for JsonObject.
   * 
   * @author Bruce Skingle
   *
   */
  public static class Builder extends AbstractBuilder<Builder, JsonObject>
  {
    /**
     * Constructor.
     */
    public Builder()
    {
      super(Builder.class);
    }

    @Override
    protected JsonObject construct()
    {
      return new JsonObject(this);
    }
  }
}
