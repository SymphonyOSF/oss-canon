<#include "/copyrightHeader.ftl"/>
<#include "/macros.ftl"/>

package ${genPackage};

import javax.annotation.concurrent.Immutable;

@Immutable
public class ${entity.type} extends ${entity.type}Entity
{
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
  public ${entity.type}(JsonObject jsonObject, IModelRegistry modelRegistry)
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
  }
  
  /**
   * Abstract builder for ${entity.type}. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<B extends AbstractBuilder<B,T>, T extends ${entity.type}Entity> extends AbstractEntityBuilder<B,T>
  {
    protected AbstractBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractBuilder(Class<B> type, ${entity.type}Entity initial)
    {
      super(type, initial);
    }
  }
}
<#include "/footer.ftl">