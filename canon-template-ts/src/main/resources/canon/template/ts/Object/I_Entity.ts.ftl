<#if ! model.isAbstract?? || ! model.isAbstract?c>
<#include "/template/ts/canon-template-ts-Prologue.ftl">
<#assign model=model.type>
<@setPrologueJavaType model/>

import java.util.Set;
import com.symphony.oss.canon.runtime.IEntity;
import com.google.common.collect.ImmutableSet;

<@importFieldTypes model true/>
<@importFacadePackages model/>

<#include "/template/ts/Object/Object.ftl">
public interface I${model.camelCapitalizedName}Entity
<#if model.superSchema??>
  extends I${model.superSchema.baseSchema.camelCapitalizedName}Entity, I${model.model.camelCapitalizedName}ModelEntity
<#else>
  extends I${model.model.camelCapitalizedName}ModelEntity
</#if>
{
  ImmutableSet<String> getCanonUnknownKeys();
<#list model.fields as field>
  <@setJavaType field/>
  ${fieldType} get${field.camelCapitalizedName}();
</#list>
}
<#include "/template/ts/canon-template-ts-Epilogue.ftl">
</#if>