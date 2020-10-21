/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon2.generator.java;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.symphony.oss.canon2.core.ResolvedSchema;
import com.symphony.oss.canon2.core.SchemaTemplateModelType;
import com.symphony.oss.canon2.generator.IGroupSchemaTemplateModel;

public class JavaGroupSchemaTemplateModel extends JavaSchemaTemplateModel
implements IGroupSchemaTemplateModel<
IJavaTemplateModel,
JavaOpenApiTemplateModel,
JavaSchemaTemplateModel>
{
  private List<JavaSchemaTemplateModel> subSchemas_ = new LinkedList<>();
  private List<JavaSchemaTemplateModel> innerClasses_ = new LinkedList<>();

  private final String nullable_;
  private String            type_;
  private String            copyPrefix_;
  private String            typeNew_;


  
  JavaGroupSchemaTemplateModel(ResolvedSchema resolvedSchema, String identifier, String packageName, 
      SchemaTemplateModelType schemaType, JavaOpenApiTemplateModel model,
      String ...templates)
  {
    super(resolvedSchema, schemaType, identifier, packageName, model, templates);
    
    type_ = getCamelCapitalizedName();
    
    switch(schemaType)
    {
      case ALL_OF:
        nullable_ = "Nonnull";
        break;
        
      default:
        nullable_ = "Nullable";
    }
  }

  @Override
  public void addSubSchema(JavaSchemaTemplateModel subSchema)
  {
    subSchemas_.add(subSchema);
    
    //setImport(subSchema.getImport());
    if(!getPackageName().equals(subSchema.getPackageName()))
      addImport(subSchema.getImport());
  }

  @Override
  public void addInnerClass(JavaSchemaTemplateModel innerClass)
  {
    innerClasses_.add(innerClass);
  }

  @Override
  public List<JavaSchemaTemplateModel> getSubSchemas()
  {
    return subSchemas_;
  }

  public Collection<JavaSchemaTemplateModel> getInnerClasses()
  {
    return innerClasses_;
  }

  @Override
  public String getJsonNodeType()
  {
    return "JsonObject";
  }

  @Override
  public String getFullyQualifiedJsonNodeType()
  {
    return "com.symphony.oss.canon.json.model.JsonObject";
  }

  @Override
  public String getBuilderTypeNew()
  {
    return typeNew_;
  }

  @Override
  public boolean getHasLimits()
  {
    for(JavaSchemaTemplateModel subSchema : subSchemas_)
    {
      if(subSchema.getHasLimits())
        return true;
    }
    return false;
  }

//  @Override
//  public SchemaType getSchemaType()
//  {
//    return SchemaType.ARRAY;
//  }

  @Override
  public JavaSchemaTemplateModel asSchemaTemplateModel()
  {
    return this;
  }

  @Override
  public IJavaTemplateModel asTemplateModel()
  {
    return this;
  }
  
  @Override
  public String getType()
  {
    return type_;
  }
  
  @Override
  public String getCopyPrefix()
  {
    return "";
  }

  @Override
  public String getCopySuffix()
  {
    return "";
  }

  public String getNullable()
  {
    return nullable_;
  }
}
