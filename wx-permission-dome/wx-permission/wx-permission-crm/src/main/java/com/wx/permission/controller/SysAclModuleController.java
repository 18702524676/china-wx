package com.wx.permission.controller;

import com.wx.core.response.JsonData;
import com.wx.permission.annotations.PermissionsLog;
import com.wx.permission.dto.SysAclModuleDto;
import com.wx.permission.service.SysAclModuleRevealService;
import com.wx.permission.service.SysAclModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import static com.wx.commons.constant.CommonConstant.BusinessOperation.DELETE_SUCCESS;
import static com.wx.permission.enums.PermissionEnumStatus.SysLogType.SYS_LOG_TYPE_3;


/**
 * @ClassName SysAclModuleController
 * @Author wx
 * @Description 权限模块Controlle
 * @Date 2018-09-02-15:00
 */
@Controller
public class SysAclModuleController implements SysAclModuleRevealService {

    @Autowired
    private SysAclModuleService sysAclModuleService;
    

    /**
     * @methodName: page
     * @author: wx
     * @description: 权限页面
     * @param
     * @date: 2018/9/26
     * @return: org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping("/acl.page")
    public ModelAndView page() {
        return new ModelAndView("acl");
    }

    /**
     * @methodName: saveSysAclModule
     * @author: wx
     * @description: 新增权限模块
     * @param sysAclModuleDto
     * @date: 2018/9/2
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @Override
    @ResponseBody
    @PermissionsLog(SYS_LOG_TYPE_3)
    public JsonData saveSysAclModule(SysAclModuleDto sysAclModuleDto){
        return  sysAclModuleService.saveSysAclModule(sysAclModuleDto);
    }
    
    /**
     * @methodName: updateSysAclModule
     * @author: wx
     * @description: 更新权限模块
     * @param sysAclModuleDto
     * @date: 2018/9/2
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @Override
    @ResponseBody
    @PermissionsLog(SYS_LOG_TYPE_3)
    public JsonData updateSysAclModule(SysAclModuleDto sysAclModuleDto){
        return sysAclModuleService.updateSysAclModule(sysAclModuleDto);
    }
    
    /**
     * @methodName: getDeptTree
     * @author: wx
     * @description: 获取权限模Tree结构
     * @param
     * @date: 2018/9/2
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @Override
    @ResponseBody
    public JsonData getAclModuleTree(){
        return JsonData.ResultMsgSuccess(sysAclModuleService.getAclModuleTree());
    }

    /**
     * @methodName: delete
     * @author: wx
     * @description: 删除权限模块
     * @param id     权限模块id
     * @date: 2018/9/9
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @Override
    @ResponseBody
    public JsonData delete(@RequestParam("id") int id) {
        sysAclModuleService.delete(id);
        return JsonData.ResultMsgSuccess(DELETE_SUCCESS);
    }
}
