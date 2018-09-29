package com.wx.permission.qo;

import com.wx.commons.tools.Page;
import lombok.Data;

/**
 * @ClassName SysAclModuleQO
 * @Author wx
 * @Description 权限模板QO
 * @Date 2018-09-02-15:31
 */
@Data
public class SysAclModuleQO extends Page{
    /**
     * 上级iid
     */
    private Integer parentId;

    /**
     * 部门名称
     */
    private String name;
}
