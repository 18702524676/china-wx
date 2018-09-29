package com.wx.permission.qo;

import com.wx.commons.tools.Page;
import lombok.Data;

/**
 * @ClassName SysUserQO
 * @Author wx
 * @Description 系统用户QO
 * @Date 2018-08-26-21:47
 */
@Data
public class SysUserQO extends Page {

	private Integer id;

	private String username;

	private String telephone;

	private String password;

	private String mail;

	private Integer deptId;

	private Integer status;
}
