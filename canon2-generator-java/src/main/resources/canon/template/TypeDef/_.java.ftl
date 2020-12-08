<#if !entity.generateFacade>
<#if entity.fullyQualifiedExternalType??>
// fullyQualifiedExternalType=${entity.fullyQualifiedExternalType}
<#else>
// fullyQualifiedExternalType UNDEFINED
</#if>
<#include "/copyrightHeader.ftl"/>
<#assign className = "${entity.camelCapitalizedName}">
<#assign classModifier = "">
<#include "TypeDef.ftl"/>
</#if>