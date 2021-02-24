/*
 *
 *
 * Copyright 2019-2020 Symphony Communication Services, LLC.
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

package com.symphony.oss.canon.runtime;

import com.symphony.oss.canon.runtime.http.ICorsHandler;

/**
 * A container for request handlers.
 * 
 * Existing implementations are ModelServlet and AwsLambdaHandlerContainer.
 * 
 * @author Bruce Skingle
 *
 */
public interface IHandlerContainer
{
  /**
   * Add the given method handler.
   * 
   * @param handler A method handler.
   * 
   * @return This (fluent method).
   */
  IHandlerContainer withHandler(IEntityHandler handler);

  /**
   * Set the CORS handler.
   * 
   * @param corsHandler A CORS Handler.
   * 
   * @return This (fluent method).
   */
  IHandlerContainer withCorsHandler(ICorsHandler corsHandler);
}
