<#if ! model.baseSchema.isGenerateFacade?? || ! model.baseSchema.isGenerateFacade>
<#include "/template/ts/canon-template-ts-Prologue.ftl">
<#assign model=model.type>
<@setPrologueJavaType model/>
import javax.annotation.concurrent.Immutable;

<@importFacadePackages model/>

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
<#include "/template/ts/canon-template-ts-Epilogue.ftl">
</#if>