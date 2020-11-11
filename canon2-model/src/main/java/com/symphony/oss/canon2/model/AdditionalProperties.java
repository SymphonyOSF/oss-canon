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
 *    Template name        template/Object/_.java.ftl
 *    At                   2020-11-10 17:41:51 GMT
 *----------------------------------------------------------------------------------------------------
 */
// importFields
// importField SchemaOrRef nullable is Nullable
// importType SchemaOrRef
// importType SchemaOrRef
// importField $1 nullable is Nullable
// importType $1
// importType $1

package com.symphony.oss.canon2.model;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableSet;
import com.symphony.oss.canon.json.ParserErrorException;
import com.symphony.oss.canon.json.model.JsonBoolean;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonNull;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon2.runtime.java.Entity;
import com.symphony.oss.canon2.runtime.java.IEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.JsonEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;
import com.symphony.oss.commons.fault.FaultAccumulator;

/**
 * Implementation for Object AdditionalProperties
 * Generated from AdditionalProperties at {entity.context.path}
 */
@Immutable
public class AdditionalProperties extends Entity
{
  /** Type ID */
  public static final String  TYPE_ID = "com.symphony.oss.canon2.model.AdditionalProperties";
  /** Type version */
  public static final String  TYPE_VERSION = "1.0";
  /** Factory instance */
  public static final Factory FACTORY = new Factory();

  private final SchemaOrRef                _schemaOrRef_;
  private final Boolean                    _$1_;

  /**
   * Constructor.
   *
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  public AdditionalProperties(Initialiser initialiser)
  {
    super(initialiser);

    if(initialiser instanceof JsonEntityInitialiser)
    {
      JsonEntityInitialiser jsonInitialiser = (JsonEntityInitialiser)initialiser;

      JsonDomNode  node = jsonInitialiser.getJson();
      int          valueCnt = 0;
       _schemaOrRef_ = SchemaOrRef.FACTORY.newInstanceOrNull(node, jsonInitialiser.getModelRegistry());
      if(_schemaOrRef_ != null)
        valueCnt++;

       if(node instanceof JsonBoolean)
       {
// schema.class class com.symphony.oss.canon2.generator.java.JavaBooleanSchemaTemplateModel name $1 type Boolean javaType Boolean
         _$1_ = ((JsonBoolean)node).asBoolean();
       }
       else 
       {
         _$1_ = null;
       }
      if(_$1_ != null)
        valueCnt++;

      if(valueCnt != 1)
        throw new ParserErrorException("Exactly one value must be present", jsonInitialiser.getJson().getContext());
    }
    else
    {
      IAdditionalPropertiesInstanceOrBuilder builder =  initialiser.getInstanceOrBuilder();

      if(builder == null)
      {
        throw new IllegalArgumentException("Initializer is not an JsonObjectEntityInitialiser but getInstanceOrBuilder() returns null");
      }
      _schemaOrRef_ = builder.getSchemaOrRef();
      _$1_ = builder.get$1();
    }
  }


  /**
   * Factory class for AdditionalProperties.
   */
  public static class Factory extends Entity.Factory<AdditionalProperties>
  {
    @Override
    public String getCanonType()
    {
      return TYPE_ID;
    }

    @Override
    public AdditionalProperties newInstance(JsonDomNode node, ModelRegistry modelRegistry)
    {
      return new AdditionalProperties(new JsonInitialiser(node, modelRegistry));
    }
  }

  /**
   * Abstract Initialiser for AdditionalProperties
   */
  public interface Initialiser extends IEntityInitialiser
  {
    /**
     * Return an instance or builder containing the values for a new instance.
     * 
     * @return an instance or builder containing the values for a new instance.
     */
    IAdditionalPropertiesInstanceOrBuilder getInstanceOrBuilder();
  }

  /**
   * JSON Initialiser for AdditionalProperties
   */
  public static class JsonInitialiser extends JsonEntityInitialiser implements Initialiser
  {
      /**
       * Constructor.
       * 
       * @param json            JSON serialised form.
       * @param modelRegistry   A parser context for deserialisation.
       */
    public JsonInitialiser(JsonDomNode json, ModelRegistry modelRegistry)
    {
      super(json, modelRegistry);
    }

    @Override
    public IAdditionalPropertiesInstanceOrBuilder getInstanceOrBuilder()
    {
      return null;
    }
  }

