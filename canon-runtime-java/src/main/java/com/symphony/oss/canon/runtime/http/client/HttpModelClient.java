/*
 *
 *
 * Copyright 2018 Symphony Communication Services, LLC.
 *
 * Licensed to The Symphony Software Foundation (SSF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
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

package com.symphony.oss.canon.runtime.http.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.client.methods.CloseableHttpResponse;

import com.symphony.oss.canon.runtime.IModelRegistry;

public class HttpModelClient
{
  private final IModelRegistry          registry_;
  private final String                  uri_;
  private final IAuthenticationProvider auth_;
  private final Map<Integer, IResponseHandler> handlerMap_;
  
  public HttpModelClient(IModelRegistry registry, String baseUri, String basePath, IAuthenticationProvider auth, Map<Integer, IResponseHandler> handlerMap)
  {
    if(baseUri.endsWith("/"))
      throw new IllegalArgumentException("baseUri must not end with a slash");

    if(basePath.length() > 0 && !basePath.startsWith("/"))
      throw new IllegalArgumentException("basePath must start with a slash");
    
    registry_ = registry;
    uri_ = baseUri + basePath;
    auth_ = auth;
    handlerMap_ = handlerMap;
  }

  public String getUri()
  {
    return uri_;
  }

  public IModelRegistry getRegistry()
  {
    return registry_;
  }
  
  public IAuthenticationProvider getAuthenticationProvider()
  {
    return auth_;
  }
  
  public Map<Integer, IResponseHandlerContext> prepareResponse()
  {
    if(handlerMap_ != null)
    {
      Map<Integer, IResponseHandlerContext> map = new HashMap<>();
      
      for(Entry<Integer, IResponseHandler> entry : handlerMap_.entrySet())
      {
        map.put(entry.getKey(), entry.getValue().prepare());
      }
      return map;
    }
    return null;
  }

  public ResponseHandlerAction handleResponse(CloseableHttpResponse response, Map<Integer, IResponseHandlerContext> contextMap)
  {
    if(contextMap != null)
    {
      IResponseHandlerContext handler = contextMap.get(response.getStatusLine().getStatusCode());
      
      if(handler != null)
      {
        return handler.handle(response);
      }
    }
    return ResponseHandlerAction.CONTINUE;
    
    
  }
}
