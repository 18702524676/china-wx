package com.wx.permission.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysLogWithBLOBs extends SysLog implements Serializable {

    private static final long serialVersionUID = -8785493242290379599L;

    private String oldValue;

    private String newValue;

}