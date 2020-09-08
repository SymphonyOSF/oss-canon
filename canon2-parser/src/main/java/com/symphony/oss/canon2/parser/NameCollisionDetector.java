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

package com.symphony.oss.canon2.parser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class NameCollisionDetector
{
  private static Logger log_ = LoggerFactory.getLogger(NameCollisionDetector.class);
  
  private Set<List<String>> collisionSet_ = new HashSet<>();
  
  NameCollisionDetector(ICanonGenerator<?,?,?,?,?,?,?> generator, Map<String, IResolvedSchema> schemas)
  {
    Map<String, List<String>>   camelMap  = new HashMap<>();
    
    for(Entry<String, IResolvedSchema> entry : schemas.entrySet())
    {
      String name = generator.getIdentifierName(entry.getKey(), entry.getValue());
      
      List<String> list = camelMap.get(name);
      
      if(list == null)
      {
        list = new LinkedList<>();
        camelMap.put(name, list);
      }
      list.add(entry.getKey());
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

  void logCollisions()
  {
    for(List<String> c : getCollisionSet())
    {
      log_.error("The following names would collide when mapped to a Java identifier, user x-canon-java-identifier or x-canon-identifier");
      
      for(String cn : c)
        log_.error("  " + cn);
    }
  }

}
