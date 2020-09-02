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

import java.util.Collection;

public interface IOpenApiTemplateModel<
  T extends ITemplateModel<T,M,S,O,A,P>,
  M extends IOpenApiTemplateModel<T,M,S,O,A,P>,
  S extends ISchemaTemplateModel<T,M,S,O,A,P>,
  O extends IObjectSchemaTemplateModel<T,M,S,O,A,P>,
  A extends IArraySchemaTemplateModel<T,M,S,O,A,P>,
  P extends IPrimitiveSchemaTemplateModel<T,M,S,O,A,P>>
    extends ITemplateModel<T,M,S,O,A,P>
{

  ICanonGenerator<T,M,S,O,A,P> getGenerator();
  
  /**
   * Return this object as an ITemplateModel.
   * 
   * Implementations of this class must also be a subclass of the implementation of ITemplateModel
   * but the Java type system is unable to represent that in a parameterised type declaration.
   * 
   * @return this object as an ITemplateModel.
   */
  ITemplateModel<T,M,S,O,A,P> asTemplateModel();
  
  void addSchema(S schema);

  Collection<S> getSchemas();
  
  @SuppressWarnings("unchecked")
  @Override
  default Collection<T> getChildren()
  {
    return (Collection<T>) getSchemas();
  }
}
