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
 *  At                  2020-08-26 08:41:45 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.parser;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.commons.immutable.ImmutableByteArray;

import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;
import com.google.common.collect.ImmutableMap;
import com.symphony.oss.canon.runtime.IEntity;
import com.symphony.oss.canon.runtime.IModelRegistry;


import com.symphony.oss.canon2.parser.model.PropertiesObjectEntity;
import com.symphony.oss.canon2.parser.model.IPropertiesObjectEntity;
import com.symphony.oss.canon2.parser.model.ISchemaOrReferenceEntity;
import com.symphony.oss.canon2.parser.model.CanonModel;

/**
 * Facade for Object ObjectSchema(PropertiesObject)
 *
 * A schema property.
 * Generated from ObjectSchema(PropertiesObject) at #/components/schemas/PropertiesObject
 */
@Immutable
@SuppressWarnings("unused")
public class PropertiesObject extends PropertiesObjectEntity implements IPropertiesObject
{
  private final ImmutableMap<String, Object> properties_;
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public PropertiesObject(AbstractPropertiesObjectBuilder<?,?> builder)
  {
    super(builder);
    
    properties_ = initProperties(null);
  }
  
  private ImmutableMap<String, Object> initProperties(IModelRegistry modelRegistry)
  {
    Map<String, Object> properties = new HashMap<>();
    
    for(String name : getCanonUnknownKeys())
    {
      ImmutableJsonObject json = (ImmutableJsonObject) getJsonObject().getObject(name);
      if(json.get("$ref") == null)
        properties.put(name, new Schema(json, modelRegistry));
      else
        properties.put(name, new ReferenceObject(json, modelRegistry));
    }
    
    return ImmutableMap.copyOf(properties);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public PropertiesObject(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    
    properties_ = initProperties(modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public PropertiesObject(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
    
    properties_ = initProperties(modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public PropertiesObject(IPropertiesObject other)
  {
    super(other);
    
    properties_ = other.getProperties();
  }

  @Override
  public ImmutableMap<String, Object> getProperties()
  {
    return properties_;
  }
  
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */