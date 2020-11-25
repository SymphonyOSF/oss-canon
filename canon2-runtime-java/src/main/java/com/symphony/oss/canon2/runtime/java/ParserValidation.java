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

package com.symphony.oss.canon2.runtime.java;

/**
 * Validation constraints to be applied by the parser.
 * 
 * @author Bruce Skingle
 *
 */
public enum ParserValidation
{
  /**
   * Enforce all validation checks.
   */
  STRICT(false, false), 
  
  /**
   * Allow and ignore unknown additional attributes.
   */
  ALLOW_UNKNOWN_ATTRIBUTES(true, false),
  
  /**
   * Allow and ignore unknown additional attributes, and ignore attributes
   * with invalid types or values.
   */
  IGNORE_INVALID_ATTRIBUTES(true, true);
  
  private final boolean allowUnnownAttributes_;
  private final boolean ignoreInvalidAttributes_;
  
  private ParserValidation(boolean allowUnnownAttributes, boolean ignoreInvalidAttributes)
  {
    allowUnnownAttributes_ = allowUnnownAttributes;
    ignoreInvalidAttributes_ = ignoreInvalidAttributes;
  }

  /**
   * Return true iff unknown additional attributes should be ignored.
   * 
   * @return true iff unknown additional attributes should be ignored.
   */
  public boolean isAllowUnknownAttributes()
  {
    return allowUnnownAttributes_;
  }

  /**
   * Return true iff attributes with invalid types or values should be ignored.
   * 
   * @return true iff attributes with invalid types or values should be ignored.
   */
  public boolean isIgnoreInvalidAttributes()
  {
    return ignoreInvalidAttributes_;
  }
}
