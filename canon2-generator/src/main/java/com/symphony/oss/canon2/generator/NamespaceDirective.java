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

package com.symphony.oss.canon2.generator;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import freemarker.core.Environment;
import freemarker.ext.beans.StringModel;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateModelIterator;
import freemarker.template.TemplateScalarModel;

/**
 *  FreeMarker user-defined directive that progressively transforms
 *  the output of its nested content to upper-case.
 *
 *
 *  <p><b>Directive info</b></p>
 *
 *  <p>Directive parameters: None
 *  <p>Loop variables: None
 *  <p>Directive nested content: Yes
 */
public class NamespaceDirective implements TemplateDirectiveModel, INamespace
{
  private static String[] GROUPS = new String[]
      {
          "java", "javax", "org", "com"
      };
  
  private Map<String, String>      forwardMap_ = new HashMap<>();
  private Map<String, String>      reverseMap_ = new HashMap<>();
  private Map<String, Set<String>> importMap_  = new HashMap<>();
  private Set<String>              importSet_  = new TreeSet<>();
  private String                   baseClass_;
  
  private boolean getBooleanParam(Map params, String name) throws TemplateModelException
  {
    return getBooleanParam(params, name, false);
  }


  private boolean getBooleanParam(Map params, String name, boolean defaultValue) throws TemplateModelException
  {
    Object param = params.get(name);
    
    if(param == null)
      return defaultValue;
    
    if(param instanceof TemplateBooleanModel)
      return ((TemplateBooleanModel)param).getAsBoolean();
    
    throw new TemplateModelException(
        "The \"" + name + "\" parameter must be a boolean.");
  }
  
  private String getStringParam(Map params, String name) throws TemplateModelException
  {
    Object param = params.get(name);
    
    if(param == null)
      return null;
    
    if(param instanceof TemplateScalarModel)
      return ((TemplateScalarModel)param).toString();
    
    throw new TemplateModelException(
        "The \"" + name + "\" parameter must be a scalar value.");
  }
  
  @Override
  public void execute(Environment env,
          Map params, TemplateModel[] loopVars,
          TemplateDirectiveBody body)
          throws TemplateException, IOException
  {
    if (loopVars.length != 0)
    {
      throw new TemplateModelException("This directive doesn't allow loop variables.");
    }
    
    if(getStringParam(params, "baseClass") != null)
    {
      baseClass_ = getStringParam(params, "baseClass") + ".";
    }
    
    Object modelObject = params.get("model");
    
    if(modelObject instanceof StringModel)
    {
      Object w = ((StringModel)modelObject).getWrappedObject();
      
      if(w instanceof INamespaceResolveable)
      {

        //env.getOut().write("// About to Resolve " + w);
        
        ((INamespaceResolveable)w).resolve(this, env.getOut());
        
        //env.getOut().write("/* Resolved " + w + " */\n");
      }
      
//      Set names = env.getKnownVariableNames();
//      TemplateHashModel data = env.getDataModel();
//      TemplateModel path = data.get("genPath");
//      
////      TemplateCollectionModel keys = data.;
////      TemplateModelIterator it = env.getCurrentNamespace().keys().iterator();
//      
//      TemplateModel entityObject = data.get("field");
//      
//      
//      String type = 
//          modelObject.getClass().getSimpleName();
    }
    
    if(getBooleanParam(params, "clear"))
    {
//      env.getOut().write("/* Clear Namespace */\n");
      forwardMap_.clear();
      reverseMap_.clear();
      importMap_.clear();
      importSet_.clear();
      
      for(String group : GROUPS)
        importMap_.put(group, new TreeSet<String>());
    }
    
    String importName = getStringParam(params, "import");
    if(importName != null)
    {
      String shortName = resolveImport(importName, env.getOut());
      
      
      String name = getStringParam(params, "name");
      
//      try
//      {
//        env.getOut().write("\n/* import " + importName + " to " + shortName + " as " + name + " */\n");
//      }
//      catch (IOException e)
//      {
//        // debug anyway
//      }
      
      if(name == null)
      {
        env.getOut().write(shortName);
      }
      else
      {
        env.getCurrentNamespace().put(name, shortName);
      }
    }
    
    if(getBooleanParam(params, "printImports"))
    {
      String localPackage = getStringParam(params, "package");
      
      
      for(String group : GROUPS)
      {
        boolean newline=false;
        for(String packageName : importMap_.get(group))
        {
          if(!packageName.startsWith(localPackage) && !packageName.startsWith("java.lang."))
          {
            env.getOut().write("import " + packageName + ";\n");
            newline=true;
          }
        }
        
        if(newline)
          env.getOut().write("\n");
      }
      for(String packageName : importSet_)
      {
        if(!packageName.startsWith(localPackage))
          env.getOut().write("import " + packageName + ";\n");
      }
    }

    // If there is non-empty nested content:
    if (body != null)
    {
        // Executes the nested body. Same as <#nested> in FTL, except
        // that we use our own writer instead of the current output writer.
      
      
      TemplateHashModel data = env.getDataModel();
      TemplateModel path = data.get("headerPath");
      File foo = new File(path.toString());
      try(FileWriter out = new FileWriter(foo, getBooleanParam(params, "append", true)))
      {
        
        
        
        body.render(out);
        
//        TemplateModelIterator it = env.getCurrentNamespace().keys().iterator();
//        while(it.hasNext())
//        {
//          String name = it.next().toString();
//          String value = env.getCurrentNamespace().get(name).toString();
//          out.write(name + " -> " + value + "\n");
//        }
        
//        out.println("Hello boys! " + path);
        
//        Iterator paramIter = params.entrySet().iterator();
//        while (paramIter.hasNext()) {
//            Map.Entry ent = (Map.Entry) paramIter.next();
//
//            String paramName = (String) ent.getKey();
//            TemplateModel paramValue = (TemplateModel) ent.getValue();
//            
//            out.println(paramName + " -> " + paramValue);
//        }
      }
      
        
    }
  }

