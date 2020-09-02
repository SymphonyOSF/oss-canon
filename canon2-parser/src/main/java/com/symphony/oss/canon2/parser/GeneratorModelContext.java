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

import com.symphony.oss.commons.fault.CodingFault;

public abstract class GeneratorModelContext<
T extends ITemplateModel<T,M,S,O,A,P>,
M extends IOpenApiTemplateModel<T,M,S,O,A,P>,
S extends ISchemaTemplateModel<T,M,S,O,A,P>,
O extends IObjectSchemaTemplateModel<T,M,S,O,A,P>,
A extends IArraySchemaTemplateModel<T,M,S,O,A,P>,
P extends IPrimitiveSchemaTemplateModel<T,M,S,O,A,P>>



//<M extends IOpenApiTemplateModel<S>, S extends ISchemaTemplateModel,
//  O extends IObjectSchemaTemplateModel<S>, A extends IArraySchemaTemplateModel<S>, P extends IPrimitiveSchemaTemplateModel<S>>
  implements IGeneratorModelContext<T,M,S,O,A,P>
{
  private final ICanonGenerator<T,M,S,O,A,P> generator_;
  private final IModelContext sourceContext_;
  private final IPathNameConstructor<T> templatePathBuilder_;
  private final IPathNameConstructor<T> proformaPathBuilder_;
  
  public GeneratorModelContext(ICanonGenerator<T,M,S,O,A,P> generator, IModelContext context,
      IPathNameConstructor<T> templatePathBuilder, IPathNameConstructor<T> proformaPathBuilder)
  {
    generator_ = generator;
    sourceContext_ = context;
    templatePathBuilder_ = templatePathBuilder;
    proformaPathBuilder_ = proformaPathBuilder;
  }
  
  @Override
  public ICanonGenerator<T,M,S,O,A,P> getGenerator()
  {
    return generator_;
  }

  @Override
  public IModelContext getSourceContext()
  {
    return sourceContext_;
  }

  @Override
  public IPathNameConstructor<T> getPathBuilder(TemplateType templateType)
  {
    switch(templateType)
    {
      case TEMPLATE:
        return templatePathBuilder_;
      
      case PROFORMA:
        return proformaPathBuilder_;
    }
    
    throw new CodingFault("Unexpected TemplateType " + templateType);
  }
}
