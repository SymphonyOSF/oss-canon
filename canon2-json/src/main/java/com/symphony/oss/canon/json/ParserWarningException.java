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

package com.symphony.oss.canon.json;

import com.symphony.oss.canon.json.model.IJsonDomNodeProvider;

/**
 * A fatal exception while parsing, such as a Syntax error.
 * 
 * @author Bruce Skingle
 *
 */
public class ParserWarningException extends ParserException
{
  private static final long    serialVersionUID = 1L;
  private static final boolean FATAL = false;

  /**
   * Constructor.
   * 
   * @param message
   *        The detail message (which is saved for later retrieval
   *        by the {@link #getMessage()} method)
   * @param context
   *        The Parser Context which provides the location of the
   *        error in the input.
   */
  public ParserWarningException(String message, IParserContext context)
  {
    super(message, context, FATAL);
  }

  /**
   * Constructor.
   * 
   * @param message
   *        The detail message (which is saved for later retrieval
   *        by the {@link #getMessage()} method)
   * @param context
   *        The Parser Context which provides the location of the
   *        error in the input.
   *
   * @param cause
   *        The cause (which is saved for later retrieval by the
   *        {@link #getCause()} method).  (A null value is permitted,
   *        and indicates that the cause is nonexistent or unknown.)
   */
  public ParserWarningException(String message, IParserContext context, Throwable cause)
  {
    super(message, context, FATAL, cause);
  }

  /**
   * Constructor.
   * 
   * @param message
   *        The detail message (which is saved for later retrieval
   *        by the {@link #getMessage()} method)
   * @param jsonProvider
   *        The JSON node which provides the location of the
   *        error in the input.
   */
  public ParserWarningException(String message, IJsonDomNodeProvider jsonProvider)
  {
    super(message, jsonProvider, FATAL);
  }

  /**
   * Constructor.
   * 
   * @param message
   *        The detail message (which is saved for later retrieval
   *        by the {@link #getMessage()} method)
   * @param jsonProvider
   *        The JSON node which provides the location of the
   *        error in the input.
   *
   * @param cause
   *        The cause (which is saved for later retrieval by the
   *        {@link #getCause()} method).  (A null value is permitted,
   *        and indicates that the cause is nonexistent or unknown.)
   */
  public ParserWarningException(String message, IJsonDomNodeProvider jsonProvider, Throwable cause)
  {
    super(message, jsonProvider, FATAL, cause);
  }

  /**
   * Constructor.
   * 
   * This is to be used only where an unexpected runtime error occurs.
   * 
   * @param cause
   *        The cause (which is saved for later retrieval by the
   *        {@link #getCause()} method).  (A null value is permitted,
   *        and indicates that the cause is nonexistent or unknown.)
   */
  public ParserWarningException(Exception cause)
  {
    super(FATAL, cause);
  }
}
