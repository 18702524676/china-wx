package com.wx.permission.enums;


import com.wx.core.enums.EnumError;

/**
 * @ClassName PermissionEnumError
 * @Author wx
 * @Description 权限系统异常枚举集合（关于为什么异常信息不使用常量，
 *              因为以后会做微服务，需要知道这个异常是从那个服务里面报的，所以需要用编号来区分）
 * @Date 2018-08-19-18:40
 */
public class PermissionEnumError {

	/**
	 * @Author wx
	 * @Description 部门异常枚举
	 * @Date 2018-8-19
	 */
	public enum SysDeptEnumError implements EnumError {
		/** 部门不存在 */
		SYS_DEPT_ENUM_ERROR_0(0, "部门不存在"),
		/** 部门不存在 */
		SYS_DEPT_ENUM_ERROR_1(1, "部门名称存在"),
		/** 上级id不能与当前部门id相同 */
		SYS_DEPT_ENUM_ERROR_2(2, "上级id不能与当前部门id相同"),
		/** 当前部门下有子部门,无法删除 */
		SYS_DEPT_ENUM_ERROR_3(3, "当前部门下有子部门,无法删除"),
		/** 当前部门下有用户,无法删除 */
		SYS_DEPT_ENUM_ERROR_4(4, "当前部门下有用户,无法删除"),
		/** 父级部门不能更新到它的子级部门下 */
		SYS_DEPT_ENUM_ERROR_5(5, "父级部门不能更新到它的子级部门下"),;
		private Integer codeError;

		private String messageError;

		private SysDeptEnumError(int codeError, String messageError) {
			this.codeError = codeError;
			this.messageError = messageError;
		}

		@Override
		public Integer getCodeError() {
			return codeError;
		}

		@Override
		public String getMessageError() {
			return messageError;
		}
	}

	/**
	 * @Author wx
	 * @Description 系统用户异常枚举
	 * @Date 2018-8-26
	 */
	public enum SysUserEnumError implements EnumError {
		/** 用户不存在 */
		SYS_USER_ENUM_ERROR_0(0, "用户不存在"),
		/** 邮箱已经被使用 */
		SYS_USER_ENUM_ERROR_1(1, "邮箱已经被使用"),
		/** 上级id不能与当前部门id相同 */
		SYS_USER_ENUM_ERROR_2(2, "手机号已经被使用"),
		/** 密码不匹配 */
		SYS_USER_ENUM_ERROR_3(3, "密码不匹配"),
		/** 用户状态异常，请联系管理员 */
		SYS_USER_ENUM_ERROR_4(4, "用户状态异常，请联系管理员"),
		/** 请选择用户 */
		SYS_USER_ENUM_ERROR_5(5, "请选择用户"),;
		private Integer codeError;

		private String messageError;

		private SysUserEnumError(int codeError, String messageError) {
			this.codeError = codeError;
			this.messageError = messageError;
		}

		@Override
		public Integer getCodeError() {
			return codeError;
		}

		@Override
		public String getMessageError() {
			return messageError;
		}
	}

	/**
	 * @Author wx
	 * @Description 权限模块异常枚举
	 * @Date 2018-9-2
	 */
	public enum SysAclModuleEnumError implements EnumError {
		/** 权限模块不存在 */
		SYS_ACL_MODULE_ENUM_ERROR_0(0, "权限模块不存在"),
		/** 权限模块名字存在 */
		SYS_ACL_MODULE_ENUM_ERROR_1(1, "权限模块名字存在"),
		/** 上级id不能与当前部门id相同 */
		SYS_ACL_MODULE_ENUM_ERROR_2(2, "上级id不能与当前权限模块id相同"),
		/** 权限模块下有子权限模块,不能进行删除 */
		SYS_ACL_MODULE_ENUM_ERROR_3(3, "权限模块下有子权限模块,不能进行删除"),
		/** 权限模块下有权限,不能进行删除 */
		SYS_ACL_MODULE_ENUM_ERROR_4(4, "权限模块下有权限,不能进行删除"),
		/** 父级权限模块不能更新到它的子级模块下 */
		SYS_ACL_MODULE_ENUM_ERROR_5(5, "父级权限模块不能更新到它的子级模块下"),;
		private Integer codeError;

