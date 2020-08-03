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

package com.symphony.oss.canon.test.typeCheck;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.util.Iterator;

import org.junit.Test;

import com.symphony.oss.canon.runtime.ModelRegistry;

public class TestObjectWithInts
{
  ModelRegistry registry_ = new ModelRegistry()
      .withFactories(TypeCheckModel.FACTORIES)
      ;
  
  @Test
  public void testSimpleObject()
  {
    ISimpleObject simpleObject = new SimpleObject.Builder()
        .withName("SquareOfTwo")
        .withValue(4L)
        .build();
    
    assertEquals("{\n" + 
        "  \"_type\":\"com.symphony.oss.canon.test.typeCheck.SimpleObject\",\n" + 
        "  \"_version\":\"1.0\",\n" + 
        "  \"name\":\"SquareOfTwo\",\n" + 
        "  \"value\":4\n" + 
        "}\n", simpleObject.toString());
    
    ISimpleObject parsedSimpleObject = new SimpleObject(
        ModelRegistry.parseOneJsonObject(new StringReader("{\n" + 
            "  \"_type\":\"com.symphony.oss.canon.test.typeCheck.SimpleObject\",\n" + 
            "  \"_version\":\"1.0\",\n" + 
            "  \"name\":\"SquareOfTwo\",\n" + 
            "  \"value\":4\n" + 
            "}\n")),
        registry_);

    assertEquals(parsedSimpleObject.getName(), simpleObject.getName());
    assertEquals(parsedSimpleObject.getValue(), simpleObject.getValue());
    assertEquals(parsedSimpleObject, simpleObject);
  }
  
  @Test
  public void testNestedObject()
  {
    ISimpleObject simpleObject = new SimpleObject.Builder()
        .withName("SquareOfTwo")
        .withValue(4L)
        .build();
    
    IAllTheTypes nestedObject = new AllTheTypes.Builder()
        .withObjectField(simpleObject)
        .withIntField(3L)
        .withInt64Field(5L)
        .withInt32Field(7)
        .build();
    
    assertEquals("{\n" + 
        "  \"_type\":\"com.symphony.oss.canon.test.typeCheck.AllTheTypes\",\n" + 
        "  \"_version\":\"1.0\",\n" + 
        "  \"int32Field\":7,\n" + 
        "  \"int64Field\":5,\n" + 
        "  \"intField\":3,\n" + 
        "  \"objectField\":{\n" + 
        "    \"_type\":\"com.symphony.oss.canon.test.typeCheck.SimpleObject\",\n" + 
        "    \"_version\":\"1.0\",\n" + 
        "    \"name\":\"SquareOfTwo\",\n" + 
        "    \"value\":4\n" + 
        "  }\n" + 
        "}\n", nestedObject.toString());
    
    IAllTheTypes parsedAllTheTypes = new AllTheTypes(
        ModelRegistry.parseOneJsonObject(new StringReader("{\n" + 
            "  \"_type\":\"com.symphony.oss.canon.test.typeCheck.AllTheTypes\",\n" + 
            "  \"_version\":\"1.0\",\n" + 
            "  \"int64Field\":5,\n" + 
            "  \"intField\":3,\n" + 
            "  \"int32Field\":7,\n" + 
            "  \"objectField\":{\n" + 
            "    \"name\":\"SquareOfTwo\",\n" + 
            "    \"value\":4\n" + 
            "  }\n" + 
            "}\n")),
        registry_);

    assertEquals(parsedAllTheTypes.getObjectField().getName(), simpleObject.getName());
    assertEquals(parsedAllTheTypes.getObjectField().getValue(), simpleObject.getValue());
    assertEquals(parsedAllTheTypes.getIntField(), nestedObject.getIntField());
  }
  
  @Test
  public void testSetAndList()
  {
    
    IObjectWithInts objectWithInts = new ObjectWithInts.Builder()
      .withIntSetField(3L)
      .withIntSetField(2L)
      .withIntSetField(7L)
      .withIntSetField(3L)
      .withIntListField(3L)
      .withIntListField(2L)
      .withIntListField(7L)
      .withIntListField(3L)
      .build();
    
    assertEquals("{\n" + 
        "  \"_type\":\"com.symphony.oss.canon.test.typeCheck.ObjectWithInts\",\n" + 
        "  \"_version\":\"1.0\",\n" + 
        "  \"intListField\":[\n" + 
        "    3,\n" + 
        "    2,\n" + 
        "    7,\n" + 
        "    3\n" + 
        "  ],\n" + 
        "  \"intSetField\":[\n" + 
        "    2,\n" + 
        "    3,\n" + 
        "    7\n" + 
        "  ]\n" + 
        "}\n", objectWithInts.toString());
  }
  

