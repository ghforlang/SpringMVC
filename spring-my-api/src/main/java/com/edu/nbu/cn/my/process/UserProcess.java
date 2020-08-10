package com.edu.nbu.cn.my.process;

import com.edu.nbu.cn.my.common.MyHttpResponse;
import com.edu.nbu.cn.my.model.User;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserProcess {

    public User buildUser(){
        User user = new User();
        user.setUserId(1L);
        user.setUserName("张三");
        user.setAddress("浙江-杭州");
        return user;
    }

    public MyHttpResponse<User> buildResponse(User user){
        if(Objects.isNull(user)){
            return MyHttpResponse.failWithDefaultMessage();
        }
        return MyHttpResponse.success(user);
    }
}
