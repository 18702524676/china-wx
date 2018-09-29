package com.wx.permission.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wx.commons.utils.CompareUtils;
import com.wx.commons.utils.ConverterUtils;
import com.wx.commons.utils.IpUtil;
import com.wx.commons.utils.JsonUtils;
import com.wx.core.response.JsonData;
import com.wx.permission.dao.SysRoleUserMapper;
import com.wx.permission.dto.SysRoleUserDto;
import com.wx.permission.model.SysRoleUser;
import com.wx.permission.utils.BusinessContextUtils;
import com.wx.permission.vo.SysLogVO;
import com.wx.permission.vo.SysUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.wx.commons.constant.CommonConstant.BusinessOperation.DELETE_SUCCESS;
import static com.wx.commons.constant.CommonConstant.BusinessOperation.UPDATE_SUCCESS;
import static com.wx.core.constant.SystemConstant.SysDefaultConfigName.DEFAULT_SYS_USER_NAME;
import static com.wx.core.enums.status.SystemEnumStatus.SYS_STATUS.SYS_STATUS_1;
import static com.wx.core.enums.status.SystemEnumStatus.YesOrNo.NO_0;


/**
 * @ClassName SysRoleUserService
 * @Author wx
 * @Description 角色用户业务层
 * @Date 2018-09-09-14:22
 */
@Service
public class SysRoleUserService {
    // 角色绑定的用户列表
    final String SELECTED = "selected";

    // 角色未绑定的用户列表
    final String UN_SELECTED = "unselected";

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    private SysUserService sysUserService;

    /**
     * @param roleId
     * @methodName: getAllUserListByRoleId
     * @author: wx
     * @description: 根据角色查询绑定的用户信息与未绑定的用户信息
     * @date: 2018/9/9
     * @return: java.util.Map<java.lang.String,java.util.List<com.wx.mypermission.model.SysUser>>
     */
    public Map<String, List<SysUserVO>> getAllUserListByRoleId(int roleId) {
        /** Start 根据角色查询绑定的用户信息与未绑定的用户信息 */
        List<SysUserVO> selectedUserList = getListByRoleId(roleId);
        List<SysUserVO> allUserList = sysUserService.getAll();
        Set<Integer> selectedUserIdSet = selectedUserList.stream().map(sysUser -> sysUser.getId()).collect(Collectors.toSet());
        // 取所有用户信息与角色绑定用户信息的差集
        List<SysUserVO> unselectedUserList = allUserList.stream().filter(sysUserVO -> {
            return Objects.equals(sysUserVO.getStatus(), SYS_STATUS_1.getCode()) && !selectedUserIdSet.contains(sysUserVO.getId());
        }).collect(Collectors.toList());
        Map<String, List<SysUserVO>> map = Maps.newHashMap();
        map.put(SELECTED, selectedUserList);
        map.put(UN_SELECTED, unselectedUserList);
        /** End */
        return map;
    }

    /**
     * @param roleId 角色id
     * @methodName: getListByRoleId
     * @author: wx
     * @description: 根据角色id查询用户信息集合
     * @date: 2018/9/9
     * @return: java.util.List<com.wx.mypermission.model.SysUser>
     */
    public List<SysUserVO> getListByRoleId(int roleId) {
        List<Integer> userIdList = sysRoleUserMapper.getUserIdListByRoleId(roleId);
        if (CollectionUtils.isEmpty(userIdList)) {
            return Lists.newArrayList();
        }
        List<SysUserVO> sysUserList = sysUserService.getByIdList(userIdList);
        if (CollectionUtils.isEmpty(sysUserList)) {
            return Lists.newArrayList();
        }
        return ConverterUtils.listDateConvert(SysUserVO.class, sysUserList);
    }

    /**
     * @param roleId     角色id
     * @param userIdList 用户集合id
     * @methodName: changeRoleUsers
     * @author: wx
     * @description: 修改角色用户数据
     * @date: 2018/9/9
     * @return: JsonData
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public JsonData changeRoleUsers(int roleId, List<Integer> userIdList) {
        //获取角色对应的用户数据
        List<Integer> originUserIdList = sysRoleUserMapper.getUserIdListByRoleId(roleId);
        if (CollectionUtils.isEmpty(userIdList)) {
            // 删除原用户数据
            sysRoleUserMapper.deleteByRoleId(roleId);
            return JsonData.ResultMsgSuccess(DELETE_SUCCESS, new SysLogVO(roleId, JsonUtils.toJson(new SysRoleUserDto(roleId, originUserIdList)), "", NO_0.getCode()));
        }
        /** Start 维护角色与用户数据 */
        if (!CollectionUtils.isEmpty(originUserIdList)) {
            if (CompareUtils.equalCollection(originUserIdList, userIdList)) {
                return JsonData.ResultMsgSuccess(UPDATE_SUCCESS, new SysLogVO(roleId, JsonUtils.toJson(new SysRoleUserDto(roleId, originUserIdList)), JsonUtils.toJson(new SysRoleUserDto(roleId, originUserIdList)), NO_0.getCode()));
            }
        }
        //角色用户批量更新
        batchSysRoleUserInsert(new SysRoleUserDto(roleId, userIdList));
        /** End */
        return JsonData.ResultMsgSuccess(UPDATE_SUCCESS, new SysLogVO(roleId, JsonUtils.toJson(new SysRoleUserDto(roleId, originUserIdList)), JsonUtils.toJson(new SysRoleUserDto(roleId, userIdList)), NO_0.getCode()));
    }

    /**
     * @param sysRoleUserDto
     * @methodName: batchSysRoleUserInsert
     * @author: wx
     * @description: 角色用户批量更新
     * @date: 2018/9/24
     * @return: void
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void batchSysRoleUserInsert(SysRoleUserDto sysRoleUserDto) {
        // 删除原用户数据
        Integer roleId = sysRoleUserDto.getRoleId();
        sysRoleUserMapper.deleteByRoleId(roleId);
        // 新增新权限数据
        List<SysRoleUser> sysRoleUserList = Lists.newArrayList();
        sysRoleUserDto.getUserIdlist().forEach(userId -> {
            SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setRoleId(roleId);
            sysRoleUser.setUserId(userId);
            sysRoleUser.setOperator(BusinessContextUtils.getSysUser() == null ? DEFAULT_SYS_USER_NAME : BusinessContextUtils.getSysUser().getUsername());
            sysRoleUser.setOperateIp(IpUtil.getRemoteIp(BusinessContextUtils.getRequest()));
            sysRoleUser.setOperateTime(new Date());
            sysRoleUserList.add(sysRoleUser);
        });
        sysRoleUserMapper.batchInsert(sysRoleUserList);
    }
}
