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
import com.symphony.oss.canon2.core.ResolvedProperty.AbstractBuilder;

/**
 * A map of resolved properties.
 * 
 * @author Bruce Skingle
 *
 */
public class ResolvedPropertiesObject
{
  private final Map<String, ResolvedProperty> properties_;
  
  private ResolvedPropertiesObject(SingletonBuilder builder)
  {
    ImmutableMap.Builder<String, ResolvedProperty> b = new ImmutableMap.Builder<String, ResolvedProperty>();
    
    for(Entry<String, AbstractBuilder<?, ?>> entry : builder.propertyBuilders_.entrySet())
    {
      b.put(entry.getKey(), entry.getValue().build());
    }
    properties_ = b.build();
  }
  
  /**
   * Builder.
   */
  public static class SingletonBuilder
  {
    Map<String, ResolvedProperty.AbstractBuilder<?,?>> propertyBuilders_ = new HashMap<>();
    ResolvedPropertiesObject built_;
    
    /**
     * Add the given property.
     * 
     * @param name      The name of the property.
     * @param property  The property.
     * 
     * @return this (fluent method).
     */
    public SingletonBuilder with(String name, ResolvedProperty.AbstractBuilder<?,?> property)
    {
      if(built_ != null)
        throw new IllegalStateException("SingletonBuilder has already been built");
    
      propertyBuilders_.put(name, property);
      
      return this;
    }
    
    /**
     * Build and return the one and only instance.
     * 
     * @return the one and only instance.
     */
    public ResolvedPropertiesObject build()
    {
      if(built_ == null)
        built_ = new ResolvedPropertiesObject(this);
      
      return built_;
    }
  }

  /**
   * Validate.
   * 
   * @param context The source context for error reporting.
   */
  public void validate(SourceContext context)
  {
    for(ResolvedProperty schema : properties_.values())
    {
      schema.validate(context);
    }
  }

  /**
   * Return all properties.
   * 
   * @return all properties.
   */
  public @Nonnull Map<String, ResolvedProperty> getResolvedProperties()
  {
    return properties_;
  }
}
