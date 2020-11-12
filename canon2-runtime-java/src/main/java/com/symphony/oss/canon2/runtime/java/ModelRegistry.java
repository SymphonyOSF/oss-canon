/*
 *
 *
 * Copyright 2017 Symphony Communication Services, LLC.
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

import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.symphony.oss.canon.json.JsonParser;
import com.symphony.oss.canon.json.ParserErrorException;
import com.symphony.oss.canon.json.model.JsonArray;
import com.symphony.oss.canon.json.model.JsonArrayDom;
import com.symphony.oss.canon.json.model.JsonDom;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon.json.model.JsonValue;
import com.symphony.oss.canon2.runtime.java.Entity.Factory;


/**
 * A ModelRegistry is a container for IModels which can deserialize objects from any of the contained models.
 * 
 * @author Bruce Skingle
 *
 */
public class ModelRegistry
{
  /** A default ModelRegistry with no factories and the default validation level. */
  public static final ModelRegistry                     DEFAULT = new Builder().build();
  /** A default ModelRegistry with no factories and strict validation. */
  public static final ModelRegistry                     STRICT = new Builder().withValidation(ParserValidation.STRICT).build();
  
  private final ParserValidation                        parserValidation_;
  private final ImmutableMap<String, Entity.Factory<?>> factoryMap_;
  
  private ModelRegistry(Builder builder)
  {
    parserValidation_ = builder.parserValidation_;
    factoryMap_       = ImmutableMap.copyOf(builder.factoryMap_);
  }

  /**
   * Return the validation level.
   * 
   * @return the validation level.
   */
  public ParserValidation getParserValidation()
  {
    return parserValidation_;
  }

  /**
   * Return a new entity instance parsed from the given JSON object.
   * 
   * @param jsonObject        A JSON object containing the serialized form of an entity.
   * 
   * @return The deserialized entity.
   * 
   * @throws NullPointerException if the value is null.
   * @throws ParserErrorException if the value is otherwise invalid.
   * This may be the case if the schema defines limits on the magnitude of the value, or if a facade
   * has been written for the type.
   */
  public Entity newInstance(JsonObject jsonObject)
  {
    String typeId;
    
    typeId = jsonObject.getString(CanonRuntime.JSON_TYPE, null);
    
    if(typeId == null)
    {
      return new Entity(jsonObject);
    }
    
    Factory<?> factory = factoryMap_.get(typeId);
    
    if(factory == null)
      return new Entity(jsonObject);
    
    return factory.newInstance(jsonObject, this);
  }

