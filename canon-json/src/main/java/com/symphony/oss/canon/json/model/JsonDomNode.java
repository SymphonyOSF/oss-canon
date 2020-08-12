/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon.json.model;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.json.IParserContext;
import com.symphony.oss.commons.fluent.BaseAbstractBuilder;

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

  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends JsonDomNode> extends BaseAbstractBuilder<T, B>
  {
    IParserContext context_;
    
    public AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    public T withContext(IParserContext context)
    {
      context_ = context;
      
      return self();
    }
  }
}
