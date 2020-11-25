<#include "/copyrightHeader.ftl"/>
<#include "InstanceOrBuilder.ftl"/>
<#assign imports = entity.imports + [
  "com.symphony.oss.canon2.runtime.java.I${entity.initialiserType}"
  ]>
<#if entity.isObjectType>
  <#assign hasUnknownProperties = true/>
  <#if entity.additionalPropertiesAllowed>
    <#if entity.additionalProperties??>
    //tr 1
      <#if entity.additionalPropertiesIsInnerClass>
      //tr 2
        <#assign additionalType = "${entity.camelCapitalizedName}.${c}AdditionalProperties"/>
      <#else>
      // tr 3
        <#assign additionalType = "${entity.additionalProperties.type}"/>
        // add ${entity.additionalProperties.import}
        <#assign imports = imports + [
          "${entity.additionalProperties.import}"
          ]>
      </#if>
    <#else>
      <#assign hasUnknownProperties = false/>
      <#assign additionalType = "Entity"/>
    </#if>
  </#if>
<#else>
  <#assign hasUnknownProperties = false/>
</#if>

<#if hasUnknownProperties || entity.additionalPropertiesAllowed>
  <#assign imports = imports + [
    "java.util.Map",
    "com.symphony.oss.canon2.runtime.java.Entity"
    ]>
</#if>
<#macro importType schema>
  <#if schema.externalType??>
    <#assign imports = imports + [
      "${schema.externalPackage}.${schema.externalType}"
    ]>
  </#if>
</#macro>
<#macro importArrayJsonType schema>
  <#switch schema.schemaType>
    <#case "ARRAY">
      <#switch schema.cardinality>
        <#case "LIST">
          <#assign imports = imports + [
            "java.util.List"
          ]>
          <#break>
        <#case "SET">
          <#assign imports = imports + [
            "java.util.Set"
          ]>
          <#break>
      </#switch>
      <@importArrayJsonType schema.elementType />
      <#break>
    <#case "OBJECT">
      <#break>
    <#default>
    <@importType schema />
  </#switch>
</#macro>
<#list entity.fields as field>
  <#assign imports = imports + [
      "javax.annotation.${field.nullable}"
    ]>
  <@importType field.typeSchema />
  <#if field.typeSchema.schemaType == "ARRAY">
    <@importArrayJsonType field.typeSchema />
  </#if>
</#list>

package ${genPackage};

<#list entity.sortImports(imports, genPackage) as import>
${import}
</#list>
<@generateInstanceOrBuilder "" entity/>
<#include "/footer.ftl">