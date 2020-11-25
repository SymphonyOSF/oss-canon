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
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableSortedMap;
import com.symphony.oss.canon.json.ParserErrorException;
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
 * Implementation for Object OpenApiObject
 * Generated from OpenApiObject at {entity.context.path}
 */
@Immutable
public abstract class OpenApiObject_Entity extends ObjectEntity
{
  /** Type ID */
  public static final String  TYPE_ID = "com.symphony.oss.canon2.model.OpenApiObject";
  /** Type version */
  public static final String  TYPE_VERSION = "1.0";
  /** Factory instance */
  public static final Factory FACTORY = new Factory();

  private final Map<String, Entity>        additionalProperties_;
  private final ComponentsObject           _components_;
  private final CanonGeneratorConfig       _xCanonGenerators_;
  private final String                     _openapi_;
  private final PathsObject                _paths_;
  private final String                     _xCanonId_;
  private final SemanticVersion            _xCanonVersion_;
  private final String                     _xCanonIdentifier_;
  private final String                     _canon_;
  private final InfoObject                 _info_;

  /**
   * Constructor.
   *
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  public OpenApiObject_Entity(Initialiser initialiser)
  {
    super(initialiser);

    if(initialiser instanceof JsonObjectEntityInitialiser)
    {
      JsonObjectEntityInitialiser jsonInitialiser = (JsonObjectEntityInitialiser)initialiser;
      ModelRegistry modelRegistry = jsonInitialiser.getModelRegistry();

      JsonDomNode               node;

      node = jsonInitialiser.get("components");
      if(node == null || node instanceof JsonNull)
      {
        _components_ = null;
      }
      else
      {
// A1
//A2
    //A3
    
    
        if(node instanceof JsonObject)
        {
//A6a
          _components_ = ComponentsObject.FACTORY.newInstance((JsonObject)node, modelRegistry);
        }
        else 
        {
          throw new ParserErrorException("components must be an instance of JsonObject not " + node.getClass().getName(), node.getContext());
        }
      }

      node = jsonInitialiser.get("x-canon-generators");
      if(node == null || node instanceof JsonNull)
      {
        _xCanonGenerators_ = null;
      }
      else
      {
// A1
//A2
    //A3
    
    
        if(node instanceof JsonObject)
        {
//A6a
          _xCanonGenerators_ = CanonGeneratorConfig.FACTORY.newInstance((JsonObject)node, modelRegistry);
        }
        else 
        {
          throw new ParserErrorException("x-canon-generators must be an instance of JsonObject not " + node.getClass().getName(), node.getContext());
        }
      }

      node = jsonInitialiser.get("openapi");
      if(node == null || node instanceof JsonNull)
      {
        _openapi_ = null;
      }
      else
      {
// A1
//A2
        if(node instanceof JsonString)
        {
//A6
          _openapi_ = ((JsonString)node).asString();
        }
        else 
        {
          throw new ParserErrorException("openapi must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }

      node = jsonInitialiser.get("paths");
      if(node == null || node instanceof JsonNull)
      {
        _paths_ = null;
      }
      else
      {
// A1
//A2
    //A3
    
    
        if(node instanceof JsonObject)
        {
//A6a
          _paths_ = PathsObject.FACTORY.newInstance((JsonObject)node, modelRegistry);
        }
        else 
        {
          throw new ParserErrorException("paths must be an instance of JsonObject not " + node.getClass().getName(), node.getContext());
        }
      }

      node = jsonInitialiser.get("x-canon-id");
      if(node == null || node instanceof JsonNull)
      {
        _xCanonId_ = null;
      }
      else
      {
// A1
//A2
        if(node instanceof JsonString)
        {
//A6
          _xCanonId_ = ((JsonString)node).asString();
        }
        else 
        {
          throw new ParserErrorException("x-canon-id must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }

      node = jsonInitialiser.get("x-canon-version");
      if(node == null || node instanceof JsonNull)
      {
        _xCanonVersion_ = null;
      }
      else
      {
// A1
//A2
        if(node instanceof JsonString)
        {
//A6
          _xCanonVersion_ = new SemanticVersion(((JsonString)node).asString());
        }
        else 
        {
          throw new ParserErrorException("x-canon-version must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
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

      node = jsonInitialiser.get("canon");
      if(node == null || node instanceof JsonNull)
      {
        _canon_ = null;
      }
      else
      {
// A1
//A2
        if(node instanceof JsonString)
        {
//A6
          _canon_ = ((JsonString)node).asString();
        }
        else 
        {
          throw new ParserErrorException("canon must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }

      node = jsonInitialiser.get("info");
      if(node == null || node instanceof JsonNull)
      {
        throw new ParserErrorException("info is required.", jsonInitialiser.getJson().getContext());
      }
      else
      {
// A1
//A2
    //A3
    
    
        if(node instanceof JsonObject)
        {
//A6a
          _info_ = InfoObject.FACTORY.newInstance((JsonObject)node, modelRegistry);
        }
        else 
        {
          throw new ParserErrorException("info must be an instance of JsonObject not " + node.getClass().getName(), node.getContext());
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
      I_OpenApiObject_InstanceOrBuilder builder =  initialiser.getInstanceOrBuilder();

      if(builder == null)
      {
        throw new IllegalArgumentException("Initializer is not an JsonObjectEntityInitialiser but getInstanceOrBuilder() returns null");
      }
      _components_ = builder.getComponents();
      _xCanonGenerators_ = builder.getXCanonGenerators();
      _openapi_ = builder.getOpenapi();
      _paths_ = builder.getPaths();
      _xCanonId_ = builder.getXCanonId();
      _xCanonVersion_ = builder.getXCanonVersion();
      _xCanonIdentifier_ = builder.getXCanonIdentifier();
      _canon_ = builder.getCanon();
      _info_ = builder.getInfo();
      if(_info_ == null)
        throw new IllegalArgumentException("info is required.");
  
      additionalProperties_ = builder.canonGetAdditionalProperties();
    }
  }


  /**
   * Factory class for OpenApiObject.
   */
  public static class Factory extends ObjectEntity.Factory<OpenApiObject>
  {
    @Override
    public String getCanonType()
    {
      return TYPE_ID;
    }

