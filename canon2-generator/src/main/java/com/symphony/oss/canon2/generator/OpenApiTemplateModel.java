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
  private final Map<String, S>        schemaMap_ = new HashMap<>();

  private final Integer               canonMajorVersion_;
  private final Integer               canonMinorVersion_;
  private final ResolvedOpenApiObject resolvedOpenApiObject_;

  
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

  public String getCanonId()
  {
    return model_.getXCanonId();
  }
  
  public Integer getCanonMajorVersion()
  {
    return canonMajorVersion_;
  }

  public Integer getCanonMinorVersion()
  {
    return canonMinorVersion_;
  }

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
    if(schemaMap_.put(schema.getCamelName(), schema) != null)
    {
      throw new ParserErrorException("Duplicate schema name \"" + schema.getCamelName() + "\"", schema);
    }
    schemas_.add(schema);
  }

  @Override
  public Collection<S> getSchemas()
  {
    return schemas_;
  }
  
  @Override
  public Collection<T> getChildren()
  {
    return (Collection<T>) getSchemas();
  }

  @Override
  public boolean hasName(String name)
  {
    return schemaMap_.containsKey(name);
  }
}
