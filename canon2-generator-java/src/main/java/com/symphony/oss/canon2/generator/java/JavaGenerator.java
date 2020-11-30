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
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;
import com.symphony.oss.canon.json.ParserResultException;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon2.core.ICanonModelEntity;
import com.symphony.oss.canon2.core.ResolvedArraySchema;
import com.symphony.oss.canon2.core.ResolvedBigDecimalSchema;
import com.symphony.oss.canon2.core.ResolvedBigIntegerSchema;
import com.symphony.oss.canon2.core.ResolvedBooleanSchema;
import com.symphony.oss.canon2.core.ResolvedDoubleSchema;
import com.symphony.oss.canon2.core.ResolvedFloatSchema;
import com.symphony.oss.canon2.core.ResolvedIntegerSchema;
import com.symphony.oss.canon2.core.ResolvedLongSchema;
import com.symphony.oss.canon2.core.ResolvedOpenApiObject;
import com.symphony.oss.canon2.core.ResolvedPrimitiveSchema;
import com.symphony.oss.canon2.core.ResolvedProperty;
import com.symphony.oss.canon2.core.ResolvedPropertyContainerSchema;
import com.symphony.oss.canon2.core.ResolvedSchema;
import com.symphony.oss.canon2.core.ResolvedStringSchema;
import com.symphony.oss.canon2.core.SourceContext;
import com.symphony.oss.canon2.generator.Canon2;
import com.symphony.oss.canon2.generator.CanonGenerationContext;
import com.symphony.oss.canon2.generator.CanonGenerator;
import com.symphony.oss.canon2.generator.IGroupSchemaTemplateModel;
import com.symphony.oss.canon2.generator.IPathNameConstructor;
import com.symphony.oss.canon2.generator.NamespaceDirective;
import com.symphony.oss.canon2.generator.CanonGenerator.AbstractContext;
import com.symphony.oss.canon2.model.CanonCardinality;
import com.symphony.oss.canon2.model.OpenApiObject;


