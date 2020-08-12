/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon.json;

import java.util.Objects;

/**
 * An exception while parsing, such as a Syntax error.
 * 
 * @author Bruce Skingle
 *
 */
public class ParserException extends Exception
{
  private static final long    serialVersionUID = 1L;

  private final IParserContext context_;

  /**
   * Constructor.
   * 
   * @param message
   *        The detail message (which is saved for later retrieval
   *        by the {@link #getMessage()} method)
   * @param context
   *        The Parser Context which provides teh location of the
   *        error in the input.
   */
  public ParserException(String message, IParserContext context)
  {
    super(getErrorMessage(message, context));
    
    context_ = context;
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
  public ParserException(String message, IParserContext context, Throwable cause)
  {
    super(getErrorMessage(message, context), cause);
   
    context_ = context;
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

  private static String getErrorMessage(String message, IParserContext context)
  {
    Objects.nonNull(context);
    
    StringBuilder s = new StringBuilder(message);
    
    if(context.getInputSource() != null)
    {
      s.append(" in \"" + context.getInputSource() + "\"");
    }
    
      return s.append(" at line ").append(context.getLine()).append(", col ").append(context.getCol()).toString();
  }
}
