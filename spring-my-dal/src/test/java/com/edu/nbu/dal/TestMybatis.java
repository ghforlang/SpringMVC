package com.edu.nbu.dal;

import com.edu.nbu.common.utils.DBUtils;
import org.junit.Test;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class TestMybatis extends DalBaseTest{

    @Resource
    private DBUtils dbUtils;

    @Test
    public void testDbcpDatasource() throws SQLException, IOException {
        Connection connection = dbUtils.getConnection("DBCP");
        Assert.isTrue(Objects.nonNull(connection),"连接不能为空!");
        System.out.println(connection.getSchema());
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
