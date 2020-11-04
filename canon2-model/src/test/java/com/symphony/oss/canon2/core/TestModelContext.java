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

import static org.junit.Assert.fail;

import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.symphony.oss.canon.json.IParserContext;
import com.symphony.oss.canon.json.ParserErrorException;
import com.symphony.oss.canon.json.ParserException;
import com.symphony.oss.canon.json.ParserResultException;
import com.symphony.oss.commons.fault.CodingFault;

@SuppressWarnings("javadoc")
public class TestModelContext
{
  @Test
  public void testMinimal() throws ParserResultException
  {
    test("{\n" + 
        "  \"canon\": \"0.0.1\",\n" + 
        "  \"info\": {\n" + 
        "    \"title\": \"typedef example\",\n" + 
        "    \"license\": {\n" + 
        "      \"name\": \"Apache2\"\n" + 
        "    }\n" + 
        "  },\n" + 
        "  \"x-canon-id\": \"com.symphony.oss.canon2.test.typedef\",\n" + 
        "  \"x-canon-version\": \"1.0\",\n" + 
        "  \"x-canon-generators\": {\n" + 
        "    \"java\": {\n" + 
        "      \"genPackage\":  \"com.symphony.oss.canon2.test.typedef\"\n" + 
        "    }\n" + 
        "  },\n" + 
        "  \"components\": {\n" + 
        "    \"schemas\": {   \n" + 
        "      \"UserId\": {\n" + 
        "        \"type\": \"integer\",\n" + 
        "        \"format\": \"int64\"\n" + 
        "      }\n" + 
        "    }\n" + 
        "  }\n" + 
        "}");
  }
  

  @Test
  public void testTypes() throws ParserResultException
  {
    testType(true, false, "integer", "float");
    
    testType(false, false, "number", "int32");
    testType(false, false, "number", "int64");
    testType(false, true, "number", "invalid");
  }
  
  private void testType(boolean expectError, boolean expectWarning, String type, String format) throws ParserResultException
  {
    try
    {
      SourceContext context = test("{\n" + 
          "  \"canon\": \"0.0.1\",\n" + 
          "  \"info\": {\n" + 
          "    \"title\": \"typedef example\",\n" + 
          "    \"license\": {\n" + 
          "      \"name\": \"Apache2\"\n" + 
          "    }\n" + 
          "  },\n" + 
          "  \"x-canon-id\": \"com.symphony.oss.canon2.test.typedef\",\n" + 
          "  \"x-canon-version\": \"1.0\",\n" + 
          "  \"x-canon-generators\": {\n" + 
          "    \"java\": {\n" + 
          "      \"genPackage\":  \"com.symphony.oss.canon2.test.typedef\"\n" + 
          "    }\n" + 
          "  },\n" + 
          "  \"components\": {\n" + 
          "    \"schemas\": {   \n" + 
          "      \"UserId\": {\n" + 
          "        \"type\": \"" + type + "\",\n" + 
          "        \"format\": \"" + format + "\"\n" + 
          "      }\n" + 
          "    }\n" + 
          "  }\n" + 
          "}");
      
      List<ParserException> errors = context.getErrors();
      
      if(expectWarning)
      {
        if(errors.isEmpty())
        {
          fail("Expected warning, but encountered none.");
        }
      }
      else
      {
        if(!errors.isEmpty())
        {
          fail("Encountered unexpected warnings.");
        }
      }
    }
    catch(ParserResultException e)
    {
      //if(e.getContext().getLine() == 18 && e.getContext().getLine() == 11)
      {
        if(expectError)
        {
            System.out.println("Error as expected.");
        } 
        else
        {
          throw e;
        }
      }
    }
  }
  
  @Test
  public void testDoubleMinMax() throws ParserResultException
  {
    test("{\n" + 
        "  \"canon\": \"0.0.1\",\n" + 
        "  \"info\": {\n" + 
        "    \"title\": \"typedef example\",\n" + 
        "    \"license\": {\n" + 
        "      \"name\": \"Apache2\"\n" + 
        "    }\n" + 
        "  },\n" + 
        "  \"x-canon-id\": \"com.symphony.oss.canon2.test.typedef\",\n" + 
        "  \"x-canon-version\": \"1.0\",\n" + 
        "  \"x-canon-generators\": {\n" + 
        "    \"java\": {\n" + 
        "      \"genPackage\":  \"com.symphony.oss.canon2.test.typedef\"\n" + 
        "    }\n" + 
        "  },\n" + 
        "  \"components\": {\n" + 
        "    \"schemas\": {   \n" + 
        "    \"DoubleTypedef\": {\n" + 
        "        \"description\": \"A double typedef with a facade.\",\n" + 
        "        \"x-canon-facade\": true,\n" + 
        "        \"type\": \"number\",\n" + 
        "        \"format\": \"double\",\n" + 
        "        \"minimum\": -765546546547723.03330025,\n" + 
        "        \"maximum\": 7665465456464550000.00333025\n" + 
        "      }\n" + 
        "    }\n" + 
        "  }\n" + 
        "}");
  }
  
  @Test
  public void testInavlidMinMax() throws ParserResultException
  {
    testError("{\n" + 
        "  \"canon\": \"0.0.1\",\n" + 
        "  \"info\": {\n" + 
        "    \"title\": \"typedef example\",\n" + 
        "    \"license\": {\n" + 
        "      \"name\": \"Apache2\"\n" + 
        "    }\n" + 
        "  },\n" + 
        "  \"x-canon-id\": \"com.symphony.oss.canon2.test.typedef\",\n" + 
        "  \"x-canon-version\": \"1.0\",\n" + 
        "  \"x-canon-generators\": {\n" + 
        "    \"java\": {\n" + 
        "      \"genPackage\":  \"com.symphony.oss.canon2.test.typedef\"\n" + 
        "    }\n" + 
        "  },\n" + 
        "  \"components\": {\n" + 
        "    \"schemas\": {   \n" + 
        "    \"Int64Typedef\": {\n" + 
        "        \"description\": \"A double typedef with a facade.\",\n" + 
        "        \"x-canon-facade\": true,\n" + 
        "        \"type\": \"number\",\n" + 
        "        \"format\": \"int64\",\n" + 
        "        \"minimum\": -765546546547723.03330025,\n" + 
        "        \"maximum\": 7665465456464550000.00333025\n" + 
        "      }\n" + 
        "    }\n" + 
        "  }\n" + 
        "}",
        "23:20",
        "24:20");
  }
  
  private void testError(String input, String ...locations) throws ParserResultException
  {
    Set<String> locationSet = new HashSet<>();
    
    for(String l : locations)
      locationSet.add(l);
    
    try
    {
      test(input);
    }
    catch(ParserResultException e)
    {
      for(ParserException pe : e.getParserExceptions())
      {
        IParserContext context = pe.getContext();
        
        if(locationSet.remove(context.getLine() + ":" + context.getCol()))
        {
          System.out.println("Error as expected: " + pe);
        }
        else
        {
          throw e;
        }
      }
      
      if(!locationSet.isEmpty())
        throw e;
    }
    catch(ParserException e)
    {
      e.printStackTrace();
      IParserContext context = e.getContext();
      
      if(locationSet.remove(context.getLine() + ":" + context.getCol()))
      {
        System.out.println("Error as expected: " + e);
      }
      else
      {
        throw e;
      }
      
      if(!locationSet.isEmpty())
        throw e;
    }
  }
  private SourceContext test(String input) throws ParserResultException
  {
    try
    {
      ModelContext modelContext = new ModelContext.Builder().build();
      
      SourceContext context = modelContext.addGenerationSource(new URL("http://local/input.json"), new StringReader(input));
      modelContext.process();
      
      return context;
    }
    catch(MalformedURLException e)
    {
      throw new CodingFault(e);
    }
  }
  
  static class ModelContext extends CanonModelContext
  {
    protected ModelContext(AbstractBuilder<?, ?> builder)
    {
      super(builder);
    }

    @Override
    protected void process(Deque<SourceContext> processQueue)
    {
      // TODO Auto-generated method stub
      
    }
    
    static class Builder extends CanonModelContext.AbstractBuilder<Builder, ModelContext>
    {
      Builder()
      {
        super(Builder.class);
      }

      @Override
      protected ModelContext construct()
      {
        return new ModelContext(this);
      }
    }
  }
}
