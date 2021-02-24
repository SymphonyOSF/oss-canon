/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon2.generator.java;

import java.io.Writer;
import java.util.List;

import com.symphony.oss.canon2.core.ResolvedPrimitiveSchema;
import com.symphony.oss.canon2.core.SourceContext;
import com.symphony.oss.canon2.generator.INamespace;
import com.symphony.oss.canon2.generator.IPrimitiveSchemaTemplateModel;
import com.symphony.oss.canon2.generator.java.JavaGenerator.Context;
import com.symphony.oss.canon2.model.CanonAttributes;

/**
 * Java template model for primitive types number, integer, boolean, string.
 * 
 * @author Bruce Skingle
 *
 */
public abstract class JavaPrimitiveSchemaTemplateModel extends JavaSchemaTemplateModel
implements IPrimitiveSchemaTemplateModel<
IJavaTemplateModel,
JavaOpenApiTemplateModel,
JavaSchemaTemplateModel>
{
  private final String  fullyQualifiedExternalType_;
  private final String  fullyQualifiedPersistedType_;
  private final String  simplePersistedType_;
  private final String  fullyQualifiedType_;
//  private final String  constructPrefix_;
//  private final String  fullyQualifiedConstructPrefix_;
//  private final String  constructSuffix_;
//  private final String  getValuePrefix_;
//  private final String  getValueSuffix_;
//  private final String  fullyQualifiedExternalType_;
  private final String fullyQualifiedBuilderType_;
  private final boolean hasLimits_;
  private final boolean isInnerClass_;
  private final boolean isGenerated_;

  private String        type_;
  private String        externalType_;
  private String        persistedType_;
  private String        builderType_;

  JavaPrimitiveSchemaTemplateModel(JavaGenerator.Context generatorContext, String identifier, ResolvedPrimitiveSchema<?> resolvedSchema,
      boolean requiresGeneration, String packageName, String javaTypePackageName, String javaType, JavaOpenApiTemplateModel model, IJavaTemplateModel outerClass,
      List<String> templates)
  { 
    super(generatorContext, identifier, resolvedSchema, packageName, resolvedSchema.getSchemaType(), model, outerClass, templates);
    
    hasLimits_ = resolvedSchema.hasLimits();
    fullyQualifiedPersistedType_ = javaTypePackageName + "." + javaType;
    simplePersistedType_ = javaType;

    CanonAttributes attr = resolvedSchema.getSchema().getXCanonAttributes();
    
    if(attr != null)
    {
      fullyQualifiedExternalType_ = attr.getJson().getString("javaExternalType", null);
      fullyQualifiedBuilderType_ = packageName + "." + getCamelCapitalizedName() + getCanonIdString() + "Builder";
    }
    else
    {
      fullyQualifiedExternalType_ = null;
      fullyQualifiedBuilderType_ = null;
    }
    
    isInnerClass_ = resolvedSchema.isInnerClass();
    
    isGenerated_ = hasLimits_ || requiresGeneration || !isInnerClass_;
    
    if(isGenerated_)
    {
      fullyQualifiedType_ = packageName + "." + initType(generatorContext, resolvedSchema);
    }
    else if(fullyQualifiedExternalType_ != null)
    {
      fullyQualifiedType_ =  fullyQualifiedExternalType_;
    }
    else
    {
      fullyQualifiedType_ = fullyQualifiedPersistedType_;
    }
  }
  


  @Override
  public String getValue(String args)
  {
    if(isGenerated_)
    {
      return args + ".getValue()";
    }
    else if(fullyQualifiedExternalType_ != null)
    {
      return getBuilderType() + ".to" + simplePersistedType_ + "(" + args + ")";
    }
    else
    {
      return args;
    }
  }
  
  @Override
  public String getPersistedValue(String args)
  {
    if(isGenerated_ && fullyQualifiedExternalType_ != null)
    {
      return getBuilderType() + ".to" + simplePersistedType_ + "(" + args + ".getValue())";
    }
    else
    {
      return getValue(args);
    }
  }
      

  @Override
  public String getConstructor(String args)
  {
    if(isGenerated_)
    {
      return "new " + getType() + "(" + args + ")";
    }
    else if(fullyQualifiedExternalType_ != null)
    {
      return getBuilderType() + ".build(" + args + ")";
    }
    else
    {
      return args;
    }
  }
      
  public String getValueConstructor(String args)
  {
    if(fullyQualifiedExternalType_ != null)
      return getBuilderType() + ".build(" + args + ")" + " /* trace 1 */";
    else
      return args + " /* trace 2 */";
  }
  
  private String initType(Context generatorContext, ResolvedPrimitiveSchema<?> resolvedSchema)
  {
    if(resolvedSchema.getResolvedContainer() == null)
    {
      return getCamelCapitalizedName();
    }
  
    return capitalize(generatorContext.getCanonIdString(), toCamelCase(resolvedSchema.getResolvedContainer().getName())) + "." + getCamelCapitalizedName();
  }

  @Override
  public void resolve(INamespace namespace, Writer writer)
  {
    super.resolve(namespace, writer);
    
    type_ = null;
    builderType_ = null;
  }

  @Override
  public String getType()
  {
    if(type_ == null)
    {
      if(isInnerClass_ && isGenerated_)
      {
        type_ = getOuterClass().getType() + "." + getCamelCapitalizedName();
      }
      else
      {
        type_ = namespace_.resolveImport(fullyQualifiedType_, namespaceWriter_);
      }
    }
    return type_;
  }
  
  @Override
  public boolean getIsTypedef()
  {
    return isGenerated_;
  }
  
  public String getExternalType()
  {
    if(externalType_ == null && fullyQualifiedExternalType_ != null)
    {
      externalType_ = namespace_.resolveImport(fullyQualifiedExternalType_, namespaceWriter_);
    }
    
    return externalType_;
  }
  
  public String getPersistedType()
  {
    if(persistedType_ == null)
    {
      persistedType_ = namespace_.resolveImport(fullyQualifiedPersistedType_, namespaceWriter_);
    }
    
    return persistedType_;
  }
  
  public String getJavaType()
  {
    if(fullyQualifiedExternalType_ != null)
      return getExternalType();
    else
      return getPersistedType();
  }
  
  private String getBuilderType()
  {
    if(builderType_ == null && fullyQualifiedBuilderType_ != null)
    {
      builderType_ = namespace_.resolveImport(fullyQualifiedBuilderType_, namespaceWriter_);
    }
    
    return builderType_;
  }

  @Override
  public void validate(SourceContext sourceContext)
  {
  }
  
  @Override
  public boolean getHasLimits()
  {
    return hasLimits_;
  }

  /**
   * Return the Java type of this schema.
   * 
   * @return the Java type of this schema.
   */
  public String getSimplePersistedType()
  {
    return simplePersistedType_;
  }

  /**
   * Return the fully qualified Java type of this schema.
   * 
   * @return the fully qualified Java type of this schema.
   */
  public final String getFullyQualifiedJavaType()
  {
    return fullyQualifiedPersistedType_;
  }

//  private String initType(Schema entity)
//  {
//
//    switch(entity.getType())
//    {
//      case "number":
//        
//        if(entity.getFormat() != null)
//        {
//          switch(entity.getFormat())
//          {
//            case "int64":   return "Long";
//            case "int32":   return "Integer";
//            case "float":   return "Float";
//            case "double":  return "Double";
//           default:
//              warnBadFormat(entity);
//              return "Double";
//          }
//        }
//        else
//        {
//          setAndAddImport("java.math", "BigDecimal");
//
//          return "BigDecimal";
//        }
//        
//      case "integer":
//        if(entity.getFormat() != null)
//        {
//          switch(entity.getFormat())
//          {
//            case "int64":   return "Long";
//            case "int32":   return "Integer";
//            default:
//              warnBadFormat(entity);
//              return "Long";
//          }
//        }
//        else
//        {
//          setAndAddImport("java.math", "BigInteger");
//
//          return "BigInteger";
//        }
//        
//      case "boolean":
//        if(entity.getFormat() != null)
//        {
//          warnBadFormat(entity);
//        }
//        return "Boolean";
//        
//      case "string":
//        if(entity.getFormat() != null)
//        {
//          switch(entity.getFormat())
//          {
//            case "byte":
//              setAndAddImport("com.symphony.oss.commons.immutable", "ImmutableByteArray");
//              return "ImmutableByteArray";
//              
//            default:
//              warnBadFormat(entity);
//              return "String";
//          }
//        }
//        else
//        {
//          return "String";
//        }
//        
//      default:
//        //return null;
//        throw new CodingFault("Unknown primative type " + entity.getType());
//    }
//  }
//
//  private BigInteger getBigInteger(Schema entity, String name)
//  {
//    switch(entity.getType())
//    {
//      case "number":
//      case "integer":
//        JsonDomNode node = entity.getJson().get(name);
//      
//        if(node == null)
//        {
//          return null;
//        }
//        else if(node instanceof JsonParsedNumber)
//        {
//          return null; //((JsonParsedNumber)node).asBigInteger();
//        }
//        else
//        {
//          throw new SyntaxErrorException("Invalid " + name + " value \"" + node + "\"", node.getContext());
//        }
//    }
//    
//    return null;
//  }
//  
//  private boolean getBoolean(Schema entity, String name)
//  {
//    switch(entity.getType())
//    {
//      case "number":
//      case "integer":
//        JsonDomNode node = entity.getJson().get(name);
//      
//        if(node instanceof IBooleanProvider)
//        {
//          return ((IBooleanProvider)node).asBoolean();
//        }
//    }
//    
//    return false;
//  }
  @Override
  public String getFullyQualifiedType()
  {
    return fullyQualifiedType_;
  }

  @Override
  public IJavaTemplateModel asTemplateModel()
  {
    return this;
  }

  @Override
  public JavaSchemaTemplateModel asSchemaTemplateModel()
  {
    return this;
  }
  
//  @Override
//  public String getConstructPrefix()
//  {
//    return constructPrefix_;
//  }
//
//  @Override
//  public String getConstructSuffix()
//  {
//    return constructSuffix_;
//  }

//  public String getGetValueSuffix()
//  {
//    return getValueSuffix_;
//  }
//
//  public String getGetValuePrefix()
//  {
//    return getValuePrefix_;
//  }
//
//  @Override
//  public String getCopyPrefix()
//  {
//    return "";
//  }
//
//  @Override
//  public String getCopySuffix()
//  {
//    return "";
//  }

//  @Override
//  public String getMinimum()
//  {
//    return null; //minimum_;
//  }
//
//  @Override
//  public BigInteger getMaximum()
//  {
//    return maximum_;
//  }
//
//  @Override
//  public boolean getExclusiveMinimum()
//  {
//    return exclusiveMinimum_;
//  }
//
//  @Override
//  public boolean getExclusiveMaximum()
//  {
//    return exclusiveMaximum_;
//  }

//  public String getFullyQualifiedExternalType()
//  {
//    return fullyQualifiedExternalType_;
//  }
  
  /**
   * Construct an instance of javaType from a string constant.
   * 
   * @param value a string constant.
   * 
   * @return The Java statement to construct an instance of javaType from the given string constant.
   */
  public String constructConstant(String value)
  {
    switch(fullyQualifiedPersistedType_)
    {
      case "java.math.BigInteger":
      case "java.math.BigDecimal":
        return "new " + fullyQualifiedPersistedType_ + "(\"" + value + "\")";
      
      case "java.lang.Long":
        return value + "L";
      
      default:
        return value;
    }
  }
}
