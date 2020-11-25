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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;

import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Files;
import com.symphony.oss.canon.json.IParserContext;
import com.symphony.oss.canon.json.ParserContext;
import com.symphony.oss.canon.json.ParserErrorException;
import com.symphony.oss.canon.json.ParserResultException;
import com.symphony.oss.canon.json.SyntaxErrorException;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon2.core.ResolvedArraySchema;
import com.symphony.oss.canon2.core.ResolvedBigDecimalSchema;
import com.symphony.oss.canon2.core.ResolvedBigIntegerSchema;
import com.symphony.oss.canon2.core.ResolvedBooleanSchema;
import com.symphony.oss.canon2.core.ResolvedDoubleSchema;
import com.symphony.oss.canon2.core.ResolvedFloatSchema;
import com.symphony.oss.canon2.core.ResolvedIntegerSchema;
import com.symphony.oss.canon2.core.ResolvedLongSchema;
import com.symphony.oss.canon2.core.ResolvedObjectSchema;
import com.symphony.oss.canon2.core.ResolvedOneOfSchema;
import com.symphony.oss.canon2.core.ResolvedOpenApiObject;
import com.symphony.oss.canon2.core.ResolvedPrimitiveSchema;
import com.symphony.oss.canon2.core.ResolvedProperty;
import com.symphony.oss.canon2.core.ResolvedPropertyContainerSchema;
import com.symphony.oss.canon2.core.ResolvedSchema;
import com.symphony.oss.canon2.core.ResolvedStringSchema;
import com.symphony.oss.canon2.core.SourceContext;
import com.symphony.oss.canon2.model.ArraySchema;
import com.symphony.oss.canon2.model.CanonCardinality;
import com.symphony.oss.canon2.model.CanonGeneratorConfig;
import com.symphony.oss.canon2.model.ObjectSchema;
import com.symphony.oss.canon2.model.OpenApiObject;
import com.symphony.oss.commons.fault.CodingFault;
import com.symphony.oss.commons.fluent.Fluent;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

/**
 * Base class for canon code generators.
 * 
 * @author Bruce Skingle
 *
 * Type parameters define the generator's concrete class for:
 * @param <T> The generic template model.
 * @param <M> The OpenApi (overall model) template model.
 * @param <S> The generic template model for all schemas.
 * @param <O> The template model for object schemas.
 * @param <A> The template model for array schemas
 * @param <P> The template model for all primitive schemas.
 * @param <F> The template model for fields (named schemas).
 * @param <C> The generator context.
 */
