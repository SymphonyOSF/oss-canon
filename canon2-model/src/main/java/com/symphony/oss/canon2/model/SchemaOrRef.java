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

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon2.core.CanonModelContext;
import com.symphony.oss.canon2.core.ResolvedObjectOrArraySchema;
import com.symphony.oss.canon2.core.ResolvedOpenApiObject;
import com.symphony.oss.canon2.core.ResolvedSchema;
import com.symphony.oss.canon2.core.SourceContext;


/**
 * Facade for Object  SchemaOrRef canon
 * Object SchemaOrRef
 * Generated from SchemaOrRef
 */
@Immutable
public class SchemaOrRef extends SchemaOrRef_Entity
{
  /**
   * Constructor.
   * 
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  protected SchemaOrRef(Initialiser initialiser)
  {
    super(initialiser);
  }

  @Deprecated
  public ReferenceObject getRef()
  {
    return getReferenceObject();
  }

  public boolean isReference()
  {
    return getReferenceObject() != null;
  }

  public ResolvedSchema.AbstractBuilder<? extends ISchemaInstance,?,?> link(ResolvedOpenApiObject.SingletonBuilder openApiObjectBuilder, CanonModelContext modelContext, SourceContext sourceContext,
      String name, String parentUri, ResolvedObjectOrArraySchema.AbstractBuilder<?,?,?> outerClassBuilder)
  {
    if(getSchema() != null)
    {
      return modelContext.link(openApiObjectBuilder, sourceContext, name, parentUri + "/" + name, getSchema(), outerClassBuilder);
    }
    else
    {
      return fetchSchema(openApiObjectBuilder, modelContext, sourceContext, getReferenceObject());
    }
  }

  // Duplicated in Schema - THIS is the correct place for this
  static ResolvedSchema.AbstractBuilder<? extends ISchemaInstance,?,?> fetchSchema(ResolvedOpenApiObject.SingletonBuilder openApiObjectBuilder, CanonModelContext modelContext, SourceContext sourceContext, ReferenceObject ref)
  {
    return ref.fetchSchema(openApiObjectBuilder, modelContext, sourceContext);
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */