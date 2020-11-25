/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon2.generator.java;

import java.util.List;

import com.symphony.oss.canon2.core.ResolvedPrimitiveSchema;
import com.symphony.oss.canon2.core.SourceContext;
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
  private final String                       javaType_;
  private final String                       type_;
//  private final BigInteger                   minimum_;
//  private final BigInteger                   maximum_;
//  private final boolean                      exclusiveMinimum_;
//  private final boolean                      exclusiveMaximum_;
//  private final String                       jsonNodeType_;
  private final String                       constructPrefix_;
  private final String                       fullyQualifiedConstructPrefix_;
  private final String                       constructSuffix_;
  private final String                       getValuePrefix_;
  private final String                       getValueSuffix_;
  private final String                       externalPackage_;
  private final String                       externalType_;

  private final boolean hasLimits_;

  private final String primitiveType_;

  JavaPrimitiveSchemaTemplateModel(JavaGenerator.Context generatorContext, String identifier, ResolvedPrimitiveSchema<?> resolvedSchema, boolean requiresGeneration, String packageName, String javaType, JavaOpenApiTemplateModel model, List<String> templates)
  { 
    super(generatorContext, identifier, resolvedSchema, resolvedSchema.getSchemaType(), model, templates);
    
    hasLimits_ = resolvedSchema.hasLimits();
    javaType_ = javaType;

    String constructPrefix = null;
    String fullyQualifiedConstructPrefix = null;
    String getValuePrefix = "";
    String getValueSuffix = "";
    
    if(!resolvedSchema.isInnerClass() || hasLimits_ || requiresGeneration) //resolvedSchema.isGenerated())
    {
      primitiveType_ = javaType_;
      type_ = initType(generatorContext, identifier, resolvedSchema) ;
//      type_ = getCamelCapitalizedName();
      
//    if(isExternal())
//    {
//      addImport(packageName + "." + getCamelCapitalizedName());
//    }
    
      setImport(packageName,  getCamelCapitalizedName());

      constructPrefix = "new " + getType() + "(";
      fullyQualifiedConstructPrefix = "new " + getImport() + "(";
      getValueSuffix = ".getValue()";
    }
    else
    {
      primitiveType_ = null;
      type_ = getJavaType();
    }
    

    
//    imports_.add("com.symphony.oss.commons.type.provider.I" + javaType_ + "Provider");
//    switch(resolvedSchema.getSchema().getType())
//    {
//      case "string":
//        jsonNodeType_ = "JsonString";
//        break;
//        
//      case "boolean":
//        jsonNodeType_ = "JsonBoolean";
//        break;
//        
//      default:
//        jsonNodeType_ = "JsonParsedNumber";
//    }
    
    
    
    CanonAttributes attr = resolvedSchema.getSchema().getXCanonAttributes();
    
    if(attr != null)
    {
      externalPackage_ = attr.getJson().getString("javaExternalPackage", "");
      externalType_ = attr.getJson().getString("javaExternalType", null);
      constructPrefix = getCamelCapitalizedName() + getCanonIdString() + "Builder.build(";
      getValuePrefix = getCamelCapitalizedName() + getCanonIdString() + "Builder.to" + javaType_ + "(";
      getValueSuffix = ")";
      setAndAddImport(externalPackage_, externalType_);
    }
    else
    {
      externalPackage_ = "";
      externalType_ = null;
    }
    
//    if(!valueMap.isEmpty())
//      constructPrefix = "new " + getType() + "(";
    
//    for(String template : templates)
//    {
//      switch(template)
//      {
//        case "TypeDef":
////          imports_.add("com.symphony.oss.canon2.runtime.java.TypeDef");
//          constructPrefix = "new " + getType() + "(";
//          break;
//      }
//    }
    
    
    if(constructPrefix == null)
    {
      fullyQualifiedConstructPrefix_ = constructPrefix_ = constructSuffix_ = "";
    }
    else
    {
      fullyQualifiedConstructPrefix_  = fullyQualifiedConstructPrefix;
      constructPrefix_                = constructPrefix;
      constructSuffix_                = ")";
    }
    getValuePrefix_ = getValuePrefix;
    getValueSuffix_ = getValueSuffix;
  }
  
  private String initType(Context generatorContext, String identifier, ResolvedPrimitiveSchema<?> resolvedSchema)
  {
    if(resolvedSchema.getResolvedContainer() == null)
    {
      return getCamelCapitalizedName();
    }
  
    return capitalize(generatorContext.getCanonIdString(), toCamelCase(resolvedSchema.getResolvedContainer().getName())) + "." + getCamelCapitalizedName();
  }

  @Override
  public void validate(SourceContext sourceContext)
  {
  }

  @Override
  public String getQuotedName()
  {
    return javaType_;
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
  public final String getJavaType()
  {
    return javaType_;
  }

  /**
   * Return the primitive type.
   * 
   * The Primitive Type is the Java type if this is an inline checked type, otherwise it is null.
   * 
   * @return the primitive type.
   */
  public String getPrimitiveType()
  {
    return primitiveType_;
  }

  @Override
  public String getFullyQualifiedJsonNodeType()
  {
    return "com.symphony.oss.canon.json.model." + getJsonNodeType();
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
  public String getType()
  {
    return type_;
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

  @Override
  public String getConstructor(boolean fullyQualified, String args)
  {
    String prefix = fullyQualified ? fullyQualifiedConstructPrefix_ : constructPrefix_;
    
    if(prefix.length()==0)
      return args;
    
    return prefix + args + constructSuffix_;
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

  public String getGetValueSuffix()
  {
    return getValueSuffix_;
  }

  public String getGetValuePrefix()
  {
    return getValuePrefix_;
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

  public String getExternalPackage()
  {
    return externalPackage_;
  }

  public String getExternalType()
  {
    return externalType_;
  }
  
  /**
   * Construct an instance of javaType from a string constant.
   * 
   * @param value a string constant.
   * 
   * @return The Java statement to construct an instance of javaType from the given string constant.
   */
  public String constructConstant(String value)
  {
    switch(javaType_)
    {
      case "BigInteger":
      case "BigDecimal":
        return "new " + javaType_ + "(\"" + value + "\")";
      
      case "Long":
        return value + "L";
      
      default:
        return value;
    }
  }
}
