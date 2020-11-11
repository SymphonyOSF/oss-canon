<#macro generateEnum indent model entity className classModifier>
${indent}/**
${indent} * Enum  ${entity.name} ${model.name}
${indent} * Model ${model}
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
${indent}public ${classModifier}enum ${className}
${indent}{
<#list entity.enumValues as name>
${indent}  /** ${name} */
${indent}  ${name}(${entity.enumMap[name]})<#sep>,</#sep>
</#list>  ;
${indent}  
${indent}  private final ${entity.javaType} value_;
${indent}  
${indent}  private ${className}(${entity.javaType} value)
${indent}  {
${indent}    value_ = value;
${indent}  }
${indent}  
${indent}  /**
${indent}   * Return the serialized value of this enum constant.
${indent}   *
${indent}   * @return the serialized value of this enum constant.
${indent}   */
${indent}  public ${entity.javaType} getValue()
${indent}  {
${indent}    return value_;
${indent}  }
${indent}  
${indent}  /**
${indent}   * Deserialize an enum constant value from a ${entity.javaType} value.
${indent}   * 
${indent}   * @param value The serialized form of an enum constant.
${indent}   * 
${indent}   * @return The enum constant value from the given ${entity.javaType} value.
${indent}   * 
${indent}   * @throws IllegalArgumentException If the given value is not a valid enum constant.
${indent}   */
${indent}  public static final ${className} deserialize(${entity.javaType} value)
${indent}  {
${indent}    switch(value)
${indent}    {
<#list entity.enumValues as name>
${indent}      case ${entity.enumMap[name]}:
${indent}        return ${name};
${indent}        
</#list>
${indent}      default:
${indent}        throw new IllegalArgumentException("No enum constant \"" + value + "\" in ${className}");
${indent}    }
${indent}  }
${indent}}
</#macro>