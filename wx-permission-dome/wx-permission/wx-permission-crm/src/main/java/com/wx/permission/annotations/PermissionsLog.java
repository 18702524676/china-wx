package com.wx.permission.annotations;


import com.wx.permission.enums.PermissionEnumStatus;

import java.lang.annotation.*;

/**
 * @ClassName PermissionsLog
 * @Author wx
 * @Description 自定义权限日志注解
 * @Date 2018-09-22-0:15
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionsLog {
    PermissionEnumStatus.SysLogType value();
}
