<#if ! model.attributes['javaExternalType']?? && ! model.enum?? && model.baseSchema.isGenerateFacade?? && model.baseSchema.isGenerateFacade>
<#include "/proforma/ts/canon-proforma-ts-Prologue.ftl">
<@setPrologueJavaType model/>
<#include "/proforma/ts/TypeDefProforma.ftl">
}

<#include "/proforma/ts/canon-proforma-ts-Epilogue.ftl">
</#if>