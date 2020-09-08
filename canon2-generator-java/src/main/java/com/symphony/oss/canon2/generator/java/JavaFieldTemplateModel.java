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

public class JavaFieldTemplateModel extends FieldTemplateModel<
IJavaTemplateModel,
JavaOpenApiTemplateModel,
JavaSchemaTemplateModel>
implements IJavaTemplateModel
{
  Set<String> imports_ = new TreeSet<>();
  
  private final String typeName_;
  
  public JavaFieldTemplateModel(IResolvedSchema entity, String name, JavaOpenApiTemplateModel model,
      JavaSchemaTemplateModel typeSchema, boolean required,
      String... temaplates)
  {
    super(name, model, typeSchema, required, temaplates);
    
//    if(entity.getTypeName() == null)
    {
      typeName_ = typeSchema.getType();
    }
//    else
//    {
//      typeName_ = capitalize(toCamelCase(
//        entity.getTypeName()));
//      
////      imports_.add(typeName_);
//    }
    
    imports_.addAll(typeSchema.getImports());
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
    return typeName_; //getTypeSchema().getType();
  }

  @Override
  public String toString()
  {
    return "JavaFieldTemplateModel " + getType() + " " + getCamelName();
  }
}