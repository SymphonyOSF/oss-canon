<#if entity.generateFacade>
<#include "/copyrightHeader.ftl"/>
<#assign className = "${entity.type}${c}EntityArray">
<#assign classModifier = "abstract ">
<#include "ArrayMacro.ftl"/>
<@generateArray "" model entity className classModifier false/>
<#include "/footer.ftl">
</#if>
