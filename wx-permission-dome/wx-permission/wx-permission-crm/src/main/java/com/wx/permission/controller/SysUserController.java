package com.wx.permission.controller;

import com.google.common.collect.Maps;
import com.wx.commons.tools.Page;
import com.wx.core.response.JsonData;
import com.wx.permission.annotations.PermissionsDesc;
import com.wx.permission.annotations.PermissionsLog;
import com.wx.permission.dto.SysUserDto;
import com.wx.permission.qo.SysUserQO;
import com.wx.permission.service.SysRoleService;
import com.wx.permission.service.SysTreeService;
import com.wx.permission.service.SysUserRevealService;
import com.wx.permission.service.SysUserService;
import com.wx.permission.vo.SysUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import static com.wx.permission.enums.PermissionEnumStatus.SysLogType.SYS_LOG_TYPE_2;


/**
 * @ClassName SysUserController
 * @Author wx
 * @Description 系统用户Controller
 * @Date 2018-08-26-20:46
 */
@Controller
public class SysUserController implements SysUserRevealService {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysTreeService sysTreeService;

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * @param sysUserDto
     * @methodName: saveUser
     * @author: wx
     * @description: 添加用户新
     * @date: 2018/8/26
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @Override
    @ResponseBody
    @PermissionsLog(SYS_LOG_TYPE_2)
    public JsonData saveUser(SysUserDto sysUserDto) {
        return sysUserService.saveUser(sysUserDto);
    }


    /**
     * @param sysUserDto
     * @methodName: updateUser
     * @author: wx
     * @description: 更新用户信息
     * @date: 2018/8/26
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @Override
    @ResponseBody
    @PermissionsLog(SYS_LOG_TYPE_2)
    public JsonData updateUser(SysUserDto sysUserDto) {
        return sysUserService.updateUser(sysUserDto);
    }


    /**
     * @param sysUserQO
     * @methodName: pageUser
     * @author: wx
     * @description: 分页查询用户信息
     * @date: 2018/8/31
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @Override
    @ResponseBody
    @PermissionsDesc("分页查询用户")
    public JsonData pageUser(SysUserQO sysUserQO) {
        Page<SysUserVO> sysUserVOPage = sysUserService.getPage(sysUserQO);
        return JsonData.ResultMsgSuccess(sysUserVOPage);
    }
    
    /**
     * @methodName: acls
     * @author: wx
     * @description: 根据用户查询绑定的权限与角色信息
     * @param
     * @date: 2018/9/9
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @Override
    @ResponseBody
    public JsonData acls(@RequestParam("userId") int userId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("acls", sysTreeService.userAclTree(userId));
        map.put("roles", sysRoleService.getRoleListByUserId(userId));
        return JsonData.ResultMsgSuccess(map);
    }

}