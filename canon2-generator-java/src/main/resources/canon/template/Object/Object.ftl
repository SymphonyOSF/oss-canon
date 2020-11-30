<#include "/macros.ftl"/>
<#include "InstanceOrBuilder.ftl">
<#include "../TypeDef/TypeDefMacro.ftl"/>
<#include "../Enum/EnumMacro.ftl"/>
<#macro generateInnerClass indent innerClass className classModifier nested>
  <#if innerClass.schemaType.isObject>
    <#if nested>
      <#assign modifier = classModifier/>
    <#else>
      <#assign modifier = "static ${classModifier}"/>
    </#if>
    <@generateObject "  ${indent}" innerClass className modifier true/>
  <#elseif innerClass.schemaType.isPrimitive>
    <#if innerClass.isEnum>
      <@generateEnum "  ${indent}" model innerClass className "static "/>
    <#elseif innerClass.hasLimits>
      <@generateTypeDef "  ${indent}" model innerClass className "static "/>
    </#if>
  <#else>
  // INNER NON OBJECT ${innerClass.schemaType}
  
  WE NEED TO GENERATE ARRAYS NOW
  
  </#if>
</#macro>
<#macro generateUnknownAttribute indent name>
${indent}if(node instanceof JsonObject)
${indent}{
${indent}  ${name} = new ObjectEntity(new JsonObjectEntityInitialiser((JsonObject)node, initialiser.getModelRegistry()));
${indent}}
${indent}else 
${indent}{
${indent}  ${name} = new <@namespace import="com.symphony.oss.canon2.runtime.java.Entity"/>(new JsonEntityInitialiser(node, initialiser.getModelRegistry()));
${indent}}
</#macro>
<#macro generateObject indent entity className classModifier nested>
<#if entity.isObjectType>
  <#assign hasUnknownProperties = true/>
  <#assign additionalPropertiesFullyQualified = false/>
  <#assign additionalTypeCopyPrefix = ""/>
  <#assign additionalTypeCopySuffix = ""/>
  <#if entity.additionalPropertiesAllowed>
    <#if entity.additionalProperties??>
      <#assign additionalTypeCopyPrefix = entity.additionalProperties.copyPrefix/>
      <#assign additionalTypeCopySuffix = entity.additionalProperties.copySuffix/>
      <#if entity.additionalPropertiesIsInnerClass>
        <#assign additionalType = "${c}AdditionalProperties"/>
      <#else>
        <@namespace name="additionalType" import=entity.additionalProperties.fullyQualifiedType/>
        <#--  --list entity.innerClasses as innerClass>
          <#-if innerClass.name == additionalType>
            <#assign additionalPropertiesFullyQualified = true/>
            <@namespace name="additionalType" import=entity.additionalProperties.fullyQualifiedType/>
          </#if>
        </#list -->
      </#if>
    <#else>
      <#assign hasUnknownProperties = false/>
      <@namespace name="additionalType" import="com.symphony.oss.canon2.runtime.java.Entity"/>
    </#if>
  </#if>
<#else>
  <#assign hasUnknownProperties = false/>
</#if>
<#if nested>
<@generateInstanceOrBuilder "${indent}" entity/>

