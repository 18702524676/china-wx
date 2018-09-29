package com.wx.permission.vo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName SysDeptVO
 * @Author wx
 * @Description 部门VO
 * @Date 2018-08-20-22:00
 */
@Data
public class SysDeptVO {
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
