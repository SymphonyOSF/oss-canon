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
 *  At                  2020-08-27 17:46:24 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.canon2.parser.model.IResolvedModelEntity;
import com.symphony.oss.canon2.parser.model.ResolvedModelEntity;
import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.fault.CodingFault;

/**
 * Facade for Object ObjectSchema(ResolvedModel)
 *
 * An OpenApi model with all references resolved.
 * Generated from ObjectSchema(ResolvedModel) at #/components/schemas/ResolvedModel
 */
@Immutable
public class ResolvedModel
<
T extends ITemplateModel<T,M,S>,
M extends IOpenApiTemplateModel<T,M,S>,
S extends ISchemaTemplateModel<T,M,S>,
O extends IObjectSchemaTemplateModel<T,M,S,F>,
A extends IArraySchemaTemplateModel<T,M,S>,
P extends IPrimitiveSchemaTemplateModel<T,M,S>,
F extends IFieldTemplateModel<T,M,S>
> extends ResolvedModelEntity implements IResolvedModel
{
  private static Logger log_ = LoggerFactory.getLogger(ResolvedModel.class);
  private final ImmutableMap<String, IResolvedSchema> schemas_;
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public ResolvedModel(AbstractResolvedModelBuilder<?,?> builder)
  {
    super(builder);
    schemas_ = ImmutableMap.copyOf(builder.schemas_);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public ResolvedModel(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    throw new CodingFault("Not serializable");
  }
//  
//  /**
//   * Constructor from mutable JSON object.
//   * 
//   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
//   * @param modelRegistry A model registry to use to deserialize any nested objects.
//   */
//  public ResolvedModel(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
//  {
//    super(mutableJsonObject, modelRegistry);
//  }
//   
//  /**
//   * Copy constructor.
//   * 
//   * @param other Another instance from which all attributes are to be copied.
//   */
//  public ResolvedModel(IResolvedModel other)
//  {
//    super(other);
//  }
  
  @Override
  public 
  //<S extends ISchemaTemplateModel<S>> IOpenApiTemplateModel<S> 
  <
  T extends ITemplateModel<T,M,S>,
  M extends IOpenApiTemplateModel<T,M,S>,
  S extends ISchemaTemplateModel<T,M,S>,
  O extends IObjectSchemaTemplateModel<T,M,S,F>,
  A extends IArraySchemaTemplateModel<T,M,S>,
  P extends IPrimitiveSchemaTemplateModel<T,M,S>,
  F extends IFieldTemplateModel<T,M,S>
  >
  M generate(IGeneratorModelContext<T,M,S,O,A,P,F> modelContext)
  {
    log_.info("generate model");
    
    M parentModel = modelContext.generateOpenApiObject(this);
    
    for(Entry<String, IResolvedSchema> entry : getResolvedSchemas().entrySet())
    {
      S model = entry.getValue().generate(parentModel, entry.getKey(), modelContext);
      
      parentModel.addSchema(model);
    }
    
    return parentModel;
  }
  
  @Override
  public ImmutableMap<String, IResolvedSchema> getResolvedSchemas()
  {
    return schemas_;
  }

  /**
   * Abstract builder for ResolvedModel. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractResolvedModelBuilder<B extends AbstractResolvedModelBuilder<B,T>, T extends IResolvedModelEntity> extends AbstractResolvedModelEntityBuilder<B,T>
  {
    private Map<String, IResolvedSchema> schemas_ = new HashMap<>();
    
    protected AbstractResolvedModelBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractResolvedModelBuilder(Class<B> type, IResolvedModelEntity initial)
    {
      super(type, initial);
    }
    
    public B withResolvedSchema(String name, IResolvedSchema schema)
    {
      schemas_.put(name, schema);
      
      return self();
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */