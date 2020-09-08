/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon2.generator.java;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.symphony.oss.canon2.parser.IPrimitiveSchemaTemplateModel;
import com.symphony.oss.canon2.parser.IResolvedSchema;
import com.symphony.oss.canon2.parser.ISchema;
import com.symphony.oss.canon2.parser.SchemaType;
import com.symphony.oss.commons.dom.json.IImmutableJsonDomNode;
import com.symphony.oss.commons.fault.CodingFault;
import com.symphony.oss.commons.type.provider.IBooleanProvider;

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
JavaSchemaTemplateModel>
{
  private static final Logger log_ = LoggerFactory.getLogger(JavaPrimitiveSchemaTemplateModel.class);
  
  private final String        type_;
  private final BigInteger    minimum_;
  private final BigInteger    maximum_;
  private final boolean       exclusiveMinimum_;
  private final boolean       exclusiveMaximum_;

  JavaPrimitiveSchemaTemplateModel(IResolvedSchema entity, String name, JavaOpenApiTemplateModel model,
       String... templates)
  { 
    super(name, model, templates);
    
    type_ = initType(entity);
    imports_.add("com.symphony.oss.commons.type.provider.I" + type_ + "Provider");
    minimum_ = getBigInteger(entity, "minimum");
    maximum_ = getBigInteger(entity, "maximum");
    exclusiveMinimum_ = getBoolean("exclusiveMinimum");
    exclusiveMaximum_ = getBoolean("exclusiveMaximum");
  }

  private boolean getBoolean(String string)
  {
    // TODO Auto-generated method stub
    return false;
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
           default:
              warnBadFormat(entity);
              return "Double";
          }
        }
        else
        {
          return "Double";
        }
        
      case "integer":
        if(entity.getFormat() != null)
        {
          switch(entity.getFormat())
          {
            case "int64":   return "Long";
            case "int32":   return "Integer";
            default:
              warnBadFormat(entity);
              return "Long";
          }
        }
        else
        {
          return "Long";
        }
        
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
              
            default:
              warnBadFormat(entity);
              return "String";
          }
        }
        else
        {
          return "String";
        }
        
      default:
        //return null;
        throw new CodingFault("Unknown primative type " + entity.getType());
    }
  }

  private BigInteger getBigInteger(ISchema entity, String name)
  {
    switch(entity.getType())
    {
      case "number":
      case "integer":
        IImmutableJsonDomNode node = entity.getJsonObject().get(name);
      
        if(node == null)
        {
          return null;
        }
        else
        {
          try
          {
            return new BigInteger(node.toString());
          }
          catch(NumberFormatException e)
          {
            log_.warn("Invalid " + name + " value \"" + node + "\" ignored at " + entity.getSourceLocation());
            return null;
          }
        }
    }
    
    return null;
  }
  
  private boolean getBoolean(ISchema entity, String name)
  {
    switch(entity.getType())
    {
      case "number":
      case "integer":
        IImmutableJsonDomNode node = entity.getJsonObject().get(name);
      
        if(node instanceof IBooleanProvider)
        {
          return ((IBooleanProvider)node).asBoolean();
        }
    }
    
    return false;
  }

  private void warnBadFormat(ISchema entity)
  {

    log_.warn("Unrecognized " + entity.getType() + " format \"" + entity.getFormat() + "\" ignored at " + entity.getSourceLocation());
  }

  @Override
  public SchemaType getSchemaType()
  {
    return SchemaType.PRIMITIVE;
  }

  @Override
  public String getType()
  {
    return type_;
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
  public BigInteger getMinimum()
  {
    return minimum_;
  }

  @Override
  public BigInteger getMaximum()
  {
    return maximum_;
  }

  @Override
  public boolean getExclusiveMinimum()
  {
    return exclusiveMinimum_;
  }

  @Override
  public boolean getExclusiveMaximum()
  {
    return exclusiveMaximum_;
  }
}
