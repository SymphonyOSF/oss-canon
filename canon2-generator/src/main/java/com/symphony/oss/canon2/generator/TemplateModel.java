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

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon2.core.ResolvedEntity;

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
  /** The empty list of template names */
  public static final List<String> EMPTY_TEMPLATES = ImmutableList.of();

  private final String                                                 name_;
  private final String                                                 quotedName_;
  private final String                                                 identifier_;
  private final String                                                 camelName_;
  private final String                                                 camelCapitalizedName_;
  private final String                                                 snakeName_;
  private final String                                                 snakeCapitalizedName_;
  private final String                                                 snakeUpperCaseName_;
  private final M                                                      model_;
  private final List<String>                                           templates_;
  private final boolean                                                external_;
  private final JsonDomNode                                            json_;
  private final CanonGenerator<T, M, S, ?, ?, ?, ?, ?>.AbstractContext generatorContext_;


  /**
   * Constructor.
   * 
   * @param generatorContext  Contains the source context for error reporting. 
   * @param name              The name of the entity represented by this model as defined in the OpenApi document.
   * @param identifier        The identifier used for this entity in generated code.
   * @param resolvedEntity    The resolvedSchema object from the OpenApi model.
   * @param model             The IOpenApiTemplateModel to which this entity belongs.
   * @param templates         The list of templates to be called for this model.
   * 
   * The reason we have name and identifier is that the name may be valid in the JSON input spec but a reserved word in the
   * target generated language.
   */
  public TemplateModel(CanonGenerator<T,M,S,?,?,?,?,?>.AbstractContext generatorContext, String name, String identifier, ResolvedEntity resolvedEntity, M model, List<String> templates)
  {
    generatorContext_     = generatorContext;
    model_                = model ;
    name_                 = name;
    quotedName_           = name.replaceAll("\\\"", "\\\\\"");
    identifier_           = identifier;
    templates_            = templates;
    
    external_             = model != null && (resolvedEntity.getResolvedOpenApiObject() != model.getResolvedOpenApiObject());
    json_                 = resolvedEntity.getJson();
  
    camelName_            = toCamelCase(getIdentifier());
    camelCapitalizedName_ = capitalize(camelName_);
    snakeName_            = toSnakeCase(getIdentifier());
    snakeCapitalizedName_ = capitalize(snakeName_);
    snakeUpperCaseName_   = snakeName_.toUpperCase();
  }
  
  @Override
  public final String getIdentifier()
  {
    return identifier_;
  }

  @Override
  public final List<String> getTemplates()
  {
    return templates_;
  }

  /**
   * Return the canon ID string.
   * 
   * Identifier names may not begin or end with this string and is used to construct secondary names in generated code.
   * The default value is _.
   * 
   * For example, an entity with the name MyObject might generate files
   *   MyObject.java
   *   I_MyObject.java
   *   MyObject_Entity.java
   *   
   * This prevents name collisions, for example if a model contained entities with the names MyObject and MyObjectEntity
   * then without this mechanism the name MyObjectEntity.java would be generated for both entities in the model.
   * 
   * The value of this string can be overridden in the model spec with canonIdString in the generator config block.
   * 
   * @return the canon ID string.
   */
  public String getCanonIdString()
  {
    return generatorContext_.getCanonIdString();
  }

  @Override
  public JsonDomNode getJson()
  {
    return json_;
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

  /**
   * Return the name of this model entity as written in the input spec, with double quotes escaped.
   * 
   * This is intended for use in a quoted context.
   * 
   * @return The name of this model entity as written in the input spec with double quotes escaped.
   */
  public String getQuotedName()
  {
    return quotedName_;
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

  /**
   * Return the name of this model entity in Snake_case in all upper case.
   * 
   * @return The name of this model entity in Snake_case in all upper case.
   */
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
