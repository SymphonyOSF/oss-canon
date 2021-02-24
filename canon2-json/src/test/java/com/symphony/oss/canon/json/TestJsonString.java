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

import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;

import com.symphony.oss.canon.json.model.JsonString;


@SuppressWarnings("javadoc")
public class TestJsonString
{
  @Test
  public void testJsonString()
  {
    List<String> stringValues = new LinkedList<String>();

    stringValues.add(String.valueOf(Integer.MIN_VALUE) + "Hello");
    stringValues.add("--1234");
    stringValues.add("1 1");
    stringValues.add("Hello World");
    stringValues.add("six");
    stringValues.add("1.2.3");
    
    for(String input : stringValues)
    {
      testString(input, false, false);
    }
    
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
      testString(input, true, true);
    }
    
    List<String> longValues = new LinkedList<String>();

    longValues.add(String.valueOf(Long.MIN_VALUE));
    longValues.add(String.valueOf(2L * Integer.MIN_VALUE));
    longValues.add(String.valueOf(2L * Integer.MAX_VALUE));
    longValues.add(String.valueOf(Long.MAX_VALUE));

    for(String input : longValues)
    {
      testString(input, true, true);
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
      testString(input, true, true);
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
      testString(input, false, true);
    }
    
    List<String> doubleValues = new LinkedList<String>();

    doubleValues.add(String.valueOf(Double.MIN_VALUE));
    doubleValues.add("314158987654E2");
    doubleValues.add("314158987654E-9");
    doubleValues.add(String.valueOf(Double.MAX_VALUE));

    for(String input : doubleValues)
    {
      testString(input, false, true);
    }
    
    List<String> bigDecimalValues = new LinkedList<String>();

    String     extraPrecision = "1234567890987654321";

    bigDecimalValues.add("-" + extraPrecision + "." + extraPrecision);
    bigDecimalValues.add(extraPrecision + "." + extraPrecision);

    for(String input : bigDecimalValues)
    {
      testString(input, false, true);
    }
    
    String[] nonBase64Values = new String[] {
        "Hello World",
        "Hi!"
        };
    
    for(String input : nonBase64Values)
    {
      testBase64String(input, false);
    }
    
    String[] base64Values = new String[] {
        "",
        Base64.encodeBase64String("".getBytes()),
        Base64.encodeBase64String("Hello World".getBytes()),
        Base64.encodeBase64String("Hello World this is a slightly longer string".getBytes())
        };
    
    for(String input : base64Values)
    {
      testBase64String(input, true);
    }
  }

  private void testString(String input, boolean expectBigInteger, boolean expectBigDecimal)
  {
    JsonString node = new JsonString.Builder()
        .withValue(input)
        .build();
    
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
    
    if(expectBigDecimal)
    {
      if(!(node.isBigDecimal()))
      {
        Assert.fail("Expected a BigDecimal value for \"" + input + "\"");
      }
    }
    else
    {
      if(node.isBigDecimal())
      {
        Assert.fail("Expected a non-BigDecimal value for \"" + input + "\"");
      }
    }}

  private void testBase64String(String input, boolean expectBase64)
  {
    JsonString node = new JsonString.Builder()
        .withValue(input)
        .build();
    
    if(expectBase64)
    {
      if(!(node.isImmutableByteArray()))
      {
        Assert.fail("Expected an ImmutableByte value for \"" + input + "\"");
      }
    }
    else
    {
      if(node.isImmutableByteArray())
      {
        Assert.fail("Expected a non-ImmutableByte value for \"" + input + "\"");
      }
    }
  }
}