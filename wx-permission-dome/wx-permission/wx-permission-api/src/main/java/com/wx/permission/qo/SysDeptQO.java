package com.wx.permission.qo;

import com.wx.commons.tools.Page;
import lombok.Data;

/**
 * @ClassName SysDeptQO
 * @Author wx
 * @Description 部门QO
 * @Date 2018-08-19-18:24
 */
@Data
public class SysDeptQO extends Page{

    /**
     * 上级iid
     */
    private Integer parentId;

    /**
     * 部门名称
     */
    private String name;
}
