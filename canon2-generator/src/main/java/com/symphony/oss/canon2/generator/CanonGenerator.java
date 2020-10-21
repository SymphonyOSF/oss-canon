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
import java.io.IOException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import com.symphony.oss.commons.fault.CodingFault;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Version;

public abstract class CanonGenerator<
T extends ITemplateModel<T,M,S>,
M extends IOpenApiTemplateModel<T,M,S>,
S extends ISchemaTemplateModel<T,M,S>,
O extends IObjectSchemaTemplateModel<T,M,S,F>,
A extends IArraySchemaTemplateModel<T,M,S>,
P extends IPrimitiveSchemaTemplateModel<T,M,S>,
F extends IFieldTemplateModel<T,M,S>,
G extends IGroupSchemaTemplateModel<T,M,S>>
implements ICanonGenerator<T,M,S,O,A,P,F,G>
{
  private final String                                   language_;
//  private Map<Class<?>, ICanonDataModelFunc<ModelElement>> dataModelFuncMap_ = new HashMap<>();
  
  private Configuration                                  config_;
  private File                                           templateDir_;

  public CanonGenerator(String language)
  {
    language_ = language;
    
    config_ = new Configuration(new Version(2, 3, 25));
    
    config_.setDefaultEncoding("UTF-8");
    config_.setLocale(Locale.US);
  }
  
//  @SuppressWarnings("unchecked")
//  protected <T extends ModelElement> void register(Class<T> elementType, ICanonDataModelFunc<T> func)
//  {
//    dataModelFuncMap_.put(elementType, (ICanonDataModelFunc<ModelElement>)func);
//  }
//
//  @Override
//  public void populateDataModel(Map<String, Object> dataModel, ModelElement modelElement)
//  {
////    ICanonDataModelFunc<ModelElement> func = dataModelFuncMap_.get(modelElement.getElementType());
////    
////    if(func != null)
////      func.populateDataModel(dataModel, modelElement);
//    
//      doPopulateDataModel(dataModel, modelElement);
////    for(Entry<Class<?>, ICanonDataModelFunc<ModelElement>> entry : dataModelFuncMap_.entrySet())
////    {
////      if(entry.getKey().isInstance(modelElement))
////      {
////        entry.getValue().populateDataModel(dataModel, modelElement);
////      }
////    }
//  }
//  
//  private <T extends ModelElement> void doPopulateDataModel(Map<String, Object> dataModel, T modelElement)
//  {
//    @SuppressWarnings("unchecked")
//    ICanonDataModelFunc<T> func = (ICanonDataModelFunc<T>) dataModelFuncMap_.get(modelElement.getClass());
//    
//    if(func != null)
//    {
//      func.populateDataModel(dataModel, modelElement);
//    }
//  }
  
  @Override
  public ICanonGenerator<T,M,S,O,A,P,F,G> withTemplateDir(File templateDir)
  {
    templateDir_ = templateDir;
    config_.setTemplateLoader(getTemplateLoader());
    return this;
  }

  @Override
  public String getLanguage()
  {
    return language_;
  }

  @Override
  public Configuration getFreemarkerConfig()
  {
    return config_;
  }

  @Override
  public TemplateLoader getTemplateLoader()
  {
    try
    {
      return new FileTemplateLoader(templateDir_);
    }
    catch (IOException e)
    {
      throw new CodingFault(e);
    } 
//        return ClassTemplateLoader(getClass().getClassLoader(), "/canon");
  }
  

  @Override
  public Set<String> getTemplatesFor(TemplateType type, String templateGroup)
  {
    Set<String> result = new HashSet<>();
    
    File tORp = new File(templateDir_, type.getDirName());
    
    if(tORp.isDirectory())
    {
      File f = new File(tORp, templateGroup);
      String[] templates = f.list();
      
      if(templates != null && templates.length>0)
      {
        for(String t : templates)
          result.add(type.getDirName() + File.separatorChar + templateGroup + File.separatorChar + t);
      }
    }
    return result;
  }
  
//  private InputStream getResourceAsStream(String resource) {
//    final InputStream in
//            = getContextClassLoader().getResourceAsStream(resource);
//
//    return in == null ? getClass().getResourceAsStream(resource) : in;
//  }
//  
//  private ClassLoader getContextClassLoader() {
//      return Thread.currentThread().getContextClassLoader();
//  }
//  
//  @Override
//  public Set<String> getTemplatesFor(String type, String element)
//  {
//    Set<String> filenames = new HashSet<>();
//    String prefix = type + "/" + language_ + "/" + element;
//    
//    System.err.println("TEMPLATES");
////    try (InputStream in = getClass().getResourceAsStream("/canon/" + prefix))
//    try (InputStream in = getContextClassLoader().getResourceAsStream("/canon/"))
//    {
//      System.err.println("TEMPLATES in=" + in);
//      if(in != null)
//      {
//        System.err.println("++++++++");
//        int c;
//        while((c = in.read()) != -1)
//          System.err.write(c);
//
//        System.err.println("++++++++");
//        
////        try (BufferedReader br = new BufferedReader(new InputStreamReader(in)))
////        {
////            String resource;
////    
////            while ((resource = br.readLine()) != null) {
////                filenames.add(prefix + "/" + resource);
////                System.err.println("TEMPLATES resource=" + resource);
////            }
////        }
//      }
//    }
//    catch(IOException e)
//    {
//      throw new CodingFault(e);
//    }
//
//    return filenames;
//  }
}
