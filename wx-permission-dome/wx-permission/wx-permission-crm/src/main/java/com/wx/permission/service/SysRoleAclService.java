package com.wx.permission.service;

import com.google.common.collect.Lists;
import com.wx.commons.utils.CompareUtils;
import com.wx.commons.utils.IpUtil;
import com.wx.commons.utils.JsonUtils;
import com.wx.core.response.JsonData;
import com.wx.permission.dao.SysRoleAclMapper;
import com.wx.permission.dto.SysRoleAclDto;
import com.wx.permission.model.SysRoleAcl;
import com.wx.permission.utils.BusinessContextUtils;
import com.wx.permission.vo.SysLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

import static com.wx.commons.constant.CommonConstant.BusinessOperation.DELETE_SUCCESS;
import static com.wx.commons.constant.CommonConstant.BusinessOperation.UPDATE_SUCCESS;
import static com.wx.core.constant.SystemConstant.SysDefaultConfigName.DEFAULT_SYS_USER_NAME;
import static com.wx.core.enums.status.SystemEnumStatus.YesOrNo.NO_0;


/**
 * @ClassName SysRoleAclService
 * @Author wx
 * @Description 角色权限关系业务层
 * @Date 2018-09-08-23:43
 */
@Service
public class SysRoleAclService {
	@Autowired
	private SysRoleAclMapper sysRoleAclMapper;

	/**
	 * @methodName: changeRoleAcls
	 * @author: wx
	 * @description: 新增or修改角色与权限的关系
	 * @param roleId 角色id
	 * @param aclIdList 权限集合id
	 * @date: 2018/9/8
	 * @return: JsonData
	 */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class,Exception.class})
	public JsonData changeRoleAcls(int roleId, List<Integer> aclIdList) {
    	//获取角色对应的权限
		List<Integer> idListByRoleId = sysRoleAclMapper.getAclIdListByRoleId(roleId);
    	if (CollectionUtils.isEmpty(aclIdList)){
			// 删除原权限数据
			sysRoleAclMapper.deleteByRoleId(roleId);
			return JsonData.ResultMsgSuccess(DELETE_SUCCESS, new SysLogVO(roleId, JsonUtils.toJson(new SysRoleAclDto(roleId,idListByRoleId)),
					"", NO_0.getCode()));
		}
		/** Start 维护角色权限数据 */
		if (!CollectionUtils.isEmpty(idListByRoleId)) {
			if (CompareUtils.equalCollection(idListByRoleId,aclIdList)) {
				return JsonData.ResultMsgSuccess(UPDATE_SUCCESS, new SysLogVO(roleId, JsonUtils.toJson(new SysRoleAclDto(roleId,idListByRoleId)),
						JsonUtils.toJson(new SysRoleAclDto(roleId,idListByRoleId)), NO_0.getCode()));
			}
		}
		//角色权限批量更新方法
		batchSysRoleAclInsert(new SysRoleAclDto(roleId,aclIdList));
		/** End  */

		return JsonData.ResultMsgSuccess(UPDATE_SUCCESS, new SysLogVO(roleId, JsonUtils.toJson(new SysRoleAclDto(roleId,idListByRoleId)),
				JsonUtils.toJson(new SysRoleAclDto(roleId,aclIdList)), NO_0.getCode()));
	}

	
	/**
	 * @methodName: batchSysRoleAclInsert
	 * @author: wx
	 * @description: 角色权限批量更新
	 * @param sysRoleAclDto
	 * @date: 2018/9/24
	 * @return: void
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class,Exception.class})
	public   void  batchSysRoleAclInsert(SysRoleAclDto sysRoleAclDto){
		// 删除原权限数据
		int roleId = sysRoleAclDto.getRoleId();
		sysRoleAclMapper.deleteByRoleId(roleId);
		//新增新权限数据
		List<SysRoleAcl> roleAclList = Lists.newArrayList();
		sysRoleAclDto.getAclIdlist().forEach(aclId -> {
			SysRoleAcl sysRoleAcl = new SysRoleAcl();
			sysRoleAcl.setRoleId(roleId);
			sysRoleAcl.setAclId(aclId);
			sysRoleAcl.setOperator(BusinessContextUtils.getSysUser() == null ? DEFAULT_SYS_USER_NAME
					: BusinessContextUtils.getSysUser().getUsername());
			sysRoleAcl.setOperateIp(IpUtil.getRemoteIp(BusinessContextUtils.getRequest()));
			sysRoleAcl.setOperateTime(new Date());
			roleAclList.add(sysRoleAcl);
		});
		//批量新增
		sysRoleAclMapper.batchInsert(roleAclList);
	}

}
