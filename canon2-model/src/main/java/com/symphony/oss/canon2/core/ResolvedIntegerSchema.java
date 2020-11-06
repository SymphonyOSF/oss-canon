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

import com.symphony.oss.canon.json.model.JsonParsedNumber;
import com.symphony.oss.commons.fault.FaultAccumulator;

/**
 * ResolvedSchema for an Integer.
 * 
 * @author Bruce Skingle
 *
 */
public class ResolvedIntegerSchema extends ResolvedNumberSchema<Integer>
{
  ResolvedIntegerSchema(AbstractBuilder<?,?> builder)
  {
    super(builder);
  }
   
  /**
   * Builder for ResolvedSchema for an Integer.
   * 
   * @author Bruce Skingle
   *
   * @param <T> Concrete type of builder.
   * @param <B> Concrete type of built schema.
   */
  public abstract static class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends ResolvedIntegerSchema> extends ResolvedNumberSchema.AbstractBuilder<T,B,Integer>
  {
    AbstractBuilder(Class<T> type)
    {
      super(type);
    }

    @Override
    protected Integer parseValue(JsonParsedNumber json)
    {
      return json.asInteger();
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
    }
  }

  /**
   * Builder for ResolvedSchema for an Integer.
   * 
   * @author Bruce Skingle
   *
   */
  public static class SingletonBuilder extends AbstractBuilder<SingletonBuilder, ResolvedIntegerSchema>
  { 
    ResolvedIntegerSchema built_;
    
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
    protected ResolvedIntegerSchema construct()
    {
      if(built_ == null)
      {
        built_ = new ResolvedIntegerSchema(this);
      }
      
      return built_;
    }
  }

  @Override
  public NumberSchemaType getNumberSchemaType()
  {
    return NumberSchemaType.INT32;
  }
}
