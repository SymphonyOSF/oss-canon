/*
 *
 *
 * Copyright 2017 Symphony Communication Services, LLC.
 *
 * Licensed to The Symphony Software Foundation (SSF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The SSF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.symphony.oss.canon.launch;

import com.symphony.oss.canon.json.ParserResultException;
import com.symphony.oss.canon2.generator.Canon2;

public class Canon2Launch extends Canon2
{ 
  /**
   * Launcher.
   * 
   * @param argv
   *          command line arguments.
   * @throws ParserResultException 
   *           If anything goes wrong.
   */
  public static void main(String[] argv) throws ParserResultException
  {
    new Canon2Launch().run(argv);
  }
}
