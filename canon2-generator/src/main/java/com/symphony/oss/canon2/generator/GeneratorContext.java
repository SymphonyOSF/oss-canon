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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon2.core.GenerationException;
import com.symphony.oss.canon2.core.ResolvedSchema;
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
F extends IFieldTemplateModel<T,M,S>>
{
  private static Logger log_ = LoggerFactory.getLogger(GeneratorContext.class);

  private final CanonGenerationContext generationContext_;
  private final ICanonGenerator<T,M,S,O,A,P,F>  generator_;
  private final SourceContext sourceContext_;
  private final JsonObject generatorConfig_;
  private final IPathNameConstructor<T> pathBuilder_;
  private final Map<String, S> schemaModelMap_ = new HashMap<>();
  
  public GeneratorContext(CanonGenerationContext generationContext, ICanonGenerator<T,M,S,O,A,P,F> generator, SourceContext sourceContext)
  {
    generationContext_ = generationContext;
    generator_ = generator;
    sourceContext_ = sourceContext;
    generatorConfig_ = generator_.getConfig(sourceContext_);
    pathBuilder_ = generator_.createPathBuilder(sourceContext_);
  }
  
  public ICanonGenerator<T,M,S,O,A,P,F> getGenerator()
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

//  public M generateModel(SourceContext sourceContext_) throws GenerationException
//  {
//    String name = sourceContext_.getInputSourceName();
//    String identifier = generator_.getIdentifierName(sourceContext_.getInputSourceName(), sourceContext_.getModel());
//    
//    M parentModel = generator_.generateOpenApiObject(sourceContext_, name, identifier);
//    
//    NameCollisionDetector ncd = new NameCollisionDetector(generator_, sourceContext_.getSchemas(), true);
//    
//    ncd.logCollisions(sourceContext_);
//    
//    for(Entry<String, ResolvedSchema> schemaEntry : sourceContext_.getSchemas().entrySet())
//    {
//      S model = generateSchema(schemaEntry, parentModel, false);
//      
//      parentModel.addSchema(model);
//    }
//    
//    return parentModel;
//  }

  //@SuppressWarnings("unchecked")
  S generateSchema(Entry<String, ResolvedSchema> schemaEntry, M model,
      boolean isReference) throws GenerationException
  {
    return generateSchema(schemaEntry.getKey(),  schemaEntry.getValue(), model, isReference);
  }

  //@SuppressWarnings("unchecked")
  S generateSchema(String name, ResolvedSchema resolvedSchema, M model,
      boolean isReference) throws GenerationException
  {
    System.err.println("generateSchema " + name + " " + resolvedSchema.getSchema());
    
    S existingSchema = schemaModelMap_.get(resolvedSchema.getUri());
    
    if(existingSchema != null)
      return existingSchema;
    
    Schema schema = resolvedSchema.getSchema();
    String identifier = generator_.getIdentifierName(name, schema);
    
    switch(schema.getType())
    {
      case "object":
      {
        O entity = generator_.generateObjectSchema(model, name, resolvedSchema, identifier, isReference);
        schemaModelMap_.put(resolvedSchema.getUri(), entity.asSchemaTemplateModel());
        
//        Map<String, F> fieldMap = new HashMap<>();
//        Map<String, S> innerClassMap = new HashMap<>();
        if(schema.getXCanonExtends() != null)
        {
          entity.setExtends(generateSchema("extends", resolvedSchema.getResolvedExtends(), model, true));
        }
        
        if(!resolvedSchema.getResolvedProperties().isEmpty())
        {
          
//        }
//        PropertiesObject propertiesObject = resolvedSchema.getProperties();
//        
//        if(propertiesObject != null)
//        {
          NameCollisionDetector ncd = new NameCollisionDetector(generator_, resolvedSchema.getResolvedProperties(), false);
//              .create(canonContext, getSourceContext().getUrl(), modelContext.getGenerator(), schemaInfo.getProperties(), false);
          
          ncd.logCollisions(sourceContext_);
          
          for(Entry<String, ResolvedSchema> propertyEntry : resolvedSchema.getResolvedProperties().entrySet())
          {
            String propertyIdentifier = generator_.getIdentifierName(propertyEntry.getKey(), propertyEntry.getValue().getSchema());
//          String typeIdentifier = modelContext.getGenerator().getIdentifierName(entry.getValue().getName(), entry.getValue());
            ResolvedSchema resolvedPropertySchema = propertyEntry.getValue();
            
            S typeSchema = generateSchema(resolvedPropertySchema.getName(), resolvedPropertySchema, model, true);
            boolean required = schema.getRequired() != null && schema.getRequired().contains(propertyEntry.getKey());
          
            entity.addField(propertyEntry.getKey(),
                generator_.generateField(model, propertyEntry.getKey(), propertyEntry.getValue(), propertyIdentifier, typeSchema, required));
            
          }
          
          for(Entry<String, ResolvedSchema> innerClassEntry : resolvedSchema.getInnerClasses().getResolvedProperties().entrySet())
          {
            S innerClass = generateSchema(innerClassEntry, model, false);
            entity.addInnerClass(innerClassEntry.getKey(), innerClass);
          }
          
//          for(Entry<String, Object> entry : propertiesObject.getProperties().entrySet())
//          {
//            Schema schema;
//            String schemaName;
//            
//            if(entry.getValue() instanceof Schema)
//            {
//              schema = (Schema)entry.getValue();
//              schemaName = entry.getKey();
//            }
//            else
//            {
//              ReferenceObject ref = (ReferenceObject)entry.getValue();
//              
//              String refName = ref.getFragment();
//              int i = refName.lastIndexOf('/');
//              
//              if(i != -1)
//                refName = refName.substring(i+1);
//              
//              SchemaInfo schemaInfo = canonContext.getSchemaInfo(ref.getAbsoluteUri(getSourceContext().getUrl()));
//              schema = schemaInfo.getSchema();
//              schemaName = schemaInfo.getName();
//            }
//            String propertyIdentifier = modelContext.getGenerator().getIdentifierName(entry.getKey(), schema);
////            String typeIdentifier = modelContext.getGenerator().getIdentifierName(entry.getValue().getName(), entry.getValue());
//            
//             S typeSchema = generateSchema(canonContext, schema, model, schemaName,
//                modelContext, true);
//            boolean required = resolvedSchema.getRequired().contains(entry.getKey());
//            
//            entity.addField(modelContext.generateField(model, schema, entry.getKey(), propertyIdentifier, 
//                typeSchema, required));
//          }
        }
        
        

        return entity.asSchemaTemplateModel();
      }
      
      case "array":
        CanonCardinality cardinality = schema.getXCanonCardinality();
        if(cardinality == null)
        {
          cardinality = CanonCardinality.LIST; 
        }
        
        A arraySchema = generator_.generateArraySchema(model, name, resolvedSchema, identifier, isReference, cardinality);
        
        schemaModelMap_.put(resolvedSchema.getUri(), arraySchema.asSchemaTemplateModel());
        
        S itemsModel = generateSchema("items", resolvedSchema.getResolvedItems(), model, resolvedSchema.getSchema().getItemsSchema() == null);
        
        arraySchema.setElementType(itemsModel);
        
        return arraySchema.asSchemaTemplateModel();
        
      case "number":
      case "boolean":
      case "string":
      case "integer":
        S primitiveSchema = generator_.generatePrimativeSchema(model, name, resolvedSchema, identifier, isReference).asSchemaTemplateModel();
        schemaModelMap_.put(resolvedSchema.getUri(), primitiveSchema);
        
        return primitiveSchema;
        
      default:
        throw new CodingFault("Unknown schema type " + schema.getType());
    }
  }
  
//  private class GenerationHelper
//  <
//  T extends ITemplateModel<T,M,S>,
//  M extends IOpenApiTemplateModel<T,M,S>,
//  S extends ISchemaTemplateModel<T,M,S>,
//  O extends IObjectSchemaTemplateModel<T,M,S,F>,
//  A extends IArraySchemaTemplateModel<T,M,S>,
//  P extends IPrimitiveSchemaTemplateModel<T,M,S>,
//  F extends IFieldTemplateModel<T,M,S>>
//  {
//    SourceContext context;
//    ICanonGenerator<T,M,S,O,A,P,F> generator;
//    TemplateModelConsumer consumer;
//
//    GenerationHelper(SourceContext context, ICanonGenerator<T,M,S,O,A,P,F> generator, TemplateModelConsumer consumer)
//    {
//      this.context = context;
//      this.generator = generator;
//      this.consumer = consumer;
//    }

//    void generateFor() throws GenerationException
//    {
//      JsonObject generatorConfig = sourceContext_.getModel().getXCanonGenerators().getJsonObject().getRequiredObject(generator.getLanguage());
//    
//      GeneratorContext<T,M,S,O,A,P,F> generatorModelContext = generator.createModelContext(CanonModelContext.this, sourceContext_, generatorConfig);
//    
//      M templateModel = generatorModelContext.generateModel();
//      
//      if(context.printErrors())
//        throw new GenerationException("Generation failed for " + context.getInputSourceName());
//    
//      gather(generatorModelContext, templateModel.asTemplateModel(), consumer);
//      //generator.generate(context.getModel(), context, this, consumer);
//
//    }

    
//  }
    


    public M process(GeneratorTemplateProcessor<T,M,S,O,A,P,F> templateProcessor) throws GenerationException
    {
      
      String identifier = generator_.getIdentifierName(sourceContext_.getInputSourceName(), sourceContext_.getModel());
      
      M parentModel = generator_.generateOpenApiObject(sourceContext_, sourceContext_.getInputSourceName(), sourceContext_.getResolvedOpenApiObject(), identifier);
      
      NameCollisionDetector ncd = new NameCollisionDetector(generator_, sourceContext_.getSchemas(), true);
      
      ncd.logCollisions(sourceContext_);
      
      for(Entry<String, ResolvedSchema> schemaEntry : sourceContext_.getSchemas().entrySet())
      {
        S model = generateSchema(schemaEntry, parentModel,
            false);
        
        parentModel.addSchema(model);
      }
      

      if(sourceContext_.printErrors())
        throw new GenerationException("Generation failed for " + sourceContext_.getInputSourceName());
    
      //gather(parentModel.asTemplateModel(), templateProcessor);
      
      templateProcessor.accept(this, parentModel.asTemplateModel());
      for(S model : schemaModelMap_.values())
      {
        templateProcessor.accept(this, model.asTemplateModel());
      }
      
      return parentModel;
    }
    
//    private void gather(T model, GeneratorTemplateProcessor<T,M,S,O,A,P,F> templateProcessor)
//    {
//      templateProcessor.accept(this, model);
//      
//      for(T child : model.getChildren())
//        gather(child, templateProcessor);
//    }

    public void populateTemplateModel(Map<String, Object> map)
    {
      generator_.populateTemplateModel(sourceContext_, map);
      generationContext_.populateTemplateModel(map);
    }
}
