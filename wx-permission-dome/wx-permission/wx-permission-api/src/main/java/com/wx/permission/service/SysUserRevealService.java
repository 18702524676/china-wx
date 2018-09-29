package com.wx.permission.service;

import com.wx.core.response.JsonData;
import com.wx.permission.dto.SysUserDto;
import com.wx.permission.qo.SysUserQO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @ClassName SysUserController
 * @Author wx
 * @Description 系统用户接口暴露
 * @Date 2018-08-26-20:46
 */
@RequestMapping("/sys/user")
public interface SysUserRevealService {


    /**
     * @param sysUserDto
     * @methodName: saveUser
     * @author: wx
     * @description: 添加用户新
     * @date: 2018/8/26
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @PostMapping("saveUser.json")
    JsonData saveUser(SysUserDto sysUserDto);


    /**
     * @param sysUserDto
     * @methodName: updateUser
     * @author: wx
     * @description: 更新用户信息
     * @date: 2018/8/26
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @PostMapping("updateUser.json")
    JsonData updateUser(SysUserDto sysUserDto);


    /**
     * @param sysUserQO
     * @methodName: pageUser
     * @author: wx
     * @description: 分页查询用户信息
     * @date: 2018/8/31
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @GetMapping("pageUser.json")
    JsonData pageUser(SysUserQO sysUserQO);

    /**
     * @param
     * @methodName: acls
     * @author: wx
     * @description: 根据用户查询绑定的权限与角色信息
     * @date: 2018/9/9
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @GetMapping("/acls.json")
    JsonData acls(@RequestParam("userId") int userId);

}