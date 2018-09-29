package com.wx.core.enums;
/**
 * @name wx
 * @msg 状态枚举接口
 * @date 2018-7-1
 */
public interface EnumStatus {
    /**
     * 获取状态标识方法
     * @return
     */
    Integer getCode();

    /**
     * 获取内容
     * @return
     */
    String getMessage();
}
