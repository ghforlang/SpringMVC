package com.nbu.edu.cn.asession.common.http.core;

import com.nbu.edu.cn.asession.core.ASession;
import com.nbu.edu.cn.exception.BaseException;
import com.nbu.edu.cn.exception.ResponseEnum;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Collections;
import java.util.Enumeration;

@Slf4j
public class ASessionHttpSession<S extends ASession> implements HttpSession {

    private ServletContext servletContext;

    private S expireASession;
    private boolean invalid;
    private boolean newCreate;

    public ASessionHttpSession(){super();}

    public ASessionHttpSession(ServletContext servletContext,S expireASession){
        this.expireASession = expireASession;
        this.servletContext = servletContext;
    }

    @Override
    public long getCreationTime() {
        checkState();
        return expireASession.expiredTime();
    }

    @Override
    public String getId() {
        return expireASession.getASessionId();
    }

    @Override
    public long getLastAccessedTime() {
        return expireASession.getLastAccessTime();
    }

    @Override
    public ServletContext getServletContext() {
        return servletContext;
    }

    @Override
    public void setMaxInactiveInterval(int interval) {
        expireASession.setMaxInactiveInterval(interval);
    }

    @Override
    public int getMaxInactiveInterval() {
        return expireASession.getMaxInactiveInterval();
    }

    @Override
    public HttpSessionContext getSessionContext() {
        return null;
    }

    @Override
    public Object getAttribute(String name) {
        checkState();
        return expireASession.getAttribute(name);;
    }

    @Override
    public Object getValue(String name) {
        checkState();
        return expireASession.getAttribute(name);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        checkState();
        return Collections.enumeration(expireASession.getAttributeNames());
    }

    @Override
    public String[] getValueNames() {
        checkState();
        return expireASession.getAttributeNames().toArray(new String[0]);
    }

    @Override
    public void setAttribute(String name, Object value) {
        checkState();
        expireASession.setAttribute(name,value);
    }

    @Override
    public void putValue(String name, Object value) {
        checkState();
        expireASession.setAttribute(name,value);
    }

    @Override
    public void removeAttribute(String name) {
        checkState();
        expireASession.removeAttribute(name);
    }

    @Override
    public void removeValue(String name) {
        checkState();
        expireASession.removeAttribute(name);
    }

    @Override
    public void invalidate() {
        checkState();
        this.invalid = true;

    }

    @Override
    public boolean isNew() {
        return newCreate;
    }

    protected boolean checkState() throws BaseException {
        if(invalid){
            log.warn("aSession失效，aSessionId:{}",expireASession.getASessionId());
            throw ResponseEnum.INVALID_A_SESSION.newException("aSession 无效 !");
        }
        return true;
    }

    public S getExpireASession(){
        return expireASession;
    }

    public void setExpireASession(S expireASession){
        this.expireASession = expireASession;
    }

    public boolean isInvalid(){
        return invalid;
    }

    public void setInvalid(boolean invalid){
        this.invalid = invalid;
    }

    public void setNewCreate(boolean newCreate){
        this.newCreate = newCreate;
    }

}
