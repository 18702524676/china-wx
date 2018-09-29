package com.wx.permission.controller;


import com.wx.commons.utils.ConverterUtils;
import com.wx.core.response.JsonData;
import com.wx.permission.annotations.PermissionsLog;
import com.wx.permission.dto.SysRoleDto;
import com.wx.permission.service.*;
import com.wx.permission.vo.SysRoleVO;
import com.wx.permission.vo.SysUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import static com.wx.permission.enums.PermissionEnumStatus.SysLogType.*;
import static com.wx.redis.constant.RedisConstant.REDIS_USER_ACL_GROUP;


/**
 * @ClassName SysRoleController
 * @Author wx
 * @Description 角色Controller
 * @Date 2018-09-05-22:10
 */
@Controller
public class SysRoleController implements SysRoleRevealService {
	@Autowired
	private SysRoleService sysRoleService;

	@Autowired
	private SysTreeService sysTreeService;

	@Autowired
	private SysRoleAclService sysRoleAclService;

	@Autowired
	private SysRoleUserService sysRoleUserService;

	/**
	 * @methodName: page
	 * @author: wx
	 * @description: 角色页面
	 * @param
	 * @date: 2018/9/5
	 * @return: org.springframework.web.servlet.ModelAndView
	 */
	@GetMapping("role.page")
	public ModelAndView page() {
		return new ModelAndView("role");
	}

	/**
	 * @methodName: saveSysRole
	 * @author: wx
	 * @description: 新增角色
	 * @param sysRoleDto
	 * @date: 2018/9/5
	 * @return: com.wx.mypermission.common.response.JsonData
	 */
	@Override
	@ResponseBody
	@PermissionsLog(SYS_LOG_TYPE_5)
	public JsonData saveSysRole(SysRoleDto sysRoleDto) {
		return sysRoleService.saveSysRole(sysRoleDto);
	}

	/**
	 * @methodName: updateSysRole
	 * @author: wx
	 * @description: 更新角色
	 * @param sysRoleDto
	 * @date: 2018/9/5
	 * @return: com.wx.mypermission.common.response.JsonData
	 */
	@Override
	@ResponseBody
	@PermissionsLog(SYS_LOG_TYPE_5)
	public JsonData updateSysRole(SysRoleDto sysRoleDto) {
		return sysRoleService.updateSysRole(sysRoleDto);
	}

	/**
	 * @methodName: getSysRoleAll
	 * @author: wx
	 * @description: 获取所有角色信息
	 * @param
	 * @date: 2018/9/5
	 * @return: com.wx.mypermission.common.response.JsonData
	 */
	@Override
	@ResponseBody
	public JsonData getSysRoleAll() {
		List<SysRoleVO> sysRoleList = sysRoleService.getAll();
		return JsonData.ResultMsgSuccess(sysRoleList);
	}

	/**
	 * @methodName: roleTree
	 * @author: wx
	 * @description: 根据角色获取权限模块 +权限
	 * @param roleId
	 * @date: 2018/9/8
	 * @return: com.wx.mypermission.common.response.JsonData
	 */
	@Override
	@ResponseBody
	public JsonData roleTree(@RequestParam("roleId") int roleId) {
		return JsonData.ResultMsgSuccess(sysTreeService.roleTree(roleId));
	}

	/**
	 * @methodName: changeAcls
	 * @author: wx
	 * @description: 修改权限数据 删除缓存allEntries默认值false 此时只删除key对应的值，true清空缓存cacheNames里的所有值
	 * @param roleId 角色id
	 * @param aclIds 权限集合id
	 * @date: 2018/9/9
	 * @return: com.wx.mypermission.common.response.JsonData
	 */
	@Override
	@ResponseBody
	@PermissionsLog(SYS_LOG_TYPE_6)
	@CacheEvict(cacheNames = REDIS_USER_ACL_GROUP, allEntries = true)
	public JsonData changeAcls(@RequestParam int roleId, @RequestParam String aclIds) {
		List<Integer> aclIdList = ConverterUtils.splitToList(Integer.class, aclIds);
		return sysRoleAclService.changeRoleAcls(roleId, aclIdList);
	}

	/**
	 * @methodName: users
	 * @author: wx
	 * @description: 根据角色id查询用户列表（包含未绑定的与当前角色绑定的）
	 * @param roleId 角色id
	 * @date: 2018/9/9
	 * @return: com.wx.mypermission.common.response.JsonData
	 */
	@Override
	@ResponseBody
	@CacheEvict(cacheNames = REDIS_USER_ACL_GROUP, allEntries = true)
	public JsonData users(@RequestParam("roleId") int roleId) {
		Map<String, List<SysUserVO>> resultMap = sysRoleUserService.getAllUserListByRoleId(roleId);
		return JsonData.ResultMsgSuccess(resultMap);
	}

	/**
	 * @methodName: changeUsers
	 * @author: wx
	 * @description: 修改用户数据
	 * @param roleId 角色id
	 * @param userIds 用户集合Id
	 * @date: 2018/9/9
	 * @return: com.wx.mypermission.common.response.JsonData
	 */
	@Override
	@ResponseBody
	@PermissionsLog(SYS_LOG_TYPE_7)
	public JsonData changeUsers(@RequestParam("roleId") int roleId,
		@RequestParam(value = "userIds", required = false, defaultValue = "") String userIds) {
		List<Integer> userIdList = ConverterUtils.splitToList(Integer.class, userIds);
		return sysRoleUserService.changeRoleUsers(roleId, userIdList);
	}
}
