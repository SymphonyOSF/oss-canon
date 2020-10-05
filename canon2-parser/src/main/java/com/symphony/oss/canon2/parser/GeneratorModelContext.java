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

import com.symphony.oss.canon2.model.CanonCardinality;
import com.symphony.oss.canon2.model.GenerationException;
import com.symphony.oss.canon2.model.ICanonContext;
import com.symphony.oss.canon2.model.IModelContext;
import com.symphony.oss.canon2.model.Schema;
import com.symphony.oss.canon2.model.SchemaInfo;
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
  private final ICanonGenerator<T,M,S,O,A,P,F>  generator_;
  private final ICanonContext                   canonContext_;
  private final IModelContext                   sourceContext_;
  private final IPathNameConstructor<T>         templatePathBuilder_;
  
  public GeneratorModelContext(ICanonGenerator<T,M,S,O,A,P,F> generator, ICanonContext canonContext, IModelContext context,
      IPathNameConstructor<T> templatePathBuilder)
  {
    generator_ = generator;
    canonContext_ = canonContext;
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
  public M generateModel() throws GenerationException
  {
    String name = sourceContext_.getInputSourceName();
    String identifier = generator_.getIdentifierName(sourceContext_.getInputSourceName(), sourceContext_.getModel());
    
    M parentModel = generateOpenApiObject(sourceContext_, name, identifier);
    
    NameCollisionDetector ncd = new NameCollisionDetector(generator_, sourceContext_.getSchemas(), true);
    
    ncd.logCollisions(sourceContext_);
    
    for(SchemaInfo schemaInfo : sourceContext_.getSchemas())
    {
      S model = generateSchema(schemaInfo, parentModel, schemaInfo.getName(), 
          false);
      
      parentModel.addSchema(model);
    }
    
    return parentModel;
  }

  @Override
  @SuppressWarnings("unchecked")
  public S generateSchema(SchemaInfo schemaInfo, M model, String name,
      boolean isReference) throws GenerationException
  {
    Schema resolvedSchema = schemaInfo.getSchema();
    String identifier = generator_.getIdentifierName(name, resolvedSchema);
    
    switch(resolvedSchema.getType())
    {
      case "object":
      {
        //IObjectSchemaTemplateModel<S> 
        O entity = generateObjectSchema(model, schemaInfo, name, identifier, isReference);
        
        if(!schemaInfo.getProperties().isEmpty())
        {
          
//        }
//        PropertiesObject propertiesObject = resolvedSchema.getProperties();
//        
//        if(propertiesObject != null)
//        {
          NameCollisionDetector ncd = new NameCollisionDetector(generator_, schemaInfo.getProperties(), false);
//              .create(canonContext, getSourceContext().getUrl(), modelContext.getGenerator(), schemaInfo.getProperties(), false);
          
          ncd.logCollisions(sourceContext_);
          
          for(SchemaInfo propertySchemaInfo : schemaInfo.getProperties())
          {
            String propertyIdentifier = generator_.getIdentifierName(propertySchemaInfo.getName(), propertySchemaInfo.getSchema());
//          String typeIdentifier = modelContext.getGenerator().getIdentifierName(entry.getValue().getName(), entry.getValue());
          
           S typeSchema = generateSchema(propertySchemaInfo, model, propertySchemaInfo.getName(),
               true);
          boolean required = resolvedSchema.getRequired() != null && resolvedSchema.getRequired().contains(propertySchemaInfo.getName());
          
          entity.addField(generateField(model, propertySchemaInfo, propertySchemaInfo.getName(), propertyIdentifier, 
              typeSchema, required));
            
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
        
        return (S) entity;
      }
      
      case "array":
        CanonCardinality cardinality = resolvedSchema.getXCanonCardinality();
        if(cardinality == null)
        {
          cardinality = CanonCardinality.LIST; 
        }
        
        return (S) generateArraySchema(model, schemaInfo, name, identifier, isReference, cardinality);
        
      case "number":
      case "boolean":
      case "string":
      case "integer":
        return (S) generatePrimativeSchema(model, schemaInfo, name, identifier, isReference);
        
      default:
        throw new CodingFault("Unknown schema type " + resolvedSchema.getType());
    }
  }
}
