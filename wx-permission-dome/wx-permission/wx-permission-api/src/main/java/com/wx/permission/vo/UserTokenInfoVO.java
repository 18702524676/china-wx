package com.wx.permission.vo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName UserTokenInfoVO
 * @Author wx
 * @Description 用户token信息VO
 * @Date 2018-09-24-14:33
 */
@Data
public class UserTokenInfoVO {
    private Integer id;

    private Integer userId;

    private String accessToken;

    private Date refreshTime;
}
