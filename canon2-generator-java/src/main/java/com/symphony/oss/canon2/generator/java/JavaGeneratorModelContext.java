/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon2.generator.java;

import java.util.Map;

import com.symphony.oss.canon2.parser.GenerationContext;
import com.symphony.oss.canon2.parser.GeneratorModelContext;
import com.symphony.oss.canon2.parser.IModelContext;
import com.symphony.oss.canon2.parser.IModelEntity;
import com.symphony.oss.canon2.parser.IOpenApiObject;
import com.symphony.oss.canon2.parser.ISchema;
import com.symphony.oss.canon2.parser.ITemplateEntity;
import com.symphony.oss.canon2.parser.TemplateEntity;
import com.symphony.oss.commons.dom.json.IJsonObject;

class JavaGeneratorModelContext extends GeneratorModelContext
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
  public IModelEntity generate(IOpenApiObject entity, GenerationContext generationContext)
  {
    return new JavaModelEntity(entity, getSourceContext().getInputSourceName(), this, "Model");
  }

  @Override
  public ITemplateEntity generate(IModelEntity model, ISchema schema, String name, GenerationContext generationContext)
  {
    switch(schema.getType())
    {
      case "object":
        return new JavaObjectEntity(schema,  name, model, this, "Object");
    }
    
    return new TemplateEntity<ISchema>(schema,  name, model, this, "UNKNOWN");
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
