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
 *  At                  2020-08-27 17:30:52 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.parser;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableMap;
import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.canon2.parser.model.IResolvedPropertiesObjectEntity;
import com.symphony.oss.canon2.parser.model.ResolvedPropertiesObjectEntity;
import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;
import com.symphony.oss.commons.fault.CodingFault;

/**
 * Facade for Object ObjectSchema(ResolvedPropertiesObject)
 *
 * A collection of resolved schema properties (fields).
 * Generated from ObjectSchema(ResolvedPropertiesObject) at #/components/schemas/ResolvedPropertiesObject
 */
@Immutable
@SuppressWarnings("unused")
public class ResolvedPropertiesObject extends ResolvedPropertiesObjectEntity implements IResolvedPropertiesObject
{
  private final ImmutableMap<String, IResolvedSchema> properties_;
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public ResolvedPropertiesObject(AbstractResolvedPropertiesObjectBuilder<?,?> builder)
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
  public ResolvedPropertiesObject(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
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
//  public ResolvedPropertiesObject(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
//  {
//    super(mutableJsonObject, modelRegistry);
//  }
//   
//  /**
//   * Copy constructor.
//   * 
//   * @param other Another instance from which all attributes are to be copied.
//   */
//  public ResolvedPropertiesObject(IResolvedPropertiesObject other)
//  {
//    super(other);
//  }
  
  @Override
  public ImmutableMap<String, IResolvedSchema> getResolvedProperties()
  {
    return properties_;
  }

  /**
   * Abstract builder for ResolvedPropertiesObject. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractResolvedPropertiesObjectBuilder<B extends AbstractResolvedPropertiesObjectBuilder<B,T>, T extends IResolvedPropertiesObjectEntity> extends AbstractResolvedPropertiesObjectEntityBuilder<B,T>
  {
    private Map<String, IResolvedSchema> properties_ = new HashMap<>();
    
    protected AbstractResolvedPropertiesObjectBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractResolvedPropertiesObjectBuilder(Class<B> type, IResolvedPropertiesObjectEntity initial)
    {
      super(type, initial);
    }
    
    public B withProperty(String name, IResolvedSchema schema)
    {
      properties_.put(name, schema);
      
      return self();
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */