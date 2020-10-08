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

import com.symphony.oss.canon2.core.ResolvedOpenApiObject;
import com.symphony.oss.canon2.core.SourceContext;
import com.symphony.oss.canon2.generator.OpenApiTemplateModel;

public class JavaOpenApiTemplateModel extends OpenApiTemplateModel<
IJavaTemplateModel,
JavaOpenApiTemplateModel,
JavaSchemaTemplateModel
>
implements IJavaTemplateModel
{
  Set<String> imports_ = new TreeSet<>();
  
  public JavaOpenApiTemplateModel(SourceContext context, String name, ResolvedOpenApiObject resolvedOpenApiObject, String identifier,
      String... temaplates)
  {
    super(context, name, resolvedOpenApiObject, identifier, temaplates);
  }

  @Override
  public IJavaTemplateModel asTemplateModel()
  {
    return this;
  }

  @Override
  public JavaOpenApiTemplateModel asOpenApiTemplateModel()
  {
    return this;
  }

  @Override
  public Set<String> getImports()
  {
    return imports_;
  }

  @Override
  public String getType()
  {
    return getCamelCapitalizedName() + "Model";
  }

  @Override
  public boolean getIsGenerated()
  {
    return true;
  }
}
