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
public class TestSchema
{
  ModelRegistry modelRegistry_ = new ModelRegistry.Builder().withFactories(OneOfModel.FACTORIES).build();
  
  @Test
  public void testBuild() throws ParserResultException
  {
    String schemaJson = "{\n" + 
        "  \"_type\":\"com.symphony.oss.canon2.test.oneOf.Schema\",\n" + 
        "  \"_version\":\"1.0\",\n" + 
        "  \"format\":\"int64\",\n" + 
        "  \"type\":\"integer\"\n" + 
        "}\n";

    String refJson = "{\n" + 
        "  \"$ref\":\"#/components/schemas/Dog\",\n" + 
        "  \"_type\":\"com.symphony.oss.canon2.test.oneOf.ReferenceObject\",\n" + 
        "  \"_version\":\"1.0\"\n" + 
        "}\n";
    
    Schema schema = new Schema.Builder()
        .withType("integer")
        .withFormat("int64")
        .build();
    
    assertEquals(schemaJson, schema.toString());
    
    ReferenceObject ref = new ReferenceObject.Builder()
        .with$ref("#/components/schemas/Dog")
        .build();
    
    assertEquals(refJson, ref.toString());
    
    SchemaOrRef schemaOrRef = new SchemaOrRef.Builder()
        .withSchema(schema)
        .build();
    
    assertEquals(schemaJson, schemaOrRef.toString());
    assertEquals(schema, schemaOrRef.getSchema());
    assertEquals(null, schemaOrRef.getReferenceObject());
    

    
    SchemaOrRef schemaOrRef2 = new SchemaOrRef.Builder()
        .withReferenceObject(ref)
        .build();
    
    assertEquals(refJson, schemaOrRef2.toString());
    assertEquals(null, schemaOrRef2.getSchema());
    assertEquals(ref, schemaOrRef2.getReferenceObject());

    SchemaOrRef parsedSchemaOrRef = SchemaOrRef.FACTORY.newInstance(schemaJson, modelRegistry_); //modelRegistry_.parseOne(catJson);
    
    assertEquals(schemaJson, parsedSchemaOrRef.toString());
    assertEquals(schema, parsedSchemaOrRef.getSchema());
    assertEquals(null, parsedSchemaOrRef.getReferenceObject());
    
    SchemaOrRef parsedSchemaOrRef2 = SchemaOrRef.FACTORY.newInstance(refJson, modelRegistry_); //modelRegistry_.parseOne(catJson);
    

    assertEquals(refJson, parsedSchemaOrRef2.toString());
    assertEquals(null, parsedSchemaOrRef2.getSchema());
    assertEquals(ref, parsedSchemaOrRef2.getReferenceObject());
  }
  
}
