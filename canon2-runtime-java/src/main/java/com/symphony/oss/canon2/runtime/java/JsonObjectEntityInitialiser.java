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

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonObject;

/**
 * Implementation of IJsonObjectEntityInitialiser.
 * 
 * @author Bruce Skingle
 *
 */
public class JsonObjectEntityInitialiser extends JsonEntityInitialiser implements IJsonObjectEntityInitialiser
{
  private final JsonObject    jsonObject_;
  private final String        type_;
  private final Set<String>   keySet_;
  
  /**
   * Constructor.
   * 
   * @param jsonObject      The JSON serialised form of the object.
   * @param modelRegistry   A ModelRegistry for deserialization.
   */
  public JsonObjectEntityInitialiser(JsonObject jsonObject, ModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    
    jsonObject_ = jsonObject;
    keySet_ = new HashSet<>(jsonObject.getNames());
    
    if(keySet_.remove(Entity.JSON_TYPE))
      type_ = jsonObject_.getString(Entity.JSON_TYPE, "UNKNONWN");
    else
      type_ = "UNKNONWN";
  }
  
  @Override
  public JsonDomNode get(String attribute)
  {
    keySet_.remove(attribute);
    
    return jsonObject_.get(attribute);
  }

  @Override
  public JsonObject getJson()
  {
    return jsonObject_;
  }

  @Override
  public ImmutableSet<String> getCanonUnknownKeys()
  {
    return ImmutableSet.copyOf(keySet_);
  }
}
