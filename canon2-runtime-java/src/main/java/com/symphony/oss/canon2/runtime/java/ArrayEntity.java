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

import java.io.StringReader;

import com.symphony.oss.canon.json.JsonParser;
import com.symphony.oss.canon.json.ParserResultException;
import com.symphony.oss.canon.json.model.IJsonArrayProvider;
import com.symphony.oss.canon.json.model.JsonArray;
import com.symphony.oss.canon.json.model.JsonDomNode;

/**
 * Base class for canon2 generated object classes.
 * 
 * @author Bruce Skingle
 *
 */
public class ArrayEntity extends Entity implements IJsonArrayProvider
{
  private final JsonArray           jsonArray_;
  
  /**
   * Constructor.
   * 
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  public ArrayEntity(IArrayEntityInitialiser initialiser)
  {
    super(initialiser.getJson());
    
    jsonArray_   = initialiser.getJson();
  }
  
  /**
   * Return the JSON object from which this entity was created.
   * 
   * @return the JSON object from which this entity was created.
   */
  @Override
  public JsonArray getJson()
  {
    return jsonArray_;
  }
  
  /**
   * Factory for ArrayEntity and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <B> Concrete type of the built class.
   */
//  public static abstract class Factory<B extends ArrayEntity> extends BaseEntity.Factory<JsonArray, B>
  public static abstract class Factory<B extends ArrayEntity> extends BaseEntity.Factory<JsonDomNode, B>
  {
//    @Deprecated
//    public B newInstance(JsonDomNode jsonArray, ModelRegistry modelRegistry)
//    {
//      return null;
//    }
//    
//    @Override
//    public B newInstance(JsonArray jsonArray, ModelRegistry modelRegistry)
//    {
//      return null;
//    }
    
    @Override
    public B newInstance(StringReader reader, ModelRegistry modelRegistry) throws ParserResultException
    {

      return newInstance(new JsonParser.Builder()
                            .withInput(reader)
                            .build()
                            .parseArray()
                          , modelRegistry);
    }
  }
  
  /**
   * Builder for ArrayEntity and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <T> Concrete type of the builder.
   * @param <B> Concrete type of the built class.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends ArrayEntity>
    extends BaseEntity.AbstractBuilder<JsonArray, T,B>
    implements IArrayEntityInitialiser
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
//    public abstract JsonArray getJsonArray();

    /**
     * Populate the given JsonArray.Builder with all attributes.
     * 
     * This method is called from generated code by super.populateJson(builder).
     * 
     * @param builder a JsonArray.Builder.
     */
    protected void populateJson(JsonArray.Builder builder)
    {
    }
    
//    /**
//     * Initialize this builder with the values from the given serialized form.
//     * 
//     * @param jsonArray    The serialized form of an instance of the built type.
//     * @param modelRegistry A model registry.
//     * 
//     * @return This (fluent method).
//     */
//    @Override
//    public T withValues(JsonArray jsonArray, ModelRegistry modelRegistry)
//    {
//      return super.withValues(jsonArray, modelRegistry);
//    }
  }
}
