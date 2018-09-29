package com.wx.permission.service;

import com.wx.core.response.JsonData;
import com.wx.permission.dto.SysAclDto;
import com.wx.permission.qo.SysAclQO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Author wx
 * @Description 权限接口暴露
 * @Date 2018-09-04-21:53
 */
@RequestMapping("/sys/acl")
public interface SysAclRevealService {


    /**
     * @param sysAclDto
     * @methodName: saveSysAcl
     * @author: wx
     * @description: 新增权限
     * @date: 2018/9/4
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @PostMapping("saveSysAcl.json")
    JsonData saveSysAcl(SysAclDto sysAclDto);

    /**
     * @param sysAclDto
     * @methodName: updateSysAcl
     * @author: wx
     * @description: 更新权限
     * @date: 2018/9/4
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @PostMapping("updateSysAcl.json")
    JsonData updateSysAcl(SysAclDto sysAclDto);

    /**
     * @param sysAclQO
     * @methodName: pageSysAcl
     * @author: wx
     * @description: 权限分页查询
     * @date: 2018/9/4
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @GetMapping("pageSysAcl.json")
    JsonData pageSysAcl(SysAclQO sysAclQO);

    /**
     * @param aclId 权限id
     * @methodName: acls
     * @author: wx
     * @description: 查询权限对应的角色and用户信息
     * @date: 2018/9/9
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @GetMapping("acls.json")
    JsonData acls(@RequestParam("aclId") int aclId);
}
