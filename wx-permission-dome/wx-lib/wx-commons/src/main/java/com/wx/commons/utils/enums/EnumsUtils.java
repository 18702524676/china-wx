package com.wx.commons.utils.enums;


import com.wx.core.enums.EnumStatus;

/**
 * @Author wx
 * @Description 枚举工具类
 * @Date 2018-8-13
 */
public class EnumsUtils {

    /**
     * @methodName: getEnum
     * @author: wx
     * @description: 获取枚举
     * @param enumClass 枚举class
     * @param code 枚举code
     * @date: 2018/8/13
     * @return: T
     */
    public static <T extends EnumStatus> T getEnum(Class<T> enumClass, Integer code) {
		if (enumClass == null) {
			return null;
		}
		for (T each : enumClass.getEnumConstants()) {
			if (each.getCode().equals(code)) {
				return each;
			}
		}
		return null;
	}

	/**
     * @methodName: getEnumMeesge
     * @author: wx
     * @description: 获取枚举翻译内容
     * @param enumClass 枚举class
     * @param code 枚举code
     * @date: 2018/8/13
     * @return: java.lang.String
     */
    public static <T extends EnumStatus> String getEnumMeesge(Class<T> enumClass, Integer code) {
		EnumStatus enumPrompt = getEnum(enumClass, code);
		if (enumPrompt != null) {
			return enumPrompt.getMessage();
		}
		return null;

	}
}
