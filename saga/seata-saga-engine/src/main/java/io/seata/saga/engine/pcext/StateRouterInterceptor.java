/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.seata.saga.engine.pcext;

import io.seata.saga.engine.exception.EngineExecutionException;
import io.seata.saga.proctrl.Instruction;
import io.seata.saga.proctrl.ProcessContext;
import io.seata.saga.statelang.domain.State;

/**
 * StateRouter Interceptor
 *
 * @see StateRouter
 */
public interface StateRouterInterceptor {

    /**
     * pre route
     *
     * @param context
     * @param state
     * @throws EngineExecutionException
     */
    void preRoute(ProcessContext context, State state) throws EngineExecutionException;

    /**
     * post route
     *
     * @param context
     * @param state
     * @param instruction
     * @param e
     * @throws EngineExecutionException
     */
    void postRoute(ProcessContext context, State state, Instruction instruction, Exception e)
        throws EngineExecutionException;


    boolean match(Class<? extends InterceptableStateRouter> clazz);
}
