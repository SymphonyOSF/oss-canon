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

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.symphony.oss.canon.json.ParserWarningException;
import com.symphony.oss.canon2.core.ResolvedStringSchema;
import com.symphony.oss.canon2.generator.IPrimitiveSchemaTemplateModel;
import com.symphony.oss.canon2.generator.java.JavaGenerator.Context;
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
  private final boolean                      isEnum_;
  private final Set<String>                  quotedEnumValues_;
  private final Set<String>                  enumValues_;
  private final ImmutableMap<String, String> enumMap_;
  
  JavaStringSchemaTemplateModel(JavaGenerator.Context generatorContext, ResolvedStringSchema resolvedSchema, String packageName, JavaOpenApiTemplateModel model, IJavaTemplateModel outerClass)
  { 
    super(generatorContext, initIdentifier(generatorContext, resolvedSchema), resolvedSchema, !resolvedSchema.getEnum().isEmpty(),
        packageName, initTypePackage(resolvedSchema), initType(resolvedSchema), model, outerClass, initTemplates(resolvedSchema));
    
    StringSchema entity = resolvedSchema.getSchema();
    if(entity.getFormat() != null)
    {
      switch(entity.getFormat())
      {
        case "byte":
          setAndAddImport("com.symphony.oss.commons.immutable", "ImmutableByteArray");
          break;
          
        default:
         generatorContext.getSourceContext().error(new ParserWarningException("Unrecognized " + entity.getType() + " format \"" + entity.getFormat() + "\" ignored.", getJson().getContext()));
      }
    }
    
    
    Set<String> enumList = resolvedSchema.getSchema().getEnum();
    
    if(enumList==null || enumList.isEmpty())
    {
      isEnum_ = false;
      enumValues_ = ImmutableSet.of();
      quotedEnumValues_ = ImmutableSet.of();
      enumMap_ = ImmutableMap.of();
    }
    else
    {
      isEnum_ = true;
      Set<String> quotedValues = new HashSet<>(enumList.size());
      Set<String> values = new HashSet<>(enumList.size());
      Map<String, String> valueMap = new HashMap<>();
      
      for(Object v : enumList)
      {
        String value = toSnakeCase(v.toString()).toUpperCase();
        String quotedValue;
        
        if("java.lang.String".equals(getFullyQualifiedJavaType()))
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
      
    }
  }

  private static List<String> initTemplates(ResolvedStringSchema resolvedSchema)
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

  private static String initIdentifier(Context generatorContext,
      ResolvedStringSchema resolvedSchema)
  {
    if(resolvedSchema.isInnerClass() || resolvedSchema.getResolvedOpenApiObject().isReferencedModel())
    {
      return resolvedSchema.getName(); // not generating for this so we don't care if the identifier is valid.
    }
    else
    {
      return getIdentifier(generatorContext, resolvedSchema);
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

  private static String initTypePackage(ResolvedStringSchema resolvedSchema)
  {
    StringSchema entity = resolvedSchema.getSchema();
    if(entity.getFormat() != null)
    {
      switch(entity.getFormat())
      {
        case "byte":
          return "com.symphony.oss.commons.immutable";
      }
    }
      
    return "java.lang";
  }

  @Override
  public String getConstructor(boolean fullyQualified, String args)
  {
    if(isEnum_)
      return "/* string getConstructor " + fullyQualified + " */" + getType() + ".deserialize(" + args + ")"; 
    else
      return "/* string getConstructor " + fullyQualified + " */" + super.getConstructor(fullyQualified, args);
  }
  
  @Override
  public String getValue(boolean fullyQualified, String args)
  {
    if(isEnum_)
      return "/* string getValue " + fullyQualified + " */" + args + ".getValue()";
    else
      return "/* string getValue " + fullyQualified + " */" + args;
  }

  @Override
  public String getFullyQualifiedJsonNodeType()
  {
    return "com.symphony.oss.canon.json.model.JsonString";
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
