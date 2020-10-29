/*
 *
 *
 * Copyright 2017, 2020 Symphony Communication Services, LLC.
 *
 * Licensed to The Symphony Software Foundation (SSF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The SSF licenses this file
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

import com.google.common.collect.ImmutableSet;
import com.symphony.oss.canon.json.model.JsonObject;

/**
 * Base class for canon2 generated object classes.
 * 
 * @author Bruce Skingle
 *
 */
public abstract class ObjectEntity extends Entity 
{
  private final JsonObject           jsonObject_;
  private final ImmutableSet<String> unknownKeys_;
  
  /**
   * Constructor.
   * 
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  public ObjectEntity(IObjectEntityInitialiser initialiser)
  {
    super(initialiser);
    
    Objects.requireNonNull(initialiser.getJson());
    
    jsonObject_   = initialiser.getJson();
    unknownKeys_  = initialiser.getCanonUnknownKeys();
  }
  
  /**
   * Return the JSON object from which this entity was created.
   * 
   * @return the JSON object from which this entity was created.
   */
  @Override
  public JsonObject getJson()
  {
    return jsonObject_;
  }
  
  /**
   * The set of keys present in the JSON from which this object was deserialized which are not defined by
   * the schema. In the case where an object extends some other object the super-class unknown keys will include
   * all keys defined by the current class.
   * 
   * @return The set of unknown keys  in the JSON from which this object was deserialized;
   */
  public ImmutableSet<String> getCanonUnknownKeys()
  {
    return unknownKeys_;
  }
  
  /**
   * Factory for ObjectEntity and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <B> Concrete type of the built class.
   */
  public static abstract class Factory<B extends ObjectEntity> extends Entity.Factory<B>
  {
  }
  
  /**
   * Builder for ObjectEntity and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <T> Concrete type of the builder.
   * @param <B> Concrete type of the built class.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends Entity>
    extends Entity.AbstractBuilder<T,B>
    implements IObjectEntityInitialiser
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

    /**
     * Constructor.
     * 
     * @param type Concrete type of the builder.
     * @param initial Initial values for the builder.
     */
    public AbstractBuilder(Class<T> type, B initial)
    {
      super(type);
    }

    // TODO: perhaps if withValues has been set this should return a non-empty set, if there were unknown keys in the object given.
    @Override
    public ImmutableSet<String> getCanonUnknownKeys()
    {
      return ImmutableSet.of();
    }

//    /**
//     * Return the type id (_type JSON attribute) for entities created by this builder.
//     * 
//     * @return The type id for entities created by this builder.
//     */
//    @Deprecated
//    public String     getCanonType()
//    {
//      return null;
//    }
//    
//    /**
//     * Return the type version (_version JSON attribute) for this entity.
//     * 
//     * @return The type version for this entity.
//     */
//    @Deprecated
//    public String getCanonVersion()
//    {
//      return null;
//    }
    
//    /**
//     * Return the major type version for entities created by this builder.
//     * 
//     * @return The major type version for entities created by this builder.
//     */
//    @Deprecated
//    public Integer    getCanonMajorVersion()
//    {
//      return 0;
//    }
//    
//    /**
//     * Return the minor type version for entities created by this builder.
//     * 
//     * @return The minor type version for entities created by this builder.
//     */
//    @Deprecated
//    public Integer    getCanonMinorVersion()
//    {
//      return 0;
//    }

//    @Override
//    public abstract JsonObject getJsonObject();

    /**
     * Populate the given JsonObject.Builder with all attributes.
     * 
     * This method is called from generated code by super.populateJson(builder).
     * 
     * @param builder a JsonObject.Builder.
     */
    protected void populateJson(JsonObject.Builder builder)
    {
    }
    
    /**
     * Initialize this builder with the values from the given serialized form.
     * 
     * @param jsonObject    The serialized form of an instance of the built type.
     * @param modelRegistry A model registry.
     * 
     * @return This (fluent method).
     */
    @Override
    public T withValues(JsonObject jsonObject, ModelRegistry modelRegistry)
    {
      return super.withValues(jsonObject, modelRegistry);
    }
  }
}
