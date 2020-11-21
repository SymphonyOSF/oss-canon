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
 *    At                   2020-11-12 11:16:47 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableSet;
import com.symphony.oss.canon.json.ParserErrorException;
import com.symphony.oss.canon.json.model.JsonArray;
import com.symphony.oss.canon.json.model.JsonBoolean;
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
 * Implementation for Object ObjectSchema
 * Generated from ObjectSchema at {entity.context.path}
 */
@Immutable
public abstract class ObjectSchemaEntity extends ObjectEntity
{
  /** Type ID */
  public static final String  TYPE_ID = "com.symphony.oss.canon2.model.ObjectSchema";
  /** Type version */
  public static final String  TYPE_VERSION = "1.0";
  /** Factory instance */
  public static final Factory FACTORY = new Factory();

  private final ImmutableSet<String>        unknownKeys_;
  private final Boolean                    _xCanonBuilderFacade_;
  private final AdditionalProperties       _additionalProperties_;
  private final String                     _xCanonIdentifier_;
  private final ObjectSchema.Type          _type_;
  private final Boolean                    _xCanonFacade_;
  private final PropertiesObject           _properties_;
  private final Set<String>                _required_;
  private final ReferenceObject            _xCanonExtends_;
  private final DiscriminatorObject        _discriminator_;

  /**
   * Constructor.
   *
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  public ObjectSchemaEntity(Initialiser initialiser)
  {
    super(initialiser);

    if(initialiser instanceof JsonObjectEntityInitialiser)
    {
      JsonObjectEntityInitialiser jsonInitialiser = (JsonObjectEntityInitialiser)initialiser;

      JsonDomNode  node;

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

      node = jsonInitialiser.get("additionalProperties");
      if(node == null || node instanceof JsonNull)
      {
        _additionalProperties_ = null;
      }
      else
      {
        _additionalProperties_ = AdditionalProperties.FACTORY.newInstance(node, jsonInitialiser.getModelRegistry());
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
          _type_ = ObjectSchema.Type.deserialize(((JsonString)node).asString());
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

      node = jsonInitialiser.get("properties");
      if(node == null || node instanceof JsonNull)
      {
        throw new ParserErrorException("properties is required.", jsonInitialiser.getJson().getContext());
      }
      else
      {
        _properties_ = PropertiesObject.FACTORY.newInstance(node, jsonInitialiser.getModelRegistry());
      }

      node = jsonInitialiser.get("required");
      if(node == null || node instanceof JsonNull)
      {
        _required_ = null;
      }
      else
      {
        if(node instanceof JsonArray)
        {
          Set<String> itemSet0 = new HashSet<>();
          for(JsonDomNode item0 : (JsonArray)node)
          {
            String itemValue0 = null;
            if(item0 instanceof JsonString)
            {
              itemValue0 = ((JsonString)item0).asString();
            }
            else 
            {
              throw new ParserErrorException("required items must be an instance of JsonString not " + item0.getClass().getName(), item0.getContext());
            }
            itemSet0.add(itemValue0);
          }
          _required_ = ImmutableSet.copyOf(itemSet0);
        }
        else 
        {
          throw new ParserErrorException("required must be a JsonArray node not " + node.getClass().getName(), node.getContext());
        }
      }

      node = jsonInitialiser.get("x-canon-extends");
      if(node == null || node instanceof JsonNull)
      {
        _xCanonExtends_ = null;
      }
      else
      {
        _xCanonExtends_ = ReferenceObject.FACTORY.newInstance(node, jsonInitialiser.getModelRegistry());
      }

      node = jsonInitialiser.get("discriminator");
      if(node == null || node instanceof JsonNull)
      {
        _discriminator_ = null;
      }
      else
      {
        _discriminator_ = DiscriminatorObject.FACTORY.newInstance(node, jsonInitialiser.getModelRegistry());
      }
      unknownKeys_ = jsonInitialiser.getCanonUnknownKeys();
    }
    else
    {
      IObjectSchemaInstanceOrBuilder builder =  initialiser.getInstanceOrBuilder();

      if(builder == null)
      {
        throw new IllegalArgumentException("Initializer is not an JsonObjectEntityInitialiser but getInstanceOrBuilder() returns null");
      }
      _xCanonBuilderFacade_ = builder.getXCanonBuilderFacade();
      _additionalProperties_ = builder.getAdditionalProperties();
      _xCanonIdentifier_ = builder.getXCanonIdentifier();
      _type_ = builder.getType();
      if(_type_ == null)
        throw new IllegalArgumentException("type is required.");
  
      _xCanonFacade_ = builder.getXCanonFacade();
      _properties_ = builder.getProperties();
      if(_properties_ == null)
        throw new IllegalArgumentException("properties is required.");
  
      _required_ = ImmutableSet.copyOf(builder.getRequired());
      _xCanonExtends_ = builder.getXCanonExtends();
      _discriminator_ = builder.getDiscriminator();
      unknownKeys_ = builder.getCanonUnknownKeys();
    }
  }


  /**
   * Factory class for ObjectSchema.
   */
  public static class Factory extends ObjectEntity.Factory<ObjectSchema>
  {
    @Override
    public String getCanonType()
    {
      return TYPE_ID;
    }

