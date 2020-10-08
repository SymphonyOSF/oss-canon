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
import java.net.URISyntaxException;
import java.net.URL;

import javax.annotation.concurrent.Immutable;


/**
 * Facade for Object  ReferenceObject canon
 * Object ReferenceObject
 * Generated from ReferenceObject
 */
@Immutable
public class ReferenceObject extends ReferenceObjectEntity
{
  class UriParts
  {
    private final URI            uri_;
    private final String         path_;
    private final String         fragment_;
    private final URI            baseUri_;
    private final String         name_;
    
    public UriParts(URI uri, String path, String fragment, URI baseUri)
    {
      uri_ = uri;
      path_ = path;
      fragment_ = fragment;
      baseUri_ = baseUri;
      
      int i = fragment_.lastIndexOf('/');
      
      if(i == -1)
        name_ = fragment_;
      else
        name_ = fragment_.substring(i+1);
    }
  }
  
  private final UriParts uriParts_;
  
  /**
   * Constructor.
   * 
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  protected ReferenceObject(Initialiser initialiser)
  {
    super(initialiser);
    
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
        uriParts_ = new UriParts(uri, uri.getPath(), uri.getFragment(), uri);
      }
      else if(i == 0)
      {
        // starts with # - local fragment
        uriParts_ = new UriParts(uri, null, uri.getFragment(), null);
      }
      else
      {
//        try
//        {
        uriParts_ = new UriParts(uri, uri.getPath(), uri.getFragment(), new URI(s.substring(0, i)));
//        }
//        catch (MalformedURLException e)
//        {
//          throw new IllegalStateException("Invalid base URL \"%s\"" + s.substring(0, i), e);
//        }
      }
    }
    catch (URISyntaxException e)
    {
      throw new IllegalStateException("Invalid base URI \"%s\"" + text, e);
    }
  }
  

  public interface IInstanceOrBuilder extends ReferenceObjectEntity.IInstanceOrBuilder
  {
  }
  
  /**
   * Abstract builder for ReferenceObject. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends ReferenceObject>
    extends ReferenceObjectEntity.AbstractBuilder<T,B>
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

  public UriParts getUriParts()
  {
    return uriParts_;
  }
  
  public URI getBaseUri()
  {
    return uriParts_.baseUri_;
  }

  public String getFragment()
  {
    return uriParts_.fragment_;
  }

  public String getName()
  {
    return uriParts_.name_;
  }

  public String getAbsoluteUri(URL url)
  {
    if(uriParts_.baseUri_ == null)
      return url + "#" + uriParts_.fragment_;
    else
      return get$ref();
  }

  public URL getAbsoluteBaseUrl(URL context) throws MalformedURLException
  {
    if(getBaseUri() == null)
      return context;
    
    return new URL(context, getBaseUri().toString());
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */