<#if ! model.baseSchema.isGenerateFacade?? || ! model.baseSchema.isGenerateFacade>
<#include "/template/ts/canon-template-ts-Prologue.ftl">
<@setPrologueJavaType model/>
import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.runtime.IEntity${modelJavaCardinality};
import com.symphony.oss.commons.dom.json.ImmutableJsonList;
import com.symphony.oss.commons.dom.json.ImmutableJsonSet;

<@importFieldTypes model false/>
<@importFacadePackages model/>

<#include "/template/ts/Array/Array.ftl">
<#include "/proforma/ts/Array/Facade.ftl">
<#include "/template/ts/canon-template-ts-Epilogue.ftl">
</#if>