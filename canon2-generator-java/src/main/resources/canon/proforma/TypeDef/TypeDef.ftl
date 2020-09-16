<#include "/copyrightHeader.ftl"/>
<#include "/macros.ftl"/>
<#assign imports = entity.imports + [
  "javax.annotation.concurrent.Immutable",
  "javax.annotation.Nonnull"
  "com.symphony.oss.commons.type.provider.IValueProvider"
  ]>

package ${genPackage};

<#list entity.sortImports(imports) as import>
${import}
</#list>
/**
 * TypeDef facade for ${model.name}.${entity.name}
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
public class ${entity.type} extends ${entity.type}TypeDef
{
  /**
   * Constructor from a ${entity.javaType} value.
   *
   * @param value the value of the required instance.
   */
  public ${entity.type}(@Nonnull ${entity.javaType} value)
  {
    super(value);
  }

  /**
   * Constructor from a JSON value node.
   *
   * @param value the value of the required instance.
   */
  ${entity.type}(@Nonnull IValueProvider node)
  {
    super(node);
  }
}
<#include "/footer.ftl">