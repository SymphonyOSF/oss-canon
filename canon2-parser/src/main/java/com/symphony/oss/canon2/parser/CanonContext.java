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
import java.util.Map.Entry;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Files;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon2.model.CanonCardinality;
import com.symphony.oss.canon2.model.CanonModel;
import com.symphony.oss.canon2.model.GenerationException;
import com.symphony.oss.canon2.model.ICanonContext;
import com.symphony.oss.canon2.model.IModelContext;
import com.symphony.oss.canon2.model.OpenApiObject;
import com.symphony.oss.canon2.model.ResolvedSchema;
import com.symphony.oss.canon2.model.SchemaInfo;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;
import com.symphony.oss.commons.fault.CodingFault;
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
public class CanonContext implements ICanonContext
{
  private static Logger                        log_                = LoggerFactory.getLogger(CanonContext.class);

  private final File                           targetDir_;
  private final File                           proformaDir_;
  private final File                           copyDir_;
  private final ImmutableList<ICanonGenerator> generators_;
  private final ImmutableMap<String, String>   uriMap_;
  private final boolean                        templateDebug_;
  
  private final ModelRegistry                  modelRegistry_      = new ModelRegistry.Builder()
      .withFactories(CanonModel.FACTORIES)
      .build();

  private Map<URL, ModelContext>               generationContexts_ = new HashMap<>();
  private Map<URL, ModelContext>               referencedContexts_ = new HashMap<>();
  private Deque<ModelContext>                  parseQueue_         = new LinkedList<>();
  private Deque<ModelContext>                  validateQueue_      = new LinkedList<>();
  private Deque<ModelContext>                  generateQueue_      = new LinkedList<>();
  private Map<URL, OpenApiObject>              modelMap_           = new HashMap<>();
  private Map<String, ResolvedSchema.Builder>  schemaMap_          = new HashMap<>();
  
  private CanonContext(AbstractBuilder<?,?> builder)
  {
    log_.info("GenerationContext created");
    
    targetDir_ = builder.targetDir_;
    proformaDir_ = builder.proformaDir_;
    copyDir_ = builder.copyDir_;
    generators_ = ImmutableList.copyOf(builder.generators_);
    uriMap_ = ImmutableMap.copyOf(builder.uriMap_);
    templateDebug_ = builder.templateDebug_;
  }
  
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends CanonContext> extends BaseAbstractBuilder<T,B>
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
  
  public static class Builder extends AbstractBuilder<Builder, CanonContext>
  {
    public Builder()
    {
      super(Builder.class);
    }

