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
import java.util.Map;

import com.symphony.oss.canon2.core.GenerationException;
import com.symphony.oss.canon2.core.ResolvedSchema;
import com.symphony.oss.canon2.generator.IObjectSchemaTemplateModel;

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
  
  private Map<String, JavaFieldTemplateModel> fieldMap_ = new HashMap<>();
  private Map<String, JavaSchemaTemplateModel> innerClassMap_ = new HashMap<>();
  private boolean hasLimits_;
  private JavaSchemaTemplateModel superType_;
  
  
  JavaObjectSchemaTemplateModel(String name, ResolvedSchema resolvedSchema, String identifier, String packageName, JavaOpenApiTemplateModel model,
      String... temaplates) throws GenerationException
  {
    super(name, resolvedSchema, identifier, packageName, model, temaplates);
  }
  
  @Override
  public void setExtends(JavaSchemaTemplateModel extendsSchema)
  {
    superType_ = extendsSchema;
  }

  @Override
  public void addField(String name, JavaFieldTemplateModel field)
  {
    fieldMap_.put(name, field);
    
    if(!getPackageName().equals(field.getTypeSchema().getPackageName()))
      addImport(field.getTypeSchema().getImport());
    
    if(field.getHasLimits())
      hasLimits_ = true;
  }

  @Override
  public void addInnerClass(String name, JavaSchemaTemplateModel innerClass)
  {
    innerClassMap_.put(name, innerClass);
  }

  public JavaSchemaTemplateModel getSuperType()
  {
    return superType_;
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

  public Collection<JavaSchemaTemplateModel> getInnerClasses()
  {
    return innerClassMap_.values();
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

//  @Override
//  public void addField(JavaFieldTemplateModel field) throws GenerationException
//  {
////    if(fieldMap_.put(field.getCamelName(), field) != null)
////    {
////      throw new GenerationException("Duplicate field name \"" + field.getCamelName() + "\" in " + getName());
////    }
//    fields_.add(field);
//    
//    imports_.addAll(field.imports_);
//    
//    if(field.getHasLimits())
//      hasLimits_ = true;
//  }
  
  @Override
  public Collection<JavaFieldTemplateModel> getFields()
  {
    return fieldMap_.values();
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
