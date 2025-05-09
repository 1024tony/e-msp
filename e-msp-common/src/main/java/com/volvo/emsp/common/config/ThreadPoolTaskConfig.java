package com.volvo.emsp.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义线程池
 *
 * @author gaoyang
 * @since 2025-04-12 13:27
 */
@Configuration
@EnableAsync
public class ThreadPoolTaskConfig {

    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数 - CPU密集设置为跟CPU核⼼数⼀样⼤⼩、IO密集型设置为2倍CPU核⼼数
        executor.setCorePoolSize(4);
        // 最大线程数
        executor.setMaxPoolSize(8);
        // 阻塞队列容量
        executor.setQueueCapacity(128);
        // 线程存活时间
        executor.setKeepAliveSeconds(60);
        // 待任务在关机时完成--表明等待所有线程执行完
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 线程名称前缀
        executor.setThreadNamePrefix("pointer-task-");
        // 拒绝处理策略 - AbortPolicy()：直接抛出异常
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化
        executor.initialize();

        return executor;
    }
}
