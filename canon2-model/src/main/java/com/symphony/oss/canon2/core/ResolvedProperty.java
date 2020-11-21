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

import com.symphony.oss.canon.json.IParserContext;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon2.model.ISchemaInstance;
import com.symphony.oss.commons.fault.FaultAccumulator;
import com.symphony.oss.commons.fluent.BaseAbstractBuilder;

public class ResolvedProperty
{
  private final ResolvedSchema.AbstractBuilder<? extends ISchemaInstance, ?, ?> schemaBuilder_;
  private final String                                                          name_;
  private final IParserContext                                                  nameContext_;
  private final JsonDomNode                                                     jsonDomNode_;

  ResolvedProperty(AbstractBuilder<?,?> builder)
  {
    schemaBuilder_ = builder.schemaBuilder_;
    name_ = builder.name_;
    nameContext_ = builder.nameContext_;
    jsonDomNode_ = builder.jsonDomNode_;
  }
  
  public abstract static class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends ResolvedProperty> extends BaseAbstractBuilder<T,B>
  {
    private ResolvedSchema.AbstractBuilder<? extends ISchemaInstance,?,?> schemaBuilder_;
    private String                                  name_;
    private IParserContext                          nameContext_;
    private JsonDomNode                             jsonDomNode_;
    
    AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    abstract boolean isBuilt();
    
    public T withResolvedSchema(ResolvedSchema.AbstractBuilder<? extends ISchemaInstance,?,?> schemaBuilder)
    {
      if(isBuilt())
        throw new IllegalStateException("SingletonBuilder has already been built");
      
      schemaBuilder_ = schemaBuilder;
      
      return self();
    }

    public T withName(String name)
    {
      if(isBuilt())
        throw new IllegalStateException("SingletonBuilder has already been built");
      
      name_ = name;
      
      return self();
    }

    public T withNameContext(IParserContext nameContext)
    {
      if(isBuilt())
        throw new IllegalStateException("SingletonBuilder has already been built");
      
      nameContext_ = nameContext;
      
      return self();
    }

    public T withJson(JsonDomNode jsonDomNode)
    {
      if(isBuilt())
        throw new IllegalStateException("SingletonBuilder has already been built");
      
      jsonDomNode_ = jsonDomNode;
      
      return self();
    }
    
    @Override
    protected void validate(FaultAccumulator faultAccumulator)
    {
      super.validate(faultAccumulator);

      faultAccumulator.checkNotNull(schemaBuilder_, "ResolvedSchema");
      faultAccumulator.checkNotNull(name_,          "Name");
      faultAccumulator.checkNotNull(nameContext_,   "Name Context");
    }
  }
  
  public static class SingletonBuilder extends AbstractBuilder<SingletonBuilder, ResolvedProperty>
  { 
    private ResolvedProperty built_;

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
    protected ResolvedProperty construct()
    {
      if(built_ == null)
      {
        built_ = new ResolvedProperty(this);
      }
      
      return built_;
    }
  }

  public ResolvedSchema<?> getResolvedSchema()
  {
    return schemaBuilder_.build();
  }

  public String getName()
  {
    return name_;
  }

  public IParserContext getNameContext()
  {
    return nameContext_;
  }

  public JsonDomNode getJsonDomNode()
  {
    return jsonDomNode_;
  }

  public void validate(SourceContext context)
  {
    getResolvedSchema().validate(context);
  }
}
