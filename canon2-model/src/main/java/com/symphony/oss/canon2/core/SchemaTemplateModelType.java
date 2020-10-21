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
  STRING("string", false, false),
  /** NUMBER */
  NUMBER("number", false, false),
  /** ARRAY */
  ARRAY("array", false, false),
  /** OBJECT */
  OBJECT("object", true, false),
  /** BOOLEAN */
  BOOLEAN("boolean", false, false),
  /** INTEGER */
  INTEGER("integer", false, false),
  /** ONE_OF */
  ONE_OF("oneOf", true, true),
  /** AALL_OF */
  ALL_OF("allOf", true, true),
  /** ANY_OF */
  ANY_OF("anyOf", true, true)
  ;
  
  private final String value_;
  private final boolean composite_;
  private final boolean object_;
  
  private SchemaTemplateModelType(String value, boolean composite, boolean object)
  {
    value_ = value;
    composite_ = composite;
    object_ = object;
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
