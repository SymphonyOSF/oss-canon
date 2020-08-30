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

public abstract class GeneratorModelContext<S extends ISchemaTemplateModel<S>> implements IGeneratorModelContext<S>
{
  private final ICanonGenerator generator_;
  private final IModelContext sourceContext_;
  private final IPathNameConstructor templatePathBuilder_;
  private final IPathNameConstructor proformaPathBuilder_;
  
  public GeneratorModelContext(ICanonGenerator generator, IModelContext context, IPathNameConstructor templatePathBuilder, IPathNameConstructor proformaPathBuilder)
  {
    generator_ = generator;
    sourceContext_ = context;
    templatePathBuilder_ = templatePathBuilder;
    proformaPathBuilder_ = proformaPathBuilder;
  }
  
  @Override
  public ICanonGenerator getGenerator()
  {
    return generator_;
  }

  @Override
  public IModelContext getSourceContext()
  {
    return sourceContext_;
  }

  @Override
  public IPathNameConstructor getPathBuilder(TemplateType templateType)
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
