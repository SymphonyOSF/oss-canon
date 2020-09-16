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
 * Implementation for Object  ReferenceObject canon
 * Object com.symphony.oss.canon2.generator.java.JavaOpenApiTemplateModel@25d250c6
 * Generated from JavaObjectSchemaTemplateModel [fields_=[JavaFieldTemplateModel $ref $ref]] at {entity.context.path}
 */
@Immutable
public abstract class ReferenceObjectEntity extends ObjectEntity
{
  /** Type ID */
  public static final String  TYPE_ID = "com.symphony.oss.canon2.model.ReferenceObject";
  /** Type version */
  public static final String  TYPE_VERSION = "1.0";
  /** Type major version */
  public static final Integer TYPE_MAJOR_VERSION = 1;
  /** Type minor version */
  public static final Integer TYPE_MINOR_VERSION = 0;
  /** Factory instance */
  public static final Factory FACTORY = new Factory();
  
  private final ImmutableSet<String>        unknownKeys_;
  // field JavaFieldTemplateModel $ref $ref field.typeSchema.name=$ref
  private final String                     _$ref_;

  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public ReferenceObjectEntity(AbstractBuilder<?,?> builder)
  {
    super(builder);
    
    _$ref_ = builder.get$ref();
    if(_$ref_ == null)
      throw new IllegalArgumentException("$ref is required.");
  
    unknownKeys_ = ImmutableSet.of();
  }
   
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public ReferenceObjectEntity(JsonObject jsonObject, ModelRegistry modelRegistry)
  {
    super(jsonObject);
  
    Set<String> keySet = new HashSet<>(super.getCanonUnknownKeys());
    
    if(keySet.remove("$ref"))
    {
      JsonDomNode  node = jsonObject.get("$ref");
//HERE0 node
      if(node instanceof IStringProvider)
      {
        _$ref_ = ((IStringProvider)node).asString();
      }
      else 
      {
        throw new IllegalArgumentException("$ref must be an instance of IStringProvider not " + node.getClass().getName());
      }
    }
    else
    {
      throw new IllegalArgumentException("$ref is required.");
    }

    unknownKeys_ = ImmutableSet.copyOf(keySet);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public ReferenceObjectEntity(ReferenceObject other)
  {
    super(other);
    
    _$ref_ = other.get$ref();

    unknownKeys_ = other.getCanonUnknownKeys();
  }
  
  @Override
  public ImmutableSet<String> getCanonUnknownKeys()
  {
    return unknownKeys_;
  }
  
  /**
   * Return the value of the $ref attribute.
   *
   * @return the value of the $ref attribute.
   */
  public String get$ref()
  {
    return _$ref_;
  }    

  @Override
  public boolean equals(Object obj)
  {
    if(obj instanceof ReferenceObjectEntity)
      return toString().equals(((ReferenceObjectEntity)obj).toString());
    
    return false;
  }

  @Override
  public int hashCode()
  {
    return toString().hashCode();
  }

  
  /**
   * Factory class for ReferenceObject.
   */
  public static class Factory extends ObjectEntity.Factory<ReferenceObjectEntity>
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
    public ReferenceObject newInstance(JsonObject jsonObject, ModelRegistry modelRegistry)
    {
      return new ReferenceObject(jsonObject, modelRegistry);
    }
  }
 
  
   
  /**
   * Builder for ReferenceObject
   */
  public static class Builder extends ReferenceObject.AbstractBuilder<Builder, ReferenceObject>
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
    public Builder(ReferenceObject initial)
    {
      super(Builder.class, initial);
    }

    @Override
    protected ReferenceObject construct()
    {
      return new ReferenceObject(this);
    }
  }
  
  
  /**
   * Abstract builder for ReferenceObject. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <T> The concrete type of the builder, used for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends ReferenceObjectEntity>
    extends ObjectEntity.AbstractBuilder<T,B>
  {
    protected String                     _$ref_;
  
    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);
      
      _$ref_ = initial.get$ref();
    }
    
    @Override
    public T withValues(JsonObject jsonObject, ModelRegistry modelRegistry)
    {
      if(jsonObject.containsKey("$ref"))
      {
        JsonDomNode  node = jsonObject.get("$ref");
//HERE0 node
        if(node instanceof IStringProvider)
        {
          _$ref_ = ((IStringProvider)node).asString();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("$ref must be an instance of IStringProvider not " + node.getClass().getName());
        }
      }
      return super.withValues(jsonObject, modelRegistry);
    }
    
    public void populateAllFields(List<Object> result)
    {
      result.add(_$ref_);
    }
    /**
     * Return the value of the $ref attribute.
     *
     * @return the value of the $ref attribute.
     */
    public String get$ref()
    {
      return _$ref_;
    }

    /**
     * Set the value of the $ref attribute.
     *
     * @param value The value to be set. 
     *
     * @return This (fluent method).
     */
     // base type String
     // base name $ref
    public T with$ref(String value)
    {
        if(value == null)
          throw new IllegalArgumentException("$ref is required.");
  
      _$ref_ = value;
      return self();
    }
    
    @Override 
    public JsonObject getJsonObject()
    {
      JsonObject.Builder builder = new JsonObject.Builder();
      
      builder.addIfNotNull(JSON_TYPE, ReferenceObjectEntity.TYPE_ID);
      builder.addIfNotNull(JSON_VERSION, ReferenceObjectEntity.TYPE_VERSION);

      populateJson(builder);
  
      return builder.build();
    }
    
    @Override 
    public void populateJson(JsonObject.Builder builder)
    {
      super.populateJson(builder);
  
      if(get$ref() != null)
      {
          builder.addIfNotNull("$ref", get$ref());
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
  
  // entity.name ReferenceObject
  // entity.class class com.symphony.oss.canon2.generator.java.JavaObjectSchemaTemplateModel
}

/*----------------------------------------------------------------------------------------------------
 * End of template template/Object/_Entity.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */