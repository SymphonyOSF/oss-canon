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


<@namespace name="JsonDomNode" import="com.symphony.oss.canon.json.model.JsonDomNode"/>
<@namespace name="jsonNodeType" import="com.symphony.oss.canon.json.model.JsonArray"/>
<@namespace name="RuntimeEntity" import="com.symphony.oss.canon2.runtime.java.Entity"/>
<@namespace name="ModelRegistry" import="com.symphony.oss.canon2.runtime.java.ModelRegistry"/>
<#switch entity.cardinality>
  <#case "SET">
    <@namespace name="superType" import="com.symphony.oss.canon2.runtime.java.SetArrayEntity"/>
    <#break>

  <#default>
    <@namespace name="superType" import="com.symphony.oss.canon2.runtime.java.ListArrayEntity"/>
</#switch>
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
${indent}public ${classModifier}class ${className} extends ${superType}<${entity.element.type}>
${indent}{
${indent}  /** Type ID */
${indent}  public static final String  TYPE_ID = "${model.canonId}.${entity.camelCapitalizedName}";
${indent}  /** Type version */
${indent}  public static final String  TYPE_VERSION = "${model.canonVersion}";
${indent}  /** Factory instance */
${indent}  public static final Factory FACTORY = new Factory();

${indent}  private ${entity.collectionType}<${entity.element.type}> elements_;

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

${indent}      ${ModelRegistry} modelRegistry = initialiser.getModelRegistry();
${indent}      ${entity.collectionType}<${entity.element.type}> elements = new ${entity.collectionImplType}<>();

${indent}      for(${JsonDomNode} node : initialiser.getJson())
${indent}      {
${indent}        ${entity.element.type} element;

    <@generateCreateFieldFromJsonDomNode "${indent}        " "node" entity.schemaType entity.element "element" "element" ""/>
${indent}        elements.add(element);
${indent}      }

${indent}      elements_ = ${entity.collectionImmutableType}.copyOf(elements);
${indent}    }
${indent}    else
${indent}    {
${indent}      AbstractBuilder<?,?> builder =  initialiser.getBuilder();

${indent}      elements_ = ${entity.collectionImmutableType}.copyOf(builder.getElements());
${indent}    }
${indent}  }

<#------------------------------------------------------------------------------------------------------------------------------

 Factory

------------------------------------------------------------------------------------------------------------------------------->

