package com.wx.permission.model;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysRole implements Serializable {

	private static final long serialVersionUID = -1815005463064325758L;

	private Integer id;

	private String name;

	private Integer type;

	private Integer status;

	private String remark;

	private String operator;

	private Date operateTime;

	private String operateIp;

}