/**
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
 * Proforma generated from
 *		Template groupId		 org.symphonyoss.s2.canon
 *           artifactId canon-template-java
 *		Template name		   proforma/java/Object/_.java.ftl
 *		Template version	   1.0
 *  At                  2020-08-21 14:27:27 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.parser;

import java.util.Map.Entry;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.canon2.parser.model.OpenApiObjectEntity;
import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;

/**
 * Facade for Object ObjectSchema(OpenApiObject)
 * Generated from ObjectSchema(OpenApiObject) at #/components/schemas/OpenApiObject
 */
@Immutable
public class OpenApiObject extends OpenApiObjectEntity implements IOpenApiObject
{
  private static Logger log_ = LoggerFactory.getLogger(OpenApiObject.class);
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public OpenApiObject(AbstractOpenApiObjectBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public OpenApiObject(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public OpenApiObject(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public OpenApiObject(IOpenApiObject other)
  {
    super(other);
  }
  
  @Override
  public @Nullable String getXCanonIdentifier(String language)
  {
    return getJsonObject().getString(Canon2.X_CANON + language + Canon2.IDENTIFIER_SUFFIX, null);
  }

  @Override
  public <T extends ICanonModelEntity> T get(String fragment, Class<T> type) throws GenerationException
  {
    ICanonModelEntity entity = get(fragment.split("/"), 1);
    
    if(type.isInstance(entity))
      return type.cast(entity);
    
    throw new GenerationException("Expected " + type + " but found " + entity.getClass());
  }
  
  @Override
  public ICanonModelEntity get(String[] parts, int index) throws GenerationException
  {
    switch(parts[index])
    {
      case "components":
        return getComponents().get(parts, index + 1);
    }
    
    return super.get(parts, index);
  }

  @Override
  public void fetchReferences(GenerationContext generationContext) throws GenerationException
  {
    log_.info("fetchReferences");
    
    ISchemasObject schemas = getComponents().getSchemas();
    
    for(ISchema schema : schemas.getSchemas().values())
    {
      schema.fetchReferences(generationContext);
    }
  }

  @Override
  public IResolvedModel resolve(GenerationContext generationContext, ModelContext modelContext)
  {
    log_.info("resolve model");
    
    ResolvedModel.Builder builder = new ResolvedModel.Builder()
        .withValues(getJsonObject(), false, generationContext.getModelRegistry());
    
    // dodge canon1 bugs

    builder.withXCanonId(getXCanonId());
    builder.withXCanonVersion(getXCanonVersion());
    builder.withXCanonGenerators(getXCanonGenerators());
    
    
    ISchemasObject schemas = getComponents().getSchemas();
    SchemaResolver resolver = new SchemaResolver();
    
    for(Entry<String, ISchema> entry : schemas.getSchemas().entrySet())
    {
      builder.withResolvedSchema(entry.getKey(), resolver.resolve(this, generationContext, modelContext, entry.getValue(), entry.getKey(), true));
    }
    
    return builder.build();
  }

  @Override
  public void validate(GenerationContext generationContext)
  {
    log_.info("validate model");
    
    ISchemasObject schemas = getComponents().getSchemas();
    
    for(ISchema schema : schemas.getSchemas().values())
    {
      schema.validate(generationContext);
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */