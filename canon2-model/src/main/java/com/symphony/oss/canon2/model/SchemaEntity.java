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
 *    At                   2020-10-28 18:16:15 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableSet;
import com.symphony.oss.canon.json.ParserErrorException;
import com.symphony.oss.canon.json.model.JsonArray;
import com.symphony.oss.canon.json.model.JsonBoolean;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonNull;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon.json.model.JsonParsedNumber;
import com.symphony.oss.canon.json.model.JsonString;
import com.symphony.oss.canon2.runtime.java.Entity;
import com.symphony.oss.canon2.runtime.java.IEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.IObjectEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.JsonEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.JsonObjectEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;
import com.symphony.oss.canon2.runtime.java.ObjectEntity;
import com.symphony.oss.commons.fault.FaultAccumulator;

/**
 * Implementation for Object Schema
 * Generated from Schema at {entity.context.path}
 */
@Immutable
public abstract class SchemaEntity extends ObjectEntity
{
  /** Type ID */
  public static final String  TYPE_ID = "com.symphony.oss.canon2.model.Schema";
  /** Type version */
  public static final String  TYPE_VERSION = "1.0";
  /** Factory instance */
  public static final Factory FACTORY = new Factory();

  private final ImmutableSet<String>        unknownKeys_;
  private final BigInteger                 _maxItems_;
  private final Boolean                    _xCanonBuilderFacade_;
  private final AdditionalProperties       _xXXadditionalProperties_;
  private final String                     _format_;
  private final CanonCardinality           _xCanonCardinality_;
  private final String                     _xCanonIdentifier_;
  private final String                     _type_;
  private final Boolean                    _xCanonFacade_;
  private final Set<String>                _enum_;
  private final Set<String>                _required_;
  private final DiscriminatorObject        _discriminator_;
  private final BigInteger                 _minItems_;
  private final Set<SchemaOrRef>           _oneOf_;
  private final CanonAttributes            _xCanonAttributes_;
  private final BigDecimal                 _maximum_;
  private final BigDecimal                 _minimum_;
  private final PropertiesObject           _properties_;
  private final ReferenceObject            _xCanonExtends_;

