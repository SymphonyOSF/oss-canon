/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon.json;

import javax.annotation.Nullable;

/**
 * A simple implementation of IParserContext to hold a snapshot of the context of a running parser.
 * 
 * @author Bruce Skingle
 *
 */
public class ParserContext implements IParserContext
{
  private final String            inputSource_;
  private final int               line_;
  private final int               col_;

  /**
   * Constructor.
   * 
   * @param other An existing parser context.
   */
  public ParserContext(IParserContext other)
  {
    inputSource_ = other.getInputSource();
    line_ = other.getLine();
    col_ = other.getCol();
  }

  @Override
  public @Nullable String getInputSource()
  {
    return inputSource_;
  }

  @Override
  public int getLine()
  {
    return line_;
  }

  @Override
  public int getCol()
  {
    return col_;
  }
}
