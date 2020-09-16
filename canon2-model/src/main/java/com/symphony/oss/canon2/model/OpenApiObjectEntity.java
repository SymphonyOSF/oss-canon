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
 *    Input source         file:/Users/bruce/symphony/git-SymphonyOSF/oss-canon/canon2-model/src/main/resources/canon/canon.json
 *    Generator groupId    org.symphonyoss.s2.canon
 *              artifactId canon2-generator-java
 *    Template name        template/Object/_Entity.java.ftl
 *    At                   2020-09-16 13:40:31 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableSet;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;
import com.symphony.oss.canon2.runtime.java.ObjectEntity;
import com.symphony.oss.commons.type.provider.IStringProvider;

/**
 * Implementation for Object  OpenApiObject canon
 * Object com.symphony.oss.canon2.generator.java.JavaOpenApiTemplateModel@25d250c6
 * Generated from JavaObjectSchemaTemplateModel [fields_=[JavaFieldTemplateModel ComponentsObject components, JavaFieldTemplateModel CanonGeneratorConfig xCanonGenerators, JavaFieldTemplateModel Openapi openapi, JavaFieldTemplateModel PathsObject paths, JavaFieldTemplateModel XCanonId xCanonId, JavaFieldTemplateModel SemanticVersion xCanonVersion, JavaFieldTemplateModel XCanonIdentifier xCanonIdentifier, JavaFieldTemplateModel Canon canon, JavaFieldTemplateModel InfoObject info]] at {entity.context.path}
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
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public OpenApiObjectEntity(AbstractBuilder<?,?> builder)
  {
    super(builder);
    
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
  
    unknownKeys_ = ImmutableSet.of();
  }
   
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public OpenApiObjectEntity(JsonObject jsonObject, ModelRegistry modelRegistry)
  {
    super(jsonObject);
  
    Set<String> keySet = new HashSet<>(super.getCanonUnknownKeys());
    
    if(keySet.remove("components"))
    {
      JsonDomNode  node = jsonObject.get("components");
//HERE0 node
      if(node instanceof JsonObject)
      {
        _components_ = modelRegistry.newInstance((JsonObject)node, ComponentsObject.TYPE_ID, ComponentsObject.class);
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
    if(keySet.remove("x-canon-generators"))
    {
      JsonDomNode  node = jsonObject.get("x-canon-generators");
//HERE0 node
      if(node instanceof JsonObject)
      {
        _xCanonGenerators_ = modelRegistry.newInstance((JsonObject)node, CanonGeneratorConfig.TYPE_ID, CanonGeneratorConfig.class);
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
    if(keySet.remove("openapi"))
    {
      JsonDomNode  node = jsonObject.get("openapi");
//HERE0 node
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
    if(keySet.remove("paths"))
    {
      JsonDomNode  node = jsonObject.get("paths");
//HERE0 node
      if(node instanceof JsonObject)
      {
        _paths_ = modelRegistry.newInstance((JsonObject)node, PathsObject.TYPE_ID, PathsObject.class);
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
    if(keySet.remove("x-canon-id"))
    {
      JsonDomNode  node = jsonObject.get("x-canon-id");
//HERE0 node
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
    if(keySet.remove("x-canon-version"))
    {
      JsonDomNode  node = jsonObject.get("x-canon-version");
//HERE0 node
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
    if(keySet.remove("x-canon-identifier"))
    {
      JsonDomNode  node = jsonObject.get("x-canon-identifier");
//HERE0 node
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
    if(keySet.remove("canon"))
    {
      JsonDomNode  node = jsonObject.get("canon");
//HERE0 node
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
    if(keySet.remove("info"))
    {
      JsonDomNode  node = jsonObject.get("info");
//HERE0 node
      if(node instanceof JsonObject)
      {
        _info_ = modelRegistry.newInstance((JsonObject)node, InfoObject.TYPE_ID, InfoObject.class);
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

    unknownKeys_ = ImmutableSet.copyOf(keySet);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public OpenApiObjectEntity(OpenApiObject other)
  {
    super(other);
    
    _components_ = other.getComponents();
    _xCanonGenerators_ = other.getXCanonGenerators();
    _openapi_ = other.getOpenapi();
    _paths_ = other.getPaths();
    _xCanonId_ = other.getXCanonId();
    _xCanonVersion_ = other.getXCanonVersion();
    _xCanonIdentifier_ = other.getXCanonIdentifier();
    _canon_ = other.getCanon();
    _info_ = other.getInfo();

    unknownKeys_ = other.getCanonUnknownKeys();
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
  public ComponentsObject getComponents()
  {
    return _components_;
  }    
  
  /**
   * Return the value of the x-canon-generators attribute.
   *
   * @return the value of the x-canon-generators attribute.
   */
  public CanonGeneratorConfig getXCanonGenerators()
  {
    return _xCanonGenerators_;
  }    
  
  /**
   * Return the value of the openapi attribute.
   *
   * @return the value of the openapi attribute.
   */
  public String getOpenapi()
  {
    return _openapi_;
  }    
  
  /**
   * Return the value of the paths attribute.
   *
   * @return the value of the paths attribute.
   */
  public PathsObject getPaths()
  {
    return _paths_;
  }    
  
  /**
   * Return the value of the x-canon-id attribute.
   *
   * @return the value of the x-canon-id attribute.
   */
  public String getXCanonId()
  {
    return _xCanonId_;
  }    
  
  /**
   * Return the value of the x-canon-version attribute.
   *
   * @return the value of the x-canon-version attribute.
   */
  public SemanticVersion getXCanonVersion()
  {
    return _xCanonVersion_;
  }    
  
  /**
   * Return the value of the x-canon-identifier attribute.
   *
   * @return the value of the x-canon-identifier attribute.
   */
  public String getXCanonIdentifier()
  {
    return _xCanonIdentifier_;
  }    
  
  /**
   * Return the value of the canon attribute.
   *
   * @return the value of the canon attribute.
   */
  public String getCanon()
  {
    return _canon_;
  }    
  
  /**
   * Return the value of the info attribute.
   *
   * @return the value of the info attribute.
   */
  public InfoObject getInfo()
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
  public static class Factory extends ObjectEntity.Factory<OpenApiObjectEntity>
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
      return new OpenApiObject(jsonObject, modelRegistry);
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
  
  
  /**
   * Abstract builder for OpenApiObject. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <T> The concrete type of the builder, used for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends OpenApiObjectEntity>
    extends ObjectEntity.AbstractBuilder<T,B>
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
//HERE0 node
        if(node instanceof JsonObject)
        {
          _components_ = modelRegistry.newInstance((JsonObject)node, ComponentsObject.TYPE_ID, ComponentsObject.class);
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("components must be an Object node not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("xCanonGenerators"))
      {
        JsonDomNode  node = jsonObject.get("xCanonGenerators");
//HERE0 node
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
//HERE0 node
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
//HERE0 node
        if(node instanceof JsonObject)
        {
          _paths_ = modelRegistry.newInstance((JsonObject)node, PathsObject.TYPE_ID, PathsObject.class);
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("paths must be an Object node not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("xCanonId"))
      {
        JsonDomNode  node = jsonObject.get("xCanonId");
//HERE0 node
        if(node instanceof IStringProvider)
        {
          _xCanonId_ = ((IStringProvider)node).asString();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("x-canon-id must be an instance of IStringProvider not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("xCanonVersion"))
      {
        JsonDomNode  node = jsonObject.get("xCanonVersion");
//HERE0 node
        if(node instanceof IStringProvider)
        {
          _xCanonVersion_ = new SemanticVersion(((IStringProvider)node).asString());
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("x-canon-version must be an instance of IStringProvider not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("xCanonIdentifier"))
      {
        JsonDomNode  node = jsonObject.get("xCanonIdentifier");
//HERE0 node
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
//HERE0 node
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
//HERE0 node
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
    
    public void populateAllFields(List<Object> result)
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
    }
    /**
     * Return the value of the components attribute.
     *
     * @return the value of the components attribute.
     */
    public ComponentsObject getComponents()
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
    
    /**
     * Return the value of the x-canon-generators attribute.
     *
     * @return the value of the x-canon-generators attribute.
     */
    public CanonGeneratorConfig getXCanonGenerators()
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
    
    /**
     * Return the value of the openapi attribute.
     *
     * @return the value of the openapi attribute.
     */
    public String getOpenapi()
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
    
    /**
     * Return the value of the paths attribute.
     *
     * @return the value of the paths attribute.
     */
    public PathsObject getPaths()
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
    
    /**
     * Return the value of the x-canon-id attribute.
     *
     * @return the value of the x-canon-id attribute.
     */
    public String getXCanonId()
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
    
    /**
     * Return the value of the x-canon-version attribute.
     *
     * @return the value of the x-canon-version attribute.
     */
    public SemanticVersion getXCanonVersion()
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
    
    /**
     * Set the value of the x-canon-version attribute.
     *
     * @param value The value to be set. 
     *
     * @return This (fluent method).
     */
     //typedef
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
    public String getXCanonIdentifier()
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
    
    /**
     * Return the value of the canon attribute.
     *
     * @return the value of the canon attribute.
     */
    public String getCanon()
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
    
    /**
     * Return the value of the info attribute.
     *
     * @return the value of the info attribute.
     */
    public InfoObject getInfo()
    {
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