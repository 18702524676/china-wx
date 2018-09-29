package com.wx.permission.service;


import com.wx.commons.utils.BusinessValidatorUtils;
import com.wx.commons.utils.IpUtil;
import com.wx.commons.utils.JsonUtils;
import com.wx.core.response.JsonData;
import com.wx.core.validator.BeanValidatorUtils;
import com.wx.core.validator.groups.Save;
import com.wx.core.validator.groups.Update;
import com.wx.permission.dao.SysAclMapper;
import com.wx.permission.dao.SysAclModuleMapper;
import com.wx.permission.dto.SysAclModuleDto;
import com.wx.permission.model.SysAclModule;
import com.wx.permission.qo.SysAclModuleQO;
import com.wx.permission.qo.SysAclQO;
import com.wx.permission.utils.BusinessContextUtils;
import com.wx.permission.utils.TreeUtils;
import com.wx.permission.vo.SysAclModuleTreeVO;
import com.wx.permission.vo.SysLogVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.wx.commons.constant.CommonConstant.BusinessOperation.ADD_SUCCESS;
import static com.wx.commons.constant.CommonConstant.BusinessOperation.UPDATE_SUCCESS;
import static com.wx.core.constant.SystemConstant.SysDefaultConfigName.DEFAULT_SYS_USER_NAME;
import static com.wx.core.enums.status.SystemEnumStatus.YesOrNo.NO_0;
import static com.wx.permission.constant.PermissionConstant.LevelRule.ROOT;
import static com.wx.permission.constant.PermissionConstant.LevelRule.SEGMENTATION;
import static com.wx.permission.enums.PermissionEnumError.SysAclModuleEnumError.*;


/**
 * @ClassName SysAclModuleService
 * @Author wx
 * @Description 权限模块业务层
 * @Date 2018-09-02-15:01
 */
@Service
public class SysAclModuleService {

	@Autowired
	private SysAclModuleMapper sysAclModuleMapper;

	@Autowired
	private SysAclMapper sysAclMapper;

	@Autowired
	private SysTreeService sysTreeService;

	/**
	 * @methodName: saveSysAclModule
	 * @author: wx
	 * @description: 新增权限模块
	 * @param sysAclModuleDto
	 * @date: 2018/9/2
	 * @return: JsonData
	 */
	public JsonData saveSysAclModule(SysAclModuleDto sysAclModuleDto) {
		// 验证DTO参数
		BeanValidatorUtils.validateObjectParam(sysAclModuleDto, Save.class);

		/** 验证当前名称是否存在 */
		SysAclModuleQO sysAclModuleQO = new SysAclModuleQO();
		sysAclModuleQO.setName(sysAclModuleDto.getName());
		sysAclModuleQO.setParentId(sysAclModuleDto.getParentId());
		BusinessValidatorUtils.isNull(sysAclModuleMapper.getByCondition(sysAclModuleQO),
			SYS_ACL_MODULE_ENUM_ERROR_1.getMessageError());
		/** End */

		/** Start 添加权限模块 */
		SysAclModule sysAclModule = new SysAclModule();
		BeanUtils.copyProperties(sysAclModuleDto, sysAclModule);
		int parentId = sysAclModule.getParentId();
		sysAclModule.setLevel(TreeUtils.generateLevel(getParentLevel(parentId), parentId));
		sysAclModule.setOperator(BusinessContextUtils.getSysUser() == null ? DEFAULT_SYS_USER_NAME
			: BusinessContextUtils.getSysUser().getUsername());
		sysAclModule.setOperateIp(IpUtil.getRemoteIp(BusinessContextUtils.getRequest()));
		sysAclModule.setOperateTime(new Date());
		sysAclModule.setId(null);
		sysAclModuleMapper.insertSelective(sysAclModule);
		/** End */
		return JsonData.ResultMsgSuccess(ADD_SUCCESS,
			new SysLogVO(sysAclModule.getId(), null, JsonUtils.toJson(sysAclModule), NO_0.getCode()));
	}

	/**
	 * @methodName: updateSysAclModule
	 * @author: wx
	 * @description: 更新权限模块
	 * @param sysAclModuleDto
	 * @date: 2018/9/2
	 * @return: JsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { RuntimeException.class, Exception.class })
	public JsonData updateSysAclModule(SysAclModuleDto sysAclModuleDto) {
		// 验证DTO参数
		BeanValidatorUtils.validateObjectParam(sysAclModuleDto, Update.class);
		// 获取权限模块id
		int aclModuleId = sysAclModuleDto.getId();
		// 验证选择的上级id不能与当前部门id相同
		BusinessValidatorUtils.isFalse(Objects.equals(sysAclModuleDto.getParentId(), sysAclModuleDto.getId()),
			SYS_ACL_MODULE_ENUM_ERROR_2.getMessageError());

		/** Start 查询权限模块信息，并验证是否存在 */
		SysAclModule sysAclModule = sysAclModuleMapper.selectByPrimaryKey(aclModuleId);
		BusinessValidatorUtils.notNull(sysAclModule, SYS_ACL_MODULE_ENUM_ERROR_0.getMessageError());
		/** End */

