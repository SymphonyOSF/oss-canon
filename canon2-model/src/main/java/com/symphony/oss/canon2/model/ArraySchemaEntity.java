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
 *    At                   2020-11-10 17:41:51 GMT
 *----------------------------------------------------------------------------------------------------
 */
// importFields
// importField minItems nullable is Nullable
// importType minItems
// importType minItems
// importField maxItems nullable is Nullable
// importType maxItems
// importType maxItems
// importField x-canon-builderFacade nullable is Nullable
// importType x-canon-builderFacade
// importType x-canon-builderFacade
// importField x-canon-cardinality nullable is Nullable
// importType CanonCardinality
// importType CanonCardinality
// importField x-canon-identifier nullable is Nullable
// importType x-canon-identifier
// importType x-canon-identifier
// importField type nullable is Nonnull
// importType type
// importType type
// importField x-canon-facade nullable is Nullable
// importType x-canon-facade
// importType x-canon-facade
// importField items nullable is Nonnull
// importType SchemaOrRef
// importType SchemaOrRef
// importField discriminator nullable is Nullable
// importType DiscriminatorObject
// importType DiscriminatorObject
  // innerClass class com.symphony.oss.canon2.generator.java.JavaNumberSchemaTemplateModel
  // innerClass minItems
         
    // innerClass minItems isPrimitive
        
        
        
  // innerClass class com.symphony.oss.canon2.generator.java.JavaNumberSchemaTemplateModel
  // innerClass maxItems
         
    // innerClass maxItems isPrimitive
        
        
        
  // innerClass class com.symphony.oss.canon2.generator.java.JavaBooleanSchemaTemplateModel
  // innerClass x-canon-builderFacade
         
    // innerClass x-canon-builderFacade isPrimitive
        
        
        
  // innerClass class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel
  // innerClass x-canon-identifier
         
    // innerClass x-canon-identifier isPrimitive
        
        
        
  // innerClass class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel
  // innerClass type
         
    // innerClass type isPrimitive
        
        
        
  // innerClass class com.symphony.oss.canon2.generator.java.JavaBooleanSchemaTemplateModel
  // innerClass x-canon-facade
         
    // innerClass x-canon-facade isPrimitive
        
        
        

package com.symphony.oss.canon2.model;

import java.math.BigInteger;
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
 * Implementation for Object ArraySchema
 * Generated from ArraySchema at {entity.context.path}
 */
@Immutable
public abstract class ArraySchemaEntity extends ObjectEntity
{
  /** Type ID */
  public static final String  TYPE_ID = "com.symphony.oss.canon2.model.ArraySchema";
  /** Type version */
  public static final String  TYPE_VERSION = "1.0";
  /** Factory instance */
  public static final Factory FACTORY = new Factory();

  private final ImmutableSet<String>        unknownKeys_;
  private final ArraySchema.MinItems       _minItems_;
  private final ArraySchema.MaxItems       _maxItems_;
  private final Boolean                    _xCanonBuilderFacade_;
  private final CanonCardinality           _xCanonCardinality_;
  private final String                     _xCanonIdentifier_;
  private final ArraySchema.Type           _type_;
  private final Boolean                    _xCanonFacade_;
  private final SchemaOrRef                _items_;
  private final DiscriminatorObject        _discriminator_;