  /**
   * Return a new entity instance of the given type, parsed from the given input.
   * 
   * The returned entity will be an instance of the given type, but may be a sub-class. The defaultTypeId is only used
   * in cases where there is no type information in the serialised JSON. This enables canon generated client stubs
   * to be used with non-canon server implementations. 
   * 
   * @param jsonObject A JSON object containing the serialized form of an entity.
   * @param defaultTypeId The type ID of the expected type.
   * @param type The expected type of the entity.
   * 
   * @return The deserialized entity.
   * 
   * @throws NullPointerException if the value is null.
   * @throws ParserErrorException if the value is not of the expected type or is otherwise invalid.
   * This may be the case if the schema defines limits on the magnitude of the value, or if a facade
   * has been written for the type.
   */
  public <E extends Entity> E newInstance(JsonObject jsonObject, String defaultTypeId, Class<E> type)
  {
    String typeId;
    
    if(defaultTypeId == null)
    {
      typeId = jsonObject.getRequiredString(CanonRuntime.JSON_TYPE);
    }
    else
    {
      typeId = jsonObject.getString(CanonRuntime.JSON_TYPE, null);
      
      if(typeId == null)
      {
        typeId = defaultTypeId;
      }
    }
    
    Factory<?> factory = factoryMap_.get(typeId);
    
    if(factory == null)
    {
      if(defaultTypeId != null)
      {
        factory = factoryMap_.get(defaultTypeId);
      }
      
      if(factory == null)
      {
        throw new ParserErrorException("Unknown type \"" + typeId + "\"", jsonObject.getContext());
      }
    }
    
    Entity result = factory.newInstance(jsonObject, this);
    
    if(type.isInstance(result))
    {
      return type.cast(result);
    }
    else
    {
      throw new ParserErrorException("Expected instance of " + type + " but found a " + typeId, jsonObject.getContext());
    }
  }
  
  
  
  
  
  
  
  

//  /**
//   * Return a new entity instance parsed from the given JSON object.
//   * 
//   * @param jsonObject        A JSON object containing the serialized form of an entity.
//   * 
//   * @return The deserialized entity.
//   * 
//   * @throws NullPointerException if the value is null.
//   * @throws IllegalStateException if the value is otherwise invalid.
//   * This may be the case if the schema defines limits on the magnitude of the value, or if a facade
//   * has been written for the type.
//   */
//  public Entity NEWnewInstance(JsonObject jsonObject)
//  {
//    IJsonObjectEntityInitialiser initialiser = new JsonObjectEntityInitialiser(jsonObject, DEFAULT);
//    
//    if(initialiser.getCanonType() == null)
//    {
//      return new Entity(initialiser);
//    }
//    
//    Factory<?> factory = factoryMap_.get(initialiser.getCanonType());
//    
//    if(factory == null)
//      return new Entity(initialiser);
//    
//    return factory.newInstance(initialiser);
//  }
//
//  /**
//   * Return a new entity instance of the given type, parsed from the given input.
//   * 
//   * The returned entity will be an instance of the given type, but may be a sub-class. The defaultTypeId is only used
//   * in cases where there is no type information in the serialised JSON. This enables canon generated client stubs
//   * to be used with non-canon server implementations. 
//   * 
//   * @param jsonObject A JSON object containing the serialized form of an entity.
//   * @param defaultTypeId The type ID of the expected type.
//   * @param type The expected type of the entity.
//   * 
//   * @return The deserialized entity.
//   * 
//   * @throws NullPointerException if the value is null.
//   * @throws IllegalStateException if the value is not of the expected type or is otherwise invalid.
//   * This may be the case if the schema defines limits on the magnitude of the value, or if a facade
//   * has been written for the type.
//   */
//  public <E extends Entity> E NEWnewInstance(JsonObject jsonObject, String defaultTypeId, Class<E> type)
//  {
//    IJsonObjectEntityInitialiser initialiser = new JsonObjectEntityInitialiser(jsonObject, DEFAULT);
//    
//    String typeId = initialiser.getCanonType();
//    
//    if(typeId == null)
//    {
//      if(defaultTypeId == null)
//      {
//        throw new IllegalStateException("No default typeId given and no typeId found in JSON");
//      }
//      else
//      {
//        typeId = defaultTypeId;
//      }
//    }
//    
//    Factory<?> factory = factoryMap_.get(typeId);
//    
//    if(factory == null)
//    {
//      if(defaultTypeId != null)
//      {
//        factory = factoryMap_.get(defaultTypeId);
//      }
//      
//      if(factory == null)
//      {
//        throw new IllegalStateException("Unknown type \"" + typeId + "\"");
//      }
//    }
//    
//    Entity result = factory.newInstance(initialiser);
//    
//    if(type.isInstance(result))
//    {
//      return type.cast(result);
//    }
//    else
//    {
//      throw new IllegalStateException("Expected instance of " + type + " but found a " + typeId);
//    }
//  }
  
//  /**
//   * Return a new entity instance of the given type, parsed from the given input.
//   * 
//   * The returned entity will be an instance of the given type, but may be a sub-class. The defaultTypeId is only used
//   * in cases where there is no type information in the serialised JSON. This enables canon generated client stubs
//   * to be used with non-canon server implementations. 
//   * 
//   * @param reader A Reader containing the serialized form of an entity.
//   * @param defaultTypeId The type ID of the expected type.
//   * @param type The expected type of the entity.
//   * 
//   * @return The deserialized entity.
//   * 
//   * @throws NullPointerException if the value is null.
//   * 
//   * @throws ParserResultException If the value cannot be parsed.
//   * @throws ParserErrorException if the value is not of the expected type or is otherwise invalid.
//   * This may be the case if the schema defines limits on the magnitude of the value, or if a facade
//   * has been written for the type.
//   */
//  public <E extends Entity> E parseOne(Reader reader, String inputSourceName, String defaultTypeId, Class<E> type) throws ParserResultException
//  {
//    return newInstance(JsonParser.parseObject(reader, inputSourceName), defaultTypeId, type);
//  }

