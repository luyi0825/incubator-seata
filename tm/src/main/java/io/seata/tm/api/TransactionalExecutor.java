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
package io.seata.tm.api;

import io.seata.tm.api.transaction.TransactionInfo;

/**
 * Callback for executing business logic in a global transaction.
 *
 */
public interface TransactionalExecutor {

    /**
     * Execute the business logic here.
     *
     * @return What the business logic returns.
     * @throws Throwable Any throwable during executing.
     */
    Object execute() throws Throwable;

    /**
     * transaction conf or other attr
     * @return transaction info
     */
    TransactionInfo getTransactionInfo();

    /**
     * The enum Code.
     */
    enum Code {

        /**
         * Unknown code.
         */
        Unknown,
        /**
         * Begin failure code.
         */
        //
        BeginFailure,

        /**
         * Commit failure code.
         */
        //
        CommitFailure,

        /**
         * Rollback failure code.
         */
        //
        RollbackFailure,

        /**
         * Rollback done code.
         */
        //
        RollbackDone,

        /**
         * Report failure code.
         */
        //
        ReportFailure,

        /**
         * Rollback retrying code.
         */
        //
        Rollbacking
    }

    /**
     * The type Execution exception.
     */
    class ExecutionException extends Exception {

        private GlobalTransaction transaction;

        private Code code;

        private Throwable originalException;

        /**
         * Instantiates a new Execution exception.
         *
         * @param transaction the transaction
         * @param cause       the cause
         * @param code        the code
         */
        public ExecutionException(GlobalTransaction transaction, Throwable cause, Code code) {
            this(transaction, cause, code, null);
        }

        /**
         * Instantiates a new Execution exception.
         *
         * @param transaction       the transaction
         * @param code              the code
         * @param originalException the original exception
         */
        public ExecutionException(GlobalTransaction transaction, Code code, Throwable originalException) {
            this(transaction, null, code, originalException);
        }

        /**
         * Instantiates a new Execution exception.
         *
         * @param transaction       the transaction
         * @param cause             the cause
         * @param code              the code
         * @param originalException the original exception
         */
        public ExecutionException(GlobalTransaction transaction, Throwable cause, Code code,
                                  Throwable originalException) {
            this(transaction, null, cause, code, originalException);
        }

        /**
         * Instantiates a new Execution exception.
         *
         * @param transaction       the transaction
         * @param message           the message
         * @param cause             the cause
         * @param code              the code
         * @param originalException the original exception
         */
        public ExecutionException(GlobalTransaction transaction, String message, Throwable cause, Code code,
                                  Throwable originalException) {
            super(message, cause);
            this.transaction = transaction;
            this.code = code;
            this.originalException = originalException;
        }

        /**
         * Gets transaction.
         *
         * @return the transaction
         */
        public GlobalTransaction getTransaction() {
            return transaction;
        }

        /**
         * Sets transaction.
         *
         * @param transaction the transaction
         */
        public void setTransaction(GlobalTransaction transaction) {
            this.transaction = transaction;
        }

        /**
         * Gets code.
         *
         * @return the code
         */
        public Code getCode() {
            return code;
        }

        /**
         * Sets code.
         *
         * @param code the code
         */
        public void setCode(Code code) {
            this.code = code;
        }

        /**
         * Gets original exception.
         *
         * @return the original exception
         */
        public Throwable getOriginalException() {
            return originalException;
        }

        /**
         * Sets original exception.
         *
         * @param originalException the original exception
         */
        public void setOriginalException(Throwable originalException) {
            this.originalException = originalException;
        }
    }
}
