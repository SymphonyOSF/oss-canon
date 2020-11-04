/*
 *
 *
 * Copyright 2017, 2020 Symphony Communication Services, LLC.
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.symphony.oss.canon.json.ParserResultException;

public class Canon2
{
  private static Logger log_ = LoggerFactory.getLogger(Canon2.class);
  
  /* General Constants */
  public static final String    TEMPLATE              = "template";
  public static final String    PROFORMA              = "proforma";

  /* JSON Constants */
  public static final String    X_CANON                = "x-canon-";

  public static final String IDENTIFIER_SUFFIX = "-identifier";
  
//  public static final String    X_MODEL               = "model";
//  public static final String    X_ID                  = "id";
//  public static final String    VERSION               = "version";
//  public static final String    X_ATTRIBUTES          = "x-canon-attributes";
//  public static final String    X_CARDINALITY         = "x-canon-cardinality";
//  public static final String    X_CARDINALITY_LIST    = "LIST";
//  public static final String    X_CARDINALITY_SET     = "SET";
//  public static final String    EXTENDS               = "extends";
//  public static final String    FACADE                = "facade";
//  public static final String    BUILDER_FACADE        = "builderFacade";
//  public static final String    PROPERTY_NAME         = "propertyName";
//  public static final String    MAPPING               = "mapping";
//  public static final String    ENUM                  = "enum";
//
//  public static final String    MODEL_NAME            = "modelName";
////  public static final String    JAVA_EXTERNAL_PACKAGE = "javaExternalPackage";
////  public static final String    JAVA_EXTERNAL_TYPE    = "javaExternalType";
//  public static final String    IS_DIRECT_EXTERNAL    = "isDirectExternal";
//
//  /* Root property names in the template data model */
//
//  public static final String    MODEL                 = "model";
//
//  public static final String    JAVA_GEN_PACKAGE      = "javaGenPackage";
//  public static final String    JAVA_FACADE_PACKAGE   = "javaFacadePackage";
//
//  public static final String    YEAR                  = "year";
//  public static final String    YEAR_MONTH            = "yearMonth";
//  public static final String    DATE                  = "date";
//  public static final String    INPUT_SOURCE          = "inputSource";
//  public static final String    PATH_TO_FACADE        = "pathToFacade";
//  public static final String    PATH_TO_GEN           = "pathToGen";
//
//  public static final String    IS_FACADE             = "isFacade";
//  public static final String    TEMPLATE_NAME         = "templateName";
//  public static final String    TEMPLATE_DEBUG        = "templateDebug";
//  public static final String    PATHS                 = "paths";
//  public static final String    METHODS               = "methods";
//  public static final String    DOLLAR_REF            = "$ref";
//  public static final String    PARAMETER_SETS        = "parameterSets";
//  public static final String    SCHEMAS               = "schemas";
//  public static final String    PARAMETERS            = "parameters";
//  public static final String    SCHEMA                = "schema";
//  public static final String    X_BASE_PATH           = "basePath";

  private boolean               verbose_              = false;
  private String                sourceDir_            = "src/main/canon";
  private String                generationTarget_     = "target/generated-sources";
  private String                proformaTarget_       = "target/proforma-sources";
  private String                outputDir_            = ".";
  private List<String>          fileNames_            = new ArrayList<>();
  private List<String>          errors_               = new ArrayList<>();
  private Map<String, String>   uriMap_               = new HashMap<>();
  private List<ICanonGenerator> generators_           = new ArrayList<>();
  private String                copyright_;
  private String                license_;

//  /**
//   * Launcher.
//   * 
//   * @param argv
//   *          command line arguments.
//   * @throws CanonException
//   *           If anything goes wrong.
//   */
//  public static void main(String[] argv) throws CanonException
//  {
//    new Canon().run(argv);
//  }

  protected void run(String[] argv) throws ParserResultException
  {
    int i = 0;

    while (i < argv.length)
    {
      if (argv[i].startsWith("--"))
      {
        switch (argv[i].substring(2))
        {
          case "verbose":
            verbose_ = true;
            break;

          case "generator":
            i++;
            if (i < argv.length)
              loadGenerator(argv[i]);
            else
              error("--generator requires className:directoryName name to follow.");
            break;

          case "copyright":
            i++;
            if (i < argv.length)
              copyright_ = argv[i];
            else
              error("--copyright requires a copyright holder to follow.");
            break;

          case "license":
            i++;
            if (i < argv.length)
              license_ = argv[i];
            else
              error("--license requires a license to follow.");
            break;
          
          case "sourceDir":
            i++;
            if (i < argv.length)
              sourceDir_ = argv[i];
            else
              error("--sourceDir requires a directory name to follow.");
            break;

          case "outputDir":
            i++;
            if (i < argv.length)
              outputDir_ = argv[i];
            else
              error("--outputDir requires a directory name to follow.");
            break;
            
          case "mapUri":
            i++;
            if (i < argv.length)
              mapUri(argv[i]);
            else
              error("--mapUri requires a name=value mapping to follow.");
            break;

          default:
            error("Unrecognized flag \"%s\".", argv[i]);
        }
      }
      else if (argv[i].startsWith("-"))
      {
        switch (argv[i].substring(1))
        {
          case "v":
            verbose_ = true;
            break;

          case "g":
            i++;
            if (i < argv.length)
              loadGenerator(argv[i]);
            else
              error("-g requires className:directoryName name to follow.");
            break;

          case "c":
            i++;
            if (i < argv.length)
              copyright_ = argv[i];
            else
              error("-c requires a copyright holder to follow.");
            break;

          case "l":
            i++;
            if (i < argv.length)
              license_ = argv[i];
            else
              error("-c requires a license to follow.");
            break;

          case "s":
            i++;
            if (i < argv.length)
              sourceDir_ = argv[i];
            else
              error("-s requires a directory name to follow.");
            break;

          case "o":
            i++;
            if (i < argv.length)
              outputDir_ = argv[i];
            else
              error("-o requires a directory name to follow.");
            break;
            
          case "m":
            i++;
            if (i < argv.length)
              mapUri(argv[i]);
            else
              error("-, requires a name=value mapping to follow.");
            break;

          default:
            error("Unrecognized flag \"%s\".", argv[i]);
        }
      }
      else
      {
        fileNames_.add(argv[i]);
      }

      i++;
    }

    File src = new File(sourceDir_);
    List<File> files = new ArrayList<>();

    if (fileNames_.isEmpty())
    {
      File[] fileList = src.listFiles();

      if (fileList == null)
      {
        error("Source directory \"%s\" does not exist", sourceDir_);
      }
      else
      {
        for (File f : fileList)
        {
          if (f.getName().endsWith(".json"))
            files.add(f);
        }

        if (files.isEmpty())
        {
          error("No source files found in %s", src.getAbsolutePath());
        }
      }
    }
    else
    {
      for (String fileName : fileNames_)
      {
        File f = new File(fileName);

        if (f.isAbsolute())
          files.add(f);
        else
          files.add(new File(src, fileName));
      }
    }

    for (File f : files)
    {
      if (!f.exists())
      {
        error("File \"%s\" does not exist.", f.getAbsolutePath());
      }
      else if (!f.isFile())
      {
        error("\"%s\" is not a file", f.getAbsolutePath());
      }
      else if (!f.canRead())
      {
        error("File \"%s\" is not readable", f.getAbsolutePath());
      }
    }

    if (generators_.isEmpty())
    {
      error("No template directories specified");
    }

    if (errors_.isEmpty())
    {
      execute(files);
    }
    else
    {
      for (String e : errors_)
      {
        System.err.println(e);
      }
      System.err.println("Aborted.");
    }
  }

  private void loadGenerator(String spec)
  {
    String[] parts = spec.split(":");
    
    if(parts.length != 2)
      error("-g/--generator requires className:directoryName name to follow.");
    
    try
    {
      Class<?> generatorClass = Class.forName(parts[0]);
      ICanonGenerator<?,?,?,?,?,?,?,?> generator = (ICanonGenerator<?,?,?,?,?,?,?,?>)generatorClass.newInstance();
      File f = new File(parts[1]);
      
      if (!f.exists())
      {
        error("Template directory \"%s\" does not exist.", f.getAbsolutePath());
      }
      else if (!f.isDirectory())
      {
        error("\"%s\" is not a directory", f.getAbsolutePath());
      }
      else if (!f.canRead())
      {
        error("Template directory \"%s\" is not readable", f.getAbsolutePath());
      }
      
      generators_.add(generator.withTemplateDir(f));
    }
    catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ClassCastException e)
    {
      e.printStackTrace();
      error("Unable to load generator spec " + spec);
    }
  }

  private void mapUri(String mapping)
  {
    int i = mapping.indexOf('=');
    
    if(i<1)
      error("Mappings must be of the form src=target");
    else
    {
      uriMap_.put(mapping.substring(0, i), mapping.substring(i+1));
    }
  }

  private void execute(List<File> files) throws ParserResultException
  {
    if (verbose_)
    {
      System.out.printf("Canon\n");
      System.out.printf("verbose:          %s\n", verbose_);
      System.out.printf("sourceDir:        %s\n", sourceDir_);
      System.out.printf("generationTarget: %s\n", generationTarget_);
      System.out.printf("proformaTarget:   %s\n", proformaTarget_);
      System.out.printf("inputFiles:\n");
      for (File f : files)
        System.out.println(f.getAbsolutePath());
    }
    
    CanonGenerationContext.Builder builder = new CanonGenerationContext.Builder()
        .withTargetDir(outputDir_ + "/target/generated-sources")
        .withProformaDir(outputDir_ + "/target/proforma-sources")
        .withCopyDir(outputDir_ + "/target/proforma-copy")
        .withCopyright(copyright_)
        .withLicense(license_)
        .withUriMappings(uriMap_)
        ;
    
    
    for(ICanonGenerator<?,?,?,?,?,?,?,?> generator : generators_)
      builder.withGenerator(generator);
    
    if (verbose_)
    {
      builder.withTemplateDebug(true);
    }

    
    CanonGenerationContext generationContext = builder.build();
    
    for (File f : files)
      generationContext.addGenerationSource(f);

    generationContext.process();
  }

  private void error(String format, Object... args)
  {
    errors_.add(String.format(format, args));
  }

//  private static void visit(IndentedWriter out, Entity model)
//  {
//    out.openBlock(model.toString());
//
//    for (Entity child : model.getChildren())
//      visit(out, child);
//
//    out.closeBlock();
//  }
}
