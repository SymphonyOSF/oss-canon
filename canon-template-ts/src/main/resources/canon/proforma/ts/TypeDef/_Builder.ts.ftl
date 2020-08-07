<#if model.attributes['tsExternalType']??>
// model ${model}
// model.attributes['tsExternalType'] ${model.attributes['tsExternalType']!"NULL"}
<#include "/proforma/ts/canon-proforma-ts-Prologue.ftl">
<@setPrologueJavaType model/>
<#include "/proforma/ts/TypeDefBuilderProforma.ftl">
}

<#include "/proforma/ts/canon-proforma-ts-Epilogue.ftl">
</#if>