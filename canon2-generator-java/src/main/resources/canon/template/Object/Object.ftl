<#include "/macros.ftl"/>
<#include "../TypeDef/TypeDefMacro.ftl"/>
<#include "../Enum/EnumMacro.ftl"/>
<#include "../Array/ArrayMacro.ftl"/>
<#macro generateUnknownAttribute indent name>
${indent}if(node instanceof JsonObject)
${indent}{
${indent}  ${name} = new ObjectEntity(new JsonObjectEntityInitialiser((JsonObject)node, initialiser.getModelRegistry()));
${indent}}
${indent}else 
${indent}{
${indent}  ${name} = new <@namespace import="com.symphony.oss.canon2.runtime.java.Entity"/>(new <@namespace import="com.symphony.oss.canon2.runtime.java.JsonEntityInitialiser"/>(node, initialiser.getModelRegistry()));
${indent}}
</#macro>
<#macro generateObject indent entity className classModifier nested>
<#if entity.isObjectType>
  <#assign hasUnknownProperties = true/>
  <#if entity.additionalPropertiesAllowed>
    <#if entity.additionalProperties??>
      <#if entity.additionalPropertiesIsInnerClass>
        <#assign additionalType = "${c}AdditionalProperties"/>
      <#else>
        <@namespace name="additionalType" import=entity.additionalProperties.fullyQualifiedType/>
        <#--  --list entity.innerClasses as innerClass>
          <#-if innerClass.name == additionalType>
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
<@namespace name="initialiserType" import=entity.fullyQualifiedInitialiserType/>
<@namespace name="jsonInitialiserType" import=entity.fullyQualifiedJsonInitialiserType/>
<@namespace name="superType" import=entity.fullyQualifiedSuperType/>
<@namespace name="jsonNodeType" import=entity.fullyQualifiedJsonNodeType/>
<@namespace name="JsonDomNode" import="com.symphony.oss.canon.json.model.JsonDomNode"/>
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
${indent}  public static final String  TYPE_ID = "${model.canonId}.${entity.camelCapitalizedName}";
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

