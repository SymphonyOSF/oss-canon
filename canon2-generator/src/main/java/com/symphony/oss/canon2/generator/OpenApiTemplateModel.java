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

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.symphony.oss.canon.json.ParserErrorException;
import com.symphony.oss.canon2.core.ResolvedOpenApiObject;
import com.symphony.oss.canon2.model.OpenApiObject;
import com.symphony.oss.canon2.model.SemanticVersion;

/**
 * Template model object for the OpenApi top-level model.
 * 
 * @author Bruce Skingle
 * 
 * @param <T> The concrete type of ITemplateModel
 * @param <M> The concrete type of IOpenApiTemplateModel
 * @param <S> The concrete type of ISchemaTemplateModel
 *
 */
public abstract class OpenApiTemplateModel<
T extends ITemplateModel<T,M,S>,
M extends IOpenApiTemplateModel<T,M,S>,
S extends ISchemaTemplateModel<T,M,S>>
  extends TemplateModel<T,M,S>
  implements IOpenApiTemplateModel<T,M,S>
{
  private final OpenApiObject         model_;
  private final List<S>               schemas_   = new LinkedList<>();
  private final List<T>               children_  = new LinkedList<>();
  private final Map<String, S>        schemaMap_ = new HashMap<>();

  private final Integer               canonMajorVersion_;
  private final Integer               canonMinorVersion_;
  private final ResolvedOpenApiObject resolvedOpenApiObject_;

  /**
   * Constructor.
   * 
   * @param generatorContext      Contains the source context for error reporting.
   * @param identifier            The identifier used for this entity in generated code.
   * @param resolvedOpenApiObject The resolvedSchema object from the OpenApi model.
   * @param templates             The list of templates to be called for this model.
   * 
   * The reason we have name and identifier is that the name may be valid in the JSON input spec but a reserved word in the
   * target generated language.
   */
  public OpenApiTemplateModel(CanonGenerator<T,M,S,?,?,?,?,?>.AbstractContext generatorContext,
      String identifier, ResolvedOpenApiObject resolvedOpenApiObject, List<String> templates)
  {
    super(generatorContext, resolvedOpenApiObject.getName(), identifier, resolvedOpenApiObject, null, templates);
    
    model_ = generatorContext.getSourceContext().getModel();
    resolvedOpenApiObject_ = resolvedOpenApiObject;
    
    String[] parts = getCanonVersion().getValue().split("\\.");
    
    if(parts.length == 2)
    {
      try
      {
        canonMajorVersion_ = Integer.parseInt(parts[0]);
        canonMinorVersion_ = Integer.parseInt(parts[1]);
      }
      catch(NumberFormatException e)
      {
        throw new IllegalStateException("version must be a 2 element semantic version (each element must be an integer)");
      }
    }
    else
    {
      canonMajorVersion_ = null;
      canonMinorVersion_ = null;
    }
  }
  
  @Override
  public ResolvedOpenApiObject getResolvedOpenApiObject()
  {
    return resolvedOpenApiObject_;
  }

  /**
   * Return the canon model ID specified by the x-canon-id attribute.
   * 
   * @return the canon model ID specified by the x-canon-id attribute.
   */
  public String getCanonId()
  {
    return model_.getXCanonId();
  }
  
  /**
   * Return the canon model major version number.
   * 
   * @return the canon model major version number.
   */
  public Integer getCanonMajorVersion()
  {
    return canonMajorVersion_;
  }

  /**
   * Return the canon model minor version number.
   * 
   * @return the canon model minor version number.
   */
  public Integer getCanonMinorVersion()
  {
    return canonMinorVersion_;
  }

  /**
   * Return the canon model version.
   * 
   * @return the canon model version.
   */
  public SemanticVersion getCanonVersion()
  {
    return model_.getXCanonVersion();
  }

  @Override
  public M getModel()
  {
    return asOpenApiTemplateModel();
  }

  @Override
  public void addSchema(S schema)
  {
    if(schemaMap_.put(schema.getIdentifier(), schema) != null)
    {
      throw new ParserErrorException("Duplicate schema name \"" + schema.getIdentifier() + "\"", schema);
    }
    schemas_.add(schema);
    children_.add(schema.asTemplateModel());
  }

  @Override
  public Collection<S> getSchemas()
  {
    return schemas_;
  }
  
  @Override
  public Collection<T> getChildren()
  {
    return children_;
  }

  @Override
  public boolean hasName(String name)
  {
    return schemaMap_.containsKey(name);
  }
}
