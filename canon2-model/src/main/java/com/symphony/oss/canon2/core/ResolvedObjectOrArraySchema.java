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

/**
 * A resolved object or array schema.
 * 
 * @author Bruce Skingle
 *
 */
public class ResolvedObjectOrArraySchema extends ResolvedSchema
{
  private final ResolvedPropertiesObject.SingletonBuilder innerClassesBuilder_;
  
  ResolvedObjectOrArraySchema(AbstractBuilder<?,?> builder)
  {
    super(builder);

    innerClassesBuilder_                  = builder.innerClassesBuilder_;
  }
  
  abstract static class AbstractBuilder<T extends AbstractBuilder<T, B>, B extends ResolvedObjectOrArraySchema> extends ResolvedSchema.AbstractBuilder<T,B>
  {
    private ResolvedPropertiesObject.SingletonBuilder innerClassesBuilder_;
    
    AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    public T withInnerClasses(ResolvedPropertiesObject.SingletonBuilder innerClassesBuilder)
    {
      if(isBuilt())
        throw new IllegalStateException("SingletonBuilder has already been built");
      
      innerClassesBuilder_ = innerClassesBuilder;
      
      return self();
    }
  }

  public ResolvedPropertiesObject getInnerClasses()
  {
    if(innerClassesBuilder_ == null)
      return null;
    
    return innerClassesBuilder_.build();
  }
}
