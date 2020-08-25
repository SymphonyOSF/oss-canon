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
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Files;
import com.symphony.oss.canon.runtime.ModelRegistry;
import com.symphony.oss.canon2.parser.model.CanonModel;
import com.symphony.oss.commons.dom.json.IJsonObject;
import com.symphony.oss.commons.fluent.BaseAbstractBuilder;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * The context for a Canon generation run.
 * 
 * Includes the models specifically included for generation as well as referenced models.
 * 
 * @author Bruce Skingle
 *
 */
public class GenerationContext
{
  private static Logger                        log_                = LoggerFactory.getLogger(GenerationContext.class);

  private final File                           targetDir_;
  private final File                           proformaDir_;
  private final File                           copyDir_;
  private final ImmutableList<ICanonGenerator> generators_;
  private final ImmutableMap<String, String>   uriMap_;
  private final boolean                        templateDebug_;
  
  private final ModelRegistry                  modelRegistry_      = new ModelRegistry()
      .withFactories(CanonModel.FACTORIES);

  private Map<URL, ModelContext>               generationContexts_ = new HashMap<>();
  private Map<URL, ModelContext>               referencedContexts_ = new HashMap<>();
  private Deque<ModelContext>                  parseQueue_         = new LinkedList<>();
  private Deque<ModelContext>                  validateQueue_      = new LinkedList<>();
  private Deque<ModelContext>                  generateQueue_      = new LinkedList<>();
  private Map<URL, IOpenApiObject>             modelMap_           = new HashMap<>();

