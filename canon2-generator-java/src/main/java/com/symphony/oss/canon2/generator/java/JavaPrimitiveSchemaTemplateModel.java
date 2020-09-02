/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon2.generator.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.symphony.oss.canon2.parser.IPrimitiveSchemaTemplateModel;
import com.symphony.oss.canon2.parser.ISchema;
import com.symphony.oss.canon2.parser.ISchemaTemplateModel;
import com.symphony.oss.commons.fault.CodingFault;

/**
 * Java template model for primitive types number, integer, boolean, string.
 * 
 * @author Bruce Skingle
 *
 */
public class JavaPrimitiveSchemaTemplateModel extends JavaSchemaTemplateModel
implements IPrimitiveSchemaTemplateModel<
IJavaTemplateModel,
JavaOpenApiTemplateModel,
JavaSchemaTemplateModel,
JavaObjectSchemaTemplateModel,
JavaArraySchemaTemplateModel,
JavaPrimitiveSchemaTemplateModel>
{
  private static final Logger log_ = LoggerFactory.getLogger(JavaPrimitiveSchemaTemplateModel.class);
  
  private final String type_;

  JavaPrimitiveSchemaTemplateModel(ISchema entity, String name, JavaOpenApiTemplateModel model, JavaGeneratorModelContext generatorModelContext,
       String... templates)
  { 
    super(entity, name, model, generatorModelContext, templates);
    
    type_ = initType(entity);
    imports_.add("com.symphony.oss.commons.type.provider.I" + type_ + "Provider");
  }

  private String initType(ISchema entity)
  {

    switch(entity.getType())
    {
      case "number":
        if(entity.getFormat() != null)
        {
          switch(entity.getFormat())
          {
            case "int64":   return "Long";
            case "int32":   return "Integer";
            case "float":   return "Float";
            case "double":  return "Double";
            case "default":
              warnBadFormat(entity);
              return "Double";
          }
        }
        else
        {
          return "Double";
        }
        break;
        
      case "integer":
        if(entity.getFormat() != null)
        {
          switch(entity.getFormat())
          {
            case "int64":   return "Long";
            case "int32":   return "Integer";
            case "default":
              warnBadFormat(entity);
              return "Long";
          }
        }
        else
        {
          return "Long";
        }
        break;
        
      case "boolean":
        if(entity.getFormat() != null)
        {
          warnBadFormat(entity);
        }
        return "Boolean";
        
      case "string":
        if(entity.getFormat() != null)
        {
          switch(entity.getFormat())
          {
            case "byte":
              imports_.add("com.symphony.oss.commons.immutable.ImmutableByteArray");
              return "ImmutableByteArray";
  
              
            case "default":
              warnBadFormat(entity);
              return "String";
  
          }
        }
        else
        {
          return "String";
        }
        break;
        
      default:
        //return null;
        throw new CodingFault("Unknown primative type " + entity.getType());
    }
    throw new CodingFault("Unreachable code");
  }

  private void warnBadFormat(ISchema entity)
  {

    log_.warn("Unrecognized " + entity.getType() + " format \"" + entity.getFormat() + "\" ignored at " + entity.getSourceLocation());
  }

  @Override
  public ISchemaTemplateModel<IJavaTemplateModel, JavaOpenApiTemplateModel, JavaSchemaTemplateModel, JavaObjectSchemaTemplateModel, JavaArraySchemaTemplateModel, JavaPrimitiveSchemaTemplateModel> asSchemaTemplateModel()
  {
    return this;
  }

  @Override
  public String getType()
  {
    return type_;
  }
}
