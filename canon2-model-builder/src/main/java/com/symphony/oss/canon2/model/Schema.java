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
 *    At                   2020-10-08 13:02:45 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.model;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Map.Entry;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon2.core.CanonModelContext;
import com.symphony.oss.canon2.core.GenerationException;
import com.symphony.oss.canon2.core.ResolvedOpenApiObject;
import com.symphony.oss.canon2.core.ResolvedPropertiesObject;
import com.symphony.oss.canon2.core.ResolvedSchema;
import com.symphony.oss.canon2.core.SourceContext;
import com.symphony.oss.canon2.core.ResolvedSchema.SingletonBuilder;
import com.symphony.oss.canon2.runtime.java.Entity;


/**
 * Facade for Object  Schema canon
 * Object Schema
 * Generated from Schema
 */
@Immutable
public class Schema extends SchemaEntity
{
  //private final ImmutableMap<String, ISchema> fields_;
  private final Schema          itemsSchema_;
  private final ReferenceObject itemsReference_;
  
  /**
   * Constructor.
   * 
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  protected Schema(Initialiser initialiser)
  {
    super(initialiser);

    Entity      items = null; 
    JsonDomNode itemsNode = getJsonObject().get("items");
    
    if(itemsNode instanceof JsonObject)
    {
      JsonObject itemsObject = (JsonObject)itemsNode;
      
      if(itemsObject.get("$ref") == null)
        items = Schema.FACTORY.newInstance(itemsObject /*, modelRegistry */);
      else
        items = ReferenceObject.FACTORY.newInstance(itemsObject /*, modelRegistry */);
    }
    
    itemsSchema_    = items instanceof Schema ? (Schema)items : null;
    itemsReference_ = items instanceof ReferenceObject ? (ReferenceObject)items : null;
  }
  

  public interface IInstanceOrBuilder extends SchemaEntity.IInstanceOrBuilder
  {
  }
  
  /**
   * Abstract builder for Schema. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends Schema>
    extends SchemaEntity.AbstractBuilder<T,B>
    implements IInstanceOrBuilder
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
  
  public @Nullable String getXCanonIdentifier(String language)
  {
    return getJsonObject().getString("x-canon-" + language + "-identifier", null);
  }
  
  //public void resolve(CanonModelContext generationContext, SchemaInfo schemaInfo)
  
  public void link(ResolvedOpenApiObject.SingletonBuilder openApiObjectBuilder, ResolvedSchema.SingletonBuilder builder, CanonModelContext modelContext, SourceContext sourceContext, String uri,  boolean generated) throws GenerationException
  {
    ResolvedPropertiesObject.SingletonBuilder  resolvedPropertiesBuilder = new ResolvedPropertiesObject.SingletonBuilder();
    ResolvedPropertiesObject.SingletonBuilder  innerClassesBuilder       = new ResolvedPropertiesObject.SingletonBuilder();
    builder
        .withSchema(this)
        .withResolvedProperties(resolvedPropertiesBuilder)
        .withInnerClasses( innerClassesBuilder)
        .withGenerated(generated)
        ;
    
    PropertiesObject propertiesObject  = getProperties();
    
    if(propertiesObject != null)
    {
      for(Entry<String, Object> entry : propertiesObject.getProperties().entrySet())
      {
        if(entry.getValue() instanceof Schema)
        {
          Schema schema = (Schema)entry.getValue();
          
          ResolvedSchema.SingletonBuilder resolvedProperty = modelContext.link(openApiObjectBuilder, sourceContext, entry.getKey(), uri + "/" + entry.getKey(), schema, isGenerated(schema.getType()));
          resolvedPropertiesBuilder.with(entry.getKey(), resolvedProperty);
          
          switch(schema.getType())
          {
            case "object":
              innerClassesBuilder.with(entry.getKey(), resolvedProperty);
              break;
          }
        }
        else
        {
          ReferenceObject ref = (ReferenceObject)entry.getValue();
          
          try
          {
//            Schema schema = fetchSchema(modelContext, sourceContext, ref);
//            ResolvedSchema.SingletonBuilder resolvedProperty = modelContext.link(sourceContext, sourceContext.getSchemaUri(entry.getKey()), schema, false); // isGenerated was true here in the previous version, what does this actually mean?
            resolvedPropertiesBuilder.with(entry.getKey(), fetchSchema(openApiObjectBuilder, modelContext, sourceContext, ref, true));
          }
          catch(GenerationException e)
          {
            sourceContext.error("Invalid schema reference \"" + ref.get$ref() + "\" at " + getSourceLocation());
          }
        }
      }
    }

    if(itemsSchema_ != null)
    { 
      builder.withResolvedItems(
          modelContext.link(openApiObjectBuilder, sourceContext, "items BRUCE 001", uri + "/items", itemsSchema_, isGenerated(itemsSchema_.getType()))
          //itemsSchema_.link(modelContext, sourceContext, uri + "/items", true)
          );
    }
    else if(itemsReference_ != null)
    {
//      Schema schema = fetchSchema(modelContext, sourceContext, itemsReference_, uri, "items", false);
      builder.withResolvedItems(fetchSchema(openApiObjectBuilder, modelContext, sourceContext, itemsReference_, true));
      
//          modelContext.link(sourceContext, uri + "/items", schema, generated)
//          schema.link(modelContext, sourceContext, itemsReference_.getAbsoluteUri(sourceContext.getUrl()), false));
    }
    
//    Schema schema = itemsSchema_;
//    boolean itemsIsGenerated = false;
//    String  itemsName = "items";
//    String itemsPath = schemaInfo.getName() + "/items";
//    
//    if(schema == null && itemsReference_ != null)
//    {
//      try
//      {
//        schema = fetchSchema(schemaInfo.getOpenApiObject(), generationContext, itemsReference_);
//        itemsIsGenerated = true;
//        itemsName = itemsPath = itemsReference_.getName();
//      }
//      catch(GenerationException e)
//      {
//        schemaInfo.getModelContext().error("Invalid schema reference \"" + itemsReference_.get$ref() + "\" at " + getSourceLocation());
//      }
//    }
//    
//    if(schema != null)
//    {
//      SchemaInfo itemsInfo = new SchemaInfo(schemaInfo, itemsName, itemsPath, schema, itemsIsGenerated);
//      
//      generationContext.resolve(itemsInfo);
//      
//      schemaInfo.setItems(itemsInfo);
//      
////      builder.withResolvedItems(resolver.resolve(openApiObject, generationContext, modelContext, schema, null, itemsIsGenerated));
//    }
    
    if(getXCanonExtends() != null)
    {
      try
      {
        //Schema extendsSchema = fetchSchema(modelContext, sourceContext, getXCanonExtends());
        builder.withResolvedExtends(fetchSchema(openApiObjectBuilder, modelContext, sourceContext, getXCanonExtends(), true));
      }
      catch(GenerationException e)
      {
        sourceContext.error("Invalid schema reference \"" + getXCanonExtends().get$ref() + "\" at " + getSourceLocation());
      }
    }
  }
  
  private boolean isGenerated(String type)
  {
    // TODO: replace with type enum
    
    if("array".equals(type))
      System.err.println("HERE");
    
    return "object".equals(type); // || "array".equals(type);
    
  }

  public String getSourceLocation()
  {
    return "unknown location";
  }

  private SingletonBuilder fetchSchema(ResolvedOpenApiObject.SingletonBuilder openApiObjectBuilder, CanonModelContext modelContext, SourceContext sourceContext, ReferenceObject ref,
      boolean generated) throws GenerationException
  {
    Schema schema;
    String uri;
    
    URI x = ref.getBaseUri();
    if(ref.getBaseUri() == null)
    {
      try
      {
        schema = sourceContext.getModel().get(ref.getFragment(), Schema.class);
      }
      catch(IllegalArgumentException e)
      {
        throw new GenerationException("No such schema \"" + ref.getFragment() + "\"");
      }
      uri = sourceContext.getUrl() + ref.getFragment();
    }
    else
    {
      try
      {
        URL url = new URL(sourceContext.getUrl(), ref.getBaseUri().toString());
        
        sourceContext = modelContext.getReferencedModel(url);
        schema = sourceContext.getModel().get(ref.getFragment(), Schema.class);
        uri = ref.get$ref();
        openApiObjectBuilder = sourceContext.getResolvedOpenApiObjectBuilder();
      }
      catch (MalformedURLException e)
      {
        throw new GenerationException(e);
      }
    }
    
    return modelContext.link(openApiObjectBuilder, sourceContext, ref.getName(), uri, schema, generated);
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

  public void fetchReferences(CanonModelContext generationContext, SourceContext sourceContext) throws GenerationException
  {
    try
    {
      PropertiesObject propertiesObject = getProperties();
      
      if(propertiesObject != null)
      {
        for(Entry<String, Object> entry : propertiesObject.getProperties().entrySet())
        {
          if(entry.getValue() instanceof ReferenceObject)
          {
            
            generationContext.addReferencedModel(((ReferenceObject)entry.getValue()).getAbsoluteBaseUrl(sourceContext.getUrl()));
          }
        }
      }
      
      if(itemsReference_ != null)
      {
        generationContext.addReferencedModel((itemsReference_).getAbsoluteBaseUrl(sourceContext.getUrl()));
//        URL url = new URL(sourceContext.getUrl(), itemsReference_.getBaseUri().toString());
//        
//        generationContext.addReferencedModel(url);
      }
    }
    catch(MalformedURLException e)
    {
      throw new GenerationException(e);
    }
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
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */