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
 *    At                   2020-11-10 17:41:52 GMT
 *----------------------------------------------------------------------------------------------------
 */
// importFields
// importField components nullable is Nullable
// importType ComponentsObject
// importType ComponentsObject
// importField x-canon-generators nullable is Nullable
// importType CanonGeneratorConfig
// importType CanonGeneratorConfig
// importField openapi nullable is Nullable
// importType openapi
// importType openapi
// importField paths nullable is Nullable
// importType PathsObject
// importType PathsObject
// importField x-canon-id nullable is Nullable
// importType x-canon-id
// importType x-canon-id
// importField x-canon-version nullable is Nullable
// importType SemanticVersion
// importType SemanticVersion
// importField x-canon-identifier nullable is Nullable
// importType x-canon-identifier
// importType x-canon-identifier
// importField canon nullable is Nullable
// importType canon
// importType canon
// importField info nullable is Nonnull
// importType InfoObject
// importType InfoObject
  // innerClass class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel
  // innerClass openapi
         
    // innerClass openapi isPrimitive
        
        
        
  // innerClass class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel
  // innerClass x-canon-id
         
    // innerClass x-canon-id isPrimitive
        
        
        
  // innerClass class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel
  // innerClass x-canon-identifier
         
    // innerClass x-canon-identifier isPrimitive
        
        
        
  // innerClass class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel
  // innerClass canon
         
    // innerClass canon isPrimitive
        
        
        

