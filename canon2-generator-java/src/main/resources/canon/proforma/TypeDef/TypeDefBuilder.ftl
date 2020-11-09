<#include "/copyrightHeader.ftl"/>
<#include "/macros.ftl"/>
<#assign imports = entity.imports + [
  "${entity.externalPackage}.${entity.externalType}",
  "javax.annotation.concurrent.Immutable",
  "javax.annotation.Nonnull"
  ]>

package ${genPackage};

<#list entity.sortImports(imports, genPackage) as import>
${import}
</#list>

// entity.class ${entity.class}
// entity.name ${entity.name}
/**
 * Builder helper function for the external type ${entity.externalType}.
 */
@Immutable
public class ${entity.externalType}Builder
{
  /**
   * Constructor from a ${entity.javaType} value.
   *
   * @param value the value of the required instance.
   * 
   * @return A ${entity.externalType} deserialized from the given ${entity.javaType} value.
   */
  public static ${entity.externalType} build(@Nonnull ${entity.javaType} value)
  {
    return ${entity.externalType}.parse(value);
  }
  
  /**
   * Return the serialized form of the given ${entity.externalType} value.
   * 
   * @param instance An Instant value.
   * 
   * @return The serialized form of the given ${entity.externalType} value.
   */
  public static String to${entity.javaType}(${entity.externalType} instance)
  {
    return instance.toString();
  }
}
<#include "/footer.ftl">