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

import javax.annotation.Nonnull;

import com.symphony.oss.canon.json.JsonParser;
import com.symphony.oss.canon.json.ParserResultException;
import com.symphony.oss.canon.json.model.JsonDomNode;

/**
 * Base class for canon2 generated classes.
 * 
 * @author Bruce Skingle
 *
 */
public class Entity extends BaseEntity
{
  /**
   * Constructor.
   * 
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  public Entity(IEntityInitialiser initialiser)
  {
    super(initialiser.getJson());
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
    super(jsonDomNode);
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
    extends BaseEntity.AbstractBuilder<JsonDomNode,T,B>
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
//     * Populate the given JsonObject.Builder with all attributes.
//     * 
//     * This method is called from generated code by super.populateJson(builder).
//     * 
//     * @param builder a JsonObject.Builder.
//     */
//    protected void populateJson(JsonObject.Builder builder)
//    {
//    }
  }
  
  /**
   * Factory for Entity and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <B> Concrete type of the built class.
   */
  public static abstract class Factory<B extends Entity> extends BaseEntity.Factory<JsonDomNode, B>
  {
    @Override
    public B newInstance(StringReader reader, ModelRegistry modelRegistry) throws ParserResultException
    {

      return newInstance(new JsonParser.Builder()
                            .withInput(reader)
                            .build()
                            .parseDomNode()
                          , modelRegistry);
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
