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

package com.symphony.oss.canon2.parser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The model object which is passed to a Freemarker template.
 * 
 * @author Bruce Skingle
 *
 */
class FreemarkerModel
{
//  private final String templateName_;
//  private final ITemplateEntity entity_;
//  private final String year_;
//  private final String yearMonth_;
//  private final String date_;
//  private final Object inputSource_;

  public static Map<String, Object> newTemplateModel(IGeneratorModelContext modelContext, String templateName, ITemplateModel entity)
  {
    Map<String, Object> map = new HashMap<>();
    
    modelContext.populateTemplateModel(map);
    
    map.put("templateName",  templateName);
    map.put("model",  entity.getModel());
    map.put("entity",  entity);
    
    Date now = new Date();
    
    map.put("year",  new SimpleDateFormat("yyyy").format(now));
    map.put("yearMonth",  new SimpleDateFormat("yyyy-MM").format(now));
    map.put("date",  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").format(now));
    map.put("inputSource",  modelContext.getSourceContext().getInputSource());
    
    return map;
  }

//  public String getTemplateName()
//  {
//    return templateName_;
//  }
//
//  public ITemplateEntity getEntity()
//  {
//    return entity_;
//  }
//
//  public String getYear()
//  {
//    return year_;
//  }
//
//  public String getYearMonth()
//  {
//    return yearMonth_;
//  }
//
//  public String getDate()
//  {
//    return date_;
//  }
//
//  public Object getInputSource()
//  {
//    return inputSource_;
//  }

}
