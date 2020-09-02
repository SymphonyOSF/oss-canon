/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon2.generator.java;

import com.symphony.oss.canon2.parser.IArraySchemaTemplateModel;
import com.symphony.oss.canon2.parser.IResolvedSchema;
import com.symphony.oss.canon2.parser.model.CanonCardinality;
import com.symphony.oss.commons.fault.CodingFault;

class JavaArraySchemaTemplateModel extends JavaSchemaTemplateModel
implements IArraySchemaTemplateModel<
IJavaTemplateModel,
JavaOpenApiTemplateModel,
JavaSchemaTemplateModel>
{
  private JavaSchemaTemplateModel elementType_;
  
  private final CanonCardinality cardinality_;

  private final String type_;
  
  JavaArraySchemaTemplateModel(IResolvedSchema entity, String name, CanonCardinality cardinality, JavaOpenApiTemplateModel model,
      JavaGeneratorModelContext generatorModelContext, String ...templates)
  {
    super(name, model, templates);
    
    cardinality_ = cardinality;
    
    imports_.add("java.util.List");
    
    IResolvedSchema resolvedItems = (IResolvedSchema) entity.getResolvedItems(); // trust me
    
    elementType_ = resolvedItems.generate(model, "items", generatorModelContext);
    
    switch(cardinality)
    {
      case SET:
        imports_.add("java.util.Set");
        imports_.add("java.util.HashSet");
        type_ = "Set<" + elementType_.getType() + ">";
        break;
        
      case LIST:
        imports_.add("java.util.List");
        imports_.add("java.util.LinkedList");
        type_ = "List<" + elementType_.getType() + ">";
        break;
        
      default:
        throw new CodingFault("Unknown cardinality " + cardinality);
    }
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
}
