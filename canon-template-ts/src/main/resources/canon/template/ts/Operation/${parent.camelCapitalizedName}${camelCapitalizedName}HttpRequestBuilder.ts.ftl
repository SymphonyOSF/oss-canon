<#include "/template/ts/canon-template-ts-Prologue.ftl">
<@setPrologueJavaType model/>
<#assign javaModelClassName=model.parent.camelCapitalizedName+model.camelCapitalizedName+"HttpRequestBuilder">
<@setJavaMethod model/>
import javax.annotation.concurrent.Immutable;

import java.util.Collection;

import com.symphony.oss.commons.dom.json.ImmutableJsonList;
import com.symphony.oss.commons.dom.json.ImmutableJsonSet;
import com.symphony.oss.commons.dom.json.MutableJsonList;

import com.symphony.oss.canon.runtime.exception.BadRequestException;
import com.symphony.oss.canon.runtime.http.ParameterLocation;
import com.symphony.oss.canon.runtime.http.client.HttpParameter;

<@importFieldTypes model true/>
<@importFacadePackages model/>

@Immutable
public class ${javaModelClassName} extends ${model.parent.camelCapitalizedName}${model.camelCapitalizedName}HttpRequestOrBuilder
{
  <#if model.payload??>
    <#if model.payload.isMultiple>
  private ${"MutableJsonList"?right_pad(25)          } canonPayload_ = new MutableJsonList();
    <#else>
  private ${methodPayloadElementType?right_pad(25)           } canonPayload_;
    </#if>
  </#if>
  <#list model.parameters as parameter>
    <@setJavaType parameter.schema/>  
  private ${("HttpParameter<" + javaClassName + ">")?right_pad(25)} ${parameter.camelName}_;
  </#list>
  
  public ${javaModelClassName}(${model.model.camelCapitalizedName}HttpModelClient canonClient)
  {
    super(canonClient);
    
  <#list model.parameters as parameter>
    <@setJavaType parameter.schema/>  
    ${parameter.camelName}_ = new HttpParameter<${javaClassName}>("${parameter.name}", ParameterLocation.${parameter.location}, ${parameter.isRequired?c});
  </#list>
  }
  <#if model.payload??>
  
    <#if model.payload.isMultiple>
  <@setJavaType model.payload.schema/>
  @Override
  public MutableJsonList getCanonPayload()
  {
    return canonPayload_;
  }
  
  public ${javaModelClassName} withCanonPayload(${methodPayloadElementType} canonPayload)
  {
    canonPayload_.add(${javaGetValuePrefix}canonPayload${javaGetValuePostfix});
    return this;
  }
  
  public ${javaModelClassName} withCanonPayload(Collection<${methodPayloadElementType}> canonPayload)
  {
    for(${methodPayloadElementType} item : canonPayload)
      canonPayload_.add(${javaGetValuePrefix}item${javaGetValuePostfix});
      
    return this;
  }
    <#else>
  @Override
  public ${methodPayloadElementType} getCanonPayload()
  {
    return canonPayload_;
  }
  
  public ${javaModelClassName} withCanonPayload(${methodPayloadElementType} canonPayload)
  {
    canonPayload_ = canonPayload;
    return this;
  }
    </#if>
  </#if>
  <#list model.parameters as parameter>
    <@setJavaType parameter.schema/>
  
  @Override
  public ${javaClassName} get${parameter.camelCapitalizedName}()
  {
    return ${parameter.camelName}_.getValue();
  }
  
  public ${javaModelClassName} with${parameter.camelCapitalizedName}(${javaClassName} ${parameter.camelName})
  {
    ${parameter.camelName}_.setValue(${parameter.camelName});
    return this;
  }
  </#list>
  
  public ${model.parent.camelCapitalizedName}${model.camelCapitalizedName}HttpRequest build() throws BadRequestException
  {
  <#list model.parameters as parameter>
    <@setJavaType parameter.schema/>  
    ${parameter.camelName}_.validate();
  </#list>
  
    return new ${model.parent.camelCapitalizedName}${model.camelCapitalizedName}HttpRequest(this);
  }
}
<#include "/template/ts/canon-template-ts-Epilogue.ftl">