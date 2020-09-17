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

import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon2.model.CanonCardinality;
import com.symphony.oss.canon2.model.GenerationException;
import com.symphony.oss.canon2.model.IModelContext;
import com.symphony.oss.canon2.model.ResolvedModel;
import com.symphony.oss.canon2.model.ResolvedSchema;
import com.symphony.oss.canon2.parser.GeneratorModelContext;

class JavaGeneratorModelContext extends GeneratorModelContext<IJavaTemplateModel, JavaOpenApiTemplateModel, JavaSchemaTemplateModel, JavaObjectSchemaTemplateModel,
  JavaArraySchemaTemplateModel, JavaPrimitiveSchemaTemplateModel, JavaFieldTemplateModel>
{
  private JsonObject generatorConfig_;

  public JavaGeneratorModelContext(JavaGenerator javaGenerator, IModelContext context, JsonObject generatorConfig)
  {
    super(javaGenerator, context,
        new JavaPathNameConstructor(generatorConfig.getRequiredString(JavaGenerator.GEN_PACKAGE)));
    
    generatorConfig_ = generatorConfig;
  }

  @Override
  public void populateTemplateModel(Map<String, Object> map)
  {
    map.put(JavaGenerator.GEN_PACKAGE, generatorConfig_.getRequiredString(JavaGenerator.GEN_PACKAGE));
  }

  @Override
  public JavaOpenApiTemplateModel generateOpenApiObject(ResolvedModel resolvedModel, String name, String identifier)
  {
    return new JavaOpenApiTemplateModel(resolvedModel, name, identifier, "Model");
  }

  
  @Override
  public JavaObjectSchemaTemplateModel generateObjectSchema(JavaOpenApiTemplateModel model, ResolvedSchema entity, String name, String identifier, boolean isReference) throws GenerationException
  {
    if(isReference)
      return new JavaObjectSchemaTemplateModel(entity, name, identifier, model, this);
    else
      return new JavaObjectSchemaTemplateModel(entity, name, identifier, model, this, "Object");
  }

  @Override
  public JavaArraySchemaTemplateModel generateArraySchema(JavaOpenApiTemplateModel model, ResolvedSchema entity,
      String name, String identifier, boolean isReference, CanonCardinality cardinality) throws GenerationException
  {
    if(isReference)
      return new JavaArraySchemaTemplateModel(entity,  name, identifier, cardinality, model, this);
    else
      return new JavaArraySchemaTemplateModel(entity,  name, identifier, cardinality, model, this,  "Array");
  }

  @Override
  public JavaPrimitiveSchemaTemplateModel generatePrimativeSchema(
      JavaOpenApiTemplateModel model, ResolvedSchema entity, String name, String identifier, boolean isReference) throws GenerationException
  {
    if(isReference || (!entity.getIsGenerated() && entity.getEnum().isEmpty()))
    {
      return new JavaPrimitiveSchemaTemplateModel(entity,  name, identifier, model);
    }
    else if(entity.getEnum().isEmpty())
    {
      return new JavaPrimitiveSchemaTemplateModel(entity,  name, identifier, model, "TypeDef");
    }
    else
    {
      return new JavaPrimitiveSchemaTemplateModel(entity,  name, identifier, model, "Enum");
    }
  }

  @Override
  public JavaFieldTemplateModel generateField(JavaOpenApiTemplateModel model, ResolvedSchema entity, String name, String identifier,
      JavaSchemaTemplateModel typeSchema, boolean required)
  {
    return new JavaFieldTemplateModel(entity, name, identifier, model, typeSchema, required);
  }

//  private String getJavaCardinality(String cardinality)
//  {
//    switch(cardinality)
//    {
//      case "SET":
//        return "Set";
//      
//      default:
//        return "List";
//    }
//  }
//
//  private String getTypeDefJavaClassName(IGenerationEntity model)
//  {
//    String externalType = model.getAttributes().get(JAVA_EXTERNAL_TYPE);
//    
//    if(externalType != null)
//    {
//      return externalType;
//    }
//    else
//    {
//      return model.getCamelCapitalizedName();
//    }
//  }
//  
//  private String getJavaClassName(IGenerationEntity model)
//  {
//    if(model.getIsTypeDef())
//    {
//      return getTypeDefJavaClassName(model);
//    }
//    if(model.getIsComponent())
//    {
//      return model.getCamelCapitalizedName();
//    }
//    
//    return getBaseType(model);
//  }
//
//  private String getBaseType(IGenerationEntity model)
//  {
//    switch(model.getElementType())
//    {
//      case "Integer":
//        switch(model.getFormat())
//        {
//          case "int32":
//            return "Integer";
//            
//           default:
//            return "Long";
//        }
//        
//      case "Double":
//        switch(model.getFormat())
//        {
//          case "float":
//            return "Float";
//          
//          default:
//            return "Double";
//        }
//      
//      case "String":
//        switch(model.getFormat())
//        {
//          case "byte":
//            return "ImmutableByteArray";
//          
//          default:
//            return "String";
//        }
//      
//      case "Boolean":
//        return "Boolean";
//      
//      default:
//        return "I" + model.getCamelCapitalizedName();
//    }
//  }
}
