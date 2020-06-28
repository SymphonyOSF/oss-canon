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

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;

import com.symphony.oss.canon.runtime.exception.BadRequestException;
import com.symphony.oss.canon.runtime.exception.DeletedException;
import com.symphony.oss.canon.runtime.exception.NotFoundException;
import com.symphony.oss.canon.runtime.exception.PermissionDeniedException;
import com.symphony.oss.canon.runtime.exception.ServerErrorException;
import com.symphony.oss.commons.immutable.ImmutableByteArray;

public class HttpRequestOrBuilder<MC extends HttpModelClient>
{
  private final MC canonClient_;

  public HttpRequestOrBuilder(MC canonClient)
  {
    canonClient_ = canonClient;
  }

  public MC getCanonClient()
  {
    return canonClient_;
  }
  
  public String asString(ImmutableByteArray byteString)
  {
    return byteString.toBase64UrlSafeString();
  }
  
  public String asString(Number number)
  {
    return number.toString();
  }
  
  public String asString(Boolean v)
  {
    return v.toString();
  }
  
  public String asString(String s)
  {
    return s;
  }
  
  private CloseableHttpResponse makeRequestOnce(CloseableHttpClient httpClient, HttpUriRequest canonRequest) throws IOException
  {
    while(true)
    {
      Map<Integer, IResponseHandlerContext> map = canonClient_.prepareResponse();
      CloseableHttpResponse response = httpClient.execute(canonRequest);
      
      switch(canonClient_.handleResponse(response, map))
      {
        case RETRY:
          continue;
          
        case CONTINUE:
          return response;
      }
    }
  }
  
  public CloseableHttpResponse makeRequest(CloseableHttpClient httpClient, HttpUriRequest canonRequest) throws IOException
  {
    CloseableHttpResponse response    = makeRequestOnce(httpClient, canonRequest);
    int                   statusCode  = response.getStatusLine().getStatusCode();
    
    if(statusCode == HttpStatus.SC_FORBIDDEN)
      throw new PermissionDeniedException(response);

    if(statusCode == HttpStatus.SC_NOT_FOUND)
      throw new NotFoundException(response);
    
    if(statusCode == HttpStatus.SC_GONE)
      throw new DeletedException(response);
    
    if(statusCode < 200 || statusCode > 599)
      throw new ServerErrorException(statusCode, "Unexpected HTTP response", response);
    
    if(statusCode >= 300 && statusCode <= 399)
      throw new ServerErrorException(statusCode, "Unexpected HTTP response", response);
    
    if(statusCode >= 500)
      throw new ServerErrorException(statusCode, response);
    
    if(statusCode >= 400)
      throw new BadRequestException(response);
    
    return response;
  }
}
