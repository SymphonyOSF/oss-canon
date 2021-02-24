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

import java.time.Instant;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.symphony.oss.canon.json.JsonParser;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;


@SuppressWarnings("javadoc")
public class TestVersionedObject
{
  ModelRegistry modelRegistry_ = new ModelRegistry.Builder().withFactories(TypeCheckModel.FACTORIES).build();
  
  @Test
  public void testBuild()
  {
    String storedVersionedObject = "{\n" + 
        "  \"_type\":\"com.symphony.oss.canon.test.typeCheck.VersionedObject\",\n" + 
        "  \"_version\":\"1.0\",\n" + 
        "  \"instant1\":\"1970-01-01T00:00:00Z\",\n" + 
        "  \"instant2\":\"+1000000000-12-31T23:59:59.999999999Z\"\n" + 
        "}\n";
        
    VersionedObject builtObject = new VersionedObject.Builder()
        .withInstant1(Instant.EPOCH)
        .withInstant2(Instant.MAX)
        .build();
    
    assertEquals(storedVersionedObject, builtObject.toString());
    
    
    VersionedObject deserialisedObject = VersionedObject.FACTORY.newInstance(JsonParser.parseObject(storedVersionedObject), modelRegistry_);
    
    assertEquals(deserialisedObject, builtObject);
  }
}