/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon.generator.typescript;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import com.symphony.oss.canon.CanonGenerator;
import com.symphony.oss.canon.model.AbstractSchema;
import com.symphony.oss.canon.model.ArraySchema;
import com.symphony.oss.canon.model.Component;
import com.symphony.oss.canon.model.ModelElement;
import com.symphony.oss.canon.model.ObjectSchema;

public class TypescriptGenerator extends CanonGenerator
{
  // IDs in the model
  static final String TS_EXTERNAL_TYPE             = "tsExternalType";
  static final String TS_EXTERNAL_PACKAGE          = "tsExternalPackage";
  static final String TS_PACKAGE_NAME              = "tsPackageName";

  // IDs of generated attributes
  static final String PREFIX                       = "ts";
  static final String PACKAGE_NAME                 = PREFIX + "PackageName";
  static final String IMPORTS                      = PREFIX + "Imports";
  static final String CLASS_NAME                   = PREFIX + "ClassName";
  static final String GENERATED_BUILDER_CLASS_NAME = PREFIX + "GeneratedBuilderClassName";
  
  /**
   * Constructor.
   */
  public TypescriptGenerator()
  {
    super("ts");
    
    register("Model", (dataModel, modelElement) -> {
      dataModel.put("tsElement", modelElement.getCamelCapitalizedName() + " " + modelElement.getClass());
      
      setImports(dataModel, modelElement);
      setElementType(dataModel, modelElement);
     
      
    });
    
    register("Object", (dataModel, modelElement) -> {
      dataModel.put("tsElement", modelElement.getCamelCapitalizedName() + " " + modelElement.getClass());
      
      setImports(dataModel, modelElement);
      setElementType(dataModel, modelElement);
     
      
    });
    
    register("AllOf", (dataModel, modelElement) -> {
      dataModel.put("tsElement", modelElement.getCamelCapitalizedName() + " " + modelElement.getClass());
      
      setImports(dataModel, modelElement);
      setElementType(dataModel, modelElement);
     
      
    });
    
    register("TypeDef", (dataModel, modelElement) -> {
      setImports(dataModel, modelElement);
      setTypeDefElementType(dataModel, modelElement);
     
      
    });
  }

  private void setTypeDefJavaClassName(Map<String, Object> dataModel, ModelElement modelElement)
  {
    String externalType = modelElement.getAttributes().get(TS_EXTERNAL_TYPE);

    if(externalType != null)
    {
      dataModel.put("isExternal", Boolean.TRUE);
      dataModel.put(CLASS_NAME, externalType);
      
      // TODO: fix get fieldType
      dataModel.put("tsFullyQualifiedClassName", modelElement.getAttributes().get("tsExternalPackage") + "." + dataModel.get("fieldType"));
          
      dataModel.put(GENERATED_BUILDER_CLASS_NAME, modelElement.getCamelCapitalizedName() + "Builder");
      // dataModel.put("tsGeneratedBuilderFullyQualifiedClassName", dataModel.get("javaFacadePackage}.${javaGeneratedBuilderClassName}">
    }
    else
    {
      if(modelElement.getEnum() != null)
      {
        dataModel.put("tsGeneratedBuilderClassName", modelElement.getCamelCapitalizedName());
        //dataModel.put("tsGeneratedBuilderFullyQualifiedClassName"${javaGenPackage}.${javaGeneratedBuilderClassName}">
        dataModel.put(CLASS_NAME, modelElement.getCamelCapitalizedName());
      }
      else
      {
        dataModel.put("tsGeneratedBuilderClassName", modelElement.getCamelCapitalizedName() + ".newBuilder()");
        dataModel.put(CLASS_NAME, modelElement.getCamelCapitalizedName());
//              dataModel.put("tsFullyQualifiedClassName="${javaFacadePackage}.${fieldType}">
      }
    }
  }

  private void setTypeDefElementType(Map<String, Object> dataModel, ModelElement model)
  {
    // first set javaElementClassName which is the basic Java type which stores simple values.
    dataModel.put("tsElementClassName", getJavaClassName(model.getElementComponent(), model.getElementComponent()));
    dataModel.put("tsElementFieldClassName", getJavaClassName(model.getElementSchema(), model.getElementSchema()));
    
    // now set javaFieldClassName
    setTypeDefJavaClassName(dataModel, model);
  }

  private void setElementType(Map<String, Object> dataModel, ModelElement model)
  {
    // first set javaElementClassName which is the basic Java type which stores simple values.
    dataModel.put("tsElementClassName", getJavaClassName(model.getElementComponent(), model.getElementComponent()));
    dataModel.put("tsElementFieldClassName", getJavaClassName(model.getElementSchema(), model.getElementSchema()));
    
    // now set javaFieldClassName
    dataModel.put("tsFieldClassName", getJavaClassName(model.getBaseSchema(), model.getElementSchema()));
    
    // now set the javaClassName

//    setClassName(model javaFieldClassName);
//    
//    // now set decorator attributes
//    decorate(model);
//    setDescription(model);
  }

  private String getJavaClassName(ModelElement model, ModelElement element)
  {
    if(model.getIsTypeDef())
    {
      String externalType = model.getAttributes().get("tsExternalType");

      if(externalType != null)
      {
        return externalType;
      }
    }
    
    if(model.getIsComponent())
    {
      return model.getCamelCapitalizedName();
    }
    
    if(model.getIsArraySchema())
    {
      String cardinality = ((ArraySchema)model).getCardinality();
      switch(cardinality)
      {
        case "SET":
          return "Set<" + getJavaClassName(element, element) + ">";
      
          default:
            return getJavaClassName(element, element) + "[]";
      }
    }

    switch(model.getElementType())
    {
      case "Integer":
        switch(model.getFormat())
        {
          case "int32":
            return "number";
        
            default:
              return "bigint";
        }
    
      case "Double":
        return "number";
   
      case "String":
        switch(model.getFormat())
        {
          case "byte":
            return "Readonly<Uint8Array>";
        
          default:
            return "String";
        }
      case "Boolean":
        return "Boolean";

      default:
        return model.getCamelCapitalizedName();
    }
  }

  private void setImports(Map<String, Object> dataModel, ModelElement modelElement)
  {
    Set<String> localImports = new TreeSet<>();
    Map<String, Set<String>> packageImportMap_ = new HashMap<>();
    
    for(AbstractSchema type : modelElement.getReferencedTypes())
    {
      Map<String, Object> fieldDataModel = type.getDataModel(this);
      Object externalType = fieldDataModel.get(TS_EXTERNAL_TYPE);
      
      if(externalType != null)
      {
        localImports.add("import { " + externalType + " } from \"./"  + fieldDataModel.get(TS_EXTERNAL_PACKAGE) + "\";");
      }
      else if(type.getModel() == modelElement.getModel())
      {
        localImports.add("import { " + type.getCamelCapitalizedName() + " } from \"./"  + type.getCamelCapitalizedName() + "\";");
      }
      else
      {
        String packageName = type.getModel().getModelMap().get(TS_PACKAGE_NAME);
        Set<String> packageImports = packageImportMap_.get(packageName);
        
        if(packageImports == null)
        {
          packageImports = new TreeSet<>();
          packageImportMap_.put(packageName, packageImports);
        }
        
        packageImports.add(type.getCamelCapitalizedName());
      }
      System.err.println(type);
    }
    
    for(Entry<String, Set<String>> entry : packageImportMap_.entrySet())
    {
      StringBuilder s = null;
      for(String name : entry.getValue())
      {
        if(s == null)
          s = new StringBuilder("import {");
        else
          s.append(",\n");
        
        s.append("    ");
        s.append(name);
      }

      if(s == null)
        s = new StringBuilder("import {");
      
      s.append("\n  } from \"");
      s.append(entry.getKey());
      s.append("\";");
      
      localImports.add(s.toString());
    }
    
    dataModel.put(IMPORTS, localImports);
  }
}
