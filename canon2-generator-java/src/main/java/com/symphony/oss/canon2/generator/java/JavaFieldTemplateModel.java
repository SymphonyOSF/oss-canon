/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon2.generator.java;

import java.util.Set;
import java.util.TreeSet;

import com.symphony.oss.canon2.model.ResolvedSchema;
import com.symphony.oss.canon2.parser.FieldTemplateModel;

public class JavaFieldTemplateModel extends FieldTemplateModel<
IJavaTemplateModel,
JavaOpenApiTemplateModel,
JavaSchemaTemplateModel>
implements IJavaTemplateModel
{
  Set<String> imports_ = new TreeSet<>();
  
  public JavaFieldTemplateModel(ResolvedSchema entity, String name, String identifier, JavaOpenApiTemplateModel model,
      JavaSchemaTemplateModel typeSchema, boolean required,
      String... temaplates)
  {
    super(name, identifier, model, typeSchema, required, temaplates);
    
    imports_.addAll(typeSchema.getImports());
  }

  @Override
  public boolean getHasLimits()
  {
    return getRequired() || getTypeSchema().getHasLimits();
  }

  @Override
  public IJavaTemplateModel asTemplateModel()
  {
    return this;
  }

  @Override
  public Set<String> getImports()
  {
    return imports_;
  }

  @Override
  public String getType()
  {
    return getTypeSchema().getType();
  }

  @Override
  public String toString()
  {
    return "JavaFieldTemplateModel " + getTypeSchema().getCamelCapitalizedName() + " " + getCamelName();
  }
}