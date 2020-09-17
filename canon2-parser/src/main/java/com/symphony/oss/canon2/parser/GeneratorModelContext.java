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

package com.symphony.oss.canon2.parser;

import java.util.Map.Entry;

import com.symphony.oss.canon2.model.CanonCardinality;
import com.symphony.oss.canon2.model.GenerationException;
import com.symphony.oss.canon2.model.IModelContext;
import com.symphony.oss.canon2.model.ResolvedModel;
import com.symphony.oss.canon2.model.ResolvedPropertiesObject;
import com.symphony.oss.canon2.model.ResolvedSchema;
import com.symphony.oss.commons.fault.CodingFault;

public abstract class GeneratorModelContext<
T extends ITemplateModel<T,M,S>,
M extends IOpenApiTemplateModel<T,M,S>,
S extends ISchemaTemplateModel<T,M,S>,
O extends IObjectSchemaTemplateModel<T,M,S,F>,
A extends IArraySchemaTemplateModel<T,M,S>,
P extends IPrimitiveSchemaTemplateModel<T,M,S>,
F extends IFieldTemplateModel<T,M,S>>
  implements IGeneratorModelContext<T,M,S,O,A,P,F>
{
  private final ICanonGenerator<T,M,S,O,A,P,F> generator_;
  private final IModelContext sourceContext_;
  private final IPathNameConstructor<T> templatePathBuilder_;
  
  public GeneratorModelContext(ICanonGenerator<T,M,S,O,A,P,F> generator, IModelContext context,
      IPathNameConstructor<T> templatePathBuilder)
  {
    generator_ = generator;
    sourceContext_ = context;
    templatePathBuilder_ = templatePathBuilder;
  }
  
  @Override
  public ICanonGenerator<T,M,S,O,A,P,F> getGenerator()
  {
    return generator_;
  }

  @Override
  public IModelContext getSourceContext()
  {
    return sourceContext_;
  }

  @Override
  public IPathNameConstructor<T> getPathBuilder(TemplateType templateType)
  {
//    switch(templateType)
//    {
//      case TEMPLATE:
        return templatePathBuilder_;
//      
//      case PROFORMA:
//        return proformaPathBuilder_;
//    }
//    
//    throw new CodingFault("Unexpected TemplateType " + templateType);
  }

  @Override
  public M generateModel(ResolvedModel resolvedModel,
      IGeneratorModelContext<T, M, S, O, A, P, F> modelContext) throws GenerationException
  {
    String name = modelContext.getSourceContext().getInputSourceName();
    String identifier = modelContext.getGenerator().getIdentifierName(modelContext.getSourceContext().getInputSourceName(), resolvedModel);
    
    M parentModel = modelContext.generateOpenApiObject(resolvedModel, name, identifier);
    
    NameCollisionDetector ncd = new NameCollisionDetector(modelContext.getGenerator(), resolvedModel.getResolvedSchemas(), true);
    
    ncd.logCollisions(modelContext.getSourceContext());
    
    for(Entry<String, ResolvedSchema> entry : resolvedModel.getResolvedSchemas().entrySet())
    {
      S model = generateSchema(entry.getValue(), parentModel, entry.getKey(), 
          modelContext, false);
      
      parentModel.addSchema(model);
    }
    
    return parentModel;
  }

  @Override
  @SuppressWarnings("unchecked")
  public S generateSchema(ResolvedSchema resolvedSchema, M model, String name,
      IGeneratorModelContext<T, M, S, O, A, P, F> modelContext, boolean isReference) throws GenerationException
  {
    String identifier = modelContext.getGenerator().getIdentifierName(name, resolvedSchema);
    
    switch(resolvedSchema.getType())
    {
      case "object":
      {
        //IObjectSchemaTemplateModel<S> 
        O entity = modelContext.generateObjectSchema(model, resolvedSchema, name, identifier, isReference);
        
        ResolvedPropertiesObject propertiesObject = resolvedSchema.getResolvedProperties();
        
        if(propertiesObject != null)
        {
          NameCollisionDetector ncd = new NameCollisionDetector(modelContext.getGenerator(), propertiesObject.getResolvedProperties(), false);
          
          ncd.logCollisions(modelContext.getSourceContext());
          
          for(Entry<String, ResolvedSchema> entry : propertiesObject.getResolvedProperties().entrySet())
          {
            String propertyIdentifier = modelContext.getGenerator().getIdentifierName(entry.getKey(), entry.getValue());
//            String typeIdentifier = modelContext.getGenerator().getIdentifierName(entry.getValue().getName(), entry.getValue());
            
             S typeSchema = generateSchema(entry.getValue(), model, entry.getValue().getName(),
                modelContext, true);
            boolean required = resolvedSchema.getRequired().contains(entry.getKey());
            
            entity.addField(modelContext.generateField(model, entry.getValue(), entry.getKey(), propertyIdentifier, 
                typeSchema, required));
          }
        }
        
        return (S) entity;
      }
      
      case "array":
        CanonCardinality cardinality = resolvedSchema.getXCanonCardinality();
        if(cardinality == null)
        {
          cardinality = CanonCardinality.LIST; 
        }
        
        return (S) modelContext.generateArraySchema(model, resolvedSchema, name, identifier, isReference, cardinality);
        
      case "number":
      case "boolean":
      case "string":
      case "integer":
        return (S) modelContext.generatePrimativeSchema(model, resolvedSchema, name, identifier, isReference);
        
      default:
        throw new CodingFault("Unknown schema type " + resolvedSchema.getType());
    }
  }
}
