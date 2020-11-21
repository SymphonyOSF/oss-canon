<#if entity.generateFacade>
<#assign className = "${entity.type}${c}Entity">
<#assign classModifier = "abstract ">
<#include "Object.ftl"/>
</#if>
