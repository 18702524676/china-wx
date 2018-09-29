package com.wx.core.utils;


import com.wx.core.exception.BusinessJsonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @Author wx
 * @Description spring 的工具
 * @Date  2018-8-14
 */
@Slf4j
@Configuration
public class SpringUtils implements ApplicationContextAware {


	private static ApplicationContext applicationContext = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringUtils.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static void publishEvent(ApplicationEvent event) {
		if (getApplicationContext() == null) {
			return;
		}
		getApplicationContext().publishEvent(event);
	}

	public static Object getBean(String beanName) {
		if (getApplicationContext() == null) {
			return null;
		}
		try {
			return getApplicationContext().getBean(beanName);
		} catch (Exception e) {
			log.error("获取Bean失败！", e);
			throw new BusinessJsonException("获取Bean失败！");
		}
	}

	public static <T> T getBean(String beanName, Class<T> classType) {
		if (getApplicationContext() == null) {
			return null;
		}
		try {
			return getApplicationContext().getBean(beanName, classType);
		} catch (Exception e) {
			log.error("获取Bean失败！", e);
			throw new BusinessJsonException("获取Bean失败！");
		}
	}

	public static <T> T getBean(Class classType) {
		if (getApplicationContext() == null) {
			return null;
		}
		try {
			return (T)getApplicationContext().getBean(classType);
		} catch (Exception e) {
			log.error("获取Bean失败！", e);
			throw new BusinessJsonException("获取Bean失败！");
		}
	}

	public static String getProperty(String key) {
		return getProperty(key, null);
	}

	public static String getProperty(String key, String defaultValue) {
		String result = defaultValue;
		Environment environment = (Environment) getBean(Environment.class);
		if (environment != null) {
			result = environment.getProperty(key, defaultValue);
		}
		return result;
	}

/*	public static <T> T getBean(Class<T> classType) {
		if (applicationContext == null) {
			return null;
		}
		try {
			return applicationContext.getBean((Class<T>) classType);
		} catch (Exception e) {
			logger.error("获取Bean失败！", e);
			throw new BusinessException(null, "获取Bean失败！");
		}
	}*/
}