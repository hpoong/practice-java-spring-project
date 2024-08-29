package com.hopoong.redis_queue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


@Configuration
public class ThreadPoolConfig {

    @Bean(name = "redisQueueTaskExecutor")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);       // 기본 스레드 수
        executor.setMaxPoolSize(20);        // 최대 스레드 수
        executor.setQueueCapacity(100);     // 대기열 크기
        executor.setThreadNamePrefix("redisQueue-");
        executor.initialize();
        return executor;
    }

}
