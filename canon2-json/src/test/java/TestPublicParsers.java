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

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.symphony.oss.canon.json.JsonParser;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon.json.model.JsonObjectDom;
import com.symphony.oss.canon.json.model.JsonString;

@SuppressWarnings("javadoc")
public class TestPublicParsers
{
  @Test
  public void testParseJsonStringObject()
  {
    
    JsonObjectDom objectDom = (JsonObjectDom) new JsonParser.Builder()
        .withInput("{\"name\": \"pi\", \"value\": \"circle ratio\"}")
        .build()
        .parse();
    
    JsonObject object = objectDom.getObject();
    
    JsonDomNode nameElement = object.get("name");
    
    assertTrue(nameElement instanceof JsonString);
    
    JsonDomNode valueElement = object.get("value");
    assertTrue(valueElement instanceof JsonString);
    assertTrue("circle ratio".equals(((JsonString)valueElement).asString()));
  }
}

  
