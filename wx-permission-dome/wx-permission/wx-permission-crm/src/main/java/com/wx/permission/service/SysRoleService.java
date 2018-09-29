package com.wx.permission.service;

import com.google.common.collect.Lists;
import com.wx.commons.utils.BusinessValidatorUtils;
import com.wx.commons.utils.ConverterUtils;
import com.wx.commons.utils.IpUtil;
import com.wx.commons.utils.JsonUtils;
import com.wx.core.response.JsonData;
import com.wx.core.validator.BeanValidatorUtils;
import com.wx.core.validator.groups.Save;
import com.wx.core.validator.groups.Update;
import com.wx.permission.dao.SysRoleAclMapper;
import com.wx.permission.dao.SysRoleMapper;
import com.wx.permission.dao.SysRoleUserMapper;
import com.wx.permission.dao.SysUserMapper;
import com.wx.permission.dto.SysRoleDto;
import com.wx.permission.model.SysRole;
import com.wx.permission.model.SysUser;
import com.wx.permission.utils.BusinessContextUtils;
import com.wx.permission.vo.SysLogVO;
import com.wx.permission.vo.SysRoleVO;
import com.wx.permission.vo.SysUserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.wx.commons.constant.CommonConstant.BusinessOperation.ADD_SUCCESS;
import static com.wx.commons.constant.CommonConstant.BusinessOperation.UPDATE_SUCCESS;
import static com.wx.core.constant.SystemConstant.SysDefaultConfigName.DEFAULT_SYS_USER_NAME;
import static com.wx.core.enums.status.SystemEnumStatus.YesOrNo.NO_0;
import static com.wx.permission.enums.PermissionEnumError.SysRoleEnumError.SYS_ROLE_ENUM_ERROR_0;
import static com.wx.permission.enums.PermissionEnumError.SysRoleEnumError.SYS_ROLE_ENUM_ERROR_1;


/**
 * @ClassName SysRoleService
 * @Author wx
 * @Description 角色业务层
 * @Date 2018-09-05-22:11
 */
@Service
public class SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    private SysRoleAclMapper sysRoleAclMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * @param sysRoleDto
     * @methodName: saveSysRole
     * @author: wx
     * @description: 新增角色
     * @date: 2018/9/5
     * @return: JsonData
     */
    public JsonData saveSysRole(SysRoleDto sysRoleDto) {
        // DTO验证
        BeanValidatorUtils.validateObjectParam(sysRoleDto, Save.class);

        /** Star 角色名验证 */
        int count = sysRoleMapper.getByNameCount(sysRoleDto.getName());
        BusinessValidatorUtils.isFalse(count > 0, SYS_ROLE_ENUM_ERROR_1.getMessageError());
        /** End */

        /** Start 添加角色 */
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(sysRoleDto, sysRole);
        sysRole.setOperator(BusinessContextUtils.getSysUser() == null ? DEFAULT_SYS_USER_NAME : BusinessContextUtils.getSysUser().getUsername());
        sysRole.setOperateTime(new Date());
        sysRole.setOperateIp(IpUtil.getRemoteIp(BusinessContextUtils.getRequest()));
        sysRole.setId(null);
        sysRoleMapper.insertSelective(sysRole);
        /** End */

        return JsonData.ResultMsgSuccess(ADD_SUCCESS, new SysLogVO(sysRole.getId(), null, JsonUtils.toJson(sysRole), NO_0.getCode()));
    }

    /**
     * @param sysRoleDto
     * @methodName: updateSysRole
     * @author: wx
     * @description: 更新角色
     * @date: 2018/9/5
     * @return: JsonData
     */
    public JsonData updateSysRole(SysRoleDto sysRoleDto) {
        // DTO验证
        BeanValidatorUtils.validateObjectParam(sysRoleDto, Update.class);

        /** Start 验证角色是否存在 */
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(sysRoleDto.getId());
        BusinessValidatorUtils.notNull(sysRole, SYS_ROLE_ENUM_ERROR_0.getMessageError());
        /** End */

        /** Start 验证名字是否重复 */
        if (!Objects.equals(sysRole.getName(), sysRoleDto.getName())) {
            BusinessValidatorUtils.isFalse(sysRoleMapper.getByNameCount(sysRoleDto.getName()) > 0, SYS_ROLE_ENUM_ERROR_1.getMessageError());
        }
        /** End */

        /** Start 更新角色 */
        SysRole updateSysRole = new SysRole();
        BeanUtils.copyProperties(sysRoleDto, updateSysRole);
        updateSysRole.setOperator(BusinessContextUtils.getSysUser() == null ? DEFAULT_SYS_USER_NAME : BusinessContextUtils.getSysUser().getUsername());
        updateSysRole.setOperateIp(IpUtil.getRemoteIp(BusinessContextUtils.getRequest()));
        updateSysRole.setOperateTime(new Date());
        sysRoleMapper.updateByPrimaryKeySelective(updateSysRole);
        /** End */

        return JsonData.ResultMsgSuccess(UPDATE_SUCCESS, new SysLogVO(updateSysRole.getId(), JsonUtils.toJson(sysRole), JsonUtils.toJson(updateSysRole), NO_0.getCode()));
    }

    /**
     * @param
     * @methodName: getAll
     * @author: wx
     * @description: 获取所有角色信息
     * @date: 2018/9/5
     * @return: java.util.List<com.wx.mypermission.model.SysRole>
     */
    public List<SysRoleVO> getAll() {
        List<SysRole> roleList = sysRoleMapper.getAll();
        if (CollectionUtils.isEmpty(roleList)) {
            return Lists.newArrayList();
        }
        return ConverterUtils.listDateConvert(SysRoleVO.class, roleList);
    }

    /**
     * @param userId 用户id
     * @methodName: getRoleListByUserId
     * @author: wx
     * @description: 根据用户id获取角色信息
     * @date: 2018/9/9
     * @return: java.util.List<com.wx.mypermission.model.SysRole>
     */
    public List<SysRoleVO> getRoleListByUserId(Integer userId) {
        List<Integer> roleIdList = sysRoleUserMapper.getRoleIdListByUserId(userId);
        if (CollectionUtils.isEmpty(roleIdList)) {
            return Lists.newArrayList();
        }

        List<SysRole> roleList = sysRoleMapper.getByIdList(roleIdList);
        if (CollectionUtils.isEmpty(roleList)) {
            return Lists.newArrayList();
        }
        return ConverterUtils.listDateConvert(SysRoleVO.class, roleList);
    }

    /**
     * @param aclId
     * @methodName: getRoleListByAclId
     * @author: wx
     * @description: 根据权限获取角色信息
     * @date: 2018/9/9
     * @return: java.util.List<com.wx.mypermission.vo.SysRoleVO>
     */
    public List<SysRoleVO> getRoleListByAclId(int aclId) {
        List<Integer> roleIdList = sysRoleAclMapper.getRoleIdListByAclId(aclId);
        if (CollectionUtils.isEmpty(roleIdList)) {
            return Lists.newArrayList();
        }
        List<SysRole> roleList = sysRoleMapper.getByIdList(roleIdList);
        if (CollectionUtils.isEmpty(roleIdList)) {
            return Lists.newArrayList();
        }
        return ConverterUtils.listDateConvert(SysRoleVO.class, roleList);
    }

    /**
     * @param roleList 角色集合id
     * @methodName: getUserListByRoleList
     * @author: wx
     * @description: 根据角色集合id查询用户信息
     * @date: 2018/9/9
     * @return: java.util.List<com.wx.mypermission.vo.SysUserVO>
     */
    public List<SysUserVO> getUserListByRoleList(List<Integer> roleList) {
        List<Integer> userIdList = sysRoleUserMapper.getUserIdListByRoleIdList(roleList);
        if (CollectionUtils.isEmpty(userIdList)) {
            return Lists.newArrayList();
        }
        List<SysUser> sysUserList = sysUserMapper.getByIdList(userIdList);
        if (CollectionUtils.isEmpty(sysUserList)) {
            return Lists.newArrayList();
        }
        return ConverterUtils.listDateConvert(SysUserVO.class, sysUserList);

    }
}
