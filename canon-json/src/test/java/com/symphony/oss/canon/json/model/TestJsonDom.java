/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.canon.json.model;

import org.junit.Assert;
import org.junit.Test;


public class TestJsonDom
{
  @Test
  public void testEmptyObject()
  {
    JsonObject emptyObject = new JsonObject.Builder()
      .build();
    
    test("{}\n", emptyObject.toString());
  }
  
  @Test
  public void testBuiltObject()
  {
    JsonObject builtObject = new JsonObject.Builder()
        .with("Bruce", "Skingle")
        .with("Mike", "Harmon")
        .with("MauritzioVeryLongNameDude", "Green")
        .build();
    
//    JsonArray builtArray = new JsonArray.Builder()
//        .with(builtObject)
//        .build();
    
    test("{\n" + 
        "  \"Bruce\":\"Skingle\",\n" + 
        "  \"MauritzioVeryLongNameDude\":\"Green\",\n" + 
        "  \"Mike\":\"Harmon\"\n" + 
        "}\n", builtObject.toString());
  }
  
  
  
  
  private void test(String expected, String received)
  {
    if(!expected.equals(received))
    {
      System.out.format("expected %s%nreceived %s%n", expected, received);
      System.out.flush();
      
      Assert.assertEquals(expected, received);
    }
  }
}