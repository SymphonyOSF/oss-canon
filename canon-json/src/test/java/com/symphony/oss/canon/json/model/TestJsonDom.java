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

import org.junit.Assert;
import org.junit.Test;


public class TestJsonDom
{
  @Test
  public void testEmptyObject()
  {
    JsonObject emptyObject = new JsonObject.Builder()
      .build();
    
    test("{}\n", emptyObject.toString());
  }
  
  @Test
  public void testBuiltObject()
  {
    JsonObject builtObject = new JsonObject.Builder()
        .with("Bruce", "Skingle")
        .with("Mike", "Harmon")
        .with("MauritzioVeryLongNameDude", "Green")
        .build();
    
//    JsonArray builtArray = new JsonArray.Builder()
//        .with(builtObject)
//        .build();
    
    test("{\n" + 
        "  \"Bruce\":\"Skingle\",\n" + 
        "  \"MauritzioVeryLongNameDude\":\"Green\",\n" + 
        "  \"Mike\":\"Harmon\"\n" + 
        "}\n", builtObject.toString());
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