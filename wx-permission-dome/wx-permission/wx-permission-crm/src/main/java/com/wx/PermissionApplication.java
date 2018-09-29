package com.wx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @EnableCaching redis缓存方式使用注解
 */
@EnableCaching
@EnableScheduling
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.wx.permission.dao")
@ComponentScan(basePackages = { "com.wx" })
public class PermissionApplication {
	public static void main(String[] args) {
		SpringApplication.run(PermissionApplication.class, args);
	}
}
