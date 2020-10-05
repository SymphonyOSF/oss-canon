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

package com.symphony.oss.canon2.model;

import java.util.LinkedList;
import java.util.List;

@Deprecated
public class SchemaInfo
{
  private final OpenApiObject openApiObject_;
  private final IModelContext modelContext_;
  private final String name_;
  private final String uri_;
  private final Schema  schema_;
  private final boolean generated_;
  private final List<SchemaInfo> innerClasses_ = new LinkedList<>();
  private final List<SchemaInfo> properties_ = new LinkedList<>();
  private SchemaInfo items_;
  private SchemaInfo extends_;
  
  public OpenApiObject getOpenApiObject()
  {
    return openApiObject_;
  }

  public SchemaInfo(OpenApiObject openApiObject, IModelContext modelContext, String name, String uriPath, Schema schema,
      boolean generated)
  {
    openApiObject_ = openApiObject;
    modelContext_ = modelContext;
    name_ = name;
    uri_ = modelContext.getUrl() + "#/components/schemas/" + uriPath;
    schema_ = schema;
    generated_ = generated;
  }

  public SchemaInfo(SchemaInfo schemaInfo, String name, String uriPath, Schema schema, boolean generated)
  {
    openApiObject_ = schemaInfo.openApiObject_;
    modelContext_ = schemaInfo.modelContext_;
    name_ = name;
    uri_ = modelContext_.getUrl() + "#/components/schemas/" + uriPath;
    schema_ = schema;
    generated_ = generated;
  }

  public String getName()
  {
    return name_;
  }

  public String getUri()
  {
    return uri_;
  }

  public Schema getSchema()
  {
    return schema_;
  }

  public boolean isGenerated()
  {
    return generated_;
  }

  public IModelContext getModelContext()
  {
    return modelContext_;
  }

//  public void resolve(ICanonContext canonContext)
//  {
//    schema_.resolve(canonContext, this);
//  }

  public void addInnerClass(SchemaInfo propertyInfo)
  {
    innerClasses_.add(propertyInfo);
  }

  public void addProperty(SchemaInfo propertyInfo)
  {
    properties_.add(propertyInfo);
  }

  public void setItems(SchemaInfo itemsInfo)
  {
   items_ = itemsInfo;
  }

  public SchemaInfo getExtends()
  {
    return extends_;
  }

  public void setExtends(SchemaInfo extends1)
  {
    extends_ = extends1;
  }

  public List<SchemaInfo> getInnerClasses()
  {
    return innerClasses_;
  }

  public List<SchemaInfo> getProperties()
  {
    return properties_;
  }

  public SchemaInfo getItems()
  {
    return items_;
  }
}
