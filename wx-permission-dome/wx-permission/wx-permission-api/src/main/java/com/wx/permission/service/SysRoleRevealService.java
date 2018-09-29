package com.wx.permission.service;


import com.wx.core.response.JsonData;
import com.wx.permission.dto.SysRoleDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName SysRoleController
 * @Author wx
 * @Description 角色接口暴露
 * @Date 2018-09-05-22:10
 */
@RequestMapping("/sys/role")
public interface SysRoleRevealService {


    /**
     * @param sysRoleDto
     * @methodName: saveSysRole
     * @author: wx
     * @description: 新增角色
     * @date: 2018/9/5
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @PostMapping("saveSysRole.json")
    JsonData saveSysRole(SysRoleDto sysRoleDto);

    /**
     * @param sysRoleDto
     * @methodName: updateSysRole
     * @author: wx
     * @description: 更新角色
     * @date: 2018/9/5
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @PostMapping("updateSysRole.json")
    JsonData updateSysRole(SysRoleDto sysRoleDto);

    /**
     * @param
     * @methodName: getSysRoleAll
     * @author: wx
     * @description: 获取所有角色信息
     * @date: 2018/9/5
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @GetMapping("getSysRoleAll.json")
    JsonData getSysRoleAll();

    /**
     * @param roleId
     * @methodName: roleTree
     * @author: wx
     * @description: 根据角色获取权限模块 +权限
     * @date: 2018/9/8
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @GetMapping("/roleTree.json")
    JsonData roleTree(@RequestParam("roleId") int roleId);

    /**
     * @param roleId 角色id
     * @param aclIds 权限集合id
     * @methodName: changeAcls
     * @author: wx
     * @description: 修改权限数据 删除缓存allEntries默认值false 此时只删除key对应的值，true清空缓存cacheNames里的所有值
     * @date: 2018/9/9
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @PostMapping("/changeAcls.json")
    JsonData changeAcls(@RequestParam int roleId, @RequestParam String aclIds);

    /**
     * @param roleId 角色id
     * @methodName: users
     * @author: wx
     * @description: 根据角色id查询用户列表（包含未绑定的与当前角色绑定的）
     * @date: 2018/9/9
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @GetMapping("/users.json")
    JsonData users(@RequestParam("roleId") int roleId);

    /**
     * @param roleId  角色id
     * @param userIds 用户集合Id
     * @methodName: changeUsers
     * @author: wx
     * @description: 修改用户数据
     * @date: 2018/9/9
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @PostMapping("/changeUsers.json")
    JsonData changeUsers(@RequestParam("roleId") int roleId, @RequestParam(value = "userIds", required = false, defaultValue = "") String userIds);
}
