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

import java.util.Collection;

import com.symphony.oss.canon.json.model.IJsonDomNodeProvider;
import com.symphony.oss.canon2.core.SourceContext;

/**
 * Super-interface for all template model objects.
 * 
 * These are the objects passed to Freemarker during template expansion.
 * 
 * This interface represents the required contract for any implementation of a template model.
 * The class TemplateModel in this package provides additional methods which may be useful to
 * generator implementations and it is recommended that generator implementors sub-class that
 * class rather than implementing this interface directly, however, if for whatever reason an
 * alternative approach is chosen, this interface represents the minimum contract which the
 * generation processor requires to work.
 * 
 * @author Bruce Skingle
 *
 * @param <T> Concrete implementation of ITemplateModel.
 * @param <M> Concrete implementation of IOpenApiTemplateModel.
 * @param <S> Concrete implementation of ISchemaTemplateModel.
 */
public interface ITemplateModel<
T extends ITemplateModel<T,M,S>,
M extends IOpenApiTemplateModel<T,M,S>,
S extends ISchemaTemplateModel<T,M,S>>
extends IJsonDomNodeProvider
{
  /**
   * Return the name of this model entity as written in the input spec.
   * 
   * @return The name of this model entity as written in the input spec.
   */
  String getName();
  
  /**
   * Return the name of this model entity as used in generated code.
   * 
   * @return The name of this model entity as used in generated code.
   */
  String getIdentifier();

  /**
   * Return the list of template names which should be run against this model.
   * 
   * @return The list of template names which should be run against this model.
   */
  Collection<String> getTemplates();

  /**
   * Return the OpenApiObject template model.
   * 
   * @return The OpenApiObject template model.
   */
  M getModel();
  
  /**
   * Return all child templates.
   * 
   * This method is called to traverse all templates in the final stage of code generation.
   * 
   * @return all child templates.
   */
  Collection<T> getChildren();
  

  /**
   * This method is called after the template generation is complete.
   * 
   * Any errors in the configuration of the template (in particular names which are invalid as
   * identifiers in the target generated language) should be reported here by calling
   * <pre>
   * 
   * sourceContext.error(new ParserErrorException(message), context));
   * 
   * </pre>
   * It may be helpful to use the NameCollisionDetector class to detect names which are duplicated after
   * all (target generation language specific) name mapping has been done by calling
   * <pre>
   * 
   * new NameCollisionDetector(getFields()).logCollisions(sourceContext, this);
   * new NameCollisionDetector(getInnerClasses()).logCollisions(sourceContext, this);
   *
   * </pre>
   * 
   * @param sourceContext The source context for the generated model to which errors may be reported.
   */
  void validate(SourceContext sourceContext);
}
