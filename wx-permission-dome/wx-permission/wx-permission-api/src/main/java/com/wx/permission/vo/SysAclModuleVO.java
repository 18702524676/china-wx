package com.wx.permission.vo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName SysAclModuleVO
 * @Author wx
 * @Description 权限模块VO
 * @Date 2018-09-02-16:23
 */
@Data
public class SysAclModuleVO {
    private Integer id;

    private String name;

    private Integer parentId;

    private String level;

    private Integer seq;

    private Integer status;

    private String remark;

    private String operator;

    private Date operateTime;

    private String operateIp;
}
