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
import java.net.URL;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Consumer;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.json.IParserContext;
import com.symphony.oss.canon.json.ParserErrorException;
import com.symphony.oss.canon.json.ParserException;
import com.symphony.oss.canon.json.ParserWarningException;
import com.symphony.oss.canon.json.SyntaxErrorException;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon2.core.CanonModelContext;
import com.symphony.oss.canon2.core.INamedModelEntity;
import com.symphony.oss.canon2.core.ResolvedArraySchema;
import com.symphony.oss.canon2.core.ResolvedBigDecimalSchema;
import com.symphony.oss.canon2.core.ResolvedBigIntegerSchema;
import com.symphony.oss.canon2.core.ResolvedBooleanSchema;
import com.symphony.oss.canon2.core.ResolvedDoubleSchema;
import com.symphony.oss.canon2.core.ResolvedFloatSchema;
import com.symphony.oss.canon2.core.ResolvedIntegerSchema;
import com.symphony.oss.canon2.core.ResolvedLongSchema;
import com.symphony.oss.canon2.core.ResolvedNumberSchema;
import com.symphony.oss.canon2.core.ResolvedObjectSchema;
import com.symphony.oss.canon2.core.ResolvedOpenApiObject;
import com.symphony.oss.canon2.core.ResolvedOpenApiObject.SingletonBuilder;
import com.symphony.oss.canon2.core.ResolvedPropertiesObject;
import com.symphony.oss.canon2.core.ResolvedSchema;
import com.symphony.oss.canon2.core.ResolvedStringSchema;
import com.symphony.oss.canon2.core.SourceContext;
import com.symphony.oss.canon2.runtime.java.Entity;


/**
 * Facade for Object  Schema canon
 * Object Schema
 * Generated from Schema
 */
@Immutable
public class OldSchema //extends OldSchemaEntity implements INamedModelEntity
{
  //private final ImmutableMap<String, ISchema> fields_;
  private final Schema          itemsSchema_;
  private final ReferenceObject itemsReference_;
  
  /**
   * Constructor.
   * 
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  protected OldSchema(Initialiser initialiser)
  {
    super(initialiser);

    Entity      items = null; 
    JsonDomNode itemsNode = getJson().get("items");
    
    if(itemsNode instanceof JsonObject)
    {
      JsonObject itemsObject = (JsonObject)itemsNode;
      
      if(itemsObject.get("$ref") == null)
        items = Schema.FACTORY.newInstance(itemsObject, initialiser.getModelRegistry());
      else
        items = ReferenceObject.FACTORY.newInstance(itemsObject, initialiser.getModelRegistry());
    }
    
    itemsSchema_    = items instanceof Schema ? (Schema)items : null;
    itemsReference_ = items instanceof ReferenceObject ? (ReferenceObject)items : null;
  }
  
  @Override
  public @Nullable String getXCanonIdentifier(String language)
  {
    return getJson().getString("x-canon-" + language + "-identifier", null);
  }
  
  //public void resolve(CanonModelContext generationContext, SchemaInfo schemaInfo)
  
  public void link(ResolvedOpenApiObject.SingletonBuilder openApiObjectBuilder, CanonModelContext modelContext, SourceContext sourceContext, 
      Consumer<ResolvedSchema.AbstractBuilder<?,?>> builderConsumer,
      String uri)
  {
    Set<SchemaOrRef> oneOf = getOneOf();
    Set<SchemaOrRef> anyOf = null; //getAnyOf();
    Set<SchemaOrRef> allOf = null; //getAllOf();
    String type = getType();
    
    int typeIndicatorCnt = 0;
    
    if(oneOf != null)
    {
      typeIndicatorCnt++;
      
      if(oneOf.isEmpty())
        throw new SyntaxErrorException("oneOf may not be empty", getJson().getContext());
      
      linkOneOf(oneOf, openApiObjectBuilder, modelContext, sourceContext, builderConsumer, uri);
    }
    
    if(anyOf != null)
    {
      typeIndicatorCnt++;
      
      sourceContext.error(new ParserErrorException("Unimplemented feature", getJson().getContext()));
    }
    
    if(allOf != null)
    {
      typeIndicatorCnt++;
      
      sourceContext.error(new ParserErrorException("Unimplemented feature", getJson().getContext()));
    }
    
    if(type != null)
    {
      typeIndicatorCnt++;
      
      switch(type)
      {
        case "object":
          linkObject(openApiObjectBuilder, modelContext, sourceContext, builderConsumer, uri);
          break;
          
        case "array":
          linkArray(openApiObjectBuilder, modelContext, sourceContext, builderConsumer, uri);
          break;
          
        case "boolean":
          builderConsumer.accept(new ResolvedBooleanSchema.SingletonBuilder());
          break;
          
        case "number":
          linkNumber(sourceContext, builderConsumer);
          break;
          
        case  "integer":
          linkInteger(sourceContext, builderConsumer);
          break;
          
        case "string":
          linkString(sourceContext, builderConsumer);
          break;
          
        default:
          sourceContext.error(new SyntaxErrorException("Unexpected type value \"" + type + "\"", getJson().getContext()));
      }
    }
    
    if(typeIndicatorCnt != 1)
    {
      sourceContext.error(new SyntaxErrorException("Exactly one of oneOf, anyOf, allOf and type must be specified.", getJson().getContext()));
    }
  }
  
  private void linkString(SourceContext sourceContext, Consumer<ResolvedSchema.AbstractBuilder<?,?>> builderConsumer)
  {
    ResolvedStringSchema.SingletonBuilder       builder = new ResolvedStringSchema.SingletonBuilder();

    builderConsumer.accept(builder);
    Set<String> x = getEnum();
//    builder
//      .withMinLength(getMinLength())
//      .withMaxLength(getMaxLength())
//      .withPattern(getPattern())
//      ;
  }

  private void linkInteger(SourceContext sourceContext, Consumer<ResolvedSchema.AbstractBuilder<?,?>> builderConsumer)
  {
    if(getFormat() == null)
    {
      linkNumber(new ResolvedBigIntegerSchema.SingletonBuilder(), builderConsumer);
    }
    else switch(getFormat())
    {
      case "int64":
        linkNumber(new ResolvedLongSchema.SingletonBuilder(), builderConsumer);
        break;
        
      case "int32":
        linkNumber(new ResolvedIntegerSchema.SingletonBuilder(), builderConsumer);
        break;
        
      case "float":
      case "double":
        sourceContext.error(new SyntaxErrorException("Invalid floating point format for integer type \"" + getFormat() + "\"", getJson().getContext()));
        linkNumber(new ResolvedBigIntegerSchema.SingletonBuilder(), builderConsumer);
        break;
        
      default:
        sourceContext.error(new ParserWarningException("Unknown number format \"" + getFormat() + "\" ignored.", getJson().getContext()));
        linkNumber(new ResolvedBigIntegerSchema.SingletonBuilder(), builderConsumer);
    }
  }

  private void linkNumber(SourceContext sourceContext, Consumer<ResolvedSchema.AbstractBuilder<?,?>> builderConsumer)
  {
    if(getFormat() == null)
    {
      linkNumber(new ResolvedBigDecimalSchema.SingletonBuilder(), builderConsumer);
    }
    else switch(getFormat())
    {
      case "int64":
        linkNumber(new ResolvedLongSchema.SingletonBuilder(), builderConsumer);
        break;
        
      case "int32":
        linkNumber(new ResolvedIntegerSchema.SingletonBuilder(), builderConsumer);
        break;
      
      case "float":
        linkNumber(new ResolvedFloatSchema.SingletonBuilder(), builderConsumer);
        break;
      
      case "double":
        linkNumber(new ResolvedDoubleSchema.SingletonBuilder(), builderConsumer);
        break;
      
      default:
        linkNumber(new ResolvedBigDecimalSchema.SingletonBuilder(), builderConsumer);
        sourceContext.error(new ParserWarningException("Unknown number format \"" + getFormat() + "\" ignored.", getJson().getContext()));
    }
  }
  
  private void linkNumber(ResolvedNumberSchema.AbstractBuilder<?,?,?> builder, Consumer<ResolvedSchema.AbstractBuilder<?,?>> builderConsumer)
  {
    builderConsumer.accept(builder);
    
    builder
        .withMinimum(getJson().get("minimum"))
        //.withExclusiveMinimum(getExclusiveMinimum())
        .withMaximum(getJson().get("maximum"))
        //.withExclusiveMaximum(getExclusiveMaximum())
        ;
  }

  private void linkArray(
      SingletonBuilder openApiObjectBuilder, CanonModelContext modelContext, SourceContext sourceContext, Consumer<ResolvedSchema.AbstractBuilder<?,?>> builderConsumer, String uri)
  {
    ResolvedArraySchema.SingletonBuilder       builder = new ResolvedArraySchema.SingletonBuilder();

    builderConsumer.accept(builder);
    // if(getA)

     if(itemsSchema_ != null)
     { 
       builder.withResolvedItems(
           modelContext.link(openApiObjectBuilder, sourceContext, "items BRUCE 001", uri + "/items", itemsSchema_, builder)
           //itemsSchema_.link(modelContext, sourceContext, uri + "/items", true)
           );
     }
     else if(itemsReference_ != null)
     {
//       Schema schema = fetchSchema(modelContext, sourceContext, itemsReference_, uri, "items", false);
       builder.withResolvedItems(fetchSchema(openApiObjectBuilder, modelContext, sourceContext, itemsReference_));
       
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

  private void linkObject(
      SingletonBuilder openApiObjectBuilder, CanonModelContext modelContext, SourceContext sourceContext, Consumer<ResolvedSchema.AbstractBuilder<?,?>> builderConsumer, String uri)
  {
    ResolvedObjectSchema.SingletonBuilder       builder = new ResolvedObjectSchema.SingletonBuilder();
    ResolvedPropertiesObject.SingletonBuilder   resolvedPropertiesBuilder = new ResolvedPropertiesObject.SingletonBuilder();
    ResolvedPropertiesObject.SingletonBuilder   innerClassesBuilder       = new ResolvedPropertiesObject.SingletonBuilder();
    
    builder
      .withResolvedProperties(resolvedPropertiesBuilder)
      .withInnerClasses(innerClassesBuilder)
      ;
    
    builderConsumer.accept(builder);
    
    PropertiesObject propertiesObject  = getProperties();
  
    if(propertiesObject != null)
    {
      for(Entry<String, Object> entry : propertiesObject.getProperties().entrySet())
      {
        if(entry.getValue() instanceof Schema)
        {
          Schema schema = (Schema)entry.getValue();
          
          ResolvedSchema.AbstractBuilder<?,?> resolvedProperty = modelContext.link(openApiObjectBuilder, sourceContext, entry.getKey(), uri + "/" + entry.getKey(), schema, builder);
          resolvedPropertiesBuilder.with(entry.getKey(), resolvedProperty);
          
//          switch(schema.getType())
//          {
//            case "object":
              innerClassesBuilder.with(entry.getKey(), resolvedProperty);
//              break;
//            default:
//              System.err.println("IGNORE INNER " + entry.getKey() + " " + entry.getValue());
//          }
        }
        else
        {
          ReferenceObject ref = (ReferenceObject)entry.getValue();
          
//          try
//          {
//                Schema schema = fetchSchema(modelContext, sourceContext, ref);
//                ResolvedSchema.SingletonBuilder resolvedProperty = modelContext.link(sourceContext, sourceContext.getSchemaUri(entry.getKey()), schema, false); // isGenerated was true here in the previous version, what does this actually mean?
            resolvedPropertiesBuilder.with(entry.getKey(), fetchSchema(openApiObjectBuilder, modelContext, sourceContext, ref));
//          }
//          catch(GenerationException e)
//          {
//            sourceContext.error("Invalid schema reference \"" + ref.get$ref() + "\" at " + getSourceLocation());
//          }
        }
      }
    }

    // TODO: Put back
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
        ResolvedSchema.AbstractBuilder<?, ?> superSchema = fetchSchema(openApiObjectBuilder, modelContext, sourceContext, getXCanonExtends());
        
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

  private void linkOneOf(Set<SchemaOrRef> oneOf, ResolvedOpenApiObject.SingletonBuilder openApiObjectBuilder, CanonModelContext modelContext, SourceContext sourceContext, Consumer<ResolvedSchema.AbstractBuilder<?,?>> builderConsumer, String uri)
  {
    
    ResolvedObjectSchema.SingletonBuilder       builder = new ResolvedObjectSchema.SingletonBuilder();
    ResolvedPropertiesObject.SingletonBuilder   resolvedPropertiesBuilder = new ResolvedPropertiesObject.SingletonBuilder();
    ResolvedPropertiesObject.SingletonBuilder   innerClassesBuilder       = new ResolvedPropertiesObject.SingletonBuilder();
    
    builderConsumer.accept(builder);
    
    builder
      .withResolvedProperties(resolvedPropertiesBuilder)
      .withInnerClasses(innerClassesBuilder)
      ;
    
    int i=1;
    for(SchemaOrRef subSchema : oneOf)
    {
      String name = subSchema.getRef() == null ? "$" + i : subSchema.getRef().getName();
      
      ResolvedSchema.AbstractBuilder<?,?> resolvedSubSchema = subSchema.link(openApiObjectBuilder, modelContext, sourceContext, name, uri, builder);
      resolvedPropertiesBuilder.with(name, resolvedSubSchema);
      
      if(subSchema.getSchema() != null && resolvedSubSchema.build().getSchemaType().getIsObject())
        innerClassesBuilder.with(name, resolvedSubSchema);
      i++;
    }
  }

  public String getSourceLocation()
  {
    IParserContext context = getJson().getContext();
    
    if(context == null)
      return "unknown location";
    
    return context.toString();
  }

  // Duplicated in SchemaOrRef - that's the correct place for this
  private ResolvedSchema.AbstractBuilder<?,?> fetchSchema(ResolvedOpenApiObject.SingletonBuilder openApiObjectBuilder, CanonModelContext modelContext, SourceContext sourceContext, ReferenceObject ref) throws ParserException
  {
    Schema schema;
    String uri;
    
    if(ref.getBaseUri() == null)
    {
      try
      {
        schema = sourceContext.getModel().get(ref.getFragment(), Schema.class);
      }
      catch(IllegalArgumentException e)
      {
        throw new ParserErrorException("No such schema \"" + ref.getFragment() + "\"", ref);
      }
      uri = sourceContext.getUrl() + "#" + ref.getFragment();
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
        throw new ParserErrorException("Invalid URL", ref, e);
      }
    }
    
    return modelContext.link(openApiObjectBuilder, sourceContext, ref.getName(), uri, schema, null);
  }

//  @Override
//  public Set<?> getEnumValues(String type)
//  {
//      IImmutableJsonDomNode enumNode = getJson().get("enum");
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
      
      if(itemsReference_ != null)
      {
        try
        {
          generationContext.addReferencedModel(itemsReference_.getAbsoluteBaseUrl(sourceContext.getUrl()));
        }
        catch(MalformedURLException e)
        {
          throw new ParserErrorException("Invalid URL", itemsReference_, e);
        }
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