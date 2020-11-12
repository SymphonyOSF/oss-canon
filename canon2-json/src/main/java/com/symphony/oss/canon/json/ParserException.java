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
 * An exception while parsing, such as a Syntax error.
 * 
 * @author Bruce Skingle
 *
 */
public abstract class ParserException extends RuntimeException implements IParserException
{
  private static final long    serialVersionUID = 1L;
  private static final ParserContext UNKNOWN_LOCATION = new ParserContext("Unknown location");
  private static final String CAUSED_BY = "Caused by:";

  private final IParserContext context_;
  private final boolean fatal_;

  /**
   * Constructor.
   * 
   * @param message
   *        The detail message (which is saved for later retrieval
   *        by the {@link #getMessage()} method)
   * @param context
   *        The Parser Context which provides the location of the
   *        error in the input.
   * @param fatal
   *        True if this is a fatal error as opposed to a non-fatal warning.
   */
  public ParserException(String message, IParserContext context, boolean fatal)
  {
    super(getErrorMessage(message, context));
    
    context_ = context;
    fatal_ = fatal;
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
   * @param fatal
   *        True if this is a fatal error as opposed to a non-fatal warning.
   * @param cause
   *        The cause (which is saved for later retrieval by the
   *        {@link #getCause()} method).  (A null value is permitted,
   *        and indicates that the cause is nonexistent or unknown.)
   */
  public ParserException(String message, IParserContext context, boolean fatal, Throwable cause)
  {
    super(getErrorMessage(message, context), cause);
   
    context_ = context;
    fatal_ = fatal;
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
   * @param fatal
   *        True if this is a fatal error as opposed to a non-fatal warning.
   */
  public ParserException(String message, IJsonDomNodeProvider jsonProvider, boolean fatal)
  {
    this(message, jsonProvider.getJson().getContext(), fatal);
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
   * @param fatal
   *        True if this is a fatal error as opposed to a non-fatal warning.
   * @param cause
   *        The cause (which is saved for later retrieval by the
   *        {@link #getCause()} method).  (A null value is permitted,
   *        and indicates that the cause is nonexistent or unknown.)
   */
  public ParserException(String message, IJsonDomNodeProvider jsonProvider, boolean fatal, Throwable cause)
  {
    this(message, jsonProvider.getJson().getContext(), fatal, cause);
  }

  /**
   * Constructor.
   * 
   * This is to be used only where an unexpected runtime error occurs.
   * 
   * @param fatal
   *        True if this is a fatal error as opposed to a non-fatal warning.
   * @param cause
   *        The cause (which is saved for later retrieval by the
   *        {@link #getCause()} method).  (A null value is permitted,
   *        and indicates that the cause is nonexistent or unknown.)
   */
  public ParserException(boolean fatal, Exception cause)
  {
    this(cause.getMessage(), UNKNOWN_LOCATION, fatal, cause);
  }

  /**
   * Return the location of the error in the input.
   * 
   * @return The location of the error in the input.
   */
  public IParserContext getContext()
  {
    return context_;
  }

  /**
   * Return true iff this is a fatal error as opposed to a non-fatal warning.
   * 
   * @return true iff this is a fatal error as opposed to a non-fatal warning.
   */
  public boolean isFatal()
  {
    return fatal_;
  }

  private static String getErrorMessage(String message, IParserContext context)
  {
    StringBuilder s = new StringBuilder(message);
   
    if(context == null)
    {
      s.append(" at unknown location");
    }
    else
    {
      if(context.getInputSource() != null)
      {
        s.append(" in \"" + context.getInputSource() + "\"");
      }
      
      s.append(" at line ").append(context.getLine()).append(", col ").append(context.getCol());
    }
    
    return s.toString();
  }

  @Override
  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    
    populateString("", builder);
    
    return builder.toString();
  }

  @Override
  public void populateString(String indent, StringBuilder builder)
  {
    builder.append(indent);
    builder.append(super.toString());
    
    if(getCause() instanceof IParserException)
    {
      indent = indent.length()==0 ? "\n  " : indent + "  ";
      builder.append(indent);
      builder.append(CAUSED_BY);
     
      ((IParserException)getCause()).populateString(indent, builder);
    }
  }
}
