<#if model.baseSchema.isGenerateFacade?? && model.baseSchema.isGenerateFacade>
<#include "/proforma/java/canon-proforma-java-Prologue.ftl">
<@setPrologueJavaType model/>
import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.runtime.IEntity;
import com.symphony.oss.canon.runtime.IEntity${modelJavaCardinality};
import com.symphony.oss.common.dom.json.ImmutableJsonList;
import com.symphony.oss.common.dom.json.ImmutableJsonSet;

<@importFieldTypes model false/>
import ${javaGenPackage}.*;

<#include "/template/java/Array/Array.ftl">
<#include "/proforma/java/Array/Facade.ftl">
<#include "/proforma/java/canon-proforma-java-Epilogue.ftl">
</#if>