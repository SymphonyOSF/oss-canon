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

package com.symphony.oss.canon2.core;

import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon2.model.ISchemaInstance;

public abstract class ResolvedSchema<S extends ISchemaInstance> extends ResolvedEntity
{
  private final String                                             uri_;
  private final S                                                  schema_;
  private final ResolvedOpenApiObject.SingletonBuilder             openApiObjectBuilder_;
  private final ResolvedObjectOrArraySchema.AbstractBuilder<?,?,?> outerClassBuilder_;
  private final boolean                                            canonPrefix_;
  
  ResolvedSchema(AbstractBuilder<S,?,?> builder)
  {
    super(builder);
    
    uri_                                  = builder.uri_;
    schema_                               = builder.schema_;
    openApiObjectBuilder_                 = builder.openApiObjectBuilder_;
    outerClassBuilder_                    = builder.outerClassBuilder_;
    canonPrefix_                          = builder.canonPrefix_;
  }
  
  public abstract static class AbstractBuilder<S extends ISchemaInstance, T extends AbstractBuilder<S,T,B>, B extends ResolvedSchema<S>> extends ResolvedEntity.AbstractBuilder<T,B>
  {
    private String                                            uri_;
    private S                                                 schema_;
    private ResolvedOpenApiObject.SingletonBuilder            openApiObjectBuilder_;
    private ResolvedObjectOrArraySchema.AbstractBuilder<?, ?, ?> outerClassBuilder_;
    private boolean                                              canonPrefix_;
    
    AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    public T withUri(String uri)
    {
      if(isBuilt())
        throw new IllegalStateException("SingletonBuilder has already been built");
      
      uri_ = uri;
      
      return self();
    }
    
    public T withResolvedOpenApiObject(ResolvedOpenApiObject.SingletonBuilder openApiObjectBuilder)
    {
      if(isBuilt())
        throw new IllegalStateException("SingletonBuilder has already been built");
      
      openApiObjectBuilder_ = openApiObjectBuilder;
      
      return self();
    }
    
    public T withResolvedContainer(ResolvedObjectOrArraySchema.AbstractBuilder<?,?,?> outerClassBuilder)
    {
      if(isBuilt())
        throw new IllegalStateException("SingletonBuilder has already been built");
      
      outerClassBuilder_ = outerClassBuilder;
      
      return self();
    }
    
    public T withCanonPrefix(boolean canonPrefix)
    {
      if(isBuilt())
        throw new IllegalStateException("SingletonBuilder has already been built");
      
      canonPrefix_ = canonPrefix;
      
      return self();
    }
    
//    public T withSubSchemas(com.symphony.oss.canon2.core.ResolvedSubSchemas.SingletonBuilder subSchemasBuilder)
//    {
//      if(isBuilt())
//        throw new IllegalStateException("SingletonBuilder has already been built");
//      
//      subSchemasBuilder_ = subSchemasBuilder;
//      
//      return self();
//    }
    
//    public ResolvedSchema build()
//    {
//      if(built_ == null)
//      {
//        if(resolvedPropertiesBuilder_ == null)
//          throw new IllegalStateException("resolvedPropertiesBuilder is required");
//        
//        built_ = new ResolvedSchema(this);
//      }
//      
//      return built_;
//    }

    public T withSchema(S schema)
    {
      schema_ = schema;
      
      return self();
    }
  }

  @Override
  public JsonDomNode getJson()
  {
    return schema_.getJson();
  }

  public String getUri()
  {
    return uri_;
  }

  public S getSchema()
  {
    return schema_;
  }
  
  public boolean isInnerClass()
  {
    return outerClassBuilder_ != null;
  }

//  public ResolvedSubSchemas getResolvedSubSchemas()
//  {
//    if(subSchemasBuilder_ == null)
//      return null;
//  
//    return subSchemasBuilder_.build();
//  }

  @Override
  public ResolvedOpenApiObject getResolvedOpenApiObject()
  {
    if(openApiObjectBuilder_ == null)
      return null;
    
    return openApiObjectBuilder_.build();
  }

  public ResolvedSchema<?> getResolvedContainer()
  {
    if(outerClassBuilder_ == null)
      return null;
    
    return outerClassBuilder_.build();
  }
  
  public SchemaTemplateModelType getSchemaType()
  {
    return getSchema().getSchemaType();
  }

  public boolean isCanonPrefix()
  {
    return canonPrefix_;
  }
}
