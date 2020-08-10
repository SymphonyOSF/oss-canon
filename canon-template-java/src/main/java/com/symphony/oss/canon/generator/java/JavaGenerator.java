/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon.generator.java;

import java.util.Map;

import com.symphony.oss.canon.Canon;
import com.symphony.oss.canon.CanonGenerator;
import com.symphony.oss.canon.model.Array;
import com.symphony.oss.canon.model.IPathNameConstructor;
import com.symphony.oss.canon.model.ModelElement;

public class JavaGenerator extends CanonGenerator
{
  // IDs in the model
  static final String JAVA_EXTERNAL_TYPE             = "javaExternalType";
  static final String JAVA_EXTERNAL_PACKAGE          = "javaExternalPackage";
  static final String JAVA_PACKAGE_NAME              = "javaPackageName";

  // IDs of generated attributes
  static final String PREFIX                       = "java";
  static final String PACKAGE_NAME                 = PREFIX + "PackageName";
  static final String IMPORTS                      = PREFIX + "Imports";
  static final String CLASS_NAME                   = PREFIX + "ClassName";
  static final String GENERATED_BUILDER_CLASS_NAME = PREFIX + "GeneratedBuilderClassName";
  
  private IPathNameConstructor templatePathBuilder_ = new JavaPathNameConstructor(Canon.JAVA_GEN_PACKAGE);
  private IPathNameConstructor proformaPathBuilder_ = new JavaPathNameConstructor(Canon.JAVA_FACADE_PACKAGE);
  
  /**
   * Constructor.
   */
  public JavaGenerator()
  {
    super("java");
    
    register(Array.class, (data, model) -> {
        data.put("tsElement", model.getCamelCapitalizedName() + " " + model.getClass());
      
        
        data.put("baseType", getBaseType(model.getElementSchema()));
        
        // First set modelJavaClassName, modelJavaFullyQualifiedClassName, and modelIsGenerated
  
        String externalType = model.getAttributes().get(JAVA_EXTERNAL_TYPE);
  
        if(externalType != null)
        {
          data.put("javaClassName", externalType);
          data.put("javaFullyQualifiedClassName", model.getAttributes().get("javaExternalPackage") + "." + externalType);
          data.put("isGenerated", Boolean.FALSE);
        }
        else
        {
          data.put("javaClassName", model.getCamelCapitalizedName());
          data.put("javaFullyQualifiedClassName", data.get("javaFacadePackage") + "." + model.getCamelCapitalizedName());
          data.put("isGenerated", Boolean.TRUE);
        }
        // Set javaElementClassName which is the basic Java type which stores simple values.
        String elementClassName = getJavaClassName(model.getElementSchema());
        data.put("javaElementClassName", elementClassName);
        // now set javaFieldClassName and decorator attributes
        
        String javaCardinality = getJavaCardinality(model.getCardinality());
        data.put("javaFieldClassName", javaCardinality + "<" + elementClassName + ">");
        data.put("javaCardinality", javaCardinality);
    });
  }

  private String getJavaCardinality(String cardinality)
  {
    switch(cardinality)
    {
      case "SET":
        return "Set";
      
      default:
        return "List";
    }
  }

  private String getTypeDefJavaClassName(ModelElement model)
  {
    String externalType = model.getAttributes().get(JAVA_EXTERNAL_TYPE);
    
    if(externalType != null)
    {
      return externalType;
    }
    else
    {
      return model.getCamelCapitalizedName();
    }
  }
  
  private String getJavaClassName(ModelElement model)
  {
    if(model.getIsTypeDef())
    {
      return getTypeDefJavaClassName(model);
    }
    if(model.getIsComponent())
    {
      return model.getCamelCapitalizedName();
    }
    
    return getBaseType(model);
  }

  private String getBaseType(ModelElement model)
  {
    switch(model.getElementType())
    {
      case "Integer":
        switch(model.getFormat())
        {
          case "int32":
            return "Integer";
            
           default:
            return "Long";
        }
        
      case "Double":
        switch(model.getFormat())
        {
          case "float":
            return "Float";
          
          default:
            return "Double";
        }
      
      case "String":
        switch(model.getFormat())
        {
          case "byte":
            return "ImmutableByteArray";
          
          default:
            return "String";
        }
      
      case "Boolean":
        return "Boolean";
      
      default:
        return "I" + model.getCamelCapitalizedName();
    }
  }

  @Override
  public IPathNameConstructor getTemplatePathNameConstructor()
  {
    return templatePathBuilder_;
  }
  
  @Override
  public IPathNameConstructor getProformaPathNameConstructor()
  {
    return proformaPathBuilder_;
  }

//  @Override
//  public void populateDataModel(Map<String, Object> dataModel, ModelElement modelElement)
//  {
//    dataModel.put("javaElement", modelElement.getCamelCapitalizedName());
//  }
}
