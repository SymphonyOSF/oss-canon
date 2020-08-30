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
 *  At                  2020-08-26 08:34:03 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.parser;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.canon2.parser.model.ReferenceObjectEntity;
import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;

/**
 * Facade for Object ObjectSchema(ReferenceObject)
 *
 * A reference.
 * Generated from ObjectSchema(ReferenceObject) at #/components/schemas/ReferenceObject
 */
@Immutable
public class ReferenceObject extends ReferenceObjectEntity implements IReferenceObject
{

  class UriParts
  {
    private final URI            uri_;
    private final String         path_;
    private final String         fragment_;
    private final URL            baseUrl_;
    
    public UriParts(URI uri, String path, String fragment, URL baseUrl)
    {
      uri_ = uri;
      path_ = path;
      fragment_ = fragment;
      baseUrl_ = baseUrl;
    }
  }
  
  private final UriParts uriParts_;
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public ReferenceObject(AbstractReferenceObjectBuilder<?,?> builder)
  {
    super(builder);
    
    uriParts_ = initUriParts();
  }
  
  private UriParts initUriParts()
  {
    String text = get$ref();
    
    try
    {
      if(text == null || text.length()==0)
      {
        throw new IllegalStateException("Empty URI");
      }
      
      URI uri = new URI(text);
      String s = uri.toString();
      int i = s.indexOf('#');
      
      if(i== -1)
      {
        return new UriParts(uri, uri.getPath(), uri.getFragment(), uri.toURL());
      }
      else if(i == 0)
      {
        // starts with # - local fragment
        return new UriParts(uri, null, uri.getFragment(), null);
      }
      else
      {
        try
        {
          return new UriParts(uri, uri.getPath(), uri.getFragment(), new URL(s.substring(0, i)));
        }
        catch (MalformedURLException e)
        {
          throw new IllegalStateException("Invalid base URL \"%s\"" + s.substring(0, i), e);
        }
      }
    }
    catch (URISyntaxException | MalformedURLException e)
    {
      throw new IllegalStateException("Invalid base URI \"%s\"" + text, e);
    }
  }

  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public ReferenceObject(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    
    uriParts_ = initUriParts();
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public ReferenceObject(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
    
    uriParts_ = initUriParts();
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public ReferenceObject(IReferenceObject other)
  {
    super(other);

    
    uriParts_ = other.getUriParts();
  }

  @Override
  public UriParts getUriParts()
  {
    return uriParts_;
  }
  
  @Override
  public URL getBaseUrl()
  {
    return uriParts_.baseUrl_;
  }

  @Override
  public String getFragment()
  {
    return uriParts_.fragment_;
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */