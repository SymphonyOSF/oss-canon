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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableSet;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon2.runtime.java.IObjectEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.JsonObjectEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;
import com.symphony.oss.canon2.runtime.java.ObjectEntity;
import com.symphony.oss.commons.type.provider.IStringProvider;

/**
 * Implementation for Object OpenApiObject
 * Generated from OpenApiObject at {entity.context.path}
 */
@Immutable
public abstract class OpenApiObjectEntity extends ObjectEntity
{
  /** Type ID */
  public static final String  TYPE_ID = "com.symphony.oss.canon2.model.OpenApiObject";
  /** Type version */
  public static final String  TYPE_VERSION = "1.0";
  /** Type major version */
  public static final Integer TYPE_MAJOR_VERSION = 1;
  /** Type minor version */
  public static final Integer TYPE_MINOR_VERSION = 0;
  /** Factory instance */
  public static final Factory FACTORY = new Factory();

  private final ImmutableSet<String>        unknownKeys_;
  // field JavaFieldTemplateModel ComponentsObject components field.typeSchema.name=ComponentsObject
  private final ComponentsObject           _components_;
  // field JavaFieldTemplateModel CanonGeneratorConfig xCanonGenerators field.typeSchema.name=CanonGeneratorConfig
  private final CanonGeneratorConfig       _xCanonGenerators_;
  // field JavaFieldTemplateModel Openapi openapi field.typeSchema.name=openapi
  private final String                     _openapi_;
  // field JavaFieldTemplateModel PathsObject paths field.typeSchema.name=PathsObject
  private final PathsObject                _paths_;
  // field JavaFieldTemplateModel XCanonId xCanonId field.typeSchema.name=x-canon-id
  private final String                     _xCanonId_;
  // field JavaFieldTemplateModel SemanticVersion xCanonVersion field.typeSchema.name=SemanticVersion
  private final SemanticVersion            _xCanonVersion_;
  // field JavaFieldTemplateModel XCanonIdentifier xCanonIdentifier field.typeSchema.name=x-canon-identifier
  private final String                     _xCanonIdentifier_;
  // field JavaFieldTemplateModel Canon canon field.typeSchema.name=canon
  private final String                     _canon_;
  // field JavaFieldTemplateModel InfoObject info field.typeSchema.name=InfoObject
  private final InfoObject                 _info_;

  /**
   * Constructor.
   *
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  public OpenApiObjectEntity(Initialiser initialiser)
  {
    super(initialiser);

    if(initialiser instanceof JsonObjectEntityInitialiser)
    {
      JsonObjectEntityInitialiser jsonInitialiser = (JsonObjectEntityInitialiser)initialiser;

      JsonDomNode  node;

      node = jsonInitialiser.get("components");
      if(node != null)
      {
        if(node instanceof JsonObject)
        {
          _components_ = jsonInitialiser.getModelRegistry().newInstance((JsonObject)node, ComponentsObject.TYPE_ID, ComponentsObject.class);
        }
        else 
        {
          throw new IllegalArgumentException("components must be an Object node not " + node.getClass().getName());
        }
      }
      else
      {
        _components_ = null;
      }

      node = jsonInitialiser.get("x-canon-generators");
      if(node != null)
      {
        if(node instanceof JsonObject)
        {
          _xCanonGenerators_ = jsonInitialiser.getModelRegistry().newInstance((JsonObject)node, CanonGeneratorConfig.TYPE_ID, CanonGeneratorConfig.class);
        }
        else 
        {
          throw new IllegalArgumentException("x-canon-generators must be an Object node not " + node.getClass().getName());
        }
      }
      else
      {
        _xCanonGenerators_ = null;
      }

      node = jsonInitialiser.get("openapi");
      if(node != null)
      {
        if(node instanceof IStringProvider)
        {
          _openapi_ = ((IStringProvider)node).asString();
        }
        else 
        {
          throw new IllegalArgumentException("openapi must be an instance of IStringProvider not " + node.getClass().getName());
        }
      }
      else
      {
        _openapi_ = null;
      }

      node = jsonInitialiser.get("paths");
      if(node != null)
      {
        if(node instanceof JsonObject)
        {
          _paths_ = jsonInitialiser.getModelRegistry().newInstance((JsonObject)node, PathsObject.TYPE_ID, PathsObject.class);
        }
        else 
        {
          throw new IllegalArgumentException("paths must be an Object node not " + node.getClass().getName());
        }
      }
      else
      {
        _paths_ = null;
      }

      node = jsonInitialiser.get("x-canon-id");
      if(node != null)
      {
        if(node instanceof IStringProvider)
        {
          _xCanonId_ = ((IStringProvider)node).asString();
        }
        else 
        {
          throw new IllegalArgumentException("x-canon-id must be an instance of IStringProvider not " + node.getClass().getName());
        }
      }
      else
      {
        _xCanonId_ = null;
      }

      node = jsonInitialiser.get("x-canon-version");
      if(node != null)
      {
        if(node instanceof IStringProvider)
        {
          _xCanonVersion_ = new SemanticVersion(((IStringProvider)node).asString());
        }
        else 
        {
          throw new IllegalArgumentException("x-canon-version must be an instance of IStringProvider not " + node.getClass().getName());
        }
      }
      else
      {
        _xCanonVersion_ = null;
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

      node = jsonInitialiser.get("canon");
      if(node != null)
      {
        if(node instanceof IStringProvider)
        {
          _canon_ = ((IStringProvider)node).asString();
        }
        else 
        {
          throw new IllegalArgumentException("canon must be an instance of IStringProvider not " + node.getClass().getName());
        }
      }
      else
      {
        _canon_ = null;
      }

      node = jsonInitialiser.get("info");
      if(node != null)
      {
        if(node instanceof JsonObject)
        {
          _info_ = jsonInitialiser.getModelRegistry().newInstance((JsonObject)node, InfoObject.TYPE_ID, InfoObject.class);
        }
        else 
        {
          throw new IllegalArgumentException("info must be an Object node not " + node.getClass().getName());
        }
      }
      else
      {
        throw new IllegalArgumentException("info is required.");
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
  
      unknownKeys_ = builder.getCanonUnknownKeys();
    }
  }
  @Override
  public ImmutableSet<String> getCanonUnknownKeys()
  {
    return unknownKeys_;
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
    if(obj instanceof OpenApiObjectEntity)
      return toString().equals(((OpenApiObjectEntity)obj).toString());

    return false;
  }

  @Override
  public int hashCode()
  {
    return toString().hashCode();
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
    public OpenApiObject newInstance(JsonObject jsonObject, ModelRegistry modelRegistry)
    {
      return new OpenApiObject(new JsonInitialiser(jsonObject, modelRegistry));
    }
  }



  /**
   * Initialiser for OpenApiObject
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
   * Instance or Builder for Object OpenApiObject
   */
  public interface IInstanceOrBuilder extends IObjectEntityInitialiser
  {
    
