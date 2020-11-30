<#include "/copyrightHeader.ftl"/>
<#include "/macros.ftl"/>
<@namespace name="javaType" import=entity.fullyQualifiedJavaType/>
<@namespace name="Nonnull" import="javax.annotation.Nonnull"/>
<@namespace name="JsonDomNode" import="com.symphony.oss.canon.json.model.JsonDomNode"/>
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
@<@namespace import="javax.annotation.concurrent.Immutable"/>
public class ${entity.type} extends ${entity.type}${c}TypeDef
{
  /**
   * Constructor from a ${javaType} value.
   *
   * @param value the value of the required instance.
   */
  public ${entity.type}(@${Nonnull} ${javaType} value)
  {
    super(value);
  }

  /**
   * Constructor from a JSON value node.
   *
   * @param node the value of the required instance.
   */
  ${entity.type}(@${Nonnull} ${JsonDomNode} node)
  {
    super(node);
  }
}
<#include "/footer.ftl">