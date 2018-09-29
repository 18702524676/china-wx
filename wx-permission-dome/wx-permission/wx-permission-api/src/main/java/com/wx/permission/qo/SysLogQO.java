package com.wx.permission.qo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wx.commons.tools.Page;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName SysLogQO
 * @Author wx
 * @Description 权限日志QO
 * @Date 2018-09-23-15:13
 */
@Data
public class SysLogQO extends Page {
	private Integer type;

	private String beforeSeg;

	private String afterSeg;

	private String operator;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fromTime;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date toTime;
}
