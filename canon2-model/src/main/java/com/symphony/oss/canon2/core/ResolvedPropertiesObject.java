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

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableMap;

public class ResolvedPropertiesObject
{
  private final Map<String, ResolvedSchema> properties_;
  
  private ResolvedPropertiesObject(SingletonBuilder builder)
  {
    ImmutableMap.Builder<String, ResolvedSchema> b = new ImmutableMap.Builder<String, ResolvedSchema>();
    
    for(Entry<String, ResolvedSchema.AbstractBuilder<?,?>> entry : builder.propertyBuilders_.entrySet())
    {
      b.put(entry.getKey(), entry.getValue().build());
    }
    properties_ = b.build();
  }
  
  public static class SingletonBuilder
  {
    Map<String, ResolvedSchema.AbstractBuilder<?,?>> propertyBuilders_ = new HashMap<>();
    ResolvedPropertiesObject built_;
    
    public SingletonBuilder with(String name, ResolvedSchema.AbstractBuilder<?,?> property)
    {
      if(built_ != null)
        throw new IllegalStateException("SingletonBuilder has already been built");
    
      propertyBuilders_.put(name, property);
      
      return this;
    }
    
    public ResolvedPropertiesObject build()
    {
      if(built_ == null)
        built_ = new ResolvedPropertiesObject(this);
      
      return built_;
    }
  }

  public void validate(SourceContext context)
  {
    for(ResolvedSchema schema : properties_.values())
    {
      schema.validate(context);
    }
  }

  public @Nonnull Map<String, ResolvedSchema> getResolvedProperties()
  {
    return properties_;
  }
}
