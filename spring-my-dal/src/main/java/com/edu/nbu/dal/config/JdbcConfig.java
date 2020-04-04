package com.edu.nbu.dal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:config/jdbc.properties")
public class JdbcConfig {

    @Value("driverClassName")
    private String jdbcDriver;

    @Value("url")
    private String url;

    @Value("username")
    private String userName;

    @Value("password")
    private String password;
}
