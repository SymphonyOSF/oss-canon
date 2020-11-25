/**
 * GENERATED CODE - DO NOT EDIT OR CHECK IN TO SOURCE CODE CONTROL
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
 *
 *----------------------------------------------------------------------------------------------------
 * Generated from
 *    Input source         canon.json
 *    Generator groupId    org.symphonyoss.s2.canon
 *              artifactId canon2-generator-java
 *    Template name        template/Enum/_.java.ftl
 *    At                   2020-11-25 15:52:09 GMT
 *----------------------------------------------------------------------------------------------------
 */
package com.symphony.oss.canon2.model;

import javax.annotation.concurrent.Immutable;

/**
 * Enum  CanonCardinality canon
 * Model canon
 * Generated from CanonCardinality at {entity.context.path}
 */
@Immutable
public enum CanonCardinality
{
  /** LIST */
  LIST("LIST"),
  /** SET */
  SET("SET")
  ;
  
  private final String value_;
  
  private CanonCardinality(String value)
  {
    value_ = value;
  }
  
  /**
   * Return the serialized value of this enum constant.
   *
   * @return the serialized value of this enum constant.
   */
  public String getValue()
  {
    return value_;
  }
  
  /**
   * Deserialize an enum constant value from a String value.
   * 
   * @param value The serialized form of an enum constant.
   * 
   * @return The enum constant value from the given String value.
   * 
   * @throws IllegalArgumentException If the given value is not a valid enum constant.
   */
  public static final CanonCardinality deserialize(String value)
  {
    switch(value)
    {
      case "LIST":
        return LIST;
        
      case "SET":
        return SET;
        
      default:
        throw new IllegalArgumentException("No enum constant \"" + value + "\" in CanonCardinality");
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template template/Enum/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */