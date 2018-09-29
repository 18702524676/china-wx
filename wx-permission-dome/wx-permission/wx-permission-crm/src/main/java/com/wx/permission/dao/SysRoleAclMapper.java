package com.wx.permission.dao;

import com.wx.permission.model.SysRoleAcl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleAclMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleAcl record);

    int insertSelective(SysRoleAcl record);

    SysRoleAcl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRoleAcl record);

    int updateByPrimaryKey(SysRoleAcl record);
    
    /**
     * @methodName: getAclIdListByRoleIdList
     * @author: wx
     * @description: 根据角色id集合查询权限id集合
     * @param roleIdList
     * @date: 2018/9/8
     * @return: java.util.List<java.lang.Integer>
     */
    List<Integer> getAclIdListByRoleIdList(@Param("roleIdList") List<Integer> roleIdList);
    
    /**
     * @methodName: getAclIdListByRoleId
     * @author: wx
     * @description: 根据角色id查询权限id集合
     * @param roleId
     * @date: 2018/9/8
     * @return: java.util.List<java.lang.Integer>
     */
    List<Integer> getAclIdListByRoleId(@Param("roleId") Integer roleId);
    
    /**
     * @methodName: deleteByRoleId
     * @author: wx
     * @description: 根据角色id删除权限数据
     * @param roleId 角色id
     * @date: 2018/9/9
     * @return: void
     */
    void deleteByRoleId(@Param("roleId") int roleId);
    
    /**
     * @methodName: batchInsert
     * @author: wx
     * @description: 批量插入
     * @param roleAclList
     * @date: 2018/9/9
     * @return: void
     */
    void batchInsert(@Param("roleAclList") List<SysRoleAcl> roleAclList);
    
    /**
     * @methodName: getRoleIdListByAclId
     * @author: wx
     * @description: 根据权限id查询角色id集合
     * @param aclId  权限id
     * @date: 2018/9/9
     * @return: java.util.List<java.lang.Integer>
     */
    List<Integer> getRoleIdListByAclId(@Param("aclId") int aclId);
}