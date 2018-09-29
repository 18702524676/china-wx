package com.wx.permission.scheduler;


import com.wx.permission.service.UserTokenInfoService;
import com.wx.permission.vo.UserTokenInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @ClassName RefreshUserTokenInfoTaskScheduler
 * @Author wx
 * @Description 刷新用户token信息定时任务
 * @Date 2018-09-24-16:33
 */
@Component
public class RefreshUserTokenInfoTaskScheduler {

    @Autowired
    private UserTokenInfoService userTokenInfoService;
    
    /**
     * @methodName: refreshUserTokenInfo
     * @author: wx
     * @description: 每天凌晨一点执行刷新token
     * @param
     * @date: 2018/9/24
     * @return: void
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void  refreshUserTokenInfo(){
        List<UserTokenInfoVO> userTokenInfoAll = userTokenInfoService.getUserTokenInfoAll();
        if (CollectionUtils.isEmpty(userTokenInfoAll)){
            return;
        }
        //刷新用户token
        userTokenInfoAll.forEach(userTokenInfoVO ->{
            userTokenInfoService.refreshToken(userTokenInfoVO.getUserId());
        });
    }

}
