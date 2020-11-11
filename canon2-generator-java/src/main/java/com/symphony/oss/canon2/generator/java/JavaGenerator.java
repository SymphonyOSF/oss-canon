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

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon2.core.INamedModelEntity;
import com.symphony.oss.canon2.core.ResolvedArraySchema;
import com.symphony.oss.canon2.core.ResolvedBigDecimalSchema;
import com.symphony.oss.canon2.core.ResolvedBigIntegerSchema;
import com.symphony.oss.canon2.core.ResolvedBooleanSchema;
import com.symphony.oss.canon2.core.ResolvedDoubleSchema;
import com.symphony.oss.canon2.core.ResolvedFloatSchema;
import com.symphony.oss.canon2.core.ResolvedIntegerSchema;
import com.symphony.oss.canon2.core.ResolvedLongSchema;
import com.symphony.oss.canon2.core.ResolvedObjectSchema;
import com.symphony.oss.canon2.core.ResolvedOpenApiObject;
import com.symphony.oss.canon2.core.ResolvedPrimitiveSchema;
import com.symphony.oss.canon2.core.ResolvedPropertyContainerSchema;
import com.symphony.oss.canon2.core.ResolvedSchema;
import com.symphony.oss.canon2.core.ResolvedStringSchema;
import com.symphony.oss.canon2.core.SourceContext;
import com.symphony.oss.canon2.generator.CanonGenerator;
import com.symphony.oss.canon2.generator.IGroupSchemaTemplateModel;
import com.symphony.oss.canon2.generator.IPathNameConstructor;
import com.symphony.oss.canon2.model.CanonCardinality;
import com.symphony.oss.canon2.model.OpenApiObject;


public class JavaGenerator extends CanonGenerator<
IJavaTemplateModel, JavaOpenApiTemplateModel,
JavaSchemaTemplateModel,
JavaObjectSchemaTemplateModel,
JavaArraySchemaTemplateModel,
JavaPrimitiveSchemaTemplateModel,
JavaFieldTemplateModel,
IGroupSchemaTemplateModel<IJavaTemplateModel,JavaOpenApiTemplateModel,JavaSchemaTemplateModel>
>
{
  private static final List<String> EMPTY_TEMPLATES              = ImmutableList.of();
  private static final List<String> TYPEDEF_TEMPLATES            = ImmutableList.of("TypeDef");
  private static final List<String> ENUM_TEMPLATES               = ImmutableList.of("Enum");
  private static final List<String> OBJECT_TEMPLATES             = ImmutableList.of("Object");
  private static final List<String> ARRAY_TEMPLATES              = ImmutableList.of("Array");
  private static final List<String> MODEL_TEMPLATES              = ImmutableList.of("Model");

  private static Logger             log_                         = LoggerFactory.getLogger(JavaGenerator.class);
  
  // IDs in the model
  private static final String       GEN_PACKAGE                  = "genPackage";
  static final String               LANGUAGE                     = "java";

  // IDs of generated attributes
  static final String               PREFIX                       = "java";
  static final String               PACKAGE_NAME                 = PREFIX + "PackageName";
  static final String               IMPORTS                      = PREFIX + "Imports";
  static final String               CLASS_NAME                   = PREFIX + "ClassName";
  static final String               GENERATED_BUILDER_CLASS_NAME = PREFIX + "GeneratedBuilderClassName";

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

//  private IdentifierAndImport getIdentifierAndImport(String identifier, ResolvedSchema resolvedEntity)
//  {
//    return new IdentifierAndImport(getJavaGenerationPackage(resolvedEntity.getResolvedOpenApiObject().getModel()), identifier, identifier, false);
//  }
  
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
    return new JavaOpenApiTemplateModel(context, name, resolvedOpenApiObject, identifier, MODEL_TEMPLATES);
  }

  
  @Override
  public JavaObjectSchemaTemplateModel generateObjectSchema(JavaOpenApiTemplateModel model, ResolvedPropertyContainerSchema<?> resolvedSchema, String identifier)
  {
    return new JavaObjectSchemaTemplateModel(resolvedSchema, 

        identifier, getJavaGenerationPackage(resolvedSchema.getResolvedOpenApiObject().getModel()),
        model, 
        resolvedSchema.isInnerClass() ? EMPTY_TEMPLATES : OBJECT_TEMPLATES);
  }

  @Override
  public JavaArraySchemaTemplateModel generateArraySchema(JavaOpenApiTemplateModel model, ResolvedArraySchema resolvedSchema, String identifier, CanonCardinality cardinality)
  {
    return new JavaArraySchemaTemplateModel(resolvedSchema,
        identifier, getJavaGenerationPackage(resolvedSchema.getResolvedOpenApiObject().getModel()),
        cardinality, model, 
        resolvedSchema.isInnerClass() || resolvedSchema.getResolvedOpenApiObject().isReferencedModel() ? EMPTY_TEMPLATES : ARRAY_TEMPLATES);
  }
  
  
  
