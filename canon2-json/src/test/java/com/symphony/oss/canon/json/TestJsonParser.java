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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.symphony.oss.canon.json.model.JsonArray;
import com.symphony.oss.canon.json.model.JsonArrayDom;
import com.symphony.oss.canon.json.model.JsonBoolean;
import com.symphony.oss.canon.json.model.JsonDom;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonInvalidDom;
import com.symphony.oss.canon.json.model.JsonNull;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon.json.model.JsonObjectDom;
import com.symphony.oss.canon.json.model.JsonParsedNumber;
import com.symphony.oss.canon.json.model.JsonString;

@SuppressWarnings("javadoc")
public class TestJsonParser
{
  static final Map<String, String>  stringEscapes = new ImmutableMap.Builder<String, String>()
      .put("Hello World", "Hello World")
      .put("Hello\\nWorld", "Hello\nWorld")
      .put("\\n\\\\\\/\\b\\f\\n\\r\\t", "\n\\/\b\f\n\r\t")
      .build();
  
  @Test
  public void testStringEscape() throws IOException, ParserException
  {
    
    for(Entry<String, String> entry : stringEscapes.entrySet())
    {
      JsonParser parser = new JsonParser.Builder()
          .withInput(entry.getKey() + "\"")
          .build();
      
      test(entry.getValue(), parser.getQuotedString());
    }
  }
  

  @Test
  public void testObjectDuplicateAttribute()
  {
    JsonDom dom = parse("{\"name\": \"pi\", \"name\": \"e\"}");
    
    assertTrue(dom.getErrors().size() == 1);
    assertTrue(dom.getErrors().get(0) instanceof DuplicateAttributeException);
  }
  
  @Test
  public void testParseEmptyObject()
  {
    testParseObject("{}", "{}\n");
  }
  
  @Test
  public void testParseStringObject()
  {
    JsonObjectDom objectDom = testParseObject("{\"name\": \"pi\", \"value\": \"circle ratio\"}", 
        "{\n" + 
        "  \"name\":\"pi\",\n" + 
        "  \"value\":\"circle ratio\"\n" + 
        "}\n");
    
    JsonObject object = objectDom.getObject();
    
    JsonDomNode nameElement = object.get("name");
    
    assertTrue(nameElement instanceof JsonString);
    test("pi", ((JsonString)nameElement).asString());
    
    JsonDomNode valueElement = object.get("value");
    assertTrue(valueElement instanceof JsonString);
    assertTrue("circle ratio".equals(((JsonString)valueElement).asString()));
  }
  
  @Test
  public void testParseNullObject()
  {
    JsonObjectDom objectDom = testParseObject("{\"name\": \"pi\", \"value\":    null    }", 
        "{\n" + 
        "  \"name\":\"pi\",\n" + 
        "  \"value\":null\n" + 
        "}\n");
    
    JsonObject object = objectDom.getObject();
    
    JsonDomNode nameElement = object.get("name");
    
    assertTrue(nameElement instanceof JsonString);
    test("pi", ((JsonString)nameElement).asString());
    
    JsonDomNode valueElement = object.get("value");
    assertTrue(valueElement instanceof JsonNull);
  }
  
  @Test
  public void testParseBooleanObject()
  {
    JsonObjectDom objectDom = testParseObject("{\"name\": \"pi\", \"value\": true,    \"alternateValue\":false}", 
        "{\n" + 
        "  \"alternateValue\":false,\n" + 
        "  \"name\":\"pi\",\n" + 
        "  \"value\":true\n" + 
        "}\n");
    
    JsonObject object = objectDom.getObject();
    
    JsonDomNode nameElement = object.get("name");
    
    assertTrue(nameElement instanceof JsonString);
    test("pi", ((JsonString)nameElement).asString());
    
    JsonDomNode valueElement = object.get("value");
    assertTrue(valueElement instanceof JsonBoolean);
    assertTrue(((JsonBoolean)valueElement).asBoolean());
    
    valueElement = object.get("alternateValue");
    assertTrue(valueElement instanceof JsonBoolean);
    assertTrue(!((JsonBoolean)valueElement).asBoolean());
  }
  
