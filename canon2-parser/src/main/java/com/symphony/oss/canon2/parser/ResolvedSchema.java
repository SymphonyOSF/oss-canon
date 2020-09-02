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
 *  At                  2020-08-27 15:32:15 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.parser;

import java.util.Map.Entry;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.canon2.parser.model.CanonCardinality;
import com.symphony.oss.canon2.parser.model.ResolvedSchemaEntity;
import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;
import com.symphony.oss.commons.fault.CodingFault;

/**
 * Facade for Object ObjectSchema(ResolvedSchema)
 *
 * A data type definition with all references resolved.
 * Generated from ObjectSchema(ResolvedSchema) at #/components/schemas/ResolvedSchema
 */
@Immutable
public class ResolvedSchema extends ResolvedSchemaEntity implements IResolvedSchema
{
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public ResolvedSchema(AbstractResolvedSchemaBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public ResolvedSchema(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public ResolvedSchema(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public ResolvedSchema(IResolvedSchema other)
  {
    super(other);
  }
  /*
   * 
   * (non-Javadoc)
   * @see com.symphony.oss.canon2.parser.IResolvedSchema#generate(com.symphony.oss.canon2.parser.IOpenApiTemplateModel, java.lang.String, com.symphony.oss.canon2.parser.IGeneratorModelContext)
   */
  @SuppressWarnings("unchecked")
  @Override
  public 
  <
  T extends ITemplateModel<T,M,S>,
  M extends IOpenApiTemplateModel<T,M,S>,
  S extends ISchemaTemplateModel<T,M,S>,
  O extends IObjectSchemaTemplateModel<T,M,S,F>,
  A extends IArraySchemaTemplateModel<T,M,S>,
  P extends IPrimitiveSchemaTemplateModel<T,M,S>,
  F extends IFieldTemplateModel<T,M,S>
  >
    S generate(M model, String name, IGeneratorModelContext<T,M,S,O,A,P,F> modelContext)
  {
    switch(getType())
    {
      case "object":
      {
        //IObjectSchemaTemplateModel<S> 
        O entity = modelContext.generateObjectSchema(model, this, name);
        
        IResolvedPropertiesObject propertiesObject = getResolvedProperties();
        
        if(propertiesObject != null)
        {
          for(Entry<String, IResolvedSchema> entry : propertiesObject.getResolvedProperties().entrySet())
          {
            S typeSchema = entry.getValue().generate(model, entry.getKey(), modelContext);
            
            entity.addField(modelContext.generateField(model, entry.getValue(), entry.getKey(), typeSchema));
          }
        }
        
        return (S) entity;
      }
      
      case "array":
        CanonCardinality cardinality = getXCanonCardinality();
        if(cardinality == null)
        {
          cardinality = CanonCardinality.LIST; 
        }
        
        return (S) modelContext.generateArraySchema(model, this, name, cardinality);
        
      case "number":
      case "boolean":
      case "string":
      case "integer":
        return (S) modelContext.generatePrimativeSchema(model, this, name);
        
      default:
        throw new CodingFault("Unknown schema type " + getType());
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */