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

import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.symphony.oss.canon2.model.ResolvedSchema;
import com.symphony.oss.canon2.parser.SchemaTemplateModel;

public abstract class JavaSchemaTemplateModel
extends SchemaTemplateModel<
IJavaTemplateModel,
JavaOpenApiTemplateModel,
JavaSchemaTemplateModel
>
implements IJavaTemplateModel
{
//  private static final String[] IMPORTS = new String[] 
//      {
//        "javax.annotation.concurrent.Immutable",
//        "javax.annotation.Nullable",
//        "com.symphony.oss.canon.json.model.JsonObject",
//        "com.symphony.oss.canon.json.model.JsonDomNode"
//      };
  
  Set<String> imports_ = new TreeSet<>();

  private final boolean generateFacade_;
  
  JavaSchemaTemplateModel(ResolvedSchema entity, String name, String identifier, JavaOpenApiTemplateModel model,
      String... templates)
  {
    super(entity, name, identifier, model, templates);
    
//    for(String importString : IMPORTS )
//    {
//      imports_.add(importString);
//    }
//    
//    for(String importString : imports )
//    {
//      imports_.add(importString);
//    }
    
    generateFacade_ = entity.getXCanonFacade() == null ? false : entity.getXCanonFacade();
  }
  
  public List<String> sortImports(List<String> list)
  {
    String[] groups = new String[]
    {
        "java.", "javax.", "org.", "com.", "" 
    };
    Map<String, Set<String>> map = new HashMap<>();
    
    for(String s : list)
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

  public boolean getGenerateFacade()
  {
    return generateFacade_;
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

  public BigInteger getMinimum()
  {
    return null;
  }
  
  public BigInteger getMaximum()
  {
    return null;
  }
  
  public boolean getExclusiveMinimum()
  {
    return false;
  }

  public boolean getExclusiveMaximum()
  {
    return false;
  }
}
