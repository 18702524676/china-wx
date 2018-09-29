package com.wx.permission.dto;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName SysLogDTO
 * @Author wx
 * @Description 权限操作日志DTO
 * @Date 2018-09-24-17:32
 */
@Data
public class SysLogDTo {

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

}
