package com.wx.permission.constant;

/**
 * @ClassName ViewConstant
 * @Author wx
 * @Description 视图常量
 * @Date 2018-08-13-22:26
 */
public interface PermissionConstant {


	/**
	 * @Author wx
	 * @Description 层级规则
	 * @Date 2018-8-19
	 */
	interface LevelRule {
		/**
		 * 最高的层级
		 */
		String ROOT = "0";

		/**
		 * 层级之间用.隔开
		 */
		String SEGMENTATION = ".";
	}



	/**
	 * @Author wx
	 * @Description 公共方法名字
	 * @Date 2018-9-19
	 */
	interface methodName {

		/** 更新公共方法名 */
		String UPDATE_METHOD_NAME = "updateByPrimaryKeySelective";

	}

	/**
	 * @Author wx
	 * @Description 公共属性名
	 * @Date 2018-9-24
	 */
	interface fieldName {
		/**  操作人 */
		String OPERATOR = "operator";
		/**  操作ip */
		String OPERATEIP = "operateIp";
		/**  操作时间 */
		String OPERATETIME = "operateTime";
	}


}