${indent}    if(initialiser.getBuilder() == null)
${indent}    {
${indent}      JsonInitialiser jsonInitialiser = initialiser.getJsonInitialiser();

${indent}      if(jsonInitialiser == null)
${indent}      {
${indent}        throw new IllegalArgumentException("Initializer returns null for getBuilder() as well as .getJsonInitialiser()");
${indent}      }

${indent}      ${ModelRegistry} modelRegistry = jsonInitialiser.getModelRegistry();
<#switch entity.schemaType>
    <#case "ONE_OF">
${indent}      List<ParserException> parserExceptions = new LinkedList<>();
${indent}      List<String>          matches = new LinkedList<>();
${indent}      ${JsonDomNode}           node = jsonInitialiser.getJson();
  <#list entity.fields as field>
    <@generateCreateFieldFromJsonDomNode "${indent}       " "node" entity.schemaType field.typeSchema field.quotedName "${c}${field.camelName}_" ""/>
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

${indent}      ${"${JsonDomNode}"?right_pad(25)} node;
  <#list entity.fields as field>

${indent}      node = jsonInitialiser.get("${field.quotedName}");
${indent}      if(node == null || node instanceof <@namespace import="com.symphony.oss.canon.json.model.JsonNull"/>)
${indent}      {
    <#if field.required>
${indent}        throw new ParserErrorException("${field.name} is required.", jsonInitialiser.getJson().getContext());
    <#else>
${indent}        ${c}${field.camelName}_ = null;
    </#if>
${indent}      }
${indent}      else
${indent}      {
    <@generateCreateFieldFromJsonDomNode "${indent}        " "node" entity.schemaType field.typeSchema field.quotedName "${c}${field.camelName}_" ""/>
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
    <@generateCreateFieldFromJsonDomNode "${indent}        " "node" additionalType entity.additionalProperties "additionalProperties" "prop" "if(!initialiser.getModelRegistry().getParserValidation().isAllowUnknownAttributes())"/>
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
${indent}      additionalProperties_ = <@namespace import="com.google.common.collect.ImmutableSortedMap"/>.copyOf(additionalProperties);
</#if>
<#if hasUnknownProperties>
${indent}      unknownProperties_ =  <@namespace import="com.google.common.collect.ImmutableSortedMap"/>.copyOf(unknownProperties);
</#if>
      <#break>
</#switch>
${indent}    }
${indent}    else
${indent}    {
${indent}      AbstractBuilder<?,?> builder =  initialiser.getBuilder();

<#list entity.fields as field>
//field.typeSchema is ${field.typeSchema.class}
${indent}      ${c}${field.camelName}_ = ${field.typeSchema.getCopy("builder.get${field.camelCapitalizedName}()")};
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
${indent}    public ${entity.type} newInstance(${JsonDomNode} node, ${ModelRegistry} modelRegistry)
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
${indent}   *
${indent}   * One of these methods must return non-null.
${indent}   */
${indent}  public static interface Initialiser extends ${initialiserType}
${indent}  {
${indent}    /**
${indent}     * Return a JsonInitialiser containing the values for a new instance.
${indent}     * 
${indent}     * @return a JsonInitialiser containing the values for a new instance.
${indent}     */
${indent}    JsonInitialiser getJsonInitialiser();

${indent}    /**
${indent}     * Return a builder containing the values for a new instance.
${indent}     * 
${indent}     * @return a builder containing the values for a new instance.
${indent}     */
${indent}    AbstractBuilder<?,?> getBuilder();
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
${indent}    public JsonInitialiser getJsonInitialiser()
${indent}    {
${indent}      return this;
${indent}    }

${indent}    @Override
${indent}    public AbstractBuilder<?,?> getBuilder()
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
${indent}    implements Initialiser
${indent}  {
  <#list entity.fields as field>
  // field.typeSchema is ${field.typeSchema.class}
${indent}    protected ${field.type?right_pad(25)}  ${c}${field.camelName}_${field.typeSchema.builderTypeNew};
  </#list>
  <#if entity.additionalPropertiesAllowed>
${indent}    protected ${"${Map}<String, ${additionalType}>"?right_pad(25)}  additionalProperties_ = new ${HashMap}<>();
  </#if>
<#if hasUnknownProperties>
${indent}    protected ${"${Map}<String, ${RuntimeEntity}>"?right_pad(25)}  unknownProperties_ = <@namespace import="com.google.common.collect.ImmutableSortedMap"/>.of(); 
</#if>

${indent}    protected AbstractBuilder(Class<T> type)
${indent}    {
${indent}      super(type);
${indent}    }

${indent}    protected AbstractBuilder(Class<T> type, B initial)
${indent}    {
${indent}      super(type, initial);

  <#list entity.fields as field>
${indent}      ${c}${field.camelName}_ = ${field.typeSchema.getCopy("initial.get${field.camelCapitalizedName}()")};
  </#list>
${indent}    }

${indent}    @Override
${indent}    public JsonInitialiser getJsonInitialiser()
${indent}    {
${indent}      return null;
${indent}    }

${indent}    @Override
${indent}    public AbstractBuilder<?,?> getBuilder()
${indent}    {
${indent}      return this;
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
${indent}    public T withValues(${JsonDomNode} json, ${ModelRegistry} modelRegistry)
${indent}    {
${indent}      List<ParserException> parserExceptions = new LinkedList<>();
${indent}      List<String>          matches = new LinkedList<>();
  <#list entity.fields as field>
    <@generateCreateFieldFromJsonDomNode "${indent}       " "json" entity.schemaType field.typeSchema field.quotedName "${c}${field.camelName}_" "if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())"/>
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
${indent}      if(json.containsKey("${field.quotedName}"))
${indent}      {
${indent}        ${JsonDomNode}  node = json.get("${field.quotedName}");
  <@generateCreateFieldFromJsonDomNode "        " "node" entity.schemaType field.typeSchema field.quotedName "${c}${field.camelName}_" "if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())"/>
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
  <#if entity.additionalProperties??>
${indent}      additionalProperties_.put(name, ${entity.additionalProperties.getCopy("value")}); //WAS additionalTypeCopyPrefix
  <#else>
${indent}      additionalProperties_.put(name, value);
  </#if>

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
${indent}    public @${field.nullable} ${field.type} get${field.camelCapitalizedName}()
${indent}    {
<#if field.required>
${indent}      if(${c}${field.camelName}_ == null)
${indent}        throw new IllegalStateException("Unexpected null value encountered");
</#if>
${indent}      return ${c}${field.camelName}_;
${indent}    }

    <#if field.typeSchema.schemaType == "ARRAY">
// Array setters
${indent}    /**
${indent}     * Clear all values from the ${field.name} attribute.
${indent}     *
${indent}     * @return This (fluent method).
${indent}     */
${indent}    public T clear${field.camelCapitalizedName}()
${indent}    {
${indent}      ${c}${field.camelName}_.clear();
${indent}      return self();
${indent}    }

//basic setter
${indent}    /**
${indent}     * Add all of the given values to the ${field.name} attribute.
${indent}     *
${indent}     * @param values The values to be added.
${indent}     *
${indent}     * @return This (fluent method).
${indent}     */
${indent}    public T with${field.camelCapitalizedName}(${field.typeSchema.type} values)
${indent}    {
${indent}      ${c}${field.camelName}_.addAll(values);
${indent}      return self();
${indent}    }

//element setter
${indent}    /**
${indent}     * Add the given value to the ${field.name} attribute.
${indent}     *
${indent}     * @param values The values to be added.
${indent}     *
${indent}     * @return This (fluent method).
${indent}     */
${indent}    public T with${field.camelCapitalizedName}(${field.typeSchema.element.type} value)
${indent}    {
${indent}      if(value == null)
${indent}        throw new IllegalArgumentException("A value is required.");

${indent}      ${c}${field.camelName}_add(value);
${indent}      return self();
${indent}    }

//externalType? field.typeSchema.element ${field.typeSchema.element.class}
    <#if field.typeSchema.element.externalType??>
//externalType
${indent}    /**
${indent}     * Add the given value to the ${field.name} attribute.
${indent}     *
${indent}     * @param value The value to be added.
${indent}     *
${indent}     * @return This (fluent method).
${indent}     */
${indent}    public T with${field.camelCapitalizedName}(${field.typeSchema.element.externalType} value)
${indent}    {
${indent}      if(value == null)
${indent}        throw new IllegalArgumentException("A value is required.");

${indent}      ${c}${field.camelName}_ = ${field.typeSchema.element.getConstructor("value")};
${indent}      return self();
${indent}    }

    <#elseif field.typeSchema.element.isTypedef>
//typedef
${indent}    /**
${indent}     * Add the given value to the ${field.name} attribute.
${indent}     *
${indent}     * @param value The value to be set.
${indent}     *
${indent}     * @return This (fluent method).
${indent}     */
${indent}    public T with${field.camelCapitalizedName}(${field.typeSchema.element.persistedType} value)
${indent}    {
${indent}      if(value == null)
${indent}        throw new IllegalArgumentException("A value is required.");

${indent}      ${c}${field.camelName}_ = ${field.typeSchema.element.getConstructor("value")};
${indent}      return self();
${indent}    }

    </#if>
    <#else>
// Non-array setters
//basic setter
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
${indent}      ${c}${field.camelName}_ = ${field.typeSchema.getCopy("value")};
${indent}      return self();
${indent}    }

//externalType? field.typeSchema ${field.typeSchema.class}
    <#if field.typeSchema.externalType??>
//externalType
${indent}    /**
${indent}     * Set the value of the ${field.name} attribute.
${indent}     *
${indent}     * @param value The value to be set.
${indent}     *
${indent}     * @return This (fluent method).
${indent}     */
${indent}    public T with${field.camelCapitalizedName}(${field.typeSchema.externalType} value)
${indent}    {
    <@checkFieldLimits "        " field "value"/>
${indent}      ${c}${field.camelName}_ = ${field.typeSchema.getConstructor("value")};
${indent}      return self();
${indent}    }

    <#elseif field.typeSchema.isTypedef>
//typedef
${indent}    /**
${indent}     * Set the value of the ${field.name} attribute.
${indent}     *
${indent}     * @param value The value to be set.
${indent}     *
${indent}     * @return This (fluent method).
${indent}     */
${indent}    public T with${field.camelCapitalizedName}(${field.typeSchema.persistedType} value)
${indent}    {
    <@checkFieldLimits "        " field "value"/>
${indent}      ${c}${field.camelName}_ = ${field.typeSchema.getConstructor("value")};
${indent}      return self();
${indent}    }

    </#if>
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
${indent}    public ${JsonDomNode} getJson()
${indent}    {
  <#list entity.fields as field>

${indent}      if(get${field.camelCapitalizedName}() != null)
${indent}      {
    <#if field.typeSchema.schemaType.isPrimitive>
${indent}        return ${JsonDomNode}.newInstance(get${field.camelCapitalizedName}());
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
${indent}    /**
${indent}     * Return any additional attributes.
${indent}     * 
${indent}     * @return any additional attributes.
${indent}     */
${indent}    public ${Map}<String, ${additionalType}> canonGetAdditionalProperties()
${indent}    {
${indent}       return <@namespace import="com.google.common.collect.ImmutableSortedMap"/>.copyOf(additionalProperties_);
${indent}    }

</#if>
<#if hasUnknownProperties>
${indent}    /**
${indent}     * Return any unknown attributes.
${indent}     * 
${indent}     * @return any unknown attributes.
${indent}     */
${indent}    public ${Map}<String, ${RuntimeEntity}> canonGetUnknownProperties()
${indent}    {
${indent}       return unknownProperties_;
${indent}    }

</#if>
${indent}    @Override
${indent}    public void validate(<@namespace import="com.symphony.oss.commons.fault.FaultAccumulator"/> faultAccumulator)
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