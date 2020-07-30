<#assign subTemplateName="${.current_template_name!''}"><#include "/template/ts/canon-template-ts-SubPrologue.ftl">
/**
<#if isFacade??>
 * Facade for Object ${model}
<#else>
 * Object ${model}
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
 * Generated from ${model} at ${model.context.path}
 */
<#assign subTemplateName="${.current_template_name!''}"><#include "/template/ts/canon-template-ts-SubEpilogue.ftl">