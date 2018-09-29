package com.wx.permission.dto;


import com.wx.core.validator.groups.Save;
import com.wx.core.validator.groups.Update;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

import static com.wx.commons.constant.CommonConstant.Regexp.REGEX_MOBILE;


/**
 * @ClassName SysUserDto
 * @Author wx
 * @Description 系统用户Dto
 * @Date 2018-08-26-20:11
 */
@Data
public class SysUserDto {

    @NotNull(message = "id_is_null",groups = {Update.class})
    private Integer id;

    @Length(min = 1, max = 20, message = "用户名长度需要在20个字以内",groups = {Save.class,Update.class})
    @NotBlank(message = "用户名不可以为空",groups = {Save.class,Update.class})
    private String username;

    @Pattern(regexp = REGEX_MOBILE,message = "手机格式不正确",groups = {Save.class,Update.class})
    @NotBlank(message = "手机号不可以为空",groups = {Save.class,Update.class})
    private String telephone;


    @NotBlank(message = "密码不能为空",groups = {Save.class})
    private String password;

    @Email(message = "邮箱格式不正确",groups = {Save.class,Update.class})
    @NotBlank(message = "邮箱不允许为空",groups = {Save.class,Update.class})
    private String mail;

    @NotNull(message = "必须提供用户所在的部门",groups = {Save.class,Update.class})
    private Integer deptId;

    @Min(value = 0, message = "用户状态不合法",groups = {Save.class,Update.class})
    @Max(value = 2, message = "用户状态不合法",groups = {Save.class,Update.class})
    @NotNull(message = "必须指定用户的状态",groups = {Save.class,Update.class})
    private Integer status;

    private String remark = "";

}
