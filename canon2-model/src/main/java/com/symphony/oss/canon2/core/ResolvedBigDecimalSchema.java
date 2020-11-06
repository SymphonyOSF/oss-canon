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

import java.math.BigDecimal;

import com.symphony.oss.canon.json.model.JsonParsedNumber;
import com.symphony.oss.commons.fault.FaultAccumulator;

/**
 * ResolvedSchema for a BigDecimal.
 * 
 * @author Bruce Skingle
 *
 */
public class ResolvedBigDecimalSchema extends ResolvedNumberSchema<BigDecimal>
{
  ResolvedBigDecimalSchema(AbstractBuilder<?,?> builder)
  {
    super(builder);
  }
   
  /**
   * Builder for ResolvedSchema for a BigDecimal.
   * 
   * @author Bruce Skingle
   *
   * @param <T> Concrete type of builder.
   * @param <B> Concrete type of built schema.
   */
  public abstract static class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends ResolvedBigDecimalSchema> extends ResolvedNumberSchema.AbstractBuilder<T,B, BigDecimal>
  {
    AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    @Override
    protected BigDecimal parseValue(JsonParsedNumber json)
    {
      return json.asBigDecimal();
    }

    @Override
    protected void validate(FaultAccumulator faultAccumulator)
    {
      super.validate(faultAccumulator);
      
      if(maximum_  != null && minimum_ != null)
      {
        if(minimum_.compareTo(maximum_) > 0)
          faultAccumulator.error("maximum must be >= minimum");
      }
    }
  }

  /**
   * Builder for ResolvedSchema for a BigDecimal.
   * 
   * @author Bruce Skingle
   *
   */
  public static class SingletonBuilder extends AbstractBuilder<SingletonBuilder, ResolvedBigDecimalSchema>
  { 
    ResolvedBigDecimalSchema built_;
    
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
    protected ResolvedBigDecimalSchema construct()
    {
      if(built_ == null)
      {
        built_ = new ResolvedBigDecimalSchema(this);
      }
      
      return built_;
    }
  }

  @Override
  public NumberSchemaType getNumberSchemaType()
  {
    return NumberSchemaType.BIG_DECIMAL;
  }
}
