<#assign imports = entity.imports + [
  "javax.annotation.concurrent.Immutable",
  "javax.annotation.Nullable",
  "com.google.common.collect.ImmutableSet",
  "com.symphony.oss.canon2.runtime.java.ModelRegistry",
  "com.symphony.oss.canon.json.model.JsonObject",
  "com.symphony.oss.canon.json.model.JsonDomNode",
  "com.symphony.oss.canon.json.ParserErrorException",
  "com.symphony.oss.commons.fault.FaultAccumulator"
  ]>
<#include "/copyrightHeader.ftl"/>
<#include "/macros.ftl"/>
<#include "InstanceOrBuilder.ftl">
<#macro importObject schema>
<#switch schema.schemaType>
    <#case "OBJECT">
      <#assign imports = imports + [
      "com.symphony.oss.canon2.runtime.java.IObjectEntityInitialiser",
      "com.symphony.oss.canon2.runtime.java.JsonObjectEntityInitialiser",
      "${schema.fullyQualifiedSuperTypeName}"
      ]>
      <#break>
    <#case "ONE_OF">
      <#assign imports = imports + [
      "com.symphony.oss.canon2.runtime.java.IEntityInitialiser",
      "com.symphony.oss.canon2.runtime.java.JsonEntityInitialiser",
      "${schema.fullyQualifiedSuperTypeName}"
      ]>
      <#break>
</#switch>
</#macro>
<#macro importType schema>
// importType ${schema.name}
  <#assign imports = imports + [
    "com.symphony.oss.canon.json.model.JsonDomNode",
    "com.symphony.oss.canon.json.model.JsonNull",
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
      <#switch schema.cardinality>
        <#case "LIST">
          <#assign imports = imports + [
            "java.util.List",
            "java.util.LinkedList",
            "com.google.common.collect.ImmutableList"
          ]>
          <#break>
        <#case "SET">
          <#assign imports = imports + [
            "java.util.Set",
            "java.util.HashSet",
            "com.google.common.collect.ImmutableSet"
          ]>
          <#break>
      </#switch>
      <@importArrayJsonType schema.elementType />
      <#break>
    <#case "OBJECT">
      <#break>
    <#default>
    <@importType schema />
  </#switch>
</#macro>
<#macro importFields entity>
// importFields
  <@importObject entity/>
  <#list entity.fields as field>
// importField ${field.name} nullable is ${field.nullable}
    <#assign imports = imports + [
        "javax.annotation.${field.nullable}"
      ]>
    <@importType field.typeSchema />
// importType ${field.typeSchema.name}
    <#--  @importObject field.typeSchema/ -->
    <#if field.typeSchema.schemaType == "ARRAY">
      <@importArrayJsonType field.typeSchema />
    </#if>
  </#list>
  <#list entity.innerClasses as innerClass>
  // innerClass ${innerClass.class}
  // innerClass ${innerClass.name}
    <#if innerClass.schemaType.isObject>
      <@importObject innerClass/>
    </#if>
         
    <#if innerClass.schemaType.isPrimitive>
    // innerClass ${innerClass.name} isPrimitive
      <#assign imports = imports + [
        "java.util.Objects",
        "com.symphony.oss.canon2.runtime.java.TypeDef",
        "javax.annotation.Nonnull"
        ]>
    </#if>
        
        
        
    <#list innerClass.fields as field>
    // innerClass.field ${field.name} nullable javax.annotation.${field.nullable}
      <#assign imports = imports + [
          "javax.annotation.${field.nullable}"
        ]>
      <#if field.typeSchema.schemaType == "OBJECT">
        //innerClass.field ${field.name} OBJECT
        <@importFields field.typeSchema/>
      <#else>
        <@importType field/>
      </#if>
    </#list>
  </#list>
</#macro>
<@importFields entity/>

package ${genPackage};

<#list entity.sortImports(imports, genPackage) as import>
${import}
</#list>
<#include "../TypeDef/TypeDefMacro.ftl"/>
<#macro generateObject indent entity className classModifier nested>
<#if nested>
<@generateInstanceOrBuilder "${indent}" entity/>

</#if>
${indent}/**
${indent} * Implementation for Object ${entity}
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
${indent}public ${classModifier}class ${className} extends ${entity.superTypeName}
${indent}{
${indent}  /** Type ID */
${indent}  public static final String  TYPE_ID = "${model.canonId}.${entity.name}";
${indent}  /** Type version */
${indent}  public static final String  TYPE_VERSION = "${model.canonVersion}";
${indent}  /** Factory instance */
${indent}  public static final Factory FACTORY = new Factory();

<#if entity.isObjectType>
${indent}  private final ${"ImmutableSet<String>"?right_pad(25)}   unknownKeys_;
</#if>
<#list entity.fields as field>
${indent}  private final ${field.type?right_pad(25)}  _${field.camelName}_;
</#list>

${indent}  /**
${indent}   * Constructor.
${indent}   *
${indent}   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
${indent}   */
${indent}  public ${className}(Initialiser initialiser)
${indent}  {
${indent}    super(initialiser);

${indent}    if(initialiser instanceof Json${entity.initialiserType})
${indent}    {
${indent}      Json${entity.initialiserType} jsonInitialiser = (Json${entity.initialiserType})initialiser;
<#switch entity.schemaType>
    <#case "ONE_OF">

${indent}      JsonDomNode  node = jsonInitialiser.getJson();
${indent}      int          valueCnt = 0;
  <#list entity.fields as field>

<#-- 
${indent}      if(node instanceof ${entity.jsonNodeType})
${indent}      {
${indent}        return ${entity.constructPrefix}((${entity.jsonNodeType})node).as${entity.javaType}()${entity.constructSuffix};
      <@checkLimits "${indent}      " entity name var "new new ParserErrorException" ", node.getContext()"/>
${indent}      }
*/ -->

//START
    <@generateCreateFieldFromJsonDomNode "${indent}        " "node" field.typeSchema field.quotedName "_${field.camelName}_" "" "jsonInitialiser.getModelRegistry()"/>
  </#list>
      <#break>
    <#case "OBJECT">

<#if entity.fields?size != 0>
${indent}      JsonDomNode  node;
  <#list entity.fields as field>

${indent}      node = jsonInitialiser.get("${field.quotedName}");
${indent}      if(node == null || node instanceof JsonNull)
${indent}      {
    <#if field.required>
${indent}        throw new ParserErrorException("${field.name} is required.", jsonInitialiser.getJson().getContext());
    <#else>
${indent}        _${field.camelName}_ = null;
    </#if>
${indent}      }
${indent}      else
${indent}      {
    <@generateCreateFieldFromJsonDomNode "${indent}        " "node" field.typeSchema field.quotedName "_${field.camelName}_" "" "jsonInitialiser.getModelRegistry()"/>
${indent}      }
  </#list>
</#if>
${indent}      unknownKeys_ = jsonInitialiser.getCanonUnknownKeys();
      <#break>
</#switch>
${indent}    }
${indent}    else
${indent}    {
${indent}      I${entity.camelCapitalizedName}InstanceOrBuilder builder =  initialiser.getInstanceOrBuilder();

${indent}      if(builder == null)
${indent}      {
${indent}        throw new IllegalArgumentException("Initializer is not an JsonObjectEntityInitialiser but getInstanceOrBuilder() returns null");
${indent}      }
<#list entity.fields as field>
${indent}      _${field.camelName}_ = ${field.typeSchema.copyPrefix}builder.get${field.camelCapitalizedName}()${field.typeSchema.copySuffix};
    <@checkFieldLimits "${indent}      " field "_${field.camelName}_"/>
</#list>
<#if entity.isObjectType>
${indent}      unknownKeys_ = builder.getCanonUnknownKeys();
</#if>
${indent}    }
${indent}  }

<#------------------------------------------------------------------------------------------------------------------------------

 Factory

------------------------------------------------------------------------------------------------------------------------------->

${indent}  /**
${indent}   * Factory class for ${entity.type}.
${indent}   */
${indent}  public static class Factory extends ${entity.superTypeName}.Factory<${entity.type}>
${indent}  {
${indent}    @Override
${indent}    public String getCanonType()
${indent}    {
${indent}      return TYPE_ID;
${indent}    }

${indent}    @Override
${indent}    public ${entity.type} newInstance(JsonDomNode node, ModelRegistry modelRegistry)
${indent}    {
<#switch entity.schemaType>
    <#case "ONE_OF">
${indent}      return new ${entity.type}(new JsonInitialiser(node, modelRegistry));
      <#break>
    <#default>
${indent}      if(node instanceof JsonObject)
${indent}      {
${indent}        return new ${entity.type}(new JsonInitialiser((JsonObject)node, modelRegistry));
${indent}      }

${indent}      if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
${indent}      {
${indent}        throw new ParserErrorException("${entity.name} must be an Object node not " + node.getClass().getName(), node.getContext());
${indent}      }
${indent}      else
${indent}      {
${indent}        return null;
${indent}      }
</#switch>
${indent}    }
${indent}  }

<#------------------------------------------------------------------------------------------------------------------------------

 Initialiser

------------------------------------------------------------------------------------------------------------------------------->
${indent}  /**
${indent}   * Abstract Initialiser for ${entity.type}
${indent}   */
${indent}  public interface Initialiser extends I${entity.initialiserType}
${indent}  {
${indent}    /**
${indent}     * Return an instance or builder containing the values for a new instance.
${indent}     * 
${indent}     * @return an instance or builder containing the values for a new instance.
${indent}     */
${indent}    I${entity.camelCapitalizedName}InstanceOrBuilder getInstanceOrBuilder();
${indent}  }

${indent}  /**
${indent}   * JSON Initialiser for ${entity.type}
${indent}   */
${indent}  public static class JsonInitialiser extends Json${entity.initialiserType} implements Initialiser
${indent}  {
${indent}      /**
${indent}       * Constructor.
${indent}       * 
${indent}       * @param json            JSON serialised form.
${indent}       * @param modelRegistry   A parser context for deserialisation.
${indent}       */
${indent}    public JsonInitialiser(${entity.jsonNodeType} json, ModelRegistry modelRegistry)
${indent}    {
${indent}      super(json, modelRegistry);
${indent}    }

${indent}    @Override
${indent}    public I${entity.camelCapitalizedName}InstanceOrBuilder getInstanceOrBuilder()
${indent}    {
${indent}      return null;
${indent}    }
${indent}  }

<#------------------------------------------------------------------------------------------------------------------------------

 AbstractBuilder

------------------------------------------------------------------------------------------------------------------------------->
${indent}  /**
${indent}   * Abstract builder for ${entity.type}. If there are sub-classes of this type then their builders sub-class this builder.
${indent}   *
${indent}   * @param <T> The concrete type of the builder, used for fluent methods.
${indent}   * @param <B> The concrete type of the built object.
${indent}   */
${indent}  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends ${className}>
// super class name
${indent}    extends ${entity.superTypeName}.AbstractBuilder<T,B>
${indent}    implements I${entity.camelCapitalizedName}InstanceOrBuilder, Initialiser
${indent}  {
  <#list entity.fields as field>
${indent}    protected ${field.type?right_pad(25)}  _${field.camelName}_${field.typeSchema.builderTypeNew};
  </#list>

${indent}    protected AbstractBuilder(Class<T> type)
${indent}    {
${indent}      super(type);
${indent}    }

${indent}    @Override
${indent}    public I${entity.camelCapitalizedName}InstanceOrBuilder getInstanceOrBuilder()
${indent}    {
${indent}      return this;
${indent}    }

${indent}    protected AbstractBuilder(Class<T> type, B initial)
${indent}    {
${indent}      super(type, initial);

  <#list entity.fields as field>
${indent}      _${field.camelName}_ = ${field.typeSchema.copyPrefix}initial.get${field.camelCapitalizedName}()${field.typeSchema.copySuffix};
  </#list>
${indent}    }

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
  <@generateCreateFieldFromJsonDomNode "        " "node" field.typeSchema field.quotedName "_${field.camelName}_" "if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())" "modelRegistry"/>
${indent}      }
</#list>
${indent}      return super.withValues(jsonObject, modelRegistry);
${indent}    }

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
${indent}    @Override
${indent}    public @${field.nullable} ${field.type} get${field.camelCapitalizedName}()
${indent}    {
<#if field.required>
${indent}      if(_${field.camelName}_ == null)
${indent}        throw new IllegalStateException("Unexpected null value encountered");
</#if>
${indent}      return _${field.camelName}_;
${indent}    }

