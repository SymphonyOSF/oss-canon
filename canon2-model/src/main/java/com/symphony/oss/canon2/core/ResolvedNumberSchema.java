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

import com.symphony.oss.canon.json.SyntaxErrorException;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonParsedNumber;
import com.symphony.oss.commons.fault.FaultAccumulator;

/**
 * ResolvedSchema for any type of Number.
 * 
 * @author Bruce Skingle
 * 
 * @param <V> The numeric type of this schema.
 *
 */
public abstract class ResolvedNumberSchema<V extends Number> extends ResolvedPrimitiveSchema
{
  private final V minimum_;
  private final V maximum_;
  
  private final boolean exclusiveMinimum_;
  private final boolean exclusiveMaximum_;
  
  ResolvedNumberSchema(AbstractBuilder<?,?,V> builder)
  {
    super(builder);

    minimum_            = builder.minimum_;
    maximum_            = builder.maximum_;

    exclusiveMinimum_   = builder.exclusiveMinimum_;
    exclusiveMaximum_   = builder.exclusiveMaximum_;
  }
  
  /**
   * Builder for ResolvedSchema for any type of Number.
   * 
   * @author Bruce Skingle
   *
   * @param <T> Concrete type of builder.
   * @param <B> Concrete type of built schema.
   * @param <V> The numeric type of this schema.
   */
  public abstract static class AbstractBuilder<T extends AbstractBuilder<T,B,V>, B extends ResolvedNumberSchema<V>, V extends Number> extends ResolvedPrimitiveSchema.AbstractBuilder<T,B>
  {
    V minimum_;
    V maximum_;
    boolean exclusiveMinimum_;
    boolean exclusiveMaximum_;
    
    AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    protected abstract V parseValue(JsonParsedNumber json);
    protected abstract String getTypeName();

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
          minimum_ = parseValue((JsonParsedNumber)json);
        }
        catch(NumberFormatException e)
        {
           withError(new SyntaxErrorException("Invalid " + getTypeName() + " value for minimum (" + e.getLocalizedMessage() + ")", json.getContext(), e));
        }
      }
      else
      {
         withError(new SyntaxErrorException("Invalid value for minimum of type " + json.getClass().getSimpleName(), json.getContext()));
      }
      
      return self();
    }

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
          maximum_ = parseValue((JsonParsedNumber)json);
        }
        catch(NumberFormatException e)
        {
           withError(new SyntaxErrorException("Invalid " + getTypeName() + " value for maximum (" + e.getLocalizedMessage() + ")", json.getContext(), e));
        }
      }
      else
      {
         withError(new SyntaxErrorException("Invalid value for maximum of type " + json.getClass().getSimpleName(), json.getContext()));
      }
      
      return self();
    }

    public T withExclusiveMinimum(boolean exclusiveMinimum)
    {
      exclusiveMinimum_ = exclusiveMinimum;
      
      return self();
    }

    public T withExclusiveMaximum(boolean exclusiveMaximum)
    {
      exclusiveMaximum_ = exclusiveMaximum;
      
      return self();
    }

    @Override
    protected void validate(FaultAccumulator faultAccumulator)
    {
      super.validate(faultAccumulator);
      
      if(maximum_  != null && exclusiveMaximum_)
        faultAccumulator.error("exclusiveMaximum set but no maximum value provided.");
      
      if(minimum_  != null && exclusiveMinimum_)
        faultAccumulator.error("exclusiveMinimum set but no minimum value provided.");
    }
  }
  /**
   * Return the minimum valid value.
   * 
   * @return the minimum valid value.
   */
  public V getMinimum()
  {
    return minimum_;
  }

  /**
   * Return the maximum valid value.
   * 
   * @return the maximum valid value.
   */
  public V getMaximum()
  {
    return maximum_;
  }
  
  @Override
  public boolean hasLimits()
  {
    return minimum_ != null || maximum_ != null;
  }
  
  /**
   * Return the numeric sub-type of this schema.
   * 
   * @return the numeric sub-type of this schema.
   */
  public abstract NumberSchemaType getNumberSchemaType();

  public boolean isExclusiveMinimum()
  {
    return exclusiveMinimum_;
  }

  public boolean isExclusiveMaximum()
  {
    return exclusiveMaximum_;
  }
}
