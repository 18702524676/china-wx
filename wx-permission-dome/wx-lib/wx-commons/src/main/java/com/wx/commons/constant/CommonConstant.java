package com.wx.commons.constant;

/**
 * @ClassName ViewConstant
 * @Author wx
 * @Description 视图常量
 * @Date 2018-08-13-22:26
 */
public interface CommonConstant {


	/**
	 * @Author wx
	 * @Description 业务操作提示
	 * @Date 2018-8-19
	 */
	interface BusinessOperation {
		String ADD_SUCCESS = "新增成功";

		String UPDATE_SUCCESS = "更新成功";

		String DELETE_SUCCESS = "删除成功";
	}



	/**
	 * @Author wx
	 * @Description 正则表达式
	 * @Date 2018-8-26
	 */
	interface Regexp {
		/**
		 * 正则表达式:验证用户名(不包含中文和特殊字符)如果用户名使用手机号码或邮箱 则结合手机号验证和邮箱验证
		 */
		String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";

		/**
		 * 正则表达式:验证密码(不包含特殊字符)
		 */
		String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,10}$";

		/**
		 * 说明：移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186
		 * 电信：133、153、180、189
		 * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		 * 验证号码 手机号 固话均可
		 * 正则表达式:验证手机号
		 */
		String REGEX_MOBILE = "((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-? \\d{7,8}-(\\d{1,4})$))";

		/**
		 * 正则表达式:验证邮箱
		 */
		String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

		/**
		 * 正则表达式:验证汉字(1-9个汉字) {1,9} 自定义区间
		 */
		String REGEX_CHINESE = "^[\u4e00-\u9fa5]{1,9}$";

		/**
		 * 正则表达式:验证身份证
		 */
		String REGEX_ID_CARD = "(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])";

		/**
		 * 正则表达式:验证URL
		 */
		String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

		/**
		 * 正则表达式:验证IP地址
		 */
		String REGEX_IP_ADDR = "(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})";

		/**
		 * yyyy-mm-dd 日期格式表达式
		 */
		String REGEX_DATE_YYYY_MM_DD = "/^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/";

		/**
		 * yyyy-MM-dd HH:mm:ss 日期格式表达式
		 */
		String REGEX_DATE_YYYY_MM_DD_HH_MM_SS = "/^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\\s+(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$/";
	}

	/**
	 * @Author wx
	 * @Description Cookie
	 * @Date 2018-8-28
	 */
	interface Cookie {
		/**
		 * Cookie Key
		 */
		String COOKIE_KEY = "token";

		/**
		 * Cookie过期时间(秒)-12小时
		 */
		Integer COOKIE_EXPIRE = 43200;
	}


	/**
	 * @Author wx
	 * @Description 日期格式
	 * @Date 2018-9-23
	 */
	public interface DATE_FORMAT {
		/**
		 * 格式0：yyyy
		 */
		final String FORMAT0 = "yyyy";

		/**
		 * 格式1：yyyy-MM-dd
		 */
		final String FORMAT1 = "yyyy-MM-dd";

		/**
		 * 格式2：yyyy-MM-dd HH
		 */
		final String FORMAT2 = "yyyy-MM-dd HH";

		/**
		 * 格式3：yyyy-MM-dd HH:mm
		 */
		final String FORMAT3 = "yyyy-MM-dd HH:mm";

		/**
		 * 格式4：yyyy-MM-dd HH:mm:ss
		 */
		final String FORMAT4 = "yyyy-MM-dd HH:mm:ss";

		/**
		 * 格式5：yyyy/MM/dd
		 */
		final String FORMAT5 = "yyyy/MM/dd";

		/**
		 * 格式6：yyyy/MM/dd HH
		 */
		final String FORMAT6 = "yyyy/MM/dd HH";

		/**
		 * 格式7：yyyy/MM/dd HH:mm
		 */
		final String FORMAT7 = "yyyy/MM/dd HH:mm";

		/**
		 * 格式8：yyyy/MM/dd HH:mm:ss
		 */
		final String FORMAT8 = "yyyy/MM/dd HH:mm:ss";

		/**
		 * 格式9：yyyyMMdd
		 */
		final String FORMAT9 = "yyyyMMdd";

		/**
		 * 格式10：yyyyMMddHH
		 */
		final String FORMAT10 = "yyyyMMddHH";

		/**
		 * 格式11：yyyyMMddHHmm
		 */
		final String FORMAT11 = "yyyyMMddHHmm";

		/**
		 * 格式12：yyyyMMddHHmmss
		 */
		final String FORMAT12 = "yyyyMMddHHmmss";

		/**
		 * 格式13：yyyyMMddHHmmssSSS
		 */
		final String FORMAT13 = "yyyyMMddHHmmssSSS";

		/**
		 * 格式14：yyyyMMdd HH
		 */
		final String FORMAT14 = "yyyyMMdd HH";

		/**
		 * 格式15：yyyyMMdd HH:mm
		 */
		final String FORMAT15 = "yyyyMMdd HH:mm";

		/**
		 * 格式16：yyyyMMdd HH:mm:ss
		 */
		final String FORMAT16 = "yyyyMMdd HH:mm:ss";

		/**
		 * 格式17: yyyy.MM.dd HH:mm:ss
		 */
		final String FORMAT17 = "yyyy.MM.dd HH:mm:ss";

		/**
		 * 格式18 yyyy-MM
		 */
		final String FORMAT18 = "yyyy-MM";

		/**
		 * 格式 HH:mm:ss
		 */
		final String FORMAT19 = "HH:mm:ss";

		/**
		 * 格式 HH:mm
		 */
		final String FORMAT20 = "HH:mm";
	}

}
