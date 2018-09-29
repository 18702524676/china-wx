package com.wx.permission.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysDept implements Serializable {

	private static final long serialVersionUID = 5206686368534259980L;

	private Integer id;

	private String name;

	private Integer parentId;

	private String level;

	private Integer seq;

	private String remark;

	private String operator;

	private Date operateTime;

	private String operateIp;
}