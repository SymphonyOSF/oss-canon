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
import java.util.Map.Entry;

import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableMap;
import com.symphony.oss.canon2.core.CanonModelContext;
import com.symphony.oss.canon2.core.GenerationException;
import com.symphony.oss.canon2.core.ResolvedOpenApiObject;
import com.symphony.oss.canon2.core.ResolvedPropertiesObject;
import com.symphony.oss.canon2.core.SourceContext;
import com.symphony.oss.canon2.runtime.java.Entity;


/**
 * Facade for Object  SchemasObject canon
 * Object SchemasObject
 * Generated from SchemasObject
 */
@Immutable
public class SchemasObject extends SchemasObjectEntity
{
  private final ImmutableMap<String, Schema> schemas_;
  
  /**
   * Constructor.
   * 
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  protected SchemasObject(Initialiser initialiser)
  {
    super(initialiser);
    
    Map<String, Schema> schemas = new HashMap<>();
    
    for(String name : getCanonUnknownKeys())
    {
      schemas.put(name, Schema.FACTORY.newInstance(getJson().getObject(name), initialiser.getModelRegistry()));
    }
    
    schemas_ = ImmutableMap.copyOf(schemas);
  }

  public ImmutableMap<String, Schema> getSchemas()
  {
    return schemas_;
  }
  
  public Entity get(String[] parts, int index)
  {
    Schema schema = schemas_.get(parts[index]);
    
    if(schema == null)
      throw new IllegalArgumentException("No path element " + parts[index]);
    else
      return schema;
  }

  public ResolvedPropertiesObject.SingletonBuilder link(ResolvedOpenApiObject.SingletonBuilder openApiObjectBuilder, CanonModelContext modelContext, SourceContext sourceContext, String uri) throws GenerationException
  {
    ResolvedPropertiesObject.SingletonBuilder builder = new ResolvedPropertiesObject.SingletonBuilder();
    
    for(Entry<String, Schema> entry : getSchemas().entrySet())
    {
      builder.with(entry.getKey(),
          modelContext.link(openApiObjectBuilder, sourceContext, entry.getKey(), uri + "/" + entry.getKey(), entry.getValue(), true, null));
    }
    
    return builder;
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */