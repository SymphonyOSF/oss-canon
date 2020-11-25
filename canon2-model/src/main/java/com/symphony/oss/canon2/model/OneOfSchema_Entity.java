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
 *    At                   2020-11-25 15:52:09 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedMap;
import com.symphony.oss.canon.json.ParserErrorException;
import com.symphony.oss.canon.json.model.JsonArray;
import com.symphony.oss.canon.json.model.JsonBoolean;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonNull;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon.json.model.JsonString;
import com.symphony.oss.canon2.runtime.java.Entity;
import com.symphony.oss.canon2.runtime.java.IObjectEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.JsonEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.JsonObjectEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;
import com.symphony.oss.canon2.runtime.java.ObjectEntity;
import com.symphony.oss.commons.fault.FaultAccumulator;

/**
 * Implementation for Object OneOfSchema
 * Generated from OneOfSchema at {entity.context.path}
 */
@Immutable
public abstract class OneOfSchema_Entity extends ObjectEntity
{
  /** Type ID */
  public static final String  TYPE_ID = "com.symphony.oss.canon2.model.OneOfSchema";
  /** Type version */
  public static final String  TYPE_VERSION = "1.0";
  /** Factory instance */
  public static final Factory FACTORY = new Factory();

  private final Map<String, Entity>        additionalProperties_;
  private final Set<SchemaOrRef>           _oneOf_;
  private final Boolean                    _xCanonBuilderFacade_;
  private final String                     _xCanonIdentifier_;
  private final Boolean                    _xCanonFacade_;
  private final DiscriminatorObject        _discriminator_;

  /**
   * Constructor.
   *
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  public OneOfSchema_Entity(Initialiser initialiser)
  {
    super(initialiser);

    if(initialiser instanceof JsonObjectEntityInitialiser)
    {
      JsonObjectEntityInitialiser jsonInitialiser = (JsonObjectEntityInitialiser)initialiser;
      ModelRegistry modelRegistry = jsonInitialiser.getModelRegistry();

      JsonDomNode               node;

      node = jsonInitialiser.get("oneOf");
      if(node == null || node instanceof JsonNull)
      {
        throw new ParserErrorException("oneOf is required.", jsonInitialiser.getJson().getContext());
      }
      else
      {
// A1
//A2
        if(node instanceof JsonArray)
        {
          Set<SchemaOrRef> itemSet0 = new HashSet<>();
          for(JsonDomNode item0 : (JsonArray)node)
          {
            SchemaOrRef itemValue0 = null;
// A1
//A2
    
    
            if(item0 instanceof JsonDomNode)
            {
//A6a
              itemValue0 = SchemaOrRef.FACTORY.newInstance((JsonDomNode)item0, modelRegistry);
            }
            else 
            {
              throw new ParserErrorException("oneOf items must be an instance of JsonDomNode not " + item0.getClass().getName(), item0.getContext());
            }
            itemSet0.add(itemValue0);
          }
          _oneOf_ = ImmutableSet.copyOf(itemSet0);
        }
        else 
        {
          throw new ParserErrorException("oneOf must be a JsonArray node not " + node.getClass().getName(), node.getContext());
        }
      }

      node = jsonInitialiser.get("x-canon-builderFacade");
      if(node == null || node instanceof JsonNull)
      {
        _xCanonBuilderFacade_ = null;
      }
      else
      {
// A1
//A2
        if(node instanceof JsonBoolean)
        {
//A6
          _xCanonBuilderFacade_ = ((JsonBoolean)node).asBoolean();
        }
        else 
        {
          throw new ParserErrorException("x-canon-builderFacade must be an instance of JsonBoolean not " + node.getClass().getName(), node.getContext());
        }
      }

      node = jsonInitialiser.get("x-canon-identifier");
      if(node == null || node instanceof JsonNull)
      {
        _xCanonIdentifier_ = null;
      }
      else
      {
// A1
//A2
        if(node instanceof JsonString)
        {
//A6
          _xCanonIdentifier_ = ((JsonString)node).asString();
        }
        else 
        {
          throw new ParserErrorException("x-canon-identifier must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }

      node = jsonInitialiser.get("x-canon-facade");
      if(node == null || node instanceof JsonNull)
      {
        _xCanonFacade_ = null;
      }
      else
      {
// A1
//A2
        if(node instanceof JsonBoolean)
        {
//A6
          _xCanonFacade_ = ((JsonBoolean)node).asBoolean();
        }
        else 
        {
          throw new ParserErrorException("x-canon-facade must be an instance of JsonBoolean not " + node.getClass().getName(), node.getContext());
        }
      }

      node = jsonInitialiser.get("discriminator");
      if(node == null || node instanceof JsonNull)
      {
        _discriminator_ = null;
      }
      else
      {
// A1
//A2
    //A3
    
    
        if(node instanceof JsonObject)
        {
//A6a
          _discriminator_ = DiscriminatorObject.FACTORY.newInstance((JsonObject)node, modelRegistry);
        }
        else 
        {
          throw new ParserErrorException("discriminator must be an instance of JsonObject not " + node.getClass().getName(), node.getContext());
        }
      }
      Map<String, Entity>       additionalProperties = new HashMap<>();    
      Entity                    prop;

      for(String name : jsonInitialiser.getCanonUnknownKeys())
      {
        prop   = null;
        node   = jsonInitialiser.get(name);
        if(node instanceof JsonObject)
        {
          prop = new ObjectEntity(new JsonObjectEntityInitialiser((JsonObject)node, initialiser.getModelRegistry()));
        }
        else 
        {
          prop = new Entity(new JsonEntityInitialiser(node, initialiser.getModelRegistry()));
        }
        if(prop != null)
        {
          additionalProperties.put(name, prop);
        }
      }
      additionalProperties_ =  ImmutableSortedMap.copyOf(additionalProperties);
    }
    else
    {
      I_OneOfSchema_InstanceOrBuilder builder =  initialiser.getInstanceOrBuilder();

      if(builder == null)
      {
        throw new IllegalArgumentException("Initializer is not an JsonObjectEntityInitialiser but getInstanceOrBuilder() returns null");
      }
      _oneOf_ = ImmutableSet.copyOf(builder.getOneOf());
      if(_oneOf_ == null)
        throw new IllegalArgumentException("oneOf is required.");
  
      _xCanonBuilderFacade_ = builder.getXCanonBuilderFacade();
      _xCanonIdentifier_ = builder.getXCanonIdentifier();
      _xCanonFacade_ = builder.getXCanonFacade();
      _discriminator_ = builder.getDiscriminator();
      additionalProperties_ = builder.canonGetAdditionalProperties();
    }
  }


  /**
   * Factory class for OneOfSchema.
   */
  public static class Factory extends ObjectEntity.Factory<OneOfSchema>
  {
    @Override
    public String getCanonType()
    {
      return TYPE_ID;
    }

