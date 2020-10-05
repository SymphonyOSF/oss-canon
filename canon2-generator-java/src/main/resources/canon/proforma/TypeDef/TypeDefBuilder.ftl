<#include "/copyrightHeader.ftl"/>
<#include "/macros.ftl"/>
<#assign imports = entity.imports + [
  "${entity.externalPackage}.${entity.externalType}",
  "${entity.fullyQualifiedJsonNodeType}",
  "javax.annotation.concurrent.Immutable",
  "javax.annotation.Nonnull"
  ]>

package ${genPackage};

<#list entity.sortImports(imports) as import>
${import}
</#list>

@Immutable
public class ${entity.type}Builder
{
  /**
   * Constructor from a ${entity.javaType} value.
   *
   * @param value the value of the required instance.
   * 
   * @return A ${entity.javaType} deserialized from the given ${entity.type} value.
   */
  public static ${entity.type} build(@Nonnull ${entity.javaType} value)
  {
    return ${entity.type}.parse(value);
  }
  
  /**
   * Return the serialized form of the given ${entity.type} value.
   * 
   * @param instance An Instant value.
   * 
   * @return The serialized form of the given ${entity.type} value.
   */
  public static String to${entity.javaType}(${entity.type} instance)
  {
    return instance.toString();
  }
}
<#include "/footer.ftl">