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

import java.io.File;
import java.util.Set;

import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon2.model.IModelContext;
import com.symphony.oss.canon2.model.INamedModelEntity;

import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;

public interface ICanonGenerator<
T extends ITemplateModel<T,M,S>,
M extends IOpenApiTemplateModel<T,M,S>,
S extends ISchemaTemplateModel<T,M,S>,
O extends IObjectSchemaTemplateModel<T,M,S,F>,
A extends IArraySchemaTemplateModel<T,M,S>,
P extends IPrimitiveSchemaTemplateModel<T,M,S>,
F extends IFieldTemplateModel<T,M,S>>
{
  
  TemplateLoader getTemplateLoader();

  Configuration getFreemarkerConfig();
  
  ICanonGenerator<T,M,S,O,A,P,F> withTemplateDir(File templateDir);

  IGeneratorModelContext<T,M,S,O,A,P,F> createModelContext(IModelContext context, JsonObject generatorConfig);

  String getLanguage();

  Set<String> getTemplatesFor(TemplateType type, String templateGroup);

  String getIdentifierName(String name, INamedModelEntity entity);
}
