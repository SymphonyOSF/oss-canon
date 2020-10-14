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
<#if entity.generateBuilderFacade>
//  /*
//   *  This facade contains a rectangle which is persisted as two integer values, width and height.
//   *  This example assumes that the model includes int attributes called width and height.
//   */
//  static class Rectangle
//  {
//    final int width_;
//    final int height_;
//    
//    public Rectangle(int width, int height)
//    {
//      width_ = width;
//      height_ = height;
//    }
//  }
//  
//  private final Rectangle  size_;

</#if>
  /**
   * Constructor.
   * 
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  protected ${entity.type}(Initialiser initialiser)
  {
    super(initialiser);
<#if entity.generateBuilderFacade>

//    // Create the Rectangle from the stored width and height values.
//    size_ = new Rectangle(getWidth(), getHeight());
</#if>
  }

<#if entity.generateBuilderFacade>
  /**
   * Abstract builder for ${entity.type}. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends ${entity.type}>
    extends ${entity.type}Entity.AbstractBuilder<T,B>
    //implements I${entity.type}InstanceOrBuilder
  {
    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }
    
    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);
    }
<#if entity.generateBuilderFacade>
    
//    /**
//     * Set the width and height using a single Rectangle.
//     * 
//     * For an optional attribute replace @Nonnull with @Nullable and remove the null guard.
//     * 
//     * @param size  the width and height using a single Rectangle.
//     * 
//     * @return this (fluent method).
//     */
//    public T withSize(@Nonnull Rectangle size)
//    {
//      if(size == null)
//        throw new IllegalArgumentException("Value of size is required");
//      
//      setWidth(size.width_);
//      setHeight(size.height_);
//      
//      return self();
//    }
</#if>
  }
<#if entity.generateBuilderFacade>/**

//  /**
//   * Return the size of this object as a Rectangle.
//   * 
//   * @return the size of this object as a Rectangle.
//   */
//  public Rectangle getSize()
//  {
//    return size_;
//  }
</#if>
</#if>
}
<#include "/footer.ftl">
</#if>