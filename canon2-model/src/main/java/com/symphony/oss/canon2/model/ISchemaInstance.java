/*
 *
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
 */

package com.symphony.oss.canon2.model;

import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.symphony.oss.canon2.core.CanonModelContext;
import com.symphony.oss.canon2.core.ICanonModelEntity;
import com.symphony.oss.canon2.core.ResolvedOpenApiObject.SingletonBuilder;
import com.symphony.oss.canon2.core.ResolvedSchema.AbstractBuilder;
import com.symphony.oss.canon2.core.SchemaTemplateModelType;
import com.symphony.oss.canon2.core.SourceContext;

/**
 * A canon schema instance.
 * 
 * @author Bruce Skingle
 *
 */
public interface ISchemaInstance extends ICanonModelEntity
{

  SchemaTemplateModelType getSchemaType();

  void link(SingletonBuilder openApiObjectBuilder, CanonModelContext modelContext, SourceContext sourceContext,
      Consumer<AbstractBuilder<? extends ISchemaInstance, ?, ?>> builderConsumer, String uri);
  
  /**
   * Return the value of the x-canon-builderFacade attribute.
   *
   * @return the value of the x-canon-builderFacade attribute.
   */
  @Nullable Boolean getXCanonBuilderFacade();

  /**
   * Return the value of the x-canon-facade attribute.
   *
   * @return the value of the x-canon-facade attribute.
   */
  @Nullable Boolean getXCanonFacade();
}
