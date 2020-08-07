<#assign subTemplateName="${.current_template_name!''}"><#include "/template/ts/canon-template-ts-SubPrologue.ftl">
<#include "/template/ts/TypeDefHeader.ftl">
<@setJavaType model/>
export enum ${modelJavaClassName}
{
  <#list model.enum.values as value>${value}<#sep>, </#list>;
<#assign subTemplateName="${.current_template_name!''}"><#include "/template/ts/canon-template-ts-SubEpilogue.ftl">