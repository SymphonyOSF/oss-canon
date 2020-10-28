<#include "/copyrightHeader.ftl"/>
<#include "/macros.ftl"/>
<#assign imports = entity.imports + [
  "javax.annotation.concurrent.Immutable",
  "javax.annotation.Nonnull",
  "java.util.Objects",
  "com.symphony.oss.canon2.runtime.java.TypeDef",
    "com.symphony.oss.canon.json.model.JsonDomNode",
    "${entity.fullyQualifiedJsonNodeType}"
  ]>

package ${genPackage};
  
<#list entity.sortImports(imports) as import>
${import}
</#list>
/**
 * TypeDef implementation for ${model.name}.${entity.name}
<#if entity.summary??>
 *
 * ${entity.summary}
</#if>
<#if entity.description??>
 *
<#list entity.description as description>
 * ${description}
</#list>
</#if>
 * Generated from ${entity} at {entity.context.path}
 */
@Immutable
public ${classModifier}class ${className} extends TypeDef implements Comparable<${className}>
{
  @Deprecated
  private static Builder theBuilder = new Builder();
  
  private final ${entity.javaType} value_;

  /**
   * Constructor from a ${entity.javaType} value.
   *
   * @param value the value of the required instance.
   */
  public ${className}(@Nonnull ${entity.javaType} value)
  {
    value_ = Objects.requireNonNull(value);
    <@checkLimits "    " entity "value" "value" "new new ParserException" ", value.getContext()"/>
  }

  /**
   * Constructor from a JSON value node.
   *
   * @param value the value of the required instance.
   */
  ${className}(@Nonnull JsonDomNode node)
  {
    Objects.requireNonNull(node);

    if(node instanceof ${entity.jsonNodeType})
    {
      value_ = ((${entity.jsonNodeType})node).as${entity.javaType}();
      <@checkLimits "    " entity "value" "value_" "new new ParserException" ", node.getContext()"/>
    }
    else
    {
      throw new IllegalArgumentException("value must be an instance of ${entity.jsonNodeType} not " + node.getClass().getName());
    }
  }
  
  /**
   * Return the wrapped value.
   * 
   * @return the wrapped value.
   */
  public ${entity.javaType} getValue()
  {
    return value_;
  }
  
  @Override
  public @Nonnull String toString()
  {
    return value_.toString();
  }
  
  @Override
  public int hashCode()
  {
    return value_.hashCode();
  }
  
  /**
   * Return the value as a ${entity.javaType}.
   * 
   * @return the value as a ${entity.javaType}.
   */
  public @Nonnull ${entity.javaType} as${entity.javaType}()
  {
    return value_;
  }

  @Override
  public boolean equals(Object obj)
  {
    if(obj instanceof ${className})
    {
      return getValue().equals(((${className})obj).getValue());
    }
    
    return false;
  }
  
  @Override
  public int compareTo(${className} other)
  {
    return getValue().compareTo(other.getValue());
  }
  
  /**
   * Return a new Builder.
   *
   * @deprecated use new ${className}(${entity.javaType} value)
   *
   * @return A new Builder.
   */
  @Deprecated
  public static Builder newBuilder()
  {
    return theBuilder;
  }
  
  /**
   * Builder.
   *
   * @deprecated use new ${className}(${entity.javaType} value)
   *
   */
  @Deprecated
  public static class Builder
  {
    private Builder()
    {
    }

    /**
     * Return a new instance.
     *
     * @param value The value for the new instance.
     *
     * @deprecated use new ${className}(${entity.javaType} value)
     *
     * @return A new instance.
     */
    @Deprecated
    public ${entity.type} build(@Nonnull ${entity.javaType} value)
    {
      return new ${entity.type}(value);
    }
    
    /**
     * Return the value from an instance.
     *
     * @param instance an instance.
     *
     * @deprecated use instance.getValue()
     *
     * @return the value from an instance.
     */
    @Deprecated
    public ${entity.javaType} toValue(${className} instance)
    {
      return instance.getValue();
    }
  }
}

<#include "/footer.ftl">