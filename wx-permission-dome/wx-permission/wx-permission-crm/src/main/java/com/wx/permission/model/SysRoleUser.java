package com.wx.permission.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysRoleUser implements Serializable {

	private static final long serialVersionUID = -5890568498832982944L;

	private Integer id;

	private Integer roleId;

	private Integer userId;

	private String operator;

	private Date operateTime;

	private String operateIp;

}