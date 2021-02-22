package com.edu.nbu.info.my.logger;

import com.edu.nbu.info.my.logger.core.factory.MyLoggerFactory;
import com.edu.nbu.info.my.logger.core.log.MyLogger;

public class TestConfiguration {

    private static final MyLogger  logger = MyLoggerFactory.getLogger(TestConfiguration.class);

    public static void main(String[] args) {
        logger.info("this is first demo!");
    }
}
