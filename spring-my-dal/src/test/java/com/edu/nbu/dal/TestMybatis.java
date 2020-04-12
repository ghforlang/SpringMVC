package com.edu.nbu.dal;

import com.edu.nbu.common.utils.DBUtils;
import org.junit.Test;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TestMybatis extends DalBaseTest{

    @Resource
    private DBUtils dbUtils;

    @Test
    public void testDbcpDatasource() throws SQLException {
        Connection connection = dbUtils.getConnection("xxx");
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
