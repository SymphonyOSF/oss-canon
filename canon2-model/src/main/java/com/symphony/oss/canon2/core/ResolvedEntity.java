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

import java.util.LinkedList;
import java.util.List;

import com.symphony.oss.canon.json.ParserException;
import com.symphony.oss.canon.json.model.IJsonDomNodeProvider;
import com.symphony.oss.commons.fluent.BaseAbstractBuilder;

public abstract class ResolvedEntity implements IJsonDomNodeProvider
{
  private final String                name_;
  private final List<ParserException> errors_;
  
  ResolvedEntity(AbstractBuilder<?,?> builder)
  {
    name_    = builder.name_;
    errors_  = builder.errors_;
  }
  
  public abstract static class AbstractBuilder<T extends AbstractBuilder<T, B>, B extends ResolvedEntity> extends BaseAbstractBuilder<T,B>
  {
    private String                      name_;
    private final List<ParserException> errors_ = new LinkedList<>();
    
    AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    abstract boolean isBuilt();

    public T withName(String name)
    {
      if(isBuilt())
        throw new IllegalStateException("SingletonBuilder has already been built");
      
      name_ = name;
      
      return self();
    }
    
    public T withError(ParserException error)
    {
      if(isBuilt())
        throw new IllegalStateException("SingletonBuilder has already been built");
      
      errors_.add(error);
      
      return self();
    }
  }
  
  public abstract ResolvedOpenApiObject getResolvedOpenApiObject();

  public void validate(SourceContext context)
  {
    for(ParserException e : errors_)
      context.error(e);
  }

  public String getName()
  {
    return name_;
  }
}
