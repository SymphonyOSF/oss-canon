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

import javax.annotation.Nullable;

/**
 * A simple implementation of IParserContext to hold a snapshot of the context of a running parser.
 * 
 * @author Bruce Skingle
 *
 */
public class ParserContext implements IParserContext
{
  private final String inputSource_;
  private final int    line_;
  private final int    col_;
  private final String sourceLocation_;

  /**
   * Constructor.
   * 
   * @param inputSource The input source.
   * @param line        The line number starting at 1.
   * @param col         The column number starting at 1.
   */
  public ParserContext(String inputSource, int line, int col)
  {
    inputSource_ = inputSource;
    line_ = line;
    col_ = col;
    
    StringBuilder s = new StringBuilder("in ");
    
    if(inputSource_ == null)
      s.append("unknonwn input source");
    else
      s.append(inputSource_);

    s.append(" at line ");
    s.append(line_);

    s.append(", col ");
    s.append(col_);
    
    sourceLocation_ = s.toString();
  }

  /**
   * Constructor for a file level error (reports line and column 0).
   * 
   * @param inputSource The input source.
   */
  public ParserContext(String inputSource)
  {
    inputSource_ = inputSource;
    line_ = 0;
    col_ = 0;
    
    if(inputSource_ == null)
      sourceLocation_ = "in unknonwn input source";
    else
      sourceLocation_ = "in " + inputSource_;
  }

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
    sourceLocation_ = other.getSourceLocation();
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
  
  @Override
  public String getSourceLocation()
  {
    return sourceLocation_;
  }

  @Override
  public String toString()
  {
    return sourceLocation_;
  }
}
