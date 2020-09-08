
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
  <#switch field.typeSchema.schemaType>
    <#case "OBJECT">
${indent}if(node instanceof JsonObject)
${indent}{
${indent}  ${var} = modelRegistry.newInstance((ImmutableJsonObject)node, ${field.typeSchema.camelCapitalizedName}.TYPE_ID, I${field.typeSchema.camelCapitalizedName}.class);
${indent}}
${indent}else ${ifValidation}
${indent}{
${indent}  throw new IllegalArgumentException("${field.camelName} must be an Object node not " + node.getClass().getName());
${indent}}
    <#break>
    
    <#case "ARRAY">
${indent}if(node instanceof Json${field.cardinality})
${indent}{
${indent}  ${var} = ${fieldType}.newBuilder().with((Json${field.cardinality})node).build();
<@checkItemLimits indent field field.camelName var/>
${indent}}
${indent}else ${ifValidation}
${indent}{
${indent}  throw new IllegalArgumentException("${field.camelName} must be a Json${field.cardinality} node not " + node.getClass().getName());
${indent}}
    <#break>
    
    <#case "PRIMITIVE">
${indent}if(node instanceof I${field.typeSchema.type}Provider)
${indent}{
${indent}  ${field.typeSchema.type} value = ${field.typeSchema.constructPrefix}((I${field.typeSchema.type}Provider)node).as${field.typeSchema.type}()${field.typeSchema.constructSuffix};
      <@checkFieldLimits "${indent}" field "value"/>
${indent}  ${var} = ${field.typeSchema.constructPrefix}value${field.typeSchema.constructSuffix};
${indent}}
${indent}else ${ifValidation}
${indent}{
${indent}    throw new IllegalArgumentException("${field.camelName} must be an instance of I${field.typeSchema.type}Provider not " + node.getClass().getName());
${indent}}
    <#break>
    
    <#default>
UNEXPECTED SCHEMA TYPE ${field.typeSchema.schemaType} in generateCreateFieldFromJsonDomNode
    <#break>
  </#switch>


<#-- 
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
${indent}    ${var} = ${javaconstructPrefix}value${javaconstructPostfix};
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
${indent}    ${var} = ${field.typeSchema.constructPrefix}list${field.typeSchema.constructSuffix};
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
${indent}  ${javaFieldClassName} value = ${javaconstructPrefix}((I${javaElementFieldClassName}Provider)node).as${javaElementFieldClassName}()${javaconstructPostfix};
      <#if requiresChecks && ifValidation == "">
        <@checkLimits "${indent}  " field "value"/>
      </#if>
${indent}  ${var} = ${field.typeSchema.constructPrefix}value${field.typeSchema.constructSuffix};
${indent}}
${indent}else ${ifValidation}
${indent}{
${indent}    throw new IllegalArgumentException("${field.camelName} must be an instance of ${javaFieldClassName} not " + node.getClass().getName());
${indent}}
    </#if>
  </#if> -->
</#macro>

<#------------------------------------------------------------------------------------------------------
 # Generate limit checks for the given field if necessary
 #
 # @param indent    An indent string which is output at the start of each line generated
 # @param field     A field model describing the field
 # @param var       A java variable containing the value being checked
 #----------------------------------------------------------------------------------------------------->
<#macro checkFieldLimits indent field var>
  <#if field.typeSchema.minimum??>
    <#if field.typeSchema.exclusiveMinimum>
${indent}if(${var} != null && ${var} <= ${field.typeSchema.minimum})
${indent}  throw new IllegalArgumentException("Value " + ${var} + " of ${field.name} is less than or equal to the exclusive minimum allowed of ${field.typeSchema.minimum}");
    <#else>
${indent}if(${var} != null && ${var} < ${field.typeSchema.minimum})
${indent}  throw new IllegalArgumentException("Value " + ${var} + " of ${field.name} is less than the minimum allowed of ${field.typeSchema.minimum}");
    </#if>
  </#if>
  <#if field.typeSchema.maximum??>
    <#if field.typeSchema.exclusiveMaximum>
${indent}if(${var} != null && ${var} >= ${field.typeSchema.maximum})
${indent}  throw new IllegalArgumentException("Value " + ${var} + " of ${field.name} is greater than or equal to the exclusive maximum allowed of ${field.typeSchema.maximum}");
    <#else>
${indent}if(${var} != null && ${var} > ${field.typeSchema.maximum})
${indent}  throw new IllegalArgumentException("Value " + ${var} + " of ${field.name} is greater than the maximum allowed of ${field.typeSchema.maximum}");
    </#if>
  
  </#if>
  <#if field.required>
${indent}if(${var} == null)
${indent}  throw new IllegalArgumentException("${field.camelName} is required.");
  
  </#if>
</#macro>
