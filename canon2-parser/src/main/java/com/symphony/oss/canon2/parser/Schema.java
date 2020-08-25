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

import java.util.function.Consumer;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.commons.immutable.ImmutableByteArray;

import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;

import com.symphony.oss.canon.runtime.IEntity;
import com.symphony.oss.canon.runtime.IModelRegistry;


import com.symphony.oss.canon2.parser.model.SchemaEntity;
import com.symphony.oss.canon2.parser.model.ISchemaEntity;
import com.symphony.oss.canon2.parser.model.CanonModel;

/**
 * Facade for Object ObjectSchema(Schema)
 *
 * A data type definition.
 * Generated from ObjectSchema(Schema) at #/components/schemas/Schema
 */
@Immutable
public class Schema extends SchemaEntity implements ISchema
{
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public Schema(AbstractSchemaBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public Schema(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public Schema(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public Schema(ISchema other)
  {
    super(other);
  }

  @Override
  public void resolve(GenerationContext generationContext)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void validate(GenerationContext generationContext)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public ITemplateEntity generate(IModelEntity parentModel, String name, IGeneratorModelContext modelContext, GenerationContext generationContext)
  {
    return modelContext.generate(parentModel, this, name, generationContext);
  }
  
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */