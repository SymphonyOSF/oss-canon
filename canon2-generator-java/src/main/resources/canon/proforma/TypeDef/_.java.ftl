<#if !entity.fullyQualifiedExternalType?? && entity.generateFacade>
<#assign className = "${entity.camelCapitalizedName}">
  <#include "TypeDef.ftl"/>
</#if>