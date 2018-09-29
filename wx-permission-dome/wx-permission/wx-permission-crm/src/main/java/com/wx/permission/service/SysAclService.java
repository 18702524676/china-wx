package com.wx.permission.service;


import com.wx.commons.tools.Page;
import com.wx.commons.utils.BusinessValidatorUtils;
import com.wx.commons.utils.ConverterUtils;
import com.wx.commons.utils.IpUtil;
import com.wx.commons.utils.JsonUtils;
import com.wx.core.response.JsonData;
import com.wx.core.validator.BeanValidatorUtils;
import com.wx.core.validator.groups.Save;
import com.wx.core.validator.groups.Update;
import com.wx.permission.dao.SysAclMapper;
import com.wx.permission.dao.SysAclModuleMapper;
import com.wx.permission.dto.SysAclDto;
import com.wx.permission.model.SysAcl;
import com.wx.permission.model.SysAclModule;
import com.wx.permission.qo.SysAclQO;
import com.wx.permission.utils.BusinessContextUtils;
import com.wx.permission.vo.SysAclVO;
import com.wx.permission.vo.SysLogVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.wx.commons.constant.CommonConstant.BusinessOperation.ADD_SUCCESS;
import static com.wx.commons.constant.CommonConstant.BusinessOperation.UPDATE_SUCCESS;
import static com.wx.core.constant.SystemConstant.SysDefaultConfigName.DEFAULT_SYS_USER_NAME;
import static com.wx.core.enums.status.SystemEnumStatus.YesOrNo.NO_0;
import static com.wx.permission.enums.PermissionEnumError.SysAclEnumError.SYS_ACL_ENUM_ERROR_0;
import static com.wx.permission.enums.PermissionEnumError.SysAclEnumError.SYS_ACL_ENUM_ERROR_1;
import static com.wx.permission.enums.PermissionEnumError.SysAclModuleEnumError.SYS_ACL_MODULE_ENUM_ERROR_0;


/**
 * @ClassName SysAclService
 * @Author wx
 * @Description 权限业务层
 * @Date 2018-09-04-21:53
 */
@Service
public class SysAclService {
	@Autowired
	private SysAclMapper sysAclMapper;

	@Autowired
	private SysAclModuleMapper sysAclModuleMapper;

	/**
	 * @methodName: saveSysAcl
	 * @author: wx
	 * @description: 新增权限
	 * @param sysAclDto
	 * @date: 2018/9/4
	 * @return: JsonData
	 */
	public JsonData saveSysAcl(SysAclDto sysAclDto) {
		// 验证dto
		BeanValidatorUtils.validateObjectParam(sysAclDto, Save.class);

		/** Start 验证名字是否重复 */
		SysAclQO sysAclQO = new SysAclQO();
		sysAclQO.setAclModuleId(sysAclDto.getAclModuleId());
		sysAclQO.setName(sysAclDto.getName());
		int count = sysAclMapper.countByCondition(sysAclQO);
		BusinessValidatorUtils.isFalse(count > 0, SYS_ACL_ENUM_ERROR_1.getMessageError());
		/** End */

		/** Start 验证所选权限模块是否存在 */
		SysAclModule sysAclModule = sysAclModuleMapper.selectByPrimaryKey(sysAclDto.getAclModuleId());
		BusinessValidatorUtils.notNull(sysAclModule, SYS_ACL_MODULE_ENUM_ERROR_0.getMessageError());
		/** End */

		/** Start 添加权限 */
		SysAcl sysAcl = new SysAcl();
		BeanUtils.copyProperties(sysAclDto, sysAcl);
		sysAcl.setCode(generateCode());
		sysAcl.setOperator(BusinessContextUtils.getSysUser() == null ? DEFAULT_SYS_USER_NAME
			: BusinessContextUtils.getSysUser().getUsername());
		sysAcl.setOperateTime(new Date());
		sysAcl.setOperateIp(IpUtil.getRemoteIp(BusinessContextUtils.getRequest()));
		sysAcl.setId(null);
		sysAclMapper.insertSelective(sysAcl);
		/** End */
		return JsonData.ResultMsgSuccess(ADD_SUCCESS,
			new SysLogVO(sysAcl.getId(), null, JsonUtils.toJson(sysAcl), NO_0.getCode()));
	}

	/**
	 * @methodName: updateSysAcl
	 * @author: wx
	 * @description: 更新权限
	 * @param sysAclDto
	 * @date: 2018/9/4
	 * @return: JsonData
	 */
	public JsonData updateSysAcl(SysAclDto sysAclDto) {
		// 验证dto
		BeanValidatorUtils.validateObjectParam(sysAclDto, Update.class);

		/** Start 验证权限是否存在 */
		SysAcl sysAcl = sysAclMapper.selectByPrimaryKey(sysAclDto.getId());
		BusinessValidatorUtils.notNull(sysAcl, SYS_ACL_ENUM_ERROR_0.getMessageError());
		/** End */

		/** Start 验证名字是否重复 */
		if (!Objects.equals(sysAcl.getName(), sysAclDto.getName())) {
			SysAclQO sysAclQO = new SysAclQO();
			sysAclQO.setAclModuleId(sysAclDto.getAclModuleId());
			sysAclQO.setName(sysAclDto.getName());
			BusinessValidatorUtils.isFalse(sysAclMapper.countByCondition(sysAclQO) > 0,
				SYS_ACL_ENUM_ERROR_1.getMessageError());
		}
		/** End */

		/** Start 验证所选权限模块是否存在 */
		SysAclModule sysAclModule = sysAclModuleMapper.selectByPrimaryKey(sysAclDto.getAclModuleId());
		BusinessValidatorUtils.notNull(sysAclModule, SYS_ACL_MODULE_ENUM_ERROR_0.getMessageError());
		/** End */

		/** Start 修改权限 */
		SysAcl updateSysAcl = new SysAcl();
		BeanUtils.copyProperties(sysAclDto, updateSysAcl);
		updateSysAcl.setOperator(BusinessContextUtils.getSysUser() == null ? DEFAULT_SYS_USER_NAME
			: BusinessContextUtils.getSysUser().getUsername());
		updateSysAcl.setOperateTime(new Date());
		updateSysAcl.setOperateIp(IpUtil.getRemoteIp(BusinessContextUtils.getRequest()));
		sysAclMapper.updateByPrimaryKeySelective(updateSysAcl);
		/** End */

		return JsonData.ResultMsgSuccess(UPDATE_SUCCESS, new SysLogVO(updateSysAcl.getId(), JsonUtils.toJson(sysAcl),
			JsonUtils.toJson(updateSysAcl), NO_0.getCode()));
	}

	/**
	 * @methodName: pageSysAcl
	 * @author: wx
	 * @description: 权限分页查询
	 * @param sysAclQO
	 * @date: 2018/9/4
	 * @return: com.wx.mypermission.common.tools.Page<com.wx.mypermission.vo.SysAclVO>
	 */
	public Page<SysAclVO> pageSysAcl(SysAclQO sysAclQO) {
		Page<SysAclVO> page = new Page();
		if (sysAclQO == null) {
			return page;
		}
		int count = sysAclMapper.countByCondition(sysAclQO);
		if (count == 0) {
			return page;
		}
		List<SysAcl> sysAclList = sysAclMapper.getPage(sysAclQO);
		if (CollectionUtils.isEmpty(sysAclList)) {
			return page;
		}
		BeanUtils.copyProperties(sysAclQO, page);
		page.setTotal(count);
		page.setData(ConverterUtils.listDateConvert(SysAclVO.class, sysAclList));
		return page;
	}

	/**
	 * @methodName: generateCode
	 * @author: wx
	 * @description: 生成权限码
	 * @param
	 * @date: 2018/9/4
	 * @return: java.lang.String
	 */
	private String generateCode() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return dateFormat.format(new Date()) + "_" + (int) (Math.random() * 100);
	}
}
