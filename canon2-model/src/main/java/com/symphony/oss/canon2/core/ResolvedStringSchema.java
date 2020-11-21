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

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.symphony.oss.canon2.model.StringSchema;
import com.symphony.oss.commons.fault.FaultAccumulator;

public class ResolvedStringSchema extends ResolvedPrimitiveSchema<StringSchema>
{
  private final Integer              minLength_;
  private final Integer              maxLength_;
  private final String               pattern_;
  private final ImmutableSet<String> enum_;
  
  ResolvedStringSchema(AbstractBuilder<?,?> builder)
  {
    super(builder);

    minLength_ = builder.minLength_;
    maxLength_ = builder.maxLength_;
    pattern_   = builder.pattern_;
    enum_      = ImmutableSet.copyOf(builder.enum_);
  }
  
  public abstract static class AbstractBuilder<T extends AbstractBuilder<T, B>, B extends ResolvedStringSchema> extends ResolvedPrimitiveSchema.AbstractBuilder<StringSchema,T,B>
  {
    private Integer minLength_;
    private Integer maxLength_;
    private String  pattern_;
    private Set<String> enum_ = new HashSet<>();
    
    AbstractBuilder(Class<T> type)
    {
      super(type);
    }

    public T withMinLength(Integer minLength)
    {
      if(minLength != null && minLength < 0)
        throw new IllegalArgumentException("minLength may not be negative");
      
      minLength_ = minLength;
      
      return self();
    }

    public T withtMaxLength(Integer maxLength)
    {
      if(maxLength != null && maxLength < 0)
        throw new IllegalArgumentException("maxLength may not be negative");
      
      maxLength_ = maxLength;
      
      return self();
    }

    public T withPattern(String pattern)
    {
//      if(!ECMA262RE.isValid(pattern))
//        throw new IllegalArgumentException("maxLength may not be negative");
      
      pattern_ = pattern;
      
      return self();
    }

    public T withEnum(String enumValue)
    {
      enum_.add(enumValue);
      
      return self();
    }

    public T withEnum(Set<String> enumValues)
    {
      enum_ = new HashSet<>(enumValues);
      
      return self();
    }

    @Override
    protected void validate(FaultAccumulator faultAccumulator)
    {
      super.validate(faultAccumulator);
      
      if(maxLength_  != null && minLength_ != null)
      {
        if(minLength_ > maxLength_)
          faultAccumulator.error("maxLength must be >= minLength");
      }
    }
    
  }
  
  public static class SingletonBuilder extends AbstractBuilder<SingletonBuilder, ResolvedStringSchema>
  { 
    ResolvedStringSchema built_;
    
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
    protected ResolvedStringSchema construct()
    {
      if(built_ == null)
      {
        built_ = new ResolvedStringSchema(this);
      }
      
      return built_;
    }
  }

  @Override
  public boolean hasLimits()
  {
    return minLength_ != null || maxLength_ != null || pattern_ != null;
  }

  public Integer getMinLength()
  {
    return minLength_;
  }

  public Integer getMaxLength()
  {
    return maxLength_;
  }

  public String getPattern()
  {
    return pattern_;
  }

  public ImmutableSet<String> getEnum()
  {
    return enum_;
  }
  
}
