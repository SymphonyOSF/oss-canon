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

import com.symphony.oss.canon.json.model.JsonDomNode;

/**
 * An initialiser for a canon generated object.
 * 
 * @author Bruce Skingle
 */
public interface IJsonObjectEntityInitialiser
extends IObjectEntityInitialiser
{ 

  /**
   * Return the attribute wit the given name.
   * 
   * This removes the attribute from the list of unknown keys.
   * 
   * @param attribute The name of the required attribute.
   * 
   * @return the attribute wit the given name.
   */
  JsonDomNode get(String attribute);
}