    @Override
    protected CanonContext construct()
    {
      return new CanonContext(this);
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

  @Override
  public void resolve(SchemaInfo info)
  {
    if(schemaMap_.put(info.getUri(), info) == null)
    {
      info.resolve(this);
    }
  }
  
  @Override
  public SchemaInfo getSchemaInfo(String absoluteUri)
  {
    return schemaMap_.get(absoluteUri);
  }

  @Override
  public ModelRegistry getModelRegistry()
  {
    return modelRegistry_;
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
  
  public void process() throws GenerationException
  {
    ModelContext context;
    OpenApiObject model;
    
    while((context = parseQueue_.pollFirst()) != null)
    {
      try
      {
        model = context.parse(modelRegistry_);
      }
      catch(IllegalStateException e)
      {
        throw new GenerationException("Failed to parse model", e);
      }
      validateQueue_.add(context);
      modelMap_.put(context.getUrl(), model);
      
      model.fetchReferences(this);
    }
    
    while((context = validateQueue_.pollFirst()) != null)
    {
      validate(context);
    }
  }

  void validate(ModelContext context) throws GenerationException
  {
    OpenApiObject model = context.getModel();
    model.resolve(this, context);
    
    model.validate(this);
    
    if(context.printErrors())
      throw new GenerationException("Generation failed for " + context.getInputSourceName());
    
    if(!context.isReferencedModel())
    {
      generateQueue_.add(context);
    }
  }

  @Override
  public void addReferencedModel(URL url) throws GenerationException
  {
    if(url != null)
    {
      ModelContext context = referencedContexts_.get(url);
      
      if(context == null)
      {
        context = new ModelContext(this, url, true, uriMap_);
        referencedContexts_.put(url, context);
        parseQueue_.add(context);
      }
    }
  }
  
  @Override
  public IModelContext getReferencedModel(URL url)
  {
    return referencedContexts_.get(url);
  }
  
  public OpenApiObject getModel(URL url)
  {
    return modelMap_.get(url);
  }
  
  private class TemplateModelContext<
  T extends ITemplateModel<T,M,S>,
  M extends IOpenApiTemplateModel<T,M,S>,
  S extends ISchemaTemplateModel<T,M,S>,
  O extends IObjectSchemaTemplateModel<T,M,S,F>,
  A extends IArraySchemaTemplateModel<T,M,S>,
  P extends IPrimitiveSchemaTemplateModel<T,M,S>,
  F extends IFieldTemplateModel<T,M,S>>
  {
    private final IGeneratorModelContext<T,M,S,O,A,P,F> context_;
    private final T model_;
    
    private TemplateModelContext(IGeneratorModelContext<T,M,S,O,A,P,F> context,
        T model)
    {
      context_ = context;
      model_ = model;
    }

    private IGeneratorModelContext<T,M,S,O,A,P,F> getContext()
    {
      return context_;
    }

    private T getModel()
    {
      return model_;
    }
  }
  
  private class TemplateModelConsumer implements Consumer<TemplateModelContext<?,?,?,?,?,?,?>>
  {
    private Map<String, List<TemplateModelContext<?,?,?,?,?,?,?>>> map_ = new HashMap<>();
    
    @Override
    public synchronized void accept(TemplateModelContext<?,?,?,?,?,?,?> modelContext)
    {
      if(modelContext.getModel().getTemaplates() != null)
      {
        for(String template : modelContext.getModel().getTemaplates())
        {
          List<TemplateModelContext<?,?,?,?,?,?,?>> list = map_.get(template);
          
          if(list == null)
          {
            list = new LinkedList<>();
            map_.put(template, list);
          }
          
          list.add(modelContext);
        }
      }
    }

   
    void generate() throws GenerationException
    {
      for(String templateGroup : map_.keySet())
      {
        log_.info("Process template " + templateGroup + "...");
        
        for(TemplateModelContext<?,?,?,?,?,?,?> modelContext : map_.get(templateGroup))
        {
          generateModel(modelContext, templateGroup);
        }
      }
    }
    
    private <
    T extends ITemplateModel<T,M,S>,
    M extends IOpenApiTemplateModel<T,M,S>,
    S extends ISchemaTemplateModel<T,M,S>,
    O extends IObjectSchemaTemplateModel<T,M,S,F>,
    A extends IArraySchemaTemplateModel<T,M,S>,
    P extends IPrimitiveSchemaTemplateModel<T,M,S>,
    F extends IFieldTemplateModel<T,M,S>
    >
    void generateModel(TemplateModelContext<T,M,S,O,A,P,F> modelContext, String templateGroup) throws GenerationException
    {
      new Helper<T,M,S,O,A,P,F>(modelContext).generateModel(templateGroup);
    }

    private  class Helper
    <
    T extends ITemplateModel<T,M,S>,
    M extends IOpenApiTemplateModel<T,M,S>,
    S extends ISchemaTemplateModel<T,M,S>,
    O extends IObjectSchemaTemplateModel<T,M,S,F>,
    A extends IArraySchemaTemplateModel<T,M,S>,
    P extends IPrimitiveSchemaTemplateModel<T,M,S>,
    F extends IFieldTemplateModel<T,M,S>
    >
    {
      private TemplateModelContext<T,M,S,O,A,P,F> modelContext_;

      private Helper(TemplateModelContext<T,M,S,O,A,P,F> modelContext)
      {
        modelContext_ = modelContext;
      }

      void generateModel(String templateGroup) throws GenerationException
      {
  
        log_.info("Process template " + templateGroup + ", model " + modelContext_.getModel().getName() + "...");
        
        IGeneratorModelContext<T,M,S,O,A,P,F> generatorModelContext = modelContext_.getContext();
        ICanonGenerator<T,M,S,O,A,P,F> generator = generatorModelContext.getGenerator();
        
        for(TemplateType templateType : TemplateType.values())
        {
          Set<String> templateNames = generator.getTemplatesFor(templateType, templateGroup);
          
          for(String templateName : templateNames)
          {
            generate(generator, generatorModelContext, modelContext_.getModel(), templateType, templateName);
            
          }
        }
      }
  
  
      private void generate(ICanonGenerator<T,M,S,O,A,P,F> generator, IGeneratorModelContext<T,M,S,O,A,P,F> modelContext, T entity,
          TemplateType templateType, String templateName) throws GenerationException
      {
        IPathNameConstructor<T> pathBuilder = modelContext.getPathBuilder(templateType);
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
            
            generate(FreemarkerModel.newTemplateModel(modelContext, templateName, entity), templateType, template, targetFileName);
  
          } catch (IOException e)
          {
            throw new GenerationException("ERROR processing " + targetFileName + " template " +
                templateName, e);
          }
        }
      }
      
      private void generate(Map<String, Object> model, TemplateType templateType, Template template,
          String targetFileName) throws GenerationException
      {
        File genPath = new File(templateType == TemplateType.TEMPLATE ? getTargetDir() : getProformaDir(), targetFileName);
        
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
        else if(templateType == TemplateType.PROFORMA && getCopyDir() != null)
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
  }
  
  public void generate() throws GenerationException
  {
    TemplateModelConsumer consumer = new TemplateModelConsumer();
   
    for(ModelContext context : generateQueue_)
    {
      for(ICanonGenerator generator : generators_)
      {
        new GenerationHelper(context, generator, consumer).generateFor();
      }
    }
    
    
    System.err.println("NOW RUN FREEMARKER GENERATION.....");
    
    consumer.generate();
  }

  private class GenerationHelper
  <
  T extends ITemplateModel<T,M,S>,
  M extends IOpenApiTemplateModel<T,M,S>,
  S extends ISchemaTemplateModel<T,M,S>,
  O extends IObjectSchemaTemplateModel<T,M,S,F>,
  A extends IArraySchemaTemplateModel<T,M,S>,
  P extends IPrimitiveSchemaTemplateModel<T,M,S>,
  F extends IFieldTemplateModel<T,M,S>>
  {
    ModelContext context;
    ICanonGenerator<T,M,S,O,A,P,F> generator;
    TemplateModelConsumer consumer;

    GenerationHelper(ModelContext context, ICanonGenerator<T,M,S,O,A,P,F> generator, TemplateModelConsumer consumer)
    {
      this.context = context;
      this.generator = generator;
      this.consumer = consumer;
    }

    void generateFor() throws GenerationException
    {
      JsonObject generatorConfig = context.getModel().getXCanonGenerators().getJsonObject().getRequiredObject(generator.getLanguage());
    
      IGeneratorModelContext<T,M,S,O,A,P,F> generatorModelContext = generator.createModelContext(CanonContext.this, context, generatorConfig);
    
      M templateModel = generatorModelContext.generateModel();
      
      if(context.printErrors())
        throw new GenerationException("Generation failed for " + context.getInputSourceName());
    
      gather(generatorModelContext, templateModel.asTemplateModel(), consumer);
      //generator.generate(context.getModel(), context, this, consumer);

    }

    private void gather(IGeneratorModelContext<T,M,S,O,A,P,F> generatorModelContext, T model, TemplateModelConsumer consumer)
    {
      consumer.accept(new TemplateModelContext<T,M,S,O,A,P,F>(generatorModelContext, model));
      
      for(T child : model.getChildren())
        gather(generatorModelContext, child, consumer);
    }
  }

  public boolean getTemplateDebug()
  {
    return templateDebug_;
  }
}
