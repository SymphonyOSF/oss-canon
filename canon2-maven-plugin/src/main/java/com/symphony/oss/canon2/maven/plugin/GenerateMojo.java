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

package com.symphony.oss.canon2.maven.plugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.factory.ArtifactFactory;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.ArtifactNotFoundException;
import org.apache.maven.artifact.resolver.ArtifactResolutionException;
import org.apache.maven.artifact.resolver.ArtifactResolver;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.symphony.oss.canon2.parser.GenerationContext;
import com.symphony.oss.canon2.parser.GenerationException;
import com.symphony.oss.canon2.parser.ICanonGenerator;
import com.symphony.oss.canon2.parser.OpenApiObject;


@Mojo( name = "generate-sources", defaultPhase = LifecyclePhase.GENERATE_SOURCES )
public class GenerateMojo extends AbstractMojo
{
  private static final Logger log_ = LoggerFactory.getLogger(OpenApiObject.class);
  
  private static final String INDENT = "  ";
  private static final String SPEC_SUFFIX = ".json";

  @Parameter( defaultValue = "${project.build.directory}", property = "canonRoot", required = true )
  private File canonRoot;
  
  @Parameter( defaultValue = "${project.build.directory}/generated-sources", property = "targetDir", required = true )
  private File targetDir;
  
  @Parameter( defaultValue = "${project.build.directory}/proforma-sources", property = "proformaTargetDir", required = true )
  private File proformaTargetDir;
  
  @Parameter( property = "proformaCopyDir" )
  private File proformaCopyDir;
  
  @Parameter( property = "uriMapping" )
  private Properties uriMapping;
  
  @Parameter( property = "fileDataModel" )
  private Properties pomDataModel;
  
  @Parameter(defaultValue = "${project}", required = true, readonly = true)
  private MavenProject project;

  @Component
  protected ArtifactFactory factory;

  @Component 
  protected ArtifactResolver artifactResolver;

  @Parameter(defaultValue = "${project.remoteArtifactRepositories}", required = true, readonly = true)
  protected List<ArtifactRepository> remoteRepositories;

  @Parameter(defaultValue = "${localRepository}", required = true, readonly = true)
  protected ArtifactRepository localRepository;

  @Parameter(property = "generatorArtifacts")
  private GeneratorArtifact[]  generatorArtifacts;
  
  @Parameter(defaultValue = "${project.basedir}/src/main/canon", property = "srcDir")
  protected File[] srcDirs;
  
  @Parameter( defaultValue = "false", property = "dumpDataModel", required = true )
  private boolean dumpDataModel;
  
  private File                 canonDir;
  
  @Override
  public void execute() throws MojoExecutionException, MojoFailureException
  {
    if (project.getPackaging() != null && "pom".equals(project.getPackaging().toLowerCase())) {
      getLog().info("Skipping 'pom' packaged project");
      return;
    }
    
    canonDir          = new File(canonRoot, "canon");
    
    log_.info( "Generating sources--------------------------------------------------------------------------");
    log_.info( "targetDir            = " + targetDir);
    log_.info( "proformaTargetDir    = " + proformaTargetDir);
    log_.info( "proformaCopyDir      = " + proformaCopyDir);
    
    for(File srcDir : srcDirs)
    {
      log_.info( "srcDir             = " + srcDir);
    }
    
    for(GeneratorArtifact ta : generatorArtifacts)
    {
      if(!ta.getPrefix().endsWith("/"))
        ta.setPrefix(ta.getPrefix() + "/");
      
      log_.info( "templateArtifactId   = " + ta.getArtifactId());
      log_.info( "templateGroupId      = " + ta.getGroupId());
      log_.info( "templateVersion      = " + ta.getVersion());
      log_.info( "templatePrefix       = " + ta.getPrefix());
    }
    
    
    log_.info( "pomDataModel-------------------------------------------------------------------------------");

    if(pomDataModel != null)
    {
      for(Entry<Object, Object> entry : pomDataModel.entrySet())
      {
        log_.info( entry.getKey() + " = " + entry.getValue());
      }
    }
    log_.info( "--------------------------------------------------------------------------------------------");
    

    
    List<File> srcList = new ArrayList<>();
    
    for(File srcDir : srcDirs)
    {
      addSrcFiles(srcList, srcDir);
    }
    
    if(srcList.isEmpty())
      throw new MojoExecutionException("No sources found");
    
    try
    {
      //ModelSetParserContext modelSetContext = new ModelSetParserContext(new MavenLogFactoryAdaptor(log));
      
      
      
      GenerationContext.Builder builder = new GenerationContext.Builder()
          .withTargetDir(targetDir)
          .withProformaDir(proformaTargetDir)
          .withCopyDir(proformaCopyDir);
      
      Enumeration<?> en = uriMapping.propertyNames();
      while(en.hasMoreElements())
      {
        Object name = en.nextElement();
        Object value = uriMapping.get(name);
        File   path = new File(value.toString());
        
        log_.info( "Map URI             = " + name);
        log_.info( "To                  = " + value);
        log_.info( "Path                = " + path.getAbsolutePath());
        
        builder.withUriMapping(name.toString(), path.getAbsolutePath());
      }
      
      for(GeneratorArtifact ta : generatorArtifacts)
      {
        System.err.println("\n\n\n\n\n\n\n=============================================================================================================================================================\n");
        System.err.println("copyArtefact " + canonDir);
        System.err.println("\n\n\n\n\n\n\n=============================================================================================================================================================\n");
        
        copyArtefact(builder, ta.getGroupId(), ta.getArtifactId(), ta.getVersion(), ta.getPrefix(), null);
      }
      
      GenerationContext generationContext = builder.build();
      
      for(File src : srcList)
      {
        generationContext.addGenerationSource(src);
      }
      
      generationContext.process();
      

      
//      System.err.println("\n\n\n\n\n\n\n=============================================================================================================================================================\n");
//      System.err.println("addTemplateDir " + canonDir);
//      System.err.println("\n\n\n\n\n\n\n=============================================================================================================================================================\n");
//      generationContext.addTemplateDirectory(canonDir);
      
      generationContext.generate();
    }
    catch (GenerationException e)
    {
      throw new MojoExecutionException("Generation failed", e);
    }
  }

