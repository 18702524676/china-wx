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
 * @ClassName SysRole
 * @Author wx
 * @Description 角色DTO
 * @Date 2018-09-05-22:12
 */
@Data
public class SysRoleDto {

    @NotNull(message = "id_is_null",groups = {Update.class})
    private Integer id;

    @NotBlank(message = "角色名称不可以为空",groups = {Save.class, Update.class})
    @Length(min = 2, max = 20, message = "角色名称长度需要在2-20个字之间",groups = {Save.class, Update.class})
    private String name;

    @Min(value = 1, message = "角色类型不合法",groups = {Save.class, Update.class})
    @Max(value = 2, message = "角色类型不合法",groups = {Save.class, Update.class})
    @NotNull(message = "角色类型不能为空",groups = {Save.class, Update.class})
    private Integer type = 1;

    @Min(value = 0, message = "角色状态不合法",groups = {Save.class, Update.class})
    @Max(value = 1, message = "角色状态不合法",groups = {Save.class, Update.class})
    @NotNull(message = "角色状态不可以为空",groups = {Save.class, Update.class})
    private Integer status;

    private String remark;
}
