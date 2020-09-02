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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.symphony.oss.canon2.parser.IObjectSchemaTemplateModel;
import com.symphony.oss.canon2.parser.IResolvedSchema;
import com.symphony.oss.canon2.parser.ISchemaTemplateModel;

public class JavaObjectSchemaTemplateModel extends JavaSchemaTemplateModel
implements IObjectSchemaTemplateModel<
IJavaTemplateModel,
JavaOpenApiTemplateModel,
JavaSchemaTemplateModel,
JavaObjectSchemaTemplateModel,
JavaArraySchemaTemplateModel,
JavaPrimitiveSchemaTemplateModel>
{
  private Map<String, JavaSchemaTemplateModel> fields_ = new HashMap<>();
  
  JavaObjectSchemaTemplateModel(IResolvedSchema entity, String name, JavaOpenApiTemplateModel model,
      JavaGeneratorModelContext generatorModelContext,
       String... temaplates)
  {
    super(entity, name, model, generatorModelContext, temaplates);
    
    imports_.add("javax.annotation.concurrent.Immutable");
    imports_.add("javax.annotation.Nullable");
  }

  @Override
  public ISchemaTemplateModel<IJavaTemplateModel, JavaOpenApiTemplateModel, JavaSchemaTemplateModel, JavaObjectSchemaTemplateModel, JavaArraySchemaTemplateModel, JavaPrimitiveSchemaTemplateModel> asSchemaTemplateModel()
  {
    return this;
  }

  @Override
  public void addField(String name, JavaSchemaTemplateModel field)
  {
    fields_.put(name, field);
    
    imports_.addAll(field.imports_);
  }
  
  @Override
  public Map<String, JavaSchemaTemplateModel> getFields()
  {
    return fields_;
  }

  @Override
  public String toString()
  {
    return "JavaObjectSchemaTemplateModel [fields_=" + fields_ + ", imports_=" + imports_ + "]";
  }

  @Override
  public String getType()
  {
    return getName();
    
    //getCamelCapitalizedName();
  }
}
