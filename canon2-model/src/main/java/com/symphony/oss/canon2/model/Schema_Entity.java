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
 * Implementation for Object Schema
 * Generated from Schema at {entity.context.path}
 */
@Immutable
public abstract class Schema_Entity extends Entity
{
  /** Type ID */
  public static final String  TYPE_ID = "com.symphony.oss.canon2.model.Schema";
  /** Type version */
  public static final String  TYPE_VERSION = "1.0";
  /** Factory instance */
  public static final Factory FACTORY = new Factory();

  private final BooleanSchema              _booleanSchema_;
  private final ArraySchema                _arraySchema_;
  private final ObjectSchema               _objectSchema_;
  private final StringSchema               _stringSchema_;
  private final NumberSchema               _numberSchema_;
  private final OneOfSchema                _oneOfSchema_;

  /**
   * Constructor.
   *
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  public Schema_Entity(Initialiser initialiser)
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
         _booleanSchema_ = BooleanSchema.FACTORY.newInstanceOrNull(parserExceptions, (JsonObject)node, modelRegistry);
       }
       else 
       {
         _booleanSchema_ = null;
       }
      if(_booleanSchema_ != null)
      {
        matches.add("BooleanSchema");
      }

// A1
//A2
    //A3
    
    
       if(node instanceof JsonObject)
       {
//A6a
         _arraySchema_ = ArraySchema.FACTORY.newInstanceOrNull(parserExceptions, (JsonObject)node, modelRegistry);
       }
       else 
       {
         _arraySchema_ = null;
       }
      if(_arraySchema_ != null)
      {
        matches.add("ArraySchema");
      }

// A1
//A2
    //A3
    
    
       if(node instanceof JsonObject)
       {
//A6a
         _objectSchema_ = ObjectSchema.FACTORY.newInstanceOrNull(parserExceptions, (JsonObject)node, modelRegistry);
       }
       else 
       {
         _objectSchema_ = null;
       }
      if(_objectSchema_ != null)
      {
        matches.add("ObjectSchema");
      }

// A1
//A2
    //A3
    
    
       if(node instanceof JsonObject)
       {
//A6a
         _stringSchema_ = StringSchema.FACTORY.newInstanceOrNull(parserExceptions, (JsonObject)node, modelRegistry);
       }
       else 
       {
         _stringSchema_ = null;
       }
      if(_stringSchema_ != null)
      {
        matches.add("StringSchema");
      }

// A1
//A2
    //A3
    
    
       if(node instanceof JsonObject)
       {
//A6a
         _numberSchema_ = NumberSchema.FACTORY.newInstanceOrNull(parserExceptions, (JsonObject)node, modelRegistry);
       }
       else 
       {
         _numberSchema_ = null;
       }
      if(_numberSchema_ != null)
      {
        matches.add("NumberSchema");
      }

// A1
//A2
    //A3
    
    
       if(node instanceof JsonObject)
       {
//A6a
         _oneOfSchema_ = OneOfSchema.FACTORY.newInstanceOrNull(parserExceptions, (JsonObject)node, modelRegistry);
       }
       else 
       {
         _oneOfSchema_ = null;
       }
      if(_oneOfSchema_ != null)
      {
        matches.add("OneOfSchema");
      }

      if(matches.size() != 1)
      {
        throw new ParserErrorException("Exactly one of BooleanSchema,\n" +
          "ArraySchema,\n" +
          "ObjectSchema,\n" +
          "StringSchema,\n" +
          "NumberSchema,\n" +
          "OneOfSchema must be present but " + matches + " were encountered", jsonInitialiser.getJson().getContext(),
                   new ParserResultException(parserExceptions));
      }
    }
    else
    {
      I_Schema_InstanceOrBuilder builder =  initialiser.getInstanceOrBuilder();

      if(builder == null)
      {
        throw new IllegalArgumentException("Initializer is not an JsonObjectEntityInitialiser but getInstanceOrBuilder() returns null");
      }
      _booleanSchema_ = builder.getBooleanSchema();
      _arraySchema_ = builder.getArraySchema();
      _objectSchema_ = builder.getObjectSchema();
      _stringSchema_ = builder.getStringSchema();
      _numberSchema_ = builder.getNumberSchema();
      _oneOfSchema_ = builder.getOneOfSchema();
    }
  }


  /**
   * Factory class for Schema.
   */
  public static class Factory extends Entity.Factory<Schema>
  {
    @Override
    public String getCanonType()
    {
      return TYPE_ID;
    }

    @Override
    public Schema newInstance(JsonDomNode node, ModelRegistry modelRegistry)
    {
      return new Schema(new JsonInitialiser(node, modelRegistry));
    }
  }

  /**
   * Abstract Initialiser for Schema
   */
  public interface Initialiser extends IEntityInitialiser
  {
    /**
     * Return an instance or builder containing the values for a new instance.
     * 
     * @return an instance or builder containing the values for a new instance.
     */
    I_Schema_InstanceOrBuilder getInstanceOrBuilder();
  }

  /**
   * JSON Initialiser for Schema
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
    public I_Schema_InstanceOrBuilder getInstanceOrBuilder()
    {
      return null;
    }
  }

  /**
   * Abstract builder for Schema. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <T> The concrete type of the builder, used for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends Schema_Entity>
    extends Entity.AbstractBuilder<T,B>
    implements I_Schema_InstanceOrBuilder, Initialiser
  {
    protected BooleanSchema              _booleanSchema_;
    protected ArraySchema                _arraySchema_;
    protected ObjectSchema               _objectSchema_;
    protected StringSchema               _stringSchema_;
    protected NumberSchema               _numberSchema_;
    protected OneOfSchema                _oneOfSchema_;

    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }

    @Override
    public I_Schema_InstanceOrBuilder getInstanceOrBuilder()
    {
      return this;
    }

    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);

      _booleanSchema_ = initial.getBooleanSchema();
      _arraySchema_ = initial.getArraySchema();
      _objectSchema_ = initial.getObjectSchema();
      _stringSchema_ = initial.getStringSchema();
      _numberSchema_ = initial.getNumberSchema();
      _oneOfSchema_ = initial.getOneOfSchema();
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
         _booleanSchema_ = BooleanSchema.FACTORY.newInstanceOrNull(parserExceptions, (JsonObject)json, modelRegistry);
       }
       else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
       {
         _booleanSchema_ = null;
       }
      if(_booleanSchema_ != null)
      {
        matches.add("BooleanSchema");
      }

// A1
//A2
    //A3
    
    
       if(json instanceof JsonObject)
       {
//A6a
         _arraySchema_ = ArraySchema.FACTORY.newInstanceOrNull(parserExceptions, (JsonObject)json, modelRegistry);
       }
       else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
       {
         _arraySchema_ = null;
       }
      if(_arraySchema_ != null)
      {
        matches.add("ArraySchema");
      }

// A1
//A2
    //A3
    
    
       if(json instanceof JsonObject)
       {
//A6a
         _objectSchema_ = ObjectSchema.FACTORY.newInstanceOrNull(parserExceptions, (JsonObject)json, modelRegistry);
       }
       else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
       {
         _objectSchema_ = null;
       }
      if(_objectSchema_ != null)
      {
        matches.add("ObjectSchema");
      }

// A1
//A2
    //A3
    
    
       if(json instanceof JsonObject)
       {
//A6a
         _stringSchema_ = StringSchema.FACTORY.newInstanceOrNull(parserExceptions, (JsonObject)json, modelRegistry);
       }
       else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
       {
         _stringSchema_ = null;
       }
      if(_stringSchema_ != null)
      {
        matches.add("StringSchema");
      }

// A1
//A2
    //A3
    
    
       if(json instanceof JsonObject)
       {
//A6a
         _numberSchema_ = NumberSchema.FACTORY.newInstanceOrNull(parserExceptions, (JsonObject)json, modelRegistry);
       }
       else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
       {
         _numberSchema_ = null;
       }
      if(_numberSchema_ != null)
      {
        matches.add("NumberSchema");
      }

// A1
//A2
    //A3
    
    
       if(json instanceof JsonObject)
       {
//A6a
         _oneOfSchema_ = OneOfSchema.FACTORY.newInstanceOrNull(parserExceptions, (JsonObject)json, modelRegistry);
       }
       else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
       {
         _oneOfSchema_ = null;
       }
      if(_oneOfSchema_ != null)
      {
        matches.add("OneOfSchema");
      }

      if(matches.size() != 1)
      {
        throw new IllegalArgumentException("Exactly one of BooleanSchema,\n" +
          "ArraySchema,\n" +
          "ObjectSchema,\n" +
          "StringSchema,\n" +
          "NumberSchema,\n" +
          "OneOfSchema must be present but " + matches + " were encountered at " + json.getContext(),
                   new ParserResultException(parserExceptions));
      }
      return self();
    }

    /* void populateAllFields(List<Object> result)
    {
      result.add(_booleanSchema_);
      result.add(_arraySchema_);
      result.add(_objectSchema_);
      result.add(_stringSchema_);
      result.add(_numberSchema_);
      result.add(_oneOfSchema_);
    }*/

    /**
     * Return the value of the BooleanSchema attribute.
     *
     * @return the value of the BooleanSchema attribute.
     */
    @Override
    public @Nullable BooleanSchema getBooleanSchema()
    {
      return _booleanSchema_;
    }

    /**
     * Set the value of the BooleanSchema attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withBooleanSchema(BooleanSchema value)
    {
      _booleanSchema_ = value;
      return self();
    }

    /**
     * Return the value of the ArraySchema attribute.
     *
     * @return the value of the ArraySchema attribute.
     */
    @Override
    public @Nullable ArraySchema getArraySchema()
    {
      return _arraySchema_;
    }

