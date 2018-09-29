package com.wx.permission.enums;


import com.wx.core.enums.EnumStatus;
import com.wx.permission.dao.*;
import com.wx.permission.dto.SysRoleAclDto;
import com.wx.permission.dto.SysRoleUserDto;
import com.wx.permission.model.*;
import com.wx.permission.service.SysRoleAclService;
import com.wx.permission.service.SysRoleUserService;

import static com.wx.permission.constant.PermissionConstant.methodName.UPDATE_METHOD_NAME;

/**
 * @ClassName EnumStatus
 * @Author wx
 * @Description 权限系统状态枚举集合
 * @Date 2018-08-19-18:43
 */
public class PermissionEnumStatus {

	/**
	 * @Author wx
	 * @Description 日志类型枚举
	 * @Date 2018-9-21
	 */
	public enum SysLogType implements EnumStatus {
		/** 部门 */
		SYS_LOG_TYPE_1(1, "部门", SysDeptMapper.class, SysDept.class,UPDATE_METHOD_NAME),
		/** 用户 */
		SYS_LOG_TYPE_2(2, "用户", SysUserMapper.class, SysUser.class,UPDATE_METHOD_NAME),
		/** 权限模块 */
		SYS_LOG_TYPE_3(3, "权限模块", SysAclModuleMapper.class, SysAclModule.class,UPDATE_METHOD_NAME),
		/** 权限 */
		SYS_LOG_TYPE_4(4, "权限", SysAclMapper.class, SysAcl.class,UPDATE_METHOD_NAME),
		/** 角色 */
		SYS_LOG_TYPE_5(5, "角色", SysRoleMapper.class, SysRole.class,UPDATE_METHOD_NAME),
		/** 角色权限 */
		SYS_LOG_TYPE_6(6, "角色权限", SysRoleAclService.class, SysRoleAclDto.class,"batchSysRoleAclInsert"),
		/** 角色用户 */
		SYS_LOG_TYPE_7(7, "角色用户", SysRoleUserService.class, SysRoleUserDto.class,"batchSysRoleUserInsert"),
		;

		/** 状态标识 */
		private Integer code;

		/** 描述 */
		private String message;

		/** IOC容器内的Class */
		private Class iocClass;

		/** 更新对象class */
		private Class updateClass;
		/**  日志还原操作方法名 */
		private String recoverMethodName;

		private SysLogType(Integer code, String message, Class iocClass, Class updateClass,String recoverMethodName) {
			this.code = code;
			this.message = message;
			this.iocClass = iocClass;
			this.updateClass = updateClass;
			this.recoverMethodName = recoverMethodName;
		}

		@Override
		public Integer getCode() {
			return code;
		}

		@Override
		public String getMessage() {
			return message;
		}

		public Class getIocClass() {
			return iocClass;
		}

		public Class getUpdateClass() {
			return updateClass;
		}

		public String getRecoverMethodName() {
			return recoverMethodName;
		}
	}

}