public abstract class CanonGenerator<
T extends ITemplateModel<T,M,S>,
M extends IOpenApiTemplateModel<T,M,S>,
S extends ISchemaTemplateModel<T,M,S>,
O extends IObjectSchemaTemplateModel<T,M,S,F>,
A extends IArraySchemaTemplateModel<T,M,S>,
P extends IPrimitiveSchemaTemplateModel<T,M,S>,
F extends IFieldTemplateModel<T,M,S>,
C extends CanonGenerator<T,M,S,O,A,P,F,C>.AbstractContext>
{
  private static Logger log_ = LoggerFactory.getLogger(CanonGenerator.class);
  
  private final String                                   language_;
//  private Map<Class<?>, ICanonDataModelFunc<ModelElement>> dataModelFuncMap_ = new HashMap<>();
  
  private Configuration                                  config_;
  private File                                           templateDir_;

  /**
   * Constructor.
   * 
   * @param language The target generated language.
   */
  public CanonGenerator(String language)
  {
    language_ = language;
    
    config_ = new Configuration(new Version(2, 3, 25));
    
    config_.setDefaultEncoding("UTF-8");
    config_.setLocale(Locale.US);
  }
  
  protected abstract M generateOpenApiObject(C generatorContext, ResolvedOpenApiObject resolvedOpenApiObject);

  protected abstract A generateArraySchema(C generatorContext, M model, ResolvedArraySchema resolvedSchema, CanonCardinality cardinality);

  protected abstract IPathNameConstructor<T> createPathBuilder(AbstractContext generatorContext);

  protected abstract void populateTemplateModel(C generatorContext, Map<String, Object> map);
  
  protected abstract F generateField(C generatorContext, M model, ResolvedProperty resolvedProperty, S typeSchema, boolean required);

  protected abstract O generateObjectSchema(C generatorContext, M model, ResolvedPropertyContainerSchema<?> resolvedSchema);

  protected abstract P generateBigDecimalSchema(C generatorContext, M model, ResolvedBigDecimalSchema resolvedSchema);

  protected abstract P generateBigIntegerSchema(C generatorContext, M model, ResolvedBigIntegerSchema resolvedSchema);

  protected abstract P generateDoubleSchema(C generatorContext, M model, ResolvedDoubleSchema resolvedSchema);

  protected abstract P generateFloatSchema(C generatorContext, M model, ResolvedFloatSchema resolvedSchema);

  protected abstract P generateIntegerSchema(C generatorContext, M model, ResolvedIntegerSchema resolvedSchema);

  protected abstract P generateLongSchema(C generatorContext, M model, ResolvedLongSchema resolvedSchema);

  protected abstract P generateStringSchema(C generatorContext, M model, ResolvedStringSchema resolvedSchema);

  protected abstract P generateBooleanSchema(C generatorContext, M model, ResolvedBooleanSchema resolvedSchema);

  
  
  public @Nullable JsonObject getConfig(C generatorContext)
  {
    return getConfig(generatorContext.getSourceContext().getModel());
  }

  public @Nullable JsonObject getConfig(OpenApiObject model)
  {
    CanonGeneratorConfig generators = model.getXCanonGenerators();
    
    if(generators != null)
    {
      return generators.getJson().getObject(getLanguage());
    }
    
    return null;
  }
  
  public abstract C newGeneratorContext(CanonGenerationContext canonGenerationContext, SourceContext sourceContext) throws ParserResultException;
  
  /**
   * Set the directory from which templates will be loaded.
   * 
   * @param templateDir the directory from which templates will be loaded.
   * 
   * @return this (fluent method).
   */
  public CanonGenerator<T,M,S,O,A,P,F,C> withTemplateDir(File templateDir)
  {
    templateDir_ = templateDir;
    config_.setTemplateLoader(getTemplateLoader());
    return this;
  }

  /**
   * Return the target generated language.
   * 
   * @return the target generated language.
   */
  public String getLanguage()
  {
    return language_;
  }

  /**
   * Return the Freemarker configuration.
   * 
   * @return the Freemarker configuration.
   */
  public Configuration getFreemarkerConfig()
  {
    return config_;
  }

  private TemplateLoader getTemplateLoader()
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
  

  private Set<String> getTemplatesFor(TemplateType type, String templateGroup)
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
  
  /**
   * Map the given name to an identifier name in the generated language.
   * 
   * This method should only do limited mapping of hyphens, underscores and other "punctuation" characters,
   * reserved words in the target language should not be mapped, these will be detected by 
   * isValidIdentifier(String identifier) and should be explicitly remapped via x-canon-identifier.
   * 
   * @param name The proposed name to be used as an identifier in generated code.
   * 
   * @return The mapped value to be used as an identifier in generated code.
   */
  protected abstract String toIdentifier(String name);
  
  /**
   * A generator context.
   * 
   * There is one instance of this class per generator per source context. Each generator provides it's own
   * subclass which is passed to all template model instances.
   * 
   * @author Bruce Skingle
   *
   */
  public abstract class AbstractContext extends Fluent<C>
  {
    private static final String DEFAULT_CANON_ID_STRING = "_";
    
    private final CanonGenerationContext                  generationContext_;
    private final SourceContext                           sourceContext_;
    private final IPathNameConstructor<T>                 pathBuilder_;
    private final Map<String, S>                          schemaModelMap_         = new HashMap<>();
    private final String                                  canonIdString_;
    
    /**
     * Constructor.
     * 
     * @param type                Concrete type for fluent method return value.
     * @param generationContext   Context for the overall generation run.
     * @param sourceContext       Context for the source model being processed.
     */
    public AbstractContext(Class<C> type, CanonGenerationContext generationContext, SourceContext sourceContext)
    {
      super(type);
      
      generationContext_ = generationContext;
      sourceContext_ = sourceContext;
      
      JsonObject config = getConfig(sourceContext.getModel());
      
      if(config == null)
      {
        canonIdString_ = DEFAULT_CANON_ID_STRING;
      }
      else
      {
        canonIdString_ = config.getString("canonIdString", DEFAULT_CANON_ID_STRING);
      }

      pathBuilder_ = createPathBuilder(this);
    }
    
    /**
     * Return the canon ID string.
     * 
     * Identifier names may not begin or end with this string and is used to construct secondary names in generated code.
     * The default value is _.
     * 
     * For example, an entity with the name MyObject might generate files
     *   MyObject.java
     *   I_MyObject.java
     *   MyObject_Entity.java
     *   
     * This prevents name collisions, for example if a model contained entities with the names MyObject and MyObjectEntity
     * then without this mechanism the name MyObjectEntity.java would be generated for both entities in the model.
     * 
     * The value of this string can be overridden in the model spec with canonIdString in the generator config block.
     * 
     * @return the canon ID string.
     */
    public String getCanonIdString()
    {
      return canonIdString_;
    }
    
    CanonGenerator<T,M,S,O,A,P,F,C> getGenerator()
    {
      return CanonGenerator.this;
    }

    IPathNameConstructor<T> getPathBuilder()
    {
      return pathBuilder_;
    }

    public CanonGenerationContext getGenerationContext()
    {
      return generationContext_;
    }

    public SourceContext getSourceContext()
    {
      return sourceContext_;
    }
    
    public String getIdentifierName(ResolvedSchema<?> resolvedSchema, Function<String, Boolean> isValidIdentifier)
    {
      return getIdentifier(resolvedSchema.getName(), resolvedSchema.getSchema().getJson(), isValidIdentifier);
    }
    
    public String getIdentifierName(ResolvedOpenApiObject resolvedOpenApiObject, Function<String, Boolean> isValidIdentifier)
    {
      return getIdentifier(resolvedOpenApiObject.getName(), resolvedOpenApiObject.getJson(), isValidIdentifier);
    }
    
    public String getIdentifier(String name, JsonDomNode jsonDomNode, Function<String, Boolean> isValidIdentifier)
    {
      String        canonIdString = getCanonIdString();
      SourceContext sourceContext = getSourceContext();
      
      if(jsonDomNode instanceof JsonObject)
      {
        JsonObject json = (JsonObject)jsonDomNode;

        // "x-canon-${LANGUAGE}-identifier"
        String attrName = Canon2.X_CANON + getLanguage() + Canon2.NAME_PART_SEPARATOR + Canon2.IDENTIFIER_SUFFIX;
        String identifier = json.getString(attrName, null);
        
        if(identifier != null)
        {
          if(!isValidIdentifier.apply(identifier))
          {
            sourceContext.error(new ParserErrorException("Element " + name + " has the " + getLanguage() + " specific name \"" + identifier + "\" specified by attribute \"" +
                attrName + "\" but that is not a valid Java identifier.", 
                json.get(attrName).getContext()));
          }
          else if(identifier.startsWith(canonIdString) || identifier.endsWith(canonIdString))
          {
            sourceContext.error(new ParserErrorException("Element " + name + " has the " + getLanguage() + " specific name \"" + identifier + "\" specified by attribute \"" +
                attrName + "\" which may not start or end with the canon ID string \"" + canonIdString + "\"", 
                json.get(attrName).getContext()));
          }
          return identifier;
        }
        
        // "x-canon-identifier"
        attrName = Canon2.X_CANON + Canon2.IDENTIFIER_SUFFIX;
        String unMappedId = json.getString(attrName, null);
        identifier = unMappedId == null ? null : toIdentifier(unMappedId);
        
        if(identifier != null)
        {
          if(!isValidIdentifier.apply(identifier))
          {
            sourceContext.error(new ParserErrorException("Element " + name + " has the name \"" + unMappedId + "\" specified by attribute \"" +
                attrName + "\" which mapps to the " + getLanguage() + " name \"" + identifier + 
                    "\" but that is not a valid Java identifier, specify a " + getLanguage() + " specific name for code generation with x-canon-" +
                    getLanguage() + "-identifier.", 
                    json.get(attrName).getContext()));
          }
          else if(identifier.startsWith(canonIdString) || identifier.endsWith(canonIdString))
          {
            sourceContext.error(new ParserErrorException("Element " + name + " has the name \"" + unMappedId + "\" specified by attribute \"" +
                attrName + "\" which mapps to the " + getLanguage() + " name \"" + identifier + "\" which may not start or end with the canon ID string \"" + canonIdString + "\"", 
                json.get(attrName).getContext()));
          }
          
          return identifier;
        }
      }
      
      return getIdentifierFromString(name, jsonDomNode.getContext(), isValidIdentifier);
    }
    
    public String getIdentifierFromString(String name, IParserContext parserContext, Function<String, Boolean> isValidIdentifier)
    {

      String identifier = toIdentifier(name);
      
      if(!isValidIdentifier.apply(identifier))
      {
        getSourceContext().error(new ParserErrorException("\"" + name + "\" is not a valid " + getLanguage() + " identifier, specify a name for code generation with x-canon-" +
            getLanguage() + "-identifier or x-canon-identifier" + ".", 
            parserContext));
      }
      else 
        if(identifier.startsWith(getCanonIdString()) || identifier.endsWith(getCanonIdString()))
      {
          getSourceContext().error(new ParserErrorException("\"" + name +  "\" may not start or end with the canon ID string \"" + getCanonIdString() +
            "\", specify a name for code generation with x-canon-" +
            getLanguage() + "-identifier or x-canon-identifier" + ".", 
            parserContext));
      }
      
      return identifier;
    }
    
//    private Map<ICanonModelEntity, Map<String, String>> schemaNameMap_ = new HashMap<>();
//    private Map<ICanonModelEntity, Map<String, String>> nonSchemaNameMap_ = new HashMap<>();
//    
//    public String getIdentifierName(String name, ICanonModelEntity entity, boolean isSchema)
//    {
//      Map<ICanonModelEntity, Map<String, String>> entityMap = isSchema ? schemaNameMap_ : nonSchemaNameMap_;
//      Map<String, String>                         map = entityMap.get(entity);
//      
//      if(map == null)
//      {
//        map = new HashMap<>();
//        entityMap.put(entity, map);
//      }
//      String                                      identifier = map.get(name);
//      
//      if(identifier == null)
//      {
//        identifier = CanonGenerator.this.getIdentifierName(name, entity, this, isSchema);
//        map.put(name);
//      }
//      
//      return identifier;
//    }

    S generateSchema(ResolvedSchema<?> resolvedSchema, M model)
    {
      S existingSchema = schemaModelMap_.get(resolvedSchema.getUri());
      
      if(existingSchema != null)
        return existingSchema;
      
      if(resolvedSchema instanceof ResolvedObjectSchema)
      {
        return generateObjectSchema((ResolvedObjectSchema)resolvedSchema, model);
      }
      if(resolvedSchema instanceof ResolvedOneOfSchema)
      {
        return generateOneOfSchema((ResolvedOneOfSchema)resolvedSchema, model);
      }
      if(resolvedSchema instanceof ResolvedArraySchema)
      {
        return generateArraySchema((ResolvedArraySchema)resolvedSchema, model);
      }
      if(resolvedSchema instanceof ResolvedPrimitiveSchema)
      {
        return generatePrimitiveSchema((ResolvedPrimitiveSchema<?>)resolvedSchema, model);
      }
      throw new SyntaxErrorException("Invalid schema", resolvedSchema.getSchema().getJson().getContext());
      
//      if(schema.getOneOf() != null)
//      {
//        return generateObjectSchema(resolvedSchema, model,
//            schema);
//        
////        G oneOfSchema = generateGroupSchema(sourceContext_, model, resolvedSchema, SchemaTemplateModelType.ONE_OF);
////        
////        schemaModelMap_.put(resolvedSchema.getUri(), oneOfSchema.asSchemaTemplateModel());
////        
////        for(Entry<String, ResolvedSchema> innerClassEntry : resolvedSchema.getInnerClasses().getResolvedProperties().entrySet())
////        {
////          S innerClass = generateSchema(innerClassEntry.getValue(), model, false);
////          oneOfSchema.addInnerClass(innerClass);
////        }
////        
////        for(ResolvedSchema subSchema : resolvedSchema.getResolvedSubSchemas().getSubSchemas())
////        {
////          S subSchemaModel = generateSchema(subSchema, model, true /* this needs to move into resolved schema */);
////          
////          oneOfSchema.addSubSchema(subSchemaModel);
////        }
////        
////        return oneOfSchema.asSchemaTemplateModel();
//      }
//      else if(schema.getType() != null)
//      {
//        switch(schema.getType())
//        {
//          case "object":
//            return generateObjectSchema(resolvedSchema, model,
//                schema);
//          
//          
//          case "array":
//            CanonCardinality cardinality = schema.getXCanonCardinality();
//            if(cardinality == null)
//            {
//              cardinality = CanonCardinality.LIST; 
//            }
//            
//            A arraySchema = generateArraySchema(sourceContext_, model, resolvedSchema, cardinality);
//            
//            schemaModelMap_.put(resolvedSchema.getUri(), arraySchema.asSchemaTemplateModel());
//            
//            S itemsModel = generateSchema(resolvedSchema.getResolvedItems(), model, resolvedSchema.getSchema().getItemsSchema() == null);
//            
//            arraySchema.setElementType(itemsModel);
//            
//            return arraySchema.asSchemaTemplateModel();
//            
//          case "number":
//          case "boolean":
//          case "string":
//          case "integer":
//            S primitiveSchema = generatePrimativeSchema(sourceContext_, model, resolvedSchema).asSchemaTemplateModel();
//            schemaModelMap_.put(resolvedSchema.getUri(), primitiveSchema);
//            
//            return primitiveSchema;
//            
//          default:
//            throw new CodingFault("Unknown schema type " + schema.getType());
//        }
//      }
//      else
//      {
//        throw new SyntaxErrorException("Invalid schema", resolvedSchema.getSchema().getJson().getContext());
//      }
    }

    private S generatePrimitiveSchema(ResolvedPrimitiveSchema<?> resolvedSchema, M model)
    {
      S primitiveSchema = doGeneratePrimativeSchema(model, resolvedSchema).asSchemaTemplateModel();
      schemaModelMap_.put(resolvedSchema.getUri(), primitiveSchema);
      
      return primitiveSchema;
    }

    private P doGeneratePrimativeSchema(M model,
        ResolvedPrimitiveSchema<?> resolvedSchema)
    {
      if(resolvedSchema instanceof ResolvedBigDecimalSchema)
        return generateBigDecimalSchema(self(), model, (ResolvedBigDecimalSchema)resolvedSchema);
      if(resolvedSchema instanceof ResolvedBigIntegerSchema)
        return generateBigIntegerSchema(self(), model, (ResolvedBigIntegerSchema)resolvedSchema);
      if(resolvedSchema instanceof ResolvedDoubleSchema)
        return generateDoubleSchema(self(), model, (ResolvedDoubleSchema)resolvedSchema);
      if(resolvedSchema instanceof ResolvedFloatSchema)
        return generateFloatSchema(self(), model, (ResolvedFloatSchema)resolvedSchema);
      if(resolvedSchema instanceof ResolvedIntegerSchema)
        return generateIntegerSchema(self(), model, (ResolvedIntegerSchema)resolvedSchema);
      if(resolvedSchema instanceof ResolvedLongSchema)
        return generateLongSchema(self(), model, (ResolvedLongSchema)resolvedSchema);
      if(resolvedSchema instanceof ResolvedStringSchema)
        return generateStringSchema(self(), model, (ResolvedStringSchema)resolvedSchema);
      if(resolvedSchema instanceof ResolvedBooleanSchema)
        return generateBooleanSchema(self(), model, (ResolvedBooleanSchema)resolvedSchema);
      
      throw new CodingFault("Unknown ResolvedPrimitiveSchema subtype " + resolvedSchema.getClass());
    }

    private S generateArraySchema(ResolvedArraySchema resolvedSchema, M model)
    {
      ArraySchema schema = resolvedSchema.getSchema();
      CanonCardinality cardinality = schema.getXCanonCardinality();
      if(cardinality == null)
      {
        cardinality = CanonCardinality.LIST; 
      }
      
      A arraySchema = CanonGenerator.this.generateArraySchema(self(), model, resolvedSchema, cardinality);
      
      schemaModelMap_.put(resolvedSchema.getUri(), arraySchema.asSchemaTemplateModel());
      
      S itemsModel = generateSchema(resolvedSchema.getResolvedItems(), model);
      
      arraySchema.setElementType(itemsModel);
      
      return arraySchema.asSchemaTemplateModel();
    }

    private O generatePropertyContainerSchema(ResolvedPropertyContainerSchema<?> resolvedSchema, M model, Set<String> requiredSet)
    {
      O entity = CanonGenerator.this.generateObjectSchema(self(), model, resolvedSchema);
      schemaModelMap_.put(resolvedSchema.getUri(), entity.asSchemaTemplateModel());
      
      if(!resolvedSchema.getResolvedProperties().isEmpty())
      {
        for(Entry<String, ResolvedProperty> propertyEntry : resolvedSchema.getResolvedProperties().entrySet())
        {
          //String propertyIdentifier = getIdentifierName(propertyEntry.getKey(), propertyEntry.getValue().getSchema(), false);
          ResolvedProperty resolvedPropertySchema = propertyEntry.getValue();
          
          S typeSchema = generateSchema(resolvedPropertySchema.getResolvedSchema(), model);
          boolean required = requiredSet != null && requiredSet.contains(propertyEntry.getKey());
        
          entity.addField(propertyEntry.getKey(),
              generateField(self(), model, resolvedPropertySchema, typeSchema, required));
          
        }
        
        for(Entry<String, ResolvedProperty> innerClassEntry : resolvedSchema.getInnerClasses().getResolvedProperties().entrySet())
        {
          S innerClass = generateSchema(innerClassEntry.getValue().getResolvedSchema(), model);
          entity.addInnerClass(innerClassEntry.getKey(), innerClass);
        }
      }
      
      entity.validate(sourceContext_);
      
      return entity;
    }

    private S generateOneOfSchema(ResolvedOneOfSchema resolvedSchema, M model)
    {
      O entity = generatePropertyContainerSchema(resolvedSchema, model, null);
      
      return entity.asSchemaTemplateModel();
    }

    private S generateObjectSchema(ResolvedObjectSchema resolvedSchema, M model)
    {
      ObjectSchema schema = resolvedSchema.getSchema();
      O entity = generatePropertyContainerSchema(resolvedSchema, model, schema.getRequired());

      if(schema.getXCanonExtends() != null)
      {
        entity.setExtends(generateSchema(resolvedSchema.getResolvedExtends(), model));
      }
      
      if(resolvedSchema.getResolvedAdditionalProperties() != null)
      {
        S additionalPropertiesSchema = generateSchema(resolvedSchema.getResolvedAdditionalProperties(), model);
        
        entity.setAdditionalProperties(additionalPropertiesSchema, resolvedSchema.isAdditionalPropertiesInnerClass());
      }
      else
      {
        entity.setAdditionalPropertiesAllowed(resolvedSchema.isAdditionalPropertiesAllowed());
      }
      
      return entity.asSchemaTemplateModel();
    }

    M process1() throws ParserResultException
    {
      
      //String identifier = getIdentifierName(sourceContext_.getInputSourceName(), sourceContext_.getModel(), true);
      
      M parentModel = generateOpenApiObject(self(), sourceContext_.getResolvedOpenApiObject());
      
      parentModel.validate(sourceContext_);
      
      for(ResolvedProperty resolvedSchema : sourceContext_.getSchemas().values())
      {
        S model = generateSchema(resolvedSchema.getResolvedSchema(), parentModel);
        
        parentModel.addSchema(model);
      }
      

      sourceContext_.printErrorsAndThrowException(false);
    
      //gather(parentModel.asTemplateModel(), templateProcessor);
      
      accept(this, parentModel.asTemplateModel());
      for(S model : schemaModelMap_.values())
      {
        accept(this, model.asTemplateModel());
      }
      
      sourceContext_.printErrorsAndThrowException(true);
      
      return parentModel;
    }

    void populateTemplateModel(Map<String, Object> map)
    {
      CanonGenerator.this.populateTemplateModel(self(), map);
      generationContext_.populateTemplateModel(map);
    }
  }
  
//  /**
//   * Responsible for calling Freemarker.
//   * 
//   * One instance per generator for all source models.
//   * 
//   * @author Bruce Skingle
//   *
//   */
//  class GeneratorTemplateProcessor
//  {
    //private static Logger log_ = LoggerFactory.getLogger(GeneratorTemplateProcessor.class);
    
    private final Map<String, List<TemplateContext>> map_ = new HashMap<>();
    
    CanonGenerator<T,M,S,O,A,P,F,C> getGenerator()
    {
      return CanonGenerator.this;
    }
    
    void process(CanonGenerationContext canonGenerationContext, SourceContext sourceContext) throws ParserResultException
    {
      C generatorContext = newGeneratorContext(canonGenerationContext, sourceContext);
      
      generatorContext.process1();
    }
    
    private class TemplateContext
    {
      private CanonGenerator<T,M,S,O,A,P,F,C>.AbstractContext generatorContext_;
      private T templateModel_;
      
      private TemplateContext(CanonGenerator<T,M,S,O,A,P,F,C>.AbstractContext generatorContext, T templateModel)
      {
        generatorContext_ = generatorContext;
        templateModel_ = templateModel;
      }
    }

    synchronized void accept(CanonGenerator<T,M,S,O,A,P,F,C>.AbstractContext generatorContext, T templateModel)
    {
      if(templateModel.getTemplates() != null)
      {
        for(String template : templateModel.getTemplates())
        {
          List<TemplateContext> list = map_.get(template);
          
          if(list == null)
          {
            list = new LinkedList<>();
            map_.put(template, list);
          }
          
          list.add(new TemplateContext(generatorContext, templateModel));
        }
      }
    }

   
    void generate()
    {
      for(String templateGroup : map_.keySet())
      {
        log_.info("Process template " + templateGroup + "...");
        
        for(TemplateContext modelContext : map_.get(templateGroup))
        {
//          generateModel(modelContext, templateGroup);
          
          log_.info("Process template " + templateGroup + ", model " + modelContext.templateModel_.getName() + "...");
          
          CanonGenerator<T,M,S,O,A,P,F,C>.AbstractContext generatorModelContext = modelContext.generatorContext_;
          CanonGenerator<?,?,?,?,?,?,?,?> generator = generatorModelContext.getGenerator();
          
          for(TemplateType templateType : TemplateType.values())
          {
            Set<String> templateNames = generator.getTemplatesFor(templateType, templateGroup);
            
            for(String templateName : templateNames)
            {
              generate(modelContext, templateType, templateName);
              
            }
          }
        }
      }
    }
    
    private void generate(TemplateContext templateContext,
        TemplateType templateType, String templateName)
    {
      IPathNameConstructor<T> pathBuilder = templateContext.generatorContext_.getPathBuilder();
      Configuration freemarkerConfig = getFreemarkerConfig();
      T entity = templateContext.templateModel_;
      
      log_.debug("Generate generate {} {}", entity.getName(), templateName);
      
      File templateFile = new File(templateName);
      
      //dataModel.put(Canon.TEMPLATE_NAME, templateName);
      
      String  targetFileName = pathBuilder.constructFile(templateFile.getName(), entity);
      
      if(targetFileName != null)
      {
        log_.debug("targetFileName " + targetFileName);
        
        try
        {
          
          Template template = freemarkerConfig.getTemplate(templateName);
    //      TemplateModel model = new TemplateModel(modelContext, templateName,
    //          entity);
          
          generate(newTemplateModel(templateContext.generatorContext_, templateName, templateType, entity), templateType, template, targetFileName, templateContext.generatorContext_.getGenerationContext());
    
        } catch (IOException e)
        {
          throw new ParserErrorException("ERROR processing " + targetFileName + " template " +
              templateName, new ParserContext(templateName), e);
        }
      }
    }
    
    Map<String, Object> newTemplateModel(CanonGenerator<T,M,S,O,A,P,F,C>.AbstractContext generatorContext, String templateName, TemplateType templateType, ITemplateModel<T,M,S> entity)
    {
      Map<String, Object> map = new HashMap<>();
      
      generatorContext.populateTemplateModel(map);
      
      map.put("templateName",  templateName);
      map.put("templateType",  templateType);
      map.put("model",         entity.getModel());
      map.put("entity",        entity);
      map.put("c",             generatorContext.getCanonIdString());
      
      Date now = new Date();
      
      map.put("year",  new SimpleDateFormat("yyyy").format(now));
      map.put("yearMonth",  new SimpleDateFormat("yyyy-MM").format(now));
      map.put("date",  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").format(now));
      map.put("inputSource",  generatorContext.getSourceContext().getInputSource());
      map.put("inputSourceFileName",  generatorContext.getSourceContext().getInputSourceFileName());
      
      return map;
    }
    
    private void generate(Map<String, Object> model, TemplateType templateType, Template template,
        String targetFileName, CanonGenerationContext generationContext)
    {
      File genPath = new File(templateType == TemplateType.TEMPLATE ? generationContext.getTargetDir() : generationContext.getProformaDir(), targetFileName);
      
      genPath.getParentFile().mkdirs();
      
      try(FileWriter writer = new FileWriter(genPath))
      {
        template.process(model, writer);
      } 
      catch (TemplateException | IOException e)
      {
        //dumpMap("", dataModel, new HashSet<Object>());
        
        throw new ParserErrorException(e);
      }
      
      if(genPath.length() == 0L)
      {
        genPath.delete();
      }
      else if(templateType == TemplateType.PROFORMA && generationContext.getCopyDir() != null)
      {
        File copyPath = new File(generationContext.getCopyDir(), targetFileName);
      
        if(copyPath.exists())
        {
          log_.info("Proforma " + copyPath.getAbsolutePath() + " exists, not copying");
        }
        else
        {
          copyPath.getParentFile().mkdirs();
          try
          {
            Files.copy(genPath, copyPath);
          }
          catch (IOException e)
          {
            throw new ParserErrorException(e);
          }
        }
      }
    }
//  }
}
