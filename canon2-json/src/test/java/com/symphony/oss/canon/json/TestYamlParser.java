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

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.symphony.oss.canon.json.model.JsonArrayDom;
import com.symphony.oss.canon.json.model.JsonBigDecimal;
import com.symphony.oss.canon.json.model.JsonBoolean;
import com.symphony.oss.canon.json.model.JsonDom;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonDouble;
import com.symphony.oss.canon.json.model.JsonFloat;
import com.symphony.oss.canon.json.model.JsonInteger;
import com.symphony.oss.canon.json.model.JsonLong;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon.json.model.JsonObjectDom;
import com.symphony.oss.canon.json.model.JsonString;

@SuppressWarnings("javadoc")
public class TestYamlParser
{

  @Test
  public void testGetValue() throws IOException, ParserException
  {
    testGetValue("-765546546547723.03330025", new JsonBigDecimal.Builder().withValue(new BigDecimal("-765546546547723.03330025")).build());
    testGetValue("-765547723.033303", new JsonDouble.Builder().withValue(-765547723.033303).build());
    testGetValue("-76.03", new JsonFloat.Builder().withValue(-76.03f).build());
    testGetValue("-76554772399", new JsonLong.Builder().withValue(-76554772399L).build());
    testGetValue("-76", new JsonInteger.Builder().withValue(-76).build());
    testGetValue("X76", new JsonString.Builder().withValue("X76").build());
  }
  
  private void testGetValue(String input, JsonDomNode node) throws IOException, ParserException
  {
    YamlParser parser = new YamlParser.Builder()
        .withInput(input)
        .build();
    
    parser.readLine();
    
    JsonDomNode domNode = parser.getValue(YamlStyle.BLOCK, 0);
    
    test(node.toString(), domNode.toString());
    assertEquals(node, domNode);
  }

  @Test
  public void testConciseObject() throws IOException, ParserException
  {
    YamlParser parser = new YamlParser.Builder()
        .withInput("\"$ref\": \"#/components/schemas/DoubleMinMax\"")
        .build();
    
    parser.readLine();
    
    JsonDomNode value = parser.getValue(YamlStyle.BLOCK, 0);
    
    
    test("{\n"
        + "  \"$ref\":\"#/components/schemas/DoubleMinMax\"\n"
        + "}\n", value.toString());
    assertTrue(value instanceof JsonObject);
  }
  
  @Test
  public void testDoubleQuotedWhitespace() throws IOException, ParserException
  {
    testDoubleQuotedEscape("\\n\\\\\\/\\b\\f\\n\\r\\t", "\n\\/\b\f\n\r\t");
  }
  
  @Test
  public void testDoubleQuotedBlankLine() throws IOException, ParserException
  {
    testDoubleQuotedEscape("Hello\\nWorld", "Hello\nWorld");
  }
  
  @Test
  public void testDoubleQuotedSimpleString() throws IOException, ParserException
  {
    testDoubleQuotedEscape("Hello World", "Hello World");
  }
  
  @Test
  public void testDoubleQuotedExample1() throws IOException, ParserException
  {
    testDoubleQuotedEscape(" 1st line\n" + 
        "      indented line\n" + 
        "\ttabbed line\n" +
        "two trailing spaces  \\\n" +
        "final line (no newline)",
        " 1st line indented line \ttabbed line two trailing spaces  final line (no newline)");
  }
  
  @Test
  public void testDoubleQuotedExample2() throws IOException, ParserException
  {
    testDoubleQuotedEscape(
        "Java epoch of 1970-01-01T00:00:00Z where instants after the epoch have positive values",
        "Java epoch of 1970-01-01T00:00:00Z where instants after the epoch have positive values"
    );
  }
  
  @Test
  public void testDoubleQuotedExample3() throws IOException, ParserException
  {
    testDoubleQuotedEscape(
    "Several lines of text,\n" + 
    "containing \\\"double quotes\\\". Escapes (like \\\\n) work.\\nIn addition,\n" +
    "newlines can be esc\\\n" +
    "aped to prevent them from being converted to a space.\n" +
    "\n" +
    "Newlines can also be added by leaving a blank line.\n" +
    "  Leading whitespace on lines is ignored.",
    "Several lines of text, " + 
        "containing \"double quotes\". Escapes (like \\n) work.\nIn addition, " +
        "newlines can be escaped to prevent them from being converted to a space.\n" +
        "Newlines can also be added by leaving a blank line. " +
        "Leading whitespace on lines is ignored."
    
    );
  }
  
