<#assign subTemplateName="${.current_template_name!''}"><#include "/proforma/ts/canon-proforma-ts-SubPrologue.ftl">
<#if model.attributes['tsExternalPackage']??>
import { ${model.attributes['tsExternalType']} } from "${model.attributes['tsExternalPackage']}";
</#if>
public class ${model.camelCapitalizedName}Builder
{
<#if model.enum??>
  public static ${modelJavaClassName} valueOf(${modelJavaFieldClassName} value)
  {
    // TODO Auto-generated method stub
    return ${modelJavaClassName}.valueOf(value);
  }
  
  public static ${modelJavaFieldClassName} to${modelJavaFieldClassName}(${modelJavaClassName} instance)
  {
    // TODO Auto-generated method stub
    return instance.toString();
  }
<#else>
  public static ${modelJavaClassName} build(${modelJavaFieldClassName} value)
  {
    // TODO Auto-generated method stub
    return new ${modelJavaClassName}(value);
  }
  
  public static ${modelJavaFieldClassName} to${modelJavaFieldClassName}(${modelJavaClassName} instance)
  {
    // TODO Auto-generated method stub
    return instance.getValue();
  }
</#if>
<#assign subTemplateName="${.current_template_name!''}"><#include "/proforma/ts/canon-proforma-ts-SubEpilogue.ftl">