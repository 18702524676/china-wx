package com.wx.permission.dao;


import com.wx.permission.model.SysAcl;
import com.wx.permission.qo.SysAclQO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysAclMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysAcl record);

    int insertSelective(SysAcl record);

    SysAcl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAcl record);

    int updateByPrimaryKey(SysAcl record);
    
    /**
     * @methodName: getByCondition
     * @author: wx
     * @description: 根据条件查询单个对象
     * @param sysAclQO
     * @date: 2018/9/4
     * @return: com.wx.mypermission.model.SysAcl
     */
    SysAcl getByCondition(SysAclQO sysAclQO);
    /**
     * @methodName: countByCondition
     * @author: wx
     * @description: 根据对象查询总数
     * @param sysAclQO
     * @date: 2018/9/4
     * @return: int
     */
    int countByCondition(SysAclQO sysAclQO);
    
    /**
     * @methodName: getPage
     * @author: wx
     * @description: 权限分页查询
     * @param sysAclQO
     * @date: 2018/9/4
     * @return: java.util.List<com.wx.mypermission.model.SysAcl>
     */
    List<SysAcl> getPage(SysAclQO sysAclQO);

    List<SysAcl> getAll();
    
    /**
     * @methodName: getByIdList
     * @author: wx
     * @description: 根据权限id集合查询权限数据
     * @param idList
     * @date: 2018/9/8
     * @return: java.util.List<com.wx.mypermission.model.SysAcl>
     */
    List<SysAcl> getByIdList(@Param("idList") List<Integer> idList);

    List<SysAcl> getByUrl(@Param("url") String url);
}