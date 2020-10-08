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
 *    Template name        template/Object/_.java.ftl
 *    At                   2020-10-08 13:45:16 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.canon2.model;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableSet;
import com.symphony.oss.canon.json.model.JsonObject;
import com.symphony.oss.canon2.runtime.java.IObjectEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.JsonObjectEntityInitialiser;
import com.symphony.oss.canon2.runtime.java.ModelRegistry;
import com.symphony.oss.canon2.runtime.java.ObjectEntity;

/**
 * Implementation for Object PathsObject
 * Generated from PathsObject at {entity.context.path}
 */
@Immutable
public class PathsObject extends ObjectEntity
{
  /** Type ID */
  public static final String  TYPE_ID = "com.symphony.oss.canon2.model.PathsObject";
  /** Type version */
  public static final String  TYPE_VERSION = "1.0";
  /** Type major version */
  public static final Integer TYPE_MAJOR_VERSION = 1;
  /** Type minor version */
  public static final Integer TYPE_MINOR_VERSION = 0;
  /** Factory instance */
  public static final Factory FACTORY = new Factory();

  private final ImmutableSet<String>        unknownKeys_;

  /**
   * Constructor.
   *
   * @param initialiser Initialiser, may be JSON serialisation, builder or another instance.
   */
  public PathsObject(Initialiser initialiser)
  {
    super(initialiser);

    if(initialiser instanceof JsonObjectEntityInitialiser)
    {
      JsonObjectEntityInitialiser jsonInitialiser = (JsonObjectEntityInitialiser)initialiser;

      unknownKeys_ = jsonInitialiser.getCanonUnknownKeys();
    }
    else
    {
      IInstanceOrBuilder builder =  initialiser.getInstanceOrBuilder();

      if(builder == null)
      {
        throw new IllegalArgumentException("Initializer is not an JsonObjectEntityInitialiser but getInstanceOrBuilder() returns null");
      }
      unknownKeys_ = builder.getCanonUnknownKeys();
    }
  }
  @Override
  public ImmutableSet<String> getCanonUnknownKeys()
  {
    return unknownKeys_;
  }

  @Override
  public boolean equals(Object obj)
  {
    if(obj instanceof PathsObject)
      return toString().equals(((PathsObject)obj).toString());

    return false;
  }

  @Override
  public int hashCode()
  {
    return toString().hashCode();
  }


  /**
   * Factory class for PathsObject.
   */
  public static class Factory extends ObjectEntity.Factory<PathsObject>
  {
    @Override
    public String getCanonType()
    {
      return TYPE_ID;
    }

    /**
     * Return the type version (_version JSON attribute) for entities created by this factory.
     *
     * @return The type version for entities created by this factory.
     */
    public String getCanonVersion()
    {
      return TYPE_VERSION;
    }

    /**
     * Return the major type version for entities created by this factory.
     *
     * @return The major type version for entities created by this factory.
     */
    public @Nullable Integer getCanonMajorVersion()
    {
      return TYPE_MAJOR_VERSION;
    }

    /**
     * Return the minjor type version for entities created by this factory.
     *
     * @return The minor type version for entities created by this factory.
     */
    public @Nullable Integer getCanonMinorVersion()
    {
      return TYPE_MINOR_VERSION;
    }

    @Override
    public PathsObject newInstance(JsonObject jsonObject, ModelRegistry modelRegistry)
    {
      return new PathsObject(new JsonInitialiser(jsonObject, modelRegistry));
    }
  }



  /**
   * Initialiser for PathsObject
   */
  
  public interface Initialiser extends IObjectEntityInitialiser
  {
    IInstanceOrBuilder getInstanceOrBuilder();
  }

  public static class JsonInitialiser extends JsonObjectEntityInitialiser implements Initialiser
  {
    public JsonInitialiser(JsonObject jsonObject, ModelRegistry modelRegistry)
    {
      super(jsonObject, modelRegistry);
    }

    @Override
    public IInstanceOrBuilder getInstanceOrBuilder()
    {
      return null;
    }
  }

  /**
   * Instance or Builder for Object PathsObject
   */
  public interface IInstanceOrBuilder extends IObjectEntityInitialiser
  {
  }


  /**
   * Abstract builder for PathsObject. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <T> The concrete type of the builder, used for fluent methods.
   * @param <B> The concrete type of the built object.
   */
  public static abstract class AbstractBuilder<T extends AbstractBuilder<T,B>, B extends PathsObject>
    extends ObjectEntity.AbstractBuilder<T,B>
    implements IInstanceOrBuilder, Initialiser
  {

    protected AbstractBuilder(Class<T> type)
    {
      super(type);
    }

    @Override
    public IInstanceOrBuilder getInstanceOrBuilder()
    {
      return this;
    }

    protected AbstractBuilder(Class<T> type, B initial)
    {
      super(type, initial);

    }

    @Override
    public T withValues(JsonObject jsonObject, ModelRegistry modelRegistry)
    {
      return super.withValues(jsonObject, modelRegistry);
    }

    /* void populateAllFields(List<Object> result)
    {
    }*/

    @Override
    public JsonObject getJsonObject()
    {
      JsonObject.Builder builder = new JsonObject.Builder();

      builder.addIfNotNull(JSON_TYPE, PathsObject.TYPE_ID);
      builder.addIfNotNull(JSON_VERSION, PathsObject.TYPE_VERSION);

      populateJson(builder);

      return builder.build();
    }

    @Override
    public void populateJson(JsonObject.Builder builder)
    {
      super.populateJson(builder);
    }

  /**
   * Builder for PathsObject
   */
  public static class Builder extends PathsObject.AbstractBuilder<Builder, PathsObject>
  {
    /**
     * Constructor.
     */
    public Builder()
    {
      super(Builder.class);
    }

    /**
     * Constructor initialised from another object instance.
     *
     * @param initial An instance of the built type from which values are to be initialised.
     */
    public Builder(PathsObject initial)
    {
      super(Builder.class, initial);
    }

    @Override
    protected PathsObject construct()
    {
      return new PathsObject(this);
    }
  }


    @Override
    public String getCanonType()
    {
      return TYPE_ID;
    }

    @Override
    public String getCanonVersion()
    {
      return TYPE_VERSION;
    }

    @Override
    public @Nullable Integer getCanonMajorVersion()
    {
      return TYPE_MAJOR_VERSION;
    }

    @Override
    public @Nullable Integer getCanonMinorVersion()
    {
      return TYPE_MINOR_VERSION;
    }
  }

  // entity.name PathsObject
  // entity.class class com.symphony.oss.canon2.generator.java.JavaObjectSchemaTemplateModel
}

/*----------------------------------------------------------------------------------------------------
 * End of template template/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */