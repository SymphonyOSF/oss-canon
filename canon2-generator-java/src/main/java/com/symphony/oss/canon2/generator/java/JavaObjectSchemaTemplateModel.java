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
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.symphony.oss.canon2.core.ResolvedPropertyContainerSchema;
import com.symphony.oss.canon2.core.SourceContext;
import com.symphony.oss.canon2.generator.IObjectSchemaTemplateModel;
import com.symphony.oss.canon2.generator.NameCollisionDetector;
import com.symphony.oss.canon2.generator.java.JavaGenerator.Context;

public class JavaObjectSchemaTemplateModel extends JavaSchemaTemplateModel
implements IObjectSchemaTemplateModel<
IJavaTemplateModel,
JavaOpenApiTemplateModel,
JavaSchemaTemplateModel,
JavaFieldTemplateModel
>
{
  private static final List<String> OBJECT_TEMPLATES             = ImmutableList.of("Object");
  
  private Map<String, JavaFieldTemplateModel> fieldMap_ = new HashMap<>();
  private Map<String, JavaSchemaTemplateModel> innerClassMap_ = new HashMap<>();
  private boolean hasLimits_;
  private JavaSchemaTemplateModel superType_;
  private final String baseSuperType_;
  private final String type_;
  private boolean additionalPropertiesAllowed_;
  private JavaSchemaTemplateModel additionalProperties_;
  private final String initialiserType_;
  private final String jsonNodeType_;
  private final boolean objectType_;
  
  
  JavaObjectSchemaTemplateModel(JavaGenerator.Context generatorContext, ResolvedPropertyContainerSchema<?> resolvedSchema,  String packageName, JavaOpenApiTemplateModel model)
  {
    super(generatorContext, initIdentifier(generatorContext, resolvedSchema), resolvedSchema, resolvedSchema.getSchemaType(), model, initTemplates(resolvedSchema));
    
    
    type_ = resolvedSchema.getResolvedContainer() == null ? getCamelCapitalizedName() :
      capitalize(toCamelCase(resolvedSchema.getResolvedContainer().getName())) + "." + getCamelCapitalizedName();
    
    
//  if(isExternal())
//  {
//    addImport(packageName + "." + getCamelCapitalizedName());
//  }
  
    setImport(packageName,  getCamelCapitalizedName());
    
    switch(resolvedSchema.getSchemaType())
    {
      case ONE_OF:
        initialiserType_ = "EntityInitialiser";
        baseSuperType_ = "Entity";
        jsonNodeType_ = "JsonDomNode";
        objectType_ = false;
        break;
        
      default:
        initialiserType_ = "ObjectEntityInitialiser";
        baseSuperType_ = "ObjectEntity";
        jsonNodeType_ = "JsonObject";
        objectType_ = true;
    }
  }

  private static List<String> initTemplates(ResolvedPropertyContainerSchema<?> resolvedSchema)
  {
    if(resolvedSchema.isInnerClass() || resolvedSchema.getResolvedOpenApiObject().isReferencedModel())
    {
      return EMPTY_TEMPLATES;
    }
    else
    {
      return OBJECT_TEMPLATES;
    }
  }

  private static String initIdentifier(Context generatorContext, ResolvedPropertyContainerSchema<?> resolvedSchema)
  {
    if(resolvedSchema.isInnerClass() || resolvedSchema.getResolvedOpenApiObject().isReferencedModel())
    {
      return resolvedSchema.getName(); // not generating for this so we don't care if the identifier is valid.
    }
    else
    {
      return getIdentifier(generatorContext, resolvedSchema);
    }
  }
  
  @Override
  public void validate(SourceContext sourceContext)
  {
    new NameCollisionDetector(getFields()).logCollisions(sourceContext, this);
    new NameCollisionDetector(getInnerClasses()).logCollisions(sourceContext, this);
  }

  public boolean getIsObjectType()
  {
    return objectType_;
  }

  public String getFullyQualifiedSuperTypeName()
  {
    if(superType_ == null)
      return "com.symphony.oss.canon2.runtime.java." + baseSuperType_;
    
    return superType_.getPackageName() + "." + superType_.getType();
  }
  
  public String getSuperTypeName()
  {
    if(superType_ == null)
      return baseSuperType_;
    
    return superType_.getType();
  }

  public String getInitialiserType()
  {
    return initialiserType_;
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
    
    if(field.getTypeSchema().getPackageName() != null && !getPackageName().equals(field.getTypeSchema().getPackageName()))
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
  public void setAdditionalProperties(JavaSchemaTemplateModel additionalProperties)
  {
    additionalProperties_ = additionalProperties;
    additionalPropertiesAllowed_ = true;
  }

  @Override
  public void setAdditionalPropertiesAllowed(boolean additionalPropertiesAllowed)
  {
    additionalPropertiesAllowed_ = additionalPropertiesAllowed;
  }

  @Override
  public String getJsonNodeType()
  {
    return jsonNodeType_;
  }

  @Override
  public String getFullyQualifiedJsonNodeType()
  {
    return "com.symphony.oss.canon.json.model.JsonObject";
  }

  @Override
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
//  public void addField(JavaFieldTemplateModel field)
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
    return type_;
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

  public boolean getAdditionalPropertiesAllowed()
  {
    return additionalPropertiesAllowed_;
  }

  public JavaSchemaTemplateModel getAdditionalProperties()
  {
    return additionalProperties_;
  }
}
