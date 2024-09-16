package com.gjy.middleware.dynamic.thread.pool.sdk.trigger.job;

import com.alibaba.fastjson.JSON;
import com.gjy.middleware.dynamic.thread.pool.sdk.domain.IDynamicThreadPoolService;
import com.gjy.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;
import com.gjy.middleware.dynamic.thread.pool.sdk.registry.IRegistry;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * @description 线程池数据上报任务
 */
public class ThreadPoolDataReportJob {

    private Logger logger = LoggerFactory.getLogger(ThreadPoolDataReportJob.class);

    private final IRegistry registry;

    private final IDynamicThreadPoolService iDynamicThreadPoolService;

    public ThreadPoolDataReportJob(IRegistry registry, IDynamicThreadPoolService iDynamicThreadPoolService) {
        this.registry = registry;
        this.iDynamicThreadPoolService = iDynamicThreadPoolService;
    }

    @Scheduled(cron = "0/20 * * * * ?")
    public void execReportThreadPoolList() {
        List<ThreadPoolConfigEntity> threadPoolConfigEntities = iDynamicThreadPoolService.queryThreadPoolList();
        registry.reportThreadTool(threadPoolConfigEntities);
        logger.info("动态线程池，上报线程池信息：{}", JSON.toJSONString(threadPoolConfigEntities));
        for (ThreadPoolConfigEntity threadPoolConfigEntity : threadPoolConfigEntities) {
            registry.reportThreadPoolConfigParameter(threadPoolConfigEntity);
            logger.info("动态线程池，上报线程池配置：{}", JSON.toJSONString(threadPoolConfigEntity));
        }
    }
}
