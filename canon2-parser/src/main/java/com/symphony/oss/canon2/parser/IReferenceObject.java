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
 *		Template name		   proforma/java/Object/I_.java.ftl
 *		Template version	   1.0
 *  At                  2020-08-26 08:34:03 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.parser;

import java.net.URL;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon2.parser.ReferenceObject.UriParts;
import com.symphony.oss.canon2.parser.model.IReferenceObjectEntity;

/**
 * Facade for Object ObjectSchema(ReferenceObject)
 *
 * A reference.
 * Generated from ObjectSchema(ReferenceObject) at #/components/schemas/ReferenceObject
 */
@Immutable
public interface IReferenceObject
  extends IReferenceObjectEntity
{

  UriParts getUriParts();

  URL getBaseUrl();

  String getFragment();
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/I_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */