<#if !entity.generateFacade && !entity.externalType??>
<#include "/copyrightHeader.ftl"/>
<#assign className = "${entity.type}">
<#assign classModifier = "">
<#include "TypeDef.ftl"/>
</#if>