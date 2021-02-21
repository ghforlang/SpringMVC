package com.edu.nbu.info.my.logger.configuration;

import com.edu.nbu.info.my.logger.core.MyLoggerContext;

import java.net.URL;
import java.util.Objects;

/**
 * 独立的初始化加载器
 */
public class MyLoggerContextInitializer {

    private static final String CONFIGURATION_URL = "test-my-logger.xml";

    private static final MyLoggerContext DEFAULT_CONTEXT = new MyLoggerContext();

    public static void autoConfig(){
        URL url = getConfigUrl();
        if(Objects.isNull(url)){
            System.out.println("invalid url ,url = " + url);
            return;
        }

        String urlString = url.toString();
        Configuration configuration = null;
        //这里可以采用工厂，根据不同文件类型，选择不不同的文件解析器
        if(urlString.endsWith(".xml")){
            configuration = new MyXMLConfiguration(url,DEFAULT_CONTEXT);
        }
        configuration.doConfig();
    }

    private static URL getConfigUrl(){
        ClassLoader classLoader = MyLoggerContextInitializer.class.getClassLoader();
        URL url = classLoader.getResource(CONFIGURATION_URL);
        return url;
    }

    public static MyLoggerContext getDefaultLoggerContext(){
        return DEFAULT_CONTEXT;
    }
}
