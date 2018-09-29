package com.wx.core.enums;
/**
 * @name wx
 * @msg 异常枚举接口
 * @date 2018-7-1
 */
public interface EnumError {
    /**
     * 获取状态标识方法
     * @return
     */
    Integer getCodeError();

    /**
     * 获取内容
     * @return
     */
    String getMessageError();
}
