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

import com.symphony.oss.canon2.core.IResolvedEntity;

/**
 * Base implementation of ITemplateModel which provides a set of methods returning the entity name mapped
 * to various case conventions.
 * 
 * @author Bruce Skingle
 * 
 * @param <T> The concrete type of ITemplateModel
 * @param <M> The concrete type of IOpenApiTemplateModel
 * @param <S> The concrete type of ISchemaTemplateModel
 *
 */
public abstract class TemplateModel<
T extends ITemplateModel<T,M,S>,
M extends IOpenApiTemplateModel<T,M,S>,
S extends ISchemaTemplateModel<T,M,S>
>
implements ITemplateModel<T,M,S>
{
  private final String[] templates_;
  private final String   name_;
  private final String   quotedName_;
  private final String   camelName_;
  private final String   camelCapitalizedName_;
  private final String   snakeName_;
  private final String   snakeCapitalizedName_;
  private final String   snakeUpperCaseName_;
  private final M        model_;
  private final boolean  external_;

  /**
   * Constructor.
   * 
   * @param name      The name of the entity represented by this model.
   * @param resolvedEntity 
   * @param model     The IOpenApiTemplateModel to which this entity belongs.
   * @param templates The list of template names which should be run against this model.
   */
  public TemplateModel(String name, IResolvedEntity resolvedEntity, String identifier, M model, String ...templates)
  {
    model_ = model ;
    name_ = name;
    quotedName_ = name.replaceAll("\\\"", "\\\\\"");
    templates_ = templates;

    camelName_ = toCamelCase(identifier);
    camelCapitalizedName_ = capitalize(camelName_);
    snakeName_ = toSnakeCase(identifier);
    snakeCapitalizedName_ = capitalize(snakeName_);
    snakeUpperCaseName_ = snakeName_.toUpperCase();
    
    external_ = model != null && (resolvedEntity.getResolvedOpenApiObject() != model.getResolvedOpenApiObject());
  }

  /**
   * Return true if this entity is not part of the generated model.
   * 
   * @return true if this entity is not part of the generated model.
   */
  public boolean isExternal()
  {
    return external_;
  }

  @Override
  public String[] getTemaplates()
  {
    return templates_;
  }

  /**
   * Convert the given name to camelCase
   * 
   * @param name a name.
   * 
   * @return the given name to camelCase
   */
  public static String toCamelCase(String name)
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
  
  /**
   * Convert the given name to snake_case
   * 
   * @param name a name.
   * 
   * @return the given name to snake_case
   */
  public static String toSnakeCase(String name)
  {
    int i=0;
    StringBuilder s = new StringBuilder(Character.toLowerCase(name.charAt(i)));
    boolean startWord=true;
    while(i<name.length())
    {
      char c = name.charAt(i++);
      
      if(Character.isUpperCase(c))
      {
        if(!startWord)
          s.append('_');
        s.append(Character.toLowerCase(c));
        startWord=true;
      }
      else
      {
        s.append(c);
        startWord=false;
      }
    }
    return s.toString();
  }

  /**
   * Return the given name with the initial letter capitalised.
   * 
   * @param name A name.
   * 
   * @return the given name with the initial letter capitalised.
   */
  public static String capitalize(String name)
  {
    return name.substring(0, 1).toUpperCase() + name.substring(1);
  }

  @Override
  public M getModel()
  {
    return model_;
  }
  
  @Override
  public String getName()
  {
    return name_;
  }

  public String getQuotedName()
  {
    return quotedName_;
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

  @Override
  public String getSnakeUpperCaseName()
  {
    return snakeUpperCaseName_;
  }

  @Override
  public String toString()
  {
    return getName();
  }
}
