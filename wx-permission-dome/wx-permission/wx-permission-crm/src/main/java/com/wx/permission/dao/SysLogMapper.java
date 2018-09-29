package com.wx.permission.dao;


import com.wx.permission.model.SysLog;
import com.wx.permission.model.SysLogWithBLOBs;
import com.wx.permission.qo.SysLogQO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysLogWithBLOBs record);

    int insertSelective(SysLogWithBLOBs record);

    SysLogWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SysLogWithBLOBs record);

    int updateByPrimaryKey(SysLog record);
    
    /**
     * @methodName: countByCondition
     * @author: wx
     * @description: 根据对象查询总数
     * @param sysLogQO
     * @date: 2018/9/23
     * @return: int
     */
    int countByCondition(SysLogQO sysLogQO);
    
    /**
     * @methodName: getPage
     * @author: wx
     * @description: 分页查询权限日历数据
     * @param sysLogQO
     * @date: 2018/9/23
     * @return: java.util.List<com.wx.mypermission.model.SysLogWithBLOBs>
     */
    List<SysLogWithBLOBs> getPage(SysLogQO sysLogQO);
}