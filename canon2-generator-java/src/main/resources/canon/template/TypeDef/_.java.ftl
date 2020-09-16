<#if !entity.generateFacade && !entity.externalType??>
<#assign className = "${entity.type}">
<#assign classModifier = "">
<#include "TypeDef.ftl"/>
</#if>