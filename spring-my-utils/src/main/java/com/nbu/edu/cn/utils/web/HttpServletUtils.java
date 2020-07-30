package com.nbu.edu.cn.utils.web;

import lombok.experimental.UtilityClass;

import javax.servlet.ServletRequest;
import java.lang.reflect.Method;

@UtilityClass
public class HttpServletUtils {

    public static boolean supportServlet3(){
        for(Method method : ServletRequest.class.getMethods()){
            if("startAsync".equals(method.getName())){
                return true;
            }
        }
        return false;
    }
}
