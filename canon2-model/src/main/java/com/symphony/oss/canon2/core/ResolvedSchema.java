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

import com.symphony.oss.canon2.model.Schema;

public class ResolvedSchema implements IResolvedEntity
{
  private final String                                    name_;
  private final String                                    uri_;
  private final Schema                                    schema_;
  private final boolean                                   generated_;
  private final ResolvedSchema.SingletonBuilder           resolvedItemsBuilder_;
  private final ResolvedSchema.SingletonBuilder           resolvedExtendsBuilder_;
  private final ResolvedPropertiesObject.SingletonBuilder resolvedPropertiesBuilder_;
  private final ResolvedPropertiesObject.SingletonBuilder innerClassesBuilder_;
  private final ResolvedOpenApiObject.SingletonBuilder    openApiObjectBuilder_; 
  
  private ResolvedSchema(SingletonBuilder builder)
  {
    name_                       = builder.name_;
    uri_                        = builder.uri_;
    schema_                     = builder.schema_;
    generated_                  = builder.generated_;
    resolvedItemsBuilder_       = builder.resolvedItemsBuilder_;
    resolvedExtendsBuilder_     = builder.resolvedExtendsBuilder_;
    resolvedPropertiesBuilder_  = builder.resolvedPropertiesBuilder_;
    innerClassesBuilder_        = builder.innerClassesBuilder_;
    openApiObjectBuilder_       = builder.openApiObjectBuilder_;
  }
  
  public static class SingletonBuilder
  {
    String                                    name_;
    String                                    uri_;
    Schema                                    schema_;
    boolean                                   generated_;
    ResolvedSchema.SingletonBuilder           resolvedItemsBuilder_;
    ResolvedSchema.SingletonBuilder           resolvedExtendsBuilder_;
    ResolvedPropertiesObject.SingletonBuilder resolvedPropertiesBuilder_;
    ResolvedPropertiesObject.SingletonBuilder innerClassesBuilder_;
    ResolvedOpenApiObject.SingletonBuilder    openApiObjectBuilder_; 
    ResolvedSchema                            built_;

    public SingletonBuilder()
    {
      System.out.println("ResolvedSchema.SingletonBuilder()");
    }
    
    public SingletonBuilder withName(String name)
    {
      if(built_ != null)
        throw new IllegalStateException("SingletonBuilder has already been built");
      
      name_ = name;
      
      return this;
    }
    
    public SingletonBuilder withUri(String uri)
    {
      if(built_ != null)
        throw new IllegalStateException("SingletonBuilder has already been built");
      
      uri_ = uri;
      
      return this;
    }
    
    public SingletonBuilder withResolvedOpenApiObject(ResolvedOpenApiObject.SingletonBuilder openApiObjectBuilder)
    {
      if(built_ != null)
        throw new IllegalStateException("SingletonBuilder has already been built");
      
      openApiObjectBuilder_ = openApiObjectBuilder;
      
      return this;
    }
    
    public SingletonBuilder withResolvedItems(ResolvedSchema.SingletonBuilder resolvedItemsBuilder)
    {
      if(built_ != null)
        throw new IllegalStateException("SingletonBuilder has already been built");
      
      resolvedItemsBuilder_ = resolvedItemsBuilder;
      
      return this;
    }
    
    public SingletonBuilder withResolvedExtends(ResolvedSchema.SingletonBuilder resolvedExtendsBuilder)
    {
      if(built_ != null)
        throw new IllegalStateException("SingletonBuilder has already been built");
      
      resolvedExtendsBuilder_ = resolvedExtendsBuilder;
      
      return this;
    }
    
    public SingletonBuilder withResolvedProperties(ResolvedPropertiesObject.SingletonBuilder resolvedPropertiesBuilder)
    {
      if(built_ != null)
        throw new IllegalStateException("SingletonBuilder has already been built");
      
      resolvedPropertiesBuilder_ = resolvedPropertiesBuilder;
      
      return this;
    }
    
    public SingletonBuilder withInnerClasses(ResolvedPropertiesObject.SingletonBuilder innerClassesBuilder)
    {
      if(built_ != null)
        throw new IllegalStateException("SingletonBuilder has already been built");
      
      innerClassesBuilder_ = innerClassesBuilder;
      
      return this;
    }
    
    public ResolvedSchema build()
    {
      System.err.println("ResolvedSchema build() " + this);
      if(built_ == null)
      {
        if(resolvedPropertiesBuilder_ == null)
          throw new IllegalStateException("resolvedPropertiesBuilder is required");
        
        built_ = new ResolvedSchema(this);
      }
      
      return built_;
    }

    public SingletonBuilder withSchema(Schema schema)
    {
      schema_ = schema;
      
      return this;
    }

    public SingletonBuilder withGenerated(boolean generated)
    {
      generated_ = generated;
      
      return this;
    }
  }

  public String getName()
  {
    return name_;
  }

  public String getUri()
  {
    return uri_;
  }

  public Schema getSchema()
  {
    return schema_;
  }

  public boolean isGenerated()
  {
    return generated_;
  }

  public ResolvedSchema getResolvedItems()
  {
    if(resolvedItemsBuilder_ == null)
      return null;
    
    return resolvedItemsBuilder_.build();
  }

  public ResolvedSchema getResolvedExtends()
  {
    if(resolvedExtendsBuilder_ == null)
      return null;
    
    return resolvedExtendsBuilder_.build();
  }

  public @Nonnull Map<String, ResolvedSchema> getResolvedProperties()
  {
    if(resolvedPropertiesBuilder_ == null)
      return null;
    
    return resolvedPropertiesBuilder_.build().getResolvedProperties();
  }

  public ResolvedPropertiesObject getInnerClasses()
  {
    if(innerClassesBuilder_ == null)
      return null;
    
    return innerClassesBuilder_.build();
  }

  @Override
  public ResolvedOpenApiObject getResolvedOpenApiObject()
  {
    if(openApiObjectBuilder_ == null)
      return null;
    
    return openApiObjectBuilder_.build();
  }

  public void validate(CanonModelContext modelContext)
  {
    // TODO Auto-generated method stub
    
  }
  
}
