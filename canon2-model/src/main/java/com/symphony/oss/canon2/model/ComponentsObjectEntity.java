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
 *    At                   2020-10-28 11:40:29 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.model;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableSet;
import com.symphony.oss.canon.json.ParserException;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonNull;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon2.runtime.java.IObjectEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.JsonObjectEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;
import com.symphony.oss.canon2.runtime.java.ObjectEntity;
import com.symphony.oss.commons.fault.FaultAccumulator;

/**
 * Implementation for Object ComponentsObject
 * Generated from ComponentsObject at {entity.context.path}
 */
@Immutable
public abstract class ComponentsObjectEntity extends ObjectEntity
{
  /** Type ID */
  public static final String  TYPE_ID = "com.symphony.oss.canon2.model.ComponentsObject";
  /** Type version */
  public static final String  TYPE_VERSION = "1.0";
  /** Type major version */
  public static final Integer TYPE_MAJOR_VERSION = 1;
  /** Type minor version */
  public static final Integer TYPE_MINOR_VERSION = 0;
  /** Factory instance */
  public static final Factory FACTORY = new Factory();

  private final ImmutableSet<String>        unknownKeys_;
  private final SchemasObject              _schemas_;

  /**
   * Constructor.
   *
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  public ComponentsObjectEntity(Initialiser initialiser)
  {
    super(initialiser);

    if(initialiser instanceof JsonObjectEntityInitialiser)
    {
      JsonObjectEntityInitialiser jsonInitialiser = (JsonObjectEntityInitialiser)initialiser;

      JsonDomNode  node;

      node = jsonInitialiser.get("schemas");
      if(node == null || node instanceof JsonNull)
      {
        _schemas_ = null;
      }
      else
      {
    
        _schemas_ = SchemasObject.FACTORY.newInstance(node, jsonInitialiser.getModelRegistry());
      }
      unknownKeys_ = jsonInitialiser.getCanonUnknownKeys();
    }
    else
    {
      IComponentsObjectInstanceOrBuilder builder =  initialiser.getInstanceOrBuilder();

      if(builder == null)
      {
        throw new IllegalArgumentException("Initializer is not an JsonObjectEntityInitialiser but getInstanceOrBuilder() returns null");
      }
      _schemas_ = builder.getSchemas();
      unknownKeys_ = builder.getCanonUnknownKeys();
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
     * Return the minor type version for entities created by this factory.
     *
     * @return The minor type version for entities created by this factory.
     */
    public @Nullable Integer getCanonMinorVersion()
    {
      return TYPE_MINOR_VERSION;
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
        throw new ParserException("ComponentsObject must be an Object node not " + node.getClass().getName(), node.getContext());
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
    IComponentsObjectInstanceOrBuilder getInstanceOrBuilder();
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
    public IComponentsObjectInstanceOrBuilder getInstanceOrBuilder()
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
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends ComponentsObjectEntity>
    extends ObjectEntity.AbstractBuilder<T,B>
    implements IComponentsObjectInstanceOrBuilder, Initialiser
  {
    protected SchemasObject              _schemas_;

    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }

    @Override
    public IComponentsObjectInstanceOrBuilder getInstanceOrBuilder()
    {
      return this;
    }

    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);

      _schemas_ = initial.getSchemas();
    }

    @Override
    public T withValues(JsonObject jsonObject, ModelRegistry modelRegistry)
    {
      if(jsonObject.containsKey("schemas"))
      {
        JsonDomNode  node = jsonObject.get("schemas");
    
        _schemas_ = SchemasObject.FACTORY.newInstance(node, modelRegistry);
      }
      return super.withValues(jsonObject, modelRegistry);
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

      builder.addIfNotNull(JSON_TYPE, ComponentsObjectEntity.TYPE_ID);
      builder.addIfNotNull(JSON_VERSION, ComponentsObjectEntity.TYPE_VERSION);

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

    @Override
    public void validate(FaultAccumulator faultAccumulator)
    {
      super.validate(faultAccumulator);
    }
  }

  //@Override
  public ImmutableSet<String> getCanonUnknownKeys()
  {
    return unknownKeys_;
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
    if(obj instanceof ComponentsObjectEntity)
      return toString().equals(((ComponentsObjectEntity)obj).toString());

    return false;
  }

  @Override
  public int hashCode()
  {
    return toString().hashCode();
  }

// entity.additionalProperties??
// innerClasses
}

/*----------------------------------------------------------------------------------------------------
 * End of template template/Object/_Entity.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */