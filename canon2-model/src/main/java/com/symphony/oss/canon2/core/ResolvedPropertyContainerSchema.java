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

import java.util.Map;

import javax.annotation.Nonnull;

import com.symphony.oss.canon2.model.ISchemaInstance;
import com.symphony.oss.commons.fault.FaultAccumulator;

public class ResolvedPropertyContainerSchema<S extends ISchemaInstance> extends ResolvedObjectOrArraySchema<S>
{
  private final ResolvedPropertiesObject.SingletonBuilder        resolvedPropertiesBuilder_;
  
  ResolvedPropertyContainerSchema(AbstractBuilder<S,?,?> builder)
  {
    super(builder);

    resolvedPropertiesBuilder_            = builder.resolvedPropertiesBuilder_;
  }
  
  abstract static class AbstractBuilder<S extends ISchemaInstance, T extends AbstractBuilder<S,T,B>, B extends ResolvedPropertyContainerSchema<S>> extends ResolvedObjectOrArraySchema.AbstractBuilder<S,T,B>
  {
    private ResolvedPropertiesObject.SingletonBuilder        resolvedPropertiesBuilder_;

    AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    public T withResolvedProperties(ResolvedPropertiesObject.SingletonBuilder resolvedPropertiesBuilder)
    {
      if(isBuilt())
        throw new IllegalStateException("SingletonBuilder has already been built");
      
      resolvedPropertiesBuilder_ = resolvedPropertiesBuilder;
      
      return self();
    }

    @Override
    protected void validate(FaultAccumulator faultAccumulator)
    {
      super.validate(faultAccumulator);
      
      if(resolvedPropertiesBuilder_ == null)
        throw new IllegalStateException("resolvedPropertiesBuilder is required");
      
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
  
  

  public @Nonnull Map<String, ResolvedProperty> getResolvedProperties()
  {
    if(resolvedPropertiesBuilder_ == null)
      return null;
    
    return resolvedPropertiesBuilder_.build().getResolvedProperties();
  }
}
