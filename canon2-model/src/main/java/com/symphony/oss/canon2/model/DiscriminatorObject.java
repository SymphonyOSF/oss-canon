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
 *    Template name        template/Object/_.java.ftl
 *    At                   2020-11-25 15:52:09 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.model;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableSortedMap;
import com.symphony.oss.canon.json.ParserErrorException;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonNull;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon.json.model.JsonString;
import com.symphony.oss.canon2.runtime.java.Entity;
import com.symphony.oss.canon2.runtime.java.IObjectEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.JsonEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.JsonObjectEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;
import com.symphony.oss.canon2.runtime.java.ObjectEntity;
import com.symphony.oss.commons.fault.FaultAccumulator;

/**
 * Implementation for Object DiscriminatorObject
 * Generated from DiscriminatorObject at {entity.context.path}
 */
@Immutable
public class DiscriminatorObject extends ObjectEntity
{
  /** Type ID */
  public static final String  TYPE_ID = "com.symphony.oss.canon2.model.DiscriminatorObject";
  /** Type version */
  public static final String  TYPE_VERSION = "1.0";
  /** Factory instance */
  public static final Factory FACTORY = new Factory();

  private final Map<String, Entity>        additionalProperties_;
  private final String                     _propertyName_;

  /**
   * Constructor.
   *
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  public DiscriminatorObject(Initialiser initialiser)
  {
    super(initialiser);

    if(initialiser instanceof JsonObjectEntityInitialiser)
    {
      JsonObjectEntityInitialiser jsonInitialiser = (JsonObjectEntityInitialiser)initialiser;
      ModelRegistry modelRegistry = jsonInitialiser.getModelRegistry();

      JsonDomNode               node;

      node = jsonInitialiser.get("propertyName");
      if(node == null || node instanceof JsonNull)
      {
        _propertyName_ = null;
      }
      else
      {
// A1
//A2
        if(node instanceof JsonString)
        {
//A6
          _propertyName_ = ((JsonString)node).asString();
        }
        else 
        {
          throw new ParserErrorException("propertyName must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }
      Map<String, Entity>       additionalProperties = new HashMap<>();    
      Entity                    prop;

      for(String name : jsonInitialiser.getCanonUnknownKeys())
      {
        prop   = null;
        node   = jsonInitialiser.get(name);
        if(node instanceof JsonObject)
        {
          prop = new ObjectEntity(new JsonObjectEntityInitialiser((JsonObject)node, initialiser.getModelRegistry()));
        }
        else 
        {
          prop = new Entity(new JsonEntityInitialiser(node, initialiser.getModelRegistry()));
        }
        if(prop != null)
        {
          additionalProperties.put(name, prop);
        }
      }
      additionalProperties_ =  ImmutableSortedMap.copyOf(additionalProperties);
    }
    else
    {
      I_DiscriminatorObject_InstanceOrBuilder builder =  initialiser.getInstanceOrBuilder();

      if(builder == null)
      {
        throw new IllegalArgumentException("Initializer is not an JsonObjectEntityInitialiser but getInstanceOrBuilder() returns null");
      }
      _propertyName_ = builder.getPropertyName();
      additionalProperties_ = builder.canonGetAdditionalProperties();
    }
  }


  /**
   * Factory class for DiscriminatorObject.
   */
  public static class Factory extends ObjectEntity.Factory<DiscriminatorObject>
  {
    @Override
    public String getCanonType()
    {
      return TYPE_ID;
    }

    @Override
    public DiscriminatorObject newInstance(JsonDomNode node, ModelRegistry modelRegistry)
    {
      if(node instanceof JsonObject)
      {
        return new DiscriminatorObject(new JsonInitialiser((JsonObject)node, modelRegistry));
      }

      if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
      {
        throw new ParserErrorException("DiscriminatorObject must be an Object node not " + node.getClass().getName(), node.getContext());
      }
      else
      {
        return null;
      }
    }
  }

  /**
   * Abstract Initialiser for DiscriminatorObject
   */
  public interface Initialiser extends IObjectEntityInitialiser
  {
    /**
     * Return an instance or builder containing the values for a new instance.
     * 
     * @return an instance or builder containing the values for a new instance.
     */
    I_DiscriminatorObject_InstanceOrBuilder getInstanceOrBuilder();
  }

  /**
   * JSON Initialiser for DiscriminatorObject
   */
  public static class JsonInitialiser extends JsonObjectEntityInitialiser implements Initialiser
  {
      /**
       * Constructor.
       * 
       * @param json            JSON serialised form.
       * @param modelRegistry   A parser context for deserialisation.
       */
    public JsonInitialiser(JsonObject json, ModelRegistry modelRegistry)
    {
      super(json, modelRegistry);
    }

