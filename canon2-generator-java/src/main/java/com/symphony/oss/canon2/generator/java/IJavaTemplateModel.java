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

package com.symphony.oss.canon2.generator.java;

import java.util.Set;

import com.symphony.oss.canon2.parser.ITemplateModel;

public interface IJavaTemplateModel extends ITemplateModel<
IJavaTemplateModel,
JavaOpenApiTemplateModel,
JavaSchemaTemplateModel
>
{
  /**
   * Return the set of imports needed for this class.
   * 
   * @return the set of imports needed for this class.
   */
  Set<String> getImports();
  
  /**
   * Return the java type of this class.
   * 
   * @return the java type of this class.
   */
  String getType();
  
  /**
   * Return the name of this model entity in camelCase with a lower case initial letter.
   * 
   * @return The name of this model entity in camelCase with a lower case initial letter.
   */
  String getCamelName();

  /**
   * Return the name of this model entity in snake_case with a lower case initial letter.
   * 
   * @return The name of this model entity in snake_case with a lower case initial letter.
   */
  String getSnakeName();

  /**
   * Return the name of this model entity in CamelCase with an upper case initial letter.
   * 
   * @return The name of this model entity in CamelCase with an upper case initial letter.
   */
  String getCamelCapitalizedName();
  
  /**
   * Return the name of this model entity in Snake_case with an upper case initial letter.
   * 
   * @return The name of this model entity in Snake_case with an upper case initial letter.
   */
  String getSnakeCapitalizedName();
}
