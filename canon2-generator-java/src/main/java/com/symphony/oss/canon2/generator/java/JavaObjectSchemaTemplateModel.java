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

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.symphony.oss.canon2.model.GenerationException;
import com.symphony.oss.canon2.model.ResolvedSchema;
import com.symphony.oss.canon2.parser.IObjectSchemaTemplateModel;

public class JavaObjectSchemaTemplateModel extends JavaSchemaTemplateModel
implements IObjectSchemaTemplateModel<
IJavaTemplateModel,
JavaOpenApiTemplateModel,
JavaSchemaTemplateModel,
JavaFieldTemplateModel
>
{
//  private static final String[] IMPORTS = new String[] 
//  {
//    "javax.annotation.concurrent.Immutable",
//    "javax.annotation.Nullable",
//    "com.google.common.collect.ImmutableSet",
//    "java.util.List",
//    "java.util.Set",
//    "java.util.HashSet",
//    "com.symphony.oss.canon2.runtime.java.ModelRegistry"
//  };
  
  private List<JavaFieldTemplateModel> fields_ = new LinkedList<>();
  private Map<String, JavaFieldTemplateModel> fieldMap_ = new HashMap<>();
  private List<JavaSchemaTemplateModel> innerClasses_ = new LinkedList<>();
  private boolean hasLimits_;
  
  JavaObjectSchemaTemplateModel(ResolvedSchema entity, String name, String identifier, JavaOpenApiTemplateModel model,
      JavaGeneratorModelContext modelContext, String... temaplates) throws GenerationException
  {
    super(entity, name, identifier, model, temaplates);
    
    for(Entry<String, ResolvedSchema> entry : entity.getInnerClasses().getResolvedProperties().entrySet())
    {
      System.err.println(entry);
      JavaSchemaTemplateModel innerClass = modelContext.generateSchema(entry.getValue(), model, entry.getKey(), modelContext, false);
      innerClasses_.add(innerClass);
//      innerClasses_.add(modelContext.generateObjectSchema(model, entry.getValue(), entry.getKey(), false));
    }
    
//    if(entity.getSuperSchema() == null)
    //imports_.add("com.symphony.oss.canon2.runtime.java.ObjectEntity");
  }

  @Override
  public String getJsonNodeType()
  {
    return "JsonObject";
  }

  @Override
  public String getFullyQualifiedJsonNodeType()
  {
    return "com.symphony.oss.canon.json.model.JsonObject";
  }

  public List<JavaSchemaTemplateModel> getInnerClasses()
  {
    return innerClasses_;
  }
  
  @Override
  public boolean getHasLimits()
  {
    return hasLimits_;
  }

  @Override
  public IJavaTemplateModel asTemplateModel()
  {
    return this;
  }

  @Override
  public JavaSchemaTemplateModel asSchemaTemplateModel()
  {
    return this;
  }

  @Override
  public void addField(JavaFieldTemplateModel field) throws GenerationException
  {
//    if(fieldMap_.put(field.getCamelName(), field) != null)
//    {
//      throw new GenerationException("Duplicate field name \"" + field.getCamelName() + "\" in " + getName());
//    }
    fields_.add(field);
    
    imports_.addAll(field.imports_);
    
    if(field.getHasLimits())
      hasLimits_ = true;
  }
  
  @Override
  public Collection<JavaFieldTemplateModel> getFields()
  {
    return fields_;
  }

  @Override
  public String toString()
  {
    return "JavaObjectSchemaTemplateModel [fields_=" + fields_ +  "]";
  }

  @Override
  public String getType()
  {
    return getCamelCapitalizedName();
  }

  @Override
  public String getCopyPrefix()
  {
    return "";
  }

  @Override
  public String getCopySuffix()
  {
    return "";
  }

  @Override
  public boolean hasName(String name)
  {
    return fieldMap_.containsKey(name);
  }
}
