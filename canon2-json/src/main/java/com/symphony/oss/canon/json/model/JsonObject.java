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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.codec.binary.Base64;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.symphony.oss.canon.json.DuplicateAttributeException;
import com.symphony.oss.canon.json.IParserContext;
import com.symphony.oss.commons.immutable.ImmutableByteArray;
import com.symphony.oss.commons.type.provider.IStringProvider;

/**
 * A JSON object.
 * 
 * @author Bruce Skingle
 *
 */
public class JsonObject extends JsonDomNode implements IJsonOrBuilder<JsonObject, Void>
{
  private final ImmutableMap<String, JsonDomNode>    children_;
  private final ImmutableMap<String, IParserContext> nameContexts_;
  
  protected JsonObject(AbstractBuilder<?,?> builder)
  {
    super(builder);
    
    children_     = builder.children_     == null ? ImmutableMap.of() : ImmutableMap.copyOf(builder.children_);
    nameContexts_ = builder.nameContexts_ == null ? ImmutableMap.of() : ImmutableMap.copyOf(builder.nameContexts_);
  }
  
  @Override
  public JsonObject getJson()
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
    s.append("{");
    toStringChildren(s, indent + INDENT_LEVEL);
    s.append(indent);
    s.append("}");
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
   * Return the IParserContext of the given attribute name.
   * 
   * @param name The name of the required attribute.
   * 
   * Note that this method does not return null, if there is no context for the given name then the
   * context of the overall JSON is given. It is assumed that this method will only be called for
   * attributes which are known to exist. If this object was deserialised then there will be
   * a context for every valid attribute. If the object was constructed then there will not.
   * 
   * @return The IParserContext of the given attribute name.
   */
  public IParserContext getNameContext(String name)
  {
    IParserContext result = nameContexts_.get(name);
    
    if(result == null)
      return getJson().getContext();
    
    return result;
  }
  
