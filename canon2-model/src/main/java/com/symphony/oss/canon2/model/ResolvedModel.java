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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;
import com.symphony.oss.commons.fault.CodingFault;


/**
 * Facade for Object  ResolvedModel canon
 * Object com.symphony.oss.canon2.generator.java.JavaOpenApiTemplateModel@4df50bcc
 * Generated from JavaObjectSchemaTemplateModel [fields_=[]] at {entity.context.path}
 */
@Immutable
public class ResolvedModel extends ResolvedModelEntity
{
  private static Logger log_ = LoggerFactory.getLogger(ResolvedModel.class);
  
  private final ImmutableMap<String, ResolvedSchema> resolvedSchemas_;
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public ResolvedModel(AbstractBuilder<?,?> builder)
  {
    super(builder);
    
    resolvedSchemas_ = ImmutableMap.copyOf(builder.resolvedSchemas_);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public ResolvedModel(JsonObject jsonObject, ModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    throw new CodingFault("Not Supported");
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public ResolvedModel(ResolvedModel other)
  {
    super(other);
    
    resolvedSchemas_ = other.resolvedSchemas_;
  }
  
  public ImmutableMap<String, ResolvedSchema> getResolvedSchemas()
  {
    return resolvedSchemas_;
  }

  /**
   * Abstract builder for ResolvedModel. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends ResolvedModel> extends ResolvedModelEntity.AbstractBuilder<T,B>
  {
    private Map<String, ResolvedSchema> resolvedSchemas_ = new HashMap<>();
    
    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);
    }
    
    public T withResolvedSchema(String name, ResolvedSchema schema)
    {
      resolvedSchemas_.put(name, schema);
      
      return self();
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */