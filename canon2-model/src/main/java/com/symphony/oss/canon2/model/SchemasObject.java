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
import com.symphony.oss.canon2.runtime.java.Entity;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;


/**
 * Facade for Object  SchemasObject canon
 * Object com.symphony.oss.canon2.generator.java.JavaOpenApiTemplateModel@4df50bcc
 * Generated from JavaObjectSchemaTemplateModel [fields_=[]] at {entity.context.path}
 */
@Immutable
public class SchemasObject extends SchemasObjectEntity
{
  private final ImmutableMap<String, Schema> schemas_;
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public SchemasObject(AbstractBuilder<?,?> builder)
  {
    super(builder);
    schemas_ = initSchemas(null);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public SchemasObject(JsonObject jsonObject, ModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    schemas_ = initSchemas(modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public SchemasObject(SchemasObject other)
  {
    super(other);
    schemas_ = other.schemas_;
  }
  
  private ImmutableMap<String, Schema> initSchemas(ModelRegistry modelRegistry)
  {
    Map<String, Schema> schemas = new HashMap<>();
    
    for(String name : getCanonUnknownKeys())
    {
      schemas.put(name, new Schema(getJsonObject().getObject(name), modelRegistry));
    }
    
    return ImmutableMap.copyOf(schemas);
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
  
  /**
   * Abstract builder for SchemasObject. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends SchemasObject> extends SchemasObjectEntity.AbstractBuilder<T,B>
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