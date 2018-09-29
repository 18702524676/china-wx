package com.wx.permission.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName SysRoleAclDto
 * @Author wx
 * @Description 角色权限Dto
 * @Date 2018-09-24-18:54
 */
@Data
public class SysRoleAclDto {

	private Integer roleId;

	private List<Integer> aclIdlist;

	/**
	 * 操作人
	 */
	private String operator;

	/**
	 * 操作时间
	 */
	private Date operateTime;

	/**
	 * ip
	 */
	private String operateIp;

	public SysRoleAclDto() {
	}

	public SysRoleAclDto(Integer roleId, List<Integer> aclIdlist) {
		this.roleId = roleId;
		this.aclIdlist = aclIdlist;
	}
}
