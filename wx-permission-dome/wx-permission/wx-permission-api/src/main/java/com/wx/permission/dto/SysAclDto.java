package com.wx.permission.dto;


import com.wx.core.validator.groups.Save;
import com.wx.core.validator.groups.Update;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName SysAclDto
 * @Author wx
 * @Description 权限Dto
 * @Date 2018-09-04-22:01
 */
@Data
public class SysAclDto {
    @NotNull(message = "id不能为空",groups = {Update.class})
    private Integer id;

    @NotBlank(message = "权限名称不可以为空",groups = {Save.class, Update.class})
    @Length(min = 2, max = 20, message = "权限名称长度需要在2-20个字之间",groups = {Save.class, Update.class})
    private String name;

    @NotNull(message = "必须指定权限模块",groups = {Save.class, Update.class})
    private Integer aclModuleId;

    @Length(min = 6, max = 100, message = "权限点URL长度需要在6-100个字符之间",groups = {Save.class, Update.class})
    private String url;

    @NotNull(message = "必须指定权限的类型",groups = {Save.class, Update.class})
    @Min(value = 1, message = "权限点类型不合法",groups = {Save.class, Update.class})
    @Max(value = 3, message = "权限点类型不合法",groups = {Save.class, Update.class})
    private Integer type;

    @NotNull(message = "必须指定权限的状态",groups = {Save.class, Update.class})
    @Min(value = 0, message = "权限点状态不合法",groups = {Save.class, Update.class})
    @Max(value = 1, message = "权限点状态不合法",groups = {Save.class, Update.class})
    private Integer status;

    @NotNull(message = "必须指定权限的展示顺序",groups = {Save.class, Update.class})
    private Integer seq;

    private String remark;
}
