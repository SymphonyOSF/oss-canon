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

package com.symphony.oss.canon2.generator;

import java.util.Collection;

import com.google.common.collect.ImmutableList;

/**
 * Template model for primitive types number, integer, boolean, string.
 * 
 * @author Bruce Skingle
 *
 */
public interface IPrimitiveSchemaTemplateModel<
T extends ITemplateModel<T,M,S>,
M extends IOpenApiTemplateModel<T,M,S>,
S extends ISchemaTemplateModel<T,M,S>
>
    extends ISchemaTemplateModel<T,M,S>
{
  /**
   * Return this object as an ISchemaTemplateModel.
   * 
   * Implementations of this class must also be a subclass of the implementation of ISchemaTemplateModel
   * but the Java type system is unable to represent that in a parameterised type declaration.
   * 
   * @return this object as an ITemplateModel.
   */
  S asSchemaTemplateModel();
  
  @Override
  default Collection<T> getChildren()
  {
    return ImmutableList.of();
  }
}
