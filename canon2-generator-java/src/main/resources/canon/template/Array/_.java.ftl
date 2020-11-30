<#if !entity.generateFacade>
// TTT1
<#include "/copyrightHeader.ftl"/>
// TTT2
<#assign className = "${entity.type}">
<#assign classModifier = "">
// TTT3
<#include "ArrayMacro.ftl"/>
// TTT4
<@generateArray "" model entity className classModifier false/>
<#include "/footer.ftl">
</#if>
