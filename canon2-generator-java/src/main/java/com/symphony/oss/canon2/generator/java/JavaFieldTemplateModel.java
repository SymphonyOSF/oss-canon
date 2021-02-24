/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon2.generator.java;

import java.io.Writer;
import java.util.Set;
import java.util.TreeSet;

import com.symphony.oss.canon2.core.ResolvedProperty;
import com.symphony.oss.canon2.core.SourceContext;
import com.symphony.oss.canon2.generator.FieldTemplateModel;
import com.symphony.oss.canon2.generator.INamespace;
import com.symphony.oss.canon2.generator.java.JavaGenerator.Context;

/**
 * Implementation of FieldTemplateModel for the Java generator.
 * 
 * @author Bruce Skingle
 *
 */
public class JavaFieldTemplateModel extends FieldTemplateModel<
IJavaTemplateModel,
JavaOpenApiTemplateModel,
JavaSchemaTemplateModel>
implements IJavaTemplateModel
{
  Set<String> imports_ = new TreeSet<>();
  private final String fullyQualifiedNullable_;

  private String nullable_;
  private INamespace namespace_;
  private Writer namespaceWriter_;

  /**
   * Constructor.
   * 
   * @param generatorContext  Contains the source context for error reporting.
   * @param resolvedProperty  The model element representing the field.
   * @param model             The IOpenApiTemplateModel to which this entity belongs.
   * @param typeSchema        The template model for the type of this field.
   * @param required          True if this field is required by it's object.
   * 
   * The reason we have name and identifier is that the name may be valid in the JSON input spec but a reserved word in the
   * target generated language.
   */
  public JavaFieldTemplateModel(JavaGenerator.Context generatorContext, ResolvedProperty resolvedProperty, JavaOpenApiTemplateModel model,
      JavaSchemaTemplateModel typeSchema, boolean required)
  {
    super(generatorContext, initIdentifier(generatorContext, resolvedProperty), resolvedProperty, model, typeSchema, required, EMPTY_TEMPLATES);
    
    imports_.addAll(typeSchema.getImports());
    fullyQualifiedNullable_ = "javax.annotation." + (required ? "Nonnull" : "Nullable");
  }
  
  private static String initIdentifier(Context generatorContext, ResolvedProperty resolvedSchema)
  {
    return generatorContext.getJavaIdentifier(resolvedSchema, false, false);
  }
  
  @Override
  public void resolve(INamespace namespace, Writer writer)
  {
    namespace_ = namespace;
    namespaceWriter_ = writer;
    
    getTypeSchema().resolve(namespace, writer);
  }

  @Override
  public void validate(SourceContext sourceContext)
  {
  }
  
  public boolean getIsObjectType()
  {
    return false;
  }

  /**
   * Return the nullness annotation name for this field.
   * 
   * @return the nullness annotation name for this field.
   */
  public String getNullable()
  {
    if(nullable_ == null)
    {
      nullable_ = namespace_.resolveImport(fullyQualifiedNullable_, namespaceWriter_);
    }
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
  public String getFullyQualifiedType()
  {
    return getTypeSchema().getFullyQualifiedType();
  }

  @Override
  public String getPackageName()
  {
    return getTypeSchema().getPackageName();
  }

  @Override
  public String getType()
  {
    return getTypeSchema().getType();
  }

  @Override
  public String getConstructor(String args)
  {
    return getTypeSchema().getConstructor(args);
  }
  
  @Override
  public String getValue(String args)
  {
    return getTypeSchema().getValue(args);
  }

  @Override
  public String getCopy(String args)
  {
    return getTypeSchema().getCopy(args);
  }

  /**
   * Return the fully qualified class name of the Json node type which represents the serialised value of this field.
   * 
   * @return the fully qualified class name of the Json node type which represents the serialised value of this field.
   */
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