<#if model.baseSchema.isGenerateFacade?? && model.baseSchema.isGenerateFacade>
<#include "/proforma/ts/canon-proforma-ts-Prologue.ftl">
<#assign model=model.type>
<@setPrologueJavaType model/>

<#include "/proforma/ts/Object/Facade.ftl">
<#include "/proforma/ts/canon-proforma-ts-Epilogue.ftl">
</#if>