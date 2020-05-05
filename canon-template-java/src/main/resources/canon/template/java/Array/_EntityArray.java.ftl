<#include "/template/java/canon-template-java-Prologue.ftl">
<#assign model=model.type>
<@setPrologueJavaType model/>
import javax.annotation.concurrent.Immutable;

import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

import com.google.common.collect.ImmutableSet;
<@importFieldTypes model true/>
<@importFacadePackages model/>

import com.symphony.oss.canon.runtime.IEntity${modelJavaCardinality};
import com.symphony.oss.canon.runtime.EntityArrayFactory;

import com.symphony.oss.common.dom.json.IImmutableJsonDomNode;
import com.symphony.oss.common.dom.json.ImmutableJsonList;
import com.symphony.oss.common.dom.json.ImmutableJsonSet;
import com.symphony.oss.common.dom.json.IJsonDomNode;
import com.symphony.oss.common.dom.json.Json${modelJavaCardinality};
import com.symphony.oss.common.dom.json.MutableJsonList;
import com.symphony.oss.common.dom.json.MutableJsonSet;

import com.symphony.oss.canon.runtime.Entity${modelJavaCardinality};

<#include "/template/java/Array/Array.ftl">
<@setJavaType model.baseSchema/>
@Immutable
public class ${modelJavaClassName}EntityArray extends Entity${modelJavaCardinality}<${modelJavaElementClassName}>
{
  protected ${modelJavaClassName}EntityArray(IEntity${modelJavaCardinality}<${modelJavaElementClassName}> other)
  {
    super(other);
<@checkItemLimits "    " model "Array" "this"/>
  }
  
  <#-- Constructor from Json   -->  
  protected ${modelJavaClassName}EntityArray(Json${modelJavaCardinality}<?> json${modelJavaCardinality})
  {
    super(json${modelJavaCardinality}.immutify(), json${modelJavaCardinality}.asImmutable${modelJavaCardinality}Of(${modelJavaElementClassName}.class));
<@checkItemLimits "    " model "Array" "this"/>
  }

  public static abstract class Builder extends EntityArrayFactory.Builder implements IEntity${modelJavaCardinality}<${modelJavaElementClassName}>
  {
    private ${modelJavaFieldClassName} elements__ =
    <#switch model.cardinality>
      <#case "SET">
                                          new HashSet<>();
        <#break>
        
      <#default>
                                          new LinkedList<>();
    </#switch>
    
    
    protected Builder()
    {
    }
    
    protected Builder(Builder initial)
    {
      elements__.addAll(initial.elements__);
    }
    
    @Override
    public Immutable${modelJavaFieldClassName} getElements()
    {
      return Immutable${modelJavaCardinality}.copyOf(elements__);
    }
    
    @Override
    public int size()
    {
      return elements__.size();
    }

    public ${modelJavaClassName}.Builder with(${modelJavaElementClassName} element)
    {
      elements__.add(element);
      return (${modelJavaClassName}.Builder)this;
    }

    public ${modelJavaClassName}.Builder with(${modelJavaClassName} elements)
    {
      elements__.addAll(elements.getElements());
      return (${modelJavaClassName}.Builder)this;
    }

    public ${modelJavaClassName}.Builder with(Json${modelJavaCardinality}<?> node)
    {
      elements__.addAll(node.asImmutableListOf(${modelJavaElementClassName}.class));
      return (${modelJavaClassName}.Builder)this;
    }
    
    @Override 
    public ImmutableJson${javaCardinality} getJson${javaCardinality}()
    {
      MutableJson${javaCardinality} jsonArray = new MutableJson${javaCardinality}();
      
      for(${modelJavaElementClassName} value : elements__)
      <#if model.items.baseSchema.isObjectSchema>
        jsonArray.add(value.getJsonObject());
      <#else>
        jsonArray.add(value);
      </#if>
      
      return jsonArray.immutify();
    }
    
    @Override
    public IImmutableJsonDomNode getJsonDomNode()
    {
      return getJson${javaCardinality}();
    }
    
    public abstract ${modelJavaClassName} build();
  }
}
<#include "/template/java/canon-template-java-Epilogue.ftl">