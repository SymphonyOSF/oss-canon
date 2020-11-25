<#if !entity.externalType?? && entity.generateFacade>
<#assign className = "${entity.type}">
  <#include "TypeDef.ftl"/>
</#if>