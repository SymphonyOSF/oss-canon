<#include "/template/ts/canon-template-ts-Prologue.ftl">
<#include "/template/ts/TypeDefHeader.ftl">
<@setPrologueJavaType model/>
export interface I${modelJavaClassName}Data {
  readonly value: ${modelJavaFieldClassName};
}
<#include "/template/ts/canon-template-ts-Epilogue.ftl">