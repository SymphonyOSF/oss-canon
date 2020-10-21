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

package com.symphony.oss.canon2.generator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.symphony.oss.canon2.core.ResolvedSchema;
import com.symphony.oss.canon2.core.SourceContext;

class NameCollisionDetector
{
  private static final String[] STD_JAVA_PACKAGES = new String[]
  {
      "java.lang",
      "java.util"
  };

//  private static Logger log_ = LoggerFactory.getLogger(NameCollisionDetector.class);
  
  private Set<List<String>> collisionSet_ = new HashSet<>();
  private Set<String>       invalidSet_   = new HashSet<>();
  
//  public static NameCollisionDetector create(ICanonContext canonContext, URL url, ICanonGenerator<?,?,?,?,?,?,?,?> generator, Map<String, Object> schemaOrReferences, boolean isSchema) throws GenerationException
//  {
//    Map<String, Schema> schemas = new HashMap<>(schemaOrReferences.size());
//    
//    for(Entry<String, Object> entry : schemaOrReferences.entrySet())
//    {
//      if(entry.getValue() instanceof Schema)
//      {
//        schemas.put(entry.getKey(), (Schema)entry.getValue());
//      }
//      else if(entry.getValue() instanceof ReferenceObject)
//      {
//        ReferenceObject ref = (ReferenceObject)entry.getValue();
//        
//        schemas.put(entry.getKey(), canonContext.getSchemaInfo(ref.getAbsoluteUri(url)).getSchema());
//      }
//      else
//      {
//        throw new GenerationException("Unexpected schema or reference " + entry.getValue().getClass());
//      }
//    }
//    
//    return new NameCollisionDetector(generator, schemas, isSchema);
//  }
  
  NameCollisionDetector(ICanonGenerator<?,?,?,?,?,?,?,?> generator, Map<String, ResolvedSchema> schemas, boolean isSchema)
  {
    Map<String, List<String>>   camelMap  = new HashMap<>();
    
    for(Entry<String, ResolvedSchema> entry : schemas.entrySet())
    {
      boolean initial   = isSchema;
      String  name      = generator.getIdentifierName(entry.getKey(), entry.getValue().getSchema());
      
      List<String> list = camelMap.get(name);
      
      if(list == null)
      {
        list = new LinkedList<>();
        camelMap.put(name, list);
      }
      list.add("\"" + entry.getKey() + "\" at " + entry.getValue().getSchema().getSourceLocation());
      
      for(char c : name.toCharArray())
      {
        boolean check = initial ? Character.isJavaIdentifierStart(c) : Character.isJavaIdentifierPart(c);
        
        initial = false;
        
        if(!check)
        {
          invalidSet_.add((isSchema ? "Schema" : "Attribute") + " \"" + entry.getKey() + "\" at " + entry.getValue().getSchema().getSourceLocation() + " (maps to " + name + ")");
          break;
        }
      }
      
      if(isSchema)
      {
        for(String packageName : STD_JAVA_PACKAGES)
        {
          try
          {
            Class.forName(packageName + "." +  name);
            invalidSet_.add("Schema \"" + entry.getKey() + "\" at " + entry.getValue().getSchema().getSourceLocation() + " (maps to " + name + ")");
            break;
          }
          catch (ClassNotFoundException e)
          {
            // This is ok
          }
        }
      }
    }
    
    for(Entry<String, List<String>> entry : camelMap.entrySet())
    {
      if(entry.getValue().size() > 1)
        collisionSet_.add(entry.getValue());
    }
  }

  Set<List<String>> getCollisionSet()
  {
    return collisionSet_;
  }

  void logCollisions(SourceContext sourceContext)
  {
    for(List<String> c : getCollisionSet())
    {
      sourceContext.error("The following names would collide when mapped to a Java identifier, user x-canon-java-identifier or x-canon-identifier");
      
      for(String cn : c)
        sourceContext.error("  " + cn);
    }
    
    for(String s : invalidSet_)
    {
      sourceContext.error(s + " is not a valid Java identifier, user x-canon-java-identifier or x-canon-identifier");
    }
  }
}
