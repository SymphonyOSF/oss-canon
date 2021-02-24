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

package com.symphony.oss.canon.json;

enum YamlStyle
{
  
  BLOCK(false, false), FLOW(true, false), FLOW_KEY(true, true);
  
  private final boolean flow_;
  private final boolean key_;
  
  private YamlStyle(boolean flow, boolean key)
  {
    flow_ = flow;
    key_ = key;
  }

  public boolean isFlow()
  {
    return flow_;
  }

  public boolean isKey()
  {
    return key_;
  }
  
}
