/**
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
 * Proforma generated from
 *		Template groupId		 org.symphonyoss.s2.canon
 *           artifactId canon-template-java
 *		Template name		   proforma/java/Object/_.java.ftl
 *		Template version	   1.0
 *  At                  2020-08-21 14:27:27 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.parser;

import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.canon2.parser.model.SchemaEntity;
import com.symphony.oss.commons.dom.json.IImmutableJsonDomNode;
import com.symphony.oss.commons.dom.json.IJsonDomNode;
import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.JsonArray;
import com.symphony.oss.commons.dom.json.MutableJsonObject;
import com.symphony.oss.commons.type.provider.IIntegerProvider;
import com.symphony.oss.commons.type.provider.IStringProvider;

/**
 * Facade for Object ObjectSchema(Schema)
 *
 * A data type definition.
 * Generated from ObjectSchema(Schema) at #/components/schemas/Schema
 */
@Immutable
public class Schema extends SchemaEntity implements ISchema
{
//  private final ImmutableMap<String, ISchema> fields_;
  private final ISchema          itemsSchema_;
  private final IReferenceObject itemsReference_;
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public Schema(AbstractSchemaBuilder<?,?> builder)
  {
    super(builder);
    
    ICanonModelEntity items = initItems(null);

    itemsSchema_    = items instanceof ISchema ? (ISchema)items : null;
    itemsReference_ = items instanceof IReferenceObject ? (IReferenceObject)items : null;
  }
  
  private ICanonModelEntity initItems(IModelRegistry modelRegistry)
  {
    IImmutableJsonDomNode itemsNode = getJsonObject().get("items");
    
    if(itemsNode instanceof ImmutableJsonObject)
    {
      ImmutableJsonObject items = (ImmutableJsonObject)itemsNode;
      
      if(items.get("$ref") == null)
        return new Schema(items, modelRegistry);
      else
        return new ReferenceObject(items, modelRegistry);
      
      
    }
    else
    {
      return null;
    }
  }
  
