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
public class TestObjectOrString
{
  ModelRegistry modelRegistry_ = new ModelRegistry.Builder().withFactories(OneOf_Model.FACTORIES).build();
  
  @Test
  public void testBuild() throws ParserResultException
  {
    String objectJson = "{\n" + 
        "  \"_type\":\"com.symphony.oss.canon2.test.oneOf.$1\",\n" + 
        "  \"_version\":\"1.0\",\n" + 
        "  \"name\":\"My name is \\\"Gladiator\\\"\"\n" + 
        "}\n";

    String string = "Hello World";
    String stringJson = "\"" + string + "\"\n";
    
    ObjectOrString.$1 object = new ObjectOrString.$1.Builder()
        .withName("My name is \"Gladiator\"")
        .build();
    
    assertEquals(objectJson, object.toString());
    
    
    
    ObjectOrString objectOrString = new ObjectOrString.Builder()
        .with$1(object)
        .build();
    
    assertEquals(objectJson, objectOrString.toString());
    assertEquals(object, objectOrString.get$1());
    assertEquals(null, objectOrString.get$2());
    

    
    ObjectOrString objectOrString2 = new ObjectOrString.Builder()
        .with$2(string)
        .build();
    
    assertEquals(stringJson, objectOrString2.toString());
    assertEquals(null, objectOrString2.get$1());
    assertEquals(string, objectOrString2.get$2());

    ObjectOrString parsedObjectOrString = ObjectOrString.FACTORY.newInstance(objectJson, modelRegistry_); //modelRegistry_.parseOne(catJson);
    
    assertEquals(objectJson, parsedObjectOrString.toString());
    assertEquals(object, parsedObjectOrString.get$1());
    assertEquals(null, parsedObjectOrString.get$2());

    // You can;t deserialise the string variant because a single string isn't a valid JSON dom.
  }
  
}