    @Override
    public OneOfSchema newInstance(JsonDomNode node, ModelRegistry modelRegistry)
    {
      if(node instanceof JsonObject)
      {
        return new OneOfSchema(new JsonInitialiser((JsonObject)node, modelRegistry));
      }

      if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
      {
        throw new ParserErrorException("OneOfSchema must be an Object node not " + node.getClass().getName(), node.getContext());
      }
      else
      {
        return null;
      }
    }
  }

  /**
   * Abstract Initialiser for OneOfSchema
   */
  public interface Initialiser extends IObjectEntityInitialiser
  {
    /**
     * Return an instance or builder containing the values for a new instance.
     * 
     * @return an instance or builder containing the values for a new instance.
     */
    I_OneOfSchema_InstanceOrBuilder getInstanceOrBuilder();
  }

  /**
   * JSON Initialiser for OneOfSchema
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
    public I_OneOfSchema_InstanceOrBuilder getInstanceOrBuilder()
    {
      return null;
    }
  }

  /**
   * Abstract builder for OneOfSchema. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <T> The concrete type of the builder, used for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends OneOfSchema_Entity>
    extends ObjectEntity.AbstractBuilder<T,B>
    implements I_OneOfSchema_InstanceOrBuilder, Initialiser
  {
    protected Set<SchemaOrRef>           _oneOf_ = new HashSet<SchemaOrRef>();
    protected Boolean                    _xCanonBuilderFacade_;
    protected String                     _xCanonIdentifier_;
    protected Boolean                    _xCanonFacade_;
    protected DiscriminatorObject        _discriminator_;
    protected Map<String, Entity>        additionalProperties_ = new HashMap<>();

    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }

    @Override
    public I_OneOfSchema_InstanceOrBuilder getInstanceOrBuilder()
    {
      return this;
    }

    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);

      _oneOf_ = ImmutableSet.copyOf(initial.getOneOf());
      _xCanonBuilderFacade_ = initial.getXCanonBuilderFacade();
      _xCanonIdentifier_ = initial.getXCanonIdentifier();
      _xCanonFacade_ = initial.getXCanonFacade();
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
      if(json.containsKey("oneOf"))
      {
        JsonDomNode  node = json.get("oneOf");
// A1
//A2
        if(node instanceof JsonArray)
        {
          Set<SchemaOrRef> itemSet0 = new HashSet<>();
          for(JsonDomNode item0 : (JsonArray)node)
          {
            SchemaOrRef itemValue0 = null;
// A1
//A2
    
    
            if(item0 instanceof JsonDomNode)
            {
//A6a
              itemValue0 = SchemaOrRef.FACTORY.newInstance((JsonDomNode)item0, modelRegistry);
            }
            else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
            {
              throw new ParserErrorException("oneOf items must be an instance of JsonDomNode not " + item0.getClass().getName(), item0.getContext());
            }
            itemSet0.add(itemValue0);
          }
          _oneOf_ = ImmutableSet.copyOf(itemSet0);
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("oneOf must be a JsonArray node not " + node.getClass().getName(), node.getContext());
        }
      }
      if(json.containsKey("x-canon-builderFacade"))
      {
        JsonDomNode  node = json.get("x-canon-builderFacade");
// A1
//A2
        if(node instanceof JsonBoolean)
        {
//A6
          _xCanonBuilderFacade_ = ((JsonBoolean)node).asBoolean();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("x-canon-builderFacade must be an instance of JsonBoolean not " + node.getClass().getName(), node.getContext());
        }
      }
      if(json.containsKey("x-canon-identifier"))
      {
        JsonDomNode  node = json.get("x-canon-identifier");
// A1
//A2
        if(node instanceof JsonString)
        {
//A6
          _xCanonIdentifier_ = ((JsonString)node).asString();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("x-canon-identifier must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }
      if(json.containsKey("x-canon-facade"))
      {
        JsonDomNode  node = json.get("x-canon-facade");
// A1
//A2
        if(node instanceof JsonBoolean)
        {
//A6
          _xCanonFacade_ = ((JsonBoolean)node).asBoolean();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("x-canon-facade must be an instance of JsonBoolean not " + node.getClass().getName(), node.getContext());
        }
      }
      if(json.containsKey("discriminator"))
      {
        JsonDomNode  node = json.get("discriminator");
// A1
//A2
    //A3
    
    
        if(node instanceof JsonObject)
        {
//A6a
          _discriminator_ = DiscriminatorObject.FACTORY.newInstance((JsonObject)node, modelRegistry);
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("discriminator must be an instance of JsonObject not " + node.getClass().getName(), node.getContext());
        }
      }
      return self();
    }

    /**
     * Set an additional property.
     * 
     * @param name  The property name.
     * @param value The property value.
     * 
     * @return This (fluent method).
     */
    public T with(String name, Entity value)
    {
      additionalProperties_.put(name, value);

      return self();
    }

