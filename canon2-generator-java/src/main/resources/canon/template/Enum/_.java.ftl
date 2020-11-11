<#include "/copyrightHeader.ftl"/>
<#include "/macros.ftl"/>
<#assign imports = entity.imports + [
  "javax.annotation.concurrent.Immutable"
  ]>
package ${genPackage};

<#list entity.sortImports(imports, genPackage) as import>
${import}
</#list>
<#include "EnumMacro.ftl"/>
<@generateEnum "" model entity entity.type ""/>
<#include "/footer.ftl">