    @Override
    public ObjectSchema newInstance(JsonDomNode node, ModelRegistry modelRegistry)
    {
      if(node instanceof JsonObject)
      {
        return new ObjectSchema(new JsonInitialiser((JsonObject)node, modelRegistry));
      }

      if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
      {
        throw new ParserErrorException("ObjectSchema must be an Object node not " + node.getClass().getName(), node.getContext());
      }
      else
      {
        return null;
      }
    }
  }

  /**
   * Abstract Initialiser for ObjectSchema
   */
  public interface Initialiser extends IObjectEntityInitialiser
  {
    /**
     * Return an instance or builder containing the values for a new instance.
     * 
     * @return an instance or builder containing the values for a new instance.
     */
    IObjectSchemaInstanceOrBuilder getInstanceOrBuilder();
  }

  /**
   * JSON Initialiser for ObjectSchema
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
    public IObjectSchemaInstanceOrBuilder getInstanceOrBuilder()
    {
      return null;
    }
  }

  /**
   * Abstract builder for ObjectSchema. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <T> The concrete type of the builder, used for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends ObjectSchemaEntity>
    extends ObjectEntity.AbstractBuilder<T,B>
    implements IObjectSchemaInstanceOrBuilder, Initialiser
  {
    protected Boolean                    _xCanonBuilderFacade_;
    protected AdditionalProperties       _additionalProperties_;
    protected String                     _xCanonIdentifier_;
    protected ObjectSchema.Type          _type_;
    protected Boolean                    _xCanonFacade_;
    protected PropertiesObject           _properties_;
    protected Set<String>                _required_ = new HashSet<String>();
    protected ReferenceObject            _xCanonExtends_;
    protected DiscriminatorObject        _discriminator_;

    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }

    @Override
    public IObjectSchemaInstanceOrBuilder getInstanceOrBuilder()
    {
      return this;
    }

    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);

      _xCanonBuilderFacade_ = initial.getXCanonBuilderFacade();
      _additionalProperties_ = initial.getAdditionalProperties();
      _xCanonIdentifier_ = initial.getXCanonIdentifier();
      _type_ = initial.getType();
      _xCanonFacade_ = initial.getXCanonFacade();
      _properties_ = initial.getProperties();
      _required_ = ImmutableSet.copyOf(initial.getRequired());
      _xCanonExtends_ = initial.getXCanonExtends();
      _discriminator_ = initial.getDiscriminator();
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
      if(json.containsKey("additionalProperties"))
      {
        JsonDomNode  node = json.get("additionalProperties");
        _additionalProperties_ = AdditionalProperties.FACTORY.newInstance(node, modelRegistry);
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
          _type_ = ObjectSchema.Type.deserialize(((JsonString)node).asString());
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
      if(json.containsKey("properties"))
      {
        JsonDomNode  node = json.get("properties");
        _properties_ = PropertiesObject.FACTORY.newInstance(node, modelRegistry);
      }
      if(json.containsKey("required"))
      {
        JsonDomNode  node = json.get("required");
        if(node instanceof JsonArray)
        {
          Set<String> itemSet0 = new HashSet<>();
          for(JsonDomNode item0 : (JsonArray)node)
          {
            String itemValue0 = null;
            if(item0 instanceof JsonString)
            {
              itemValue0 = ((JsonString)item0).asString();
            }
            else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
            {
              throw new ParserErrorException("required items must be an instance of JsonString not " + item0.getClass().getName(), item0.getContext());
            }
            itemSet0.add(itemValue0);
          }
          _required_ = ImmutableSet.copyOf(itemSet0);
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("required must be a JsonArray node not " + node.getClass().getName(), node.getContext());
        }
      }
      if(json.containsKey("x-canon-extends"))
      {
        JsonDomNode  node = json.get("x-canon-extends");
        _xCanonExtends_ = ReferenceObject.FACTORY.newInstance(node, modelRegistry);
      }
      if(json.containsKey("discriminator"))
      {
        JsonDomNode  node = json.get("discriminator");
        _discriminator_ = DiscriminatorObject.FACTORY.newInstance(node, modelRegistry);
      }
      return self();
    }

    /* void populateAllFields(List<Object> result)
    {
      result.add(_xCanonBuilderFacade_);
      result.add(_additionalProperties_);
      result.add(_xCanonIdentifier_);
      result.add(_type_);
      result.add(_xCanonFacade_);
      result.add(_properties_);
      result.add(_required_);
      result.add(_xCanonExtends_);
      result.add(_discriminator_);
    }*/

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
     * Return the value of the additionalProperties attribute.
     *
     * @return the value of the additionalProperties attribute.
     */
    @Override
    public @Nullable AdditionalProperties getAdditionalProperties()
    {
      return _additionalProperties_;
    }

    /**
     * Set the value of the additionalProperties attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withAdditionalProperties(AdditionalProperties value)
    {
      _additionalProperties_ = value;
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
    public @Nonnull ObjectSchema.Type getType()
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
    public T withType(ObjectSchema.Type value)
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

      _type_ = ObjectSchema.Type.deserialize(value);
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
     * Return the value of the properties attribute.
     *
     * @return the value of the properties attribute.
     */
    @Override
    public @Nonnull PropertiesObject getProperties()
    {
      if(_properties_ == null)
        throw new IllegalStateException("Unexpected null value encountered");
      return _properties_;
    }

    /**
     * Set the value of the properties attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withProperties(PropertiesObject value)
    {
        if(value == null)
          throw new IllegalArgumentException("properties is required.");
  
      _properties_ = value;
      return self();
    }

    /**
     * Return the value of the required attribute.
     *
     * @return the value of the required attribute.
     */
    @Override
    public @Nullable Set<String> getRequired()
    {
      return _required_;
    }

    /**
     * Set the value of the required attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withRequired(Set<String> value)
    {
      _required_ = ImmutableSet.copyOf(value);
      return self();
    }

    /**
     * Return the value of the x-canon-extends attribute.
     *
     * @return the value of the x-canon-extends attribute.
     */
    @Override
    public @Nullable ReferenceObject getXCanonExtends()
    {
      return _xCanonExtends_;
    }

