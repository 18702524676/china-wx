package com.wx.permission.aspect;


import com.wx.core.response.JsonData;
import com.wx.permission.annotations.PermissionsLog;
import com.wx.permission.vo.SysLogVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName SysLogAop
 * @Author wx
 * @Description 权限日志AOP
 * @Date 2018-09-22-0:13
 */
@Aspect
@Slf4j
@Component
public class PermissionsLogAop {

	@Autowired
	private PermissionsLogThread permissionsLogThread;

	/**
	 * @methodName: sysLogMethodAfter
	 * @author: wx
	 * @description: 后置返回通知
	 * @param result 返回结果
	 * @param permissionsLog 自定义日志注解
	 * @date: 2018/9/23
	 * @return: void
	 */
	@AfterReturning(value = "(execution(* com.wx.permission.controller..*.*(..)) && @annotation(permissionsLog))",
		returning = "result")
	public void sysLogMethodAfter(Object result, PermissionsLog permissionsLog) {
		try {
			if (result != null && result instanceof JsonData) {
				JsonData jsonData = (JsonData) result;
				Object data = jsonData.getData();
				if (data != null && data instanceof SysLogVO) {
					SysLogVO sysLogVO = (SysLogVO) data;
					sysLogVO.setType(permissionsLog.value().getCode());
                    permissionsLogThread.offerQueue(sysLogVO);
				}
			}
		} catch (Exception e) {
			log.error(String.format("权限操作日志记录异常，返回参数:$s,异常信息:$s"), result.toString(), e.getMessage());
		}
	}
}
