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
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.symphony.oss.canon.runtime.exception.CanonException;
import com.symphony.oss.canon.runtime.exception.NotAuthenticatedException;
import com.symphony.oss.canon.runtime.exception.PermissionDeniedException;
import com.symphony.oss.canon.runtime.http.IRequestAuthenticator;
import com.symphony.oss.canon.runtime.http.IRequestContext;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

/**
 * Base class for an authenticator of JWTs.
 * 
 * @author Bruce Skingle
 *
 * @param <T> The concrete type of the returned authentication context.
 */
public abstract class JwtAuthenticator<T> extends JwtBase implements IRequestAuthenticator<T>
{
  private final Key key_;
  private final Long maxAge_;
  private final String requiredAlgorithm_;
  
  /**
   * Constructor.
   * 
   * @param key               Key to be used to validate signature.
   * @param maxAge            Maximum acceptable age of JWT.
   * @param requiredAlgorithm Signature algorithm which is acceptable.
   */
  public JwtAuthenticator(Key key, Long maxAge, String requiredAlgorithm)
  {
    key_ = key;
    maxAge_ = maxAge;
    requiredAlgorithm_ = requiredAlgorithm;
  }
  
  @Override
  public T authenticate(IRequestContext context) throws NotAuthenticatedException, PermissionDeniedException
  {
    return authenticate(getToken(context));
  }
  
  @Override
  public T authenticate(HttpServletRequest context) throws CanonException
  {
    return authenticate(getToken(context));
  }

  protected T authenticate(String token) throws NotAuthenticatedException, PermissionDeniedException
  {  
    try
    {
      Jws<Claims> parsedJwt = Jwts.parserBuilder()
          .setSigningKey(key_)
          .build()
          .parseClaimsJws(token)
          ;
      
      if(!requiredAlgorithm_.equals(parsedJwt.getHeader().getAlgorithm()))
        throw new NotAuthenticatedException("Invalid JWT Token, unacceptable signature algorithm");
      
      Claims claims = parsedJwt
        .getBody();
      
      Date now = new Date();
      Date expiry = claims.getExpiration();
      
      if(expiry != null && expiry.before(now))
        throw new NotAuthenticatedException("Expired JWT Token");
      
      if(maxAge_ != null)
      {
        Date issuedAt = claims.getIssuedAt();
        
        if(issuedAt == null)
          throw new NotAuthenticatedException("Invalid JWT Token, no issued at attribute");
        
        if(now.getTime() - issuedAt.getTime() > maxAge_)
          throw new NotAuthenticatedException("Invalid JWT Token, too old");
      }
      return extractAuth(claims);
    }
    catch(SecurityException e)
    {
      throw new PermissionDeniedException(e);
    }
    catch(ExpiredJwtException e)
    {
      throw new NotAuthenticatedException("Expired JWT Token");
    }
    catch(RuntimeException e)
    {
      throw new NotAuthenticatedException("Invalid JWT Token", e);
    }
  }

  protected abstract T extractAuth(Claims claims) throws NotAuthenticatedException, PermissionDeniedException;
}
