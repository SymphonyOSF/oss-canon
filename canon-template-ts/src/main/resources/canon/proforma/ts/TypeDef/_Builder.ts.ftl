<#if model.attributes['tsExternalType']?? && (model.attributes['tsIsDirectExternal']!"false") != "true">
// model ${model}
// model.attributes['tsExternalType'] ${model.attributes['tsExternalType']!"NULL"}
// model.attributes['tsIsDirectExternal'] ${model.attributes['tsIsDirectExternal']!"NULL"}
<#include "/proforma/ts/canon-proforma-ts-Prologue.ftl">
<@setPrologueJavaType model/>
<#include "/proforma/ts/TypeDefBuilderProforma.ftl">
}

<#include "/proforma/ts/canon-proforma-ts-Epilogue.ftl">
</#if>