  @Override
  public String resolveImport(String importName, Writer writer)
  {
    String shortName = forwardMap_.get(importName);
    
    if(shortName == null)
    {
      if(importName.startsWith(baseClass_))
      {
        shortName = importName.substring(baseClass_.length());
      }
      else
      {
        int i = importName.lastIndexOf('.');
        shortName = i==-1 ? importName : importName.substring(i+1);
      }
      
      if(reverseMap_.get(shortName) == null)
      {
        forwardMap_.put(importName, shortName);
        reverseMap_.put(shortName, importName);
        
        int i = importName.indexOf('.');
        String group = i==-1 ? "" : importName.substring(0,i);
        
        Set<String> set = importMap_.get(group);
        
        if(set == null)
          importSet_.add(importName);
        else
          set.add(importName);
        
//        try
//        {
//          writer.write("\n/* resolve " + importName + " to " + shortName + " */\n");
//        }
//        catch (IOException e)
//        {
//          // debug anyway
//        }
      }
      else
      {
        forwardMap_.put(importName, importName);
        reverseMap_.put(importName, importName);
        shortName = importName;
        
//        try
//        {
//          writer.write("\n/* resolve " + importName + " to " + shortName + " */\n");
//        }
//        catch (IOException e)
//        {
//          // debug anyway
//        }
      }
    }
    return shortName;
  }

  /**
   * A {@link Writer} that transforms the character stream to upper case
   * and forwards it to another {@link Writer}.
   */
  private static class UpperCaseFilterWriter extends Writer {

      private final Writer out;

      UpperCaseFilterWriter (Writer out) {
          this.out = out;
      }

      public void write(char[] cbuf, int off, int len)
              throws IOException {
          char[] transformedCbuf = new char[len];
          for (int i = 0; i < len; i++) {
              transformedCbuf[i] = Character.toUpperCase(cbuf[i + off]);
          }
          out.write(transformedCbuf);
      }

      public void flush() throws IOException {
          out.flush();
      }

      public void close() throws IOException {
          out.close();
      }
  }

}