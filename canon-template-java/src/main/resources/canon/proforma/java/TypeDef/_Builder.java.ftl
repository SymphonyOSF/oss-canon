<#if model.attributes['javaExternalType']?? && (model.attributes['javaIsDirectExternal']!"false") != "true">
<#include "/proforma/java/canon-proforma-java-Prologue.ftl">
<@setPrologueJavaType model/>
<#include "/proforma/java/TypeDefBuilderProforma.ftl">
}

<#include "/proforma/java/canon-proforma-java-Epilogue.ftl">
</#if>