    /**
     * Set the value of the ArraySchema attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withArraySchema(ArraySchema value)
    {
      _arraySchema_ = value;
      return self();
    }

    /**
     * Return the value of the ObjectSchema attribute.
     *
     * @return the value of the ObjectSchema attribute.
     */
    @Override
    public @Nullable ObjectSchema getObjectSchema()
    {
      return _objectSchema_;
    }

    /**
     * Set the value of the ObjectSchema attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withObjectSchema(ObjectSchema value)
    {
      _objectSchema_ = value;
      return self();
    }

    /**
     * Return the value of the StringSchema attribute.
     *
     * @return the value of the StringSchema attribute.
     */
    @Override
    public @Nullable StringSchema getStringSchema()
    {
      return _stringSchema_;
    }

    /**
     * Set the value of the StringSchema attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withStringSchema(StringSchema value)
    {
      _stringSchema_ = value;
      return self();
    }

    /**
     * Return the value of the NumberSchema attribute.
     *
     * @return the value of the NumberSchema attribute.
     */
    @Override
    public @Nullable NumberSchema getNumberSchema()
    {
      return _numberSchema_;
    }

    /**
     * Set the value of the NumberSchema attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withNumberSchema(NumberSchema value)
    {
      _numberSchema_ = value;
      return self();
    }

    /**
     * Return the value of the OneOfSchema attribute.
     *
     * @return the value of the OneOfSchema attribute.
     */
    @Override
    public @Nullable OneOfSchema getOneOfSchema()
    {
      return _oneOfSchema_;
    }

    /**
     * Set the value of the OneOfSchema attribute.
     *
     * @param value The value to be set.
     *
     * @return This (fluent method).
     */
    public T withOneOfSchema(OneOfSchema value)
    {
      _oneOfSchema_ = value;
      return self();
    }


    @Override
    public JsonDomNode getJson()
    {

      if(getBooleanSchema() != null)
      {
        return getBooleanSchema().getJson();
      }

      if(getArraySchema() != null)
      {
        return getArraySchema().getJson();
      }

      if(getObjectSchema() != null)
      {
        return getObjectSchema().getJson();
      }

      if(getStringSchema() != null)
      {
        return getStringSchema().getJson();
      }

      if(getNumberSchema() != null)
      {
        return getNumberSchema().getJson();
      }

      if(getOneOfSchema() != null)
      {
        return getOneOfSchema().getJson();
      }
  
      throw new IllegalStateException("No value present in OneOf instance");
    }

    @Override
    public void validate(FaultAccumulator faultAccumulator)
    {
      super.validate(faultAccumulator);
      faultAccumulator.checkValueCount("fields", 1, 1,
        _booleanSchema_,
        _arraySchema_,
        _objectSchema_,
        _stringSchema_,
        _numberSchema_,
        _oneOfSchema_
      );
    }
  }

  /**
   * Builder for Schema
   */
  public static class Builder extends Schema.AbstractBuilder<Builder, Schema>
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
    public Builder(Schema initial)
    {
      super(Builder.class, initial);
    }

    @Override
    protected Schema construct()
    {
      return new Schema(this);
    }
  }


  /**
   * Return the value of the BooleanSchema attribute.
   *
   * @return the value of the BooleanSchema attribute.
   */
  public @Nullable BooleanSchema getBooleanSchema()
  {
    return _booleanSchema_;
  }

  /**
   * Return the value of the ArraySchema attribute.
   *
   * @return the value of the ArraySchema attribute.
   */
  public @Nullable ArraySchema getArraySchema()
  {
    return _arraySchema_;
  }

  /**
   * Return the value of the ObjectSchema attribute.
   *
   * @return the value of the ObjectSchema attribute.
   */
  public @Nullable ObjectSchema getObjectSchema()
  {
    return _objectSchema_;
  }

  /**
   * Return the value of the StringSchema attribute.
   *
   * @return the value of the StringSchema attribute.
   */
  public @Nullable StringSchema getStringSchema()
  {
    return _stringSchema_;
  }

  /**
   * Return the value of the NumberSchema attribute.
   *
   * @return the value of the NumberSchema attribute.
   */
  public @Nullable NumberSchema getNumberSchema()
  {
    return _numberSchema_;
  }

  /**
   * Return the value of the OneOfSchema attribute.
   *
   * @return the value of the OneOfSchema attribute.
   */
  public @Nullable OneOfSchema getOneOfSchema()
  {
    return _oneOfSchema_;
  }

  @Override
  public boolean equals(Object obj)
  {
    if(obj instanceof Schema_Entity)
      return toString().equals(((Schema_Entity)obj).toString());

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
    if(_booleanSchema_ != null)
      return _booleanSchema_;

    if(_arraySchema_ != null)
      return _arraySchema_;

    if(_objectSchema_ != null)
      return _objectSchema_;

    if(_stringSchema_ != null)
      return _stringSchema_;

    if(_numberSchema_ != null)
      return _numberSchema_;

    if(_oneOfSchema_ != null)
      return _oneOfSchema_;

    return null;
  }

}

/*----------------------------------------------------------------------------------------------------
 * End of template template/Object/_Entity.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */