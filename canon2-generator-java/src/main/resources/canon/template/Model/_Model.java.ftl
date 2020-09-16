<#include "/copyrightHeader.ftl">

package ${genPackage};

import com.symphony.oss.canon2.runtime.java.ObjectEntity;

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
public class ${model.camelCapitalizedName}Model
{
  /** Factories for every object in the model. */
  public static final ObjectEntity.Factory<?>[] FACTORIES = new ObjectEntity.Factory<?>[]
  {
<#list model.schemas as object>
  <#if object.schemaType == "OBJECT">
    ${object.camelCapitalizedName}.FACTORY<#sep>,</#sep>
  </#if>
</#list>
  };
    
  private ${model.camelCapitalizedName}Model()
  {
  }
}
<#include "/footer.ftl">