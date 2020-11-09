/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon2.core;

import com.symphony.oss.canon2.model.SchemaType;
import com.symphony.oss.commons.fault.CodingFault;

/**
 * The type of a SchemTemplateModel.
 * 
 * @author Bruce Skingle
 *
 */
public enum SchemaTemplateModelType
{
  /** STRING */
  STRING("string", false, false, true),
  /** NUMBER */
  NUMBER("number", false, false, true),
  /** ARRAY */
  ARRAY("array", false, false, false),
  /** OBJECT */
  OBJECT("object", false, true, false),
  /** BOOLEAN */
  BOOLEAN("boolean", false, false, true),
  /** INTEGER */
  INTEGER("integer", false, false, true),
  /** ONE_OF */
  ONE_OF("oneOf", true, true, false),
  /** AALL_OF */
  ALL_OF("allOf", true, true, false),
  /** ANY_OF */
  ANY_OF("anyOf", true, true, false)
  ;
  
  private final String value_;
  private final boolean composite_;
  private final boolean object_;
  private final boolean primitive_;
  
  private SchemaTemplateModelType(String value, boolean composite, boolean object, boolean primitive)
  {
    value_ = value;
    composite_ = composite;
    object_ = object;
    primitive_ = primitive;
  }
  
  /**
   * Return the serialized value of this enum constant.
   *
   * @return the serialized value of this enum constant.
   */
  public String getValue()
  {
    return value_;
  }
  
  /**
   * Return true if this is a composite type, allOf, anyOf, oneOf.
   * 
   * @return true if this is a composite type, allOf, anyOf, oneOf.
   */
  public boolean getIsComposite()
  {
    return composite_;
  }

  /**
   * Return true if this schema type generates an object, i.e. is object or a composite type.
   * 
   * @return true if this schema type generates an object, i.e. is object or a composite type.
   */
  public boolean getIsObject()
  {
    return object_;
  }

  /**
   * Return true if this schema type is a primitive.
   * 
   * @return true if this schema type is a primitive.
   */
  public boolean getIsPrimitive()
  {
    return primitive_;
  }

  /**
   * Return the equivalent value for the given SchemaType value.
   * 
   * @param value The SchemaType value for which the equivalent value is required.
   * 
   * @return The equivalent value for the given SchemaType value.
   */
  public static final SchemaTemplateModelType valueOf(SchemaType value)
  {
    switch(value)
    {
      case ARRAY:
        return ARRAY;
            
      case BOOLEAN:
        return BOOLEAN;
        
      case INTEGER:
        return INTEGER;
        
      case NUMBER:
        return NUMBER;
        
      case OBJECT:
        return OBJECT;
        
      case STRING:
        return STRING;
        
      default:
        throw new CodingFault("Unknown SchemaType " + value);
    }
  }
}
