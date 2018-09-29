package com.wx.permission.service;


import com.wx.commons.tools.Page;
import com.wx.commons.utils.*;
import com.wx.commons.utils.enums.EnumsUtils;
import com.wx.core.utils.SpringUtils;
import com.wx.permission.dao.SysLogMapper;
import com.wx.permission.enums.PermissionEnumStatus;
import com.wx.permission.model.SysLogWithBLOBs;
import com.wx.permission.qo.SysLogQO;
import com.wx.permission.utils.BusinessContextUtils;
import com.wx.permission.vo.SysLogVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import static com.wx.core.constant.SystemConstant.SysDefaultConfigName.DEFAULT_SYS_USER_NAME;
import static com.wx.core.enums.error.SystemEnumError.SystemError.*;
import static com.wx.core.enums.status.SystemEnumStatus.YesOrNo.YES_1;
import static com.wx.permission.constant.PermissionConstant.fieldName.*;
import static com.wx.permission.enums.PermissionEnumError.SysLogEnumError.SYS_LOG_ENUM_ERROR_0;
import static com.wx.permission.enums.PermissionEnumError.SysLogEnumError.SYS_LOG_ENUM_ERROR_1;


/**
 * @ClassName SysLogService
 * @Author wx
 * @Description 权限业务层
 * @Date 2018-09-18-23:31
 */
@Slf4j
@Service
public class SysLogService {

	@Autowired
	private SysLogMapper sysLogMapper;

	/**
	 * @methodName: saveSyslog
	 * @author: wx
	 * @description: 新增权限操作日记
	 * @param sysLogVO 数据
	 * @date: 2018/9/22
	 * @return: void
	 */
	public void saveSyslog(SysLogVO sysLogVO) {
		BusinessValidatorUtils.notNull(sysLogVO, SYSTEM_ERROR_0.getMessageError());
		SysLogWithBLOBs sysLog = new SysLogWithBLOBs();
		BeanUtils.copyProperties(sysLogVO, sysLog);
		sysLog.setOperator(BusinessContextUtils.getSysUser() == null ? DEFAULT_SYS_USER_NAME
			: BusinessContextUtils.getSysUser().getUsername());
		sysLog.setOperateIp(IpUtil.getRemoteIp(BusinessContextUtils.getRequest()));
		sysLog.setOperateTime(new Date());
		sysLogMapper.insertSelective(sysLog);
	}

	/**
	 * @methodName: pageSysLog
	 * @author: wx
	 * @description: 权限日志分页查询
	 * @param sysLogQO
	 * @date: 2018/9/23
	 * @return: Page
	 */
	public Page<SysLogVO> pageSysLog(SysLogQO sysLogQO) {
		Page<SysLogVO> page = new Page();
		if (sysLogQO == null) {
			return page;
		}
		if (sysLogQO.getToTime() != null) {
			sysLogQO.setToTime(DateUtils.dateCalculation(sysLogQO.getToTime(), 1));
		}
		/** Start 获取总数量 */
		int count = sysLogMapper.countByCondition(sysLogQO);
		if (count == 0) {
			return page;
		}
		/** End */

		/** Start 获取分页数据 */
		List<SysLogWithBLOBs> sysLogList = sysLogMapper.getPage(sysLogQO);
		if (CollectionUtils.isEmpty(sysLogList)) {
			return page;
		}
		/** End */

		/** Start 填充page */
		BeanUtils.copyProperties(sysLogQO, page);
		page.setTotal(count);
		page.setData(ConverterUtils.listDateConvert(SysLogVO.class, sysLogList));
		/** End */
		return page;
	}

	/**
	 * @methodName: recover
	 * @author: wx
	 * @description: 还原记录
	 * @param id 记录id
	 * @date: 2018/9/24
	 * @return: void
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { RuntimeException.class, Exception.class })
	public void recover(int id) {
		/** Start 验证记录是否存在、验证是否是新增删除 */
		SysLogWithBLOBs sysLog = sysLogMapper.selectByPrimaryKey(id);
		BusinessValidatorUtils.notNull(sysLog, SYS_LOG_ENUM_ERROR_0.getMessageError());
		BusinessValidatorUtils.isTure(StringUtils.isNotBlank(sysLog.getOldValue()),
			SYS_LOG_ENUM_ERROR_1.getMessageError());
		/** End */

		/** Start 还原记录操作 */
		PermissionEnumStatus.SysLogType sysLogType = EnumsUtils.getEnum(PermissionEnumStatus.SysLogType.class,
			sysLog.getType());
		BusinessValidatorUtils.notNull(sysLogType, SYSTEM_ERROR_1.getMessageError());
		//执行更新方法
		String methodName = sysLogType.getRecoverMethodName();
		Object iocObject = SpringUtils.getBean(sysLogType.getIocClass());
		BusinessValidatorUtils.notNull(iocObject, SYSTEM_ERROR_2.getMessageError());
		Object updateObject = JsonUtils.toObject(sysLog.getOldValue(), sysLogType.getUpdateClass());
		BusinessValidatorUtils.notNull(iocObject, SYSTEM_ERROR_2.getMessageError());
		try {
			Field operator = updateObject.getClass().getDeclaredField(OPERATOR);
			Field operateIp = updateObject.getClass().getDeclaredField(OPERATEIP);
			Field operateTime = updateObject.getClass().getDeclaredField(OPERATETIME);
			operator.setAccessible(true);
			operateIp.setAccessible(true);
			operateTime.setAccessible(true);
			ReflectionUtils.setField(operator, updateObject, BusinessContextUtils.getSysUser() == null
				? DEFAULT_SYS_USER_NAME : BusinessContextUtils.getSysUser().getUsername());
			ReflectionUtils.setField(operateIp, updateObject, IpUtil.getRemoteIp(BusinessContextUtils.getRequest()));
			ReflectionUtils.setField(operateTime, updateObject, new Date());
			//反射执行方法
			performMethod(iocObject, methodName, updateObject);
		} catch (NoSuchFieldException e) {
			log.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		/** End */

		/** Start 还原记录 */
		SysLogVO sysLogVO = new SysLogVO();
		sysLogVO.setTargetId(sysLog.getTargetId());
		sysLogVO.setNewValue(sysLog.getOldValue());
		sysLogVO.setOldValue(sysLog.getNewValue());
		sysLogVO.setStatus(YES_1.getCode());
		sysLogVO.setType(sysLogType.getCode());
		saveSyslog(sysLogVO);
		/** End */
	}

	/**
	 * @methodName: performMethod
	 * @author: wx
	 * @description: 反射执行方法
	 * @param iocObject mapper对象
	 * @param methodName 方法名
	 * @param arg 参数
	 * @date: 2018/9/24
	 * @return: java.lang.Object
	 */
	private Object performMethod(Object iocObject, String methodName, Object arg) {
		/** Start 根据方法名获取指定方法，并执行该方法 */
		Method method = ReflectionUtils.findMethod(iocObject.getClass(), methodName, arg.getClass());
		method.setAccessible(true);
		Object result = ReflectionUtils.invokeMethod(method, iocObject, arg);
		/** End */
		return result;
	}
}
