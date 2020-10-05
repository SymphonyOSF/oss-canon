/*
 *
 *
 * Copyright 2017 Symphony Communication Services, LLC.
 *
 * Licensed to The Symphony Software Foundation (SSF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The SSF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.symphony.oss.canon2.model;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;
import com.symphony.oss.commons.fluent.BaseAbstractBuilder;



/**
 * The context for a Canon processing run.
 * 
 * This class deals with parsing and resolving models, sub-classes must implement the 
 * 
 * Includes the models specifically included for processing as well as referenced models.
 * 
 * @author Bruce Skingle
 *
 */
public abstract class CanonModelContext
{
  private static Logger                        log_                = LoggerFactory.getLogger(CanonModelContext.class);

  
  private final ModelRegistry                  modelRegistry_      = new ModelRegistry.Builder()
      .withFactories(CanonModel.FACTORIES)
      .build();

  private final ImmutableMap<String, String>   uriMap_;
  
  private Map<URL, SourceContext>               generationContexts_ = new HashMap<>();
  private Map<URL, SourceContext>               referencedContexts_ = new HashMap<>();
  private Deque<SourceContext>                  parseQueue_         = new LinkedList<>();
  private Deque<SourceContext>                  validateQueue_      = new LinkedList<>();
  private Deque<SourceContext>                  generateQueue_      = new LinkedList<>();
  private Map<URL, OpenApiObject>              modelMap_           = new HashMap<>();
  private Map<String, ResolvedSchema.SingletonBuilder>  schemaMap_          = new HashMap<>();
  
  protected CanonModelContext(AbstractBuilder<?,?> builder)
  {
    log_.info("CanonModelContext created");
    
    uriMap_ = ImmutableMap.copyOf(builder.uriMap_);
  }
  
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends CanonModelContext> extends BaseAbstractBuilder<T,B>
  {
    private Map<String, String>   uriMap_     = new HashMap<>();
    
    public AbstractBuilder(Class<T> type)
    {
      super(type);
    }

    public T withUriMapping(String key, String value)
    {
      uriMap_.put(key, value);
      
      return self();
    }

    /**
     * Replace the current uri mappings with the given one.
     * 
     * @param uriMap The set of URI mappings to be used.
     * 
     * @return This (fluent method).
     */
    public T withUriMappings(Map<String, String> uriMap)
    {
      uriMap_.clear();
      uriMap_.putAll(uriMap);
      
      return self();
    }
  }
  
// this class is abstract
//  public static class Builder extends AbstractBuilder<Builder, CanonModelContext>
//  {
//    public Builder()
//    {
//      super(Builder.class);
//    }
//
//    @Override
//    protected CanonModelContext construct()
//    {
//      return new CanonModelContext(this);
//    }
//  }


  public synchronized ResolvedSchema.SingletonBuilder link(ResolvedOpenApiObject.SingletonBuilder openApiObjectBuilder, SourceContext sourceContext,
      String name, String uri, Schema schema, boolean generated) throws GenerationException
  {
    ResolvedSchema.SingletonBuilder builder = schemaMap_.get(uri);
    
    if(builder == null)
    {
      builder = new ResolvedSchema.SingletonBuilder()
          .withName(name)
          .withUri(uri)
          .withGenerated(generated)
          .withResolvedOpenApiObject(openApiObjectBuilder)
          ;
      
      schemaMap_.put(uri, builder);   
          
      schema.link(openApiObjectBuilder, builder, this, sourceContext, uri, generated);
    }
    
    return builder;
  }
  
//  public void resolve(SchemaInfo info)
//  {
//    if(schemaMap_.put(info.getUri(), info) == null)
//    {
//      info.resolve(this);
//    }
//  }
  
  public ResolvedSchema.SingletonBuilder getSchemaInfo(String absoluteUri)
  {
    return schemaMap_.get(absoluteUri);
  }

  public ModelRegistry getModelRegistry()
  {
    return modelRegistry_;
  }

  public void addGenerationSource(File file) throws GenerationException
  {
    try
    {
      URL url = file.toURI().toURL();
      
      SourceContext context = new SourceContext(this, url, false, uriMap_);
      generationContexts_.put(url, context);
      parseQueue_.add(context);
    }
    catch(IOException e)
    {
      throw new GenerationException(e);
    }
  }
  
  public void addGenerationSource(URL baseUrl, Reader reader)
  {
    SourceContext context = new SourceContext(this, baseUrl, reader, false, uriMap_);
    generationContexts_.put(baseUrl, context);
    parseQueue_.add(context);
  }
  
//  public IOpenApiObject  parseOneModel() throws GenerationException
//  {
//    SourceContext context = parseQueue_.pollFirst();
//    
//    if(context == null)
//      throw new GenerationException("No models left to parse");
//
//    IOpenApiObject model = parse(context);
//    validateQueue_.add(model);
//    modelMap_.put(context.getUrl(), model);
//    
//    return model;
//  }
  
  public void process() throws GenerationException
  {
    SourceContext sourceContext;
    OpenApiObject model;
    
    while((sourceContext = parseQueue_.pollFirst()) != null)
    {
      try
      {
        model = sourceContext.parse(modelRegistry_);
      }
      catch(IllegalStateException e)
      {
        throw new GenerationException("Failed to parse model", e);
      }
      validateQueue_.add(sourceContext);
      modelMap_.put(sourceContext.getUrl(), model);
      
      model.fetchReferences(this, sourceContext);
    }
    
    while((sourceContext = validateQueue_.pollFirst()) != null)
    {
      validate(sourceContext);
    }
    
    process(generateQueue_);
  }

  protected abstract void process(Deque<SourceContext> processQueue) throws GenerationException;

  void validate(SourceContext context) throws GenerationException
  {
//    OpenApiObject                           model = context.getModel();
//    ResolvedOpenApiObject.SingletonBuilder  builder = model.link(this, context);
//    ResolvedOpenApiObject                   resolvedOpenApiObject = builder.build();
    
    context.link(this);
   // model.resolve(this, context);
    
    context.getResolvedOpenApiObject().validate(this);
    
    if(context.printErrors())
      throw new GenerationException("Generation failed for " + context.getInputSourceName());
    
    if(!context.isReferencedModel())
    {
      generateQueue_.add(context);
    }
  }

  public void addReferencedModel(URL url) throws GenerationException
  {
    if(url != null)
    {
      SourceContext context = referencedContexts_.get(url);
      
      if(context == null)
      {
        context = new SourceContext(this, url, true, uriMap_);
        referencedContexts_.put(url, context);
        parseQueue_.add(context);
      }
    }
  }
  
  public SourceContext getReferencedModel(URL url)
  {
    return referencedContexts_.get(url);
  }
  
  public OpenApiObject getModel(URL url)
  {
    return modelMap_.get(url);
  }
}