package com.symphony.oss.canon2.model;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableSet;
import com.symphony.oss.canon.json.ParserErrorException;
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
  /** Factory instance */
  public static final Factory FACTORY = new Factory();

  private final ImmutableSet<String>        unknownKeys_;
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
  public OpenApiObjectEntity(Initialiser initialiser)
  {
    super(initialiser);

    if(initialiser instanceof JsonObjectEntityInitialiser)
    {
      JsonObjectEntityInitialiser jsonInitialiser = (JsonObjectEntityInitialiser)initialiser;

      JsonDomNode  node;

      node = jsonInitialiser.get("components");
      if(node == null || node instanceof JsonNull)
      {
        _components_ = null;
      }
      else
      {
        _components_ = ComponentsObject.FACTORY.newInstance(node, jsonInitialiser.getModelRegistry());
      }

      node = jsonInitialiser.get("x-canon-generators");
      if(node == null || node instanceof JsonNull)
      {
        _xCanonGenerators_ = null;
      }
      else
      {
        _xCanonGenerators_ = CanonGeneratorConfig.FACTORY.newInstance(node, jsonInitialiser.getModelRegistry());
      }

      node = jsonInitialiser.get("openapi");
      if(node == null || node instanceof JsonNull)
      {
        _openapi_ = null;
      }
      else
      {
        if(node instanceof JsonString)
        {
// schema.class class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel name openapi type String javaType String
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
        _paths_ = PathsObject.FACTORY.newInstance(node, jsonInitialiser.getModelRegistry());
      }

      node = jsonInitialiser.get("x-canon-id");
      if(node == null || node instanceof JsonNull)
      {
        _xCanonId_ = null;
      }
      else
      {
        if(node instanceof JsonString)
        {
// schema.class class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel name x-canon-id type String javaType String
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
        if(node instanceof JsonString)
        {
// schema.class class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel name SemanticVersion type SemanticVersion javaType String
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

      node = jsonInitialiser.get("canon");
      if(node == null || node instanceof JsonNull)
      {
        _canon_ = null;
      }
      else
      {
        if(node instanceof JsonString)
        {
// schema.class class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel name canon type String javaType String
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
        _info_ = InfoObject.FACTORY.newInstance(node, jsonInitialiser.getModelRegistry());
      }
      unknownKeys_ = jsonInitialiser.getCanonUnknownKeys();
    }
    else
    {
      IOpenApiObjectInstanceOrBuilder builder =  initialiser.getInstanceOrBuilder();

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
    IOpenApiObjectInstanceOrBuilder getInstanceOrBuilder();
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
    public IOpenApiObjectInstanceOrBuilder getInstanceOrBuilder()
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
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends OpenApiObjectEntity>
// super class name
    extends ObjectEntity.AbstractBuilder<T,B>
    implements IOpenApiObjectInstanceOrBuilder, Initialiser
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
    public IOpenApiObjectInstanceOrBuilder getInstanceOrBuilder()
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
        _components_ = ComponentsObject.FACTORY.newInstance(node, modelRegistry);
      }
      if(jsonObject.containsKey("x-canon-generators"))
      {
        JsonDomNode  node = jsonObject.get("x-canon-generators");
        _xCanonGenerators_ = CanonGeneratorConfig.FACTORY.newInstance(node, modelRegistry);
      }
      if(jsonObject.containsKey("openapi"))
      {
        JsonDomNode  node = jsonObject.get("openapi");
        if(node instanceof JsonString)
        {
// schema.class class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel name openapi type String javaType String
          _openapi_ = ((JsonString)node).asString();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("openapi must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }
      if(jsonObject.containsKey("paths"))
      {
        JsonDomNode  node = jsonObject.get("paths");
        _paths_ = PathsObject.FACTORY.newInstance(node, modelRegistry);
      }
      if(jsonObject.containsKey("x-canon-id"))
      {
        JsonDomNode  node = jsonObject.get("x-canon-id");
        if(node instanceof JsonString)
        {
// schema.class class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel name x-canon-id type String javaType String
          _xCanonId_ = ((JsonString)node).asString();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("x-canon-id must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }
      if(jsonObject.containsKey("x-canon-version"))
      {
        JsonDomNode  node = jsonObject.get("x-canon-version");
        if(node instanceof JsonString)
        {
// schema.class class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel name SemanticVersion type SemanticVersion javaType String
          _xCanonVersion_ = new SemanticVersion(((JsonString)node).asString());
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("x-canon-version must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
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
      if(jsonObject.containsKey("canon"))
      {
        JsonDomNode  node = jsonObject.get("canon");
        if(node instanceof JsonString)
        {
// schema.class class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel name canon type String javaType String
          _canon_ = ((JsonString)node).asString();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new ParserErrorException("canon must be an instance of JsonString not " + node.getClass().getName(), node.getContext());
        }
      }
      if(jsonObject.containsKey("info"))
      {
        JsonDomNode  node = jsonObject.get("info");
        _info_ = InfoObject.FACTORY.newInstance(node, modelRegistry);
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
    public T withComponents(ComponentsObject value) //main
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
    public T withXCanonGenerators(CanonGeneratorConfig value) //main
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
    public T withOpenapi(String value) //main
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
    public T withPaths(PathsObject value) //main
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
    public T withXCanonId(String value) //main
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
    public T withXCanonVersion(SemanticVersion value) //main
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
    public T withXCanonVersion(String value) // primitive
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
    public T withXCanonIdentifier(String value) //main
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
    public T withCanon(String value) //main
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
    public T withInfo(InfoObject value) //main
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
    }

    @Override
    public void validate(FaultAccumulator faultAccumulator)
    {
      super.validate(faultAccumulator);
      faultAccumulator.checkNotNull(_info_, "info");
    }
  }

  @Override
  public ImmutableSet<String> getCanonUnknownKeys()
  {
    return unknownKeys_;
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
    if(obj instanceof OpenApiObjectEntity)
      return toString().equals(((OpenApiObjectEntity)obj).toString());

    return false;
  }

  @Override
  public int hashCode()
  {
    return toString().hashCode();
  }

// entity.additionalProperties??
// innerClasses
  // innerClass openapi STRING class com.symphony.oss.canon2.core.SchemaTemplateModelType
  // innerClass x-canon-id STRING class com.symphony.oss.canon2.core.SchemaTemplateModelType
  // innerClass x-canon-identifier STRING class com.symphony.oss.canon2.core.SchemaTemplateModelType
  // innerClass canon STRING class com.symphony.oss.canon2.core.SchemaTemplateModelType
}

/*----------------------------------------------------------------------------------------------------
 * End of template template/Object/_Entity.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */