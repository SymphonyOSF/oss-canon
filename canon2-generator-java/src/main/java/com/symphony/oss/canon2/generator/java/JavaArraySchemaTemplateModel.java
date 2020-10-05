/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon2.generator.java;

import java.math.BigInteger;

import com.symphony.oss.canon2.generator.IArraySchemaTemplateModel;
import com.symphony.oss.canon2.model.CanonCardinality;
import com.symphony.oss.canon2.model.ResolvedSchema;
import com.symphony.oss.canon2.model.SchemaType;
import com.symphony.oss.commons.fault.CodingFault;

public class JavaArraySchemaTemplateModel extends JavaSchemaTemplateModel
implements IArraySchemaTemplateModel<
IJavaTemplateModel,
JavaOpenApiTemplateModel,
JavaSchemaTemplateModel>
{
//  private static final String[] IMPORTS = new String[] 
//  {
//      
//  };
  
  private JavaSchemaTemplateModel elementType_;
  
  private final CanonCardinality  cardinality_;
  
  private String            type_;
  private String            copyPrefix_;
  private final BigInteger        minItems_;
  private final BigInteger        maxItems_;
  private String            typeNew_;
  
  JavaArraySchemaTemplateModel(String name, ResolvedSchema resolvedSchema, String identifier, String packageName, CanonCardinality cardinality, JavaOpenApiTemplateModel model,
      String ...templates)
  {
    super(name, resolvedSchema, identifier, packageName, model, templates);
    
    cardinality_ = cardinality;
    
    //generator.generateSchema(resolvedItems, model, "items", resolvedSchema.getSchema().getItemsSchema() == null);
    

    
    minItems_ = resolvedSchema.getSchema().getMinItems();
    maxItems_ = resolvedSchema.getSchema().getMaxItems();
  }

  @Override
  public void setElementType(JavaSchemaTemplateModel elementType)
  {
    elementType_ = elementType;
    imports_.addAll(elementType.imports_);
    
    switch(cardinality_)
    {
      case SET:
//        imports_.add("java.util.Set");
//        imports_.add("java.util.HashSet");
//        imports_.add("com.google.common.collect.ImmutableSet");
        type_ = "Set<" + elementType_.getType() + ">";
        typeNew_ = " = new Hash" + type_ + "()";
        copyPrefix_ = "ImmutableSet.copyOf(";
        break;
        
      case LIST:
//        imports_.add("java.util.List");
//        imports_.add("com.google.common.collect.ImmutableList");
//        imports_.add("java.util.LinkedList");
        type_ = "List<" + elementType_.getType() + ">";
        typeNew_ = " = new Linked" + type_ + "()";
        copyPrefix_ = "ImmutableList.copyOf(";
        break;
        
      default:
        throw new CodingFault("Unknown cardinality " + cardinality_);
    }
  }

  @Override
  public String getJsonNodeType()
  {
    return "JsonArray";
  }

  @Override
  public String getFullyQualifiedJsonNodeType()
  {
    return "com.symphony.oss.canon.json.model.JsonArray";
  }

  @Override
  public String getBuilderTypeNew()
  {
    return typeNew_;
  }

  @Override
  public boolean getHasLimits()
  {
    return minItems_ != null || maxItems_ != null;
  }

  public BigInteger getMinItems()
  {
    return minItems_;
  }

  public BigInteger getMaxItems()
  {
    return maxItems_;
  }

  @Override
  public SchemaType getSchemaType()
  {
    return SchemaType.ARRAY;
  }

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
  public JavaSchemaTemplateModel getElementType()
  {
    return elementType_;
  }

  @Override
  public CanonCardinality getCardinality()
  {
    return cardinality_;
  }

  @Override
  public String getType()
  {
    return type_;
  }
  
  @Override
  public String getCopyPrefix()
  {
    return copyPrefix_;
  }

  @Override
  public String getCopySuffix()
  {
    return ")";
  }
}
