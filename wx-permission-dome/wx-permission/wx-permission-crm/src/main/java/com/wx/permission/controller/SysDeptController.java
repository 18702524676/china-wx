package com.wx.permission.controller;


import com.wx.core.response.JsonData;
import com.wx.permission.annotations.PermissionsLog;
import com.wx.permission.dto.SysDeptDto;
import com.wx.permission.service.SysDeptRevealService;
import com.wx.permission.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import static com.wx.commons.constant.CommonConstant.BusinessOperation.DELETE_SUCCESS;
import static com.wx.permission.enums.PermissionEnumStatus.SysLogType.SYS_LOG_TYPE_1;

/**
 * @ClassName SysDeptController
 * @Author wx
 * @Description 部门Controlle
 * @Date 2018-08-19-18:04
 */
@Controller
public class SysDeptController implements SysDeptRevealService {

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * @param sysDeptDto 对象dto
     * @methodName: saveDept
     * @author: wx
     * @description: 新增部门
     * @date: 2018/8/19
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @Override
    @ResponseBody
    @PermissionsLog(SYS_LOG_TYPE_1)
    public JsonData saveDept(SysDeptDto sysDeptDto) {
        return sysDeptService.saveDept(sysDeptDto);
    }

    /**
     * @param sysDeptDto 对象dto
     * @methodName: updateDept
     * @author: wx
     * @description: 更新部门
     * @date: 2018/8/22
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @Override
    @ResponseBody
    @PermissionsLog(SYS_LOG_TYPE_1)
    public JsonData updateDept(SysDeptDto sysDeptDto) {
        return sysDeptService.updateDept(sysDeptDto);
    }

    /**
     * @param
     * @methodName: getDeptTree
     * @author: wx
     * @description: 获取部门Tree结构
     * @date: 2018/8/21
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @Override
    @ResponseBody
    public JsonData getDeptTree() {
        return JsonData.ResultMsgSuccess(sysDeptService.getDeptTree());
    }

    /**
     * @param id
     * @methodName: delete
     * @author: wx
     * @description: 删除部门数据
     * @date: 2018/9/9
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @Override
    @ResponseBody
    public JsonData delete(@RequestParam("id") int id) {
        sysDeptService.delete(id);
        return JsonData.ResultMsgSuccess(DELETE_SUCCESS);
    }

    /**
     * @param
     * @methodName: index
     * @author: wx
     * @description: 部门页面
     * @date: 2018/9/9
     * @return: org.springframework.web.servlet.ModelAndView
     */
    @GetMapping("dept.page")
    public ModelAndView index() {
        return new ModelAndView("dept");
    }
}
