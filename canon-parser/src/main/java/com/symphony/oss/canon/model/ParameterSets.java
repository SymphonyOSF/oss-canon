/*
 *
 *
 * Copyright 2018 Symphony Communication Services, LLC.
 *
 * Licensed to The Symphony Software Foundation (SSF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
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

package com.symphony.oss.canon.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.symphony.oss.canon.parser.ParserContext;

public class ParameterSets extends ModelElement
{
  private static Logger log_ = LoggerFactory.getLogger(ParameterSets.class);

  public ParameterSets(Components parent, ParserContext parserContext)
  {
    super(parent, parserContext, "ParameterSets");
    
    for(ParserContext parameterSet : parserContext)
    {
      log_.debug("Found parameterSet \"" + parameterSet.getName() + "\" at " + parameterSet.getPath());
      
      add(parameterSet.getName(), new ParameterContainer(this, parameterSet, "ParameterSet", parameterSet.getName(),
          parameterSet, null));
    }
  }
}
