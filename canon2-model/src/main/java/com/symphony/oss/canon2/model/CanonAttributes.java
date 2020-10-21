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
 *    At                   2020-10-21 14:50:09 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.model;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableSet;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon2.runtime.java.IObjectEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.JsonObjectEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;
import com.symphony.oss.canon2.runtime.java.ObjectEntity;
import com.symphony.oss.commons.fault.FaultAccumulator;

/**
 * Implementation for Object CanonAttributes
 * Generated from CanonAttributes at {entity.context.path}
 */
@Immutable
public class CanonAttributes extends ObjectEntity
{
  /** Type ID */
  public static final String  TYPE_ID = "com.symphony.oss.canon2.model.CanonAttributes";
  /** Type version */
  public static final String  TYPE_VERSION = "1.0";
  /** Type major version */
  public static final Integer TYPE_MAJOR_VERSION = 1;
  /** Type minor version */
  public static final Integer TYPE_MINOR_VERSION = 0;
  /** Factory instance */
  public static final Factory FACTORY = new Factory();

  private final ImmutableSet<String>        unknownKeys_;

  /**
   * Constructor.
   *
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  public CanonAttributes(Initialiser initialiser)
  {
    super(initialiser);

    if(initialiser instanceof JsonObjectEntityInitialiser)
    {
      JsonObjectEntityInitialiser jsonInitialiser = (JsonObjectEntityInitialiser)initialiser;

      unknownKeys_ = jsonInitialiser.getCanonUnknownKeys();
    }
    else
    {
      ICanonAttributesInstanceOrBuilder builder =  initialiser.getInstanceOrBuilder();

      if(builder == null)
      {
        throw new IllegalArgumentException("Initializer is not an JsonObjectEntityInitialiser but getInstanceOrBuilder() returns null");
      }
      unknownKeys_ = builder.getCanonUnknownKeys();
    }
  }


  /**
   * Factory class for CanonAttributes.
   */
  public static class Factory extends ObjectEntity.Factory<CanonAttributes>
  {
    @Override
    public String getCanonType()
    {
      return TYPE_ID;
    }

    /**
     * Return the type version (_version JSON attribute) for entities created by this factory.
     *
     * @return The type version for entities created by this factory.
     */
    public String getCanonVersion()
    {
      return TYPE_VERSION;
    }

    /**
     * Return the major type version for entities created by this factory.
     *
     * @return The major type version for entities created by this factory.
     */
    public @Nullable Integer getCanonMajorVersion()
    {
      return TYPE_MAJOR_VERSION;
    }

    /**
     * Return the minjor type version for entities created by this factory.
     *
     * @return The minor type version for entities created by this factory.
     */
    public @Nullable Integer getCanonMinorVersion()
    {
      return TYPE_MINOR_VERSION;
    }

    @Override
    public CanonAttributes newInstance(JsonObject jsonObject, ModelRegistry modelRegistry)
    {
      return new CanonAttributes(new JsonInitialiser(jsonObject, modelRegistry));
    }
  }

  /**
   * Abstract Initialiser for CanonAttributes
   */
  public interface Initialiser extends IObjectEntityInitialiser
  {
    /**
     * Return an instance or builder containing the values for a new instance.
     * 
     * @return an instance or builder containing the values for a new instance.
     */
    ICanonAttributesInstanceOrBuilder getInstanceOrBuilder();
  }

  /**
   * JSON Initialiser for CanonAttributes
   */
  public static class JsonInitialiser extends JsonObjectEntityInitialiser implements Initialiser
  {
      /**
       * Constructor.
       * 
       * @param jsonObject      A JSON Object.
       * @param modelRegistry   A parser context for deserialisation.
       */
    public JsonInitialiser(JsonObject jsonObject, ModelRegistry modelRegistry)
    {
      super(jsonObject, modelRegistry);
    }

    @Override
    public ICanonAttributesInstanceOrBuilder getInstanceOrBuilder()
    {
      return null;
    }
  }

  /**
   * Abstract builder for CanonAttributes. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <T> The concrete type of the builder, used for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends CanonAttributes>
    extends ObjectEntity.AbstractBuilder<T,B>
    implements ICanonAttributesInstanceOrBuilder, Initialiser
  {

    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }

    @Override
    public ICanonAttributesInstanceOrBuilder getInstanceOrBuilder()
    {
      return this;
    }

    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);

    }

    @Override
    public T withValues(JsonObject jsonObject, ModelRegistry modelRegistry)
    {
      return super.withValues(jsonObject, modelRegistry);
    }

    /* void populateAllFields(List<Object> result)
    {
    }*/

    @Override
    public JsonObject getJsonObject()
    {
      JsonObject.Builder builder = new JsonObject.Builder();

      builder.addIfNotNull(JSON_TYPE, CanonAttributes.TYPE_ID);
      builder.addIfNotNull(JSON_VERSION, CanonAttributes.TYPE_VERSION);

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

    @Override
    public String getCanonType()
    {
      return TYPE_ID;
    }

    @Override
    public String getCanonVersion()
    {
      return TYPE_VERSION;
    }

    @Override
    public @Nullable Integer getCanonMajorVersion()
    {
      return TYPE_MAJOR_VERSION;
    }

    @Override
    public @Nullable Integer getCanonMinorVersion()
    {
      return TYPE_MINOR_VERSION;
    }
  }

  /**
   * Builder for CanonAttributes
   */
  public static class Builder extends CanonAttributes.AbstractBuilder<Builder, CanonAttributes>
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
    public Builder(CanonAttributes initial)
    {
      super(Builder.class, initial);
    }

    @Override
    protected CanonAttributes construct()
    {
      return new CanonAttributes(this);
    }
  }

  @Override
  public ImmutableSet<String> getCanonUnknownKeys()
  {
    return unknownKeys_;
  }

  @Override
  public boolean equals(Object obj)
  {
    if(obj instanceof CanonAttributes)
      return toString().equals(((CanonAttributes)obj).toString());

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