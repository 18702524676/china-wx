package com.wx.permission.service;


import com.wx.core.response.JsonData;
import com.wx.permission.dto.SysAclModuleDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Author wx
 * @Description 权限模块接口暴露
 * @Date 2018-09-02-15:00
 */
@RequestMapping("/sys/aclModule")
public interface SysAclModuleRevealService {


    /**
     * @param sysAclModuleDto
     * @methodName: saveSysAclModule
     * @author: wx
     * @description: 新增权限模块
     * @date: 2018/9/2
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @PostMapping("saveSysAclModule.json")
    JsonData saveSysAclModule(SysAclModuleDto sysAclModuleDto);

    /**
     * @param sysAclModuleDto
     * @methodName: updateSysAclModule
     * @author: wx
     * @description: 更新权限模块
     * @date: 2018/9/2
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @PostMapping("updateSysAclModule.json")
    JsonData updateSysAclModule(SysAclModuleDto sysAclModuleDto);

    /**
     * @param
     * @methodName: getDeptTree
     * @author: wx
     * @description: 获取权限模Tree结构
     * @date: 2018/9/2
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @GetMapping("getAclModuleTree.json")
    JsonData getAclModuleTree();

    /**
     * @param id 权限模块id
     * @methodName: delete
     * @author: wx
     * @description: 删除权限模块
     * @date: 2018/9/9
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @PostMapping("/delete.json")
    JsonData delete(@RequestParam("id") int id);
}
