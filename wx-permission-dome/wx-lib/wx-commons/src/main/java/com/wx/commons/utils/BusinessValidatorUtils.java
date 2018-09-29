package com.wx.commons.utils;



import com.wx.core.exception.ParamException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;

/**
 * @ClassName BusinessValidatorUtils
 * @Author wx
 * @Description 业务相关验证工具类
 * @Date 2018-08-19-1:14
 */

public class BusinessValidatorUtils {

    private BusinessValidatorUtils(){}
    
    /**
     * @methodName: notNull
     * @author: wx
     * @description: 验证对象不为null
     * @param object 对象
     * @param errorMessage 错误提示
     * @date: 2018/8/19
     * @return: void
     */
    public static void notNull(Object object,String errorMessage){
        if (object == null){
            throw new ParamException(errorMessage);
        }
    }
    /**
     * @methodName: isNull
     * @author: wx
     * @description: 验证对象为null
     * @param object 对象
     * @param errorMessage 错误提示
     * @date: 2018/8/19
     * @return: void
     */
    public static void isNull(Object object,String errorMessage){
        if (object != null){
            throw new ParamException(errorMessage);
        }
    }
    
    /**
     * @methodName: notBlank
     * @author: wx
     * @description: 验证字符串不为空
     * @param str    字符串
     * @param errorMessage 错误提示
     * @date: 2018/8/19
     * @return: void
     */
    public static void notBlank(String str,String errorMessage){
        if (StringUtils.isBlank(str)){
            throw new ParamException(errorMessage);
        }
    }
    
    /**
     * @methodName: isBlank
     * @author: wx
     * @description: 验证字符串为空
     * @param str    字符串
     * @param errorMessage 错误提示
     * @date: 2018/8/19
     * @return: void
     */
    public static void isBlank(String str,String errorMessage){
        if (StringUtils.isNotBlank(str)){
            throw new ParamException(errorMessage);
        }
    }
    
    /**
     * @methodName: notEmpty
     * @author: wx
     * @description: 验证集合不为空
     * @param collection 集合接口
     * @param errorMessage 错误提示
     * @date: 2018/8/19
     * @return: void
     */
    public static void notEmpty(Collection collection,String errorMessage){
        if (CollectionUtils.isEmpty(collection)) {
            throw new ParamException(errorMessage);
        }
    }

    /**
     * @methodName: notMap
     * @author: wx
     * @description: 验证map不为空
     * @param map    map接口
     * @param errorMessage 错误提示
     * @date: 2018/9/21
     * @return: void
     */
    public static void notMap(Map map, String errorMessage){
        if (CollectionUtils.isEmpty(map)) {
            throw new ParamException(errorMessage);
        }
    }

    /**
     * @methodName: notEmpty
     * @author: wx
     * @description: 验证集合为空
     * @param collection 集合标准类
     * @param errorMessage 错误提示
     * @date: 2018/8/19
     * @return: void
     */
    public static void isEmpty(Collection collection,String errorMessage){
        if (!CollectionUtils.isEmpty(collection)) {
            throw new ParamException(errorMessage);
        }
    }
    
    /**
     * @methodName: isTure
     * @author: wx
     * @description: 如果结果为ture就通过
     * @param result 结果
     * @param errorMessage 错误消息
     * @date: 2018/8/19
     * @return: void
     */
    public static void isTure(boolean result,String errorMessage){
        if (!result){
            throw new ParamException(errorMessage);
        }
    }
    
    /**
     * @methodName: isFalse
     * @author: wx
     * @description: 如果结果为false就通过
     * @param result 结果
     * @param errorMessage 错误消息
     * @date: 2018/8/24
     * @return: void
     */
    public static void isFalse(boolean result,String errorMessage){
        if (result){
            throw new ParamException(errorMessage);
        }
    }

    /**
     * @methodName: notArray
     * @author: wx
     * @description: 验证数组不为空
     * @param arg    数组
     * @param errorMessage 错误提示
     * @date: 2018/8/19
     * @return: void
     */
    public static void notArray(Object[] arg,String errorMessage){
        if (arg == null || arg.length == 0) {
            throw new ParamException(errorMessage);
        }
    }
}
