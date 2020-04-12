package com.edu.nbu.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DBUtils {
    private DataSource dbcpDatasource;
    private DataSource springDatasource;
    private DataSource druidDataSource;

    Map<String,DataSource> dataSourceMap = new HashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(DBUtils.class);

    @PostConstruct
    public void initDatasourceMap(){
        dataSourceMap.put("DBCP",dbcpDatasource);
        dataSourceMap.put("SPRING",springDatasource);
        dataSourceMap.put("DRUID",druidDataSource);
    }

    public Connection getConnection(String dataSourceType) throws SQLException {
        if(StringUtils.isBlank(dataSourceType) || Objects.isNull(dataSourceMap.get(dataSourceType))){
            LOGGER.warn("数据源类型无效,dataSourceType:{}",dataSourceType);
            return null;
        }
        return dataSourceMap.get(dataSourceType).getConnection();
    }


    public void setDbcpDatasource(DataSource dbcpDatasource) {
        this.dbcpDatasource = dbcpDatasource;
    }

    public void setSpringDatasource(DataSource springDatasource) {
        this.springDatasource = springDatasource;
    }

    public void setDruidDataSource(DataSource druidDataSource) {
        this.druidDataSource = druidDataSource;
    }
}
