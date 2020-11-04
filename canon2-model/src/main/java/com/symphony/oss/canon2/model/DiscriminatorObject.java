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
 *    At                   2020-10-28 18:16:15 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.model;

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

  private final ImmutableSet<String>        unknownKeys_;
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

      JsonDomNode  node;

      node = jsonInitialiser.get("propertyName");
      if(node == null || node instanceof JsonNull)
      {
        _propertyName_ = null;
      }
      else
      {
        if(node instanceof JsonString)
        {
          _propertyName_ = ((JsonString)node).asString();
        }
        else 
        {
          throw new ParserErrorException("propertyName must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }
      unknownKeys_ = jsonInitialiser.getCanonUnknownKeys();
    }
    else
    {
      IDiscriminatorObjectInstanceOrBuilder builder =  initialiser.getInstanceOrBuilder();

      if(builder == null)
      {
        throw new IllegalArgumentException("Initializer is not an JsonObjectEntityInitialiser but getInstanceOrBuilder() returns null");
      }
      _propertyName_ = builder.getPropertyName();
      unknownKeys_ = builder.getCanonUnknownKeys();
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
    IDiscriminatorObjectInstanceOrBuilder getInstanceOrBuilder();
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
    public IDiscriminatorObjectInstanceOrBuilder getInstanceOrBuilder()
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
// super class name
    extends ObjectEntity.AbstractBuilder<T,B>
    implements IDiscriminatorObjectInstanceOrBuilder, Initialiser
  {
    protected String                     _propertyName_;

    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }

    @Override
    public IDiscriminatorObjectInstanceOrBuilder getInstanceOrBuilder()
    {
      return this;
    }

    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);

      _propertyName_ = initial.getPropertyName();
    }

    @Override
    public T withValues(JsonObject jsonObject, ModelRegistry modelRegistry)
    {
      if(jsonObject.containsKey("propertyName"))
      {
        JsonDomNode  node = jsonObject.get("propertyName");
        if(node instanceof JsonString)
        {
          _propertyName_ = ((JsonString)node).asString();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("propertyName must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }
      return super.withValues(jsonObject, modelRegistry);
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
//T1 entity DiscriminatorObject OBJECT
    @Override
    public void populateJson(JsonObject.Builder builder)
    {
      super.populateJson(builder);

      if(getPropertyName() != null)
      {
          builder.addIfNotNull("propertyName", getPropertyName());
      }
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

// entity.additionalProperties??
// innerClasses
}

/*----------------------------------------------------------------------------------------------------
 * End of template template/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */