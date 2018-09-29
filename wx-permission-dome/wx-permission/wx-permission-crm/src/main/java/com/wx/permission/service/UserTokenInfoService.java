package com.wx.permission.service;

import com.google.common.collect.Lists;
import com.wx.commons.utils.BusinessValidatorUtils;
import com.wx.commons.utils.ConverterUtils;
import com.wx.permission.dao.UserTokenInfoMapper;
import com.wx.permission.model.UserTokenInfo;
import com.wx.permission.qo.UserTokenInfoQO;
import com.wx.permission.vo.UserTokenInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.wx.permission.enums.PermissionEnumError.UserTokenInfoEnumError.USER_TOKEN_INFO_ENUM_ERROR_0;
import static com.wx.permission.enums.PermissionEnumError.UserTokenInfoEnumError.USER_TOKEN_INFO_ENUM_ERROR_1;


/**
 * @ClassName UserTokenInfoService
 * @Author wx
 * @Description 用户token信息业务层
 * @Date 2018-09-24-13:06
 */
@Slf4j
@Service
public class UserTokenInfoService {
	private final String userIdErrorinfo = "userId_is_null";

	private final String accesTokenErrorinfo = "accesToken_is_null";

	@Autowired
	private UserTokenInfoMapper userTokenInfoMapper;

	/**
	 * @methodName: save
	 * @author: wx
	 * @description: 新增用户token信息
	 * @param userId
	 * @param accesToken
	 * @date: 2018/9/24
	 * @return: void
	 */
	public void save(Integer userId, String accesToken) {
		BusinessValidatorUtils.notNull(userId, userIdErrorinfo);
		BusinessValidatorUtils.notBlank(accesToken, accesTokenErrorinfo);
		UserTokenInfo userTokenInfo = new UserTokenInfo();
		userTokenInfo.setUserId(userId);
		userTokenInfo.setAccessToken(accesToken);
		userTokenInfo.setRefreshTime(new Date());
		userTokenInfoMapper.insertSelective(userTokenInfo);
	}

	/**
	 * @methodName: refreshToken
	 * @author: wx
	 * @description: 刷新token
	 * @param userId 用户id
	 * @date: 2018/9/24
	 * @return: String
	 */
	public String refreshToken(Integer userId) {
		BusinessValidatorUtils.notNull(userId, userIdErrorinfo);
		UserTokenInfoQO userTokenInfoQO = new UserTokenInfoQO();
		userTokenInfoQO.setUserId(userId);
		BusinessValidatorUtils.notNull(validationUserTokenInfo(userTokenInfoQO),
			USER_TOKEN_INFO_ENUM_ERROR_0.getMessageError());
		String accessToken = UUID.randomUUID().toString();
		UserTokenInfo userTokenInfo = new UserTokenInfo();
		userTokenInfo.setUserId(userId);
		userTokenInfo.setAccessToken(accessToken);
		userTokenInfo.setRefreshTime(new Date());
		userTokenInfoMapper.refreshToken(userTokenInfo);
		return accessToken;
	}

	/**
	 * @methodName: getUserTokenInfo
	 * @author: wx
	 * @description: 获取token信息
	 * @param userTokenInfoQO
	 * @date: 2018/9/24
	 * @return: com.wx.mypermission.vo.UserTokenInfoVO
	 */
	public UserTokenInfoVO getUserTokenInfo(UserTokenInfoQO userTokenInfoQO) {
		UserTokenInfoVO userTokenInfoVO = validationUserTokenInfo(userTokenInfoQO);
		return userTokenInfoVO;
	}

	/**
	 * @methodName: getUserTokenInfoAll
	 * @author: wx
	 * @description: 获取所有用户token信息
	 * @param
	 * @date: 2018/9/24
	 * @return: java.util.List<com.wx.mypermission.vo.UserTokenInfoVO>
	 */
	public List<UserTokenInfoVO> getUserTokenInfoAll() {
		List<UserTokenInfo> userTokenInfoAll = userTokenInfoMapper.getUserTokenInfoAll();
		if (CollectionUtils.isEmpty(userTokenInfoAll)) {
			return Lists.newArrayList();
		}
		return ConverterUtils.listDateConvert(UserTokenInfoVO.class, userTokenInfoAll);
	}

	/**
	 * @methodName: validationUserTokenInfo
	 * @author: wx
	 * @description: 验证用户token信息私有方法
	 * @param userTokenInfoQO
	 * @date: 2018/9/24
	 * @return: com.wx.mypermission.vo.UserTokenInfoVO
	 */
	private UserTokenInfoVO validationUserTokenInfo(UserTokenInfoQO userTokenInfoQO) {
		List<UserTokenInfo> userTokenInfoList = userTokenInfoMapper.getUserTokenInfoListByCondition(userTokenInfoQO);
		if (CollectionUtils.isEmpty(userTokenInfoList)) {
			return null;
		}
		BusinessValidatorUtils.isTure(userTokenInfoList.size() == 1, USER_TOKEN_INFO_ENUM_ERROR_1.getMessageError());
		UserTokenInfoVO userTokenInfoVO = new UserTokenInfoVO();
		BeanUtils.copyProperties(userTokenInfoList.get(0), userTokenInfoVO);
		return userTokenInfoVO;
	}
}