  private GenerationContext(AbstractBuilder<?,?> builder)
  {
    log_.info("GenerationContext created");
    
    targetDir_ = builder.targetDir_;
    proformaDir_ = builder.proformaDir_;
    copyDir_ = builder.copyDir_;
    generators_ = ImmutableList.copyOf(builder.generators_);
    uriMap_ = ImmutableMap.copyOf(builder.uriMap_);
    templateDebug_ = builder.templateDebug_;
  }
  
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends GenerationContext> extends BaseAbstractBuilder<T,B>
  {
    private File                  targetDir_;
    private File                  proformaDir_;
    private File                  copyDir_;
    private List<ICanonGenerator> generators_ = new LinkedList<>();
    private Map<String, String>   uriMap_     = new HashMap<>();
    private boolean templateDebug_;
    
    public AbstractBuilder(Class<T> type)
    {
      super(type);
    }

    public T withTargetDir(File targetDir)
    {
      targetDir_ = validateDir(targetDir);
      
      return self();
    }

    public T withTargetDir(String targetDir)
    {
      targetDir_ = validateDir(targetDir);
      
      return self();
    }

    public T withProformaDir(File proformaDir)
    {
      proformaDir_ = validateDir(proformaDir);
      
      return self();
    }

    public T withProformaDir(String proformaDir)
    {
      proformaDir_ = validateDir(proformaDir);
      
      return self();
    }

    public T withCopyDir(File copyDir)
    {
      copyDir_ = copyDir;
      
      return self();
    }

    public T withCopyDir(String copyDir)
    {
      copyDir_ = validateDir(copyDir);
      
      return self();
    }

    public T withGenerators(List<ICanonGenerator> generators)
    {
      generators_ = generators;
      
      return self();
    }

    public T withGenerator(ICanonGenerator generator)
    {
      generators_.add(generator);
      
      return self();
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

    public T withTemplateDebug(boolean templateDebug)
    {
      templateDebug_ = templateDebug;
      
      return self();
    }
  }
  
  public static class Builder extends AbstractBuilder<Builder, GenerationContext>
  {
    public Builder()
    {
      super(Builder.class);
    }

    @Override
    protected GenerationContext construct()
    {
      return new GenerationContext(this);
    }
  }

  private static File validateDir(String targetDir) throws IllegalArgumentException
  {
    return validateDir(new File(targetDir));
  }
  
  private static File validateDir(File targetDir) throws IllegalArgumentException
  {
    if(!targetDir.exists())
    {
      if(!targetDir.mkdirs())
      {
        throw new IllegalArgumentException("Target dir \"" + targetDir.getAbsolutePath() + "\" does not exist and cannot be created.");
      }
    }
    else
    {
      if(!targetDir.isDirectory())
      {
        throw new IllegalArgumentException("Target dir \"" + targetDir.getAbsolutePath() + "\" is not a directory.");
      }
      else if(!targetDir.canWrite())
      {
        throw new IllegalArgumentException("Target dir \"" + targetDir.getAbsolutePath() + "\" is not writable.");
      }
    }
    
    return targetDir;
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

  public void addGenerationSource(File file) throws GenerationException
  {
    try
    {
      URL url = file.toURI().toURL();
      
      ModelContext context = new ModelContext(this, url, false, uriMap_);
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
    ModelContext context = new ModelContext(this, baseUrl, reader, false, uriMap_);
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
  
  public void process()
  {
    ModelContext context;
    IOpenApiObject model;
    
    while((context = parseQueue_.pollFirst()) != null)
    {
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

  void validate(ModelContext context)
  {
    IOpenApiObject model = context.getModel();
    model.resolve(this);
    model.validate(this);
    
    if(!context.isReferencedModel())
    {
      generateQueue_.add(context);
    }
  }

  ModelContext addReferencedModel(URL url) throws GenerationException
  {
    ModelContext context = referencedContexts_.get(url);
    
    if(context == null)
    {
      context = new ModelContext(this, url, true, uriMap_);
      referencedContexts_.put(url, context);
      parseQueue_.add(context);
    }
    
    return context;
  }
  
  public IOpenApiObject getModel(URL url)
  {
    return modelMap_.get(url);
  }
  
  private class TemplateModelConsumer implements Consumer<ITemplateEntity>
  {
    private Map<String, List<ITemplateEntity>> map_ = new HashMap<>();
    
    @Override
    public synchronized void accept(ITemplateEntity model)
    {
      for(String template : model.getTemaplates())
      {
        List<ITemplateEntity> list = map_.get(template);
        
        if(list == null)
        {
          list = new LinkedList<>();
          map_.put(template, list);
        }
        
        list.add(model);
      }
    }

    void generate() throws GenerationException
    {
      for(String templateGroup : map_.keySet())
      {
        log_.info("Process template " + templateGroup + "...");
        
        for(ITemplateEntity model : map_.get(templateGroup))
        {
          log_.info("Process template " + templateGroup + ", model " + model.getName() + "...");
          
          IGeneratorModelContext modelContext = model.getGeneratorModelContext();
          ICanonGenerator generator = modelContext.getGenerator();
          
          for(TemplateType templateType : TemplateType.values())
          {
            Set<String> templateNames = generator.getTemplatesFor(templateType, templateGroup);
            
            for(String templateName : templateNames)
            {
              generate(generator, modelContext, model, templateType, templateName);
              
            }
          }
        }
      }
    }

    private void generate(ICanonGenerator generator, IGeneratorModelContext modelContext, ITemplateEntity entity,
        TemplateType templateType, String templateName) throws GenerationException
    {
      IPathNameConstructor pathBuilder = modelContext.getPathBuilder(templateType);
      Configuration freemarkerConfig = generator.getFreemarkerConfig();
      
      
      log_.debug("Generate generate {} {}", entity.getName(), templateName);
      
      File templateFile = new File(templateName);
      
      //dataModel.put(Canon.TEMPLATE_NAME, templateName);
      
      String  targetFileName = pathBuilder.constructFile(templateFile.getName(), entity);
      
      if(targetFileName != null)
      {
        log_.debug("targetFileName " + targetFileName);
        
        try
        {
          
          Template template = freemarkerConfig.getTemplate(templateName);
//          TemplateModel model = new TemplateModel(modelContext, templateName,
//              entity);
          
          generate(TemplateModel.newTemplateModel(modelContext, templateName, entity), template, targetFileName);

        } catch (IOException e)
        {
          throw new GenerationException("ERROR processing " + targetFileName + " template " +
              templateName, e);
        }
      }
    }
    
    private void generate(Map<String, Object> model, Template template,
        String targetFileName) throws GenerationException
    {
      File genPath = new File(getTargetDir(), targetFileName);
      
      genPath.getParentFile().mkdirs();
      
      try(FileWriter writer = new FileWriter(genPath))
      {
        template.process(model, writer);
      } 
      catch (TemplateException | IOException e)
      {
        //dumpMap("", dataModel, new HashSet<Object>());
        
        throw new GenerationException(e);
      }
      
      if(genPath.length() == 0L)
      {
        genPath.delete();
      }
      else if(getCopyDir() != null)
      {
        File copyPath = new File(getCopyDir(), targetFileName);
      
        if(copyPath.exists())
        {
          log_.info("Proforma " + copyPath.getAbsolutePath() + " exists, not copying");
        }
        else
        {
          copyPath.getParentFile().mkdirs();
          try
          {
            Files.copy(genPath, copyPath);
          }
          catch (IOException e)
          {
            throw new GenerationException(e);
          }
        }
      }
    }
  }
  
  public void generate() throws GenerationException
  {
    TemplateModelConsumer consumer = new TemplateModelConsumer();
   
    for(ModelContext context : generateQueue_)
    {
      for(ICanonGenerator generator : generators_)
      {
        IJsonObject<?> generatorConfig = context.getModel().getXCanonGenerators().getJsonObject().getRequiredObject(generator.getLanguage());
        
        IGeneratorModelContext generatorModelContext = generator.createModelContext(context, generatorConfig);
        
        context.getModel().generate(generatorModelContext, this, consumer);
        //generator.generate(context.getModel(), context, this, consumer);
      }
    }
    
    
    System.err.println("NOW RUN FREEMARKER GENERATION.....");
    
    consumer.generate();
  }

  public boolean getTemplateDebug()
  {
    return templateDebug_;
  }
  
//  public void visitAllModels(IModelVisitor visitor)
//  {
//    for(OpenApiObject model : generateQueue_)
//    {
//      visitor.visit(model);
//    }
//  }
}