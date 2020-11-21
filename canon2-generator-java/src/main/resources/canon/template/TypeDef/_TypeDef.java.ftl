<#if entity.generateFacade && !entity.externalType??>
<#assign className = "${entity.type}${c}TypeDef">
<#assign classModifier = "abstract ">
<#include "TypeDef.ftl"/>
</#if>
