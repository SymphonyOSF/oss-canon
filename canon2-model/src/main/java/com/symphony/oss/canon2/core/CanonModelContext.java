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

package com.symphony.oss.canon2.core;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.symphony.oss.canon.json.ParserContext;
import com.symphony.oss.canon.json.ParserErrorException;
import com.symphony.oss.canon.json.ParserException;
import com.symphony.oss.canon.json.ParserResultException;
import com.symphony.oss.canon2.core.ResolvedOpenApiObject.SingletonBuilder;
import com.symphony.oss.canon2.model.Canon_Model;
import com.symphony.oss.canon2.model.ISchemaInstance;
import com.symphony.oss.canon2.model.OpenApiObject;
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
      .withFactories(Canon_Model.FACTORIES)
      .build();

  private final ImmutableMap<String, String>   uriMap_;
  private final boolean                        templateDebug_;
  
  private Map<URL, SourceContext>               generationContexts_ = new HashMap<>();
  private Map<URL, SourceContext>               referencedContexts_ = new HashMap<>();
  private Deque<SourceContext>                  parseQueue_         = new LinkedList<>();
  private Deque<SourceContext>                  validateQueue_      = new LinkedList<>();
  private Deque<SourceContext>                  generateQueue_      = new LinkedList<>();
  private Map<URL, OpenApiObject>               modelMap_           = new HashMap<>();
  private Map<String, ResolvedSchema.AbstractBuilder<?,?,?>>  schemaMap_          = new HashMap<>();
  
  protected CanonModelContext(AbstractBuilder<?,?> builder)
  {
    log_.info("CanonModelContext created");
    
    uriMap_ = ImmutableMap.copyOf(builder.uriMap_);
    templateDebug_  = builder.verbose_;
  }
  
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends CanonModelContext> extends BaseAbstractBuilder<T,B>
  {
    private Map<String, String> uriMap_ = new HashMap<>();
    private boolean             verbose_;
    
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

    /**
     * Set if verbose logging an error reporting is required.
     * 
     * @param verbose  true if verbose logging an error reporting is required.
     * 
     * @return This (fluent method).
     */
    public T withVerbose(boolean verbose)
    {
      verbose_ = verbose;
      
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
  
  /**
   * Return true if verbose logging an error reporting is required.
   * 
   * @return true if verbose logging an error reporting is required.
   */
  public boolean isVerbose()
  {
    return templateDebug_;
  }

  private class BuilderConsumer implements Consumer<ResolvedSchema.AbstractBuilder<?,?,?>>
  {
    private final String                                            name_;
    private final SingletonBuilder                                  openApiObjectBuilder_;
    private final String                                            uri_;
    private final ResolvedObjectOrArraySchema.AbstractBuilder<?,?,?> outerClassBuilder_;
    private ResolvedSchema.AbstractBuilder<?,?,?>                    builder_;

    BuilderConsumer(ResolvedOpenApiObject.SingletonBuilder openApiObjectBuilder,
      String name, String uri,  ResolvedObjectOrArraySchema.AbstractBuilder<?,?,?> outerClassBuilder)
    {
      openApiObjectBuilder_ = openApiObjectBuilder;
      name_ = name;
      uri_ = uri;
      outerClassBuilder_ = outerClassBuilder;
    }
    
    @Override
    public void accept(ResolvedSchema.AbstractBuilder<?,?,?> builder)
    {
      builder_ = builder;
      
      builder
        .withName(name_)
        .withUri(uri_)
        .withResolvedContainer(outerClassBuilder_)
        .withResolvedOpenApiObject(openApiObjectBuilder_)
        ;
  
      schemaMap_.put(uri_, builder);   
    }
  }

  public synchronized ResolvedSchema.AbstractBuilder<?,?,?> link(ResolvedOpenApiObject.SingletonBuilder openApiObjectBuilder, SourceContext sourceContext,
      String name, String uri, ISchemaInstance schema, ResolvedObjectOrArraySchema.AbstractBuilder<?,?,?> outerClassBuilder, int depth)
  {
    ResolvedSchema.AbstractBuilder<?,?,?> builder = schemaMap_.get(uri);
    
    if(builder == null)
    {
      BuilderConsumer c = new BuilderConsumer(openApiObjectBuilder, name, uri, outerClassBuilder);
      
      schema.link(openApiObjectBuilder, this, sourceContext,
          c,
          uri,
          depth);
      
      if(outerClassBuilder != null && Boolean.TRUE == schema.getXCanonFacade())
        sourceContext.error(new ParserErrorException("Only top level entities can have a facade", schema));
      
      builder = c.builder_;
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
  
  public ResolvedSchema.AbstractBuilder<?,?,?> getSchemaInfo(String absoluteUri)
  {
    return schemaMap_.get(absoluteUri);
  }

  public ModelRegistry getModelRegistry()
  {
    return modelRegistry_;
  }

  public void addGenerationSource(File file) throws ParserException
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
      throw new ParserErrorException("Unable to open generation source ", new ParserContext(file.getAbsolutePath()), e);
    }
  }
  
  public SourceContext addGenerationSource(URL baseUrl, Reader reader)
  {
    SourceContext context = new SourceContext(this, baseUrl, reader, false, uriMap_);
    generationContexts_.put(baseUrl, context);
    parseQueue_.add(context);
    
    return context;
  }
  
//  public IOpenApiObject  parseOneModel()
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
  
  public void process() throws ParserResultException, ParserException
  {
    SourceContext sourceContext;
    OpenApiObject model;
    
    while((sourceContext = parseQueue_.pollFirst()) != null)
    {
      model = sourceContext.parse(modelRegistry_);
     
//      catch(IllegalStateException e)
//      {
//        throw new ParserErrorException("Failed to parse model", e);
//      }
//      catch (ParserResultException e)
//      {
//        for(ParserException parserException : e.getParserExceptions())
//        {
//          log_.error(parserException.toString());
//        }
//        throw new GenerationException("Failed to parse model", e);
//      }
//      catch(ParserException e)
//      {
//        log_.error(e.toString());
//        throw new GenerationException("Failed to parse model", e);
//      }
      validateQueue_.add(sourceContext);
      modelMap_.put(sourceContext.getUrl(), model);
      
      model.fetchReferences(this, sourceContext);
    }
    
    while((sourceContext = validateQueue_.pollFirst()) != null)
    {
      validate(sourceContext);
    }
    

//    try
//    {
      process(generateQueue_);
//    }
//    catch(ParserException e)
//    {
//      log_.error(e.toString());
//      throw new GenerationException("Failed to parse model", e);
//    }
  }

  protected abstract void process(Deque<SourceContext> processQueue) throws ParserResultException;

  void validate(SourceContext context) throws ParserResultException
  {
//    OpenApiObject                           model = context.getModel();
//    ResolvedOpenApiObject.SingletonBuilder  builder = model.link(this, context);
//    ResolvedOpenApiObject                   resolvedOpenApiObject = builder.build();
    
    context.link(this);
   // model.resolve(this, context);
    
    context.getResolvedOpenApiObject().validate(context);
    
    context.printErrorsAndThrowException(false);
    
    if(!context.isReferencedModel())
    {
      generateQueue_.add(context);
    }
  }

  public void addReferencedModel(URL url) throws ParserException
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