  /**
   * Constructor.
   *
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  public ArraySchemaEntity(Initialiser initialiser)
  {
    super(initialiser);

    if(initialiser instanceof JsonObjectEntityInitialiser)
    {
      JsonObjectEntityInitialiser jsonInitialiser = (JsonObjectEntityInitialiser)initialiser;

      JsonDomNode  node;

      node = jsonInitialiser.get("minItems");
      if(node == null || node instanceof JsonNull)
      {
        _minItems_ = null;
      }
      else
      {
        if(node instanceof JsonParsedNumber)
        {
// schema.class class com.symphony.oss.canon2.generator.java.JavaNumberSchemaTemplateModel name minItems type ArraySchema.MinItems javaType BigInteger
          _minItems_ = new ArraySchema.MinItems(((JsonParsedNumber)node).asBigInteger());
        }
        else 
        {
          throw new ParserErrorException("minItems must be an instance of JsonParsedNumber not " + node.getClass().getName(), node.getContext());
        }
      }

      node = jsonInitialiser.get("maxItems");
      if(node == null || node instanceof JsonNull)
      {
        _maxItems_ = null;
      }
      else
      {
        if(node instanceof JsonParsedNumber)
        {
// schema.class class com.symphony.oss.canon2.generator.java.JavaNumberSchemaTemplateModel name maxItems type ArraySchema.MaxItems javaType BigInteger
          _maxItems_ = new ArraySchema.MaxItems(((JsonParsedNumber)node).asBigInteger());
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
// schema.class class com.symphony.oss.canon2.generator.java.JavaBooleanSchemaTemplateModel name x-canon-builderFacade type Boolean javaType Boolean
          _xCanonBuilderFacade_ = ((JsonBoolean)node).asBoolean();
        }
        else 
        {
          throw new ParserErrorException("x-canon-builderFacade must be an instance of JsonBoolean not " + node.getClass().getName(), node.getContext());
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
// schema.class class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel name CanonCardinality type CanonCardinality javaType String
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
// schema.class class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel name x-canon-identifier type String javaType String
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
// schema.class class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel name type type ArraySchema.Type javaType String
          _type_ = ArraySchema.Type.deserialize(((JsonString)node).asString());
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
// schema.class class com.symphony.oss.canon2.generator.java.JavaBooleanSchemaTemplateModel name x-canon-facade type Boolean javaType Boolean
          _xCanonFacade_ = ((JsonBoolean)node).asBoolean();
        }
        else 
        {
          throw new ParserErrorException("x-canon-facade must be an instance of JsonBoolean not " + node.getClass().getName(), node.getContext());
        }
      }

      node = jsonInitialiser.get("items");
      if(node == null || node instanceof JsonNull)
      {
        throw new ParserErrorException("items is required.", jsonInitialiser.getJson().getContext());
      }
      else
      {
        _items_ = SchemaOrRef.FACTORY.newInstance(node, jsonInitialiser.getModelRegistry());
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
      IArraySchemaInstanceOrBuilder builder =  initialiser.getInstanceOrBuilder();

      if(builder == null)
      {
        throw new IllegalArgumentException("Initializer is not an JsonObjectEntityInitialiser but getInstanceOrBuilder() returns null");
      }
      _minItems_ = builder.getMinItems();
      _maxItems_ = builder.getMaxItems();
      _xCanonBuilderFacade_ = builder.getXCanonBuilderFacade();
      _xCanonCardinality_ = builder.getXCanonCardinality();
      _xCanonIdentifier_ = builder.getXCanonIdentifier();
      _type_ = builder.getType();
      if(_type_ == null)
        throw new IllegalArgumentException("type is required.");
  
      _xCanonFacade_ = builder.getXCanonFacade();
      _items_ = builder.getItems();
      if(_items_ == null)
        throw new IllegalArgumentException("items is required.");
  
      _discriminator_ = builder.getDiscriminator();
      unknownKeys_ = builder.getCanonUnknownKeys();
    }
  }


  /**
   * Factory class for ArraySchema.
   */
  public static class Factory extends ObjectEntity.Factory<ArraySchema>
  {
    @Override
    public String getCanonType()
    {
      return TYPE_ID;
    }

    @Override
    public ArraySchema newInstance(JsonDomNode node, ModelRegistry modelRegistry)
    {
      if(node instanceof JsonObject)
      {
        return new ArraySchema(new JsonInitialiser((JsonObject)node, modelRegistry));
      }

      if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
      {
        throw new ParserErrorException("ArraySchema must be an Object node not " + node.getClass().getName(), node.getContext());
      }
      else
      {
        return null;
      }
    }
  }

  /**
   * Abstract Initialiser for ArraySchema
   */
  public interface Initialiser extends IObjectEntityInitialiser
  {
    /**
     * Return an instance or builder containing the values for a new instance.
     * 
     * @return an instance or builder containing the values for a new instance.
     */
    IArraySchemaInstanceOrBuilder getInstanceOrBuilder();
  }