</#if>
<@namespace name="initialiserType" import=entity.fullyQualifiedInitialiserType/>
<@namespace name="jsonInitialiserType" import=entity.fullyQualifiedJsonInitialiserType/>
<@namespace name="superType" import=entity.fullyQualifiedSuperType/>
<@namespace name="jsonNodeType" import=entity.fullyQualifiedJsonNodeType/>
<#if hasUnknownProperties || entity.additionalPropertiesAllowed>
<@namespace name="Map" import="java.util.Map"/>
<@namespace name="HashMap" import="java.util.HashMap"/>
<@namespace name="ModelRegistry" import="com.symphony.oss.canon2.runtime.java.ModelRegistry"/>
</#if>
<#if hasUnknownProperties>
<@namespace name="RuntimeEntity" import="com.symphony.oss.canon2.runtime.java.Entity"/>
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
${indent}@<@namespace import="javax.annotation.concurrent.Immutable"/>
${indent}public ${classModifier}class ${className} extends <@namespace import=entity.fullyQualifiedSuperType/>
${indent}{
${indent}  /** Type ID */
${indent}  public static final String  TYPE_ID = "${model.canonId}.${entity.name}";
${indent}  /** Type version */
${indent}  public static final String  TYPE_VERSION = "${model.canonVersion}";
${indent}  /** Factory instance */
${indent}  public static final Factory FACTORY = new Factory();

<#if entity.isObjectType>
  <#if entity.additionalPropertiesAllowed>
${indent}  private final ${"${Map}<String, ${additionalType}>"?right_pad(25)}  additionalProperties_;
  </#if>
  <#if hasUnknownProperties>
${indent}  private final ${"Map<String, ${RuntimeEntity}>"?right_pad(25)}  unknownProperties_;
  </#if>
</#if>
<#list entity.fields as field>
${indent}  private final ${field.type?right_pad(25)}  ${c}${field.camelName}_;
</#list>

${indent}  /**
${indent}   * Constructor.
${indent}   *
${indent}   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
${indent}   */
${indent}  public ${className}(Initialiser initialiser)
${indent}  {
${indent}    super(initialiser);

${indent}    if(initialiser instanceof ${jsonInitialiserType})
${indent}    {
${indent}      ${jsonInitialiserType} jsonInitialiser = (${jsonInitialiserType})initialiser;
${indent}      ${ModelRegistry} modelRegistry = jsonInitialiser.getModelRegistry();
<#switch entity.schemaType>
    <#case "ONE_OF">
${indent}      List<ParserException> parserExceptions = new LinkedList<>();
${indent}      List<String>          matches = new LinkedList<>();
${indent}      JsonDomNode           node = jsonInitialiser.getJson();
  <#list entity.fields as field>
    <#assign  fullyQualified  = false/>
    <@generateCreateFieldFromJsonDomNode "${indent}       " "node" entity.schemaType field.typeSchema field.quotedName "${c}${field.camelName}_" "" fullyQualified/>
${indent}      if(${c}${field.camelName}_ != null)
${indent}      {
${indent}        matches.add("${field.typeSchema.name}");
${indent}      }

    </#list>
${indent}      if(matches.size() != 1)
${indent}      {
${indent}        throw new ParserErrorException("Exactly one of <#list entity.fields as field>${field.typeSchema.name}<#sep>,\n" +
${indent}          "</#sep></#list> must be present but " + matches + " were encountered", jsonInitialiser.getJson().getContext(),
                   new ParserResultException(parserExceptions));
${indent}      }
      <#break>
    <#case "OBJECT">

${indent}      ${"JsonDomNode"?right_pad(25)} node;
  <#list entity.fields as field>
    <#assign  fullyQualified  = false/>

${indent}      node = jsonInitialiser.get("${field.quotedName}");
${indent}      if(node == null || node instanceof JsonNull)
${indent}      {
    <#if field.required>
${indent}        throw new ParserErrorException("${field.name} is required.", jsonInitialiser.getJson().getContext());
    <#else>
${indent}        ${c}${field.camelName}_ = null;
    </#if>
${indent}      }
${indent}      else
${indent}      {
    <@generateCreateFieldFromJsonDomNode "${indent}        " "node" entity.schemaType field.typeSchema field.quotedName "${c}${field.camelName}_" "" fullyQualified/>
${indent}      }
  </#list>
<#if entity.additionalPropertiesAllowed>
${indent}      ${"${Map}<String, ${additionalType}>"?right_pad(25)} additionalProperties = new ${HashMap}<>();    
${indent}      ${additionalType?right_pad(25)} prop;
</#if>
<#if hasUnknownProperties>
${indent}      ${"${Map}<String, ${RuntimeEntity}>"?right_pad(25)} unknownProperties = new ${HashMap}<>(); 
${indent}      ${"${RuntimeEntity}"?right_pad(25)} entity;
</#if>

${indent}      for(String name : jsonInitialiser.getCanonUnknownKeys())
${indent}      {
<#if entity.additionalPropertiesAllowed>
${indent}        prop   = null;
</#if>
<#if hasUnknownProperties>
${indent}        entity = null;
</#if>
${indent}        node   = jsonInitialiser.get(name);
<#if entity.additionalPropertiesAllowed>
  <#if entity.additionalProperties??>
    <@generateCreateFieldFromJsonDomNode "${indent}        " "node" additionalType entity.additionalProperties "additionalProperties" "prop" "if(!initialiser.getModelRegistry().getParserValidation().isAllowUnknownAttributes())" additionalPropertiesFullyQualified/>
${indent}        else
${indent}        {
    <@generateUnknownAttribute "${indent}          " "entity"/>
${indent}        }
  <#else>
    <@generateUnknownAttribute "${indent}        " "prop"/>
  </#if>
${indent}        if(prop != null)
${indent}        {
${indent}          additionalProperties.put(name, prop);
${indent}        }
  <#else>
    <@generateUnknownAttribute "${indent}        " "entity"/>
</#if>
<#if hasUnknownProperties>
${indent}        if(entity != null)
${indent}        {
${indent}          unknownProperties.put(name, entity);
${indent}        }
</#if>
${indent}      }
<#if entity.additionalPropertiesAllowed>
${indent}      additionalProperties_ =  ImmutableSortedMap.copyOf(additionalProperties);
</#if>
<#if hasUnknownProperties>
${indent}      unknownProperties_ =  ImmutableSortedMap.copyOf(unknownProperties);
</#if>
      <#break>
</#switch>
${indent}    }
${indent}    else
${indent}    {
${indent}      I${c}${entity.camelCapitalizedName}${c}InstanceOrBuilder builder =  initialiser.getInstanceOrBuilder();

${indent}      if(builder == null)
${indent}      {
${indent}        throw new IllegalArgumentException("Initializer is not an JsonObjectEntityInitialiser but getInstanceOrBuilder() returns null");
${indent}      }
<#list entity.fields as field>
${indent}      ${c}${field.camelName}_ = ${field.typeSchema.copyPrefix}builder.get${field.camelCapitalizedName}()${field.typeSchema.copySuffix};
    <@checkFieldLimits "${indent}      " field "${c}${field.camelName}_"/>
</#list>
<#if entity.additionalPropertiesAllowed>
${indent}      additionalProperties_ = builder.canonGetAdditionalProperties();
</#if>
<#if hasUnknownProperties>
${indent}      unknownProperties_ = builder.canonGetUnknownProperties();
</#if>
${indent}    }
${indent}  }

<#------------------------------------------------------------------------------------------------------------------------------

 Factory

------------------------------------------------------------------------------------------------------------------------------->

${indent}  /**
${indent}   * Factory class for ${entity.type}.
${indent}   */
${indent}  public static class Factory extends ${superType}.Factory<${entity.type}>
${indent}  {
${indent}    @Override
${indent}    public String getCanonType()
${indent}    {
${indent}      return TYPE_ID;
${indent}    }

${indent}    @Override
${indent}    public ${entity.type} newInstance(JsonDomNode node, ${ModelRegistry} modelRegistry)
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
${indent}  public interface Initialiser extends ${initialiserType}
${indent}  {
${indent}    /**
${indent}     * Return an instance or builder containing the values for a new instance.
${indent}     * 
${indent}     * @return an instance or builder containing the values for a new instance.
${indent}     */
${indent}    I${c}${entity.camelCapitalizedName}${c}InstanceOrBuilder getInstanceOrBuilder();
${indent}  }

${indent}  /**
${indent}   * JSON Initialiser for ${entity.type}
${indent}   */
${indent}  public static class JsonInitialiser extends ${jsonInitialiserType} implements Initialiser
${indent}  {
${indent}      /**
${indent}       * Constructor.
${indent}       * 
${indent}       * @param json            JSON serialised form.
${indent}       * @param modelRegistry   A parser context for deserialisation.
${indent}       */
${indent}    public JsonInitialiser(${jsonNodeType} json, ${ModelRegistry} modelRegistry)
${indent}    {
${indent}      super(json, modelRegistry);
${indent}    }

${indent}    @Override
${indent}    public I${c}${entity.camelCapitalizedName}${c}InstanceOrBuilder getInstanceOrBuilder()
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
${indent}    extends ${superType}.AbstractBuilder<T,B>
${indent}    implements I${c}${entity.camelCapitalizedName}${c}InstanceOrBuilder, Initialiser
${indent}  {
  <#list entity.fields as field>
${indent}    protected ${field.type?right_pad(25)}  ${c}${field.camelName}_${field.typeSchema.builderTypeNew};
  </#list>
  <#if entity.additionalPropertiesAllowed>
${indent}    protected ${"${Map}<String, ${additionalType}>"?right_pad(25)}  additionalProperties_ = new ${HashMap}<>();
  </#if>
<#if hasUnknownProperties>
${indent}    protected ${"${Map}<String, ${RuntimeEntity}>"?right_pad(25)}  unknownProperties_ = ImmutableSortedMap.of(); 
</#if>

${indent}    protected AbstractBuilder(Class<T> type)
${indent}    {
${indent}      super(type);
${indent}    }

${indent}    @Override
${indent}    public I${c}${entity.camelCapitalizedName}${c}InstanceOrBuilder getInstanceOrBuilder()
${indent}    {
${indent}      return this;
${indent}    }

${indent}    protected AbstractBuilder(Class<T> type, B initial)
${indent}    {
${indent}      super(type, initial);

  <#list entity.fields as field>
${indent}      ${c}${field.camelName}_ = ${field.typeSchema.copyPrefix}initial.get${field.camelCapitalizedName}()${field.typeSchema.copySuffix};
  </#list>
${indent}    }

${indent}    /**
${indent}     * Initialize this builder with the values from the given serialized form.
${indent}     * 
${indent}     * @param json          The serialized form of an instance of the built type.
${indent}     * @param modelRegistry A model registry.
${indent}     * 
${indent}     * @return This (fluent method).
${indent}     */
<#switch entity.schemaType>
    <#case "ONE_OF">
${indent}    public T withValues(JsonDomNode json, ${ModelRegistry} modelRegistry)
${indent}    {
${indent}      List<ParserException> parserExceptions = new LinkedList<>();
${indent}      List<String>          matches = new LinkedList<>();
  <#list entity.fields as field>
    <#assign  fullyQualified  = false/>
    <@generateCreateFieldFromJsonDomNode "${indent}       " "json" entity.schemaType field.typeSchema field.quotedName "${c}${field.camelName}_" "if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())" fullyQualified/>
${indent}      if(${c}${field.camelName}_ != null)
${indent}      {
${indent}        matches.add("${field.typeSchema.name}");
${indent}      }

    </#list>
${indent}      if(matches.size() != 1)
${indent}      {
${indent}        throw new IllegalArgumentException("Exactly one of <#list entity.fields as field>${field.typeSchema.name}<#sep>,\n" +
${indent}          "</#sep></#list> must be present but " + matches + " were encountered at " + json.getContext(),
                   new ParserResultException(parserExceptions));
${indent}      }
      <#break>
    <#case "OBJECT">
${indent}    public T withValues(JsonObject json, ${ModelRegistry} modelRegistry)
${indent}    {
    <#list entity.fields as field>
    <#assign  fullyQualified  = false/>
${indent}      if(json.containsKey("${field.quotedName}"))
${indent}      {
${indent}        JsonDomNode  node = json.get("${field.quotedName}");
  <@generateCreateFieldFromJsonDomNode "        " "node" entity.schemaType field.typeSchema field.quotedName "${c}${field.camelName}_" "if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())" fullyQualified/>
${indent}      }
</#list>
      <#break>
</#switch>
<#if entity.superSchema??>
${indent}      return super.withValues(json, modelRegistry);
<#else>
${indent}      return self();
</#if>
${indent}    }

<#if entity.additionalPropertiesAllowed>
${indent}    /**
${indent}     * Set an additional property.
${indent}     * 
${indent}     * @param name  The property name.
${indent}     * @param value The property value.
${indent}     * 
${indent}     * @return This (fluent method).
${indent}     */
${indent}    public T with(String name, ${additionalType} value)
${indent}    {
${indent}      additionalProperties_.put(name, ${additionalTypeCopyPrefix}value${additionalTypeCopySuffix});

${indent}      return self();
${indent}    }

</#if>
${indent}    /* void populateAllFields(List<Object> result)
${indent}    {
<#if entity.superSchema??>
${indent}      super.populateAllFields(result);
</#if>
<#list entity.fields as field>
${indent}      result.add(${c}${field.camelName}_);
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
${indent}      if(${c}${field.camelName}_ == null)
${indent}        throw new IllegalStateException("Unexpected null value encountered");
</#if>
${indent}      return ${c}${field.camelName}_;
${indent}    }

${indent}    /**
${indent}     * Set the value of the ${field.name} attribute.
${indent}     *
${indent}     * @param value The value to be set.
${indent}     *
${indent}     * @return This (fluent method).
${indent}     */
${indent}    public T with${field.camelCapitalizedName}(${field.typeSchema.type} value)
${indent}    {
    <@checkFieldLimits "        " field "value"/>
${indent}      ${c}${field.camelName}_ = ${field.typeSchema.copyPrefix}value${field.typeSchema.copySuffix};
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
${indent}      ${c}${field.camelName}_.add(value);
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
${indent}    public T with${field.camelCapitalizedName}(${field.typeSchema.primitiveType} value)
${indent}    {
    <#if field.required>
${indent}      if(value == null)
${indent}        throw new IllegalArgumentException("${field.camelName} is required.");

    </#if>
${indent}      ${c}${field.camelName}_ = ${field.typeSchema.getConstructor(false, "value")};
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

${indent}    @Override
${indent}    public void populateJson(JsonObject.Builder builder)
${indent}    {
${indent}      super.populateJson(builder);
  <#list entity.fields as field>

${indent}      if(get${field.camelCapitalizedName}() != null)
${indent}      {
        <@generateCreateJsonDomNodeFromField "${indent}        " field.typeSchema "\"${field.quotedName}\"" "get${field.camelCapitalizedName}()" "builder"/>
${indent}      }

  </#list>
<#if entity.additionalPropertiesAllowed>
${indent}      for(String name : additionalProperties_.keySet())
${indent}      {
${indent}        ${additionalType} value = additionalProperties_.get(name);
  <#if entity.additionalProperties??>
    <@generateCreateJsonDomNodeFromField "${indent}        " entity.additionalProperties "name" "value" "builder"/>
  <#else>
${indent}        builder.addIfNotNull(name, value.getJson());
  </#if>
${indent}      }
</#if>
${indent}    }
       <#break>
    <#case "ONE_OF">
${indent}    @Override
${indent}    public JsonDomNode getJson()
${indent}    {
  <#list entity.fields as field>

${indent}      if(get${field.camelCapitalizedName}() != null)
${indent}      {
    <#if field.typeSchema.schemaType.isPrimitive>
${indent}        return JsonDomNode.newInstance(get${field.camelCapitalizedName}());
    <#else>
${indent}        return get${field.camelCapitalizedName}().getJson();
    </#if>
${indent}      }
  </#list>
  
${indent}      throw new IllegalStateException("No value present in OneOf instance");
${indent}    }
      <#break>
  </#switch>

<#if entity.additionalPropertiesAllowed>
${indent}    @Override
${indent}    public ${Map}<String, ${additionalType}> canonGetAdditionalProperties()
${indent}    {
${indent}       return ImmutableSortedMap.copyOf(additionalProperties_);
${indent}    }

</#if>
<#if hasUnknownProperties>
${indent}    @Override
${indent}    public ${Map}<String, ${RuntimeEntity}> canonGetUnknownProperties()
${indent}    {
${indent}       return unknownProperties_;
${indent}    }

</#if>
${indent}    @Override
${indent}    public void validate(FaultAccumulator faultAccumulator)
${indent}    {
${indent}      super.validate(faultAccumulator);
  <#switch entity.schemaType>
    <#case "OBJECT">
  <#list entity.fields as field>
    <#if field.required>
${indent}      faultAccumulator.checkNotNull(${c}${field.camelName}_, "${field.name}");
    </#if>
  </#list>
       <#break>
    <#case "ONE_OF">
${indent}      faultAccumulator.checkValueCount("fields", 1, 1,
  <#list entity.fields as field>
${indent}        ${c}${field.camelName}_<#sep>,</#sep>
  </#list>
${indent}      );
      <#break>
  </#switch>
${indent}    }
${indent}  }

<#if entity.additionalPropertiesAllowed>
${indent}  /**
${indent}   * Return any additional attributes.
${indent}   * 
${indent}   * @return any additional attributes.
${indent}   */
${indent}  public ${Map}<String, ${additionalType}> canonGetAdditionalProperties()
${indent}  {
${indent}     return additionalProperties_;
${indent}  }

</#if>
<#if hasUnknownProperties>
${indent} /**
${indent}   * Return any additional attributes.
${indent}   * 
${indent}   * @return any additional attributes.
${indent}   */
${indent}  public ${Map}<String, ${RuntimeEntity}> canonGetUnknownProperties()
${indent}  {
${indent}     return unknownProperties_;
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
${indent}    return ${c}${field.camelName}_;
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
${indent}    if(${c}${field.camelName}_ != null)
${indent}      return ${c}${field.camelName}_;

      </#list>
${indent}    return null;
${indent}  }
      <#break>
</#switch>

<#--  --if entity.additionalProperties??>
  <#if entity.additionalPropertiesIsInnerClass>
    <@generateInnerClass indent entity.additionalProperties entity.additionalProperties.camelCapitalizedName classModifier nested/>
    
  </#if>
</#if -->
<#list entity.innerClasses as innerClass>
// INNER CLASS ${innerClass.camelCapitalizedName}
  <@generateInnerClass indent innerClass innerClass.camelCapitalizedName classModifier nested/>
</#list>
${indent}}
</#macro>
<@generateObject "" entity className classModifier false/>

<#include "/footer.ftl">