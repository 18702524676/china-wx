package com.wx.permission.service;

import com.wx.core.response.JsonData;
import com.wx.permission.dto.SysDeptDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Author wx
 * @Description 部门接口暴露
 * @Date 2018-08-19-18:04
 */
@RequestMapping("/sys/dept/")
public interface SysDeptRevealService {


    /**
     * @param sysDeptDto 对象dto
     * @methodName: saveDept
     * @author: wx
     * @description: 新增部门
     * @date: 2018/8/19
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @PostMapping("saveDept.json")
    JsonData saveDept(SysDeptDto sysDeptDto);

    /**
     * @param sysDeptDto 对象dto
     * @methodName: updateDept
     * @author: wx
     * @description: 更新部门
     * @date: 2018/8/22
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @PostMapping("updateDept.json")
    JsonData updateDept(SysDeptDto sysDeptDto);

    /**
     * @param
     * @methodName: getDeptTree
     * @author: wx
     * @description: 获取部门Tree结构
     * @date: 2018/8/21
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @GetMapping("getDeptTree.json")
    JsonData getDeptTree();

    /**
     * @param id
     * @methodName: delete
     * @author: wx
     * @description: 删除部门数据
     * @date: 2018/9/9
     * @return: com.wx.mypermission.common.response.JsonData
     */
    @PostMapping("/delete.json")
    JsonData delete(@RequestParam("id") int id);

}
