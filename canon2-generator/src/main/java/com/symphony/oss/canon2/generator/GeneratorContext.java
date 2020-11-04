/*
 *
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
 */

package com.symphony.oss.canon2.generator;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.symphony.oss.canon.json.ParserResultException;
import com.symphony.oss.canon.json.SyntaxErrorException;
import com.symphony.oss.canon2.core.ResolvedArraySchema;
import com.symphony.oss.canon2.core.ResolvedBigDecimalSchema;
import com.symphony.oss.canon2.core.ResolvedBigIntegerSchema;
import com.symphony.oss.canon2.core.ResolvedBooleanSchema;
import com.symphony.oss.canon2.core.ResolvedDoubleSchema;
import com.symphony.oss.canon2.core.ResolvedFloatSchema;
import com.symphony.oss.canon2.core.ResolvedIntegerSchema;
import com.symphony.oss.canon2.core.ResolvedLongSchema;
import com.symphony.oss.canon2.core.ResolvedObjectSchema;
import com.symphony.oss.canon2.core.ResolvedPrimitiveSchema;
import com.symphony.oss.canon2.core.ResolvedSchema;
import com.symphony.oss.canon2.core.ResolvedStringSchema;
import com.symphony.oss.canon2.core.SourceContext;
import com.symphony.oss.canon2.model.CanonCardinality;
import com.symphony.oss.canon2.model.Schema;
import com.symphony.oss.commons.fault.CodingFault;

/**
 * The context for a single generator, for a run of the canon code generator.
 * 
 * @author Bruce Skingle
 *
 * @param <T>
 * @param <M>
 * @param <S>
 * @param <O>
 * @param <A>
 * @param <P>
 * @param <F>
 */
