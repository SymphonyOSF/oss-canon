<#if entity.generateFacade>
<#include "/copyrightHeader.ftl"/>
<#assign className = "${entity.type}${c}Entity">
<#assign classModifier = "abstract ">
<#include "Object.ftl"/>
</#if>
