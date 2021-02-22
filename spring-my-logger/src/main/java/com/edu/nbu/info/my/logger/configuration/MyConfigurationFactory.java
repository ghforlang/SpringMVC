package com.edu.nbu.info.my.logger.configuration;

import com.edu.nbu.info.my.logger.core.MyLoggerContext;
import lombok.Getter;

import java.net.URL;

public class MyConfigurationFactory {



    @Getter
    public static class ConfigurationBuilder {

        /**
         * 文件url
         *
         */
        private URL url;

        /**
         * loggerContext
         */
        private MyLoggerContext loggerContext;

        public Configuration build(){
            return new  MyXMLConfiguration(this);
        }

        public ConfigurationBuilder url(URL url){
            this.url = url;
            return this;
        }

        public ConfigurationBuilder loggerContext(MyLoggerContext loggerContext){
            this.loggerContext = loggerContext;
            return this;
        }

    }
}
