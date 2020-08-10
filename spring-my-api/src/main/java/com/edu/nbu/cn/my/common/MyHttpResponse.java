package com.edu.nbu.cn.my.common;

import com.edu.nbu.cn.my.common.constant.MyHttpResponseCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Getter
@Setter
@ToString
public class MyHttpResponse<T> {

    /**
     * 响应code
     */
    private String responseCode;
    /**
     * 相应信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    public MyHttpResponse() {
    }

    public MyHttpResponse(String responseCode, String message, T data) {
        this.responseCode = responseCode;
        this.message = message;
        this.data = data;
    }



    public static <T> MyHttpResponse<T> success(T data){
        return new MyHttpResponse<>(MyHttpResponseCode.OK.getCode(),"成功!",data);
    }

    public static <T> MyHttpResponse<T> fail(String message){
        return new MyHttpResponse<>(MyHttpResponseCode.FAIL.getCode(),StringUtils.isBlank(message) ? "失败!" : message,null);
    }

    public static <T> MyHttpResponse<T> failWithDefaultMessage(){
        return fail(null);
    }

    public static <T> MyHttpResponse<T> error(String message){
        return new MyHttpResponse<>(MyHttpResponseCode.ERROR.getCode(), StringUtils.isBlank(message) ? "访问错误!" : message,null);
    }

    public static <T> MyHttpResponse<T> errorWithDefaultMessage(){
        return error(null);
    }


}
