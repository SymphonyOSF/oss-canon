<#if entity.superType??>
<#assign superClassName = entity.superType.type/>
<#else>
<#assign superClassName = "ObjectEntity">
</#if>
<#include "/copyrightHeader.ftl"/>
<#include "/macros.ftl"/>
<#assign imports = entity.imports + [
  "javax.annotation.concurrent.Immutable",
  "javax.annotation.Nullable",
  "com.google.common.collect.ImmutableSet",
  "java.util.Set",
  "java.util.HashSet",
  "com.symphony.oss.canon2.runtime.java.ModelRegistry",
  "com.symphony.oss.canon2.runtime.java.ObjectEntity",
  "com.symphony.oss.canon.json.model.JsonObject"
  ]>
<#macro importType schema>
  <#assign imports = imports + [
    "com.symphony.oss.canon.json.model.JsonDomNode",
    "${schema.fullyQualifiedJsonNodeType}"
  ]>
  <#if schema.externalType??>
    <#assign imports = imports + [
      "${schema.externalPackage}.${schema.externalType}"
    ]>
  </#if>
</#macro>
<#macro importArrayJsonType schema>
  <#switch schema.schemaType>
    <#case "ARRAY">
      <#if schema.cardinality == "LIST">
        <#assign imports = imports + [
          "java.util.List",
          "java.util.LinkedList",
          "com.google.common.collect.ImmutableList"
        ]>
      </#if>
      <@importArrayJsonType schema.elementType />
      <#break>
    <#case "OBJECT">
      <#break>
    <#default>
    <@importType schema />
  </#switch>
</#macro>
<#list entity.fields as field>
  <@importType field.typeSchema />
  <#if field.typeSchema.schemaType == "ARRAY">
    <@importArrayJsonType field.typeSchema />
  </#if>
</#list>

package ${genPackage};

<#list entity.sortImports(imports) as import>
${import}
</#list>
<#macro generateObject indent entity className superClassName classModifier>
${indent}/**
${indent} * Implementation for Object  ${entity.name} ${model.name}
${indent} * Object ${model}
<#if entity.summary??>
${indent} *
${indent} * ${entity.summary}
</#if>
<#if entity.description??>
${indent} *
<#list entity.description as description>
${indent} * ${description}
</#list>
</#if>
${indent} * Generated from ${entity} at {entity.context.path}
${indent} */
${indent}@Immutable
${indent}public ${classModifier}class ${className} extends ${superClassName}
${indent}{
${indent}  /** Type ID */
${indent}  public static final String  TYPE_ID = "${model.canonId}.${entity.name}";
${indent}  /** Type version */
${indent}  public static final String  TYPE_VERSION = "${model.canonVersion}";
${indent}  /** Type major version */
${indent}  public static final Integer TYPE_MAJOR_VERSION = ${model.canonMajorVersion};
${indent}  /** Type minor version */
${indent}  public static final Integer TYPE_MINOR_VERSION = ${model.canonMinorVersion};
${indent}  /** Factory instance */
${indent}  public static final Factory FACTORY = new Factory();
${indent}  
${indent}  private final ${"ImmutableSet<String>"?right_pad(25)}   unknownKeys_;
<#list entity.fields as field>
${indent}  // field ${field} field.typeSchema.name=${field.typeSchema.name}
${indent}  private final ${field.type?right_pad(25)}  _${field.camelName}_;
</#list>
${indent}
${indent}  /**
${indent}   * Constructor from builder.
${indent}   * 
${indent}   * @param builder A mutable builder containing all values.
${indent}   */
${indent}  public ${className}(AbstractBuilder<?,?> builder)
${indent}  {
${indent}    super(builder);
${indent}    
<#list entity.fields as field>
${indent}    _${field.camelName}_ = ${field.typeSchema.copyPrefix}builder.get${field.camelCapitalizedName}()${field.typeSchema.copySuffix};
    <@checkFieldLimits "    " field "_${field.camelName}_"/>
<#-- <#if field.typeSchema.minimum??>
  <#if field.typeSchema.exclusiveMinimum>
${indent}    if(_${field.camelName}_ != null && _${field.camelName}_ <= ${field.typeSchema.minimum})
${indent}      throw new IllegalArgumentException("Value " +_${field.camelName}_ + " of _${field.camelName}_ is less than or equal to the exclusive minimum allowed of ${field.typeSchema.minimum}");
  <#else>
${indent}    if(_${field.camelName}_ != null && _${field.camelName}_ < ${field.typeSchema.minimum})
${indent}      throw new IllegalArgumentException("Value " +_${field.camelName}_ + " of _${field.camelName}_ is less than the minimum allowed of ${field.typeSchema.minimum}");
  </#if>
</#if>
<#if field.typeSchema.maximum??>
  <#if field.typeSchema.exclusiveMaximum>
${indent}    if(_${field.camelName}_ != null && _${field.camelName}_ >= ${field.typeSchema.maximum})
${indent}      throw new IllegalArgumentException("Value " +_${field.camelName}_ + " of _${field.camelName}_ is greater than or equal to the exclusive maximum allowed of ${field.typeSchema.maximum}");
  <#else>
${indent}    if(_${field.camelName}_ != null && _${field.camelName}_ > ${field.typeSchema.maximum})
${indent}      throw new IllegalArgumentException("Value " +_${field.camelName}_ + " of _${field.camelName}_ is greater than the maximum allowed of ${field.typeSchema.maximum}");
  </#if>
${indent}            
</#if>
<#if field.required>
${indent}    if(_${field.camelName}_ == null)
${indent}      throw new IllegalArgumentException("${field.camelName} is required.");
${indent}      
</#if> -->
</#list>
${indent}    unknownKeys_ = ImmutableSet.of();
${indent}  }
<#-- 
${indent}  /**
${indent}   * Set the _type attribute of the given mutable JSON object to the type ID of this type if it is null and
${indent}   * return an immutable copy.
${indent}   *
${indent}   * @param mutableJsonObject A mutable JSON Object.
${indent}   *
${indent}   * @return An immutable copy of the given object with the _type attribute set.
${indent}   */
${indent}  public static ImmutableJsonObject setType(MutableJsonObject mutableJsonObject)
${indent}  {
${indent}    if(mutableJsonObject.get(CanonRuntime.JSON_TYPE) == null)
${indent}      mutableJsonObject.addIfNotNull(CanonRuntime.JSON_TYPE, TYPE_ID);
${indent}    
${indent}    return mutableJsonObject.immutify();
${indent}  }
${indent}  
${indent}  /**
${indent}   * Constructor from mutable JSON object.
${indent}   * 
${indent}   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
${indent}   * @param modelRegistry A model registry to use to deserialize any nested objects.
${indent}   */
${indent}  public ${className}(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
${indent}  {
${indent}    this(setType(mutableJsonObject), modelRegistry);
${indent}  }
${indent}  
${indent}
${indent} -->
${indent}   
${indent}  /**
${indent}   * Constructor from serialised form.
${indent}   * 
${indent}   * @param jsonObject An immutable JSON object containing the serialized form of the object.
${indent}   * @param modelRegistry A model registry to use to deserialize any nested objects.
${indent}   */
${indent}  public ${className}(JsonObject jsonObject, ModelRegistry modelRegistry)
${indent}  {
${indent}    super(jsonObject);
${indent}  
${indent}    Set<String> keySet = new HashSet<>(super.getCanonUnknownKeys());
${indent}    
<#list entity.fields as field>
${indent}    if(keySet.remove("${field.quotedName}"))
${indent}    {
${indent}      JsonDomNode  node = jsonObject.get("${field.quotedName}");
  <@generateCreateFieldFromJsonDomNode "      " "node" field.typeSchema field.quotedName "_${field.camelName}_" ""/>
${indent}    }
${indent}    else
${indent}    {
  <#if field.required>
${indent}      throw new IllegalArgumentException("${field.name} is required.");
  <#else>
${indent}      _${field.camelName}_ = null;
  </#if>
${indent}    }
</#list>
${indent}
${indent}    unknownKeys_ = ImmutableSet.copyOf(keySet);
${indent}  }
${indent}   
${indent}  /**
${indent}   * Copy constructor.
${indent}   * 
${indent}   * @param other Another instance from which all attributes are to be copied.
${indent}   */
${indent}  public ${className}(${entity.type} other)
${indent}  {
${indent}    super(other);
${indent}    
<#list entity.fields as field>
${indent}    _${field.camelName}_ = other.get${field.camelCapitalizedName}();
</#list>
${indent}
${indent}    unknownKeys_ = other.getCanonUnknownKeys();
${indent}  }
${indent}  
${indent}  @Override
${indent}  public ImmutableSet<String> getCanonUnknownKeys()
${indent}  {
${indent}    return unknownKeys_;
${indent}  }
<#list entity.fields as field>
${indent}  
${indent}  /**
${indent}   * Return the value of the ${field.name} attribute.
${indent}   *
  <#if field.typeSchema.description??>
${indent}   * ${field.typeSchema.description}
${indent}   *
  </#if>
${indent}   * @return the value of the ${field.name} attribute.
${indent}   */
${indent}  public ${field.type} get${field.camelCapitalizedName}()
${indent}  {
${indent}    return _${field.camelName}_;
${indent}  }    
</#list>
${indent}
${indent}  @Override
${indent}  public boolean equals(Object obj)
${indent}  {
${indent}    if(obj instanceof ${className})
${indent}      return toString().equals(((${className})obj).toString());
${indent}    
${indent}    return false;
${indent}  }
${indent}
${indent}  @Override
${indent}  public int hashCode()
${indent}  {
${indent}    return toString().hashCode();
${indent}  }
${indent}
<#------------------------------------------------------------------------------------------------------------------------------
${indent}
${indent} Factory
${indent} 
${indent}-------------------------------------------------------------------------------------------------------------------------------> 
${indent}  
${indent}  /**
${indent}   * Factory class for ${entity.type}.
${indent}   */
${indent}  public static class Factory extends ${superClassName}.Factory<${className}>
${indent}  {
${indent}    @Override
${indent}    public String getCanonType()
${indent}    {
${indent}      return TYPE_ID;
${indent}    }
${indent}    
${indent}    /**
${indent}     * Return the type version (_version JSON attribute) for entities created by this factory.
${indent}     * 
${indent}     * @return The type version for entities created by this factory.
${indent}     */
${indent}    public String getCanonVersion()
${indent}    {
${indent}      return TYPE_VERSION;
${indent}    }
${indent}    
${indent}    /**
${indent}     * Return the major type version for entities created by this factory.
${indent}     * 
${indent}     * @return The major type version for entities created by this factory.
${indent}     */
${indent}    public @Nullable Integer getCanonMajorVersion()
${indent}    {
${indent}      return TYPE_MAJOR_VERSION;
${indent}    }
${indent}    
${indent}    /**
${indent}     * Return the minjor type version for entities created by this factory.
${indent}     * 
${indent}     * @return The minor type version for entities created by this factory.
${indent}     */
${indent}    public @Nullable Integer getCanonMinorVersion()
${indent}    {
${indent}      return TYPE_MINOR_VERSION;
${indent}    }
${indent}    
${indent}    @Override
${indent}    public ${entity.type} newInstance(JsonObject jsonObject, ModelRegistry modelRegistry)
${indent}    {
${indent}      return new ${entity.type}(jsonObject, modelRegistry);
${indent}    }
<#-------------
<#if entity.superSchema??>
<#else>
${indent}    // We can't extend a parameterized super type here because the further sub-classing by users does not work
${indent}
${indent}    /**
${indent}     * Return a list of new entity instances created from the given JSON array.
${indent}     * 
${indent}     * @param jsonArray An array of the JSON serialized form of the required entity.
${indent}     * 
${indent}     * @return A list of instances of the entity represented by the given serialized form.
${indent}     * 
${indent}     * @throws IllegalArgumentException If the given JSON is not valid.
${indent}     */
${indent}    public List<I${entity.type}> newMutableList(JsonArray<?> jsonArray)
${indent}    {
${indent}      List<I${entity.type}> list = new LinkedList<>();
${indent}      
${indent}      for(JsonDomNode node : jsonArray)
${indent}      {
${indent}        if(node instanceof JsonObject)
${indent}          list.add(newInstance((ImmutableJsonObject) node));
${indent}        else
${indent}          throw new IllegalArgumentException("Expected an array of JSON objectcs, but encountered a " + node.getClass().getName());
${indent}      }
${indent}      
${indent}      return list;
${indent}    }
${indent}  
${indent}    /**
${indent}     * Return a set of new entity instances created from the given JSON array.
${indent}     * 
${indent}     * @param jsonArray An array of the JSON serialized form of the required entity.
${indent}     * 
${indent}     * @return A set of instances of the entity represented by the given serialized form.
${indent}     * 
${indent}     * @throws IllegalArgumentException If the given JSON is not valid.
${indent}     */
${indent}    public Set<I${entity.type}> newMutableSet(JsonArray<?> jsonArray)
${indent}    {
${indent}      Set<I${entity.type}> list = new HashSet<>();
${indent}      
${indent}      for(JsonDomNode node : jsonArray)
${indent}      {
${indent}        if(node instanceof JsonObject)
${indent}        {
${indent}          list.add(newInstance((ImmutableJsonObject) node.immutify()));
${indent}        }
${indent}        else
${indent}        {
${indent}          throw new IllegalArgumentException("Expected an array of JSON objectcs, but encountered a " + node.getClass().getName());
${indent}        }
${indent}      }
${indent}      
${indent}      return list;
${indent}    }
${indent}  
${indent}    /**
${indent}     * Return a list of new entity instances created from the given JSON array.
${indent}     * 
${indent}     * @param jsonArray An array of the JSON serialized form of the required entity.
${indent}     * 
${indent}     * @return A list of instances of the entity represented by the given serialized form.
${indent}     * 
${indent}     * @throws IllegalArgumentException If the given JSON is not valid.
${indent}     */
${indent}    public ImmutableList<E> newImmutableList(JsonArray<?> jsonArray)
${indent}    {
${indent}      return ImmutableList.copyOf(newMutableList(jsonArray));
${indent}    }
${indent}  
${indent}    /**
${indent}     * Return a set of new entity instances created from the given JSON array.
${indent}     * 
${indent}     * @param jsonArray An array of the JSON serialized form of the required entity.
${indent}     * 
${indent}     * @return A set of instances of the entity represented by the given serialized form.
${indent}     * 
${indent}     * @throws IllegalArgumentException If the given JSON is not valid.
${indent}     */
${indent}    public ImmutableSet<E> newImmutableSet(JsonArray<?> jsonArray)
${indent}    {
${indent}      return ImmutableSet.copyOf(newMutableSet(jsonArray));
${indent}    }
</#if>
${indent}---->
${indent}  }
${indent} 
${indent}  
<#------------------------------------------------------------------------------------------------------------------------------
${indent}
${indent} Builder
${indent} 
${indent}------------------------------------------------------------------------------------------------------------------------------->
${indent}   
${indent}  /**
${indent}   * Builder for ${entity.type}
${indent}   */
${indent}  public static class Builder extends ${entity.type}.AbstractBuilder<Builder, ${entity.type}>
${indent}  {
${indent}    /**
${indent}     * Constructor.
${indent}     */
${indent}    public Builder()
${indent}    {
${indent}      super(Builder.class);
${indent}    }
${indent}
${indent}    /**
${indent}     * Constructor initialised from another object instance.
${indent}     * 
${indent}     * @param initial An instance of the built type from which values are to be initialised.
${indent}     */
${indent}    public Builder(${entity.type} initial)
${indent}    {
${indent}      super(Builder.class, initial);
${indent}    }
${indent}
${indent}    @Override
${indent}    protected ${entity.type} construct()
${indent}    {
${indent}      return new ${entity.type}(this);
${indent}    }
${indent}  }
${indent}  
<#------------------------------------------------------------------------------------------------------------------------------
${indent}
${indent} AbstractBuilder
${indent} 
${indent}-------------------------------------------------------------------------------------------------------------------------------> 
${indent}  
${indent}  /**
${indent}   * Abstract builder for ${entity.type}. If there are sub-classes of this type then their builders sub-class this builder.
${indent}   *
${indent}   * @param <T> The concrete type of the builder, used for fluent methods.
${indent}   * @param <B> The concrete type of the built object.
${indent}   */
${indent}  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends ${className}>
${indent}    extends ${superClassName}.AbstractBuilder<T,B>
${indent}  {
  <#list entity.fields as field>
${indent}    protected ${field.type?right_pad(25)}  _${field.camelName}_${field.typeSchema.builderTypeNew};
  </#list>
${indent}  
${indent}    protected AbstractBuilder(Class<T> type)
${indent}    {
${indent}      super(type);
${indent}    }
${indent}    
${indent}    protected AbstractBuilder(Class<T> type, B initial)
${indent}    {
${indent}      super(type, initial);
${indent}      
  <#list entity.fields as field>
${indent}      _${field.camelName}_ = ${field.typeSchema.copyPrefix}initial.get${field.camelCapitalizedName}()${field.typeSchema.copySuffix};
  </#list>
${indent}    }
${indent}    
${indent}    @Override
${indent}    public T withValues(JsonObject jsonObject, ModelRegistry modelRegistry)
${indent}    {
<#if entity.superSchema??>
${indent}      super.withValues(jsonObject, ignoreValidation);
</#if>    
<#list entity.fields as field>
${indent}      if(jsonObject.containsKey("${field.quotedName}"))
${indent}      {
${indent}        JsonDomNode  node = jsonObject.get("${field.quotedName}");
  <@generateCreateFieldFromJsonDomNode "        " "node" field.typeSchema field.quotedName "_${field.camelName}_" "if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())"/>
${indent}      }
</#list>
${indent}      return super.withValues(jsonObject, modelRegistry);
${indent}    }
${indent}    
${indent}    /* void populateAllFields(List<Object> result)
${indent}    {
<#if entity.superSchema??>
${indent}      super.populateAllFields(result);
</#if>    
<#list entity.fields as field>
${indent}      result.add(_${field.camelName}_);
</#list>
${indent}    }*/

  <#list entity.fields as field>
${indent}    /**
${indent}     * Return the value of the ${field.name} attribute.
${indent}     *
    <#if field.typeSchema.description??>
${indent}     * ${field.typeSchema.description}
${indent}     *
    </#if>
${indent}     * @return the value of the ${field.name} attribute.
${indent}     */
${indent}    public ${field.type} get${field.camelCapitalizedName}()
${indent}    {
${indent}      return _${field.camelName}_;
${indent}    }
${indent}
${indent}    /**
${indent}     * Set the value of the ${field.name} attribute.
${indent}     *
${indent}     * @param value The value to be set. 
${indent}     *
${indent}     * @return This (fluent method).
${indent}     */
${indent}     // base type ${field.typeSchema.type}
${indent}     // base name ${field.typeSchema.name}
${indent}    public T with${field.camelCapitalizedName}(${field.typeSchema.type} value)
${indent}    {
    <@checkFieldLimits "        " field "value"/>
${indent}      _${field.camelName}_ = ${field.typeSchema.copyPrefix}value${field.typeSchema.copySuffix};
${indent}      return self();
${indent}    }
// field.typeSchema.schemaType ${field.typeSchema.schemaType}
<#if field.typeSchema.schemaType == "ARRAY">
// field.typeSchema.elementType.class ${field.typeSchema.elementType.class}
// field.typeSchema.elementType.name ${field.typeSchema.elementType.name}
// field.typeSchema.elementType.isGenerated ${field.typeSchema.elementType.isGenerated?then('Y', 'N')}
</#if>
    <#if field.typeSchema.schemaType == "ARRAY" && field.typeSchema.elementType.schemaType == "PRIMITIVE" && field.typeSchema.elementType.primitiveType??>
${indent}  
${indent}    /**
${indent}     * Set the value of the ${field.name} attribute.
${indent}     *
${indent}     * @param value The value to be set. 
${indent}     *
${indent}     * @return This (fluent method).
${indent}     */
${indent}     // Array
${indent}    public T with${field.camelCapitalizedName}(${field.typeSchema.type} value)
${indent}    {
    <@checkFieldLimits "    " field "value"/>
${indent}      _${field.camelName}_.add(value);
${indent}      return self();
${indent}    }
    </#if>
${indent}    
    <#if field.typeSchema.schemaType == "PRIMITIVE" && field.typeSchema.primitiveType??>
${indent}    /**
${indent}     * Set the value of the ${field.name} attribute.
${indent}     *
${indent}     * @param value The value to be set. 
${indent}     *
${indent}     * @return This (fluent method).
${indent}     */
${indent}     //typedef
${indent}    public T with${field.camelCapitalizedName}(${field.typeSchema.primitiveType} value)
${indent}    {
    <#if field.required>
${indent}      if(value == null)
${indent}        throw new IllegalArgumentException("${field.camelName} is required.");
${indent}  
    </#if>
${indent}      _${field.camelName}_ = ${field.typeSchema.constructPrefix}value${field.typeSchema.constructSuffix};
${indent}      return self();
${indent}    }
${indent}    
    </#if>
  </#list>
${indent}    @Override 
${indent}    public JsonObject getJsonObject()
${indent}    {
${indent}      JsonObject.Builder builder = new JsonObject.Builder();
${indent}      
${indent}      builder.addIfNotNull(JSON_TYPE, ${className}.TYPE_ID);
${indent}      builder.addIfNotNull(JSON_VERSION, ${className}.TYPE_VERSION);
${indent}
${indent}      populateJson(builder);
${indent}  
${indent}      return builder.build();
${indent}    }
${indent}    
${indent}    @Override
${indent}    public void populateJson(JsonObject.Builder builder)
${indent}    {
${indent}      super.populateJson(builder);
  <#list entity.fields as field>
${indent}  
${indent}      if(get${field.camelCapitalizedName}() != null)
${indent}      {
        <@generateCreateJsonDomNodeFromField "          " field.typeSchema field.quotedName "get${field.camelCapitalizedName}()" "builder"/>
${indent}      }
  </#list>
${indent}    }
${indent}
${indent}    @Override
${indent}    public String getCanonType()
${indent}    {
${indent}      return TYPE_ID;
${indent}    }
${indent}    
${indent}    @Override
${indent}    public String getCanonVersion()
${indent}    {
${indent}      return TYPE_VERSION;
${indent}    }
${indent}    
${indent}    @Override
${indent}    public @Nullable Integer getCanonMajorVersion()
${indent}    {
${indent}      return TYPE_MAJOR_VERSION;
${indent}    }
${indent}
${indent}    @Override
${indent}    public @Nullable Integer getCanonMinorVersion()
${indent}    {
${indent}      return TYPE_MINOR_VERSION;
${indent}    }
${indent}  }
${indent}  
${indent}  // entity.name ${entity.name}
${indent}  // entity.class ${entity.class}
  <#list entity.innerClasses as innerClass>
${indent}    // Inner Class ${innerClass.name}
    <@generateObject "  ${indent}" innerClass innerClass.camelCapitalizedName superClassName "static ${classModifier}"/>
  </#list>
${indent}}
</#macro>
<@generateObject "" entity className superClassName classModifier/>

<#include "/footer.ftl">