  
  
// TRACE 1 imports
// javax.annotation.concurrent.Immutable
// com.symphony.oss.canon2.runtime.java.ModelRegistry
// com.symphony.oss.canon.json.model.JsonDomNode
// com.symphony.oss.canon.json.ParserErrorException
// com.symphony.oss.commons.fault.FaultAccumulator


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
 *    At                   2020-11-25 13:28:55 GMT
 *----------------------------------------------------------------------------------------------------
 */
  
  
// TRACE 2 imports
// javax.annotation.concurrent.Immutable
// com.symphony.oss.canon2.runtime.java.ModelRegistry
// com.symphony.oss.canon.json.model.JsonDomNode
// com.symphony.oss.canon.json.ParserErrorException
// com.symphony.oss.commons.fault.FaultAccumulator


  
  
// TRACE 3 imports
// javax.annotation.concurrent.Immutable
// com.symphony.oss.canon2.runtime.java.ModelRegistry
// com.symphony.oss.canon.json.model.JsonDomNode
// com.symphony.oss.canon.json.ParserErrorException
// com.symphony.oss.commons.fault.FaultAccumulator


// T2 A
      // field ReferenceObject
    
      // T B ReferenceObject
// schema.class class com.symphony.oss.canon2.generator.java.JavaObjectSchemaTemplateModel
    // add ReferenceObject -> com.symphony.oss.canon.json.model.JsonObject
      // field Schema
    
      // T B Schema
// schema.class class com.symphony.oss.canon2.generator.java.JavaObjectSchemaTemplateModel
  
  
// TRACE 4 imports
// javax.annotation.concurrent.Immutable
// com.symphony.oss.canon2.runtime.java.ModelRegistry
// com.symphony.oss.canon.json.model.JsonDomNode
// com.symphony.oss.canon.json.ParserErrorException
// com.symphony.oss.commons.fault.FaultAccumulator
// com.symphony.oss.canon2.runtime.java.IEntityInitialiser
// com.symphony.oss.canon2.runtime.java.JsonEntityInitialiser
// java.util.List
// java.util.LinkedList
// com.symphony.oss.canon.json.ParserException
// com.symphony.oss.canon.json.ParserResultException
// com.symphony.oss.canon2.runtime.java.Entity
// javax.annotation.Nullable
// com.symphony.oss.canon.json.model.JsonNull
// com.symphony.oss.canon.json.model.JsonObject
// javax.annotation.Nullable



package com.symphony.oss.canon2.model;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.json.ParserErrorException;
import com.symphony.oss.canon.json.ParserException;
import com.symphony.oss.canon.json.ParserResultException;
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
public abstract class SchemaOrRef_Entity extends Entity
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
  public SchemaOrRef_Entity(Initialiser initialiser)
  {
    super(initialiser);

    if(initialiser instanceof JsonEntityInitialiser)
    {
      JsonEntityInitialiser jsonInitialiser = (JsonEntityInitialiser)initialiser;
      ModelRegistry modelRegistry = jsonInitialiser.getModelRegistry();
      List<ParserException> parserExceptions = new LinkedList<>();
      List<String>          matches = new LinkedList<>();
      JsonDomNode           node = jsonInitialiser.getJson();
// A1
//A2
    //A3
    
    
       if(node instanceof JsonObject)
       {
//A6a
         _referenceObject_ = ReferenceObject.FACTORY.newInstanceOrNull(parserExceptions, (JsonObject)node, modelRegistry);
       }
       else 
       {
         _referenceObject_ = null;
       }
      if(_referenceObject_ != null)
      {
        matches.add("ReferenceObject");
      }

// A1
//A2
    
    
       if(node instanceof JsonDomNode)
       {
//A6a
         _schema_ = Schema.FACTORY.newInstanceOrNull(parserExceptions, (JsonDomNode)node, modelRegistry);
       }
       else 
       {
         _schema_ = null;
       }
      if(_schema_ != null)
      {
        matches.add("Schema");
      }

      if(matches.size() != 1)
      {
        throw new ParserErrorException("Exactly one of ReferenceObject,\n" +
          "Schema must be present but " + matches + " were encountered", jsonInitialiser.getJson().getContext(),
                   new ParserResultException(parserExceptions));
      }
    }
    else
    {
      I_SchemaOrRef_InstanceOrBuilder builder =  initialiser.getInstanceOrBuilder();

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
    I_SchemaOrRef_InstanceOrBuilder getInstanceOrBuilder();
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
    public I_SchemaOrRef_InstanceOrBuilder getInstanceOrBuilder()
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
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends SchemaOrRef_Entity>
    extends Entity.AbstractBuilder<T,B>
    implements I_SchemaOrRef_InstanceOrBuilder, Initialiser
  {
    protected ReferenceObject            _referenceObject_;
    protected Schema                     _schema_;

    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }

    @Override
    public I_SchemaOrRef_InstanceOrBuilder getInstanceOrBuilder()
    {
      return this;
    }

    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);

      _referenceObject_ = initial.getReferenceObject();
      _schema_ = initial.getSchema();
    }

    /**
     * Initialize this builder with the values from the given serialized form.
     * 
     * @param json          The serialized form of an instance of the built type.
     * @param modelRegistry A model registry.
     * 
     * @return This (fluent method).
     */
    public T withValues(JsonDomNode json, ModelRegistry modelRegistry)
    {
      List<ParserException> parserExceptions = new LinkedList<>();
      List<String>          matches = new LinkedList<>();
// A1
//A2
    //A3
    
    
       if(json instanceof JsonObject)
       {
//A6a
         _referenceObject_ = ReferenceObject.FACTORY.newInstanceOrNull(parserExceptions, (JsonObject)json, modelRegistry);
       }
       else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
       {
         _referenceObject_ = null;
       }
      if(_referenceObject_ != null)
      {
        matches.add("ReferenceObject");
      }

// A1
//A2
    
    
       if(json instanceof JsonDomNode)
       {
//A6a
         _schema_ = Schema.FACTORY.newInstanceOrNull(parserExceptions, (JsonDomNode)json, modelRegistry);
       }
       else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
       {
         _schema_ = null;
       }
      if(_schema_ != null)
      {
        matches.add("Schema");
      }

      if(matches.size() != 1)
      {
        throw new IllegalArgumentException("Exactly one of ReferenceObject,\n" +
          "Schema must be present but " + matches + " were encountered at " + json.getContext(),
                   new ParserResultException(parserExceptions));
      }
      return self();
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
    public T withReferenceObject(ReferenceObject value)
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
    public T withSchema(Schema value)
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
    if(obj instanceof SchemaOrRef_Entity)
      return toString().equals(((SchemaOrRef_Entity)obj).toString());

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

}

/*----------------------------------------------------------------------------------------------------
 * End of template template/Object/_Entity.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */