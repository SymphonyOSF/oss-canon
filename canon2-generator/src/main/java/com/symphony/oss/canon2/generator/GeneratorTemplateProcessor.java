/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon2.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Files;
import com.symphony.oss.canon2.core.GenerationException;
import com.symphony.oss.canon2.core.SourceContext;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Responsible for calling Freemarker.
 * 
 * One instance per generator for all source models.
 * 
 * @author Bruce Skingle
 *
 */
class GeneratorTemplateProcessor<
T extends ITemplateModel<T,M,S>,
M extends IOpenApiTemplateModel<T,M,S>,
S extends ISchemaTemplateModel<T,M,S>,
O extends IObjectSchemaTemplateModel<T,M,S,F>,
A extends IArraySchemaTemplateModel<T,M,S>,
P extends IPrimitiveSchemaTemplateModel<T,M,S>,
F extends IFieldTemplateModel<T,M,S>,
G extends IGroupSchemaTemplateModel<T,M,S>>
{
  private static Logger log_ = LoggerFactory.getLogger(GeneratorTemplateProcessor.class);
  
  private final ICanonGenerator<T,M,S,O,A,P,F,G>  generator_;
  private final Map<String, List<TemplateContext>> map_ = new HashMap<>();
  
  GeneratorTemplateProcessor(ICanonGenerator<T,M,S,O,A,P,F,G> generator)
  {
    generator_ = generator;
  }

  public ICanonGenerator<T,M,S,O,A,P,F,G> getGenerator()
  {
    return generator_;
  }
  
  public void process(CanonGenerationContext canonGenerationContext, SourceContext sourceContext) throws GenerationException
  {
    GeneratorContext<T,M,S,O,A,P,F,G> generatorContext = new GeneratorContext<>(canonGenerationContext, generator_, sourceContext);
    
    generatorContext.process(this);
  }
  
  private class TemplateContext
  {
    private GeneratorContext<T,M,S,O,A,P,F,G> generatorContext_;
    private T templateModel_;
    
    private TemplateContext(GeneratorContext<T,M,S,O,A,P,F,G> generatorContext, T templateModel)
    {
      generatorContext_ = generatorContext;
      templateModel_ = templateModel;
    }
  }

  public synchronized void accept(GeneratorContext<T,M,S,O,A,P,F,G> generatorContext, T templateModel)
  {
    if(templateModel.getTemaplates() != null)
    {
      for(String template : templateModel.getTemaplates())
      {
        List<TemplateContext> list = map_.get(template);
        
        if(list == null)
        {
          list = new LinkedList<>();
          map_.put(template, list);
        }
        
        list.add(new TemplateContext(generatorContext, templateModel));
      }
    }
  }

 
  void generate() throws GenerationException
  {
    for(String templateGroup : map_.keySet())
    {
      log_.info("Process template " + templateGroup + "...");
      
      for(TemplateContext modelContext : map_.get(templateGroup))
      {
//        generateModel(modelContext, templateGroup);
        
        log_.info("Process template " + templateGroup + ", model " + modelContext.templateModel_.getName() + "...");
        
        GeneratorContext<?,?,?,?,?,?,?,?> generatorModelContext = modelContext.generatorContext_;
        ICanonGenerator<?,?,?,?,?,?,?,?> generator = generatorModelContext.getGenerator();
        
        for(TemplateType templateType : TemplateType.values())
        {
          Set<String> templateNames = generator.getTemplatesFor(templateType, templateGroup);
          
          for(String templateName : templateNames)
          {
            generate(modelContext, templateType, templateName);
            
          }
        }
      }
    }
  }
  
  private void generate(TemplateContext templateContext,
      TemplateType templateType, String templateName) throws GenerationException
  {
    IPathNameConstructor<T> pathBuilder = templateContext.generatorContext_.getPathBuilder();
    Configuration freemarkerConfig = generator_.getFreemarkerConfig();
    T entity = templateContext.templateModel_;
    
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
  //      TemplateModel model = new TemplateModel(modelContext, templateName,
  //          entity);
        
        generate(newTemplateModel(templateContext.generatorContext_, templateName, templateType, entity), templateType, template, targetFileName, templateContext.generatorContext_.getGenerationContext());
  
      } catch (IOException e)
      {
        throw new GenerationException("ERROR processing " + targetFileName + " template " +
            templateName, e);
      }
    }
  }
  
  Map<String, Object> newTemplateModel(GeneratorContext<T,M,S,O,A,P,F,G> modelContext, String templateName, TemplateType templateType, ITemplateModel<T,M,S> entity)
  {
    Map<String, Object> map = new HashMap<>();
    
    modelContext.populateTemplateModel(map);
    
    map.put("templateName",  templateName);
    map.put("templateType",  templateType);
    map.put("model",  entity.getModel());
    map.put("entity",  entity);
    
    Date now = new Date();
    
    map.put("year",  new SimpleDateFormat("yyyy").format(now));
    map.put("yearMonth",  new SimpleDateFormat("yyyy-MM").format(now));
    map.put("date",  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").format(now));
    map.put("inputSource",  modelContext.getSourceContext().getInputSource());
    map.put("inputSourceFileName",  modelContext.getSourceContext().getInputSourceFileName());
    
    return map;
  }
  
  private void generate(Map<String, Object> model, TemplateType templateType, Template template,
      String targetFileName, CanonGenerationContext generationContext) throws GenerationException
  {
    File genPath = new File(templateType == TemplateType.TEMPLATE ? generationContext.getTargetDir() : generationContext.getProformaDir(), targetFileName);
    
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
    else if(templateType == TemplateType.PROFORMA && generationContext.getCopyDir() != null)
    {
      File copyPath = new File(generationContext.getCopyDir(), targetFileName);
    
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
  
//  private <
//  T extends ITemplateModel<T,M,S>,
//  M extends IOpenApiTemplateModel<T,M,S>,
//  S extends ISchemaTemplateModel<T,M,S>,
//  O extends IObjectSchemaTemplateModel<T,M,S,F>,
//  A extends IArraySchemaTemplateModel<T,M,S>,
//  P extends IPrimitiveSchemaTemplateModel<T,M,S>,
//  F extends IFieldTemplateModel<T,M,S>
//  >
//  void generateModel(TemplateModelContext<T,M,S,O,A,P,F,G> modelContext, String templateGroup) throws GenerationException
//  {
//    //new Helper<T,M,S,O,A,P,F,G>(modelContext).generateModel(templateGroup);
//    
//    
//  }
//
//  private  class Helper
//  <
//  T extends ITemplateModel<T,M,S>,
//  M extends IOpenApiTemplateModel<T,M,S>,
//  S extends ISchemaTemplateModel<T,M,S>,
//  O extends IObjectSchemaTemplateModel<T,M,S,F>,
//  A extends IArraySchemaTemplateModel<T,M,S>,
//  P extends IPrimitiveSchemaTemplateModel<T,M,S>,
//  F extends IFieldTemplateModel<T,M,S>
//  >
//  {
//    private TemplateModelContext<T,M,S,O,A,P,F,G> modelContext_;
//
//    private Helper(TemplateModelContext<T,M,S,O,A,P,F,G> modelContext)
//    {
//      modelContext_ = modelContext;
//    }
//
//    void generateModel(String templateGroup) throws GenerationException
//    {
//
//      log_.info("Process template " + templateGroup + ", model " + modelContext_.getModel().getName() + "...");
//      
//      GeneratorContext<T,M,S,O,A,P,F,G> generatorModelContext = modelContext_.getContext();
//      ICanonGenerator<T,M,S,O,A,P,F,G> generator = generatorModelContext.getGenerator();
//      
//      for(TemplateType templateType : TemplateType.values())
//      {
//        Set<String> templateNames = generator.getTemplatesFor(templateType, templateGroup);
//        
//        for(String templateName : templateNames)
//        {
//          generate(generator, generatorModelContext, modelContext_.getModel(), templateType, templateName);
//          
//        }
//      }
//    }
//
//
//    private void generate(ICanonGenerator<T,M,S,O,A,P,F,G> generator, GeneratorContext<T,M,S,O,A,P,F,G> modelContext, T entity,
//        TemplateType templateType, String templateName) throws GenerationException
//    {
//      IPathNameConstructor<T> pathBuilder = modelContext.getPathBuilder(templateType);
//      Configuration freemarkerConfig = generator.getFreemarkerConfig();
//      
//      
//      log_.debug("Generate generate {} {}", entity.getName(), templateName);
//      
//      File templateFile = new File(templateName);
//      
//      //dataModel.put(Canon.TEMPLATE_NAME, templateName);
//      
//      String  targetFileName = pathBuilder.constructFile(templateFile.getName(), entity);
//      
//      if(targetFileName != null)
//      {
//        log_.debug("targetFileName " + targetFileName);
//        
//        try
//        {
//          
//          Template template = freemarkerConfig.getTemplate(templateName);
////          TemplateModel model = new TemplateModel(modelContext, templateName,
////              entity);
//          
//          generate(FreemarkerModel.newTemplateModel(modelContext, templateName, entity), templateType, template, targetFileName);
//
//        } catch (IOException e)
//        {
//          throw new GenerationException("ERROR processing " + targetFileName + " template " +
//              templateName, e);
//        }
//      }
//    }
//    
//    private void generate(Map<String, Object> model, TemplateType templateType, Template template,
//        String targetFileName) throws GenerationException
//    {
//      File genPath = new File(templateType == TemplateType.TEMPLATE ? getTargetDir() : getProformaDir(), targetFileName);
//      
//      genPath.getParentFile().mkdirs();
//      
//      try(FileWriter writer = new FileWriter(genPath))
//      {
//        template.process(model, writer);
//      } 
//      catch (TemplateException | IOException e)
//      {
//        //dumpMap("", dataModel, new HashSet<Object>());
//        
//        throw new GenerationException(e);
//      }
//      
//      if(genPath.length() == 0L)
//      {
//        genPath.delete();
//      }
//      else if(templateType == TemplateType.PROFORMA && getCopyDir() != null)
//      {
//        File copyPath = new File(getCopyDir(), targetFileName);
//      
//        if(copyPath.exists())
//        {
//          log_.info("Proforma " + copyPath.getAbsolutePath() + " exists, not copying");
//          }
//          else
//          {
//            copyPath.getParentFile().mkdirs();
//            try
//            {
//              Files.copy(genPath, copyPath);
//            }
//            catch (IOException e)
//            {
//              throw new GenerationException(e);
//            }
//          }
//        }
//      }
//    }
//}