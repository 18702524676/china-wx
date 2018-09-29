package com.wx.permission.service;

import com.google.common.collect.Lists;
import com.wx.commons.tools.Page;
import com.wx.commons.utils.*;
import com.wx.core.response.JsonData;
import com.wx.core.validator.BeanValidatorUtils;
import com.wx.core.validator.groups.Save;
import com.wx.core.validator.groups.Update;
import com.wx.permission.dao.SysDeptMapper;
import com.wx.permission.dao.SysUserMapper;
import com.wx.permission.dto.SysUserDto;
import com.wx.permission.model.SysUser;
import com.wx.permission.qo.SysUserQO;
import com.wx.permission.utils.BusinessContextUtils;
import com.wx.permission.vo.SysLogVO;
import com.wx.permission.vo.SysUserVO;
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
import static com.wx.permission.enums.PermissionEnumError.SysDeptEnumError.SYS_DEPT_ENUM_ERROR_0;
import static com.wx.permission.enums.PermissionEnumError.SysUserEnumError.*;

/**
 * @ClassName SysUserService
 * @Author wx
 * @Description 系统用户业务层
 * @Date 2018-08-26-20:48
 */
@Service
public class SysUserService {
	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysDeptMapper sysDeptMapper;

	/**
	 * @methodName: saveUser
	 * @author: wx
	 * @description: 添加用户新
	 * @param sysUserDto
	 * @date: 2018/8/26
	 * @return: JsonData
	 */
	public JsonData saveUser(SysUserDto sysUserDto) {
		// dto验证
		BeanValidatorUtils.validateObjectParam(sysUserDto, Save.class);

		/** Start 验证部门是否存在 */
		BusinessValidatorUtils.notNull(sysDeptMapper.selectByPrimaryKey(sysUserDto.getDeptId()),
			SYS_DEPT_ENUM_ERROR_0.getMessageError());
		/** End */

		/** Start 验证邮箱and手机号是否被使用 */
		int mailCount = sysUserMapper.countByMail(sysUserDto.getMail());
		BusinessValidatorUtils.isTure(mailCount == 0, SYS_USER_ENUM_ERROR_1.getMessageError());
		int telephoneCount = sysUserMapper.countByTelephone(sysUserDto.getTelephone());
		BusinessValidatorUtils.isTure(telephoneCount == 0, SYS_USER_ENUM_ERROR_2.getMessageError());
		/** End */

		/** Start 添加用户信息 */
		sysUserDto.setPassword(MD5Util.encrypt(sysUserDto.getPassword()));
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(sysUserDto, sysUser);
		sysUser.setOperator(BusinessContextUtils.getSysUser() == null ? DEFAULT_SYS_USER_NAME
			: BusinessContextUtils.getSysUser().getUsername());
		sysUser.setOperateIp(IpUtil.getRemoteIp(BusinessContextUtils.getRequest()));
		sysUser.setOperateTime(new Date());
		sysUser.setId(null);
		sysUserMapper.insertSelective(sysUser);
		/** End */

		return JsonData.ResultMsgSuccess(ADD_SUCCESS,
			new SysLogVO(sysUser.getId(), null, JsonUtils.toJson(sysUser), NO_0.getCode()));
	}

	/**
	 * @methodName: updateUser
	 * @author: wx
	 * @description: 更新用户信息
	 * @param sysUserDto
	 * @date: 2018/8/26
	 * @return: JsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public JsonData updateUser(SysUserDto sysUserDto) {
		// dto验证
		BeanValidatorUtils.validateObjectParam(sysUserDto, Update.class);

		/** Start 验证用户是否存在 */
		SysUser sysUser = sysUserMapper.selectByPrimaryKey(sysUserDto.getId());
		BusinessValidatorUtils.notNull(sysUser, SYS_USER_ENUM_ERROR_0.getMessageError());
		/** End */

		/** Start 验证邮箱and手机号是否被使用 */
		if (!Objects.equals(sysUser.getMail(), sysUserDto.getMail())) {
			int mailCount = sysUserMapper.countByMail(sysUserDto.getMail());
			BusinessValidatorUtils.isTure(mailCount == 0, SYS_USER_ENUM_ERROR_1.getMessageError());
		}
		if (!Objects.equals(sysUser.getTelephone(), sysUserDto.getTelephone())) {
			int telephoneCount = sysUserMapper.countByTelephone(sysUserDto.getTelephone());
			BusinessValidatorUtils.isTure(telephoneCount == 0, SYS_USER_ENUM_ERROR_2.getMessageError());
		}
		/** End */

