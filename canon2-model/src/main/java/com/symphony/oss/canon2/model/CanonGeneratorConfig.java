  
  
// TRACE 1 imports
// javax.annotation.concurrent.Immutable
// com.symphony.oss.canon2.runtime.java.ModelRegistry
// com.symphony.oss.canon.json.model.JsonDomNode
// com.symphony.oss.canon.json.ParserErrorException
// com.symphony.oss.commons.fault.FaultAccumulator


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
 *    At                   2020-11-25 13:28:55 GMT
 *----------------------------------------------------------------------------------------------------
 */
  
  
// TRACE 2 imports
// javax.annotation.concurrent.Immutable
// com.symphony.oss.canon2.runtime.java.ModelRegistry
// com.symphony.oss.canon.json.model.JsonDomNode
// com.symphony.oss.canon.json.ParserErrorException
// com.symphony.oss.commons.fault.FaultAccumulator


  
  
// TRACE 3 imports
// javax.annotation.concurrent.Immutable
// com.symphony.oss.canon2.runtime.java.ModelRegistry
// com.symphony.oss.canon.json.model.JsonDomNode
// com.symphony.oss.canon.json.ParserErrorException
// com.symphony.oss.commons.fault.FaultAccumulator


// T2 A
  
  
// TRACE 4 imports
// javax.annotation.concurrent.Immutable
// com.symphony.oss.canon2.runtime.java.ModelRegistry
// com.symphony.oss.canon.json.model.JsonDomNode
// com.symphony.oss.canon.json.ParserErrorException
// com.symphony.oss.commons.fault.FaultAccumulator
// com.google.common.collect.ImmutableSortedMap
// java.util.Map
// java.util.HashMap
// com.symphony.oss.canon2.runtime.java.IObjectEntityInitialiser
// com.symphony.oss.canon2.runtime.java.JsonObjectEntityInitialiser
// com.symphony.oss.canon2.runtime.java.JsonEntityInitialiser
// com.symphony.oss.canon2.runtime.java.ObjectEntity
// com.symphony.oss.canon2.runtime.java.Entity
// com.symphony.oss.canon2.runtime.java.ObjectEntity
// com.symphony.oss.canon.json.model.JsonObject



package com.symphony.oss.canon2.model;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableSortedMap;
import com.symphony.oss.canon.json.ParserErrorException;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon2.runtime.java.Entity;
import com.symphony.oss.canon2.runtime.java.IObjectEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.JsonEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.JsonObjectEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;
import com.symphony.oss.canon2.runtime.java.ObjectEntity;
import com.symphony.oss.commons.fault.FaultAccumulator;

/**
 * Implementation for Object CanonGeneratorConfig
 * Generated from CanonGeneratorConfig at {entity.context.path}
 */
@Immutable
public class CanonGeneratorConfig extends ObjectEntity
{
  /** Type ID */
  public static final String  TYPE_ID = "com.symphony.oss.canon2.model.CanonGeneratorConfig";
  /** Type version */
  public static final String  TYPE_VERSION = "1.0";
  /** Factory instance */
  public static final Factory FACTORY = new Factory();

  private final Map<String, Entity>        additionalProperties_;

  /**
   * Constructor.
   *
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  public CanonGeneratorConfig(Initialiser initialiser)
  {
    super(initialiser);

    if(initialiser instanceof JsonObjectEntityInitialiser)
    {
      JsonObjectEntityInitialiser jsonInitialiser = (JsonObjectEntityInitialiser)initialiser;
      ModelRegistry modelRegistry = jsonInitialiser.getModelRegistry();

      JsonDomNode               node;
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
        additionalProperties.put(name, prop);
      }
      additionalProperties_ =  ImmutableSortedMap.copyOf(additionalProperties);
    }
    else
    {
      I_CanonGeneratorConfig_InstanceOrBuilder builder =  initialiser.getInstanceOrBuilder();

      if(builder == null)
      {
        throw new IllegalArgumentException("Initializer is not an JsonObjectEntityInitialiser but getInstanceOrBuilder() returns null");
      }
      additionalProperties_ = builder.canonGetAdditionalProperties();
    }
  }


  /**
   * Factory class for CanonGeneratorConfig.
   */
  public static class Factory extends ObjectEntity.Factory<CanonGeneratorConfig>
  {
    @Override
    public String getCanonType()
    {
      return TYPE_ID;
    }

    @Override
    public CanonGeneratorConfig newInstance(JsonDomNode node, ModelRegistry modelRegistry)
    {
      if(node instanceof JsonObject)
      {
        return new CanonGeneratorConfig(new JsonInitialiser((JsonObject)node, modelRegistry));
      }

      if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
      {
        throw new ParserErrorException("CanonGeneratorConfig must be an Object node not " + node.getClass().getName(), node.getContext());
      }
      else
      {
        return null;
      }
    }
  }

  /**
   * Abstract Initialiser for CanonGeneratorConfig
   */
  public interface Initialiser extends IObjectEntityInitialiser
  {
    /**
     * Return an instance or builder containing the values for a new instance.
     * 
     * @return an instance or builder containing the values for a new instance.
     */
    I_CanonGeneratorConfig_InstanceOrBuilder getInstanceOrBuilder();
  }

  /**
   * JSON Initialiser for CanonGeneratorConfig
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
    public I_CanonGeneratorConfig_InstanceOrBuilder getInstanceOrBuilder()
    {
      return null;
    }
  }

  /**
   * Abstract builder for CanonGeneratorConfig. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <T> The concrete type of the builder, used for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends CanonGeneratorConfig>
    extends ObjectEntity.AbstractBuilder<T,B>
    implements I_CanonGeneratorConfig_InstanceOrBuilder, Initialiser
  {
    protected Map<String, Entity>        additionalProperties_ = ImmutableSortedMap.of();

    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }

    @Override
    public I_CanonGeneratorConfig_InstanceOrBuilder getInstanceOrBuilder()
    {
      return this;
    }

    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);

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
      return self();
    }

    /* void populateAllFields(List<Object> result)
    {
    }*/


    @Override
    public JsonObject getJson()
    {
      JsonObject.Builder builder = new JsonObject.Builder();

      builder.addIfNotNull(JSON_TYPE, CanonGeneratorConfig.TYPE_ID);
      builder.addIfNotNull(JSON_VERSION, CanonGeneratorConfig.TYPE_VERSION);

      populateJson(builder);

      return builder.build();
    }

    @Override
    public void populateJson(JsonObject.Builder builder)
    {
      super.populateJson(builder);
    }

    @Override
    public Map<String, Entity> canonGetAdditionalProperties()
    {
       return additionalProperties_;
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
   * Builder for CanonGeneratorConfig
   */
  public static class Builder extends CanonGeneratorConfig.AbstractBuilder<Builder, CanonGeneratorConfig>
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
    public Builder(CanonGeneratorConfig initial)
    {
      super(Builder.class, initial);
    }

    @Override
    protected CanonGeneratorConfig construct()
    {
      return new CanonGeneratorConfig(this);
    }
  }


  @Override
  public boolean equals(Object obj)
  {
    if(obj instanceof CanonGeneratorConfig)
      return toString().equals(((CanonGeneratorConfig)obj).toString());

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