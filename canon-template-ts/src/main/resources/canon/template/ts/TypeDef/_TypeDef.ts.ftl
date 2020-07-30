<#if ! model.attributes['javaExternalType']?? && ! model.enum??>
<#include "/template/ts/canon-template-ts-Prologue.ftl">
<@setPrologueJavaType model/>
<#include "/template/ts/TypeDefTemplate.ftl">
}

<#include "/template/ts/canon-template-ts-Epilogue.ftl">
</#if>