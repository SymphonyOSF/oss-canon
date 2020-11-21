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
 *    At                   2020-11-12 11:16:47 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.model;

import java.math.BigDecimal;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.symphony.oss.canon2.runtime.java.IObjectEntityInitialiser;

/**
 * Instance or Builder for Object NumberSchema
 */
public interface INumberSchemaInstanceOrBuilder extends IObjectEntityInitialiser
{
  
  /**
   * Return the value of the x-canon-attributes attribute.
   *
   * @return the value of the x-canon-attributes attribute.
   */
  @Nullable CanonAttributes getXCanonAttributes();
  
  /**
   * Return the value of the x-canon-builderFacade attribute.
   *
   * @return the value of the x-canon-builderFacade attribute.
   */
  @Nullable Boolean getXCanonBuilderFacade();
  
  /**
   * Return the value of the format attribute.
   *
   * @return the value of the format attribute.
   */
  @Nullable String getFormat();
  
  /**
   * Return the value of the maximum attribute.
   *
   * @return the value of the maximum attribute.
   */
  @Nullable BigDecimal getMaximum();
  
  /**
   * Return the value of the x-canon-identifier attribute.
   *
   * @return the value of the x-canon-identifier attribute.
   */
  @Nullable String getXCanonIdentifier();
  
  /**
   * Return the value of the type attribute.
   *
   * @return the value of the type attribute.
   */
  @Nonnull NumberSchema.Type getType();
  
  /**
   * Return the value of the x-canon-facade attribute.
   *
   * @return the value of the x-canon-facade attribute.
   */
  @Nullable Boolean getXCanonFacade();
  
  /**
   * Return the value of the minimum attribute.
   *
   * @return the value of the minimum attribute.
   */
  @Nullable BigDecimal getMinimum();
}
/*----------------------------------------------------------------------------------------------------
 * End of template template/Object/I_InstanceOrBuilder.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */