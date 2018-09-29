package com.wx.permission.dao;

import com.wx.permission.model.SysRoleUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleUser record);

    int insertSelective(SysRoleUser record);

    SysRoleUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRoleUser record);

    int updateByPrimaryKey(SysRoleUser record);
    /**
     * @methodName: getRoleIdListByUserId
     * @author: wx
     * @description: 根据用户id获取角色集合id
     * @param userId
     * @date: 2018/9/8
     * @return: java.util.List<java.lang.Integer>
     */
    List<Integer> getRoleIdListByUserId(@Param("userId") Integer userId);
    
    /**
     * @methodName: getUserIdListByRoleId
     * @author: wx
     * @description: 根据角色id查询用户id集合
     * @param roleId 角色id
     * @date: 2018/9/9
     * @return: java.util.List<java.lang.Integer>
     */
    List<Integer> getUserIdListByRoleId(@Param("roleId") int roleId);
    
    /**
     * @methodName: deleteByRoleId
     * @author: wx
     * @description: 根据角色id删除用户数据
     * @param roleId 角色id
     * @date: 2018/9/9
     * @return: void
     */
    void deleteByRoleId(int roleId);
    
    /**
     * @methodName: batchInsert
     * @author: wx
     * @description: 批量插入
     * @param roleUserList
     * @date: 2018/9/9
     * @return: void
     */
    void batchInsert(@Param("roleUserList") List<SysRoleUser> roleUserList);
    
    /**
     * @methodName: getUserIdListByRoleIdList
     * @author: wx
     * @description: 根据角色id集合查询用户id集合
     * @param roleIdList 角色id集合
     * @date: 2018/9/9
     * @return: java.util.List<java.lang.Integer>
     */
    List<Integer> getUserIdListByRoleIdList(@Param("roleIdList") List<Integer> roleIdList);
}