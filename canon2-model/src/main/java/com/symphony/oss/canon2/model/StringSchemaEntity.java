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
// importField x-canon-attributes nullable is Nullable
// importType CanonAttributes
// importType CanonAttributes
// importField x-canon-builderFacade nullable is Nullable
// importType x-canon-builderFacade
// importType x-canon-builderFacade
// importField minLength nullable is Nullable
// importType minLength
// importType minLength
// importField format nullable is Nullable
// importType format
// importType format
// importField pattern nullable is Nullable
// importType pattern
// importType pattern
// importField x-canon-identifier nullable is Nullable
// importType x-canon-identifier
// importType x-canon-identifier
// importField type nullable is Nonnull
// importType type
// importType type
// importField x-canon-facade nullable is Nullable
// importType x-canon-facade
// importType x-canon-facade
// importField enum nullable is Nullable
// importType enum
// importType enum
// importType items BRUCE 001
// importField maxLength nullable is Nullable
// importType maxLength
// importType maxLength
  // innerClass class com.symphony.oss.canon2.generator.java.JavaBooleanSchemaTemplateModel
  // innerClass x-canon-builderFacade
         
    // innerClass x-canon-builderFacade isPrimitive
        
        
        
  // innerClass class com.symphony.oss.canon2.generator.java.JavaNumberSchemaTemplateModel
  // innerClass minLength
         
    // innerClass minLength isPrimitive
        
        
        
  // innerClass class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel
  // innerClass format
         
    // innerClass format isPrimitive
        
        
        
  // innerClass class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel
  // innerClass pattern
         
    // innerClass pattern isPrimitive
        
        
        
  // innerClass class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel
  // innerClass x-canon-identifier
         
    // innerClass x-canon-identifier isPrimitive
        
        
        
  // innerClass class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel
  // innerClass type
         
    // innerClass type isPrimitive
        
        
        
  // innerClass class com.symphony.oss.canon2.generator.java.JavaBooleanSchemaTemplateModel
  // innerClass x-canon-facade
         
    // innerClass x-canon-facade isPrimitive
        
        
        
  // innerClass class com.symphony.oss.canon2.generator.java.JavaArraySchemaTemplateModel
  // innerClass enum
         
        
        
        
  // innerClass class com.symphony.oss.canon2.generator.java.JavaNumberSchemaTemplateModel
  // innerClass maxLength
         
    // innerClass maxLength isPrimitive
        
        
        

package com.symphony.oss.canon2.model;

import java.math.BigInteger;
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
import com.symphony.oss.canon.json.model.JsonParsedNumber;
import com.symphony.oss.canon.json.model.JsonString;
import com.symphony.oss.canon2.runtime.java.IObjectEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.JsonObjectEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;
import com.symphony.oss.canon2.runtime.java.ObjectEntity;
import com.symphony.oss.canon2.runtime.java.TypeDef;
import com.symphony.oss.commons.fault.FaultAccumulator;

/**
 * Implementation for Object StringSchema
 * Generated from StringSchema at {entity.context.path}
 */
@Immutable
public abstract class StringSchemaEntity extends ObjectEntity
{
  /** Type ID */
  public static final String  TYPE_ID = "com.symphony.oss.canon2.model.StringSchema";
  /** Type version */
  public static final String  TYPE_VERSION = "1.0";
  /** Factory instance */
  public static final Factory FACTORY = new Factory();

  private final ImmutableSet<String>        unknownKeys_;
  private final CanonAttributes            _xCanonAttributes_;
  private final Boolean                    _xCanonBuilderFacade_;
  private final BigInteger                 _minLength_;
  private final String                     _format_;
  private final String                     _pattern_;
  private final String                     _xCanonIdentifier_;
  private final StringSchema.Type          _type_;
  private final Boolean                    _xCanonFacade_;
  private final Set<String>                _enum_;
  private final BigInteger                 _maxLength_;

