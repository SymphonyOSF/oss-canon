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

package com.symphony.oss.canon2.test.oneOf;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.symphony.oss.canon.json.ParserResultException;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;

@SuppressWarnings("javadoc")
public class TestOneOfObject
{
  ModelRegistry modelRegistry_ = new ModelRegistry.Builder().withFactories(OneOf_Model.FACTORIES).build();
  
  @Test
  public void testObject() throws ParserResultException
  {
    String objectJson = "{\n" + 
        "  \"_type\":\"com.symphony.oss.canon2.test.oneOf.$1\",\n" + 
        "  \"_version\":\"1.0\",\n" + 
        "  \"name\":\"My name is \\\"Gladiator\\\"\"\n" + 
        "}\n";
    String oneOfObjectJson = "{\n" + 
        "  \"_type\":\"com.symphony.oss.canon2.test.oneOf.OneOfObject\",\n" + 
        "  \"_version\":\"1.0\",\n" + 
        "  \"objectOrString\":{\n" + 
        "    \"_type\":\"com.symphony.oss.canon2.test.oneOf.$1\",\n" + 
        "    \"_version\":\"1.0\",\n" + 
        "    \"name\":\"My name is \\\"Gladiator\\\"\"\n" + 
        "  }\n" + 
        "}\n";
    
    ObjectOrString.$1 object = new ObjectOrString.$1.Builder()
        .withName("My name is \"Gladiator\"")
        .build();
    
    assertEquals(objectJson, object.toString());

    
    ObjectOrString objectOrString = new ObjectOrString.Builder()
        .with$1(object)
        .build();
    
    OneOfObject oneOfObject = new OneOfObject.Builder()
        .withObjectOrString(objectOrString)
        .build();
    
    assertEquals(oneOfObjectJson, oneOfObject.toString());
    
    OneOfObject parsedOneOfObject = OneOfObject.FACTORY.newInstance(oneOfObjectJson, modelRegistry_);

    assertEquals(oneOfObjectJson, parsedOneOfObject.toString());
    assertEquals(object, parsedOneOfObject.getObjectOrString().get$1());
    assertEquals(null, parsedOneOfObject.getObjectOrString().get$2());
  }
  

  @Test
  public void testString() throws ParserResultException
  {
    String oneOfObjectJson = "{\n" + 
        "  \"_type\":\"com.symphony.oss.canon2.test.oneOf.OneOfObject\",\n" + 
        "  \"_version\":\"1.0\",\n" + 
        "  \"objectOrString\":\"This is a string\"\n" + 
        "}\n";
    String string = "This is a string";
    
    ObjectOrString objectOrString = new ObjectOrString.Builder()
        .with$2(string)
        .build();
    
    OneOfObject oneOfObject = new OneOfObject.Builder()
        .withObjectOrString(objectOrString)
        .build();
    
    assertEquals(oneOfObjectJson, oneOfObject.toString());
    
    OneOfObject parsedOneOfObject = OneOfObject.FACTORY.newInstance(oneOfObjectJson, modelRegistry_);

    assertEquals(oneOfObjectJson, parsedOneOfObject.toString());
    assertEquals(null, parsedOneOfObject.getObjectOrString().get$1());
    assertEquals(string, parsedOneOfObject.getObjectOrString().get$2());
  }
  
}
