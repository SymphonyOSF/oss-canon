<#macro generateInstanceOrBuilder indent entity>
<#------------------------------------------------------------------------------------------------------------------------------

 Instance or Builder

------------------------------------------------------------------------------------------------------------------------------->
${indent}/**
${indent} * Instance or Builder for Object ${entity}
${indent} */
${indent}public interface I${entity.camelCapitalizedName}InstanceOrBuilder extends I${entity.initialiserType}
${indent}{
<#list entity.fields as field>
${indent}  
${indent}  /**
${indent}   * Return the value of the ${field.name} attribute.
${indent}   *
  <#if field.typeSchema.description??>
${indent}   * ${field.typeSchema.description}
${indent}   *
  </#if>
${indent}   * @return the value of the ${field.name} attribute.
${indent}   */
${indent}  @${field.nullable} ${field.type} get${field.camelCapitalizedName}();
</#list>
${indent}}
</#macro>