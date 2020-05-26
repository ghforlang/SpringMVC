package com.edu.nbu.dal;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.util.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;

@ContextConfiguration(locations = {"classpath*:spring-mybatis-config.xml"})
@RunWith(value = SpringJUnit4ClassRunner.class)
@Slf4j
public class DalBaseTest {

    @Test
    public void baseTest(){
        log.info("this is DalBaseTest");
    }

    public String readFromFile(String filePath) throws IOException {
        File file = new File(this.getClass().getResource("/json/" + filePath).getFile());
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String tmp = "";
        while((tmp = br.readLine()) != null){
            sb.append(tmp);
        }
        return sb.toString();
    }

}
