package com.wx.permission.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysRoleAcl implements Serializable {
	private static final long serialVersionUID = 2494713412424826257L;

	private Integer id;

	private Integer roleId;

	private Integer aclId;

	private String operator;

	private Date operateTime;

	private String operateIp;

}