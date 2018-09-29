package com.wx.commons.utils;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.wx.core.exception.BusinessJsonException;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * wx
 * 转换器工具类
 * 2018-7-1
 */
public class ConverterUtils {

	/**
	 * wx
	 * 集合对象转换(把list的数据复制到你指定container类型里面去并生成新的集合)
	 * 
	 * @param targetObjectType 集合的类型
	 * @param source 数据源集合
	 * @param <T> 泛型
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static <T> List<T> listDateConvert(Class targetObjectType, List<?> source) {
		return source.stream().map(e -> {
			T newContainer;
			try {
				newContainer = (T) targetObjectType.newInstance();
			} catch (Exception e1) {
				e1.printStackTrace();
				throw new BusinessJsonException(e1.getMessage());
			}
			BeanUtils.copyProperties(e, newContainer);
			return newContainer;
		}).collect(Collectors.toList());
	}

	/**
     * @methodName: splitToList
     * @author: wx
     * @description: 分隔符字符串转换为集合（仅限制String跟Integer）
     * @param listType 指定类型
     * @param agrs    分隔符字符串
     * @date: 2018/9/8
     * @return: java.util.List
     */
    public static List splitToList(Class listType, String agrs) {
        List<String> strList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(agrs);
        if (listType.isAssignableFrom(String.class)){
            return strList;
        }else if(listType.isAssignableFrom(Integer.class)){
            return strList.stream().map(strItem -> Integer.parseInt(strItem)).collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }
}
