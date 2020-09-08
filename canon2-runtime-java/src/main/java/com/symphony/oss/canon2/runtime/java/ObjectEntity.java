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
   * Builder for ObjectEntity and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <T> Concrete type of the builder.
   * @param <B> Concrete type of the build class.
   */
  public abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends Entity> extends Entity.AbstractBuilder<T,B>
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

    protected abstract String     getCanonType();
    protected abstract Integer    getCanonMajorVersion();
    protected abstract Integer    getCanonMinorVersion();

    protected JsonObject getJsonObject()
    {
      JsonObject.Builder builder = new JsonObject.Builder();
      
      populateJson(builder);
      
      return builder.build();
    }
    
    protected void populateJson(JsonObject.Builder builder)
    {
      builder.with(JSON_TYPE, getCanonType());
      builder.with(JSON_VERSION, getCanonMajorVersion() + "." + getCanonMinorVersion());
    }
  }
}
