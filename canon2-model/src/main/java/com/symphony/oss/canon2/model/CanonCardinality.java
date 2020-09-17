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
 *    At                   2020-09-16 16:04:42 BST
 *----------------------------------------------------------------------------------------------------
 */
package com.symphony.oss.canon2.model;

import javax.annotation.concurrent.Immutable;

/**
 * Enum  CanonCardinality canon
 * Model com.symphony.oss.canon2.generator.java.JavaOpenApiTemplateModel@4df50bcc
 * Generated from com.symphony.oss.canon2.generator.java.JavaPrimitiveSchemaTemplateModel@d4342c2 at {entity.context.path}
 */
@Immutable
public enum CanonCardinality
{
  /** L_I_S_T */
  LIST("LIST"),
  /** S_E_T */
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
}
/*----------------------------------------------------------------------------------------------------
 * End of template template/Enum/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */