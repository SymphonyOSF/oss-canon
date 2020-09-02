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

package com.symphony.oss.canon2.generator.java;

import java.util.Set;
import java.util.TreeSet;

import com.symphony.oss.canon2.parser.ISchema;
import com.symphony.oss.canon2.parser.ITemplateModel;
import com.symphony.oss.canon2.parser.SchemaTemplateModel;

public abstract class JavaSchemaTemplateModel
extends SchemaTemplateModel<
IJavaTemplateModel,
JavaOpenApiTemplateModel,
JavaSchemaTemplateModel,
JavaObjectSchemaTemplateModel,
JavaArraySchemaTemplateModel,
JavaPrimitiveSchemaTemplateModel,
ISchema>
implements IJavaTemplateModel
{
  Set<String> imports_ = new TreeSet<>();
  
  JavaSchemaTemplateModel(ISchema entity, String name, JavaOpenApiTemplateModel model,
      JavaGeneratorModelContext generatorModelContext,
      String... templates)
  {
    super(entity, name, model, generatorModelContext, templates);
    
    imports_.add("javax.annotation.concurrent.Immutable");
    imports_.add("javax.annotation.Nullable");
  }

  @Override
  public ITemplateModel<IJavaTemplateModel, JavaOpenApiTemplateModel, JavaSchemaTemplateModel, JavaObjectSchemaTemplateModel, JavaArraySchemaTemplateModel, JavaPrimitiveSchemaTemplateModel> asTemplateModel()
  {
    return this;
  }

  @Override
  public Set<String> getImports()
  {
    return imports_;
  }
}