class GeneratorContext<
T extends ITemplateModel<T,M,S>,
M extends IOpenApiTemplateModel<T,M,S>,
S extends ISchemaTemplateModel<T,M,S>,
O extends IObjectSchemaTemplateModel<T,M,S,F>,
A extends IArraySchemaTemplateModel<T,M,S>,
P extends IPrimitiveSchemaTemplateModel<T,M,S>,
F extends IFieldTemplateModel<T,M,S>,
G extends IGroupSchemaTemplateModel<T,M,S>>
{
//  private static Logger log_ = LoggerFactory.getLogger(GeneratorContext.class);

  private final CanonGenerationContext generationContext_;
  private final ICanonGenerator<T,M,S,O,A,P,F,G>  generator_;
  private final SourceContext sourceContext_;
  private final IPathNameConstructor<T> pathBuilder_;
  private final Map<String, S> schemaModelMap_ = new HashMap<>();
  
  public GeneratorContext(CanonGenerationContext generationContext, ICanonGenerator<T,M,S,O,A,P,F,G> generator, SourceContext sourceContext)
  {
    generationContext_ = generationContext;
    generator_ = generator;
    sourceContext_ = sourceContext;
    pathBuilder_ = generator_.createPathBuilder(sourceContext_);
  }
  
  public ICanonGenerator<T,M,S,O,A,P,F,G> getGenerator()
  {
    return generator_;
  }

  public IPathNameConstructor<T> getPathBuilder()
  {
    return pathBuilder_;
  }

  public CanonGenerationContext getGenerationContext()
  {
    return generationContext_;
  }

  public SourceContext getSourceContext()
  {
    return sourceContext_;
  }
  
  S generateSchema(ResolvedSchema resolvedSchema, M model)
  {
    S existingSchema = schemaModelMap_.get(resolvedSchema.getUri());
    
    if(existingSchema != null)
      return existingSchema;
    
    Schema schema = resolvedSchema.getSchema();
    String identifier = generator_.getIdentifierName(resolvedSchema.getName(), schema);
    
    System.out.println("SCHEMA " + resolvedSchema.getName());
    
    if(resolvedSchema instanceof ResolvedObjectSchema)
    {
      return generateObjectSchema((ResolvedObjectSchema)resolvedSchema, model,
          schema, identifier);
    }
    if(resolvedSchema instanceof ResolvedArraySchema)
    {
      return generateArraySchema((ResolvedArraySchema)resolvedSchema, model,
          schema, identifier);
    }
    if(resolvedSchema instanceof ResolvedPrimitiveSchema)
    {
      return generatePrimitiveSchema((ResolvedPrimitiveSchema)resolvedSchema, model,
          identifier);
    }
    throw new SyntaxErrorException("Invalid schema", resolvedSchema.getSchema().getJson().getContext());
    
//    if(schema.getOneOf() != null)
//    {
//      return generateObjectSchema(resolvedSchema, model,
//          schema, identifier);
//      
////      G oneOfSchema = generator_.generateGroupSchema(model, resolvedSchema, identifier, SchemaTemplateModelType.ONE_OF);
////      
////      schemaModelMap_.put(resolvedSchema.getUri(), oneOfSchema.asSchemaTemplateModel());
////      
////      for(Entry<String, ResolvedSchema> innerClassEntry : resolvedSchema.getInnerClasses().getResolvedProperties().entrySet())
////      {
////        S innerClass = generateSchema(innerClassEntry.getValue(), model, false);
////        oneOfSchema.addInnerClass(innerClass);
////      }
////      
////      for(ResolvedSchema subSchema : resolvedSchema.getResolvedSubSchemas().getSubSchemas())
////      {
////        S subSchemaModel = generateSchema(subSchema, model, true /* this needs to move into resolved schema */);
////        
////        oneOfSchema.addSubSchema(subSchemaModel);
////      }
////      
////      return oneOfSchema.asSchemaTemplateModel();
//    }
//    else if(schema.getType() != null)
//    {
//      switch(schema.getType())
//      {
//        case "object":
//          return generateObjectSchema(resolvedSchema, model,
//              schema, identifier);
//        
//        
//        case "array":
//          CanonCardinality cardinality = schema.getXCanonCardinality();
//          if(cardinality == null)
//          {
//            cardinality = CanonCardinality.LIST; 
//          }
//          
//          A arraySchema = generator_.generateArraySchema(model, resolvedSchema, identifier, cardinality);
//          
//          schemaModelMap_.put(resolvedSchema.getUri(), arraySchema.asSchemaTemplateModel());
//          
//          S itemsModel = generateSchema(resolvedSchema.getResolvedItems(), model, resolvedSchema.getSchema().getItemsSchema() == null);
//          
//          arraySchema.setElementType(itemsModel);
//          
//          return arraySchema.asSchemaTemplateModel();
//          
//        case "number":
//        case "boolean":
//        case "string":
//        case "integer":
//          S primitiveSchema = generator_.generatePrimativeSchema(model, resolvedSchema, identifier).asSchemaTemplateModel();
//          schemaModelMap_.put(resolvedSchema.getUri(), primitiveSchema);
//          
//          return primitiveSchema;
//          
//        default:
//          throw new CodingFault("Unknown schema type " + schema.getType());
//      }
//    }
//    else
//    {
//      throw new SyntaxErrorException("Invalid schema", resolvedSchema.getSchema().getJson().getContext());
//    }
  }

  private S generatePrimitiveSchema(ResolvedPrimitiveSchema resolvedSchema, M model,
      String identifier)
  {
    S primitiveSchema = doGeneratePrimativeSchema(model, resolvedSchema, identifier).asSchemaTemplateModel();
    schemaModelMap_.put(resolvedSchema.getUri(), primitiveSchema);
    
    return primitiveSchema;
  }

  private P doGeneratePrimativeSchema(M model,
      ResolvedPrimitiveSchema resolvedSchema, String identifier)
  {
    if(resolvedSchema instanceof ResolvedBigDecimalSchema)
      return generator_.generateBigDecimalSchema(model, (ResolvedBigDecimalSchema)resolvedSchema, identifier);
    if(resolvedSchema instanceof ResolvedBigIntegerSchema)
      return generator_.generateBigIntegerSchema(model, (ResolvedBigIntegerSchema)resolvedSchema, identifier);
    if(resolvedSchema instanceof ResolvedDoubleSchema)
      return generator_.generateDoubleSchema(model, (ResolvedDoubleSchema)resolvedSchema, identifier);
    if(resolvedSchema instanceof ResolvedFloatSchema)
      return generator_.generateFloatSchema(model, (ResolvedFloatSchema)resolvedSchema, identifier);
    if(resolvedSchema instanceof ResolvedIntegerSchema)
      return generator_.generateIntegerSchema(model, (ResolvedIntegerSchema)resolvedSchema, identifier);
    if(resolvedSchema instanceof ResolvedLongSchema)
      return generator_.generateLongSchema(model, (ResolvedLongSchema)resolvedSchema, identifier);
    if(resolvedSchema instanceof ResolvedStringSchema)
      return generator_.generateStringSchema(model, (ResolvedStringSchema)resolvedSchema, identifier);
    if(resolvedSchema instanceof ResolvedBooleanSchema)
      return generator_.generateBooleanSchema(model, (ResolvedBooleanSchema)resolvedSchema, identifier);
    
    throw new CodingFault("Unknown ResolvedPrimitiveSchema subtype " + resolvedSchema.getClass());
  }

  private S generateArraySchema(ResolvedArraySchema resolvedSchema, M model, Schema schema,
      String identifier)
  {
    CanonCardinality cardinality = schema.getXCanonCardinality();
    if(cardinality == null)
    {
      cardinality = CanonCardinality.LIST; 
    }
    
    A arraySchema = generator_.generateArraySchema(model, resolvedSchema, identifier, cardinality);
    
    schemaModelMap_.put(resolvedSchema.getUri(), arraySchema.asSchemaTemplateModel());
    
    S itemsModel = generateSchema(resolvedSchema.getResolvedItems(), model);
    
    arraySchema.setElementType(itemsModel);
    
    return arraySchema.asSchemaTemplateModel();
  }

  private S generateObjectSchema(ResolvedObjectSchema resolvedSchema, M model, Schema schema,
      String identifier)
  {
    O entity = generator_.generateObjectSchema(model, resolvedSchema, identifier);
    schemaModelMap_.put(resolvedSchema.getUri(), entity.asSchemaTemplateModel());

    if(schema.getXCanonExtends() != null)
    {
      entity.setExtends(generateSchema(resolvedSchema.getResolvedExtends(), model));
    }
    
    if(!resolvedSchema.getResolvedProperties().isEmpty())
    {
      NameCollisionDetector ncd = new NameCollisionDetector(generator_, resolvedSchema.getResolvedProperties(), false);
//              .create(canonContext, getSourceContext().getUrl(), modelContext.getGenerator(), schemaInfo.getProperties(), false);
      
      ncd.logCollisions(sourceContext_, resolvedSchema);
      
      for(Entry<String, ResolvedSchema> propertyEntry : resolvedSchema.getResolvedProperties().entrySet())
      {
        String propertyIdentifier = generator_.getIdentifierName(propertyEntry.getKey(), propertyEntry.getValue().getSchema());
//          String typeIdentifier = modelContext.getGenerator().getIdentifierName(entry.getValue().getName(), entry.getValue());
        ResolvedSchema resolvedPropertySchema = propertyEntry.getValue();
        
        S typeSchema = generateSchema(resolvedPropertySchema, model);
        boolean required = schema.getRequired() != null && schema.getRequired().contains(propertyEntry.getKey());
      
        entity.addField(propertyEntry.getKey(),
            generator_.generateField(model, propertyEntry.getKey(), propertyEntry.getValue(), propertyIdentifier, typeSchema, required));
        
      }
      
      for(Entry<String, ResolvedSchema> innerClassEntry : resolvedSchema.getInnerClasses().getResolvedProperties().entrySet())
      {
        S innerClass = generateSchema(innerClassEntry.getValue(), model);
        entity.addInnerClass(innerClassEntry.getKey(), innerClass);
      }
    }
    
    if(resolvedSchema.getResolvedAdditionalProperties() != null)
    {
      S additionalPropertiesSchema = generateSchema(resolvedSchema.getResolvedAdditionalProperties(), model);
      
      entity.setAdditionalProperties(additionalPropertiesSchema);
    }
    else if(resolvedSchema.isAdditionalPropertiesAllowed())
    {
      entity.setAdditionalPropertiesAllowed(true);
    }
    
    return entity.asSchemaTemplateModel();
  }

  public M process(GeneratorTemplateProcessor<T,M,S,O,A,P,F,G> templateProcessor) throws ParserResultException
  {
    
    String identifier = generator_.getIdentifierName(sourceContext_.getInputSourceName(), sourceContext_.getModel());
    
    M parentModel = generator_.generateOpenApiObject(sourceContext_, sourceContext_.getInputSourceName(), sourceContext_.getResolvedOpenApiObject(), identifier);
    
    NameCollisionDetector ncd = new NameCollisionDetector(generator_, sourceContext_.getSchemas(), true);
    
    ncd.logCollisions(sourceContext_, sourceContext_.getResolvedOpenApiObject());
    
    for(ResolvedSchema resolvedSchema : sourceContext_.getSchemas().values())
    {
      S model = generateSchema(resolvedSchema, parentModel);
      
      parentModel.addSchema(model);
    }
    

    sourceContext_.printErrorsAndThrowException();
  
    //gather(parentModel.asTemplateModel(), templateProcessor);
    
    templateProcessor.accept(this, parentModel.asTemplateModel());
    for(S model : schemaModelMap_.values())
    {
      templateProcessor.accept(this, model.asTemplateModel());
    }
    
    return parentModel;
  }

  public void populateTemplateModel(Map<String, Object> map)
  {
    generator_.populateTemplateModel(sourceContext_, map);
    generationContext_.populateTemplateModel(map);
  }
}
