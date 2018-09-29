package com.wx.permission.service;


import com.wx.commons.utils.BusinessValidatorUtils;
import com.wx.commons.utils.IpUtil;
import com.wx.commons.utils.JsonUtils;
import com.wx.core.response.JsonData;
import com.wx.core.validator.BeanValidatorUtils;
import com.wx.core.validator.groups.Save;
import com.wx.core.validator.groups.Update;
import com.wx.permission.dao.SysDeptMapper;
import com.wx.permission.dao.SysUserMapper;
import com.wx.permission.dto.SysDeptDto;
import com.wx.permission.model.SysDept;
import com.wx.permission.qo.SysDeptQO;
import com.wx.permission.qo.SysUserQO;
import com.wx.permission.utils.BusinessContextUtils;
import com.wx.permission.utils.TreeUtils;
import com.wx.permission.vo.SysDeptTreeVO;
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
import static com.wx.permission.enums.PermissionEnumError.SysDeptEnumError.*;


/**
 * @ClassName SysDeptService
 * @Author wx
 * @Description 部门业务层
 * @Date 2018-08-19-18:17
 */
@Service
public class SysDeptService {
	@Autowired
	private SysDeptMapper sysDeptMapper;

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysTreeService sysTreeService;

	/**
	 * @methodName: saveDept
	 * @author: wx
	 * @description: 新增部门
	 * @param sysDeptDto 部门dto
	 * @date: 2018/8/19
	 * @return: JsonData
	 */
	public JsonData saveDept(SysDeptDto sysDeptDto) {
		// 验证DTO参数
		BeanValidatorUtils.validateObjectParam(sysDeptDto, Save.class);

		/** 验证当前名称是否存在 */
		SysDeptQO sysDeptQO = new SysDeptQO();
		sysDeptQO.setName(sysDeptDto.getName());
		sysDeptQO.setParentId(sysDeptDto.getParentId());
		BusinessValidatorUtils.isNull(sysDeptMapper.getByCondition(sysDeptQO), SYS_DEPT_ENUM_ERROR_1.getMessageError());
		/** End */

		/** Start 添加部门 */
		SysDept sysDept = new SysDept();
		BeanUtils.copyProperties(sysDeptDto, sysDept);
		int parentId = sysDept.getParentId();
		sysDept.setLevel(TreeUtils.generateLevel(getParentLevel(parentId), parentId));
		sysDept.setOperator(BusinessContextUtils.getSysUser() == null ? DEFAULT_SYS_USER_NAME
			: BusinessContextUtils.getSysUser().getUsername());
		sysDept.setOperateIp(IpUtil.getRemoteIp(BusinessContextUtils.getRequest()));
		sysDept.setOperateTime(new Date());
		sysDept.setId(null);
		sysDeptMapper.insertSelective(sysDept);
		/** End */

		return JsonData.ResultMsgSuccess(ADD_SUCCESS,
			new SysLogVO(sysDept.getId(), null, JsonUtils.toJson(sysDept), NO_0.getCode()));
	}

	/**
	 * @methodName: updateDept
	 * @author: wx
	 * @description: 更新部门
	 * @param sysDeptDto 对象dto
	 * @date: 2018/8/22
	 * @return: JsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { RuntimeException.class, Exception.class })
	public JsonData updateDept(SysDeptDto sysDeptDto) {
		// 验证DTO参数
		BeanValidatorUtils.validateObjectParam(sysDeptDto, Update.class);
		// 获取部门id
		int deptId = sysDeptDto.getId();
		// 验证选择的上级id不能与当前部门id相同
		BusinessValidatorUtils.isFalse(Objects.equals(sysDeptDto.getParentId(), sysDeptDto.getId()),
			SYS_DEPT_ENUM_ERROR_2.getMessageError());
		/** Start 查询部门信息，并验证是否存在 */
		SysDept sysDept = sysDeptMapper.selectByPrimaryKey(deptId);
		BusinessValidatorUtils.notNull(sysDept, SYS_DEPT_ENUM_ERROR_1.getMessageError());
		/** End */

		// 待修改名称
		String updateName = sysDeptDto.getName();
		// 待修改的父级id
		int parentId = sysDeptDto.getParentId();

		/** Start 验证父节点是否是该部门的子节点 */
		if (!Objects.equals(parentId, Integer.valueOf(ROOT))) {
			SysDept parentSysDept = sysDeptMapper.selectByPrimaryKey(parentId);
			BusinessValidatorUtils.isFalse(parentSysDept.getLevel().contains(String.valueOf(sysDept.getId())),
				SYS_DEPT_ENUM_ERROR_5.getMessageError());
		}
		/** End */

