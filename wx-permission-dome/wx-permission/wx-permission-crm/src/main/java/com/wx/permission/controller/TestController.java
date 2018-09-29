package com.wx.permission.controller;


import com.google.common.collect.Lists;
import com.wx.core.response.JsonData;
import com.wx.core.validator.BeanValidatorUtils;
import com.wx.core.validator.groups.Save;
import com.wx.permission.model.SysAcl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello() {
        log.info("hello");
        SysAcl sysAcl = new SysAcl();
        sysAcl.setCode(null);
        BeanValidatorUtils.validateObjectParam(sysAcl, Save.class);
        return null;
    }

    @RequestMapping("/validate.page")
    @ResponseBody
    public JsonData validate() {
        log.info("validate");
        SysAcl sysAcl = new SysAcl();
        sysAcl.setCode(null);
        SysAcl sysAcl2 = new SysAcl();
        sysAcl2.setCode(null);
        SysAcl sysAcl3 = new SysAcl();
        sysAcl3.setCode("11");
        SysAcl sysAcl4 = new SysAcl();
        sysAcl4.setCode(null);
        List<SysAcl> sysAclList = Lists.newArrayList();
        sysAclList.add(sysAcl);
        sysAclList.add(sysAcl2);
        sysAclList.add(sysAcl3);
        sysAclList.add(sysAcl4);
        BeanValidatorUtils.validateObjectParam(sysAclList, Save.class);
        //throw new BusinessPageException("空指针异常");
        return null;
    }
}
