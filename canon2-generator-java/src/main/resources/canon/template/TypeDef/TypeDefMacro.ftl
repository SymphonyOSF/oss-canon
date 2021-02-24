<#macro generateTypeDef indent model entity className classModifier>
<@namespace name="Nonnull" import="javax.annotation.Nonnull"/>
${indent}/**
${indent} * TypeDef implementation for ${model.name}.${entity.name}
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
${indent}public ${classModifier}class ${className} extends <@namespace import="com.symphony.oss.canon2.runtime.java.TypeDef"/> implements Comparable<${className}>
${indent}{
  <#if entity.minimum??>
${indent}  /** Minimum value */
${indent}  public static final ${entity.javaType?right_pad(20)} MINIMUM           = ${entity.constructConstant(entity.minimum)};
${indent}  /** Minimum value is exclusive */
    <#if entity.exclusiveMinimum>
${indent}  public static final boolean              EXCLUSIVE_MINIMUM = true;
    <#else>
${indent}  public static final boolean              EXCLUSIVE_MINIMUM = false;
    </#if>
  </#if>
  <#if entity.maximum??>
${indent}  /** Maximum value */
${indent}  public static final ${entity.javaType?right_pad(20)} MAXIMUM           = ${entity.constructConstant(entity.maximum)};
${indent}  /** Maximum value is exclusive */
    <#if entity.exclusiveMaximum>
${indent}  public static final boolean              EXCLUSIVE_MAXIMUM = true;
    <#else>
${indent}  public static final boolean              EXCLUSIVE_MAXIMUM = false;
    </#if>
${indent}  
  </#if>
${indent}  @Deprecated
${indent}  private static Builder theBuilder = new Builder();

${indent}  private final ${entity.javaType} value_;

${indent}  /**
${indent}   * Constructor from a ${entity.javaType} value.
${indent}   *
${indent}   * @param value the value of the required instance.
${indent}   */
${indent}  public ${className}(@${Nonnull} ${entity.javaType} value)
${indent}  {
${indent}    value_ = <@namespace import="java.util.Objects"/>.requireNonNull(value);
    <@checkLimits "    " entity "value" "value" "new IllegalArgumentException" ""/>
${indent}  }

${indent}  /**
${indent}   * Constructor from a JSON value node.
${indent}   *
${indent}   * @param value the value of the required instance.
${indent}   */
${indent}  ${className}(@${Nonnull} <@namespace import="com.symphony.oss.canon.json.model.JsonDomNode"/> node)
${indent}  {
${indent}    Objects.requireNonNull(node);

${indent}    if(node instanceof ${entity.jsonNodeType})
${indent}    {
${indent}      value_ = ${entity.getValueConstructor("((${entity.jsonNodeType})node).as${entity.simplePersistedType}()")};
      <#if entity.hasLimits>

        <@namespace name="ParserErrorException" import="com.symphony.oss.canon.json.ParserErrorException"/>
        <@checkLimits "${indent}      " entity "value" "value_" "new ${ParserErrorException}" ", node.getContext()"/>
      </#if>
${indent}    }
${indent}    else
${indent}    {
${indent}      throw new IllegalArgumentException("value must be an instance of ${entity.jsonNodeType} not " + node.getClass().getName());
${indent}    }
${indent}  }
${indent}  
${indent}  /**
${indent}   * Return the wrapped value.
${indent}   * 
${indent}   * @return the wrapped value.
${indent}   */
${indent}  public ${entity.javaType} getValue()
${indent}  {
${indent}    return value_;
${indent}  }
${indent}  
${indent}  @Override
${indent}  public @${Nonnull} String toString()
${indent}  {
${indent}    return value_.toString();
${indent}  }
${indent}  
${indent}  @Override
${indent}  public int hashCode()
${indent}  {
${indent}    return value_.hashCode();
${indent}  }
${indent}  
${indent}  /**
${indent}   * Return the value as a ${entity.javaType}.
${indent}   * 
${indent}   * @return the value as a ${entity.javaType}.
${indent}   */
${indent}  public @${Nonnull} ${entity.javaType} as${entity.simplePersistedType}()
${indent}  {
${indent}    return value_;
${indent}  }

${indent}  @Override
${indent}  public boolean equals(Object obj)
${indent}  {
${indent}    if(obj instanceof ${className})
${indent}    {
${indent}      return getValue().equals(((${className})obj).getValue());
${indent}    }
${indent}    
${indent}    return false;
${indent}  }
${indent}  
${indent}  @Override
${indent}  public int compareTo(${className} other)
${indent}  {
${indent}    return getValue().compareTo(other.getValue());
${indent}  }
${indent}  
${indent}  /**
${indent}   * Return a new Builder.
${indent}   *
${indent}   * @deprecated use new ${className}(${entity.javaType} value)
${indent}   *
${indent}   * @return A new Builder.
${indent}   */
${indent}  @Deprecated
${indent}  public static Builder newBuilder()
${indent}  {
${indent}    return theBuilder;
${indent}  }
${indent}  
${indent}  /**
${indent}   * Builder.
${indent}   *
${indent}   * @deprecated use new ${entity.type}(${entity.javaType} value)
${indent}   *
${indent}   */
${indent}  @Deprecated
${indent}  public static class Builder
${indent}  {
${indent}    private Builder()
${indent}    {
${indent}    }

${indent}    /**
${indent}     * Return a new instance.
${indent}     *
${indent}     * @param value The value for the new instance.
${indent}     *
${indent}     * @deprecated use new ${entity.type}(${entity.javaType} value)
${indent}     *
${indent}     * @return A new instance.
${indent}     */
${indent}    @Deprecated
${indent}    public ${entity.type} build(@${Nonnull} ${entity.javaType} value)
${indent}    {
${indent}      return new ${entity.type}(value);
${indent}    }
${indent}    
${indent}    /**
${indent}     * Return the value from an instance.
${indent}     *
${indent}     * @param instance an instance.
${indent}     *
${indent}     * @deprecated use instance.getValue()
${indent}     *
${indent}     * @return the value from an instance.
${indent}     */
${indent}    @Deprecated
${indent}    public ${entity.javaType} toValue(${className} instance)
${indent}    {
${indent}      return instance.getValue();
${indent}    }
${indent}  }
${indent}}
</#macro>