${indent}  /**
${indent}   * Factory class for ${entity.type}.
${indent}   */
${indent}  public static class Factory extends ${superType}.Factory<${entity.element.type}, ${entity.type}>
${indent}  {
${indent}    @Override
${indent}    public String getCanonType()
${indent}    {
${indent}      return TYPE_ID;
${indent}    }

${indent}    @Override
${indent}    public ${entity.type} newInstance(JsonDomNode node, ${ModelRegistry} modelRegistry)
${indent}    {
${indent}      if(node instanceof ${jsonNodeType})
${indent}      {
${indent}        return new ${entity.type}(new JsonInitialiser((${jsonNodeType})node, modelRegistry));
${indent}      }

${indent}      if(!modelRegistry.getParserValidation().isIgnoreInvalidAttributes())
${indent}      {
${indent}        throw new ParserErrorException("${entity.camelCapitalizedName} must be an Array node not " + node.getClass().getName(), node.getContext());
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
${indent}  public static interface Initialiser extends ${entity.initialiserType}<${entity.element.type}>
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
${indent}  public static class JsonInitialiser extends <@namespace import="com.symphony.oss.canon2.runtime.java.JsonArrayEntityInitialiser"/> implements ${entity.jsonInitialiserType}, Initialiser
${indent}  {
${indent}    private ${jsonNodeType} json_;

${indent}      /**
${indent}       * Constructor.
${indent}       * 
${indent}       * @param json            JSON serialised form.
${indent}       * @param modelRegistry   A parser context for deserialisation.
${indent}       */
${indent}    public JsonInitialiser(${jsonNodeType} json, ${ModelRegistry} modelRegistry)
${indent}    {
${indent}      super(json, modelRegistry);

${indent}      json_ = json;
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

${indent}    @Override
${indent}    public ${entity.collectionImmutableType}<Entity> getCanonUnknownElements()
${indent}    {
${indent}      return ${entity.collectionImmutableType}.of();
${indent}    }

${indent}    @Override
${indent}    public ${entity.collectionImmutableType}<${entity.element.type}> getElements()
${indent}    {
${indent}      // TODO Auto-generated method stub
${indent}      return null;
${indent}    }

${indent}    @Override
${indent}    public ${jsonNodeType} getJson()
${indent}    {
${indent}      return json_;
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
${indent}    extends ${superType}.AbstractBuilder<${entity.element.type},T,B>
${indent}    implements Initialiser
${indent}  {
${indent}    private ${entity.collectionType}<${entity.element.type}> elements_ = new ${entity.collectionImplType}<>();

${indent}    protected AbstractBuilder(Class<T> type)
${indent}    {
${indent}      super(type);
${indent}    }

${indent}    protected AbstractBuilder(Class<T> type, B initial)
${indent}    {
${indent}      super(type, initial);
${indent}      elements_.addAll(initial.getElements());
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

${indent}    @Override
${indent}    public ${entity.collectionImmutableType}<${entity.element.type}> getElements()
${indent}    {
${indent}      return ${entity.collectionImmutableType}.copyOf(elements_);
${indent}    }

${indent}    /**
${indent}     * Return the size of this array.
${indent}     *
${indent}     * @return the size of this array.
${indent}     */
${indent}    public int size()
${indent}    {
${indent}      return elements_.size();
${indent}    }

${indent}    /**
${indent}     * Add the given element to this array.
${indent}     *
${indent}     * @param element The element to be added.
${indent}     *
${indent}     * @return this (fluent method).
${indent}     */
${indent}    public T with(${entity.element.type} element)
${indent}    {
${indent}      elements_.add(element);
${indent}      return self();
${indent}    }

${indent}    /**
${indent}     * Add all of the elements in the given array to this array.
${indent}     *
${indent}     * @param elements The elements to be added.
${indent}     *
${indent}     * @return this (fluent method).
${indent}     */
${indent}    public T with(${className} elements)
${indent}    {
${indent}      elements_.addAll(elements.getElements());
${indent}      return self();
${indent}    }

${indent}    @Override
${indent}    protected void populateJson(${jsonNodeType}.Builder builder)
${indent}    {
// entity ${entity.class}
// entity.element ${entity.element.class}
${indent}      for(${entity.element.type} element : getElements())
${indent}        builder.with(${entity.element.getPersistedValue("element")});
${indent}    }
/*
${indent}    public T with(${entity.jsonInitialiserType}<?> node)
${indent}    {
${indent}      elements_.addAll(node.asImmutableListOf(${entity.element.type}.class));
${indent}      return self();
${indent}    }

${indent}    @Override 
${indent}    public ImmutableJson${entity.collectionType} getJson${entity.simpleCollectionType}()
${indent}    {
${indent}      MutableJson${entity.collectionType} jsonArray = new MutableJson${entity.collectionType}();

${indent}      for(${entity.element.type} value : elements__)
      <#if entity.element.isObjectType>
${indent}        jsonArray.add(value.getJsonObject());
      <#else>
${indent}        jsonArray.add(value);
      </#if>

${indent}      return jsonArray.immutify();
${indent}    }

${indent}    @Override
${indent}    public IImmutableJsonDomNode getJsonDomNode()
${indent}    {
${indent}      return getJson${entity.simpleCollectionType}();
${indent}    }
*/
${indent}  }

<#------------------------------------------------------------------------------------------------------------------------------

 Builder

------------------------------------------------------------------------------------------------------------------------------->
${indent}  /**
${indent}   * Builder for ${entity.type}.
${indent}   */
${indent}  public static class Builder extends AbstractBuilder<Builder, ${entity.type}>
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
${indent}    public ${className} construct()
${indent}    {
${indent}      return new ${className}(this);
${indent}    }
${indent}  }

${indent}  /**
${indent}   * Return the size of this array.
${indent}   *
${indent}   * @return the size of this array.
${indent}   */
${indent}  public int size()
${indent}  {
${indent}    return elements_.size();
${indent}  }

${indent}  /**
${indent}   * Return the elements of this array.
${indent}   *
${indent}   * @return the elements of this array.
${indent}   */
${indent}  public ${entity.collectionType}<${entity.element.type}> getElements()
${indent}  {
${indent}    return elements_;
${indent}  }
// INNER TRACE
<#list entity.innerClasses as innerClass>
// INNER CLASS ${innerClass.camelCapitalizedName}
  <@generateInnerClass indent innerClass innerClass.camelCapitalizedName classModifier nested/>
</#list>
}
</#macro>