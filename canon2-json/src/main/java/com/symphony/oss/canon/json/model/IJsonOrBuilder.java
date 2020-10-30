/*
 *
 *
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.symphony.oss.canon.json.model;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * A JSON Object or Array OR a builder of some sort.
 * 
 * This interface is intended to be the parameter type for the constructor of some class which can be serialised to
 * and deserialised from JSON and which also uses the builder pattern.
 * 
 * The builder should always be able to provide the serialized form so even in the case where getBuilder() returns a
 * non-null value, getJson() should also return a non-null value.
 * 
 * @author Bruce Skingle
 *
 * @param <J> The concrete type of the JSON, expected to be JsonObject or JsonArray
 * @param <B> The concrete type of the builder.
 */
public interface IJsonOrBuilder<J extends JsonDomNode, B>
{
  /**
   * Return the JSON serialised form of the object.
   * 
   * @return the JSON serialised form of the object.
   */
  @Nonnull J getJson();
  
  /**
   * Optionally return the builder.
   * 
   * @return The builder or null.
   */
  @Nullable B getBuilder();
  
  /**
   * Return true if getBuilder() returns non-null.
   * 
   * @return true if getBuilder() returns non-null.
   */
  default boolean isBuilder()
  {
    return getBuilder() != null;
  }
}
