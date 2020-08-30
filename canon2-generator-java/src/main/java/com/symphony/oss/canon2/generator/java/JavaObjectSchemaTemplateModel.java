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

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.symphony.oss.canon2.parser.IGeneratorModelContext;
import com.symphony.oss.canon2.parser.IObjectSchemaTemplateModel;
import com.symphony.oss.canon2.parser.IOpenApiTemplateModel;
import com.symphony.oss.canon2.parser.ISchema;
import com.symphony.oss.canon2.parser.ISchemaTemplateModel;

public class JavaObjectSchemaTemplateModel extends JavaSchemaTemplateModel implements IObjectSchemaTemplateModel<JavaSchemaTemplateModel>
{
  private List<ISchemaTemplateModel<JavaSchemaTemplateModel>> fields_ = new LinkedList<>();
  
  JavaObjectSchemaTemplateModel(ISchema entity, String name, IOpenApiTemplateModel<JavaSchemaTemplateModel> model,
      IGeneratorModelContext<JavaSchemaTemplateModel> generatorModelContext,
       String... temaplates)
  {
    super(entity, name, model, generatorModelContext, temaplates);
    
    imports_.add("javax.annotation.concurrent.Immutable");
    imports_.add("javax.annotation.Nullable");
  }

  @Override
  public void addField(ISchemaTemplateModel<JavaSchemaTemplateModel> field)
  {
    fields_.add(field);
  }
  
  @Override
  public List<ISchemaTemplateModel<JavaSchemaTemplateModel>> getFields()
  {
    return fields_;
  }

  @Override
  public Set<String> getImports()
  {
    // TODO Auto-generated method stub
    return super.getImports();
  }

  @Override
  public String toString()
  {
    return "JavaObjectSchemaTemplateModel [fields_=" + fields_ + ", imports_=" + imports_ + "]";
  }

  @Override
  public String getType()
  {
    return getCamelCapitalizedName();
  }
}
