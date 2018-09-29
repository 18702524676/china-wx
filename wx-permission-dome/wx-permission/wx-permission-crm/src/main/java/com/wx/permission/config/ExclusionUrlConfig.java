package com.wx.permission.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName ExclusionUrlConfig
 * @Author wx
 * @Description 过滤器白名单配置
 * @Date 2018-09-11-23:52
 */
@Data
@Component
@ConfigurationProperties(prefix = "exclusion")
public class ExclusionUrlConfig {
    /**
     * 白名单url地址
     */
    private String  urls;

}
