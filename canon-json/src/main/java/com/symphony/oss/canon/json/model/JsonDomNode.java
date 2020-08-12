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

package com.symphony.oss.canon.json.model;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.json.IParserContext;
import com.symphony.oss.commons.fluent.BaseAbstractBuilder;

/**
 * A node in a JSON DOM.
 * 
 * @author Bruce Skingle
 *
 */
@Immutable
public abstract class JsonDomNode
{
  static final String INDENT_LEVEL = "  ";
  
  private final IParserContext context_;
  private String stringValue_;
  
  protected JsonDomNode(AbstractBuilder<?,?> builder)
  {
    context_ = builder.context_;
  }
  
  @Override
  public synchronized String toString()
  {
    if(stringValue_ == null)
    {
      StringBuilder s = new StringBuilder();
      
      toString(s, "");
      
      stringValue_ = s.toString();
    }
    
    return stringValue_;
  }
  
  public @Nullable IParserContext getContext()
  {
    return context_;
  }

  abstract void toString(StringBuilder s, String indent);

  /**
   * Builder for this and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <T> The concrete type of the builder for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends JsonDomNode> extends BaseAbstractBuilder<T, B>
  {
    IParserContext context_;
    
    AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    /**
     * Set the location of the source of this object in the parsed input stream.
     * 
     * @param context
     * @return
     */
    public T withContext(IParserContext context)
    {
      context_ = context;
      
      return self();
    }
  }
}
