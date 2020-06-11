/*
 *
 *
 * Copyright 2018 Symphony Communication Services, LLC.
 *
 * Licensed to The Symphony Software Foundation (SSF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.symphony.oss.canon.runtime.jjwt;

import java.security.Key;

import io.jsonwebtoken.Claims;

/**
 * An example JwtAutheticator which returns the subject claim as a String as the Authentication context.
 * 
 * This implementation has a default value which is returned in the case where a valid token has no subject claim.
 * In a real implementation one would probably throw a NotAuthenticatedException to return an HTTP 401 in that case.
 * 
 * @author Bruce Skingle
 *
 */
public class JwtSubjectAuthenticator extends JwtAuthenticator<String>
{
  private String defaultSubject_;

  /**
   * Constructor.
   * 
   * @param key               Key to be used to validate signature.
   * @param maxAge            Maximum acceptable age of JWT.
   * @param defaultSubject    Value to return if there is no subject claim.
   * @param algorithm         Signature algorithm which is acceptable.
   */
  public JwtSubjectAuthenticator(Key key, Long maxAge, String defaultSubject, String algorithm)
  {
    super(key, maxAge, algorithm);
    defaultSubject_ = defaultSubject;
  }

  @Override
  protected String extractAuth(Claims claims)
  {
    String subject = claims.getSubject();
    
    if(subject == null)
      return defaultSubject_;
    
    return subject;
  }
}