  /**
   * Constructor.
   *
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  public StringSchemaEntity(Initialiser initialiser)
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
// schema.class class com.symphony.oss.canon2.generator.java.JavaBooleanSchemaTemplateModel name x-canon-builderFacade type Boolean javaType Boolean
          _xCanonBuilderFacade_ = ((JsonBoolean)node).asBoolean();
        }
        else 
        {
          throw new ParserErrorException("x-canon-builderFacade must be an instance of JsonBoolean not " + node.getClass().getName(), node.getContext());
        }
      }

      node = jsonInitialiser.get("minLength");
      if(node == null || node instanceof JsonNull)
      {
        _minLength_ = null;
      }
      else
      {
        if(node instanceof JsonParsedNumber)
        {
// schema.class class com.symphony.oss.canon2.generator.java.JavaNumberSchemaTemplateModel name minLength type BigInteger javaType BigInteger
          _minLength_ = ((JsonParsedNumber)node).asBigInteger();
        }
        else 
        {
          throw new ParserErrorException("minLength must be an instance of JsonParsedNumber not " + node.getClass().getName(), node.getContext());
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
// schema.class class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel name format type String javaType String
          _format_ = ((JsonString)node).asString();
        }
        else 
        {
          throw new ParserErrorException("format must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }

      node = jsonInitialiser.get("pattern");
      if(node == null || node instanceof JsonNull)
      {
        _pattern_ = null;
      }
      else
      {
        if(node instanceof JsonString)
        {
// schema.class class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel name pattern type String javaType String
          _pattern_ = ((JsonString)node).asString();
        }
        else 
        {
          throw new ParserErrorException("pattern must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
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
// schema.class class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel name type type StringSchema.Type javaType String
          _type_ = StringSchema.Type.deserialize(((JsonString)node).asString());
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
// schema.class class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel name items BRUCE 001 type String javaType String
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

      node = jsonInitialiser.get("maxLength");
      if(node == null || node instanceof JsonNull)
      {
        _maxLength_ = null;
      }
      else
      {
        if(node instanceof JsonParsedNumber)
        {
// schema.class class com.symphony.oss.canon2.generator.java.JavaNumberSchemaTemplateModel name maxLength type BigInteger javaType BigInteger
          _maxLength_ = ((JsonParsedNumber)node).asBigInteger();
        }
        else 
        {
          throw new ParserErrorException("maxLength must be an instance of JsonParsedNumber not " + node.getClass().getName(), node.getContext());
        }
      }
      unknownKeys_ = jsonInitialiser.getCanonUnknownKeys();
    }
    else
    {
      IStringSchemaInstanceOrBuilder builder =  initialiser.getInstanceOrBuilder();

      if(builder == null)
      {
        throw new IllegalArgumentException("Initializer is not an JsonObjectEntityInitialiser but getInstanceOrBuilder() returns null");
      }
      _xCanonAttributes_ = builder.getXCanonAttributes();
      _xCanonBuilderFacade_ = builder.getXCanonBuilderFacade();
      _minLength_ = builder.getMinLength();
      _format_ = builder.getFormat();
      _pattern_ = builder.getPattern();
      _xCanonIdentifier_ = builder.getXCanonIdentifier();
      _type_ = builder.getType();
      if(_type_ == null)
        throw new IllegalArgumentException("type is required.");
  
      _xCanonFacade_ = builder.getXCanonFacade();
      _enum_ = ImmutableSet.copyOf(builder.getEnum());
      _maxLength_ = builder.getMaxLength();
      unknownKeys_ = builder.getCanonUnknownKeys();
    }
  }


  /**
   * Factory class for StringSchema.
   */
  public static class Factory extends ObjectEntity.Factory<StringSchema>
  {
    @Override
    public String getCanonType()
    {
      return TYPE_ID;
    }

    @Override
    public StringSchema newInstance(JsonDomNode node, ModelRegistry modelRegistry)
    {
      if(node instanceof JsonObject)
      {
        return new StringSchema(new JsonInitialiser((JsonObject)node, modelRegistry));
      }

      if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
      {
        throw new ParserErrorException("StringSchema must be an Object node not " + node.getClass().getName(), node.getContext());
      }
      else
      {
        return null;
      }
    }
  }

  /**
   * Abstract Initialiser for StringSchema
   */
  public interface Initialiser extends IObjectEntityInitialiser
  {
    /**
     * Return an instance or builder containing the values for a new instance.
     * 
     * @return an instance or builder containing the values for a new instance.
     */
    IStringSchemaInstanceOrBuilder getInstanceOrBuilder();
  }

