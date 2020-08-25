/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon2.generator.java;

import java.util.Set;
import java.util.TreeSet;

import com.symphony.oss.canon2.parser.IGeneratorModelContext;
import com.symphony.oss.canon2.parser.ISchema;
import com.symphony.oss.canon2.parser.ITemplateEntity;
import com.symphony.oss.canon2.parser.TemplateEntity;

public class JavaObjectEntity extends TemplateEntity<ISchema>
{
  private Set<String> imports_ = new TreeSet<>();
  
  JavaObjectEntity(ISchema entity, String name, ITemplateEntity model, IGeneratorModelContext generatorModelContext,
       String... temaplates)
  {
    super(entity, name, model, generatorModelContext, temaplates);
    
    imports_.add("javax.annotation.concurrent.Immutable");
    imports_.add("javax.annotation.Nullable");
  }

  public Set<String> getImports()
  {
    return imports_;
  }
}
