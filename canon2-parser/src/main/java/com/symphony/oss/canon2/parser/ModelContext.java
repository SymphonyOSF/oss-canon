/*
 *
 *
 * Copyright 2017 Symphony Communication Services, LLC.
 *
 * Licensed to The Symphony Software Foundation (SSF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The SSF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.symphony.oss.canon2.parser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.symphony.oss.canon2.model.GenerationException;
import com.symphony.oss.canon2.model.IModelContext;
import com.symphony.oss.canon2.model.OpenApiObject;
import com.symphony.oss.canon2.model.ResolvedModel;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;

class ModelContext implements IModelContext
{
  private static Logger log_ = LoggerFactory.getLogger(ModelContext.class);


  private final URL                   url_;
  private final CanonContext     generationContext_;
  private OpenApiObject        model_;
  private ResolvedModel        resolvedModel_;
  private final Map<String, String>   uriMap_;
  
  private final String          inputSource_;
  private final String          inputSourceFileName_;
  private final String          inputSourceName_;
  private final boolean         referencedModel_;
  private Reader                reader_;
  private List<String>          errors_ = new LinkedList<>();
  
  

  public ModelContext(CanonContext modelSetParserContext, URL url, boolean referencedModel, Map<String, String> uriMap) throws GenerationException
  {
    this(modelSetParserContext, url, openStream(url), referencedModel, uriMap);
  }
  
  private static Reader openStream(URL url) throws GenerationException
  {
    try
    {
      return new InputStreamReader(url.openStream());
    }
    catch(IOException e)
    {
      throw new GenerationException(e);
    }
  }

  public ModelContext(CanonContext generationContext, URL baseUrl, Reader reader, boolean referencedModel, Map<String, String> uriMap)
  {
    generationContext_ = generationContext;
    url_ = baseUrl;
    reader_ = reader;
    inputSource_ = url_.toString();
    referencedModel_ = referencedModel;
    uriMap_ = uriMap;
    
    String path = url_.getPath();
    
    if(path == null || path.length()==0 || "/".equals(path))
    {
      inputSourceFileName_ = inputSourceName_ = url_.getHost();
    }
    else
    {
      int i = path.lastIndexOf('/');
      
      if(i != -1)
        path = path.substring(i+1);
      
      inputSourceFileName_ = path;
      inputSourceName_ = trim(path);
    }
  }
  
  private String trim(String path)
  {
    if(path.length() > 5)
    {
      int l = path.length()-5;
      
      if(".json".equalsIgnoreCase(path.substring(l)))
        return path.substring(0, l);
    }
    
    if(path.length() > 3)
    {
      int l = path.length()-3;
      
      if(".js".equalsIgnoreCase(path.substring(l)))
        return path.substring(0, l);
    }
    return path;
  }

//  public ModelContext(File inputFile, Reader inputStream, boolean referencedModel, Map<String, String> uriMap)
//  {
//    reader_ = inputStream;
//    inputSource_ = inputFile.getAbsolutePath();
//    inputSourceName_ = inputFile.getName();
//    referencedModel_ = referencedModel;
//    uriMap_ = uriMap;
//    
//    try
//    {
//      url_ = inputFile.toURI().toURL();
//    }
//    catch (MalformedURLException e)
//    {
//      throw new CodingFault(e);
//    }
//  }
  
  OpenApiObject parse(ModelRegistry modelRegistry)
  {
    log_.info("Parsing {}...", getInputSource());
    
    model_ = modelRegistry.parseOne(getReader(), OpenApiObject.TYPE_ID, OpenApiObject.class);
    
//    if(errorCnt_ == 0)
      log_.info("Parsing of {} completed OK.", getInputSource());
//    else
//      log_.errorf("%s of %s completed with %d errors.", action, getInputSource(), errorCnt_);
    
    System.out.println(model_);
    
    return model_;
  }
  
  @Override
  public OpenApiObject getModel()
  {
    return model_;
  }

  ResolvedModel getResolvedModel()
  {
    return resolvedModel_;
  }

  void setResolvedModel(ResolvedModel resolvedModel)
  {
    resolvedModel_ = resolvedModel;
  }

  /**
   * @return True iff this is a referenced model, and generation should not be performed.
   */
  public boolean isReferencedModel()
  {
    return referencedModel_;
  }

  @Override
  public URL getUrl()
  {
    return url_;
  }

  public Reader getReader()
  {
    return reader_;
  }

  @Override
  public String getInputSource()
  {
    return inputSource_;
  }
  
  @Override
  public String getInputSourceName()
  {
    return inputSourceName_;
  }
  
  @Override
  public String getInputSourceFileName()
  {
    return inputSourceFileName_;
  }

  public void addReferencedModel(URI uri) throws GenerationException
  {
    try
    {
      URL url = getReferencedUrl(uri);
        
      generationContext_.addReferencedModel(url);
    }
    catch (IOException | URISyntaxException e)
    {
      throw new GenerationException("Invalid URI \"" + uri + "\"", e);
    }
  }
  
  private URL getReferencedUrl(URI uri) throws URISyntaxException, MalformedURLException
  {
    String uriString = uri.toString();
    
    for(Entry<String, String> entry : uriMap_.entrySet())
    {
      if(uriString.startsWith(entry.getKey()))
      {
        uri = new URI(entry.getValue() + uriString.substring(entry.getKey().length()));
        break;
      }
    }
    
    return uri.isAbsolute()
      ? uri.toURL()
      : new URL(url_, uri.toString());
  }

  public OpenApiObject getModel(URI uri)
  {
    
    try
    {
      URL url = getReferencedUrl(uri);
        
      return generationContext_.getModel(url);
    }
    catch (MalformedURLException | URISyntaxException e)
    {
      throw new IllegalStateException(e);
    }
  }
  
  public boolean  printErrors()
  {
    if(errors_.isEmpty())
      return false;
    
    log_.error("=============================================================================================================================");
    log_.error(errors_.size() + " errors encountered:");
    for(String error : errors_)
      log_.error(error);
    log_.error("=============================================================================================================================");
    
    return true;
  }

  @Override
  public void error(String error)
  {
    log_.error(error);
    errors_.add(error);
  }
}
