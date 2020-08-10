package com.edu.nbu.cn.my.common.constant;

public enum MyHttpResponseCode {
    OK("0"),ERROR("1"),FAIL("2");

    private String code;

    MyHttpResponseCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
