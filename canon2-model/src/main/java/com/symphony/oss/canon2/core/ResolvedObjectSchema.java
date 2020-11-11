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

import com.symphony.oss.canon2.model.ObjectSchema;
import com.symphony.oss.commons.fault.FaultAccumulator;

public class ResolvedObjectSchema extends ResolvedPropertyContainerSchema<ObjectSchema>
{
  private final ResolvedObjectSchema.SingletonBuilder     resolvedExtendsBuilder_;
  private final ResolvedObjectSchema.SingletonBuilder     resolvedAdditionalPropertiesBuilder_;
  private final boolean                                   additionalPropertiesAllowed_;
  
  ResolvedObjectSchema(AbstractBuilder<?,?> builder)
  {
    super(builder);

    resolvedExtendsBuilder_               = builder.resolvedExtendsBuilder_;
    resolvedAdditionalPropertiesBuilder_  = builder.resolvedAdditionalPropertiesBuilder_;
    additionalPropertiesAllowed_          = builder.additionalPropertiesAllowed_;
    
    
  }
  
  abstract static class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends ResolvedObjectSchema> extends ResolvedPropertyContainerSchema.AbstractBuilder<ObjectSchema,T,B>
  {
    private ResolvedObjectSchema.SingletonBuilder     resolvedExtendsBuilder_;
    private ResolvedObjectSchema.SingletonBuilder     resolvedAdditionalPropertiesBuilder_;
    private boolean                                   additionalPropertiesAllowed_;
    
    AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    public T withResolvedExtends(ResolvedObjectSchema.SingletonBuilder resolvedExtendsBuilder)
    {
      if(isBuilt())
        throw new IllegalStateException("SingletonBuilder has already been built");
      
      resolvedExtendsBuilder_ = resolvedExtendsBuilder;
      
      return self();
    }
    
    public T withResolvedAdditionalProperties(ResolvedObjectSchema.SingletonBuilder resolvedAdditionalPropertiesBuilder)
    {
      if(isBuilt())
        throw new IllegalStateException("SingletonBuilder has already been built");
      
      resolvedAdditionalPropertiesBuilder_ = resolvedAdditionalPropertiesBuilder;
      additionalPropertiesAllowed_ = true;
      
      return self();
    }
    
    public T withAdditionalPropertiesAllowed(boolean additionalPropertiesAllowed)
    {
      if(isBuilt())
        throw new IllegalStateException("SingletonBuilder has already been built");
      
      additionalPropertiesAllowed_ = additionalPropertiesAllowed;
      
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
  
  public static class SingletonBuilder extends AbstractBuilder<SingletonBuilder, ResolvedObjectSchema>
  { 
    ResolvedObjectSchema built_;
    
    public SingletonBuilder()
    {
      super(SingletonBuilder.class);
    }

    @Override
    boolean isBuilt()
    {
      return built_ != null;
    }

    @Override
    protected ResolvedObjectSchema construct()
    {
      if(built_ == null)
      {
        built_ = new ResolvedObjectSchema(this);
      }
      
      return built_;
    }
    
  }

  public ResolvedObjectSchema getResolvedExtends()
  {
    if(resolvedExtendsBuilder_ == null)
      return null;
    
    return resolvedExtendsBuilder_.build();
  }

  public ResolvedObjectSchema getResolvedAdditionalProperties()
  {
    if(resolvedAdditionalPropertiesBuilder_ == null)
      return null;
    
    return resolvedAdditionalPropertiesBuilder_.build();
  }

  public boolean isAdditionalPropertiesAllowed()
  {
    return additionalPropertiesAllowed_;
  }
}