  void testDoubleQuotedEscape(String input, String expected) throws IOException, ParserException
  {
    YamlParser parser = new YamlParser.Builder()
        .withInput(input + "\"")
        .build();
    
    parser.readLine();
    
    test(expected, parser.getDoubleQuotedString());
  }
  
  @Test
  public void testSingleQuotedExample2() throws IOException, ParserException
  {
    testSingleQuotedEscape(
    
        "Several lines of text,\n" +
        "containing ''single quotes''. Escapes (like \\n) don''t do anything.\n" +
        "\n" +
        "Newlines can be added by leaving a blank line.\n" +
        "  Leading whitespace on lines is ignored."
        ,
    "Several lines of text, containing 'single quotes'. Escapes (like \\n) don't do anything.\n" + 
    "Newlines can be added by leaving a blank line. Leading whitespace on lines is ignored."
    
    );
  }
  
  void testSingleQuotedEscape(String input, String expected) throws IOException, ParserException
  {
    YamlParser parser = new YamlParser.Builder()
        .withInput(input + "'")
        .build();
    
    parser.readLine();
    
    test(expected, parser.getSingleQuotedString());
  }
  
  

  @Test
  public void testBlockObjectDuplicateAttribute()
  {
    JsonDom dom = parse("name: value\n" +
        "name: value2\n");
    
    assertTrue(dom.getErrors().size() == 1);
    assertTrue(dom.getErrors().get(0) instanceof DuplicateAttributeException);
  }
  

  @Test
  public void testFlowObjectDuplicateAttribute()
  {
    JsonDom dom = parse("{\"name\": \"pi\", \"name\": \"e\"}");
    
    assertTrue(dom.getErrors().size() == 1);
    assertTrue(dom.getErrors().get(0) instanceof DuplicateAttributeException);
  }
  
  @Test
  public void testSimpleBlockObject()
  {
    parseObject("name: value\n" +
        "name2: value2\n",
        "{\n"
        + "  \"name\":\"value\",\n"
        + "  \"name2\":\"value2\"\n"
        + "}\n");
  }
  
  @Test
  public void testNestedBlockObject()
  {
  parseObject(
      "name: value\n" +
      "nested:\n" +
      "  name2: value2\n",
      
      "{\n" + 
      "  \"name\":\"value\",\n" + 
      "  \"nested\":{\n" + 
      "    \"name2\":\"value2\"\n" + 
      "  }\n" + 
      "}\n");
  }
  
  @Test
  public void testQuotedSimpleBlockObject()
  {
    parseObject("\"first name\": fred\n" +
        "'last name': bloggs\n",
        "{\n"
        + "  \"first name\":\"fred\",\n"
        + "  \"last name\":\"bloggs\"\n"
        + "}\n");
  }
  


  @Test
  public void testSimpleFlowObject()
  {
    parseObject("{name: value, name2: value2}",
        "{\n"
        + "  \"name\":\"value\",\n"
        + "  \"name2\":\"value2\"\n"
        + "}\n");
  }
  
  @Test
  public void testBigIntegerFlowObject()
  {
    parseObject("{\n" + 
        "            \"description\":\"A float\",\n" + 
        "            \"type\":\"number\",\n" + 
        "            \"format\":\"double\",\n" + 
        "            \"minimum\":-765546546547723,\n" + 
        "            \"maximum\":7665465456464550000\n" + 
        "}",
        "{\n" + 
        "  \"description\":\"A float\",\n" + 
        "  \"format\":\"double\",\n" + 
        "  \"maximum\":7665465456464550000,\n" + 
        "  \"minimum\":-765546546547723,\n" + 
        "  \"type\":\"number\"\n" + 
        "}\n");
  }
  
  @Test
  public void testEmbeddedColonFlowObject()
  {
    parseObject(
        "description: Seconds measured from the standard Java epoch of 1970-01-01T00:00:00Z\n" + 
        "            where instants after the epoch have positive values, and earlier instants\n" + 
        "            have negative values.\n" + 
        "type: integer\n" + 
        "format: int64",
        "{\n" + 
        "  \"description\":\"Seconds measured from the standard Java epoch of 1970-01-01T00:00:00Z where instants after the epoch have positive values, and earlier instants have negative values.\",\n" + 
        "  \"format\":\"int64\",\n" + 
        "  \"type\":\"integer\"\n" + 
        "}\n");
  }
  
  @Test
  public void testEmbeddedColonBlockObject()
  {
    parseObject("{\n" + 
        "            \"description\":\"Seconds measured from the standard Java epoch of 1970-01-01T00:00:00Z where instants after the epoch have positive values, and earlier instants have negative values.\",\n" + 
        "            \"type\":\"integer\",\n" + 
        "            \"format\":\"int64\"\n" + 
        "          }",
        "{\n" + 
        "  \"description\":\"Seconds measured from the standard Java epoch of 1970-01-01T00:00:00Z where instants after the epoch have positive values, and earlier instants have negative values.\",\n" + 
        "  \"format\":\"int64\",\n" + 
        "  \"type\":\"integer\"\n" + 
        "}\n");
  }
  
  @Test
  public void testStringFlowObject()
  {
    parseObject("{\"name\": \"pi\", \"value\": \"circle ratio\"}", 
        "{\n" + 
        "  \"name\":\"pi\",\n" + 
        "  \"value\":\"circle ratio\"\n" + 
        "}\n");
  }
  
  @Test
  public void testStringBlockObject()
  {
    parseObject("name: pi\nvalue: circle ratio\n", 
        "{\n" + 
        "  \"name\":\"pi\",\n" + 
        "  \"value\":\"circle ratio\"\n" + 
        "}\n");
  }
  
  @Test
  public void testNullObject()
  {
    parseObject("{\"name\": \"pi\", \"value\":    null    }", 
        "{\n" + 
        "  \"name\":\"pi\",\n" + 
        "  \"value\":null\n" + 
        "}\n");
  }
  
