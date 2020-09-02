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

import com.symphony.oss.commons.dom.json.IJsonObject;

import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;

public interface ICanonGenerator<
T extends ITemplateModel<T,M,S,O,A,P>,
M extends IOpenApiTemplateModel<T,M,S,O,A,P>,
S extends ISchemaTemplateModel<T,M,S,O,A,P>,
O extends IObjectSchemaTemplateModel<T,M,S,O,A,P>,
A extends IArraySchemaTemplateModel<T,M,S,O,A,P>,
P extends IPrimitiveSchemaTemplateModel<T,M,S,O,A,P>>
{
  
  TemplateLoader getTemplateLoader();

  Configuration getFreemarkerConfig();
  
  ICanonGenerator<T,M,S,O,A,P> withTemplateDir(File templateDir);

  IGeneratorModelContext<T,M,S,O,A,P> createModelContext(IModelContext context, IJsonObject<?> generatorConfig);

  String getLanguage();

  Set<String> getTemplatesFor(TemplateType type, String templateGroup);
}