  /**
   * Return true iff an attribute with the given name exists.
   * 
   * @param name An attribute name of interest.
   * 
   * @return true iff an attribute with the given name exists.
   */
  public boolean containsKey(String name)
  {
    return children_.containsKey(name);
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
   * Return the object attribute with the given name, or null if there is no attribute with the given name.
   * 
   * @param name The name of the required object.
   * 
   * @return The required object or null.
   * 
   * @throws IllegalStateException if the attribute exists but is not an object.
   */
  public @Nullable JsonObject getObject(String name)
  {
    JsonDomNode node = get(name);
    
    if(node == null)
      return null;
    
    if(node instanceof JsonObject)
      return (JsonObject) node;
    
    throw new IllegalStateException("\"" + name + "\" is not an object");
  }
  
  /**
   * Return the object attribute with the given name
   * 
   * @param name The name of the required object.
   * 
   * @return The required object.
   * 
   * @throws IllegalStateException if the attribute does not exist or is not an object.
   */
  public @Nonnull JsonObject getRequiredObject(String name)
  {
    JsonDomNode node = get(name);
    
    if(node instanceof JsonObject)
      return (JsonObject) node;
    
    throw new IllegalStateException("\"" + name + "\" is not an object");
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
    Map<String, JsonDomNode> children_;
    Map<String, IParserContext> nameContexts_;
    
    AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    @Override
    public T withCanonicalize(boolean canonicalize)
    {
      if(children_ != null)
        throw new IllegalStateException("withCanonicalize() must be called prior to inserting children.");
      
      return super.withCanonicalize(canonicalize);
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
      if(children_ == null)
        children_ = canonicalize_ ? new TreeMap<>() : new LinkedHashMap<>();
        
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
      if(children_ == null)
        children_ = canonicalize_ ? new TreeMap<>() : new LinkedHashMap<>();
      
      JsonString node = Base64.isBase64(value) ?
          new JsonBase64String.Builder().withValue(value).build() :
          new JsonString.Builder().withValue(value).build();
          
      children_.put(name, node);
      
      return self();
    }

    /**
     * Set the given value as a member of this object with the given name, only if it is non-null.
     * 
     * @param name  The name of the new member.
     * @param value The member.
     * 
     * @return This (fluent method).
     */
    public T addIfNotNull(String name, JsonDomNode value)
    {
      if(value != null)
        return with(name, value);
      
      return self();
    }

    /**
     * Set the given value as a member of this object with the given name, only if it is non-null.
     * 
     * @param name  The name of the new member.
     * @param value The member.
     * 
     * @return This (fluent method).
     */
    public T addIfNotNull(String name, String value)
    {
      if(value != null)
        return with(name, value);
      
      return self();
    }

    /**
     * Set the given value as a member of this object with the given name, only if it is non-null.
     * 
     * @param name  The name of the new member.
     * @param value The member.
     * 
     * @return This (fluent method).
     */
    public T addIfNotNull(String name, Boolean value)
    {
      if(value != null)
        return with(name, new JsonBoolean.Builder().withValue(value).build());
      
      return self();
    }

    /**
     * Set the given value as a member of this object with the given name, only if it is non-null.
     * 
     * @param name  The name of the new member.
     * @param value The member.
     * 
     * @return This (fluent method).
     */
    public T addIfNotNull(String name, ImmutableByteArray value)
    {
      if(value != null)
        return with(name, new JsonBase64String.Builder().withValue(value).build());
      
      return self();
    }

    /**
     * Set the given value as a member of this object with the given name, only if it is non-null.
     * 
     * @param name  The name of the new member.
     * @param value The member.
     * 
     * @return This (fluent method).
     */
    public T addIfNotNull(String name, BigDecimal value)
    {
      if(value != null)
        return with(name, new JsonBigDecimal.Builder().withValue(value).build());
      
      return self();
    }

    /**
     * Set the given value as a member of this object with the given name, only if it is non-null.
     * 
     * @param name  The name of the new member.
     * @param value The member.
     * 
     * @return This (fluent method).
     */
    public T addIfNotNull(String name, BigInteger value)
    {
      if(value != null)
        return with(name, new JsonBigInteger.Builder().withValue(value).build());
      
      return self();
    }

    /**
     * Set the given value as a member of this object with the given name, only if it is non-null.
     * 
     * @param name  The name of the new member.
     * @param value The member.
     * 
     * @return This (fluent method).
     */
    public T addIfNotNull(String name, Double value)
    {
      if(value != null)
        return with(name, new JsonDouble.Builder().withValue(value).build());
      
      return self();
    }

    /**
     * Set the given value as a member of this object with the given name, only if it is non-null.
     * 
     * @param name  The name of the new member.
     * @param value The member.
     * 
     * @return This (fluent method).
     */
    public T addIfNotNull(String name, Float value)
    {
      if(value != null)
        return with(name, new JsonFloat.Builder().withValue(value).build());
      
      return self();
    }

    /**
     * Set the given value as a member of this object with the given name, only if it is non-null.
     * 
     * @param name  The name of the new member.
     * @param value The member.
     * 
     * @return This (fluent method).
     */
    public T addIfNotNull(String name, Long value)
    {
      if(value != null)
        return with(name, new JsonLong.Builder().withValue(value).build());
      
      return self();
    }

    /**
     * Set the given value as a member of this object with the given name, only if it is non-null.
     * 
     * @param name  The name of the new member.
     * @param value The member.
     * 
     * @return This (fluent method).
     */
    public T addIfNotNull(String name, Integer value)
    {
      if(value != null)
        return with(name, new JsonInteger.Builder().withValue(value).build());
      
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
      if(children_ == null)
        children_ = canonicalize_ ? new TreeMap<>() : new LinkedHashMap<>();
        
      if(children_.containsKey(name))
        throw new DuplicateAttributeException("Attribute \"" + name + "\" redefined", context);
      
      if(nameContexts_ == null)
        nameContexts_ = canonicalize_ ? new TreeMap<>() : new LinkedHashMap<>();
      
      children_.put(name, value);
      nameContexts_.put(name, context);
      
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

  /**
   * Return the String value of the attribute with the give name.
   * 
   * @param name          The name of the attribute of interest.
   * @param defaultValue  The value to be returned if the attribute does not exist.
   * 
   * @return the String value of the attribute with the give name.
   * 
   * @throws IllegalStateException if the attribute exists but is not a String value.
   */
  public String getString(String name, String defaultValue)
  {
    JsonDomNode node = get(name);
    
    if(node == null)
      return defaultValue;
    
    if(node instanceof IStringProvider)
      return ((IStringProvider)node).asString();
    
    throw new IllegalStateException("\"" + name + "\" is not a String value.");
  }

  /**
   * Return the String value of the attribute with the give name.
   * 
   * @param name          The name of the attribute of interest.
   * 
   * @return the String value of the attribute with the give name.
   * 
   * @throws IllegalStateException if the attribute exists but is not a String value.
   */
  public String getRequiredString(String name)
  {
    JsonDomNode node = get(name);
    
    if(node == null)
      throw new IllegalStateException("\"" + name + "\" does not exist");
    
    if(node instanceof IStringProvider)
      return ((IStringProvider)node).asString();
    
    throw new IllegalStateException("\"" + name + "\" is not a String value.");
  }
}
