/*
 *
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
 */

package com.symphony.oss.canon.json.model;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * A JSON value.
 * 
 * @author Bruce Skingle
 *
 */
public abstract class JsonValue extends JsonDomNode
{
  JsonValue(AbstractBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Builder for this and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <T> The concrete type of the builder for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends JsonValue> extends JsonDomNode.AbstractBuilder<T, B>
  {
    AbstractBuilder(Class<T> type)
    {
      super(type);
    }
  }
  
  public static JsonValue valueOf(String stringValue)
  {

    switch(stringValue.toLowerCase())
    {
      case "true":
        return new JsonBoolean.Builder().withValue(true).build();
        
      case "false":
        return new JsonBoolean.Builder().withValue(false).build();
        
      case "null":
        return JsonNull.INSTANCE;
    }
    
    try
    {
      return new JsonInteger.Builder().withValue(Integer.parseInt(stringValue)).build();
    }
    catch(NumberFormatException e)
    {
      // Not an integer
    }
    
    try
    {
      return new JsonLong.Builder().withValue(Long.parseLong(stringValue)).build();
    }
    catch(NumberFormatException e)
    {
      // Not a Long
    }

    try
    {
      return new JsonBigInteger.Builder().withValue(new BigInteger(stringValue)).build();
    }
    catch(NumberFormatException e)
    {
      // Not a BigInteger
    }

    try
    {
      BigDecimal  bigDecimalValue   = new BigDecimal(stringValue);
      String      bigDecimalString  = bigDecimalValue.toString();
      Float       floatValue        = Float.parseFloat(stringValue);
      String      floatString       = new BigDecimal(floatValue.toString()).toString();
      
      BigDecimal bd = new BigDecimal(floatValue.toString());
      
      if(floatString.equals(bigDecimalString))
      {
        return new JsonFloat.Builder()
            //.withContext(context)
            .withValue(floatValue)
            .build();
      }
      
      Double      doubleValue       = Double.parseDouble(stringValue);
      String      doubleString      = new BigDecimal(doubleValue.toString()).toString();
      
      double debug = -765547723.033303;
      BigDecimal dd = new BigDecimal(doubleValue);
      double ddd = bigDecimalValue.doubleValue();
      float fff = bigDecimalValue.floatValue();
      
      boolean dde = ddd == doubleValue;
      boolean ffe = fff == floatValue;
      
      if(doubleString.equals(bigDecimalString))
      {
        return new JsonDouble.Builder()
            //.withContext(context)
            .withValue(doubleValue)
            .build();
      }
      else
      {
        return new JsonBigDecimal.Builder()
            //.withContext(context)
            .withValue(bigDecimalValue)
            .build();
      }
    }
    catch(NumberFormatException e)
    {
      // Not a Double
    }
    
    return new JsonString.Builder().withValue(stringValue).build();
  }
}
