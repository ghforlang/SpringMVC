package com.edu.nbu.cn.my.common.utils;

import com.edu.nbu.cn.my.common.MyHttpResponse;
import com.edu.nbu.cn.my.common.constant.MyHttpResponseCode;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class MyHttpResponseUtils {

    public static final boolean isSuccess(MyHttpResponse response){
        return Objects.nonNull(response) && MyHttpResponseCode.OK.getCode().equals(response.getResponseCode());
    }

    public static final boolean isFail(MyHttpResponse response){
        return Objects.isNull(response) ||  MyHttpResponseCode.FAIL.getCode().equals(response.getResponseCode());
    }

    public static final boolean isError(MyHttpResponse response){
        return Objects.isNull(response) || MyHttpResponseCode.ERROR.getCode().equals(response.getResponseCode());
    }
}
