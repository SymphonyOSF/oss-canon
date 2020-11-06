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
import com.symphony.oss.canon2.generator.SchemaTemplateModel;

public abstract class JavaSchemaTemplateModel
extends SchemaTemplateModel<
IJavaTemplateModel,
JavaOpenApiTemplateModel,
JavaSchemaTemplateModel
>
implements IJavaTemplateModel
{ 
  private static final List<JavaSchemaTemplateModel> EMPTY_SCHEMAS = ImmutableList.of();
  private static final List<JavaFieldTemplateModel>   EMPTY_FIELDS = ImmutableList.of();

  /** Set of imports any class implementing this schema needs */
  private Set<String> imports_ = new TreeSet<>();
  
  /** Package Name of import */
  private String packageName_;
  /** import needed by classes which reference this schema */
  private String import_;

  private final boolean generateFacade_;
  private final boolean generateBuilderFacade_;
  
  JavaSchemaTemplateModel(ResolvedSchema resolvedSchema, SchemaTemplateModelType schemaType, IdentifierAndImport identifierAndImport, JavaOpenApiTemplateModel model,
      List<String> templates)
  {
    super(resolvedSchema, schemaType, identifierAndImport.identifier_, model, templates);

    if(identifierAndImport.package_ != null)
    {
      String fullyQualifiedImport = identifierAndImport.package_ + "." + identifierAndImport.type_;
      
      setImport(fullyQualifiedImport);
      if(isExternal() || identifierAndImport.add_)
      {
        addImport(fullyQualifiedImport);
      }
    }
    
    generateFacade_ = resolvedSchema.getSchema().getXCanonFacade() == null ? false : resolvedSchema.getSchema().getXCanonFacade();
    generateBuilderFacade_ = resolvedSchema.getSchema().getXCanonBuilderFacade() == null ? false : resolvedSchema.getSchema().getXCanonBuilderFacade();
  }
  
  static class IdentifierAndImport
  {
    final String  package_;
    final String  type_;
    final String  identifier_;
    final boolean add_;

    IdentifierAndImport(String package1, String identifier, String type, boolean add)
    {
      package_ = package1;
      identifier_ = capitalize(toCamelCase(identifier));
      type_ = type;
      add_ = add;
    }
    
//    IdentifierAndImport(String package1, String identifier)
//    {
//      package_ = package1;
//      identifier_ = capitalize(toCamelCase(identifier));
//      type_ = identifier_;
//      add_ = false;
//    }
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
  
  void setImport(String packageName, String camelCapitalizedName)
  {
    packageName_ = packageName;
    import_ = packageName + "." + camelCapitalizedName;
  }

  void addImport(String fullyQualifiedImport)
  {
    imports_.add(fullyQualifiedImport);
  }

  public String getPackageName()
  {
    return packageName_;
  }

  public String getImport()
  {
    return import_;
  }

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

  public String getConstructPrefix()
  {
    return "/*ConstructPrefix*/";
  }

  public String getConstructSuffix()
  {
    return "";
  }

  public abstract String getJsonNodeType();

  public abstract String getFullyQualifiedJsonNodeType();
  
  public abstract String getCopyPrefix();

  public abstract String getCopySuffix();

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
}
