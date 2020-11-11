/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon2.generator.java;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.symphony.oss.canon2.core.ResolvedStringSchema;
import com.symphony.oss.canon2.generator.IPrimitiveSchemaTemplateModel;
import com.symphony.oss.canon2.model.StringSchema;

/**
 * Java template model for number and integer.
 * 
 * @author Bruce Skingle
 *
 */
public class JavaStringSchemaTemplateModel extends JavaPrimitiveSchemaTemplateModel
implements IPrimitiveSchemaTemplateModel<
IJavaTemplateModel,
JavaOpenApiTemplateModel,
JavaSchemaTemplateModel>
{  
  private static final Logger log_ = LoggerFactory.getLogger(JavaStringSchemaTemplateModel.class);
  
  private final Set<String>                  quotedEnumValues_;
  private final Set<String>                  enumValues_;
  private final ImmutableMap<String, String> enumMap_;
  private final String                       constructPrefix_;
  private final String                       getValueSuffix_;
  
  JavaStringSchemaTemplateModel(ResolvedStringSchema resolvedSchema, String identifier, String packageName, JavaOpenApiTemplateModel model,
       List<String> templates)
  { 
    super(resolvedSchema, !resolvedSchema.getEnum().isEmpty(), identifier, packageName, initType(resolvedSchema), model, templates);
    
    StringSchema entity = resolvedSchema.getSchema();
    if(entity.getFormat() != null)
    {
      switch(entity.getFormat())
      {
        case "byte":
          setAndAddImport("com.symphony.oss.commons.immutable", "ImmutableByteArray");
          break;
          
        default:
          log_.warn("Unrecognized " + entity.getType() + " format \"" + entity.getFormat() + "\" ignored at " + entity.getJson().getContext().getSourceLocation());
      }
    }
    
    Set<String> enumList = resolvedSchema.getSchema().getEnum();
    
    if(enumList==null || enumList.isEmpty())
    {
      enumValues_ = ImmutableSet.of();
      quotedEnumValues_ = ImmutableSet.of();
      enumMap_ = ImmutableMap.of();
      constructPrefix_ = super.getConstructPrefix();
      getValueSuffix_ = super.getGetValueSuffix();
    }
    else
    {
      int debig=1;
      Set<String> quotedValues = new HashSet<>(enumList.size());
      Set<String> values = new HashSet<>(enumList.size());
      Map<String, String> valueMap = new HashMap<>();
      
      for(Object v : enumList)
      {
        String value = toSnakeCase(v.toString()).toUpperCase();
        String quotedValue;
        
        if("String".equals(getJavaType()))
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
      constructPrefix_ = getType() + ".deserialize(";
      getValueSuffix_ = ".getValue()";
    }
  }

  private static String initType(ResolvedStringSchema resolvedSchema)
  {
    StringSchema entity = resolvedSchema.getSchema();
    if(entity.getFormat() != null)
    {
      switch(entity.getFormat())
      {
        case "byte":
          return "ImmutableByteArray";
      }
    }
      
    return "String";
    
  }

  @Override
  public String getConstructPrefix()
  {
    return constructPrefix_;
  }

  @Override
  public String getGetValueSuffix()
  {
    return getValueSuffix_;
  }

  @Override
  public String getJsonNodeType()
  {
    return "JsonString";
  }

  @Override
  public boolean getHasLimits()
  {
    return false;
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
  public boolean getIsEnum()
  {
    return !enumMap_.isEmpty();
  }
}
