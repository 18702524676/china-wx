package com.wx.permission.filter;

import com.google.common.collect.Sets;
import com.wx.commons.utils.JsonUtils;
import com.wx.core.response.JsonData;
import com.wx.permission.model.SysUser;
import com.wx.permission.utils.AclInfoUtils;
import com.wx.permission.utils.BusinessContextUtils;
import com.wx.permission.utils.ExclusionUrlUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

import static com.wx.core.constant.SystemConstant.ViewSys.NOAUTH_VIME;
import static com.wx.permission.enums.PermissionEnumError.SysAclEnumError.SYS_ACL_ENUM_ERROR_3;


/**
 * @ClassName AclFilter
 * @Author wx
 * @Description 权限验证过滤器
 * @Date 2018-09-11-23:37
 */
@Slf4j
public class AclFilter implements Filter {
	/** 装载白名单容器 */
	private static Set<String> exclusionUrlSet = Sets.newConcurrentHashSet();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
		throws IOException, ServletException {
		if (exclusionUrlSet.size() == 0) {
			exclusionUrlSet = ExclusionUrlUtils.getExclusionUrlSet();
		}
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String servletPath = request.getServletPath();
		if (exclusionUrlSet.contains(servletPath)) {
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}

		SysUser sysUser = BusinessContextUtils.getSysUser();
		if (sysUser == null) {
			noAuth(request, response);
			return;
		}

		if (!AclInfoUtils.permissionsValidation(servletPath)) {
			noAuth(request, response);
			return;
		}

		filterChain.doFilter(servletRequest, servletResponse);
		return;

	}

	@Override
	public void destroy() {

	}

	/**
     * @methodName: noAuth
     * @author: wx
     * @description: 无权限转发页面
     * @param request 请求对象
     * @param response 返回对象
     * @date: 2018/9/15
     * @return: void
     */
    private void noAuth(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String servletPath = request.getServletPath();
        if (servletPath.endsWith(".json")){
            response.setHeader("Content-Type", "application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(JsonUtils.toJson(JsonData.ResultMsgError(SYS_ACL_ENUM_ERROR_3.getMessageError())));
        }else {
            response.sendRedirect(request.getContextPath() + NOAUTH_VIME);
        }
    }

}