  /**
   * JSON Initialiser for StringSchema
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
    public IStringSchemaInstanceOrBuilder getInstanceOrBuilder()
    {
      return null;
    }
  }

  /**
   * Abstract builder for StringSchema. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <T> The concrete type of the builder, used for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends StringSchemaEntity>
// super class name
    extends ObjectEntity.AbstractBuilder<T,B>
    implements IStringSchemaInstanceOrBuilder, Initialiser
  {
    protected CanonAttributes            _xCanonAttributes_;
    protected Boolean                    _xCanonBuilderFacade_;
    protected BigInteger                 _minLength_;
    protected String                     _format_;
    protected String                     _pattern_;
    protected String                     _xCanonIdentifier_;
    protected StringSchema.Type          _type_;
    protected Boolean                    _xCanonFacade_;
    protected Set<String>                _enum_ = new HashSet<String>();
    protected BigInteger                 _maxLength_;

    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }

    @Override
    public IStringSchemaInstanceOrBuilder getInstanceOrBuilder()
    {
      return this;
    }

    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);

      _xCanonAttributes_ = initial.getXCanonAttributes();
      _xCanonBuilderFacade_ = initial.getXCanonBuilderFacade();
      _minLength_ = initial.getMinLength();
      _format_ = initial.getFormat();
      _pattern_ = initial.getPattern();
      _xCanonIdentifier_ = initial.getXCanonIdentifier();
      _type_ = initial.getType();
      _xCanonFacade_ = initial.getXCanonFacade();
      _enum_ = ImmutableSet.copyOf(initial.getEnum());
      _maxLength_ = initial.getMaxLength();
    }

    @Override
    public T withValues(JsonObject jsonObject, ModelRegistry modelRegistry)
    {
      if(jsonObject.containsKey("x-canon-attributes"))
      {
        JsonDomNode  node = jsonObject.get("x-canon-attributes");
        _xCanonAttributes_ = CanonAttributes.FACTORY.newInstance(node, modelRegistry);
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
      if(jsonObject.containsKey("minLength"))
      {
        JsonDomNode  node = jsonObject.get("minLength");
        if(node instanceof JsonParsedNumber)
        {
// schema.class class com.symphony.oss.canon2.generator.java.JavaNumberSchemaTemplateModel name minLength type BigInteger javaType BigInteger
          _minLength_ = ((JsonParsedNumber)node).asBigInteger();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("minLength must be an instance of JsonParsedNumber not " + node.getClass().getName(), node.getContext());
        }
      }
      if(jsonObject.containsKey("format"))
      {
        JsonDomNode  node = jsonObject.get("format");
        if(node instanceof JsonString)
        {
// schema.class class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel name format type String javaType String
          _format_ = ((JsonString)node).asString();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("format must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }
      if(jsonObject.containsKey("pattern"))
      {
        JsonDomNode  node = jsonObject.get("pattern");
        if(node instanceof JsonString)
        {
// schema.class class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel name pattern type String javaType String
          _pattern_ = ((JsonString)node).asString();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("pattern must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
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
// schema.class class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel name type type StringSchema.Type javaType String
          _type_ = StringSchema.Type.deserialize(((JsonString)node).asString());
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
// schema.class class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel name items BRUCE 001 type String javaType String
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
      if(jsonObject.containsKey("maxLength"))
      {
        JsonDomNode  node = jsonObject.get("maxLength");
        if(node instanceof JsonParsedNumber)
        {
// schema.class class com.symphony.oss.canon2.generator.java.JavaNumberSchemaTemplateModel name maxLength type BigInteger javaType BigInteger
          _maxLength_ = ((JsonParsedNumber)node).asBigInteger();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("maxLength must be an instance of JsonParsedNumber not " + node.getClass().getName(), node.getContext());
        }
      }
      return super.withValues(jsonObject, modelRegistry);
    }

    /* void populateAllFields(List<Object> result)
    {
      result.add(_xCanonAttributes_);
      result.add(_xCanonBuilderFacade_);
      result.add(_minLength_);
      result.add(_format_);
      result.add(_pattern_);
      result.add(_xCanonIdentifier_);
      result.add(_type_);
      result.add(_xCanonFacade_);
      result.add(_enum_);
      result.add(_maxLength_);
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
    public T withXCanonAttributes(CanonAttributes value) //main
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
    public T withXCanonBuilderFacade(Boolean value) //main
    {
      _xCanonBuilderFacade_ = value;
      return self();
    }

    /**
     * Return the value of the minLength attribute.
     *
     * @return the value of the minLength attribute.
     */
    @Override
    public @Nullable BigInteger getMinLength()
    {
      return _minLength_;
    }

