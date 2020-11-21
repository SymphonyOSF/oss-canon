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

import java.math.BigDecimal;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableSet;
import com.symphony.oss.canon.json.ParserErrorException;
import com.symphony.oss.canon.json.model.JsonBoolean;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonNull;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon.json.model.JsonParsedNumber;
import com.symphony.oss.canon.json.model.JsonString;
import com.symphony.oss.canon2.runtime.java.IObjectEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.JsonObjectEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;
import com.symphony.oss.canon2.runtime.java.ObjectEntity;
import com.symphony.oss.canon2.runtime.java.TypeDef;
import com.symphony.oss.commons.fault.FaultAccumulator;

/**
 * Implementation for Object NumberSchema
 * Generated from NumberSchema at {entity.context.path}
 */
@Immutable
public abstract class NumberSchema_Entity extends ObjectEntity
{
  /** Type ID */
  public static final String  TYPE_ID = "com.symphony.oss.canon2.model.NumberSchema";
  /** Type version */
  public static final String  TYPE_VERSION = "1.0";
  /** Factory instance */
  public static final Factory FACTORY = new Factory();

  private final ImmutableSet<String>        unknownKeys_;
  private final CanonAttributes            _xCanonAttributes_;
  private final Boolean                    _xCanonBuilderFacade_;
  private final String                     _format_;
  private final BigDecimal                 _maximum_;
  private final String                     _xCanonIdentifier_;
  private final NumberSchema.Type          _type_;
  private final Boolean                    _xCanonFacade_;
  private final BigDecimal                 _minimum_;

  /**
   * Constructor.
   *
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  public NumberSchema_Entity(Initialiser initialiser)
  {
    super(initialiser);

    if(initialiser instanceof JsonObjectEntityInitialiser)
    {
      JsonObjectEntityInitialiser jsonInitialiser = (JsonObjectEntityInitialiser)initialiser;

      JsonDomNode  node;

      node = jsonInitialiser.get("x-canon-attributes");
      if(node == null || node instanceof JsonNull)
      {
        _xCanonAttributes_ = null;
      }
      else
      {
        _xCanonAttributes_ = CanonAttributes.FACTORY.newInstance(node, jsonInitialiser.getModelRegistry());
      }

      node = jsonInitialiser.get("x-canon-builderFacade");
      if(node == null || node instanceof JsonNull)
      {
        _xCanonBuilderFacade_ = null;
      }
      else
      {
        if(node instanceof JsonBoolean)
        {
          _xCanonBuilderFacade_ = ((JsonBoolean)node).asBoolean();
        }
        else 
        {
          throw new ParserErrorException("x-canon-builderFacade must be an instance of JsonBoolean not " + node.getClass().getName(), node.getContext());
        }
      }

      node = jsonInitialiser.get("format");
      if(node == null || node instanceof JsonNull)
      {
        _format_ = null;
      }
      else
      {
        if(node instanceof JsonString)
        {
          _format_ = ((JsonString)node).asString();
        }
        else 
        {
          throw new ParserErrorException("format must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }

      node = jsonInitialiser.get("maximum");
      if(node == null || node instanceof JsonNull)
      {
        _maximum_ = null;
      }
      else
      {
        if(node instanceof JsonParsedNumber)
        {
          _maximum_ = ((JsonParsedNumber)node).asBigDecimal();
        }
        else 
        {
          throw new ParserErrorException("maximum must be an instance of JsonParsedNumber not " + node.getClass().getName(), node.getContext());
        }
      }

      node = jsonInitialiser.get("x-canon-identifier");
      if(node == null || node instanceof JsonNull)
      {
        _xCanonIdentifier_ = null;
      }
      else
      {
        if(node instanceof JsonString)
        {
          _xCanonIdentifier_ = ((JsonString)node).asString();
        }
        else 
        {
          throw new ParserErrorException("x-canon-identifier must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }

      node = jsonInitialiser.get("type");
      if(node == null || node instanceof JsonNull)
      {
        throw new ParserErrorException("type is required.", jsonInitialiser.getJson().getContext());
      }
      else
      {
        if(node instanceof JsonString)
        {
          _type_ = NumberSchema.Type.deserialize(((JsonString)node).asString());
        }
        else 
        {
          throw new ParserErrorException("type must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }

      node = jsonInitialiser.get("x-canon-facade");
      if(node == null || node instanceof JsonNull)
      {
        _xCanonFacade_ = null;
      }
      else
      {
        if(node instanceof JsonBoolean)
        {
          _xCanonFacade_ = ((JsonBoolean)node).asBoolean();
        }
        else 
        {
          throw new ParserErrorException("x-canon-facade must be an instance of JsonBoolean not " + node.getClass().getName(), node.getContext());
        }
      }

      node = jsonInitialiser.get("minimum");
      if(node == null || node instanceof JsonNull)
      {
        _minimum_ = null;
      }
      else
      {
        if(node instanceof JsonParsedNumber)
        {
          _minimum_ = ((JsonParsedNumber)node).asBigDecimal();
        }
        else 
        {
          throw new ParserErrorException("minimum must be an instance of JsonParsedNumber not " + node.getClass().getName(), node.getContext());
        }
      }
      unknownKeys_ = jsonInitialiser.getCanonUnknownKeys();
    }
    else
    {
      I_NumberSchema_InstanceOrBuilder builder =  initialiser.getInstanceOrBuilder();

      if(builder == null)
      {
        throw new IllegalArgumentException("Initializer is not an JsonObjectEntityInitialiser but getInstanceOrBuilder() returns null");
      }
      _xCanonAttributes_ = builder.getXCanonAttributes();
      _xCanonBuilderFacade_ = builder.getXCanonBuilderFacade();
      _format_ = builder.getFormat();
      _maximum_ = builder.getMaximum();
      _xCanonIdentifier_ = builder.getXCanonIdentifier();
      _type_ = builder.getType();
      if(_type_ == null)
        throw new IllegalArgumentException("type is required.");
  
      _xCanonFacade_ = builder.getXCanonFacade();
      _minimum_ = builder.getMinimum();
      unknownKeys_ = builder.getCanonUnknownKeys();
    }
  }


  /**
   * Factory class for NumberSchema.
   */
  public static class Factory extends ObjectEntity.Factory<NumberSchema>
  {
    @Override
    public String getCanonType()
    {
      return TYPE_ID;
    }

