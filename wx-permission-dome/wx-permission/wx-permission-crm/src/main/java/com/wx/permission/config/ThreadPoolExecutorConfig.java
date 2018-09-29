package com.wx.permission.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author wx
 * @Description 自定义线程池配置类
 * @Date 2018-9-24
 */
@EnableAsync
@Configuration
public class ThreadPoolExecutorConfig {
	/**
	 * 核心线程数
	 */
	private int corePoolSize = 100;

	/**
	 * 最大线程数
	 */
	private int maxPoolSize = 200;

	/**
	 * 队列容量
	 */
	private int queueCapacity = 100;

	@Bean
	public ThreadPoolTaskExecutor myExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setThreadNamePrefix("MyExecutor-");
		
		// rejection-policy：当pool已经达到max size的时候，如何处理新任务
		// CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.initialize();
		return executor;
	}
}
