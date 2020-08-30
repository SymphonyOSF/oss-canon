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

package com.symphony.oss.canon2.parser;

import java.util.Collection;

import com.google.common.collect.ImmutableList;

/**
 * A model for generation of one or more Freemarker templates.
 * 
 * @author Bruce Skingle
 *
 */
public abstract class TemplateModel<S extends ISchemaTemplateModel<S>, T extends ICanonModelEntity> implements ITemplateModel
{
  
  
  protected T entity_;
  
  private final IGeneratorModelContext<S> generatorModelContext_;
  private final String[]               templates_;
  private final String                 name_;
  private final String                 camelName_;
  private final String                 camelCapitalizedName_;
  private final String                 snakeName_;
  private final String                 snakeCapitalizedName_;
  private final ITemplateModel         model_;

  public TemplateModel(T entity, String name, ITemplateModel model, IGeneratorModelContext<S> generatorModelContext, String ...templates)
  {
    entity_ = entity;
    generatorModelContext_ = generatorModelContext;
    model_ = model ;
    name_ = name;
    templates_ = templates;

    
    camelName_ = toCamelCase(name_);
    camelCapitalizedName_ = capitalize(camelName_);
    snakeName_ = toSnakeCase(name_);
    snakeCapitalizedName_ = capitalize(snakeName_);
  }
  
  @Override
  public IGeneratorModelContext getGeneratorModelContext()
  {
    return generatorModelContext_;
  }

  @Override
  public String[] getTemaplates()
  {
    return templates_;
  }

  private String toCamelCase(String name)
  {
    int i=0;
    StringBuilder s = new StringBuilder();
    
    while(i<name.length() && name.charAt(i)=='_')
    {
      s.append('_');
      i++;
    }
    
    if(i<name.length())
    {
      s.append(Character.toLowerCase(name.charAt(i++)));
    }
    
    while(i<name.length())
    {
      char c = name.charAt(i++);
   
      if((c=='_' || c=='-') && i<name.length())
      {
        s.append(Character.toUpperCase(name.charAt(i++)));
      }
      else
      {
        s.append(c);
      }
    }
    return s.toString();
  }
  
  private String toSnakeCase(String name)
  {
    int i=0;
    StringBuilder s = new StringBuilder(Character.toLowerCase(name.charAt(i)));
    
    while(i<name.length())
    {
      char c = name.charAt(i++);
      
      if(Character.isUpperCase(c))
      {
        if(i>1)
          s.append('_');
        s.append(Character.toLowerCase(c));
      }
      else
      {
        s.append(c);
      }
    }
    return s.toString();
  }

  private static String capitalize(String name)
  {
    return name.substring(0, 1).toUpperCase() + name.substring(1);
  }

  @Override
  public ITemplateModel getModel()
  {
    return model_;
  }
  
  @Override
  public String getName()
  {
    return name_;
  }

  @Override
  public String getCamelName()
  {
    return camelName_;
  }

  @Override
  public String getCamelCapitalizedName()
  {
    return camelCapitalizedName_;
  }

  @Override
  public String getSnakeName()
  {
    return snakeName_;
  }

  @Override
  public String getSnakeCapitalizedName()
  {
    return snakeCapitalizedName_;
  }
}
