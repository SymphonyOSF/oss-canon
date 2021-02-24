<#include "/copyrightHeader.ftl"/>
<#include "InstanceOrBuilder.ftl"/>
<#assign imports = entity.imports + [
  "com.symphony.oss.canon2.runtime.java.IObjectEntityInitialiser"
  ]>
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

<#list entity.sortImports(imports) as import>
${import}
</#list>
<@generateInstanceOrBuilder "" entity/>
<#include "/footer.ftl">