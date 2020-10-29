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
import com.symphony.oss.canon.json.model.JsonDomNode;

public class JsonEntityInitialiser implements IEntityInitialiser
{
  private static final ImmutableSet<String> EMPTY_KEYS = ImmutableSet.of();
  
  private final JsonDomNode jsonDomNode_;
  private final ModelRegistry modelRegistry_;

  public JsonEntityInitialiser(JsonDomNode jsonDomNode, ModelRegistry modelRegistry)
  {
    jsonDomNode_ = jsonDomNode;
    modelRegistry_ = modelRegistry;
  }

  @Override
  public JsonDomNode getJson()
  {
    return jsonDomNode_;
  }

  @Override
  public ModelRegistry getModelRegistry()
  {
    return modelRegistry_;
  }

  @Deprecated
  public ImmutableSet<String> getCanonUnknownKeys()
  {
    return EMPTY_KEYS;
  }
}
