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
 * @ClassName SysAclModuleDto
 * @Author wx
 * @Description 权限模块DTO
 * @Date 2018-09-02-14:53
 */
@Data
public class SysAclModuleDto {

	@NotNull(message = "id_is_null",groups = {Update.class})
	private Integer id;

	@Length(min = 2, max = 20, message = "权限模块名称长度需要在2~20个字之间", groups = {Save.class,Update.class})
	@NotBlank(message = "权限模块名称不可以为空", groups = {Save.class,Update.class})
	private String name;

    /**
     * 权限模块上级id，默认最高级的上级 = 0
     */
	private Integer parentId = 0;

	@NotNull(message = "权限模块展示顺序不可以为空",groups = {Save.class,Update.class})
	private Integer seq;


	@Min(value = 0, message = "权限模块状态不合法",groups = {Save.class,Update.class})
	@Max(value = 1, message = "权限模块状态不合法",groups = {Save.class,Update.class})
    @NotNull(message = "权限模块状态不可以为空",groups = {Save.class,Update.class})
	private Integer status;

	private String remark;
}