    /**
     * Return the value of the components attribute.
     *
     * @return the value of the components attribute.
     */
    @Nullable ComponentsObject getComponents();
    
    /**
     * Return the value of the x-canon-generators attribute.
     *
     * @return the value of the x-canon-generators attribute.
     */
    @Nullable CanonGeneratorConfig getXCanonGenerators();
    
    /**
     * Return the value of the openapi attribute.
     *
     * @return the value of the openapi attribute.
     */
    @Nullable String getOpenapi();
    
    /**
     * Return the value of the paths attribute.
     *
     * @return the value of the paths attribute.
     */
    @Nullable PathsObject getPaths();
    
    /**
     * Return the value of the x-canon-id attribute.
     *
     * @return the value of the x-canon-id attribute.
     */
    @Nullable String getXCanonId();
    
    /**
     * Return the value of the x-canon-version attribute.
     *
     * @return the value of the x-canon-version attribute.
     */
    @Nullable SemanticVersion getXCanonVersion();
    
    /**
     * Return the value of the x-canon-identifier attribute.
     *
     * @return the value of the x-canon-identifier attribute.
     */
    @Nullable String getXCanonIdentifier();
    
    /**
     * Return the value of the canon attribute.
     *
     * @return the value of the canon attribute.
     */
    @Nullable String getCanon();
    
    /**
     * Return the value of the info attribute.
     *
     * @return the value of the info attribute.
     */
    @Nonnull InfoObject getInfo();
  }


