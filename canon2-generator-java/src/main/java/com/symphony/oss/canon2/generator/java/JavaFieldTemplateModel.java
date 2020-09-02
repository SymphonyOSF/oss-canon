/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon2.generator.java;

import java.util.Set;
import java.util.TreeSet;

import com.symphony.oss.canon2.parser.FieldTemplateModel;
import com.symphony.oss.canon2.parser.IResolvedSchema;
import com.symphony.oss.canon2.parser.ITemplateModel;

class JavaFieldTemplateModel extends FieldTemplateModel<
IJavaTemplateModel,
JavaOpenApiTemplateModel,
JavaSchemaTemplateModel,
JavaObjectSchemaTemplateModel,
JavaArraySchemaTemplateModel,
JavaPrimitiveSchemaTemplateModel>
implements IJavaTemplateModel
{
  Set<String> imports_ = new TreeSet<>();
  JavaSchemaTemplateModel type_;
  
  public JavaFieldTemplateModel( IResolvedSchema entity, String name, JavaOpenApiTemplateModel model, JavaGeneratorModelContext generatorModelContext,
      JavaSchemaTemplateModel typeSchema,
      String... temaplates)
  {
    super(entity, name, model, generatorModelContext, typeSchema, temaplates);
  }

  @Override
  public ITemplateModel<IJavaTemplateModel, JavaOpenApiTemplateModel, JavaSchemaTemplateModel, JavaObjectSchemaTemplateModel, JavaArraySchemaTemplateModel, JavaPrimitiveSchemaTemplateModel> asTemplateModel()
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