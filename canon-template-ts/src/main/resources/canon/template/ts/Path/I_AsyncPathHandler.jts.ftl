<#include "/template/ts/canon-template-ts-Prologue.ftl">
<@setPrologueJavaType model/>
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.symphony.oss.canon.runtime.exception.CanonException;
import com.symphony.oss.canon.runtime.IAsyncEntityHandler;
import com.symphony.oss.fugue.trace.ITraceContext;
import com.symphony.oss.fugue.pipeline.IConsumer;

<@importFieldTypes model true/>
<@importFacadePackages model/>

<#include "/template/ts/Path/Path.ftl">
public interface I${modelJavaClassName}AsyncPathHandler<T> extends IAsyncEntityHandler
{
<#list model.operations as operation>
  <@printMethodJavadoc operation true/>
  void handle${operation.camelCapitalizedName}(
  <#if operation.payload??>
  <@setJavaType operation.payload.schema/>
    @Nonnull  ${fieldType?right_pad(35)} canonPayload,
  </#if>
  <#if operation.response??>
  <@setJavaType operation.response.schema/>
    @Nonnull  ${"IConsumer<${fieldType}>"?right_pad(35)} canonConsumer,
  </#if>
    @Nullable ${"T"?right_pad(35)} canonAuth, 
    @Nonnull  ${"ITraceContext"?right_pad(35)} canonTrace<#if operation.parameters?size != 0>,</#if>
	<#list operation.parameters as parameter>
	  <@setJavaType parameter.schema/>
	  <#if parameter.isRequired>
    @Nonnull  ${fieldType?right_pad(35)} ${parameter.camelName}<#sep>,</#sep>
    <#else>
    @Nullable ${fieldType?right_pad(35)} ${parameter.camelName}<#sep>,</#sep>
    </#if>
	</#list>
	
    ) throws CanonException;
    
</#list>
}
<#include "/template/ts/canon-template-ts-Epilogue.ftl">