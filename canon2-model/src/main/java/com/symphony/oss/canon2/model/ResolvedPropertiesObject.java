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
 *    At                   2020-09-16 16:04:42 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.model;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableMap;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;
import com.symphony.oss.commons.fault.CodingFault;


/**
 * Facade for Object  ResolvedPropertiesObject canon
 * Object com.symphony.oss.canon2.generator.java.JavaOpenApiTemplateModel@4df50bcc
 * Generated from JavaObjectSchemaTemplateModel [fields_=[]] at {entity.context.path}
 */
@Immutable
public class ResolvedPropertiesObject extends ResolvedPropertiesObjectEntity
{
  private final ImmutableMap<String, ResolvedSchema> properties_;
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public ResolvedPropertiesObject(AbstractBuilder<?,?> builder)
  {
    super(builder);
    
    properties_ = ImmutableMap.copyOf(builder.properties_);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public ResolvedPropertiesObject(JsonObject jsonObject, ModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    throw new CodingFault("Not serializable");
  }
   
//  /**
//   * Copy constructor.
//   * 
//   * @param other Another instance from which all attributes are to be copied.
//   */
//  public ResolvedPropertiesObject(ResolvedPropertiesObject other)
//  {
//    super(other);
//  }
  
  public ImmutableMap<String, ResolvedSchema> getResolvedProperties()
  {
    return properties_;
  }
  
  /**
   * Abstract builder for ResolvedPropertiesObject. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends ResolvedPropertiesObject> extends ResolvedPropertiesObjectEntity.AbstractBuilder<T,B>
  {
    private Map<String, ResolvedSchema> properties_ = new HashMap<>();
    
    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);
    }
    
    public T withProperty(String name, ResolvedSchema schema)
    {
      properties_.put(name, schema);
      
      return self();
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */