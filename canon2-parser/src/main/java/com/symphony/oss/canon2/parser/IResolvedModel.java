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
 *  At                  2020-08-27 15:32:15 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.parser;

import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableMap;
import com.symphony.oss.canon2.parser.model.IResolvedModelEntity;

/**
 * Facade for Object ObjectSchema(ResolvedModel)
 *
 * An OpenApi model with all references resolved.
 * Generated from ObjectSchema(ResolvedModel) at #/components/schemas/ResolvedModel
 */
@Immutable
public interface IResolvedModel
  extends IOpenApiObject, IResolvedModelEntity
{

  <
  T extends ITemplateModel<T,M,S>,
  M extends IOpenApiTemplateModel<T,M,S>,
  S extends ISchemaTemplateModel<T,M,S>,
  O extends IObjectSchemaTemplateModel<T,M,S,F>,
  A extends IArraySchemaTemplateModel<T,M,S>,
  P extends IPrimitiveSchemaTemplateModel<T,M,S>,
  F extends IFieldTemplateModel<T,M,S>> M generate(IGeneratorModelContext<T,M,S,O,A,P,F> modelContext) throws GenerationException;

  ImmutableMap<String, IResolvedSchema> getResolvedSchemas();
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/I_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */