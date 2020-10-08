/**
 * GENERATED CODE - DO NOT EDIT OR CHECK IN TO SOURCE CODE CONTROL
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
 *    At                   2020-09-16 16:04:42 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.model;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon2.core.CanonModelContext;
import com.symphony.oss.canon2.core.GenerationException;
import com.symphony.oss.canon2.core.INamedModelEntity;
import com.symphony.oss.canon2.core.ResolvedOpenApiObject;
import com.symphony.oss.canon2.core.SourceContext;
import com.symphony.oss.canon2.runtime.java.Entity;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;


/**
 * Facade for Object  OpenApiObject canon
 * Object com.symphony.oss.canon2.generator.java.JavaOpenApiTemplateModel@4df50bcc
 * Generated from JavaObjectSchemaTemplateModel [fields_=[JavaFieldTemplateModel ComponentsObject components, JavaFieldTemplateModel CanonGeneratorConfig xCanonGenerators, JavaFieldTemplateModel Openapi openapi, JavaFieldTemplateModel PathsObject paths, JavaFieldTemplateModel XCanonId xCanonId, JavaFieldTemplateModel SemanticVersion xCanonVersion, JavaFieldTemplateModel XCanonIdentifier xCanonIdentifier, JavaFieldTemplateModel Canon canon, JavaFieldTemplateModel InfoObject info]] at {entity.context.path}
 */
@Immutable
public class OpenApiObject extends OpenApiObjectEntity implements INamedModelEntity
{
  private static Logger log_ = LoggerFactory.getLogger(OpenApiObject.class);
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public OpenApiObject(AbstractBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public OpenApiObject(JsonObject jsonObject, ModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public OpenApiObject(OpenApiObject other)
  {
    super(other);
  }
  
  @Override
  public @Nullable String getXCanonIdentifier(String language)
  {
    return getJsonObject().getString("x-canon-" + language + "-identifier", null);
  }

  public <T extends Entity> T get(String fragment, Class<T> type)
  {
    Entity entity = get(fragment.split("/"), 1);
    
    if(type.isInstance(entity))
      return type.cast(entity);
    
    throw new IllegalArgumentException("Expected " + type + " but found " + entity.getClass());
  }
  
  public Entity get(String[] parts, int index)
  {
    switch(parts[index])
    {
      case "components":
        return getComponents().get(parts, index + 1);
    }
    
    throw new IllegalArgumentException("No path element " + parts[index]);
  }

  public void fetchReferences(CanonModelContext generationContext, SourceContext sourceContext) throws GenerationException
  {
    log_.info("fetchReferences");
    
    SchemasObject schemas = getComponents().getSchemas();
    
    for(Schema schema : schemas.getSchemas().values())
    {
      schema.fetchReferences(generationContext, sourceContext);
    }
  }

  public void link(ResolvedOpenApiObject.SingletonBuilder builder, CanonModelContext modelContext, SourceContext sourceContext, String uri) throws GenerationException
  {
    log_.info("link model");
    
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
////        .withValues(getJsonObject(), generationContext.getModelRegistry());
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


  
  /**
   * Abstract builder for OpenApiObject. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends OpenApiObject> extends OpenApiObjectEntity.AbstractBuilder<T,B>
  {
    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);
    }
  }

  public ImmutableMap<String, Schema> getSchemas()
  {
    return getComponents().getSchemas().getSchemas();
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */