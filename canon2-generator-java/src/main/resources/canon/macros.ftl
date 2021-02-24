
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
${indent}${var}.addIfNotNull("${name}", ${source}.getJsonObject());
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
${indent}${var}.with(${source}.getJsonObject());
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
 # @param modelRegistry an expression to return a ModelRegistry.
 #----------------------------------------------------------------------------------------------------->
<#macro generateCreateFieldFromJsonDomNode indent node schema name var ifValidation modelRegistry>
<@generateCreateFieldFromJsonDomNodePrivate indent 0 node schema name var ifValidation modelRegistry/>
</#macro>

<#macro generateCreateFieldFromJsonDomNodePrivate indent cnt node schema name var ifValidation modelRegistry>
  <#switch schema.schemaType>
    <#case "OBJECT">
${indent}if(${node} instanceof JsonObject)
${indent}{
${indent}  ${var} = ${modelRegistry}.newInstance((JsonObject)${node}, ${schema.camelCapitalizedName}.TYPE_ID, ${schema.type}.class);
${indent}}
${indent}else ${ifValidation}
${indent}{
${indent}  throw new IllegalArgumentException("${name} must be an Object node not " + ${node}.getClass().getName());
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
        <@generateCreateFieldFromJsonDomNodePrivate "${indent}    " cnt+1 "item${cnt}" schema.elementType "${name} items" "itemValue${cnt}" ifValidation modelRegistry/>
${indent}    itemList${cnt}.add(itemValue${cnt});
${indent}  }
${indent}  ${var} = ImmutableList.copyOf(itemList${cnt});
      <#else>
${indent}  Set<${schema.elementType.type}> itemSet${cnt} = new HashSet<>();
${indent}  for(JsonDomNode item${cnt} : (JsonArray)${node})
${indent}  {
${indent}    ${schema.elementType.type} itemValue${cnt} = null;
        <@generateCreateFieldFromJsonDomNode "${indent}    " "item${cnt}" schema.elementType "${name} items" "itemValue${cnt}" ifValidation modelRegistry/>
${indent}    itemSet${cnt}.add(itemValue${cnt});
${indent}  }
${indent}  ${var} = ImmutableSet.copyOf(itemSet${cnt});
      </#if>
        <@checkItemLimits "${indent}  " schema name var/>
${indent}}
${indent}else ${ifValidation}
${indent}{
${indent}  throw new IllegalArgumentException("${name} must be a JsonArray node not " + ${node}.getClass().getName());
${indent}}
    <#break>
    
    <#case "STRING">
    <#case "NUMBER">
    <#case "INTEGER">
    <#case "BOOLEAN">
      <@generateCreatePrimitiveFieldFromJsonDomNode  indent node schema name var ifValidation/>
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
<#macro generateCreatePrimitiveFieldFromJsonDomNode indent node schema name var ifValidation>
${indent}if(${node} instanceof ${schema.jsonNodeType})
${indent}{
${indent}  ${var} = ${schema.constructPrefix}((${schema.jsonNodeType})${node}).as${schema.javaType}()${schema.constructSuffix};
      <@checkLimits "${indent}" schema name var/>
${indent}}
${indent}else ${ifValidation}
${indent}{
${indent}  throw new IllegalArgumentException("${name} must be an instance of ${schema.jsonNodeType} not " + ${node}.getClass().getName());
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
  <@checkLimits indent field.typeSchema, field.name, var/>
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
<#macro checkLimits indent schema name var>
  <#if schema.minimum??>
    <#if schema.exclusiveMinimum>
${indent}if(${var} != null && ${var} <= ${schema.minimum})
${indent}  throw new IllegalArgumentException("Value " + ${var} + " of ${name} is less than or equal to the exclusive minimum allowed of ${schema.minimum}");
    <#else>
${indent}if(${var} != null && ${var} < ${schema.minimum})
${indent}  throw new IllegalArgumentException("Value " + ${var} + " of ${name} is less than the minimum allowed of ${schema.minimum}");
    </#if>
  </#if>
  <#if schema.maximum??>
    <#if schema.exclusiveMaximum>
${indent}if(${var} != null && ${var} >= ${schema.maximum})
${indent}  throw new IllegalArgumentException("Value " + ${var} + " of ${name} is greater than or equal to the exclusive maximum allowed of ${schema.maximum}");
    <#else>
${indent}if(${var} != null && ${var} > ${schema.maximum})
${indent}  throw new IllegalArgumentException("Value " + ${var} + " of ${name} is greater than the maximum allowed of ${schema.maximum}");
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