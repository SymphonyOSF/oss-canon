/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
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
    
  public ImmutableList<ParserException> getErrors()
  {
    return errors_;
  }

  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends JsonDom> extends BaseAbstractBuilder<T, B>
  {
    JsonObject object_;
    JsonArray array_;
    List<ParserException> errors_ = new LinkedList<>(); 
    
    AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    public T withArray(JsonArray array)
    {
      array_ = array;
      
      return self();
    }
    
    public T withObject(JsonObject object)
    {
      object_ = object;
      
      return self();
    }
  }
  
  public static class Builder extends AbstractBuilder<Builder, JsonDom>
  {
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
  
  public static class ParserBuilder extends AbstractBuilder<ParserBuilder, JsonDom>
  {
    public ParserBuilder()
    {
      super(ParserBuilder.class);
    }
    
    public ParserBuilder withError(ParserException error)
    {
      errors_.add(error);
      
      return self();
    }

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
