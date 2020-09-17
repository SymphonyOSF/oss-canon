/*
 *
 *
 * Copyright 2020 Symphony Communication Services, LLC.
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

package com.symphony.oss.canon2.model;

/**
 * An exception encountered while generating.
 * 
 * @author Bruce Skingle
 *
 */
public class GenerationException extends Exception
{
  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   * 
   * @param message
   *        The detail message (which is saved for later retrieval
   *        by the {@link #getMessage()} method)
   */
  public GenerationException(String message)
  {
    super(message);
  }

  /**
   * Constructor.
   * 
   * @param cause
   *        The cause (which is saved for later retrieval by the
   *        {@link #getCause()} method).  (A null value is permitted,
   *        and indicates that the cause is nonexistent or unknown.)
   */
  public GenerationException(Throwable cause)
  {
    super(cause);
  }

  /**
   * Constructor.
   * 
   * @param message
   *        The detail message (which is saved for later retrieval
   *        by the {@link #getMessage()} method)
   * @param cause
   *        The cause (which is saved for later retrieval by the
   *        {@link #getCause()} method).  (A null value is permitted,
   *        and indicates that the cause is nonexistent or unknown.)
   */
  public GenerationException(String message, Throwable cause)
  {
    super(message, cause);
  }
}
