<#if model.baseSchema.isGenerateFacade?? && model.baseSchema.isGenerateFacade>
<#include "/proforma/ts/canon-proforma-ts-Prologue.ftl">
<@setPrologueJavaType model/>
import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.runtime.IEntity;
import com.symphony.oss.canon.runtime.IEntity${modelJavaCardinality};
import com.symphony.oss.commons.dom.json.ImmutableJsonList;
import com.symphony.oss.commons.dom.json.ImmutableJsonSet;

<@importFieldTypes model false/>
import ${javaGenPackage}.*;

<#include "/template/ts/Array/Array.ftl">
<#include "/proforma/ts/Array/Facade.ftl">
<#include "/proforma/ts/canon-proforma-ts-Epilogue.ftl">
</#if>