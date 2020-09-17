/**
 * GENERATED CODE - DO NOT EDIT OR CHECK IN TO SOURCE CODE CONTROL
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
 *    At                   2020-09-16 16:04:42 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.model;

import java.util.Map.Entry;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon2.runtime.java.Entity;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;


/**
 * Facade for Object  Schema canon
 * Object com.symphony.oss.canon2.generator.java.JavaOpenApiTemplateModel@4df50bcc
 * Generated from JavaObjectSchemaTemplateModel [fields_=[JavaFieldTemplateModel MaxItems maxItems, JavaFieldTemplateModel Format format, JavaFieldTemplateModel CanonCardinality xCanonCardinality, JavaFieldTemplateModel XCanonIdentifier xCanonIdentifier, JavaFieldTemplateModel Type type, JavaFieldTemplateModel XCanonFacade xCanonFacade, JavaFieldTemplateModel Enum enum, JavaFieldTemplateModel Required required, JavaFieldTemplateModel MinItems minItems, JavaFieldTemplateModel CanonAttributes xCanonAttributes, JavaFieldTemplateModel Maximum maximum, JavaFieldTemplateModel Minimum minimum, JavaFieldTemplateModel PropertiesObject properties]] at {entity.context.path}
 */
@Immutable
public class Schema extends SchemaEntity
{
  //private final ImmutableMap<String, ISchema> fields_;
  private final Schema          itemsSchema_;
  private final ReferenceObject itemsReference_;

  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public Schema(AbstractBuilder<?,?> builder)
  {
    super(builder);
    
    Entity items = initItems(null);

    itemsSchema_    = items instanceof Schema ? (Schema)items : null;
    itemsReference_ = items instanceof ReferenceObject ? (ReferenceObject)items : null;
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public Schema(JsonObject jsonObject, ModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    
    Entity items = initItems(modelRegistry);

    itemsSchema_    = items instanceof Schema ? (Schema)items : null;
    itemsReference_ = items instanceof ReferenceObject ? (ReferenceObject)items : null;
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public Schema(Schema other)
  {
    super(other);
    
    itemsSchema_ = other.getItemsSchema();
    itemsReference_ = other.getItemsReference();
  }
  
  private Entity initItems(ModelRegistry modelRegistry)
  {
    JsonDomNode itemsNode = getJsonObject().get("items");
    
    if(itemsNode instanceof JsonObject)
    {
      JsonObject items = (JsonObject)itemsNode;
      
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
  
  public @Nullable String getXCanonIdentifier(String language)
  {
    return getJsonObject().getString("x-canon-" + language + "-identifier", null);
  }

  public ResolvedSchema resolve(OpenApiObject openApiObject, SchemaResolver resolver, ICanonContext generationContext, IModelContext modelContext, boolean isGenerated, String name)
  {
    ResolvedSchema.Builder builder = new ResolvedSchema.Builder()
        .withValues(getJsonObject(), generationContext.getModelRegistry())
        //.withNameCollision(nameCollision)
        .withIsGenerated(isGenerated)
        .withName(name)
        ;
    
    PropertiesObject                 propertiesObject  = getProperties();
    
    if(propertiesObject != null)
    {
      ResolvedPropertiesObject.Builder  propertiesBuilder = new ResolvedPropertiesObject.Builder();
      ResolvedPropertiesObject.Builder  innerClassesBuilder = new ResolvedPropertiesObject.Builder();
      
      for(Entry<String, Object> entry : propertiesObject.getProperties().entrySet())
      {
        if(entry.getValue() instanceof Schema)
        {
          Schema schema = (Schema)entry.getValue();
          
          ResolvedSchema resolvedProperty = resolver.resolve(openApiObject, generationContext, modelContext, schema, entry.getKey(), false);
          
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
          ReferenceObject ref = (ReferenceObject)entry.getValue();
          
          String refName = ref.getFragment();
          int i = refName.lastIndexOf('/');
          
          if(i != -1)
            refName = refName.substring(i+1);
          
          try
          {
            Schema schema = fetchSchema(openApiObject, generationContext, ref);
            
            ResolvedSchema resolvedSchema = resolver.resolve(openApiObject, generationContext, modelContext, schema, refName, true);
            
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

    
    Schema schema = itemsSchema_;
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
  
  public String getSourceLocation()
  {
    return "unknown location";
  }

  private Schema fetchSchema(OpenApiObject openApiObject, ICanonContext generationContext, ReferenceObject ref) throws GenerationException
  {
    if(ref.getBaseUrl() == null)
    {
      return openApiObject.get(ref.getFragment(), Schema.class);
    }
    else
    {
      IModelContext refGenContext = generationContext.getReferencedModel(ref.getBaseUrl());
      return refGenContext.getModel().get(ref.getFragment(), Schema.class);
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
  
  public Schema getItemsSchema()
  {
    return itemsSchema_;
  }

  public ReferenceObject getItemsReference()
  {
    return itemsReference_;
  }

  public void validate(ICanonContext generationContext)
  {
    // TODO Auto-generated method stub
    
  }

  public void fetchReferences(ICanonContext generationContext) throws GenerationException
  {
    PropertiesObject propertiesObject = getProperties();
    
    if(propertiesObject != null)
    {
      for(Entry<String, Object> entry : propertiesObject.getProperties().entrySet())
      {
        if(entry.getValue() instanceof ReferenceObject)
        {
          generationContext.addReferencedModel(((ReferenceObject)entry.getValue()).getBaseUrl());
        }
      }
    }
    
    if(itemsReference_ != null)
    {
      generationContext.addReferencedModel(itemsReference_.getBaseUrl());
    }
  }
  
  /**
   * Abstract builder for Schema. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends Schema> extends SchemaEntity.AbstractBuilder<T,B>
  {
    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */