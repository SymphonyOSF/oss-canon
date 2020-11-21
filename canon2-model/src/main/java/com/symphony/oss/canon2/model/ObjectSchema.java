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

import java.net.MalformedURLException;
import java.util.Map.Entry;
import java.util.function.Consumer;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.json.ParserErrorException;
import com.symphony.oss.canon2.core.CanonModelContext;
import com.symphony.oss.canon2.core.ResolvedObjectSchema;
import com.symphony.oss.canon2.core.ResolvedOpenApiObject.SingletonBuilder;
import com.symphony.oss.canon2.core.ResolvedPropertiesObject;
import com.symphony.oss.canon2.core.ResolvedProperty;
import com.symphony.oss.canon2.core.ResolvedSchema;
import com.symphony.oss.canon2.core.SchemaTemplateModelType;
import com.symphony.oss.canon2.core.SourceContext;


/**
 * Facade for Object  ObjectSchema canon
 * Object ObjectSchema
 * Generated from ObjectSchema
 */
@Immutable
public class ObjectSchema extends ObjectSchema_Entity implements ISchema
{
  /**
   * Constructor.
   * 
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  protected ObjectSchema(Initialiser initialiser)
  {
    super(initialiser);
  }

  @Override
  public void fetchReferences(CanonModelContext generationContext, SourceContext sourceContext)
  {
      PropertiesObject propertiesObject = getProperties();
      
      if(propertiesObject != null)
      {
        for(Entry<String, Object> entry : propertiesObject.getProperties().entrySet())
        {
          if(entry.getValue() instanceof ReferenceObject)
          {
            try
            {
              generationContext.addReferencedModel(((ReferenceObject)entry.getValue()).getAbsoluteBaseUrl(sourceContext.getUrl()));
            }
            catch(MalformedURLException e)
            {
              throw new ParserErrorException("Invalid URL", (ReferenceObject)entry.getValue(), e);
            }
          }
        }
      }
  }
  
  @Override
  public void link(
      SingletonBuilder openApiObjectBuilder, CanonModelContext modelContext, SourceContext sourceContext, Consumer<ResolvedSchema.AbstractBuilder<? extends ISchemaInstance,?,?>> builderConsumer, String uri)
  {
    ResolvedObjectSchema.SingletonBuilder       builder = new ResolvedObjectSchema.SingletonBuilder();
    ResolvedPropertiesObject.SingletonBuilder   resolvedPropertiesBuilder = new ResolvedPropertiesObject.SingletonBuilder();
    ResolvedPropertiesObject.SingletonBuilder   innerClassesBuilder       = new ResolvedPropertiesObject.SingletonBuilder();
    
    builder
      .withResolvedProperties(resolvedPropertiesBuilder)
      .withInnerClasses(innerClassesBuilder)
      .withSchema(this)
      ;
    
    builderConsumer.accept(builder);
    
    PropertiesObject propertiesObject  = getProperties();
  
    if(propertiesObject != null)
    {
      for(Entry<String, Object> entry : propertiesObject.getProperties().entrySet())
      {
        if(entry.getValue() instanceof Schema)
        {
          ISchemaInstance schema = (ISchemaInstance)entry.getValue();
          
          ResolvedSchema.AbstractBuilder<? extends ISchemaInstance,?,?> resolvedPropertySchema = modelContext.link(openApiObjectBuilder, sourceContext, entry.getKey(), uri + "/" + entry.getKey(), schema, builder);
          ResolvedProperty.SingletonBuilder resolvedProperty = new ResolvedProperty.SingletonBuilder()
              .withName(entry.getKey())
              .withNameContext(propertiesObject.getNameContext(entry.getKey()))
              .withResolvedSchema(resolvedPropertySchema)
              .withJson(schema.getJson());
          resolvedPropertiesBuilder.with(entry.getKey(), resolvedProperty);

          innerClassesBuilder.with(entry.getKey(), resolvedProperty);
        }
        else
        {
          ReferenceObject ref = (ReferenceObject)entry.getValue();
          ResolvedSchema.AbstractBuilder<? extends ISchemaInstance,?,?> resolvedPropertySchema = ref.fetchSchema(openApiObjectBuilder, modelContext, sourceContext);
          ResolvedProperty.SingletonBuilder resolvedProperty = new ResolvedProperty.SingletonBuilder()
              .withName(entry.getKey())
              .withNameContext(propertiesObject.getNameContext(entry.getKey()))
              .withResolvedSchema(resolvedPropertySchema)
              .withJson(ref.getJson());
          resolvedPropertiesBuilder.with(entry.getKey(), resolvedProperty);
        }
      }
    }

//    if(getAdditionalProperties() != null)
//    {
//      Object additionalProperies = getAdditionalProperties().canonGetValue();
//      
//      if(additionalProperies instanceof Boolean)
//      {
//        builder.withAdditionalPropertiesAllowed((boolean)additionalProperies);
//      }
//      else if(additionalProperies instanceof SchemaOrRef)
//      {
//        SchemaOrRef schemaOrRef = (SchemaOrRef) additionalProperies;
//        
//        String name = schemaOrRef.getRef() == null ? "$additionalProperties" : schemaOrRef.getRef().getName();
//        
//        ResolvedSchema.SingletonBuilder resolvedAdditionalProperties = schemaOrRef.link(openApiObjectBuilder, modelContext, sourceContext, name, uri, builder);
//        builder.withResolvedAdditionalProperties(resolvedAdditionalProperties);
//      }
//      else if(additionalProperies != null)
//      {
//        throw new GenerationException("Unexpected additional properties object of type " + additionalProperies.getClass());
//      }
//    }
    
//    if(getDiscriminator() != null)
//    {
//      String propertyName = getDiscriminator().getPropertyName();
//      
//      if(propertyName != null)
//      {
//        Object property = resolvedPropertiesBuilder.get(propertyName);
//      }
//    }
    
    if(getXCanonExtends() != null)
    {
//      try
//      {
        ResolvedSchema.AbstractBuilder<? extends ISchemaInstance,?,?> superSchema = getXCanonExtends().fetchSchema(openApiObjectBuilder, modelContext, sourceContext);
        
        if(superSchema instanceof ResolvedObjectSchema.SingletonBuilder)
        {
          builder.withResolvedExtends((ResolvedObjectSchema.SingletonBuilder) superSchema);
        }
        else
        {
          throw new ParserErrorException("Super class must be an object", getXCanonExtends().getJson().getContext());
        }
//      }
//      catch(GenerationException e)
//      {
//        sourceContext.error("Invalid schema reference \"" + getXCanonExtends().get$ref() + "\" at " + getSourceLocation());
//      }
    }
  }

  @Override
  public  SchemaTemplateModelType getSchemaType()
  {
    return SchemaTemplateModelType.OBJECT;
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */