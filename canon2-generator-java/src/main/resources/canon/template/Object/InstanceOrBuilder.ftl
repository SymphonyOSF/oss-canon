<#macro generateInstanceOrBuilder indent entity>
<#------------------------------------------------------------------------------------------------------------------------------

 Instance or Builder

------------------------------------------------------------------------------------------------------------------------------->
${indent}/**
${indent} * Instance or Builder for Object ${entity}
${indent} */
// entity.class ${entity.class}
${indent}public interface I${c}${entity.camelCapitalizedName}${c}InstanceOrBuilder extends I${entity.initialiserType}
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
<#if entity.additionalPropertiesAllowed>

${indent} /**
${indent}   * Return any additional attributes.
${indent}   * 
${indent}   * @return any additional attributes.
${indent}   */
${indent}  Map<String, ${additionalType}> canonGetAdditionalProperties();
</#if>
<#if hasUnknownProperties>

${indent} /**
${indent}   * Return any unknown attributes.
${indent}   * 
${indent}   * @return any unknown attributes.
${indent}   */
${indent}  Map<String, Entity> canonGetUnknownProperties();
</#if>
${indent}}
</#macro>