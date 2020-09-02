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

import com.symphony.oss.canon2.parser.GeneratorModelContext;
import com.symphony.oss.canon2.parser.IModelContext;
import com.symphony.oss.canon2.parser.IResolvedModel;
import com.symphony.oss.canon2.parser.IResolvedSchema;
import com.symphony.oss.canon2.parser.ISchema;
import com.symphony.oss.canon2.parser.model.CanonCardinality;
import com.symphony.oss.commons.dom.json.IJsonObject;

class JavaGeneratorModelContext extends GeneratorModelContext<IJavaTemplateModel, JavaOpenApiTemplateModel, JavaSchemaTemplateModel, JavaObjectSchemaTemplateModel,
  JavaArraySchemaTemplateModel, JavaPrimitiveSchemaTemplateModel>
{
  private IJsonObject<?> generatorConfig_;

  public JavaGeneratorModelContext(JavaGenerator javaGenerator, IModelContext context, IJsonObject<?> generatorConfig)
  {
    super(javaGenerator, context,
        new JavaPathNameConstructor(generatorConfig.getRequiredString(JavaGenerator.GEN_PACKAGE)),
        new JavaPathNameConstructor(generatorConfig.getRequiredString(JavaGenerator.FACADE_PACKAGE)));
    
    generatorConfig_ = generatorConfig;
  }

  @Override
  public void populateTemplateModel(Map<String, Object> map)
  {
    map.put(JavaGenerator.GEN_PACKAGE, generatorConfig_.getRequiredString(JavaGenerator.GEN_PACKAGE));
    map.put(JavaGenerator.FACADE_PACKAGE, generatorConfig_.getRequiredString(JavaGenerator.FACADE_PACKAGE));
  }

  @Override
  public JavaOpenApiTemplateModel generateOpenApiObject(IResolvedModel entity)
  {
    return new JavaOpenApiTemplateModel(entity, getSourceContext().getInputSourceName(), getGenerator(), "Model");
  }

  
  @Override
  public JavaObjectSchemaTemplateModel generateObjectSchema(JavaOpenApiTemplateModel model, IResolvedSchema entity, String name)
  {
    return new JavaObjectSchemaTemplateModel(entity,  name, model, this, "Object");
  }

  @Override
  public JavaArraySchemaTemplateModel generateArraySchema(JavaOpenApiTemplateModel model, IResolvedSchema entity,
      String name, CanonCardinality cardinality)
  {
    return new JavaArraySchemaTemplateModel(entity,  name, cardinality, model, this);
  }

  @Override
  public JavaPrimitiveSchemaTemplateModel generatePrimativeSchema(
      JavaOpenApiTemplateModel model, ISchema entity, String name)
  {
    return new JavaPrimitiveSchemaTemplateModel(entity,  name, model, this);
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