  /**
   * JSON Initialiser for ArraySchema
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
    public IArraySchemaInstanceOrBuilder getInstanceOrBuilder()
    {
      return null;
    }
  }

  /**
   * Abstract builder for ArraySchema. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <T> The concrete type of the builder, used for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends ArraySchemaEntity>
// super class name
    extends ObjectEntity.AbstractBuilder<T,B>
    implements IArraySchemaInstanceOrBuilder, Initialiser
  {
    protected ArraySchema.MinItems       _minItems_;
    protected ArraySchema.MaxItems       _maxItems_;
    protected Boolean                    _xCanonBuilderFacade_;
    protected CanonCardinality           _xCanonCardinality_;
    protected String                     _xCanonIdentifier_;
    protected ArraySchema.Type           _type_;
    protected Boolean                    _xCanonFacade_;
    protected SchemaOrRef                _items_;
    protected DiscriminatorObject        _discriminator_;

    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }

    @Override
    public IArraySchemaInstanceOrBuilder getInstanceOrBuilder()
    {
      return this;
    }

    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);

      _minItems_ = initial.getMinItems();
      _maxItems_ = initial.getMaxItems();
      _xCanonBuilderFacade_ = initial.getXCanonBuilderFacade();
      _xCanonCardinality_ = initial.getXCanonCardinality();
      _xCanonIdentifier_ = initial.getXCanonIdentifier();
      _type_ = initial.getType();
      _xCanonFacade_ = initial.getXCanonFacade();
      _items_ = initial.getItems();
      _discriminator_ = initial.getDiscriminator();
    }

    @Override
    public T withValues(JsonObject jsonObject, ModelRegistry modelRegistry)
    {
      if(jsonObject.containsKey("minItems"))
      {
        JsonDomNode  node = jsonObject.get("minItems");
        if(node instanceof JsonParsedNumber)
        {
// schema.class class com.symphony.oss.canon2.generator.java.JavaNumberSchemaTemplateModel name minItems type ArraySchema.MinItems javaType BigInteger
          _minItems_ = new ArraySchema.MinItems(((JsonParsedNumber)node).asBigInteger());
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("minItems must be an instance of JsonParsedNumber not " + node.getClass().getName(), node.getContext());
        }
      }
      if(jsonObject.containsKey("maxItems"))
      {
        JsonDomNode  node = jsonObject.get("maxItems");
        if(node instanceof JsonParsedNumber)
        {
// schema.class class com.symphony.oss.canon2.generator.java.JavaNumberSchemaTemplateModel name maxItems type ArraySchema.MaxItems javaType BigInteger
          _maxItems_ = new ArraySchema.MaxItems(((JsonParsedNumber)node).asBigInteger());
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
// schema.class class com.symphony.oss.canon2.generator.java.JavaBooleanSchemaTemplateModel name x-canon-builderFacade type Boolean javaType Boolean
          _xCanonBuilderFacade_ = ((JsonBoolean)node).asBoolean();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("x-canon-builderFacade must be an instance of JsonBoolean not " + node.getClass().getName(), node.getContext());
        }
      }
      if(jsonObject.containsKey("x-canon-cardinality"))
      {
        JsonDomNode  node = jsonObject.get("x-canon-cardinality");
        if(node instanceof JsonString)
        {
// schema.class class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel name CanonCardinality type CanonCardinality javaType String
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
// schema.class class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel name x-canon-identifier type String javaType String
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
// schema.class class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel name type type ArraySchema.Type javaType String
          _type_ = ArraySchema.Type.deserialize(((JsonString)node).asString());
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
// schema.class class com.symphony.oss.canon2.generator.java.JavaBooleanSchemaTemplateModel name x-canon-facade type Boolean javaType Boolean
          _xCanonFacade_ = ((JsonBoolean)node).asBoolean();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("x-canon-facade must be an instance of JsonBoolean not " + node.getClass().getName(), node.getContext());
        }
      }
      if(jsonObject.containsKey("items"))
      {
        JsonDomNode  node = jsonObject.get("items");
        _items_ = SchemaOrRef.FACTORY.newInstance(node, modelRegistry);
      }
      if(jsonObject.containsKey("discriminator"))
      {
        JsonDomNode  node = jsonObject.get("discriminator");
        _discriminator_ = DiscriminatorObject.FACTORY.newInstance(node, modelRegistry);
      }
      return super.withValues(jsonObject, modelRegistry);
    }

    /* void populateAllFields(List<Object> result)
    {
      result.add(_minItems_);
      result.add(_maxItems_);
      result.add(_xCanonBuilderFacade_);
      result.add(_xCanonCardinality_);
      result.add(_xCanonIdentifier_);
      result.add(_type_);
      result.add(_xCanonFacade_);
      result.add(_items_);
      result.add(_discriminator_);
    }*/

    /**
     * Return the value of the minItems attribute.
     *
     * @return the value of the minItems attribute.
     */
    @Override
    public @Nullable ArraySchema.MinItems getMinItems()
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
    public T withMinItems(ArraySchema.MinItems value) //main
    {
      _minItems_ = value;
      return self();
    }

