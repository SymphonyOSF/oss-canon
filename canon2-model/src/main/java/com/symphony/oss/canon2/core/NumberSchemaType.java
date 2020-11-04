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

package com.symphony.oss.canon2.core;

/**
 * The different types of Numeric schemas.
 * 
 * @author Bruce Skingle
 *
 */
public enum NumberSchemaType
{
  /** 
   * type: number
   */
  BIG_DECIMAL, 
  
  /** 
   * type: integer
   */
  BIG_INTEGER, 
  
  /** 
   * type: number
   * format: double
   */
  DOUBLE, 
  
  /** 
   * type: number
   * format: float
   */
  FLOAT, 
  
  /** 
   * type: number or integer
   * format: int32
   */
  INT32, 
  
  /** 
   * type: number or integer
   * format: int64
   */
  INT64;
}
