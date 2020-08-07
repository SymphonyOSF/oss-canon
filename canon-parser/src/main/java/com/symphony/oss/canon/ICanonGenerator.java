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

package com.symphony.oss.canon;

import java.io.File;
import java.util.Map;
import java.util.Set;

import com.symphony.oss.canon.model.IPathNameConstructor;
import com.symphony.oss.canon.model.ModelElement;

import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;

public interface ICanonGenerator
{
  TemplateLoader getTemplateLoader();
  
  Set<String> getTemplatesFor(String type, String element);

  IPathNameConstructor getTemplatePathNameConstructor();

  IPathNameConstructor getProformaPathNameConstructor();

  Configuration getFreemarkerConfig();
  
  ICanonGenerator withTemplateDir(File templateDir);

  void populateDataModel(Map<String, Object> dataModel, ModelElement modelElement);
}
