package com.wx.commons.utils;

import java.util.regex.Pattern;

import static com.wx.commons.constant.CommonConstant.Regexp.*;

/**
 * @ClassName RegexpUtils
 * @Author wx
 * @Description 正则验证工具类
 * @Date 2018-08-26-20:41
 */
public class RegexpUtils {


    /**
     * @methodName: isUserName
     * @author: wx
     * @description: 校验用户名
     * @param username 用户名
     * @date: 2018/8/26
     * @return: boolean 校验通过返回true，否则返回false
     */
    public static boolean isUserName(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }


    /**
     * @methodName: isPassword
     * @author: wx
     * @description: 校验密码
     * @param password 密码
     * @date: 2018/8/26
     * @return: boolean 校验通过返回true，否则返回false
     */
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }


    /**
     * @methodName: isMobile
     * @author: wx
     * @description: 校验手机号
     * @param mobile 手机号
     * @date: 2018/8/26
     * @return: boolean 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }


    /**
     * @methodName: isEmail
     * @author: wx
     * @description: 校验邮箱
     * @param email 邮箱
     * @date: 2018/8/26
     * @return: boolean 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }


    /**
     * @methodName: isChinese
     * @author: wx
     * @description: 校验汉字
     * @param chinese 汉字
     * @date: 2018/8/26
     * @return: boolean 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }


    /**
     * @methodName: isIDCard
     * @author: wx
     * @description: 校验身份证
     * @param idCard 身份证号
     * @date: 2018/8/26
     * @return: boolean 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }


    /**
     * @methodName: isUrl
     * @author: wx
     * @description: 校验URL
     * @param url URL
     * @date: 2018/8/26
     * @return: boolean 校验通过返回true，否则返回false
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }


    /**
     * @methodName: isIPAddress
     * @author: wx
     * @description: 校验IP地址
     * @param ipAddress IP地址
     * @date: 2018/8/26
     * @return: boolean 校验通过返回true，否则返回false
     */
    public static boolean isIPAddress(String ipAddress) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddress);
    }
}
