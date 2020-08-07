<#if ! model.isAbstract?? || ! model.isAbstract?c>
<#include "/template/ts/canon-template-ts-Prologue.ftl">
<#assign model=model.type>
<@setPrologueJavaType model/>

<#if model.superSchema??>
import I${model.superSchema.baseSchema.camelCapitalizedName}Data from "./I${model.superSchema.baseSchema.camelCapitalizedName}Data";
</#if>

<#include "/template/ts/Object/Object.ftl">

<#if model.superSchema??>
export interface I${modelJavaClassName}Data extends I${model.superSchema.baseSchema.camelCapitalizedName}Data
<#else>
export interface I${modelJavaClassName}Data
</#if>
{
<#list model.fields as field>
  <@setJavaType field/>
  readonly ${field.camelName}: ${fieldType};
</#list>
}

<#include "/template/ts/canon-template-ts-Epilogue.ftl">
</#if>