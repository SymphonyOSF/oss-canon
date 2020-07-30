<#include "/template/ts/canon-template-ts-Prologue.ftl">
<#assign model=model.type>
<@setPrologueJavaType model/>
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.symphony.oss.commons.immutable.ImmutableByteArray;

import com.symphony.oss.canon.runtime.IEntity;
import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.canon.runtime.CanonRuntime;
import com.symphony.oss.canon.runtime.Entity;
import com.symphony.oss.canon.runtime.EntityBuilder;
import com.symphony.oss.canon.runtime.EntityFactory;
import com.symphony.oss.canon.runtime.IBuilderFactory;

import com.symphony.oss.commons.type.provider.IBooleanProvider;
import com.symphony.oss.commons.type.provider.IStringProvider;
import com.symphony.oss.commons.type.provider.IIntegerProvider;
import com.symphony.oss.commons.type.provider.ILongProvider;
import com.symphony.oss.commons.type.provider.IFloatProvider;
import com.symphony.oss.commons.type.provider.IDoubleProvider;
import com.symphony.oss.commons.type.provider.IImmutableByteArrayProvider;
import com.symphony.oss.commons.dom.json.IJsonDomNode;
import com.symphony.oss.commons.dom.json.IImmutableJsonDomNode;
import com.symphony.oss.commons.dom.json.ImmutableJsonList;
import com.symphony.oss.commons.dom.json.ImmutableJsonSet;
import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonList;
import com.symphony.oss.commons.dom.json.MutableJsonSet;
import com.symphony.oss.commons.dom.json.MutableJsonObject;
import com.symphony.oss.commons.dom.json.JsonArray;
import com.symphony.oss.commons.dom.json.JsonList;
import com.symphony.oss.commons.dom.json.JsonSet;

import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.Iterator;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
<@importFieldTypes model true/>
<@importFacadePackages model/>

<#include "/template/ts/Object/Object.ftl">
@Immutable
@SuppressWarnings("unused")
<#if model.superSchema??>
public abstract class ${modelJavaClassName}Entity extends ${model.superSchema.baseSchema.camelCapitalizedName}
<#else>
public abstract class ${modelJavaClassName}Entity extends Entity
</#if>
 implements I${modelJavaClassName}, I${model.model.camelCapitalizedName}ModelEntity<#list model.superClasses as s><#if s.isComponent>, I${s.camelCapitalizedName}</#if></#list>
{
  /** Type ID */
  public static final String  TYPE_ID = "${model.model.canonId}.${model.name}";
  /** Type version */
  public static final String  TYPE_VERSION = "${model.model.canonVersion}";
  /** Type major version */
  public static final Integer TYPE_MAJOR_VERSION = ${model.model.canonMajorVersion};
  /** Type minor version */
  public static final Integer TYPE_MINOR_VERSION = ${model.model.canonMinorVersion};
  /** Factory instance */
  public static final Factory FACTORY = new Factory();
  
  /**
   *  Builder factory instance
   *
   *  @deprecated use <code>new ${modelJavaClassName}.Builder()</code> or <code>new ${modelJavaClassName}.Builder(I${modelJavaClassName}Entity)</code> 
   */
  @Deprecated
  public static final IBuilderFactory<I${modelJavaClassName}Entity, Builder> BUILDER = new BuilderFactory();
