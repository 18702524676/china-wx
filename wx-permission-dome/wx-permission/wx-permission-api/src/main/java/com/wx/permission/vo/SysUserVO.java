package com.wx.permission.vo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName SysUserVO
 * @Author wx
 * @Description 系统用户VO
 * @Date 2018-08-31-22:24
 */
@Data
public class SysUserVO {

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
