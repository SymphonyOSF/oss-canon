/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon2.generator.java;

import java.math.BigInteger;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.symphony.oss.canon2.core.ResolvedArraySchema;
import com.symphony.oss.canon2.core.SchemaTemplateModelType;
import com.symphony.oss.canon2.core.SourceContext;
import com.symphony.oss.canon2.generator.IArraySchemaTemplateModel;
import com.symphony.oss.canon2.generator.java.JavaGenerator.Context;
import com.symphony.oss.canon2.model.ArraySchema_Entity.MaxItems;
import com.symphony.oss.canon2.model.ArraySchema_Entity.MinItems;
import com.symphony.oss.canon2.model.CanonCardinality;
import com.symphony.oss.commons.fault.CodingFault;

public class JavaArraySchemaTemplateModel extends JavaSchemaTemplateModel
implements IArraySchemaTemplateModel<
IJavaTemplateModel,
JavaOpenApiTemplateModel,
JavaSchemaTemplateModel>
{
  private static final List<String> ARRAY_TEMPLATES              = ImmutableList.of("Array");
  
  private JavaSchemaTemplateModel elementType_;
  
  private final CanonCardinality  cardinality_;
  
  private String                  type_;
  private String                  copyPrefix_;
  private final MinItems          minItems_;
  private final MaxItems          maxItems_;
  private String                  typeNew_;
  
  JavaArraySchemaTemplateModel(JavaGenerator.Context generatorContext, ResolvedArraySchema resolvedSchema, String packageName, CanonCardinality cardinality, JavaOpenApiTemplateModel model)
  {
    super(generatorContext, initIdentifier(generatorContext, resolvedSchema), resolvedSchema, SchemaTemplateModelType.ARRAY, model, initTemplates(resolvedSchema));
    
    cardinality_ = cardinality;
    
    
//    if(isExternal())
//    {
//      addImport(packageName + "." + getCamelCapitalizedName());
//    }
    
    setImport(packageName,  getCamelCapitalizedName());
    
    minItems_ = resolvedSchema.getSchema().getMinItems();
    maxItems_ = resolvedSchema.getSchema().getMaxItems();
  }

  private static List<String> initTemplates(ResolvedArraySchema resolvedSchema)
  {
    if(resolvedSchema.isInnerClass() || resolvedSchema.getResolvedOpenApiObject().isReferencedModel())
    {
      return EMPTY_TEMPLATES;
    }
    else
    {
      return ARRAY_TEMPLATES;
    }
  }

  private static String initIdentifier(Context generatorContext, ResolvedArraySchema resolvedSchema)
  {
    if(resolvedSchema.isInnerClass() || resolvedSchema.getResolvedOpenApiObject().isReferencedModel())
    {
      return resolvedSchema.getName(); // not generating for this so we don't care if the identifier is valid.
    }
    else
    {
      return getIdentifier(generatorContext, resolvedSchema);
    }
  }

  @Override
  public void validate(SourceContext sourceContext)
  {
  }
  
  public String getBaseSuperType()
  {
    return "Entity";
  }

  @Override
  public void setElementType(JavaSchemaTemplateModel elementType)
  {
    elementType_ = elementType;
    
    setImport(elementType.getImport());
    if(elementType.getPackageName() != null && !getPackageName().equals(elementType.getPackageName()))
      addImport(elementType.getImport());
    
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
    if(minItems_ == null)
      return null;
    
    return minItems_.asBigInteger();
  }

  public BigInteger getMaxItems()
  {
    if(maxItems_ == null)
      return null;
    
    return maxItems_.asBigInteger();
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
