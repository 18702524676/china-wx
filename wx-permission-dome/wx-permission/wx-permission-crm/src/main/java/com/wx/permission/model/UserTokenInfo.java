package com.wx.permission.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserTokenInfo {
    private Integer id;

    private Integer userId;

    private String accessToken;

    private Date refreshTime;

}