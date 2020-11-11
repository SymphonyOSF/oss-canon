/**
 * Proforma implementation:
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
 *
 *----------------------------------------------------------------------------------------------------
 * Generated from
 *    Input source         canon.json
 *    Generator groupId    org.symphonyoss.s2.canon
 *              artifactId canon2-generator-java
 *    Template name        proforma/Object/_.java.ftl
 *    At                   2020-11-10 16:58:05 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.model;

import java.util.function.Consumer;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.json.SyntaxErrorException;
import com.symphony.oss.canon2.core.CanonModelContext;
import com.symphony.oss.canon2.core.ResolvedOpenApiObject;
import com.symphony.oss.canon2.core.ResolvedSchema;
import com.symphony.oss.canon2.core.SchemaTemplateModelType;
import com.symphony.oss.canon2.core.SourceContext;
import com.symphony.oss.commons.fault.CodingFault;


/**
 * Facade for Object  Schema canon
 * Object Schema
 * Generated from Schema
 */
@Immutable
public class Schema extends SchemaEntity implements ISchema
{
  /**
   * Constructor.
   * 
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  protected Schema(Initialiser initialiser)
  {
    super(initialiser);
  }

  @Override
  public void fetchReferences(CanonModelContext generationContext, SourceContext sourceContext)
  {
    if(getObjectSchema() != null)
      getObjectSchema().fetchReferences(generationContext, sourceContext);
    
    if(getArraySchema() != null)
      getArraySchema().fetchReferences(generationContext, sourceContext);
  }
  
  @Override
  public void link(ResolvedOpenApiObject.SingletonBuilder openApiObjectBuilder, CanonModelContext modelContext, SourceContext sourceContext, 
      Consumer<ResolvedSchema.AbstractBuilder<? extends ISchemaInstance,?,?>> builderConsumer,
      String uri)
  {
    int typeIndicatorCnt = 0;
    
    if(getOneOfSchema() != null)
    {
      getOneOfSchema().link(openApiObjectBuilder, modelContext, sourceContext, builderConsumer, uri);
      typeIndicatorCnt++;
    }

    if(getObjectSchema() != null)
    {
      getObjectSchema().link(openApiObjectBuilder, modelContext, sourceContext, builderConsumer, uri);
      typeIndicatorCnt++;
    }
    
    if(getArraySchema() != null)
    {
      getArraySchema().link(openApiObjectBuilder, modelContext, sourceContext, builderConsumer, uri);
      typeIndicatorCnt++;
    }
    
    if(getBooleanSchema() != null)
    {
      getBooleanSchema().link(openApiObjectBuilder, modelContext, sourceContext, builderConsumer, uri);
      typeIndicatorCnt++;
    }
    
    if(getNumberSchema() != null)
    {
      getNumberSchema().link(openApiObjectBuilder, modelContext, sourceContext, builderConsumer, uri);
      typeIndicatorCnt++;
    }
    
    if(getStringSchema() != null)
    {
      getStringSchema().link(openApiObjectBuilder, modelContext, sourceContext, builderConsumer, uri);
      typeIndicatorCnt++;
    }
    
    if(typeIndicatorCnt != 1)
    {
      sourceContext.error(new SyntaxErrorException("Exactly one of oneOf, anyOf, allOf and type must be specified.", getJson().getContext()));
    }
  }

  @Override
  public SchemaTemplateModelType getSchemaType()
  {
    return getSchema().getSchemaType();
  }
  
  @Override
  public String getXCanonIdentifier()
  {
    return getSchema().getXCanonIdentifier();
  }
  
  @Override
  public @Nullable String getXCanonIdentifier(String language)
  {
    return getSchema().getXCanonIdentifier(language);
  }

  @Override
  public Boolean getXCanonBuilderFacade()
  {
    return getSchema().getXCanonBuilderFacade();
  }

  @Override
  public Boolean getXCanonFacade()
  {
    return getSchema().getXCanonFacade();
  }

  private ISchemaInstance getSchema()
  {
    if(getOneOfSchema() != null)
    {
      return getOneOfSchema();
    }

    if(getObjectSchema() != null)
    {
      return getObjectSchema();
    }
    
    if(getArraySchema() != null)
    {
      return getArraySchema();
    }
    
    if(getBooleanSchema() != null)
    {
      return getBooleanSchema();
    }
    
    if(getNumberSchema() != null)
    {
      return getNumberSchema();
    }
    
    if(getStringSchema() != null)
    {
      return getStringSchema();
    }
    
    throw new CodingFault("Unknown schema type");
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */