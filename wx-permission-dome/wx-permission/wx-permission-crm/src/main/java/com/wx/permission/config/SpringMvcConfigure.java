package com.wx.permission.config;

import com.baidu.unbiz.fluentvalidator.exception.RuntimeValidateException;
import com.wx.permission.Interceptor.HttpInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.wx.core.constant.SystemConstant.ViewSys.*;

/**
 * @ClassName SpringMvcConfigure
 * @Author wx
 * @Description mvc配置类
 * @Date 2018-08-19-15:32
 */
@Slf4j @EnableWebMvc @Configuration public class SpringMvcConfigure implements WebMvcConfigurer {

	/**
	 * @param registry view注册器
	 * @methodName: configureViewResolvers
	 * @author: wx
	 * @description: 注册jsp视图解析器
	 * @date: 2018/8/19
	 * @return: void
	 */
	@Override public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix(PREFIX);
		viewResolver.setSuffix(SUFFIX);
		registry.viewResolver(viewResolver);
	}

	/**
	 * @param registry
	 * @methodName: addViewControllers
	 * @author: wx
	 * @description: 设置默认访问页面
	 * @date: 2018/8/23
	 * @return: void
	 */
	@Override public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/err").setViewName(EXCEPTION_VIME);
	}

	/**
	 * @param registry 静态资源处理注册器
	 * @methodName: addResourceHandlers
	 * @author: wx
	 * @description: 注册静态资源处理器
	 * @date: 2018/8/19
	 * @return: void
	 */
	@Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
		/** Start 注册js静态资源处理器 */
		ResourceHandlerRegistration resourceHandlerRegistrationJs = registry.addResourceHandler("/js/**");
		resourceHandlerRegistrationJs.addResourceLocations("/js/");
		/** End */

		/** 注册css静态资源处理器 */
		ResourceHandlerRegistration resourceHandlerRegistrationCss = registry.addResourceHandler("/css/**");
		resourceHandlerRegistrationCss.addResourceLocations("/css/");
		/** End */

		/** 注册Bootstrap静态资源处理器 */
		ResourceHandlerRegistration resourceHandlerRegistrationBootstrap = registry.addResourceHandler("/bootstrap3.3.5/**");
		resourceHandlerRegistrationBootstrap.addResourceLocations("/bootstrap3.3.5/");
		/** End */

		/** 注册assets静态资源处理器 */
		ResourceHandlerRegistration resourceHandlerRegistrationAssets = registry.addResourceHandler("/assets/**");
		resourceHandlerRegistrationAssets.addResourceLocations("/assets/");
		/** End */

		/** Start 注册Ztree静态资源处理器 */
		ResourceHandlerRegistration resourceHandlerRegistrationZtree = registry.addResourceHandler("/ztree/**");
		resourceHandlerRegistrationZtree.addResourceLocations("/ztree/");
		/** End */

		/** Start 注册时间控件静态资源处理器 */
		ResourceHandlerRegistration resourceHandlerRegistrationDatePicker = registry.addResourceHandler("/My97DatePicker/**");
		resourceHandlerRegistrationDatePicker.addResourceLocations("/My97DatePicker/");
		/** End */

	}

	/**
	 * @param registry 拦截注册器
	 * @methodName: addInterceptors
	 * @author: wx
	 * @description: 注册拦截器
	 * @date: 2018/8/19
	 * @return: void
	 */
	@Override public void addInterceptors(InterceptorRegistry registry) {
		// 默认拦截所有请求,如果需要指定后面追加.addPathPatterns()
		InterceptorRegistration interceptorRegistration = registry.addInterceptor(new HttpInterceptor());
		interceptorRegistration.excludePathPatterns("/favicon.ico");
		interceptorRegistration.excludePathPatterns("/signin.jsp");
		interceptorRegistration.excludePathPatterns("/error");
	}

	/**
	 * @param registry
	 * @methodName: addFormatters
	 * @author: wx
	 * @description: 注册转换器
	 * @date: 2018/9/23
	 * @return: void
	 */
	@Override public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new StringToDateConverter());
	}

	/**
	 * @Author wx
	 * @Description 字符串to时间转换器
	 * @Date 2018-9-23
	 */
	private class StringToDateConverter implements Converter<String, Date> {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		@Nullable @Override public Date convert(String s) {
			if (StringUtils.isBlank(s)) {
				return null;
			}
			try {
				return sdf.parse(s);
			} catch (ParseException e) {
				log.error(e.getMessage());
				throw new RuntimeValidateException(e.getMessage());
			}
		}
	}
}
