/*
 *
 *
 * Copyright 2017-2019 Symphony Communication Services, LLC.
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

package com.symphony.oss.canon.runtime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.symphony.oss.canon.runtime.http.HttpMethod;
import com.symphony.oss.canon.runtime.http.ICorsHandler;
import com.symphony.oss.canon.runtime.http.ServletRequestContext;
import com.symphony.oss.fugue.trace.ITraceContext;
import com.symphony.oss.fugue.trace.ITraceContextTransaction;
import com.symphony.oss.fugue.trace.ITraceContextTransactionFactory;

/**
 * A Servlet implementation of IAsyncHandlerContainer.
 * 
 * @author Bruce Skingle
 *
 */
public class ModelServlet extends HttpServlet implements IModelServlet
{
  private static final long                            serialVersionUID = 1L;

  private final ITraceContextTransactionFactory        traceFactory_;
  private final IModelRegistry                         modelRegistry_;
  private final TreeMap<Integer, List<IAbstractEntityHandler>> handlerMap_   = new TreeMap<>(new Comparator<Integer>()
      {
        /*
         * We want the map in descending order.
         */
        @Override
        public int compare(Integer a, Integer b)
        {
          if(a>b)
            return -1;
          
          if(a<b)
            return 1;
          
          return 0;
        }});

  private ICorsHandler corsHandler_;

  /**
   * Constructor.
   * 
   * @param traceFactory    Trace context factory.
   * @param modelRegistry   Model Registry.
   */
  public ModelServlet(ITraceContextTransactionFactory traceFactory, IModelRegistry modelRegistry)
  {
    traceFactory_ = traceFactory;
    modelRegistry_ = modelRegistry;
  }

  @Override
  public String getUrlPath()
  {
    return "/*";
  }
  
  @Override
  public ModelServlet withCorsHandler(ICorsHandler corsHandler)
  {
    corsHandler_ = corsHandler;
    
    return this;
  }
  
  @Override
  public ModelServlet withHandler(IEntityHandler handler)
  {
    return addHandler(handler);
  }
  
  @Override
  public ModelServlet withHandler(IAsyncEntityHandler handler)
  {
    return addHandler(handler);
  }
  
  private ModelServlet addHandler(IAbstractEntityHandler handler)
  {
    List<IAbstractEntityHandler> list = handlerMap_.get(handler.getPartsLength());
    
    if(list == null)
    {
      list = new ArrayList<>();
      handlerMap_.put(handler.getPartsLength(), list);
    }
    
    list.add(handler);
    
    return this;
  }
  
  private void handle(HttpMethod method, HttpServletRequest req, HttpServletResponse resp) throws IOException
  {
    try(ITraceContextTransaction traceTransaction = traceFactory_.createTransaction("HTTP " + method, UUID.randomUUID().toString()))
    {
      ITraceContext trace = traceTransaction.open();

      //doCorsHeaders(req, resp);
      
      ServletRequestContext context = new ServletRequestContext(method, trace, modelRegistry_, req, resp);
      
      if(corsHandler_ != null)
        corsHandler_.handle(context);
      
      for(List<IAbstractEntityHandler> list : handlerMap_.values())
      {
        for(IAbstractEntityHandler handler : list)
        {
          if(handle(handler, context))
          {
            traceTransaction.finished();
            return;
          }
        }
      }
      
      context.error("No handler found for " + context.getPathInfo());
      context.sendErrorResponse(HttpServletResponse.SC_NOT_FOUND);
      traceTransaction.aborted();
    }
  }

  private boolean handle(IAbstractEntityHandler handler, ServletRequestContext context) throws IOException
  {
    if(handler instanceof IEntityHandler)
      return ((IEntityHandler)handler).handle(context);
    
    return ((IAsyncEntityHandler)handler).handle(context);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
  {
    handle(HttpMethod.Get, req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
  {
    handle(HttpMethod.Post, req, resp);
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException
  {
    handle(HttpMethod.Put, req, resp);
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException
  {
    handle(HttpMethod.Delete, req, resp);
  }

  @Override
  protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    //if(doCorsHeaders(req, resp))
    if(corsHandler_ != null)
    {
      try(ITraceContextTransaction traceTransaction = traceFactory_.createTransaction("HTTP " + HttpMethod.Options, UUID.randomUUID().toString()))
      {
        ITraceContext trace = traceTransaction.open();

        ServletRequestContext context = new ServletRequestContext(HttpMethod.Options, trace, modelRegistry_, req, resp);
       
        if(corsHandler_.handle(context))
        {
          resp.setStatus(HttpServletResponse.SC_OK);
          
          return;
        }
      }
    }
    
    super.doOptions(req, resp);
  }

//  private boolean doCorsHeaders(HttpServletRequest req, HttpServletResponse resp)
//  {
//    String origin = req.getHeader("Origin");
//    
//    if(origin != null)
//    {
//      int i = origin.lastIndexOf(':');
//      
//      String s = i>0 ? origin.substring(0, i) : origin;
//      
//      if(s.endsWith(".symphony.com"))
//      {
////        resp.setHeader("Access-Control-Allow-Origin", origin);
////        resp.setHeader("Access-Control-Allow-Headers", "*");
////        resp.setHeader("Access-Control-Allow-Credentials", "true");
//        
//        resp.setHeader("access-control-allow-origin", origin);
//        resp.setHeader("access-control-allow-headers", "*");
//        resp.setHeader("access-control-allow-credentials", "true");
//        
//        
//        resp.setHeader("x-amzn-RequestId", "660a4048-f331-4adb-af68-f3bd9ba3cd4b");
//        resp.setHeader("Content-Type", "application/json");
//        resp.setHeader("Connection", "keep-alive");
//        resp.setHeader("x-amz-apigw-id", "PB8HwFPVIAMFbgA=");
//        resp.setHeader("X-Amzn-Trace-Id", "Root=1-5efd73cb-f216c1be26c170cd94c183e5;Sampled=0");
//        
//        return true;
//      }
//    }
//    return false;
//  }
}
