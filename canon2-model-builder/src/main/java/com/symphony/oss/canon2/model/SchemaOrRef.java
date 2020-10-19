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
    
    if(getJsonObject().get("$ref") == null)
    {
      schema_ = Schema.FACTORY.newInstance(getJsonObject(), initialiser.getModelRegistry());
      ref_ = null;
    }
    else
    {
      schema_ = null;
      ref_ = ReferenceObject.FACTORY.newInstance(getJsonObject(), initialiser.getModelRegistry());
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
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */