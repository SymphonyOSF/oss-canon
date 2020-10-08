/*
 *
 *
 * Copyright 2018, 2020 Symphony Communication Services, LLC.
 *
 * Licensed to The Symphony Software Foundation (SSF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
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

package com.symphony.oss.canon2.runtime.java;

import java.util.Objects;

import javax.annotation.Nonnull;

import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.commons.fluent.BaseAbstractBuilder;

/**
 * Base class for canon2 generated classes.
 * 
 * @author Bruce Skingle
 *
 */
public class Entity
{
  /** Name of the JSON type attribute in a serialised canon object. */
  public static final String JSON_TYPE = "_type";

  /** Name of the JSON version attribute in a serialised canon object. */
  public static final String JSON_VERSION = "_version";
  
  private final @Nonnull JsonDomNode jsonDomNode_;
  
  /**
   * Constructor.
   * 
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  public Entity(IEntityInitialiser initialiser)
  {
    Objects.requireNonNull(initialiser.getJsonDomNode());
    
    jsonDomNode_ = initialiser.getJsonDomNode();
  }
  
  
  
  
  
//  @Deprecated
//  public Entity(@Nonnull AbstractBuilder<?,?> builder)
//  {
//    jsonDomNode_ = builder.getJsonDomNode();
//  }
  
  @Deprecated
  public Entity(Entity other)
  {
    jsonDomNode_ = other.jsonDomNode_;
  }
  
  @Deprecated
  public Entity(@Nonnull JsonDomNode jsonDomNode)
  {
    Objects.requireNonNull(jsonDomNode);
    
    jsonDomNode_ = jsonDomNode;
  }
  
  
  
  
  

  /**
   * Return the serialized form of this object.
   * 
   * @return the serialized form of this object.
   */
  public @Nonnull JsonDomNode getJsonDomNode()
  {
    return jsonDomNode_;
  }
  
  @Override
  public @Nonnull String toString()
  {
    return jsonDomNode_.toString();
  }
  
  @Override
  public int hashCode()
  {
    return jsonDomNode_.hashCode();
  }

  @Override
  public boolean equals(Object other)
  {
    return other instanceof Entity && jsonDomNode_.equals(((Entity)other).jsonDomNode_);
  }
  
  /**
   * Builder for Entity and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <T> Concrete type of the builder.
   * @param <B> Concrete type of the built class.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends Entity>
    extends BaseAbstractBuilder<T,B>
    implements IEntityInitialiser
  {
    /**
     * Constructor.
     * 
     * @param type Concrete type of the builder.
     */
    public AbstractBuilder(Class<T> type)
    {
      super(type);
    }
  }
  
  /**
   * Factory for Entity and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <B> Concrete type of the built class.
   */
  public static abstract class Factory<B extends Entity>
  {
    /**
     * Return the type identifier (_type JSON attribute) for entities created by this factory.
     * 
     * @return The type identifier for entities created by this factory.
     */
    public abstract String getCanonType();
    
    /**
     * Return a new entity instance created from the given JSON serialization.
     * 
     * @param jsonObject    The JSON serialized form of the required entity.
     * @param modelRegistry A context which controls validation behaviour and provides factories for deserialization.
     * 
     * @return An instance of the entity represented by the given serialized form.
     * 
     * @throws IllegalArgumentException If the given JSON is not valid.
     */
    public abstract B newInstance(JsonObject jsonObject, ModelRegistry modelRegistry);
    
    /**
     * Return a new entity instance created from the given JSON serialization.
     * 
     * @param jsonObject    The JSON serialized form of the required entity.
     * 
     * @return An instance of the entity represented by the given serialized form.
     * 
     * @throws IllegalArgumentException If the given JSON is not valid.
     */
    public B newInstance(JsonObject jsonObject)
    {
      return newInstance(jsonObject, ModelRegistry.DEFAULT);
    }
  }
}
