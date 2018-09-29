package com.wx.permission.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName SysAclModuleTreeVO
 * @Author wx
 * @Description 权限模块树VO
 * @Date 2018-09-02-16:20
 */
@Data
public class SysAclModuleTreeVO extends TreeParentVO<SysAclModuleTreeVO>{
    /**
     * 权限模块名称
     */
    private String name;

    /**
     * 权限模块上级id
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
    
    /**
     * 权限集合
     */
    private List<SysAclVO> sysAclVOList;
}
