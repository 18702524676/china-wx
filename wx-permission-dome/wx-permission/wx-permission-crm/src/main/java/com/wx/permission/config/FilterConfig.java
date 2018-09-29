package com.wx.permission.config;

import com.wx.permission.filter.AclFilter;
import com.wx.permission.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName FileConfig
 * @Author wx
 * @Description 过滤器配置
 * @Date 2018-09-15-23:53
 */
@Configuration
public class FilterConfig {
    
    
    /**
     * @methodName: loginFilter
     * @author: wx
     * @description: 登录过滤器
     * @param 
     * @date: 2018/9/15
     * @return: org.springframework.boot.web.servlet.FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean loginFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new LoginFilter());
        registration.addUrlPatterns( "/sys/*");
        registration.addUrlPatterns("/admin/*");
        registration.addUrlPatterns("/signin.jsp");
        registration.addUrlPatterns("/views/*");
        registration.setName("loginFilter");
        registration.setOrder(1);
        return registration;
    }
    
    /**
     * @methodName: aclFilter
     * @author: wx
     * @description: 权限过滤器
     * @param
     * @date: 2018/9/16
     * @return: org.springframework.boot.web.servlet.FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean aclFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new AclFilter());
        registration.addUrlPatterns( "/sys/*");
        registration.addUrlPatterns("/admin/*");
        registration.setName("aclFilter");
        registration.setOrder(2);
        return registration;
    }

}
