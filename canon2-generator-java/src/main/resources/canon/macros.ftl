
<#------------------------------------------------------------------------------------------------------
 # 
 # Create a Json DOM node from a field.
 #
 # @param indent    An indent string which is output at the start of each line generated
 # @param model     A model element representing the field to generate for
 # @param var       The name of a MutableJsonObject to which the field will be added 
 #----------------------------------------------------------------------------------------------------->
<#macro generateCreateJsonDomNodeFromField indent schema name source var>
  <#switch schema.schemaType>
    <#case "OBJECT">
    <#case "ALL_OF">
    <#case "ANY_OF">
    <#case "ONE_OF">
${indent}${var}.addIfNotNull("${name}", ${source}.getJson());
    <#break>
    <#case "ARRAY">
${indent}JsonArray.Builder arrayBuilder = new JsonArray.Builder();
${indent}for(${schema.elementType.type} item : ${source})
${indent}{
      <@generateCreateArrayJsonDomNode "${indent}  " 1 schema.elementType "item" "arrayBuilder"/>
${indent}}
${indent}${var}.with("${name}", arrayBuilder.build());
    <#break>
    <#case "STRING">
    <#case "NUMBER">
    <#case "INTEGER">
    <#case "BOOLEAN">
${indent}${var}.addIfNotNull("${name}", ${schema.getValuePrefix}${source}${schema.getValueSuffix});
    <#break>
    
    <#default>
UNEXPECTED SCHEMA TYPE ${schema.schemaType} in generateCreateJsonDomNodeFromField
    <#break>
  </#switch>
</#macro>

<#macro generateCreateArrayJsonDomNode indent cnt schema source var>
  <#switch schema.schemaType>
    <#case "OBJECT">
    <#case "ALL_OF">
    <#case "ANY_OF">
    <#case "ONE_OF">
${indent}${var}.with(${source}.getJson());
    <#break>
    <#case "ARRAY">
${indent}JsonArray.Builder arrayBuilder${cnt} = new JsonArray.Builder();

${indent}for(${schema.elementType.type} item${cnt} : ${source})
${indent}{
      <@generateCreateArrayJsonDomNode "${indent}  " cnt+1 schema.elementType "item${cnt}" "arrayBuilder${cnt}"/>
${indent}}
${indent}${var}.with(arrayBuilder${cnt}.build());
    <#break>
    <#case "STRING">
    <#case "NUMBER">
    <#case "INTEGER">
    <#case "BOOLEAN">
${indent}${var}.with(${schema.getValuePrefix}${source}${schema.getValueSuffix});
    <#break>
    <#default>
UNEXPECTED SCHEMA TYPE ${schema.schemaType} in generateCreateJsonDomNodeFromField
    <#break>
  </#switch>
</#macro>

<#------------------------------------------------------------------------------------------------------
 # 
 # Create a field from a Json DOM node.
 # The java variable "node" must have already been set to an IJsonDomNode and must not be null.
 #
 # @param indent        An indent string which is output at the start of each line generated
 # @param schema        A model element representing the field to generate for
 # @param name          The name of the attribute for error messages.
 # @param var           The name of a variable to which the extracted value will be assigned 
 # @param ifValidation  If set then an if statement which guards validation checks
 #----------------------------------------------------------------------------------------------------->
<#macro generateCreateFieldFromJsonDomNode indent node objectSchemaType schema name var ifValidation fullyQualified>
// A1
<@generateCreateFieldFromJsonDomNodePrivate indent 0 node objectSchemaType schema name var ifValidation fullyQualified/>
</#macro>

<#macro generateCreateFieldFromJsonDomNodePrivate indent cnt node objectSchemaType schema name var ifValidation fullyQualified>
//A2
  <#switch schema.schemaType>
    <#case "OBJECT">
    //A3
    <#case "ALL_OF">
    <#case "ANY_OF">
    <#case "ONE_OF">
    
    
${indent}if(${node} instanceof ${schema.jsonNodeType})
${indent}{
//A6a
      <#switch objectSchemaType>
        <#case "ONE_OF">
${indent}  ${var} = ${schema.type}.FACTORY.newInstanceOrNull(parserExceptions, (${schema.jsonNodeType})${node}, modelRegistry);
           <#break>
          <#default>
${indent}  ${var} = ${schema.type}.FACTORY.newInstance((${schema.jsonNodeType})${node}, modelRegistry);
           <#break>
       </#switch>
${indent}}
${indent}else ${ifValidation}
${indent}{
      <#switch objectSchemaType>
        <#case "ONE_OF">
${indent}  ${var} = null;
           <#break>
          <#default>
${indent}  throw new ParserErrorException("${name} must be an instance of ${schema.jsonNodeType} not " + ${node}.getClass().getName(), ${node}.getContext());
           <#break>
       </#switch>
${indent}}
    <#break>
    
    <#case "ARRAY">
${indent}if(${node} instanceof JsonArray)
${indent}{
      <#if schema.cardinality == "LIST">
${indent}  ${schema.type} itemList${cnt} = new LinkedList<>();
${indent}  for(JsonDomNode item${cnt} : (JsonArray)${node})
${indent}  {
${indent}    ${schema.elementType.type} itemValue${cnt} = null;
        <@generateCreateFieldFromJsonDomNodePrivate "${indent}    " cnt+1 "item${cnt}" objectSchemaType schema.elementType "${name} items" "itemValue${cnt}" ifValidation fullyQualified/>
${indent}    itemList${cnt}.add(itemValue${cnt});
${indent}  }
${indent}  ${var} = ImmutableList.copyOf(itemList${cnt});
      <#else>
${indent}  Set<${schema.elementType.type}> itemSet${cnt} = new HashSet<>();
${indent}  for(JsonDomNode item${cnt} : (JsonArray)${node})
${indent}  {
${indent}    ${schema.elementType.type} itemValue${cnt} = null;
        <@generateCreateFieldFromJsonDomNode "${indent}    " "item${cnt}" objectSchemaType schema.elementType "${name} items" "itemValue${cnt}" ifValidation fullyQualified/>
${indent}    itemSet${cnt}.add(itemValue${cnt});
${indent}  }
${indent}  ${var} = ImmutableSet.copyOf(itemSet${cnt});
      </#if>
        <@checkItemLimits "${indent}  " schema name var/>
${indent}}
${indent}else ${ifValidation}
${indent}{
${indent}  throw new ParserErrorException("${name} must be a JsonArray node not " + ${node}.getClass().getName(), ${node}.getContext());
${indent}}
    <#break>
    
    <#case "STRING">
    <#case "NUMBER">
    <#case "INTEGER">
    <#case "BOOLEAN">
      <@generateCreatePrimitiveFieldFromJsonDomNode  indent node objectSchemaType schema name var ifValidation fullyQualified/>
    <#break>
    
    <#default>
UNEXPECTED SCHEMA TYPE ${schema.schemaType} in generateCreateFieldFromJsonDomNode
    <#break>
  </#switch>
</#macro>

<#------------------------------------------------------------------------------------------------------
 # 
 # Create a primitive field from a Json DOM node.
 # The java variable "node" must have already been set to an IJsonDomNode and must not be null.
 #
 # @param indent        An indent string which is output at the start of each line generated
 # @param schema        A model element representing the field to generate for
 # @param name          The name of the attribute for error messages.
 # @param var           The name of a variable to which the extracted value will be assigned 
 # @param ifValidation  If set then an if statement which guards validation checks
 #----------------------------------------------------------------------------------------------------->
