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


/**
 * Facade for Object  PropertiesObject canon
 * Object com.symphony.oss.canon2.generator.java.JavaOpenApiTemplateModel@4df50bcc
 * Generated from JavaObjectSchemaTemplateModel [fields_=[]] at {entity.context.path}
 */
@Immutable
public class PropertiesObject extends PropertiesObjectEntity
{
  private final ImmutableMap<String, Object> properties_;
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public PropertiesObject(AbstractBuilder<?,?> builder)
  {
    super(builder);
    
    properties_ = initProperties(null);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public PropertiesObject(JsonObject jsonObject, ModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    
    properties_ = initProperties(modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public PropertiesObject(PropertiesObject other)
  {
    super(other);
    
    properties_ = other.getProperties();
  }
  
  private ImmutableMap<String, Object> initProperties(ModelRegistry modelRegistry)
  {
    Map<String, Object> properties = new HashMap<>();
    
    for(String name : getCanonUnknownKeys())
    {
      JsonObject json = getJsonObject().getObject(name);
      if(json.get("$ref") == null)
        properties.put(name, new Schema(json, modelRegistry));
      else
        properties.put(name, new ReferenceObject(json, modelRegistry));
    }
    
    return ImmutableMap.copyOf(properties);
  }

  public ImmutableMap<String, Object> getProperties()
  {
    return properties_;
  }
  
  /**
   * Abstract builder for PropertiesObject. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends PropertiesObject> extends PropertiesObjectEntity.AbstractBuilder<T,B>
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
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */