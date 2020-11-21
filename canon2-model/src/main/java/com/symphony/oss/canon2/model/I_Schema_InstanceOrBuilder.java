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
 *    Template name        template/Object/I_InstanceOrBuilder.java.ftl
 *    At                   2020-11-21 06:21:48 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.model;

import javax.annotation.Nullable;

import com.symphony.oss.canon2.runtime.java.IEntityInitialiser;

/**
 * Instance or Builder for Object Schema
 */
public interface I_Schema_InstanceOrBuilder extends IEntityInitialiser
{
  
  /**
   * Return the value of the BooleanSchema attribute.
   *
   * @return the value of the BooleanSchema attribute.
   */
  @Nullable BooleanSchema getBooleanSchema();
  
  /**
   * Return the value of the ArraySchema attribute.
   *
   * @return the value of the ArraySchema attribute.
   */
  @Nullable ArraySchema getArraySchema();
  
  /**
   * Return the value of the ObjectSchema attribute.
   *
   * @return the value of the ObjectSchema attribute.
   */
  @Nullable ObjectSchema getObjectSchema();
  
  /**
   * Return the value of the StringSchema attribute.
   *
   * @return the value of the StringSchema attribute.
   */
  @Nullable StringSchema getStringSchema();
  
  /**
   * Return the value of the NumberSchema attribute.
   *
   * @return the value of the NumberSchema attribute.
   */
  @Nullable NumberSchema getNumberSchema();
  
  /**
   * Return the value of the OneOfSchema attribute.
   *
   * @return the value of the OneOfSchema attribute.
   */
  @Nullable OneOfSchema getOneOfSchema();
}
/*----------------------------------------------------------------------------------------------------
 * End of template template/Object/I_InstanceOrBuilder.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */