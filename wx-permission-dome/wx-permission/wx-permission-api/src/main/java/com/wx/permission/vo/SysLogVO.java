package com.wx.permission.vo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName SysLogVo
 * @Author wx
 * @Description 权限操作日志VO
 * @Date 2018-09-23-13:12
 */
@Data
public class SysLogVO {

	private Integer id;

	private Integer targetId;

	private Integer type;

	private String oldValue;

	private String newValue;

	private String operator;

	private Date operateTime;

	private String operateIp;

	/**
	 * 当前是否复原过，0:否，1:是
	 */
	private Integer status;

	public SysLogVO() {
	}

	public SysLogVO(Integer targetId, String oldValue, String newValue, Integer status) {
		this.targetId = targetId;
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.status = status;
	}
}
