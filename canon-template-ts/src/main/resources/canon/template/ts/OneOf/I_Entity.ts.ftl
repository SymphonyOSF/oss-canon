<#include "/template/ts/canon-template-ts-Prologue.ftl">
<#assign model=model.type>
<@setPrologueJavaType model/>

import com.symphony.oss.canon.runtime.IEntity;

<@importFacadePackages model/>

<#include "/template/ts/Object/Object.ftl">
public interface I${model.camelCapitalizedName}Entity extends IEntity,
<#list model.children as field>
  I${field.camelCapitalizedName}<#sep>,
</#list>
 
{
}
<#include "/template/ts/canon-template-ts-Epilogue.ftl">