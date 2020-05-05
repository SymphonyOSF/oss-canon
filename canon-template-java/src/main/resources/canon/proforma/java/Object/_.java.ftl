<#if model.baseSchema.isGenerateFacade?? && model.baseSchema.isGenerateFacade>
<#include "/proforma/java/canon-proforma-java-Prologue.ftl">
<#assign model=model.type>
<@setPrologueJavaType model/>
import javax.annotation.concurrent.Immutable;

import com.symphony.oss.commons.immutable.ImmutableByteArray;

import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;

import com.symphony.oss.canon.runtime.IEntity;
import com.symphony.oss.canon.runtime.IModelRegistry;

<@importFieldTypes model false/>

import ${javaGenPackage}.${model.camelCapitalizedName}Entity;
import ${javaGenPackage}.I${model.camelCapitalizedName}Entity;
import ${javaGenPackage}.${model.model.camelCapitalizedName}Model;

<#include "/proforma/java/Object/Facade.ftl">
<#include "/proforma/java/canon-proforma-java-Epilogue.ftl">
</#if>