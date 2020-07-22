package com.nbu.edu.cn.utils;

import com.nbu.edu.cn.exception.ResponseEnum;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@UtilityClass
public class CookieUtils {

    public static Cookie getCookie(HttpServletRequest request,String name){
        if(Objects.isNull(request) || StringUtils.isBlank(name)){
            throw ResponseEnum.ILLEGAL_ARGS.newException("request or name can not be null !");
        }

        Cookie[] cookies = request.getCookies();
        if(ArrayUtils.isNotEmpty(cookies)){
            for (Cookie cookie : cookies) {
                if(name.equals(cookie.getName())){
                    return cookie;
                }
            }
        }
        return null;
    }
}
