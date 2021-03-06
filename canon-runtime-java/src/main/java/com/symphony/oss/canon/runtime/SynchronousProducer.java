/*
 * Copyright 2017 Symphony Communication Services, LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.symphony.oss.canon.runtime;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.symphony.oss.fugue.pipeline.IConsumer;
import com.symphony.oss.fugue.trace.ITraceContext;

/**
 * A simple implementation of IProducer which calls
 * listeners in the current thread.
 * 
 * If listener implementations block then there may be
 * performance issues so they should not do so, but they
 * need not be thread safe.
 * @author Bruce Skingle
 *
 * @param <V> The listener payload
 */
public class SynchronousProducer<V> implements IProducerImpl<V>
{
  private static Logger      log_ = LoggerFactory.getLogger(SynchronousProducer.class);
  
  private List<IConsumer<V>> listeners_;

  @Override
  public synchronized void addListener(IConsumer<V> listener)
  {
    if(listeners_ == null)
      listeners_ = new LinkedList<>();
    
    listeners_.add(listener);
  }
  
  @Override
  public synchronized boolean removeListener(IConsumer<V> listener)
  {
    if(listeners_ == null)
      return false;
    
    return listeners_.remove(listener);
  }
  
  @Override
  public void produce(V value, ITraceContext trace)
  {
    if(listeners_ != null)
    {
      for(IConsumer<V> listener : listeners_)
      {
        notify(listener, value, trace);
      }
    }
  }

  protected void notify(IConsumer<V> listener, V value, ITraceContext trace)
  {
    try
    {
      listener.consume(value, trace);
    }
    catch(RuntimeException e)
    {
      log_.error("Unknown exception thrown by Monitor listener (removed)", e);
      removeListener(listener);
    }
  }
}
