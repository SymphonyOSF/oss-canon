<#assign subTemplateName="${.current_template_name!''}"><#include "/template/ts/canon-template-ts-SubPrologue.ftl">
import javax.annotation.concurrent.Immutable;

<#include "/template/ts/TypeDefHeader.ftl">
<@setJavaType model/>
@Immutable
public enum ${modelJavaClassName}
{
  <#list model.enum.values as value>${value}<#sep>, </#list>;
  
  public String getValue()
  {
    return toString();
  }
<#assign subTemplateName="${.current_template_name!''}"><#include "/template/ts/canon-template-ts-SubEpilogue.ftl">