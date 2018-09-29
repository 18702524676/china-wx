package com.wx.permission.dto;


import com.wx.core.validator.groups.Save;
import com.wx.core.validator.groups.Update;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName SysDto
 * @Author wx
 * @Description 部门dto
 * @Date 2018-08-19-17:36
 */
@Data
public class SysDeptDto {
    /**
     * id
     */
    @NotNull(message = "id_is_null",groups = {Update.class})
    private Integer id;
    /**
     * 部门名称
     */
    @Length(max = 15, min = 2, message = "部门名称长度需要在2-15个字之间", groups = {Save.class,Update.class})
    @NotBlank(message = "部门名称不可以为空", groups = {Save.class,Update.class})
    private String name;
    /**
     * 部门上级id，默认最高级的上级 = 0
     */
    private Integer parentId = 0;
    /**
     * 展示顺序
     */
    @NotNull(message = "展示顺序不可以为空", groups = {Save.class,Update.class})
    private Integer seq;
    /**
     * 备注
     */
    private String remark;
}
