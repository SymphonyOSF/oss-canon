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

import java.util.Collection;

/**
 * An exception representing a failed attempt to parse an input.
 * 
 * Contains one or more individual ParserExceptions.
 * 
 * @author Bruce Skingle
 *
 */
public class ParserResultException extends Exception implements IParserException
{
  private static final long serialVersionUID = 1L;
  private static final String CAUSED_BY = "Caused by:";
  
  private final Collection<ParserException> parserExceptions_;

  /**
   * Constructor.
   * 
   * @param parserExceptions Exceptions to be included in the result.
   */
  public ParserResultException(Collection<ParserException> parserExceptions)
  {
    parserExceptions_ = parserExceptions;
  }

  public Collection<ParserException> getParserExceptions()
  {
    return parserExceptions_;
  }

  @Override
  public void populateString(String indent, StringBuilder builder)
  {
    builder.append(indent);
    builder.append(super.toString());
    
    if(!getParserExceptions().isEmpty())
    {
      indent = indent.length()==0 ? "\n  " : indent + "  ";
      builder.append(indent);
      builder.append(CAUSED_BY);
     
      for(ParserException cause : getParserExceptions())
      {
        cause.populateString(indent, builder);
      }
    }
    
    if(getCause() instanceof IParserException)
    {
      indent = indent.length()==0 ? "\n  " : indent + "  ";
      builder.append(indent);
      builder.append(CAUSED_BY);
     
      ((IParserException)getCause()).populateString(indent, builder);
    }
  }
}
