package com.nbu.edu.cn.asession.common.http.wrapper;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class ASessionHttpResponseWrapper extends HttpServletResponseWrapper {
    
    private ASessionHttpRequestWrapper request;
    private boolean disableOnCommitted;
    private long contentLength;
    private long contentWritten;
    
    public ASessionHttpResponseWrapper(HttpServletResponse response, ASessionHttpRequestWrapper request){
        super(response);
        this.request = request;
        this.disableOnCommitted = false;
    }

    @Override
    public void addHeader(String name, String value) {
        if("Content-Length".endsWith(name)){
            setContentLength(Long.parseLong(value));
        }
        super.addHeader(name, value);
    }

    @Override
    public void setContentLength(int len) {
        setContentLength((long)len);
        super.setContentLength(len);
    }
    
    private void setContentLength(long len){
        this.contentLength = len;
        checkContentLength(0);
    }
    
    public void disableOnResponseCommitted(){
        this.disableOnCommitted = true;
    }
    
    public void checkContentLength(long contentLengthToWrite){
        contentWritten += contentLengthToWrite;
        boolean isBodyFullyWritten = contentLength > 0 && contentWritten >= contentLength;
        int bufferSize = getBufferSize();
        boolean requiresFlush = bufferSize > 0 && contentWritten >= bufferSize;
        if(isBodyFullyWritten || requiresFlush){
            commitSession();
        }

    }

    public void commitSession(){
        request.commit();
    }

    @Override
    public void sendError(int sc, String msg) throws IOException {
        commitSession();
        super.sendError(sc, msg);
    }

    @Override
    public void sendError(int sc) throws IOException {
        commitSession();
        super.sendError(sc);
    }

    @Override
    public void sendRedirect(String location) throws IOException {
        super.sendRedirect(location);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new ServletOutputStreamWrapper(super.getOutputStream(),this);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriterWrapper(super.getWriter(),this);
    }

    public void trackContentLength(char content){
        checkContentLength(1);
    }

    public void trackContentLength(Object content){
        trackContentLength(String.valueOf(content));
    }

    public void trackContentLength(byte[] content){
        checkContentLength(content == null ? 0 : content.length);
    }

    public void trackContentLength(char[] content){
        checkContentLength(content == null ? 0 : content.length);
    }

    public void trackContentLength(int content){
        trackContentLength(content);
    }

    public void trackContentLength(float content){
        trackContentLength(content);
    }

    public void trackContentLength(double content){
        trackContentLength(content);
    }

    public void trackContentLengthLn(){
        trackContentLength("\r\n");
    }

    public void trackContentLength(String content){
        checkContentLength(content.length());
    }


    public void doOnResponseCommitted(){
        if(!disableOnCommitted){
            commitSession();
            disableOnResponseCommitted();
        }else{
            log.debug("skip invoking on ");
        }
    }
}
