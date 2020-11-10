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

import java.io.StringReader;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.symphony.oss.canon.json.JsonParser;
import com.symphony.oss.canon.json.ParserErrorException;
import com.symphony.oss.canon.json.ParserException;
import com.symphony.oss.canon.json.ParserResultException;
import com.symphony.oss.canon.json.model.IJsonDomNodeProvider;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.commons.fluent.BaseAbstractBuilder;

/**
 * Base class for canon2 generated classes.
 * 
 * @author Bruce Skingle
 *
 */
public class Entity implements IJsonDomNodeProvider
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
    Objects.requireNonNull(initialiser.getJson());
    
    jsonDomNode_ = initialiser.getJson();
  }
  
  /**
   * Constructor.
   * 
   * This constructor is intended for creating an untyped Entity where the actual type ofthe object is unknown.
   * 
   * @param jsonDomNode The JSON serialised form of the entity.
   */
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
  @Override
  public @Nonnull JsonDomNode getJson()
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
    private ModelRegistry modelRegistry_ = ModelRegistry.DEFAULT;

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

    @Override
    public ModelRegistry getModelRegistry()
    {
      return modelRegistry_;
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

//    @Override
//    @Deprecated
//    public ImmutableSet<String> getCanonUnknownKeys()
//    {
//      // TODO Auto-generated method stub
//      return null;
//    }
    
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
     * @throws ParserException  If the given JSON is not valid.
     */
    public abstract B newInstance(JsonDomNode jsonObject, ModelRegistry modelRegistry);
    
    /**
     * a new entity instance created from the given JSON serialization, or null if the given KSON is invalid.
     * 
     * @param jsonObject    The JSON serialized form of the required entity.
     * @param modelRegistry A context which controls validation behaviour and provides factories for deserialization.
     * 
     * @return An instance of the entity represented by the given serialized form.
     */
    public @Nullable B newInstanceOrNull(JsonDomNode jsonObject, ModelRegistry modelRegistry)
    {
      try
      {
         return newInstance(jsonObject, modelRegistry);
      }
      catch(ParserErrorException e)
      {
       return null;
      }
    }
    
    /**
     * Return a new entity instance created from the given JSON serialization.
     * 
     * @param json          The JSON serialized form of the required entity.
     * @param modelRegistry A context which controls validation behaviour and provides factories for deserialization.
     * 
     * @return An instance of the entity represented by the given serialized form.
     * 
     * @throws ParserResultException  If the given JSON is not valid.
     * @throws ParserException        If the given JSON is not valid.
     */
    public B newInstance(String json, ModelRegistry modelRegistry) throws ParserResultException
    {
      return newInstance(new StringReader(json), modelRegistry);
    }
//    public B newInstance(JsonDomNode jsonObject, ModelRegistry modelRegistry)
//    {
//      return newInstance((JsonObject)jsonObject, modelRegistry);
//    }
//    
//    @Deprecated
//    public B newInstance(JsonObject jsonObject, ModelRegistry modelRegistry)
//    {
//      return null;
//    }

    /**
     * Return a new entity instance created from the given JSON serialization.
     * 
     * @param reader        A Reader containing the JSON serialized form of the required entity.
     * @param modelRegistry A context which controls validation behaviour and provides factories for deserialization.
     * 
     * @return An instance of the entity represented by the given serialized form.
     * 
     * @throws ParserException  If the given JSON is not valid.
     */
    public B newInstance(StringReader reader, ModelRegistry modelRegistry) throws ParserResultException
    {

      return newInstance(JsonParser.parseObject(reader), modelRegistry);
    }
    
//    /**
//     * Return a new entity instance created from the given JSON serialization.
//     * 
//     * @param jsonObject    The JSON serialized form of the required entity.
//     * 
//     * @return An instance of the entity represented by the given serialized form.
//     * 
//     * @throws ParserException If the given JSON is not valid.
//     */
//    public B newInstance(JsonObject jsonObject)
//    {
//      return newInstance(jsonObject, ModelRegistry.DEFAULT);
//    }
  }
}
