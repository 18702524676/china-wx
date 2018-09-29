package com.wx.redis.constant;

/**
 * @Author wx
 * @Description redis常量类
 * @Date 2018-09-25-21:52
 */
public interface RedisConstant {
    /**
     * Redis用户key前缀
     */
    String REDIS_USER_PREFIX = "user_";

    /**
     * Reids用户权限key前缀
     */
    String REDIS_USER_ACL_SUFFIX = "_user_acl";

    /**
     * 用户权限缓存组
     */
    String REDIS_USER_ACL_GROUP = "userAcl";

    /**
     * Redis过期时间(秒)-30分钟
     */
    Integer REDIS_EXPIRE = 1800;
}