  @Test
  public void testParseLongObject()
  {
    JsonObjectDom objectDom = testParseObject("{\"name\": \"big\", \"value\": 2147483647999}", 
        "{\n" + 
        "  \"name\":\"big\",\n" + 
        "  \"value\":2147483647999\n" + 
        "}\n" + 
        "");
    
    JsonObject object = objectDom.getObject();
    
    JsonDomNode valueElement = object.get("value");
    assertTrue(valueElement instanceof JsonParsedNumber);
    assertTrue(2147483647999L == ((JsonParsedNumber)valueElement).asLong());
  }  
  
  @Test
  public void testParseNegativeLongObject()
  {
    JsonObjectDom objectDom = testParseObject("{\"name\": \"big\", \"value\": -2147483647999}", 
        "{\n" + 
        "  \"name\":\"big\",\n" + 
        "  \"value\":-2147483647999\n" + 
        "}\n" + 
        "");
    
    JsonObject object = objectDom.getObject();
    
    JsonDomNode valueElement = object.get("value");
    assertTrue(valueElement instanceof JsonParsedNumber);
    assertTrue(-2147483647999L == ((JsonParsedNumber)valueElement).asLong());
  }  
  
  @Test
  public void testParseIntObject()
  {
    JsonObjectDom objectDom = testParseObject("{\"name\": \"small\", \"value\": 6}", 
        "{\n" + 
        "  \"name\":\"small\",\n" + 
        "  \"value\":6\n" + 
        "}\n" + 
        "");
    
    JsonObject object = objectDom.getObject();
    
    JsonDomNode valueElement = object.get("value");
    assertTrue(valueElement instanceof JsonParsedNumber);
    assertTrue(6 == ((JsonParsedNumber)valueElement).asInteger());
  }  
  
  @Test
  public void testParseNegativeIntObject()
  {
    JsonObjectDom objectDom = testParseObject("{\"name\": \"small\", \"value\": -6}", 
        "{\n" + 
        "  \"name\":\"small\",\n" + 
        "  \"value\":-6\n" + 
        "}\n");
    
    JsonObject object = objectDom.getObject();
    
    JsonDomNode valueElement = object.get("value");
    assertTrue(valueElement instanceof JsonParsedNumber);
    assertTrue(-6 == ((JsonParsedNumber)valueElement).asInteger());
  }  
  
  @Test
  public void testParseBigDoubleObject()
  {
    JsonObjectDom objectDom = testParseObject("{\"name\": \"avogadro\", \"value\": 6.02214086e23}", 
        "{\n" + 
        "  \"name\":\"avogadro\",\n" + 
        "  \"value\":6.02214086e23\n" + 
        "}\n" + 
        "");
    
    JsonObject object = objectDom.getObject();
    
    JsonDomNode nameElement = object.get("name");
    
    assertTrue(nameElement instanceof JsonString);
    test("avogadro", ((JsonString)nameElement).asString());
    
    JsonDomNode valueElement = object.get("value");
    assertTrue(valueElement instanceof JsonParsedNumber);
    assertTrue(6.02214086e23 == ((JsonParsedNumber)valueElement).asDouble());
  }   
  
  @Test
  public void testParseBigDoubleObject2()
  {
    JsonObjectDom objectDom = testParseObject("{\"name\": \"avogadro\", \"value\": 6.02214086e23}", 
        "{\n" + 
        "  \"name\":\"avogadro\",\n" + 
        "  \"value\":6.02214086e23\n" + 
        "}\n" + 
        "");
    
    JsonObject object = objectDom.getObject();
    
    JsonDomNode nameElement = object.get("name");
    
    assertTrue(nameElement instanceof JsonString);
    test("avogadro", ((JsonString)nameElement).asString());
    
    JsonDomNode valueElement = object.get("value");
    assertTrue(valueElement instanceof JsonParsedNumber);
    assertTrue(6.02214086e23 == ((JsonParsedNumber)valueElement).asDouble());
  }  
  
