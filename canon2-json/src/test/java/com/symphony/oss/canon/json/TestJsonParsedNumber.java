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

package com.symphony.oss.canon.json;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.symphony.oss.canon.json.model.JsonParsedNumber;


@SuppressWarnings("javadoc")
public class TestJsonParsedNumber
{
  @Test
  public void testGetFloat()
  {
    List<String> integerValues = new LinkedList<String>();

    integerValues.add(String.valueOf(Integer.MIN_VALUE));
    integerValues.add("-1234");
    integerValues.add("-1");
    integerValues.add("0");
    integerValues.add("1");
    integerValues.add("1234");
    integerValues.add(String.valueOf(Integer.MAX_VALUE));
    
    for(String input : integerValues)
    {
      testNumber(input, true, true, true, true, true);
    }
    
    List<String> longValues = new LinkedList<String>();

    longValues.add(String.valueOf(Long.MIN_VALUE));
    longValues.add(String.valueOf(2L * Integer.MIN_VALUE));
    longValues.add(String.valueOf(2L * Integer.MAX_VALUE));
    longValues.add(String.valueOf(Long.MAX_VALUE));

    for(String input : longValues)
    {
      testNumber(input, false, true, true, true, true);
    }
    
    List<String> bigIntegerValues = new LinkedList<String>();

    BigInteger longMin = new BigInteger(String.valueOf(Long.MIN_VALUE));
    BigInteger longMax = new BigInteger(String.valueOf(Long.MAX_VALUE));

    bigIntegerValues.add(String.valueOf(longMin.multiply(BigInteger.TEN)));
    bigIntegerValues.add(String.valueOf(longMin.multiply(longMax)));
    bigIntegerValues.add(String.valueOf(longMax.multiply(longMax)));
    bigIntegerValues.add(String.valueOf(longMax.multiply(BigInteger.TEN)));

    for(String input : bigIntegerValues)
    {
      testNumber(input, false, false, true, true, true);
    }
    
    List<String> floatValues = new LinkedList<String>();

    floatValues.add(String.valueOf(Float.MIN_VALUE));
    floatValues.add("-1234.567");
    floatValues.add("-1.2");
    floatValues.add("0.1");
    floatValues.add("314158E-9");
    floatValues.add("1.2");
    floatValues.add("1234.567");
    floatValues.add("6E23");
    floatValues.add(String.valueOf(Float.MAX_VALUE));

    for(String input : floatValues)
    {
      testNumber(input, false, false, false, true, true);
    }
    
    List<String> doubleValues = new LinkedList<String>();

    doubleValues.add(String.valueOf(Double.MIN_VALUE));
    doubleValues.add("314158987654E2");
    doubleValues.add("314158987654E-9");
    doubleValues.add(String.valueOf(Double.MAX_VALUE));

    for(String input : doubleValues)
    {
      testNumber(input, false, false, false, true, true);
    }
    
    List<String> bigDecimalValues = new LinkedList<String>();

    String     extraPrecision = "1234567890987654321";

    bigDecimalValues.add("-" + extraPrecision + "." + extraPrecision);
    bigDecimalValues.add(extraPrecision + "." + extraPrecision);

    for(String input : bigDecimalValues)
    {
      testNumber(input, false, false, false, true, true);
    }
  }

  private void testNumber(String input, boolean expectInteger, boolean expectLong, boolean expectBigInteger, boolean expectFloat, boolean expectDouble)
  {
    System.out.println(input);
    
    JsonParsedNumber node = new JsonParsedNumber.Builder()
        .withValue(input)
        .build();

    if(expectInteger)
    {
      if(!(node.isInteger()))
      {
        Assert.fail("Expected a Integer value for \"" + input + "\"");
      }
    }
    else
    {
      if(node.isInteger())
      {
        Assert.fail("Expected a non-Integer value for \"" + input + "\"");
      }
    }
    
    if(expectLong)
    {
      if(!(node.isLong()))
      {
        Assert.fail("Expected a Long value for \"" + input + "\"");
      }
    }
    else
    {
      if(node.isLong())
      {
        Assert.fail("Expected a non-Long value for \"" + input + "\"");
      }
    }
    
    if(expectBigInteger)
    {
      if(!(node.isBigInteger()))
      {
        Assert.fail("Expected a BigInteger value for \"" + input + "\"");
      }
    }
    else
    {
      if(node.isBigInteger())
      {
        Assert.fail("Expected a non-BigInteger value for \"" + input + "\"");
      }
    }
    
    if(expectFloat)
    {
      if(!(node.isFloat()))
      {
        Assert.fail("Expected a Float value for \"" + input + "\"");
      }
    }
    else
    {
      if(node.isFloat())
      {
        Assert.fail("Expected a non-Float value for \"" + input + "\"");
      }
    }

    if(expectDouble)
    {
      if(!(node.isDouble()))
      {
        Assert.fail("Expected a Double value for \"" + input + "\"");
      }
    }
    else
    {
      if(node.isDouble())
      {
        Assert.fail("Expected a non-Double value for \"" + input + "\"");
      }
    }

    node.asBigDecimal();
  }
}