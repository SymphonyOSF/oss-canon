/*
 *
 *
 * Copyright 2017 Symphony Communication Services, LLC.
 *
 * Licensed to The Symphony Software Foundation (SSF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The SSF licenses this file
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

package com.symphony.oss.canon.test.oneofeverything;

import java.io.IOException;

import com.symphony.oss.fugue.server.http.FugueHttpServer;

public class OneOfEverythingServer
{
  public static void main(String[] argv) throws IOException
  {
    FugueHttpServer server = new FugueHttpServer.Builder()
        .withApplicationName("OneOfEverythingServer")
        .withHttpPort(7009)
        .build();
    
    server.start();
    
    System.out.println("Server started, press RETURN to terminate");
    System.in.read();
    
    System.out.println("Stopping...");
    
    server.stop();
    
    System.out.println("Finished.");
  }
}
