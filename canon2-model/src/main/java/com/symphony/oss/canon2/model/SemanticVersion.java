/**
 * GENERATED CODE - DO NOT EDIT OR CHECK IN TO SOURCE CODE CONTROL
 *
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *----------------------------------------------------------------------------------------------------
 * Generated from
 *    Input source         canon.json
 *    Generator groupId    org.symphonyoss.s2.canon
 *              artifactId canon2-generator-java
 *    Template name        template/TypeDef/_.java.ftl
 *    At                   2020-11-10 17:41:51 GMT
 *----------------------------------------------------------------------------------------------------
 */
package com.symphony.oss.canon2.model;
  
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonString;
import com.symphony.oss.canon2.runtime.java.TypeDef;

/**
 * TypeDef implementation for canon.SemanticVersion
 * Generated from SemanticVersion at {entity.context.path}
 */
@Immutable
public class SemanticVersion extends TypeDef implements Comparable<SemanticVersion>
{
  @Deprecated
  private static Builder theBuilder = new Builder();
  
  private final String value_;

  /**
   * Constructor from a String value.
   *
   * @param value the value of the required instance.
   */
  public SemanticVersion(@Nonnull String value)
  {
    value_ = Objects.requireNonNull(value);
// schema.class class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel
// name value
  }

  /**
   * Constructor from a JSON value node.
   *
   * @param value the value of the required instance.
   */
  SemanticVersion(@Nonnull JsonDomNode node)
  {
    Objects.requireNonNull(node);

    if(node instanceof JsonString)
    {
      value_ = ((JsonString)node).asString();
// schema.class class com.symphony.oss.canon2.generator.java.JavaStringSchemaTemplateModel
// name value
    }
    else
    {
      throw new IllegalArgumentException("value must be an instance of JsonString not " + node.getClass().getName());
    }
  }
  
  /**
   * Return the wrapped value.
   * 
   * @return the wrapped value.
   */
  public String getValue()
  {
    return value_;
  }
  
  @Override
  public @Nonnull String toString()
  {
    return value_.toString();
  }
  
  @Override
  public int hashCode()
  {
    return value_.hashCode();
  }
  
  /**
   * Return the value as a String.
   * 
   * @return the value as a String.
   */
  public @Nonnull String asString()
  {
    return value_;
  }

  @Override
  public boolean equals(Object obj)
  {
    if(obj instanceof SemanticVersion)
    {
      return getValue().equals(((SemanticVersion)obj).getValue());
    }
    
    return false;
  }
  
  @Override
  public int compareTo(SemanticVersion other)
  {
    return getValue().compareTo(other.getValue());
  }
  
  /**
   * Return a new Builder.
   *
   * @deprecated use new SemanticVersion(String value)
   *
   * @return A new Builder.
   */
  @Deprecated
  public static Builder newBuilder()
  {
    return theBuilder;
  }
  
  /**
   * Builder.
   *
   * @deprecated use new SemanticVersion(String value)
   *
   */
  @Deprecated
  public static class Builder
  {
    private Builder()
    {
    }

    /**
     * Return a new instance.
     *
     * @param value The value for the new instance.
     *
     * @deprecated use new SemanticVersion(String value)
     *
     * @return A new instance.
     */
    @Deprecated
    public SemanticVersion build(@Nonnull String value)
    {
      return new SemanticVersion(value);
    }
    
    /**
     * Return the value from an instance.
     *
     * @param instance an instance.
     *
     * @deprecated use instance.getValue()
     *
     * @return the value from an instance.
     */
    @Deprecated
    public String toValue(SemanticVersion instance)
    {
      return instance.getValue();
    }
  }
}

/*----------------------------------------------------------------------------------------------------
 * End of template template/TypeDef/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */