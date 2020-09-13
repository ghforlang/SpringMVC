package com.nbu.edu.cn.utils.context;

public class ErrorContextTest {

    public static void main(String[] args) {
        ErrorContext ec = ErrorContext.instance();
        ec.cause(new RuntimeException("exception"));
        ec.errorCode("500");
        ec.message("调用发生异常");
        System.out.println(ec.toString());
    }
}