		private String messageError;

		private SysAclModuleEnumError(int codeError, String messageError) {
			this.codeError = codeError;
			this.messageError = messageError;
		}

		@Override
		public Integer getCodeError() {
			return codeError;
		}

		@Override
		public String getMessageError() {
			return messageError;
		}
	}

	/**
	 * @Author wx
	 * @Description 权限异常枚举
	 * @Date 2018-9-4
	 */
	public enum SysAclEnumError implements EnumError {
		/** 权限模块不存在 */
		SYS_ACL_ENUM_ERROR_0(0, "权限不存在"),
		/** 权限模块名字存在 */
		SYS_ACL_ENUM_ERROR_1(1, "权限名字存在"),
		/** 请选择分配权限 */
		SYS_ACL_ENUM_ERROR_2(2, "请选择分配权限"),
		/** 没有访问权限，如需要访问，请联系管理员 */
		SYS_ACL_ENUM_ERROR_3(3, "没有操作权限，请联系管理员"),;
		private Integer codeError;

		private String messageError;

		private SysAclEnumError(int codeError, String messageError) {
			this.codeError = codeError;
			this.messageError = messageError;
		}

		@Override
		public Integer getCodeError() {
			return codeError;
		}

		@Override
		public String getMessageError() {
			return messageError;
		}
	}

	/**
	 * @Author wx
	 * @Description 角色异常枚举
	 * @Date 2018-9-4
	 */
	public enum SysRoleEnumError implements EnumError {
		/** 角色不存在 */
		SYS_ROLE_ENUM_ERROR_0(0, "角色不存在"),
		/** 角色名字存在 */
		SYS_ROLE_ENUM_ERROR_1(1, "角色名字存在"),;
		private Integer codeError;

		private String messageError;

		private SysRoleEnumError(int codeError, String messageError) {
			this.codeError = codeError;
			this.messageError = messageError;
		}

		@Override
		public Integer getCodeError() {
			return codeError;
		}

		@Override
		public String getMessageError() {
			return messageError;
		}
	}

	/**
	 * @Author wx
	 * @Description 树形工具类异常枚举
	 * @Date 2018-9-16
	 */
	public enum TreeEnumError implements EnumError {
		/** 层级level为空 */
		TREE_ENUM_ERROR_0(0, "层级level为空"),;
		private Integer codeError;

		private String messageError;

		private TreeEnumError(int codeError, String messageError) {
			this.codeError = codeError;
			this.messageError = messageError;
		}

		@Override
		public Integer getCodeError() {
			return codeError;
		}

		@Override
		public String getMessageError() {
			return messageError;
		}
	}


	/**
	 * @Author wx
	 * @Description 权限日志操作异常枚举
	 * @Date 2018-9-24
	 */
	public enum SysLogEnumError implements EnumError {
		/** 日志记录不存在 */
		SYS_LOG_ENUM_ERROR_0(0, "权限操作日志记录不存在!"),
		/**  日志新增和删除操作不做还原 */
		SYS_LOG_ENUM_ERROR_1(1, "日志新增和删除操作不做还原!"),
		;
		private Integer codeError;

		private String messageError;

		private SysLogEnumError(int codeError, String messageError) {
			this.codeError = codeError;
			this.messageError = messageError;
		}

		@Override
		public Integer getCodeError() {
			return codeError;
		}

		@Override
		public String getMessageError() {
			return messageError;
		}
	}


	/**
	 * @Author wx
	 * @Description 用户token异常枚举
	 * @Date 2018-9-24
	 */
	public enum UserTokenInfoEnumError implements EnumError {
		/** 数据异常,用户token信息不存在 */
		USER_TOKEN_INFO_ENUM_ERROR_0(0, "数据异常,用户token信息不存在"),
		/**  日志新增和删除操作不做还原 */
		USER_TOKEN_INFO_ENUM_ERROR_1(1, "数据异常,同一用户存在多个条token信息!"),
		;
		private Integer codeError;

		private String messageError;

		private UserTokenInfoEnumError(int codeError, String messageError) {
			this.codeError = codeError;
			this.messageError = messageError;
		}

		@Override
		public Integer getCodeError() {
			return codeError;
		}

		@Override
		public String getMessageError() {
			return messageError;
		}
	}
}
