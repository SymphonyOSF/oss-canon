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
 *    At                   2020-09-16 15:04:08 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.symphony.oss.canon.json.model.JsonArray;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;
import com.symphony.oss.canon2.runtime.java.ObjectEntity;
import com.symphony.oss.commons.type.provider.IBooleanProvider;
import com.symphony.oss.commons.type.provider.IDoubleProvider;
import com.symphony.oss.commons.type.provider.IIntegerProvider;
import com.symphony.oss.commons.type.provider.IStringProvider;

/**
 * Implementation for Object  Schema canon
 * Object com.symphony.oss.canon2.generator.java.JavaOpenApiTemplateModel@4df50bcc
 * Generated from JavaObjectSchemaTemplateModel [fields_=[JavaFieldTemplateModel MaxItems maxItems, JavaFieldTemplateModel Format format, JavaFieldTemplateModel CanonCardinality xCanonCardinality, JavaFieldTemplateModel XCanonIdentifier xCanonIdentifier, JavaFieldTemplateModel Type type, JavaFieldTemplateModel XCanonFacade xCanonFacade, JavaFieldTemplateModel Enum enum, JavaFieldTemplateModel Required required, JavaFieldTemplateModel MinItems minItems, JavaFieldTemplateModel CanonAttributes xCanonAttributes, JavaFieldTemplateModel Maximum maximum, JavaFieldTemplateModel Minimum minimum, JavaFieldTemplateModel PropertiesObject properties]] at {entity.context.path}
 */
@Immutable
public abstract class SchemaEntity extends ObjectEntity
{
  /** Type ID */
  public static final String  TYPE_ID = "com.symphony.oss.canon2.model.Schema";
  /** Type version */
  public static final String  TYPE_VERSION = "1.0";
  /** Type major version */
  public static final Integer TYPE_MAJOR_VERSION = 1;
  /** Type minor version */
  public static final Integer TYPE_MINOR_VERSION = 0;
  /** Factory instance */
  public static final Factory FACTORY = new Factory();
  
  private final ImmutableSet<String>        unknownKeys_;
  // field JavaFieldTemplateModel MaxItems maxItems field.typeSchema.name=maxItems
  private final Integer                    _maxItems_;
  // field JavaFieldTemplateModel Format format field.typeSchema.name=format
  private final String                     _format_;
  // field JavaFieldTemplateModel CanonCardinality xCanonCardinality field.typeSchema.name=CanonCardinality
  private final CanonCardinality           _xCanonCardinality_;
  // field JavaFieldTemplateModel XCanonIdentifier xCanonIdentifier field.typeSchema.name=x-canon-identifier
  private final String                     _xCanonIdentifier_;
  // field JavaFieldTemplateModel Type type field.typeSchema.name=type
  private final String                     _type_;
  // field JavaFieldTemplateModel XCanonFacade xCanonFacade field.typeSchema.name=x-canon-facade
  private final Boolean                    _xCanonFacade_;
  // field JavaFieldTemplateModel Enum enum field.typeSchema.name=enum
  private final List<String>               _enum_;
  // field JavaFieldTemplateModel Required required field.typeSchema.name=required
  private final List<String>               _required_;
  // field JavaFieldTemplateModel MinItems minItems field.typeSchema.name=minItems
  private final Integer                    _minItems_;
  // field JavaFieldTemplateModel CanonAttributes xCanonAttributes field.typeSchema.name=CanonAttributes
  private final CanonAttributes            _xCanonAttributes_;
  // field JavaFieldTemplateModel Maximum maximum field.typeSchema.name=maximum
  private final Double                     _maximum_;
  // field JavaFieldTemplateModel Minimum minimum field.typeSchema.name=minimum
  private final Double                     _minimum_;
  // field JavaFieldTemplateModel PropertiesObject properties field.typeSchema.name=PropertiesObject
  private final PropertiesObject           _properties_;

  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public SchemaEntity(AbstractBuilder<?,?> builder)
  {
    super(builder);
    
    _maxItems_ = builder.getMaxItems();
    _format_ = builder.getFormat();
    _xCanonCardinality_ = builder.getXCanonCardinality();
    _xCanonIdentifier_ = builder.getXCanonIdentifier();
    _type_ = builder.getType();
    _xCanonFacade_ = builder.getXCanonFacade();
    _enum_ = ImmutableList.copyOf(builder.getEnum());
    _required_ = ImmutableList.copyOf(builder.getRequired());
    _minItems_ = builder.getMinItems();
    _xCanonAttributes_ = builder.getXCanonAttributes();
    _maximum_ = builder.getMaximum();
    _minimum_ = builder.getMinimum();
    _properties_ = builder.getProperties();
    unknownKeys_ = ImmutableSet.of();
  }
   
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public SchemaEntity(JsonObject jsonObject, ModelRegistry modelRegistry)
  {
    super(jsonObject);
  
    Set<String> keySet = new HashSet<>(super.getCanonUnknownKeys());
    
    if(keySet.remove("maxItems"))
    {
      JsonDomNode  node = jsonObject.get("maxItems");
//HERE0 node
      if(node instanceof IIntegerProvider)
      {
        _maxItems_ = ((IIntegerProvider)node).asInteger();
      }
      else 
      {
        throw new IllegalArgumentException("maxItems must be an instance of IIntegerProvider not " + node.getClass().getName());
      }
    }
    else
    {
      _maxItems_ = null;
    }
    if(keySet.remove("format"))
    {
      JsonDomNode  node = jsonObject.get("format");
//HERE0 node
      if(node instanceof IStringProvider)
      {
        _format_ = ((IStringProvider)node).asString();
      }
      else 
      {
        throw new IllegalArgumentException("format must be an instance of IStringProvider not " + node.getClass().getName());
      }
    }
    else
    {
      _format_ = null;
    }
    if(keySet.remove("x-canon-cardinality"))
    {
      JsonDomNode  node = jsonObject.get("x-canon-cardinality");
//HERE0 node
      if(node instanceof IStringProvider)
      {
        _xCanonCardinality_ = CanonCardinality.valueOf(((IStringProvider)node).asString());
      }
      else 
      {
        throw new IllegalArgumentException("x-canon-cardinality must be an instance of IStringProvider not " + node.getClass().getName());
      }
    }
    else
    {
      _xCanonCardinality_ = null;
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
    if(keySet.remove("type"))
    {
      JsonDomNode  node = jsonObject.get("type");
//HERE0 node
      if(node instanceof IStringProvider)
      {
        _type_ = ((IStringProvider)node).asString();
      }
      else 
      {
        throw new IllegalArgumentException("type must be an instance of IStringProvider not " + node.getClass().getName());
      }
    }
    else
    {
      _type_ = null;
    }
    if(keySet.remove("x-canon-facade"))
    {
      JsonDomNode  node = jsonObject.get("x-canon-facade");
//HERE0 node
      if(node instanceof IBooleanProvider)
      {
        _xCanonFacade_ = ((IBooleanProvider)node).asBoolean();
      }
      else 
      {
        throw new IllegalArgumentException("x-canon-facade must be an instance of IBooleanProvider not " + node.getClass().getName());
      }
    }
    else
    {
      _xCanonFacade_ = null;
    }
    if(keySet.remove("enum"))
    {
      JsonDomNode  node = jsonObject.get("enum");
//HERE0 node
      if(node instanceof JsonArray)
      {
      List<String> itemList = new LinkedList<>();
      for(JsonDomNode item : (JsonArray)node)
      {
        String itemValue = null;
//HERE0 item
        if(item instanceof IStringProvider)
        {
          itemValue = ((IStringProvider)item).asString();
        }
        else 
        {
          throw new IllegalArgumentException("enum items must be an instance of IStringProvider not " + item.getClass().getName());
        }
        itemList.add(itemValue);
      }
      _enum_ = ImmutableList.copyOf(itemList);
      }
      else 
      {
        throw new IllegalArgumentException("enum must be a JsonArray node not " + node.getClass().getName());
      }
    }
    else
    {
      _enum_ = null;
    }
    if(keySet.remove("required"))
    {
      JsonDomNode  node = jsonObject.get("required");
//HERE0 node
      if(node instanceof JsonArray)
      {
      List<String> itemList = new LinkedList<>();
      for(JsonDomNode item : (JsonArray)node)
      {
        String itemValue = null;
//HERE0 item
        if(item instanceof IStringProvider)
        {
          itemValue = ((IStringProvider)item).asString();
        }
        else 
        {
          throw new IllegalArgumentException("required items must be an instance of IStringProvider not " + item.getClass().getName());
        }
        itemList.add(itemValue);
      }
      _required_ = ImmutableList.copyOf(itemList);
      }
      else 
      {
        throw new IllegalArgumentException("required must be a JsonArray node not " + node.getClass().getName());
      }
    }
    else
    {
      _required_ = null;
    }
    if(keySet.remove("minItems"))
    {
      JsonDomNode  node = jsonObject.get("minItems");
//HERE0 node
      if(node instanceof IIntegerProvider)
      {
        _minItems_ = ((IIntegerProvider)node).asInteger();
      }
      else 
      {
        throw new IllegalArgumentException("minItems must be an instance of IIntegerProvider not " + node.getClass().getName());
      }
    }
    else
    {
      _minItems_ = null;
    }
    if(keySet.remove("x-canon-attributes"))
    {
      JsonDomNode  node = jsonObject.get("x-canon-attributes");
//HERE0 node
      if(node instanceof JsonObject)
      {
        _xCanonAttributes_ = modelRegistry.newInstance((JsonObject)node, CanonAttributes.TYPE_ID, CanonAttributes.class);
      }
      else 
      {
        throw new IllegalArgumentException("x-canon-attributes must be an Object node not " + node.getClass().getName());
      }
    }
    else
    {
      _xCanonAttributes_ = null;
    }
    if(keySet.remove("maximum"))
    {
      JsonDomNode  node = jsonObject.get("maximum");
//HERE0 node
      if(node instanceof IDoubleProvider)
      {
        _maximum_ = ((IDoubleProvider)node).asDouble();
      }
      else 
      {
        throw new IllegalArgumentException("maximum must be an instance of IDoubleProvider not " + node.getClass().getName());
      }
    }
    else
    {
      _maximum_ = null;
    }
    if(keySet.remove("minimum"))
    {
      JsonDomNode  node = jsonObject.get("minimum");
//HERE0 node
      if(node instanceof IDoubleProvider)
      {
        _minimum_ = ((IDoubleProvider)node).asDouble();
      }
      else 
      {
        throw new IllegalArgumentException("minimum must be an instance of IDoubleProvider not " + node.getClass().getName());
      }
    }
    else
    {
      _minimum_ = null;
    }
    if(keySet.remove("properties"))
    {
      JsonDomNode  node = jsonObject.get("properties");
//HERE0 node
      if(node instanceof JsonObject)
      {
        _properties_ = modelRegistry.newInstance((JsonObject)node, PropertiesObject.TYPE_ID, PropertiesObject.class);
      }
      else 
      {
        throw new IllegalArgumentException("properties must be an Object node not " + node.getClass().getName());
      }
    }
    else
    {
      _properties_ = null;
    }

    unknownKeys_ = ImmutableSet.copyOf(keySet);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public SchemaEntity(Schema other)
  {
    super(other);
    
    _maxItems_ = other.getMaxItems();
    _format_ = other.getFormat();
    _xCanonCardinality_ = other.getXCanonCardinality();
    _xCanonIdentifier_ = other.getXCanonIdentifier();
    _type_ = other.getType();
    _xCanonFacade_ = other.getXCanonFacade();
    _enum_ = other.getEnum();
    _required_ = other.getRequired();
    _minItems_ = other.getMinItems();
    _xCanonAttributes_ = other.getXCanonAttributes();
    _maximum_ = other.getMaximum();
    _minimum_ = other.getMinimum();
    _properties_ = other.getProperties();

    unknownKeys_ = other.getCanonUnknownKeys();
  }
  
  @Override
  public ImmutableSet<String> getCanonUnknownKeys()
  {
    return unknownKeys_;
  }
  
  /**
   * Return the value of the maxItems attribute.
   *
   * @return the value of the maxItems attribute.
   */
  public Integer getMaxItems()
  {
    return _maxItems_;
  }    
  
  /**
   * Return the value of the format attribute.
   *
   * @return the value of the format attribute.
   */
  public String getFormat()
  {
    return _format_;
  }    
  
  /**
   * Return the value of the x-canon-cardinality attribute.
   *
   * @return the value of the x-canon-cardinality attribute.
   */
  public CanonCardinality getXCanonCardinality()
  {
    return _xCanonCardinality_;
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
   * Return the value of the type attribute.
   *
   * @return the value of the type attribute.
   */
  public String getType()
  {
    return _type_;
  }    
  
  /**
   * Return the value of the x-canon-facade attribute.
   *
   * @return the value of the x-canon-facade attribute.
   */
  public Boolean getXCanonFacade()
  {
    return _xCanonFacade_;
  }    
  
  /**
   * Return the value of the enum attribute.
   *
   * @return the value of the enum attribute.
   */
  public List<String> getEnum()
  {
    return _enum_;
  }    
  
  /**
   * Return the value of the required attribute.
   *
   * @return the value of the required attribute.
   */
  public List<String> getRequired()
  {
    return _required_;
  }    
  
  /**
   * Return the value of the minItems attribute.
   *
   * @return the value of the minItems attribute.
   */
  public Integer getMinItems()
  {
    return _minItems_;
  }    
  
  /**
   * Return the value of the x-canon-attributes attribute.
   *
   * @return the value of the x-canon-attributes attribute.
   */
  public CanonAttributes getXCanonAttributes()
  {
    return _xCanonAttributes_;
  }    
  
  /**
   * Return the value of the maximum attribute.
   *
   * @return the value of the maximum attribute.
   */
  public Double getMaximum()
  {
    return _maximum_;
  }    
  
  /**
   * Return the value of the minimum attribute.
   *
   * @return the value of the minimum attribute.
   */
  public Double getMinimum()
  {
    return _minimum_;
  }    
  
  /**
   * Return the value of the properties attribute.
   *
   * @return the value of the properties attribute.
   */
  public PropertiesObject getProperties()
  {
    return _properties_;
  }    

  @Override
  public boolean equals(Object obj)
  {
    if(obj instanceof SchemaEntity)
      return toString().equals(((SchemaEntity)obj).toString());
    
    return false;
  }

  @Override
  public int hashCode()
  {
    return toString().hashCode();
  }

  
  /**
   * Factory class for Schema.
   */
  public static class Factory extends ObjectEntity.Factory<SchemaEntity>
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
    public Schema newInstance(JsonObject jsonObject, ModelRegistry modelRegistry)
    {
      return new Schema(jsonObject, modelRegistry);
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
   * Abstract builder for Schema. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <T> The concrete type of the builder, used for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends SchemaEntity>
    extends ObjectEntity.AbstractBuilder<T,B>
  {
    protected Integer                    _maxItems_;
    protected String                     _format_;
    protected CanonCardinality           _xCanonCardinality_;
    protected String                     _xCanonIdentifier_;
    protected String                     _type_;
    protected Boolean                    _xCanonFacade_;
    protected List<String>               _enum_ = new LinkedList<String>();
    protected List<String>               _required_ = new LinkedList<String>();
    protected Integer                    _minItems_;
    protected CanonAttributes            _xCanonAttributes_;
    protected Double                     _maximum_;
    protected Double                     _minimum_;
    protected PropertiesObject           _properties_;
  
    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);
      
      _maxItems_ = initial.getMaxItems();
      _format_ = initial.getFormat();
      _xCanonCardinality_ = initial.getXCanonCardinality();
      _xCanonIdentifier_ = initial.getXCanonIdentifier();
      _type_ = initial.getType();
      _xCanonFacade_ = initial.getXCanonFacade();
      _enum_ = ImmutableList.copyOf(initial.getEnum());
      _required_ = ImmutableList.copyOf(initial.getRequired());
      _minItems_ = initial.getMinItems();
      _xCanonAttributes_ = initial.getXCanonAttributes();
      _maximum_ = initial.getMaximum();
      _minimum_ = initial.getMinimum();
      _properties_ = initial.getProperties();
    }
    
    @Override
    public T withValues(JsonObject jsonObject, ModelRegistry modelRegistry)
    {
      if(jsonObject.containsKey("maxItems"))
      {
        JsonDomNode  node = jsonObject.get("maxItems");
//HERE0 node
        if(node instanceof IIntegerProvider)
        {
          _maxItems_ = ((IIntegerProvider)node).asInteger();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("maxItems must be an instance of IIntegerProvider not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("format"))
      {
        JsonDomNode  node = jsonObject.get("format");
//HERE0 node
        if(node instanceof IStringProvider)
        {
          _format_ = ((IStringProvider)node).asString();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("format must be an instance of IStringProvider not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("xCanonCardinality"))
      {
        JsonDomNode  node = jsonObject.get("xCanonCardinality");
//HERE0 node
        if(node instanceof IStringProvider)
        {
          _xCanonCardinality_ = CanonCardinality.valueOf(((IStringProvider)node).asString());
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("x-canon-cardinality must be an instance of IStringProvider not " + node.getClass().getName());
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
      if(jsonObject.containsKey("type"))
      {
        JsonDomNode  node = jsonObject.get("type");
//HERE0 node
        if(node instanceof IStringProvider)
        {
          _type_ = ((IStringProvider)node).asString();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("type must be an instance of IStringProvider not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("xCanonFacade"))
      {
        JsonDomNode  node = jsonObject.get("xCanonFacade");
//HERE0 node
        if(node instanceof IBooleanProvider)
        {
          _xCanonFacade_ = ((IBooleanProvider)node).asBoolean();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("x-canon-facade must be an instance of IBooleanProvider not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("enum"))
      {
        JsonDomNode  node = jsonObject.get("enum");
//HERE0 node
        if(node instanceof JsonArray)
        {
        List<String> itemList = new LinkedList<>();
        for(JsonDomNode item : (JsonArray)node)
        {
          String itemValue = null;
//HERE0 item
          if(item instanceof IStringProvider)
          {
            itemValue = ((IStringProvider)item).asString();
          }
          else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
          {
            throw new IllegalArgumentException("enum items must be an instance of IStringProvider not " + item.getClass().getName());
          }
          itemList.add(itemValue);
        }
        _enum_ = ImmutableList.copyOf(itemList);
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("enum must be a JsonArray node not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("required"))
      {
        JsonDomNode  node = jsonObject.get("required");
//HERE0 node
        if(node instanceof JsonArray)
        {
        List<String> itemList = new LinkedList<>();
        for(JsonDomNode item : (JsonArray)node)
        {
          String itemValue = null;
//HERE0 item
          if(item instanceof IStringProvider)
          {
            itemValue = ((IStringProvider)item).asString();
          }
          else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
          {
            throw new IllegalArgumentException("required items must be an instance of IStringProvider not " + item.getClass().getName());
          }
          itemList.add(itemValue);
        }
        _required_ = ImmutableList.copyOf(itemList);
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("required must be a JsonArray node not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("minItems"))
      {
        JsonDomNode  node = jsonObject.get("minItems");
//HERE0 node
        if(node instanceof IIntegerProvider)
        {
          _minItems_ = ((IIntegerProvider)node).asInteger();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("minItems must be an instance of IIntegerProvider not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("xCanonAttributes"))
      {
        JsonDomNode  node = jsonObject.get("xCanonAttributes");
//HERE0 node
        if(node instanceof JsonObject)
        {
          _xCanonAttributes_ = modelRegistry.newInstance((JsonObject)node, CanonAttributes.TYPE_ID, CanonAttributes.class);
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("x-canon-attributes must be an Object node not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("maximum"))
      {
        JsonDomNode  node = jsonObject.get("maximum");
//HERE0 node
        if(node instanceof IDoubleProvider)
        {
          _maximum_ = ((IDoubleProvider)node).asDouble();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("maximum must be an instance of IDoubleProvider not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("minimum"))
      {
        JsonDomNode  node = jsonObject.get("minimum");
//HERE0 node
        if(node instanceof IDoubleProvider)
        {
          _minimum_ = ((IDoubleProvider)node).asDouble();
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("minimum must be an instance of IDoubleProvider not " + node.getClass().getName());
        }
      }
      if(jsonObject.containsKey("properties"))
      {
        JsonDomNode  node = jsonObject.get("properties");
//HERE0 node
        if(node instanceof JsonObject)
        {
          _properties_ = modelRegistry.newInstance((JsonObject)node, PropertiesObject.TYPE_ID, PropertiesObject.class);
        }
        else if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
        {
          throw new IllegalArgumentException("properties must be an Object node not " + node.getClass().getName());
        }
      }
      return super.withValues(jsonObject, modelRegistry);
    }
    
    public void populateAllFields(List<Object> result)
    {
      result.add(_maxItems_);
      result.add(_format_);
      result.add(_xCanonCardinality_);
      result.add(_xCanonIdentifier_);
      result.add(_type_);
      result.add(_xCanonFacade_);
      result.add(_enum_);
      result.add(_required_);
      result.add(_minItems_);
      result.add(_xCanonAttributes_);
      result.add(_maximum_);
      result.add(_minimum_);
      result.add(_properties_);
    }
    /**
     * Return the value of the maxItems attribute.
     *
     * @return the value of the maxItems attribute.
     */
    public Integer getMaxItems()
    {
      return _maxItems_;
    }

    /**
     * Set the value of the maxItems attribute.
     *
     * @param value The value to be set. 
     *
     * @return This (fluent method).
     */
     // base type Integer
     // base name maxItems
    public T withMaxItems(Integer value)
    {
      _maxItems_ = value;
      return self();
    }
    
    /**
     * Return the value of the format attribute.
     *
     * @return the value of the format attribute.
     */
    public String getFormat()
    {
      return _format_;
    }

    /**
     * Set the value of the format attribute.
     *
     * @param value The value to be set. 
     *
     * @return This (fluent method).
     */
     // base type String
     // base name format
    public T withFormat(String value)
    {
      _format_ = value;
      return self();
    }
    
    /**
     * Return the value of the x-canon-cardinality attribute.
     *
     * @return the value of the x-canon-cardinality attribute.
     */
    public CanonCardinality getXCanonCardinality()
    {
      return _xCanonCardinality_;
    }

    /**
     * Set the value of the x-canon-cardinality attribute.
     *
     * @param value The value to be set. 
     *
     * @return This (fluent method).
     */
     // base type CanonCardinality
     // base name CanonCardinality
    public T withXCanonCardinality(CanonCardinality value)
    {
      _xCanonCardinality_ = value;
      return self();
    }
    
    /**
     * Set the value of the x-canon-cardinality attribute.
     *
     * @param value The value to be set. 
     *
     * @return This (fluent method).
     */
     //typedef
    public T withXCanonCardinality(String value)
    {
      _xCanonCardinality_ = CanonCardinality.valueOf(value);
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
     * Return the value of the type attribute.
     *
     * @return the value of the type attribute.
     */
    public String getType()
    {
      return _type_;
    }

    /**
     * Set the value of the type attribute.
     *
     * @param value The value to be set. 
     *
     * @return This (fluent method).
     */
     // base type String
     // base name type
    public T withType(String value)
    {
      _type_ = value;
      return self();
    }
    
    /**
     * Return the value of the x-canon-facade attribute.
     *
     * @return the value of the x-canon-facade attribute.
     */
    public Boolean getXCanonFacade()
    {
      return _xCanonFacade_;
    }

    /**
     * Set the value of the x-canon-facade attribute.
     *
     * @param value The value to be set. 
     *
     * @return This (fluent method).
     */
     // base type Boolean
     // base name x-canon-facade
    public T withXCanonFacade(Boolean value)
    {
      _xCanonFacade_ = value;
      return self();
    }
    
    /**
     * Return the value of the enum attribute.
     *
     * @return the value of the enum attribute.
     */
    public List<String> getEnum()
    {
      return _enum_;
    }

    /**
     * Set the value of the enum attribute.
     *
     * @param value The value to be set. 
     *
     * @return This (fluent method).
     */
     // base type List<String>
     // base name enum
    public T withEnum(List<String> value)
    {
      _enum_ = ImmutableList.copyOf(value);
      return self();
    }
    
    /**
     * Return the value of the required attribute.
     *
     * @return the value of the required attribute.
     */
    public List<String> getRequired()
    {
      return _required_;
    }

    /**
     * Set the value of the required attribute.
     *
     * @param value The value to be set. 
     *
     * @return This (fluent method).
     */
     // base type List<String>
     // base name required
    public T withRequired(List<String> value)
    {
      _required_ = ImmutableList.copyOf(value);
      return self();
    }
    
    /**
     * Return the value of the minItems attribute.
     *
     * @return the value of the minItems attribute.
     */
    public Integer getMinItems()
    {
      return _minItems_;
    }

    /**
     * Set the value of the minItems attribute.
     *
     * @param value The value to be set. 
     *
     * @return This (fluent method).
     */
     // base type Integer
     // base name minItems
    public T withMinItems(Integer value)
    {
      _minItems_ = value;
      return self();
    }
    
    /**
     * Return the value of the x-canon-attributes attribute.
     *
     * @return the value of the x-canon-attributes attribute.
     */
    public CanonAttributes getXCanonAttributes()
    {
      return _xCanonAttributes_;
    }

    /**
     * Set the value of the x-canon-attributes attribute.
     *
     * @param value The value to be set. 
     *
     * @return This (fluent method).
     */
     // base type CanonAttributes
     // base name CanonAttributes
    public T withXCanonAttributes(CanonAttributes value)
    {
      _xCanonAttributes_ = value;
      return self();
    }
    
    /**
     * Return the value of the maximum attribute.
     *
     * @return the value of the maximum attribute.
     */
    public Double getMaximum()
    {
      return _maximum_;
    }

    /**
     * Set the value of the maximum attribute.
     *
     * @param value The value to be set. 
     *
     * @return This (fluent method).
     */
     // base type Double
     // base name maximum
    public T withMaximum(Double value)
    {
      _maximum_ = value;
      return self();
    }
    
    /**
     * Return the value of the minimum attribute.
     *
     * @return the value of the minimum attribute.
     */
    public Double getMinimum()
    {
      return _minimum_;
    }

    /**
     * Set the value of the minimum attribute.
     *
     * @param value The value to be set. 
     *
     * @return This (fluent method).
     */
     // base type Double
     // base name minimum
    public T withMinimum(Double value)
    {
      _minimum_ = value;
      return self();
    }
    
    /**
     * Return the value of the properties attribute.
     *
     * @return the value of the properties attribute.
     */
    public PropertiesObject getProperties()
    {
      return _properties_;
    }

    /**
     * Set the value of the properties attribute.
     *
     * @param value The value to be set. 
     *
     * @return This (fluent method).
     */
     // base type PropertiesObject
     // base name PropertiesObject
    public T withProperties(PropertiesObject value)
    {
      _properties_ = value;
      return self();
    }
    
    @Override 
    public JsonObject getJsonObject()
    {
      JsonObject.Builder builder = new JsonObject.Builder();
      
      builder.addIfNotNull(JSON_TYPE, SchemaEntity.TYPE_ID);
      builder.addIfNotNull(JSON_VERSION, SchemaEntity.TYPE_VERSION);

      populateJson(builder);
  
      return builder.build();
    }
    
    @Override 
    public void populateJson(JsonObject.Builder builder)
    {
      super.populateJson(builder);
  
      if(getMaxItems() != null)
      {
          builder.addIfNotNull("maxItems", getMaxItems());
      }
  
      if(getFormat() != null)
      {
          builder.addIfNotNull("format", getFormat());
      }
  
      if(getXCanonCardinality() != null)
      {
          builder.addIfNotNull("x-canon-cardinality", getXCanonCardinality().getValue());
      }
  
      if(getXCanonIdentifier() != null)
      {
          builder.addIfNotNull("x-canon-identifier", getXCanonIdentifier());
      }
  
      if(getType() != null)
      {
          builder.addIfNotNull("type", getType());
      }
  
      if(getXCanonFacade() != null)
      {
          builder.addIfNotNull("x-canon-facade", getXCanonFacade());
      }
  
      if(getEnum() != null)
      {
          JsonArray.Builder arrayBuilder = new JsonArray.Builder();
          for(String item : getEnum())
          {
            arrayBuilder.with(item);
          }
          builder.with("enum", arrayBuilder.build());
      }
  
      if(getRequired() != null)
      {
          JsonArray.Builder arrayBuilder = new JsonArray.Builder();
          for(String item : getRequired())
          {
            arrayBuilder.with(item);
          }
          builder.with("required", arrayBuilder.build());
      }
  
      if(getMinItems() != null)
      {
          builder.addIfNotNull("minItems", getMinItems());
      }
  
      if(getXCanonAttributes() != null)
      {
          builder.addIfNotNull("x-canon-attributes", getXCanonAttributes().getJsonObject());
      }
  
      if(getMaximum() != null)
      {
          builder.addIfNotNull("maximum", getMaximum());
      }
  
      if(getMinimum() != null)
      {
          builder.addIfNotNull("minimum", getMinimum());
      }
  
      if(getProperties() != null)
      {
          builder.addIfNotNull("properties", getProperties().getJsonObject());
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
  
  // entity.name Schema
  // entity.class class com.symphony.oss.canon2.generator.java.JavaObjectSchemaTemplateModel
}

/*----------------------------------------------------------------------------------------------------
 * End of template template/Object/_Entity.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */