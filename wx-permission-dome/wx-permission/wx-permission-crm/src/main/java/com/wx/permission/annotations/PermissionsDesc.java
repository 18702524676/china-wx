package com.wx.permission.annotations;

import java.lang.annotation.*;

/**
 * @ClassName Permissions
 * @Author wx
 * @Description 自定义权限注解
 * @Date 2018-09-16-23:03
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionsDesc {
    String value() default "";
}
