<#include "/template/ts/canon-template-ts-Prologue.ftl">

import com.symphony.oss.canon.runtime.IEntityFactory;

public class ${model.camelCapitalizedName}Model
{ 
  public static final IEntityFactory<?,?,?>[] FACTORIES = new IEntityFactory<?,?,?>[]
    {
<#list model.schemas as object>
  <#if object.isAbstract?? && object.isAbstract>
    //ABSTRACT ${object.camelName}
  <#else>
      ${object.camelCapitalizedName}Entity.FACTORY<#sep>,</#sep>
  </#if>
</#list>
    };
    
  private ${model.camelCapitalizedName}Model()
  {
  }
}
<#include "/template/ts/canon-template-ts-Epilogue.ftl">