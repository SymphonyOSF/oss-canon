/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon2.generator.java;

import java.util.List;

import com.symphony.oss.canon2.core.ResolvedBooleanSchema;
import com.symphony.oss.canon2.generator.IPrimitiveSchemaTemplateModel;

/**
 * Java template model for number and integer.
 * 
 * @author Bruce Skingle
 *
 */
public class JavaBooleanSchemaTemplateModel extends JavaPrimitiveSchemaTemplateModel
implements IPrimitiveSchemaTemplateModel<
IJavaTemplateModel,
JavaOpenApiTemplateModel,
JavaSchemaTemplateModel>
{  
  JavaBooleanSchemaTemplateModel(ResolvedBooleanSchema resolvedSchema, IdentifierAndImport identifierAndImport, JavaOpenApiTemplateModel model,
       List<String> templates)
  { 
    super(resolvedSchema, identifierAndImport, model, templates);
  }

  @Override
  public String getJsonNodeType()
  {
    return "JsonBoolean";
  }

  @Override
  public boolean getHasLimits()
  {
    return false;
  }
}