    /**
     * Set the value of the minItems attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withMinItems(BigInteger value) // primitive
    {
      _minItems_ = new ArraySchema.MinItems(value);
      return self();
    }

    /**
     * Return the value of the maxItems attribute.
     *
     * @return the value of the maxItems attribute.
     */
    @Override
    public @Nullable ArraySchema.MaxItems getMaxItems()
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
    public T withMaxItems(ArraySchema.MaxItems value) //main
    {
      _maxItems_ = value;
      return self();
    }

    /**
     * Set the value of the maxItems attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withMaxItems(BigInteger value) // primitive
    {
      _maxItems_ = new ArraySchema.MaxItems(value);
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
    public T withXCanonBuilderFacade(Boolean value) //main
    {
      _xCanonBuilderFacade_ = value;
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
    public T withXCanonCardinality(CanonCardinality value) //main
    {
      _xCanonCardinality_ = value;
      return self();
    }

    /**
     * Set the value of the x-canon-cardinality attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withXCanonCardinality(String value) // primitive
    {
      _xCanonCardinality_ = CanonCardinality.deserialize(value);
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
    public T withXCanonIdentifier(String value) //main
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
    public @Nonnull ArraySchema.Type getType()
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
    public T withType(ArraySchema.Type value) //main
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
    public T withType(String value) // primitive
    {
      if(value == null)
        throw new IllegalArgumentException("type is required.");

      _type_ = ArraySchema.Type.deserialize(value);
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
    public T withXCanonFacade(Boolean value) //main
    {
      _xCanonFacade_ = value;
      return self();
    }

    /**
     * Return the value of the items attribute.
     *
     * @return the value of the items attribute.
     */
    @Override
    public @Nonnull SchemaOrRef getItems()
    {
      if(_items_ == null)
        throw new IllegalStateException("Unexpected null value encountered");
      return _items_;
    }

    /**
     * Set the value of the items attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withItems(SchemaOrRef value) //main
    {
        if(value == null)
          throw new IllegalArgumentException("items is required.");
  
      _items_ = value;
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
    public T withDiscriminator(DiscriminatorObject value) //main
    {
      _discriminator_ = value;
      return self();
    }


    @Override
    public JsonObject getJson()
    {
      JsonObject.Builder builder = new JsonObject.Builder();

      builder.addIfNotNull(JSON_TYPE, ArraySchemaEntity.TYPE_ID);
      builder.addIfNotNull(JSON_VERSION, ArraySchemaEntity.TYPE_VERSION);

      populateJson(builder);

      return builder.build();
    }

    @Override
    public void populateJson(JsonObject.Builder builder)
    {
      super.populateJson(builder);

      if(getMinItems() != null)
      {
        builder.addIfNotNull("minItems", getMinItems().getValue());
      }

      if(getMaxItems() != null)
      {
        builder.addIfNotNull("maxItems", getMaxItems().getValue());
      }

      if(getXCanonBuilderFacade() != null)
      {
        builder.addIfNotNull("x-canon-builderFacade", getXCanonBuilderFacade());
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
        builder.addIfNotNull("type", getType().getValue());
      }

      if(getXCanonFacade() != null)
      {
        builder.addIfNotNull("x-canon-facade", getXCanonFacade());
      }

      if(getItems() != null)
      {
        builder.addIfNotNull("items", getItems().getJson());
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
      faultAccumulator.checkNotNull(_items_, "items");
    }
  }

  @Override
  public ImmutableSet<String> getCanonUnknownKeys()
  {
    return unknownKeys_;
  }

  /**
   * Builder for ArraySchema
   */
  public static class Builder extends ArraySchema.AbstractBuilder<Builder, ArraySchema>
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
    public Builder(ArraySchema initial)
    {
      super(Builder.class, initial);
    }

    @Override
    protected ArraySchema construct()
    {
      return new ArraySchema(this);
    }
  }


  /**
   * Return the value of the minItems attribute.
   *
   * @return the value of the minItems attribute.
   */
  public @Nullable ArraySchema.MinItems getMinItems()
  {
    return _minItems_;
  }

  /**
   * Return the value of the maxItems attribute.
   *
   * @return the value of the maxItems attribute.
   */
  public @Nullable ArraySchema.MaxItems getMaxItems()
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
  public @Nonnull ArraySchema.Type getType()
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
   * Return the value of the items attribute.
   *
   * @return the value of the items attribute.
   */
  public @Nonnull SchemaOrRef getItems()
  {
    return _items_;
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
    if(obj instanceof ArraySchemaEntity)
      return toString().equals(((ArraySchemaEntity)obj).toString());

    return false;
  }

  @Override
  public int hashCode()
  {
    return toString().hashCode();
  }

