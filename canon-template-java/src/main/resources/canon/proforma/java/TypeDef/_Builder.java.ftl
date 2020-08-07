<#if model.attributes['javaExternalType']??>
<#include "/proforma/java/canon-proforma-java-Prologue.ftl">
<@setPrologueJavaType model/>
<#include "/proforma/java/TypeDefBuilderProforma.ftl">
}

<#include "/proforma/java/canon-proforma-java-Epilogue.ftl">
</#if>