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

import java.util.Collection;

/**
 * Template model for primitive types number, integer, boolean, string.
 * 
 * @author Bruce Skingle
 *
 */
public interface IPrimitiveSchemaTemplateModel<S extends ISchemaTemplateModel<S>> extends ISchemaTemplateModel<S>
{
  @Override
  default Collection<? extends ITemplateModel> getChildren()
  {
    return EMPTY_CHILDREN;
  }

  @Override
  default SchemaType getSchemaType()
  {
    return SchemaType.PRIMITIVE;
  }
}
