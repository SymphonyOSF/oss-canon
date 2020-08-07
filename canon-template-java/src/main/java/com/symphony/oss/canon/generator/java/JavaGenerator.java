/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon.generator.java;

import java.util.Map;

import com.symphony.oss.canon.Canon;
import com.symphony.oss.canon.CanonGenerator;
import com.symphony.oss.canon.model.IPathNameConstructor;
import com.symphony.oss.canon.model.ModelElement;

public class JavaGenerator extends CanonGenerator
{
  private IPathNameConstructor templatePathBuilder_ = new JavaPathNameConstructor(Canon.JAVA_GEN_PACKAGE);
  private IPathNameConstructor proformaPathBuilder_ = new JavaPathNameConstructor(Canon.JAVA_FACADE_PACKAGE);
  
  /**
   * Constructor.
   */
  public JavaGenerator()
  {
    super("java");
  }

  @Override
  public IPathNameConstructor getTemplatePathNameConstructor()
  {
    return templatePathBuilder_;
  }
  
  @Override
  public IPathNameConstructor getProformaPathNameConstructor()
  {
    return proformaPathBuilder_;
  }

  @Override
  public void populateDataModel(Map<String, Object> dataModel, ModelElement modelElement)
  {
    dataModel.put("javaElement", modelElement.getCamelCapitalizedName());
  }
}
