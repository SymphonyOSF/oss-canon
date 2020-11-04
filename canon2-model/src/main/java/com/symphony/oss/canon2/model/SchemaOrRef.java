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
 *    At                   2020-10-19 14:43:21 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.model;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.json.IParserContext;
import com.symphony.oss.canon.json.ParserErrorException;
import com.symphony.oss.canon2.core.CanonModelContext;
import com.symphony.oss.canon2.core.ResolvedObjectSchema;
import com.symphony.oss.canon2.core.ResolvedOpenApiObject;
import com.symphony.oss.canon2.core.ResolvedSchema;
import com.symphony.oss.canon2.core.SourceContext;


/**
 * Facade for Object  SchemaOrRef canon
 * Object SchemaOrRef
 * Generated from SchemaOrRef
 */
@Immutable
public class SchemaOrRef extends SchemaOrRefEntity
{
  private final Schema schema_;
  private final ReferenceObject ref_;

  /**
   * Constructor.
   * 
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  protected SchemaOrRef(Initialiser initialiser)
  {
    super(initialiser);
    
    if(getJson().get("$ref") == null)
    {
      schema_ = Schema.FACTORY.newInstance(getJson(), initialiser.getModelRegistry());
      ref_ = null;
    }
    else
    {
      schema_ = null;
      ref_ = ReferenceObject.FACTORY.newInstance(getJson(), initialiser.getModelRegistry());
    }
  }

  public Schema getSchema()
  {
    return schema_;
  }

  public ReferenceObject getRef()
  {
    return ref_;
  }

  public boolean isReference()
  {
    return ref_ != null;
  }

  public ResolvedSchema.AbstractBuilder<?,?> link(ResolvedOpenApiObject.SingletonBuilder openApiObjectBuilder, CanonModelContext modelContext, SourceContext sourceContext,
      String name, String parentUri, ResolvedObjectSchema.SingletonBuilder outerClassBuilder)
  {
    if(schema_ != null)
    {
      return modelContext.link(openApiObjectBuilder, sourceContext, name, parentUri + "/" + name, schema_, isGenerated(schema_.getType()), outerClassBuilder);
    }
    else
    {
      return fetchSchema(openApiObjectBuilder, modelContext, sourceContext, ref_, true);
    }
  }
  


//  private String getSourceLocation()
//  {
//    IParserContext context = getJsonDomNode().getContext();
//    
//    if(context == null)
//      return "unknown location";
//    
//    return context.toString();
//  }

  // Duplicated in Schema - THIS is the correct place for this
  private ResolvedSchema.AbstractBuilder<?,?> fetchSchema(ResolvedOpenApiObject.SingletonBuilder openApiObjectBuilder, CanonModelContext modelContext, SourceContext sourceContext, ReferenceObject ref,
      boolean generated)
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
        throw new ParserErrorException("Invalid URL", ref, e);
      }
    }
    
    return modelContext.link(openApiObjectBuilder, sourceContext, ref.getName(), uri, schema, generated, null);
  }
  
  private boolean isGenerated(String type)
  {
    // TODO: replace with type enum
    
    if("array".equals(type))
      System.err.println("HERE");
    
    return "object".equals(type); // || "array".equals(type);
    
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */