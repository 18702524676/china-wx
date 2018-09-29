package com.wx.commons.tools;


import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName Page
 * @Author wx
 * @Description 分页工具
 * @Date 2018-08-31-22:17
 */
@Data
public class Page<T> implements Serializable{
    private static final long serialVersionUID = 3871427006594804484L;
    /**
     * 页码
     */
    private int pageNo = 1;
    /**
     * 每页展示数量
     */
    private int pageSize = 5;
    /**
     * 偏移量
     */
    private int offset;
    /**
     * 总数
     */
    private int total = 0;
    /**
     * 数据
     */
    private List<T> data = Lists.newArrayList();

    public int getOffset() {
        return (this.pageNo - 1) * this.pageSize;
    }
}
