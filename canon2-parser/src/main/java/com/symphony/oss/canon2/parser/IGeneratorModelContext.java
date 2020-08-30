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

public interface IGeneratorModelContext<S extends ISchemaTemplateModel<S>>
{
  IModelContext getSourceContext();

  IOpenApiTemplateModel<S> generateOpenApiObject(IResolvedModel entity);

  IObjectSchemaTemplateModel<S> generateObjectSchema(IOpenApiTemplateModel<S> model, IResolvedSchema entity, String name);
  IArraySchemaTemplateModel<S> generateArraySchema(IOpenApiTemplateModel<S> model, IResolvedSchema entity, String name, CanonCardinality cardinality);

  ICanonGenerator getGenerator();

  IPathNameConstructor getPathBuilder(TemplateType templateType);

  void populateTemplateModel(Map<String, Object> map);

  ISchemaTemplateModel<S> generatePrimativeSchema(IOpenApiTemplateModel<S> model, ISchema entity, String name);
}
