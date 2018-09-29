package com.wx.permission.filter;

import com.google.common.collect.Sets;
import com.wx.commons.utils.CookieUtil;
import com.wx.core.utils.SpringUtils;
import com.wx.permission.model.SysUser;
import com.wx.permission.qo.UserTokenInfoQO;
import com.wx.permission.service.SysUserService;
import com.wx.permission.service.UserTokenInfoService;
import com.wx.permission.utils.BusinessContextUtils;
import com.wx.permission.utils.ExclusionUrlUtils;
import com.wx.permission.vo.UserTokenInfoVO;
import com.wx.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;

import static com.wx.commons.constant.CommonConstant.Cookie.COOKIE_KEY;
import static com.wx.core.constant.SystemConstant.ViewSys.INDEX_VIEW;
import static com.wx.core.constant.SystemConstant.ViewSys.LOGIN_VIEW;
import static com.wx.redis.constant.RedisConstant.REDIS_EXPIRE;
import static com.wx.redis.constant.RedisConstant.REDIS_USER_PREFIX;


/**
 * @ClassName LoginFilter
 * @Author wx
 * @Description 登录过滤器
 * @Date 2018-08-29-21:36
 */
@Slf4j
public class LoginFilter implements Filter {
	/** 装载白名单容器 */
	private static Set<String> exclusionUrlSet = Sets.newConcurrentHashSet();

	/** 用户token信息Servcie */
	private UserTokenInfoService userTokenInfoService = SpringUtils.getBean(UserTokenInfoService.class);

	/** 用户信息Servcie */
	private SysUserService sysUserService = SpringUtils.getBean(SysUserService.class);

	/**
	 * @methodName: init
	 * @author: wx
	 * @description: 容器在创建当前过滤器的时候自动调用
	 * @param filterConfig
	 * @date: 2018/8/29
	 * @return: void
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/**
	 * @methodName: doFilter
	 * @author: wx
	 * @description: 每次客户端请求都会调用一次。
	 * @param servletRequest
	 * @param servletResponse
	 * @param filterChain
	 * @date: 2018/8/29
	 * @return: void
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
		throws IOException, ServletException {
		initializeData();
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		/** Start 获取用户缓存信息 */
		Cookie cookie = CookieUtil.get((HttpServletRequest) servletRequest, COOKIE_KEY);
		if (cookie == null || StringUtils.isBlank(cookie.getValue())) {
			exclusionVerification(request, response, filterChain);
			return;
		}
		SysUser sysUser = (SysUser) RedisUtils.redisClient.get(REDIS_USER_PREFIX + cookie.getValue());
		if (sysUser == null) {
			UserTokenInfoQO userTokenInfoQO = new UserTokenInfoQO();
			userTokenInfoQO.setAccessToken(cookie.getValue());
			UserTokenInfoVO userTokenInfoVO = userTokenInfoService.getUserTokenInfo(userTokenInfoQO);
			if (userTokenInfoVO != null) {
				/** Start 重新设置redis缓存 */
				sysUser = sysUserService.getSysUserById(userTokenInfoVO.getUserId());
				RedisUtils.redisClient.set(REDIS_USER_PREFIX + cookie.getValue(), sysUser, REDIS_EXPIRE);
				/** End */
			} else {
				exclusionVerification(request, response, filterChain);
				return;
			}
		}
		request.setAttribute("user", sysUser);
		/** End */

		/** 设置用户信息与request请求对象到业务上下文中 */
		BusinessContextUtils.add(BusinessContextUtils.createBusinessContext().setSysUser(sysUser).setRequest(request));
		/** End */

		/** Start 如果已经登录，再次进入登录界面则转发到首页 */
		if (Objects.equals(request.getServletPath(), LOGIN_VIEW)) {
			request.getRequestDispatcher(INDEX_VIEW).forward(request, response);
		}
		/** End */

		// 如果还有其他的过滤器就调用其它的过滤器
		filterChain.doFilter(request, response);
	}

	/**
	 * @methodName: destroy
	 * @author: wx
	 * @description: 容器在销毁当前过滤器的时候自动调用
	 * @param
	 * @date: 2018/8/29
	 * @return: void
	 */
	@Override
	public void destroy() {
	}

	/**
	 * @methodName: exclusionVerification
	 * @author: wx
	 * @description: 白名单验证
	 * @param request 请求对象
	 * @param response 返回对象
	 * @param filterChain 过滤器链
	 * @date: 2018/9/13
	 * @return: void
	 */
	private void exclusionVerification(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws IOException, ServletException {
		String servletPath = request.getServletPath();
		if (exclusionUrlSet.contains(servletPath)) {
			filterChain.doFilter(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + LOGIN_VIEW);
		}
	}

	/**
	 * @methodName: initializeData
	 * @author: wx
	 * @description: 初始化数据
	 * @param
	 * @date: 2018/9/24
	 * @return: void
	 */
	private void initializeData() {
		if (exclusionUrlSet.size() == 0) {
			exclusionUrlSet = ExclusionUrlUtils.getExclusionUrlSet();
		}
		if (userTokenInfoService == null) {
			userTokenInfoService = SpringUtils.getBean(UserTokenInfoService.class);
		}
		if (sysUserService == null) {
			sysUserService = SpringUtils.getBean(SysUserService.class);
		}
	}

}
