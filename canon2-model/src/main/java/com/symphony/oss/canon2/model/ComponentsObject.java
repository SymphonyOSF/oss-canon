/**
 * GENERATED CODE - DO NOT EDIT OR CHECK IN TO SOURCE CODE CONTROL
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
 *    At                   2020-10-06 07:12:27 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.model;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon2.core.CanonModelContext;
import com.symphony.oss.canon2.core.GenerationException;
import com.symphony.oss.canon2.core.ResolvedComponentsObject;
import com.symphony.oss.canon2.core.ResolvedOpenApiObject;
import com.symphony.oss.canon2.core.SourceContext;
import com.symphony.oss.canon2.runtime.java.Entity;


/**
 * Facade for Object  ComponentsObject canon
 * Object ComponentsObject
 * Generated from ComponentsObject
 */
@Immutable
public class ComponentsObject extends ComponentsObjectEntity
{
  /**
   * Constructor.
   * 
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  protected ComponentsObject(Initialiser initialiser)
  {
    super(initialiser);
  }
  

  public interface IInstanceOrBuilder extends ComponentsObjectEntity.IInstanceOrBuilder
  {
  }
  
  /**
   * Abstract builder for ComponentsObject. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends ComponentsObject>
    extends ComponentsObjectEntity.AbstractBuilder<T,B>
    implements IInstanceOrBuilder
  {
    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);
    }
  }
  
  public Entity get(String[] parts, int index)
  {
    switch(parts[index])
    {
      case "schemas":
        return getSchemas().get(parts, index + 1);
    }
    
    throw new IllegalArgumentException("No path element " + parts[index]);
  }

  public ResolvedComponentsObject.SingletonBuilder link(ResolvedOpenApiObject.SingletonBuilder openApiObjectBuilder, CanonModelContext modelContext, SourceContext sourceContext, String uri) throws GenerationException
  {
    ResolvedComponentsObject.SingletonBuilder builder = new ResolvedComponentsObject.SingletonBuilder();
    
    if(getSchemas() != null)
    {
      builder.withSchemas(getSchemas().link(openApiObjectBuilder, modelContext, sourceContext, uri + "/schemas"));
    }
    
    return builder;
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */