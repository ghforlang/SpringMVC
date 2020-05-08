package com.edu.nbu.dal;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"classpath*:mybatis/mybatis-config.xml"})
@RunWith(value = SpringJUnit4ClassRunner.class)
@Slf4j
public class DalBaseTest {

    @Test
    public void baseTest(){
        log.info("this is DalBaseTest");
    }
}