  /**
   * Parse a list of JSON objects from the given Reader.
   * 
   * This method does not do a partial read, it is expected that the contents of the Reader
   * are a single list of objects.
   * 
   * @param reader  The source of JSON objects.
   * @return  The parsed list of JsonObjects.
   */
  public static List<JsonObject> parseListOfJsonObjects(Reader reader)
  {
    JsonDom dom = new JsonParser.Builder()
        .withInput(reader)
        .build()
        .parse();
    
//    if(dom instanceof JsonObjectDom)
//    {
//      return ((JsonObjectDom)dom).getObject();
//    }
    
    if(dom instanceof JsonArrayDom)
    {
      List<JsonObject>  result = new LinkedList<>();
      JsonArray array = ((JsonArrayDom)dom).getArray();
      
      for(JsonDomNode item : array)
      {
        if(item instanceof JsonObject)
        {
          result.add((JsonObject) item);
        }
        else
        {
          throw new IllegalStateException("Expected an array of JSON objects but read a " + item.getClass().getName());
        }
      }
      return result;
    }
    else
    {
      throw new IllegalStateException("Expected a JSON array but read a " + dom.getClass().getName());
    }
  }
  
  /**
   * Parse a list of JSON values from the given Reader.
   * 
   * This method does not do a partial read, it is expected that the contents of the Reader
   * are a single value.
   * 
   * @param reader  The source of a JSON value.
   * @return  The parsed list of (Immutable) JsonValues.
   * 
   * @throws IllegalStateException If the input cannot be parsed or does not contain a list of objects.
   */
  public static List<JsonValue> parseListOfJsonValues(Reader reader)
  {
    JsonDom dom = new JsonParser.Builder()
        .withInput(reader)
        .build()
        .parse();
    
    if(dom instanceof JsonArrayDom)
    {
      List<JsonValue>  result = new LinkedList<>();
      JsonArray array = ((JsonArrayDom)dom).getArray();
      
      for(JsonDomNode item : array)
      {
        if(item instanceof JsonValue)
        {
          result.add((JsonValue) item);
        }
        else
        {
          throw new IllegalStateException("Expected an array of JSON values but read a " + item.getClass().getName());
        }
      }
      return result;
    }
    else
    {
      throw new IllegalStateException("Expected a JSON array but read a " + dom.getClass().getName());
    }
  }
  
//  /**
//   * Parse a single JSON value from the given Reader.
//   * 
//   * This method does not do a partial read, it is expected that the contents of the Reader
//   * are a single value.
//   * 
//   * @param reader  The source of a JSON value.
//   * @return  The parsed value as an (Immutable) JsonValue.
//   * 
//   * @throws IllegalStateException If the input cannot be parsed or does not contain a single object.
//   */
//  public static JsonValue<?,?> parseOneJsonValue(Reader reader)
//  {
//    ObjectMapper  mapper = new ObjectMapper().configure(Feature.AUTO_CLOSE_SOURCE, false);
//    
//    try
//    {
//      JsonNode tree = mapper.readTree(reader);
//      
//      if(tree.isValueNode())
//      {
//        return (JsonValue<?,?>)JacksonAdaptor.adapt(tree);
//      }
//      else
//      {
//        throw new IllegalStateException("Expected a JSON value but read a " + tree.getClass().getName());
//      }
//    }
//    catch(IOException e)
//    {
//      throw new IllegalStateException("Failed to parse input", e);
//    }
//  }
  
//  /**
//   * Return a new entity instance parsed from the given input.
//   * 
//   * @param reader A Reader containing the serialized form of an entity.
//   * 
//   * @return The deserialized entity.
//   * 
//   * @throws ParserResultException If the value cannot be parsed.
//   * @throws NullPointerException if the value is null.
//   * @throws ParserErrorException if the value is not of the expected type or is otherwise invalid.
//   * This may be the case if the schema defines limits on the magnitude of the value, or if a facade
//   * has been written for the type.
//   */
//  public Entity parseOne(Reader reader) throws ParserResultException, ParserErrorException
//  {
//    return newInstance(JsonParser.parseObject(reader));
//  }
  

//  /**
//   * Return a new entity instance parsed from the given input.
//   * 
//   * @param input A String containing the serialized form of an entity.
//   * 
//   * @return The deserialized entity.
//   * 
//   * @throws ParserResultException If the value cannot be parsed.
//   * @throws NullPointerException if the value is null.
//   * @throws ParserErrorException if the value is not of the expected type or is otherwise invalid.
//   * This may be the case if the schema defines limits on the magnitude of the value, or if a facade
//   * has been written for the type.
//   */
//  public Entity parseOne(String input) throws ParserResultException, ParserErrorException
//  {
//    return newInstance(JsonParser.parseObject(new StringReader(input)));
//  }

//  /**
//   * Parse a stream of entities from the given input and pass them to the given consumer.
//   * 
//   * @param in            A input stream containing serialised entities.
//   * @param consumer      A sink for the parsed entities.
//   * @throws ParserErrorException  If the input is not an array of serialised entities.
//   */
//  public void parseStream(InputStream in, Consumer<Entity> consumer)
//  {
//    Consumer<JsonDomNode> jsonConsumer = new Consumer<JsonDomNode>()
//    {
//      @Override
//      public void accept(JsonDomNode node)
//      {
//        if(node instanceof JsonObject)
//        {
//          try
//          {
//            Entity result = newInstance((JsonObject)node);
//            
//            consumer.accept(result);
//          }
//          catch(ParserErrorException e)
//          {
//            throw new InvocationTargetException(e);
//          }
//        }
//        else
//        {
//          throw new ParserErrorException("Expected an array of JSON objects but read a " + node.getClass().getName(), node.getContext());
//        }
//      }
//    };
//    
//    new JsonParser.Builder()
//        .withInput(in)
//        .withArrayElementConsumer(jsonConsumer)
//        .build()
//        .parse();
//  }
  
