<#if entity.generateFacade && !entity.externalType??>
<#assign className = "${entity.type}TypeDef">
<#assign classModifier = "abstract ">
<#include "TypeDef.ftl"/>
</#if>
