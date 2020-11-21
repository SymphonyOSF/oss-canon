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

package com.symphony.oss.canon2.generator;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.symphony.oss.commons.fault.CodingFault;


public class PathNameConstructor<T extends ITemplateModel<?,?,?>> implements IPathNameConstructor<T>
{
  private final String language_;
  private final String canonIdString_;

  public PathNameConstructor(String language, String canonIdString)
  {
    language_ = language;
    canonIdString_ = canonIdString;
  }

  @Override
  public String constructFile(String templateName,
      T modelElement) 
  {
    return constructFile(null, templateName, modelElement, modelElement.getName());
  }

  protected String constructFile(String directoryPath, String templateName,
     T modelElement, String modelElementName) 
  {
    int     underscoreIndex = templateName.indexOf('_');
    int     dollarIndex = templateName.indexOf('$');
    
    if(underscoreIndex == -1 && dollarIndex == -1)
      return null;
    
    StringBuilder s = null;
    
    if(language_ != null)
    {
      s = new StringBuilder();
      
      s.append(language_);
    }
    
    if(directoryPath != null)
    {
      if(s==null)
        s = new StringBuilder();
      else
        s.append(File.separatorChar);
      
      s.append(directoryPath);
    }
    
    if(s==null)
      s = new StringBuilder();
    else
      s.append(File.separatorChar);
    
    
    if(dollarIndex != -1)
    {
      List<String>  varNameList = new ArrayList<>();
      StringBuilder varName = null;
      boolean firstVarName = true;
      boolean inDollar = false;
      boolean dollarFirst=false;
      
      char[] chars = templateName.endsWith(".ftl") ? templateName.substring(0, templateName.length() - 4).toCharArray() : 
        templateName.toCharArray();
      
      for(int i=0 ; i<chars.length ; i++)
      {
        if(inDollar)
        {  
          switch(chars[i])
          {
            case '{':
              varName = new StringBuilder();
              varNameList.clear();
              firstVarName = true;
              break;
              
            default:
              s.append('$');
              s.append(chars[i]);
          }
          inDollar = false;
        }
        else if(varName != null)
        {  
          switch(chars[i])
          {
            case '}':
              varNameList.add(varName.toString());
              varName = null;
              firstVarName = true;
              
              Object value = modelElement;
              
              for(String v : varNameList)
              {
                String name = "get" + v;
                
                try
                {
                  Method getter = modelElement.getClass().getMethod(name);
                  value = getter.invoke(value);
                }
                catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
                {
                  throw new CodingFault("Unknown variable \"" + name + "\"", e);
                }
              }
              
              if(dollarFirst)
              {
                s.append(canonIdString_);
              }
              s.append(value);
              
              if(i<chars.length && templateName.lastIndexOf('.') != i)
              {
                s.append(canonIdString_);
              }
              break;
            
            case '.':
              varNameList.add(varName.toString());
              varName = new StringBuilder();
              firstVarName = true;
              break;
              
            default:
              if(firstVarName)
              {
                varName.append(Character.toUpperCase(chars[i]));
                firstVarName = false;
              }
              else
              {
                varName.append(chars[i]);
              }
          }
        }
        else
        {  
          switch(chars[i])
          {
            case '$':
              inDollar=true;
              dollarFirst = i == 0;
              break;
            
            default:
              s.append(chars[i]);
          }
        }
      }
    }
    else
    {
      int     len = templateName.endsWith(".ftl") ? templateName.length() - 4 : templateName.length();
      int     i = 0;

      while(i<underscoreIndex)
      {
        s.append(templateName.charAt(i++));
      }
      
      if(i > 0)
      {
        s.append(canonIdString_);
      }
      
      i++; // skip the underscore
      
      s.append(modelElementName);

      if(i<len && templateName.lastIndexOf('.', len-1) != i)
      {
        s.append(canonIdString_);
      }
      
      while(i<len)
      {
        s.append(templateName.charAt(i++));
      }
    }
    
    return s.toString();
  }
}