  @Test
  public void testBooleanObject()
  {
    JsonObjectDom objectDom = parseObject("{\"name\": \"pi\", \"value\": true,    \"alternateValue\":false}", 
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
  public void testLongObject()
  {
    JsonObjectDom objectDom = parseObject("{\"name\": \"big\", \"value\": 2147483647999}", 
        "{\n" + 
        "  \"name\":\"big\",\n" + 
        "  \"value\":2147483647999\n" + 
        "}\n" + 
        "");
    
    JsonObject object = objectDom.getObject();
    
    JsonDomNode valueElement = object.get("value");
    assertTrue(valueElement instanceof JsonLong);
    assertTrue(2147483647999L == ((JsonLong)valueElement).asLong());
  }  
  
  @Test
  public void testNegativeLongObject()
  {
    JsonObjectDom objectDom = parseObject("{\"name\": \"big\", \"value\": -2147483647999}", 
        "{\n" + 
        "  \"name\":\"big\",\n" + 
        "  \"value\":-2147483647999\n" + 
        "}\n" + 
        "");
    
    JsonObject object = objectDom.getObject();
    
    JsonDomNode valueElement = object.get("value");
    assertTrue(valueElement instanceof JsonLong);
    assertTrue(-2147483647999L == ((JsonLong)valueElement).asLong());
  }  
  
  @Test
  public void testIntObject()
  {
    JsonObjectDom objectDom = parseObject("{\"name\": \"small\", \"value\": 6}", 
        "{\n" + 
        "  \"name\":\"small\",\n" + 
        "  \"value\":6\n" + 
        "}\n" + 
        "");
    
    JsonObject object = objectDom.getObject();
    
    JsonDomNode valueElement = object.get("value");
    assertTrue(valueElement instanceof JsonInteger);
    assertTrue(6 == ((JsonInteger)valueElement).asInteger());
  }  
  
  @Test
  public void testNegativeIntObject()
  {
    JsonObjectDom objectDom = parseObject("{\"name\": \"small\", \"value\": -6}", 
        "{\n" + 
        "  \"name\":\"small\",\n" + 
        "  \"value\":-6\n" + 
        "}\n");
    
    JsonObject object = objectDom.getObject();
    
    JsonDomNode valueElement = object.get("value");
    assertTrue(valueElement instanceof JsonInteger);
    assertTrue(-6 == ((JsonInteger)valueElement).asInteger());
  }  
  
  @Test
  public void testBigDoubleObject()
  {
    JsonObjectDom objectDom = parseObject("{\"name\": \"avogadro\", \"value\": 6.02214086e23}", 
        "{\n" + 
        "  \"name\":\"avogadro\",\n" + 
        "  \"value\":6.02214086E23\n" + 
        "}\n" + 
        "");
    
    JsonObject object = objectDom.getObject();
    
    JsonDomNode nameElement = object.get("name");
    
    assertTrue(nameElement instanceof JsonString);
    test("avogadro", ((JsonString)nameElement).asString());
    
    JsonDomNode valueElement = object.get("value");
    assertTrue(valueElement instanceof JsonDouble);
    assertTrue(6.02214086e23 == ((JsonDouble)valueElement).asDouble());
  }   
  
  @Test
  public void testBigDoubleObject2()
  {
    JsonObjectDom objectDom = parseObject("{\"name\": \"avogadro\", \"value\": 6.02214086E23}", 
        "{\n" + 
        "  \"name\":\"avogadro\",\n" + 
        "  \"value\":6.02214086E23\n" + 
        "}\n" + 
        "");
    
    JsonObject object = objectDom.getObject();
    
    JsonDomNode nameElement = object.get("name");
    
    assertTrue(nameElement instanceof JsonString);
    test("avogadro", ((JsonString)nameElement).asString());
    
    JsonDomNode valueElement = object.get("value");
    assertTrue(valueElement instanceof JsonDouble);
    assertTrue(6.02214086e23 == ((JsonDouble)valueElement).asDouble());
  }  
  
  @Test
  public void testSmallDoubleObject()
  {
    JsonObjectDom objectDom = parseObject("{\"name\": \"hydrogen\", \"value\": 1.6735575e-27}", 
        "{\n" + 
        "  \"name\":\"hydrogen\",\n" + 
        "  \"value\":1.6735575E-27\n" + 
        "}\n" + 
        "");
    
    JsonObject object = objectDom.getObject();
    
    JsonDomNode nameElement = object.get("name");
    
    assertTrue(nameElement instanceof JsonString);
    test("hydrogen", ((JsonString)nameElement).asString());
    
    JsonDomNode valueElement = object.get("value");
    assertTrue(valueElement instanceof JsonDouble);
    assertTrue(1.6735575e-27 == ((JsonDouble)valueElement).asDouble());
  } 
  
  @Test
  public void testNegativelDoubleObject()
  {
    JsonObjectDom objectDom = parseObject("{\"name\": \"minusPi\", \"value\": -3.14159}", 
        "{\n" + 
        "  \"name\":\"minusPi\",\n" + 
        "  \"value\":-3.14159\n" + 
        "}\n" + 
        "");
    
    JsonObject object = objectDom.getObject();
    
    JsonDomNode valueElement = object.get("value");
    assertTrue(valueElement instanceof JsonFloat);
    assertTrue(-3.14159F == ((JsonFloat)valueElement).asFloat());
  }
  
  @Test
  public void testPWhiteSpacelDoubleObject()
  {
    JsonObjectDom objectDom = parseObject(""
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
    assertTrue(valueElement instanceof JsonFloat);
    assertTrue(-3.14159F == ((JsonFloat)valueElement).asFloat());
  }
  
  @Test
  public void testDoubleObject()
  {
    JsonObjectDom objectDom = parseObject("{\"name\": \"pi\", \"value\": 3.141590118408203}", 
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
    assertTrue(valueElement instanceof JsonDouble);
    assertTrue(3.141590118408203 == ((JsonDouble)valueElement).asDouble());
  }
  
  @Test
  public void testTrivialArray()
  {
    parseArray("- a\n", 
          "[\n"
        + "  \"a\"\n"
        + "]\n");
  }
  
  @Test
  public void testSimpleArray()
  {
    parseArray("- a\n- b\n", 
          "[\n"
        + "  \"a\",\n"
        + "  \"b\"\n"
        + "]\n");
  }
  
  @Test
  public void testFlowTrivialArray()
  {
    parseArray("[a]\n", 
          "[\n"
        + "  \"a\"\n"
        + "]\n");
  }
  
  @Test
  public void testFlowSimpleArray()
  {
    parseArray("[a,b]\n", 
          "[\n"
        + "  \"a\",\n"
        + "  \"b\"\n"
        + "]\n");
  }
  
  @Test
  public void testIllegalMappingEntry()
  {
    parseArray("- : a\n- b\n", 
          "[\n"
        + "  \"b\"\n"
        + "]\n",
        1);
  }
  
  @Test
  public void testIllegalMappingKey()
  {
    parseArray("- ? a\n- b\n", 
        "[\n"
      + "  \"b\"\n"
      + "]\n",
      1);
  }
  
  @Test
  public void testIllegalMappingKey2()
  {
    parseArray("? a\n- b\n", 
        "[\n"
      + "  \"b\"\n"
      + "]\n",
      1);
  }
    
  @Test
  public void testEmptyWhtespaceArray()
  {
    parseArray("-     \n \n\n    \n", 
        "[\n"
      + "  null\n"
      + "]\n");
  }
  
  @Test
  public void testArray1()
  {
    parseArray("- 1a\n" + 
        "- 1b\n" + 
        "-\n" + 
        "  - 2a\n" + 
        "  - 2b\n" + 
        "  -\n" + 
        "    - 3a\n" + 
        "    - 3b\n" + 
        "    -\n" + 
        "      - 4a\n" + 
        "      - 4b\n" + 
        "    - 3c\n" + 
        "  - 2c\n" + 
        "- 1 c",
        
        "[\n" + 
        "  \"1a\",\n" + 
        "  \"1b\",\n" + 
        "  [\n" + 
        "    \"2a\",\n" + 
        "    \"2b\",\n" + 
        "    [\n" + 
        "      \"3a\",\n" + 
        "      \"3b\",\n" + 
        "      [\n" + 
        "        \"4a\",\n" + 
        "        \"4b\"\n" + 
        "      ],\n" + 
        "      \"3c\"\n" + 
        "    ],\n" + 
        "    \"2c\"\n" + 
        "  ],\n" + 
        "  \"1 c\"\n" + 
        "]\n" 
        );
  }
  
  @Test
  public void testArray2()
  {
    parseArray("- 1a\n" + 
        "- 1b\n" + 
        "-\n" + 
        "  - 2a\n" + 
        "  - 2b\n" + 
        "  -\n" + 
        "    - 3a\n" + 
        "    - 3b\n" + 
        "    - [4a, 4b]\n" + 
        "    - 3c\n" + 
        "  - 2c\n" + 
        "- 1 c",
        
        "[\n" + 
        "  \"1a\",\n" + 
        "  \"1b\",\n" + 
        "  [\n" + 
        "    \"2a\",\n" + 
        "    \"2b\",\n" + 
        "    [\n" + 
        "      \"3a\",\n" + 
        "      \"3b\",\n" + 
        "      [\n" + 
        "        \"4a\",\n" + 
        "        \"4b\"\n" + 
        "      ],\n" + 
        "      \"3c\"\n" + 
        "    ],\n" + 
        "    \"2c\"\n" + 
        "  ],\n" + 
        "  \"1 c\"\n" + 
        "]\n" 
        );
  }
  
  @Test
  public void testArray3()
  {
    parseArray("[     4a    \n"
        + "line2  , 4b]",
        
        "[\n" + 
        "  \"4a line2\",\n" + 
        "  \"4b\"\n" + 
        "]\n" 
        );
  }
  
  @Test
  public void testObjectDoubleQuotedString()
  {
    parseObject("first: {\"value a\": \"\\\\a, \\a, \\\\\", \"value b\": 'b'}\n" + 
        "second: a\n"
        ,
        
        "{\n" + 
        "  \"first\":{\n" + 
        "    \"value a\":\"\\\\a, \\u0007, \\\\\",\n" +
        "    \"value b\":\"b\"\n" + 
        "  },\n" + 
        "  \"second\":\"a\"\n" + 
        "}\n" 
        );
  }
  
  @Test
  public void testArrayDoubleQuotedString()
  {
    parseArray("- [\"\\\\a, \\a, \\\\\n" + 
        "'b',\n" + 
        "\\\"c\\\"\"]\n" + 
        "- a\n"
        ,
        
        "[\n" + 
        "  [\n" + 
        "    \"\\\\a, \\u0007, \\\\ 'b', \\\"c\\\"\"\n" + 
        "  ],\n" + 
        "  \"a\"\n" + 
        "]\n" 
        );
  }
  
  @Test
  public void testArraySingleQuotedString()
  {
    parseArray( 
        "- a\n" + 
        "- 'c,\n" + 
        "\n" + 
        "\n" + 
        "\"d\",\\\n" + 
        "\n" + 
        "''e'''\n" + 
        "",
        
        "[\n" + 
        "  \"a\",\n" + 
        "  \"c,\\n\\\"d\\\",\\n'e'\"\n" + 
        "]\n" 
        );
  }
  

  
  @Test
  public void testFlowArray()
  {
    parseArray("[\"1,-2\",3,-5,8,-11]",
        
        "[\n" + 
        "  \"1,-2\",\n" + 
        "  3,\n" + 
        "  -5,\n" + 
        "  8,\n" + 
        "  -11\n" + 
        "]\n");
  }
  

  @Test
  public void testIntegerBlockArray()
  {
    parseArray(
        
        "- 1"
        + "- -2\n"
        + "- 3\n"
        + "- -5"
        + "\n"
        + "- 8\n"
        + "- -11\n",
        
        "[\n" + 
        "  \"1- -2\",\n" + 
        "  3,\n" + 
        "  -5,\n" + 
        "  8,\n" + 
        "  -11\n" + 
        "]\n");
  }
  
//  
//  @Test
//  public void testInvalidArrayHandler()
//  {
//    TestIntegerConsumer consumer = new TestIntegerConsumer(new int[] {1,-2,3,-5,8,-11});
//    
//    JsonDom dom = new YamlParser.Builder()
//      .withInput("[1,BLAH FOO,-2,3,-5,8,-11]")
//      .withArrayElementConsumer(consumer)
//      .build()
//      .parse();
//    
//    assertTrue(dom instanceof JsonInvalidDom);
//    assertEquals(1, dom.getErrors().size());
//    assertEquals(6, consumer.cnt_);
//  }
  
  

  private JsonDom parse(String input)
  {
    return new YamlParser.Builder()
        .withInput(input)
        .build()
        .parse();
  }


  @Test
  public void testFloatObject()
  {
    JsonObjectDom objectDom = parseObject(
        "name: pi\nvalue: 3.14159\n", 
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
    assertTrue(valueElement instanceof JsonFloat);
    
    assertTrue(3.14159F == ((JsonFloat)valueElement).asFloat());
  }
  

  private JsonArrayDom parseArray(String input, String output)
  {
    return parseArray(input, output, 0);
  }
  
  private JsonArrayDom parseArray(String input, String output, int errorCnt)
  {

    JsonDom dom = parse(input);
    
    if(dom.getErrors().size() != errorCnt || !(dom instanceof JsonArrayDom))
    {
      System.out.format("expected %s%nreceived %s%n", output, dom.toString());
      System.out.flush();
      Assert.fail();
    }
    
    JsonArrayDom arrayDom = (JsonArrayDom) dom;
    
    test(output, arrayDom.getArray().toString());
    
    return arrayDom;
  }

  private JsonObjectDom parseObject(String input, String output)
  {
    return parseObject(input, output, 0);
  }

  private JsonObjectDom parseObject(String input, String output, int errorCnt)
  {
    JsonDom dom = parse(input);
    
    if(dom.getErrors().size() != errorCnt || !(dom instanceof JsonObjectDom))
    {
      System.out.format("expected %s%nreceived %s%n", output, dom.toString());
      System.out.flush();
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
      
//      for(int i=0 ; i<Math.min(expected.length(), received.length()) ; i++)
//      {
//        char c = received.charAt(i);
//        char e = expected.charAt(i);
//        
//        System.out.print((int)c);
//        System.out.print(" == ");
//        System.out.print((int)e);
//        
//        System.out.print(" -> ");
//
//        System.out.print(c);
//        System.out.print(" == ");
//        System.out.println(e);
//      }
      
      Assert.assertEquals(expected, received);
    }
  }
}

  
