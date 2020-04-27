package com.cmaple.honeycomb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 类名：异步调用配置类 - CORSConfig
 * 功能描述： 异步调用方法的相关配置类
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：cmaple
 * 创建日期：2020-03-10
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    private static final int CORE_POOL_SIZE = 4;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 100;

    @Bean
    public Executor taskExecutor() {
        // Spring 默认配置是核心线程数大小为1，最大线程容量大小不受限制，队列容量也不受限制。
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数
        executor.setCorePoolSize(CORE_POOL_SIZE);
        // 最大线程数
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        // 队列大小
        executor.setQueueCapacity(QUEUE_CAPACITY);
        // 当最大池已满时，此策略保证不会丢失任务请求，但是可能会影响应用程序整体性能。
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setThreadNamePrefix("My ThreadPoolTaskExecutor-");
        executor.initialize();
        return executor;
    }
}