  /**
   * Builder for ModelRegistry.
   * 
   * @author Bruce Skingle
   *
   */
  public static class Builder
  {
    private ParserValidation  parserValidation_ = ParserValidation.ALLOW_UNKNOWN_ATTRIBUTES;
    private Map<String, Entity.Factory<?>>  factoryMap_ = new HashMap<>();
    
    /**
     * Default constructor.
     */
    public Builder()
    {}
    
    /**
     * Constructor from an existing ModelRegistry.
     *  
     * @param other An existing ModelRegistry, the builder is initialized with the same values.
     */
    public Builder(ModelRegistry other)
    {
      parserValidation_ = other.parserValidation_;
      factoryMap_.putAll(other.factoryMap_);
    }

    /**
     * Set the validation level.
     * 
     * @param parserValidation The validation to be applied.
     * 
     * @return this (Fluent interface)
     */
    public Builder withValidation(ParserValidation parserValidation)
    {
      parserValidation_ = parserValidation;
      
      return this;
    }

    /**
     * Register the given models.
     * 
     * @param factories Entity factories to be registered.
     * 
     * @return this (Fluent interface)
     */
    public Builder withFactories(Entity.Factory<?> ...factories)
    {
      for(Entity.Factory<?> factory :factories)
      {
        factoryMap_.put(factory.getCanonType(), factory);
      }
      
      return this;
    }
    
    /**
     * Instantiate an immutable ModelRegistry.
     * 
     * @return an immutable ModelRegistry initialized with the values of this builder.
     */
    public ModelRegistry build()
    {
      return new ModelRegistry(this);
    }
  }
}
