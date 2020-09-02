/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon2.generator.java;

import java.util.Set;
import java.util.TreeSet;

import com.symphony.oss.canon2.parser.FieldTemplateModel;

class JavaFieldTemplateModel extends FieldTemplateModel<
IJavaTemplateModel,
JavaOpenApiTemplateModel,
JavaSchemaTemplateModel>
implements IJavaTemplateModel
{
  Set<String> imports_ = new TreeSet<>();
  
  public JavaFieldTemplateModel(String name, JavaOpenApiTemplateModel model,
      JavaSchemaTemplateModel typeSchema,
      String... temaplates)
  {
    super(name, model, typeSchema, temaplates);
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
}