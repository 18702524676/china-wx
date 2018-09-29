package com.wx.permission.dao;

import com.wx.permission.model.UserTokenInfo;
import com.wx.permission.qo.UserTokenInfoQO;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserTokenInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserTokenInfo userTokenInfo);

    int insertSelective(UserTokenInfo userTokenInfo);

    UserTokenInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTokenInfo userTokenInfo);

    int updateByPrimaryKey(UserTokenInfo userTokenInfo);
    
    /**
     * @methodName: refreshToken
     * @author: wx
     * @description: 刷新toekn
     * @param userTokenInfo
     * @date: 2018/9/24
     * @return: void
     */
    void refreshToken(UserTokenInfo userTokenInfo);

    
    /**
     * @methodName: getUserTokenInfoListByCondition
     * @author: wx
     * @description: 根据对象条件查询用户token信息
     * @param userTokenInfoQO
     * @date: 2018/9/24
     * @return: java.util.List<com.wx.mypermission.model.UserTokenInfo>
     */
    List<UserTokenInfo> getUserTokenInfoListByCondition(UserTokenInfoQO userTokenInfoQO);
    
    /**
     * @methodName: getUserTokenInfoAll
     * @author: wx
     * @description: 获取所有的用户token信息
     * @param
     * @date: 2018/9/24
     * @return: java.util.List<com.wx.mypermission.model.UserTokenInfo>
     */
    List<UserTokenInfo> getUserTokenInfoAll();
}