/**
 * Proforma implementation:
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
 *
 *----------------------------------------------------------------------------------------------------
 * Generated from
 *    Input source         canon.json
 *    Generator groupId    org.symphonyoss.s2.canon
 *              artifactId canon2-generator-java
 *    Template name        proforma/Object/_.java.ftl
 *    At                   2020-10-08 13:02:45 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.model;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableMap;
import com.symphony.oss.canon.json.IParserContext;
import com.symphony.oss.canon.json.model.JsonObject;


/**
 * Facade for Object  PropertiesObject canon
 * Object PropertiesObject
 * Generated from PropertiesObject
 */
@Immutable
public class PropertiesObject extends PropertiesObjectEntity
{
  private final ImmutableMap<String, Object> properties_;
  
  /**
   * Constructor.
   * 
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  protected PropertiesObject(Initialiser initialiser)
  {
    super(initialiser);
    
    Map<String, Object> properties = new HashMap<>();
    
    for(String name : getCanonUnknownKeys())
    {
      JsonObject json = getJson().getObject(name);
      if(json.get("$ref") == null)
        properties.put(name, Schema.FACTORY.newInstance(json, initialiser.getModelRegistry()));
      else
        properties.put(name, ReferenceObject.FACTORY.newInstance(json, initialiser.getModelRegistry()));
    }
    
    properties_ = ImmutableMap.copyOf(properties);
  }

  public ImmutableMap<String, Object> getProperties()
  {
    return properties_;
  }
  
  public IParserContext getNameContext(String name)
  {
    return getJson().getNameContext(name);
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */