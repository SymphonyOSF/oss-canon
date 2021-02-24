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
 *    At                   2020-11-10 15:27:52 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.model;

import java.util.function.Consumer;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon2.core.CanonModelContext;
import com.symphony.oss.canon2.core.ResolvedOneOfSchema;
import com.symphony.oss.canon2.core.ResolvedOpenApiObject;
import com.symphony.oss.canon2.core.ResolvedPropertiesObject;
import com.symphony.oss.canon2.core.ResolvedProperty;
import com.symphony.oss.canon2.core.ResolvedSchema;
import com.symphony.oss.canon2.core.ResolvedSchemasObject;
import com.symphony.oss.canon2.core.SchemaTemplateModelType;
import com.symphony.oss.canon2.core.SourceContext;


/**
 * Facade for Object  OneOfSchema canon
 * Object OneOfSchema
 * Generated from OneOfSchema
 */
@Immutable
public class OneOfSchema extends OneOfSchema_Entity implements ISchemaInstance
{
  /**
   * Constructor.
   * 
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  protected OneOfSchema(Initialiser initialiser)
  {
    super(initialiser);
  }

  @Override
  public void link(ResolvedOpenApiObject.SingletonBuilder openApiObjectBuilder, CanonModelContext modelContext, SourceContext sourceContext, Consumer<ResolvedSchema.AbstractBuilder<? extends ISchemaInstance,?,?>> builderConsumer, String uri, int depth)
  {
    
    ResolvedOneOfSchema.SingletonBuilder        builder = new ResolvedOneOfSchema.SingletonBuilder();
    ResolvedPropertiesObject.SingletonBuilder   resolvedPropertiesBuilder = new ResolvedPropertiesObject.SingletonBuilder();
    ResolvedSchemasObject.SingletonBuilder      innerClassesBuilder       = new ResolvedSchemasObject.SingletonBuilder();
    
    builder
      .withResolvedProperties(resolvedPropertiesBuilder)
      .withInnerClasses(innerClassesBuilder)
      .withSchema(this)
      ;
    
    builderConsumer.accept(builder);
     
    int i=1;
    for(SchemaOrRef subSchema : getOneOf())
    {
      String name = subSchema.getReferenceObject() == null ? "$" + i : subSchema.getReferenceObject().getName();
      
      ResolvedSchema.AbstractBuilder<? extends ISchemaInstance,?,?> resolvedSubSchema = subSchema.link(openApiObjectBuilder, modelContext, sourceContext, name, uri, builder, depth);
      ResolvedProperty.SingletonBuilder resolvedProperty = new ResolvedProperty.SingletonBuilder()
          .withName(name)
          .withNameContext(subSchema.getJson().getContext())
          .withResolvedSchema(resolvedSubSchema)
          .withJson(getJson());
      resolvedPropertiesBuilder.with(name, resolvedProperty);
      
      if(subSchema.getSchema() != null && resolvedSubSchema.build().getSchemaType().getIsObject())
        innerClassesBuilder.with(name, resolvedSubSchema);
      i++;
    }
  }

  @Override
  public SchemaTemplateModelType getSchemaType()
  {
    return SchemaTemplateModelType.ONE_OF;
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */