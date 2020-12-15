/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon2.generator.java;

import java.io.Writer;
import java.math.BigInteger;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.symphony.oss.canon2.core.ResolvedArraySchema;
import com.symphony.oss.canon2.core.SchemaTemplateModelType;
import com.symphony.oss.canon2.core.SourceContext;
import com.symphony.oss.canon2.generator.IArraySchemaTemplateModel;
import com.symphony.oss.canon2.generator.INamespace;
import com.symphony.oss.canon2.generator.java.JavaGenerator.Context;
import com.symphony.oss.canon2.model.ArraySchema_Entity.MaxItems;
import com.symphony.oss.canon2.model.ArraySchema_Entity.MinItems;
import com.symphony.oss.canon2.model.CanonCardinality;
import com.symphony.oss.commons.fault.CodingFault;

/**
 * Template model of an Array schema for the Java generator.
 * 
 * @author Bruce Skingle
 *
 */
public class JavaArraySchemaTemplateModel extends JavaSchemaTemplateModel
implements IArraySchemaTemplateModel<
IJavaTemplateModel,
JavaOpenApiTemplateModel,
JavaSchemaTemplateModel>
{
  private static final List<String> ARRAY_TEMPLATES              = ImmutableList.of("Array");
  
  private final String              fullyQualifiedCollectionType_;
  private final String              fullyQualifiedCollectionImplType_;
  private final String              fullyQualifiedInitialiserType_;
  private final String              fullyQualifiedJsonInitialiserType_;
  private final String              fullyQualifiedCollectionImmutableType_;
  
  private JavaSchemaTemplateModel   elementType_;

  private final CanonCardinality    cardinality_;

  private final String              fullyQualifiedType_;
  private String                    type_;
  private String                    collectionImmutableType_;
  private String                    collectionImplType_;
  private final MinItems            minItems_;
  private final MaxItems            maxItems_;




  
  JavaArraySchemaTemplateModel(JavaGenerator.Context generatorContext, ResolvedArraySchema resolvedSchema, String packageName, CanonCardinality cardinality, JavaOpenApiTemplateModel model, IJavaTemplateModel outerClass)
  {
    super(generatorContext, initIdentifier(generatorContext, resolvedSchema), resolvedSchema, packageName, SchemaTemplateModelType.ARRAY, model, outerClass, initTemplates(resolvedSchema));
    
    cardinality_ = cardinality;
    fullyQualifiedType_ = getPackageName() + "." + getCamelCapitalizedName();
    fullyQualifiedJsonInitialiserType_ = "com.symphony.oss.canon2.runtime.java.IArrayEntityInitialiser";

    switch(cardinality_)
    {
      case SET:
        fullyQualifiedCollectionType_ = "java.util.Set";
        fullyQualifiedCollectionImplType_ = "java.util.HashSet";
        fullyQualifiedCollectionImmutableType_ = "com.google.common.collect.ImmutableSet";
        fullyQualifiedInitialiserType_ = "com.symphony.oss.canon2.runtime.java.ISetArrayEntityInitialiser";
        break;
        
      case LIST:
        fullyQualifiedCollectionType_ = "java.util.List";
        fullyQualifiedCollectionImplType_ = "java.util.LinkedList";
        fullyQualifiedCollectionImmutableType_ = "com.google.common.collect.ImmutableList";
        fullyQualifiedInitialiserType_ = "com.symphony.oss.canon2.runtime.java.IListArrayEntityInitialiser";
        break;
        
      default:
        throw new CodingFault("Unknown cardinality " + cardinality_);
    }
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
  
  @Override
  public String getConstructor(String args)
  {
    return "new " + collectionImplType_ + "(" + args + ")";
  }

  @Override
  public String getCopy(String args)
  {
    return collectionImmutableType_ + ".copyOf(" + args + ")";
  }

  @Override
  public String getFullyQualifiedType()
  {
    return fullyQualifiedType_;
  }

  public String getFullyQualifiedInitialiserType()
  {
    return fullyQualifiedInitialiserType_;
  }

  @Override
  public void resolve(INamespace namespace, Writer writer)
  {
    type_                     = namespace.resolveImport(fullyQualifiedType_, writer);
    collectionImmutableType_  = namespace.resolveImport(fullyQualifiedCollectionImmutableType_, writer);
    collectionImplType_       = namespace.resolveImport(fullyQualifiedCollectionImplType_, writer);
    
    elementType_.resolve(namespace, writer);
  }

  /**
   * Return the super class name in cases where this entity does not extend another entity in the model.
   * 
   * @return the super class name in cases where this entity does not extend another entity in the model.
   */
  public String getBaseSuperType()
  {
    return "Entity";
  }

  public String getFullyQualifiedElementType()
  {
    return elementType_.getFullyQualifiedType();
  }

  public String getFullyQualifiedCollectionType()
  {
    return fullyQualifiedCollectionType_;
  }

  public String getFullyQualifiedCollectionImplType()
  {
    return fullyQualifiedCollectionImplType_;
  }

  public String getFullyQualifiedCollectionImmutableType()
  {
    return fullyQualifiedCollectionImmutableType_;
  }

  public String getFullyQualifiedJsonInitialiserType()
  {
    return fullyQualifiedJsonInitialiserType_;
  }

  @Override
  public void setElementType(JavaSchemaTemplateModel elementType)
  {
    elementType_ = elementType;
    
//    fullyQualifiedElementType_ = 
    
    setImport(elementType.getImport());
    if(elementType.getLEGACYPackageName() != null && !getLEGACYPackageName().equals(elementType.getLEGACYPackageName()))
      addImport(elementType.getImport());
    
//    switch(cardinality_)
//    {
//      case SET:
////        imports_.add("java.util.Set");
////        imports_.add("java.util.HashSet");
////        imports_.add("com.google.common.collect.ImmutableSet");
//        type_ = "Set<" + elementType_.getType() + ">";
//        typeNew_ = " = new Hash" + type_ + "()";
//        copyPrefix_ = "ImmutableSet.copyOf(";
//        break;
//        
//      case LIST:
////        imports_.add("java.util.List");
////        imports_.add("com.google.common.collect.ImmutableList");
////        imports_.add("java.util.LinkedList");
//        type_ = "List<" + elementType_.getType() + ">";
//        typeNew_ = " = new Linked" + type_ + "()";
//        copyPrefix_ = "ImmutableList.copyOf(";
//        break;
//        
//      default:
//        throw new CodingFault("Unknown cardinality " + cardinality_);
//    }
  }

  @Override
  public String getFullyQualifiedJsonNodeType()
  {
    return "com.symphony.oss.canon.json.model.JsonArray";
  }

  @Override
  public String getBuilderTypeNew()
  {
    return "new " + collectionImplType_ + "<" + type_ + ">()";
  }

  @Override
  public boolean getHasLimits()
  {
    return minItems_ != null || maxItems_ != null;
  }

  /**
   * Return the minimum allowed number of elements or null if there is no minimum.
   * 
   * @return the minimum allowed number of elements or null if there is no minimum.
   */
  public BigInteger getMinItems()
  {
    if(minItems_ == null)
      return null;
    
    return minItems_.asBigInteger();
  }

  /**
   * Return the maximum allowed number of elements or null if there is no maximum.
   * 
   * @return the maximum allowed number of elements or null if there is no maximum.
   */
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
  
//  @Override
//  public String getCopyPrefix()
//  {
//    return copyPrefix_;
//  }
//
//  @Override
//  public String getCopySuffix()
//  {
//    return ")";
//  }
}
