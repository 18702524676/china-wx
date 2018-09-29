package com.wx.permission.dao;


import com.wx.permission.model.SysAclModule;
import com.wx.permission.qo.SysAclModuleQO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SysAclModuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysAclModule record);

    int insertSelective(SysAclModule record);

    SysAclModule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAclModule record);

    int updateByPrimaryKey(SysAclModule record);
    
    /**
     * @methodName: getByCondition
     * @author: wx
     * @description: 根据条件查询
     * @param sysAclModuleQO
     * @date: 2018/9/2
     * @return: com.wx.mypermission.model.SysAclModule
     */
    SysAclModule getByCondition(SysAclModuleQO sysAclModuleQO);
    
    
    /**
     * @methodName: getChildAclModuleListByLevel
     * @author: wx
     * @description: 获取level下的所有权限模块
     * @param level  权限模块层级
     * @date: 2018/9/2
     * @return: java.util.List<com.wx.mypermission.model.SysAclModule>
     */
    List<SysAclModule> getChildAclModuleListByLevel(@Param("level") String level);
    
    /**
     * @methodName: batchUpdateAclModuleLevel
     * @author: wx
     * @description: 批量更新所有下级权限模块
     * @param aclModuleList 批量更新所有下级权限模块集合数据
     * @date: 2018/9/2
     * @return: void
     */
    void batchUpdateAclModuleLevel(@Param("aclModuleList") List<SysAclModule> aclModuleList);
    
    /**
     * @methodName: getAclModuleAll
     * @author: wx
     * @description: 获取所有权限模块数据
     * @param
     * @date: 2018/9/2
     * @return: java.util.List<com.wx.mypermission.model.SysAclModule>
     */
    List<SysAclModule> getAclModuleAll();
    
    /**
     * @methodName: countByParentId
     * @author: wx
     * @description: 查询该权限模块下的子权限模块数量
     * @param aclModuleId 权限模块id
     * @date: 2018/9/9
     * @return: java.lang.Integer
     */
    Integer countByParentId(Integer aclModuleId);
}