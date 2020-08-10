package com.edu.nbu.cn.my.controller;

import com.edu.nbu.cn.my.common.MyHttpResponse;
import com.edu.nbu.cn.my.jsonview.UserDetailView;
import com.edu.nbu.cn.my.jsonview.UserSimpleView;
import com.edu.nbu.cn.my.jsonview.UserView;
import com.edu.nbu.cn.my.model.User;
import com.edu.nbu.cn.my.process.UserProcess;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController("/user")
@Slf4j
public class UserController {

    @Resource
    private UserProcess userProcess;

    @GetMapping("/get/{id:\\d+}")
    @JsonView(UserView.class)
    public User getUser(@PathVariable("id") String id){
        return  userProcess.buildUser();
    }

    @GetMapping("/get/{id:\\d+}")
    @JsonView(UserSimpleView.class)
    public User getUserSimple(@PathVariable("id") String id){
        return  userProcess.buildUser();
    }

    @GetMapping("/get/{id:\\d+}")
    @JsonView(UserDetailView.class)
    public User getUserDetail(@PathVariable("id") String id){
        return  userProcess.buildUser();
    }

    @PostMapping("/register")
    public MyHttpResponse<User> register(@Valid @RequestBody User user, BindingResult errors){
        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> log.error(error.getDefaultMessage()));
            return MyHttpResponse.errorWithDefaultMessage();
        }
        return userProcess.buildResponse(user);
    }



}
