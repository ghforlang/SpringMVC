package com.nbu.edu.cn.asession.common.http.wrapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.IOException;

public class ServletOutputStreamWrapper extends ServletOutputStream {

    private ServletOutputStream servletOutputStream;
    private ASessionHttpResponseWrapper responseWrapper;

    public ServletOutputStreamWrapper(ServletOutputStream servletOutputStream, ASessionHttpResponseWrapper responseWrapper) {
        this.servletOutputStream = servletOutputStream;
        this.responseWrapper = responseWrapper;
    }

    @Override
    public void flush() throws IOException {
        super.flush();
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    @Override
    public void write(int b) throws IOException {
        responseWrapper.trackContentLength(b);
        servletOutputStream.write(b);
    }

    @Override
    public int hashCode() {
        return servletOutputStream.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return servletOutputStream.equals(obj);
    }


    @Override
    public void print(String s) throws IOException {
        responseWrapper.trackContentLength(s);
        servletOutputStream.println(s);
    }

    @Override
    public void print(boolean b) throws IOException {
        responseWrapper.trackContentLength(b);
        servletOutputStream.println(b);
    }

    @Override
    public void print(char c) throws IOException {
        responseWrapper.trackContentLength(c);
        servletOutputStream.println(c);
    }

    @Override
    public void print(int i) throws IOException {
        responseWrapper.trackContentLength(i);
        servletOutputStream.println(i);
    }

    @Override
    public void print(long l) throws IOException {
        responseWrapper.trackContentLength(l);
        servletOutputStream.println(l);
    }

    @Override
    public void print(float f) throws IOException {
        responseWrapper.trackContentLength(f);
        servletOutputStream.println(f);
    }

    @Override
    public void print(double d) throws IOException {
        responseWrapper.trackContentLength(d);
        servletOutputStream.println(d);
    }

    @Override
    public void println() throws IOException {
        responseWrapper.trackContentLengthLn();
        servletOutputStream.println();
    }

    @Override
    public void println(String s) throws IOException {
        responseWrapper.trackContentLength(s);
        servletOutputStream.println(s);
    }

    @Override
    public void println(boolean b) throws IOException {
        responseWrapper.trackContentLength(b);
        responseWrapper.trackContentLengthLn();
        servletOutputStream.println(b);
    }

    @Override
    public void println(char c) throws IOException {
        responseWrapper.trackContentLength(c);
        responseWrapper.trackContentLengthLn();
        servletOutputStream.println(c);
    }

    @Override
    public void println(int i) throws IOException {
        responseWrapper.trackContentLength(i);
        responseWrapper.trackContentLengthLn();
        servletOutputStream.println(i);
        super.println(i);
    }

    @Override
    public void println(long l) throws IOException {
        responseWrapper.trackContentLength(l);
        responseWrapper.trackContentLengthLn();
        servletOutputStream.println(l);
        super.println(l);
    }

    @Override
    public void println(float f) throws IOException {
        responseWrapper.trackContentLength(f);
        responseWrapper.trackContentLengthLn();
        servletOutputStream.println(f);
        super.println(f);
    }

    @Override
    public void println(double d) throws IOException {
        responseWrapper.trackContentLength(d);
        responseWrapper.trackContentLengthLn();
        servletOutputStream.println(d);
        super.println(d);
    }

    @Override
    public void write(byte[] b) throws IOException {
        responseWrapper.trackContentLength(b);
        servletOutputStream.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        responseWrapper.checkContentLength(len);
        servletOutputStream.write(b,off,len);
    }

    @Override
    public boolean isReady() {
        return servletOutputStream.isReady();
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {
        servletOutputStream.setWriteListener(writeListener);
    }
}
