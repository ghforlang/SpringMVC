package com.nbu.edu.cn.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PageModel<T> implements Model{

    /**
     * 当前页码
     */
    private Integer countPage;
    /**
     * 当前页大小
     */
    private Integer pageNo;
    /**
     * 总记录数
     */
    private Integer totalCount;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 数据
     */
    private List<T> data;
}
