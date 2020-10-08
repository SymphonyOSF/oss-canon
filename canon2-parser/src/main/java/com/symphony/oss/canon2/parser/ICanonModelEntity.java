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
 *		Template name		   proforma/java/Model/I_ModelEntity.java.ftl
 *		Template version	   1.0
 *  At                  2020-08-21 14:27:27 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.parser;

import com.symphony.oss.canon2.model.GenerationException;

/**
 * Base interface for Canon model entities.
 */
public interface ICanonModelEntity
{
  /**
   * Get the model entity at the given path.
   * 
   * @param parts The path as an array of element names.
   * @param index The index into the array of the current level of the overall path.
   * 
   * @return The model element at the given path.
   * 
   * @throws GenerationException if the given path is invalid.
   */
  default ICanonModelEntity get(String[] parts, int index) throws GenerationException
  {
    throw new GenerationException("No path element " + parts[index]);
  }
  
  /**
   * Get the line and column in the source document of this element's declaration.
   * 
   * @return The line and column in the source document of this element's declaration.
   */
  default String getSourceLocation()
  {
    return "unknown source location";
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Model/I_ModelEntity.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */