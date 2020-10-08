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
 *    At                   2020-10-08 13:45:16 BST
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
import com.symphony.oss.canon.json.model.JsonArray;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon2.runtime.java.IObjectEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.JsonObjectEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;
import com.symphony.oss.canon2.runtime.java.ObjectEntity;
import com.symphony.oss.commons.type.provider.IBigDecimalProvider;
import com.symphony.oss.commons.type.provider.IBigIntegerProvider;
import com.symphony.oss.commons.type.provider.IBooleanProvider;
import com.symphony.oss.commons.type.provider.IStringProvider;

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
  /** Type major version */
  public static final Integer TYPE_MAJOR_VERSION = 1;
  /** Type minor version */
  public static final Integer TYPE_MINOR_VERSION = 0;
  /** Factory instance */
  public static final Factory FACTORY = new Factory();

  private final ImmutableSet<String>        unknownKeys_;
  // field JavaFieldTemplateModel MaxItems maxItems field.typeSchema.name=maxItems
  private final BigInteger                 _maxItems_;
  // field JavaFieldTemplateModel XCanonBuilderFacade xCanonBuilderFacade field.typeSchema.name=x-canon-builderFacade
  private final Boolean                    _xCanonBuilderFacade_;
  // field JavaFieldTemplateModel Format format field.typeSchema.name=format
  private final String                     _format_;
  // field JavaFieldTemplateModel CanonCardinality xCanonCardinality field.typeSchema.name=CanonCardinality
  private final CanonCardinality           _xCanonCardinality_;
  // field JavaFieldTemplateModel XCanonIdentifier xCanonIdentifier field.typeSchema.name=x-canon-identifier
  private final String                     _xCanonIdentifier_;
  // field JavaFieldTemplateModel Type type field.typeSchema.name=type
  private final String                     _type_;
  // field JavaFieldTemplateModel XCanonFacade xCanonFacade field.typeSchema.name=x-canon-facade
  private final Boolean                    _xCanonFacade_;
  // field JavaFieldTemplateModel Enum enum field.typeSchema.name=enum
  private final Set<String>                _enum_;
  // field JavaFieldTemplateModel Required required field.typeSchema.name=required
  private final Set<String>                _required_;
  // field JavaFieldTemplateModel MinItems minItems field.typeSchema.name=minItems
  private final BigInteger                 _minItems_;
  // field JavaFieldTemplateModel CanonAttributes xCanonAttributes field.typeSchema.name=CanonAttributes
  private final CanonAttributes            _xCanonAttributes_;
  // field JavaFieldTemplateModel Maximum maximum field.typeSchema.name=maximum
  private final BigDecimal                 _maximum_;
  // field JavaFieldTemplateModel Minimum minimum field.typeSchema.name=minimum
  private final BigDecimal                 _minimum_;
  // field JavaFieldTemplateModel PropertiesObject properties field.typeSchema.name=PropertiesObject
  private final PropertiesObject           _properties_;
  // field JavaFieldTemplateModel ReferenceObject xCanonExtends field.typeSchema.name=ReferenceObject
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
      if(node != null)
      {
        if(node instanceof IBigIntegerProvider)
        {
          _maxItems_ = ((IBigIntegerProvider)node).asBigInteger();
        }
        else 
        {
          throw new IllegalArgumentException("maxItems must be an instance of IBigIntegerProvider not " + node.getClass().getName());
        }
      }
      else
      {
        _maxItems_ = null;
      }

      node = jsonInitialiser.get("x-canon-builderFacade");
      if(node != null)
      {
        if(node instanceof IBooleanProvider)
        {
          _xCanonBuilderFacade_ = ((IBooleanProvider)node).asBoolean();
        }
        else 
        {
          throw new IllegalArgumentException("x-canon-builderFacade must be an instance of IBooleanProvider not " + node.getClass().getName());
        }
      }
      else
      {
        _xCanonBuilderFacade_ = null;
      }

      node = jsonInitialiser.get("format");
      if(node != null)
      {
        if(node instanceof IStringProvider)
        {
          _format_ = ((IStringProvider)node).asString();
        }
        else 
        {
          throw new IllegalArgumentException("format must be an instance of IStringProvider not " + node.getClass().getName());
        }
      }
      else
      {
        _format_ = null;
      }

      node = jsonInitialiser.get("x-canon-cardinality");
      if(node != null)
      {
        if(node instanceof IStringProvider)
        {
          _xCanonCardinality_ = CanonCardinality.valueOf(((IStringProvider)node).asString());
        }
        else 
        {
          throw new IllegalArgumentException("x-canon-cardinality must be an instance of IStringProvider not " + node.getClass().getName());
        }
      }
      else
      {
        _xCanonCardinality_ = null;
      }

      node = jsonInitialiser.get("x-canon-identifier");
      if(node != null)
      {
        if(node instanceof IStringProvider)
        {
          _xCanonIdentifier_ = ((IStringProvider)node).asString();
        }
        else 
        {
          throw new IllegalArgumentException("x-canon-identifier must be an instance of IStringProvider not " + node.getClass().getName());
        }
      }
      else
      {
        _xCanonIdentifier_ = null;
      }

      node = jsonInitialiser.get("type");
      if(node != null)
      {
        if(node instanceof IStringProvider)
        {
          _type_ = ((IStringProvider)node).asString();
        }
        else 
        {
          throw new IllegalArgumentException("type must be an instance of IStringProvider not " + node.getClass().getName());
        }
      }
      else
      {
        _type_ = null;
      }

      node = jsonInitialiser.get("x-canon-facade");
      if(node != null)
      {
        if(node instanceof IBooleanProvider)
        {
          _xCanonFacade_ = ((IBooleanProvider)node).asBoolean();
        }
        else 
        {
          throw new IllegalArgumentException("x-canon-facade must be an instance of IBooleanProvider not " + node.getClass().getName());
        }
      }
      else
      {
        _xCanonFacade_ = null;
      }

      node = jsonInitialiser.get("enum");
      if(node != null)
      {
        if(node instanceof JsonArray)
        {
          Set<String> itemSet0 = new HashSet<>();
          for(JsonDomNode item0 : (JsonArray)node)
          {
            String itemValue0 = null;
            if(item0 instanceof IStringProvider)
            {
              itemValue0 = ((IStringProvider)item0).asString();
            }
            else 
            {
              throw new IllegalArgumentException("enum items must be an instance of IStringProvider not " + item0.getClass().getName());
            }
            itemSet0.add(itemValue0);
          }
          _enum_ = ImmutableSet.copyOf(itemSet0);
        }
        else 
        {
          throw new IllegalArgumentException("enum must be a JsonArray node not " + node.getClass().getName());
        }
      }
      else
      {
        _enum_ = null;
      }

      node = jsonInitialiser.get("required");
      if(node != null)
      {
        if(node instanceof JsonArray)
        {
          Set<String> itemSet0 = new HashSet<>();
          for(JsonDomNode item0 : (JsonArray)node)
          {
            String itemValue0 = null;
            if(item0 instanceof IStringProvider)
            {
              itemValue0 = ((IStringProvider)item0).asString();
            }
            else 
            {
              throw new IllegalArgumentException("required items must be an instance of IStringProvider not " + item0.getClass().getName());
            }
            itemSet0.add(itemValue0);
          }
          _required_ = ImmutableSet.copyOf(itemSet0);
        }
        else 
        {
          throw new IllegalArgumentException("required must be a JsonArray node not " + node.getClass().getName());
        }
      }
      else
      {
        _required_ = null;
      }

      node = jsonInitialiser.get("minItems");
      if(node != null)
      {
        if(node instanceof IBigIntegerProvider)
        {
          _minItems_ = ((IBigIntegerProvider)node).asBigInteger();
        }
        else 
        {
          throw new IllegalArgumentException("minItems must be an instance of IBigIntegerProvider not " + node.getClass().getName());
        }
      }
      else
      {
        _minItems_ = null;
      }

      node = jsonInitialiser.get("x-canon-attributes");
      if(node != null)
      {
        if(node instanceof JsonObject)
        {
          _xCanonAttributes_ = jsonInitialiser.getModelRegistry().newInstance((JsonObject)node, CanonAttributes.TYPE_ID, CanonAttributes.class);
        }
        else 
        {
          throw new IllegalArgumentException("x-canon-attributes must be an Object node not " + node.getClass().getName());
        }
      }
      else
      {
        _xCanonAttributes_ = null;
      }

      node = jsonInitialiser.get("maximum");
      if(node != null)
      {
        if(node instanceof IBigDecimalProvider)
        {
          _maximum_ = ((IBigDecimalProvider)node).asBigDecimal();
        }
        else 
        {
          throw new IllegalArgumentException("maximum must be an instance of IBigDecimalProvider not " + node.getClass().getName());
        }
      }
      else
      {
        _maximum_ = null;
      }

      node = jsonInitialiser.get("minimum");
      if(node != null)
      {
        if(node instanceof IBigDecimalProvider)
        {
          _minimum_ = ((IBigDecimalProvider)node).asBigDecimal();
        }
        else 
        {
          throw new IllegalArgumentException("minimum must be an instance of IBigDecimalProvider not " + node.getClass().getName());
        }
      }
      else
      {
        _minimum_ = null;
      }

      node = jsonInitialiser.get("properties");
      if(node != null)
      {
        if(node instanceof JsonObject)
        {
          _properties_ = jsonInitialiser.getModelRegistry().newInstance((JsonObject)node, PropertiesObject.TYPE_ID, PropertiesObject.class);
        }
        else 
        {
          throw new IllegalArgumentException("properties must be an Object node not " + node.getClass().getName());
        }
      }
      else
      {
        _properties_ = null;
      }

      node = jsonInitialiser.get("x-canon-extends");
      if(node != null)
      {
        if(node instanceof JsonObject)
        {
          _xCanonExtends_ = jsonInitialiser.getModelRegistry().newInstance((JsonObject)node, ReferenceObject.TYPE_ID, ReferenceObject.class);
        }
        else 
        {
          throw new IllegalArgumentException("x-canon-extends must be an Object node not " + node.getClass().getName());
        }
      }
      else
      {
        _xCanonExtends_ = null;
      }
      unknownKeys_ = jsonInitialiser.getCanonUnknownKeys();
    }
    else
    {
      IInstanceOrBuilder builder =  initialiser.getInstanceOrBuilder();

      if(builder == null)
      {
        throw new IllegalArgumentException("Initializer is not an JsonObjectEntityInitialiser but getInstanceOrBuilder() returns null");
      }
      _maxItems_ = builder.getMaxItems();
      _xCanonBuilderFacade_ = builder.getXCanonBuilderFacade();
      _format_ = builder.getFormat();
      _xCanonCardinality_ = builder.getXCanonCardinality();
      _xCanonIdentifier_ = builder.getXCanonIdentifier();
      _type_ = builder.getType();
      _xCanonFacade_ = builder.getXCanonFacade();
      _enum_ = ImmutableSet.copyOf(builder.getEnum());
      _required_ = ImmutableSet.copyOf(builder.getRequired());
      _minItems_ = builder.getMinItems();
      _xCanonAttributes_ = builder.getXCanonAttributes();
      _maximum_ = builder.getMaximum();
      _minimum_ = builder.getMinimum();
      _properties_ = builder.getProperties();
      _xCanonExtends_ = builder.getXCanonExtends();
      unknownKeys_ = builder.getCanonUnknownKeys();
    }
  }
  @Override
  public ImmutableSet<String> getCanonUnknownKeys()
  {
    return unknownKeys_;
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
   * Return the value of the minItems attribute.
   *
   * @return the value of the minItems attribute.
   */
  public @Nullable BigInteger getMinItems()
  {
    return _minItems_;
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
    public Schema newInstance(JsonObject jsonObject, ModelRegistry modelRegistry)
    {
      return new Schema(new JsonInitialiser(jsonObject, modelRegistry));
    }
  }



  /**
   * Initialiser for Schema
   */
  
  public interface Initialiser extends IObjectEntityInitialiser
  {
    IInstanceOrBuilder getInstanceOrBuilder();
  }

  public static class JsonInitialiser extends JsonObjectEntityInitialiser implements Initialiser
  {
    public JsonInitialiser(JsonObject jsonObject, ModelRegistry modelRegistry)
    {
      super(jsonObject, modelRegistry);
    }

    @Override
    public IInstanceOrBuilder getInstanceOrBuilder()
    {
      return null;
    }
  }

  /**
   * Instance or Builder for Object Schema
   */
  public interface IInstanceOrBuilder extends IObjectEntityInitialiser
  {
    
    /**
     * Return the value of the maxItems attribute.
     *
     * @return the value of the maxItems attribute.
     */
    @Nullable BigInteger getMaxItems();
    
    /**
     * Return the value of the x-canon-builderFacade attribute.
     *
     * @return the value of the x-canon-builderFacade attribute.
     */
    @Nullable Boolean getXCanonBuilderFacade();
    
    /**
     * Return the value of the format attribute.
     *
     * @return the value of the format attribute.
     */
    @Nullable String getFormat();
    
    /**
     * Return the value of the x-canon-cardinality attribute.
     *
     * @return the value of the x-canon-cardinality attribute.
     */
    @Nullable CanonCardinality getXCanonCardinality();
    
    /**
     * Return the value of the x-canon-identifier attribute.
     *
     * @return the value of the x-canon-identifier attribute.
     */
    @Nullable String getXCanonIdentifier();
    
    /**
     * Return the value of the type attribute.
     *
     * @return the value of the type attribute.
     */
    @Nullable String getType();
    
    /**
     * Return the value of the x-canon-facade attribute.
     *
     * @return the value of the x-canon-facade attribute.
     */
    @Nullable Boolean getXCanonFacade();
    
    /**
     * Return the value of the enum attribute.
     *
     * @return the value of the enum attribute.
     */
    @Nullable Set<String> getEnum();
    
    /**
     * Return the value of the required attribute.
     *
     * @return the value of the required attribute.
     */
    @Nullable Set<String> getRequired();
    
    /**
     * Return the value of the minItems attribute.
     *
     * @return the value of the minItems attribute.
     */
    @Nullable BigInteger getMinItems();
    
    /**
     * Return the value of the x-canon-attributes attribute.
     *
     * @return the value of the x-canon-attributes attribute.
     */
    @Nullable CanonAttributes getXCanonAttributes();
    
    /**
     * Return the value of the maximum attribute.
     *
     * @return the value of the maximum attribute.
     */
    @Nullable BigDecimal getMaximum();
    
    /**
     * Return the value of the minimum attribute.
     *
     * @return the value of the minimum attribute.
     */
    @Nullable BigDecimal getMinimum();
    
    /**
     * Return the value of the properties attribute.
     *
     * @return the value of the properties attribute.
     */
    @Nullable PropertiesObject getProperties();
    
    /**
     * Return the value of the x-canon-extends attribute.
     *
     * @return the value of the x-canon-extends attribute.
     */
    @Nullable ReferenceObject getXCanonExtends();
  }


  /**
   * Abstract builder for Schema. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <T> The concrete type of the builder, used for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends SchemaEntity>
    extends ObjectEntity.AbstractBuilder<T,B>
    implements IInstanceOrBuilder, Initialiser
  {
    protected BigInteger                 _maxItems_;
    protected Boolean                    _xCanonBuilderFacade_;
    protected String                     _format_;
    protected CanonCardinality           _xCanonCardinality_;
    protected String                     _xCanonIdentifier_;
    protected String                     _type_;
    protected Boolean                    _xCanonFacade_;
    protected Set<String>                _enum_ = new HashSet<String>();
    protected Set<String>                _required_ = new HashSet<String>();
    protected BigInteger                 _minItems_;
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
    public IInstanceOrBuilder getInstanceOrBuilder()
    {
      return this;
    }

    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);

      _maxItems_ = initial.getMaxItems();
      _xCanonBuilderFacade_ = initial.getXCanonBuilderFacade();
      _format_ = initial.getFormat();
      _xCanonCardinality_ = initial.getXCanonCardinality();
      _xCanonIdentifier_ = initial.getXCanonIdentifier();
      _type_ = initial.getType();
      _xCanonFacade_ = initial.getXCanonFacade();
      _enum_ = ImmutableSet.copyOf(initial.getEnum());
      _required_ = ImmutableSet.copyOf(initial.getRequired());
      _minItems_ = initial.getMinItems();
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
        if(node instanceof IBigIntegerProvider)
        {
          _maxItems_ = ((IBigIntegerProvider)node).asBigInteger();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("maxItems must be an instance of IBigIntegerProvider not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("x-canon-builderFacade"))
      {
        JsonDomNode  node = jsonObject.get("x-canon-builderFacade");
        if(node instanceof IBooleanProvider)
        {
          _xCanonBuilderFacade_ = ((IBooleanProvider)node).asBoolean();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("x-canon-builderFacade must be an instance of IBooleanProvider not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("format"))
      {
        JsonDomNode  node = jsonObject.get("format");
        if(node instanceof IStringProvider)
        {
          _format_ = ((IStringProvider)node).asString();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("format must be an instance of IStringProvider not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("x-canon-cardinality"))
      {
        JsonDomNode  node = jsonObject.get("x-canon-cardinality");
        if(node instanceof IStringProvider)
        {
          _xCanonCardinality_ = CanonCardinality.valueOf(((IStringProvider)node).asString());
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("x-canon-cardinality must be an instance of IStringProvider not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("x-canon-identifier"))
      {
        JsonDomNode  node = jsonObject.get("x-canon-identifier");
        if(node instanceof IStringProvider)
        {
          _xCanonIdentifier_ = ((IStringProvider)node).asString();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("x-canon-identifier must be an instance of IStringProvider not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("type"))
      {
        JsonDomNode  node = jsonObject.get("type");
        if(node instanceof IStringProvider)
        {
          _type_ = ((IStringProvider)node).asString();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("type must be an instance of IStringProvider not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("x-canon-facade"))
      {
        JsonDomNode  node = jsonObject.get("x-canon-facade");
        if(node instanceof IBooleanProvider)
        {
          _xCanonFacade_ = ((IBooleanProvider)node).asBoolean();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("x-canon-facade must be an instance of IBooleanProvider not " + node.getClass().getName());
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
            if(item0 instanceof IStringProvider)
            {
              itemValue0 = ((IStringProvider)item0).asString();
            }
            else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
            {
              throw new IllegalArgumentException("enum items must be an instance of IStringProvider not " + item0.getClass().getName());
            }
            itemSet0.add(itemValue0);
          }
          _enum_ = ImmutableSet.copyOf(itemSet0);
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("enum must be a JsonArray node not " + node.getClass().getName());
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
            if(item0 instanceof IStringProvider)
            {
              itemValue0 = ((IStringProvider)item0).asString();
            }
            else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
            {
              throw new IllegalArgumentException("required items must be an instance of IStringProvider not " + item0.getClass().getName());
            }
            itemSet0.add(itemValue0);
          }
          _required_ = ImmutableSet.copyOf(itemSet0);
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("required must be a JsonArray node not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("minItems"))
      {
        JsonDomNode  node = jsonObject.get("minItems");
        if(node instanceof IBigIntegerProvider)
        {
          _minItems_ = ((IBigIntegerProvider)node).asBigInteger();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("minItems must be an instance of IBigIntegerProvider not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("x-canon-attributes"))
      {
        JsonDomNode  node = jsonObject.get("x-canon-attributes");
        if(node instanceof JsonObject)
        {
          _xCanonAttributes_ = modelRegistry.newInstance((JsonObject)node, CanonAttributes.TYPE_ID, CanonAttributes.class);
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("x-canon-attributes must be an Object node not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("maximum"))
      {
        JsonDomNode  node = jsonObject.get("maximum");
        if(node instanceof IBigDecimalProvider)
        {
          _maximum_ = ((IBigDecimalProvider)node).asBigDecimal();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("maximum must be an instance of IBigDecimalProvider not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("minimum"))
      {
        JsonDomNode  node = jsonObject.get("minimum");
        if(node instanceof IBigDecimalProvider)
        {
          _minimum_ = ((IBigDecimalProvider)node).asBigDecimal();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("minimum must be an instance of IBigDecimalProvider not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("properties"))
      {
        JsonDomNode  node = jsonObject.get("properties");
        if(node instanceof JsonObject)
        {
          _properties_ = modelRegistry.newInstance((JsonObject)node, PropertiesObject.TYPE_ID, PropertiesObject.class);
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("properties must be an Object node not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("x-canon-extends"))
      {
        JsonDomNode  node = jsonObject.get("x-canon-extends");
        if(node instanceof JsonObject)
        {
          _xCanonExtends_ = modelRegistry.newInstance((JsonObject)node, ReferenceObject.TYPE_ID, ReferenceObject.class);
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("x-canon-extends must be an Object node not " + node.getClass().getName());
        }
      }
      return super.withValues(jsonObject, modelRegistry);
    }

    /* void populateAllFields(List<Object> result)
    {
      result.add(_maxItems_);
      result.add(_xCanonBuilderFacade_);
      result.add(_format_);
      result.add(_xCanonCardinality_);
      result.add(_xCanonIdentifier_);
      result.add(_type_);
      result.add(_xCanonFacade_);
      result.add(_enum_);
      result.add(_required_);
      result.add(_minItems_);
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
     // base type BigInteger
     // base name maxItems
    public T withMaxItems(BigInteger value)
    {
      _maxItems_ = value;
      return self();
    }
// field.typeSchema.schemaType INTEGER

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
     // base type Boolean
     // base name x-canon-builderFacade
    public T withXCanonBuilderFacade(Boolean value)
    {
      _xCanonBuilderFacade_ = value;
      return self();
    }
// field.typeSchema.schemaType BOOLEAN

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
     // base type String
     // base name format
    public T withFormat(String value)
    {
      _format_ = value;
      return self();
    }
// field.typeSchema.schemaType STRING

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
     // base type CanonCardinality
     // base name CanonCardinality
    public T withXCanonCardinality(CanonCardinality value)
    {
      _xCanonCardinality_ = value;
      return self();
    }
// field.typeSchema.schemaType STRING

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
     // base type String
     // base name x-canon-identifier
    public T withXCanonIdentifier(String value)
    {
      _xCanonIdentifier_ = value;
      return self();
    }
// field.typeSchema.schemaType STRING

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
     // base type String
     // base name type
    public T withType(String value)
    {
      _type_ = value;
      return self();
    }
// field.typeSchema.schemaType STRING

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
     // base type Boolean
     // base name x-canon-facade
    public T withXCanonFacade(Boolean value)
    {
      _xCanonFacade_ = value;
      return self();
    }
// field.typeSchema.schemaType BOOLEAN

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
     // base type Set<String>
     // base name enum
    public T withEnum(Set<String> value)
    {
      _enum_ = ImmutableSet.copyOf(value);
      return self();
    }
// field.typeSchema.schemaType ARRAY
// field.typeSchema.elementType.class class com.symphony.oss.canon2.generator.java.JavaPrimitiveSchemaTemplateModel
// field.typeSchema.elementType.name items
// field.typeSchema.elementType.isGenerated N

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
     // base type Set<String>
     // base name required
    public T withRequired(Set<String> value)
    {
      _required_ = ImmutableSet.copyOf(value);
      return self();
    }
// field.typeSchema.schemaType ARRAY
// field.typeSchema.elementType.class class com.symphony.oss.canon2.generator.java.JavaPrimitiveSchemaTemplateModel
// field.typeSchema.elementType.name items
// field.typeSchema.elementType.isGenerated N

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
     // base type BigInteger
     // base name minItems
    public T withMinItems(BigInteger value)
    {
      _minItems_ = value;
      return self();
    }
// field.typeSchema.schemaType INTEGER

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
     // base type CanonAttributes
     // base name CanonAttributes
    public T withXCanonAttributes(CanonAttributes value)
    {
      _xCanonAttributes_ = value;
      return self();
    }
// field.typeSchema.schemaType OBJECT

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
     // base type BigDecimal
     // base name maximum
    public T withMaximum(BigDecimal value)
    {
      _maximum_ = value;
      return self();
    }
// field.typeSchema.schemaType NUMBER

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
     // base type BigDecimal
     // base name minimum
    public T withMinimum(BigDecimal value)
    {
      _minimum_ = value;
      return self();
    }
// field.typeSchema.schemaType NUMBER

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
     // base type PropertiesObject
     // base name PropertiesObject
    public T withProperties(PropertiesObject value)
    {
      _properties_ = value;
      return self();
    }
// field.typeSchema.schemaType OBJECT

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
     // base type ReferenceObject
     // base name ReferenceObject
    public T withXCanonExtends(ReferenceObject value)
    {
      _xCanonExtends_ = value;
      return self();
    }
// field.typeSchema.schemaType OBJECT

    @Override
    public JsonObject getJsonObject()
    {
      JsonObject.Builder builder = new JsonObject.Builder();

      builder.addIfNotNull(JSON_TYPE, SchemaEntity.TYPE_ID);
      builder.addIfNotNull(JSON_VERSION, SchemaEntity.TYPE_VERSION);

      populateJson(builder);

      return builder.build();
    }

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

      if(getMinItems() != null)
      {
          builder.addIfNotNull("minItems", getMinItems());
      }

      if(getXCanonAttributes() != null)
      {
          builder.addIfNotNull("x-canon-attributes", getXCanonAttributes().getJsonObject());
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
          builder.addIfNotNull("properties", getProperties().getJsonObject());
      }

      if(getXCanonExtends() != null)
      {
          builder.addIfNotNull("x-canon-extends", getXCanonExtends().getJsonObject());
      }
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

  // entity.name Schema
  // entity.class class com.symphony.oss.canon2.generator.java.JavaObjectSchemaTemplateModel
}

/*----------------------------------------------------------------------------------------------------
 * End of template template/Object/_Entity.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */