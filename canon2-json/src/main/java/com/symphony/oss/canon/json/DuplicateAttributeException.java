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

/**
 * An attribute with the same name as another attribute in the same object.
 * 
 * @author Bruce Skingle
 *
 */
public class DuplicateAttributeException extends ParserException
{
  private static final long serialVersionUID = 1L;
  
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
  public DuplicateAttributeException(String message, IParserContext context)
  {
    super(message, context);
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
  public DuplicateAttributeException(String message, IParserContext context, Throwable cause)
  {
    super(message, context, cause);
  }

}
