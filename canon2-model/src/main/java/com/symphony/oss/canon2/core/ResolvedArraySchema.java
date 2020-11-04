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

public class ResolvedArraySchema extends ResolvedObjectOrArraySchema
{
  private final ResolvedSchema.AbstractBuilder<?,?> resolvedItemsBuilder_;

  ResolvedArraySchema(AbstractBuilder<?,?> builder)
  {
    super(builder);

    resolvedItemsBuilder_                 = builder.resolvedItemsBuilder_;
  }
  
  public abstract static class AbstractBuilder<T extends AbstractBuilder<T, B>, B extends ResolvedArraySchema> extends ResolvedObjectOrArraySchema.AbstractBuilder<T,B>
  {
    ResolvedSchema.AbstractBuilder<?,?>          resolvedItemsBuilder_;
    
    AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    public T withResolvedItems(ResolvedSchema.AbstractBuilder<?,?> resolvedItemsBuilder)
    {
      if(isBuilt())
        throw new IllegalStateException("SingletonBuilder has already been built");
      
      resolvedItemsBuilder_ = resolvedItemsBuilder;
      
      return self();
    }
  }
  
  public static class SingletonBuilder extends AbstractBuilder<SingletonBuilder, ResolvedArraySchema>
  { 
    ResolvedArraySchema built_;
    
    /**
     * Constructor.
     */
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
    protected ResolvedArraySchema construct()
    {
      if(built_ == null)
      {
        if(resolvedItemsBuilder_ == null)
          throw new IllegalStateException("resolvedItemsBuilder is required");
        
        built_ = new ResolvedArraySchema(this);
      }
      
      return built_;
    }
    
  }

  public ResolvedSchema getResolvedItems()
  {
    if(resolvedItemsBuilder_ == null)
      return null;
    
    return resolvedItemsBuilder_.build();
  }
}
