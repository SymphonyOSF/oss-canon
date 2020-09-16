/**
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
 * Proforma generated from
 *		Template groupId		 org.symphonyoss.s2.canon
 *           artifactId canon-template-java
 *		Template name		   proforma/java/Object/_.java.ftl
 *		Template version	   1.0
 *  At                  2020-08-21 14:27:27 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.parser;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableMap;
import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.canon2.parser.model.SchemasObjectEntity;
import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;

/**
 * Facade for Object ObjectSchema(SchemasObject)
 *
 * An element to hold various schemas for the specification.
 * Generated from ObjectSchema(SchemasObject) at #/components/schemas/SchemasObject
 */
@Immutable
public class SchemasObject extends SchemasObjectEntity implements ISchemasObject
{
  private final ImmutableMap<String, ISchema> schemas_;
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public SchemasObject(AbstractSchemasObjectBuilder<?,?> builder)
  {
    super(builder);
    schemas_ = initSchemas(null);
//    schemas_ = null;
  }
  
  private ImmutableMap<String, ISchema> initSchemas(IModelRegistry modelRegistry)
  {
    Map<String, ISchema> schemas = new HashMap<>();
    
    for(String name : getCanonUnknownKeys())
    {
      schemas.put(name, new Schema((ImmutableJsonObject) getJsonObject().getObject(name), modelRegistry));
    }
    
    return ImmutableMap.copyOf(schemas);
  }

  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public SchemasObject(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    schemas_ = initSchemas(modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public SchemasObject(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
    schemas_ = initSchemas(modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public SchemasObject(ISchemasObject other)
  {
    super(other);
    schemas_ = other.getSchemas();
  }

  @Override
  public ImmutableMap<String, ISchema> getSchemas()
  {
    return schemas_;
  }
  
  @Override
  public ICanonModelEntity get(String[] parts, int index) throws GenerationException
  {
    ISchema schema = schemas_.get(parts[index]);
    
    if(schema == null)
      return super.get(parts, index);
    else
      return schema;
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */