<#if ! model.attributes['javaExternalType']??>
  <#if model.enum??>
    <#include "/template/ts/canon-template-ts-Prologue.ftl">
    <@setPrologueJavaType model/>
    <#include "/template/ts/EnumTemplate.ftl">
    }
    
    <#include "/template/ts/canon-template-ts-Epilogue.ftl">
  <#else>
    <#if ! model.baseSchema.isGenerateFacade?? || ! model.baseSchema.isGenerateFacade>
      <#include "/template/ts/canon-template-ts-Prologue.ftl">
      <@setPrologueJavaType model/>
      <#include "/proforma/ts/TypeDefProforma.ftl">
}
  
      <#include "/template/ts/canon-template-ts-Epilogue.ftl">
    </#if>
  </#if>
</#if>