<#if entity.generateFacade>
<#include "/copyrightHeader.ftl"/>
<#include "/macros.ftl"/>

package ${genPackage};

import javax.annotation.concurrent.Immutable;


/**
 * Facade for Object  ${entity.name} ${model.name}
 * Object ${entity}
<#if entity.summary??>
 *
 * ${entity.summary}
</#if>
<#if entity.description??>
 *
<#list entity.description as description>
 * ${description}
</#list>
</#if>
 * Generated from ${entity}
 */
@Immutable
public class ${entity.type} extends ${entity.type}Entity
{
  /**
   * Constructor.
   * 
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  protected ${entity.type}(Initialiser initialiser)
  {
    super(initialiser);
  }
  
  <#-- 
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public ${entity.type}(AbstractBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public ${entity.type}(JsonObject jsonObject, ModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public ${entity.type}(${entity.type} other)
  {
    super(other);
  } -->

  public interface IInstanceOrBuilder extends ${entity.type}Entity.IInstanceOrBuilder
  {
  }
  
  /**
   * Abstract builder for ${entity.type}. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends ${entity.type}>
    extends ${entity.type}Entity.AbstractBuilder<T,B>
    implements IInstanceOrBuilder
  {
    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);
    }
  }
}
<#include "/footer.ftl">
</#if>