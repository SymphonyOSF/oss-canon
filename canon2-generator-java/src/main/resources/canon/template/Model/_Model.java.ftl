<#include "/copyrightHeader.ftl">

/**
 * Model class for ${model.name}
<#if entity.summary??>
 *
 * ${entity.summary}
</#if>
<#if entity.description??>
 *
<#list entity.description as description>
 * ${description}
</#list>
</#if>
 */
public class ${model.camelCapitalizedName}${c}Model
{
<@namespace name="BaseEntity" import="com.symphony.oss.canon2.runtime.java.BaseEntity"/>
  /** Factories for every object in the model. */
  public static final ${BaseEntity}.Factory<?,?>[] FACTORIES = new ${BaseEntity}.Factory<?,?>[]
  {
<#macro generateFactories indent object prefix>
<#t>${separator}${indent}${prefix}${object.camelCapitalizedName}.FACTORY<#assign separator = ",\n"/><#list object.innerClasses as innerClass>
     <#if innerClass.schemaType.isObject>
      <@generateFactories indent innerClass "${prefix}${object.camelCapitalizedName}."/>
    </#if>
  </#list>
</#macro>
<#assign separator = ""/>
<#list model.schemas as object>
  <#switch object.schemaType>
    <#case "OBJECT">
    <#case "ALL_OF">
    <#case "ONE_OF">
    <#case "ANY_OF">
      <@generateFactories "    " object ""/>
  </#switch>
</#list>

  };
    
  private ${model.camelCapitalizedName}${c}Model()
  {
  }
}
<#include "/footer.ftl">