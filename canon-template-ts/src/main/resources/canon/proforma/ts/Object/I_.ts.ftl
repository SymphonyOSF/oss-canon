<#if model.baseSchema.isGenerateFacade?? && model.baseSchema.isGenerateFacade>
<#include "/proforma/ts/canon-proforma-ts-Prologue.ftl">
<#assign model=model.type>
<@setPrologueJavaType model/>
import javax.annotation.concurrent.Immutable;

import ${javaGenPackage}.I${model.camelCapitalizedName}Entity;

<#include "/template/ts/Object/Object.ftl">
@Immutable
public interface I${model.camelCapitalizedName}
<#if model.superSchema??>
  extends I${model.superSchema.baseSchema.camelCapitalizedName}, I${model.camelCapitalizedName}Entity
<#else>
  extends I${model.camelCapitalizedName}Entity
</#if>
{
}
<#include "/proforma/ts/canon-proforma-ts-Epilogue.ftl">
</#if>