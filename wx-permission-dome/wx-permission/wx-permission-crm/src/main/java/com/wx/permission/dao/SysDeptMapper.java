package com.wx.permission.dao;


import com.wx.permission.model.SysDept;
import com.wx.permission.qo.SysDeptQO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysDeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);
    
    /**
     * @methodName: getByCondition
     * @author: wx
     * @description: 根据对象条件查询
     * @param sysDeptQO
     * @date: 2018/8/20
     * @return: com.wx.mypermission.model.SysDept
     */
    SysDept getByCondition(SysDeptQO sysDeptQO);
    
    /**
     * @methodName: getDeptAll
     * @author: wx
     * @description: 查询所有部门数据
     * @param
     * @date: 2018/8/20
     * @return: java.util.List<com.wx.mypermission.model.SysDept>
     */
    List<SysDept> getDeptAll();
    
    /**
     * @methodName: getChildDeptListByLevel
     * @author: wx
     * @description: 获取level下的所有部门
     * @param level  部门层级
     * @date: 2018/8/22
     * @return: java.util.List<com.wx.mypermission.model.SysDept>
     */
    List<SysDept> getChildDeptListByLevel(@Param("level") String level);
    
    /**
     * @methodName: batchUpdateLevel
     * @author: wx
     * @description: 批量更新所有下级部门
     * @param deptList 批量更新集合对象
     * @date: 2018/8/22
     * @return: void
     */
    void batchUpdateDeptLevel(@Param("deptList") List<SysDept> deptList);
    
    /**
     * @methodName: countByParentId
     * @author: wx
     * @description: 查询该部门下的子部门的数量
     * @param deptId 部门id
     * @date: 2018/9/9
     * @return: java.lang.Integer
     */
    Integer countByParentId(@Param("deptId") int deptId);
}