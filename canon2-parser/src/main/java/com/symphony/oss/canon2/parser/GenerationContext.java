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

package com.symphony.oss.canon2.parser;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.symphony.oss.canon.ICanonGenerator;
import com.symphony.oss.canon.parser.log.Logger;
import com.symphony.oss.canon.parser.log.LoggerFactory;
import com.symphony.oss.canon.runtime.ModelRegistry;
import com.symphony.oss.canon2.parser.model.CanonModel;

/**
 * The context for a Canon generation run.
 * 
 * Includes the models specifically included for generation as well as referenced models.
 * 
 * @author Bruce Skingle
 *
 */
class GenerationContext
{
  private LoggerFactory               logFactory_;
  private Logger                      log_;
  
  private File                   targetDir_;
  private File                   proformaDir_;
  private File                   copyDir_;
  private Map<String, Object>    dataModel_      = new HashMap<>();
  private List<ICanonGenerator>  generators_     = new LinkedList<>();
  

  private Map<URL, ModelContext> generationContexts_ = new HashMap<>();
  private Map<URL, ModelContext> referencedContexts_ = new HashMap<>();
  private Deque<ModelContext>    parseQueue_         = new LinkedList<>();
  private Deque<ModelContext>       validateQueue_      = new LinkedList<>();
  private Deque<ModelContext>       generateQueue_      = new LinkedList<>();
  private Map<URL, IOpenApiObject>    modelMap_           = new HashMap<>();
  private ModelRegistry               modelRegistry_      = new ModelRegistry()
      .withFactories(CanonModel.FACTORIES);

  public GenerationContext(LoggerFactory logFactory, String targetDirName, String proformaDirName, String copyDirName) throws GenerationException
  {
    this(logFactory, new File(targetDirName), proformaDirName == null ? new File(targetDirName) : new File(proformaDirName),
        copyDirName == null ? null : new File(copyDirName));
  }
  
  public GenerationContext(LoggerFactory logFactory, File targetDir, File proformaDir, File copyDir) throws GenerationException
  {
    logFactory_ = logFactory;
    log_ = logFactory.getLogger(getClass());
    
    log_.info("GenerationContext created");
    
    validateDir(targetDir);
    
    if(proformaDir != null)
    {
      validateDir(proformaDir);
    }
    
    if(copyDir != null)
    {
      validateDir(copyDir);
    }
    
    targetDir_ = targetDir;
    proformaDir_ = proformaDir;
    copyDir_ = copyDir;
  }
  
  public Object put(String key, Object value)
  {
    return dataModel_.put(key, value);
  }

  public Map<String, Object> getDataModel()
  {
    return dataModel_;
  }

  private void validateDir(File targetDir) throws GenerationException
  {
    if(!targetDir.exists())
    {
      if(!targetDir.mkdirs())
      {
        throw new GenerationException("Target dir \"" + targetDir.getAbsolutePath() + "\" does not exist and cannot be created.");
      }
    }
    else
    {
      if(!targetDir.isDirectory())
      {
        throw new GenerationException("Target dir \"" + targetDir.getAbsolutePath() + "\" is not a directory.");
      }
      else if(!targetDir.canWrite())
      {
        throw new GenerationException("Target dir \"" + targetDir.getAbsolutePath() + "\" is not writable.");
      }
    }
  }

  public List<ICanonGenerator> getGenerators()
  {
    return generators_;
  }

  public File getTargetDir()
  {
    return targetDir_;
  }

  public File getProformaDir()
  {
    return proformaDir_;
  }

  public File getCopyDir()
  {
    return copyDir_;
  }

  public void addGenerator(ICanonGenerator generator)
  {
    generators_.add(generator);
  }
  


  public LoggerFactory getLogFactory()
  {
    return logFactory_;
  }

  public void addGenerationSource(File file) throws GenerationException
  {
    try
    {
      URL url = file.toURI().toURL();
      
      ModelContext context = new ModelContext(this, url, false);
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
    ModelContext context = new ModelContext(this, baseUrl, reader, false);
    generationContexts_.put(baseUrl, context);
    parseQueue_.add(context);
  }
  
//  public IOpenApiObject  parseOneModel() throws GenerationException
//  {
//    ModelContext context = parseQueue_.pollFirst();
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
  
  void process(Map<String, String> uriMap)
  {
    ModelContext context;
    IOpenApiObject model;
    
    while((context = parseQueue_.pollFirst()) != null)
    {
      context.setUriMap(uriMap);
      model = context.parse(modelRegistry_);
      validateQueue_.add(context);
      modelMap_.put(context.getUrl(), model);
    }
    
    while((context = validateQueue_.pollFirst()) != null)
    {
      validate(context);
//      
//      if(!model.getContext().getRootParserContext().getErrors().isEmpty())
//        throw new ParsingException("Generation failed for " +model.getContext().getRootParserContext().getInputSourceName());
    }
  }

  public void validate(ModelContext context)
  {
    IOpenApiObject model = context.getModel();
    model.resolve(this);
    model.validate(this);
    
    if(!context.isReferencedModel())
    {
      generateQueue_.add(context);
    }
  }

  public ModelContext addReferencedModel(URL url) throws GenerationException
  {
    ModelContext context = referencedContexts_.get(url);
    
    if(context == null)
    {
      context = new ModelContext(this, url, true);
      referencedContexts_.put(url, context);
      parseQueue_.add(context);
    }
    
    return context;
  }
  
  public IOpenApiObject getModel(URL url)
  {
    return modelMap_.get(url);
  }
  
  public void generate() throws GenerationException
  {
    for(ModelContext context : generateQueue_)
    {
      context.getModel().generate(this);
//      model.getContext().getRootParserContext().epilogue("Generation");
    }
  }
  
//  public void visitAllModels(IModelVisitor visitor)
//  {
//    for(OpenApiObject model : generateQueue_)
//    {
//      visitor.visit(model);
//    }
//  }
}
