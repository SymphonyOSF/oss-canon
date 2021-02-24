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

import com.symphony.oss.canon.json.model.JsonArray;

/**
 * Implementation of IJsonObjectEntityInitialiser.
 * 
 * @author Bruce Skingle
 *
 */
public class JsonArrayEntityInitialiser extends JsonEntityInitialiser
{
  private final JsonArray     jsonArray_;
  
  /**
   * Constructor.
   * 
   * @param jsonArray      The JSON serialised form of the Array.
   * @param modelRegistry   A ModelRegistry for deserialization.
   */
  public JsonArrayEntityInitialiser(JsonArray jsonArray, ModelRegistry modelRegistry)
  {
    super(jsonArray, modelRegistry);
    
    jsonArray_ = jsonArray;
  }

  @Override
  public JsonArray getJson()
  {
    return jsonArray_;
  }
}
