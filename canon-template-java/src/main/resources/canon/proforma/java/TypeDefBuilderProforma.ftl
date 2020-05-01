<#assign subTemplateName="${.current_template_name!''}"><#include "/proforma/java/canon-proforma-java-SubPrologue.ftl">
import com.symphony.oss.commons.immutable.ImmutableByteArray;

import com.symphony.oss.commons.type.provider.IBooleanProvider;
import com.symphony.oss.commons.type.provider.IStringProvider;
import com.symphony.oss.commons.type.provider.IIntegerProvider;
import com.symphony.oss.commons.type.provider.ILongProvider;
import com.symphony.oss.commons.type.provider.IFloatProvider;
import com.symphony.oss.commons.type.provider.IDoubleProvider;
import com.symphony.oss.commons.type.provider.IImmutableByteArrayProvider;

import ${modelJavaFullyQualifiedClassName};

@SuppressWarnings("unused")
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
<#assign subTemplateName="${.current_template_name!''}"><#include "/proforma/java/canon-proforma-java-SubEpilogue.ftl">