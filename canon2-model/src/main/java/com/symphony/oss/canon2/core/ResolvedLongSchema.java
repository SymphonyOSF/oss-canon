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

import com.symphony.oss.canon.json.SyntaxErrorException;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonParsedNumber;
import com.symphony.oss.commons.fault.FaultAccumulator;

/**
 * ResolvedSchema for a Long.
 * 
 * @author Bruce Skingle
 *
 */
public class ResolvedLongSchema extends ResolvedNumberSchema
{
  private final Long minimum_;
  private final Long maximum_;
  
  ResolvedLongSchema(AbstractBuilder<?,?> builder)
  {
    super(builder);

    minimum_            = builder.minimum_;
    maximum_            = builder.maximum_;
  }
   
  /**
   * Builder for ResolvedSchema for a Long.
   * 
   * @author Bruce Skingle
   *
   * @param <T> Concrete type of builder.
   * @param <B> Concrete type of built schema.
   */
  public abstract static class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends ResolvedLongSchema> extends ResolvedNumberSchema.AbstractBuilder<T,B>
  {
    private Long minimum_;
    private Long maximum_;
    
    AbstractBuilder(Class<T> type)
    {
      super(type);
    }

    @Override
    public T withMinimum(JsonDomNode json)
    {
      if(json == null)
      {
        minimum_ = null;
      }
      else if(json instanceof JsonParsedNumber)
      {
        try
        {
          minimum_ = ((JsonParsedNumber)json).asLong();
        }
        catch(NumberFormatException e)
        {
          withError(new SyntaxErrorException("Invalid value for minimum" + json.getClass().getSimpleName(), json.getContext(), e));
        }
      }
      else
      {
        withError(new SyntaxErrorException("Invalid value for minimum of type " + json.getClass().getSimpleName(), json.getContext()));
      }
      
      return self();
    }

    @Override
    public T withMaximum(JsonDomNode json)
    {
      if(json == null)
      {
        maximum_ = null;
      }
      else if(json instanceof JsonParsedNumber)
      {
        try
        {
          maximum_ = ((JsonParsedNumber)json).asLong();
        }
        catch(NumberFormatException e)
        {
          withError(new SyntaxErrorException("Invalid value for maximum" + json.getClass().getSimpleName(), json.getContext(), e));
        }
      }
      else
      {
         withError(new SyntaxErrorException("Invalid value for maximum of type " + json.getClass().getSimpleName(), json.getContext()));
      }
      
      return self();
    }

    @Override
    protected void validate(FaultAccumulator faultAccumulator)
    {
      super.validate(faultAccumulator);
      
      if(maximum_  != null && minimum_ != null)
      {
        if(minimum_ > maximum_)
          faultAccumulator.error("maximum must be >= minimum");
      }
      
      if(maximum_  != null && exclusiveMaximum_)
        faultAccumulator.error("exclusiveMaximum set but no maximum value provided.");
      
      if(minimum_  != null && exclusiveMinimum_)
        faultAccumulator.error("exclusiveMinimum set but no minimum value provided.");
    }
  }

  /**
   * Builder for ResolvedSchema for a Long.
   * 
   * @author Bruce Skingle
   *
   */
  public static class SingletonBuilder extends AbstractBuilder<SingletonBuilder, ResolvedLongSchema>
  { 
    ResolvedLongSchema built_;
    
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
    protected ResolvedLongSchema construct()
    {
      if(built_ == null)
      {
        built_ = new ResolvedLongSchema(this);
      }
      
      return built_;
    }
  }

  /**
   * Return the minimum valid value.
   * 
   * @return the minimum valid value.
   */
  public Long getMinimum()
  {
    return minimum_;
  }

  /**
   * Return the maximum valid value.
   * 
   * @return the maximum valid value.
   */
  public Long getMaximum()
  {
    return maximum_;
  }

  @Override
  public NumberSchemaType getNumberSchemaType()
  {
    return NumberSchemaType.INT64;
  }
}
