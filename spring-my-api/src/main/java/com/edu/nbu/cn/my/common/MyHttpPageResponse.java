package com.edu.nbu.cn.my.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MyHttpPageResponse<T> extends MyHttpResponse {

    /**
     * 当前页码
     */
    private Integer pageNo;

    /**
     * 页大小
     */
    private Integer pageSize;

}
