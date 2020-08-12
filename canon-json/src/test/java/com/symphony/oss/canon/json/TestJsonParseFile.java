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

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.symphony.oss.canon.json.model.JsonDom;

@SuppressWarnings("javadoc")
public class TestJsonParseFile
{
  @Test
  public void testParseFile() throws IOException
  {
    try(InputStream in = getClass().getResourceAsStream("/oneOfEverything.json"))
    {
      System.err.println("in " + in);
    
      JsonParser parser = new JsonParser.Builder()
          .withCanonicalize(false)
          .withInput(in)
          .build();
      
      JsonDom dom = parser.parse();
      
      System.out.println(dom.toString());
      
      assertEquals(0, dom.getErrors().size());
    }
  }
}