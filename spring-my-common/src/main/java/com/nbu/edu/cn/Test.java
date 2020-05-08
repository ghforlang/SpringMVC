package com.nbu.edu.cn;

import com.nbu.edu.cn.exception.ResponseEnum;

public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        test.testException();
    }

    public void testException(){
        try {
            System.out.println(ResponseEnum.ILLEGAL_ARGS.getCode() + " : " + ResponseEnum.ILLEGAL_ARGS.getMessage());
            ResponseEnum.ILLEGAL_ARGS.assertNotNull(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
