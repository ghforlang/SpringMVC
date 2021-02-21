package com.edu.nbu.info.my.logger.core.appender;

import com.edu.nbu.info.my.logger.core.MyLoggingEvent;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * 控制台输出方式
 */
public class ConsoleAppender extends AbstractLoggerAppender {

    private OutputStream out = System.out;
    private OutputStream outError = System.err;

    @Override
    public void append(MyLoggingEvent event) {
        try {
            out.write(event.toString().getBytes(StandardCharsets.UTF_8));
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
