
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
${indent}${var}.addIfNotNull(${name}, ${source}.getJson());
    <#break>
    <#case "ARRAY">
${indent}JsonArray.Builder arrayBuilder = new JsonArray.Builder();
${indent}for(${schema.element.type} item : ${source})
${indent}{
      <@generateCreateArrayJsonDomNode "${indent}  " 1 schema.element "item" "arrayBuilder"/>
${indent}}
${indent}${var}.with(${name}, arrayBuilder.build());
    <#break>
    <#case "STRING">
    <#case "NUMBER">
    <#case "INTEGER">
    <#case "BOOLEAN">
${indent}${var}.addIfNotNull(${name}, ${schema.getPersistedValue(source)});
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

${indent}for(${schema.element.type} item${cnt} : ${source})
${indent}{
      <@generateCreateArrayJsonDomNode "${indent}  " cnt+1 schema.element "item${cnt}" "arrayBuilder${cnt}"/>
${indent}}
${indent}${var}.with(arrayBuilder${cnt}.build());
    <#break>
    <#case "STRING">
    <#case "NUMBER">
    <#case "INTEGER">
    <#case "BOOLEAN">
    // schema.class ${schema.class}
    // schema.name ${schema.name}
${indent}${var}.with(${schema.getPersistedValue(source)});
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
<#macro generateCreateFieldFromJsonDomNode indent node objectSchemaType schema name var ifValidation>
// A1 schema ${schema}
<@generateCreateFieldFromJsonDomNodePrivate indent 0 node objectSchemaType schema name var ifValidation/>
</#macro>

<#macro generateCreateFieldFromJsonDomNodePrivate indent cnt node objectSchemaType schema name var ifValidation>
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
${indent}  throw new <@namespace import="com.symphony.oss.canon.json.ParserErrorException"/>("${name} must be an instance of ${schema.jsonNodeType} not " + ${node}.getClass().getName(), ${node}.getContext());
           <#break>
       </#switch>
${indent}}
    <#break>
    
    <#case "ARRAY">
${indent}if(${node} instanceof JsonArray)
${indent}{
      <#if schema.isPrimitive>
// BRUCE7 schema ${schema.name}
${indent}  ${schema.collectionType}<${schema.element.type}> itemList${cnt} = new ${schema.collectionImplType}<>();
${indent}  for(JsonDomNode item${cnt} : (JsonArray)${node})
${indent}  {
${indent}    ${schema.element.type} itemValue${cnt} = null;
        <@generateCreateFieldFromJsonDomNodePrivate "${indent}    " cnt+1 "item${cnt}" objectSchemaType schema.element "${name} items" "itemValue${cnt}" ifValidation/>
${indent}    itemList${cnt}.add(itemValue${cnt});
${indent}  }
${indent}  ${var} = ${schema.getCopy("itemList${cnt}")}; //${schema.collectionImmutableType}.copyOf(itemList${cnt}); // HERE4
        <@checkItemLimits "${indent}  " schema name var/>
      <#else>
${indent}  ${var} = ${schema.type}.FACTORY.newInstance(node, modelRegistry);
      </#if>
${indent}}
${indent}else ${ifValidation}
${indent}{
${indent}  throw new <@namespace import="com.symphony.oss.canon.json.ParserErrorException"/>("${name} must be a JsonArray node not " + ${node}.getClass().getName(), ${node}.getContext());
${indent}}
    <#break>
    
    <#case "STRING">
    <#case "NUMBER">
    <#case "INTEGER">
    <#case "BOOLEAN">
      <@generateCreatePrimitiveFieldFromJsonDomNode  indent node objectSchemaType schema name var ifValidation/>
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
<#macro generateCreatePrimitiveFieldFromJsonDomNode indent node objectSchemaType schema name var ifValidation>
${indent}if(${node} instanceof ${schema.jsonNodeType})
${indent}{
//A6 schema.class ${schema.class} name ${schema.name}
${indent}  ${var} = ${schema.getConstructor(schema.getValueConstructor("((${schema.jsonNodeType})${node}).as${schema.simplePersistedType}()"))};
${indent}}
${indent}else ${ifValidation}
${indent}{
      <#switch objectSchemaType>
        <#case "ONE_OF">
${indent}  ${var} = null;
           <#break>
          <#default>
${indent}  throw new <@namespace import="com.symphony.oss.canon.json.ParserErrorException"/>("${name} must be an instance of ${schema.jsonNodeType} not " + ${node}.getClass().getName(), ${node}.getContext());
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


<#macro generateInnerClass indent innerClass className classModifier nested>
// INNER T1 ${innerClass.name} ${innerClass.class}
  <#if innerClass.schemaType.isObject>
// INNER T2
    <#if nested>
      <#assign modifier = classModifier/>
    <#else>
      <#assign modifier = "static ${classModifier}"/>
    </#if>
    <@generateObject "  ${indent}" innerClass className modifier true/>
  <#elseif innerClass.schemaType.isPrimitive>
// INNER T3
    <#if innerClass.isEnum>
      <@generateEnum "  ${indent}" model innerClass className "static "/>
    <#elseif innerClass.hasLimits>
      <@generateTypeDef "  ${indent}" model innerClass className "static "/>
    </#if>
  <#else>
// INNER T4
  // INNER NON OBJECT ${innerClass.schemaType} ${innerClass.camelCapitalizedName} ${innerClass.identifier}
    <#if innerClass.hasLimits>
// INNER T5
      <@generateArray "  ${indent}" model innerClass className "static " true/>
    </#if>
// INNER T6
  </#if>
// INNER T7 ${innerClass.name} ${innerClass.class}
</#macro>