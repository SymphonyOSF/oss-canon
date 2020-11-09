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
<#if entity.hasLimits>
  <#assign imports = imports + [
    "com.symphony.oss.canon.json.ParserErrorException"
  ]>
</#if>
package ${genPackage};
  
<#list entity.sortImports(imports, genPackage) as import>
${import}
</#list>
<#include "TypeDefMacro.ftl"/>
<@generateTypeDef "" model entity className classModifier/>

<#include "/footer.ftl">