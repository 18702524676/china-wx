package com.wx.permission.dao;


import com.wx.permission.model.SysRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
    
    /**
     * @methodName: getByNameCount
     * @author: wx
     * @description: 根据角色名查询记录数量
     * @param name
     * @date: 2018/9/5
     * @return: int
     */
    int getByNameCount(@Param("name") String name);
    
    /**
     * @methodName: getAll
     * @author: wx
     * @description: 获取所有角色信息
     * @param 
     * @date: 2018/9/5
     * @return: java.util.List<com.wx.mypermission.model.SysRole>
     */
    List<SysRole> getAll();
    
    /**
     * @methodName: getByIdList
     * @author: wx
     * @description: 根据角色id集合查询角色信息
     * @param idList 角色id集合
     * @date: 2018/9/9
     * @return: java.util.List<com.wx.mypermission.model.SysRole>
     */
    List<SysRole> getByIdList(@Param("idList") List<Integer> idList);
}