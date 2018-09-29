package com.wx.permission.utils;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import com.wx.core.utils.SpringUtils;
import com.wx.permission.config.ExclusionUrlConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Set;

/**
 * @ClassName ExclusionUrlUtils
 * @Author wx
 * @Description 白名单工具类
 * @Date 2018-09-13-23:10
 */
public class ExclusionUrlUtils {

    private static ExclusionUrl exclusionUrl = new ExclusionUrlUtils().new ExclusionUrl();

    private ExclusionUrlUtils(){}
    
    /**
     * @methodName: getExclusionUrlSet
     * @author: wx
     * @description: 获取白名单
     * @param
     * @date: 2018/9/16
     * @return: java.util.Set<java.lang.String>
     */
    public static Set<String> getExclusionUrlSet(){
        return exclusionUrl.getExclusionUrlSet();
    }


    /**
     * @Author wx
     * @Description 白名单具体实现内部类
     * @Date 2018-9-13
     */
    private class ExclusionUrl {
        /**
         * 装载白名单容器
         */
        private Set<String> exclusionUrlSet = Sets.newConcurrentHashSet();
        /**
         * 白名单配置
         */
       private ExclusionUrlConfig exclusionUrlConfig = SpringUtils.getBean(ExclusionUrlConfig.class);

        public Set<String> getExclusionUrlSet(){
            if (StringUtils.isNotBlank(exclusionUrlConfig.getUrls())){
                List<String> exclusionUrlList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList((exclusionUrlConfig.getUrls()));
                exclusionUrlSet.addAll(exclusionUrlList);
            }
            return exclusionUrlSet;
        }
    }
}
