/**
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

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

import com.symphony.oss.canon.json.ParserResultException;
import com.symphony.oss.canon.test.typeCheck.AllTheTypes.InlineObjectField;
import com.symphony.oss.canon.test.typeCheck.AllTheTypes.InlineObjectField.NestedObjectField;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;



@SuppressWarnings("javadoc")
public class TestAllTheTypes
{
  ModelRegistry modelRegistry_ = new ModelRegistry.Builder().withFactories(TypeCheckModel.FACTORIES).build();
  
  @Test
  public void testBuild() throws ParserResultException
  {
    String storedAllTheTypes = "{\n" + 
        "  \"_type\":\"com.symphony.oss.canon.test.typeCheck.AllTheTypes\",\n" + 
        "  \"_version\":\"1.0\",\n" + 
        "  \"collideField\":3.141,\n" + 
        "  \"doubleField\":32317006071310999832043959664664900000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001129490596633992954310000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009.86902225,\n" + 
        "  \"enumsField\":\"blue\",\n" + 
        "  \"inlineObjectField\":{\n" + 
        "    \"_type\":\"com.symphony.oss.canon.test.typeCheck.inlineObjectField\",\n" + 
        "    \"_version\":\"1.0\",\n" + 
        "    \"name\":\"InlineThing\",\n" + 
        "    \"nestedObjectField\":{\n" + 
        "      \"_type\":\"com.symphony.oss.canon.test.typeCheck.nestedObjectField\",\n" + 
        "      \"_version\":\"1.0\",\n" + 
        "      \"name\":\"NestedThing\",\n" + 
        "      \"value\":1\n" + 
        "    },\n" + 
        "    \"value\":10\n" + 
        "  },\n" + 
        "  \"int64Field\":123,\n" + 
        "  \"intField\":85070591730234615847396907784232501249\n" + 
        "}\n" + 
        "";
    
    
    String foo = "{\n" + 
        "  \"_type\":\"com.symphony.oss.canon.test.typeCheck.AllTheTypes\",\n" + 
        "  \"_version\":\"1.0\",\n" + 
        "  \"collideField\":3.141,\n" + 
        "  \"doubleField\":32317006071310999832043959664664900000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001129490596633992954310000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009.86902225,\n" + 
        "  \"enumsField\":\"blue\",\n" + 
        "  \"inlineObjectField\":{\n" + 
        "    \"_type\":\"com.symphony.oss.canon.test.typeCheck.inlineObjectField\",\n" + 
        "    \"_version\":\"1.0\",\n" + 
        "    \"name\":\"InlineThing\",\n" + 
        "    \"nestedObjectField\":{\n" + 
        "      \"_type\":\"com.symphony.oss.canon.test.typeCheck.nestedObjectField\",\n" + 
        "      \"_version\":\"1.0\",\n" + 
        "      \"name\":\"NestedThing\",\n" + 
        "      \"value\":1\n" + 
        "    },\n" + 
        "    \"value\":10\n" + 
        "  },\n" + 
        "  \"int64Field\":123,\n" + 
        "  \"intField\":85070591730234615847396907784232501249\n" + 
        "}\n";
    
    for(int i=0 ; i<storedAllTheTypes.length() ; i++)
    {
      System.out.println(storedAllTheTypes.charAt(i) + " " + foo.charAt(i) + " " + (storedAllTheTypes.charAt(i) == foo.charAt(i)));
    }
    
    BigInteger bigIntegerValue = BigInteger.valueOf(Long.MAX_VALUE).pow(2);
    BigDecimal bigDecimalValue = BigDecimal.valueOf(Double.MAX_VALUE).add(BigDecimal.valueOf(3.1415)).pow(2);
    
    NestedObjectField nestedObject = new AllTheTypes.InlineObjectField.NestedObjectField.Builder()
        .withName("NestedThing")
        .withValue(BigInteger.ONE)
        .build();
    
    InlineObjectField inlineObject = new AllTheTypes.InlineObjectField.Builder()
        .withName("InlineThing")
        .withValue(BigInteger.TEN)
        .withNestedObjectField(nestedObject)
        .build();
    
    AllTheTypes builtObject = new AllTheTypes.Builder()
        .withIntField(bigIntegerValue)
        .withCollideField(3.141f)
        .withDoubleField(bigDecimalValue)
        .withEnumsField(Colour.BLUE)
        .withInlineObjectField(inlineObject)
        .withInt64Field(123L)
        .build();
    
    if(!storedAllTheTypes.equals(builtObject.toString()))
      org.junit.Assert.fail("Expected serialized form to be \"" + storedAllTheTypes + "\" but received \"" + builtObject.toString() + "\"");

    AllTheTypes deserialisedObject = AllTheTypes.FACTORY.newInstance(storedAllTheTypes, modelRegistry_);
    
    assertEquals(deserialisedObject, builtObject);
  }
}