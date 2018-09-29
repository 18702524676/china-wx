package com.wx.permission.dao;


import com.wx.permission.model.SysUser;
import com.wx.permission.qo.SysUserQO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    
    /**
     * @methodName: countByMail
     * @author: wx
     * @description: 根据邮箱查询数据的count
     * @param mail 邮箱
     * @date: 2018/8/26
     * @return: int
     */
    int countByMail(@Param("mail") String mail);
    
    /**
     * @methodName: countByTelephone
     * @author: wx
     * @description: 根据手机号查询数据的count
     * @param telephone 手机号
     * @date: 2018/8/26
     * @return: int
     */
    int countByTelephone(@Param("telephone") String telephone);
    
    /**
     * @methodName: findByKeyword
     * @author: wx
     * @description: 根据邮箱or手机号查询
     * @param keyword 邮箱or手机号查询
     * @date: 2018/8/28
     * @return: com.wx.mypermission.model.SysUser
     */
    SysUser findByKeyword(@Param("keyword") String keyword);
    
    /**
     * @methodName: countByCondition
     * @author: wx
     * @description: 根据条件统计总数
     * @param sysUserQO
     * @date: 2018/8/31
     * @return: int
     */
    int countByCondition(SysUserQO sysUserQO);
    
    /**
     * @methodName: getPage
     * @author: wx
     * @description: 分页查询
     * @param sysUserQO
     * @date: 2018/8/31
     * @return: java.util.List<com.wx.mypermission.model.SysUser>
     */
    List<SysUser> getPage(SysUserQO sysUserQO);
    
    /**
     * @methodName: getByIdList
     * @author: wx
     * @description: 根据用户id集合查询
     * @param idList 用户id集合
     * @date: 2018/9/9
     * @return: java.util.List<com.wx.mypermission.model.SysUser>
     */
    List<SysUser> getByIdList(@Param("idList") List<Integer> idList);
    
    /**
     * @methodName: getAll
     * @author: wx
     * @description: 查询所有用户信息
     * @param 
     * @date: 2018/9/9
     * @return: java.util.List<com.wx.mypermission.model.SysUser>
     */
    List<SysUser> getAll();
}