    @Override
    public NumberSchema newInstance(JsonDomNode node, ModelRegistry modelRegistry)
    {
      if(node instanceof JsonObject)
      {
        return new NumberSchema(new JsonInitialiser((JsonObject)node, modelRegistry));
      }

      if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
      {
        throw new ParserErrorException("NumberSchema must be an Object node not " + node.getClass().getName(), node.getContext());
      }
      else
      {
        return null;
      }
    }
  }

  /**
   * Abstract Initialiser for NumberSchema
   */
  public interface Initialiser extends IObjectEntityInitialiser
  {
    /**
     * Return an instance or builder containing the values for a new instance.
     * 
     * @return an instance or builder containing the values for a new instance.
     */
    I_NumberSchema_InstanceOrBuilder getInstanceOrBuilder();
  }

  /**
   * JSON Initialiser for NumberSchema
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
    public I_NumberSchema_InstanceOrBuilder getInstanceOrBuilder()
    {
      return null;
    }
  }

  /**
   * Abstract builder for NumberSchema. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <T> The concrete type of the builder, used for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends NumberSchema_Entity>
    extends ObjectEntity.AbstractBuilder<T,B>
    implements I_NumberSchema_InstanceOrBuilder, Initialiser
  {
    protected CanonAttributes            _xCanonAttributes_;
    protected Boolean                    _xCanonBuilderFacade_;
    protected String                     _format_;
    protected BigDecimal                 _maximum_;
    protected String                     _xCanonIdentifier_;
    protected NumberSchema.Type          _type_;
    protected Boolean                    _xCanonFacade_;
    protected BigDecimal                 _minimum_;

    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }

    @Override
    public I_NumberSchema_InstanceOrBuilder getInstanceOrBuilder()
    {
      return this;
    }

    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);

      _xCanonAttributes_ = initial.getXCanonAttributes();
      _xCanonBuilderFacade_ = initial.getXCanonBuilderFacade();
      _format_ = initial.getFormat();
      _maximum_ = initial.getMaximum();
      _xCanonIdentifier_ = initial.getXCanonIdentifier();
      _type_ = initial.getType();
      _xCanonFacade_ = initial.getXCanonFacade();
      _minimum_ = initial.getMinimum();
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
      if(json.containsKey("x-canon-attributes"))
      {
        JsonDomNode  node = json.get("x-canon-attributes");
        _xCanonAttributes_ = CanonAttributes.FACTORY.newInstance(node, modelRegistry);
      }
      if(json.containsKey("x-canon-builderFacade"))
      {
        JsonDomNode  node = json.get("x-canon-builderFacade");
        if(node instanceof JsonBoolean)
        {
          _xCanonBuilderFacade_ = ((JsonBoolean)node).asBoolean();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("x-canon-builderFacade must be an instance of JsonBoolean not " + node.getClass().getName(), node.getContext());
        }
      }
      if(json.containsKey("format"))
      {
        JsonDomNode  node = json.get("format");
        if(node instanceof JsonString)
        {
          _format_ = ((JsonString)node).asString();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("format must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }
      if(json.containsKey("maximum"))
      {
        JsonDomNode  node = json.get("maximum");
        if(node instanceof JsonParsedNumber)
        {
          _maximum_ = ((JsonParsedNumber)node).asBigDecimal();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("maximum must be an instance of JsonParsedNumber not " + node.getClass().getName(), node.getContext());
        }
      }
      if(json.containsKey("x-canon-identifier"))
      {
        JsonDomNode  node = json.get("x-canon-identifier");
        if(node instanceof JsonString)
        {
          _xCanonIdentifier_ = ((JsonString)node).asString();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("x-canon-identifier must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }
      if(json.containsKey("type"))
      {
        JsonDomNode  node = json.get("type");
        if(node instanceof JsonString)
        {
          _type_ = NumberSchema.Type.deserialize(((JsonString)node).asString());
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("type must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }
      if(json.containsKey("x-canon-facade"))
      {
        JsonDomNode  node = json.get("x-canon-facade");
        if(node instanceof JsonBoolean)
        {
          _xCanonFacade_ = ((JsonBoolean)node).asBoolean();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("x-canon-facade must be an instance of JsonBoolean not " + node.getClass().getName(), node.getContext());
        }
      }
      if(json.containsKey("minimum"))
      {
        JsonDomNode  node = json.get("minimum");
        if(node instanceof JsonParsedNumber)
        {
          _minimum_ = ((JsonParsedNumber)node).asBigDecimal();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("minimum must be an instance of JsonParsedNumber not " + node.getClass().getName(), node.getContext());
        }
      }
      return self();
    }

    /* void populateAllFields(List<Object> result)
    {
      result.add(_xCanonAttributes_);
      result.add(_xCanonBuilderFacade_);
      result.add(_format_);
      result.add(_maximum_);
      result.add(_xCanonIdentifier_);
      result.add(_type_);
      result.add(_xCanonFacade_);
      result.add(_minimum_);
    }*/

