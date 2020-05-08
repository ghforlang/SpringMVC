package com.nbu.edu.cn.model.response;

import com.nbu.edu.cn.model.PageModel;

public class PageResponse<T extends PageModel> extends BaseResponse{

    private T data;

    PageResponse(String code,String message){
        super(code,message);
        this.data = data;
    }
}
