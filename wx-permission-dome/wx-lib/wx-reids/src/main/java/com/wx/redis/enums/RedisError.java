package com.wx.redis.enums;

import com.wx.core.enums.EnumError;

/**
 * @Author wx
 * @Description redis异常枚举
 * @Date 2018-09-25-21:54
 */
public enum RedisError implements EnumError {
    /**  redis操作异常 */
    REDIS_ERROR(0,"redis操作异常"),
    /**  redis添加数据发送异常 */
    REDIS_ADD(1,"redis添加数据发送异常"),
    /**  redis获取数据发送异常 */
    REDIS_GET(2,"redis获取数据发送异常"),
    /**  redis缓存实例需要一个ID */
    REDIS_ERROR_3(3,"redis缓存实例需要一个ID"),
    ;
    /**  错误状态码 */
    private Integer codeError;
    /** 错误提示信息 */
    private  String messageError;
    private RedisError(int codeError,String messageError){
        this.codeError = codeError;
        this.messageError = messageError;
    }
    @Override
    public Integer getCodeError() {
        return codeError;
    }

    @Override
    public String getMessageError() {
        return messageError;
    }
}
