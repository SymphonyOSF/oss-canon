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

package com.symphony.oss.canon2.generator.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.symphony.oss.canon2.parser.CanonGenerator;
import com.symphony.oss.canon2.parser.IModelContext;
import com.symphony.oss.canon2.parser.IPathNameConstructor;
import com.symphony.oss.commons.dom.json.IJsonObject;

public class JavaGenerator extends CanonGenerator
{
  private static Logger log_ = LoggerFactory.getLogger(JavaGenerator.class);
  
  // IDs in the model
  static final String          GEN_PACKAGE                  = "genPackage";
  static final String          FACADE_PACKAGE               = "facadePackage";

  // IDs of generated attributes
  static final String PREFIX                       = "java";
  static final String PACKAGE_NAME                 = PREFIX + "PackageName";
  static final String IMPORTS                      = PREFIX + "Imports";
  static final String CLASS_NAME                   = PREFIX + "ClassName";
  static final String GENERATED_BUILDER_CLASS_NAME = PREFIX + "GeneratedBuilderClassName";
  
  private IPathNameConstructor templatePathBuilder_;
  private IPathNameConstructor proformaPathBuilder_;
  
  /**
   * Constructor.
   */
  public JavaGenerator()
  {
    super("java");
    
//    register(Array.class, (data, model) -> {
//        data.put("tsElement", model.getCamelCapitalizedName() + " " + model.getClass());
//      
//        
//        data.put("baseType", getBaseType(model.getElementSchema()));
//        
//        // First set modelJavaClassName, modelJavaFullyQualifiedClassName, and modelIsGenerated
//  
//        String externalType = model.getAttributes().get(JAVA_EXTERNAL_TYPE);
//  
//        if(externalType != null)
//        {
//          data.put("javaClassName", externalType);
//          data.put("javaFullyQualifiedClassName", model.getAttributes().get("javaExternalPackage") + "." + externalType);
//          data.put("isGenerated", Boolean.FALSE);
//        }
//        else
//        {
//          data.put("javaClassName", model.getCamelCapitalizedName());
//          data.put("javaFullyQualifiedClassName", data.get("javaFacadePackage") + "." + model.getCamelCapitalizedName());
//          data.put("isGenerated", Boolean.TRUE);
//        }
//        // Set javaElementClassName which is the basic Java type which stores simple values.
//        String elementClassName = getJavaClassName(model.getElementSchema());
//        data.put("javaElementClassName", elementClassName);
//        // now set javaFieldClassName and decorator attributes
//        
//        String javaCardinality = getJavaCardinality(model.getCardinality());
//        data.put("javaFieldClassName", javaCardinality + "<" + elementClassName + ">");
//        data.put("javaCardinality", javaCardinality);
//    });
  }

  @Override
  public JavaGeneratorModelContext createModelContext(IModelContext context, IJsonObject<?> generatorConfig)
  {
    return new JavaGeneratorModelContext(this, context, generatorConfig);
  }
}
