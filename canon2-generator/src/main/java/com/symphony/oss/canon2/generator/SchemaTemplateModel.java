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

package com.symphony.oss.canon2.generator;

import java.util.List;

import com.symphony.oss.canon2.core.ResolvedSchema;
import com.symphony.oss.canon2.core.SchemaTemplateModelType;

/**
 * Base implementation of ISchemaTemplateModel.
 * 
 * @author Bruce Skingle
 * 
 * @param <T> The concrete type of ITemplateModel
 * @param <M> The concrete type of IOpenApiTemplateModel
 * @param <S> The concrete type of ISchemaTemplateModel
 *
 */
public abstract class SchemaTemplateModel<
T extends ITemplateModel<T,M,S>,
M extends IOpenApiTemplateModel<T,M,S>,
S extends ISchemaTemplateModel<T,M,S>
>
  extends TemplateModel<T,M,S>
  implements ISchemaTemplateModel<T,M,S>
{
  private final SchemaTemplateModelType schemaType_;
  
  /**
   * Constructor.
   * 
   * 
   * @param generatorContext  Contains the source context for error reporting. 
   * @param identifier        The identifier used for this entity in generated code.
   * @param resolvedSchema    The resolvedSchema object from the OpenApi model.
   * @param schemaType        The type of this schema.
   * @param model             The IOpenApiTemplateModel to which this entity belongs.
   * @param templates         The list of templates to be called for this model.
   */
  public SchemaTemplateModel(CanonGenerator<T,M,S,?,?,?,?,?>.AbstractContext generatorContext, 
      String identifier, ResolvedSchema<?> resolvedSchema, SchemaTemplateModelType schemaType, M model, List<String> templates)
  {
    super(generatorContext,
        resolvedSchema.isCanonPrefix() ? 
          generatorContext.getCanonIdString() + resolvedSchema.getName() : 
            resolvedSchema.getName(),
        resolvedSchema.isCanonPrefix() ? 
          generatorContext.getCanonIdString() + identifier :
            identifier,
        resolvedSchema, model, templates);
    
    schemaType_ = schemaType;
  }

  /**
   * Return the schema type.
   * 
   * @return the schema type.
   */
  public final SchemaTemplateModelType getSchemaType()
  {
    return schemaType_;
  }
}
