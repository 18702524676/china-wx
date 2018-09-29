package com.wx.permission.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysLog implements Serializable {

	private static final long serialVersionUID = 8979518159734239888L;

	private Integer id;

	private Integer type;

	private Integer targetId;

	private String operator;

	private Date operateTime;

	private String operateIp;
	
	/**
	 * 当前是否复原过，0:否，1:是
	 */
	private Integer status;

}