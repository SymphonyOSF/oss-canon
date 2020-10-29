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

package com.symphony.oss.canon2.runtime.java;

import com.google.common.collect.ImmutableSet;
import com.symphony.oss.canon.json.model.JsonObject;

/**
 * An initialiser for a canon generated object.
 * 
 * @author Bruce Skingle
 */
public interface IObjectEntityInitialiser
extends IEntityInitialiser
{ 
  /**
   * Return a JSON serialised form for the object to be initialised.
   * 
   * @return a JSON serialised form for the object to be initialised.
   */
  @Override
  JsonObject getJson();
  
  /**
   * The set of keys present in the JSON from which this object was deserialized which are not defined by
   * the schema. In the case where an object extends some other object the super-class unknown keys will include
   * all keys defined by the current class.
   * 
   * @return The set of unknown keys  in the JSON from which this object was deserialized;
   */
  ImmutableSet<String> getCanonUnknownKeys();
}
