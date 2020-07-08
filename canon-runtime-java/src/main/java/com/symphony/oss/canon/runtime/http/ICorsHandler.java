/*
 *
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
 */

package com.symphony.oss.canon.runtime.http;

/**
 * A handler for CORS requests.
 * 
 * @author Bruce Skingle
 *
 */
@FunctionalInterface
public interface ICorsHandler
{
  /**
   * Handle the given request context.
   * 
   * If the request is a CORS Options request then deal with it and return true, otherwise make no changes to the context and
   * return false. If this method returns true then the caller should take no further action with thw request.
   * 
   * @param context A request context.
   * 
   * @return True if the request is a CORS Option request and it has been handled.
   */
  boolean handle(IRequestContext context);
}