// entity.additionalProperties??
// innerClasses
  // innerClass minItems INTEGER class com.symphony.oss.canon2.core.SchemaTemplateModelType
        // Primitive inner class class com.symphony.oss.canon2.generator.java.JavaNumberSchemaTemplateModel
  /**
   * TypeDef implementation for canon.minItems
   * Generated from minItems at {entity.context.path}
   */
  @Immutable
  public static class MinItems extends TypeDef implements Comparable<MinItems>
  {
    /** Minimum value */
    public static final BigInteger           MINIMUM           = new BigInteger("0");
    /** Minimum value is exclusive */
    public static final boolean              EXCLUSIVE_MINIMUM = false;
    @Deprecated
    private static Builder theBuilder = new Builder();
    
    private final BigInteger value_;

    /**
     * Constructor from a BigInteger value.
     *
     * @param value the value of the required instance.
     */
    public MinItems(@Nonnull BigInteger value)
    {
      value_ = Objects.requireNonNull(value);
// schema.class class com.symphony.oss.canon2.generator.java.JavaNumberSchemaTemplateModel
// name value
    if(value.compareTo(MINIMUM) < 0)
      throw new IllegalArgumentException("Value " + value + " of value is less than the minimum allowed of 0");
    }

    /**
     * Constructor from a JSON value node.
     *
     * @param value the value of the required instance.
     */
    MinItems(@Nonnull JsonDomNode node)
    {
      Objects.requireNonNull(node);

      if(node instanceof JsonParsedNumber)
      {
        value_ = ((JsonParsedNumber)node).asBigInteger();
// schema.class class com.symphony.oss.canon2.generator.java.JavaNumberSchemaTemplateModel
// name value
    if(value_.compareTo(MINIMUM) < 0)
      throw new ParserErrorException("Value " + value_ + " of value is less than the minimum allowed of 0", node.getContext());
      }
      else
      {
        throw new IllegalArgumentException("value must be an instance of JsonParsedNumber not " + node.getClass().getName());
      }
    }
    
    /**
     * Return the wrapped value.
     * 
     * @return the wrapped value.
     */
    public BigInteger getValue()
    {
      return value_;
    }
    
    @Override
    public @Nonnull String toString()
    {
      return value_.toString();
    }
    
    @Override
    public int hashCode()
    {
      return value_.hashCode();
    }
    
    /**
     * Return the value as a BigInteger.
     * 
     * @return the value as a BigInteger.
     */
    public @Nonnull BigInteger asBigInteger()
    {
      return value_;
    }

    @Override
    public boolean equals(Object obj)
    {
      if(obj instanceof MinItems)
      {
        return getValue().equals(((MinItems)obj).getValue());
      }
      
      return false;
    }
    
    @Override
    public int compareTo(MinItems other)
    {
      return getValue().compareTo(other.getValue());
    }
    
    /**
     * Return a new Builder.
     *
     * @deprecated use new MinItems(BigInteger value)
     *
     * @return A new Builder.
     */
    @Deprecated
    public static Builder newBuilder()
    {
      return theBuilder;
    }
    
    /**
     * Builder.
     *
     * @deprecated use new MinItems(BigInteger value)
     *
     */
    @Deprecated
    public static class Builder
    {
      private Builder()
      {
      }

      /**
       * Return a new instance.
       *
       * @param value The value for the new instance.
       *
       * @deprecated use new MinItems(BigInteger value)
       *
       * @return A new instance.
       */
      @Deprecated
      public ArraySchema.MinItems build(@Nonnull BigInteger value)
      {
        return new ArraySchema.MinItems(value);
      }
      
      /**
       * Return the value from an instance.
       *
       * @param instance an instance.
       *
       * @deprecated use instance.getValue()
       *
       * @return the value from an instance.
       */
      @Deprecated
      public BigInteger toValue(MinItems instance)
      {
        return instance.getValue();
      }
    }
  }
  // innerClass maxItems INTEGER class com.symphony.oss.canon2.core.SchemaTemplateModelType
        // Primitive inner class class com.symphony.oss.canon2.generator.java.JavaNumberSchemaTemplateModel
  /**
   * TypeDef implementation for canon.maxItems
   * Generated from maxItems at {entity.context.path}
   */
  @Immutable
  public static class MaxItems extends TypeDef implements Comparable<MaxItems>
  {
    /** Minimum value */
    public static final BigInteger           MINIMUM           = new BigInteger("0");
    /** Minimum value is exclusive */
    public static final boolean              EXCLUSIVE_MINIMUM = false;
    @Deprecated
    private static Builder theBuilder = new Builder();
    
    private final BigInteger value_;

    /**
     * Constructor from a BigInteger value.
     *
     * @param value the value of the required instance.
     */
    public MaxItems(@Nonnull BigInteger value)
    {
      value_ = Objects.requireNonNull(value);
// schema.class class com.symphony.oss.canon2.generator.java.JavaNumberSchemaTemplateModel
// name value
    if(value.compareTo(MINIMUM) < 0)
      throw new IllegalArgumentException("Value " + value + " of value is less than the minimum allowed of 0");
    }

    /**
     * Constructor from a JSON value node.
     *
     * @param value the value of the required instance.
     */
    MaxItems(@Nonnull JsonDomNode node)
    {
      Objects.requireNonNull(node);

      if(node instanceof JsonParsedNumber)
      {
        value_ = ((JsonParsedNumber)node).asBigInteger();
// schema.class class com.symphony.oss.canon2.generator.java.JavaNumberSchemaTemplateModel
// name value
    if(value_.compareTo(MINIMUM) < 0)
      throw new ParserErrorException("Value " + value_ + " of value is less than the minimum allowed of 0", node.getContext());
      }
      else
      {
        throw new IllegalArgumentException("value must be an instance of JsonParsedNumber not " + node.getClass().getName());
      }
    }
    
    /**
     * Return the wrapped value.
     * 
     * @return the wrapped value.
     */
    public BigInteger getValue()
    {
      return value_;
    }
    
    @Override
    public @Nonnull String toString()
    {
      return value_.toString();
    }
    
    @Override
    public int hashCode()
    {
      return value_.hashCode();
    }
    
    /**
     * Return the value as a BigInteger.
     * 
     * @return the value as a BigInteger.
     */
    public @Nonnull BigInteger asBigInteger()
    {
      return value_;
    }

    @Override
    public boolean equals(Object obj)
    {
      if(obj instanceof MaxItems)
      {
        return getValue().equals(((MaxItems)obj).getValue());
      }
      
      return false;
    }
    
    @Override
    public int compareTo(MaxItems other)
    {
      return getValue().compareTo(other.getValue());
    }
    
    /**
     * Return a new Builder.
     *
     * @deprecated use new MaxItems(BigInteger value)
     *
     * @return A new Builder.
     */
    @Deprecated
    public static Builder newBuilder()
    {
      return theBuilder;
    }
    
    /**
     * Builder.
     *
     * @deprecated use new MaxItems(BigInteger value)
     *
     */
    @Deprecated
    public static class Builder
    {
      private Builder()
      {
      }

      /**
       * Return a new instance.
       *
       * @param value The value for the new instance.
       *
       * @deprecated use new MaxItems(BigInteger value)
       *
       * @return A new instance.
       */
      @Deprecated
      public ArraySchema.MaxItems build(@Nonnull BigInteger value)
      {
        return new ArraySchema.MaxItems(value);
      }
      
      /**
       * Return the value from an instance.
       *
       * @param instance an instance.
       *
       * @deprecated use instance.getValue()
       *
       * @return the value from an instance.
       */
      @Deprecated
      public BigInteger toValue(MaxItems instance)
      {
        return instance.getValue();
      }
    }
  }
  // innerClass x-canon-builderFacade BOOLEAN class com.symphony.oss.canon2.core.SchemaTemplateModelType
  // innerClass x-canon-identifier STRING class com.symphony.oss.canon2.core.SchemaTemplateModelType
  // innerClass type STRING class com.symphony.oss.canon2.core.SchemaTemplateModelType
  /**
   * Enum  type canon
   * Model canon
   * Generated from type at {entity.context.path}
   */
  @Immutable
  public static enum Type
  {
    /** ARRAY */
    ARRAY("array")
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
        case "array":
          return ARRAY;
          
        default:
          throw new IllegalArgumentException("No enum constant \"" + value + "\" in Type");
      }
    }
  }
  // innerClass x-canon-facade BOOLEAN class com.symphony.oss.canon2.core.SchemaTemplateModelType
}

/*----------------------------------------------------------------------------------------------------
 * End of template template/Object/_Entity.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */