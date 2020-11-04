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

import com.symphony.oss.canon.json.model.JsonArray;
import com.symphony.oss.canon.json.model.JsonDomNode;
import com.symphony.oss.canon.json.model.JsonObject;

class YamlCollection
{
  Integer   indentLevel_;
  YamlCollectionType  type_;
  JsonArray.Builder arrayBuilder_;
  JsonObject.Builder objectBuilder_;
  boolean canonocalize_;
  
  YamlCollection(Integer indentLevel, YamlCollectionType  type, boolean canonocalize)
  {
    indentLevel_ = indentLevel;
    type_ = type;
    canonocalize_ = canonocalize;
  }

  Integer getIndentLevel()
  {
    return indentLevel_;
  }
  
  void setIndentLevel(int indentLevel)
  {
    if(indentLevel_ != null)
      throw new IllegalStateException("Indent level is already set to " + indentLevel_);
    
    indentLevel_ = indentLevel;
  }

  YamlCollectionType getType()
  {
    return type_;
  }
  
  boolean isInstance(YamlCollectionType type)
  {
    return type_ == type;
  }
  
  void validate(String name, IParserContext context) throws ParserException
  {
    if(name == null)
    {
      if(type_ == YamlCollectionType.MAPPING)
        throw new SyntaxErrorException("Sequence element found in a mapping context", context);
    }
    else
    {
      if(type_ == YamlCollectionType.SEQUENCE)
        throw new SyntaxErrorException("Mapping found in a sequence context", context);
    }
  }
  
  void put(String name, JsonDomNode value, IParserContext context) throws ParserException
  {
    if(name == null)
    {
      switch(type_)
      {
        case MAPPING:
          throw new SyntaxErrorException("Sequence element found in a mapping context", context);
          
        case COLLECTION:
          type_ = YamlCollectionType.SEQUENCE;
          // fall through
          
        case SEQUENCE:
          if(arrayBuilder_ == null)
          {
            arrayBuilder_ = new JsonArray.Builder();
          }
          
          arrayBuilder_.with(value);
          break;
      }
    }
    else
    {
      switch(type_)
      {
        case SEQUENCE:
          throw new SyntaxErrorException("Mapping found in a sequence context", context);
          
        case COLLECTION:
          type_ = YamlCollectionType.MAPPING;
          // fall through
          
        case MAPPING:
          if(objectBuilder_ == null)
          {
            objectBuilder_ = new JsonObject.Builder().withCanonicalize(canonocalize_);
          }
          
          objectBuilder_.with(context, name, value);
          break;
      }
    }
  }
  
  JsonDomNode build(IParserContext context) throws ParserException
  {
    switch(type_)
    {
      case SEQUENCE:
        return arrayBuilder_.build();
        
      case MAPPING:
        return objectBuilder_.build();
        
      default:
        throw new ParserErrorException("Build called on a collection of type " + type_, context);
    }
  }

  public void setType(YamlCollectionType type, IParserContext context) throws ParserException
  {
    if(type_ == YamlCollectionType.COLLECTION)
      type_ = type;
    else if(type_ != type)
      throw new ParserErrorException("Attempt to force a collection of type " + type_ + " to " + type, context);
  }
  
}