//  private JavaPrimitiveSchemaTemplateModel generatePrimitiveSchemaTemplateModel(JavaOpenApiTemplateModel model,
//      ResolvedPrimitiveSchema resolvedSchema, String identifier, String javaTypePackage, String javaType)
//  {
//    boolean typedef;
//    
//    List<String> templates;
//    
//    if(resolvedSchema.isInnerClass())
//    {
//      typedef = resolvedSchema.hasLimits();
//    }
//    else
//    {
//      typedef = true;
//    }
//    
//    if(resolvedSchema.isInnerClass() || resolvedSchema.getResolvedOpenApiObject().isReferencedModel())
//    {
//      templates = EMPTY_TEMPLATES;
//    }
//    else if(!resolvedSchema.getSchema().isEnum())
//    {
//      templates = TYPEDEF_TEMPLATES;
//    }
//    else
//    {
//      templates = ENUM_TEMPLATES;
//    }
//    
//    if(typedef)
//    {
//      return null;
//    }
//    else
//    {
//      return new JavaPrimitiveSchemaTemplateModel();
//    }
//  }
  
  
  
  @Override
  public JavaPrimitiveSchemaTemplateModel generateBigDecimalSchema(JavaOpenApiTemplateModel model,
      ResolvedBigDecimalSchema resolvedSchema, String identifier)
  {
    return new JavaNumberSchemaTemplateModel(resolvedSchema,
        identifier, getJavaGenerationPackage(resolvedSchema.getResolvedOpenApiObject().getModel()), "java.math", "BigDecimal",  model,
         true, getJavaPrimitiveTemplates(resolvedSchema));
  }

  @Override
  public JavaPrimitiveSchemaTemplateModel generateBigIntegerSchema(JavaOpenApiTemplateModel model,
      ResolvedBigIntegerSchema resolvedSchema, String identifier)
  {
    return new JavaNumberSchemaTemplateModel(resolvedSchema,
        identifier, getJavaGenerationPackage(resolvedSchema.getResolvedOpenApiObject().getModel()), "java.math", "BigInteger",  model,
        true, getJavaPrimitiveTemplates(resolvedSchema));
  }

  @Override
  public JavaPrimitiveSchemaTemplateModel generateDoubleSchema(JavaOpenApiTemplateModel model,
      ResolvedDoubleSchema resolvedSchema, String identifier)
  {
    return new JavaNumberSchemaTemplateModel(resolvedSchema,
        identifier, getJavaGenerationPackage(resolvedSchema.getResolvedOpenApiObject().getModel()) ,null, "Double",  model,
        false, getJavaPrimitiveTemplates(resolvedSchema));
  }

  @Override
  public JavaPrimitiveSchemaTemplateModel generateFloatSchema(JavaOpenApiTemplateModel model,
      ResolvedFloatSchema resolvedSchema, String identifier)
  {
    return new JavaNumberSchemaTemplateModel(resolvedSchema,
        identifier, getJavaGenerationPackage(resolvedSchema.getResolvedOpenApiObject().getModel()), null, "Float", model,
        false, getJavaPrimitiveTemplates(resolvedSchema));
  }

  @Override
  public JavaPrimitiveSchemaTemplateModel generateIntegerSchema(JavaOpenApiTemplateModel model,
      ResolvedIntegerSchema resolvedSchema, String identifier)
  {
    return new JavaNumberSchemaTemplateModel(resolvedSchema,
        identifier, getJavaGenerationPackage(resolvedSchema.getResolvedOpenApiObject().getModel()), null, "Integer", model,
       false, getJavaPrimitiveTemplates(resolvedSchema));
  }

  @Override
  public JavaPrimitiveSchemaTemplateModel generateLongSchema(JavaOpenApiTemplateModel model,
      ResolvedLongSchema resolvedSchema, String identifier)
  {
    return new JavaNumberSchemaTemplateModel(resolvedSchema,
        identifier, getJavaGenerationPackage(resolvedSchema.getResolvedOpenApiObject().getModel()), null, "Long",  model,
        false, getJavaPrimitiveTemplates(resolvedSchema));
  }

  private List<String> getJavaPrimitiveTemplates(ResolvedPrimitiveSchema<?> resolvedSchema)
  {
    if(resolvedSchema.isInnerClass() || resolvedSchema.getResolvedOpenApiObject().isReferencedModel())
    {
      return EMPTY_TEMPLATES;
    }
    else
    {
      return TYPEDEF_TEMPLATES;
    }
  }

  private List<String> getJavaStringTemplates(ResolvedStringSchema resolvedSchema)
  {
    if(resolvedSchema.isInnerClass() || resolvedSchema.getResolvedOpenApiObject().isReferencedModel())
    {
      return EMPTY_TEMPLATES;
    }
    else if(!resolvedSchema.getSchema().isEnum())
    {
      return TYPEDEF_TEMPLATES;
    }
    else
    {
      return ENUM_TEMPLATES;
    }
  }

  @Override
  public JavaPrimitiveSchemaTemplateModel generateStringSchema(JavaOpenApiTemplateModel model,
      ResolvedStringSchema resolvedSchema, String identifier)
  {
    return new JavaStringSchemaTemplateModel(resolvedSchema,
        identifier, getJavaGenerationPackage(resolvedSchema.getResolvedOpenApiObject().getModel()),
        model, getJavaStringTemplates(resolvedSchema));
  }

  @Override
  public JavaPrimitiveSchemaTemplateModel generateBooleanSchema(JavaOpenApiTemplateModel model,
      ResolvedBooleanSchema resolvedSchema, String identifier)
  {
    return new JavaBooleanSchemaTemplateModel(resolvedSchema,
        identifier, getJavaGenerationPackage(resolvedSchema.getResolvedOpenApiObject().getModel()),
        model, getJavaPrimitiveTemplates(resolvedSchema));
  }

//  public JavaPrimitiveSchemaTemplateModel generatePrimativeSchema(
//      JavaOpenApiTemplateModel model, ResolvedPrimitiveSchema resolvedSchema, String identifier)
//  {
//    //if(isReference || (!resolvedSchema.isGenerated() && !resolvedSchema.getSchema().isEnum()))
//    if(resolvedSchema.isInnerClass() || resolvedSchema.getResolvedOpenApiObject().isReferencedModel())
//    {
//      return new JavaPrimitiveSchemaTemplateModel(resolvedSchema, identifier, getJavaGenerationPackage(resolvedSchema), model);
//    }
//    else if(!resolvedSchema.getSchema().isEnum())
//    {
//      return new JavaPrimitiveSchemaTemplateModel(resolvedSchema, identifier, getJavaGenerationPackage(resolvedSchema), model, "TypeDef");
//    }
//    else
//    {
//      return new JavaPrimitiveSchemaTemplateModel(resolvedSchema, identifier, getJavaGenerationPackage(resolvedSchema), model, "Enum");
//    }
//  }

  @Override
  public JavaFieldTemplateModel generateField(JavaOpenApiTemplateModel model, String name, ResolvedSchema resolvedSchema, String identifier,
      JavaSchemaTemplateModel typeSchema, boolean required)
  {
    return new JavaFieldTemplateModel(name, resolvedSchema, identifier, model, typeSchema, required, EMPTY_TEMPLATES);
  }
}
