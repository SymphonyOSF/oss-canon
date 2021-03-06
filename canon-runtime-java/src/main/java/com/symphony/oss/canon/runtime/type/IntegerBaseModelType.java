/*
 *
 *
 * Copyright 2018 Symphony Communication Services, LLC.
 *
 * Licensed to The Symphony Software Foundation (SSF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
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

package com.symphony.oss.canon.runtime.type;

import javax.annotation.Nonnull;

import com.symphony.oss.commons.dom.json.IJsonDomNode;
import com.symphony.oss.commons.dom.json.JsonInteger;

public class IntegerBaseModelType
{
  private final @Nonnull JsonInteger jsonValue_;

  public IntegerBaseModelType(Integer value)
  {
    if(value == null)
      throw new IllegalArgumentException("value is required.");

    jsonValue_ = new JsonInteger(value);
  }
  
  public IntegerBaseModelType(@Nonnull IJsonDomNode node)
  {
    if(node == null)
      throw new IllegalArgumentException("value is required.");
      
    if(node instanceof JsonInteger)
    {
      jsonValue_ = (JsonInteger)node;
    }
    else
    {
      throw new IllegalArgumentException("value must be an instance of Integer not " + node.getClass().getName());
    }
  }

  public @Nonnull Integer getValue()
  {
    return jsonValue_.asInteger();
  }

  public @Nonnull JsonInteger getJsonValue()
  {
    return jsonValue_;
  }
}
