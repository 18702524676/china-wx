package com.wx.permission.vo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName SysRoleVO
 * @Author wx
 * @Description 角色VO
 * @Date 2018-09-09-18:11
 */
@Data
public class SysRoleVO {
    private Integer id;

    private String name;

    private Integer type;

    private Integer status;

    private String remark;

    private String operator;

    private Date operateTime;

    private String operateIp;
}