    /**
     * Set the value of the minLength attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withMinLength(BigInteger value) //main
    {
      _minLength_ = value;
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
    public T withFormat(String value) //main
    {
      _format_ = value;
      return self();
    }

    /**
     * Return the value of the pattern attribute.
     *
     * @return the value of the pattern attribute.
     */
    @Override
    public @Nullable String getPattern()
    {
      return _pattern_;
    }

    /**
     * Set the value of the pattern attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withPattern(String value) //main
    {
      _pattern_ = value;
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
    public @Nonnull StringSchema.Type getType()
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
    public T withType(StringSchema.Type value) //main
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

      _type_ = StringSchema.Type.deserialize(value);
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
    public T withEnum(Set<String> value) //main
    {
      _enum_ = ImmutableSet.copyOf(value);
      return self();
    }

    /**
     * Return the value of the maxLength attribute.
     *
     * @return the value of the maxLength attribute.
     */
    @Override
    public @Nullable BigInteger getMaxLength()
    {
      return _maxLength_;
    }

    /**
     * Set the value of the maxLength attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withMaxLength(BigInteger value) //main
    {
      _maxLength_ = value;
      return self();
    }


    @Override
    public JsonObject getJson()
    {
      JsonObject.Builder builder = new JsonObject.Builder();

      builder.addIfNotNull(JSON_TYPE, StringSchemaEntity.TYPE_ID);
      builder.addIfNotNull(JSON_VERSION, StringSchemaEntity.TYPE_VERSION);

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

      if(getMinLength() != null)
      {
        builder.addIfNotNull("minLength", getMinLength());
      }

      if(getFormat() != null)
      {
        builder.addIfNotNull("format", getFormat());
      }

      if(getPattern() != null)
      {
        builder.addIfNotNull("pattern", getPattern());
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

      if(getEnum() != null)
      {
        JsonArray.Builder arrayBuilder = new JsonArray.Builder();
        for(String item : getEnum())
        {
          arrayBuilder.with(item);
        }
        builder.with("enum", arrayBuilder.build());
      }

      if(getMaxLength() != null)
      {
        builder.addIfNotNull("maxLength", getMaxLength());
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
   * Builder for StringSchema
   */
  public static class Builder extends StringSchema.AbstractBuilder<Builder, StringSchema>
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
    public Builder(StringSchema initial)
    {
      super(Builder.class, initial);
    }

    @Override
    protected StringSchema construct()
    {
      return new StringSchema(this);
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
   * Return the value of the minLength attribute.
   *
   * @return the value of the minLength attribute.
   */
  public @Nullable BigInteger getMinLength()
  {
    return _minLength_;
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
   * Return the value of the pattern attribute.
   *
   * @return the value of the pattern attribute.
   */
  public @Nullable String getPattern()
  {
    return _pattern_;
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
  public @Nonnull StringSchema.Type getType()
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
   * Return the value of the maxLength attribute.
   *
   * @return the value of the maxLength attribute.
   */
  public @Nullable BigInteger getMaxLength()
  {
    return _maxLength_;
  }

  @Override
  public boolean equals(Object obj)
  {
    if(obj instanceof StringSchemaEntity)
      return toString().equals(((StringSchemaEntity)obj).toString());

    return false;
  }

  @Override
  public int hashCode()
  {
    return toString().hashCode();
  }

// entity.additionalProperties??
// innerClasses
  // innerClass x-canon-builderFacade BOOLEAN class com.symphony.oss.canon2.core.SchemaTemplateModelType
  // innerClass minLength INTEGER class com.symphony.oss.canon2.core.SchemaTemplateModelType
  // innerClass format STRING class com.symphony.oss.canon2.core.SchemaTemplateModelType
  // innerClass pattern STRING class com.symphony.oss.canon2.core.SchemaTemplateModelType
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
    /** STRING */
    STRING("string")
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
        case "string":
          return STRING;
          
        default:
          throw new IllegalArgumentException("No enum constant \"" + value + "\" in Type");
      }
    }
  }
  // innerClass x-canon-facade BOOLEAN class com.symphony.oss.canon2.core.SchemaTemplateModelType
  // innerClass enum ARRAY class com.symphony.oss.canon2.core.SchemaTemplateModelType
  // innerClass maxLength INTEGER class com.symphony.oss.canon2.core.SchemaTemplateModelType
}

/*----------------------------------------------------------------------------------------------------
 * End of template template/Object/_Entity.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */