package com.edu.nbu.dal.dao;

import com.edu.nbu.dal.po.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.JdbcTemplate;

@Getter
@Setter
public class UserDao {

    private JdbcTemplate jdbcTemplate;
    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    User selectById(Long id){
        return null;
    };
}
