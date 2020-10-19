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

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon2.core.GenerationException;
import com.symphony.oss.canon2.core.INamedModelEntity;
import com.symphony.oss.canon2.core.IResolvedEntity;
import com.symphony.oss.canon2.core.ResolvedOpenApiObject;
import com.symphony.oss.canon2.core.ResolvedSchema;
import com.symphony.oss.canon2.core.SourceContext;
import com.symphony.oss.canon2.generator.CanonGenerator;
import com.symphony.oss.canon2.generator.IPathNameConstructor;
import com.symphony.oss.canon2.model.CanonCardinality;
import com.symphony.oss.canon2.model.OpenApiObject;


public class JavaGenerator extends CanonGenerator<
IJavaTemplateModel, JavaOpenApiTemplateModel,
JavaSchemaTemplateModel,
JavaObjectSchemaTemplateModel,
JavaArraySchemaTemplateModel,
JavaPrimitiveSchemaTemplateModel,
JavaFieldTemplateModel
>
{
  private static Logger log_ = LoggerFactory.getLogger(JavaGenerator.class);
  
  // IDs in the model
  private static final String          GEN_PACKAGE                  = "genPackage";
  static final String LANGUAGE                       = "java";

  // IDs of generated attributes
  static final String PREFIX                       = "java";
  static final String PACKAGE_NAME                 = PREFIX + "PackageName";
  static final String IMPORTS                      = PREFIX + "Imports";
  static final String CLASS_NAME                   = PREFIX + "ClassName";
  static final String GENERATED_BUILDER_CLASS_NAME = PREFIX + "GeneratedBuilderClassName";

  /**
   * Constructor.
   */
  public JavaGenerator()
  {
    super("java");
    
//    register(Array.class, (data, model) -> {
//        data.put("tsElement", model.getCamelCapitalizedName() + " " + model.getClass());
//      
//        
//        data.put("baseType", getBaseType(model.getElementSchema()));
//        
//        // First set modelJavaClassName, modelJavaFullyQualifiedClassName, and modelIsGenerated
//  
//        String externalType = model.getAttributes().get(JAVA_EXTERNAL_TYPE);
//  
//        if(externalType != null)
//        {
//          data.put("javaClassName", externalType);
//          data.put("javaFullyQualifiedClassName", model.getAttributes().get("javaExternalPackage") + "." + externalType);
//          data.put("isGenerated", Boolean.FALSE);
//        }
//        else
//        {
//          data.put("javaClassName", model.getCamelCapitalizedName());
//          data.put("javaFullyQualifiedClassName", data.get("javaFacadePackage") + "." + model.getCamelCapitalizedName());
//          data.put("isGenerated", Boolean.TRUE);
//        }
//        // Set javaElementClassName which is the basic Java type which stores simple values.
//        String elementClassName = getJavaClassName(model.getElementSchema());
//        data.put("javaElementClassName", elementClassName);
//        // now set javaFieldClassName and decorator attributes
//        
//        String javaCardinality = getJavaCardinality(model.getCardinality());
//        data.put("javaFieldClassName", javaCardinality + "<" + elementClassName + ">");
//        data.put("javaCardinality", javaCardinality);
//    });
  }

//  @Override
//  public JavaGeneratorModelContext createModelContext(ICanonContext canonContext, IModelContext context, JsonObject generatorConfig)
//  {
//    return new JavaGeneratorModelContext(this, canonContext, context, generatorConfig);
//  }

  public String getJavaGenerationPackage(SourceContext sourceContext)
  {
    return getJavaGenerationPackage(sourceContext.getModel());
  }
  


  public String getJavaGenerationPackage(OpenApiObject model)
  {
    JsonObject config = getConfig(model);
    
    if(config == null)
      return getDefaultGenerationPackage(model);
    
    return config.getString(JavaGenerator.GEN_PACKAGE, model.getXCanonId());
  }

  private String getDefaultGenerationPackage(OpenApiObject model)
  {
    String defaultGenerationPackage = model.getXCanonId();
    
    if(defaultGenerationPackage == null)
      return "canon.generated";
    
    return defaultGenerationPackage;
  }

  public String getJavaGenerationPackage(IResolvedEntity resolvedEntity)
  {
    return getJavaGenerationPackage(resolvedEntity.getResolvedOpenApiObject().getModel());
  }
  
  @Override
  public String getIdentifierName(String name, INamedModelEntity entity)
  {
    String identifier = entity.getXCanonIdentifier(LANGUAGE);
    
    if(identifier != null)
    {
      if(!isValidIdentifier(identifier))
        log_.error("Element " + name + " has the Java name \"" + identifier + "\" but that is not a valid Java identifier.");
      
      return identifier;
    }
    
    identifier = entity.getXCanonIdentifier();
    
    if(identifier != null)
    {
      return toIdentifier(identifier);
    }
    
    return toIdentifier(name);
  }
  
  @Override
  public IPathNameConstructor<IJavaTemplateModel> createPathBuilder(SourceContext sourceContext)
  {
    return new JavaPathNameConstructor(getJavaGenerationPackage(sourceContext));
  }

  private boolean isValidIdentifier(String identifier)
  {
    if(identifier.length() == 0)
      return false;
    
    if(!Character.isJavaIdentifierStart(identifier.charAt(0)))
    {
      return false;
    }
    
    for(int i=1 ; i<identifier.length() ; i++)
    {
      if(!Character.isJavaIdentifierPart(identifier.charAt(i)))
      {
        return false;
      }
    }
    
    return true;
  }

  private static String toIdentifier(String name)
  {
    StringBuilder s = new StringBuilder();
    int i=1;
    
    if(Character.isJavaIdentifierStart(name.charAt(0)))
    {
      s.append(Character.toUpperCase(name.charAt(0)));
    }
    else
    {
      s.append('_');
    }
    
    while(i<name.length())
    {
      char c = name.charAt(i++);
   
      if(c=='_' || c=='-' || !Character.isJavaIdentifierPart(c))
      {
        if(i<name.length())
        {
          s.append(Character.toUpperCase(name.charAt(i++)));
        }
        else
        {
          s.append('_');
        }
      }
      else
      {
        s.append(c);
      }
    }
    
    return s.toString();
  }

  @Override
  public void populateTemplateModel(SourceContext sourceContext, Map<String, Object> map)
  {
    
    map.put(JavaGenerator.GEN_PACKAGE, getJavaGenerationPackage(sourceContext));
  }

  @Override
  public JavaOpenApiTemplateModel generateOpenApiObject(SourceContext context, String name, ResolvedOpenApiObject resolvedOpenApiObject, String identifier)
  {
    return new JavaOpenApiTemplateModel(context, name, resolvedOpenApiObject, identifier, getJavaGenerationPackage(resolvedOpenApiObject), "Model");
  }

  
  @Override
  public JavaObjectSchemaTemplateModel generateObjectSchema(JavaOpenApiTemplateModel model, ResolvedSchema resolvedSchema, String identifier, boolean isReference) throws GenerationException
  {
    if(isReference)
      return new JavaObjectSchemaTemplateModel(resolvedSchema, identifier, getJavaGenerationPackage(resolvedSchema), model);
    else
      return new JavaObjectSchemaTemplateModel(resolvedSchema, identifier, getJavaGenerationPackage(resolvedSchema), model, "Object");
  }

  @Override
  public JavaArraySchemaTemplateModel generateArraySchema(JavaOpenApiTemplateModel model, ResolvedSchema resolvedSchema, String identifier, boolean isReference, CanonCardinality cardinality) throws GenerationException
  {
    if(isReference)
      return new JavaArraySchemaTemplateModel(resolvedSchema, identifier, getJavaGenerationPackage(resolvedSchema), cardinality, model);
    else
      return new JavaArraySchemaTemplateModel(resolvedSchema, identifier, getJavaGenerationPackage(resolvedSchema), cardinality, model,  "Array");
  }

  @Override
  public JavaPrimitiveSchemaTemplateModel generatePrimativeSchema(
      JavaOpenApiTemplateModel model, ResolvedSchema resolvedSchema, String identifier, boolean isReference) throws GenerationException
  {
    if(isReference || (!resolvedSchema.isGenerated() && !resolvedSchema.getSchema().isEnum()))
    {
      return new JavaPrimitiveSchemaTemplateModel(resolvedSchema, identifier, getJavaGenerationPackage(resolvedSchema), model);
    }
    else if(!resolvedSchema.getSchema().isEnum())
    {
      return new JavaPrimitiveSchemaTemplateModel(resolvedSchema, identifier, getJavaGenerationPackage(resolvedSchema), model, "TypeDef");
    }
    else
    {
      return new JavaPrimitiveSchemaTemplateModel(resolvedSchema, identifier, getJavaGenerationPackage(resolvedSchema), model, "Enum");
    }
  }

  @Override
  public JavaFieldTemplateModel generateField(JavaOpenApiTemplateModel model, String name, ResolvedSchema resolvedSchema, String identifier,
      JavaSchemaTemplateModel typeSchema, boolean required)
  {
    return new JavaFieldTemplateModel(name, resolvedSchema, identifier, model, typeSchema, required);
  }
}
