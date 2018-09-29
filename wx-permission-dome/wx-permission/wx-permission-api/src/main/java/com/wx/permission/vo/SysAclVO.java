package com.wx.permission.vo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName SysAclVO
 * @Author wx
 * @Description 权限VO
 * @Date 2018-09-04-22:50
 */
@Data
public class SysAclVO {

	private static final long serialVersionUID = -3997832579651315603L;

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

	/**
	 * 是否要默认选中
	 */
	private boolean checked;

	/**
	 * 是否有权限操作
	 */
	private boolean hasAcl;
}
