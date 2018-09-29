package com.wx.permission.initialize;


import com.wx.permission.aspect.PermissionsLogThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * @ClassName SysLogCommandLineRunner
 * @Author wx
 * @Description 日志初始化操作
 * @Date 2018-09-24-17:45
 */
@Component
public class SysLogCommandLineRunner implements CommandLineRunner {
    @Autowired
    private PermissionsLogThread permissionsLogThread;
    @Autowired
    private ThreadPoolTaskExecutor myExecutor;
    
    /**
     * @methodName: run
     * @author: wx
     * @description: 运行操作日志线程
     * @param args
     * @date: 2018/9/24
     * @return: void
     */
    @Override
    public void run(String... args) throws Exception {
        myExecutor.execute(permissionsLogThread);
    }
}
