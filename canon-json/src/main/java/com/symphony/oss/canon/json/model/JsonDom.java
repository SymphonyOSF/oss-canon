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

import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.symphony.oss.canon.json.IParserContext;
import com.symphony.oss.canon.json.ParserException;
import com.symphony.oss.commons.fault.FaultAccumulator;
import com.symphony.oss.commons.fluent.BaseAbstractBuilder;

/**
 * A Json DOM a.k.a Document or Text.
 * 
 * @author Bruce Skingle
 *
 */
public class JsonDom
{
  private final ImmutableList<ParserException> errors_;

  protected JsonDom(AbstractBuilder<?,?> builder)
  {
    errors_ = ImmutableList.copyOf(builder.errors_);
  }
    
  /**
   * Return all errors encountered parsing this dom.
   * 
   * @return All errors encountered parsing this dom.
   */
  public ImmutableList<ParserException> getErrors()
  {
    return errors_;
  }

  /**
   * Builder for this and sub-classes.
   * 
   * @author Bruce Skingle
   *
   * @param <T> The concrete type of the builder for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends JsonDom> extends BaseAbstractBuilder<T, B>
  {
    JsonObject object_;
    JsonArray array_;
    List<ParserException> errors_ = new LinkedList<>(); 
    
    AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    /**
     * Set the content of this DOM.
     * 
     * Only one of withArray() and withObject() should be called.
     * 
     * @param array The content of this DOM.
     * 
     * @return This (fluent method).
     */
    public T withArray(JsonArray array)
    {
      array_ = array;
      
      return self();
    }
    
    /**
     * Set the content of this DOM.
     * 
     * Only one of withArray() and withObject() should be called.
     * 
     * @param object The content of this DOM.
     * 
     * @return This (fluent method).
     */
    public T withObject(JsonObject object)
    {
      object_ = object;
      
      return self();
    }
  }
  
  /**
   * Builder for use when constructing a DOM directly.
   * 
   * @author Bruce Skingle
   *
   */
  public static class Builder extends AbstractBuilder<Builder, JsonDom>
  {
    /**
     * Constructor.
     */
    public Builder()
    {
      super(Builder.class);
    }

    @Override
    protected void validate(FaultAccumulator faultAccumulator)
    {
      super.validate(faultAccumulator);
      
      faultAccumulator.checkValueCount("Object or Array", 1, 1, object_, array_);

    }

    @Override
    protected JsonDom construct()
    {
      if(array_ != null)
        return new JsonArrayDom(this, array_);
      
      return new JsonObjectDom(this, object_);
    }
  }
  
  /**
   * Builder for use when parsing a DOM.
   * 
   * @author Bruce Skingle
   *
   */
  public static class ParserBuilder extends AbstractBuilder<ParserBuilder, JsonDom>
  {
    /**
     * Constructor.
     */
    public ParserBuilder()
    {
      super(ParserBuilder.class);
    }
    
    /**
     * Add the given parsing error to this dom.
     * 
     * @param error A parsing error encountered when parsing the dom.
     * 
     * @return This (fluent method).
     */
    public ParserBuilder withError(ParserException error)
    {
      errors_.add(error);
      
      return self();
    }

    /**
     * Add the given parsing error to this dom.
     * 
     * @param e         A parsing error encountered when parsing the dom.
     * @param context   The location of the error in the parsed input.
     * 
     * @return This (fluent method).
     */
    public ParserBuilder withError(Exception e, IParserContext context)
    {
      return withError(new ParserException(e.getMessage(), context, e));
    }

    @Override
    protected JsonDom construct()
    {
      if(array_ != null)
        return new JsonArrayDom(this, array_);
      
      if(object_ != null)
        return new JsonObjectDom(this, object_);
      
      return new JsonInvalidDom(this);
    }
  }
}
