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
 * A parser context which represents a point in the input stream for a parser.
 * 
 * @author Bruce Skingle
 *
 */
public interface IParserContext
{
  /**
   * Return the name of the input source, if any.
   * 
   * @return The name of the input source, if any.
   */
  @Nullable String getInputSource();

  /**
   * Return the line number of this context in the input.
   * 
   * @return The line number of this context in the input.
   */
  int getLine();

  /**
   * Return the column number of this context in the input.
   * 
   * @return The column number of this context in the input.
   */
  int getCol();

  /**
   * Return the source location as a string for printing as an error message.
   * 
   * @return the source location as a string for printing as an error message.
   */
  String getSourceLocation();

}
