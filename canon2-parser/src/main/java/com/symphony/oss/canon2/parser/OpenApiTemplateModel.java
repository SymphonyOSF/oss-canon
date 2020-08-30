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
import java.util.LinkedList;
import java.util.List;

import com.symphony.oss.canon2.parser.model.SemanticVersion;

/**
 * Template model object for the OpenApi top-level model.
 * 
 * @author Bruce Skingle
 *
 */
public class OpenApiTemplateModel<S extends ISchemaTemplateModel<S>> extends TemplateModel<S, IOpenApiObject> implements IOpenApiTemplateModel<S>
{
  private List<ISchemaTemplateModel<S>> schemas_ = new LinkedList<>();

  private Integer             canonMajorVersion_;
  private Integer             canonMinorVersion_;
  
  public OpenApiTemplateModel(IOpenApiObject entity, String name, IGeneratorModelContext<S> generatorModelContext,
      String[] temaplates)
  {
    super(entity, name, null, generatorModelContext, temaplates);
    
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
  }
  
  public String getCanonId()
  {
    return entity_.getXCanonId();
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
    return entity_.getXCanonVersion();
  }

  @Override
  public ITemplateModel getModel()
  {
    return this;
  }

  @Override
  public void addSchema(ISchemaTemplateModel<S> schema)
  {
    schemas_.add(schema);
  }

  @Override
  public Collection<ISchemaTemplateModel<S>> getSchemas()
  {
    return schemas_;
  }
}
