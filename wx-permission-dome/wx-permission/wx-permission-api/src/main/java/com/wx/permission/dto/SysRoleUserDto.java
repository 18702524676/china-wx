package com.wx.permission.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName SysRoleUserDto
 * @Author wx
 * @Description 角色用户dto
 * @Date 2018-09-24-18:53
 */
@Data
public class SysRoleUserDto {

	private Integer roleId;

	private List<Integer> userIdlist;

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

	public SysRoleUserDto() {
	}

	public SysRoleUserDto(Integer roleId, List<Integer> userIdlist) {
		this.roleId = roleId;
		this.userIdlist = userIdlist;
	}
}
