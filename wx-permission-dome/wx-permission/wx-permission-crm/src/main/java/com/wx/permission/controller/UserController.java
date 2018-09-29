package com.wx.permission.controller;


import com.wx.commons.utils.CookieUtil;
import com.wx.commons.utils.MD5Util;
import com.wx.core.exception.BusinessPageException;
import com.wx.core.validator.BeanValidatorUtils;
import com.wx.permission.model.SysUser;
import com.wx.permission.qo.SysUserQO;
import com.wx.permission.qo.UserTokenInfoQO;
import com.wx.permission.service.SysUserService;
import com.wx.permission.service.UserRevealService;
import com.wx.permission.service.UserTokenInfoService;
import com.wx.permission.validator.groups.Login;
import com.wx.permission.vo.UserTokenInfoVO;
import com.wx.redis.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import static com.wx.commons.constant.CommonConstant.Cookie.COOKIE_EXPIRE;
import static com.wx.commons.constant.CommonConstant.Cookie.COOKIE_KEY;
import static com.wx.core.constant.SystemConstant.ViewSys.REDIRECT_INDEX_VIEW;
import static com.wx.core.constant.SystemConstant.ViewSys.REDIRECT_LOGIN_VIEW;
import static com.wx.core.enums.status.SystemEnumStatus.SYS_STATUS.SYS_STATUS_1;
import static com.wx.permission.enums.PermissionEnumError.SysUserEnumError.*;
import static com.wx.redis.constant.RedisConstant.REDIS_EXPIRE;
import static com.wx.redis.constant.RedisConstant.REDIS_USER_PREFIX;
import static org.springframework.http.HttpStatus.SEE_OTHER;

/**
 * @ClassName UserController
 * @Author wx
 * @Description 用户相关操作
 * @Date 2018-08-28-21:40
 */
@Controller
public class UserController implements UserRevealService {
	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private UserTokenInfoService userTokenInfoService;

	/**
	 * @param sysUserQO
	 * @param response
	 * @methodName: login
	 * @author: wx
	 * @description: 登录操作
	 * @date: 2018/8/28
	 * @return: org.springframework.web.servlet.ModelAndView
	 */
	@PostMapping("/login.page")
	public ModelAndView login(SysUserQO sysUserQO, HttpServletResponse response) throws IOException, ServletException {
		// QO验证
		BeanValidatorUtils.validateObjectParam(sysUserQO, Login.class);

		/** Start 查询对象验证是否存在 */
		SysUser sysUser = sysUserService.findByKeyword(sysUserQO.getUsername());
		if (sysUser == null) {
			throw new BusinessPageException(SEE_OTHER.value(), SYS_USER_ENUM_ERROR_0.getMessageError(),
				REDIRECT_LOGIN_VIEW);
		}
		/** End */

		// 初始化ModelAndView
		ModelAndView modelAndView = new ModelAndView(REDIRECT_INDEX_VIEW);
		/** Start 验证密码是否匹配、状态是否异常 */
		if (!Objects.equals(sysUser.getPassword(), MD5Util.encrypt(sysUserQO.getPassword()))) {
			throw new BusinessPageException(SEE_OTHER.value(), SYS_USER_ENUM_ERROR_3.getMessageError(),
				REDIRECT_LOGIN_VIEW);
		}
		if (!Objects.equals(sysUser.getStatus(), SYS_STATUS_1.getCode())) {
			throw new BusinessPageException(SEE_OTHER.value(), SYS_USER_ENUM_ERROR_4.getMessageError(),
				REDIRECT_LOGIN_VIEW);
		}
		/** End */

		/** Start 设置token到mysql、Reids 、cookie   */
		String accessToken = UUID.randomUUID().toString();
		UserTokenInfoQO userTokenInfoQO = new UserTokenInfoQO();
		userTokenInfoQO.setUserId(sysUser.getId());
        UserTokenInfoVO userTokenInfo = userTokenInfoService.getUserTokenInfo(userTokenInfoQO);
        if (userTokenInfo == null){
            userTokenInfoService.save(sysUser.getId(),accessToken);
        } else {
        	//刷新token
			accessToken = userTokenInfoService.refreshToken(sysUser.getId());
        }
		RedisUtils.redisClient.set(REDIS_USER_PREFIX + accessToken, sysUser, REDIS_EXPIRE);
        //删除redis原来的token
		RedisUtils.redisClient.del(REDIS_USER_PREFIX + userTokenInfo.getAccessToken());
		CookieUtil.addCookie(response, COOKIE_KEY, accessToken, COOKIE_EXPIRE);
		/** End */
		return modelAndView;
	}

	/**
	 * @param request 请求对象
	 * @param response 返回对象
	 * @methodName: logout
	 * @author: wx
	 * @description: 登出
	 * @date: 2018/8/29
	 * @return: org.springframework.web.servlet.ModelAndView
	 */
	@GetMapping("/logout.page")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		/** Start 清除缓存 */
		Cookie cookie = CookieUtil.get(request, COOKIE_KEY);
		// 如果Cookie key不存在则直接重定向登录页面
		if (cookie == null || StringUtils.isBlank(cookie.getValue())) {
			return new ModelAndView(REDIRECT_LOGIN_VIEW);
		}
		// 删除redis用户数据
		RedisUtils.redisClient.del(cookie.getValue());
		// 删除Cookie key
		CookieUtil.removeCookie(response, COOKIE_KEY);
		/** End */
		return new ModelAndView(REDIRECT_LOGIN_VIEW);
	}
}