  @Override
  public @Nullable String getXCanonIdentifier(String language)
  {
    return getJsonObject().getString(Canon2.X_CANON + language + Canon2.IDENTIFIER_SUFFIX, null);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public Schema(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    
    ICanonModelEntity items = initItems(modelRegistry);

    itemsSchema_    = items instanceof ISchema ? (ISchema)items : null;
    itemsReference_ = items instanceof IReferenceObject ? (IReferenceObject)items : null;
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public Schema(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
    
    ICanonModelEntity items = initItems(modelRegistry);

    itemsSchema_    = items instanceof ISchema ? (ISchema)items : null;
    itemsReference_ = items instanceof IReferenceObject ? (IReferenceObject)items : null;
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public Schema(ISchema other)
  {
    super(other);
    
    itemsSchema_ = other.getItemsSchema();
    itemsReference_ = other.getItemsReference();
  }

  @Override
  public IResolvedSchema resolve(IOpenApiObject openApiObject, SchemaResolver resolver, GenerationContext generationContext, ModelContext modelContext, boolean isGenerated, String name)
  {
    ResolvedSchema.Builder builder = new ResolvedSchema.Builder()
        .withValues(getJsonObject(), false, generationContext.getModelRegistry())
        //.withNameCollision(nameCollision)
        .withIsGenerated(isGenerated)
        .withName(name)
        ;
    
    IPropertiesObject                 propertiesObject  = getProperties();
    
    if(propertiesObject != null)
    {
      ResolvedPropertiesObject.Builder  propertiesBuilder = new ResolvedPropertiesObject.Builder();
      ResolvedPropertiesObject.Builder  innerClassesBuilder = new ResolvedPropertiesObject.Builder();
      
      for(Entry<String, Object> entry : propertiesObject.getProperties().entrySet())
      {
        if(entry.getValue() instanceof ISchema)
        {
          ISchema schema = (ISchema)entry.getValue();
          
          IResolvedSchema resolvedProperty = resolver.resolve(openApiObject, generationContext, modelContext, schema, entry.getKey(), false);
          
          propertiesBuilder.withProperty(entry.getKey(), resolvedProperty);
          
          switch(resolvedProperty.getType())
          {
            case "object":
              innerClassesBuilder.withProperty(entry.getKey(), resolvedProperty);
              break;
          }
        }
        else
        {
          IReferenceObject ref = (IReferenceObject)entry.getValue();
          
          String refName = ref.getFragment();
          int i = refName.lastIndexOf('/');
          
          if(i != -1)
            refName = refName.substring(i+1);
          
          try
          {
            ISchema schema = fetchSchema(openApiObject, generationContext, ref);
            
            IResolvedSchema resolvedSchema = resolver.resolve(openApiObject, generationContext, modelContext, schema, refName, true);
            
            propertiesBuilder.withProperty(entry.getKey(), resolvedSchema);
          }
          catch(GenerationException e)
          {
            modelContext.error("Invalid schema reference \"" + ref.get$ref() + "\" at " + getSourceLocation());
          }
        }
      }
      
      builder
        .withResolvedProperties(propertiesBuilder.build())
        .withInnerClasses(innerClassesBuilder.build())
        ;
    }

    
    ISchema schema = itemsSchema_;
    boolean itemsIsGenerated = false;
    
    if(schema == null && itemsReference_ != null)
    {
      try
      {
        schema = fetchSchema(openApiObject, generationContext, itemsReference_);
        itemsIsGenerated = true;
      }
      catch(GenerationException e)
      {
        modelContext.error("Invalid schema reference \"" + itemsReference_.get$ref() + "\" at " + getSourceLocation());
      }
    }
    
    if(schema != null)
    {
      builder.withResolvedItems(resolver.resolve(openApiObject, generationContext, modelContext, schema, null, itemsIsGenerated));
    }
    
    builder.withXCanonIdentifier(getXCanonIdentifier());
    builder.withXCanonFacade(getXCanonFacade());
    
    return builder.build();
  }
  
  private ISchema fetchSchema(IOpenApiObject openApiObject, GenerationContext generationContext, IReferenceObject ref) throws GenerationException
  {
    if(ref.getBaseUrl() == null)
    {
      return openApiObject.get(ref.getFragment(), ISchema.class);
    }
    else
    {
      ModelContext refGenContext = generationContext.getReferencedModel(ref.getBaseUrl());
      return refGenContext.getModel().get(ref.getFragment(), ISchema.class);
    }
  }

//  @Override
//  public Set<?> getEnumValues(String type) throws GenerationException
//  {
//      IImmutableJsonDomNode enumNode = getJsonObject().get("enum");
//      
//      if(enumNode == null)
//        return null;
//      
//      if(enumNode instanceof JsonArray)
//      {
//        if(type.equals("Integer"))
//        {
//          Set<Integer> result = new HashSet<>();
//          for(IJsonDomNode element : (JsonArray<?>)enumNode)
//          {
//            if(element instanceof IIntegerProvider)
//              result.add(((IIntegerProvider)element).asInteger());
//          }
//          
//          return result;
//        }
//        else if(type.equals("String"))
//        {
//          Set<String> result = new HashSet<>();
//          for(IJsonDomNode element : (JsonArray<?>)enumNode)
//          {
//            if(element instanceof IStringProvider)
//              result.add(((IStringProvider)element).asString());
//          }
//          
//          return result;
//        }
//        else
//            throw new GenerationException("Invalid enum type " + type);
//      }
//      
//      throw new GenerationException("enums must be an array");
//  }
  
  @Override
  public ISchema getItemsSchema()
  {
    return itemsSchema_;
  }
  @Override
  public IReferenceObject getItemsReference()
  {
    return itemsReference_;
  }

  @Override
  public void validate(GenerationContext generationContext)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void fetchReferences(GenerationContext generationContext) throws GenerationException
  {
    IPropertiesObject propertiesObject = getProperties();
    
    if(propertiesObject != null)
    {
      for(Entry<String, Object> entry : propertiesObject.getProperties().entrySet())
      {
        if(entry.getValue() instanceof IReferenceObject)
        {
          generationContext.addReferencedModel(((IReferenceObject)entry.getValue()).getBaseUrl());
        }
      }
    }
    
    if(itemsReference_ != null)
    {
      generationContext.addReferencedModel(itemsReference_.getBaseUrl());
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */