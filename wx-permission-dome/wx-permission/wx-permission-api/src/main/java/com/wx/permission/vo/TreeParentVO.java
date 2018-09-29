package com.wx.permission.vo;

import lombok.Data;

import java.util.List;

/**
 * @ClassName TreeParentVO
 * @Author wx
 * @Description 树形结构父类
 * @Date 2018-08-24-23:48
 */
@Data
public class TreeParentVO<T> {
    /**
     * id
     */
    private Integer id;
    /**
     * 所有父级level
     */
    private String level;
    /**
     * 顺序
     */
    private Integer seq;
    
    /**
     * 树下的子树集合
     */
    private List<T> treeVOList;
}
