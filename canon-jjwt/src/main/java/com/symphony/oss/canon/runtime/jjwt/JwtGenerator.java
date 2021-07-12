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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.client.methods.RequestBuilder;

import com.google.common.collect.ImmutableMap;
import com.symphony.oss.canon.runtime.http.client.IJwtAuthenticationProvider;
import com.symphony.oss.commons.fluent.Fluent;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

/**
 * Base class for JWT Generators.
 * 
 * The default TTL is 5 minutes, which means generated tokens have an expires claim 5 minutes after their issue time.
 * 
 * Call setTtl() to override. Passing a null value removes the expires claim.
 * 
 * @author Bruce Skingle
 *
 * @param <T> Concrete type for fluent methods.
 */
public abstract class JwtGenerator<T extends JwtGenerator<T>> extends Fluent<T> implements IJwtAuthenticationProvider
{
  /**
   * Constructor.
   * 
   * @param type Concrete type for fluent methods.
   */
  public JwtGenerator(Class<T> type)
  {
    super(type);
  }

  private String  subject_;
  private Long    ttl_ = 5 * 60 * 1000L;  // default 5 minutes.
  private String  kid_;
  private String  issuer_;
  private Map<String, Object> claims_ = new HashMap<>();
  
  @Override
  public void authenticate(RequestBuilder builder)
  {
    builder.addHeader(JwtBase.AUTH_HEADER_KEY, JwtBase.AUTH_HEADER_VALUE_PREFIX + createJwt());
  }
  
  @Override
  public String createJwt()
  {
    Date now = new Date();
    
    JwtBuilder jwt = Jwts.builder().setIssuedAt(now);
    
    if(kid_ != null)
      jwt.setHeader(ImmutableMap.of("kid", kid_));
      
    if(issuer_ != null)
      jwt.setIssuer(issuer_);
    
    if(subject_ != null)
      jwt.setSubject(subject_);
    
    if(ttl_ != null)
      jwt.setExpiration(new Date(now.getTime() + ttl_));
    
    for(Entry<String, Object> claim : claims_.entrySet())
    {
      jwt.claim(claim.getKey(), claim.getValue());
    }
    
    return sign(jwt);
  }

  /**
   * Set the value for the issuer claim.
   * 
   * @param issuer the value for the issuer claim.
   * 
   * @return This (fluent method).
   */
  public T withIssuer(String issuer)
  {
    issuer_ = issuer;
    return self();
  }
  
  /**
   * Set the value for the kid header.
   * 
   * @param kid the value for the kid header.
   * 
   * @return This (fluent method).
   */
  public T withKeyId(String kid)
  {
    kid_ = kid;
    return self();
  }
  
  /**
   * Set the value for the subject claim.
   * 
   * @param subject the value for the subject claim.
   * 
   * @return This (fluent method).
   */
  public T withSubject(String subject)
  {
    subject_ = subject;
    return self();
  }
  
  /**
   * Set the time to live in milliseconds for generated tokens.
   * 
   * @param ttl The time to live in milliseconds for generated tokens.
   * 
   * @return This (fluent method).
   */
  public T withTTL(long ttl)
  {
    ttl_ = ttl;
    return self();
  }
  
  /**
   * Add the given claim.
   * 
   * @param name  The name of the claim.
   * @param value The value of the claim.
   * 
   * @return This (fluent method).
   */
  public T withClaim(String name, Object value)
  {
    claims_.put(name, value);
    
    return self();
  }
  
  protected abstract String sign(JwtBuilder builder);
}
