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
 *    At                   2020-10-08 13:02:45 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.model;

import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableMap;
import com.symphony.oss.canon2.core.CanonModelContext;
import com.symphony.oss.canon2.core.ICanonModelEntity;
import com.symphony.oss.canon2.core.ResolvedOpenApiObject;
import com.symphony.oss.canon2.core.SourceContext;


/**
 * Facade for Object  OpenApiObject canon
 * Object OpenApiObject
 * Generated from OpenApiObject
 */
@Immutable
public class OpenApiObject extends OpenApiObjectEntity implements ICanonModelEntity
{
  /**
   * Constructor.
   * 
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  protected OpenApiObject(Initialiser initialiser)
  {
    super(initialiser);
  }

  public <T extends ICanonModelEntity> T get(String fragment, Class<T> type)
  {
    ICanonModelEntity entity = get(fragment.split("/"), 1);
    
    if(type.isInstance(entity))
      return type.cast(entity);
    
    throw new IllegalArgumentException("Expected " + type + " but found " + entity.getClass());
  }
  
  public ICanonModelEntity get(String[] parts, int index)
  {
    switch(parts[index])
    {
      case "components":
        return getComponents().get(parts, index + 1);
    }
    
    throw new IllegalArgumentException("No path element " + parts[index]);
  }

  public void fetchReferences(CanonModelContext generationContext, SourceContext sourceContext)
  {
    SchemasObject schemas = getComponents().getSchemas();
    
    for(ISchema schema : schemas.getSchemas().values())
    {
      schema.fetchReferences(generationContext, sourceContext);
    }
  }

  public void link(ResolvedOpenApiObject.SingletonBuilder builder, CanonModelContext modelContext, SourceContext sourceContext, String uri)
  {
    if(getComponents() != null)
    {
      builder.withComponents(getComponents().link(builder, modelContext, sourceContext, uri + "/components"));
    }
  }

//  public void resolve(CanonModelContext modellContext, SourceContext sourceContext)
//  {
//    log_.info("resolve model");
//    
////    ResolvedModel.Builder builder = new ResolvedModel.Builder()
////        .withValues(getJson(), generationContext.getModelRegistry());
////    
////    // dodge canon1 bugs
////
////    builder.withXCanonId(getXCanonId());
////    builder.withXCanonVersion(getXCanonVersion());
////    builder.withXCanonGenerators(getXCanonGenerators());
//    
//    List<SchemaInfo>  modelInfo   = new LinkedList<>();
//    SchemasObject     schemas     = getComponents().getSchemas();
//    boolean           isGenerated = true;
////    SchemaResolver resolver = new SchemaResolver();
//    
//    for(Entry<String, Schema> entry : schemas.getSchemas().entrySet())
//    {
//      String      name    = entry.getKey();
//      Schema      schema  = entry.getValue();
//      SchemaInfo  info    = new SchemaInfo(this, sourceContext, name, name, schema, isGenerated);
//      
//      modellContext.resolve(info);
//      
//      sourceContext.addSchema(info);
//      
//      //builder.withResolvedSchema(entry.getKey(), resolver.resolve(this, generationContext, modelContext, entry.getValue(), entry.getKey(), true));
//    }
//    
//    //return builder.build();
//  }


  public ImmutableMap<String, ? extends ISchema> getSchemas()
  {
    return getComponents().getSchemas().getSchemas();
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */