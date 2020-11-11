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

package com.symphony.oss.canon2.core;

import com.symphony.oss.canon2.model.ISchemaInstance;
import com.symphony.oss.commons.fault.FaultAccumulator;

public class ResolvedCompositeSchema<S extends ISchemaInstance> extends ResolvedObjectOrArraySchema<S>
{
  private final ResolvedCompositeSchema.AbstractBuilder<S,?,?>     resolvedExtendsBuilder_;
  
  ResolvedCompositeSchema(AbstractBuilder<S,?,?> builder)
  {
    super(builder);

    resolvedExtendsBuilder_               = builder.resolvedExtendsBuilder_;
  }
  
  abstract static class AbstractBuilder<S extends ISchemaInstance, T extends AbstractBuilder<S,T,B>, B extends ResolvedCompositeSchema<S>> extends ResolvedObjectOrArraySchema.AbstractBuilder<S,T,B>
  {
    private ResolvedCompositeSchema.AbstractBuilder<S,?,?>     resolvedExtendsBuilder_;
    
    AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    public T withResolvedExtends(ResolvedCompositeSchema.AbstractBuilder<S,?,?> resolvedExtendsBuilder)
    {
      if(isBuilt())
        throw new IllegalStateException("SingletonBuilder has already been built");
      
      resolvedExtendsBuilder_ = resolvedExtendsBuilder;
      
      return self();
    }

    @Override
    protected void validate(FaultAccumulator faultAccumulator)
    {
      super.validate(faultAccumulator);
      
//      if(getSchema().getDiscriminator() != null)
//      {
//        String          propertyName = getSchema().getDiscriminator().getPropertyName();
//        ResolvedSchema  property = getResolvedProperties().get(propertyName);
//        
//        if(property == null)
//        {
//          withE
//        }
//      }
    }
  }

  public ResolvedCompositeSchema<?> getResolvedExtends()
  {
    if(resolvedExtendsBuilder_ == null)
      return null;
    
    return resolvedExtendsBuilder_.build();
  }
}
