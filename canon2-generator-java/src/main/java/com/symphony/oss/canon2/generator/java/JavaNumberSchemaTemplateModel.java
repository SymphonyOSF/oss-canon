/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon2.generator.java;

import java.util.List;

import com.symphony.oss.canon2.core.ResolvedNumberSchema;
import com.symphony.oss.canon2.generator.IPrimitiveSchemaTemplateModel;

/**
 * Java template model for number and integer.
 * 
 * @author Bruce Skingle
 *
 */
public class JavaNumberSchemaTemplateModel extends JavaPrimitiveSchemaTemplateModel
implements IPrimitiveSchemaTemplateModel<
IJavaTemplateModel,
JavaOpenApiTemplateModel,
JavaSchemaTemplateModel>
{
  private final String  minimum_;
  private final String  maximum_;
  private final boolean exclusiveMinimum_;
  private final boolean exclusiveMaximum_;
  private final boolean isComparable_;
  
  JavaNumberSchemaTemplateModel(ResolvedNumberSchema<?> resolvedSchema, String identifier, String packageName,
      String javaTypePackageName, String javaType, JavaOpenApiTemplateModel model,
      boolean isComparable,
      List<String> templates)
  { 
    super(resolvedSchema, identifier, packageName, javaType, model, templates);

    minimum_ = resolvedSchema.getMinimum() == null ? null : resolvedSchema.getMinimum().toString();
    maximum_ = resolvedSchema.getMaximum() == null ? null : resolvedSchema.getMaximum().toString();
    exclusiveMinimum_ = resolvedSchema.isExclusiveMinimum();
    exclusiveMaximum_ = resolvedSchema.isExclusiveMaximum();
    isComparable_ = isComparable;
    
    if(javaTypePackageName != null)
    {
      setAndAddImport(javaTypePackageName, javaType);
    }
    else
    {
      setImport(packageName,  getCamelCapitalizedName());
    }
    

//  if(isExternal())
//  {
//    addImport(packageName + "." + getCamelCapitalizedName());
//  }
  
  }

  @Override
  public String getJsonNodeType()
  {
    return "JsonParsedNumber";
  }

  @Override
  public boolean getHasLimits()
  {
    return minimum_!=null || maximum_ != null;
  }
  
  public String generateComparason(String var, String value, String comparason)
  {
    if(isComparable_)
      return var + ".compareTo(" + value + ") " + comparason + " 0";
    else
      return var + " " + comparason + " " + value;
  }

  public String getMinimum()
  {
    return minimum_;
  }

  public String getMaximum()
  {
    return maximum_;
  }

  public boolean getExclusiveMinimum()
  {
    return exclusiveMinimum_;
  }

  public boolean getExclusiveMaximum()
  {
    return exclusiveMaximum_;
  }
}
