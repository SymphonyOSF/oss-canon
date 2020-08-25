/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon2.generator.java;

import com.symphony.oss.canon2.parser.IGeneratorModelContext;
import com.symphony.oss.canon2.parser.IOpenApiObject;
import com.symphony.oss.canon2.parser.ModelEntity;

public class JavaModelEntity extends ModelEntity
{
  public JavaModelEntity(IOpenApiObject entity, String name, IGeneratorModelContext generatorModelContext,
      String... temaplates)
  {
    super(entity, name, generatorModelContext, temaplates);
  }
}
