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
// importField ReferenceObject nullable is Nullable
// importType ReferenceObject
// importType ReferenceObject
// importField Schema nullable is Nullable
// importType Schema
// importType Schema

package com.symphony.oss.canon2.model;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableSet;
import com.symphony.oss.canon.json.ParserErrorException;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonNull;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon2.runtime.java.Entity;
import com.symphony.oss.canon2.runtime.java.IEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.JsonEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;
import com.symphony.oss.commons.fault.FaultAccumulator;

/**
 * Implementation for Object SchemaOrRef
 * Generated from SchemaOrRef at {entity.context.path}
 */
@Immutable
public abstract class SchemaOrRefEntity extends Entity
{
  /** Type ID */
  public static final String  TYPE_ID = "com.symphony.oss.canon2.model.SchemaOrRef";
  /** Type version */
  public static final String  TYPE_VERSION = "1.0";
  /** Factory instance */
  public static final Factory FACTORY = new Factory();

  private final ReferenceObject            _referenceObject_;
  private final Schema                     _schema_;

  /**
   * Constructor.
   *
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  public SchemaOrRefEntity(Initialiser initialiser)
  {
    super(initialiser);

    if(initialiser instanceof JsonEntityInitialiser)
    {
      JsonEntityInitialiser jsonInitialiser = (JsonEntityInitialiser)initialiser;

      JsonDomNode  node = jsonInitialiser.getJson();
      int          valueCnt = 0;
       _referenceObject_ = ReferenceObject.FACTORY.newInstanceOrNull(node, jsonInitialiser.getModelRegistry());
      if(_referenceObject_ != null)
        valueCnt++;

       _schema_ = Schema.FACTORY.newInstanceOrNull(node, jsonInitialiser.getModelRegistry());
      if(_schema_ != null)
        valueCnt++;

      if(valueCnt != 1)
        throw new ParserErrorException("Exactly one value must be present", jsonInitialiser.getJson().getContext());
    }
    else
    {
      ISchemaOrRefInstanceOrBuilder builder =  initialiser.getInstanceOrBuilder();

      if(builder == null)
      {
        throw new IllegalArgumentException("Initializer is not an JsonObjectEntityInitialiser but getInstanceOrBuilder() returns null");
      }
      _referenceObject_ = builder.getReferenceObject();
      _schema_ = builder.getSchema();
    }
  }


  /**
   * Factory class for SchemaOrRef.
   */
  public static class Factory extends Entity.Factory<SchemaOrRef>
  {
    @Override
    public String getCanonType()
    {
      return TYPE_ID;
    }

    @Override
    public SchemaOrRef newInstance(JsonDomNode node, ModelRegistry modelRegistry)
    {
      return new SchemaOrRef(new JsonInitialiser(node, modelRegistry));
    }
  }

  /**
   * Abstract Initialiser for SchemaOrRef
   */
  public interface Initialiser extends IEntityInitialiser
  {
    /**
     * Return an instance or builder containing the values for a new instance.
     * 
     * @return an instance or builder containing the values for a new instance.
     */
    ISchemaOrRefInstanceOrBuilder getInstanceOrBuilder();
  }

  /**
   * JSON Initialiser for SchemaOrRef
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
    public ISchemaOrRefInstanceOrBuilder getInstanceOrBuilder()
    {
      return null;
    }
  }

  /**
   * Abstract builder for SchemaOrRef. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <T> The concrete type of the builder, used for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends SchemaOrRefEntity>
// super class name
    extends Entity.AbstractBuilder<T,B>
    implements ISchemaOrRefInstanceOrBuilder, Initialiser
  {
    protected ReferenceObject            _referenceObject_;
    protected Schema                     _schema_;

    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }

    @Override
    public ISchemaOrRefInstanceOrBuilder getInstanceOrBuilder()
    {
      return this;
    }

    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);

      _referenceObject_ = initial.getReferenceObject();
      _schema_ = initial.getSchema();
    }

    @Override
    public T withValues(JsonObject jsonObject, ModelRegistry modelRegistry)
    {
      if(jsonObject.containsKey("ReferenceObject"))
      {
        JsonDomNode  node = jsonObject.get("ReferenceObject");
        _referenceObject_ = ReferenceObject.FACTORY.newInstanceOrNull(node, modelRegistry);
      }
      if(jsonObject.containsKey("Schema"))
      {
        JsonDomNode  node = jsonObject.get("Schema");
        _schema_ = Schema.FACTORY.newInstanceOrNull(node, modelRegistry);
      }
      return super.withValues(jsonObject, modelRegistry);
    }

    /* void populateAllFields(List<Object> result)
    {
      result.add(_referenceObject_);
      result.add(_schema_);
    }*/

    /**
     * Return the value of the ReferenceObject attribute.
     *
     * @return the value of the ReferenceObject attribute.
     */
    @Override
    public @Nullable ReferenceObject getReferenceObject()
    {
      return _referenceObject_;
    }

    /**
     * Set the value of the ReferenceObject attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withReferenceObject(ReferenceObject value) //main
    {
      _referenceObject_ = value;
      return self();
    }

    /**
     * Return the value of the Schema attribute.
     *
     * @return the value of the Schema attribute.
     */
    @Override
    public @Nullable Schema getSchema()
    {
      return _schema_;
    }

    /**
     * Set the value of the Schema attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withSchema(Schema value) //main
    {
      _schema_ = value;
      return self();
    }


    @Override
    public JsonDomNode getJson()
    {

      if(getReferenceObject() != null)
      {
        return getReferenceObject().getJson();
      }

      if(getSchema() != null)
      {
        return getSchema().getJson();
      }
  
      throw new IllegalStateException("No value present in OneOf instance");
    }

    @Override
    public void validate(FaultAccumulator faultAccumulator)
    {
      super.validate(faultAccumulator);
      faultAccumulator.checkValueCount("fields", 1, 1,
        _referenceObject_,
        _schema_
      );
    }
  }


  /**
   * Builder for SchemaOrRef
   */
  public static class Builder extends SchemaOrRef.AbstractBuilder<Builder, SchemaOrRef>
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
    public Builder(SchemaOrRef initial)
    {
      super(Builder.class, initial);
    }

    @Override
    protected SchemaOrRef construct()
    {
      return new SchemaOrRef(this);
    }
  }


  /**
   * Return the value of the ReferenceObject attribute.
   *
   * @return the value of the ReferenceObject attribute.
   */
  public @Nullable ReferenceObject getReferenceObject()
  {
    return _referenceObject_;
  }

  /**
   * Return the value of the Schema attribute.
   *
   * @return the value of the Schema attribute.
   */
  public @Nullable Schema getSchema()
  {
    return _schema_;
  }

  @Override
  public boolean equals(Object obj)
  {
    if(obj instanceof SchemaOrRefEntity)
      return toString().equals(((SchemaOrRefEntity)obj).toString());

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
    if(_referenceObject_ != null)
      return _referenceObject_;

    if(_schema_ != null)
      return _schema_;

    return null;
  }
// entity.additionalProperties??
// innerClasses
}

/*----------------------------------------------------------------------------------------------------
 * End of template template/Object/_Entity.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */