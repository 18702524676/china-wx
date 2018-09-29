package com.wx.commons.utils;

import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.Set;

import static com.wx.commons.enums.error.CommonEnumError.CompareError.COMPARE_ERROR_0;


/**
 * @ClassName compareUtils
 * @Author wx
 * @Description 比较器工具类
 * @Date 2018-09-09-19:11
 */
public class CompareUtils {
    
    
    
    /**
     * @methodName: equalCollection
     * @author: wx
     * @description: 比较2个集合内是否相等
     * @param collection
     * @param collection2
     * @date: 2018/9/9
     * @return: boolean
     */
    public static boolean equalCollection(Collection collection,Collection collection2){
        BusinessValidatorUtils.notNull(collection,COMPARE_ERROR_0.getMessageError());
        BusinessValidatorUtils.notNull(collection2,COMPARE_ERROR_0.getMessageError());
        // 如果长度相等验证权限数据是否一致
        if (collection.size() == collection2.size()) {
            Set<Integer> originSet = Sets.newHashSet(collection);
            originSet.addAll(collection2);
            if (originSet.size() == collection2.size()) {
                return true;
            }
        }
        return false;

    }
}