    @Override
    public OpenApiObject newInstance(JsonDomNode node, ModelRegistry modelRegistry)
    {
      if(node instanceof JsonObject)
      {
        return new OpenApiObject(new JsonInitialiser((JsonObject)node, modelRegistry));
      }

      if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
      {
        throw new ParserErrorException("OpenApiObject must be an Object node not " + node.getClass().getName(), node.getContext());
      }
      else
      {
        return null;
      }
    }
  }

  /**
   * Abstract Initialiser for OpenApiObject
   */
  public interface Initialiser extends IObjectEntityInitialiser
  {
    /**
     * Return an instance or builder containing the values for a new instance.
     * 
     * @return an instance or builder containing the values for a new instance.
     */
    I_OpenApiObject_InstanceOrBuilder getInstanceOrBuilder();
  }

  /**
   * JSON Initialiser for OpenApiObject
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
    public I_OpenApiObject_InstanceOrBuilder getInstanceOrBuilder()
    {
      return null;
    }
  }

  /**
   * Abstract builder for OpenApiObject. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <T> The concrete type of the builder, used for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends OpenApiObject_Entity>
    extends ObjectEntity.AbstractBuilder<T,B>
    implements I_OpenApiObject_InstanceOrBuilder, Initialiser
  {
    protected ComponentsObject           _components_;
    protected CanonGeneratorConfig       _xCanonGenerators_;
    protected String                     _openapi_;
    protected PathsObject                _paths_;
    protected String                     _xCanonId_;
    protected SemanticVersion            _xCanonVersion_;
    protected String                     _xCanonIdentifier_;
    protected String                     _canon_;
    protected InfoObject                 _info_;
    protected Map<String, Entity>        additionalProperties_ = new HashMap<>();

    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }

    @Override
    public I_OpenApiObject_InstanceOrBuilder getInstanceOrBuilder()
    {
      return this;
    }

    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);

      _components_ = initial.getComponents();
      _xCanonGenerators_ = initial.getXCanonGenerators();
      _openapi_ = initial.getOpenapi();
      _paths_ = initial.getPaths();
      _xCanonId_ = initial.getXCanonId();
      _xCanonVersion_ = initial.getXCanonVersion();
      _xCanonIdentifier_ = initial.getXCanonIdentifier();
      _canon_ = initial.getCanon();
      _info_ = initial.getInfo();
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
      if(json.containsKey("components"))
      {
        JsonDomNode  node = json.get("components");
// A1
//A2
    //A3
    
    
        if(node instanceof JsonObject)
        {
//A6a
          _components_ = ComponentsObject.FACTORY.newInstance((JsonObject)node, modelRegistry);
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("components must be an instance of JsonObject not " + node.getClass().getName(), node.getContext());
        }
      }
      if(json.containsKey("x-canon-generators"))
      {
        JsonDomNode  node = json.get("x-canon-generators");
// A1
//A2
    //A3
    
    
        if(node instanceof JsonObject)
        {
//A6a
          _xCanonGenerators_ = CanonGeneratorConfig.FACTORY.newInstance((JsonObject)node, modelRegistry);
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("x-canon-generators must be an instance of JsonObject not " + node.getClass().getName(), node.getContext());
        }
      }
      if(json.containsKey("openapi"))
      {
        JsonDomNode  node = json.get("openapi");
// A1
//A2
        if(node instanceof JsonString)
        {
//A6
          _openapi_ = ((JsonString)node).asString();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("openapi must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }
      if(json.containsKey("paths"))
      {
        JsonDomNode  node = json.get("paths");
// A1
//A2
    //A3
    
    
        if(node instanceof JsonObject)
        {
//A6a
          _paths_ = PathsObject.FACTORY.newInstance((JsonObject)node, modelRegistry);
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("paths must be an instance of JsonObject not " + node.getClass().getName(), node.getContext());
        }
      }
      if(json.containsKey("x-canon-id"))
      {
        JsonDomNode  node = json.get("x-canon-id");
// A1
//A2
        if(node instanceof JsonString)
        {
//A6
          _xCanonId_ = ((JsonString)node).asString();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("x-canon-id must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }
      if(json.containsKey("x-canon-version"))
      {
        JsonDomNode  node = json.get("x-canon-version");
// A1
//A2
        if(node instanceof JsonString)
        {
//A6
          _xCanonVersion_ = new SemanticVersion(((JsonString)node).asString());
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("x-canon-version must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
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
      if(json.containsKey("canon"))
      {
        JsonDomNode  node = json.get("canon");
// A1
//A2
        if(node instanceof JsonString)
        {
//A6
          _canon_ = ((JsonString)node).asString();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("canon must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }
      if(json.containsKey("info"))
      {
        JsonDomNode  node = json.get("info");
// A1
//A2
    //A3
    
    
        if(node instanceof JsonObject)
        {
//A6a
          _info_ = InfoObject.FACTORY.newInstance((JsonObject)node, modelRegistry);
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("info must be an instance of JsonObject not " + node.getClass().getName(), node.getContext());
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
      result.add(_components_);
      result.add(_xCanonGenerators_);
      result.add(_openapi_);
      result.add(_paths_);
      result.add(_xCanonId_);
      result.add(_xCanonVersion_);
      result.add(_xCanonIdentifier_);
      result.add(_canon_);
      result.add(_info_);
    }*/

