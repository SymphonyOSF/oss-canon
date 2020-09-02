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
 * Base implementation of ITemplateModel which provides a set of methods returning the entity name mapped
 * to various case conventions.
 * 
 * @author Bruce Skingle
 *
 */
public abstract class TemplateModel<
T extends ITemplateModel<T,M,S,O,A,P>,
M extends IOpenApiTemplateModel<T,M,S,O,A,P>,
S extends ISchemaTemplateModel<T,M,S,O,A,P>,
O extends IObjectSchemaTemplateModel<T,M,S,O,A,P>,
A extends IArraySchemaTemplateModel<T,M,S,O,A,P>,
P extends IPrimitiveSchemaTemplateModel<T,M,S,O,A,P>,
E extends ICanonModelEntity>
{
  protected E entity_;
  
  private final IGeneratorModelContext<T,M,S,O,A,P> generatorModelContext_;
  private final String[]               templates_;
  private final String                 name_;
  private final String                 camelName_;
  private final String                 camelCapitalizedName_;
  private final String                 snakeName_;
  private final String                 snakeCapitalizedName_;
  private final M         model_;

  public TemplateModel(E entity, String name, M model, IGeneratorModelContext<T,M,S,O,A,P> generatorModelContext, String ...templates)
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
  
  public IGeneratorModelContext<T,M,S,O,A,P> getGeneratorModelContext()
  {
    return generatorModelContext_;
  }

  /**
   * Return the list of template names which should be run against this model.
   * 
   * @return The list of template names which should be run against this model.
   */
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

  /**
   * Return the OpenApiObject template model.
   * 
   * @return The OpenApiObject template model.
   */
  public M getModel()
  {
    return model_;
  }
  
  /**
   * Return the name of this model entity as written in the input spec.
   * 
   * @return The name of this model entity as written in the input spec.
   */
  public String getName()
  {
    return name_;
  }

  /**
   * Return the name of this model entity in camelCase with a lower case initial letter.
   * 
   * @return The name of this model entity in camelCase with a lower case initial letter.
   */
  public String getCamelName()
  {
    return camelName_;
  }

  /**
   * Return the name of this model entity in CamelCase with an upper case initial letter.
   * 
   * @return The name of this model entity in CamelCase with an upper case initial letter.
   */
  public String getCamelCapitalizedName()
  {
    return camelCapitalizedName_;
  }

  /**
   * Return the name of this model entity in snake_case with a lower case initial letter.
   * 
   * @return The name of this model entity in snake_case with a lower case initial letter.
   */
  public String getSnakeName()
  {
    return snakeName_;
  }
  
  /**
  * Return the name of this model entity in Snake_case with an upper case initial letter.
  * 
  * @return The name of this model entity in Snake_case with an upper case initial letter.
  */
  public String getSnakeCapitalizedName()
  {
    return snakeCapitalizedName_;
  }

//  public abstract Collection<S> getChildren();
}
