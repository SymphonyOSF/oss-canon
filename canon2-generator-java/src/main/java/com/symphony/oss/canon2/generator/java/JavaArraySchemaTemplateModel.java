/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon2.generator.java;

import java.io.Writer;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  private final String              simpleCollectionType_;
  private final boolean             isInnerClass_;
  private final boolean             isPrimitive_;
  private final CanonCardinality    cardinality_;
  private final String              fullyQualifiedType_;
  private final MinItems            minItems_;
  private final MaxItems            maxItems_;
  private Map<String, JavaSchemaTemplateModel> innerClassMap_   = new HashMap<>();
  
  private final String              fullyQualifiedCollectionType_;
  private final String              fullyQualifiedCollectionImplType_;
  private final String              fullyQualifiedInitialiserType_;
  private final String              fullyQualifiedJsonInitialiserType_;
  private final String              fullyQualifiedCollectionImmutableType_;

  private String                    collectionType_;
  private String                    collectionImplType_;
  private String                    initialiserType_;
  private String                    jsonInitialiserType_;
  private String                    collectionImmutableType_;
  
  private String                    type_;
  private JavaSchemaTemplateModel   element_;
  
  
  JavaArraySchemaTemplateModel(JavaGenerator.Context generatorContext, ResolvedArraySchema resolvedSchema, String packageName, CanonCardinality cardinality, JavaOpenApiTemplateModel model, IJavaTemplateModel outerClass)
  {
    super(generatorContext, initIdentifier(generatorContext, resolvedSchema), resolvedSchema, packageName, SchemaTemplateModelType.ARRAY, model, outerClass, initTemplates(resolvedSchema));
    
    cardinality_ = cardinality;
    minItems_ = resolvedSchema.getSchema().getMinItems();
    maxItems_ = resolvedSchema.getSchema().getMaxItems();
//    fullyQualifiedType_ = getPackageName() + "." + getCamelCapitalizedName();
    
    if(resolvedSchema.isInnerClass() && outerClass != null)
    {
      fullyQualifiedType_ = outerClass.getFullyQualifiedType() + "." + getCamelCapitalizedName();
      isInnerClass_ = true;
    }
    else
    {
      fullyQualifiedType_ = getPackageName() + "." + getCamelCapitalizedName();
      isInnerClass_ = false;
    }
    
    isPrimitive_ = getHasLimits() == false && Boolean.TRUE != resolvedSchema.getSchema().getXCanonFacade(); 
    
    fullyQualifiedJsonInitialiserType_ = "com.symphony.oss.canon2.runtime.java.IArrayEntityInitialiser";

    switch(cardinality_)
    {
      case SET:
        simpleCollectionType_ = "Set";
        fullyQualifiedCollectionType_ = "java.util.Set";
        fullyQualifiedCollectionImplType_ = "java.util.HashSet";
        fullyQualifiedCollectionImmutableType_ = "com.google.common.collect.ImmutableSet";
        fullyQualifiedInitialiserType_ = "com.symphony.oss.canon2.runtime.java.ISetArrayEntityInitialiser";
        break;
        
      case LIST:
        simpleCollectionType_ = "List";
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
    
  }

  private static List<String> initTemplates(ResolvedArraySchema resolvedSchema)
  {
    if(
        (resolvedSchema.getSchema().getMinItems() == null && 
            resolvedSchema.getSchema().getMinItems() == null && 
            Boolean.TRUE != resolvedSchema.getSchema().getXCanonFacade()) ||
        resolvedSchema.isInnerClass() || resolvedSchema.getResolvedOpenApiObject().isReferencedModel())
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
    if(
        //resolvedSchema.isInnerClass() ||
        resolvedSchema.getResolvedOpenApiObject().isReferencedModel())
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
  public void addInnerClass(String name, JavaSchemaTemplateModel innerClass)
  {
    innerClassMap_.put(name, innerClass);
  }
  
  @Override
  public String getConstructor(String args)
  {
    if(getHasLimits())
    {
      return "new " + getType() + "(" + args + ") /* ARRAY_CONSTRUCT */";
    }
    else
    {
      return getCollectionImmutableType() + ".copyOf(" + args + ") /* ARRAY_CONSTRUCT */";
    }
    //return "new " + getCollectionImplType() + "(" + args + ") /* ARRAY_CONSTRUCT */";
  }

  @Override
  public String getCopy(String args)
  {
    if(getHasLimits())
    {
      return args;
    }
    else
    {
      return getCollectionImmutableType() + ".copyOf(" + args + ")";
    }
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
    super.resolve(namespace, writer);
    
    type_ = null;
    
    collectionType_           = null;
    collectionImplType_       = null;
    initialiserType_          = null;
    jsonInitialiserType_      = null;
    collectionImmutableType_  = null;
    
    element_.resolve(namespace, writer);
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

//  public String getFullyQualifiedElementType()
//  {
//    return element_.getFullyQualifiedType();
//  }
//
//  public String getFullyQualifiedCollectionType()
//  {
//    return fullyQualifiedCollectionType_;
//  }
//
//  public String getFullyQualifiedCollectionImplType()
//  {
//    return fullyQualifiedCollectionImplType_;
//  }
//
//  public String getFullyQualifiedCollectionImmutableType()
//  {
//    return fullyQualifiedCollectionImmutableType_;
//  }
//
//  public String getFullyQualifiedJsonInitialiserType()
//  {
//    return fullyQualifiedJsonInitialiserType_;
//  }

  @Override
  public void setElement(JavaSchemaTemplateModel element)
  {
    element_ = element;
    
//    fullyQualifiedElementType_ = 
    
    setImport(element.getImport());
    if(element.getLEGACYPackageName() != null && !getLEGACYPackageName().equals(element.getLEGACYPackageName()))
      addImport(element.getImport());
    
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
  protected String getFullyQualifiedJsonNodeType()
  {
    return "com.symphony.oss.canon.json.model.JsonArray";
  }

  @Override
  public String getBuilderTypeNew()
  {
    if(isPrimitive_)
      return " = new " + getCollectionImplType() + "<" + getElement().getType() + ">()";
    else
      return "";
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
  public JavaSchemaTemplateModel getElement()
  {
    return element_;
  }

  @Override
  public CanonCardinality getCardinality()
  {
    return cardinality_;
  }

  @Override
  public String getType()
  {
    if(type_ == null)
    {
      if(isPrimitive_)
      {
        type_ = getCollectionType() + "<" + getElement().getType() + ">";
      }
      else
      {
        if(isInnerClass_)
        {
          type_ = getOuterClass().getType() + "." + getCamelCapitalizedName();
        }
        else
        {
          type_ = namespace_.resolveImport(fullyQualifiedType_, namespaceWriter_);
        }
      }
    }
    return type_;
  }
  
  public String getCollectionImmutableType()
  {
    if(collectionImmutableType_ == null)
      collectionImmutableType_ = namespace_.resolveImport(fullyQualifiedCollectionImmutableType_, namespaceWriter_);
    
    return collectionImmutableType_;
  }

  public String getCollectionImplType()
  {
    if(collectionImplType_ == null)
      collectionImplType_ = namespace_.resolveImport(fullyQualifiedCollectionImplType_, namespaceWriter_);
    
    return collectionImplType_;
  }

  public String getCollectionType()
  {
    if(collectionType_ == null)
      collectionType_ = namespace_.resolveImport(fullyQualifiedCollectionType_, namespaceWriter_);
    
    return collectionType_;
  }

  public String getJsonInitialiserType()
  {
    if(jsonInitialiserType_ == null)
      jsonInitialiserType_ = namespace_.resolveImport(fullyQualifiedJsonInitialiserType_, namespaceWriter_);
    
    return jsonInitialiserType_;
  }

  public String getInitialiserType()
  {
    if(initialiserType_ == null)
      initialiserType_ = namespace_.resolveImport(fullyQualifiedInitialiserType_, namespaceWriter_);
    
    return initialiserType_;
  }

  public String getSimpleCollectionType()
  {
    return simpleCollectionType_;
  }

  public boolean getAdditionalPropertiesAllowed()
  {
    return false;
  }

  public boolean getIsPrimitive()
  {
    return isPrimitive_;
  }

  @Override
  public Collection<JavaSchemaTemplateModel> getInnerClasses()
  {
    return innerClassMap_.values();
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
