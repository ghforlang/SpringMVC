package com.edu.nbu.info.my.logger;

import com.edu.nbu.info.my.logger.configuration.Configuration;
import com.edu.nbu.info.my.logger.configuration.MyConfigurationFactory;
import com.edu.nbu.info.my.logger.core.MyLoggerContext;

import java.net.URL;

public class TestMyConfigurationFactory {

    public static void main(String[] args) {
        URL url = TestMyConfigurationFactory.class.getClassLoader().getResource("test-my-logger.xml");
        MyLoggerContext loggerContext = new MyLoggerContext();
        Configuration configuration = new MyConfigurationFactory.ConfigurationBuilder()
                .url(url)
                .loggerContext(loggerContext).build();
        System.out.println(configuration);
    }
}
