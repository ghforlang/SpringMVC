package com.edu.nbu.dal.mapper;

import com.edu.nbu.dal.DalBaseTest;
import com.edu.nbu.dal.po.UserPO;
import com.nbu.edu.cn.utils.JackSonUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;

public class TestUserMapper extends DalBaseTest {

    @Resource
    private UserMapper userMapper;

    public void testInsert() throws IOException {
        String jsonStr = readFromFile("userPO.json");
        UserPO user = JackSonUtils.toObj(jsonStr,UserPO.class);
        int insertResult = userMapper.insert(user);
        System.out.println("insert result : " + insertResult);
    }

    @Test
    public void testGetByPkId(){
        UserPO user = userMapper.getByPkId(1L);
        System.out.println(JackSonUtils.toJsonString(user));
    }
}
