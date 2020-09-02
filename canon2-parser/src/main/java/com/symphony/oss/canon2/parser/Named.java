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

package com.symphony.oss.canon2.parser;

/**
 * A wrapped object with a name.
 * 
 * @author Bruce Skingle
 * 
 * @param <T> The type of the wrapped object. 
 *
 */
class Named<T>
{
  private final String name_;
  private final T value_;
  
  public Named(String name, T value)
  {
    name_ = name;
    value_ = value;
  }

  public String getName()
  {
    return name_;
  }

  public T getValue()
  {
    return value_;
  }

  @Override
  public String toString()
  {
    return "Named [name_=" + name_ + ", value_=" + value_ + "]";
  }
}
