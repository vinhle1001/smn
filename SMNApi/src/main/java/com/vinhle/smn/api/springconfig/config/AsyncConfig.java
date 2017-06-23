package com.vinhle.smn.api.springconfig.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

public class AsyncConfig extends AsyncConfigurerSupport {

    @Value("${async.core-size}")
    private int coreSize;

    @Value("${async.pool-size}")
    private int poolSize;

    @Value("${async.queen-size}")
    private int queenSize;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(coreSize);
        executor.setMaxPoolSize(poolSize);
        executor.setQueueCapacity(queenSize);
        executor.setThreadNamePrefix("SMNPool-");
        executor.initialize();
        return executor;
    }


}
