package com.nbu.edu.cn.model.response;

import com.nbu.edu.cn.model.Model;

public class CommonResponse<T extends Model> extends BaseResponse{

    /**
     * 数据
     */
    private T data;

    CommonResponse(String code,String message,T data){
        super(code,message);
        this.data = data;
    }
}