  private void addSrcFiles(List<File> srcList, File srcDir)
  {
    File[] files = srcDir.listFiles();
    
    if(files != null)
    {
      for(File f : files)
      {
        if(f.isFile() && f.getName().endsWith(SPEC_SUFFIX))
        {
          srcList.add(f);
        }
        else if(f.isDirectory())
        {
          addSrcFiles(srcList, f);
        }
      }
    }
  }

  private void copyArtefact(GenerationContext.Builder generationContext, String artefactGroupId, String artefactArtifactId, String artefactVersion, String artefactPrefix, String artefactSuffix) throws MojoExecutionException
  {
    try
    {
     Artifact artefact = this.factory.createArtifact(artefactGroupId, artefactArtifactId, 
          artefactVersion, "",
          "jar");

      artifactResolver.resolve(artefact, this.remoteRepositories, this.localRepository);
      
      File artefactFile = artefact.getFile();
      
      log_.debug("Artefact file is " + artefactFile.getAbsolutePath());
      
      File templateCopy = new File(canonDir, artefactGroupId + "." + artefactArtifactId);
      
      templateCopy.mkdirs();
      
      if(artefactFile.isDirectory())
      {
        throw new MojoExecutionException("Template artefact file " + artefactFile +
            " is a directory");
        
//        if(artefactPrefix != null)
//          artefactFile = new File(artefactFile, artefactPrefix);
//        
//        if(artefactFile.isDirectory())
//        {
//          log_.debug("Copy artefacts from " + artefactFile.getAbsolutePath());
//          
//          try
//          {
//            copyFiles(artefactFile, templateCopy, artefactSuffix);
//          }
//          catch (IOException e)
//          {
//            throw new MojoExecutionException("Error copying artefacts from " + artefactFile, e);
//          }
//        }
//        else
//          throw new MojoExecutionException("Error copying artefacts, " + artefactFile +
//              " is not a directory");
      }
      else
      {
        System.err.println("\n\n\n\n\n\n\n=============================================================================================================================================================\n");
        System.err.println("artefactFile " + artefactFile.getAbsolutePath());
        System.err.println("\n\n\n\n\n\n\n=============================================================================================================================================================\n");
        
//        //JarFile jarFile = new JarFile(artefactFile);
//
//        //String generatorName = "com.symphony.oss.canon.generator.typescript.Generator";
//        try
//        {
//          String urlString = "jar:file:" + artefactFile.getPath()+"!/";
//          
//          System.err.println("urlString=" + urlString);
//          
//          URL[] urls = { new URL(urlString) };
//          URLClassLoader cl = URLClassLoader.newInstance(urls, getClass().getClassLoader());
//          
//          JarFile jarFile = new JarFile(artefactFile);
//          Enumeration<JarEntry> e = jarFile.entries();
//
//          while (e.hasMoreElements()) {
//            JarEntry je = e.nextElement();
//            if(je.isDirectory() || !je.getName().endsWith(".class")){
//                continue;
//            }
//            // -6 because of .class
//            String className = je.getName().substring(0,je.getName().length()-6);
//            className = className.replace('/', '.');
//            
//
//            System.out.println("load " + className + "...");
//            
//            
//            try
//            {
//              Class<?> generatorClass = cl.loadClass(className);
//              Object generator = generatorClass.newInstance();
//              
//              if(generator instanceof ICanonGenerator)
//              {
//
//                log_.info("Class " + generatorClass + " IS an ICanonGenerator");
//                generationContext.addGenerator((ICanonGenerator)generator);
//              }
//              else
//              {
//                log_.info("Class " + generatorClass + " is not an ICanonGenerator");
//              }
//            }
//            catch (ClassNotFoundException | InstantiationException | IllegalAccessException e2)
//            {
//              log_.debug("Unable to instantiate class " + className, e2);
//            }
//          }
//        }
//        catch (IOException e)
//        {
//          e.printStackTrace();
//          throw new MojoExecutionException("INTERNAL Error instantiating generator from file "+ artefactFile, e);
//        }
////        catch (ClassNotFoundException e)
////        {
////
////          e.printStackTrace();
////          throw new MojoExecutionException("Error instantiating generator, " + generatorName + " from file "+ artefactFile, e);
////        }
//        
//        
//        
        
        
        
        
        
        
        try(
            FileInputStream in = new FileInputStream(artefactFile);
            JarInputStream  jarIn = new JarInputStream(in);
            )
        {

          String urlString = "jar:file:" + artefactFile.getPath()+"!/";
          
          System.err.println("urlString=" + urlString);
          
          URL[] urls = { new URL(urlString) };
          URLClassLoader cl = URLClassLoader.newInstance(urls, getClass().getClassLoader());
            
          
          JarEntry jarEntry;
          
          while((jarEntry = jarIn.getNextJarEntry()) != null)
          {
            if((artefactPrefix == null || jarEntry.getName().startsWith(artefactPrefix)) &&
                (artefactSuffix == null || jarEntry.getName().endsWith(artefactSuffix)))
            {
              String fileName;
              
              if(artefactPrefix == null)
              {
                fileName = jarEntry.getName();
                
                int i = fileName.lastIndexOf('/');
                
                if(i>0)
                  fileName = fileName.substring(i);
                
              }
              else
              {
                fileName = jarEntry.getName().substring(artefactPrefix.length());
              }
              
              File file = new File(templateCopy, fileName);
              
              if(jarEntry.isDirectory())
              {
                log_.debug("Artefact dir " + jarEntry.getName());
                
                if(!file.exists())
                {
                  log_.debug("Create artefact dir " + file);
                  file.mkdirs();
                }
              }
              else
              {
                log_.debug("Artefact file " + jarEntry.getName());
                
                try(
                    FileOutputStream out = new FileOutputStream(file);
                    )
                {
                  byte[] buf = new byte[1024];
                  int    nbytes;
                  
                  while((nbytes = jarIn.read(buf))>0)
                  {
                    out.write(buf, 0, nbytes);
                  }
                }
              }
            }
            else
            {
              
              
              
              if(jarEntry.getName().endsWith(".class"))
              {
                // -6 because of .class
                String className = jarEntry.getName().substring(0, jarEntry.getName().length()-6);
                className = className.replace('/', '.');
                
  
                log_.debug("load " + className + "...");
                
                
                try
                {
                  Class<?> generatorClass = cl.loadClass(className);
                  Object generator = generatorClass.newInstance();
                  
                  if(generator instanceof ICanonGenerator)
                  {
  
                    log_.info("Class " + generatorClass + " IS an ICanonGenerator");
                    generationContext.withGenerator(((ICanonGenerator)generator).withTemplateDir(templateCopy));
                  }
                  else
                  {
                    log_.info("Class " + generatorClass + " is not an ICanonGenerator");
                  }
                }
                catch (ClassNotFoundException | InstantiationException | IllegalAccessException e2)
                {
                  log_.debug("Unable to instantiate class " + className, e2);
                }
              
              }
              else
              {
                log_.debug("SKIP Artefact file " + jarEntry.getName());
              }
              
              
              
              
              
            }
          }
        } catch (IOException e)
        {
          throw new MojoExecutionException("Error copying artefacts from " + artefactFile, e);
        }
      }
    } catch (ArtifactResolutionException | ArtifactNotFoundException e)
    {
      throw new MojoExecutionException("can't resolve artefact pom", e);
    }
    
  }

//  private void addGenerator(ICanonGenerator generator)
//  {
//    System.err.println("\n\n\n\n\n\n\n=============================================================================================================================================================\n");
//    System.err.println("generator " + generator.hello());
//    System.err.println("\n\n\n\n\n\n\n=============================================================================================================================================================\n");
//
//  }

//  private void copyFiles(File srcDir, File targetDir, String artefactSuffix) throws FileNotFoundException, IOException
//  {
//    log_.debug("Copy artefacts from " + srcDir + " to " + targetDir);
//    
//    if(!targetDir.exists())
//      targetDir.mkdirs();
//    
//    File[] files = srcDir.listFiles();
//    
//    if(files != null)
//    {
//      for(File file : files)
//      {
//        if(file.isDirectory())
//        {
//          File targetSubDir = new File(targetDir, file.getName());
//          
//          if(!targetSubDir.exists())
//            targetSubDir.mkdirs();
//          
//          copyFiles(file, targetSubDir, artefactSuffix);
//        }
//        else
//        {
//          if(artefactSuffix == null || file.getName().endsWith(artefactSuffix))
//          {
//            File targetFile = new File(targetDir, file.getName());
//            
//            log_.debug("Copy artefact " + targetFile);
//            
//            try(
//                FileInputStream in = new FileInputStream(file);
//                FileOutputStream  out = new FileOutputStream(targetFile);
//                )
//            {
//              byte[] buf = new byte[1024];
//              int    nbytes;
//              
//              while((nbytes = in.read(buf))>0)
//              {
//                out.write(buf, 0, nbytes);
//              }
//            }
//          }
//        }
//      }
//    }
//  }

  private void dumpMap(String indent, Map<?, ?> map, Set<Object> visitSet) throws MojoExecutionException
  {
    for(Entry<?, ?> entry : map.entrySet())
    {
      dump(indent, entry.getKey(), entry.getValue(), visitSet);
    }
  }
  
  private void dumpCollection(String indent, Collection<?> collection, Set<Object> visitSet) throws MojoExecutionException
  {
    int i=0;
    
    for(Object value : collection)
    {
      dump(indent, "[" + i + "]", value, visitSet);
      i++;
    }
  }

  private void dump(String indent, Object name, Object value, Set<Object> visitSet) throws MojoExecutionException
  {
    if(value == null)
    {
      log_.info(indent + name + " = NULL");
      return;
    }
    
    if(value instanceof String 
        || value instanceof Number 
        || value instanceof Date
        || value instanceof Class
        || value instanceof Boolean)
    {
      log_.info(indent + name + " = " + value);
      return;
    }
    
    if(value instanceof Map && ((Map<?,?>)value).isEmpty())
    {
      log_.info(indent + name + " = EMPTY MAP");
      return;
    }
    
    if(value instanceof Collection && ((Collection<?>)value).isEmpty())
    {
      log_.info(indent + name + " = EMPTY LIST");
      return;
    }
    
    if(visitSet.contains(value))
    {
      log_.info(indent + name + " RECURSION");
      return;
    }
    
    visitSet.add(value);
    
    if(value instanceof Map)
    {
      log_.info(indent + name + " = MAP");
      dumpMap(indent + INDENT, (Map<?,?>)value, visitSet);
      return;
    }
    
    if(value instanceof Collection)
    {
      log_.info(indent + name + " = LIST");
      dumpCollection(indent + INDENT, (Collection<?>)value, visitSet);
      return;
    }
    
    log_.info(indent + name + " = OBJECT of type " + value.getClass());
    dumpObject(indent + INDENT, value, visitSet);
  }

  private void dumpObject(String indent, Object v, Set<Object> visitSet) throws MojoExecutionException
  {
    for(Method m : v.getClass().getMethods())
    {
      if(m.getDeclaringClass() != Object.class &&
          !Modifier.isStatic(m.getModifiers()) &&
          !Modifier.isAbstract(m.getModifiers()) &&
          m.getName().startsWith("get") && m.getParameterTypes().length == 0)
      {
          Object value;
          
          try
          {
            value =  m.invoke(v);
          }
          catch(Exception e)
          {
            value = e.toString();
          }
          dump(indent, m.getName().substring(3,4).toLowerCase() + m.getName().substring(4), value, visitSet);
      }
    }
  }
}
