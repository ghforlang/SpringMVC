package com.nbu.edu.cn.model.response;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class BaseResponse {

    /**
     * code
     */
    private String code;

    /**
     * message
     */
    private String message;

    BaseResponse(){}

    protected BaseResponse(String code,String message){
        this.code = code;
        this.message = message;
    }

}
