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

import java.io.Writer;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.symphony.oss.canon2.core.ResolvedPropertyContainerSchema;
import com.symphony.oss.canon2.core.SourceContext;
import com.symphony.oss.canon2.generator.INamespace;
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
  private static final List<String>            OBJECT_TEMPLATES = ImmutableList.of("Object");

  private Map<String, JavaFieldTemplateModel>  fieldMap_        = new HashMap<>();
  private Map<String, JavaSchemaTemplateModel> innerClassMap_   = new HashMap<>();
  private boolean                              hasLimits_;
  private JavaSchemaTemplateModel              superType_;
  
  private final String                         fullyQualifiedType_;
  private final String                         fullyQualifiedInitialiserType_;
  private final String                         fullyQualifiedJsonInitialiserType_;
  private final String                         fullyQualifiedJsonNodeType_;
  private final String                         fullyQualifiedBaseSuperType_;
  
  private String                               type_;
  
  
  private boolean                        objectType_;
  
  private boolean                              additionalPropertiesAllowed_;
  private JavaSchemaTemplateModel              additionalProperties_;

  private boolean                              additionalPropertiesIsInnerClass_;
  
  
  JavaObjectSchemaTemplateModel(JavaGenerator.Context generatorContext, ResolvedPropertyContainerSchema<?> resolvedSchema,
      String packageName, JavaOpenApiTemplateModel model, IJavaTemplateModel outerClass)
  {
    super(generatorContext, initIdentifier(generatorContext, resolvedSchema), resolvedSchema, packageName, resolvedSchema.getSchemaType(), model, initTemplates(resolvedSchema));
    
    if(outerClass == null)
    {
      fullyQualifiedType_ = getPackageName() + "." + getIdentifier();
    }
    else
    {
      fullyQualifiedType_ = outerClass.getFullyQualifiedType() + "." + getIdentifier();
    }
        
//        resolvedSchema.getResolvedContainer() == null ? getCamelCapitalizedName() :
//      capitalize(generatorContext.getCanonIdString(), toCamelCase(resolvedSchema.getResolvedContainer().getName())) + "." + getCamelCapitalizedName();
    
    
//  if(isExternal())
//  {
//    addImport(packageName + "." + getCamelCapitalizedName());
//  }
  
    setImport(packageName,  getCamelCapitalizedName());
    
    switch(resolvedSchema.getSchemaType())
    {
      case ONE_OF:
        fullyQualifiedJsonInitialiserType_ = "com.symphony.oss.canon2.runtime.java.JsonEntityInitialiser";
        fullyQualifiedInitialiserType_ = "com.symphony.oss.canon2.runtime.java.IEntityInitialiser";
        fullyQualifiedBaseSuperType_ = "com.symphony.oss.canon2.runtime.java.Entity";
        fullyQualifiedJsonNodeType_ = "com.symphony.oss.canon.json.model.JsonDomNode";
        objectType_ = false;
        break;
        
      default:
        fullyQualifiedJsonInitialiserType_ = "com.symphony.oss.canon2.runtime.java.JsonObjectEntityInitialiser";
        fullyQualifiedInitialiserType_ = "com.symphony.oss.canon2.runtime.java.IObjectEntityInitialiser";
        fullyQualifiedBaseSuperType_ = "com.symphony.oss.canon2.runtime.java.ObjectEntity";
        fullyQualifiedJsonNodeType_ = "com.symphony.oss.canon.json.model.JsonObject";
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
  public void resolve(INamespace namespace, Writer writer)
  {
    type_             = namespace.resolveImport(fullyQualifiedType_, writer);
//    initialiserType_  = namespace.resolveImport(fullyQualifiedInitialiserType_, writer);
//    jsonNodeType_     = namespace.resolveImport(fullyQualifiedJsonNodeType_, writer);
//    baseSuperType_    = namespace.resolveImport(fullyQualifiedBaseSuperType_, writer);
    
    for(JavaFieldTemplateModel child : getFields())
      child.resolve(namespace, writer);
    
    for(JavaSchemaTemplateModel child : getInnerClasses())
      child.resolve(namespace, writer);
  }

  @Override
  public void validate(SourceContext sourceContext)
  {
    new NameCollisionDetector(getFields()).logCollisions(sourceContext, this);
    new NameCollisionDetector(getInnerClasses()).logCollisions(sourceContext, this);
  }

  @Override
  public String getFullyQualifiedType()
  {
    return fullyQualifiedType_;
  }

  @Override
  public boolean getIsObjectType()
  {
    return objectType_;
  }

  public String getFullyQualifiedInitialiserType()
  {
    return fullyQualifiedInitialiserType_;
  }

  public String getFullyQualifiedJsonInitialiserType()
  {
    return fullyQualifiedJsonInitialiserType_;
  }

  public String getFullyQualifiedSuperType()
  {
    if(superType_ == null)
      return fullyQualifiedBaseSuperType_;
    
    return superType_.getFullyQualifiedType();
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
    
    if(field.getTypeSchema().getLEGACYPackageName() != null && !getLEGACYPackageName().equals(field.getTypeSchema().getLEGACYPackageName()))
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
  public void setAdditionalProperties(JavaSchemaTemplateModel additionalProperties, boolean isInnerClass)
  {
    additionalProperties_ = additionalProperties;
    additionalPropertiesIsInnerClass_ = isInnerClass;
    additionalPropertiesAllowed_ = true;
  }

  @Override
  public void setAdditionalPropertiesAllowed(boolean additionalPropertiesAllowed)
  {
    additionalPropertiesAllowed_ = additionalPropertiesAllowed;
  }

  public String getFullyQualifiedJsonNodeType()
  {
    return fullyQualifiedJsonNodeType_;
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

  public boolean getAdditionalPropertiesIsInnerClass()
  {
    return additionalPropertiesIsInnerClass_;
  }

  public JavaSchemaTemplateModel getAdditionalProperties()
  {
    return additionalProperties_;
  }
}
