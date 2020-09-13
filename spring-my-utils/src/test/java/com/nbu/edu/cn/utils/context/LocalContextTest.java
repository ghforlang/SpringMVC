package com.nbu.edu.cn.utils.context;

import com.nbu.edu.cn.utils.JackSonUtils;
import com.nbu.edu.cn.utils.model.CourseAndScoreBO;
import com.nbu.edu.cn.utils.model.StudentBO;

import java.util.concurrent.TimeUnit;

public class LocalContextTest {

    private LocalContext<StudentBO> localContext1;
    private LocalContext<StudentBO> localContext2;
    private LocalContext<CourseAndScoreBO> localContext3;

    public void m1(){
        StudentBO studentBO = StudentBO.getInstance();
        studentBO.setName("context-m1");
        localContext1 = LocalContext.newInstance(studentBO);
        System.out.println(Thread.currentThread().getName() + " after set : " + JackSonUtils.toJsonString(localContext1.get()));
        localContext3 = LocalContext.newInstance(CourseAndScoreBO.getInstance());
        System.out.println(Thread.currentThread().getName() + " localContext3: " + JackSonUtils.toJsonString(localContext3.get()));
        System.out.println(Thread.currentThread().getName() + " localContext1: " + JackSonUtils.toJsonString(localContext1.get()));
        studentBO.setName("context-m1-stored");
        studentBO = localContext1.stored(studentBO);
        System.out.println(Thread.currentThread().getName() + " after stored : " + JackSonUtils.toJsonString(studentBO));
        studentBO = localContext1.recall();
        System.out.println(Thread.currentThread().getName() + " after recall : " + JackSonUtils.toJsonString(studentBO));
        studentBO = localContext1.reset().get();
        System.out.println(Thread.currentThread().getName() + " after reset : " + JackSonUtils.toJsonString(studentBO));
        studentBO = localContext1.recall();
        System.out.println(Thread.currentThread().getName() + " after reset - recall : " + JackSonUtils.toJsonString(studentBO));
        localContext1.resetAll();
        studentBO = localContext1.resetAll().get();
        System.out.println(Thread.currentThread().getName() + " after resetAll : " + JackSonUtils.toJsonString(studentBO));
    }

    public void m2(){
        StudentBO studentBO = StudentBO.getInstance();
        studentBO.setName("context-m2");
        localContext2 = LocalContext.newInstance(studentBO);
        StudentBO sb2 = localContext2.get();
        System.out.println(JackSonUtils.toJsonString(sb2));
    }

    public static void main(String[] args) throws InterruptedException {
        LocalContextTest test = new LocalContextTest();
        StudentBO studentBO = StudentBO.getInstance();
        studentBO.setName("context1");
        LocalContext<StudentBO> context1 = LocalContext.newInstance(studentBO);
        StudentBO s3 = context1.get();
        System.out.println("mainThread context1 : " + JackSonUtils.toJsonString(s3));
        System.out.println(studentBO == s3);
        Thread t1 = new Thread(test::m1,"t1");
//        Thread t2 = new Thread(test::m2);
        t1.start();
//        t2.start();

        TimeUnit.MILLISECONDS.sleep(200);
        StudentBO ss1 = test.localContext1.get();
        System.out.println("mainThread  at the end : " + JackSonUtils.toJsonString(ss1));
//        StudentBO ss2 = test.localContext2.get();
//        System.out.println(JackSonUtils.toJsonString(ss2));
    }
}
