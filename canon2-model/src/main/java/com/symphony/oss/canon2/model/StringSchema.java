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
import com.symphony.oss.canon2.core.ResolvedOpenApiObject.SingletonBuilder;
import com.symphony.oss.canon2.core.ResolvedSchema;
import com.symphony.oss.canon2.core.ResolvedStringSchema;
import com.symphony.oss.canon2.core.SchemaTemplateModelType;
import com.symphony.oss.canon2.core.SourceContext;


/**
 * Facade for Object  StringSchema canon
 * Object StringSchema
 * Generated from StringSchema
 */
@Immutable
public class StringSchema extends StringSchemaEntity implements IPrimitiveSchema
{
  /**
   * Constructor.
   * 
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  protected StringSchema(Initialiser initialiser)
  {
    super(initialiser);
  }

  @Override
  public void link(SingletonBuilder openApiObjectBuilder, CanonModelContext modelContext, SourceContext sourceContext,
      Consumer<ResolvedSchema.AbstractBuilder<? extends ISchemaInstance,?,?>> builderConsumer, String uri)
  {
    ResolvedStringSchema.SingletonBuilder       builder = new ResolvedStringSchema.SingletonBuilder()
        .withSchema(this);

    builderConsumer.accept(builder);
    
    if(getEnum() != null)
      builder.withEnum(getEnum());
//    builder
//      .withMinLength(getMinLength())
//      .withMaxLength(getMaxLength())
//      .withPattern(getPattern())
//      ;
  }

  /**
   * Return true if this schema is an enum.
   * 
   * @return true if this schema is an enum.
   */
  public boolean isEnum()
  {
    return getEnum() != null && !getEnum().isEmpty();
  }

  @Override
  public  SchemaTemplateModelType getSchemaType()
  {
    return SchemaTemplateModelType.STRING;
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */