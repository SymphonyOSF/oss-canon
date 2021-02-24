/*
 *
 *
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.symphony.oss.canon2.generator.java;

import java.io.Writer;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.google.common.collect.ImmutableList;
import com.symphony.oss.canon2.core.ResolvedSchema;
import com.symphony.oss.canon2.core.SchemaTemplateModelType;
import com.symphony.oss.canon2.generator.INamespace;
import com.symphony.oss.canon2.generator.SchemaTemplateModel;

/**
 * Implementation of SchemaTemplateModel for the Java generator.
 * 
 * @author Bruce Skingle
 *
 */
public abstract class JavaSchemaTemplateModel
extends SchemaTemplateModel<
IJavaTemplateModel,
JavaOpenApiTemplateModel,
JavaSchemaTemplateModel
>
implements IJavaTemplateModel
{ 

  static final List<String> TYPEDEF_TEMPLATES            = ImmutableList.of("TypeDef");
  static final List<String> ENUM_TEMPLATES               = ImmutableList.of("Enum");
  static final List<String> OBJECT_TEMPLATES             = ImmutableList.of("Object");
  static final List<String> ARRAY_TEMPLATES              = ImmutableList.of("Array");
  static final List<String> MODEL_TEMPLATES              = ImmutableList.of("Model");
  
  private static final List<JavaSchemaTemplateModel> EMPTY_SCHEMAS = ImmutableList.of();
  private static final List<JavaFieldTemplateModel>   EMPTY_FIELDS = ImmutableList.of();
  

  static final String[] STD_JAVA_PACKAGES = new String[]
  {
      "java.lang",
      "java.util"
  };
  
  protected INamespace                namespace_;
  protected Writer                    namespaceWriter_;

  private final String                               packageName_;
  private final boolean                              generateFacade_;
  private final boolean                              generateBuilderFacade_;
  private final IJavaTemplateModel                   outerClass_;

  /** Set of imports any class implementing this schema needs */
  @Deprecated
  private Set<String> imports_ = new TreeSet<>();
  
  /** Package Name of import */
  @Deprecated
  private String LEGACYpackageName_;
  /** import needed by classes which reference this schema */
  @Deprecated
  private String import_;
  private String jsonNodeType_;

  
  JavaSchemaTemplateModel(JavaGenerator.Context generatorContext, 
      String identifier, ResolvedSchema<?> resolvedSchema,  String packageName,
      SchemaTemplateModelType schemaType, JavaOpenApiTemplateModel model, IJavaTemplateModel outerClass, List<String> templates)
  {
    super(generatorContext, identifier, resolvedSchema, schemaType, model, templates);
    
    packageName_            = packageName;
    generateFacade_         = resolvedSchema.getSchema().getXCanonFacade() == null ? false : resolvedSchema.getSchema().getXCanonFacade();
    generateBuilderFacade_  = resolvedSchema.getSchema().getXCanonBuilderFacade() == null ? false : resolvedSchema.getSchema().getXCanonBuilderFacade();
    outerClass_ = outerClass;
  }
  
//  static class IdentifierAndImport
//  {
//    final String  package_;
//    final String  type_;
//    final String  identifier_;
//    final boolean add_;
//
//    IdentifierAndImport(String package1, String identifier, String type, boolean add)
//    {
//      package_ = package1;
//      identifier_ = capitalize(toCamelCase(identifier));
//      type_ = type;
//      add_ = add;
//    }
//    
////    IdentifierAndImport(String package1, String identifier)
////    {
////      package_ = package1;
////      identifier_ = capitalize(toCamelCase(identifier));
////      type_ = identifier_;
////      add_ = false;
////    }
//  }
  
  @Override
  public String getPackageName()
  {
    return packageName_;
  }

  public IJavaTemplateModel getOuterClass()
  {
    return outerClass_;
  }

  public boolean getIsObjectType()
  {
    return false;
  }
  
  static String getIdentifier(JavaGenerator.Context generatorContext, ResolvedSchema<?> resolvedSchema)
  {
    if(resolvedSchema.isInnerClass())
      return generatorContext.getJavaIdentifier(resolvedSchema, true, false);
    

    return generatorContext.getJavaIdentifier(resolvedSchema, true, true);
  }

  void setAndAddImport(String packageName, String camelCapitalizedName)
  {
    setImport(packageName, camelCapitalizedName);
    addImport(import_);
  }
  
  void setImport(String fullyQualifiedName)
  {
    if(fullyQualifiedName == null)
      return;
      
    int i = fullyQualifiedName.lastIndexOf('.');
    
    setImport(fullyQualifiedName.substring(0, i), fullyQualifiedName.substring(i+1));
  }
  
  @Deprecated
  void setImport(String packageName, String camelCapitalizedName)
  {
    LEGACYpackageName_ = packageName;
    import_ = packageName + "." + camelCapitalizedName;
  }

  @Deprecated
  void addImport(String fullyQualifiedImport)
  {
    if(fullyQualifiedImport != null)
      imports_.add(fullyQualifiedImport);
  }

  /**
   * Return the fully qualified Java package name of this type.
   * 
   * @return the fully qualified Java package name of this type.
   */
  public String getLEGACYPackageName()
  {
    return LEGACYpackageName_;
  }

  // not called as far as I can tell
  public String getImport()
  {
    return import_;
  }

  /**
   * Sort and de-duplicate the given list of imports.
   * 
   * @param list        A list of fully qualified java classes to import.
   * @param genPackage  The package of the current class to filter unneeded imports.
   * 
   * @return  A sorted and de-duplicated list of import statements.
   */
  public List<String> sortImports(List<String> list, String genPackage)
  {
    String[] groups = new String[]
    {
        "java.", "javax.", "org.", "com.", "" 
    };
    Map<String, Set<String>> map = new HashMap<>();
    
    for(String s : list)
    {
      if(needToImport(s, genPackage))
      {
        for(String group : groups)
        {
          if(s.startsWith(group))
          {
            Set<String> set = map.get(group);
            
            if(set == null)
            {
              set = new TreeSet<>();
              map.put(group,  set);
            }
            
            set.add(s);
            break;
          }
        }
      }
    }
    
    List<String> result = new LinkedList<>();
    
    for(String group : groups)
    {
      Set<String> set = map.get(group);
      
      if(set != null)
      {
        for(String s : set)
        {
          result.add("import " + s + ";");
        }
        result.add("");
      }
    }
    
    return result;
  }

  private boolean needToImport(String s, String genPackage)
  {
    int i = s.lastIndexOf('.');
    
    if(i == -1)
      return true;
    
    return !s.substring(0, i).equals(genPackage);
  }

  public boolean getGenerateFacade()
  {
    return generateFacade_;
  }

  public boolean getGenerateBuilderFacade()
  {
    return generateBuilderFacade_;
  }

  @Override
  public IJavaTemplateModel asTemplateModel()
  {
    return this;
  }

  @Override
  public Set<String> getImports()
  {
    return imports_;
  }
  
  /**
   * Return a construction statement with the given arguments.
   * 
   * @param args            The parameters for the constructor call.
   * 
   * @return A statement to construct an instance of this type.
   */
  @Override
  public String getConstructor(String args)
  {
    return args;
  }
  
  public String getValue(String args)
  {
    return args;
  }
  
  public String getPersistedValue(String args)
  {
      return getValue(args);
  }
  
  public String getCopy(String args)
  {
    return args;
  }
  
  public boolean getIsTypedef()
  {
    return false;
  }

//  public final String getConstructPrefix()
//  {
//    return "/*ConstructPrefix*/";
//  }
//
//  public final String getConstructSuffix()
//  {
//    return "";
//  }
  
  @Override
  public void resolve(INamespace namespace, Writer writer)
  {
    namespace_ = namespace;
    namespaceWriter_ = writer;
    
    jsonNodeType_  = null;
  }

  protected abstract String getFullyQualifiedJsonNodeType();
  
  /**
   * Return the imported Json Node type for the serialised form of this entity.
   * 
   * @return the imported Json Node type for the serialised form of this entity.
   */
  public String getJsonNodeType()
  {
    if(jsonNodeType_ == null)
      jsonNodeType_ = namespace_.resolveImport(getFullyQualifiedJsonNodeType(), namespaceWriter_);
    
    return jsonNodeType_;
  }
  
//  public abstract String getCopyPrefix();
//
//  public abstract String getCopySuffix();

  /**
   * Return the statement to create an empty instance of the mutable form of this type for use in a builder.
   * 
   * @return the statement to create an empty instance of the mutable form of this type for use in a builder.
   */
  public String getBuilderTypeNew()
  {
    return "";
  }

//  public BigInteger getMinimum()
//  {
//    return null;
//  }
//  
//  public BigInteger getMaximum()
//  {
//    return null;
//  }
//  
//  public boolean getExclusiveMinimum()
//  {
//    return false;
//  }
//
//  public boolean getExclusiveMaximum()
//  {
//    return false;
//  }

  public Collection<JavaSchemaTemplateModel> getInnerClasses()
  {
    return EMPTY_SCHEMAS;
  }
  
  public Collection<JavaFieldTemplateModel> getFields()
  {
    return EMPTY_FIELDS;
  }
  
  public boolean getIsEnum()
  {
    return false;
  }
}
