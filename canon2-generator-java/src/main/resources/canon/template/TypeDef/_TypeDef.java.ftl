<#if entity.generateFacade && !entity.fullyQualifiedExternalType??>
<#include "/copyrightHeader.ftl"/>
<#assign className = "${entity.type}${c}TypeDef">
<#assign classModifier = "abstract ">
<#include "TypeDef.ftl"/>
</#if>
