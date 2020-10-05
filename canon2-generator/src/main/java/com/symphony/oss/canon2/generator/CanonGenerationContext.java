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

package com.symphony.oss.canon2.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;
import com.symphony.oss.canon2.model.CanonModelContext;
import com.symphony.oss.canon2.model.GenerationException;
import com.symphony.oss.canon2.model.SourceContext;

/**
 * Concrete implementation of CanonModelContext for a processing run of the canon code generator.
 * 
 * Includes all generators specified for the run.
 * Includes the models specifically included for generation as well as referenced models.
 * 
 * @author Bruce Skingle
 *
 */
public class CanonGenerationContext extends CanonModelContext
{
  private static Logger                        log_                = LoggerFactory.getLogger(CanonGenerationContext.class);

  private final File                           targetDir_;
  private final File                           proformaDir_;
  private final File                           copyDir_;
  private final ImmutableList<ICanonGenerator<?,?,?,?,?,?,?>> generators_;
  private final boolean                        templateDebug_;
  
  private CanonGenerationContext(AbstractBuilder<?,?> builder)
  {
    super(builder);
    
    log_.info("CanonGeneratorContext created");
    
    targetDir_ = builder.targetDir_;
    proformaDir_ = builder.proformaDir_;
    copyDir_ = builder.copyDir_;
    generators_ = ImmutableList.copyOf(builder.generators_);
    templateDebug_ = builder.templateDebug_;
  }
  
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends CanonGenerationContext> extends CanonModelContext.AbstractBuilder<T,B>
  {
    private File                  targetDir_;
    private File                  proformaDir_;
    private File                  copyDir_;
    private List<ICanonGenerator<?,?,?,?,?,?,?>> generators_ = new LinkedList<>();
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

    public T withGenerators(List<ICanonGenerator<?,?,?,?,?,?,?>> generators)
    {
      generators_ = generators;
      
      return self();
    }

    public T withGenerator(ICanonGenerator<?,?,?,?,?,?,?> generator)
    {
      generators_.add(generator);
      
      return self();
    }

    public T withTemplateDebug(boolean templateDebug)
    {
      templateDebug_ = templateDebug;
      
      return self();
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
  }
  
  public static class Builder extends AbstractBuilder<Builder, CanonGenerationContext>
  {
    public Builder()
    {
      super(Builder.class);
    }

    @Override
    protected CanonGenerationContext construct()
    {
      return new CanonGenerationContext(this);
    }
  }
  
  public boolean getTemplateDebug()
  {
    return templateDebug_;
  }


  public List<com.symphony.oss.canon2.generator.ICanonGenerator<?, ?, ?, ?, ?, ?, ?>> getGenerators()
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


  @Override
  protected void process(Deque<SourceContext> processQueue) throws GenerationException
  {
    List<GeneratorTemplateProcessor<?,?,?,?,?,?,?>> templateProcessors = new ArrayList<>(generators_.size());
    
    for(ICanonGenerator<?,?,?,?,?,?,?> generator : generators_)
    {
       templateProcessors.add(new GeneratorTemplateProcessor<>(generator));
    }
    
    for(SourceContext sourceContext : processQueue)
    {
      for(GeneratorTemplateProcessor<?,?,?,?,?,?,?> templateProcessor : templateProcessors)
      {
        templateProcessor.process(this, sourceContext);
        
      }
    }
    
    for(GeneratorTemplateProcessor<?,?,?,?,?,?,?> templateProcessor : templateProcessors)
    {
      templateProcessor.generate();
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
//  GeneratorContext<T,M,S,O,A,P,F> createGeneratorContext(ICanonGenerator<T,M,S,O,A,P,F> generator, SourceContext sourceContext)
//  {
//    return new GeneratorContext<T,M,S,O,A,P,F>(this, generator, sourceContext);
//  }
  
//  private <
//  T extends ITemplateModel<T,M,S>,
//  M extends IOpenApiTemplateModel<T,M,S>,
//  S extends ISchemaTemplateModel<T,M,S>,
//  O extends IObjectSchemaTemplateModel<T,M,S,F>,
//  A extends IArraySchemaTemplateModel<T,M,S>,
//  P extends IPrimitiveSchemaTemplateModel<T,M,S>,
//  F extends IFieldTemplateModel<T,M,S>
//  >
//  void process(SourceContext context, ICanonGenerator<T,M,S,O,A,P,F> generator,
//      TemplateModelConsumer consumer)
//  {
//    new GeneratorContext<T,M,S,O,A,P,F>(generator, this, context, consumer).generateFor();
//  }
}
