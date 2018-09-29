package com.wx.permission.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysUser implements Serializable {

	private static final long serialVersionUID = -7709858339860420017L;

	private Integer id;

	private String username;

	private String telephone;

	private String mail;

	private String password;

	private Integer deptId;

	private Integer status;

	private String remark;

	private String operator;

	private Date operateTime;

	private String operateIp;

}