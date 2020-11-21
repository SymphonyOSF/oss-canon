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
import com.symphony.oss.canon2.core.ResolvedBooleanSchema;
import com.symphony.oss.canon2.core.ResolvedOpenApiObject.SingletonBuilder;
import com.symphony.oss.canon2.core.ResolvedSchema;
import com.symphony.oss.canon2.core.SchemaTemplateModelType;
import com.symphony.oss.canon2.core.SourceContext;


/**
 * Facade for Object  BooleanSchema canon
 * Object BooleanSchema
 * Generated from BooleanSchema
 */
@Immutable
public class BooleanSchema extends BooleanSchema_Entity implements IPrimitiveSchema
{
  /**
   * Constructor.
   * 
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  protected BooleanSchema(Initialiser initialiser)
  {
    super(initialiser);
  }

  @Override
  public void link(SingletonBuilder openApiObjectBuilder, CanonModelContext modelContext, SourceContext sourceContext,
      Consumer<ResolvedSchema.AbstractBuilder<? extends ISchemaInstance,?,?>> builderConsumer, String uri)
  {
    builderConsumer.accept(new ResolvedBooleanSchema.SingletonBuilder()
        .withSchema(this));
  }

  @Override
  public SchemaTemplateModelType getSchemaType()
  {
    return SchemaTemplateModelType.BOOLEAN;
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */