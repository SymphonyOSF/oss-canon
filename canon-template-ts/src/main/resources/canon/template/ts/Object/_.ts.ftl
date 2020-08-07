<#if ! model.baseSchema.isGenerateFacade?? || ! model.baseSchema.isGenerateFacade>
<#include "/template/ts/canon-template-ts-Prologue.ftl">
<#assign model=model.type>
<@setPrologueJavaType model/>
<#include "/proforma/ts/Object/Facade.ftl">
<#include "/template/ts/canon-template-ts-Epilogue.ftl">
</#if>