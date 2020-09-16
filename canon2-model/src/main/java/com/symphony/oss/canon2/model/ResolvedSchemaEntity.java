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
import com.symphony.oss.commons.type.provider.IBooleanProvider;
import com.symphony.oss.commons.type.provider.IStringProvider;

/**
 * Implementation for Object  ResolvedSchema canon
 * Object com.symphony.oss.canon2.generator.java.JavaOpenApiTemplateModel@25d250c6
 * Generated from JavaObjectSchemaTemplateModel [fields_=[JavaFieldTemplateModel IsGenerated isGenerated, JavaFieldTemplateModel Name name, JavaFieldTemplateModel Schema resolvedItems, JavaFieldTemplateModel ResolvedPropertiesObject resolvedProperties, JavaFieldTemplateModel ResolvedPropertiesObject innerClasses]] at {entity.context.path}
 */
@Immutable
public abstract class ResolvedSchemaEntity extends ObjectEntity
{
  /** Type ID */
  public static final String  TYPE_ID = "com.symphony.oss.canon2.model.ResolvedSchema";
  /** Type version */
  public static final String  TYPE_VERSION = "1.0";
  /** Type major version */
  public static final Integer TYPE_MAJOR_VERSION = 1;
  /** Type minor version */
  public static final Integer TYPE_MINOR_VERSION = 0;
  /** Factory instance */
  public static final Factory FACTORY = new Factory();
  
  private final ImmutableSet<String>        unknownKeys_;
  // field JavaFieldTemplateModel IsGenerated isGenerated field.typeSchema.name=isGenerated
  private final Boolean                    _isGenerated_;
  // field JavaFieldTemplateModel Name name field.typeSchema.name=name
  private final String                     _name_;
  // field JavaFieldTemplateModel Schema resolvedItems field.typeSchema.name=Schema
  private final Schema                     _resolvedItems_;
  // field JavaFieldTemplateModel ResolvedPropertiesObject resolvedProperties field.typeSchema.name=ResolvedPropertiesObject
  private final ResolvedPropertiesObject   _resolvedProperties_;
  // field JavaFieldTemplateModel ResolvedPropertiesObject innerClasses field.typeSchema.name=ResolvedPropertiesObject
  private final ResolvedPropertiesObject   _innerClasses_;

  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public ResolvedSchemaEntity(AbstractBuilder<?,?> builder)
  {
    super(builder);
    
    _isGenerated_ = builder.getIsGenerated();
    _name_ = builder.getName();
    _resolvedItems_ = builder.getResolvedItems();
    _resolvedProperties_ = builder.getResolvedProperties();
    _innerClasses_ = builder.getInnerClasses();
    unknownKeys_ = ImmutableSet.of();
  }
   
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public ResolvedSchemaEntity(JsonObject jsonObject, ModelRegistry modelRegistry)
  {
    super(jsonObject);
  
    Set<String> keySet = new HashSet<>(super.getCanonUnknownKeys());
    
    if(keySet.remove("isGenerated"))
    {
      JsonDomNode  node = jsonObject.get("isGenerated");
//HERE0 node
      if(node instanceof IBooleanProvider)
      {
        _isGenerated_ = ((IBooleanProvider)node).asBoolean();
      }
      else 
      {
        throw new IllegalArgumentException("isGenerated must be an instance of IBooleanProvider not " + node.getClass().getName());
      }
    }
    else
    {
      _isGenerated_ = null;
    }
    if(keySet.remove("name"))
    {
      JsonDomNode  node = jsonObject.get("name");
//HERE0 node
      if(node instanceof IStringProvider)
      {
        _name_ = ((IStringProvider)node).asString();
      }
      else 
      {
        throw new IllegalArgumentException("name must be an instance of IStringProvider not " + node.getClass().getName());
      }
    }
    else
    {
      _name_ = null;
    }
    if(keySet.remove("resolvedItems"))
    {
      JsonDomNode  node = jsonObject.get("resolvedItems");
//HERE0 node
      if(node instanceof JsonObject)
      {
        _resolvedItems_ = modelRegistry.newInstance((JsonObject)node, Schema.TYPE_ID, Schema.class);
      }
      else 
      {
        throw new IllegalArgumentException("resolvedItems must be an Object node not " + node.getClass().getName());
      }
    }
    else
    {
      _resolvedItems_ = null;
    }
    if(keySet.remove("resolvedProperties"))
    {
      JsonDomNode  node = jsonObject.get("resolvedProperties");
//HERE0 node
      if(node instanceof JsonObject)
      {
        _resolvedProperties_ = modelRegistry.newInstance((JsonObject)node, ResolvedPropertiesObject.TYPE_ID, ResolvedPropertiesObject.class);
      }
      else 
      {
        throw new IllegalArgumentException("resolvedProperties must be an Object node not " + node.getClass().getName());
      }
    }
    else
    {
      _resolvedProperties_ = null;
    }
    if(keySet.remove("innerClasses"))
    {
      JsonDomNode  node = jsonObject.get("innerClasses");
//HERE0 node
      if(node instanceof JsonObject)
      {
        _innerClasses_ = modelRegistry.newInstance((JsonObject)node, ResolvedPropertiesObject.TYPE_ID, ResolvedPropertiesObject.class);
      }
      else 
      {
        throw new IllegalArgumentException("innerClasses must be an Object node not " + node.getClass().getName());
      }
    }
    else
    {
      _innerClasses_ = null;
    }

    unknownKeys_ = ImmutableSet.copyOf(keySet);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public ResolvedSchemaEntity(ResolvedSchema other)
  {
    super(other);
    
    _isGenerated_ = other.getIsGenerated();
    _name_ = other.getName();
    _resolvedItems_ = other.getResolvedItems();
    _resolvedProperties_ = other.getResolvedProperties();
    _innerClasses_ = other.getInnerClasses();

    unknownKeys_ = other.getCanonUnknownKeys();
  }
  
  @Override
  public ImmutableSet<String> getCanonUnknownKeys()
  {
    return unknownKeys_;
  }
  
  /**
   * Return the value of the isGenerated attribute.
   *
   * @return the value of the isGenerated attribute.
   */
  public Boolean getIsGenerated()
  {
    return _isGenerated_;
  }    
  
  /**
   * Return the value of the name attribute.
   *
   * @return the value of the name attribute.
   */
  public String getName()
  {
    return _name_;
  }    
  
  /**
   * Return the value of the resolvedItems attribute.
   *
   * @return the value of the resolvedItems attribute.
   */
  public Schema getResolvedItems()
  {
    return _resolvedItems_;
  }    
  
  /**
   * Return the value of the resolvedProperties attribute.
   *
   * @return the value of the resolvedProperties attribute.
   */
  public ResolvedPropertiesObject getResolvedProperties()
  {
    return _resolvedProperties_;
  }    
  
  /**
   * Return the value of the innerClasses attribute.
   *
   * @return the value of the innerClasses attribute.
   */
  public ResolvedPropertiesObject getInnerClasses()
  {
    return _innerClasses_;
  }    

  @Override
  public boolean equals(Object obj)
  {
    if(obj instanceof ResolvedSchemaEntity)
      return toString().equals(((ResolvedSchemaEntity)obj).toString());
    
    return false;
  }

  @Override
  public int hashCode()
  {
    return toString().hashCode();
  }

  
  /**
   * Factory class for ResolvedSchema.
   */
  public static class Factory extends ObjectEntity.Factory<ResolvedSchemaEntity>
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
    public ResolvedSchema newInstance(JsonObject jsonObject, ModelRegistry modelRegistry)
    {
      return new ResolvedSchema(jsonObject, modelRegistry);
    }
  }
 
  
   