    /**
     * Set the value of the x-canon-extends attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withXCanonExtends(ReferenceObject value)
    {
      _xCanonExtends_ = value;
      return self();
    }

    /**
     * Return the value of the discriminator attribute.
     *
     * @return the value of the discriminator attribute.
     */
    @Override
    public @Nullable DiscriminatorObject getDiscriminator()
    {
      return _discriminator_;
    }

    /**
     * Set the value of the discriminator attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withDiscriminator(DiscriminatorObject value)
    {
      _discriminator_ = value;
      return self();
    }


    @Override
    public JsonObject getJson()
    {
      JsonObject.Builder builder = new JsonObject.Builder();

      builder.addIfNotNull(JSON_TYPE, ObjectSchemaEntity.TYPE_ID);
      builder.addIfNotNull(JSON_VERSION, ObjectSchemaEntity.TYPE_VERSION);

      populateJson(builder);

      return builder.build();
    }

    @Override
    public void populateJson(JsonObject.Builder builder)
    {
      super.populateJson(builder);

      if(getXCanonBuilderFacade() != null)
      {
        builder.addIfNotNull("x-canon-builderFacade", getXCanonBuilderFacade());
      }

      if(getAdditionalProperties() != null)
      {
        builder.addIfNotNull("additionalProperties", getAdditionalProperties().getJson());
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

      if(getProperties() != null)
      {
        builder.addIfNotNull("properties", getProperties().getJson());
      }

      if(getRequired() != null)
      {
        JsonArray.Builder arrayBuilder = new JsonArray.Builder();
        for(String item : getRequired())
        {
          arrayBuilder.with(item);
        }
        builder.with("required", arrayBuilder.build());
      }

      if(getXCanonExtends() != null)
      {
        builder.addIfNotNull("x-canon-extends", getXCanonExtends().getJson());
      }

      if(getDiscriminator() != null)
      {
        builder.addIfNotNull("discriminator", getDiscriminator().getJson());
      }
    }

    @Override
    public void validate(FaultAccumulator faultAccumulator)
    {
      super.validate(faultAccumulator);
      faultAccumulator.checkNotNull(_type_, "type");
      faultAccumulator.checkNotNull(_properties_, "properties");
    }
  }

  @Override
  public ImmutableSet<String> getCanonUnknownKeys()
  {
    return unknownKeys_;
  }

  /**
   * Builder for ObjectSchema
   */
  public static class Builder extends ObjectSchema.AbstractBuilder<Builder, ObjectSchema>
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
    public Builder(ObjectSchema initial)
    {
      super(Builder.class, initial);
    }

    @Override
    protected ObjectSchema construct()
    {
      return new ObjectSchema(this);
    }
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
   * Return the value of the additionalProperties attribute.
   *
   * @return the value of the additionalProperties attribute.
   */
  public @Nullable AdditionalProperties getAdditionalProperties()
  {
    return _additionalProperties_;
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
  public @Nonnull ObjectSchema.Type getType()
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
   * Return the value of the properties attribute.
   *
   * @return the value of the properties attribute.
   */
  public @Nonnull PropertiesObject getProperties()
  {
    return _properties_;
  }

  /**
   * Return the value of the required attribute.
   *
   * @return the value of the required attribute.
   */
  public @Nullable Set<String> getRequired()
  {
    return _required_;
  }

  /**
   * Return the value of the x-canon-extends attribute.
   *
   * @return the value of the x-canon-extends attribute.
   */
  public @Nullable ReferenceObject getXCanonExtends()
  {
    return _xCanonExtends_;
  }

  /**
   * Return the value of the discriminator attribute.
   *
   * @return the value of the discriminator attribute.
   */
  public @Nullable DiscriminatorObject getDiscriminator()
  {
    return _discriminator_;
  }

  @Override
  public boolean equals(Object obj)
  {
    if(obj instanceof ObjectSchemaEntity)
      return toString().equals(((ObjectSchemaEntity)obj).toString());

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
    /** OBJECT */
    OBJECT("object")
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
        case "object":
          return OBJECT;
          
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