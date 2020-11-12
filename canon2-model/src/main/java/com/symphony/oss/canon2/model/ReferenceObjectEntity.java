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
 *    At                   2020-11-12 10:04:36 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.model;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableSet;
import com.symphony.oss.canon.json.ParserErrorException;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonNull;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon.json.model.JsonString;
import com.symphony.oss.canon2.runtime.java.IObjectEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.JsonObjectEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;
import com.symphony.oss.canon2.runtime.java.ObjectEntity;
import com.symphony.oss.canon2.runtime.java.TypeDef;
import com.symphony.oss.commons.fault.FaultAccumulator;

/**
 * Implementation for Object ReferenceObject
 * Generated from ReferenceObject at {entity.context.path}
 */
@Immutable
public abstract class ReferenceObjectEntity extends ObjectEntity
{
  /** Type ID */
  public static final String  TYPE_ID = "com.symphony.oss.canon2.model.ReferenceObject";
  /** Type version */
  public static final String  TYPE_VERSION = "1.0";
  /** Factory instance */
  public static final Factory FACTORY = new Factory();

  private final ImmutableSet<String>        unknownKeys_;
  private final String                     _$ref_;

  /**
   * Constructor.
   *
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  public ReferenceObjectEntity(Initialiser initialiser)
  {
    super(initialiser);

    if(initialiser instanceof JsonObjectEntityInitialiser)
    {
      JsonObjectEntityInitialiser jsonInitialiser = (JsonObjectEntityInitialiser)initialiser;

      JsonDomNode  node;

      node = jsonInitialiser.get("$ref");
      if(node == null || node instanceof JsonNull)
      {
        throw new ParserErrorException("$ref is required.", jsonInitialiser.getJson().getContext());
      }
      else
      {
        if(node instanceof JsonString)
        {
          _$ref_ = ((JsonString)node).asString();
        }
        else 
        {
          throw new ParserErrorException("$ref must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }
      unknownKeys_ = jsonInitialiser.getCanonUnknownKeys();
    }
    else
    {
      IReferenceObjectInstanceOrBuilder builder =  initialiser.getInstanceOrBuilder();

      if(builder == null)
      {
        throw new IllegalArgumentException("Initializer is not an JsonObjectEntityInitialiser but getInstanceOrBuilder() returns null");
      }
      _$ref_ = builder.get$ref();
      if(_$ref_ == null)
        throw new IllegalArgumentException("$ref is required.");
  
      unknownKeys_ = builder.getCanonUnknownKeys();
    }
  }


  /**
   * Factory class for ReferenceObject.
   */
  public static class Factory extends ObjectEntity.Factory<ReferenceObject>
  {
    @Override
    public String getCanonType()
    {
      return TYPE_ID;
    }

    @Override
    public ReferenceObject newInstance(JsonDomNode node, ModelRegistry modelRegistry)
    {
      if(node instanceof JsonObject)
      {
        return new ReferenceObject(new JsonInitialiser((JsonObject)node, modelRegistry));
      }

      if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
      {
        throw new ParserErrorException("ReferenceObject must be an Object node not " + node.getClass().getName(), node.getContext());
      }
      else
      {
        return null;
      }
    }
  }

  /**
   * Abstract Initialiser for ReferenceObject
   */
  public interface Initialiser extends IObjectEntityInitialiser
  {
    /**
     * Return an instance or builder containing the values for a new instance.
     * 
     * @return an instance or builder containing the values for a new instance.
     */
    IReferenceObjectInstanceOrBuilder getInstanceOrBuilder();
  }

  /**
   * JSON Initialiser for ReferenceObject
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
    public IReferenceObjectInstanceOrBuilder getInstanceOrBuilder()
    {
      return null;
    }
  }

  /**
   * Abstract builder for ReferenceObject. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <T> The concrete type of the builder, used for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends ReferenceObjectEntity>
    extends ObjectEntity.AbstractBuilder<T,B>
    implements IReferenceObjectInstanceOrBuilder, Initialiser
  {
    protected String                     _$ref_;

    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }

    @Override
    public IReferenceObjectInstanceOrBuilder getInstanceOrBuilder()
    {
      return this;
    }

    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);

      _$ref_ = initial.get$ref();
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
      if(json.containsKey("$ref"))
      {
        JsonDomNode  node = json.get("$ref");
        if(node instanceof JsonString)
        {
          _$ref_ = ((JsonString)node).asString();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("$ref must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }
      return self();
    }

    /* void populateAllFields(List<Object> result)
    {
      result.add(_$ref_);
    }*/

    /**
     * Return the value of the $ref attribute.
     *
     * @return the value of the $ref attribute.
     */
    @Override
    public @Nonnull String get$ref()
    {
      if(_$ref_ == null)
        throw new IllegalStateException("Unexpected null value encountered");
      return _$ref_;
    }

    /**
     * Set the value of the $ref attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T with$ref(String value)
    {
        if(value == null)
          throw new IllegalArgumentException("$ref is required.");
  
      _$ref_ = value;
      return self();
    }


    @Override
    public JsonObject getJson()
    {
      JsonObject.Builder builder = new JsonObject.Builder();

      builder.addIfNotNull(JSON_TYPE, ReferenceObjectEntity.TYPE_ID);
      builder.addIfNotNull(JSON_VERSION, ReferenceObjectEntity.TYPE_VERSION);

      populateJson(builder);

      return builder.build();
    }

    @Override
    public void populateJson(JsonObject.Builder builder)
    {
      super.populateJson(builder);

      if(get$ref() != null)
      {
        builder.addIfNotNull("$ref", get$ref());
      }
    }

    @Override
    public void validate(FaultAccumulator faultAccumulator)
    {
      super.validate(faultAccumulator);
      faultAccumulator.checkNotNull(_$ref_, "$ref");
    }
  }

  @Override
  public ImmutableSet<String> getCanonUnknownKeys()
  {
    return unknownKeys_;
  }

  /**
   * Builder for ReferenceObject
   */
  public static class Builder extends ReferenceObject.AbstractBuilder<Builder, ReferenceObject>
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
    public Builder(ReferenceObject initial)
    {
      super(Builder.class, initial);
    }

    @Override
    protected ReferenceObject construct()
    {
      return new ReferenceObject(this);
    }
  }


  /**
   * Return the value of the $ref attribute.
   *
   * @return the value of the $ref attribute.
   */
  public @Nonnull String get$ref()
  {
    return _$ref_;
  }

  @Override
  public boolean equals(Object obj)
  {
    if(obj instanceof ReferenceObjectEntity)
      return toString().equals(((ReferenceObjectEntity)obj).toString());

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