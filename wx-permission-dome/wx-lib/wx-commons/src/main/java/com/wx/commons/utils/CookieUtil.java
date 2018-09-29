package com.wx.commons.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CookieUtil
 * @Author wx
 * @Description Cookie工具类
 * @Date 2018-08-28-21:13
 */
public class CookieUtil {
    
    /**
     * @methodName: addCookie
     * @author: wx
     * @description: 添加Cookie
     * @param response 返回结果对象
     * @param name  Cookie Key
     * @param value Cookie Value
     * @param timeout 超时时间
     * @date: 2018/8/28
     * @return: java.lang.String
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int timeout){
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(timeout);
        response.addCookie(cookie);
    }

    /**
     * @methodName: get
     * @author: wx
     * @description: 获取cookie
     * @param request 请求对象
     * @param name Cookie Key
     * @date: 2018/8/28
     * @return: javax.servlet.http.Cookie
     */
    public static Cookie get(HttpServletRequest request,
                             String name) {
        Map<String, Cookie> cookieMap = readCookieMap(request);
        if (cookieMap.containsKey(name)) {
            return cookieMap.get(name);
        }else {
            return null;
        }
    }
    
    /**
     * @methodName: removeCookie
     * @author: wx
     * @description: 删除Cookie
     * @param response 返回结果对象
     * @param name  Cookie Key
     * @date: 2018/8/29
     * @return: void
     */
    public static void removeCookie(HttpServletResponse response, String name){
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    /**
     * @methodName: readCookieMap
     * @author: wx
     * @description: 将cookie封装成Map
     * @param request 请求对象
     * @date: 2018/8/28
     * @return: java.util.Map<java.lang.String,javax.servlet.http.Cookie>
     */
    private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie: cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
