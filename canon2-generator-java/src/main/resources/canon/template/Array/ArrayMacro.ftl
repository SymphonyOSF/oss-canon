<#include "/macros.ftl"/>
<#macro generateArray indent model entity className classModifier nested>


// check needed
<#--  --@namespace name="jsonNodeType" import=entity.fullyQualifiedJsonNodeType/>
<@namespace name="javaType" import=entity.fullyQualifiedJavaType/>
<@namespace name="Nonnull" import="javax.annotation.Nonnull"/>
<@namespace name="ParserErrorException" import="com.symphony.oss.canon.json.ParserErrorException"/>
<@namespace name="superType" import=entity.fullyQualifiedSuperType/>
<@namespace name="jsonNodeType" import=entity.fullyQualifiedJsonNodeType/>
<#if hasUnknownProperties || entity.additionalPropertiesAllowed>
<@namespace name="Map" import="java.util.Map"/>
<@namespace name="HashMap" import="java.util.HashMap"/>
</#if -->
// end check needed



<#switch entity.cardinality>
  <#case "SET">
    <#assign javaCardinality = "Set">
    <#break>

  <#default>
    <#assign javaCardinality = "List">
</#switch>


<@namespace name="elementType" import=entity.fullyQualifiedElementType/>
<@namespace name="jsonInitialiserType" import="com.symphony.oss.canon.json.model.JsonArray"/>
<@namespace name="initialiserType" import=entity.fullyQualifiedInitialiserType/>
<@namespace name="collectionType" import=entity.fullyQualifiedCollectionType/>
<@namespace name="collectionImplType_" import=entity.fullyQualifiedCollectionImplType/>
<@namespace name="collectionImmutableType" import=entity.fullyQualifiedCollectionImmutableType/>
// collectionImmutableType = ${collectionImmutableType}
<@namespace name="RuntimeEntity" import="com.symphony.oss.canon2.runtime.java.Entity"/>
<@namespace name="superType" import="com.symphony.oss.canon2.runtime.java.${javaCardinality}ArrayEntity"/>
<@namespace name="ModelRegistry" import="com.symphony.oss.canon2.runtime.java.ModelRegistry"/>
${indent}/**
${indent} * Implementation for Array ${entity}
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
${indent} * Generated from ${entity}
${indent} */

// TTT5
${indent}@<@namespace import="javax.annotation.concurrent.Immutable"/>
// TTT6
${indent}public class ${className} extends ${superType}<${elementType}>
${indent}{
${indent}  /** Type ID */
${indent}  public static final String  TYPE_ID = "${model.canonId}.${entity.name}";
${indent}  /** Type version */
${indent}  public static final String  TYPE_VERSION = "${model.canonVersion}";
${indent}  /** Factory instance */
${indent}  public static final Factory FACTORY = new Factory();

${indent}  /**
${indent}   * Constructor.
${indent}   *
${indent}   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
${indent}   */
${indent}  public ${className}(Initialiser initialiser)
${indent}  {
${indent}    super(initialiser);
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
${indent}      if(node instanceof JsonArray)
${indent}      {
${indent}        return new ${entity.type}(new JsonInitialiser((JsonArray)node, modelRegistry));
${indent}      }

${indent}      if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
${indent}      {
${indent}        throw new ParserErrorException("${entity.name} must be an Array node not " + node.getClass().getName(), node.getContext());
${indent}      }
${indent}      else
${indent}      {
${indent}        return null;
${indent}      }
${indent}    }
${indent}  }

<#------------------------------------------------------------------------------------------------------------------------------

 Initialiser

------------------------------------------------------------------------------------------------------------------------------->
${indent}  /**
${indent}   * Abstract Initialiser for ${entity.type}
${indent}   */
${indent}  public interface Initialiser extends ${initialiserType}<${elementType}>
${indent}  {
${indent}    /**
${indent}     * Return an instance or builder containing the values for a new instance.
${indent}     * 
${indent}     * @return an instance or builder containing the values for a new instance.
${indent}     */
${indent}    ${initialiserType}<${elementType}> getInstanceOrBuilder();
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
${indent}    public JsonInitialiser(${jsonInitialiserType} json, ${ModelRegistry} modelRegistry)
${indent}    {
${indent}      super(json, modelRegistry);
${indent}    }

${indent}    @Override
${indent}    public ${initialiserType}<${elementType}> getInstanceOrBuilder()
${indent}    {
${indent}      return null;
${indent}    }
${indent}  }

<#------------------------------------------------------------------------------------------------------------------------------

 AbstractBuilder

------------------------------------------------------------------------------------------------------------------------------->
${indent}  /**
${indent}   * Builder for ${entity.type}.
${indent}   */
${indent}  public static class Builder extends ${superType}.AbstractBuilder<Builder,${className}>
${indent}    implements /*${initialiserType}<${elementType}>,*/ Initialiser
${indent}  {
${indent}    private ${elementType} elements_ =
    <#switch entity.cardinality>
      <#case "SET">
${indent}                                          new HashSet<>();
        <#break>

      <#default>
${indent}                                          new LinkedList<>();
    </#switch>


${indent}    protected Builder()
${indent}    {
${indent}    }

${indent}    protected Builder(Builder initial)
${indent}    {
${indent}      elements_.addAll(initial.elements_);
${indent}    }

${indent}    @Override
${indent}    public ${collectionImmutableType}<${elementType}> getElements()
${indent}    {
${indent}      return${collectionImmutableType}.copyOf(elements_);
${indent}    }

${indent}    @Override
${indent}    public int size()
${indent}    {
${indent}      return elements__.size();
${indent}    }

${indent}    public ${className}.Builder with(${elementType} element)
${indent}    {
${indent}      elements_.add(element);
${indent}      return (${className}.Builder)this;
${indent}    }

${indent}    public ${className}.Builder with(${className} elements)
${indent}    {
${indent}      elements__.addAll(elements.getElements());
${indent}      return (${className}.Builder)this;
${indent}    }

${indent}    public ${className}.Builder with(${jsonInitialiserType}<?> node)
${indent}    {
${indent}      elements__.addAll(node.asImmutableListOf(${elementType}.class));
${indent}      return (${className}.Builder)this;
${indent}    }

${indent}    @Override 
${indent}    public ImmutableJson${javaCardinality} getJson${javaCardinality}()
${indent}    {
${indent}      MutableJson${javaCardinality} jsonArray = new MutableJson${javaCardinality}();

${indent}      for(${elementType} value : elements__)
// entity.class ${entity.class}
// entity.name ${entity.name}
// entity.elementType.class ${entity.elementType.class}
      <#if entity.elementType.isObjectType>
${indent}        jsonArray.add(value.getJsonObject());
      <#else>
${indent}        jsonArray.add(value);
      </#if>

${indent}      return jsonArray.immutify();
${indent}    }

${indent}    @Override
${indent}    public IImmutableJsonDomNode getJsonDomNode()
${indent}    {
${indent}      return getJson${javaCardinality}();
${indent}    }

${indent}    public abstract ${className} build();
${indent}  }
}
</#macro>