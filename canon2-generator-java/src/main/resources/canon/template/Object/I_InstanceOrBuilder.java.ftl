<#include "/copyrightHeader.ftl"/>
<#include "InstanceOrBuilder.ftl"/>
<#if entity.isObjectType>
  <#assign hasUnknownProperties = true/>
  <#if entity.additionalPropertiesAllowed>
    <#if entity.additionalProperties??>
      <#if entity.additionalPropertiesIsInnerClass>
        <#assign additionalType = "${entity.camelCapitalizedName}.${c}AdditionalProperties"/>
      <#else>
        <@namespace name="additionalType" import=entity.additionalProperties.fullyQualifiedType/>
      </#if>
    <#else>
      <#assign hasUnknownProperties = false/>
      <@namespace name="additionalType" import="com.symphony.oss.canon2.runtime.java.Entity"/>
    </#if>
  </#if>
<#else>
  <#assign hasUnknownProperties = false/>
</#if>

<@generateInstanceOrBuilder "" entity/>
<#include "/footer.ftl">