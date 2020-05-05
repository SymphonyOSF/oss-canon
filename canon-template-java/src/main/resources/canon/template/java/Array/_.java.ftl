<#if ! model.baseSchema.isGenerateFacade?? || ! model.baseSchema.isGenerateFacade>
<#include "/template/java/canon-template-java-Prologue.ftl">
<@setPrologueJavaType model/>
import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.runtime.IEntity${modelJavaCardinality};
import com.symphony.oss.common.dom.json.ImmutableJsonList;
import com.symphony.oss.common.dom.json.ImmutableJsonSet;

<@importFieldTypes model false/>
<@importFacadePackages model/>

<#include "/template/java/Array/Array.ftl">
<#include "/proforma/java/Array/Facade.ftl">
<#include "/template/java/canon-template-java-Epilogue.ftl">
</#if>