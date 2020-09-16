<#if entity.externalType??>
    <#include "TypeDefBuilder.ftl"/>
<#else>
  <#if entity.generateFacade>
    <#include "TypeDef.ftl"/>
  </#if>
</#if>