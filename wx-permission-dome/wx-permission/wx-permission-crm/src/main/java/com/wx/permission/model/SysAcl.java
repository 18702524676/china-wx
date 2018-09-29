package com.wx.permission.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysAcl implements Serializable {

	private static final long serialVersionUID = 451140498065684179L;

	private Integer id;

	private String code;

	private String name;

	private Integer aclModuleId;

	private String url;

	private Integer type;

	private Integer status;

	private Integer seq;

	private String remark;

	private String operator;

	private Date operateTime;

	private String operateIp;

}