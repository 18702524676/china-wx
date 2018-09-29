package com.wx.permission.qo;

import com.wx.commons.tools.Page;
import lombok.Data;

/**
 * @ClassName UserTokenInfoQO
 * @Author wx
 * @Description 用户token QO
 * @Date 2018-09-24-15:49
 */
@Data
public class UserTokenInfoQO extends Page {

	private Integer userId;

	private String accessToken;
}
