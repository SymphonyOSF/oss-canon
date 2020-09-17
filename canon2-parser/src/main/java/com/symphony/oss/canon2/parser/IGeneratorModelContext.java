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

import com.symphony.oss.canon2.model.CanonCardinality;
import com.symphony.oss.canon2.model.GenerationException;
import com.symphony.oss.canon2.model.IModelContext;
import com.symphony.oss.canon2.model.ResolvedModel;
import com.symphony.oss.canon2.model.ResolvedSchema;

public interface IGeneratorModelContext<
T extends ITemplateModel<T,M,S>,
M extends IOpenApiTemplateModel<T,M,S>,
S extends ISchemaTemplateModel<T,M,S>,
O extends IObjectSchemaTemplateModel<T,M,S,F>,
A extends IArraySchemaTemplateModel<T,M,S>,
P extends IPrimitiveSchemaTemplateModel<T,M,S>,
F extends IFieldTemplateModel<T,M,S>
>


//<M extends IOpenApiTemplateModel<S>, S extends ISchemaTemplateModel,
//  O extends IObjectSchemaTemplateModel<S>, A extends IArraySchemaTemplateModel<S>, P extends IPrimitiveSchemaTemplateModel<S>>
{
  IModelContext getSourceContext();

  M generateOpenApiObject(ResolvedModel entity, String name, String identifier) throws GenerationException;

  O generateObjectSchema(M model, ResolvedSchema entity, String name, String identifier, boolean isReference) throws GenerationException;
  A generateArraySchema(M model, ResolvedSchema entity, String name, String identifier, boolean isReference, CanonCardinality cardinality) throws GenerationException;

  ICanonGenerator<T,M,S,O,A,P,F> getGenerator();

  IPathNameConstructor<T> getPathBuilder(TemplateType templateType);

  void populateTemplateModel(Map<String, Object> map);

  P generatePrimativeSchema(M model, ResolvedSchema entity, String name, String identifier, boolean isReference) throws GenerationException;
  
  F generateField(M model, ResolvedSchema entity, String name, String identifier, S typeSchema, boolean required);

  // these things are really generate operations, maybe the things above should be renamed...
  M generateModel(ResolvedModel resolvedModel, IGeneratorModelContext<T, M, S, O, A, P, F> modelContext)
      throws GenerationException;

  S generateSchema(ResolvedSchema resolvedSchema, M model, String name,
      IGeneratorModelContext<T, M, S, O, A, P, F> modelContext, boolean isReference) throws GenerationException;
}
