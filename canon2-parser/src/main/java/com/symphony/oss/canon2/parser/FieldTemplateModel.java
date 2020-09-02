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

package com.symphony.oss.canon2.parser;

/**
 * Template model object for an object field.
 * 
 * @author Bruce Skingle
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
  
  public FieldTemplateModel(String name, M model,
      S typeSchema,
      String[] temaplates)
  {
    super(name, model, temaplates);
    
    typeSchema_ = typeSchema;
  }

  @Override
  public S getTypeSchema()
  {
    return typeSchema_;
  }
  
}