		/** Start 更新用户信息 */
		SysUser updateSysUser = new SysUser();
		BeanUtils.copyProperties(sysUserDto, updateSysUser);
		updateSysUser.setOperator(BusinessContextUtils.getSysUser() == null ? DEFAULT_SYS_USER_NAME
			: BusinessContextUtils.getSysUser().getUsername());
		updateSysUser.setOperateIp(IpUtil.getRemoteIp(BusinessContextUtils.getRequest()));
		updateSysUser.setOperateTime(new Date());
		sysUserMapper.updateByPrimaryKeySelective(updateSysUser);
		/** End */

		return JsonData.ResultMsgSuccess(UPDATE_SUCCESS, new SysLogVO(updateSysUser.getId(), JsonUtils.toJson(sysUser),
			JsonUtils.toJson(updateSysUser), NO_0.getCode()));

	}

	/**
	 * @methodName: findByKeyword
	 * @author: wx
	 * @description: 查询用户是否存在
	 * @param keyword 邮箱or手机号
	 * @date: 2018/8/28
	 * @return: com.wx.mypermission.model.SysUser
	 */
	public SysUser findByKeyword(String keyword) {
		return sysUserMapper.findByKeyword(keyword);
	}

	/**
	 * @methodName: getPage
	 * @author: wx
	 * @description: 分页查询系统用户
	 * @param sysUserQO
	 * @date: 2018/8/31
	 * @return: com.wx.mypermission.common.tools.Page<com.wx.mypermission.vo.SysUserVO>
	 */
	public Page<SysUserVO> getPage(SysUserQO sysUserQO) {
		Page<SysUserVO> page = new Page();
		if (sysUserQO == null) {
			return page;
		}
		int count = sysUserMapper.countByCondition(sysUserQO);
		if (count == 0) {
			return page;
		}
		List<SysUser> sysUserList = sysUserMapper.getPage(sysUserQO);
		if (CollectionUtils.isEmpty(sysUserList)) {
			return page;
		}

		BeanUtils.copyProperties(sysUserQO, page);
		page.setTotal(count);
		page.setData(ConverterUtils.listDateConvert(SysUserVO.class, sysUserList));
		return page;
	}

	/**
	 * @methodName: getAll
	 * @author: wx
	 * @description: 获取所有用户信息
	 * @param
	 * @date: 2018/9/9
	 * @return: java.util.List<com.wx.mypermission.vo.SysUserVO>
	 */
	public List<SysUserVO> getAll() {
		List<SysUser> userList = sysUserMapper.getAll();
		if (CollectionUtils.isEmpty(userList)) {
			return Lists.newArrayList();
		}
		return ConverterUtils.listDateConvert(SysUserVO.class, userList);
	}

	/**
	 * @methodName: getByIdList
	 * @author: wx
	 * @description: 根据用户id集合查询用户信息
	 * @param userIdList 用户id集合
	 * @date: 2018/9/9
	 * @return: java.util.List<com.wx.mypermission.model.SysUserVO>
	 */
	public List<SysUserVO> getByIdList(List<Integer> userIdList) {
		List<SysUser> sysUserList = sysUserMapper.getByIdList(userIdList);
		if (CollectionUtils.isEmpty(sysUserList)) {
			return Lists.newArrayList();
		}
		return ConverterUtils.listDateConvert(SysUserVO.class, sysUserList);
	}
	
	
	/**
	 * @methodName: getSysUserById
	 * @author: wx
	 * @description: 获取用户信息（内部使用）
	 * @param userId
	 * @date: 2018/9/24
	 * @return: com.wx.mypermission.model.SysUser
	 */
	public SysUser getSysUserById(int userId){
		SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
		BusinessValidatorUtils.notNull(sysUser,SYS_USER_ENUM_ERROR_0.getMessageError());
		return sysUser;
	}

	/**
	 * @methodName: getSysUserVOById
	 * @author: wx
	 * @description: 获取用户信息VO
	 * @param userId
	 * @date: 2018/9/24
	 * @return: com.wx.mypermission.model.SysUser
	 */
	public SysUserVO getSysUserVOById(int userId){
		SysUser sysUser = getSysUserById(userId);
		SysUserVO sysUserVO = new SysUserVO();
		BeanUtils.copyProperties(sysUser,sysUserVO);
		return sysUserVO;
	}
}
