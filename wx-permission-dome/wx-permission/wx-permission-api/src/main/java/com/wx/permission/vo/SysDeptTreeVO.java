package com.wx.permission.vo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName SysDeptTreeVO
 * @Author wx
 * @Description 部门树VO
 * @Date 2018-08-20-22:12
 */
@Data
public class SysDeptTreeVO extends TreeParentVO<SysDeptTreeVO>{

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门上级id
     */
    private Integer parentId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    private Date operateTime;

    /**
     * ip
     */
    private String operateIp;
}
