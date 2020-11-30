<#if entity.generateFacade && !entity.externalType??>
<#include "/copyrightHeader.ftl"/>
<#assign className = "${entity.type}${c}TypeDef">
<#assign classModifier = "abstract ">
<#include "TypeDef.ftl"/>
</#if>
