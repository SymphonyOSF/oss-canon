<@setJavaType model/>
<#include "/template/ts/Object/Object.ftl">
<#if model.baseSchema.isGenerateFacade>
import { ${modelJavaClassName}Entity } from "../../${pathToGen}/ts/${modelName}/${modelJavaClassName}Entity";
<#else>
import { ${modelJavaClassName}Entity } from "./${modelJavaClassName}Entity";
</#if>
import {
  Entity,
  EntityData,
  IllegalArgumentException
  } from "canon-runtime-ts";
  
export class ${model.camelCapitalizedName} extends ${model.camelCapitalizedName}Entity
{
  /**
   * Constructor.
   * 
   * @param entityData Parsed JSON.
   */
  constructor(entityData: EntityData) {
    super(entityData);
  }
  
  <#if model.baseSchema.isGenerateBuilderFacade>
  /**
   * Abstract builder for ${modelJavaClassName}. If there are sub-classes of this type then their builders sub-class this builder.
   */
  static Abstract${modelJavaClassName}Builder extends Abstract${modelJavaClassName}EntityBuilder = abstract class
  {
  }
  </#if>
}