  @Test
  public void testParsSmallDoubleObject()
  {
    JsonObjectDom objectDom = testParseObject("{\"name\": \"hydrogen\", \"value\": 1.6735575e-27}", 
        "{\n" + 
        "  \"name\":\"hydrogen\",\n" + 
        "  \"value\":1.6735575e-27\n" + 
        "}\n" + 
        "");
    
    JsonObject object = objectDom.getObject();
    
    JsonDomNode nameElement = object.get("name");
    
    assertTrue(nameElement instanceof JsonString);
    test("hydrogen", ((JsonString)nameElement).asString());
    
    JsonDomNode valueElement = object.get("value");
    assertTrue(valueElement instanceof JsonParsedNumber);
    assertTrue(1.6735575e-27 == ((JsonParsedNumber)valueElement).asDouble());
  } 
  
  @Test
  public void testParseNegativelDoubleObject()
  {
    JsonObjectDom objectDom = testParseObject("{\"name\": \"minusPi\", \"value\": -3.14159}", 
        "{\n" + 
        "  \"name\":\"minusPi\",\n" + 
        "  \"value\":-3.14159\n" + 
        "}\n" + 
        "");
    
    JsonObject object = objectDom.getObject();
    
    JsonDomNode valueElement = object.get("value");
    assertTrue(valueElement instanceof JsonParsedNumber);
    assertTrue(-3.14159F == ((JsonParsedNumber)valueElement).asFloat());
  }
  
  @Test
  public void testPWhiteSpacelDoubleObject()
  {
    JsonObjectDom objectDom = testParseObject(""
        + "\n"
        + "\n"
        + "    \n"
        + "{      \"name\":\n"
        + "  \n"
        + " \"minusPi\",      \"value\":-3.14159}\n"
        + "\n"
        + "\n", 
        "{\n" + 
        "  \"name\":\"minusPi\",\n" + 
        "  \"value\":-3.14159\n" + 
        "}\n" + 
        "");
    
    JsonObject object = objectDom.getObject();
    
    JsonDomNode valueElement = object.get("value");
    assertTrue(valueElement instanceof JsonParsedNumber);
    assertTrue(-3.14159F == ((JsonParsedNumber)valueElement).asFloat());
  }
  
  @Test
  public void testParseDoubleObject()
  {
    JsonObjectDom objectDom = testParseObject("{\"name\": \"pi\", \"value\": 3.141590118408203}", 
        "{\n" + 
        "  \"name\":\"pi\",\n" + 
        "  \"value\":3.141590118408203\n" + 
        "}\n" + 
        "");
    
    JsonObject object = objectDom.getObject();
    
    JsonDomNode nameElement = object.get("name");
    
    assertTrue(nameElement instanceof JsonString);
    test("pi", ((JsonString)nameElement).asString());
    
    JsonDomNode valueElement = object.get("value");
    assertTrue(valueElement instanceof JsonParsedNumber);
    assertTrue(3.141590118408203 == ((JsonParsedNumber)valueElement).asDouble());
  }
  
  @Test
  public void testParseEmptyArray()
  {
    testParseArray("[]", 
        "[]\n");
  }
    
  @Test
  public void testParseEmptyWhtespaceArray()
  {
    testParseArray("\n"
        + "[\n            ]", 
        "[]\n");
  }
  

  
  @Test
  public void testParseIntegerArray()
  {
    JsonArrayDom arrayDom = testParseArray("[1,-2,3,-5,8,-11]", 
        "[\n" + 
        "  1,\n" + 
        "  -2,\n" + 
        "  3,\n" + 
        "  -5,\n" + 
        "  8,\n" + 
        "  -11\n" + 
        "]\n");
  
    JsonArray array = arrayDom.getArray();
    
    int[] values = new int[] {1,-2,3,-5,8,-11};
    int cnt=0;
    
    for(JsonDomNode node : array)
    {
      if(node instanceof JsonParsedNumber)
      {
        if(((JsonParsedNumber)node).asInteger() != values[cnt])
        {
          String message = "Expected " + values[cnt] + " but found " + ((JsonParsedNumber)node).asInteger();
          System.err.println(message);
          fail(message);
        }
        cnt++;
      }
      else
      {
        String message = "Expected Integer but found " + node.getClass();
        System.err.println(message);
        fail(message);
      }
    }
  }
  
  @Test
  public void testParseArrayHandler()
  {
    TestIntegerConsumer consumer = new TestIntegerConsumer(new int[] {1,-2,3,-5,8,-11});
    
    JsonDom dom = new JsonParser.Builder()
      .withInput("[1,-2,3,-5,8,-11]")
      .withArrayElementConsumer(consumer)
      .build()
      .parse();
    
    assertTrue(dom instanceof JsonInvalidDom);
    assertTrue(dom.getErrors().isEmpty());
    assertEquals(6, consumer.cnt_);
  }
  
  @Test
  public void testParseInvalidArrayHandler()
  {
    TestIntegerConsumer consumer = new TestIntegerConsumer(new int[] {1,-2,3,-5,8,-11});
    
    JsonDom dom = new JsonParser.Builder()
      .withInput("[1,BLAH FOO,-2,3,-5,8,-11]")
      .withArrayElementConsumer(consumer)
      .build()
      .parse();
    
    assertTrue(dom instanceof JsonInvalidDom);
    assertEquals(1, dom.getErrors().size());
    assertEquals(6, consumer.cnt_);
  }
  
  class TestIntegerConsumer implements Consumer<JsonDomNode>
  {
    int[] values_;
    int cnt_=0;
    
    TestIntegerConsumer(int[] values)
    {
      values_ = values;
    }
    
    @Override
    public void accept(JsonDomNode node)
    {
      if(node instanceof JsonParsedNumber)
      {
        if(((JsonParsedNumber)node).asInteger() != values_[cnt_])
        {
          String message = "Expected " + values_[cnt_] + " but found " + ((JsonParsedNumber)node).asInteger();
          System.err.println(message);
          fail(message);
        }
        cnt_++;
      }
      else
      {
        String message = "Expected Integer but found " + node.getClass();
        System.err.println(message);
        fail(message);
      }
    }
  }

  private JsonArrayDom testParseArray(String input, String output)
  {

    JsonDom dom = parse(input);
    
    if(!dom.getErrors().isEmpty())
    {
      System.out.format("expected %s%n", output);
      System.out.flush();
      for(ParserException error : dom.getErrors())
      {
        System.err.println(error);
      }
      Assert.fail();
    }
    
    JsonArrayDom arrayDom = (JsonArrayDom) dom;
    
    test(output, arrayDom.getArray().toString());
    
    return arrayDom;
  }
  

  private JsonDom parse(String input)
  {
    return new JsonParser.Builder()
        .withInput(input)
        .build()
        .parse();
  }


  @Test
  public void testParseFloatObject()
  {
    JsonObjectDom objectDom = testParseObject("{\"name\": \"pi\", \"value\": 3.14159}", 
        "{\n" + 
        "  \"name\":\"pi\",\n" + 
        "  \"value\":3.14159\n" + 
        "}\n" + 
        "");
    
    JsonObject object = objectDom.getObject();
    
    JsonDomNode nameElement = object.get("name");
    
    assertTrue(nameElement instanceof JsonString);
    test("pi", ((JsonString)nameElement).asString());
    
    JsonDomNode valueElement = object.get("value");
    assertTrue(valueElement instanceof JsonParsedNumber);
    
    assertTrue(3.14159F == ((JsonParsedNumber)valueElement).asFloat());
  }

  private JsonObjectDom testParseObject(String input, String output)
  {

    JsonDom dom = parse(input);
    
    if(!dom.getErrors().isEmpty())
    {
      System.out.format("expected %s%n", output);
      System.out.flush();
      for(ParserException error : dom.getErrors())
      {
        System.err.println(error);
      }
      Assert.fail();
    }
    
    JsonObjectDom objectDom = (JsonObjectDom) dom;
    
    test(output, objectDom.getObject().toString());
    
    return objectDom;
  }


  private void test(String expected, String received)
  {
    if(!expected.equals(received))
    {
      System.out.format("expected %s%nreceived %s%n", expected, received);
      System.out.flush();
      
      Assert.assertEquals(expected, received);
    }
  }
}

  