    @Override
    public I_DiscriminatorObject_InstanceOrBuilder getInstanceOrBuilder()
    {
      return null;
    }
  }

  /**
   * Abstract builder for DiscriminatorObject. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <T> The concrete type of the builder, used for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends DiscriminatorObject>
    extends ObjectEntity.AbstractBuilder<T,B>
    implements I_DiscriminatorObject_InstanceOrBuilder, Initialiser
  {
    protected String                     _propertyName_;
    protected Map<String, Entity>        additionalProperties_ = new HashMap<>();

    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }

    @Override
    public I_DiscriminatorObject_InstanceOrBuilder getInstanceOrBuilder()
    {
      return this;
    }

    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);

      _propertyName_ = initial.getPropertyName();
    }

    /**
     * Initialize this builder with the values from the given serialized form.
     * 
     * @param json          The serialized form of an instance of the built type.
     * @param modelRegistry A model registry.
     * 
     * @return This (fluent method).
     */
    public T withValues(JsonObject json, ModelRegistry modelRegistry)
    {
      if(json.containsKey("propertyName"))
      {
        JsonDomNode  node = json.get("propertyName");
// A1
//A2
        if(node instanceof JsonString)
        {
//A6
          _propertyName_ = ((JsonString)node).asString();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("propertyName must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }
      return self();
    }

    /**
     * Set an additional property.
     * 
     * @param name  The property name.
     * @param value The property value.
     * 
     * @return This (fluent method).
     */
    public T with(String name, Entity value)
    {
      additionalProperties_.put(name, value);

      return self();
    }

    /* void populateAllFields(List<Object> result)
    {
      result.add(_propertyName_);
    }*/

    /**
     * Return the value of the propertyName attribute.
     *
     * @return the value of the propertyName attribute.
     */
    @Override
    public @Nullable String getPropertyName()
    {
      return _propertyName_;
    }

    /**
     * Set the value of the propertyName attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withPropertyName(String value)
    {
      _propertyName_ = value;
      return self();
    }


    @Override
    public JsonObject getJson()
    {
      JsonObject.Builder builder = new JsonObject.Builder();

      builder.addIfNotNull(JSON_TYPE, DiscriminatorObject.TYPE_ID);
      builder.addIfNotNull(JSON_VERSION, DiscriminatorObject.TYPE_VERSION);

      populateJson(builder);

      return builder.build();
    }

    @Override
    public void populateJson(JsonObject.Builder builder)
    {
      super.populateJson(builder);

      if(getPropertyName() != null)
      {
        builder.addIfNotNull("propertyName", getPropertyName());
      }

      for(String name : additionalProperties_.keySet())
      {
        Entity value = additionalProperties_.get(name);
        builder.addIfNotNull(name, value.getJson());
      }
    }

    @Override
    public Map<String, Entity> canonGetAdditionalProperties()
    {
       return ImmutableSortedMap.copyOf(additionalProperties_);
    }

    @Override
    public void validate(FaultAccumulator faultAccumulator)
    {
      super.validate(faultAccumulator);
    }
  }

  /**
   * Return any additional attributes.
   * 
   * @return any additional attributes.
   */
  public Map<String, Entity> canonGetAdditionalProperties()
  {
     return additionalProperties_;
  }

  /**
   * Builder for DiscriminatorObject
   */
  public static class Builder extends DiscriminatorObject.AbstractBuilder<Builder, DiscriminatorObject>
  {
    /**
     * Constructor.
     */
    public Builder()
    {
      super(Builder.class);
    }

    /**
     * Constructor initialised from another object instance.
     *
     * @param initial An instance of the built type from which values are to be initialised.
     */
    public Builder(DiscriminatorObject initial)
    {
      super(Builder.class, initial);
    }

    @Override
    protected DiscriminatorObject construct()
    {
      return new DiscriminatorObject(this);
    }
  }


  /**
   * Return the value of the propertyName attribute.
   *
   * @return the value of the propertyName attribute.
   */
  public @Nullable String getPropertyName()
  {
    return _propertyName_;
  }

  @Override
  public boolean equals(Object obj)
  {
    if(obj instanceof DiscriminatorObject)
      return toString().equals(((DiscriminatorObject)obj).toString());

    return false;
  }

  @Override
  public int hashCode()
  {
    return toString().hashCode();
  }


}

/*----------------------------------------------------------------------------------------------------
 * End of template template/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */