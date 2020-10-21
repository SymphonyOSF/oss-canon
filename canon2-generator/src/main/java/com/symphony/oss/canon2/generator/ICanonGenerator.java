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

import java.io.File;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon2.core.GenerationException;
import com.symphony.oss.canon2.core.INamedModelEntity;
import com.symphony.oss.canon2.core.ResolvedOpenApiObject;
import com.symphony.oss.canon2.core.ResolvedSchema;
import com.symphony.oss.canon2.core.SchemaTemplateModelType;
import com.symphony.oss.canon2.core.SourceContext;
import com.symphony.oss.canon2.model.CanonCardinality;
import com.symphony.oss.canon2.model.CanonGeneratorConfig;
import com.symphony.oss.canon2.model.OpenApiObject;

import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;

public interface ICanonGenerator<
T extends ITemplateModel<T,M,S>,
M extends IOpenApiTemplateModel<T,M,S>,
S extends ISchemaTemplateModel<T,M,S>,
O extends IObjectSchemaTemplateModel<T,M,S,F>,
A extends IArraySchemaTemplateModel<T,M,S>,
P extends IPrimitiveSchemaTemplateModel<T,M,S>,
F extends IFieldTemplateModel<T,M,S>,
G extends IGroupSchemaTemplateModel<T,M,S>>
{
  
  TemplateLoader getTemplateLoader();

  Configuration getFreemarkerConfig();
  
  ICanonGenerator<T,M,S,O,A,P,F,G> withTemplateDir(File templateDir);

  //IGeneratorModelContext<T,M,S,O,A,P,F,G> createModelContext(ICanonContext canonContext, IModelContext context, JsonObject generatorConfig);

  String getLanguage();

  Set<String> getTemplatesFor(TemplateType type, String templateGroup);

  String getIdentifierName(String name, INamedModelEntity entity);
  
  // formally GeneratorModelContext:
  
  M generateOpenApiObject(SourceContext modelContext, String name, ResolvedOpenApiObject resolvedOpenApiObject, String identifier) throws GenerationException;

  A generateArraySchema(M model, ResolvedSchema resolvedSchema, String identifier, boolean isReference, CanonCardinality cardinality) throws GenerationException;

  IPathNameConstructor<T> createPathBuilder(SourceContext sourceContext);

  void populateTemplateModel(SourceContext sourceContext, Map<String, Object> map);

  P generatePrimativeSchema(M model, ResolvedSchema resolvedSchema, String identifier, boolean isReference) throws GenerationException;
  
  F generateField(M model, String name, ResolvedSchema resolvedSchema, String identifier, S typeSchema, boolean required);

  O generateObjectSchema(M model, ResolvedSchema resolvedSchema, String identifier, boolean isReference) throws GenerationException;

  default @Nullable JsonObject getConfig(SourceContext sourceContext)
  {
    return getConfig(sourceContext.getModel());
  }

  default @Nullable JsonObject getConfig(OpenApiObject model)
  {
    CanonGeneratorConfig generators = model.getXCanonGenerators();
    
    if(generators != null)
    {
      return generators.getJsonObject().getObject(getLanguage());
    }
    
    return null;
  }
  
  G generateGroupSchema(M model, ResolvedSchema resolvedSchema, String identifier, boolean isReference,
      SchemaTemplateModelType oneOf);

  // these things are really generate operations, maybe the things above should be renamed...
//  M generateModel()
//      throws GenerationException;

//  moved to GeneratorContext
//  S generateSchema(ResolvedSchema resolvedSchema, M model, String name,
//      boolean isReference) throws GenerationException;
}