${indent}    /**
${indent}     * Set the value of the ${field.name} attribute.
${indent}     *
${indent}     * @param value The value to be set.
${indent}     *
${indent}     * @return This (fluent method).
${indent}     */
${indent}    public T with${field.camelCapitalizedName}(${field.typeSchema.type} value) //main
${indent}    {
    <@checkFieldLimits "        " field "value"/>
${indent}      _${field.camelName}_ = ${field.typeSchema.copyPrefix}value${field.typeSchema.copySuffix};
${indent}      return self();
${indent}    }
<#if field.typeSchema.schemaType == "ARRAY">
</#if>
    <#if field.typeSchema.schemaType == "ARRAY" && field.typeSchema.elementType.schemaType == "PRIMITIVE" && field.typeSchema.elementType.primitiveType??>

${indent}    /**
${indent}     * Set the value of the ${field.name} attribute.
${indent}     *
${indent}     * @param value The value to be set.
${indent}     *
${indent}     * @return This (fluent method).
${indent}     */
${indent}    public T with${field.camelCapitalizedName}(${field.typeSchema.type} value)
${indent}    {
    <@checkFieldLimits "    " field "value"/>
${indent}      _${field.camelName}_.add(value);
${indent}      return self();
${indent}    }
    </#if>

    <#if field.typeSchema.schemaType.isPrimitive && field.typeSchema.primitiveType??>
${indent}    /**
${indent}     * Set the value of the ${field.name} attribute.
${indent}     *
${indent}     * @param value The value to be set.
${indent}     *
${indent}     * @return This (fluent method).
${indent}     */
${indent}    public T with${field.camelCapitalizedName}(${field.typeSchema.primitiveType} value) // primitive
${indent}    {
    <#if field.required>
${indent}      if(value == null)
${indent}        throw new IllegalArgumentException("${field.camelName} is required.");

    </#if>
${indent}      _${field.camelName}_ = ${field.typeSchema.constructPrefix}value${field.typeSchema.constructSuffix};
${indent}      return self();
${indent}    }

    </#if>
  </#list>

  <#switch entity.schemaType>
    <#case "OBJECT">
${indent}    @Override
${indent}    public JsonObject getJson()
${indent}    {
${indent}      JsonObject.Builder builder = new JsonObject.Builder();

${indent}      builder.addIfNotNull(JSON_TYPE, ${className}.TYPE_ID);
${indent}      builder.addIfNotNull(JSON_VERSION, ${className}.TYPE_VERSION);

${indent}      populateJson(builder);

${indent}      return builder.build();
${indent}    }
//T1 entity ${entity.name} ${entity.schemaType}
${indent}    @Override
${indent}    public void populateJson(JsonObject.Builder builder)
${indent}    {
${indent}      super.populateJson(builder);
  <#list entity.fields as field>

${indent}      if(get${field.camelCapitalizedName}() != null)
${indent}      {
        <@generateCreateJsonDomNodeFromField "          " field.typeSchema field.quotedName "get${field.camelCapitalizedName}()" "builder"/>
${indent}      }
  </#list>
${indent}    }
      <#break>
    <#case "ONE_OF">
