package com.wx.redis;


import com.wx.core.utils.SpringUtils;

/**
 * @ClassName RedisUtils
 * @Author wx
 * @Description redis工具类
 * @Date 2018-08-28-22:18
 */
public class RedisUtils {
    /**
     * redis客户端对象
     */
    public static  RedisClient redisClient = SpringUtils.getBean(RedisClient.class);
}
