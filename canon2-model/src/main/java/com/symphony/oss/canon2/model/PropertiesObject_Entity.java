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
 *    At                   2020-11-21 06:21:48 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.model;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableSet;
import com.symphony.oss.canon.json.ParserErrorException;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon2.runtime.java.IObjectEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.JsonObjectEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;
import com.symphony.oss.canon2.runtime.java.ObjectEntity;
import com.symphony.oss.commons.fault.FaultAccumulator;

/**
 * Implementation for Object PropertiesObject
 * Generated from PropertiesObject at {entity.context.path}
 */
@Immutable
public abstract class PropertiesObject_Entity extends ObjectEntity
{
  /** Type ID */
  public static final String  TYPE_ID = "com.symphony.oss.canon2.model.PropertiesObject";
  /** Type version */
  public static final String  TYPE_VERSION = "1.0";
  /** Factory instance */
  public static final Factory FACTORY = new Factory();

  private final ImmutableSet<String>        unknownKeys_;

  /**
   * Constructor.
   *
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  public PropertiesObject_Entity(Initialiser initialiser)
  {
    super(initialiser);

    if(initialiser instanceof JsonObjectEntityInitialiser)
    {
      JsonObjectEntityInitialiser jsonInitialiser = (JsonObjectEntityInitialiser)initialiser;

      unknownKeys_ = jsonInitialiser.getCanonUnknownKeys();
    }
    else
    {
      I_PropertiesObject_InstanceOrBuilder builder =  initialiser.getInstanceOrBuilder();

      if(builder == null)
      {
        throw new IllegalArgumentException("Initializer is not an JsonObjectEntityInitialiser but getInstanceOrBuilder() returns null");
      }
      unknownKeys_ = builder.getCanonUnknownKeys();
    }
  }


  /**
   * Factory class for PropertiesObject.
   */
  public static class Factory extends ObjectEntity.Factory<PropertiesObject>
  {
    @Override
    public String getCanonType()
    {
      return TYPE_ID;
    }

    @Override
    public PropertiesObject newInstance(JsonDomNode node, ModelRegistry modelRegistry)
    {
      if(node instanceof JsonObject)
      {
        return new PropertiesObject(new JsonInitialiser((JsonObject)node, modelRegistry));
      }

      if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
      {
        throw new ParserErrorException("PropertiesObject must be an Object node not " + node.getClass().getName(), node.getContext());
      }
      else
      {
        return null;
      }
    }
  }

  /**
   * Abstract Initialiser for PropertiesObject
   */
  public interface Initialiser extends IObjectEntityInitialiser
  {
    /**
     * Return an instance or builder containing the values for a new instance.
     * 
     * @return an instance or builder containing the values for a new instance.
     */
    I_PropertiesObject_InstanceOrBuilder getInstanceOrBuilder();
  }

  /**
   * JSON Initialiser for PropertiesObject
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
    public I_PropertiesObject_InstanceOrBuilder getInstanceOrBuilder()
    {
      return null;
    }
  }

  /**
   * Abstract builder for PropertiesObject. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <T> The concrete type of the builder, used for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends PropertiesObject_Entity>
    extends ObjectEntity.AbstractBuilder<T,B>
    implements I_PropertiesObject_InstanceOrBuilder, Initialiser
  {

    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }

    @Override
    public I_PropertiesObject_InstanceOrBuilder getInstanceOrBuilder()
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

      builder.addIfNotNull(JSON_TYPE, PropertiesObject_Entity.TYPE_ID);
      builder.addIfNotNull(JSON_VERSION, PropertiesObject_Entity.TYPE_VERSION);

      populateJson(builder);

      return builder.build();
    }

    @Override
    public void populateJson(JsonObject.Builder builder)
    {
      super.populateJson(builder);
    }

    @Override
    public void validate(FaultAccumulator faultAccumulator)
    {
      super.validate(faultAccumulator);
    }
  }

  @Override
  public ImmutableSet<String> getCanonUnknownKeys()
  {
    return unknownKeys_;
  }

  /**
   * Builder for PropertiesObject
   */
  public static class Builder extends PropertiesObject.AbstractBuilder<Builder, PropertiesObject>
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
    public Builder(PropertiesObject initial)
    {
      super(Builder.class, initial);
    }

    @Override
    protected PropertiesObject construct()
    {
      return new PropertiesObject(this);
    }
  }


  @Override
  public boolean equals(Object obj)
  {
    if(obj instanceof PropertiesObject_Entity)
      return toString().equals(((PropertiesObject_Entity)obj).toString());

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