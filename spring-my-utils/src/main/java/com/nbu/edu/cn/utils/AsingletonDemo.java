package com.nbu.edu.cn.utils;

import com.nbu.edu.cn.utils.web.Singleton;

public class AsingletonDemo {

   private static class  CreateSingleton{
       private static Singleton instance = new Singleton();
   }

    public static Singleton getInstance(){
        return CreateSingleton.instance;
    }
}
