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

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.json.ParserWarningException;
import com.symphony.oss.canon.json.SyntaxErrorException;
import com.symphony.oss.canon2.core.CanonModelContext;
import com.symphony.oss.canon2.core.ResolvedBigDecimalSchema;
import com.symphony.oss.canon2.core.ResolvedBigIntegerSchema;
import com.symphony.oss.canon2.core.ResolvedDoubleSchema;
import com.symphony.oss.canon2.core.ResolvedFloatSchema;
import com.symphony.oss.canon2.core.ResolvedIntegerSchema;
import com.symphony.oss.canon2.core.ResolvedLongSchema;
import com.symphony.oss.canon2.core.ResolvedNumberSchema;
import com.symphony.oss.canon2.core.ResolvedOpenApiObject.SingletonBuilder;
import com.symphony.oss.canon2.core.ResolvedSchema;
import com.symphony.oss.canon2.core.SchemaTemplateModelType;
import com.symphony.oss.canon2.core.SourceContext;


/**
 * Facade for Object  NumberSchema canon
 * Object NumberSchema
 * Generated from NumberSchema
 */
@Immutable
public class NumberSchema extends NumberSchemaEntity implements IPrimitiveSchema
{
  /**
   * Constructor.
   * 
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  protected NumberSchema(Initialiser initialiser)
  {
    super(initialiser);
  }

  @Override
  public void link(SingletonBuilder openApiObjectBuilder, CanonModelContext modelContext, SourceContext sourceContext,
      Consumer<ResolvedSchema.AbstractBuilder<? extends ISchemaInstance,?,?>> builderConsumer, String uri)
  {
    switch(getType())
    {
      case NUMBER:
        linkNumber(sourceContext, builderConsumer);
        break;
        
      case INTEGER:
        linkInteger(sourceContext, builderConsumer);
        break;
    }
  }

  private void linkInteger(SourceContext sourceContext, Consumer<ResolvedSchema.AbstractBuilder<? extends ISchemaInstance,?,?>> builderConsumer)
  {
    if(getFormat() == null)
    {
      doLink(new ResolvedBigIntegerSchema.SingletonBuilder(), builderConsumer);
    }
    else switch(getFormat())
    {
      case "int64":
        doLink(new ResolvedLongSchema.SingletonBuilder(), builderConsumer);
        break;
        
      case "int32":
        doLink(new ResolvedIntegerSchema.SingletonBuilder(), builderConsumer);
        break;
        
      case "float":
      case "double":
        sourceContext.error(new SyntaxErrorException("Invalid floating point format for integer type \"" + getFormat() + "\"", getJson().getContext()));
        doLink(new ResolvedBigIntegerSchema.SingletonBuilder(), builderConsumer);
        break;
        
      default:
        sourceContext.error(new ParserWarningException("Unknown number format \"" + getFormat() + "\" ignored.", getJson().getContext()));
        doLink(new ResolvedBigIntegerSchema.SingletonBuilder(), builderConsumer);
    }
  }

  private void linkNumber(SourceContext sourceContext, Consumer<ResolvedSchema.AbstractBuilder<? extends ISchemaInstance,?,?>> builderConsumer)
  {
    if(getFormat() == null)
    {
      doLink(new ResolvedBigDecimalSchema.SingletonBuilder(), builderConsumer);
    }
    else switch(getFormat())
    {
      case "int64":
        doLink(new ResolvedLongSchema.SingletonBuilder(), builderConsumer);
        break;
        
      case "int32":
        doLink(new ResolvedIntegerSchema.SingletonBuilder(), builderConsumer);
        break;
      
      case "float":
        doLink(new ResolvedFloatSchema.SingletonBuilder(), builderConsumer);
        break;
      
      case "double":
        doLink(new ResolvedDoubleSchema.SingletonBuilder(), builderConsumer);
        break;
      
      default:
        doLink(new ResolvedBigDecimalSchema.SingletonBuilder(), builderConsumer);
        sourceContext.error(new ParserWarningException("Unknown number format \"" + getFormat() + "\" ignored.", getJson().getContext()));
    }
  }
  
  private void doLink(ResolvedNumberSchema.AbstractBuilder<?,?,?> builder, Consumer<ResolvedSchema.AbstractBuilder<? extends ISchemaInstance,?,?>> builderConsumer)
  {
    builder
      .withSchema(this)
      .withMinimum(getJson().get("minimum"))
      //.withExclusiveMinimum(getExclusiveMinimum())
      .withMaximum(getJson().get("maximum"))
      //.withExclusiveMaximum(getExclusiveMaximum())
      ;
    
    builderConsumer.accept(builder);
  }

  @Override
  public  SchemaTemplateModelType getSchemaType()
  {
    return SchemaTemplateModelType.NUMBER;
  }
  
  @Override
  public @Nullable String getXCanonIdentifier(String language)
  {
    return getJson().getString("x-canon-" + language + "-identifier", null);
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */