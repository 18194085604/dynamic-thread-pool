package com.gjy.middleware.dynamic.thread.pool.sdk.registry;

import com.gjy.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;

import java.util.List;

public interface IRegistry {
    void reportThreadTool(List<ThreadPoolConfigEntity> threadTool);

    void reportThreadPoolConfigParameter(ThreadPoolConfigEntity threadPoolConfigEntity);


}
