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
  STRING("string"),
  /** NUMBER */
  NUMBER("number"),
  /** ARRAY */
  ARRAY("array"),
  /** OBJECT */
  OBJECT("object"),
  /** BOOLEAN */
  BOOLEAN("boolean"),
  /** INTEGER */
  INTEGER("integer"),
  /** ONE_OF */
  ONE_OF("oneOf"),
  /** AALL_OF */
  ALL_OF("allOf"),
  /** ANY_OF */
  ANY_OF("anyOf")
  ;
  
  private final String value_;
  
  private SchemaTemplateModelType(String value)
  {
    value_ = value;
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
