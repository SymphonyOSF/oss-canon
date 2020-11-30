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

/**
 * A resolved object or array schema.
 * 
 * @author Bruce Skingle
 *
 */
public abstract class ResolvedObjectOrArraySchema<S extends ISchemaInstance> extends ResolvedSchema<S>
{
  private final ResolvedSchemasObject.SingletonBuilder innerClassesBuilder_;
  
  ResolvedObjectOrArraySchema(AbstractBuilder<S,?,?> builder)
  {
    super(builder);

    innerClassesBuilder_                  = builder.innerClassesBuilder_;
  }
  
  public abstract static class AbstractBuilder<S extends ISchemaInstance, T extends AbstractBuilder<S,T,B>, B extends ResolvedObjectOrArraySchema<S>> extends ResolvedSchema.AbstractBuilder<S,T,B>
  {
    private ResolvedSchemasObject.SingletonBuilder innerClassesBuilder_;
    
    AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    public T withInnerClasses(ResolvedSchemasObject.SingletonBuilder innerClassesBuilder)
    {
      if(isBuilt())
        throw new IllegalStateException("SingletonBuilder has already been built");
      
      innerClassesBuilder_ = innerClassesBuilder;
      
      return self();
    }
  }

  public ResolvedSchemasObject getInnerClasses()
  {
    if(innerClassesBuilder_ == null)
      return null;
    
    return innerClassesBuilder_.build();
  }
}
