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

import javax.annotation.concurrent.Immutable;

/**
 * A JSON Dom (a.k.a document or text) containing an array.
 * 
 * @author Bruce Skingle
 *
 */
@Immutable
public class JsonArrayDom extends JsonDom
{
  private final JsonArray array_;

  protected JsonArrayDom(AbstractBuilder<?,?> builder, JsonArray array)
  {
    super(builder);
    
    array_ = array;
  }
  
  @Override
  void toString(StringBuilder s, String indent)
  {
    s.append("Array DOM\n");
    s.append(array_.toString());
    
    super.toString(s, indent);
  }

  /**
   * Return the content of this dom.
   * 
   * @return The content of this dom.
   */
  public JsonArray getArray()
  {
    return array_;
  }
}
