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

package com.symphony.oss.canon.generator.java;

import java.io.File;
import java.util.Map;
import java.util.regex.Matcher;

import com.symphony.oss.canon.model.ModelElement;
import com.symphony.oss.canon.model.PathNameConstructor;
import com.symphony.oss.canon.parser.GenerationException;

public class JavaPathNameConstructor extends PathNameConstructor
{
  private String packageVar_;

  /**
   * Constructor.
   * 
   * @param packageVar  Name of the model variable containing the java package name.
   */
  public JavaPathNameConstructor(String packageVar)
  {
    super("java");
    packageVar_ = packageVar;
  }

  @Override
  public String constructFile(Map<String, Object> dataModel, String templateName,
      ModelElement modelElement) throws GenerationException
  {
    return constructFile(convertPath(dataModel.get(packageVar_)), templateName, modelElement, modelElement.getCamelCapitalizedName());
  }

  private Object convertPath(Object object)
  {
    if(object == null)
      return null;
    
    return object.toString().replaceAll("\\.", Matcher.quoteReplacement(File.separator));
//    return object.toString().replaceAll("\\.", File.separator);
  }
}