    /* void populateAllFields(List<Object> result)
    {
      result.add(_oneOf_);
      result.add(_xCanonBuilderFacade_);
      result.add(_xCanonIdentifier_);
      result.add(_xCanonFacade_);
      result.add(_discriminator_);
    }*/

    /**
     * Return the value of the oneOf attribute.
     *
     * @return the value of the oneOf attribute.
     */
    @Override
    public @Nonnull Set<SchemaOrRef> getOneOf()
    {
      if(_oneOf_ == null)
        throw new IllegalStateException("Unexpected null value encountered");
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
        if(value == null)
          throw new IllegalArgumentException("oneOf is required.");
  
      _oneOf_ = ImmutableSet.copyOf(value);
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

      builder.addIfNotNull(JSON_TYPE, OneOfSchema_Entity.TYPE_ID);
      builder.addIfNotNull(JSON_VERSION, OneOfSchema_Entity.TYPE_VERSION);

      populateJson(builder);

      return builder.build();
    }

    @Override
    public void populateJson(JsonObject.Builder builder)
    {
      super.populateJson(builder);

      if(getOneOf() != null)
      {
        JsonArray.Builder arrayBuilder = new JsonArray.Builder();
        for(SchemaOrRef item : getOneOf())
        {
          arrayBuilder.with(item.getJson());
        }
        builder.with("oneOf", arrayBuilder.build());
      }


      if(getXCanonBuilderFacade() != null)
      {
        builder.addIfNotNull("x-canon-builderFacade", getXCanonBuilderFacade());
      }


      if(getXCanonIdentifier() != null)
      {
        builder.addIfNotNull("x-canon-identifier", getXCanonIdentifier());
      }


      if(getXCanonFacade() != null)
      {
        builder.addIfNotNull("x-canon-facade", getXCanonFacade());
      }


      if(getDiscriminator() != null)
      {
        builder.addIfNotNull("discriminator", getDiscriminator().getJson());
      }

      for(String name : additionalProperties_.keySet())
      {
        Entity value = additionalProperties_.get(name);
        builder.addIfNotNull(name, value.getJson());
      }
    }

    @Override
    public Map<String, Entity> canonGetAdditionalProperties()
    {
       return ImmutableSortedMap.copyOf(additionalProperties_);
    }

    @Override
    public void validate(FaultAccumulator faultAccumulator)
    {
      super.validate(faultAccumulator);
      faultAccumulator.checkNotNull(_oneOf_, "oneOf");
    }
  }

  /**
   * Return any additional attributes.
   * 
   * @return any additional attributes.
   */
  public Map<String, Entity> canonGetAdditionalProperties()
  {
     return additionalProperties_;
  }

  /**
   * Builder for OneOfSchema
   */
  public static class Builder extends OneOfSchema.AbstractBuilder<Builder, OneOfSchema>
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
    public Builder(OneOfSchema initial)
    {
      super(Builder.class, initial);
    }

    @Override
    protected OneOfSchema construct()
    {
      return new OneOfSchema(this);
    }
  }


  /**
   * Return the value of the oneOf attribute.
   *
   * @return the value of the oneOf attribute.
   */
  public @Nonnull Set<SchemaOrRef> getOneOf()
  {
    return _oneOf_;
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
   * Return the value of the x-canon-identifier attribute.
   *
   * @return the value of the x-canon-identifier attribute.
   */
  public @Nullable String getXCanonIdentifier()
  {
    return _xCanonIdentifier_;
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
    if(obj instanceof OneOfSchema_Entity)
      return toString().equals(((OneOfSchema_Entity)obj).toString());

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