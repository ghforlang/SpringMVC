package com.edu.nbu.dal;

import com.edu.nbu.common.utils.DBUtils;
import com.edu.nbu.dal.mapper.UserMapper;
import com.edu.nbu.dal.po.UserPO;
import com.nbu.edu.cn.utils.JackSonUtils;
import org.junit.Test;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class TestMybatis extends DalBaseTest{

    @Resource
    private DBUtils dbUtils;
    @Resource
    private UserMapper userMapper;

    @Test
    public void testDbcpDatasource() throws SQLException, IOException {
        Connection connection = dbUtils.getConnection("DBCP");
        Assert.isTrue(Objects.nonNull(connection),"连接不能为空!");
        System.out.println(connection.getSchema());
        String jsonStr = readFromFile("userPO.json");
        UserPO user = JackSonUtils.toObj(jsonStr,UserPO.class);

        int insertResult = userMapper.insert(user);
    }

    @Test
    public void testSpringDatasource() throws SQLException {
        Connection connection = dbUtils.getConnection("SPRING");
    }

    @Test
    public void testDruidDatasource() throws SQLException {
        Connection connection = dbUtils.getConnection("DRUID");
    }
}
