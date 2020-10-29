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
 *    At                   2020-10-28 18:16:15 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.model;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.symphony.oss.canon2.runtime.java.IObjectEntityInitialiser;

/**
 * Instance or Builder for Object OpenApiObject
 */
public interface IOpenApiObjectInstanceOrBuilder extends IObjectEntityInitialiser
{
  
  /**
   * Return the value of the components attribute.
   *
   * @return the value of the components attribute.
   */
  @Nullable ComponentsObject getComponents();
  
  /**
   * Return the value of the x-canon-generators attribute.
   *
   * @return the value of the x-canon-generators attribute.
   */
  @Nullable CanonGeneratorConfig getXCanonGenerators();
  
  /**
   * Return the value of the openapi attribute.
   *
   * @return the value of the openapi attribute.
   */
  @Nullable String getOpenapi();
  
  /**
   * Return the value of the paths attribute.
   *
   * @return the value of the paths attribute.
   */
  @Nullable PathsObject getPaths();
  
  /**
   * Return the value of the x-canon-id attribute.
   *
   * @return the value of the x-canon-id attribute.
   */
  @Nullable String getXCanonId();
  
  /**
   * Return the value of the x-canon-version attribute.
   *
   * @return the value of the x-canon-version attribute.
   */
  @Nullable SemanticVersion getXCanonVersion();
  
  /**
   * Return the value of the x-canon-identifier attribute.
   *
   * @return the value of the x-canon-identifier attribute.
   */
  @Nullable String getXCanonIdentifier();
  
  /**
   * Return the value of the canon attribute.
   *
   * @return the value of the canon attribute.
   */
  @Nullable String getCanon();
  
  /**
   * Return the value of the info attribute.
   *
   * @return the value of the info attribute.
   */
  @Nonnull InfoObject getInfo();
}
/*----------------------------------------------------------------------------------------------------
 * End of template template/Object/I_InstanceOrBuilder.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */