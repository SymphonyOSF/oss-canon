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
 *
 *----------------------------------------------------------------------------------------------------
 * Proforma generated from
 *		Template groupId		 org.symphonyoss.s2.canon
 *           artifactId canon-template-java
 *		Template name		   proforma/java/Object/I_.java.ftl
 *		Template version	   1.0
 *  At                  2020-08-21 14:27:27 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.parser;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon2.parser.model.ISchemaEntity;

/**
 * Facade for Object ObjectSchema(Schema)
 *
 * A data type definition.
 * Generated from ObjectSchema(Schema) at #/components/schemas/Schema
 */
@Immutable
public interface ISchema
  extends ISchemaEntity
{

  IResolvedSchema resolve(IOpenApiObject openApiObject, GenerationContext generationContext);

  void validate(GenerationContext generationContext);
  
  ISchema getItemsSchema();

  IReferenceObject getItemsReference();

  void fetchReferences(GenerationContext generationContext) throws GenerationException;

//  ImmutableMap<String, ISchema> getFields();
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/I_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */