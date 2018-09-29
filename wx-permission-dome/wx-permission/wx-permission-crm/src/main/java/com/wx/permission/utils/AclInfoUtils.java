package com.wx.permission.utils;


import com.google.common.collect.Lists;
import com.wx.commons.utils.ConverterUtils;
import com.wx.core.response.JsonData;
import com.wx.core.utils.SpringUtils;
import com.wx.permission.dao.SysAclMapper;
import com.wx.permission.dao.SysRoleAclMapper;
import com.wx.permission.dao.SysRoleUserMapper;
import com.wx.permission.model.SysAcl;
import com.wx.permission.model.SysUser;
import com.wx.permission.vo.SysAclVO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.wx.core.enums.status.SystemEnumStatus.SYS_STATUS.SYS_STATUS_1;
import static com.wx.redis.constant.RedisConstant.REDIS_USER_ACL_GROUP;
import static com.wx.redis.constant.RedisConstant.REDIS_USER_ACL_SUFFIX;


/**
 * @ClassName AclInfoUtils
 * @Author wx
 * @Description 权限信息工具类
 * @Date 2018-09-08-0:13
 */
public class AclInfoUtils {
	private static AclInfo aclinfo = new AclInfoUtils().new AclInfo();

	private AclInfoUtils() {
	}

	/**
	 * @methodName: getUserAcl
	 * @author: wx
	 * @description: 获取用户所拥有的权限
	 * @param
	 * @date: 2018/9/8
	 * @return: java.util.List<com.wx.mypermission.model.SysAcl>
	 */
	public static List<SysAclVO> getUserAcl() {
		return aclinfo.getUserAcl();
	}

	/**
	 * @methodName: getAclListByRoleId
	 * @author: wx
	 * @description: 根据角色id获取拥有的权限
	 * @param roleId 角色id
	 * @date: 2018/9/8
	 * @return: java.util.List<com.wx.mypermission.model.SysAcl>
	 */
	public static List<SysAclVO> getAclListByRoleId(Integer roleId) {
		return aclinfo.getAclListByRoleId(roleId);
	}

	/**
	 * @methodName: getAclListByUserId
	 * @author: wx
	 * @description: 根据用户id获取对应的权限数据
	 * @param userId 用户id
	 * @date: 2018/9/9
	 * @return: java.util.List<com.wx.mypermission.vo.SysAclVO>
	 */
	public static List<SysAclVO> getAclListByUserId(Integer userId) {
		return aclinfo.getAclListByUserId(userId);
	}
	
	/**
	 * @methodName: permissionsValidation
	 * @author: wx
	 * @description: 权限验证
	 * @param url    请求url
	 * @date: 2018/9/13
	 * @return: boolean
	 */
	public static boolean permissionsValidation(String url) {
		if (aclinfo.isSuperAdmin()) {
			return true;
		}
		List<SysAclVO> aclList = aclinfo.getByUrl(url);
		if (CollectionUtils.isEmpty(aclList)) {
			return true;
		}
		List<SysAclVO> userAcllist = (List<SysAclVO>)aclinfo.getUserAclCache(BusinessContextUtils.getSysUser().getId()).getData();
		if (CollectionUtils.isEmpty(userAcllist)){
			return false;
		}
		Set<Integer>  userAclIdSet = userAcllist.stream().map(userAcl -> userAcl.getId()).collect(Collectors.toSet());
		boolean result = true;
		for (SysAclVO sysAclVO : aclList) {
			if (!Objects.equals(sysAclVO.getStatus(),SYS_STATUS_1.getCode())){
				continue;
			}
			result = false;
			if (userAclIdSet.contains(sysAclVO.getId())) {
				return true;
			}
		}
		return result;
	}

	public static SysAclMapper getSysAclMapper() {
		return aclinfo.sysAclMapper;
	}

	/**
	 * @Author wx
	 * @Description 权限信息具体实现内部类
	 * @Date 2018-9-8
	 */
	private class AclInfo {
		private SysAclMapper sysAclMapper = SpringUtils.getBean(SysAclMapper.class);

		private SysRoleUserMapper sysRoleUserMapper = SpringUtils.getBean(SysRoleUserMapper.class);

		private SysRoleAclMapper sysRoleAclMapper = SpringUtils.getBean(SysRoleAclMapper.class);

		/**
		 * @methodName: getUserAcl
		 * @author: wx
		 * @description: 获取用户权限信息
		 * @param
		 * @date: 2018/9/8
		 * @return: java.util.List<com.wx.mypermission.model.SysAcl>
		 */
		public List<SysAclVO> getUserAcl() {
			Integer userId = BusinessContextUtils.getSysUser().getId();
			if (isSuperAdmin()) {
				List<SysAcl> aclList = sysAclMapper.getAll();
				if (CollectionUtils.isEmpty(aclList)) {
					return Lists.newArrayList();
				}
				return ConverterUtils.listDateConvert(SysAclVO.class, aclList);
			}

			List<Integer> userRoleIdList = sysRoleUserMapper.getRoleIdListByUserId(userId);
			if (CollectionUtils.isEmpty(userRoleIdList)) {
				return Lists.newArrayList();
			}
			return getAclListByRoleIds(userRoleIdList);
		}

		/**
		 * @methodName: getAclListByRoleId
		 * @author: wx
		 * @description: 根据角色id获取权限
		 * @param roleId
		 * @date: 2018/9/8
		 * @return: java.util.List<com.wx.mypermission.model.SysAcl>
		 */
		public List<SysAclVO> getAclListByRoleId(Integer roleId) {
			if (roleId == null) {
				return Lists.newArrayList();
			}
			return getAclListByRoleIds(Lists.newArrayList(roleId));
		}

		/**
		 * @methodName: getAclListByUserId
		 * @author: wx
		 * @description: 根据用户id获取对应的权限数据
		 * @param userId 用户id
		 * @date: 2018/9/9
		 * @return: java.util.List<com.wx.mypermission.vo.SysAclVO>
		 */
		public List<SysAclVO> getAclListByUserId(int userId) {
			List<Integer> userRoleIdList = sysRoleUserMapper.getRoleIdListByUserId(userId);
			if (CollectionUtils.isEmpty(userRoleIdList)) {
				return Lists.newArrayList();
			}
			return getAclListByRoleIds(userRoleIdList);
		}

		/**
		 * @methodName: getAclListByRoleIds
		 * @author: wx
		 * @description: 根据角色id集合获取权限
		 * @param userRoleIdList
		 * @date: 2018/9/8
		 * @return: java.util.List<com.wx.mypermission.model.SysAcl>
		 */
		private List<SysAclVO> getAclListByRoleIds(List<Integer> userRoleIdList) {
			List<Integer> aclIdList;
			if (userRoleIdList.size() == 1) {
				aclIdList = sysRoleAclMapper.getAclIdListByRoleId(userRoleIdList.get(0));
			} else {
				aclIdList = sysRoleAclMapper.getAclIdListByRoleIdList(userRoleIdList);
			}
			if (CollectionUtils.isEmpty(aclIdList)) {
				return Lists.newArrayList();
			}
			List<SysAcl> aclList = sysAclMapper.getByIdList(aclIdList);
			if (CollectionUtils.isEmpty(aclList)) {
				return Lists.newArrayList();
			}
			return ConverterUtils.listDateConvert(SysAclVO.class, aclList);
		}
		
		/**
		 * @methodName: getByUrl
		 * @author: wx
		 * @description: 根据url查询权限
		 * @param url
		 * @date: 2018/9/13
		 * @return: java.util.List<com.wx.mypermission.vo.SysAclVO>
		 */
		private List<SysAclVO> getByUrl(String url){
			List<SysAcl> sysAclList = sysAclMapper.getByUrl(url);
			if (CollectionUtils.isEmpty(sysAclList)){
				return Lists.newArrayList();
			}
			return ConverterUtils.listDateConvert(SysAclVO.class,sysAclList);
		}

		/**
		 * @methodName: getUserAclCache
		 * @author: wx
		 * @description: 获取用户权限缓存
		 * @param userId key前缀
		 * @date: 2018/9/13
		 * @return: com.wx.mypermission.common.response.JsonData
		 */
		@Cacheable(cacheNames = REDIS_USER_ACL_GROUP, key = "#userId" + REDIS_USER_ACL_SUFFIX)
		public JsonData getUserAclCache(int userId) {
			return JsonData.ResultMsgSuccess(getUserAcl());
		}

		/**
		 * @methodName: isSuperAdmin
		 * @author: wx
		 * @description: 验证是否是超级管理员
		 * @param
		 * @date: 2018/9/9
		 * @return: boolean
		 */
		private boolean isSuperAdmin() {
			// 这里是我自己定义了一个假的超级管理员规则，实际中要根据项目进行修改
			// 可以是配置文件获取，可以指定某个用户，也可以指定某个角色
			SysUser sysUser = BusinessContextUtils.getSysUser();
			if (Objects.equals(sysUser.getMail(), "admin@163.com")) {
				return true;
			}
			return false;
		}

	}

}