		// 待修改名称
		String updateName = sysAclModuleDto.getName();
		// 待修改的父级id
		int parentId = sysAclModuleDto.getParentId();

		/** Start 验证父节点是否是该模块的子节点 */
		if (!Objects.equals(parentId, Integer.valueOf(ROOT))) {
			SysAclModule parentSysAclModule = sysAclModuleMapper.selectByPrimaryKey(parentId);
			BusinessValidatorUtils.isFalse(parentSysAclModule.getLevel().contains(String.valueOf(sysAclModule.getId())),
				SYS_ACL_MODULE_ENUM_ERROR_5.getMessageError());
		}
		/** End */

		/** Start 验证名字是否重复 */
		if (!Objects.equals(updateName, sysAclModule.getName())) {
			SysAclModuleQO sysAclModuleQO = new SysAclModuleQO();
			sysAclModuleQO.setName(updateName);
			sysAclModuleQO.setParentId(parentId);
			BusinessValidatorUtils.isNull(sysAclModuleMapper.getByCondition(sysAclModuleQO),
				SYS_ACL_MODULE_ENUM_ERROR_1.getMessageError());
		}
		/** End */

		// 修改前层级
		String beforeLevel = sysAclModule.getLevel();
		// 修改后层级
		String afterLevel = TreeUtils.generateLevel(getParentLevel(parentId), parentId);

		/** Start 验证是否需要更新所有下级权限模块 */
		if (!Objects.equals(parentId, sysAclModule.getParentId())) {
			// 获取修改前层级下的所有权限模块
			List<SysAclModule> aclModuleList = sysAclModuleMapper
				.getChildAclModuleListByLevel(StringUtils.join(beforeLevel, SEGMENTATION, aclModuleId));

			if (!CollectionUtils.isEmpty(aclModuleList)) {
				aclModuleList.forEach(newAclModule -> {
					newAclModule.setLevel(
						TreeUtils.distinctLevel(afterLevel + newAclModule.getLevel().substring(beforeLevel.length())));
				});
				// 批量更新所有下级部门
				sysAclModuleMapper.batchUpdateAclModuleLevel(aclModuleList);
			}
		}
		/** End */

		/** Start 更新权限模块 */
		SysAclModule updateSysAclModule = new SysAclModule();
		BeanUtils.copyProperties(sysAclModuleDto, updateSysAclModule);
		updateSysAclModule.setLevel(afterLevel);
		updateSysAclModule.setOperator(BusinessContextUtils.getSysUser() == null ? DEFAULT_SYS_USER_NAME
			: BusinessContextUtils.getSysUser().getUsername());
		updateSysAclModule.setOperateIp(IpUtil.getRemoteIp(BusinessContextUtils.getRequest()));
		updateSysAclModule.setOperateTime(new Date());
		sysAclModuleMapper.updateByPrimaryKeySelective(updateSysAclModule);
		/** End */

		return JsonData.ResultMsgSuccess(UPDATE_SUCCESS, new SysLogVO(updateSysAclModule.getId(),
			JsonUtils.toJson(sysAclModule), JsonUtils.toJson(updateSysAclModule),NO_0.getCode()));
	}

	/**
	 * @methodName: getParentLevel
	 * @author: wx
	 * @description: 获取父权限模块的Level
	 * @param deptId 部门id
	 * @date: 2018/8/19
	 * @return: java.lang.String
	 */
	private String getParentLevel(int deptId) {
		SysAclModule sysAclModule = sysAclModuleMapper.selectByPrimaryKey(deptId);
		if (sysAclModule == null || StringUtils.isBlank(sysAclModule.getLevel())) {
			return null;
		} else {
			return sysAclModule.getLevel();
		}

	}

	/**
	 * @methodName: getAclModuleTree
	 * @author: wx
	 * @description: 获取权限模Tree结构
	 * @param
	 * @date: 2018/9/2
	 * @return: java.util.List<com.wx.mypermission.vo.SysAclModuleTreeVO>
	 */
	public List<SysAclModuleTreeVO> getAclModuleTree() {
		return sysTreeService.getSysAclModuleTree();
	}

	/**
	 * @methodName: delete
	 * @author: wx
	 * @description: 删除权限模块
	 * @param aclModuleId 权限模块id
	 * @date: 2018/9/9
	 * @return: void
	 */
	public void delete(int aclModuleId) {
		/** Start 删除前置验证 */
		SysAclModule sysAclModule = sysAclModuleMapper.selectByPrimaryKey(aclModuleId);
		BusinessValidatorUtils.notNull(sysAclModule, SYS_ACL_MODULE_ENUM_ERROR_0.getMessageError());

		BusinessValidatorUtils.isFalse(sysAclModuleMapper.countByParentId(aclModuleId) > 0,
			SYS_ACL_MODULE_ENUM_ERROR_3.getMessageError());

		SysAclQO sysAclQO = new SysAclQO();
		sysAclQO.setAclModuleId(aclModuleId);
		BusinessValidatorUtils.isFalse(sysAclMapper.countByCondition(sysAclQO) > 0,
			SYS_ACL_MODULE_ENUM_ERROR_4.getMessageError());
		/** End */
		// 执行删除
		sysAclModuleMapper.deleteByPrimaryKey(aclModuleId);
	}

}
