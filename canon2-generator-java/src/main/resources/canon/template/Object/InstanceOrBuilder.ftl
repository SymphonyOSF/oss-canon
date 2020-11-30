<#macro generateInstanceOrBuilder indent entity>
<#------------------------------------------------------------------------------------------------------------------------------

 Instance or Builder

------------------------------------------------------------------------------------------------------------------------------->
${indent}/**
${indent} * Instance or Builder for Object ${entity}
${indent} */
// entity.class ${entity.class}
<@namespace name="className" import="${genPackage}.I${c}${entity.identifier}${c}InstanceOrBuilder"/>
<@namespace name="interfaceName" import=entity.fullyQualifiedInitialiserType/>
<@namespace name="Map" import="java.util.Map"/>
${indent}public interface ${className} extends ${interfaceName}
${indent}{
<#list entity.fields as field>
<@namespace model=field/>
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
${indent}  @<@namespace import=field.nullable/> ${field.type} get${field.camelCapitalizedName}();
</#list>
<#if entity.additionalPropertiesAllowed>

${indent} /**
${indent}   * Return any additional attributes.
${indent}   * 
${indent}   * @return any additional attributes.
${indent}   */
${indent}  ${Map}<String, ${additionalType}> canonGetAdditionalProperties();
</#if>
<#if hasUnknownProperties>

${indent} /**
${indent}   * Return any unknown attributes.
${indent}   * 
${indent}   * @return any unknown attributes.
${indent}   */
${indent}  ${Map}<String, <@namespace import="com.symphony.oss.canon2.runtime.java.Entity"/>> canonGetUnknownProperties();
</#if>
${indent}}
</#macro>