    /**
     * Return the value of the x-canon-attributes attribute.
     *
     * @return the value of the x-canon-attributes attribute.
     */
    @Override
    public @Nullable CanonAttributes getXCanonAttributes()
    {
      return _xCanonAttributes_;
    }

    /**
     * Set the value of the x-canon-attributes attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withXCanonAttributes(CanonAttributes value)
    {
      _xCanonAttributes_ = value;
      return self();
    }

    /**
     * Return the value of the x-canon-builderFacade attribute.
     *
     * @return the value of the x-canon-builderFacade attribute.
     */
    @Override
    public @Nullable Boolean getXCanonBuilderFacade()
    {
      return _xCanonBuilderFacade_;
    }

    /**
     * Set the value of the x-canon-builderFacade attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withXCanonBuilderFacade(Boolean value)
    {
      _xCanonBuilderFacade_ = value;
      return self();
    }

    /**
     * Return the value of the format attribute.
     *
     * @return the value of the format attribute.
     */
    @Override
    public @Nullable String getFormat()
    {
      return _format_;
    }

    /**
     * Set the value of the format attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withFormat(String value)
    {
      _format_ = value;
      return self();
    }

    /**
     * Return the value of the maximum attribute.
     *
     * @return the value of the maximum attribute.
     */
    @Override
    public @Nullable BigDecimal getMaximum()
    {
      return _maximum_;
    }

    /**
     * Set the value of the maximum attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withMaximum(BigDecimal value)
    {
      _maximum_ = value;
      return self();
    }

    /**
     * Return the value of the x-canon-identifier attribute.
     *
     * @return the value of the x-canon-identifier attribute.
     */
    @Override
    public @Nullable String getXCanonIdentifier()
    {
      return _xCanonIdentifier_;
    }

    /**
     * Set the value of the x-canon-identifier attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withXCanonIdentifier(String value)
    {
      _xCanonIdentifier_ = value;
      return self();
    }

    /**
     * Return the value of the type attribute.
     *
     * @return the value of the type attribute.
     */
    @Override
    public @Nonnull NumberSchema.Type getType()
    {
      if(_type_ == null)
        throw new IllegalStateException("Unexpected null value encountered");
      return _type_;
    }

    /**
     * Set the value of the type attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withType(NumberSchema.Type value)
    {
        if(value == null)
          throw new IllegalArgumentException("type is required.");
  
      _type_ = value;
      return self();
    }

    /**
     * Set the value of the type attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withType(String value)
    {
      if(value == null)
        throw new IllegalArgumentException("type is required.");

      _type_ = NumberSchema.Type.deserialize(value);
      return self();
    }

    /**
     * Return the value of the x-canon-facade attribute.
     *
     * @return the value of the x-canon-facade attribute.
     */
    @Override
    public @Nullable Boolean getXCanonFacade()
    {
      return _xCanonFacade_;
    }

    /**
     * Set the value of the x-canon-facade attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withXCanonFacade(Boolean value)
    {
      _xCanonFacade_ = value;
      return self();
    }

    /**
     * Return the value of the minimum attribute.
     *
     * @return the value of the minimum attribute.
     */
    @Override
    public @Nullable BigDecimal getMinimum()
    {
      return _minimum_;
    }

    /**
     * Set the value of the minimum attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withMinimum(BigDecimal value)
    {
      _minimum_ = value;
      return self();
    }


    @Override
    public JsonObject getJson()
    {
      JsonObject.Builder builder = new JsonObject.Builder();

      builder.addIfNotNull(JSON_TYPE, NumberSchema_Entity.TYPE_ID);
      builder.addIfNotNull(JSON_VERSION, NumberSchema_Entity.TYPE_VERSION);

      populateJson(builder);

      return builder.build();
    }

    @Override
    public void populateJson(JsonObject.Builder builder)
    {
      super.populateJson(builder);

      if(getXCanonAttributes() != null)
      {
        builder.addIfNotNull("x-canon-attributes", getXCanonAttributes().getJson());
      }

      if(getXCanonBuilderFacade() != null)
      {
        builder.addIfNotNull("x-canon-builderFacade", getXCanonBuilderFacade());
      }

      if(getFormat() != null)
      {
        builder.addIfNotNull("format", getFormat());
      }

      if(getMaximum() != null)
      {
        builder.addIfNotNull("maximum", getMaximum());
      }

      if(getXCanonIdentifier() != null)
      {
        builder.addIfNotNull("x-canon-identifier", getXCanonIdentifier());
      }

      if(getType() != null)
      {
        builder.addIfNotNull("type", getType().getValue());
      }

      if(getXCanonFacade() != null)
      {
        builder.addIfNotNull("x-canon-facade", getXCanonFacade());
      }

      if(getMinimum() != null)
      {
        builder.addIfNotNull("minimum", getMinimum());
      }
    }

    @Override
    public void validate(FaultAccumulator faultAccumulator)
    {
      super.validate(faultAccumulator);
      faultAccumulator.checkNotNull(_type_, "type");
    }
  }

  @Override
  public ImmutableSet<String> getCanonUnknownKeys()
  {
    return unknownKeys_;
  }

  /**
   * Builder for NumberSchema
   */
  public static class Builder extends NumberSchema.AbstractBuilder<Builder, NumberSchema>
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
    public Builder(NumberSchema initial)
    {
      super(Builder.class, initial);
    }

    @Override
    protected NumberSchema construct()
    {
      return new NumberSchema(this);
    }
  }


  /**
   * Return the value of the x-canon-attributes attribute.
   *
   * @return the value of the x-canon-attributes attribute.
   */
  public @Nullable CanonAttributes getXCanonAttributes()
  {
    return _xCanonAttributes_;
  }

  /**
   * Return the value of the x-canon-builderFacade attribute.
   *
   * @return the value of the x-canon-builderFacade attribute.
   */
  public @Nullable Boolean getXCanonBuilderFacade()
  {
    return _xCanonBuilderFacade_;
  }

  /**
   * Return the value of the format attribute.
   *
   * @return the value of the format attribute.
   */
  public @Nullable String getFormat()
  {
    return _format_;
  }

  /**
   * Return the value of the maximum attribute.
   *
   * @return the value of the maximum attribute.
   */
  public @Nullable BigDecimal getMaximum()
  {
    return _maximum_;
  }

  /**
   * Return the value of the x-canon-identifier attribute.
   *
   * @return the value of the x-canon-identifier attribute.
   */
  public @Nullable String getXCanonIdentifier()
  {
    return _xCanonIdentifier_;
  }

  /**
   * Return the value of the type attribute.
   *
   * @return the value of the type attribute.
   */
  public @Nonnull NumberSchema.Type getType()
  {
    return _type_;
  }

  /**
   * Return the value of the x-canon-facade attribute.
   *
   * @return the value of the x-canon-facade attribute.
   */
  public @Nullable Boolean getXCanonFacade()
  {
    return _xCanonFacade_;
  }

  /**
   * Return the value of the minimum attribute.
   *
   * @return the value of the minimum attribute.
   */
  public @Nullable BigDecimal getMinimum()
  {
    return _minimum_;
  }

  @Override
  public boolean equals(Object obj)
  {
    if(obj instanceof NumberSchema_Entity)
      return toString().equals(((NumberSchema_Entity)obj).toString());

    return false;
  }

  @Override
  public int hashCode()
  {
    return toString().hashCode();
  }

  /**
   * Enum  type canon
   * Model canon
   * Generated from type at {entity.context.path}
   */
  @Immutable
  public static enum Type
  {
    /** NUMBER */
    NUMBER("number"),
    /** INTEGER */
    INTEGER("integer")
  ;
    
    private final String value_;
    
    private Type(String value)
    {
      value_ = value;
    }
    
    /**
     * Return the serialized value of this enum constant.
     *
     * @return the serialized value of this enum constant.
     */
    public String getValue()
    {
      return value_;
    }
    
    /**
     * Deserialize an enum constant value from a String value.
     * 
     * @param value The serialized form of an enum constant.
     * 
     * @return The enum constant value from the given String value.
     * 
     * @throws IllegalArgumentException If the given value is not a valid enum constant.
     */
    public static final Type deserialize(String value)
    {
      switch(value)
      {
        case "number":
          return NUMBER;
          
        case "integer":
          return INTEGER;
          
        default:
          throw new IllegalArgumentException("No enum constant \"" + value + "\" in Type");
      }
    }
  }
}

/*----------------------------------------------------------------------------------------------------
 * End of template template/Object/_Entity.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */