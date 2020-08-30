
<#------------------------------------------------------------------------------------------------------
 # 
 # Create a field from a Json DOM node.
 # The java variable "node" must have already been set to an IJsonDomNode and must not be null.
 #
 # @param indent        An indent string which is output at the start of each line generated
 # @param field         A model element representing the field to generate for
 # @param var           The name of a variable to which the extracted value will be assigned 
 # @param ifValidation  If set then an if statement which guards validation checks
 # @param mutable       "Mutable" for builders and "Immutable" for objects
 #----------------------------------------------------------------------------------------------------->
<#macro generateCreateFieldFromJsonDomNode indent field var ifValidation mutable>
  <#switch field.getSchemaType>
    <#case OBJECT>
${indent}if(node instanceof ImmutableJsonObject)
${indent}{
${indent}  ${var} = modelRegistry.newInstance((ImmutableJsonObject)node, ${field.elementSchema.camelCapitalizedName}.TYPE_ID, I${field.elementSchema.camelCapitalizedName}.class);
${indent}}
${indent}else ${ifValidation}
${indent}{
${indent}  throw new IllegalArgumentException("${field.camelName} must be an Object node not " + node.getClass().getName());
${indent}}
    <#break>
    
    <#case ARRAY>
${indent}if(node instanceof Json${field.cardinality})
${indent}{
${indent}  ${var} = ${fieldType}.newBuilder().with((Json${fieldCardinality}<?>)node).build();
<@checkItemLimits indent field field.camelName var/>
${indent}}
${indent}else ${ifValidation}
${indent}{
${indent}  throw new IllegalArgumentException("${field.camelName} must be an array node not " + node.getClass().getName());
${indent}}
    <#break>
    
    <#case PRIMITIVE>
    <#break>
    
    <#default>
UNEXPECTED SCHEMA TYPE ${field.getSchemaType} in generateCreateFieldFromJsonDomNode
    <#break>
  </#switch>



  <#if field.isComponent>
    <#if field.isObjectSchema>

    <#else>
      <#if field.isArraySchema>

      <#else>
${indent}if(node instanceof I${javaElementFieldClassName}Provider)
${indent}{
${indent}  ${javaElementFieldClassName} value = ((I${javaElementFieldClassName}Provider)node).as${javaElementFieldClassName}();

${indent}  try
${indent}  {
${indent}    ${var} = ${javaConstructTypePrefix}value${javaConstructTypePostfix};
${indent}  }
${indent}  catch(RuntimeException e)
${indent}  {
${indent}     ${ifValidation} throw new IllegalArgumentException("Value \"" + value + "\" for ${field.camelName} is not a valid value", e);
${indent}  }
${indent}}
${indent}else ${ifValidation}
${indent}{
${indent}    throw new IllegalArgumentException("${field.camelName} must be an instance of ${javaFieldClassName} not " + node.getClass().getName());
${indent}}     
        </#if>
      </#if>
  <#else>
    <#if field.isArraySchema>
${indent}if(node instanceof JsonArray)
${indent}{
    <#if field.baseSchema.items.isTypeDef>
<#assign elementClassName=field.baseSchema.items.baseSchema.camelCapitalizedName>   
    
${indent}${fieldCardinality}<${javaElementClassName}> list${javaBuilderTypeNew};
    
${indent}for(IJsonDomNode itemNode : ((JsonArray<?>)node))
${indent}{
${indent}  if(itemNode instanceof I${javaElementFieldClassName}Provider)
${indent}  {
${indent}    ${javaElementFieldClassName} value = ((I${javaElementFieldClassName}Provider)itemNode).as${javaElementFieldClassName}();
<@createTypeDefValue indent field.baseSchema.items.baseSchema "list" "value"/>
${indent}  }
${indent}}
${indent}    ${var} = ${javaTypeCopyPrefix}list${javaTypeCopyPostfix};
    <#else>
      <#if field.baseSchema.items.isComponent>
${indent}  ${var} = ${field.elementSchema.camelCapitalizedName}.FACTORY.new${mutable}${fieldCardinality}((JsonArray<?>)node, modelRegistry);

      <#else>
${indent}  ${var} = ((JsonArray<?>)node).asImmutable${fieldCardinality}Of(${javaElementFieldClassName}.class);
      </#if>
    </#if>
    <#if ifValidation == "">
<@checkItemLimits indent field field.camelName var/>
    </#if>
${indent}}
${indent}else ${ifValidation}
${indent}{
${indent}  throw new IllegalArgumentException("${field.camelName} must be an array not " + node.getClass().getName());
${indent}}
    <#else> 
${indent}if(node instanceof I${javaElementFieldClassName}Provider)
${indent}{
${indent}  ${javaFieldClassName} value = ${javaConstructTypePrefix}((I${javaElementFieldClassName}Provider)node).as${javaElementFieldClassName}()${javaConstructTypePostfix};
      <#if requiresChecks && ifValidation == "">
        <@checkLimits "${indent}  " field "value"/>
      </#if>
${indent}  ${var} = ${javaTypeCopyPrefix}value${javaTypeCopyPostfix};
${indent}}
${indent}else ${ifValidation}
${indent}{
${indent}    throw new IllegalArgumentException("${field.camelName} must be an instance of ${javaFieldClassName} not " + node.getClass().getName());
${indent}}
    </#if>
  </#if>
</#macro>