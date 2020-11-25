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
import java.util.Map.Entry;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.symphony.oss.canon.json.ParserResultException;
import com.symphony.oss.canon2.runtime.java.Entity;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;


@SuppressWarnings("javadoc")
public class TestFundamentalObject
{
  ModelRegistry modelRegistry_ = new ModelRegistry.Builder().withFactories(TypeCheck_Model.FACTORIES).build();
  
  @Test
  public void testBuild() throws ParserResultException
  {
    String storedFundamentalObject = "{\n" + 
        "  \"_type\":\"com.symphony.oss.canon.test.typeCheck.FundamentalObject\",\n" + 
        "  \"_version\":\"1.0\",\n" + 
        "  \"instant\":\"1970-01-01T00:00:00Z\",\n" + 
        "  \"instantList\":[\n" + 
        "    \"-1000000000-01-01T00:00:00Z\",\n" + 
        "    \"1970-01-01T00:00:00Z\",\n" + 
        "    \"+1000000000-12-31T23:59:59.999999999Z\"\n" + 
        "  ]\n" + 
        "}\n";
    
    List<Instant> instantList = new ImmutableList.Builder<Instant>()
        .add(Instant.MIN)
        .add(Instant.EPOCH)
        .add(Instant.MAX)
        .build();
    
    FundamentalObject builtObject = new FundamentalObject.Builder()
        .withInstant(Instant.EPOCH)
        .withInstantList(instantList)
        .build();
    
    assertEquals(storedFundamentalObject, builtObject.toString());
    
    
    FundamentalObject deserialisedObject = FundamentalObject.FACTORY.newInstance(storedFundamentalObject, modelRegistry_);
    
    assertEquals(deserialisedObject, builtObject);
  }
  
  @Test
  public void testDeserialize() throws ParserResultException
  {
    String storedFundamentalObject = "{\n" + 
        "  \"_type\":\"com.symphony.oss.canon.test.typeCheck.FundamentalObject\",\n" + 
        "  \"_version\":\"1.0\",\n" + 
        "  \"additionalOne\":2,\n" + 
        "  \"additionalTwo\":5,\n" + 
        "  \"instant\":\"1970-01-01T00:00:00Z\",\n" + 
        "  \"instantList\":[\n" + 
        "    \"-1000000000-01-01T00:00:00Z\",\n" + 
        "    \"1970-01-01T00:00:00Z\",\n" + 
        "    \"+1000000000-12-31T23:59:59.999999999Z\"\n" + 
        "  ]\n" + 
        "}\n";
    
    FundamentalObject deserialisedObject = FundamentalObject.FACTORY.newInstance(storedFundamentalObject, modelRegistry_);
    
//    for(Entry<String, IntTypedef> entry : deserialisedObject.canonGetAdditionalProperties().entrySet())
//    {
//      System.out.println(entry.getKey() + " -> " + entry.getValue());
//    }
//    
//    for(Entry<String, Entity> entry : deserialisedObject.canonGetUnknownProperties().entrySet())
//    {
//      System.out.println(entry.getKey() + " -> " + entry.getValue());
//    }

    assertEquals(2, deserialisedObject.canonGetAdditionalProperties().size());
    assertEquals(1, deserialisedObject.canonGetUnknownProperties().size());
  }
  
  @Test
  public void testAdditionalAttributes() throws ParserResultException
  {
    String storedFundamentalObject = "{\n" + 
        "  \"_type\":\"com.symphony.oss.canon.test.typeCheck.FundamentalObject\",\n" + 
        "  \"_version\":\"1.0\",\n" + 
        "  \"additionalOne\":2,\n" + 
        "  \"additionalTwo\":5,\n" + 
        "  \"instant\":\"1970-01-01T00:00:00Z\",\n" + 
        "  \"instantList\":[\n" + 
        "    \"-1000000000-01-01T00:00:00Z\",\n" + 
        "    \"1970-01-01T00:00:00Z\",\n" + 
        "    \"+1000000000-12-31T23:59:59.999999999Z\"\n" + 
        "  ]\n" + 
        "}\n";
    
    FundamentalObject deserialisedObject = FundamentalObject.FACTORY.newInstance(storedFundamentalObject, modelRegistry_);
    
    List<Instant> instantList = new ImmutableList.Builder<Instant>()
        .add(Instant.MIN)
        .add(Instant.EPOCH)
        .add(Instant.MAX)
        .build();
    
    FundamentalObject builtObject = new FundamentalObject.Builder()
        .withInstant(Instant.EPOCH)
        .withInstantList(instantList)
        .with("additionalOne", new IntTypedef(2))
        .with("additionalTwo", new IntTypedef(5))
        .build();
    
    assertEquals(storedFundamentalObject, builtObject.toString());

    assertEquals(deserialisedObject, builtObject);
  }
}