package com.wx.permission.utils;

import com.wx.permission.model.SysUser;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName BusinessContextUtils
 * @Author wx
 * @Description 上下文工具类
 * @Date 2018-08-31-23:17
 */
@Data
public class BusinessContextUtils {

	private static final ThreadLocal<BusinessContext> businessContextThreadLocal = new ThreadLocal<BusinessContext>();

	private BusinessContextUtils() {
	}

	/**
	 * @methodName: createBusinessContext
	 * @author: wx
	 * @description: 创建上下文对象
	 * @param
	 * @date: 2018/8/31
	 * @return: com.wx.mypermission.common.utils.BusinessContextUtils.BusinessContext
	 */
	public static BusinessContext createBusinessContext() {
		BusinessContextUtils businessContextUtils = new BusinessContextUtils();
		return businessContextUtils.new BusinessContext();
	}

	/**
	 * @methodName: add
	 * @author: wx
	 * @description: 设置业务上下文
	 * @param businessContext
	 * @date: 2018/8/31
	 * @return: void
	 */
	public static void add(BusinessContext businessContext) {
		businessContextThreadLocal.set(businessContext);
	}

	/**
	 * @methodName: getBusinessContext
	 * @author: wx
	 * @description: 获取业务上下文
	 * @param
	 * @date: 2018/8/31
	 * @return: com.wx.mypermission.common.utils.BusinessContextUtils.BusinessContext
	 */
	public static BusinessContext getBusinessContext() {
		return businessContextThreadLocal.get();
	}

	/**
	 * @methodName: getSysUser
	 * @author: wx
	 * @description: 获取用户信息
	 * @param
	 * @date: 2018/8/31
	 * @return: com.wx.mypermission.model.SysUser
	 */
	public static SysUser getSysUser() {
		return getBusinessContext() == null ? null : getBusinessContext().getSysUser();
	}

	/**
	 * @methodName: getRequest
	 * @author: wx
	 * @description: 获取请求对象
	 * @param
	 * @date: 2018/9/1
	 * @return: javax.servlet.http.HttpServletRequest
	 */
	public static HttpServletRequest getRequest() {
		return getBusinessContext() == null ? null : getBusinessContext().getRequest();
	}

	/**
	 * @methodName: celan
	 * @author: wx
	 * @description: 清除
	 * @param
	 * @date: 2018/9/1
	 * @return: void
	 */
	public static void celan() {
		businessContextThreadLocal.remove();
	}

	/**
	 * @Author wx
	 * @Description 业务上下文对象（不做暴露）
	 * @Date 2018-8-31
	 */
	public class BusinessContext {

		private HttpServletRequest request;

		private SysUser sysUser;

		public HttpServletRequest getRequest() {
			return request;
		}

		public BusinessContext setRequest(HttpServletRequest request) {
			this.request = request;
			return this;
		}

		public SysUser getSysUser() {
			return sysUser;
		}

		public BusinessContext setSysUser(SysUser sysUser) {
			this.sysUser = sysUser;
			return this;
		}
	}
}
