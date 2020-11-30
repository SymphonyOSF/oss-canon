/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon2.generator.java;

import java.util.List;

import com.symphony.oss.canon2.core.ResolvedBooleanSchema;
import com.symphony.oss.canon2.core.ResolvedPropertyContainerSchema;
import com.symphony.oss.canon2.generator.IPrimitiveSchemaTemplateModel;
import com.symphony.oss.canon2.generator.java.JavaGenerator.Context;

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
  JavaBooleanSchemaTemplateModel(JavaGenerator.Context generatorContext, ResolvedBooleanSchema resolvedSchema, String packageName, JavaOpenApiTemplateModel model)
  { 
    super(generatorContext, initIdentifier(generatorContext, resolvedSchema), resolvedSchema, false, packageName, "java.lang", "Boolean", model, initTemplates(resolvedSchema));
  }

  private static List<String> initTemplates(ResolvedBooleanSchema resolvedSchema)
  {
    if(resolvedSchema.isInnerClass() || resolvedSchema.getResolvedOpenApiObject().isReferencedModel())
    {
      return EMPTY_TEMPLATES;
    }
    else
    {
      return TYPEDEF_TEMPLATES;
    }
  }

  private static String initIdentifier(Context generatorContext, ResolvedBooleanSchema resolvedSchema)
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

  @Override
  public String getFullyQualifiedJsonNodeType()
  {
    return "com.symphony.oss.canon.json.model.JsonBoolean";
  }

  @Override
  public boolean getHasLimits()
  {
    return false;
  }
}
