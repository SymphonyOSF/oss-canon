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
 * Base implementation of ISchemaTemplateModel.
 * 
 * @author Bruce Skingle
 *
 */
public abstract class ObjectSchemaTemplateModel<
T extends ITemplateModel<T,M,S>,
M extends IOpenApiTemplateModel<T,M,S>,
S extends ISchemaTemplateModel<T,M,S>,
F extends IFieldTemplateModel<T,M,S>
>
  extends SchemaTemplateModel<T,M,S>
  implements IObjectSchemaTemplateModel<T,M,S,F>
{
  public ObjectSchemaTemplateModel(String name, M model, String... templates)
  {
    super(name, model, templates);
  }

  @Override
  public SchemaType getSchemaType()
  {
    return SchemaType.OBJECT;
  }
}
