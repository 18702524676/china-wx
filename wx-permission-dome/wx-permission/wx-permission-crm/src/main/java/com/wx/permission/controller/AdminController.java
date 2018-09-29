package com.wx.permission.controller;

import com.wx.permission.service.AdminRevealService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName SysDeptController
 * @Author wx
 * @Description 首页Controlle
 * @Date 2018-08-19-18:04
 */
@Controller
public class AdminController implements AdminRevealService {
    
    /**
     * @methodName: index
     * @author: wx
     * @description: 首页
     * @param 
     * @date: 2018/8/29
     * @return: org.springframework.web.servlet.ModelAndView
     */
    @GetMapping("index.page")
    public ModelAndView index() {
        return new ModelAndView("admin");
    }
}
