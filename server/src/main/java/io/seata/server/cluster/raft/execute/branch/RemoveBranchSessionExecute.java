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
package io.seata.server.cluster.raft.execute.branch;

import io.seata.server.cluster.raft.execute.AbstractRaftMsgExecute;
import io.seata.server.cluster.raft.sync.msg.RaftBaseMsg;
import io.seata.server.cluster.raft.sync.msg.RaftBranchSessionSyncMsg;
import io.seata.server.session.BranchSession;
import io.seata.server.session.GlobalSession;
import io.seata.server.session.SessionHolder;
import io.seata.server.storage.raft.session.RaftSessionManager;

/**
 */
public class RemoveBranchSessionExecute extends AbstractRaftMsgExecute {

    @Override
    public Boolean execute(RaftBaseMsg syncMsg) throws Throwable {
        RaftBranchSessionSyncMsg sessionSyncMsg = (RaftBranchSessionSyncMsg)syncMsg;
        RaftSessionManager raftSessionManager = (RaftSessionManager) SessionHolder.getRootSessionManager(sessionSyncMsg.getGroup());
        GlobalSession globalSession = raftSessionManager.findGlobalSession(sessionSyncMsg.getBranchSession().getXid());
        if (globalSession != null) {
            BranchSession branchSession = globalSession.getBranch(sessionSyncMsg.getBranchSession().getBranchId());
            if (branchSession != null) {
                raftLockManager.localReleaseLock(branchSession);
                globalSession.remove(branchSession);
            }
            if (logger.isDebugEnabled()) {
                logger.debug("removeBranch xid: {},branchId: {}", globalSession.getXid(),
                    sessionSyncMsg.getBranchSession().getBranchId());
            }
        }
        return true;
    }
}