  /**
   * Abstract builder for AdditionalProperties. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <T> The concrete type of the builder, used for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends AdditionalProperties>
// super class name
    extends Entity.AbstractBuilder<T,B>
    implements IAdditionalPropertiesInstanceOrBuilder, Initialiser
  {
    protected SchemaOrRef                _schemaOrRef_;
    protected Boolean                    _$1_;

    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }

    @Override
    public IAdditionalPropertiesInstanceOrBuilder getInstanceOrBuilder()
    {
      return this;
    }

    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);

      _schemaOrRef_ = initial.getSchemaOrRef();
      _$1_ = initial.get$1();
    }

    @Override
    public T withValues(JsonObject jsonObject, ModelRegistry modelRegistry)
    {
      if(jsonObject.containsKey("SchemaOrRef"))
      {
        JsonDomNode  node = jsonObject.get("SchemaOrRef");
        _schemaOrRef_ = SchemaOrRef.FACTORY.newInstanceOrNull(node, modelRegistry);
      }
      if(jsonObject.containsKey("$1"))
      {
        JsonDomNode  node = jsonObject.get("$1");
        if(node instanceof JsonBoolean)
        {
// schema.class class com.symphony.oss.canon2.generator.java.JavaBooleanSchemaTemplateModel name $1 type Boolean javaType Boolean
          _$1_ = ((JsonBoolean)node).asBoolean();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          _$1_ = null;
        }
      }
      return super.withValues(jsonObject, modelRegistry);
    }

    /* void populateAllFields(List<Object> result)
    {
      result.add(_schemaOrRef_);
      result.add(_$1_);
    }*/

    /**
     * Return the value of the SchemaOrRef attribute.
     *
     * @return the value of the SchemaOrRef attribute.
     */
    @Override
    public @Nullable SchemaOrRef getSchemaOrRef()
    {
      return _schemaOrRef_;
    }

    /**
     * Set the value of the SchemaOrRef attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withSchemaOrRef(SchemaOrRef value) //main
    {
      _schemaOrRef_ = value;
      return self();
    }

    /**
     * Return the value of the $1 attribute.
     *
     * @return the value of the $1 attribute.
     */
    @Override
    public @Nullable Boolean get$1()
    {
      return _$1_;
    }

    /**
     * Set the value of the $1 attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T with$1(Boolean value) //main
    {
      _$1_ = value;
      return self();
    }


    @Override
    public JsonDomNode getJson()
    {

      if(getSchemaOrRef() != null)
      {
        return getSchemaOrRef().getJson();
      }

      if(get$1() != null)
      {
        return JsonDomNode.newInstance(get$1());
      }
  
      throw new IllegalStateException("No value present in OneOf instance");
    }

    @Override
    public void validate(FaultAccumulator faultAccumulator)
    {
      super.validate(faultAccumulator);
      faultAccumulator.checkValueCount("fields", 1, 1,
        _schemaOrRef_,
        _$1_
      );
    }
  }


  /**
   * Builder for AdditionalProperties
   */
  public static class Builder extends AdditionalProperties.AbstractBuilder<Builder, AdditionalProperties>
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
    public Builder(AdditionalProperties initial)
    {
      super(Builder.class, initial);
    }

    @Override
    protected AdditionalProperties construct()
    {
      return new AdditionalProperties(this);
    }
  }


  /**
   * Return the value of the SchemaOrRef attribute.
   *
   * @return the value of the SchemaOrRef attribute.
   */
  public @Nullable SchemaOrRef getSchemaOrRef()
  {
    return _schemaOrRef_;
  }

  /**
   * Return the value of the $1 attribute.
   *
   * @return the value of the $1 attribute.
   */
  public @Nullable Boolean get$1()
  {
    return _$1_;
  }

  @Override
  public boolean equals(Object obj)
  {
    if(obj instanceof AdditionalProperties)
      return toString().equals(((AdditionalProperties)obj).toString());

    return false;
  }

  @Override
  public int hashCode()
  {
    return toString().hashCode();
  }


  /**
   * Return the value of this OneOf entity.
   *
   * @return the value of this OneOf entity.
   */
  public Object canonGetValue()
  {
    if(_schemaOrRef_ != null)
      return _schemaOrRef_;

    if(_$1_ != null)
      return _$1_;

    return null;
  }
// entity.additionalProperties??
// innerClasses
}

/*----------------------------------------------------------------------------------------------------
 * End of template template/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */