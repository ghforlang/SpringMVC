package com.edu.nbu.info.my.logger.configuration;

import com.edu.nbu.info.my.logger.core.MyLoggerContext;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;

public class MyXMLConfiguration implements Configuration{

    /**
     * 文件url
     */
    private URL url;

    /**
     * loggerContext
     */
    private MyLoggerContext loggerContext;

    public MyXMLConfiguration(URL url, MyLoggerContext loggerContext) {
        this.url = url;
        this.loggerContext = loggerContext;
    }

    public MyXMLConfiguration(MyConfigurationFactory.ConfigurationBuilder builder){
        this(builder.getUrl(),builder.getLoggerContext());
    }


    @Override
    public void doConfig() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(url.openStream());
            parse(document);
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    /**
     * 解析参数至MyLoggerContext
     * @param doc
     */
    private void parse(Document doc){
        //参数解析
    }

}
