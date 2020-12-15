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
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.checkerframework.checker.signature.qual.FullyQualifiedName;

import com.google.common.collect.ImmutableList;
import com.symphony.oss.canon2.core.ResolvedOpenApiObject;
import com.symphony.oss.canon2.core.SourceContext;
import com.symphony.oss.canon2.generator.INamespace;
import com.symphony.oss.canon2.generator.NameCollisionDetector;
import com.symphony.oss.canon2.generator.OpenApiTemplateModel;

/**
 * Implementation of OpenApiTemplateModel for the Java generator.
 * 
 * @author Bruce Skingle
 *
 */
class JavaOpenApiTemplateModel extends OpenApiTemplateModel<
IJavaTemplateModel,
JavaOpenApiTemplateModel,
JavaSchemaTemplateModel
>
implements IJavaTemplateModel
{
  private static final List<String> MODEL_TEMPLATES              = ImmutableList.of("Model");
  
  Set<String> imports_ = new TreeSet<>();

  private final String packageName_;
  private final String fullyQualifiedType_;

  private String type_;
  
  JavaOpenApiTemplateModel(JavaGenerator.Context generatorContext, ResolvedOpenApiObject resolvedOpenApiObject, String packageName)
  {
    super(generatorContext, generatorContext.getJavaIdentifier(resolvedOpenApiObject, false, false) + generatorContext.getCanonIdString() + "Model",
        resolvedOpenApiObject, MODEL_TEMPLATES);
    
    packageName_ = packageName;
    type_ = fullyQualifiedType_ = packageName_ + "." + getIdentifier();
  }
  
  @Override
  public String getPackageName()
  {
    return packageName_;
  }

  @Override
  public String getFullyQualifiedType()
  {
    return fullyQualifiedType_;
  }
  
  @Override
  public String getType()
  {
    return type_;
  }

  @Override
  public String getConstructor(String args)
  {
    return args;
  }

  @Override
  public String getValue(String args)
  {
    return args;
  }

  @Override
  public String getCopy(String args)
  {
    return args;
  }

  @Override
  public void resolve(INamespace namespace, Writer writer)
  {
    type_ = namespace.resolveImport(fullyQualifiedType_, writer);
    for(JavaSchemaTemplateModel child : getSchemas())
    {
      namespace.resolveImport(packageName_ + "." + child.getIdentifier(), writer);
    }
  }
  
  @Override
  public void validate(SourceContext sourceContext)
  {
    NameCollisionDetector ncd = new NameCollisionDetector(getSchemas());
    
    ncd.logCollisions(sourceContext, getResolvedOpenApiObject());
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
}
