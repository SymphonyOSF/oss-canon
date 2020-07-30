<#assign subTemplateName="${.current_template_name!''}"><#include "/proforma/ts/canon-proforma-ts-SubPrologue.ftl">

<#if model.baseSchema.isGenerateFacade>
import { ${modelJavaClassName}TypeDef } from "../../${pathToGen}/ts/${modelName}/${modelJavaClassName}TypeDef";
<#else>
import { ${modelJavaClassName}TypeDef } from "./${modelJavaClassName}TypeDef";
</#if>

<#include "/template/ts/TypeDefHeader.ftl">
export class ${modelJavaClassName} extends ${modelJavaClassName}TypeDef
{
  protected constructor(value: ${modelJavaFieldClassName})
  {
    super(value);
  }
  
  /**
   * Return a new Builder.
   *
   * The generated version of this builder is stateless and so it is safe to return a 
   * reference to a shared instance, if you add functionality to this builder which is
   * not thread safe then you need to change this to return new Builder();
   *
   * @return A new Builder.
   */
  public static newBuilder()
  {
    return ${modelJavaClassName}.theBuilder;
  }
  
  /**
   * Builder for ${modelJavaClassName}.
   */
  static Builder = class extends ${modelJavaClassName}TypeDef.Builder {
    public constructor() {
      super();
    }
    
    public build(value: ${modelJavaFieldClassName}) {
      return new ${modelJavaClassName}(value);
    }
    
    public toValue(instance: ${modelJavaClassName})
    {
      return instance.getValue();
    }
  }
  
  private static theBuilder = new ${modelJavaClassName}.Builder();
<#assign subTemplateName="${.current_template_name!''}"><#include "/proforma/ts/canon-proforma-ts-SubEpilogue.ftl">