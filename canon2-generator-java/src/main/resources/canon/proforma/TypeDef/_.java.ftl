<#if !entity.fullyQualifiedExternalType?? && entity.generateFacade>
<#assign className = "${entity.type}">
  <#include "TypeDef.ftl"/>
</#if>