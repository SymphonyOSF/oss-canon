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

/**
 * ResolvedSchema for any type of Number.
 * 
 * @author Bruce Skingle
 *
 */
public abstract class ResolvedNumberSchema extends ResolvedPrimitiveSchema
{
  private final boolean exclusiveMinimum_;
  private final boolean exclusiveMaximum_;
  
  ResolvedNumberSchema(AbstractBuilder<?,?> builder)
  {
    super(builder);

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
   */
  public abstract static class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends ResolvedNumberSchema> extends ResolvedPrimitiveSchema.AbstractBuilder<T,B>
  {
    protected boolean exclusiveMinimum_;
    protected boolean exclusiveMaximum_;
    
    AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    /**
     * Set the minimum valid value.
     * 
     * @param json the minimum valid value.
     * 
     * @return This (fluent method).
     */
    public abstract T withMinimum(JsonDomNode json);

    /**
     * Set the maximum valid value.
     * 
     * @param json the maximum valid value.
     * 
     * @return This (fluent method).
     */
    public abstract T withMaximum(JsonDomNode json);

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
