package com.wx.permission.service;


import com.wx.core.response.JsonData;
import com.wx.permission.qo.SysLogQO;
import org.springframework.web.bind.annotation.*;

;

/**
 * @ClassName SysLogController
 * @Author wx
 * @Description 权限操作日志接口暴露
 * @Date 2018-09-18-23:30
 */
@RequestMapping("/sys/log")
public interface SysLogRevealService {


    /**
     * @param sysLogQO 查询对象
     * @methodName: pageSysLog
     * @author: wx
     * @description: 权限日志分页查询
     * @date: 2018/9/23
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @GetMapping("/pageSysLog.json")
    JsonData pageSysLog(SysLogQO sysLogQO);

    /**
     * @param id
     * @methodName: recover
     * @author: wx
     * @description: 还原记录
     * @date: 2018/9/24
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @PostMapping("/recover.json")
    public JsonData recover(@RequestParam("id") int id);
}
