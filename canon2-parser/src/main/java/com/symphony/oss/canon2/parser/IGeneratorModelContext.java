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

import java.util.Map;

import com.symphony.oss.canon2.parser.model.CanonCardinality;

public interface IGeneratorModelContext<
T extends ITemplateModel<T,M,S,O,A,P>,
M extends IOpenApiTemplateModel<T,M,S,O,A,P>,
S extends ISchemaTemplateModel<T,M,S,O,A,P>,
O extends IObjectSchemaTemplateModel<T,M,S,O,A,P>,
A extends IArraySchemaTemplateModel<T,M,S,O,A,P>,
P extends IPrimitiveSchemaTemplateModel<T,M,S,O,A,P>>


//<M extends IOpenApiTemplateModel<S>, S extends ISchemaTemplateModel,
//  O extends IObjectSchemaTemplateModel<S>, A extends IArraySchemaTemplateModel<S>, P extends IPrimitiveSchemaTemplateModel<S>>
{
  IModelContext getSourceContext();

  M generateOpenApiObject(IResolvedModel entity);

  O generateObjectSchema(M model, IResolvedSchema entity, String name);
  A generateArraySchema(M model, IResolvedSchema entity, String name, CanonCardinality cardinality);

  ICanonGenerator<T,M,S,O,A,P> getGenerator();

  IPathNameConstructor<T> getPathBuilder(TemplateType templateType);

  void populateTemplateModel(Map<String, Object> map);

  P generatePrimativeSchema(M model, ISchema entity, String name);
}
