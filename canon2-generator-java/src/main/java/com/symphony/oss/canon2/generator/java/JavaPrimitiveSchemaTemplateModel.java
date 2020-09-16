/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon2.generator.java;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.symphony.oss.canon2.parser.GenerationException;
import com.symphony.oss.canon2.parser.ICanonAttributes;
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
//  private static final String[] IMPORTS = new String[] 
//  {
//      "javax.annotation.Nonnull",
//      "java.util.Objects",
//      "com.symphony.oss.commons.type.provider.IValueProvider"
//  };

  private final String          javaType_;
  private final String                       type_;
  private final String                       primitiveType_;
  private final BigInteger                   minimum_;
  private final BigInteger                   maximum_;
  private final boolean                      exclusiveMinimum_;
  private final boolean                      exclusiveMaximum_;
  private final String                       jsonNodeType_;
  private final String                       constructPrefix_;
  private final String                       constructSuffix_;
  private final String                       getValue_;
  private final Set<String>                  quotedEnumValues_;
  private final Set<String>                  enumValues_;
  private final ImmutableMap<String, String> enumMap_;
  private final String                       externalPackage_;
  private final String                       externalType_;
  private final boolean isGenerated_;

  JavaPrimitiveSchemaTemplateModel(IResolvedSchema entity, String name, String identifier, JavaOpenApiTemplateModel model,
       String... templates) throws GenerationException
  { 
    super(entity, name, identifier, model, templates);
    
    javaType_ = initType(entity);
    isGenerated_ = entity.getIsGenerated() == null ? false : entity.getIsGenerated();

    String constructPrefix = null;
    String getValue = "";
    
    Set<String> quotedValues = new HashSet<>(entity.getEnum().size());
    Set<String> values = new HashSet<>(entity.getEnum().size());
    Map<String, String> valueMap = new HashMap<>();
    
    for(Object v : entity.getEnum())
    {
      String value = toSnakeCase(v.toString()).toUpperCase();
      String quotedValue;
      
      if("String".equals(javaType_))
      {
        quotedValue = "\"" + v + "\"";
      }
      else
      {
        quotedValue = v.toString();
      }
      
      quotedValues.add(quotedValue);
      values.add(value);
      valueMap.put(value, quotedValue);
    }
    
    enumValues_ = ImmutableSet.copyOf(values);
    quotedEnumValues_ = ImmutableSet.copyOf(quotedValues);
    enumMap_ = ImmutableMap.copyOf(valueMap);
    
//    imports_.add("com.symphony.oss.commons.type.provider.I" + javaType_ + "Provider");
    jsonNodeType_ = "I" + javaType_ + "Provider";
    
    if(enumValues_.isEmpty())
    {
      type_ = entity.getIsGenerated() ? 
          getCamelCapitalizedName() : 
            javaType_;
      primitiveType_ = entity.getIsGenerated() ? javaType_ : null;
      if(entity.getIsGenerated())
      {
        constructPrefix = "new " + getType() + "(";
        getValue = ".getValue()";
      }
      minimum_ = getBigInteger(entity, "minimum");
      maximum_ = getBigInteger(entity, "maximum");
      exclusiveMinimum_ = getBoolean(entity, "exclusiveMinimum");
      exclusiveMaximum_ = getBoolean(entity, "exclusiveMaximum");
      
    }
    else
    {
      type_ = entity.getIsGenerated() ? getCamelCapitalizedName() : null;
      primitiveType_ = javaType_;
      constructPrefix = type_ + ".valueOf(";
      getValue = ".getValue()";
      minimum_ = null;
      maximum_ = null;
      exclusiveMinimum_ = false;
      exclusiveMaximum_ = false;
    }
    
    ICanonAttributes attr = entity.getXCanonAttributes();
    
    if(attr != null)
    {
      externalPackage_ = attr.getJsonObject().getString("javaExternalPackage", "");
      externalType_ = attr.getJsonObject().getString("javaExternalType", null);
      constructPrefix = getCamelCapitalizedName() + "Builder.newInstance(";
    }
    else
    {
      externalPackage_ = "";
      externalType_ = null;
    }
    
//    if(!valueMap.isEmpty())
//      constructPrefix = "new " + getType() + "(";
    
//    for(String template : templates)
//    {
//      switch(template)
//      {
//        case "TypeDef":
////          imports_.add("com.symphony.oss.canon2.runtime.java.TypeDef");
//          constructPrefix = "new " + getType() + "(";
//          break;
//      }
//    }
    
    
    if(constructPrefix == null)
    {
      constructPrefix_ = constructSuffix_ = "";
    }
    else
    {
      constructPrefix_ = constructPrefix;
      constructSuffix_ = ")";
    }
    getValue_ = getValue;
  }

  public boolean getIsGenerated()
  {
    return isGenerated_;
  }

  public String getQuotedName()
  {
    return javaType_;
  }
  
  public String getJavaType()
  {
    return javaType_;
  }

  public Set<?> getEnumValues()
  {
    return enumValues_;
  }

  public Set<String> getQuotedEnumValues()
  {
    return quotedEnumValues_;
  }

  public Map<String, String> getEnumMap()
  {
    return enumMap_;
  }

  @Override
  public String getJsonNodeType()
  {
    return jsonNodeType_;
  }

  @Override
  public String getFullyQualifiedJsonNodeType()
  {
    return "com.symphony.oss.commons.type.provider." + jsonNodeType_;
  }

  @Override
  public boolean getHasLimits()
  {
    return minimum_!=null || maximum_ != null;
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

  public String getType()
  {
    return type_;
  }

  @Override
  public IJavaTemplateModel asTemplateModel()
  {
    return this;
  }

  public String getPrimitiveType()
  {
    return primitiveType_;
  }

  @Override
  public JavaSchemaTemplateModel asSchemaTemplateModel()
  {
    return this;
  }
  
  @Override
  public String getConstructPrefix()
  {
    return constructPrefix_;
  }

  @Override
  public String getConstructSuffix()
  {
    return constructSuffix_;
  }

  public String getGetValue()
  {
    return getValue_;
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

  public String getExternalPackage()
  {
    return externalPackage_;
  }

  public String getExternalType()
  {
    return externalType_;
  }
}
