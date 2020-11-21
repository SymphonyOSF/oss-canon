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

import com.symphony.oss.canon2.core.ResolvedProperty;

/**
 * Template model object for an object field.
 * 
 * @author Bruce Skingle
 * 
 * @param <T> The concrete type of ITemplateModel
 * @param <M> The concrete type of IOpenApiTemplateModel
 * @param <S> The concrete type of ISchemaTemplateModel
 *
 */
public abstract class FieldTemplateModel<
T extends ITemplateModel<T,M,S>,
M extends IOpenApiTemplateModel<T,M,S>,
S extends ISchemaTemplateModel<T,M,S>>
  extends TemplateModel<T,M,S>
  implements IFieldTemplateModel<T,M,S>
{
  private final S typeSchema_;
  private final boolean required_;
  
  /**
   * Constructor.
   * 
   * @param generatorContext  Contains the source context for error reporting.
   * @param identifier        The identifier used for this entity in generated code.
   * @param resolvedProperty  The model element representing the field.
   * @param model             The IOpenApiTemplateModel to which this entity belongs.
   * @param typeSchema        The template model for the type of this field.
   * @param required          True if this field is required by it's object.
   * @param templates         The list of templates to be called for this model.
   * 
   * The reason we have name and identifier is that the name may be valid in the JSON input spec but a reserved word in the
   * target generated language.
   */
  public FieldTemplateModel(CanonGenerator<T,M,S,?,?,?,?,?>.AbstractContext generatorContext, String identifier, ResolvedProperty resolvedProperty, M model,
      S typeSchema, boolean required, List<String> templates)
  {
    super(generatorContext, resolvedProperty.getName(), identifier, resolvedProperty.getResolvedSchema(), model, templates);
    
    typeSchema_ = typeSchema;
    required_ = required;
  }

  @Override
  public S getTypeSchema()
  {
    return typeSchema_;
  }

  /**
   * Return True if this field is required by it's object.
   * 
   * @return True if this field is required by it's object.
   */
  public boolean getRequired()
  {
    return required_;
  }
  
}
