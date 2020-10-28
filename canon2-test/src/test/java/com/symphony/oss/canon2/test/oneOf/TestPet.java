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
import com.symphony.oss.canon2.runtime.java.Entity;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;

@SuppressWarnings("javadoc")
public class TestPet
{
  ModelRegistry modelRegistry_ = new ModelRegistry.Builder().withFactories(OneOfModel.FACTORIES).build();
  
  @Test
  public void testBuild() throws ParserResultException
  {
    String dogJson = "{\n" + 
        "  \"_type\":\"com.symphony.oss.canon2.test.oneOf.Dog\",\n" + 
        "  \"_version\":\"1.0\",\n" + 
        "  \"bark\":\"Loud\"\n" + 
        "}\n";

    String catJson = "{\n" + 
        "  \"_type\":\"com.symphony.oss.canon2.test.oneOf.Cat\",\n" + 
        "  \"_version\":\"1.0\",\n" + 
        "  \"hunts\":\"Mice\"\n" + 
        "}\n";
    
    String petJson = "{\n" + 
        "  \"Cat\":{\n" + 
        "    \"_type\":\"com.symphony.oss.canon2.test.oneOf.Cat\",\n" + 
        "    \"_version\":\"1.0\",\n" + 
        "    \"hunts\":\"Mice\"\n" + 
        "  },\n" + 
        "  \"_type\":\"com.symphony.oss.canon2.test.oneOf.Pet\",\n" + 
        "  \"_version\":\"1.0\"\n" + 
        "}\n";
    
    Dog dog = new Dog.Builder()
        .withBark("Loud")
        .build();
    
    assertEquals(dogJson, dog.toString());
    
    Cat cat = new Cat.Builder()
        .withHunts("Mice")
        .build();
    
    assertEquals(catJson, cat.toString());
    
    Pet pet = new Pet.Builder()
        .withCat(cat)
        .build();
    
    assertEquals(petJson, pet.toString());
    assertEquals(cat, pet.getCat());
    assertEquals(null, pet.getDog());
    
    Entity parsedDog = modelRegistry_.parseOne(dogJson);
    
    assertEquals(parsedDog, dog);
    
    Entity parsedCat = modelRegistry_.parseOne(catJson);
    
    assertEquals(parsedCat, cat);
    
    Entity parsedPet = modelRegistry_.parseOne(petJson);
    
    assertEquals(parsedPet, pet);
  }
  
  
}