<#macro generateCreatePrimitiveFieldFromJsonDomNode indent node objectSchemaType schema name var ifValidation fullyQualified>
${indent}if(${node} instanceof ${schema.jsonNodeType})
${indent}{
//A6
${indent}  ${var} = ${schema.getConstructor(fullyQualified, "((${schema.jsonNodeType})${node}).as${schema.javaType}()")};
${indent}}
${indent}else ${ifValidation}
${indent}{
      <#switch objectSchemaType>
        <#case "ONE_OF">
${indent}  ${var} = null;
           <#break>
          <#default>
${indent}  throw new ParserErrorException("${name} must be an instance of ${schema.jsonNodeType} not " + ${node}.getClass().getName(), ${node}.getContext());
           <#break>
       </#switch>
${indent}}
</#macro>

<#------------------------------------------------------------------------------------------------------
 # Generate limit checks for the given field if necessary
 #
 # @param indent    An indent string which is output at the start of each line generated
 # @param field     A field model describing the field
 # @param var       A java variable containing the value being checked
 #----------------------------------------------------------------------------------------------------->
<#macro checkFieldLimits indent field var>
  <#-- @checkLimits indent field.typeSchema, field.name, var "new IllegalArgumentException" ""/ -->
  <#if field.required>
${indent}if(${var} == null)
${indent}  throw new IllegalArgumentException("${field.name} is required.");
  
  </#if>
</#macro>

<#------------------------------------------------------------------------------------------------------
 # Generate limit checks for the given field if necessary
 #
 # @param indent    An indent string which is output at the start of each line generated
 # @param schema    A schema describing the limits
 # @param name      The name to be used in error messages
 # @param var       A java variable containing the value being checked
 #----------------------------------------------------------------------------------------------------->
<#macro checkLimits indent schema name var exceptionPrefix exceptionSuffix>
  <#if schema.minimum??>
    <#if schema.exclusiveMinimum>
${indent}if(${schema.generateComparason(var, "MINIMUM", "<=")})
${indent}  throw ${exceptionPrefix}("Value " + ${var} + " of ${name} is less than or equal to the exclusive minimum allowed of ${schema.minimum}"${exceptionSuffix});
    <#else>
${indent}if(${schema.generateComparason(var, "MINIMUM", "<")})
${indent}  throw ${exceptionPrefix}("Value " + ${var} + " of ${name} is less than the minimum allowed of ${schema.minimum}"${exceptionSuffix});
    </#if>
  </#if>
  <#if schema.maximum??>
    <#if schema.exclusiveMaximum>
${indent}if(${schema.generateComparason(var, "MAXIMUM", ">=")})
${indent}  throw ${exceptionPrefix}("Value " + ${var} + " of ${name} is greater than or equal to the exclusive maximum allowed of ${schema.maximum}"${exceptionSuffix});
    <#else>
${indent}if(${schema.generateComparason(var, "MAXIMUM", ">")})
${indent}  throw ${exceptionPrefix}("Value " + ${var} + " of ${name} is greater than the maximum allowed of ${schema.maximum}"${exceptionSuffix});
    </#if>
  
  </#if>
</#macro>

<#------------------------------------------------------------------------------------------------------
 # Generate limit checks for the given Array type if necessary
 #
 # @param indent    An indent string which is output at the start of each line generated
 # @param model     A model element representing the field to generate for
 # @param name      The name of an array value being checked
 # @param var       The name of a variable to which the extracted value will be assigned 
 #----------------------------------------------------------------------------------------------------->
<#macro checkItemLimits indent model name var>
  <#if model.minItems??>

${indent}if(${var}.size() < ${model.minItems})
${indent}{
${indent}  throw new IllegalArgumentException("${name} has " + ${var}.size() + " items but at least ${model.minItems} are required");
${indent}}
  </#if>
  <#if model.maxItems??>
${indent}if(${var}.size() > ${model.maxItems})
${indent}{
${indent}  throw new IllegalArgumentException("${name} has " + ${var}.size() + " items but at most ${model.maxItems} are allowed");
${indent}}
  </#if>
</#macro>