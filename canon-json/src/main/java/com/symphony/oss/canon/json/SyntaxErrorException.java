/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon.json;

/**
 * A syntax error.
 * 
 * @author Bruce Skingle
 *
 */
public class SyntaxErrorException extends ParserException
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
  public SyntaxErrorException(String message, IParserContext context)
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
  public SyntaxErrorException(String message, IParserContext context, Throwable cause)
  {
    super(message, context, cause);
  }

}
