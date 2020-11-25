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
 *    Template name        template/Object/_Entity.java.ftl
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
import com.symphony.oss.canon2.runtime.java.Entity;
import com.symphony.oss.canon2.runtime.java.IObjectEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.JsonEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.JsonObjectEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;
import com.symphony.oss.canon2.runtime.java.ObjectEntity;
import com.symphony.oss.commons.fault.FaultAccumulator;

/**
 * Implementation for Object ComponentsObject
 * Generated from ComponentsObject at {entity.context.path}
 */
@Immutable
public abstract class ComponentsObject_Entity extends ObjectEntity
{
  /** Type ID */
  public static final String  TYPE_ID = "com.symphony.oss.canon2.model.ComponentsObject";
  /** Type version */
  public static final String  TYPE_VERSION = "1.0";
  /** Factory instance */
  public static final Factory FACTORY = new Factory();

  private final Map<String, Entity>        additionalProperties_;
  private final SchemasObject              _schemas_;

  /**
   * Constructor.
   *
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  public ComponentsObject_Entity(Initialiser initialiser)
  {
    super(initialiser);

    if(initialiser instanceof JsonObjectEntityInitialiser)
    {
      JsonObjectEntityInitialiser jsonInitialiser = (JsonObjectEntityInitialiser)initialiser;
      ModelRegistry modelRegistry = jsonInitialiser.getModelRegistry();

      JsonDomNode               node;

      node = jsonInitialiser.get("schemas");
      if(node == null || node instanceof JsonNull)
      {
        _schemas_ = null;
      }
      else
      {
// A1
//A2
    //A3
    
    
        if(node instanceof JsonObject)
        {
//A6a
          _schemas_ = SchemasObject.FACTORY.newInstance((JsonObject)node, modelRegistry);
        }
        else 
        {
          throw new ParserErrorException("schemas must be an instance of JsonObject not " + node.getClass().getName(), node.getContext());
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
      I_ComponentsObject_InstanceOrBuilder builder =  initialiser.getInstanceOrBuilder();

      if(builder == null)
      {
        throw new IllegalArgumentException("Initializer is not an JsonObjectEntityInitialiser but getInstanceOrBuilder() returns null");
      }
      _schemas_ = builder.getSchemas();
      additionalProperties_ = builder.canonGetAdditionalProperties();
    }
  }


  /**
   * Factory class for ComponentsObject.
   */
  public static class Factory extends ObjectEntity.Factory<ComponentsObject>
  {
    @Override
    public String getCanonType()
    {
      return TYPE_ID;
    }

    @Override
    public ComponentsObject newInstance(JsonDomNode node, ModelRegistry modelRegistry)
    {
      if(node instanceof JsonObject)
      {
        return new ComponentsObject(new JsonInitialiser((JsonObject)node, modelRegistry));
      }

      if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
      {
        throw new ParserErrorException("ComponentsObject must be an Object node not " + node.getClass().getName(), node.getContext());
      }
      else
      {
        return null;
      }
    }
  }

  /**
   * Abstract Initialiser for ComponentsObject
   */
  public interface Initialiser extends IObjectEntityInitialiser
  {
    /**
     * Return an instance or builder containing the values for a new instance.
     * 
     * @return an instance or builder containing the values for a new instance.
     */
    I_ComponentsObject_InstanceOrBuilder getInstanceOrBuilder();
  }

  /**
   * JSON Initialiser for ComponentsObject
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
    public I_ComponentsObject_InstanceOrBuilder getInstanceOrBuilder()
    {
      return null;
    }
  }

  /**
   * Abstract builder for ComponentsObject. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <T> The concrete type of the builder, used for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends ComponentsObject_Entity>
    extends ObjectEntity.AbstractBuilder<T,B>
    implements I_ComponentsObject_InstanceOrBuilder, Initialiser
  {
    protected SchemasObject              _schemas_;
    protected Map<String, Entity>        additionalProperties_ = new HashMap<>();

    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }

    @Override
    public I_ComponentsObject_InstanceOrBuilder getInstanceOrBuilder()
    {
      return this;
    }

    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);

      _schemas_ = initial.getSchemas();
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
      if(json.containsKey("schemas"))
      {
        JsonDomNode  node = json.get("schemas");
// A1
//A2
    //A3
    
    
        if(node instanceof JsonObject)
        {
//A6a
          _schemas_ = SchemasObject.FACTORY.newInstance((JsonObject)node, modelRegistry);
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("schemas must be an instance of JsonObject not " + node.getClass().getName(), node.getContext());
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
      result.add(_schemas_);
    }*/

    /**
     * Return the value of the schemas attribute.
     *
     * @return the value of the schemas attribute.
     */
    @Override
    public @Nullable SchemasObject getSchemas()
    {
      return _schemas_;
    }

    /**
     * Set the value of the schemas attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withSchemas(SchemasObject value)
    {
      _schemas_ = value;
      return self();
    }


    @Override
    public JsonObject getJson()
    {
      JsonObject.Builder builder = new JsonObject.Builder();

      builder.addIfNotNull(JSON_TYPE, ComponentsObject_Entity.TYPE_ID);
      builder.addIfNotNull(JSON_VERSION, ComponentsObject_Entity.TYPE_VERSION);

      populateJson(builder);

      return builder.build();
    }

    @Override
    public void populateJson(JsonObject.Builder builder)
    {
      super.populateJson(builder);

      if(getSchemas() != null)
      {
        builder.addIfNotNull("schemas", getSchemas().getJson());
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
   * Builder for ComponentsObject
   */
  public static class Builder extends ComponentsObject.AbstractBuilder<Builder, ComponentsObject>
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
    public Builder(ComponentsObject initial)
    {
      super(Builder.class, initial);
    }

    @Override
    protected ComponentsObject construct()
    {
      return new ComponentsObject(this);
    }
  }


  /**
   * Return the value of the schemas attribute.
   *
   * @return the value of the schemas attribute.
   */
  public @Nullable SchemasObject getSchemas()
  {
    return _schemas_;
  }

  @Override
  public boolean equals(Object obj)
  {
    if(obj instanceof ComponentsObject_Entity)
      return toString().equals(((ComponentsObject_Entity)obj).toString());

    return false;
  }

  @Override
  public int hashCode()
  {
    return toString().hashCode();
  }


}

/*----------------------------------------------------------------------------------------------------
 * End of template template/Object/_Entity.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */