package com.wx.core.constant;

/**
 * @Author wx
 * @Description 系统常量
 * @Date 2018-9-25
 */
public interface SystemConstant {

    /**
     * @Author wx
     * @Description 系统视图
     * @Date 2018-8-13
     */
    interface ViewSys {
        /**
         * 前缀
         */
        String PREFIX = "/views/";

        /**
         * 后缀
         */
        String SUFFIX = ".jsp";

        /**
         * 重定向登录视图
         */
        String REDIRECT_LOGIN_VIEW = "redirect:/signin.jsp";

        /**
         * 登录视图
         */
        String LOGIN_VIEW = "/signin.jsp";

        /**
         * 重定向主页视图
         */
        String REDIRECT_INDEX_VIEW = "redirect:/admin/index.page";

        /**
         * 主页视图
         */
        String INDEX_VIEW = "/admin/index.page";

        /**
         * 系统错误视图
         */
        String EXCEPTION_VIME = "exception";

        /**
         * 无权限视图
         */
        String NOAUTH_VIME = "/views/noAuth.jsp";
    }

    /**
     * @Author wx
     * @Description jsonData字段名
     * @Date 2018-8-25
     */
    interface JsonDataField {
        String STATUS = "status";

        String MESSAGE = "message";
    }

    /**
     * @Author wx
     * @Description 系统默认配置名称
     * @Date 2018-8-31
     */
    interface SysDefaultConfigName {
        String DEFAULT_SYS_USER_NAME = "sysAdmin";

        String DEFAULT_IP = "127.0.0.1";
    }


}
