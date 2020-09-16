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

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonObject;

/**
 * Base class for canon2 generated object classes.
 * 
 * @author Bruce Skingle
 *
 */
public class ObjectEntity extends Entity 
{
  private final JsonObject           jsonObject_;
  private final String               type_;
  private final Integer              majorVersion_;
  private final Integer              minorVersion_;
  private final ImmutableSet<String> unknownKeys_;
  
  /**
   * Constructor from Builder.
   * 
   * @param builder The builder.
   */
  public ObjectEntity(AbstractBuilder<?,?> builder)
  {
    super(builder);
    
    jsonObject_   = builder.getJsonObject();
    type_         = builder.getCanonType();
    majorVersion_ = builder.getCanonMajorVersion();
    minorVersion_ = builder.getCanonMinorVersion();
    unknownKeys_  = ImmutableSet.of();
  }
  
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public ObjectEntity(ObjectEntity other)
  {
    super(other);
    
    jsonObject_   = other.getJsonObject();
    type_         = other.getCanonType();
    majorVersion_ = other.getCanonMajorVersion();
    minorVersion_ = other.getCanonMinorVersion();
    unknownKeys_  = other.unknownKeys_;
  }
  
  /**
   * Constructor from serialized form.
   * 
   * @param jsonObject A parse tree of the serialized form.
   */
  public ObjectEntity(JsonObject jsonObject)
  {
    super(jsonObject);
    
    jsonObject_ = jsonObject;
    
    Set<String>       keySet = new HashSet<>(jsonObject.getNames());
    
    if(keySet.remove(JSON_TYPE))
      type_ = jsonObject_.get(JSON_TYPE).toString();
    else
      type_ = "UNKNONWN";
    
    if(keySet.remove(JSON_VERSION))
    {
      String versionStr = jsonObject_.get(JSON_VERSION).toString();
      int     i = versionStr.indexOf('.');
      
      if(i == -1)
        throw new IllegalArgumentException("Version must be of the form Magor.Minor not \"" + versionStr + "\"");
      
      try
      {
        majorVersion_ = Integer.parseInt(versionStr.substring(0,i));
        minorVersion_ = Integer.parseInt(versionStr.substring(i+1));
      }
      catch(NumberFormatException e)
      {
        throw new IllegalArgumentException("Version must be of the form Magor.Minor not \"" + versionStr + "\"", e);
      }
    }
    else
    {
      majorVersion_ = null;
      minorVersion_ = null;
    }
    
    unknownKeys_ = ImmutableSet.copyOf(keySet);
  }
  
  /**
   * Return the JSON object from which this entity was created.
   * 
   * @return the JSON object from which this entity was created.
   */
  public JsonObject getJsonObject()
  {
    return jsonObject_;
  }
  
  /**
   * Return the type identifier (_type JSON attribute) for this entity.
   * 
   * @return The type identifier for this object.
   */
  public @Nonnull String getCanonType()
  {
    return type_;
  }

  /**
   * @return The major part of the canon schema version defining this object.
   */
  public @Nullable Integer getCanonMajorVersion()
  {
    return majorVersion_;
  }

  /**
   * @return The minor part of the canon schema version defining this object.
   */
  public @Nullable Integer getCanonMinorVersion()
  {
    return minorVersion_;
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
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends Entity> extends Entity.AbstractBuilder<T,B>
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
    
    /**
     * Return the type id (_type JSON attribute) for entities created by this builder.
     * 
     * @return The type id for entities created by this builder.
     */
    public abstract String     getCanonType();
    
    /**
     * Return the type version (_version JSON attribute) for this entity.
     * 
     * @return The type version for this entity.
     */
    public abstract String getCanonVersion();
    
    /**
     * Return the major type version for entities created by this builder.
     * 
     * @return The major type version for entities created by this builder.
     */
    public abstract Integer    getCanonMajorVersion();
    
    /**
     * Return the minor type version for entities created by this builder.
     * 
     * @return The minor type version for entities created by this builder.
     */
    public abstract Integer    getCanonMinorVersion();

    @Override
    protected JsonDomNode getJsonDomNode()
    {
      return getJsonObject();
    }

    protected abstract JsonObject getJsonObject();

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
    public T withValues(JsonObject jsonObject, ModelRegistry modelRegistry)
    {
      return self();
    }
  }
}
