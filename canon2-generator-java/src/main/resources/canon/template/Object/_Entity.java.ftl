<#if entity.generateFacade>
<#assign className = "${entity.type}Entity">
<#assign classModifier = "abstract ">
<#include "Object.ftl"/>
</#if>
