<#assign subTemplateName="${.current_template_name!''}"><#include "/template/ts/canon-template-ts-SubPrologue.ftl">
/**
<#if isFacade??>
 * Facade for ${model}
<#else>
 * ${model}
</#if>
<#if model.summary??>
 *
 * ${model.summary}
</#if>
<#if model.description??>
 *
<#list model.description as description>
 * ${description}
</#list>
</#if>
 *
 * Path					${model.path}
 * Bind Path			${model.bindPath}
 */
<#assign subTemplateName="${.current_template_name!''}"><#include "/template/ts/canon-template-ts-SubEpilogue.ftl">