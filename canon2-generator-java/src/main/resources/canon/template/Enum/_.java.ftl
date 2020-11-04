<#include "/copyrightHeader.ftl"/>
<#include "/macros.ftl"/>
<#assign imports = entity.imports + [
  "javax.annotation.concurrent.Immutable"
  ]>
package ${genPackage};

<#list entity.sortImports(imports, genPackage) as import>
${import}
</#list>
/**
 * Enum  ${entity.name} ${model.name}
 * Model ${model}
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
public enum ${entity.type}
{
<#list entity.enumValues as name>
  /** ${name} */
  ${name}(${entity.enumMap[name]})<#sep>,</#sep>
</#list>  ;
  
  private final ${entity.javaType} value_;
  
  private ${entity.type}(${entity.javaType} value)
  {
    value_ = value;
  }
  
  /**
   * Return the serialized value of this enum constant.
   *
   * @return the serialized value of this enum constant.
   */
  public ${entity.javaType} getValue()
  {
    return value_;
  }
  
  /**
   * Deserialize an enum constant value from a ${entity.javaType} value.
   * 
   * @param value The serialized form of an enum constant.
   * 
   * @return The enum constant value from the given ${entity.javaType} value.
   * 
   * @throws IllegalArgumentException If the given value is not a valid enum constant.
   */
  public static final ${entity.type} deserialize(${entity.javaType} value)
  {
    switch(value)
    {
<#list entity.enumValues as name>
      case ${entity.enumMap[name]}:
        return ${name};
        
</#list>
      default:
        throw new IllegalArgumentException("No enum constant \"" + value + "\" in ${entity.type}");
    }
  }
}
<#include "/footer.ftl">