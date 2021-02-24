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
 *    At                   2020-11-10 15:27:51 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.model;

import java.net.MalformedURLException;
import java.util.function.Consumer;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.json.ParserErrorException;
import com.symphony.oss.canon2.core.CanonModelContext;
import com.symphony.oss.canon2.core.ResolvedArraySchema;
import com.symphony.oss.canon2.core.ResolvedOpenApiObject.SingletonBuilder;
import com.symphony.oss.canon2.core.ResolvedSchema;
import com.symphony.oss.canon2.core.ResolvedSchemasObject;
import com.symphony.oss.canon2.core.SchemaTemplateModelType;
import com.symphony.oss.canon2.core.SourceContext;


/**
 * Facade for Object  ArraySchema canon
 * Object ArraySchema
 * Generated from ArraySchema
 */
@Immutable
public class ArraySchema extends ArraySchema_Entity implements ISchemaInstance
{
  /**
   * Constructor.
   * 
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  protected ArraySchema(Initialiser initialiser)
  {
    super(initialiser);
  }

  public void fetchReferences(CanonModelContext generationContext, SourceContext sourceContext)
  {
      
      if(getItems().getReferenceObject() != null)
      {
        try
        {
          generationContext.addReferencedModel(getItems().getReferenceObject().getAbsoluteBaseUrl(sourceContext.getUrl()));
        }
        catch(MalformedURLException e)
        {
          throw new ParserErrorException("Invalid URL", getItems().getReferenceObject(), e);
        }
      }
  }

  @Override
  public void link(
      SingletonBuilder openApiObjectBuilder, CanonModelContext modelContext, SourceContext sourceContext, Consumer<ResolvedSchema.AbstractBuilder<? extends ISchemaInstance,?,?>> builderConsumer, String uri, int depth)
  {
    ResolvedSchemasObject.SingletonBuilder      innerClassesBuilder       = new ResolvedSchemasObject.SingletonBuilder();
    ResolvedArraySchema.SingletonBuilder       builder = new ResolvedArraySchema.SingletonBuilder()
        .withInnerClasses(innerClassesBuilder)
        .withSchema(this);
    

    builderConsumer.accept(builder);
    // if(getA)

     if(getItems().getSchema() != null)
     {
       String name = depth > 1 ? "items" + depth : "items";
       
       ResolvedSchema.AbstractBuilder<?, ?, ?> resolvedItemsSchema = modelContext.link(openApiObjectBuilder, sourceContext, name, uri + "/" + name, getItems().getSchema(), builder, depth+1);
       builder.withResolvedItems(resolvedItemsSchema
           
           //itemsSchema_.link(modelContext, sourceContext, uri + "/items", true)
           );
       
       innerClassesBuilder.with("items", resolvedItemsSchema);
     }
     else if(getItems().getReferenceObject() != null)
     {
//       Schema schema = fetchSchema(modelContext, sourceContext, itemsReference_, uri, "items", false);
       builder.withResolvedItems(getItems().getReferenceObject().fetchSchema(openApiObjectBuilder, modelContext, sourceContext));
       
//           modelContext.link(sourceContext, uri + "/items", schema, generated)
//           schema.link(modelContext, sourceContext, itemsReference_.getAbsoluteUri(sourceContext.getUrl()), false));
     }
     
//     Schema schema = itemsSchema_;
//     boolean itemsIsGenerated = false;
//     String  itemsName = "items";
//     String itemsPath = schemaInfo.getName() + "/items";
//     
//     if(schema == null && itemsReference_ != null)
//     {
//       try
//       {
//         schema = fetchSchema(schemaInfo.getOpenApiObject(), generationContext, itemsReference_);
//         itemsIsGenerated = true;
//         itemsName = itemsPath = itemsReference_.getName();
//       }
//       catch(GenerationException e)
//       {
//         schemaInfo.getModelContext().error("Invalid schema reference \"" + itemsReference_.get$ref() + "\" at " + getSourceLocation());
//       }
//     }
//     
//     if(schema != null)
//     {
//       SchemaInfo itemsInfo = new SchemaInfo(schemaInfo, itemsName, itemsPath, schema, itemsIsGenerated);
//       
//       generationContext.resolve(itemsInfo);
//       
//       schemaInfo.setItems(itemsInfo);
//       
////       builder.withResolvedItems(resolver.resolve(openApiObject, generationContext, modelContext, schema, null, itemsIsGenerated));
//     }
  }

  @Override
  public  SchemaTemplateModelType getSchemaType()
  {
    return SchemaTemplateModelType.ARRAY;
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */