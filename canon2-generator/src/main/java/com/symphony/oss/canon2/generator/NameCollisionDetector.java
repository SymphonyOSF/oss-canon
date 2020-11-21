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

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.symphony.oss.canon.json.ParserErrorException;
import com.symphony.oss.canon.json.model.IJsonDomNodeProvider;
import com.symphony.oss.canon2.core.SourceContext;

public class NameCollisionDetector
{
  private Set<List<String>>     collisionSet_     = new HashSet<>();
  private Set<ParserErrorException>  invalidSet_       = new HashSet<>();
    
  public NameCollisionDetector(Collection<? extends ITemplateModel<?,?,?>> schemas)
  {
    Map<String, List<String>>   camelMap  = new HashMap<>();
    
    for(ITemplateModel<?,?,?> schema : schemas)
    {
      String  name = schema.getIdentifier();
      
      List<String> list = camelMap.get(name);
      
      if(list == null)
      {
        list = new LinkedList<>();
        camelMap.put(name, list);
      }
      list.add("\"" + schema.getName() + "\" at " + schema.getJson().getContext());
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

  public void logCollisions(SourceContext sourceContext, IJsonDomNodeProvider context)
  {
    for(List<String> c : getCollisionSet())
    {
      StringBuilder s = new StringBuilder("The following names would collide when mapped to a Java identifier, user x-canon-java-identifier or x-canon-identifier");
      
      for(String cn : c)
        s.append("\n  " + cn);
      
      sourceContext.error(new ParserErrorException(s.toString(), context));
    }
    
    for(ParserErrorException e : invalidSet_)
    {
      sourceContext.error(e);
    }
  }
}