    /**
     * Return the value of the components attribute.
     *
     * @return the value of the components attribute.
     */
    @Override
    public @Nullable ComponentsObject getComponents()
    {
      return _components_;
    }

    /**
     * Set the value of the components attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withComponents(ComponentsObject value)
    {
      _components_ = value;
      return self();
    }

    /**
     * Return the value of the x-canon-generators attribute.
     *
     * @return the value of the x-canon-generators attribute.
     */
    @Override
    public @Nullable CanonGeneratorConfig getXCanonGenerators()
    {
      return _xCanonGenerators_;
    }

    /**
     * Set the value of the x-canon-generators attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withXCanonGenerators(CanonGeneratorConfig value)
    {
      _xCanonGenerators_ = value;
      return self();
    }

    /**
     * Return the value of the openapi attribute.
     *
     * @return the value of the openapi attribute.
     */
    @Override
    public @Nullable String getOpenapi()
    {
      return _openapi_;
    }

    /**
     * Set the value of the openapi attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withOpenapi(String value)
    {
      _openapi_ = value;
      return self();
    }

    /**
     * Return the value of the paths attribute.
     *
     * @return the value of the paths attribute.
     */
    @Override
    public @Nullable PathsObject getPaths()
    {
      return _paths_;
    }

    /**
     * Set the value of the paths attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withPaths(PathsObject value)
    {
      _paths_ = value;
      return self();
    }

    /**
     * Return the value of the x-canon-id attribute.
     *
     * @return the value of the x-canon-id attribute.
     */
    @Override
    public @Nullable String getXCanonId()
    {
      return _xCanonId_;
    }

    /**
     * Set the value of the x-canon-id attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withXCanonId(String value)
    {
      _xCanonId_ = value;
      return self();
    }

    /**
     * Return the value of the x-canon-version attribute.
     *
     * @return the value of the x-canon-version attribute.
     */
    @Override
    public @Nullable SemanticVersion getXCanonVersion()
    {
      return _xCanonVersion_;
    }