  /**
   * Constructor.
   *
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  public SchemaEntity(Initialiser initialiser)
  {
    super(initialiser);

    if(initialiser instanceof JsonObjectEntityInitialiser)
    {
      JsonObjectEntityInitialiser jsonInitialiser = (JsonObjectEntityInitialiser)initialiser;

      JsonDomNode  node;

      node = jsonInitialiser.get("maxItems");
      if(node == null || node instanceof JsonNull)
      {
        _maxItems_ = null;
      }
      else
      {
        if(node instanceof JsonParsedNumber)
        {
          _maxItems_ = ((JsonParsedNumber)node).asBigInteger();
        }
        else 
        {
          throw new ParserErrorException("maxItems must be an instance of JsonParsedNumber not " + node.getClass().getName(), node.getContext());
        }
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

      node = jsonInitialiser.get("additionalProperties");
      if(node == null || node instanceof JsonNull)
      {
        _xXXadditionalProperties_ = null;
      }
      else
      {
    
        _xXXadditionalProperties_ = AdditionalProperties.FACTORY.newInstance(node, jsonInitialiser.getModelRegistry());
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

      node = jsonInitialiser.get("x-canon-cardinality");
      if(node == null || node instanceof JsonNull)
      {
        _xCanonCardinality_ = null;
      }
      else
      {
        if(node instanceof JsonString)
        {
          _xCanonCardinality_ = CanonCardinality.deserialize(((JsonString)node).asString());
        }
        else 
        {
          throw new ParserErrorException("x-canon-cardinality must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
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
        _type_ = null;
      }
      else
      {
        if(node instanceof JsonString)
        {
          _type_ = ((JsonString)node).asString();
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

      node = jsonInitialiser.get("enum");
      if(node == null || node instanceof JsonNull)
      {
        _enum_ = null;
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
              throw new ParserErrorException("enum items must be an instance of JsonString not " + item0.getClass().getName(), item0.getContext());
            }
            itemSet0.add(itemValue0);
          }
          _enum_ = ImmutableSet.copyOf(itemSet0);
        }
        else 
        {
          throw new ParserErrorException("enum must be a JsonArray node not " + node.getClass().getName(), node.getContext());
        }
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

      node = jsonInitialiser.get("discriminator");
      if(node == null || node instanceof JsonNull)
      {
        _discriminator_ = null;
      }
      else
      {
    
        _discriminator_ = DiscriminatorObject.FACTORY.newInstance(node, jsonInitialiser.getModelRegistry());
      }

      node = jsonInitialiser.get("minItems");
      if(node == null || node instanceof JsonNull)
      {
        _minItems_ = null;
      }
      else
      {
        if(node instanceof JsonParsedNumber)
        {
          _minItems_ = ((JsonParsedNumber)node).asBigInteger();
        }
        else 
        {
          throw new ParserErrorException("minItems must be an instance of JsonParsedNumber not " + node.getClass().getName(), node.getContext());
        }
      }

      node = jsonInitialiser.get("oneOf");
      if(node == null || node instanceof JsonNull)
      {
        _oneOf_ = null;
      }
      else
      {
        if(node instanceof JsonArray)
        {
          Set<SchemaOrRef> itemSet0 = new HashSet<>();
          for(JsonDomNode item0 : (JsonArray)node)
          {
            SchemaOrRef itemValue0 = null;
    
            itemValue0 = SchemaOrRef.FACTORY.newInstance(item0, jsonInitialiser.getModelRegistry());
            itemSet0.add(itemValue0);
          }
          _oneOf_ = ImmutableSet.copyOf(itemSet0);
        }
        else 
        {
          throw new ParserErrorException("oneOf must be a JsonArray node not " + node.getClass().getName(), node.getContext());
        }
      }

      node = jsonInitialiser.get("x-canon-attributes");
      if(node == null || node instanceof JsonNull)
      {
        _xCanonAttributes_ = null;
      }
      else
      {
    
        _xCanonAttributes_ = CanonAttributes.FACTORY.newInstance(node, jsonInitialiser.getModelRegistry());
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

      node = jsonInitialiser.get("properties");
      if(node == null || node instanceof JsonNull)
      {
        _properties_ = null;
      }
      else
      {
    
        _properties_ = PropertiesObject.FACTORY.newInstance(node, jsonInitialiser.getModelRegistry());
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
      unknownKeys_ = jsonInitialiser.getCanonUnknownKeys();
    }
    else
    {
      ISchemaInstanceOrBuilder builder =  initialiser.getInstanceOrBuilder();

      if(builder == null)
      {
        throw new IllegalArgumentException("Initializer is not an JsonObjectEntityInitialiser but getInstanceOrBuilder() returns null");
      }
      _maxItems_ = builder.getMaxItems();
      _xCanonBuilderFacade_ = builder.getXCanonBuilderFacade();
      _xXXadditionalProperties_ = builder.getXXXadditionalProperties();
      _format_ = builder.getFormat();
      _xCanonCardinality_ = builder.getXCanonCardinality();
      _xCanonIdentifier_ = builder.getXCanonIdentifier();
      _type_ = builder.getType();
      _xCanonFacade_ = builder.getXCanonFacade();
      _enum_ = ImmutableSet.copyOf(builder.getEnum());
      _required_ = ImmutableSet.copyOf(builder.getRequired());
      _discriminator_ = builder.getDiscriminator();
      _minItems_ = builder.getMinItems();
      _oneOf_ = ImmutableSet.copyOf(builder.getOneOf());
      _xCanonAttributes_ = builder.getXCanonAttributes();
      _maximum_ = builder.getMaximum();
      _minimum_ = builder.getMinimum();
      _properties_ = builder.getProperties();
      _xCanonExtends_ = builder.getXCanonExtends();
      unknownKeys_ = builder.getCanonUnknownKeys();
    }
  }


  /**
   * Factory class for Schema.
   */
  public static class Factory extends ObjectEntity.Factory<Schema>
  {
    @Override
    public String getCanonType()
    {
      return TYPE_ID;
    }

    @Override
    public Schema newInstance(JsonDomNode node, ModelRegistry modelRegistry)
    {
      if(node instanceof JsonObject)
      {
        return new Schema(new JsonInitialiser((JsonObject)node, modelRegistry));
      }

      if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
      {
        throw new ParserErrorException("Schema must be an Object node not " + node.getClass().getName(), node.getContext());
      }
      else
      {
        return null;
      }
    }
  }