  @Test
  public void testExtraFields()
  {
    String incoming = "{\"_type\":\"com.symphony.oss.canon.test.typeCheck.ObjectWithInts\",\"_version\":\"1.0\",\"lsstName\":\"Bloggs\",\"firstName\":\"Fred\",\"intListField\":[3,2,7,3],\"intSetField\":[7,3,2,7,7,3]}";
        
    
    
    IObjectWithInts objectWithInts = new ObjectWithInts(
        ModelRegistry.parseOneJsonObject(new StringReader(incoming)),
        registry_);

    assertEquals(3, objectWithInts.getIntSetField().size());
    
    Iterator<Long> it = objectWithInts.getIntSetField().iterator();

    assertEquals(Long.valueOf(2), it.next());
    assertEquals(Long.valueOf(3), it.next());
    assertEquals(Long.valueOf(7), it.next());
    
    assertEquals("{\n" + 
        "  \"_type\":\"com.symphony.oss.canon.test.typeCheck.ObjectWithInts\",\n" + 
        "  \"_version\":\"1.0\",\n" + 
        "  \"firstName\":\"Fred\",\n" + 
        "  \"intListField\":[\n" + 
        "    3,\n" + 
        "    2,\n" + 
        "    7,\n" + 
        "    3\n" + 
        "  ],\n" + 
        "  \"intSetField\":[\n" + 
        "    7,\n" + 
        "    3,\n" + 
        "    2,\n" + 
        "    7,\n" + 
        "    7,\n" + 
        "    3\n" + 
        "  ],\n" + 
        "  \"lsstName\":\"Bloggs\"\n" + 
        "}\n" + 
        "", objectWithInts.toString());
  }
  
  @Test
  public void testExtraArrayFields()
  {
    String incoming = "{\"_type\":\"com.symphony.oss.canon.test.typeCheck.ObjectWithInts\",\"_version\":\"1.0\","
        + "\"anArray\":[7,5,3,  1,3,5,7,9],"
        + "\"firstName\":\"Fred\",\"intListField\":[3,2,7,3],\"intSetField\":[2,3,7]}";
        
    ModelRegistry registry = new ModelRegistry()
        .withFactories(TypeCheckModel.FACTORIES)
        ;
    
    IObjectWithInts objectWithInts = new ObjectWithInts(ModelRegistry.parseOneJsonObject(new StringReader(incoming)), registry);
    
    assertEquals("{\n" + 
        "  \"_type\":\"com.symphony.oss.canon.test.typeCheck.ObjectWithInts\",\n" + 
        "  \"_version\":\"1.0\",\n" + 
        "  \"anArray\":[\n" + 
        "    7,\n" + 
        "    5,\n" + 
        "    3,\n" + 
        "    1,\n" + 
        "    3,\n" + 
        "    5,\n" + 
        "    7,\n" + 
        "    9\n" + 
        "  ],\n" + 
        "  \"firstName\":\"Fred\",\n" + 
        "  \"intListField\":[\n" + 
        "    3,\n" + 
        "    2,\n" + 
        "    7,\n" + 
        "    3\n" + 
        "  ],\n" + 
        "  \"intSetField\":[\n" + 
        "    2,\n" + 
        "    3,\n" + 
        "    7\n" + 
        "  ]\n" + 
        "}\n", objectWithInts.toString());
  }
  

  @Test
  public void testExtraFields2()
  {
    String incoming = "{\n" + 
        "  \"lsstName\":\"Bloggs\",\n" + 
        "  \"firstName\":\"Fred\",\n" + 
        "  \"intListField\":[\n" + 
        "    3,\n" + 
        "    2,\n" + 
        "    7,\n" + 
        "    3\n" + 
        "  ],\n" + 
        "  \"intSetField\":[\n" + 
        "    2,\n" + 
        "    3,\n" + 
        "    7\n" + 
        "  ]\n" + 
        "}\n";
        
    ModelRegistry registry = new ModelRegistry()
        .withFactories(TypeCheckModel.FACTORIES)
        ;
    
    IObjectWithInts objectWithInts = new ObjectWithInts(ModelRegistry.parseOneJsonObject(new StringReader(incoming)), registry);
    
    assertEquals("{\n" + 
        "  \"firstName\":\"Fred\",\n" + 
        "  \"intListField\":[\n" + 
        "    3,\n" + 
        "    2,\n" + 
        "    7,\n" + 
        "    3\n" + 
        "  ],\n" + 
        "  \"intSetField\":[\n" + 
        "    2,\n" + 
        "    3,\n" + 
        "    7\n" + 
        "  ],\n" + 
        "  \"lsstName\":\"Bloggs\"\n" + 
        "}\n", objectWithInts.toString());
  }
}