		/** Start 验证名字是否重复 */
		if (!Objects.equals(updateName, sysDept.getName())) {
			SysDeptQO sysDeptQO = new SysDeptQO();
			sysDeptQO.setName(updateName);
			sysDeptQO.setParentId(parentId);
			BusinessValidatorUtils.isNull(sysDeptMapper.getByCondition(sysDeptQO),
				SYS_DEPT_ENUM_ERROR_1.getMessageError());
		}
		/** End */

		// 修改前层级
		String beforeLevel = sysDept.getLevel();
		// 修改后层级
		String afterLevel = TreeUtils.generateLevel(getParentLevel(parentId), parentId);

		/** Start 验证是否需要更新所有下级部门 */
		if (!Objects.equals(parentId, sysDept.getParentId())) {
			// 获取修改前层级下的所有部门
			List<SysDept> deptList = sysDeptMapper
				.getChildDeptListByLevel(StringUtils.join(beforeLevel, SEGMENTATION, deptId));

			if (!CollectionUtils.isEmpty(deptList)) {
				deptList.forEach(newSysDept -> {
					newSysDept.setLevel(
						TreeUtils.distinctLevel(afterLevel + newSysDept.getLevel().substring(beforeLevel.length())));
				});
				// 批量更新所有下级部门
				sysDeptMapper.batchUpdateDeptLevel(deptList);
			}
		}
		/** End */

		/** Start 更新部门 */
		SysDept updateSysDept = new SysDept();
		BeanUtils.copyProperties(sysDeptDto, updateSysDept);
		updateSysDept.setLevel(afterLevel);
		updateSysDept.setOperator(BusinessContextUtils.getSysUser() == null ? DEFAULT_SYS_USER_NAME
			: BusinessContextUtils.getSysUser().getUsername());
		updateSysDept.setOperateIp(IpUtil.getRemoteIp(BusinessContextUtils.getRequest()));
		updateSysDept.setOperateTime(new Date());
		sysDeptMapper.updateByPrimaryKeySelective(updateSysDept);
		/** End */

		return JsonData.ResultMsgSuccess(UPDATE_SUCCESS, new SysLogVO(updateSysDept.getId(), JsonUtils.toJson(sysDept),
			JsonUtils.toJson(updateSysDept), NO_0.getCode()));
	}

	/**
	 * @methodName: getDeptTree
	 * @author: wx
	 * @description: 获取部门Tree结构
	 * @param
	 * @date: 2018/8/21
	 * @return: java.util.List<com.wx.mypermission.vo.SysDeptTreeVO>
	 */
	public List<SysDeptTreeVO> getDeptTree() {
		return sysTreeService.getSysDeptTree();
	}

	/**
	 * @methodName: delete
	 * @author: wx
	 * @description: 根据部门id删除部门
	 * @param deptId 部门id
	 * @date: 2018/9/9
	 * @return: void
	 */
	public void delete(int deptId) {
		/** Start 删除前置验证 */
		SysDept sysDept = sysDeptMapper.selectByPrimaryKey(deptId);
		BusinessValidatorUtils.notNull(sysDept, SYS_DEPT_ENUM_ERROR_0.getMessageError());

		BusinessValidatorUtils.isFalse(sysDeptMapper.countByParentId(deptId) > 0,
			SYS_DEPT_ENUM_ERROR_3.getMessageError());

		SysUserQO sysUserQO = new SysUserQO();
		sysUserQO.setDeptId(deptId);
		BusinessValidatorUtils.isFalse(sysUserMapper.countByCondition(sysUserQO) > 0,
			SYS_DEPT_ENUM_ERROR_4.getMessageError());
		/** End */
		// 执行删除
		sysDeptMapper.deleteByPrimaryKey(deptId);
	}

	/**
	 * @methodName: getParentLevel
	 * @author: wx
	 * @description: 获取父部门的Level
	 * @param deptId 部门id
	 * @date: 2018/8/19
	 * @return: java.lang.String
	 */
	private String getParentLevel(int deptId) {
		SysDept sysDept = sysDeptMapper.selectByPrimaryKey(deptId);
		if (sysDept == null || StringUtils.isBlank(sysDept.getLevel())) {
			return null;
		} else {
			return sysDept.getLevel();
		}

	}

}
