package com.wx.permission.controller;

import com.google.common.collect.Maps;
import com.wx.commons.tools.Page;
import com.wx.core.response.JsonData;
import com.wx.permission.annotations.PermissionsLog;
import com.wx.permission.dto.SysAclDto;
import com.wx.permission.qo.SysAclQO;
import com.wx.permission.service.SysAclRevealService;
import com.wx.permission.service.SysAclService;
import com.wx.permission.service.SysRoleService;
import com.wx.permission.vo.SysAclVO;
import com.wx.permission.vo.SysRoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.wx.permission.enums.PermissionEnumStatus.SysLogType.SYS_LOG_TYPE_4;


/**
 * @ClassName SysAclController
 * @Author wx
 * @Description 权限Controller
 * @Date 2018-09-04-21:53
 */
@Controller
public class SysAclController implements SysAclRevealService {

    @Autowired
    private SysAclService sysAclService;

    @Autowired
    private SysRoleService sysRoleService;
    
    /**
     * @methodName: saveSysAcl
     * @author: wx
     * @description: 新增权限
     * @param sysAclDto
     * @date: 2018/9/4
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @Override
    @ResponseBody
    @PermissionsLog(SYS_LOG_TYPE_4)
    public JsonData saveSysAcl(SysAclDto sysAclDto){
        return sysAclService.saveSysAcl(sysAclDto);
    }

    /**
     * @methodName: updateSysAcl
     * @author: wx
     * @description: 更新权限
     * @param sysAclDto
     * @date: 2018/9/4
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @Override
    @ResponseBody
    public JsonData updateSysAcl(SysAclDto sysAclDto){
        return sysAclService.updateSysAcl(sysAclDto);
    }
    
    /**
     * @methodName: pageSysAcl
     * @author: wx
     * @description: 权限分页查询
     * @param sysAclQO
     * @date: 2018/9/4
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @Override
    @ResponseBody
    public JsonData pageSysAcl(SysAclQO sysAclQO){
        Page<SysAclVO> sysAclVOpage =  sysAclService.pageSysAcl(sysAclQO);
        return JsonData.ResultMsgSuccess(sysAclVOpage);
    }
    
    /**
     * @methodName: acls
     * @author: wx
     * @description: 查询权限对应的角色and用户信息
     * @param aclId  权限id
     * @date: 2018/9/9
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @Override
    @ResponseBody
    public JsonData acls(@RequestParam("aclId") int aclId) {
        Map<String, Object> map = Maps.newHashMap();
        List<SysRoleVO> sysRoleVOList = sysRoleService.getRoleListByAclId(aclId);
        List<Integer> roleList = sysRoleVOList.stream().map(sysRoleVO -> sysRoleVO.getId()).collect(Collectors.toList());
        map.put("roles", sysRoleService.getRoleListByAclId(aclId));
        map.put("users", sysRoleService.getUserListByRoleList(roleList));
        return JsonData.ResultMsgSuccess(map);
    }
}