    /**
     * Set the value of the x-canon-version attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withXCanonVersion(SemanticVersion value)
    {
      _xCanonVersion_ = value;
      return self();
    }

    /**
     * Set the value of the x-canon-version attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withXCanonVersion(String value)
    {
      _xCanonVersion_ = new SemanticVersion(value);
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
     * Return the value of the canon attribute.
     *
     * @return the value of the canon attribute.
     */
    @Override
    public @Nullable String getCanon()
    {
      return _canon_;
    }

    /**
     * Set the value of the canon attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withCanon(String value)
    {
      _canon_ = value;
      return self();
    }

    /**
     * Return the value of the info attribute.
     *
     * @return the value of the info attribute.
     */
    @Override
    public @Nonnull InfoObject getInfo()
    {
      if(_info_ == null)
        throw new IllegalStateException("Unexpected null value encountered");
      return _info_;
    }

    /**
     * Set the value of the info attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withInfo(InfoObject value)
    {
        if(value == null)
          throw new IllegalArgumentException("info is required.");
  
      _info_ = value;
      return self();
    }


    @Override
    public JsonObject getJson()
    {
      JsonObject.Builder builder = new JsonObject.Builder();

      builder.addIfNotNull(JSON_TYPE, OpenApiObject_Entity.TYPE_ID);
      builder.addIfNotNull(JSON_VERSION, OpenApiObject_Entity.TYPE_VERSION);

      populateJson(builder);

      return builder.build();
    }

    @Override
    public void populateJson(JsonObject.Builder builder)
    {
      super.populateJson(builder);

      if(getComponents() != null)
      {
        builder.addIfNotNull("components", getComponents().getJson());
      }


      if(getXCanonGenerators() != null)
      {
        builder.addIfNotNull("x-canon-generators", getXCanonGenerators().getJson());
      }


      if(getOpenapi() != null)
      {
        builder.addIfNotNull("openapi", getOpenapi());
      }


      if(getPaths() != null)
      {
        builder.addIfNotNull("paths", getPaths().getJson());
      }


      if(getXCanonId() != null)
      {
        builder.addIfNotNull("x-canon-id", getXCanonId());
      }


      if(getXCanonVersion() != null)
      {
        builder.addIfNotNull("x-canon-version", getXCanonVersion().getValue());
      }


      if(getXCanonIdentifier() != null)
      {
        builder.addIfNotNull("x-canon-identifier", getXCanonIdentifier());
      }


      if(getCanon() != null)
      {
        builder.addIfNotNull("canon", getCanon());
      }


      if(getInfo() != null)
      {
        builder.addIfNotNull("info", getInfo().getJson());
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
      faultAccumulator.checkNotNull(_info_, "info");
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
   * Builder for OpenApiObject
   */
  public static class Builder extends OpenApiObject.AbstractBuilder<Builder, OpenApiObject>
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
    public Builder(OpenApiObject initial)
    {
      super(Builder.class, initial);
    }

    @Override
    protected OpenApiObject construct()
    {
      return new OpenApiObject(this);
    }
  }


  /**
   * Return the value of the components attribute.
   *
   * @return the value of the components attribute.
   */
  public @Nullable ComponentsObject getComponents()
  {
    return _components_;
  }

  /**
   * Return the value of the x-canon-generators attribute.
   *
   * @return the value of the x-canon-generators attribute.
   */
  public @Nullable CanonGeneratorConfig getXCanonGenerators()
  {
    return _xCanonGenerators_;
  }

  /**
   * Return the value of the openapi attribute.
   *
   * @return the value of the openapi attribute.
   */
  public @Nullable String getOpenapi()
  {
    return _openapi_;
  }

  /**
   * Return the value of the paths attribute.
   *
   * @return the value of the paths attribute.
   */
  public @Nullable PathsObject getPaths()
  {
    return _paths_;
  }

  /**
   * Return the value of the x-canon-id attribute.
   *
   * @return the value of the x-canon-id attribute.
   */
  public @Nullable String getXCanonId()
  {
    return _xCanonId_;
  }

  /**
   * Return the value of the x-canon-version attribute.
   *
   * @return the value of the x-canon-version attribute.
   */
  public @Nullable SemanticVersion getXCanonVersion()
  {
    return _xCanonVersion_;
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
   * Return the value of the canon attribute.
   *
   * @return the value of the canon attribute.
   */
  public @Nullable String getCanon()
  {
    return _canon_;
  }

  /**
   * Return the value of the info attribute.
   *
   * @return the value of the info attribute.
   */
  public @Nonnull InfoObject getInfo()
  {
    return _info_;
  }

  @Override
  public boolean equals(Object obj)
  {
    if(obj instanceof OpenApiObject_Entity)
      return toString().equals(((OpenApiObject_Entity)obj).toString());

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