${indent}    @Override
${indent}    public JsonDomNode getJson()
${indent}    {
${indent}      return null; // TODO: implement
${indent}    }
      <#break>
  </#switch>

${indent}    @Override
${indent}    public void validate(FaultAccumulator faultAccumulator)
${indent}    {
${indent}      super.validate(faultAccumulator);
  <#list entity.fields as field>
    <#if field.required>
${indent}      faultAccumulator.checkNotNull(_${field.camelName}_, "${field.name}");
    </#if>
  </#list>
${indent}    }
${indent}  }

<#if entity.isObjectType>
${indent}  @Override
${indent}  public ImmutableSet<String> getCanonUnknownKeys()
${indent}  {
${indent}    return unknownKeys_;
${indent}  }
</#if>
<#------------------------------------------------------------------------------------------------------------------------------

 Builder

------------------------------------------------------------------------------------------------------------------------------->

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

${indent}    /**
${indent}     * Constructor initialised from another object instance.
${indent}     *
${indent}     * @param initial An instance of the built type from which values are to be initialised.
${indent}     */
${indent}    public Builder(${entity.type} initial)
${indent}    {
${indent}      super(Builder.class, initial);
${indent}    }

${indent}    @Override
${indent}    protected ${entity.type} construct()
${indent}    {
${indent}      return new ${entity.type}(this);
${indent}    }
${indent}  }
<#------------------------------------------------------------------------------------------------------------------------------

 Methods

------------------------------------------------------------------------------------------------------------------------------->

<#list entity.fields as field>

${indent}  /**
${indent}   * Return the value of the ${field.name} attribute.
${indent}   *
  <#if field.typeSchema.description??>
${indent}   * ${field.typeSchema.description}
${indent}   *
  </#if>
${indent}   * @return the value of the ${field.name} attribute.
${indent}   */
${indent}  public @${field.nullable} ${field.type} get${field.camelCapitalizedName}()
${indent}  {
${indent}    return _${field.camelName}_;
${indent}  }
</#list>

${indent}  @Override
${indent}  public boolean equals(Object obj)
${indent}  {
${indent}    if(obj instanceof ${className})
${indent}      return toString().equals(((${className})obj).toString());

${indent}    return false;
${indent}  }

${indent}  @Override
${indent}  public int hashCode()
${indent}  {
${indent}    return toString().hashCode();
${indent}  }

<#switch entity.schemaType>
    <#case "ONE_OF">

${indent}  /**
${indent}   * Return the value of this OneOf entity.
${indent}   *
${indent}   * @return the value of this OneOf entity.
${indent}   */
${indent}  public Object canonGetValue()
${indent}  {
      <#list entity.fields as field>
${indent}    if(_${field.camelName}_ != null)
${indent}      return _${field.camelName}_;

      </#list>
${indent}    return null;
${indent}  }
      <#break>
</#switch>
// entity.additionalProperties??
<#if entity.additionalProperties??>
// entity.additionalProperties ${entity.additionalProperties.name}
</#if>
// innerClasses
  <#list entity.innerClasses as innerClass>
  // innerClass ${innerClass.name} ${innerClass.schemaType} ${innerClass.schemaType.class}
    <#if innerClass.schemaType.isObject>
      <#if nested>
        <#assign modifier = classModifier/>
      <#else>
        <#assign modifier = "static ${classModifier}"/>
      </#if>
      <@generateObject "  ${indent}" innerClass innerClass.camelCapitalizedName modifier true/>
    <#elseif innerClass.schemaType.isPrimitive>
      <#if innerClass.hasLimits>
        // Primitive inner class ${innerClass.class}
        <@generateTypeDef "  " model innerClass innerClass.camelCapitalizedName "static "/>
      </#if>
    </#if>
  </#list>
${indent}}
</#macro>
<@generateObject "" entity className classModifier false/>

<#include "/footer.ftl">