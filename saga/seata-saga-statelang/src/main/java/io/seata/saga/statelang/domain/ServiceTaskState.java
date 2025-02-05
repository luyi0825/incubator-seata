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
package io.seata.saga.statelang.domain;

import java.util.List;

/**
 * ServiceTask State, be used to invoke a service
 *
 */
public interface ServiceTaskState extends TaskState {

    /**
     * Service type: such as SpringBean, SOFA RPC, default is StringBean
     *
     * @return the service type
     */
    String getServiceType();

    /**
     * service name
     *
     * @return the service name
     */
    String getServiceName();

    /**
     * service method
     *
     * @return the service method
     */
    String getServiceMethod();

    /**
     * parameter types
     *
     * @return the parameter types
     */
    List<String> getParameterTypes();

    /**
     * Is it necessary to persist the service execution log? default is true
     *
     * @return the boolean
     */
    boolean isPersist();

    /**
     * Is update last retry execution log, default append new
     *
     * @return the boolean
     */
    Boolean isRetryPersistModeUpdate();

    /**
     * Is update last compensate execution log, default append new
     *
     * @return the boolean
     */
    Boolean isCompensatePersistModeUpdate();
}
