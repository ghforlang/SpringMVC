package com.edu.nbu.cn.my.model;

import com.edu.nbu.cn.my.jsonview.UserDetailView;
import com.edu.nbu.cn.my.jsonview.UserSimpleView;
import com.edu.nbu.cn.my.jsonview.UserView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {

    /***
     * 用户id
     */
    @JsonView(UserView.class)
    private Long userId;

    /**
     * 用户姓名
     */
    @JsonView(UserSimpleView.class)
    private String userName;

    /**
     * 用户地址
     */
    @JsonView(UserDetailView.class)
    private String address;
}
