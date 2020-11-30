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

import com.google.common.collect.ImmutableSet;
import com.symphony.oss.canon.json.ParserResultException;
import com.symphony.oss.canon.json.model.IJsonArrayProvider;
import com.symphony.oss.canon.json.model.JsonArray;
import com.symphony.oss.canon.json.model.JsonDomNode;

/**
 * Base class for canon2 generated object classes.
 * 
 * @param <E> Element type of this array type. 
 * 
 * @author Bruce Skingle
 *
 */
public class SetArrayEntity<E> extends ArrayEntity implements IJsonArrayProvider
{
  private final ImmutableSet<E>      elements_;
  private final ImmutableSet<Entity> unknownElements_;
  
  /**
   * Constructor.
   * 
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  public SetArrayEntity(ISetArrayEntityInitialiser<E> initialiser)
  {
    super(initialiser);

    elements_         = initialiser.getElements();
    unknownElements_  = initialiser.getCanonUnknownElements();
  }

  /**
   * Return the elements of this array.
   * 
   * @return the elements of this array.
   */
  ImmutableSet<E> getElements()
  {
    return elements_;
  }
  
  /**
   * The set of keys present in the JSON from which this object was deserialized which are not defined by
   * the schema. In the case where an object extends some other object the super-class unknown keys will include
   * all keys defined by the current class.
   * 
   * @return The set of unknown keys  in the JSON from which this object was deserialized;
   */
  public ImmutableSet<Entity> getCanonUnknownElements()
  {
    return unknownElements_;
  }
  
  /**
   * Factory for SetArrayEntity and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <E> Element type of this array type. 
   * @param <B> Concrete type of the built class.
   */
//  public static abstract class Factory<B extends SetArrayEntity> extends BaseEntity.Factory<JsonArray, B>
  public static abstract class Factory<E, B extends SetArrayEntity<E>> extends BaseEntity.Factory<JsonDomNode, B>
  {
    
    @Override
    public B newInstance(StringReader reader, ModelRegistry modelRegistry) throws ParserResultException
    {
      return newInstance(reader, modelRegistry);
    }
  }
  
  /**
   * Builder for SetArrayEntity and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <E> Element type of this array type. 
   * @param <T> Concrete type of the builder.
   * @param <B> Concrete type of the built class.
   */
  public static abstract class AbstractBuilder<E, T extends AbstractBuilder<E,T,B>, B extends SetArrayEntity<E>>
    extends ArrayEntity.AbstractBuilder<T,B>
    implements ISetArrayEntityInitialiser<T>
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
    public ImmutableSet<Entity> getCanonUnknownElements()
    {
      return ImmutableSet.of();
    }
    
    /**
     * Populate the given JsonArray.Builder with all attributes.
     * 
     * This method is called from generated code by super.populateJson(builder).
     * 
     * @param builder a JsonArray.Builder.
     */
    @Override
    protected void populateJson(JsonArray.Builder builder)
    {
      super.populateJson(builder);
    }
  }
}
