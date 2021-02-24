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
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

import com.google.common.collect.ImmutableList;
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
public class ListArrayEntity<E> extends ArrayEntity implements IJsonArrayProvider, Iterable<E>
{
  private final ImmutableList<E>      elements_;
  private final ImmutableList<Entity> unknownElements_;
  
  /**
   * Constructor.
   * 
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  public ListArrayEntity(IListArrayEntityInitialiser<E> initialiser)
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
  ImmutableList<E> getElements()
  {
    return elements_;
  }
  
  @Override
  public Iterator<E> iterator()
  {
    return elements_.iterator();
  }

  @Override
  public void forEach(Consumer<? super E> action)
  {
    elements_.forEach(action);
  }

  @Override
  public Spliterator<E> spliterator()
  {
    return elements_.spliterator();
  }

  /**
   * The set of keys present in the JSON from which this object was deserialized which are not defined by
   * the schema. In the case where an object extends some other object the super-class unknown keys will include
   * all keys defined by the current class.
   * 
   * @return The set of unknown keys  in the JSON from which this object was deserialized;
   */
  public ImmutableList<Entity> getCanonUnknownElements()
  {
    return unknownElements_;
  }
  
  /**
   * Factory for ListArrayEntity and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <E> Element type of this array type. 
   * @param <B> Concrete type of the built class.
   */
//  public static abstract class Factory<B extends ListArrayEntity> extends BaseEntity.Factory<JsonArray, B>
  public static abstract class Factory<E, B extends ListArrayEntity<E>> extends BaseEntity.Factory<JsonDomNode, B>
  {
    
    @Override
    public B newInstance(StringReader reader, ModelRegistry modelRegistry) throws ParserResultException
    {
      return newInstance(reader, modelRegistry);
    }
  }
  
  /**
   * Builder for ListArrayEntity and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <E> Element type of this array type. 
   * @param <T> Concrete type of the builder.
   * @param <B> Concrete type of the built class.
   */
  public static abstract class AbstractBuilder<E, T extends AbstractBuilder<E,T,B>, B extends ListArrayEntity<E>>
    extends ArrayEntity.AbstractBuilder<T,B>
    //implements IListArrayEntityInitialiser<T>
  {
    private static final ImmutableList<Entity> UNKNOWN_ELEMENTS = ImmutableList.of();

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
    //@Override
    public ImmutableList<Entity> getCanonUnknownElements()
    {
      return UNKNOWN_ELEMENTS;
    }
    
//    /**
//     * Populate the given JsonArray.Builder with all attributes.
//     * 
//     * This method is called from generated code by super.populateJson(builder).
//     * 
//     * @param builder a JsonArray.Builder.
//     */
//    @Override
//    protected void populateJson(JsonArray.Builder builder)
//    {
//      super.populateJson(builder);
//    }
  }
}
