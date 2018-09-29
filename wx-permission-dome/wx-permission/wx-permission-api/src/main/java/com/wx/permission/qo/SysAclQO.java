package com.wx.permission.qo;


import com.wx.commons.tools.Page;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName SysAclQO
 * @Author wx
 * @Description 权限QO
 * @Date 2018-09-04-22:13
 */
@Data
public class SysAclQO extends Page {

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
