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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface IObjectSchemaTemplateModel<
  T extends ITemplateModel<T,M,S>,
  M extends IOpenApiTemplateModel<T,M,S>,
  S extends ISchemaTemplateModel<T,M,S>,
  F extends IFieldTemplateModel<T,M,S>
  >
    extends ISchemaTemplateModel<T,M,S>, ITemplateModelNameSpace
{
  /**
   * Set the schema of the super-class.
   * 
   * @param extendsSchema the schema of the super-class.
   */
  void setExtends(S extendsSchema);
  
  void addField(String name, F field);
  void addInnerClass(String name, S innerClass);
  void setAdditionalProperties(S additionalProperties);
  void setAdditionalPropertiesAllowed(boolean additionalPropertiesAllowed);
  
  /**
   * Return this object as an ISchemaTemplateModel.
   * 
   * Implementations of this class must also be a subclass of the implementation of ISchemaTemplateModel
   * but the Java type system is unable to represent that in a parameterised type declaration.
   * 
   * This method must either cast the existing subject as the required type or return a view
   * onto the subject, it MUST NOT make a copy.
   * 
   * @return this object as an ITemplateModel.
   */
  S asSchemaTemplateModel();
  
  Collection<F> getFields();
  
  @Override
  default Collection<T> getChildren()
  {
    Collection<F> fields = getFields();
    
    List<T> result = new ArrayList<>(fields.size());
    
    for(F field : fields)
      result.add(field.asTemplateModel());
    
    return result;
  }

//  @Override
//  default SchemaType getSchemaType()
//  {
//    return SchemaType.OBJECT;
//  }
}
