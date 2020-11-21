/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon2.generator.java;

import java.util.Set;
import java.util.TreeSet;

import com.symphony.oss.canon2.core.ResolvedProperty;
import com.symphony.oss.canon2.core.SourceContext;
import com.symphony.oss.canon2.generator.FieldTemplateModel;
import com.symphony.oss.canon2.generator.java.JavaGenerator.Context;

public class JavaFieldTemplateModel extends FieldTemplateModel<
IJavaTemplateModel,
JavaOpenApiTemplateModel,
JavaSchemaTemplateModel>
implements IJavaTemplateModel
{
  Set<String> imports_ = new TreeSet<>();
  private final String nullable_;
  
  public JavaFieldTemplateModel(JavaGenerator.Context generatorContext, ResolvedProperty resolvedProperty, JavaOpenApiTemplateModel model,
      JavaSchemaTemplateModel typeSchema, boolean required)
  {
    super(generatorContext, initIdentifier(generatorContext, resolvedProperty), resolvedProperty, model, typeSchema, required, EMPTY_TEMPLATES);
    
    imports_.addAll(typeSchema.getImports());
    nullable_ = required ? "Nonnull" : "Nullable";
  }

  private static String initIdentifier(Context generatorContext, ResolvedProperty resolvedSchema)
  {
    return generatorContext.getJavaIdentifier(resolvedSchema, false, false);
  }
  
  @Override
  public void validate(SourceContext sourceContext)
  {
  }

  public String getNullable()
  {
    return nullable_;
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
  
  public String getFullyQualifiedJsonNodeType()
  {
    return getTypeSchema().getFullyQualifiedJsonNodeType();
  }

  @Override
  public String toString()
  {
    return "JavaFieldTemplateModel " + getTypeSchema().getCamelCapitalizedName() + " " + getCamelName();
  }
}