  /**
   * Abstract Initialiser for Schema
   */
  public interface Initialiser extends IObjectEntityInitialiser
  {
    /**
     * Return an instance or builder containing the values for a new instance.
     * 
     * @return an instance or builder containing the values for a new instance.
     */
    ISchemaInstanceOrBuilder getInstanceOrBuilder();
  }

  /**
   * JSON Initialiser for Schema
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
    public ISchemaInstanceOrBuilder getInstanceOrBuilder()
    {
      return null;
    }
  }

  /**
   * Abstract builder for Schema. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <T> The concrete type of the builder, used for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends SchemaEntity>
// super class name
    extends ObjectEntity.AbstractBuilder<T,B>
    implements ISchemaInstanceOrBuilder, Initialiser
  {
    protected BigInteger                 _maxItems_;
    protected Boolean                    _xCanonBuilderFacade_;
    protected AdditionalProperties       _xXXadditionalProperties_;
    protected String                     _format_;
    protected CanonCardinality           _xCanonCardinality_;
    protected String                     _xCanonIdentifier_;
    protected String                     _type_;
    protected Boolean                    _xCanonFacade_;
    protected Set<String>                _enum_ = new HashSet<String>();
    protected Set<String>                _required_ = new HashSet<String>();
    protected DiscriminatorObject        _discriminator_;
    protected BigInteger                 _minItems_;
    protected Set<SchemaOrRef>           _oneOf_ = new HashSet<SchemaOrRef>();
    protected CanonAttributes            _xCanonAttributes_;
    protected BigDecimal                 _maximum_;
    protected BigDecimal                 _minimum_;
    protected PropertiesObject           _properties_;
    protected ReferenceObject            _xCanonExtends_;

    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }

    @Override
    public ISchemaInstanceOrBuilder getInstanceOrBuilder()
    {
      return this;
    }

    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);

      _maxItems_ = initial.getMaxItems();
      _xCanonBuilderFacade_ = initial.getXCanonBuilderFacade();
      _xXXadditionalProperties_ = initial.getXXXadditionalProperties();
      _format_ = initial.getFormat();
      _xCanonCardinality_ = initial.getXCanonCardinality();
      _xCanonIdentifier_ = initial.getXCanonIdentifier();
      _type_ = initial.getType();
      _xCanonFacade_ = initial.getXCanonFacade();
      _enum_ = ImmutableSet.copyOf(initial.getEnum());
      _required_ = ImmutableSet.copyOf(initial.getRequired());
      _discriminator_ = initial.getDiscriminator();
      _minItems_ = initial.getMinItems();
      _oneOf_ = ImmutableSet.copyOf(initial.getOneOf());
      _xCanonAttributes_ = initial.getXCanonAttributes();
      _maximum_ = initial.getMaximum();
      _minimum_ = initial.getMinimum();
      _properties_ = initial.getProperties();
      _xCanonExtends_ = initial.getXCanonExtends();
    }

    @Override
    public T withValues(JsonObject jsonObject, ModelRegistry modelRegistry)
    {
      if(jsonObject.containsKey("maxItems"))
      {
        JsonDomNode  node = jsonObject.get("maxItems");
        if(node instanceof JsonParsedNumber)
        {
          _maxItems_ = ((JsonParsedNumber)node).asBigInteger();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("maxItems must be an instance of JsonParsedNumber not " + node.getClass().getName(), node.getContext());
        }
      }
      if(jsonObject.containsKey("x-canon-builderFacade"))
      {
        JsonDomNode  node = jsonObject.get("x-canon-builderFacade");
        if(node instanceof JsonBoolean)
        {
          _xCanonBuilderFacade_ = ((JsonBoolean)node).asBoolean();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("x-canon-builderFacade must be an instance of JsonBoolean not " + node.getClass().getName(), node.getContext());
        }
      }
      if(jsonObject.containsKey("XXXadditionalProperties"))
      {
        JsonDomNode  node = jsonObject.get("XXXadditionalProperties");
    
        _xXXadditionalProperties_ = AdditionalProperties.FACTORY.newInstance(node, modelRegistry);
      }
      if(jsonObject.containsKey("format"))
      {
        JsonDomNode  node = jsonObject.get("format");
        if(node instanceof JsonString)
        {
          _format_ = ((JsonString)node).asString();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("format must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }
      if(jsonObject.containsKey("x-canon-cardinality"))
      {
        JsonDomNode  node = jsonObject.get("x-canon-cardinality");
        if(node instanceof JsonString)
        {
          _xCanonCardinality_ = CanonCardinality.deserialize(((JsonString)node).asString());
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("x-canon-cardinality must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }
      if(jsonObject.containsKey("x-canon-identifier"))
      {
        JsonDomNode  node = jsonObject.get("x-canon-identifier");
        if(node instanceof JsonString)
        {
          _xCanonIdentifier_ = ((JsonString)node).asString();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("x-canon-identifier must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }
      if(jsonObject.containsKey("type"))
      {
        JsonDomNode  node = jsonObject.get("type");
        if(node instanceof JsonString)
        {
          _type_ = ((JsonString)node).asString();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("type must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }
      if(jsonObject.containsKey("x-canon-facade"))
      {
        JsonDomNode  node = jsonObject.get("x-canon-facade");
        if(node instanceof JsonBoolean)
        {
          _xCanonFacade_ = ((JsonBoolean)node).asBoolean();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("x-canon-facade must be an instance of JsonBoolean not " + node.getClass().getName(), node.getContext());
        }
      }
      if(jsonObject.containsKey("enum"))
      {
        JsonDomNode  node = jsonObject.get("enum");
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
              throw new ParserErrorException("enum items must be an instance of JsonString not " + item0.getClass().getName(), item0.getContext());
            }
            itemSet0.add(itemValue0);
          }
          _enum_ = ImmutableSet.copyOf(itemSet0);
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("enum must be a JsonArray node not " + node.getClass().getName(), node.getContext());
        }
      }
      if(jsonObject.containsKey("required"))
      {
        JsonDomNode  node = jsonObject.get("required");
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
      if(jsonObject.containsKey("discriminator"))
      {
        JsonDomNode  node = jsonObject.get("discriminator");
    
        _discriminator_ = DiscriminatorObject.FACTORY.newInstance(node, modelRegistry);
      }
      if(jsonObject.containsKey("minItems"))
      {
        JsonDomNode  node = jsonObject.get("minItems");
        if(node instanceof JsonParsedNumber)
        {
          _minItems_ = ((JsonParsedNumber)node).asBigInteger();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("minItems must be an instance of JsonParsedNumber not " + node.getClass().getName(), node.getContext());
        }
      }
      if(jsonObject.containsKey("oneOf"))
      {
        JsonDomNode  node = jsonObject.get("oneOf");
        if(node instanceof JsonArray)
        {
          Set<SchemaOrRef> itemSet0 = new HashSet<>();
          for(JsonDomNode item0 : (JsonArray)node)
          {
            SchemaOrRef itemValue0 = null;
    
            itemValue0 = SchemaOrRef.FACTORY.newInstance(item0, modelRegistry);
            itemSet0.add(itemValue0);
          }
          _oneOf_ = ImmutableSet.copyOf(itemSet0);
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("oneOf must be a JsonArray node not " + node.getClass().getName(), node.getContext());
        }
      }
      if(jsonObject.containsKey("x-canon-attributes"))
      {
        JsonDomNode  node = jsonObject.get("x-canon-attributes");
    
        _xCanonAttributes_ = CanonAttributes.FACTORY.newInstance(node, modelRegistry);
      }
      if(jsonObject.containsKey("maximum"))
      {
        JsonDomNode  node = jsonObject.get("maximum");
        if(node instanceof JsonParsedNumber)
        {
          _maximum_ = ((JsonParsedNumber)node).asBigDecimal();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("maximum must be an instance of JsonParsedNumber not " + node.getClass().getName(), node.getContext());
        }
      }
      if(jsonObject.containsKey("minimum"))
      {
        JsonDomNode  node = jsonObject.get("minimum");
        if(node instanceof JsonParsedNumber)
        {
          _minimum_ = ((JsonParsedNumber)node).asBigDecimal();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("minimum must be an instance of JsonParsedNumber not " + node.getClass().getName(), node.getContext());
        }
      }
      if(jsonObject.containsKey("properties"))
      {
        JsonDomNode  node = jsonObject.get("properties");
    
        _properties_ = PropertiesObject.FACTORY.newInstance(node, modelRegistry);
      }
      if(jsonObject.containsKey("x-canon-extends"))
      {
        JsonDomNode  node = jsonObject.get("x-canon-extends");
    
        _xCanonExtends_ = ReferenceObject.FACTORY.newInstance(node, modelRegistry);
      }
      return super.withValues(jsonObject, modelRegistry);
    }

    /* void populateAllFields(List<Object> result)
    {
      result.add(_maxItems_);
      result.add(_xCanonBuilderFacade_);
      result.add(_xXXadditionalProperties_);
      result.add(_format_);
      result.add(_xCanonCardinality_);
      result.add(_xCanonIdentifier_);
      result.add(_type_);
      result.add(_xCanonFacade_);
      result.add(_enum_);
      result.add(_required_);
      result.add(_discriminator_);
      result.add(_minItems_);
      result.add(_oneOf_);
      result.add(_xCanonAttributes_);
      result.add(_maximum_);
      result.add(_minimum_);
      result.add(_properties_);
      result.add(_xCanonExtends_);
    }*/

    /**
     * Return the value of the maxItems attribute.
     *
     * @return the value of the maxItems attribute.
     */
    @Override
    public @Nullable BigInteger getMaxItems()
    {
      return _maxItems_;
    }

    /**
     * Set the value of the maxItems attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withMaxItems(BigInteger value)
    {
      _maxItems_ = value;
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
     * Return the value of the XXXadditionalProperties attribute.
     *
     * @return the value of the XXXadditionalProperties attribute.
     */
    @Override
    public @Nullable AdditionalProperties getXXXadditionalProperties()
    {
      return _xXXadditionalProperties_;
    }

    /**
     * Set the value of the XXXadditionalProperties attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withXXXadditionalProperties(AdditionalProperties value)
    {
      _xXXadditionalProperties_ = value;
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
     * Return the value of the x-canon-cardinality attribute.
     *
     * @return the value of the x-canon-cardinality attribute.
     */
    @Override
    public @Nullable CanonCardinality getXCanonCardinality()
    {
      return _xCanonCardinality_;
    }

    /**
     * Set the value of the x-canon-cardinality attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withXCanonCardinality(CanonCardinality value)
    {
      _xCanonCardinality_ = value;
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
    public @Nullable String getType()
    {
      return _type_;
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
      _type_ = value;
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
     * Return the value of the enum attribute.
     *
     * @return the value of the enum attribute.
     */
    @Override
    public @Nullable Set<String> getEnum()
    {
      return _enum_;
    }

    /**
     * Set the value of the enum attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withEnum(Set<String> value)
    {
      _enum_ = ImmutableSet.copyOf(value);
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

    /**
     * Return the value of the minItems attribute.
     *
     * @return the value of the minItems attribute.
     */
    @Override
    public @Nullable BigInteger getMinItems()
    {
      return _minItems_;
    }

    /**
     * Set the value of the minItems attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withMinItems(BigInteger value)
    {
      _minItems_ = value;
      return self();
    }

    /**
     * Return the value of the oneOf attribute.
     *
     * @return the value of the oneOf attribute.
     */
    @Override
    public @Nullable Set<SchemaOrRef> getOneOf()
    {
      return _oneOf_;
    }

    /**
     * Set the value of the oneOf attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withOneOf(Set<SchemaOrRef> value)
    {
      _oneOf_ = ImmutableSet.copyOf(value);
      return self();
    }

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

    /**
     * Return the value of the properties attribute.
     *
     * @return the value of the properties attribute.
     */
    @Override
    public @Nullable PropertiesObject getProperties()
    {
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
      _properties_ = value;
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


    @Override
    public JsonObject getJson()
    {
      JsonObject.Builder builder = new JsonObject.Builder();

      builder.addIfNotNull(JSON_TYPE, SchemaEntity.TYPE_ID);
      builder.addIfNotNull(JSON_VERSION, SchemaEntity.TYPE_VERSION);

      populateJson(builder);

      return builder.build();
    }
//T1 entity Schema OBJECT
    @Override
    public void populateJson(JsonObject.Builder builder)
    {
      super.populateJson(builder);

      if(getMaxItems() != null)
      {
          builder.addIfNotNull("maxItems", getMaxItems());
      }

      if(getXCanonBuilderFacade() != null)
      {
          builder.addIfNotNull("x-canon-builderFacade", getXCanonBuilderFacade());
      }

      if(getXXXadditionalProperties() != null)
      {
          builder.addIfNotNull("XXXadditionalProperties", getXXXadditionalProperties().getJson());
      }

      if(getFormat() != null)
      {
          builder.addIfNotNull("format", getFormat());
      }

      if(getXCanonCardinality() != null)
      {
          builder.addIfNotNull("x-canon-cardinality", getXCanonCardinality().getValue());
      }

      if(getXCanonIdentifier() != null)
      {
          builder.addIfNotNull("x-canon-identifier", getXCanonIdentifier());
      }

      if(getType() != null)
      {
          builder.addIfNotNull("type", getType());
      }

      if(getXCanonFacade() != null)
      {
          builder.addIfNotNull("x-canon-facade", getXCanonFacade());
      }

      if(getEnum() != null)
      {
          JsonArray.Builder arrayBuilder = new JsonArray.Builder();
          for(String item : getEnum())
          {
            arrayBuilder.with(item);
          }
          builder.with("enum", arrayBuilder.build());
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

      if(getDiscriminator() != null)
      {
          builder.addIfNotNull("discriminator", getDiscriminator().getJson());
      }

      if(getMinItems() != null)
      {
          builder.addIfNotNull("minItems", getMinItems());
      }

      if(getOneOf() != null)
      {
          JsonArray.Builder arrayBuilder = new JsonArray.Builder();
          for(SchemaOrRef item : getOneOf())
          {
            arrayBuilder.with(item.getJson());
          }
          builder.with("oneOf", arrayBuilder.build());
      }

      if(getXCanonAttributes() != null)
      {
          builder.addIfNotNull("x-canon-attributes", getXCanonAttributes().getJson());
      }

      if(getMaximum() != null)
      {
          builder.addIfNotNull("maximum", getMaximum());
      }

      if(getMinimum() != null)
      {
          builder.addIfNotNull("minimum", getMinimum());
      }

      if(getProperties() != null)
      {
          builder.addIfNotNull("properties", getProperties().getJson());
      }

      if(getXCanonExtends() != null)
      {
          builder.addIfNotNull("x-canon-extends", getXCanonExtends().getJson());
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
   * Builder for Schema
   */
  public static class Builder extends Schema.AbstractBuilder<Builder, Schema>
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
    public Builder(Schema initial)
    {
      super(Builder.class, initial);
    }

    @Override
    protected Schema construct()
    {
      return new Schema(this);
    }
  }


  /**
   * Return the value of the maxItems attribute.
   *
   * @return the value of the maxItems attribute.
   */
  public @Nullable BigInteger getMaxItems()
  {
    return _maxItems_;
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
   * Return the value of the XXXadditionalProperties attribute.
   *
   * @return the value of the XXXadditionalProperties attribute.
   */
  public @Nullable AdditionalProperties getXXXadditionalProperties()
  {
    return _xXXadditionalProperties_;
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
   * Return the value of the x-canon-cardinality attribute.
   *
   * @return the value of the x-canon-cardinality attribute.
   */
  public @Nullable CanonCardinality getXCanonCardinality()
  {
    return _xCanonCardinality_;
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
  public @Nullable String getType()
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
   * Return the value of the enum attribute.
   *
   * @return the value of the enum attribute.
   */
  public @Nullable Set<String> getEnum()
  {
    return _enum_;
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
   * Return the value of the discriminator attribute.
   *
   * @return the value of the discriminator attribute.
   */
  public @Nullable DiscriminatorObject getDiscriminator()
  {
    return _discriminator_;
  }

  /**
   * Return the value of the minItems attribute.
   *
   * @return the value of the minItems attribute.
   */
  public @Nullable BigInteger getMinItems()
  {
    return _minItems_;
  }

  /**
   * Return the value of the oneOf attribute.
   *
   * @return the value of the oneOf attribute.
   */
  public @Nullable Set<SchemaOrRef> getOneOf()
  {
    return _oneOf_;
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
   * Return the value of the maximum attribute.
   *
   * @return the value of the maximum attribute.
   */
  public @Nullable BigDecimal getMaximum()
  {
    return _maximum_;
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

  /**
   * Return the value of the properties attribute.
   *
   * @return the value of the properties attribute.
   */
  public @Nullable PropertiesObject getProperties()
  {
    return _properties_;
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

  @Override
  public boolean equals(Object obj)
  {
    if(obj instanceof SchemaEntity)
      return toString().equals(((SchemaEntity)obj).toString());

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