  /**
   * Builder for ResolvedSchema
   */
  public static class Builder extends ResolvedSchema.AbstractBuilder<Builder, ResolvedSchema>
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
    public Builder(ResolvedSchema initial)
    {
      super(Builder.class, initial);
    }

    @Override
    protected ResolvedSchema construct()
    {
      return new ResolvedSchema(this);
    }
  }
  
  
  /**
   * Abstract builder for ResolvedSchema. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <T> The concrete type of the builder, used for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends ResolvedSchemaEntity>
    extends ObjectEntity.AbstractBuilder<T,B>
  {
    protected Boolean                    _isGenerated_;
    protected String                     _name_;
    protected Schema                     _resolvedItems_;
    protected ResolvedPropertiesObject   _resolvedProperties_;
    protected ResolvedPropertiesObject   _innerClasses_;
  
    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);
      
      _isGenerated_ = initial.getIsGenerated();
      _name_ = initial.getName();
      _resolvedItems_ = initial.getResolvedItems();
      _resolvedProperties_ = initial.getResolvedProperties();
      _innerClasses_ = initial.getInnerClasses();
    }
    
    @Override
    public T withValues(JsonObject jsonObject, ModelRegistry modelRegistry)
    {
      if(jsonObject.containsKey("isGenerated"))
      {
        JsonDomNode  node = jsonObject.get("isGenerated");
//HERE0 node
        if(node instanceof IBooleanProvider)
        {
          _isGenerated_ = ((IBooleanProvider)node).asBoolean();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("isGenerated must be an instance of IBooleanProvider not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("name"))
      {
        JsonDomNode  node = jsonObject.get("name");
//HERE0 node
        if(node instanceof IStringProvider)
        {
          _name_ = ((IStringProvider)node).asString();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("name must be an instance of IStringProvider not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("resolvedItems"))
      {
        JsonDomNode  node = jsonObject.get("resolvedItems");
//HERE0 node
        if(node instanceof JsonObject)
        {
          _resolvedItems_ = modelRegistry.newInstance((JsonObject)node, Schema.TYPE_ID, Schema.class);
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("resolvedItems must be an Object node not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("resolvedProperties"))
      {
        JsonDomNode  node = jsonObject.get("resolvedProperties");
//HERE0 node
        if(node instanceof JsonObject)
        {
          _resolvedProperties_ = modelRegistry.newInstance((JsonObject)node, ResolvedPropertiesObject.TYPE_ID, ResolvedPropertiesObject.class);
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("resolvedProperties must be an Object node not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("innerClasses"))
      {
        JsonDomNode  node = jsonObject.get("innerClasses");
//HERE0 node
        if(node instanceof JsonObject)
        {
          _innerClasses_ = modelRegistry.newInstance((JsonObject)node, ResolvedPropertiesObject.TYPE_ID, ResolvedPropertiesObject.class);
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("innerClasses must be an Object node not " + node.getClass().getName());
        }
      }
      return super.withValues(jsonObject, modelRegistry);
    }
    
    public void populateAllFields(List<Object> result)
    {
      result.add(_isGenerated_);
      result.add(_name_);
      result.add(_resolvedItems_);
      result.add(_resolvedProperties_);
      result.add(_innerClasses_);
    }
    /**
     * Return the value of the isGenerated attribute.
     *
     * @return the value of the isGenerated attribute.
     */
    public Boolean getIsGenerated()
    {
      return _isGenerated_;
    }

    /**
     * Set the value of the isGenerated attribute.
     *
     * @param value The value to be set. 
     *
     * @return This (fluent method).
     */
     // base type Boolean
     // base name isGenerated
    public T withIsGenerated(Boolean value)
    {
      _isGenerated_ = value;
      return self();
    }
    
    /**
     * Return the value of the name attribute.
     *
     * @return the value of the name attribute.
     */
    public String getName()
    {
      return _name_;
    }

    /**
     * Set the value of the name attribute.
     *
     * @param value The value to be set. 
     *
     * @return This (fluent method).
     */
     // base type String
     // base name name
    public T withName(String value)
    {
      _name_ = value;
      return self();
    }
    
    /**
     * Return the value of the resolvedItems attribute.
     *
     * @return the value of the resolvedItems attribute.
     */
    public Schema getResolvedItems()
    {
      return _resolvedItems_;
    }

    /**
     * Set the value of the resolvedItems attribute.
     *
     * @param value The value to be set. 
     *
     * @return This (fluent method).
     */
     // base type Schema
     // base name Schema
    public T withResolvedItems(Schema value)
    {
      _resolvedItems_ = value;
      return self();
    }
    
    /**
     * Return the value of the resolvedProperties attribute.
     *
     * @return the value of the resolvedProperties attribute.
     */
    public ResolvedPropertiesObject getResolvedProperties()
    {
      return _resolvedProperties_;
    }

    /**
     * Set the value of the resolvedProperties attribute.
     *
     * @param value The value to be set. 
     *
     * @return This (fluent method).
     */
     // base type ResolvedPropertiesObject
     // base name ResolvedPropertiesObject
    public T withResolvedProperties(ResolvedPropertiesObject value)
    {
      _resolvedProperties_ = value;
      return self();
    }
    
    /**
     * Return the value of the innerClasses attribute.
     *
     * @return the value of the innerClasses attribute.
     */
    public ResolvedPropertiesObject getInnerClasses()
    {
      return _innerClasses_;
    }

    /**
     * Set the value of the innerClasses attribute.
     *
     * @param value The value to be set. 
     *
     * @return This (fluent method).
     */
     // base type ResolvedPropertiesObject
     // base name ResolvedPropertiesObject
    public T withInnerClasses(ResolvedPropertiesObject value)
    {
      _innerClasses_ = value;
      return self();
    }
    
    @Override 
    public JsonObject getJsonObject()
    {
      JsonObject.Builder builder = new JsonObject.Builder();
      
      builder.addIfNotNull(JSON_TYPE, ResolvedSchemaEntity.TYPE_ID);
      builder.addIfNotNull(JSON_VERSION, ResolvedSchemaEntity.TYPE_VERSION);

      populateJson(builder);
  
      return builder.build();
    }
    
    @Override 
    public void populateJson(JsonObject.Builder builder)
    {
      super.populateJson(builder);
  
      if(getIsGenerated() != null)
      {
          builder.addIfNotNull("isGenerated", getIsGenerated());
      }
  
      if(getName() != null)
      {
          builder.addIfNotNull("name", getName());
      }
  
      if(getResolvedItems() != null)
      {
          builder.addIfNotNull("resolvedItems", getResolvedItems().getJsonObject());
      }
  
      if(getResolvedProperties() != null)
      {
          builder.addIfNotNull("resolvedProperties", getResolvedProperties().getJsonObject());
      }
  
      if(getInnerClasses() != null)
      {
          builder.addIfNotNull("innerClasses", getInnerClasses().getJsonObject());
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
  
  // entity.name ResolvedSchema
  // entity.class class com.symphony.oss.canon2.generator.java.JavaObjectSchemaTemplateModel
}

/*----------------------------------------------------------------------------------------------------
 * End of template template/Object/_Entity.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */