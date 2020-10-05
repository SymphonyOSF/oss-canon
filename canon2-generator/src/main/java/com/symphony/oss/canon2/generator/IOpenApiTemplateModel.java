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

import com.symphony.oss.canon2.model.GenerationException;
import com.symphony.oss.canon2.model.ResolvedOpenApiObject;

/**
 * The template model for the overall OpenApi model.
 * 
 * @author Bruce Skingle
 *
 * @param <T> Concrete implementation of ITemplateModel.
 * @param <M> Concrete implementation of IOpenApiTemplateModel.
 * @param <S> Concrete implementation of ISchemaTemplateModel.
 */
public interface IOpenApiTemplateModel<
  T extends ITemplateModel<T,M,S>,
  M extends IOpenApiTemplateModel<T,M,S>,
  S extends ISchemaTemplateModel<T,M,S>>
    extends ITemplateModel<T,M,S>, ITemplateModelNameSpace
{
  /**
   * Return this object as an ITemplateModel.
   * 
   * Implementations of this class must also be a subclass of the implementation of ITemplateModel
   * but the Java type system is unable to represent that in a parameterised type declaration.
   * 
   * @return this object as an ITemplateModel.
   */
  T asTemplateModel();
  
  /**
   * Return this object as an IOpenApiTemplateModel.
   * 
   * This method is needed to maintain type safety.
   * 
   * @return this object as an OpenApiTemplateModel.
   */
  M asOpenApiTemplateModel();
  
  /**
   * Add the given schema to this model.
   * 
   * @param schema A schema to be added to this model.
   * 
   * @throws GenerationException If there is a duplicate name. 
   */
  void addSchema(S schema) throws GenerationException;

  /**
   * Return all schemas in this model.
   * 
   * @return all schemas in this model.
   */
  Collection<S> getSchemas();
  
  // TODO: make this typesafe
  @Override
  default Collection<T> getChildren()
  {
    return (Collection<T>) getSchemas();
  }

  ResolvedOpenApiObject getResolvedOpenApiObject();
}
