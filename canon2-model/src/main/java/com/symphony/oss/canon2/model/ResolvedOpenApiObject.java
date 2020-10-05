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

package com.symphony.oss.canon2.model;

public class ResolvedOpenApiObject implements IResolvedEntity
{
  private final OpenApiObject model_;
  private final ResolvedComponentsObject components_ ;
  
  private ResolvedOpenApiObject(SingletonBuilder builder)
  {
    model_ = builder.model_;
    components_ = builder.componentsBuilder_.build();
  }
  
  public static class SingletonBuilder
  {
    ResolvedComponentsObject.SingletonBuilder componentsBuilder_;
    ResolvedOpenApiObject built_;
    OpenApiObject model_;
    
    public synchronized SingletonBuilder withComponents(ResolvedComponentsObject.SingletonBuilder schemasBuilder)
    {
      if(built_ != null)
        throw new IllegalStateException("SingletonBuilder has already been built");
      
      componentsBuilder_ = schemasBuilder;
      
      return this;
    }
    
    public synchronized ResolvedOpenApiObject build()
    {
      if(built_ == null)
        built_ = new ResolvedOpenApiObject(this);
      
      return built_;
    }

    public void withModel(OpenApiObject model)
    {
      model_ = model;
    }
  }

  public OpenApiObject getModel()
  {
    return model_;
  }

  public ResolvedComponentsObject getComponents()
  {
    return components_;
  }
  
  public void validate(CanonModelContext modelContext)
  {
    if(getComponents() != null)
      getComponents().validate(modelContext);
  }

  @Override
  public ResolvedOpenApiObject getResolvedOpenApiObject()
  {
    return this;
  }
}
