package com.wx.permission.controller;

import com.wx.commons.tools.Page;
import com.wx.core.response.JsonData;
import com.wx.permission.qo.SysLogQO;
import com.wx.permission.service.SysLogRevealService;
import com.wx.permission.service.SysLogService;
import com.wx.permission.vo.SysLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import static com.wx.commons.constant.CommonConstant.BusinessOperation.UPDATE_SUCCESS;


/**
 * @ClassName SysLogController
 * @Author wx
 * @Description 权限操作日志
 * @Date 2018-09-18-23:30
 */
@Controller
public class SysLogController implements SysLogRevealService {

    @Autowired
    private SysLogService sysLogService;
    /**
     * @methodName: page
     * @author: wx
     * @description: 权限日志记录页面
     * @param
     * @date: 2018/9/23
     * @return: org.springframework.web.servlet.ModelAndView
     */
    @GetMapping("/log.page")
    public ModelAndView page() {
        return new ModelAndView("log");
    }
    
    /**
     * @methodName: pageSysLog
     * @author: wx
     * @description: 权限日志分页查询
     * @param sysLogQO 查询对象
     * @date: 2018/9/23
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @Override
    @ResponseBody
    public JsonData pageSysLog(SysLogQO sysLogQO) {
        Page<SysLogVO> sysLogVOList = sysLogService.pageSysLog(sysLogQO);
        return JsonData.ResultMsgSuccess(sysLogVOList);
    }
    
    /**
     * @methodName: recover
     * @author: wx
     * @description: 还原记录
     * @param id
     * @date: 2018/9/24
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @Override
    @ResponseBody
    public JsonData recover(@RequestParam("id") int id) {
        sysLogService.recover(id);
        return JsonData.ResultMsgSuccess(UPDATE_SUCCESS);
    }
}