public class JavaGenerator extends CanonGenerator<
IJavaTemplateModel, JavaOpenApiTemplateModel,
JavaSchemaTemplateModel,
JavaObjectSchemaTemplateModel,
JavaArraySchemaTemplateModel,
JavaPrimitiveSchemaTemplateModel,
JavaFieldTemplateModel,
JavaGenerator.Context
>
{
  private static final List<String> EMPTY_TEMPLATES              = ImmutableList.of();
  private static final List<String> TYPEDEF_TEMPLATES            = ImmutableList.of("TypeDef");
  private static final List<String> ENUM_TEMPLATES               = ImmutableList.of("Enum");
  private static final List<String> OBJECT_TEMPLATES             = ImmutableList.of("Object");
  private static final List<String> ARRAY_TEMPLATES              = ImmutableList.of("Array");
//  private static final List<String> MODEL_TEMPLATES              = ImmutableList.of("Model");

  private static Logger             log_                         = LoggerFactory.getLogger(JavaGenerator.class);
  
  // IDs in the model
  private static final String       GEN_PACKAGE                  = "genPackage";

  // IDs of generated attributes
  static final String               PREFIX                       = "java";
  static final String               PACKAGE_NAME                 = PREFIX + "PackageName";
  static final String               IMPORTS                      = PREFIX + "Imports";
  static final String               CLASS_NAME                   = PREFIX + "ClassName";
  static final String               GENERATED_BUILDER_CLASS_NAME = PREFIX + "GeneratedBuilderClassName";

  private static final String[] STD_JAVA_PACKAGES = new String[]
  {
      "java.lang",
      "java.util"
  };
  
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
  
  class Context extends CanonGenerator<
  IJavaTemplateModel, JavaOpenApiTemplateModel,
  JavaSchemaTemplateModel,
  JavaObjectSchemaTemplateModel,
  JavaArraySchemaTemplateModel,
  JavaPrimitiveSchemaTemplateModel,
  JavaFieldTemplateModel,
  Context
  >.AbstractContext
  {

    Context(CanonGenerationContext generationContext,
        SourceContext sourceContext)
    {
      super(Context.class, generationContext, sourceContext);
    }
    
    String getJavaIdentifier(ResolvedSchema<?> resolvedSchema, boolean isClassName, boolean isTopLevelClassName)
    {
      return getIdentifierName(resolvedSchema, (identifier) -> isValidJavaIdentifier(identifier, isClassName, isTopLevelClassName));
    }
    
    String getJavaIdentifier(ResolvedOpenApiObject resolvedOpenApiObject, boolean isClassName, boolean isTopLevelClassName)
    {
      return getIdentifierName(resolvedOpenApiObject, (identifier) -> isValidJavaIdentifier(identifier, isClassName, isTopLevelClassName));
    }
    
    String getJavaIdentifier(ResolvedProperty resolvedProperty, boolean isClassName, boolean isTopLevelClassName)
    {
      return getIdentifier(resolvedProperty.getName(), resolvedProperty.getJsonDomNode(), (identifier) -> isValidJavaIdentifier(identifier, isClassName, isTopLevelClassName));
    }
    
    private boolean isValidJavaIdentifier(String identifier, boolean isClassName, boolean isTopLevelClassName)
    {
      if(identifier.length() == 0)
        return false;
      
      int i=0;
      
      if(isClassName)
      {
        if(!Character.isJavaIdentifierStart(identifier.charAt(i++)))
        {
          return false;
        }
      }
      
      while(i<identifier.length())
      {
        if(!Character.isJavaIdentifierPart(identifier.charAt(i++)))
        {
          return false;
        }
      }
      
      if(isTopLevelClassName)
      {
        for(String packageName : STD_JAVA_PACKAGES)
        {
          try
          {
            Class.forName(packageName + "." +  identifier);
            return false;
          }
          catch (ClassNotFoundException e)
          {
            // This is ok
          }
        }
      }
      
      return true;
    }
  }
  
  @Override
  public Context newGeneratorContext(CanonGenerationContext canonGenerationContext, SourceContext sourceContext) throws ParserResultException
  {
    return new Context(canonGenerationContext, sourceContext);
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
//    return new IdentifierAndImport(getJavaGenerationPackage(resolvedEntity.getResolvedOpenApiObject().getModel()), identifier, false);
//  }
  
  protected String getIdentifierName(ICanonModelEntity entity, String language)
  {
    if(entity.getJson() instanceof JsonObject)
    {
      JsonObject json = (JsonObject)entity.getJson();

      // "x-canon-${LANGUAGE}-identifier"
      String attrName = Canon2.X_CANON + language + Canon2.NAME_PART_SEPARATOR + Canon2.IDENTIFIER_SUFFIX;
      String result = json.getString(attrName, null);
      
      if(result == null)
      {
        // "x-canon-identifier"
        attrName = Canon2.X_CANON + Canon2.IDENTIFIER_SUFFIX;
        result = toIdentifier(json.getString(attrName, null));
      }
      
      return result;
    }
    else
    {
      return null;
    }
  }  

  @Override
  protected String toIdentifier(String name)
  {
    StringBuilder s = new StringBuilder();
    int i=1;
    
    s.append(Character.toUpperCase(name.charAt(0)));
    
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
  public IPathNameConstructor<IJavaTemplateModel> createPathBuilder(AbstractContext generatorContext)
  {
    return new JavaPathNameConstructor(getJavaGenerationPackage(generatorContext.getSourceContext()), generatorContext.getCanonIdString());
  }
  
  
  
  
//  @Override
//  protected boolean isValidIdentifier(String identifier, boolean isSchema)
//  {
//    if(identifier.length() == 0)
//      return false;
//    
//    if(!Character.isJavaIdentifierStart(identifier.charAt(0)))
//    {
//      return false;
//    }
//    
//    for(int i=1 ; i<identifier.length() ; i++)
//    {
//      if(!Character.isJavaIdentifierPart(identifier.charAt(i)))
//      {
//        return false;
//      }
//    }
//    
//    if(isSchema)
//    {
//      for(String packageName : STD_JAVA_PACKAGES)
//      {
//        try
//        {
//          Class.forName(packageName + "." +  identifier);
//          return false;
//        }
//        catch (ClassNotFoundException e)
//        {
//          // This is ok
//        }
//      }
//    }
//    
//    return true;
//  }

  @Override
  public void populateTemplateModel(JavaGenerator.Context generatorContext, Map<String, Object> map)
  {
    
    map.put(JavaGenerator.GEN_PACKAGE, getJavaGenerationPackage(generatorContext.getSourceContext()));
    map.put("namespace", new NamespaceDirective());
  }

  @Override
  public JavaOpenApiTemplateModel generateOpenApiObject(JavaGenerator.Context generatorContext, ResolvedOpenApiObject resolvedOpenApiObject)
  {
    return new JavaOpenApiTemplateModel(generatorContext, resolvedOpenApiObject,
        getJavaGenerationPackage(resolvedOpenApiObject.getModel()));
  }

  
  @Override
  public JavaObjectSchemaTemplateModel generateObjectSchema(JavaGenerator.Context generatorContext, JavaOpenApiTemplateModel model, ResolvedPropertyContainerSchema<?> resolvedSchema, IJavaTemplateModel outerClass)
  {
    return new JavaObjectSchemaTemplateModel(generatorContext, resolvedSchema, 

        getJavaGenerationPackage(resolvedSchema.getResolvedOpenApiObject().getModel()),
        model, outerClass);
  }

  @Override
  public JavaArraySchemaTemplateModel generateArraySchema(JavaGenerator.Context generatorContext, JavaOpenApiTemplateModel model, ResolvedArraySchema resolvedSchema, CanonCardinality cardinality)
  {
    return new JavaArraySchemaTemplateModel(generatorContext, resolvedSchema,
        getJavaGenerationPackage(resolvedSchema.getResolvedOpenApiObject().getModel()),
        cardinality, model);
  }
  
  
  
//  private JavaPrimitiveSchemaTemplateModel generatePrimitiveSchemaTemplateModel(JavaOpenApiTemplateModel model,
//      ResolvedPrimitiveSchema resolvedSchema, String javaTypePackage, String javaType)
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
  public JavaPrimitiveSchemaTemplateModel generateBigDecimalSchema(JavaGenerator.Context generatorContext, JavaOpenApiTemplateModel model,
      ResolvedBigDecimalSchema resolvedSchema)
  {
    return new JavaNumberSchemaTemplateModel(generatorContext, resolvedSchema,
         getJavaGenerationPackage(resolvedSchema.getResolvedOpenApiObject().getModel()), "java.math", "BigDecimal",  model,
         true);
  }

  @Override
  public JavaPrimitiveSchemaTemplateModel generateBigIntegerSchema(JavaGenerator.Context generatorContext, JavaOpenApiTemplateModel model,
      ResolvedBigIntegerSchema resolvedSchema)
  {
    return new JavaNumberSchemaTemplateModel(generatorContext, resolvedSchema,
         getJavaGenerationPackage(resolvedSchema.getResolvedOpenApiObject().getModel()), "java.math", "BigInteger",  model,
        true);
  }

  @Override
  public JavaPrimitiveSchemaTemplateModel generateDoubleSchema(JavaGenerator.Context generatorContext, JavaOpenApiTemplateModel model,
      ResolvedDoubleSchema resolvedSchema)
  {
    return new JavaNumberSchemaTemplateModel(generatorContext, resolvedSchema,
         getJavaGenerationPackage(resolvedSchema.getResolvedOpenApiObject().getModel()) ,"java.lang", "Double",  model,
        false);
  }

  @Override
  public JavaPrimitiveSchemaTemplateModel generateFloatSchema(JavaGenerator.Context generatorContext, JavaOpenApiTemplateModel model,
      ResolvedFloatSchema resolvedSchema)
  {
    return new JavaNumberSchemaTemplateModel(generatorContext, resolvedSchema,
        getJavaGenerationPackage(resolvedSchema.getResolvedOpenApiObject().getModel()), "java.lang", "Float", model,
        false);
  }

  @Override
  public JavaPrimitiveSchemaTemplateModel generateIntegerSchema(JavaGenerator.Context generatorContext, JavaOpenApiTemplateModel model,
      ResolvedIntegerSchema resolvedSchema)
  {
    return new JavaNumberSchemaTemplateModel(generatorContext, resolvedSchema,
         getJavaGenerationPackage(resolvedSchema.getResolvedOpenApiObject().getModel()), "java.lang", "Integer", model,
       false);
  }

  @Override
  public JavaPrimitiveSchemaTemplateModel generateLongSchema(JavaGenerator.Context generatorContext, JavaOpenApiTemplateModel model,
      ResolvedLongSchema resolvedSchema)
  {
    return new JavaNumberSchemaTemplateModel(generatorContext, resolvedSchema,
         getJavaGenerationPackage(resolvedSchema.getResolvedOpenApiObject().getModel()), "java.lang", "Long",  model,
        false);
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
  public JavaPrimitiveSchemaTemplateModel generateStringSchema(JavaGenerator.Context generatorContext, JavaOpenApiTemplateModel model,
      ResolvedStringSchema resolvedSchema)
  {
    return new JavaStringSchemaTemplateModel(generatorContext, resolvedSchema,
        getJavaGenerationPackage(resolvedSchema.getResolvedOpenApiObject().getModel()),
        model);
  }

  @Override
  public JavaPrimitiveSchemaTemplateModel generateBooleanSchema(JavaGenerator.Context generatorContext, JavaOpenApiTemplateModel model,
      ResolvedBooleanSchema resolvedSchema)
  {
    return new JavaBooleanSchemaTemplateModel(generatorContext, resolvedSchema,
         getJavaGenerationPackage(resolvedSchema.getResolvedOpenApiObject().getModel()),
        model);
  }

//  public JavaPrimitiveSchemaTemplateModel generatePrimativeSchema(
//      JavaOpenApiTemplateModel model, ResolvedPrimitiveSchema resolvedSchema)
//  {
//    //if(isReference || (!resolvedSchema.isGenerated() && !resolvedSchema.getSchema().isEnum()))
//    if(resolvedSchema.isInnerClass() || resolvedSchema.getResolvedOpenApiObject().isReferencedModel())
//    {
//      return new JavaPrimitiveSchemaTemplateModel(generatorContext, resolvedSchema, getJavaGenerationPackage(resolvedSchema), model);
//    }
//    else if(!resolvedSchema.getSchema().isEnum())
//    {
//      return new JavaPrimitiveSchemaTemplateModel(generatorContext, resolvedSchema, getJavaGenerationPackage(resolvedSchema), model, "TypeDef");
//    }
//    else
//    {
//      return new JavaPrimitiveSchemaTemplateModel(generatorContext, resolvedSchema, getJavaGenerationPackage(resolvedSchema), model, "Enum");
//    }
//  }

  @Override
  public JavaFieldTemplateModel generateField(JavaGenerator.Context generatorContext, JavaOpenApiTemplateModel model,
      ResolvedProperty resolvedProperty, JavaSchemaTemplateModel typeSchema, boolean required)
  {
    return new JavaFieldTemplateModel(generatorContext, resolvedProperty, model, typeSchema, required);
  }
}