  /**
   * Abstract builder for OpenApiObject. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <T> The concrete type of the builder, used for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends OpenApiObjectEntity>
    extends ObjectEntity.AbstractBuilder<T,B>
    implements IInstanceOrBuilder, Initialiser
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

    @Override
    public T withValues(JsonObject jsonObject, ModelRegistry modelRegistry)
    {
      if(jsonObject.containsKey("components"))
      {
        JsonDomNode  node = jsonObject.get("components");
        if(node instanceof JsonObject)
        {
          _components_ = modelRegistry.newInstance((JsonObject)node, ComponentsObject.TYPE_ID, ComponentsObject.class);
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("components must be an Object node not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("x-canon-generators"))
      {
        JsonDomNode  node = jsonObject.get("x-canon-generators");
        if(node instanceof JsonObject)
        {
          _xCanonGenerators_ = modelRegistry.newInstance((JsonObject)node, CanonGeneratorConfig.TYPE_ID, CanonGeneratorConfig.class);
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("x-canon-generators must be an Object node not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("openapi"))
      {
        JsonDomNode  node = jsonObject.get("openapi");
        if(node instanceof IStringProvider)
        {
          _openapi_ = ((IStringProvider)node).asString();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("openapi must be an instance of IStringProvider not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("paths"))
      {
        JsonDomNode  node = jsonObject.get("paths");
        if(node instanceof JsonObject)
        {
          _paths_ = modelRegistry.newInstance((JsonObject)node, PathsObject.TYPE_ID, PathsObject.class);
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("paths must be an Object node not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("x-canon-id"))
      {
        JsonDomNode  node = jsonObject.get("x-canon-id");
        if(node instanceof IStringProvider)
        {
          _xCanonId_ = ((IStringProvider)node).asString();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("x-canon-id must be an instance of IStringProvider not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("x-canon-version"))
      {
        JsonDomNode  node = jsonObject.get("x-canon-version");
        if(node instanceof IStringProvider)
        {
          _xCanonVersion_ = new SemanticVersion(((IStringProvider)node).asString());
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("x-canon-version must be an instance of IStringProvider not " + node.getClass().getName());
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
      if(jsonObject.containsKey("canon"))
      {
        JsonDomNode  node = jsonObject.get("canon");
        if(node instanceof IStringProvider)
        {
          _canon_ = ((IStringProvider)node).asString();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("canon must be an instance of IStringProvider not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("info"))
      {
        JsonDomNode  node = jsonObject.get("info");
        if(node instanceof JsonObject)
        {
          _info_ = modelRegistry.newInstance((JsonObject)node, InfoObject.TYPE_ID, InfoObject.class);
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("info must be an Object node not " + node.getClass().getName());
        }
      }
      return super.withValues(jsonObject, modelRegistry);
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
     // base type ComponentsObject
     // base name ComponentsObject
    public T withComponents(ComponentsObject value)
    {
      _components_ = value;
      return self();
    }
// field.typeSchema.schemaType OBJECT

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
     // base type CanonGeneratorConfig
     // base name CanonGeneratorConfig
    public T withXCanonGenerators(CanonGeneratorConfig value)
    {
      _xCanonGenerators_ = value;
      return self();
    }
// field.typeSchema.schemaType OBJECT

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
     // base type String
     // base name openapi
    public T withOpenapi(String value)
    {
      _openapi_ = value;
      return self();
    }
// field.typeSchema.schemaType STRING

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
     // base type PathsObject
     // base name PathsObject
    public T withPaths(PathsObject value)
    {
      _paths_ = value;
      return self();
    }
// field.typeSchema.schemaType OBJECT

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
     // base type String
     // base name x-canon-id
    public T withXCanonId(String value)
    {
      _xCanonId_ = value;
      return self();
    }
// field.typeSchema.schemaType STRING

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
     // base type SemanticVersion
     // base name SemanticVersion
    public T withXCanonVersion(SemanticVersion value)
    {
      _xCanonVersion_ = value;
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
     // base type String
     // base name canon
    public T withCanon(String value)
    {
      _canon_ = value;
      return self();
    }
// field.typeSchema.schemaType STRING

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
     // base type InfoObject
     // base name InfoObject
    public T withInfo(InfoObject value)
    {
        if(value == null)
          throw new IllegalArgumentException("info is required.");
  
      _info_ = value;
      return self();
    }
// field.typeSchema.schemaType OBJECT

    @Override
    public JsonObject getJsonObject()
    {
      JsonObject.Builder builder = new JsonObject.Builder();

      builder.addIfNotNull(JSON_TYPE, OpenApiObjectEntity.TYPE_ID);
      builder.addIfNotNull(JSON_VERSION, OpenApiObjectEntity.TYPE_VERSION);

      populateJson(builder);

      return builder.build();
    }

    @Override
    public void populateJson(JsonObject.Builder builder)
    {
      super.populateJson(builder);

      if(getComponents() != null)
      {
          builder.addIfNotNull("components", getComponents().getJsonObject());
      }

      if(getXCanonGenerators() != null)
      {
          builder.addIfNotNull("x-canon-generators", getXCanonGenerators().getJsonObject());
      }

      if(getOpenapi() != null)
      {
          builder.addIfNotNull("openapi", getOpenapi());
      }

      if(getPaths() != null)
      {
          builder.addIfNotNull("paths", getPaths().getJsonObject());
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
          builder.addIfNotNull("info", getInfo().getJsonObject());
      }
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

  // entity.name OpenApiObject
  // entity.class class com.symphony.oss.canon2.generator.java.JavaObjectSchemaTemplateModel
}

/*----------------------------------------------------------------------------------------------------
 * End of template template/Object/_Entity.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */