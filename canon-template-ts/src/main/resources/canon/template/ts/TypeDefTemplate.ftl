<#assign subTemplateName="${.current_template_name!''}"><#include "/template/ts/canon-template-ts-SubPrologue.ftl">

<@checkLimitsClassImportException model/>
<#if model.baseSchema.isGenerateFacade>
import { ${modelJavaClassName} } from "../../${pathToFacade}/ts/${modelName}/${modelJavaClassName}";
<#else>
import { ${modelJavaClassName} } from "./${modelJavaClassName}";
</#if>
import { I${modelJavaClassName}TypeDef } from "./I${modelJavaClassName}TypeDef";

<#include "/template/ts/TypeDefHeader.ftl">
<@setJavaType model/>
// TypeDefTemplate
export class ${modelJavaClassName}TypeDef implements I${modelJavaClassName}Data {
  readonly value: ${modelJavaFieldClassName};

  protected constructor(value: ${modelJavaFieldClassName}) {
<#if requiresChecks>
<@checkLimits "    " model  "value"/>

<#else>
  <#if field.required>
    if(_${field.camelName} == null)
      throw new IllegalArgumentException("${field.camelName} is required.");
      
  </#if>
</#if>
      this.value = value;
  }

  public getValue() {
      return this.value;
  }

  public as${modelJavaFieldClassName}() {
      return this.value;
  }

  public toString() {
      return this.value.toString();
  }

  static Builder = class {
      build(value: ${modelJavaFieldClassName}): ${modelJavaClassName} {
          throw new Error('This method is abstract');
      }
  }
<#assign subTemplateName="${.current_template_name!''}"><#include "/template/ts/canon-template-ts-SubEpilogue.ftl">