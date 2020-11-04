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

import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon2.model.OpenApiObject;

public class ResolvedOpenApiObject extends ResolvedEntity
{
  private final OpenApiObject            model_;
  private final ResolvedComponentsObject components_;
  private final boolean                  referencedModel_;
  
  private ResolvedOpenApiObject(AbstractBuilder<?, ?> builder)
  {
    super(builder);
    
    model_            = builder.model_;
    referencedModel_  = builder.referencedModel_;
    components_       = builder.componentsBuilder_.build();
  }
  
  public abstract static class AbstractBuilder<T extends AbstractBuilder<T, B>, B extends ResolvedOpenApiObject> extends ResolvedEntity.AbstractBuilder<T,B>
  {
    ResolvedComponentsObject.SingletonBuilder componentsBuilder_;
    ResolvedOpenApiObject                     built_;
    OpenApiObject                             model_;
    boolean                                   referencedModel_;
    
    AbstractBuilder(Class<T> type)
    {
      super(type);
    }

    public synchronized T withComponents(ResolvedComponentsObject.SingletonBuilder schemasBuilder)
    {
      if(built_ != null)
        throw new IllegalStateException("SingletonBuilder has already been built");
      
      componentsBuilder_ = schemasBuilder;
      
      return self();
    }

    public T withModel(OpenApiObject model)
    {
      model_ = model;
      
      return self();
    }

    public T withReferencedModel(boolean referencedModel)
    {
      referencedModel_ = referencedModel;
      return self();
    }
  }
  
  public static class SingletonBuilder extends AbstractBuilder<SingletonBuilder, ResolvedOpenApiObject>
  {
    public SingletonBuilder()
    {
      super(SingletonBuilder.class);
    }

    @Override
    protected ResolvedOpenApiObject construct()
    {
      if(built_ == null)
        built_ = new ResolvedOpenApiObject(this);
      
      return built_;
    }

    @Override
    boolean isBuilt()
    {
      return built_ != null;
    }
  }
  
  @Override
  public JsonDomNode getJson()
  {
    return model_.getJson();
  }

  public OpenApiObject getModel()
  {
    return model_;
  }

  public ResolvedComponentsObject getComponents()
  {
    return components_;
  }
  
  public void validate(SourceContext context)
  {
    super.validate(context);
    
    if(getComponents() != null)
      getComponents().validate(context);
  }

  public ResolvedOpenApiObject getResolvedOpenApiObject()
  {
    return this;
  }

  public boolean isReferencedModel()
  {
    return referencedModel_;
  }
}
