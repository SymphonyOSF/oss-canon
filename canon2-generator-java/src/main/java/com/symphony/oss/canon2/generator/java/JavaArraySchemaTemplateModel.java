/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon2.generator.java;

import java.math.BigInteger;

import com.symphony.oss.canon2.parser.GenerationException;
import com.symphony.oss.canon2.parser.IArraySchemaTemplateModel;
import com.symphony.oss.canon2.parser.IResolvedSchema;
import com.symphony.oss.canon2.parser.SchemaType;
import com.symphony.oss.canon2.parser.model.CanonCardinality;
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
  
  private final CanonCardinality cardinality_;

  private final String type_;
  private final String copyPrefix_;
  private final Integer minItems_;
  private final Integer maxItems_;
  private final String typeNew_;
  
  JavaArraySchemaTemplateModel(IResolvedSchema entity, String name, String identifier, CanonCardinality cardinality, JavaOpenApiTemplateModel model,
      JavaGeneratorModelContext generatorModelContext, String ...templates) throws GenerationException
  {
    super(entity, name, identifier, model, templates);
    
    cardinality_ = cardinality;
    
//    imports_.add("java.util.List");
    
    IResolvedSchema resolvedItems = (IResolvedSchema) entity.getResolvedItems(); // trust me
    
    elementType_ = resolvedItems.generate(model, "items", generatorModelContext, entity.getItemsSchema() == null);
    
    switch(cardinality)
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
        throw new CodingFault("Unknown cardinality " + cardinality);
    }
    
    minItems_ = entity.getMinItems();
    maxItems_ = entity.getMaxItems();
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

  public Integer getMinItems()
  {
    return minItems_;
  }

  public Integer getMaxItems()
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
