/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon2.generator;

/**
 * A template model which contains named entities.
 * 
 * @author Bruce Skingle
 *
 */
public interface ITemplateModelNameSpace
{
  /**
   * Return true iff the given name already exists in this name space.
   * 
   * @param name The name of interest.
   * 
   * @return true iff the given name already exists in this name space.
   */
  boolean hasName(String name);
}
