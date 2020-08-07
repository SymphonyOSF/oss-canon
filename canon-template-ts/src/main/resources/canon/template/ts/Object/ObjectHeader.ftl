<#include "/template/ts/canon-template-ts-Prologue.ftl">
<#assign model=model.type>
<@setPrologueJavaType model/>

<#list tsImports as import>
${import}
</#list>
<#if model.baseSchema.isGenerateFacade>
import { ${modelJavaClassName} } from "../../${pathToFacade}/ts/${modelName}/${modelJavaClassName}";
<#else>
import { ${modelJavaClassName} } from "./${modelJavaClassName}";
</#if>
import { I${modelJavaClassName}Data } from "./I${modelJavaClassName}Data";
import {
  Entity,
  EntityData,
  IllegalArgumentException
  } from "canon-runtime-ts";

<@importFieldTypes model true/>
<@importFacadePackages model/>

<#include "/template/ts/Object/Object.ftl">

<#if model.superSchema??>
export abstract class ${modelJavaClassName}Entity extends ${model.superSchema.baseSchema.camelCapitalizedName}
<#else>
export abstract class ${modelJavaClassName}Entity extends Entity
</#if>
 implements I${modelJavaClassName}Data
{
  /** Type ID */
  public static readonly TYPE_ID = "${model.model.canonId}.${model.name}";
  /** Type version */
  public static readonly TYPE_VERSION = "${model.model.canonVersion}";
  /** Type major version */
  public static readonly TYPE_MAJOR_VERSION = ${model.model.canonMajorVersion};
  /** Type minor version */
  public static readonly TYPE_MINOR_VERSION = ${model.model.canonMinorVersion};
